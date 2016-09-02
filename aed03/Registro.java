package aed03;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public interface Registro extends Cloneable , Comparable {
    
    public void setCodigo(int codigo);
    public int getCodigo();
    
    public void writeRegistroIndicadorTamanho(RandomAccessFile arq) throws IOException;
    public void writeRegistroIndicadorTamanho(DataOutputStream arq) throws IOException;
    public void readRegistroIndicadorTamanho(RandomAccessFile arq) throws IOException, ClassNotFoundException;
    public void readRegistroIndicadorTamanho(DataInputStream arq) throws IOException, ClassNotFoundException;
    public int compareTo();
    public Object clone() throws CloneNotSupportedException;
}
