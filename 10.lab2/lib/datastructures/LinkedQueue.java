package datastructures;

  
import java.util.*;
/**
 * Implementation of a queue by using a linked structure.
 * All objects may by stored in the the queue.
 * 
 * @author Bror Bjerner
 * @version (2007)
 */
public class LinkedQueue<E> implements Queues<E> {

    private Node first;
    private Node last;
    private int  size;

    private class Node {
	E      element;
	Node   next;

	Node( E element, Node next ) {
	    this.element = element;
	    this.next    = next;
	} // constructor Node
    }  //  class Node

    /**
     * Constructor for objects of class DymanicQueue
     */
    public LinkedQueue() {
	first = last = null;
	size  = 0;
    } // constructor LinkedQueue

    /**
     * Stores the element in the rear of the queueen
     * @param elem the element to store
     */
    public void enqueue(E elem) {
	if (last != null)
	    last = last.next = new Node(elem, null);
	else
	    first = last = new Node(elem, null);
	size++;
    } //  enqueue








    /**
     * Gives the next element in the queue in FIFO order
     * and take it away from the queue.
     * @return  the next element
     * @throws  NoSuchElementException if queue is empty	
     */
    public E dequeue() {
	try {  
	    E res = first.element;
	    first = first.next;  
	    if(first == null)  
		last = null;
	    size--;
	    return res;
	}
	catch(NullPointerException npe ) {
	    throw new NoSuchElementException("Queue is empty in dequeue");
	}
    }  // dequeue

    /**
     *  Gives the number of elements stored in the queue
     * 
     * @return  the number of elements stored
     */
    public int size() {
	return size;
    }  //  size

    /**
     * Gives the number of elements stored in the queue
     * @return  the number of elements stored
     */
    public boolean isEmpty()  {
	return first == null;
    }  // isEmpty

    /**
     * Gives the next element in the queue in FIFO order
     * @return  the next element
     * @throws NoSuchElementException if queue is empty
     */
    public E front() {
	try {  
	    return first.element;
	}
	catch(NullPointerException npe ) {
	    throw new NoSuchElementException("Queue is empty in front");
	}
    }  //  front

}  //  LinkedQueue

