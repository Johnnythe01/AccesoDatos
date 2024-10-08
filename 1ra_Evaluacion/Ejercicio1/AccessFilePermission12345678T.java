import java.io.File;

public class AccessFilePermission12345678T {
    public void QuitarPermisoEscritura(String path) {
        File file = new File(path);  // Archivo del cual hemos quitado permisos de escritura

        if (file.exists()) {
            boolean result = file.setWritable(false);  // Quitar permisos de escritura

            if (!result) {
                System.out.println("No se pudo quitar el permiso de escritura.");
            } else {
                System.out.println("Se quitaron los permisos de escritura para: " + path);
                AccessFileWriter12345678T escritor = new AccessFileWriter12345678T();
                System.out.println("escribiendo archivo2");
                escritor.EscritorArchivo(path, "intentamos escribir??");
            }
        } else {
            System.out.println("El archivo no existe: " + path);
        }
    }
}