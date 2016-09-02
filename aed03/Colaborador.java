package aed03;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Colaborador implements Registro {
    protected int codigo;
	protected String name;
    protected String email;
    
    public Colaborador(int c, String n, String e) {
        codigo = c;
        name = n;
        email = e;
    }
    public Colaborador() {
        codigo = 0;
        name = "";
        email = "";
    }
    
    public void setCodigo(int c) {
        codigo = c;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String toString() {
        return "\nCódigo...:" + codigo +
                "\nNome....:" + name +
                "\nEmail...:" + email;
    }
    

    public final void writeRegistroIndicadorTamanho(RandomAccessFile arq) throws IOException {

        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( registro );
        
        saida.writeInt(codigo);
        saida.writeUTF(name);
        saida.writeUTF(email);
        byte[] buffer = registro.toByteArray();
        
        short tamanho = (short)buffer.length;
        arq.writeShort(tamanho);
        arq.write(buffer);

    }
    
    public final void writeRegistroIndicadorTamanho(DataOutputStream arq) throws IOException {

        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( registro );
        
        saida.writeInt(codigo);
        saida.writeUTF(name);
        saida.writeUTF(email);
        byte[] buffer = registro.toByteArray();
        
        short tamanho = (short)buffer.length;
        arq.writeShort(tamanho);
        arq.write(buffer);

    }
    
    public final void readRegistroIndicadorTamanho(RandomAccessFile arq) throws IOException, ClassNotFoundException {
        
        short tamanho = arq.readShort();
        byte[] ba = new byte[tamanho];
        if(arq.read(ba) != tamanho) throw new IOException("Dados inconsistentes");
        
        ByteArrayInputStream registro = new ByteArrayInputStream(ba);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        name = entrada.readUTF();
        email = entrada.readUTF();
    }
    
public final void readRegistroIndicadorTamanho(DataInputStream arq) throws IOException, ClassNotFoundException {
        
        short tamanho = arq.readShort();
        byte[] ba = new byte[tamanho];
        if(arq.read(ba) != tamanho) throw new IOException("Dados inconsistentes");
        
        ByteArrayInputStream registro = new ByteArrayInputStream(ba);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        name = entrada.readUTF();
        email = entrada.readUTF();
    }

    public Object clone() throws CloneNotSupportedException{
    	return super.clone();
    }
    
    public int compareTo(){
    	return 1;
    }
    
}
