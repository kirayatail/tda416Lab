import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E> edgeList;
	private int noNodes;

	public DirectedGraph(int noOfNodes) {
		noNodes = noOfNodes;

		// Store all edges in a huge linkedlist. We want to iterate through
		// every element in the mst method.
		edgeList = new LinkedList<E>();
	}

	public void addEdge(E e) {
		if (!edgeList.contains(e)) {
			edgeList.add(e);
		}
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}

	/**
	 * Woho! Kruskal!
	 * 
	 * @return
	 */
	public Iterator<E> minimumSpanningTree() {
		// Create and populate PQ
		PriorityQueue<E> pq = new PriorityQueue<E>();
		Iterator<E> it = edgeList.iterator();
		while (it.hasNext()) {
			pq.add(it.next());
		}

		// Create node array (each index represents a node number) which will
		// contain empty lists of edges (components)
		@SuppressWarnings("unchecked")
		List<E>[] nodeArray = (List<E>[]) new LinkedList[this.noNodes+1];
		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = new LinkedList<E>();
		}

		while (!pq.isEmpty()) {

			// Get the lightest edge
			E candEdge = pq.poll();

			int small, big; // indices referring to which of the from-to pair
							// has the smallest/largest component

			// Doesn't point to the same component, do the magic!
			if (nodeArray[candEdge.from] != nodeArray[candEdge.to]) {

				// Determine and set big and small
				if (nodeArray[candEdge.from].size() >= nodeArray[candEdge.to]
						.size()) {
					big = candEdge.from;
					small = candEdge.to;
				} else {
					big = candEdge.to;
					small = candEdge.from;
				}

				// Re-point each from and to from each edge in the "small"
				// component and also add each edge to "big"
				for (E subEdge : nodeArray[small]) {
					nodeArray[subEdge.from] = nodeArray[big];
					nodeArray[subEdge.to] = nodeArray[big];
					nodeArray[big].add(subEdge);
				}

				// Re-point small to point at big's component.
				nodeArray[small] = nodeArray[big];

				// Add the candidate to "big"
				nodeArray[big].add(candEdge);
				
				// We're done if big's component has n-1 edges (where n = number
				// of nodes)
				if (nodeArray[big].size() == (nodeArray.length - 2)) {
					return nodeArray[big].iterator();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		
		// Example from lecture
		
		DirectedGraph<KruskalEdge> dg = new DirectedGraph<KruskalEdge>(6); 
		
		dg.addEdge(new KruskalEdge(5,6,6));
		dg.addEdge(new KruskalEdge(2,5,3));
		dg.addEdge(new KruskalEdge(3,5,6));
		dg.addEdge(new KruskalEdge(3,6,4));
		dg.addEdge(new KruskalEdge(4,6,2));
		dg.addEdge(new KruskalEdge(2,3,5));
		dg.addEdge(new KruskalEdge(3,4,5));
		dg.addEdge(new KruskalEdge(1,3,1));
		dg.addEdge(new KruskalEdge(1,2,6));
		dg.addEdge(new KruskalEdge(1,4,5));
		
		Iterator<KruskalEdge> it = dg.minimumSpanningTree();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}
