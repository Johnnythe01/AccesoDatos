import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class FileReader {

  Reader fileReader = new FileReader("C:\\Users\\alumne-DAM\\Documents\\DAM 2º\\Acceso a Datos\\AccesoDatos\\input-text.txt");

  int data = fileReader.read();
  while(data != -1) {
    data = fileReader.read();
  }
}
