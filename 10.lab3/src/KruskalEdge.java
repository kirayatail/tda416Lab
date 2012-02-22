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

	@Override
	public boolean equals(Object o) {
		if (o.getClass() == this.getClass()) {
			return (super.equals(o) && (((KruskalEdge<E>) o).compareTo(this) == 0));
		} else {
			return false;
		}
	}

	public String toString() {
		return this.edge.toString();
	}
}
