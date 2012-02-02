import datastructures.LinkedCollection;


public class SortedLinkedCollection<E extends Comparable<E>> extends LinkedCollection<E> {
	
	public SortedLinkedCollection(){
		super();
	}
	
	@Override
	public boolean add(E element){
		if(element == null){
			throw new IllegalArgumentException("Argument cannot be null");
		}
		Entry current = this.head;
		if(head == null){
			head = new Entry(element, null);
		} else {
			
			while(current.next != null && element.compareTo(current.next.element) <=0){
				current = current.next;
			}
			current.next = new Entry(element, current.next);
		}
		
		return true;
	}
}
