package datastructures;

   
import  java.util.*; 

/**
 * A priorty queue 
 * for all kind of elements implementing Comparable.
 * The highest priority has the element which is
 * smallest according to the Comparator given to the construktor.
 * The implementation uses a heap to decrease the average complexity
 * of the methods.
 * @author (Bror Bjerner)
 * @version (2008)
 */
public class PriQueHeapComparator<E>
    implements PriorityQueues<E> {

    /** 
     *  Default capacity of the priority queue.  
     */
    public static final int DEFAULT_CAPACITY = 10;

    private E [] qe;
    private int  size;
    Comparator<E> comp;

    /**
     * Constructor for a priority queue
     * with defined start capacity.
     *
     * @param capacity the initial capacity
     */
    public PriQueHeapComparator(int capacity, Comparator<E> comp ) {
	size = 0;
	qe   = (E []) new Object [capacity+1];
        this.comp = comp;
    }  //  constructor PriQueHeapComparator


    /**
     * Constructor for a priority queue
     * with default capacity
     *
     * @param capacity the initial capacity
     */

    public PriQueHeapComparator(Comparator<E> comp ) {
	this( DEFAULT_CAPACITY, comp );
    }  //  constructor PriQueHeapComparator






    /**
     * Find out the number of elements in the priority queue.
     * @return the number of elements.
     */
    public int size() {
	return size;
    }  //  size

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
	return size == 0;
    }  //  isEmpty

    /**
     * Insert into the priority queue.
     * Duplicates are allowed.
     * 
     * @param elem the item to insert.
     */
    public void add( E elem ) {
	int hole = ++size;
	//  If more entries is needed the array is doubled
	if ( hole == qe.length ) {
	    E [] temp = (E []) new Object[ 2 * qe.length ];
	    for (int i = 0; i < qe.length; i++ )
		temp[i] = qe[i];
	    Arrays.fill( temp, qe.length+1, temp.length, null );
	    qe = temp;
	}  
	while ( hole > 1 && comp.compare( elem, qe[hole/2] ) < 0 ) {
	    qe[hole] = qe[hole/2];
	    hole /= 2;
	}
	qe[hole] = elem;
    }  //  add 

    /**
     * Find the smallest element in the priority queue.
     * @return the smallest element.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public E getMin( ) {
	if ( size == 0 )
	    throw new NoSuchElementException("PriQueHeap.minElement");
	else
	    return qe[1];
    } //  getMin




    /**
     * Remove the smallest element from the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public E removeMin( ) {
	if ( size == 0 )
	    throw new NoSuchElementException("PriQueHeap.removeMinElement");
	else {
	    int hole  = 1,
		child = 2;
    
	    qe[0] = qe[1]; // qu[1] is now a proper hole
	    while ( child+1 < size && 
		    ( comp.compare( qe[child],   qe[size]) < 0  || 
		      comp.compare( qe[child+1], qe[size]) < 0 )
		    || child+1 == size && 
		    comp.compare( qe[child], qe[size]) < 0 ) {
		if (child+1 != size && 
		    comp.compare( qe[child+1], qe[child]) < 0  )
		    child++;
	  
		qe[hole] = qe[child];
		hole     = child;
		child    *= 2;
	    }

	    qe[hole] = qe[size];
	    qe[size--] = null;
	    return qe[0];
	}
    }  //  removeMin

}  //  class PriQueHeapComparator
