package grafos;

public class Celula {

	public String cor ="braca";
	public Celula prox = null;
	public int distancia = 0;
	public int vertice = 0;
	public int hasVertice= 0;
	
	public Celula(){}
	
	public Celula(int d, String c, Celula prox, int v, int h){
		this.distancia = d;
		this.cor = c;
		this.vertice = v;
		this.hasVertice = h;
		prox = null;
	}
}
