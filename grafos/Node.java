
public class Node {
	public Vertice v;
	public Node esq;
	public Node dir;
	
	public Node (Vertice vertice) {
		this.v = vertice;
		dir = esq = null;
	}
}
