import java.io.*;

public class AccessFileWriter12345678T {
    public void EscritorArchivo(String path, String contenido) {
        try {
        FileWriter writer = new FileWriter(path);
        writer.write(contenido);
        writer.close();

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
}