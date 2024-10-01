import java.io.*;

public class AccessFileWriter12345678T {
    public void EscritorArchivo(String path, String contenido) {
        File archivoOrigen = new File("input1.txt");    // Archivo original
        File archivoDestino = new File("copia_input1.txt"); // Archivo copiado

        // Comprobamos si el archivo de origen existe
        if (!archivoOrigen.exists()) {
            System.out.println("El archivo original no existe.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOrigen));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDestino))) {

            String linea;
            // Leemos el archivo línea por línea y escribimos cada línea en el archivo de destino
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();  // Añadimos un salto de línea para mantener el formato original
            }

            System.out.println("Archivo copiado correctamente al destino: " + archivoDestino.getPath());

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
}
