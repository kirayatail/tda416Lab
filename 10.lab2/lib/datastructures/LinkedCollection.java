package datastructures;
  
import java.util.*;

/**
 * A minimal implementation of a collection
 * as a singly linked list.The elements are 
 * unordered and may contain duplicates.
 * As proposed in Collins book Data Structures and
 * the Java Collections Framework, chapter 7.3.
 *
 * @author Bror Bjerner
 * @version (070125)
 */
public class LinkedCollection<E>
    extends AbstractCollection<E> {

    /**
     * <tt> head </tt> is a referens to the first
     * element in the list.
     * <tt> head </tt> is <tt> null </tt> in the
     *  empty collection.
     */
    protected Entry head;


    /**
     * The class of objects used as nodes of the
     *  singly linked list
     */
    protected class Entry { 

        public E      element;
        public Entry  next;

        public Entry( E element, Entry next ) {
            this.element = element;
            this.next = next;
        }

    } // Entry






    /** 
     * The constructor of the collection.
     * The collection is initialized to be empty.
     */
    public LinkedCollection() {
        head = null;
    }  // constructor LinkedCollection

    /**
     * Compute the number of elements in the container.
     * @return The number of elements in the container.
     */
    public int size() {
	int count = 0;
        for ( Entry p = head; p != null; p = p.next )
            count++;
        return count;
    } // size

    /**
     * Create an iterator over the elements contained
     * in the collection.
     * @return the created iterator.
     */
    public Iterator<E> iterator() {
	return new LinkedCollectionIterator();
    }  // iterator 
    
    /**
     * Adds an element to the collection.
     * The element added last will be the first element 
     * given by the iterator and the first element 
     * added will be the last given by the iterator.
     * 
     * @param element the object to add into the list
     * @return true if the object has been added to the list.
     * @throws NullPointerException if parameter <tt>element<tt> is null. 
     */
    public boolean add( E element ) {
        if ( element == null )
	    throw new NullPointerException();
	else {
	    head = new Entry( element, head );
	    return true;
	}
    } // add

    /**
     * Decides if there are any elements in the collection.
     *
     * @return true if there are no elements in the collection, 
     *         otherwise false.
     * 
     */
    public boolean isEmpty() {  
        // not necessary, but more efficient
	return head == null;
    } // isEmpty

    /**
     * Remove all of the elements from this collection.
     */
    public void clear() {  
        // not necessary, but much more efficient
        // than to remove the elements one by one
    	head = null;
    } // isEmpty


    private class LinkedCollectionIterator
	implements Iterator<E>         {

        Entry   next, previous;
        boolean removeAllowed;

	LinkedCollectionIterator() {
	    next          = head;
	    previous      = null;
	    removeAllowed = false;
	} //  constructor LinkedCollectionIterator















	public boolean hasNext() {
	    return next != null;
	}  //  hasNext

	public E next() {
	    try {
		previous      = next;
		next          = next.next;
		removeAllowed = true;
		return previous.element;
	    }
	    catch(NullPointerException npe) {
		throw new NoSuchElementException();
	    } //  next

	} // next 

	public void remove() {
	    if ( removeAllowed ) {
		if ( previous == head )
		    head = head.next;
		else {
		    Entry p = head;
		    while ( p.next != previous )
			p = p.next;
		    p.next = p.next.next;
		}
		removeAllowed = false;
	    }
	    else
		throw new IllegalStateException();
	}  //  remove 

    }  // class LinkedCollectionIterator

} // LinkedCollection
