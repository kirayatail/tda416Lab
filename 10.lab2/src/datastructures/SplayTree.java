package datastructures;

import java.util.List;

import datastructures.BinarySearchTree.Entry;

import testSortCol.CollectionWithGet;

public class SplayTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E>{
	
	public static void main(String[] args){
		SplayTree<Integer> sp = new SplayTree<Integer>();
		sp.add(3);
		sp.add(4);
		sp.add(2);
		sp.add(5);
		sp.add(9);
		sp.add(6);
		System.out.println(""+sp.get(5));
	}
	
	@Override
	public E get(E e) {
		if(root != null && e != null){
			return this.findSplayClosest(e, this.root);
			// Alternativ version.
//			Entry candidate =  this.splayFind(e, this.root);
//			if (candidate != null) {
//				return this.root.element;
//			} else {
//				return null;
//			}
		} else {
			return null;
		}
	}
	
	/**
	 * Finds the Entry with the specified element and splays on it, or the closest node if not found.
	 * @param e
	 * @param x
	 * @return target element or null if not found
	 */
	private E findSplayClosest(E e, Entry x){
		
		int comparison = x.element.compareTo(e);
		if(comparison > 0){
			if(x.left != null){
				return findSplayClosest(e, x.left);
			} else {
				splay(x);
				return null;
			}
		} else if(comparison < 0){
			if(x.right != null){
				return findSplayClosest(e, x.right);
			} else {
				splay(x);
				return null;
			}
		} else {
			splay(x);
			return this.root.element;
		}
	}
	/*
	 * Add och Remove kan ärvas från BST utan vidare.
	 * 
	 */
	
	private void splay(Entry x){
		// PSEUDOKOD!! \\
		
		/*
		 * If x == root
		 * 		do nothing and return.
		 */
		if(this.root == x){
			return;
		}
		
		/*
		 * If x's parent == root
		 * 		if x is left kid
		 * 		then rotate right
		 * 		
		 * 		otherwise rotate left
		 * 
		 * 		root = x
		 */
		else if(this.root == x.parent){
			if(x == x.parent.left){
				x = rotateRight(x);
			} else {
				x = rotateLeft(x);
			}
			this.root = x;
		}
		/*
		 * Get x's grandparent. (save for later)
		 */
		else {
			Entry gp = x.parent.parent;

		/*
		 * If x is the ... 
		 * 		left-left kid   --  rotate double right
		 * 
		 * 		left-right kid  --  rotate left-right
		 * 		
		 * 		right-left kid  --  rotate right-left
		 * 
		 * 		right-right kid --  rotate double left
		 * 
		 * 		(this might seem confusing, but rotations and ancestry don't match)
		 */
			boolean foundX = false;
			if(gp.left != null){ // x might be left-something
				if(x == gp.left.left){
					x = rotateDoubleRight(x);
					foundX = true;
				} else if(x == gp.left.right) { // left-right
					x = rotateLeftRight(x);
					foundX = true;
				}
			}
			if(gp.right != null && !foundX){
				if(x == gp.right.left){
					x = rotateRightLeft(x);
				} else {
					x = rotateDoubleLeft(x);
				}
			}
		/*
		 * If root == x's grandparent
		 * 		then root = x
		 */
			if(this.root == gp){
				this.root = x;
			}
		}
		/*
		 * Splay x again
		 */
		splay(x);
	}
	
	 /* Rotera 1 steg i v�nstervarv, dvs 
	    y'                 x'
	   / \                / \
	  A   x'  -->        y'  C
	     / \            / \  
	    B   C          A   B   
	*/
		private Entry rotateLeft(Entry x) {
			Entry y = x.parent;
			E tmp = y.element;
			y.element = x.element;
			x.element = tmp;
			
			y.right = x.right;
			x.right = x.left;
			x.left = y.left;
			y.left = x;
			
			if(y.right != null){
				y.right.parent = y;
			}
			if(x.left != null){
				x.left.parent = x;
			}
			return y;
		} // rotateLeft

