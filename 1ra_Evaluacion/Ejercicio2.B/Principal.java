import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Principal {

    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();

            LibroHandler handler = new LibroHandler();
            parser.parse("llibres.xml", handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

/* (Sólo por No-DUAL)
Haz un archivo de texto para cada uno de los años de publicación. Añade dentro de cada archivo lo mismo que sacas por la consola.
esta bien, el problema */
// el problema es que no está creando un archivo por cada año, sino que está creando un archivo con todos los libros dentro,como si fuera llibres.xml pero en .txt