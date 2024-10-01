import java.io.*;

public class AccessFileWriter12345678T {
    public static void main(String[] args) {
        File archivoOrigen = new File("input1.txt");    // Archivo original
        File archivoDestino = new File("copia_input1.txt"); // Archivo copiado

        try (FileReader fr = new FileReader(archivoOrigen);
             FileWriter fw = new FileWriter(archivoDestino)) {

            System.out.println("Archivo copiado correctamente al destino: " + archivoDestino.getPath());
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
}

