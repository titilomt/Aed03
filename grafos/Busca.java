import java.util.ArrayList;
import java.util.List;

public class Busca {
	//protected static BinaryTree BT_BINARY_TREE;
	public static final int INFINITY = -99999;
	public static final int NIL = -99999;
	protected static Vertice current = new Vertice();
	
	public static  void bFS( ArrayList<Vertice> graph, int root){
		// each node in graph = INFINITY
		for(Vertice v: graph){
			v.distancia = INFINITY;
			v.parent = NIL;
			for(Vertice v2 : v.listVertice){
				v2.distancia = INFINITY;
				v2.parent = NIL;
			}
		}
		graph.get(root).distancia = 0;
		List<Vertice> queue = new ArrayList<Vertice>();
		queue.add(graph.get(root));
		
		while(!queue.isEmpty()){
			current = queue.remove(0);
			for(Vertice tmp : current.listVertice){
				if(tmp.distancia == INFINITY){
					tmp.distancia = current.distancia + 1;
					tmp.parent = current.vertice;
					queue.add(tmp);
				}
			}
		}
	}
	
	
	// Falta pensar um pouco
//	public static void createBT (ArrayList<Vertice>[] graph, int root) {
//		for(Vertice v : graph[root]){
//			BT_BINARY_TREE.insert(v);
//		}
//		BT_BINARY_TREE.preorder();
//	}
}


/*	Steps =>
 *
 * Breadth-First-Search(Graph, root):
 2 
 3     for each node n in Graph:            
 4         n.distance = INFINITY        
 5         n.parent = NIL
 6 
 7     create empty queue Q      
 8 
 9     root.distance = 0
10     Q.enqueue(root)                      
11 
12     while Q is not empty:        
13     
14         current = Q.dequeue()
15     
16         for each node n that is adjacent to current:
17             if n.distance == INFINITY:
18                 n.distance = current.distance + 1
19                 n.parent = current
20                 Q.enqueue(n)
*/
 