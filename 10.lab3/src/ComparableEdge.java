public class ComparableEdge extends Edge implements Comparable<ComparableEdge> {

	private final int weight;

	public ComparableEdge(int from, int to, int weight) {
		super(from, to);
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(ComparableEdge edge) {
		return this.weight - edge.weight;
	}
}
