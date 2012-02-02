import testSortCol.CollectionWithGet;
import datastructures.LinkedCollection;
import java.util.*;

/**
 * Sorted linked collection implemented by a minimal linked list. Stores
 * elements according to <code>compareTo()</code>, where the first element given
 * by the iterator is the smallest. <code>null</code> elements are not allowed,
 * however, duplicates are allowed.
 * 
 * @author Sebastian Ljunggren, Max Witt grupp 10
 * 
 * @param <E>
 *            implements java.util.Comparable<E>
 */
public class SortedLinkedCollection<E extends Comparable<E>> extends
		LinkedCollection<E> implements CollectionWithGet<E> {

	public SortedLinkedCollection() {
		super();
	}

	/**
	 * Simple test cases for this class. Testing the add method, the sorting
	 * behavior and get method for both positive and negative behavior.
	 * 
	 * 
	 * @param args
	 *            - N/A
	 */
	public static void main(String[] args) {
		SortedLinkedCollection<Integer> numbers = new SortedLinkedCollection<Integer>();
		// Siffror som läggs till: 3,3,5,7,9 i ordningen 3,7,9,5,3

		System.out.print("testar att lägga till element i en tom lista: ");
		numbers.add(3);
		if (numbers.head.element.equals(3)) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element i tom lista");
		}

		System.out
				.print("testar att skicka in null, kolla att exception kastas: ");
		try {
			numbers.add(null);
			System.out.println("Fel! kunde lägga till null i listan");
		} catch (IllegalArgumentException e) {
			System.out.println("Ok!");
		}

		System.out
				.print("testar att lägga till element som kommer hamna i slutet av listan: ");
		numbers.add(7);
		LinkedCollection<Integer>.Entry entry = numbers.head;
		while (entry.next != null) {
			entry = entry.next;
		}
		if (entry.element == 7) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element i slutet av listan");
		}
		numbers.add(9);

		System.out
				.print("testar att lägga till element som kommer hamna mitt i listan: ");
		numbers.add(5);
		entry = numbers.head.next;
		if (entry.element == 5 && entry.next.element == 7) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element i mitten av listan");
		}

		System.out.print("testar att lägga till dublett: ");
		numbers.add(3);
		entry = numbers.head;
		if (entry.element == entry.next.element) {
			System.out.println("Ok!");
		} else {
			System.out.println("Fel! hanterar inte dubletter");
		}
		System.out.print("testar att get fungerar för existerande element: ");
		if (numbers.get(3) == 3 && numbers.get(5) == 5 && numbers.get(9) == 9) {
			System.out.println("Ok!");
		} else {
			System.out.println("Fel! Get kan inte hitta existerande element");
		}

		System.out.print("testar get med null: ");
		if (numbers.get(null) == null) {
			System.out.println("Ok!");
		} else {
			System.out.println("Mycket fel! get(null) ger något annat än null");
		}

		System.out.print("testar get med nåt som inte finns i listan: ");
		if (numbers.get(8) == null) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! get med icke-existerande element ger inte null");
		}
		System.out.print("automatisk kontroll - är elementen i ordning?: ");
		boolean errorFlag = false;
		int[] intArray = { 3, 3, 5, 7, 9 };
		entry = numbers.head;
		for (int i = 0; i < intArray.length; i++) {
			if (intArray[i] != entry.element) {
				errorFlag = true;
			}
			entry = entry.next;
		}

		if (!errorFlag) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! automatisk kontroll säger att elementen inte är i ordning");
		}

		System.out.println("är följande lika är allt OK:\n[3, 3, 5, 7, 9]\n"
				+ numbers);

	}

	/**
	 * Adds an element to the collection. The method makes sure it is added in
	 * the correct position. Duplicates are allowed.
	 * 
	 * @param element
	 *            the element to add
	 * @return <code>true</code> if the element was successfully added.
	 * @throws IllegalArgumentException
	 *             if the element is <code>null</code>
	 */
	@Override
	public boolean add(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Argument cannot be null");
		}
		Entry current = this.head;
		if (head == null) {
			head = new Entry(element, null);
		} else {

			while (current.next != null
					&& element.compareTo(current.next.element) > 0) {
				current = current.next;
			}
			current.next = new Entry(element, current.next);
		}

		return true;
	}

	/**
	 * Looks for the first occurrence of an element in the collection and return
	 * it if it is in the collection.
	 * 
	 * @param element
	 *            the element to look in the collection for
	 * @return a reference to the element in the collection or <code>null</code>
	 *         if it isn't found
	 */
	@Override
	public E get(E element) {
		if (element == null) {
			return null;
		}
		Entry current = this.head;
		while (current != null) {
			int comparison = current.element.compareTo(element);
			if (comparison == 0) {
				return current.element;
			} else if(comparison > 0){
				// We've gone too far, ending early.
				return null;
			}
			current = current.next;
		}
		return null;
	}
}
