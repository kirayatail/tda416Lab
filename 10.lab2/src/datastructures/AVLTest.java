package datastructures;

import datastructures.BinarySearchTree.Entry;

public class AVLTest {
	public static void main(String[] args){
		SplayTree<Integer> at = new SplayTree<Integer>();
		at.add(1);
		at.add(2);
		at.add(3);
		at.add(4);
		at.add(5);
		at.add(6);
		at.add(7);
		at.add(8);
		at.add(9);
		at.add(10);
		at.add(11);
		at.add(12);
		at.add(13);
		at.add(14);
		at.add(15);
		
		recursivePrint(at.root);
	}
	
	private static void recursivePrint(Entry e){
		
		if(e != null){
			String toPrint = e.element+" left:";
			if(e.left != null){
				toPrint += e.left.element+" right:";
			} else {
				toPrint += "null right:";
			}
			
			if(e.right != null){
				toPrint += e.right.element+"";
			} else {
				toPrint += "null";
			}
			System.out.println(toPrint);
			
			recursivePrint(e.left);
			recursivePrint(e.right);
		}
	}
}
