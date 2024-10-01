import java.io.*;
import java.util.Scanner;

public class AccessFileWriter12345678T {
    public static void copiarContenido(){
        Scanner escaner;
        escaner = new Scanner(System.in);

        System.out.println("Escribe la ruta del archivo a copiar");
        String archivoOrigen = escaner.nextLine();

        System.out.println("Escribe la ruta del archivo a copiar");
        String archivoDestino = escaner.nextLine();

        try (FileReader fr = new FileReader(archivoOrigen);
             BufferedReader br = new BufferedReader(fr);
             
             FileWriter fw = new FileWriter(archivoDestino)) {
             BufferedWriter bw = new BufferedWriter(fw);
            
             String linea;

            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Archivo copiado correctamente al destino: ");
            
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
}

