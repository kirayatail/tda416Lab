import java.util.*;

public class ComparableEdge<E extends Edge> implements
		Comparable<ComparableEdge<E>> {

	private List<E> edgeList;
	private double weight;
	private int to;

	public ComparableEdge() {
		this.edgeList = new LinkedList<E>();
		this.weight = 0;
	}
	
	public ComparableEdge(ComparableEdge<E> ce) {
		this.edgeList = new LinkedList<E>();
		this.edgeList.addAll(ce.edgeList);
		this.to = ce.to;
		this.weight = ce.weight;
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
		if(!edgeList.isEmpty()){
			return this.to;
		} else {
			throw new NoSuchElementException("ComparableEdge is empty");
		}
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
