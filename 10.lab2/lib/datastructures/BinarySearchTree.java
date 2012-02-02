package datastructures;

import java.util.*;

/**
 *  A simple binary searchtree ordered by the
 *  method compareTo for the elements.
 * @author (Bror Bjerner) 
 * @version (2007)
 */

public class BinarySearchTree<E extends Comparable<? super E>>
       extends AbstractCollection<E>  {
       

    protected Entry root;
    protected int   size;

    protected  class Entry  {

        public E      element;
        public Entry  left, right, parent;

        public Entry( E element,
		      Entry  left, 
                      Entry  right,
                      Entry  parent   ) {

	    this.element = element; 
	    this.left    = left; 
	    this.right   = right; 
	    this.parent  = parent; 
      
	} //  constructor Entry
      
	public  Entry( E element, Entry parent) {
	    this( element, null, null, parent );
	} //  constructor Entry

    } //  class  Entry


    /**
     *  The constructor creates the empty tree.
     */
    public BinarySearchTree( ) {
	super();
	root = null;
	size = 0;
    }  // constructor BinarySearchTree
    



    /**
     * The number of objects in this collection.
     * @return the number of elements in the tree. 
     */
    public int size() {
	return size;
    }  // size

    /**
     * Create an iterator for elements in the tree in inorder.
     * @return the created iterator.
     */
    public Iterator<E> iterator() {
	return new BST_Iterator();
    }  //  iterator

    protected void addIn(E newElem, Entry t) {
	if ( newElem.compareTo( t.element ) < 0 )
	    if ( t.left == null )
		t.left = new Entry( newElem, t);
	    else
		addIn( newElem, t.left );
	else
	    if ( t.right == null )
		t.right = new Entry( newElem, t);
	    else
		addIn( newElem, t.right );
    }  //  addIn

    /**
     * Add the element into the three at first proper empty place
     * @param o the element to be included  
     * @returns true if the element is in included in the tree.
     */
    public boolean add( E elem ) {
        if ( root == null )
	    root = new Entry( elem, null );
        else
	    addIn( elem, root );
        size++;
        return true; 
    } // add












    protected Entry find( E elem, Entry t ) {
        
	if ( t == null )
	    return null;
        else {
	    int jfr = elem.compareTo( t.element );
	    if ( jfr  < 0 )
		return find( elem, t.left );
	    else if ( jfr > 0 )
		return find( elem, t.right );
	    else 
		return t;
        }
    }  //   find


    /**
     * Check if the element is in the the tree.
     * @param elem The element to check
     * @returns true if the element is contained in the tree,
     *          otherwise false is returned.  
     */ 
    public boolean contains( E elem ) {
        return find( elem, root ) != null;
    }  // contains 

    /**
     * Removes all of the elements from this tree
     */ 
    public void clear() {
	root = null;
        size = 0;   
    }   //  clear

    protected Entry findRefToMostRight( Entry t ) {
	if ( t.right == null )
	    return t;
        else 
            return findRefToMostRight( t.right );
    }  //   findRefToMostRight

    protected void removeLeaf( Entry leaf, Entry parent ) {
	if ( parent == null )
	    root = null;
	else if ( parent.left == leaf )
	    parent.left = null;
	else
	    parent.right = null;
    } // removeLeaf





    protected void removeThis( Entry t ) {
	if ( t.left == null )
	    if ( t.right == null )
		removeLeaf( t, t.parent );
	    else {
		t.element = t.right.element;
		t.left    = t.right.left;
		if ( t.left != null )
		    t.left.parent = t;
		t.right   = t.right.right;
		if ( t.right != null )
		    t.right.parent = t;
	    }
	else {
	    Entry bytEntry = findRefToMostRight( t.left );
	    t.element = bytEntry.element;
	    if (bytEntry == t.left ) {
		t.left = bytEntry.left;
		if (t.left != null)
		    t.left.parent = t;
	    }
	    else {
		bytEntry.parent.right = bytEntry.left;
		if ( bytEntry.left != null )
		    bytEntry.left.parent = bytEntry.parent;
	    }
	}
	size--;
    }  // removeThis

    /**
     * Remove the first occurance of an element for which 
     * compareTo with the argument yields 0. If no element 
     * is removed false is returned, otherwise true.  
     * @param elem element of Comarable
     * @return true if the tree has changed, otherwise false.
     */
    public boolean remove( E elem ) {
	Entry remElem = find( elem, root );
         if ( remElem == null )
	    return false;
	else {
            removeThis( remElem );
            return true;
	}
    }  // remove








    // An inner class to create an iterator for 
    // the collection of elements.
    protected class BST_Iterator implements Iterator<E> {
        private Stacks<Entry> nextOnTop  = new LinkedStack<Entry>();
        private Entry         lastNext   = null;
        private boolean       remAllowed = false; 

        protected BST_Iterator() {
	    for ( Entry p = root; p != null; p = p.left )
		nextOnTop.push( p ); 
        } // constructor BST_Iterator 
        
	public boolean hasNext() {
	    return ! nextOnTop.isEmpty();
	} // hasNext

	public E next() {
            lastNext =  nextOnTop.pop();
	    // throws NoSuchElementException if empty
            for ( Entry p = lastNext.right; p != null; p = p.left )
		nextOnTop.push( p );
            remAllowed = true; 
            return lastNext.element;
	} // next

	public void remove() {
	    if ( remAllowed ) {
		removeThis( lastNext );
		remAllowed = false;
	    } 
	    else
		throw new IllegalStateException();    
	} // remove
    }  //  classBST_Iterator
}  //  class BinarySearchTree
