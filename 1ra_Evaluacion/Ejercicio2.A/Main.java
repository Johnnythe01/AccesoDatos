import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:\\Users\\alumne-DAM\\Documents\\DAM2\\AccesoDatos\\1ra_Evaluacion\\Ejercicio2.A\\llibres.xml"));
            doc.getDocumentElement().normalize();

            // Obtener lista de libros y procesarla
            NodeList libros = doc.getElementsByTagName("llibre");
            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
                String titol = libro.getElementsByTagName("titol").item(0).getTextContent();
                String any = libro.getElementsByTagName("any").item(0).getTextContent();
                String resum = libro.getElementsByTagName("resum").item(0).getTextContent();

                // Imprimir por consola
                System.out.printf("Autor: %s\nTítulo: %s\nAño: %s\nResumen: %s\n\n", autor, titol, any, resum);

                // Crear archivo del autor (parte No-Dual)
                FileWriter escritor = new FileWriter(autor.replace(" ", "_") + ".txt", true);
                escritor.write(String.format("Autor: %s\nTítulo: %s\nAño: %s\nResumen: %s\n\n", autor, titol, any, resum));
                escritor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
U1EX02A

Ejercicio: Mapeo de un documento XML a objetos Java utilizando DOM
(por todos)
Objetivo: Crear un programa Java que utilice DOM para leer un documento XML que contiene
información sobre libros y convertirlo en objetos Java.
Imprime por consola el resultado de todos los campos de XML:


(Sólo para No-DUAL)
Haz un archivo de texto para cada uno de los autores. Añade dentro de cada archivo lo mismo
que sacas por la consola.
*/