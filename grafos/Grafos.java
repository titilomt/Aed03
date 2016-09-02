package grafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Grafos{
	public static int [][] matrix;
	public static String []grafo;
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String name = "";
		grafo = new String [100];

		for(int i = 0; i < 100; i++)grafo[i]= "-9999";
		int option = 0;
		try {
			while (option > -1) {
				option = menu();
				switch (option) {
				case 1:
					System.out.println("Entrar com nome do arquivo: (Se o arquivo possuir extencao favor digitar)");
					name = in.next();
					name = "//tmp//"+name;
					FileReader fr = new FileReader (name);
					BufferedReader br = new BufferedReader(fr); 
					int contador = 0;
					String line = br.readLine();
					while (line != null) {						
						grafo[contador] = line;// build the monstro
						contador++;
						line = br.readLine();

					}

					grafo = formatGrafo(grafo);
					br.close();
					matrix = buildMatrix(grafo); // mandar para matriz
					printMatrix(matrix);
					break;

				case 2:
					int[][] complement = buildComplementMatrix(matrix); // matrix complementar
					printMatrix(complement);
					break;

				case 3:
					System.out
							.println("Entrar com vertice desejado (1 to N): ");
					int ver = in.nextInt();
					int resp = degreeVertice(ver, matrix);
					System.out.println("O grau do vertice, " + ver + " = "
							+ resp);
					break;

				case 4:
					boolean is = isComplete(matrix);
					if (is) {
						System.out.println("Grafo completo");
					} else
						System.out.println("Grafo nao e completo");
					break;

				case 5:
					int[] teste = allDegreeVertice(matrix);
					System.out
							.println("Entrar com o vertice para ver se e adjacente (1 to N): ");
					int i = in.nextInt() - 1;
					if (teste[i] > 0) {
						i = i + 1;
						System.out.println("O vertice " + i
								+ " tem adjacencia.");
					} else {
						i = i + 1;
						System.out.println("O vertice " + i
								+ " nao possui adjacencia.");
					}
					break;
					
					case 0:
						System.out.println("Fora do menu!");
					break;
				}
					
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	
	public static int menu(){
		int resp = 0;
		System.out.println("Bem vindo, o que deseja fazer ?");
		System.out
				.println("1 - Gravar o grafo;\n2 - Construir matriz complementar;\n3 - "
						+ "Descobrir o grau do vertice;\n4 - Grafo completo ou nao;\n5 - Descobrir a adjacencia do vertice; "
						+ "\nDigite -1 para terminar.\n");
		resp = in.nextInt();
		return resp;
	}
	
	public static String [] formatGrafo(String [] grafo){
		String [] grafoFormatado =null;
		int contador = 0;
		while(grafo[contador]!="-9999") {contador++;}
		
		grafoFormatado = new String [contador];
		
		for(int i = 0; i < contador; i++){
			grafoFormatado[i] = grafo[i];
		}
		return grafoFormatado;
	}
	
	public static int [][] buildMatrix(String [] grafo){
		
		int hight = Integer.parseInt(grafo[0]);
		int [][] matrix = new int [hight][hight];
		
		for(int i=0; i < hight; i++){
			
			String [] tmp = grafo[i+1].split(",");
			for (int j = 0; j < hight; j++){
				matrix[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		return matrix;
	}
	
	public static int [][] buildComplementMatrix(int [][]matrix){
		
		int hight = matrix.length;
		int [][] complement = new int [hight][hight];
		
		for(int i = 0; i < hight; i++){
			
			for(int j = 0; j < hight; j++){
				
				if(i!=j){
					complement[i][j] = matrix[i][j] != 0 ? 0: 1;
				} else {
					complement[i][j] = matrix[i][j];
				}
			}
		}
		
		return complement;
	}
	
	public static int degreeVertice(int vertice, int[][]matrix){
		vertice = vertice - 1;
		int resp = 0;
		for (int i = 0; i < matrix.length; i++){
			
			if(matrix[i][vertice] != 0){
				resp++;
			}
		}
		return resp;
	}
	
	public static int [] allDegreeVertice(int [][]matrix){
		
		int []allDegree = new int [matrix.length];
		for(int i = 0; i < matrix.length; i++){
			allDegree[i] = 0; 
		}
		for (int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				if(matrix[i][j]!=0){
					allDegree[j]++;
				}
			}
		}
		return allDegree;
	}
	
	public static void printMatrix(int matrix[][]){
		for (int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				System.out.print("["+matrix[i][j]+"] ");
			}
			System.out.println();
		}
	}
	
	public static boolean isComplete(int [][]matrix){
		boolean resp = true;
		for(int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix.length; j++){
				if(j!=i && matrix[i][j] == 0){
					resp = false;
				}
			}
		}
		return resp;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public static String[] getGrafo() {
		return grafo;
	}

	public static void setGrafo(String[] grafo) {
		Grafos.grafo = grafo;
	}
}
