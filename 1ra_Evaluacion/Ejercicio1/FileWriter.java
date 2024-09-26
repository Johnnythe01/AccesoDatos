import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();
        System.out.println("Introduce el texto a escribir en el fichero: ");
        String texto = sc.nextLine();
        try {
            FileWriter fw = new FileWriter(nombreFichero);
            fw.write(texto);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
        sc.close();
    }  
}
