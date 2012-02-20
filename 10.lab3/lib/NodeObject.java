// @TODO getters istället för publikt?
  public class NodeObject  {

     public final String name;
     private int nodeNo;

     NodeObject(String name) { 
        this.name = name.trim();
     }

	public int getNodeNo() {
		return nodeNo;
	}
	
	public void setNodeNo(int no) {
		nodeNo = no;
	}
	

	public String toString() {
		//return name + "," + nodeNo;
		return name;
	}
  }
