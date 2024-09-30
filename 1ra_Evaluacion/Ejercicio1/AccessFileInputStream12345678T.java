import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;

public class AccessFileInputStream12345678T {

  public static void main(String[] args) {
      FileInputStream fileInputStream = new FileInputStream("C:\\\\Users\\\\alumne-DAM\\\\Documents\\\\DAM 2º\\\\Acceso a Datos\\\\AccesoDatos\\\\input1.txt");
  
  
  int data = fileInputStream.read();
  while(data != -1) {
      // do something with data variable
  
      data = fileInputStream.read(); // read next byte
  }
  }
}