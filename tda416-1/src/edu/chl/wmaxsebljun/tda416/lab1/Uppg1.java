package edu.chl.wmaxsebljun.tda416.lab1;

import java.util.NoSuchElementException;

/**
 * Enkel datastruktur med begränsad kapacitet. Vår implementation är enkel och rättfram vilket möjligen ger nackdelar i prestanda.
 * Listan fylls på med första elementet på plats 0 och behåller en logisk struktur där elementen står i traditionell ordning.
 * Eftersom det läggs vikt vid att hantera första elementet i listan vid varje givet tillfälle så sker många kopieringar och 
 * skiftningar av element för att behålla strukturen. En prestandamässigt bättre variant hade varit att spegelvända listan så att 
 * exempelvis första elementet hamnar på plats 0 och "nästa första" element hamnar på plats 1 samtidigt som en intern pekare håller 
 * koll på var listans huvud finns. Vi valde att inte implementera listan på det sättet för tydlighetens skull. 
 * Strukturen påminner om en blandning mellan en ArrayList och en Stack. Det vi saknas för en ArrayList är bl.a. add() som 
 * lägger till element sist i listan.
 * 
 * 
 * @author Sebastian Ljunggren, Max Witt grupp 10
 *
 */
public class Uppg1 {

	private String[] elements;
	private int size;
	private int p;
	private static final String EMPTY_LIST_MESSAGE = "The list is empty";
	private static final String FULL_LIST_MESSAGE = "The list is full and the element couldn't be added";
	
	/**
	 * Creates new list with room for 20 elements
	 * 
	 */
	public Uppg1() {
		this(20);
	}
	/**
	 * Creates new list with specified capacity
	 * 
	 * @param capacity number of elements
	 */
	public Uppg1(int capacity) {
		elements = new String[capacity];
		size = 0;
		p = 0;
	}
	
