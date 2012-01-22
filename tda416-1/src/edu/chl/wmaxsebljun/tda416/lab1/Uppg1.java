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

	public static void main(String[] args) {
		/*
		 * Vill testa: lägga till strängar, printa dem printa en tom lista testa
		 * att exceptions kastas: getFirst/removeFirst och addFirst testa
		 * metoder exist positivt och negativt testa empty
		 */

		Uppg1 testList = new Uppg1(3);
		try { // testa exception getFirst från tom lista
			String str = testList.getFirst();
			System.out.println("Exception från tom lista kastades inte!");
		} catch (NoSuchElementException e) {
			System.out.println("Exception kastades från getFirst, allt ok");
		}
		try { // testa exception removeFirst från tom lista
			testList.removeFirst();
			System.out.println("Exception från tom lista kastades inte!");
		} catch (NoSuchElementException e) {
			System.out.println("Exception kastades från removeFirst, allt ok");
		}
		System.out
				.println("Printar tom lista, bör se ut som [  ]: " + testList);

		testList.addFirst("Cesar");
		testList.addFirst("Bertil");
		testList.addFirst("Adam");

		try { // testa exception för full lista
			testList.addFirst("Xerxes");
			System.out.println("Exception från full lista kastades inte!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception kastades från addFirst, allt ok");
		}

		System.out
				.println("Printar ABC ur bokstaveringsalfabetet: " + testList);

		testList.removeFirst();
		System.out.println("Printar Bertil Cesar: " + testList);

		if (!testList.exist("Adam")) {
			System.out.println("Adam finns inte längre, allt ok");
		} else {
			System.out.println("Adam finns, Något är fel");
		}

		if (testList.exist("Bertil")) {
			System.out.println("Bertil finns, allt ok");
		} else {
			System.out.println("Bertil finns inte, Något är fel");
		}

		testList.removeFirst();
		testList.removeFirst();
		if (testList.empty()) {
			System.out.println("Listan tom, allt ok");
		} else {
			System.out.println("Listan inte tom, Något är fel");
		}
	}

	/**
	 * Adds an element to the first position in the list.
	 * 
	 * @param element
	 *            the string to add to the list
	 * @throws IndexOutOfBoundsException
	 *             if there is no room for the element
	 */
	public void addFirst(String element) throws IndexOutOfBoundsException {
		if (size < elements.length) {
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

	/**
	 * Gets the first string in the list if any.
	 * 
	 * @return the first element
	 * @throws IndexOutOfBoundsException
	 *             if the list is empty
	 */
	public String getFirst() throws NoSuchElementException {
		if (!this.empty()) {
			return elements[0];
		} else {
			throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
		}
	}

	/**
	 * Removes the first element from the list if any. Doesn't return the
	 * element since that is the responsibility of getFirst().
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public void removeFirst() throws NoSuchElementException {
		if (!this.empty()) {
			this.shift(-1);
			this.size--;
		} else {
			throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
		}
	}

	/**
	 * Checks if a string already has been added to the list.
	 * 
	 * @param element
	 *            the string to look for
	 * @return <code>true</code> if the element already has been added
	 */
	public boolean exist(String element) {
		boolean found = false;
		for (int i = 0; i < this.size; i++) {
			if (this.elements[i].equals(element)) {
				found = true;
			}
		}

		return found;
	}

	public String toString() {
		String result = "[ ";
		for (int i = 0; i < this.size; i++) {
			result += this.elements[i];
			if (i < this.size - 1) {
				result += ", ";
			}
		}
		result += " ]";
		return result;
	}

	private void shift(int offset) {
		String[] temp = new String[this.elements.length];
		for (int i = 0; i < this.size; i++) {
			if (i + offset >= 0 && i + offset < this.elements.length) {
				temp[i + offset] = this.elements[i];
			}
		}
		this.elements = temp;
	}
}