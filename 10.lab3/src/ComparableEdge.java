import java.util.*;

public class ComparableEdge<E extends Edge> implements
		Comparable<ComparableEdge<E>> {

	private List<E> edgeList;
	private double weight;
	private int to, from;

	public ComparableEdge(int from) {
		this.edgeList = new LinkedList<E>();
		this.from = from;
		this.to = from;
		this.weight = 0;
	}

	public ComparableEdge(ComparableEdge<E> ce, E edge) {
		this.edgeList = new LinkedList<E>();
		this.edgeList.addAll(ce.edgeList);
		this.from = ce.from;
		this.weight = ce.weight;
		this.addEdge(edge);
	}

	public void addEdge(E edge) {
		edgeList.add(edge);
		this.weight += edge.getWeight();
		this.to = edge.to;
	}

	public double getWeight() {
		return this.weight;
	}

	public int getFrom() {
		return this.from;
	}

	public int getTo() {
		return this.to;
	}

	@Override
	public int compareTo(ComparableEdge<E> edge) {
		if (this.getWeight() < edge.getWeight()) {
			return -1;
		} else if (this.getWeight() == edge.getWeight()) {
			return 0;
		} else {
			return 1;
		}
	}

	public Iterator<E> iterator() {
		return edgeList.iterator();
	}
}
