package edu.chl.sebljunwmax.exercise;

import java.util.*;

/**
 * <p>
 * A clean and simple binary search tree, implemented with linked nodes and full
 * parent/child knowledge. When done, it should implement the operations Add,
 * Get, Remove, HasElement.
 * </p>
 * <p>
 * The nodes within (Entry class) should contain references to parent, left and
 * right child nodes, an integer id (which will constitute the sortable
 * identity) and (optionally) a generic payload, or data container.
 * 
 * @author max
 * 
 */
public class LinkedBST<E> {
	private Entry root;

	public LinkedBST() {
		root = null;
	}

	public void add(int id, E data) {
		if (this.root == null) {
			this.root = new Entry(id, data, null);
		} else {
			Entry duplicate = this.getEntry(id);
			if (duplicate != null) {
				duplicate.data = data;
			} else {
				this.recursiveAdd(this.root, id, data);
			}
		}

	}
	
	/**
	 * Recursive add function. Erland would be proud.
	 * 
	 * @pre candidate is a valid node, id is not a duplicate
	 * 
	 * @param candidate
	 * @param id
	 * @param data
	 */
	private void recursiveAdd(Entry candidate, int id, E data) {
		if (id < candidate.id) {
			if (candidate.left == null) {
				candidate.left = new Entry(id, data, candidate);
			} else {
				recursiveAdd(candidate.left, id, data);
			}
		} else if (id > candidate.id) {
			if (candidate.right == null) {
				candidate.right = new Entry(id, data, candidate);
			} else {
				recursiveAdd(candidate.right, id, data);
			}
		}
	}

	/**
	 * Uses a RemoveMin method to find a suitable node to replace the removed
	 * one.
	 * 
	 * @param id
	 *            - node to be removed
	 * @throws NoSuchElementException
	 *             - if specified node doesn't exist already.
	 */
	public void remove(int id) throws NoSuchElementException {
		Entry candidate = this.getEntry(id);
		Entry newCand = null;
		if(candidate == null){
			throw new NoSuchElementException();
		}
		
		Entry parent = candidate.parent;
		if(candidate.left == null && candidate.right == null){
			newCand = findMax(candidate.left);			
			newCand.right = candidate.right;
		} else if(candidate.left == null){
			newCand = candidate.right;
		} else if(candidate.right == null){
			newCand = candidate.left;
		}
		
		if(parent.left.id == candidate.id){
			parent.left = newCand;
		} else {
			parent.right = newCand;
		}
		if(newCand != null){
			newCand.parent = parent;
		}
	}
	
	private Entry findMax(Entry e){
		if(e.right == null){
			return e;
		} else {
			return findMax(e.right);
		}
	}
	/**
	 * 
	 * @param id
	 * @return data of the specified element.
	 * @throws NoSuchElementException
	 */
	public E get(int id) throws NoSuchElementException {
		Entry candidate = this.getEntry(id);
		if (candidate != null) {
			return candidate.data;
		} else {
			throw new NoSuchElementException();
		}
	}

	private Entry getEntry(int id) {
		Entry candidate = this.root;

		while (candidate != null) {
			if (candidate.id > id) {
				candidate = candidate.left;
			} else if (candidate.id < id) {
				candidate = candidate.right;
			} else if (candidate.id == id) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return if specified element exists
	 */
	public boolean hasElement(int id) {
		Entry candidate = this.root;

		while (candidate != null) {
			if (candidate.id < id) {
				candidate = candidate.left;
			} else if (candidate.id > id) {
				candidate = candidate.right;
			} else if (candidate.id == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Node class.
	 * 
	 * @author max
	 * 
	 * @param <E>
	 */

	public class Entry {
		public Entry parent;
		public Entry left;
		public Entry right;
		public int id;
		public E data;

		public Entry(int id, E data, Entry parent) {
			this.parent = parent;
			this.data = data;
			this.id = id;
			this.left = null;
			this.right = null;
		}

	}
}
