package aed03;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Arquivo<T extends Registro> {

    RandomAccessFile arq;
    String nomeArquivo;
    Class<T> classe;
    
    public Arquivo(String n, Class<T> c) throws Exception {
        nomeArquivo = n;
        classe = c;
        arq = new RandomAccessFile(nomeArquivo, "rw");
        if(arq.length()<4)
            arq.writeInt(0);
    }
    
    public int incluir(T l) throws Exception {
        arq.seek(0);
        int cod = arq.readInt();
        cod++;
        arq.seek(arq.length());
        l.setCodigo(cod);
        arq.writeByte(' ');
        l.writeRegistroIndicadorTamanho(arq);
        arq.seek(0);
        arq.writeInt(cod);
        return cod;
    }
    
    public Object[] listar() throws Exception {
        ArrayList<T> lista = new ArrayList<>();
        arq.seek(4);
        byte lapide;
        T l;
        while(arq.getFilePointer()<arq.length()) {
            l = classe.newInstance();
            lapide = arq.readByte();
            l.readRegistroIndicadorTamanho(arq);
            if(lapide==' ')
                lista.add(l);
        }
        
        Object[] ls = lista.toArray();
        return ls;
    }
    
    public T buscar(int c) throws Exception {
        
        T aux = classe.newInstance();
        byte lapide;
        long pos;
        arq.seek(4);
        while(arq.getFilePointer()<arq.length()) {
            pos = arq.getFilePointer();
            lapide = arq.readByte();
            aux.readRegistroIndicadorTamanho(arq);
            if(lapide==' ' && aux.getCodigo() == c) {
                arq.seek(pos);
                return aux;
            }
        }
        return null;
    }
    
    
    public boolean excluir() throws Exception {
        arq.write('*');
        return true;
    }
    
    public boolean alterar(T l) throws Exception {
        arq.write('*');

        arq.seek(arq.length());
        arq.writeByte(' ');
        l.writeRegistroIndicadorTamanho(arq);
        return true;
    }
    
    public void reorganizar() throws Exception{
    	int tamBlocoMem = 3;
    	arq.seek(0);
    	int topo = arq.readInt();
    	List<T> registrosOrdenados = new ArrayList<>();
    	int contador = 0, seletor = 0;
    	int tam, cod;
    	byte [] data;
    	byte lapide;
    	T	r1= classe.newInstance(),
    		r2= classe.newInstance(),
    		r3= classe.newInstance();
    	T	rAnt1, rAnt2, rAnt3;
    	DataOutputStream dos1 = new DataOutputStream(new FileOutputStream(""));
    	DataOutputStream dos2 = new DataOutputStream(new FileOutputStream(""));
    	DataOutputStream dos3 = new DataOutputStream(new FileOutputStream(""));
    	DataOutputStream out = dos1;
    	try{
    		contador = 0;
    		seletor = 0;
    		while(true){
    			lapide = arq.readByte();
    			r1.readRegistroIndicadorTamanho(arq);
    			if(lapide == ' '){
    				registrosOrdenados.add((T)r1.clone());
    				contador++;
    			}
    			switch(seletor){
    				case 0: out = dos1;	break;
    				case 1: out = dos2; break;
    				case 2: out = dos3; break;
    			
    			}
    			
    			for(T r:registrosOrdenados){
    				r.writeRegistroIndicadorTamanho(out);
    			}
    			
    		}
    	
    	dos1.close();
    	dos2.close();
    	dos3.close();
    	boolean sentido = true;
    	DataInputStream in1, in2, in3;
    	boolean maisIntercalacoes = true;
    	boolean compara1, compara2, compara3;//indica que ha mais registros no arquivo tmp correspondente
    	boolean terminou1, terminou2,terminou3;//indica que acabou os registros no arquivo temporario correspondente
    	boolean mudou1, mudou2, mudou3; // indica se mudou de arquivo
    	while(maisIntercalacoes) {
    		if (sentido){
    			in1 = new DataInputStream(new FileInputStream("tmp1.db"));
    			in2 = new DataInputStream(new FileInputStream("tmp2.db"));
    			in3 = new DataInputStream(new FileInputStream("tmp3.db"));
    			dos1 = new DataOutputStream(new FileOutputStream("tmp4.db"));
    			dos2 = new DataOutputStream(new FileOutputStream("tmp5.db"));
    			dos3 = new DataOutputStream(new FileOutputStream("tmp6.db"));
    		} 
    		else {
    			in1 = new DataInputStream(new FileInputStream("tmp4.db"));
    			in2 = new DataInputStream(new FileInputStream("tmp5.db"));
    			in3 = new DataInputStream(new FileInputStream("tmp6.db"));
    			dos1 = new DataOutputStream(new FileOutputStream("tmp1.db"));
    			dos2 = new DataOutputStream(new FileOutputStream("tmp2.db"));
    			dos3 = new DataOutputStream(new FileOutputStream("tmp1.db"));
    		}
    		sentido = !sentido;
    		seletor = 0;
    		
    		/*LIBERANDO REGISTROS DE INTERCALACOES ANTERIORES*/
    		
    		r1 = classe.newInstance();
    		r2 = classe.newInstance();
    		r3 = classe.newInstance();
    		
    		terminou1 = terminou2 = terminou3 = false;
    		while(!terminou1 || !terminou2 || !terminou3){
    			
    		}
    		
    	}
    	} 
    }
}
