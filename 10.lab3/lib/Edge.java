/**
* A minimal object for edges in a directed graph of type DirectedGraph.
* Note that the weight is given by a abstract method, since it can be more 
* complicated than just a number.
* The subclasses constructors is supposed to have at least integers
* for from and to as arguments.
* @author (Bror Bjerner)
* @version (2001)
* @author (Erland Holmström)
* @version (2011 added getters, toString, hashCode and equals)
*/
public abstract class Edge {

	/**
	* The nodes from which the edge starts to which 
	* the edge ends. Should never change !
	*/
	/** The source vertix */
	public final int from;
	/** The destination vertix */
	public final int to;

	/**
	* Since from and to are final, there values must be
	* given to the constructor.
	*/
	public Edge( int from, int to ) {
		this.from   = from;
		this.to     = to;
	}

	/**
	* Find the weight for this edge.
	* @return the found weight.
	*/
	public abstract double getWeight();
	
	
	// ========================================================
	// the rest is not really needed for the laboration but I added it to make 
	// the class more complete

	/** Get the source
	* @return The value of from
	*/
	public int getSource() {
		return from;
	}

	/** Get the destination
	* @return The value of to
	*/
	public int getDest() {
		return to;
	}

	/** Returns a String representation of the edge
	* @return A String representation of the edge
	*/
	public String toString() {
		StringBuffer sb = new StringBuffer("{(");
		sb.append( Integer.toString(from) + ", " + Integer.toString(to) );
		sb.append( "): " + Double.toString(getWeight()) + "}" );
		return sb.toString();
	}

	/** Return true if two edges are equal. Edges are equal if the source and 
	destination are equal. Weight is not conidered.
	@param obj The object to compare to
	@return true if the edges have the same source and destination
	*/
	public boolean equals(Object obj) {
		if (obj instanceof Edge) {
			Edge edge = (Edge) obj;
			return (from == edge.from && to == edge.to);
		} else {
			return false;
		}
	}

	/** Return a hash code for an edge.  
	The hash code is the source shifted left 16 bits exclusive or with the dest
	@return a hash code for an edge
	*/
	public int hashCode() {
		return (from << 16) ^ to;
	}
}
