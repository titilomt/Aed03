
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static ArrayList []list;
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		String name = "";
		System.out.println("Entrar com nome do arquivo: (Se o arquivo possuir extencao favor digitar)");
		name = in.next();
		name = "//tmp//"+name;
		FileReader fr;
		// Leitura Monstro
		try {
			fr = new FileReader (name);
			BufferedReader br = new BufferedReader(fr); 
			int contador = 0;
			String line = br.readLine();
			list = new ArrayList[Integer.parseInt(line)];
			
			for(int i = 0; i < list.length; i ++){
				list[i] = new ArrayList();
			}
			
			while (line != null) {
				line = br.readLine();
				// Formar a lista de monstros
				String [] tmp = line.split(",");
				for(int i = 0; i < tmp.length; i++){
					if(tmp[i].equals("1")){
						Vertice v = new Vertice(1,"branca", i, 1);
						list[contador].add((Object)v);
					} 
				}
				contador++;
			}
			br.close();
			//printLista();
		} catch (FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioe) {
			// TODO: handle exception
//			ioe.printStackTrace();
			System.out.println("Arquivo nao encontrado.");
		}
		
	}
	
//	public static void printLista(){
//		for(int i = 0; i < vertices.length; i++){
//			for(Celula j = vertices[i].prox; j != null; j = j.prox){
//				System.out.print(""+ j.hasVertice);
//			}
//			System.out.println();
//		}
//	}
}