	/* Rotera 1 steg i högervarv, dvs 
	  * 
	  * Här känner vi inledningsvis till elementet som skall gå mot toppen,
	  * till skillnad från AVL där vi tittar uppifrån och ned på noderna.
	  * 
	  * 
	    y'                 x'
	   / \                / \
	  x'  C   -->        A   y'
	 / \                    / \  
	A   B                  B   C
	*/
	private Entry rotateRight(Entry x) {
		Entry y = x.parent;
		E tmp = y.element;
		y.element = x.element;
		x.element = tmp;
		
		y.left = x.left;
		x.left = x.right;
		x.right = y.right;
		y.right = x;
		if(y.left != null){
			y.left.parent = y;
		}
		if(x.right != null){
			x.right.parent = x;
		}	
		return y;
	} //   rotateRight

/* Rotera 2 steg vänster
			z'					x'
		   / \				   / \
		  A   y' 	-->		  y'  D
  			 / \			 / \
			B   x'			z'	C
			   / \		   / \
			  C	  D		  A	  B
 */
	private Entry rotateDoubleLeft(Entry x){
		Entry y = x.parent;
		Entry z = y.parent;
		
		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;
		
		z.right = x.right; 	// D
		y.right = x.left;	// C
		x.right = y.left;	// B
		x.left = z.left;	// A
		
		z.left = y;
		y.left = x;
				
		if(z.right != null){
			z.right.parent = z;
		}
		if(y.right != null){
			y.right.parent = y;
		}
		if(x.right != null){
			x.right.parent = x;
		}
		if(x.left != null){
			x.left.parent = x;
		}
		
		return z;
	}

/* Rotera 2 steg höger
			z'				x'
		   / \			   / \
		  y'  D 		  A	  y'
		 / \	 -->		 / \
		x'  C				B	z'
	   / \					   / \
	  A   B					  C	  D
 */
	private Entry rotateDoubleRight(Entry x){
		Entry y = x.parent;
		Entry z = y.parent;
		
		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;
		
		z.left = x.left; 	// A
		y.left = x.right;	// B
		x.left = y.right;	// C
		x.right = z.right;	// D
		
		z.right = y;
		y.right = x;
				
		if(z.left != null){
			z.left.parent = z;
		}
		if(y.left != null){
			y.left.parent = y;
		}
		if(x.left != null){
			x.left.parent = x;
		}
		if(x.right != null){
			x.right.parent = x;
		}
		return z;
	}


/* Rotera vänster sen höger 
 * 
 * Även denna och dess systermetod är omarbetade för att fokusera på elementet x som ska uppåt.
 * 
    z'                  x'
   / \                /   \
  y'  D   -->        y'    z'
 / \                / \   / \
A   x'             A   B C   D
   / \  
  B   C  
*/
	private Entry rotateLeftRight(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;
		
		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;
		
						// A OK
		y.right = x.left; 	// B
		x.left = x.right; 	// C
		x.right = z.right;	// D
		
		z.right = x;
		x.parent = z;
		
		if(y.right != null){
			y.right.parent = y;
		}
		if(x.right != null){
			x.right.parent = x;
		}
		return z;
	} // rotateLeftRight

/* Rotera höger sen vänster 
    z'                  x'
   / \                /   \
  A   y'   -->       z'    y'
     / \            / \   / \
    x'  D          A   B C   D
   / \  
  B   C  
*/
	private Entry rotateRightLeft(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;
		
		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;
		
						// D OK
		y.left = x.right;	// C
		x.right = x.left;	// B
		x.left = z.left;	// A
		
		z.left = x;
		x.parent = z;

		if(y.left != null){
			y.left.parent = y;
		}
		if(x.left != null){
			x.left.parent = x;
		}
		return z;
	} // rotateRightLeft
	
	protected Entry splayFind(E elemement, Entry candidate) {
		if (candidate == null) {
			return null;
		}
		int comparison = elemement.compareTo(candidate.element);
		if (comparison < 0) {
			if (candidate.left == null) {
				splay(candidate);
				return null;
			} else {
				return splayFind(elemement, candidate.left);
			}
		} else if (comparison > 0) {
			if (candidate.right == null) {
				splay(candidate);
				return null;
			} else {
				return splayFind(elemement, candidate.right);
			}
		} else {
			splay(candidate);
			return candidate;
		}
	}

}
