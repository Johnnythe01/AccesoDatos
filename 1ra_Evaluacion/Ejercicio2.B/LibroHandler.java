import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibroHandler extends DefaultHandler {

    private StringBuilder value;

    public LibroHandler() {
        this.value = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes)
            throws SAXException {

        this.value.setLength(0);

        // if (qName.equals("llibre")) {
        //     String any = attributes.getValue("any");
        //     System.out.println("Atribut any: " + any);
        // }

    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {

        this.value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        switch (qName) {
            case "llibre":
                System.out.println("");
                break;
            case "autor":
                System.out.println("Autor: " + this.value.toString());
                break;
            case "titol":
                System.out.println("Titol: " + this.value.toString());
                break;
            case "any":
                System.out.println("Any: " + this.value.toString());
                break;
            case "resum":
                System.out.println("Resum: " + this.value.toString());
                break;
        }

    }

}