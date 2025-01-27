package com.example;

import org.json.JSONObject;
import org.json.XML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Clase para convertir XML a JSON
class Xml2Json {
    public JSONObject convertirXmlAJson(String xmlContent) throws Exception {
        try {
            return XML.toJSONObject(xmlContent);
        } catch (Exception e) {
            throw new Exception("Error al convertir XML a JSON: " + e.getMessage());
        }
    }
}

// Clase para convertir JSON a XML
class Json2Xml {
    public String convertirJsonAXml(JSONObject jsonObject) throws Exception {
        try {
            return XML.toString(jsonObject);
        } catch (Exception e) {
            throw new Exception("Error al convertir JSON a XML: " + e.getMessage());
        }
    }
}

// Clase principal para realizar las pruebas
public class Main {
    public static void main(String[] args) {
        try {
            // Ruta de los archivos
            String xmlFilePath = "archivo.xml";
            String jsonFilePath = "archivo.json";
            String outputXmlFilePath = "archivo_convertido.xml";

            // Leer contenido del archivo XML
            String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            System.out.println("Contenido original del archivo XML:");
            System.out.println(xmlContent);

            // Convertir XML a JSON
            Xml2Json xml2Json = new Xml2Json();
            JSONObject jsonObject = xml2Json.convertirXmlAJson(xmlContent);

            // Guardar JSON en un archivo
            Files.write(Paths.get(jsonFilePath), jsonObject.toString(4).getBytes());
            System.out.println("\nJSON generado:");
            System.out.println(jsonObject.toString(4));

            // Leer contenido del archivo JSON
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONObject jsonFromFile = new JSONObject(jsonContent);

            // Convertir JSON a XML
            Json2Xml json2Xml = new Json2Xml();
            String xmlConverted = json2Xml.convertirJsonAXml(jsonFromFile);

            // Guardar XML convertido en un archivo
            Files.write(Paths.get(outputXmlFilePath), xmlConverted.getBytes());
            System.out.println("\nXML generado desde JSON:");
            System.out.println(xmlConverted);

        } catch (IOException e) {
            System.err.println("Error al leer o escribir archivos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
