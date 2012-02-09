package datastructures;

import testSortCol.CollectionWithGet;

public class SplayTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E>{
	
	
	@Override
	public E get(E e) {
		Entry candidate = root;
		int comparison;
		while(candidate != null  && (comparison = candidate.element.compareTo(e)) != 0){
			if(comparison < 0){
				candidate = candidate.right;
			} else {
				candidate = candidate.left;
			}
		}
		
		if(candidate != null){
			splay(candidate);
			return candidate.element;
		} else {
			return null;
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
				rotateRight(x);
			} else {
				rotateLeft(x);
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
			if(x == gp.left.left){
				rotateDoubleRight(x);
			} else if(x == gp.left.right){
				rotateLeftRight(x);
			} else if(x == gp.right.left){
				rotateRightLeft(x);
			} else {
				rotateDoubleLeft(x);
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
	
	 /* Rotera 1 steg i högervarv, dvs 
	  * 
	  * Här känner vi inledningsvis till elementet som skall gå mot toppen,
	  * till skillnad från AVL där vi tittar uppifrån och ned på noderna.
	  * 
	  * 
	  * 
	    y'                 x'
	   / \                / \
	  x'  C   -->        A   y'
	 / \                    / \  
	A   B                  B   C
	*/
	private void rotateRight(Entry x) {
		Entry y = x.parent;
		y.left = x.right;
		if(y.left != null){
			y.left.parent = y;
		}
		x.right = y;
		x.parent = y.parent;
		y.parent = x;
		
	} //   rotateRight

/* Rotera 1 steg i v�nstervarv, dvs 
    y'                 x'
   / \                / \
  A   x'  -->        y'  C
     / \            / \  
    B   C          A   B   
*/
	private void rotateLeft(Entry x) {
		Entry y = x.parent;
		y.right = x.left;
		if(y.right != null){
			y.right.parent = y;
		}
		x.left = y;
		x.parent = y.parent;
		y.parent = x;
	} // rotateLeft

/* Rotera 2 steg vänster
			z'					x'
		   / \				   / \
		  A   y' 	-->		  y'  D
  			 / \			 / \
			B   x'			z'	C
			   / \		   / \
			  C	  D		  A	  B
 */
	private void rotateDoubleLeft(Entry x){
		Entry y = x.parent;
		Entry z = y.parent;
		
		z.right = y.left;
		y.right = x.left;
		
		if(z.right != null){
			z.right.parent = z;
		}
		if(y.right != null){
			y.right.parent = y;
		}
		y.left = z;
		x.left = y;
		x.parent = z.parent;
		y.parent = x;
		z.parent = y;
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
	private void rotateDoubleRight(Entry x){
		Entry y = x.parent;
		Entry z = y.parent;
		
		z.left = y.right;
		y.left = x.right;
		
		if(z.left != null){
			z.left.parent = z;
		}
		if(y.left != null){
			y.left.parent = y;
		}
		
		y.right = z;
		x.right = y;
		x.parent = z.parent;
		y.parent = x;
		z.parent = y;		
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
	private void rotateLeftRight(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;
		
		y.right = x.left;
		z.left = x.right;
		
		if(y.right != null){
			y.right.parent = y;
		}
		if(z.left != null){
			z.left.parent = z;
		}
		
		x.left = y;
		x.right = z;
		
		x.parent = z.parent;
		y.parent = x;
		z.parent = x;

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
	private void rotateRightLeft(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;
		
		z.right = x.left;
		y.left = x.right;
		
		if(z.right != null){
			z.right.parent = z;
		}
		if(y.left != null){
			y.left.parent = y;
		}
		
		x.left = z;
		x.right = y;
		
		x.parent = z.parent;
		y.parent = x;
		z.parent = x;

	} // rotateRightLeft

}
