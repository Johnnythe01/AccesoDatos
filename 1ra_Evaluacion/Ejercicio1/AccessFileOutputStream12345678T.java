import java.io.*;

public class AccessFileOutputStream12345678T {
    public void EscritorArchivoBinario(String pathOrigen, String pathDestino) {
        File archivoOrigen = new File(pathOrigen);    // Archivo original
        File archivoDestino = new File(pathDestino);  // Archivo destino

        // Comprobamos si el archivo de origen existe
        if (!archivoOrigen.exists()) {
            System.out.println("El archivo original no existe: " + pathOrigen);
            return;
        }

        // Utilizamos FileInputStream y FileOutputStream para copiar archivos binarios
        try (FileInputStream fis = new FileInputStream(archivoOrigen);
             FileOutputStream fos = new FileOutputStream(archivoDestino)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            // Leemos y escribimos el archivo en bloques de bytes
            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);  // Escribimos los bytes leídos en el archivo de destino
            }

            System.out.println("Archivo binario copiado correctamente al destino: " + archivoDestino.getPath());

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AccessFileOutputStream12345678T escritor = new AccessFileOutputStream12345678T();
        escritor.EscritorArchivoBinario("cuesta_obispo.jpg", "cuesta_obispo2.jpg");
    }
}