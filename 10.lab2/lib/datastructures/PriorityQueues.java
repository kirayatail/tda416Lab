package datastructures;

/**
 * PriorityQueue interface for priority queues
 * for all kind of elements implementing Comparable.
 * The highest priority has the element which is
 * smallest according to the method compareTo in Comparable
 * or according to a given Comparator.
 * 
 * @author (Bror Bjerner)
 * @version (2007)
 */
public interface PriorityQueues<E> {

    /**
     * Find out the number of elements in the priority queue.
     * @return the number of elements.
     */
    int size( );

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    boolean isEmpty( );

    /**
     * Add an element into the priority queue.
     * Duplicates are allowed.
     * @param elem the element to insert.
     */
    void  add( E elem );

    /**
     * Find the smallest element in the priority queue.
     * @return the smallest element.
     * @exception NoSuchElementException if the priority queue is empty.
     */
    E getMin( );

    /**
     * Remove the smallest element from the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     * @return the smallest element.
     */
    E removeMin( );

}  //  interface PriorityQueue
