import java.io.*;
import java.util.Scanner;

public class Ejercicio1 {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Introduce el nombre del fichero: ");
    String nombreFichero = sc.nextLine();
    File fichero = new File(nombreFichero);
    if (fichero.exists()) {
        System.out.println("El fichero ya existe");
    } else {
        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado");
            } else {
                System.out.println("No se ha podido crear el fichero");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    sc.close();
}
    
}    
