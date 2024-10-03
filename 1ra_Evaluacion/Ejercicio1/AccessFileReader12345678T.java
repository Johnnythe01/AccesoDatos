import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccessFileReader12345678T {
  public void LeerArchivo(String path) {
    try {
      File myObj = new File("C:\\Users\\alumne-DAM\\Documents\\DAM 2º\\Acceso a Datos\\AccesoDatos\\input1.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Ha ocurrido un error.");
      e.printStackTrace();
    }
  }
}