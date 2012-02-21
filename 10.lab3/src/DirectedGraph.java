import java.util.*;

public class DirectedGraph<E extends Edge> {

	/*
	 * Simple array containing linked lists with edges. Every index of the array
	 * corresponds to a node, with the directive edges as content
	 */
	private List<LinkedCollection<E>> nodeList;

	public DirectedGraph(int noOfNodes) {
		nodeList = new ArrayList<LinkedCollection<E>>(noOfNodes); 
		
	}

	public void addEdge(E e) {
		;
	}

	public Iterator<E> shortestPath(int from, int to) {
		return null;
	}

	public Iterator<E> minimumSpanningTree() {
		return null;
	}

}
