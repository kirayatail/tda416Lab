package datastructures;

import testSortCol.CollectionWithGet;

public class SplayTree<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E>{
	
	
	@Override
	public E get(E e) {
		// TODO get
		return null;
	}
	/*
	 * Add och Remove kan ärvas från BST utan vidare.
	 * 
	 * 
	 */
	
	/**
	 * 
	 */
	private void splay(Entry n){
		// TODO Splay
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
