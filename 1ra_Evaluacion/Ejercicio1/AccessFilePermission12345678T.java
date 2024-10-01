import java.io.File;

public class AccessFilePermission12345678T {
    public static void main(String[] args) {
        File file = new File("input1.txt");  // El archivo del cual hemos quitado permisos de lectura

        if (file.exists()) {
            boolean result = file.setReadable(false);  // Quitar permisos de lectura

            if (!result) {
                System.out.println("Failed to remove read permission.");
            } else {
                System.out.println("Read permission removed.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}