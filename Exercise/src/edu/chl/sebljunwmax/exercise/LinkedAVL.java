package edu.chl.sebljunwmax.exercise;

import java.util.NoSuchElementException;

public class LinkedAVL<E> extends LinkedBST<E> {
	private AVLEntry root;

	@Override
	public void add(int id, E data) {
		if (this.root == null) {
			this.root = new AVLEntry(id, data, null);
		} else {
			recursiveAVLAdd(this.root, id, data);
		}
	}

	/**
	 * Nifty thing
	 * 
	 * @param e
	 * @return
	 */
	private int height(Entry e) {
		if (e.left == null && e.right == null) {
			return 0;
		} else {
			return 1 + Math.max(height(e.left), height(e.right));
		}

	}

	/**
	 * Recursive add which updates the weight of all affected nodes.
	 * 
	 * For each node: If the new node is the only child of the candidate, the
	 * weight of this node and all parents will be changed by 1. If there is a
	 * sibling, weight change is 0. If the new node will be a lower descendant
	 * of the candidate, the weight will be changed according to the returned
	 * value from this candidates child.
	 * 
	 * weight subtraction or addition is determined by the direction of the
	 * affected child (left negative, right positive).
	 * 
	 * @param candidate
	 *            - each node along the way, should be called with root
	 * @param id
	 * @param data
	 * @return absolute value of how much weight should be changed for all
	 *         parents
	 */
	private int recursiveAVLAdd(AVLEntry candidate, int id, E data) {
		int bubbleValue = 0;
		/*
		 * Bubblevalue is a value that should bubble recursively all the way up
		 * to the root, and determines whether the weight of the node will be
		 * changed or not.
		 * 
		 * At the point of actual insertion, a pre-existing sibling will give 0
		 * to bubbleValue (new level has not been reached), no sibling will give
		 * 1.
		 * 
		 * Each node on the way up will determine whether (based on which leg
		 * the call went through) the value should be subtracted or added to the
		 * own weight.
		 */

		if (id < candidate.id) { // Go left, and possibly minus on own weight
			if (candidate.right == null) { // flag for no sibling.

				bubbleValue = 1; // may be changed five rows down if recursive
									// call occurs.
			}
			if (candidate.left == null) { // do the add
				candidate.left = new AVLEntry(id, data, candidate);
			} else { // overwrite bubbleValue with what happens further down
				bubbleValue = recursiveAVLAdd(candidate.left, id, data);
			}
			// this candidates weight should be subtracted with bubbleValue
			// which may be 0 or 1 from recursion, or determined by existing
			// sibling to the new node.
			candidate.weight -= bubbleValue;

		} else if (id > candidate.id) {
			// Same as above, but the other way around.

			if (candidate.left == null) {
				bubbleValue = 1;
			}

			if (candidate.right == null) {
				candidate.right = new AVLEntry(id, data, candidate);
			} else {
				bubbleValue = recursiveAVLAdd(candidate.right, id, data);
			}
			candidate.weight += bubbleValue;
		} else {
			// Just update data, bubbleValue must be 0.
			candidate.data = data;
			bubbleValue = 0;
		}
		return bubbleValue;
	}

	@Override
	public void remove(int id) throws NoSuchElementException {
		// TODO Auto-generated method stub
		super.remove(id);
	}

	public class AVLEntry extends LinkedBST<E>.Entry {
		public int weight;
		public AVLEntry parent;
		public AVLEntry left;
		public AVLEntry right;

		public AVLEntry(int id, E data, AVLEntry parent) {
			super(id, data, parent);
			weight = 0;
		}
	}

}
