package edu.chl.wmaxsebljun.tda416.lab1;

import java.util.NoSuchElementException;

public class Uppg1 {

	private String[] elements;
	private int size;
	private static final String EMPTY_LIST_MESSAGE = "The list is empty";

	public Uppg1() {
		this(20);
	}

	public Uppg1(int capacity) {
		elements = new String[capacity];
		size = 0;
	}

	public void addFirst(String element) {
		if(size < elements.length) {
			this.shift(1);
			this.elements[0] = element;
			this.size++;
		} else {
			throw new IndexOutOfBoundsException(
					"There is no room for additional elements.");
		}
	}

	public boolean empty() {
		return this.size == 0;
	}

	public String getFirst() {
		if(size > 0) {
			return elements[0];
		} else {
			throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
		}
	}

	public void removeFirst() {
		if(size > 0) {
			this.shift(-1);
			this.size--;
		} else {
			throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
		}
	}

	public boolean exist(String element) {
		boolean found = false;
		for(int i=0; i<this.size; i++){
			if(this.elements[i].equals(element)){
				found = true;
			}
		}
		
		return found;
	}

	public String toString() {
		String result = "[ ";
		for(int i = 0; i < this.size; i++) {
			result += this.elements[i];
			if(i < this.size - 1) {
				result += ", ";
			}
		}
		result += " ]";
		return result;
	}

	private void shift(int offset) {
		String[] temp = new String[this.elements.length];
		for(int i = 0; i < this.size; i++) {
			if(i + offset >= 0 && i + offset < this.elements.length) {
				temp[i + offset] = this.elements[i];
			}
		}
		this.elements = temp;
	}
}
