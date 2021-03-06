
/**
 * Wrapper class for Edge with a compareTo which is only concerned with the
 * weight of an edge.
 * 
 * @author Sebastian Ljunggren, Max Witt grp 10
 * 
 * @param <E>
 */
public class ComparableEdge<E extends Edge> implements Comparable<ComparableEdge<E>> {

	private E edge;

	public ComparableEdge(E e) {
		this.edge = e;
	}

	public double getWeight() {
		return this.edge.getWeight();
	}

	public int compareTo(ComparableEdge<E> otherEdge) {
		if (this.getWeight() < otherEdge.getWeight()) {
			return -1;
		} else if (this.getWeight() == otherEdge.getWeight()) {
			return 0;
		} else {
			return 1;
		}
	}

	public int getFrom() {
		return this.edge.from;
	}

	public int getTo() {
		return this.edge.to;
	}

	public E getEdge() {
		return this.edge;
	}

	public String toString() {
		return this.edge.toString();
	}
}
