import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccessFileReader12345678T {
    public String LeerArchivo(String path) {
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            // System.out.println("Archivo 1 leído correctamente: \n" + contenido.toString());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return contenido.toString();  // Devolvemos todo el contenido del archivo como una cadena
    }
}