	/**
	 * Tests the entire class. Log output in Swedish.
	 * @param args
	 */
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
			System.out.println("Exception kastades från removeFirst, allt OK");
		}
		System.out
				.println("Printar tom lista, bör se ut som [  ]: " + testList);

		testList.addFirst("Cesar");
		testList.addFirst("Bertil");
		testList.addFirst("Adam");

		try { // testa getFirst och get
			System.out.println("getFirst fungerar om följande är \"Adam\": "
					+ testList.getFirst());
			System.out.println("get fungerar om följande är \"Cesar\": "
					+ testList.get(2));
			System.out
					.println("Fel! get borde ha kastat exception här, men gav istället: "
							+ testList.get(3));
		} catch (IndexOutOfBoundsException e) {
			if (Integer.parseInt(e.getMessage()) == 3) {
				System.out.println("Exception kastades från get(3), allt OK");
			} else {
				System.out.println("Exception kastades från get("
						+ e.getMessage() + "), FEL!");
			}
		}

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

		testList.setP(0);
		try { // testa setP dubbelt
			testList.setP(2);
			testList.setP(3);
			System.out.println("Exception från setP kastades inte!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception kastades från setP, allt ok");
		}
		if (testList.p == 2) {
			System.out.println("setP fungerar");
		} else {
			System.out.println("Fel på setP, ska vara 2 men är: " + testList.p);
		}

		try { // testa att lägga till sist i listan om p är satt korrekt.
			testList.addAfterP("David");
		} catch (IndexOutOfBoundsException e) {
			// Detta bör inte hända
			System.out
					.println("addAfterP kastade ett exception pga. fel med p tidigare.");
		}

		try { // testa att exc kastas för att listan är full.
			testList.addAfterP("David");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception kastades från addAfterP, allt OK.");
		}
		System.out
				.println("Printar listan, bör innehålla \"Bertil, Cesar, David\": "
						+ testList);

		testList.setP(1);
		try {
			testList.moveP(1);
			System.out.println("Flyttade p med offset, allt OK");
			testList.moveP(2);
			System.out.println("Fel. moveP(int) kunde flytta p utanför gränsen");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception kastades från moveP(int), allt OK");
		}
		
		try {
			testList.moveP("Bertil");
			if(testList.p == 0){
				System.out.println("p pekar på Bertil, allt OK");
			} else {
				System.out.println("moveP(String) fungerar inte med godkänd indata, och kastar inget exception!");
			}
		} catch(NoSuchElementException e){
			System.out.println("moveP kastar exception med godkänd indata, FEL");
		}
		try {
			testList.moveP("Adam");
			System.out.println("moveP(String) får felaktig indata men kastar inget exception!");
		} catch(NoSuchElementException e){
			System.out.println("Exception kastades från moveP(String), allt OK");
		}

		testList.removeFirst();
		if (testList.p == 0) {
			System.out.println("p skiftas vid removeFirst, allt OK");
		} else {
			System.out.println("Fel vid removeFirst. p bör vara 0, är: "
					+ testList.p);
		}
		testList.setP(1);
		if (!testList.hasNext()) {
			System.out
					.println("p pekar på sista elementet, hasNext säger nej, allt OK");
		} else {
			System.out.println("Fel i hasNext. p pekar på sista elementet");
		}
		testList.setP(0);
		if (testList.hasNext()) {
			System.out
					.println("p har element efter sig, hasNext säger ja, allt OK");
		} else {
			System.out.println("Fel i hasNext. p har element efter sig");
		}

		testList.removeFirst();
		if (testList.p == 0) {
			System.out.println("p skiftas inte vid removeFirst, allt OK");
		} else {
			System.out.println("Fel vid removeFirst. p bör vara 0, är: "
					+ testList.p);
		}
		testList.removeFirst();
		if (testList.empty()) {
			System.out.println("Listan tom, allt ok");
		} else {
			System.out.println("Listan inte tom, Något är fel");
		}
		
		System.out.println("Enligt coverage körs 100% i alla metoder");
	}

	/**
	 * Adds an element to the first position in the list.
	 * 
	 * @post internal pointer p will shift with the list and point out the same
	 *       content as before.
	 * 
	 * @param element
	 *            the string to add to the list
	 * @throws IndexOutOfBoundsException
	 *             if there is no room for the element
	 */
	public void addFirst(String element) throws IndexOutOfBoundsException {
		if (!full()) {
			this.shift(1);
			this.elements[0] = element;
			this.size++;
			this.p++;
		} else {
			throw new IndexOutOfBoundsException(FULL_LIST_MESSAGE);
		}
	}

	/**
	 * Check if the list is empty.
	 * 
	 * @return <code>true</code> if the list is empty
	 */
	public boolean empty() {
		return (this.size == 0);
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
	 * @post internal pointer p will shift with the list and point out the same
	 *       content as before. p will not shift if it points to position 0.
	 * 
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public void removeFirst() throws NoSuchElementException {
		if (!this.empty()) {
			this.shift(-1);
			this.size--;
			if (this.p > 0) {
				p--;
			}
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
	
	@Override
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

	/**
	 * Sets p to a position in the list.
	 * 
	 * @param p
	 *            the index p should point to
	 * @throws IndexOutOfBoundsException
	 *             if p is not within the list or the first element after the
	 *             list
	 */
	public void setP(int p) throws IndexOutOfBoundsException {
		if (p <= size && p >= 0 && p < this.elements.length) {
			this.p = p;
		} else {
			throw new IndexOutOfBoundsException(
					"p must point to the end of the list or an element in the list.");
		}
	}

	/**
	 * Checks whether there is an element after p. The only case when this is
	 * <code>false</code> is when p points to the end of the list.
	 * 
	 * @return <code>true</code> if p points on an element
	 */
	public boolean hasNext() {
		return (this.p < this.size-1);
	}

	/**
	 * <p>
	 * Adds a new element in the position p of the list. Elements after p are
	 * shifted to the right. p remains the same after this method is called.
	 * </p>
	 * 
	 * <p>
	 * The choice to not have p as a parameter was made because there already is
	 * a method for setting p that properly validates it. It would be out of the
	 * scope of this method to also set p.
	 * </p>
	 * 
	 * @param element
	 *            the string to be added
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the list is full
	 * 
	 * @see #setP(int p)
	 */
	public void addAfterP(String element) throws IndexOutOfBoundsException {
		if (!this.full()) {
			System.arraycopy(this.elements, this.p, this.elements, this.p + 1,
					this.size - this.p);
			this.size++;
			this.elements[p] = element;
		} else {
			throw new IndexOutOfBoundsException(FULL_LIST_MESSAGE);
		}
	}
	/**
	 * gets the element at index p. 
	 * 
	 * @param p index
	 * @return String content at index
	 * @throws IndexOutOfBoundsException p doesn't point to an element
	 */
	public String get(int p) throws IndexOutOfBoundsException {
		if (p < this.size && p >= 0) {
			return this.elements[p];
		} else {
			throw new IndexOutOfBoundsException("" + p);
		}
	}

	/**
	 * Moves p by an offset.
	 * 
	 * @param offset
	 *            the offset p should be moved by
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the new p is invalid
	 * 
	 * @see #setP(int p)
	 */
	public void moveP(int offset) throws IndexOutOfBoundsException {
		this.setP(p + offset);
	}

	/**
	 * Sets p to the position of the element if it is added to the list.
	 * 
	 * @param element
	 *            the element p should point to
	 * @throws NoSuchElementException
	 *             if the element is not in the list
	 */
	public void moveP(String element) throws NoSuchElementException {
		for (int i = 0; i < this.size; i++) {
			if (element.equals(this.elements[i])) {
				setP(i);
				return;
			}
		}
		throw new NoSuchElementException();
	}

	/**
	 * Checks if there is room for additional elements in the list.
	 * 
	 * @return <code>true</code> if the list is full
	 */
	public boolean full() {
		return this.size == this.elements.length;
	}
	
	/**
	 * moves all elements in the list by an offset. if elements fall outside the list bounds, they are silently deleted.
	 * 
	 * @param offset positive or negative integer.
	 */
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