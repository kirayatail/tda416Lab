package datastructures;
  
import java.util.*;

/**
 * Implementation of a queue by using a cyclic array
 * All objects may be stored in the the queue
 *
 * @author Bror Bjerner
 * @version (2007)
 */
public class ArrayQueue<E> implements Queues<E> {

    /**
     * Default capacity of an ArrayQueue
     */
    public static final int DEFAULT_CAPACITY = 10;

    private int front, rear;
    private E[] que;

    /**
     * Constructor for objects of class ArrayQueue.
     * @param cap The initializing capacity of the 
     *            queue
     */
    public ArrayQueue(int cap){
	que      = ( E[]) new Object[cap];
	front    = 0;
	rear     = -1;
    } //  constructor

    /**
     * Constructor for objects of class ArrayQueue.
     * The initializing capacity of the 
     *        queue is the default capacity.
     */
    public ArrayQueue() {
	this(DEFAULT_CAPACITY);
    }  //  constructor

    /**
     * Check if the queue is empty
     * @return true if queue empty, false otherwise 
     */
    public boolean isEmpty() {
	return rear == -1;
    }  //  isEmpty





 
    /**
     *  Gives the number of elements stored in the queue
     * 
     * @return  the number of elements stored
     */
    public int size() {
	return rear == -1 ? 0 
	                  : (que.length+rear-front)%que.length + 1;
    }  //  size

    /**
     * Gives the next element in the queue in FIFO order.
     * 
     * @return  the next element
     * @throws NoSuchElementException if queue is empty
     */
    public E front() {
	if (rear < 0)
	    throw new NoSuchElementException("Queue empty on front");
        else
	    return que[front];
    } //  front

    /**
     *  Gives the next element in the queue in FIFO order
     *  and take it away from the que.
     * 
     * @return  the next element
     * @throws NoSuchElementException  if queue is empty
     */
    public E dequeue() {
	if (rear < 0)
	    throw new NoSuchElementException("Queue empty on dequeue");
	else {
	    E res = que[front];
	    que[front] = null;
	    if ( front == rear ) {
		front = 0;
		rear  = -1;
	    }
	    else 
		front = (front + 1) % que.length;
	    return res;
	}
    }  //  dequeue









    /**
     * Stores the element in the rear of the queue.
     * If the array is full, the array is expanded.
     * 
     * @param elem the element to store
     * @return  
     */
    public void enqueue(E elem) {
	if ( size()  == que.length ) { // the array is full
	    E[] temp = (E [])new Object[ 3*que.length/2 + 1] ;
	    System.arraycopy( que, front, temp, 0, que.length - front );
	    System.arraycopy( que, 0, temp, que.length - front , front );
	    rear      = que.length;
	    front     = 0;
	    que       = temp;
	    que[rear] = elem;
	}
	else {
	    rear      = (rear+1) % que.length;
	    que[rear] = elem;
	}
    }  // enqueue
}  //  ArrayQueue
