import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Lista {
	
	public static ArrayList<Vertice> list;
	public static Scanner in = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String name = "";
		System.out.println("Entrar com nome do arquivo: (Se o arquivo possuir extencao favor digitar)");
		name = in.next();
		//name = "//tmp//"+name;   <--------- THIS IS FOR LINUX
		name = Paths.get(".").toAbsolutePath().normalize().toString().replace("\\", "//") +"//"+name; // FOR windows file in current DIR
		System.out.println(name);
		
		FileReader fr;
		// Leitura Monstro
		try {
			fr = new FileReader (name);
			BufferedReader br = new BufferedReader(fr); 
			int contador = 0;
			String line = br.readLine();
			//Iniciar bixao
			for (int i = 0; i < Integer.parseInt(line); i++){
				Vertice aux = new Vertice(0, "branco", i, false, -1);
				list.add(aux);
			}
			while (line != null) {
				line = br.readLine();
				// Formar a lista de monstros
				if(line != null){
					String [] tmp = line.split(",");
					int somador=0;
					somador = somar(tmp);
					for(int i = 0; i < tmp.length; i++){
						if (somador > 0) {
							list.get(contador).hasVizinho = true;
							if(tmp[i].equals("1")){
								Vertice v = new Vertice(1,"branca", i, true, contador);
								list.get(contador).listVertice.add(v);
							}
						}
					}
					contador++;
				}
			}
			br.close();
			printLista();
		} catch (FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ioe) {
			// TODO: handle exception
//			ioe.printStackTrace();
			System.out.println("Arquivo nao encontrado.");
		}
		
	}
	
	public static int somar (String [] s){
		int resp = 0;
		for(int i = 0; i < s.length; i ++){
			resp = resp + Integer.parseInt(s[i]);
		}
		return resp;
	}
	
	public static void printLista(){
		for(Vertice v : list){
			System.out.println(v.vertice+"\n");
			for(Vertice v2: v.listVertice){
				System.out.print(v2.vertice+"\t");
			}
		}
	}
}
