import java.util.*;

public class ComparableEdge<E extends Edge> implements
		Comparable<ComparableEdge<E>> {

	private List<E> edgeList;
	private double weight;
	private int to;

	public ComparableEdge() {
		edgeList = new LinkedList<E>();
		this.weight = 0;
	}

	public void addEdge(E edge) {
		edgeList.add(edge);
		this.weight += edge.getWeight();
		this.to = edge.to;
	}

	public double getWeight() {
		return this.weight;
	}

	public int getFrom(){
		if(!edgeList.isEmpty()){
			return edgeList.get(0).from;
		} else {
			throw new NoSuchElementException("ComparableEdge is empty");
		}
	}
	
	public int getTo(){
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
}
