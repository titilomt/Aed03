package grafos;

public class Lista {
	private Celula primeiro;
	private Celula ultimo;


	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserir(Celula c) {
		Celula tmp = c;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}

   public int tamanho() {
      int tamanho = 0;
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}
