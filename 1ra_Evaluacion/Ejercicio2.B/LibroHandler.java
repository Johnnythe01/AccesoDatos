import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibroHandler extends DefaultHandler {

    private StringBuilder value;
    private StringBuilder contenidoLibro;
    private String anyActual;

    public LibroHandler() {
        this.value = new StringBuilder();
        this.contenidoLibro = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);

        if (qName.equals("llibre")) {
            contenidoLibro.setLength(0);
            anyActual = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String texto = "";
        switch (qName) {
            case "llibre":
                if (anyActual != null) {
                    escribirEnArchivo(anyActual, contenidoLibro.toString());
                } else {
                    System.out.println("Error: Libro sin año");
                }
                break;
            case "autor":
                texto = "Autor: " + value.toString();
                contenidoLibro.append(texto).append("\n");
                break;
            case "titol":
                texto = "Titol: " + value.toString();
                contenidoLibro.append(texto).append("\n");
                break;
            case "any":
                texto = "Any: " + value.toString();
                contenidoLibro.append(texto).append("\n");
                anyActual = value.toString();
                break;
            case "resum":
                texto = "Resum: " + value.toString();
                contenidoLibro.append(texto).append("\n");
                break;
        }
    }

    // Método para escribir el contenido del libro en el archivo correspondiente al año
    private void escribirEnArchivo(String any, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(any + ".txt", true))) {
            writer.write(contenido);
            writer.write("\n"); // Añadir una línea en blanco entre libros
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
