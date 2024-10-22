import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibroHandler extends DefaultHandler {

    //creo las variables necesarias
    private StringBuilder value;
    private StringBuilder contenidoLibro;
    private String anyActual;

    //inicializar variables para acumular texto/contenido
    public LibroHandler() {
        this.value = new StringBuilder();
        this.contenidoLibro = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0); //limpia el contenido de la variable

        if (qName.equals("llibre")) {
            contenidoLibro.setLength(0); //limpia el contenido del libro
            anyActual = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length); //acumula el contenido
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String texto = "";
        switch (qName) {
            case "llibre": //cuando se cierra el tag llibre
                if (anyActual != null) {
                    escribirEnArchivo(anyActual, contenidoLibro.toString());
                } else {
                    System.out.println("Error: Libro sin año"); //si no tiene año, no se puede escribir en archivo
                }
                break;
                //ahora se extrae el contenido de los tags
            case "autor":
                texto = "Autor: " + value.toString(); //extrae el contenido del tag autor
                contenidoLibro.append(texto).append("\n");
                break;
            case "titol":
                texto = "Titol: " + value.toString(); //extrae el contenido del tag titol
                contenidoLibro.append(texto).append("\n");
                break;
            case "any":
                texto = "Any: " + value.toString(); //extrae el contenido del tag any
                contenidoLibro.append(texto).append("\n");
                anyActual = value.toString();
                break;
            case "resum":
                texto = "Resum: " + value.toString(); //extrae el contenido del tag resum
                contenidoLibro.append(texto).append("\n");
                break;
        }
    }

    //metodo para escribir en un archivo de texto el contenido de los libros
    private void escribirEnArchivo(String any, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(any + ".txt", true))) {
            writer.write(contenido);
            writer.write("\n"); //salto de linea simple
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
