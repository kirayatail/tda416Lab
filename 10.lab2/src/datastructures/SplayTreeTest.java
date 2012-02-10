package datastructures;

import datastructures.BinarySearchTree.Entry;

public class SplayTreeTest {

	public static void main(String[] args) {
		SplayTree<Integer> sp = new SplayTree<Integer>();

		sp.add(5);
		sp.add(2);
		sp.add(7);
		sp.add(6);
		sp.add(3);
		sp.add(4);
		sp.add(1);
		sp.add(8);
		sp.add(9);
		/*
		 * should look like this:
		 * 
		 * <pre>
		 *            5
		 *          /   \
		 *        2       7
		 *       / \     / \
		 *      1   3   6   8
		 *           \       \
		 *            4       9
		 * </pre>
		 */

		if (checkBST(sp.root)) {
			System.out.println("All elements are sorted correctly");
		} else {
			System.out.println("Error while checking if elements are sorted");
		}
		Entry threeCand = sp.root.left.right;
		if (threeCand.element.equals(3)) {
			System.out.println("The left-right child of the root is correct");
		} else {
			System.out.println("Error when comparing left-right child of root");
		}

		sp.get(3);
		/*
		 * should look like this:
		 * 
		 * <pre>
		 *            3
		 *          /   \
		 *        2       5
		 *       /       / \
		 *      1       4   7
		 *                 / \
		 *                6   8
		 *                     \
		 *                      9
		 * </pre>
		 */
		int root = sp.root.element; // 3
		int right = sp.root.right.element; // 5
		int rightLeft = sp.root.right.left.element; // 4
		int rightRightLeft = sp.root.right.right.left.element; // 6

		if (root == 3 && right == 5 && rightLeft == 4 && rightRightLeft == 6) {
			System.out.println("Splays correctly and looks like our model");
		} else {
			System.out
					.println("The elements aren't ordered like our model, Wrong!");
		}

	}

	private static boolean checkBST(Entry e) {
		boolean flag = true;
		if (e != null) {
			if (e.left != null) {
				if (e.element.compareTo(e.left.element) > 0) {
					if (!checkBST(e.left))
						flag = false;
				} else {
					return false;
				}
			}
			if (e.right != null) {
				if (e.element.compareTo(e.right.element) < 0) {
					if (!checkBST(e.right))
						flag = false;

				} else {
					return false;
				}
			}
		}
		return flag;
	}
}
