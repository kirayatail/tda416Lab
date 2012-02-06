package edu.chl.sebljunwmax.exercise;

import java.util.NoSuchElementException;

public class LinkedAVL<E> extends LinkedBST<E>{
	
	
	
	@Override
	public void add(int id, E data) {
		super.add(id, data);
		
	}
	/**
	 * Nifty thing
	 * @param e
	 * @return
	 */
	private int height(Entry e){
		if(e.left == null && e.right == null){
			return 0;
		} else {
			return 1+Math.max(height(e.left),height(e.right));
		}
		
	}

	@Override
	public void remove(int id) throws NoSuchElementException {
		// TODO Auto-generated method stub
		super.remove(id);
	}

	public class AVLEntry extends LinkedBST<E>.Entry{
		public int weight;
		public AVLEntry(int id, E data, AVLEntry parent){
			super(id, data, parent);
			weight = 0;
		}
	}

}
