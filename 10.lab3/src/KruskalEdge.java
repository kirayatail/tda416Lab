
public class KruskalEdge extends Edge implements Comparable<KruskalEdge> {

	private final int weight;

	public KruskalEdge(int from, int to, int weight) {
		super(from, to);
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(KruskalEdge edge) {
		return this.weight - edge.weight;
	}
	
	@Override
	public boolean equals(Object o){
		if(o.getClass() == this.getClass()){
			return (super.equals(o) && (((KruskalEdge) o).weight == this.weight));
		} else {
			return false;
		}
	}
}
