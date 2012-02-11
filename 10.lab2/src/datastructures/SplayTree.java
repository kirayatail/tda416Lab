package datastructures;

import testSortCol.CollectionWithGet;

/**
 * A data structure ensures quick access to elements recently accessed. Based on
 * a binary search tree elements are splayed to the top of the tree upon calls to
 * <code>get()</code>.
 * 
 * @author Max Witt, Sebastian Ljunggren
 * 
 * @param <E>
 *            the data type stored in the tree
 */
public class SplayTree<E extends Comparable<? super E>> extends
		BinarySearchTree<E> implements CollectionWithGet<E> {

	/**
	 * Looks for an element in the tree. If the element is found it is splayed
	 * to the top for fast future access.
	 * 
	 * @param e
	 *            the element too look for
	 * @return the element if it was found, <code>null</code> otherwise
	 */
	@Override
	public E get(E e) {
		if (root != null && e != null) {
			return this.findSplayClosest(e, this.root);
		} else {
			return null;
		}
	}

	/**
	 * Finds the Entry with the specified element and splays on it, or the
	 * closest node if not found.
	 * 
	 * @param the
	 *            element to look for
	 * @param candidate
	 *            the entry to look for e in
	 * @return target element or null if not found
	 */
	private E findSplayClosest(E e, Entry candidate) {
		int comparison = candidate.element.compareTo(e);
		if (comparison > 0) {
			if (candidate.left != null) {
				return findSplayClosest(e, candidate.left);
			} else {
				splay(candidate);
				return null;
			}
		} else if (comparison < 0) {
			if (candidate.right != null) {
				return findSplayClosest(e, candidate.right);
			} else {
				splay(candidate);
				return null;
			}
		} else {
			splay(candidate);
			return this.root.element;
		}
	}

	private void splay(Entry x) {
		if (this.root == x) {
			return;
		} else if (this.root == x.parent) {
			if (x == x.parent.left) {
				x = rotateRight(x);
			} else {
				x = rotateLeft(x);
			}
			this.root = x;
		} else {
			Entry gp = x.parent.parent;
			boolean foundX = false;
			if (gp.left != null) { // x might be left-something
				if (x == gp.left.left) {
					x = rotateDoubleRight(x);
					foundX = true;
				} else if (x == gp.left.right) { // left-right
					x = rotateLeftRight(x);
					foundX = true;
				}
			}
			if (gp.right != null && !foundX) {
				if (x == gp.right.left) {
					x = rotateRightLeft(x);
				} else {
					x = rotateDoubleLeft(x);
				}
			}
			if (this.root == gp) {
				this.root = x;
			}
		}
		splay(x);
	}

	/**
	 * Rotates an entry one step to the left.
	 * 
	 * <pre>
	 *   y'                 x'
	 *  / \                / \
	 * A   x'  -->        y'  C
	 *    / \            / \  
	 *   B   C          A   B
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
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

		if (y.right != null) {
			y.right.parent = y;
		}
		if (x.left != null) {
			x.left.parent = x;
		}
		return y;
	}

	/**
	 * Rotates an entry one step to the right.
	 * 
	 * <pre>
	 *     y'             x'
	 *    / \            / \
	 *   x'  C   -->    A   y'
	 *  / \                / \  
	 * A   B              B   C
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
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
		if (y.left != null) {
			y.left.parent = y;
		}
		if (x.right != null) {
			x.right.parent = x;
		}
		return y;
	}

	/**
	 * Rotates an entry to the left twice.
	 * 
	 * <pre>
	 *          z'                  x'
	 *         / \                 / \
	 *        A   y'     -->      y'  D
	 *           / \                 / \
	 *          B   x'              z'  C
	 *             / \             / \
	 *            C   D           A   B
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
	 */
	private Entry rotateDoubleLeft(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;

		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;

		z.right = x.right; // D
		y.right = x.left; // C
		x.right = y.left; // B
		x.left = z.left; // A

		z.left = y;
		y.left = x;

		if (z.right != null) {
			z.right.parent = z;
		}
		if (y.right != null) {
			y.right.parent = y;
		}
		if (x.right != null) {
			x.right.parent = x;
		}
		if (x.left != null) {
			x.left.parent = x;
		}

		return z;
	}

	/**
	 * Rotates an entry to the right twice.
	 * 
	 * <pre>
	 *          z'              x'
	 *         / \             / \
	 *        y'  D           A   y'
	 *       / \       -->       / \
	 *      x'  C               B   z'
	 *     / \                     / \
	 *    A   B                   C   D
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
	 */
	private Entry rotateDoubleRight(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;

		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;

		z.left = x.left; // A
		y.left = x.right; // B
		x.left = y.right; // C
		x.right = z.right; // D

		z.right = y;
		y.right = x;

		if (z.left != null) {
			z.left.parent = z;
		}
		if (y.left != null) {
			y.left.parent = y;
		}
		if (x.left != null) {
			x.left.parent = x;
		}
		if (x.right != null) {
			x.right.parent = x;
		}
		return z;
	}

	/**
	 * Rotates an entry to the left and then to the right.
	 * 
	 * <pre>
	 *     z'                  x'
	 *    / \                /   \
	 *   y'  D   -->        y'    z'
	 *  / \                / \   / \
	 * A   x'             A   B C   D
	 *    / \  
	 *   B   C
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
	 */
	private Entry rotateLeftRight(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;

		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;

		// A OK
		y.right = x.left; // B
		x.left = x.right; // C
		x.right = z.right; // D

		z.right = x;
		x.parent = z;

		if (y.right != null) {
			y.right.parent = y;
		}
		if (x.right != null) {
			x.right.parent = x;
		}
		return z;
	}

	/**
	 * Rotates an entry to the right and then the left.
	 * 
	 * <pre>
	 *    z'                  x'
	 *   / \                /   \
	 *  A   y'   -->       z'    y'
	 *     / \            / \   / \
	 *    x'  D          A   B C   D
	 *   / \  
	 *  B   C
	 * </pre>
	 * 
	 * @param x
	 *            the entry to rotate
	 * @return the new entry containing the same element as param x
	 */
	private Entry rotateRightLeft(Entry x) {
		Entry y = x.parent;
		Entry z = y.parent;

		E tmp = z.element;
		z.element = x.element;
		x.element = tmp;

		// D OK
		y.left = x.right; // C
		x.right = x.left; // B
		x.left = z.left; // A

		z.left = x;
		x.parent = z;

		if (y.left != null) {
			y.left.parent = y;
		}
		if (x.left != null) {
			x.left.parent = x;
		}
		return z;
	}
}
