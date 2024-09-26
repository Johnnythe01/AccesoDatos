import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Scanner;

public class FileInputStream {
    InputStream input = new FileInputStream("C:\\\\Users\\\\alumne-DAM\\\\Documents\\\\DAM 2º\\\\Acceso a Datos\\\\AccesoDatos\\\\input-text.txt");

int data = input.read();
while(data != -1) {
  //do something with data...
  doSomethingWithData(data);

  data = input.read();
}
input.close();
}
