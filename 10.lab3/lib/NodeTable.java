import java.util.*;
/*
* Mapping between nod number and node name
* @author rewritten by E Holmström 2011, added reallocate
*/

public class NodeTable <NO extends NodeObject>{ 

	private TreeMap<String, NO>  fromName; // mapping name -> node object
	private NO[]  fromNodeNo; // table of node objects i.e a mapping from node number to a node
	private int   capacity = 10;
	private int   size = 0;     
	private int   modCount = 0;

	public NodeTable() {
		this(10);
	}
	
	@SuppressWarnings("unchecked")
		
	public NodeTable(int initialNoOfNodes ) {
		capacity = initialNoOfNodes;
		fromNodeNo = (NO[]) new NodeObject[initialNoOfNodes];
		fromName   = new TreeMap<String, NO>();
		Arrays.fill(fromNodeNo, null);
	}

	@SuppressWarnings("unchecked")
	/**
	Adds a node 
	*/
	public void add(NO no) {
		if ( !(fromName.get(no.name) == null) ) { // already added
			throw new RuntimeException("Duplicate nodenames illegal !!");
		} else {
			// need to reallocate?
			if ( size==capacity ) {
				capacity = 2 * capacity;
			    NO[] newfromNodeNo = (NO[]) new NodeObject[capacity];
				Arrays.fill(newfromNodeNo, null);
			    System.arraycopy(fromNodeNo, 0, newfromNodeNo, 0, size);
			    fromNodeNo = newfromNodeNo;
			}
			// now there is surely room for another element
			no.setNodeNo(size);
			fromNodeNo[size] = no;
			fromName.put( no.name, no );
			size++;
		}
	}
	public NO find( String name ) {
		return  fromName.get(name);
	}
	
	public NO findLeading( String name ) {
		/*
		 OBS experimentell metod
		 klarar inmatning typ "bevä" som ger Beväringsgatan
		 man måste mata in tillräckligt många tecken för att det skall vara 
		 ett unikt namn (och trycka return)
		 detta är dyrbart och inte optimalt så använd den inte i onödan 
		 tex inte när du bygger upp grafen
		 behövs inte för labben, find ovan räcker
		 @TODO felokänslig typ "bevaring" ger Beväringsgatan
		*/
		
		// try to lookup the name 
		NO tmpNode = fromName.get(name);
		if ( tmpNode != null ) {
			return tmpNode;
		} else {
			// we didn't find the name, try to find a substring
			// this is expensive...
			// make a string of the map
			String tmp = fromName.toString();
			// take away {} in the beginning and the end
			tmp = tmp.substring(1, tmp.length()-1);
			// split the string
			String[] tmpArr = tmp.split(", ");
			// now finally we have an array with the strings twice 
			// like this "station=station"
			/* debug
			int i = 0;
			for (String p : tmpArr ) {
				System.out.println( "" + i++ + " *" + p + "*");
			}
			*/
			/*
			Now we do a binary search for the station. We don't expect to find 
			it but we get the position where it should be. 
			So we can search in the neighbourhood .
			index of the search key, if it is contained in the list; otherwise, 
			(-(insertion point) - 1). The insertion point is defined as the point 
			at which the key would be inserted into the list: the index of the 
			first element greater than the key, or list.size(), if all elements 
			in the list are less than the specified key. Note that this guarantees 
			that the return value will be >= 0 if and only if the key is found. 
			*/
			//System.out.println("name= " + name);
			int pos = Arrays.binarySearch(tmpArr, name+"="+name);
			//System.out.println(pos);
			pos = Math.abs(pos+1);
			//System.out.println(pos);
			//System.out.println(tmpArr[pos]); 
			int l = name.length();
			//System.out.println(name + " name");
			//System.out.println(tmpArr[pos].substring(0, l) + " substring");
			if ( tmpArr[pos].substring(0, l).equals(name) 
						&& ( pos==tmpArr.length-1 
							||  !(tmpArr[pos+1].substring(0, l).equals(name)))
				) {
				//System.out.println(find(pos) + " found");
				//System.out.println(find(pos-1) + " found before");
				//System.out.println(find(133)  + " last");
				return find(pos);
			} else {
				//System.out.println(find(pos) + " found");
				//System.out.println(find(pos-1) + " found before");
				//System.out.println(find(133)  + " last");
				//System.out.println("not found");
				return null;
			}
		}
		
	} 
	
	public NO find( int no ) {
		return fromNodeNo[no];
	} 

	public int noOfNodes() {
		return size;
	}

	public String toString() {
		return fromName.toString();
	}

}
