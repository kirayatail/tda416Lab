
package testSortCol;

import datastructures.*;

/**
 * This is a regular <tt>BinarySearchTree</tt>,
 * extended with the method <tt>get</tt>, see below.
 * 
 * @author (Bror Bjerner) 
 * @version (2007)
 */
public class BSTwithGet<E extends Comparable<? super E>>
       extends BinarySearchTree<E>
       implements CollectionWithGet<E> {

	/**
	 * Constructor for objects of class BSTwithGet
	 */
	public BSTwithGet() {
	    super();
	} // constructor BSTwithGet

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
	public E get(E e) {
	     Entry t = find(e,root);
	     return t == null ? null : t.element;
    }  // get
    
}   // class BSTwithGet

