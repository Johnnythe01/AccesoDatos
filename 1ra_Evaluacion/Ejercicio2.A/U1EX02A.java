import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class U1EX02A {

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File archivoXML = new File("C:\\Users\\alumne-DAM\\Documents\\DAM 2º\\Acceso a Datos\\AccesoDatos\\1ra_Evaluacion\\Ejercicio2.A\\libros.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            // Obtener todos los elementos <llibre>
            NodeList nList = doc.getElementsByTagName("llibre");

            // Recorrer la lista de libros
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    // Obtener los valores de cada campo
                    String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
                    String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
                    String any = eElement.getElementsByTagName("any").item(0).getTextContent();
                    String resum = eElement.getElementsByTagName("resum").item(0).getTextContent();

                    // Imprimir por consola
                    System.out.println("Autor: " + autor);
                    System.out.println("Título: " + titol);
                    System.out.println("Año: " + any);
                    System.out.println("Resumen: " + resum);
                    System.out.println();

                    // Crear un archivo de texto para el autor (parte No-DUAL)
                    crearArchivoDeAutor(autor, titol, any, resum);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para crear un archivo de texto para cada autor
    private static void crearArchivoDeAutor(String autor, String titol, String any, String resum) {
        try {
            // Reemplazar espacios en el nombre del autor para el nombre del archivo
            FileWriter escritor = new FileWriter(autor.replace(" ", "_") + ".txt", true);

            // Escribir la información en el archivo
            escritor.write("Autor: " + autor + "\n");
            escritor.write("Título: " + titol + "\n");
            escritor.write("Año: " + any + "\n");
            escritor.write("Resumen: " + resum + "\n");
            escritor.write("-------------------------\n");
            escritor.close();

            System.out.println("Archivo creado o actualizado para el autor: " + autor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}