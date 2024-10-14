import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

    public static void main(String[] args) {

        try {
            // Crear una instancia del SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Handler personalizado para procesar el XML
            DefaultHandler handler = new DefaultHandler() {

                String autor, titol, any, resum;
                boolean isAutor = false;
                boolean isTitol = false;
                boolean isAny = false;
                boolean isResum = false;

                Map<String, StringBuilder> anyFiles = new HashMap<>();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("autor")) {
                        isAutor = true;
                    } else if (qName.equalsIgnoreCase("titol")) {
                        isTitol = true;
                    } else if (qName.equalsIgnoreCase("any")) {
                        isAny = true;
                    } else if (qName.equalsIgnoreCase("resum")) {
                        isResum = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isAutor) {
                        autor = new String(ch, start, length);
                        isAutor = false;
                    } else if (isTitol) {
                        titol = new String(ch, start, length);
                        isTitol = false;
                    } else if (isAny) {
                        any = new String(ch, start, length);
                        isAny = false;
                    } else if (isResum) {
                        resum = new String(ch, start, length);
                        isResum = false;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("llibre")) {
                        
                        System.out.println("Autor: " + autor);
                        System.out.println("Títol: " + titol);
                        System.out.println("Any: " + any);
                        System.out.println("Resum: " + resum);
                        System.out.println();

                        // Añadir contenido a un StringBuilder específico para el año
                        anyFiles.putIfAbsent(any, new StringBuilder());
                        anyFiles.get(any).append("Autor: ").append(autor).append("\n")
                                        .append("Títol: ").append(titol).append("\n")
                                        .append("Any: ").append(any).append("\n")
                                        .append("Resum: ").append(resum).append("\n\n");
                    }
                }

                @Override
                public void endDocument() throws SAXException {
                    try {
                        // Crear un archivo para cada año con su contenido correspondiente
                        for (Map.Entry<String, StringBuilder> entry : anyFiles.entrySet()) {
                            String any = entry.getKey();
                            StringBuilder contenido = entry.getValue();
                            FileWriter writer = new FileWriter(any + ".txt");
                            writer.write(contenido.toString());
                            writer.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            // Parsear el archivo XML
            saxParser.parse(new File("llibres.xml"), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}