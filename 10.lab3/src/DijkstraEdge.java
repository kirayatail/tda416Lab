import java.util.*;

/**
 * @author Sebastian Ljunggren, Max Witt, grp 10
 *
 * @param <E>
 */
public class DijkstraEdge<E extends Edge> implements
		Comparable<DijkstraEdge<E>> {

	private List<E> edgeList;
	private double weight;
	private int to, from;

	/**
	 * Create a DijkstraEdge without any edges and a starting point.
	 * 
	 * @param from
	 *            the starting point
	 */
	public DijkstraEdge(int from) {
		this.edgeList = new LinkedList<E>();
		this.from = from;
		this.to = from;
		this.weight = 0;
	}

	/**
	 * Create a DijkstraEdge from an existing instance and add another edge to
	 * it.
	 * 
	 * @param ce
	 *            the copied instance
	 * @param edge
	 *            the additional edge
	 */
	public DijkstraEdge(DijkstraEdge<E> ce, E edge) {
		this.edgeList = new LinkedList<E>();
		this.edgeList.addAll(ce.edgeList);
		this.from = ce.from;
		this.weight = ce.weight;
		this.addEdge(edge);
	}

	/**
	 * Add another edge to the end of the edge list.
	 * 
	 * @param edge
	 *            the edge to add
	 */
	public void addEdge(E edge) {
		edgeList.add(edge);
		this.weight += edge.getWeight();
		this.to = edge.to;
	}

	/**
	 * Gives the total weight of all edges in the DijkstraEdge.
	 * 
	 * @return the total weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Gets the starting node.
	 * 
	 * @return the starting position
	 */
	public int getFrom() {
		return this.from;
	}

	/**
	 * Gets the end node.
	 * 
	 * @return the end point
	 */
	public int getTo() {
		return this.to;
	}

	/**
	 * Compares two DijkstraEdges' weight.
	 */
	@Override
	public int compareTo(DijkstraEdge<E> edge) {
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
