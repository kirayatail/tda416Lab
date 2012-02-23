
/**
 * Wrapper class for Edge with a compareTo which is only concerned with the
 * weight of an edge.
 * 
 * @author Max Witt, Sebastian Ljunggren grp 10
 * 
 * @param <E>
 */
public class KruskalEdge<E extends Edge> implements Comparable<KruskalEdge<E>> {

	private E edge;

	public KruskalEdge(E e) {
		this.edge = e;
	}

	public double getWeight() {
		return this.edge.getWeight();
	}

	public int compareTo(KruskalEdge<E> otherEdge) {
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
