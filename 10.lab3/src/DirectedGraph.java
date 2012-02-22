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
	public Iterator<KruskalEdge<E>> minimumSpanningTree() {
		// Create and populate PQ
		PriorityQueue<KruskalEdge<E>> pq = new PriorityQueue<KruskalEdge<E>>();
		Iterator<E> it = edgeList.iterator();
		while (it.hasNext()) {
			pq.add(new KruskalEdge<E>(it.next()));
		}

		// Create node array (each index represents a node number) which will
		// contain empty lists of edges (components)
		@SuppressWarnings("unchecked")
		List<KruskalEdge<E>>[] nodeArray = (List<KruskalEdge<E>>[]) new LinkedList[this.noNodes];
		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = new LinkedList<KruskalEdge<E>>();
		}

		while (!pq.isEmpty()) {

			// Get the lightest edge
			KruskalEdge<E> candEdge = pq.poll();

			int small, big; // indices referring to which of the from-to pair
							// has the smallest/largest component

			// Doesn't point to the same component, do the magic!
			if (nodeArray[candEdge.getFrom()] != nodeArray[candEdge.getTo()]) {

				// Determine and set big and small
				if (nodeArray[candEdge.getFrom()].size() >= nodeArray[candEdge.getTo()]
						.size()) {
					big = candEdge.getFrom();
					small = candEdge.getTo();
				} else {
					big = candEdge.getTo();
					small = candEdge.getFrom();
				}

				// Re-point each from and to from each edge in the "small"
				// component and also add each edge to "big"
				for (KruskalEdge<E> subEdge : nodeArray[small]) {
					nodeArray[subEdge.getFrom()] = nodeArray[big];
					nodeArray[subEdge.getTo()] = nodeArray[big];
					nodeArray[big].add(subEdge);
				}

				// Re-point small to point at big's component.
				nodeArray[small] = nodeArray[big];

				// Add the candidate to "big"
				nodeArray[big].add(candEdge);
				
				// We're done if big's component has n-1 edges (where n = number
				// of nodes)
				if (nodeArray[big].size() == (nodeArray.length - 1)) {
					return nodeArray[big].iterator();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		
		// Example from lecture
		
		DirectedGraph<BusEdge> dg = new DirectedGraph<BusEdge>(6); 
		
		dg.addEdge(new BusEdge(4,5,6,""));
		dg.addEdge(new BusEdge(1,4,3,""));
		dg.addEdge(new BusEdge(2,4,6,""));
		dg.addEdge(new BusEdge(2,5,4,""));
		dg.addEdge(new BusEdge(3,5,2,""));
		dg.addEdge(new BusEdge(1,2,5,""));
		dg.addEdge(new BusEdge(2,3,5,""));
		dg.addEdge(new BusEdge(0,2,1,""));
		dg.addEdge(new BusEdge(0,1,6,""));
		dg.addEdge(new BusEdge(0,3,5,""));
		
		Iterator<KruskalEdge<BusEdge>> it = dg.minimumSpanningTree();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}
