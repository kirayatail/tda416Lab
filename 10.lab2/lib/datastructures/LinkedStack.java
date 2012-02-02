package datastructures;


import java.util.*; 

/**
 * Implementation of a stack using a linked structure.
 * All types elements may be stored in the stack.
 * 
 * @author (Bror Bjerner) 
 * @version (2007)
 */

public class LinkedStack<E> implements Stacks<E> {

    private int  size;
    private Node stack;

    /* The internal node element, that links the elements. */
    private class Node {
      
	E     element;
        Node  next;

        public Node( E element,  Node next ) {
            this.element = element;
            this.next    = next;
	}  //  constructor Node

    }  // class Node

    /**
     * Constructor for elements of class DynamicStack
     */
    public LinkedStack() {
	stack = null;
	size  = 0;
    } //  constructor LinkedStack

    /**
     * To add an element into the top of the stack 
     * @param elem the element to add to the stack
     */
    public void push(E elem) {
	stack = new Node(elem, stack);
	size++;
    }  //  push








    /**
     * Remove the top element from the stack
     * @return the removed element
     * @throws NoSuchElementException if stack is empty
     */
    public E pop() {
	try {
	    E res = stack.element;
	    stack = stack.next;
	    size--;
	    return res;
	}
	catch(NullPointerException npe ) {
	    throw new NoSuchElementException("Stack is empty in pop");
	}
    }  //  pop

    /**
     * Test whether the stack is empty
     * @return  true if the stack is empty, fals otherwise
     */
    public boolean isEmpty() {
	return stack == null;
    }  // isEmpty

    /**
     * The current number of elements in the stack
     * @return  the number of elements stored in the stack
     */
    public int size() {
	return size;
    }  //  size

    /**
     * return the elements in FILO order
     * @return the top element of the stack
     * @exception NoSuchElementException if stack is empty
     */
    public E top() {
	try {  
	    return stack.element;
	}
	catch(NullPointerException npe ) {
	    throw new NoSuchElementException("Stack is empty in top");
	}
    }  // top

} // class LinkedStack 

