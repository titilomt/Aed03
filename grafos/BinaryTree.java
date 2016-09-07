
public class BinaryTree {
	
	public Node root;
	
	 /* Functions to insert data */
    public void insert(Vertice v)
    {
        root = insert(root, v);
    }
    /* Function to insert data recursively */
    private Node insert(Node node, Vertice data)
    {
        if (node == null)
            node = new Node(data);
        else
        {
            if (node.dir == null)
                node.esq = insert(node.dir, data);
            else
                node.dir = insert(node.esq, data);             
        }
        return node;
    }
    
    public void preorder() {
        preorder(root);
    }
    
    private void preorder(Node r){
        if (r != null) {
            System.out.print(r.v +" ");
            preorder(r.esq);
            preorder(r.dir);
        }
    }
}
