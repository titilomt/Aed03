import java.util.ArrayList;
import java.util.List;

public class Vertice {

	public String cor;
	public int distancia;
	public int vertice;
	public boolean hasVizinho;
	public int parent;
	public List<Vertice> listVertice;
	
	public Vertice(){}
	
	public Vertice (int d, String c, int v, boolean h, int p) {
		this.distancia = d;
		this.cor = c;
		this.vertice = v;
		this.hasVizinho = h;
		this.parent = p;
		listVertice = new ArrayList<Vertice>();
	}
	
	public String toString(){
		return ""+this.vertice;
	}
}
