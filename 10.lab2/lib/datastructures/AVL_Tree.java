package datastructures;

import java.util.*;

/**
 *  A binary searchtree ordered by the
 *  method compareTo for the elements and
 *  perform height balancing when adding 
 *  or removing elements
 * 
 * @author (Bror Bjerner) 
 * @version (2007)
 */

public class AVL_Tree<E extends Comparable<? super E>> 
       extends BinarySearchTree<E> {

    private class AVL_Entry extends Entry {

        // The empty tree (i.e. null) is considered
        // to be of height 0
	int height;

        private AVL_Entry( E          element,
			   Entry  left, 
			   Entry  right,
			   Entry  parent ) {
            super( element, left, right, parent );
            checkHeight(this);
	}   //  constructor AVL_Entry

        private AVL_Entry( E          element,
			   Entry  parent   ) {
            super( element, null, null, parent );
            height = 1;
	} //  constructor AVL_Entry
       
    }  //  class AVL_Entry

    private int height( Entry t ) {
	if ( t == null )
	    return 0;
	else 
	    return ((AVL_Entry) t).height;
    } //  height

    private void checkHeight( Entry t ) {
	(( AVL_Entry ) t).height = 1 + Math.max( height(t.left ),
						 height(t.right )); 
    }  //  CheckHeight




    /**
     *  The constructor creates the empty tree
     */
    public AVL_Tree() {
	super();
    }  // constructor AVL_Tree

    /**
     * Add the element to its first proper empty place.
     * After the element is inserted the height balance
     * is checked and if nescessary restored.
     * @param elem the element to be included  
     */
    public boolean add(E elem) {
        if ( root == null )
	    root = new AVL_Entry( elem, null );
        else
	    addInAVL( elem , root );
        size++;
        return true; 
    } // add

    /**
     * Remove the first occurance of an element with the same key
     * as the argument element. 
     * If no element is removed false is returned,
     * otherwise true is returned.
     * After the element is removed the height balance
     * is checked and if nescessary restored.
     * 
     * @param elem any element with the searched key
     * @return true if an element has disapeared from the tree,
     *         false otherwise
     */
    public boolean remove( E elem ) {
        if ( root == null )
	    return false;
        else if ( root.element.compareTo(elem) == 0 && 
                  (root.left == null || root.right == null ) ){
	    root = root.left == null ? root.right : root.left;
            size--;
            return true;
	}
	else {
            int oldSize = size;
            remove( elem, root);
	    return size != oldSize;
	}
    } // remove 





    //  In order to make the iterator in 
    //  BinarySearchTree to work properly !!
    protected void removeThis( Entry t ) {
        remove( t.element, root );
    }  //  removeThis 

    private boolean addInAVL( E newElem, Entry t ) {
	if ( newElem.compareTo( t.element ) < 0 ) {
	    if ( t.left == null ) { 
		t.left = new AVL_Entry( newElem, t );
		checkHeight(t);
	    }
	    else {
		boolean left = addInAVL( newElem, t.left );
		if ( height(t.left) - height(t.right) > 1 ) {
		    if ( left )
			rotateRight( t );
		    else 
			doubleRotateRight( t );
		}
		else
		    checkHeight(t); 
	    }
            return true;
	}
	else {
	    if ( t.right == null ) { 
		t.right = new AVL_Entry( newElem, t );
                checkHeight(t);
	    }
            else {
		boolean left = addInAVL( newElem, t.right );
		if ( height(t.right) - height(t.left) > 1 ) {
		    if ( left )
			doubleRotateLeft( t );
		    else 
			rotateLeft( t );
		}
		else
		    checkHeight(t); 
	    }
	}
	return false;
    }  //   addInAVL














    private void remove(  E elem, Entry x ) {
	if ( elem.compareTo( x.element ) == 0 )
	    if ( x.left == null || x.right == null ) {
		Entry newX =  x.left == null ? x.right : x.left;
		if ( newX != null )
		    newX.parent = x.parent;
		if ( x.parent.left == x )
		    x.parent.left = newX;
		else
		    x.parent.right = newX;
		size--;
		return;
	    }
	    else {
		x.element = findRefToMostRight( x.left ).element;
		remove( x.element, x.left );
		if ( height( x.right ) - height( x.left ) > 1 )
		    if ( height( x.right.right ) < 
			 height( x.right.left ) )
			doubleRotateLeft( x );
		    else
			rotateLeft( x );
		else 
		    checkHeight( x );
	    }
	else 
	    if ( elem.compareTo( x.element ) < 0 ) {
		if ( x.left != null ) {
		    remove( elem, x.left );
		    if ( height( x.right ) - height( x.left ) > 1 )
			if ( height( x.right.right ) < 
			     height( x.right.left ) )
			    doubleRotateLeft( x );
			else
			    rotateLeft( x );
		    else 
			checkHeight( x );
		}
	    }
	    else 
		if ( x.right != null ) {
		    remove( elem, x.right );
		    if ( height( x.left ) - height( x.right ) > 1 )
			if ( height( x.left.left ) < 
			     height( x.left.right ) )
			    doubleRotateRight(x);
			else
			    rotateRight(x);
		    else
			checkHeight( x );
		}
    }  // remove private version              
          

     /* Rotera 1 steg i högervarv, dvs 
               x'                 y'
              / \                / \
             y'  C   -->        A   x'
            / \                    / \  
           A   B                  B   C
     */
   private void rotateRight( Entry x ) {
        Entry   y = x.left;
        E    temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left    = y.left;
        if ( x.left != null )
	    x.left.parent   = x;
        y.left    = y.right;
        y.right   = x.right;
        if ( y.right != null )
	    y.right.parent  = y;
        x.right   = y;
        checkHeight( y );
        checkHeight( x );
    } //   rotateRight
   
     /* Rotera 1 steg i vänstervarv, dvs 
               x'                 y'
              / \                / \
             A   y'  -->        x'  C
                / \            / \  
               B   C          A   B   
     */
   private void rotateLeft( Entry x ) {
        Entry  y  = x.right;
        E temp    = x.element;
        x.element = y.element;
        y.element = temp;
        x.right   = y.right;
        if ( x.right != null )
	    x.right.parent  = x;
        y.right   = y.left;
        y.left    = x.left;
        if ( y.left != null )
	    y.left.parent   = y;
        x.left    = y;
        checkHeight( y );
        checkHeight( x );
    } //   rotateLeft
    
















     /* Rotera 2 steg i högervarv, dvs 
               x'                  z'
              / \                /   \
             y'  D   -->        y'    x'
            / \                / \   / \
           A   z'             A   B C   D
              / \  
             B   C  
     */
   private void doubleRotateRight( Entry x ) {
        Entry   y = x.left,
	        z = x.left.right;
        E       e = x.element;
        x.element = z.element;
        z.element = e;
        y.right   = z.left;
        if ( y.right != null )
	    y.right.parent = y;
        z.left    = z.right;
        z.right   = x.right;
        if ( z.right != null )
	    z.right.parent = z;
        x.right   = z;
        z.parent  = x;
        checkHeight( z );
        checkHeight( y );
        checkHeight( x );
    }  //  doubleRotateRight

    /* Rotera 2 steg i vänstervarv, dvs 
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \  
             B   C  
     */
    private void doubleRotateLeft( Entry x ) {
        Entry  y  = x.right,
	z  = x.right.left;
        E      e  = x.element;
        x.element = z.element;
        z.element = e;
        y.left    = z.right;
        if ( y.left != null )
	    y.left.parent = y;
        z.right   = z.left;
        z.left    = x.left;
        if ( z.left != null )
	    z.left.parent = z;
        x.left    = z;
        z.parent  = x;
        checkHeight( z );
        checkHeight( y );
        checkHeight( x );
    } //  doubleRotateLeft

}  //  class AVL_Tree

