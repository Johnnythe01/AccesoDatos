import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AccessFileInputStream12345678T {
    public void LeerArchivo(String path) {
        File archivo = new File("C:\\Users\\alumne-DAM\\Documents\\DAM 2º\\Acceso a Datos\\AccesoDatos\\cuesta_obispo.jpg");
        try (FileInputStream fis = new FileInputStream(archivo)) {
            int byteData;
            // Leemos el archivo byte por byte
            while ((byteData = fis.read()) != -1) {
            }
            System.out.println("Archivo leído correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AccessFileInputStream12345678T lector = new AccessFileInputStream12345678T();
        lector.LeerArchivo("C:\\Users\\alumne-DAM\\Documents\\DAM 2º\\Acceso a Datos\\AccesoDatos\\cuesta_obispo.jpg");
    }
}