import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Principal {

    public static void main(String[] args) {

        try {
            //creo el parser y el handler
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();
            
            LibroHandler handler = new LibroHandler();
            parser.parse("llibres.xml", handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) { //captura de excepciones
            System.out.println(ex.getMessage()); 
        }

    }

}

/* (Sólo por No-DUAL)
Haz un archivo de texto para cada uno de los años de publicación. Añade dentro de cada archivo lo mismo que sacas por la consola.
esta bien, el problema */