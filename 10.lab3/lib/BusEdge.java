
// extends an Edge with a weight and a name for the bus line
public class BusEdge extends Edge {

  public final String line;
  public final double weight;

  BusEdge( int from, int to, double weight, String line ) {
    super(from,to);
    this.line = line;
    this.weight = weight;
  }

  public double getWeight() {
    return weight;
  } 
  
}

