package datastructures;

import java.util.*;
/**
 * Implementation of a stack using an array.
 * All types elements may be stored in the stack.
 *
 * @author (Bror Bjerner) 
 * @version (2007)
 */
public class ArrayStack<E> implements Stacks<E> {

    /**  Default capacity of an ArrayStack 
     */
    public static final int DEFAULT_CAPACITY = 10;

    private E[] s;
    private int top = -1;

    /**
     * Constructor for elements of class ArrayStack
     */
    public ArrayStack( int cap ) {
    	s = ( E [])(new Object[cap]);	
    }  //  constructor ArrayStack
    
    public ArrayStack() {
	this(DEFAULT_CAPACITY);
    }  //  constructor ArrayStack

    /**
     * The current number of elements in the stack
     * 
     * @return	the number of elements stored in the stack
     */
    public int size() {
	return top + 1;
    } //  size

    /**
     * Test whether the stack is empty
     * 
     * @return	true if the stack is empty, false otehrwise
     */
    public boolean isEmpty() {
	return top == -1;
    }  // isEmpty








    /**
     * Returns the elements in FILO order
     * 
     * @return  the top element of the stack
     * @throws NoSuchElementException if stack is empty
     */
    public E top() {
	try {
	    return s[top];
	}
	catch(ArrayIndexOutOfBoundsException aie ) {
	    throw new NoSuchElementException("in top");
	}   
    } // top

    /**
     * To add an element into the top of the stack.
     * If the array is full, the size of the array is doubled.
     * 
     * @param  element	the element to add to the stack
     */
    public void push(E element) {
	if (++top < s.length)
	    s[top] = element;
	else {
	    E[] temp = ( E [] ) new Object[3*s.length/2 + 1];
            System.arraycopy( s, 0, temp, 0, s.length );
            temp[top] = element;
	    s = temp;
	}  
    }  // push

    /**
     * Remove the top element from the stack
     * 
     * @return  the removed element
     * @throws NoSuchElementException if stack is empty
     */
    public E pop() {
	try {
	    E res = s[top];
	    s[top--] = null;
	    return res;
	}
	catch(ArrayIndexOutOfBoundsException aie ) {
	    throw new NoSuchElementException("Stack empty on pop");
	}
    }  // pop

}  // class ArrayStack
