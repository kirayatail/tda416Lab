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
	
	public LinkedBST(){
		
	}
	
	public void add(int id, E data){
		
	}
	/**
	 * Uses a RemoveMin method to find a suitable node to replace the removed one.
	 * 
	 * @param id - node to be removed
	 * @throws NoSuchElementException - if specified node doesn't exist already.
	 */
	public void remove(int id) throws NoSuchElementException{
		
	}
	
	/**
	 * 
	 * @param id
	 * @return data of the specified element.
	 * @throws NoSuchElementException
	 */
	public E get(int id) throws NoSuchElementException{
		
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return if specified element exists
	 */
	public boolean hasElement(int id){
		return true;
	}
	
	/**
	 * Node class.
	 * @author max
	 *
	 * @param <E>
	 */
	public class Entry<E>{
		private Entry<E> parent;
		private Entry<E> left;
		private Entry<E> right;
		private int id;
		private E data;
		
		public Entry(int id, E data, Entry<E> parent){
			
		}
		
	}
}
