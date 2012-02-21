import java.util.*;

public class DirectedGraph<E extends Edge> {

	private List<E> edgeList;
	private int noNodes;

	@SuppressWarnings("unchecked")
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
	 * @return
	 */
	public Iterator<E> minimumSpanningTree() {
		
		PriorityQueue<E> pq = new PriorityQueue<E>();
		Iterator<E> it = edgeList.iterator();
		while(it.hasNext()){
			pq.add(it.next());
		}
		List<E>[] nodeArray = (List<E>[]) new LinkedList[this.noNodes];
		for(int i=0; i<nodeArray.length; i++){
			nodeArray[i] = new LinkedList<E>();
		}
		
		while(!pq.isEmpty()){
			E candEdge = pq.poll();
			int small, big;
			if(nodeArray[candEdge.from] != nodeArray[candEdge.to]){
				if(nodeArray[candEdge.from].size() >= nodeArray[candEdge.to].size()){
					big = candEdge.from;
					small = candEdge.to;
				} else {
					big = candEdge.to;
					small = candEdge.from;
				}
				
				for(E subEdge : nodeArray[small]){
					nodeArray[subEdge.from] = nodeArray[big];
					nodeArray[subEdge.to] = nodeArray[big];
					nodeArray[big].add(subEdge);
				}
				nodeArray[small] = nodeArray[big];
				if(nodeArray[big].size() == nodeArray.length-1){
					return nodeArray[big].iterator();
				}
			}	
		}
		return null;
	}

}
