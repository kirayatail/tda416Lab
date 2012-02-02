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

	public static void main(String[] args) {
		SortedLinkedCollection<Integer> numbers = new SortedLinkedCollection<Integer>();
		// * lägga till element i en tom lista
		
		// * skicka in null, kolla att vi får fel

		// * lägga till element som kommer hamna i slutet av listan
		
		// * lägga till element som kommer hamna mitt i listan
		
		// * Skicka in element i oordning, kolla att de är sorterade
		
		// * testa get

		// * testa get med null

		// * testa get med nåt som inte finns i listan (ska ge null)

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
					&& element.compareTo(current.next.element) >= 0) {
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
			if (current.element.compareTo(element) == 0) {
				return current.element;
			}
			current = current.next;
		}
		return null;
	}
}
