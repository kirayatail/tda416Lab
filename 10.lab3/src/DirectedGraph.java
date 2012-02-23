import java.util.*;
/**
 * DirectedGraph
 * 
 * @author Sebastian Ljunggren, Max Witt grp 10
 *
 * @param <E>
 */
public class DirectedGraph<E extends Edge> {

	private List<E>[] nodeArray;

	@SuppressWarnings("unchecked")
	public DirectedGraph(int noOfNodes) {

		nodeArray = (List<E>[]) new LinkedList[noOfNodes];
		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = new LinkedList<E>();
		}
		// Store all edges in a huge linkedlist. We want to iterate through
		// every element in the mst method.

	}

	public void addEdge(E e) {
		this.nodeArray[e.from].add(e);
	}

	/**
	 * Finds the shortest path (lowest weight) between two nodes in the graph.
	 * 
	 * @param from
	 *            the starting point
	 * @param to
	 *            the destination
	 * @return an iterator for all edges in the shortest paths
	 */
	public Iterator<E> shortestPath(int from, int to) {
		boolean[] visited = new boolean[this.nodeArray.length];
		PriorityQueue<ComparableEdge<E>> edges = new PriorityQueue<ComparableEdge<E>>();
		edges.add(new ComparableEdge<E>(from));
		while (!edges.isEmpty()) {
			ComparableEdge<E> shortest = edges.poll();
			int shortestTo = shortest.getTo();
			if (!visited[shortestTo]) {
				if (shortestTo == to) {
					return shortest.iterator();
				} else {
					visited[shortestTo] = true;
					for (E edge : this.nodeArray[shortestTo]) {
						if (!visited[edge.to]) {
							edges.add(new ComparableEdge<E>(shortest, edge));
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Implements Kruskal's algorithm for finding a minimum spanning tree.
	 * Directions in the graph are disregarded in this matter.
	 * 
	 * @return iterator to the mst or null if it doesn't exist (bad graph)
	 */
	public Iterator<E> minimumSpanningTree() {
		// Create and populate PQ
		PriorityQueue<KruskalEdge<E>> pq = new PriorityQueue<KruskalEdge<E>>();
		for (List<E> edgeList : this.nodeArray) {
			for (E edge : edgeList) {
				pq.add(new KruskalEdge<E>(edge));
			}
		}

		// Create node array (each index represents a node number) which will
		// contain empty lists of edges (components)
		@SuppressWarnings("unchecked")
		List<E>[] nodes = (List<E>[]) new LinkedList[this.nodeArray.length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new LinkedList<E>();
		}

		while (!pq.isEmpty()) {

			// Get the lightest edge
			KruskalEdge<E> candEdge = pq.poll();

			int small, big; // indices referring to which of the from-to pair
							// has the smallest/largest component

			// Doesn't point to the same component, do the magic!
			if (nodes[candEdge.getFrom()] != nodes[candEdge.getTo()]) {

				// Determine and set big and small

				if (nodes[candEdge.getFrom()].size() >= nodes[candEdge.getTo()]
						.size()) {
					big = candEdge.getFrom();
					small = candEdge.getTo();
				} else {
					big = candEdge.getTo();
					small = candEdge.getFrom();
				}

				// Re-point each from and to from each edge in the "small"
				// component and also add each edge to "big"
				for (E subEdge : nodes[small]) {
					nodes[subEdge.from] = nodes[big];
					nodes[subEdge.to] = nodes[big];
					nodes[big].add(subEdge);
				}

				// Re-point small to point at big's component.
				nodes[small] = nodes[big];

				// Add the candidate to "big"
				nodes[big].add(candEdge.getEdge());

				// We're done if big's component has n-1 edges (where n = number
				// of nodes)
				if (nodes[big].size() == (nodes.length - 1)) {
					return nodes[big].iterator();
				}
			}
		}
		return null;
	}
}
