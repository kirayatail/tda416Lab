package edu.chl.wmaxsebljun.tda416.lab1;

public class Uppg1 {

	private String[] elements;
	private int size;

	public Uppg1() {
		this(20);
	}

	public Uppg1(int capacity) {
		elements = new String[capacity];
		size = 0;
	}

	public void addFirst(String element) {
		// TODO
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public String getFirst() {
		// TODO
		return "";
	}

	public void removeFirst() {
		this.shift(-1);
		this.size--;
	}

	public boolean exist(String element) {
		// TODO
		return false;
	}

	public String toString() {
		// TODO
		return "";
	}

	private void shift(int offset) {
		String[] temp = new String[this.elements.length];
		for(int i = 0; i < this.elements.length; i++) {
			if(i + offset >= 0 && i + offset < this.elements.length) {
				temp[i + offset] = this.elements[i];
			}
		}
		this.elements = temp;
	}
}
