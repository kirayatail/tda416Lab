package datastructures;

public class SortedLinkedCollectionTest {

	/**
	 * Simple test cases for Sort. Testing the add method, the sorting behavior
	 * and get method for both positive and negative behavior.
	 * 
	 * 
	 * @param args
	 *            - N/A
	 */
	public static void main(String[] args) {
		SortedLinkedCollection<Integer> numbers = new SortedLinkedCollection<Integer>();
		// Siffror som läggs till: 3,3,5,7,9 i ordningen 5,3,7,9,3

		System.out.println("testar att lägga till element i en tom lista: ");
		numbers.add(5);
		if (numbers.head.element.equals(5)) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element i tom lista");
		}

		System.out
				.println("testar att skicka in null, kolla att exception kastas: ");
		try {
			numbers.add(null);
			System.out.println("Fel! kunde lägga till null i listan");
		} catch (IllegalArgumentException e) {
			System.out.println("Ok!");
		}
		System.out
				.println("testar att lägga till element som kommer hamna först i listan");
		numbers.add(3);
		if (numbers.head.element.equals(3)
				&& numbers.head.next.element.equals(5)) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element först i listan");
		}
		System.out
				.println("testar att lägga till element som kommer hamna i slutet av listan: ");
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
				.println("testar att lägga till element som kommer hamna mitt i listan: ");
		entry = numbers.head.next;
		if (entry.element == 5 && entry.next.element == 7) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! kunde inte lägga till element i mitten av listan");
		}

		System.out.println("testar att lägga till dublett: ");
		numbers.add(3);
		entry = numbers.head;
		if (entry.element == entry.next.element) {
			System.out.println("Ok!");
		} else {
			System.out.println("Fel! hanterar inte dubletter");
		}
		System.out.println("testar att get fungerar för existerande element: ");
		if (numbers.get(3) == 3 && numbers.get(5) == 5 && numbers.get(9) == 9) {
			System.out.println("Ok!");
		} else {
			System.out.println("Fel! Get kan inte hitta existerande element");
		}

		System.out.println("testar get med null: ");
		if (numbers.get(null) == null) {
			System.out.println("Ok!");
		} else {
			System.out.println("Mycket fel! get(null) ger något annat än null");
		}

		System.out.println("testar get med nåt som inte finns i listan: ");
		if (numbers.get(8) == null) {
			System.out.println("Ok!");
		} else {
			System.out
					.println("Fel! get med icke-existerande element ger inte null");
		}
		System.out.println("automatisk kontroll - är elementen i ordning?: ");
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

}
