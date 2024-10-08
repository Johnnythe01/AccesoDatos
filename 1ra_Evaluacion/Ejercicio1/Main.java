
public class Main {
    
    public static void main(String[] args){

        String pathtxt = "input1.txt";
        String pathtxtDestino = "copia_input2.txt";
        String pathImagen = "cuesta_obispo.jpg";
        AccessFileReader12345678T lector = new AccessFileReader12345678T();
        String Contenido = lector.LeerArchivo(pathtxt);
        System.out.println("archivo1:");
        System.out.println(Contenido);

        AccessFileWriter12345678T escritor = new AccessFileWriter12345678T();
        System.out.println("escribiendo archivo2...");
        escritor.EscritorArchivo(pathtxtDestino, Contenido);

        Contenido = lector.LeerArchivo(pathtxtDestino);
        System.out.println("archivo2:");
        System.out.println(Contenido);

        AccessFileInputStream12345678T accessFileInputStream12345678T = new AccessFileInputStream12345678T();
        accessFileInputStream12345678T.LeerArchivo(pathtxt);

        AccessFileOutputStream12345678T accessFileOutputStream12345678T = new AccessFileOutputStream12345678T();
        accessFileOutputStream12345678T.EscritorArchivoBinario(pathtxtDestino, "Hola Mundo");

        AccessFilePermission12345678T accessFilePermission12345678T = new AccessFilePermission12345678T();
        accessFilePermission12345678T.QuitarPermisoEscritura(pathtxtDestino);

        lector.LeerArchivo(pathtxtDestino); //leer el .txt, esta vez sin permisos de lectura.

    }
}

/*

-File
-FileInputStream
-FileReader
-FileOutputStream
-FileWriter

The objective is to create an example with each of the JAVA classes watched at class for the management of files
and directories. 
First, you have to execute your program with access to the reading file. After that, you have to make a copy
of the original content of the file to another new file. Then you have to remove permission for reading the
file and execute it another time. For this option you have to show/print a message “YOU CAN NOT READ FILE”.

It is mandatory for your delivery, calling your Java class with your document ID at the end of class.
Example: AccesFileWriter12345678T.java
                 AccesFileReader12345678T.java
                 ….

Extra points: allow configuration of path and file name with a configuration file, like “application.properties”
or “application.yml”. Recommendation: use relative path instead of complete path.
Your properties must be called like: “exercise.path”, ”exercise.nameFile”, “exercise.fileExtension”

 */