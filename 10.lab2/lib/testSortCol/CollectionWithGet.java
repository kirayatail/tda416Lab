package testSortCol;
import java.util.*;

/**
 * An interface for <tt>CollectionWithGet</tt>.
 * This is an interface used by <tt>TestMapWithCounter</tt>
 * to specify its collection of  <tt>TestMapEntry</tt>'s.
 * 
 * @author (Bror Bjerner) 
 * @version (2007)
 */
public interface CollectionWithGet<E extends Comparable<? super E>>
       extends Collection<E> {
   /**
    *  Find the first occurence of an element 
    *  in the collection that is equal to the argument
    *  <tt>e</tt> with respect to its natural order.
    *  I.e. <tt>e.compateTo(element)</tt> is 0.
    *  
    *  @param e The dummy element to compare to.
    *  @return  An element  <tt>e'</tt> in the collection
    *           satisfying <tt>e.compareTo(e') == 0</tt>.
    *           If no element is found, <tt>null</tt> is returned
    */
   public E get(E e);
   
}  
