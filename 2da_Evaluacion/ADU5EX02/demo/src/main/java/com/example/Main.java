package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.BsonDocument;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {
            // Rutas de los archivos
            String xmlFilePath = "archivo.xml";
            String jsonFilePath = "archivo.json";
            String bsonFilePath = "archivo.bson";
            String outputXmlFilePath = "archivo_convertido.xml";

            // Leemos el contenido del archivo XML
            String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            System.out.println("Contenido original del archivo XML:");
            System.out.println(xmlContent);

            // Convierte XML a JSON
            Xml2Json xml2Json = new Xml2Json();
            JSONObject jsonObject = xml2Json.convertirXmlAJson(xmlContent);

            // Guarda JSON en un archivo
            Files.write(Paths.get(jsonFilePath), jsonObject.toString(4).getBytes());
            System.out.println("\nJSON generado:");
            System.out.println(jsonObject.toString(4));

            // Lee contenido del archivo JSON
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONObject jsonFromFile = new JSONObject(jsonContent);

            // Convierte JSON a BSON
            Json2Bson json2Bson = new Json2Bson();
            BsonDocument bsonDocument = json2Bson.convertirJsonABson(jsonFromFile);

            // Guarda BSON en un archivo
            Files.write(Paths.get(bsonFilePath), bsonDocument.toJson().getBytes());
            System.out.println("\nBSON generado (en formato binario):");
            System.out.println(bsonDocument.toJson());

            // Lee BSON desde el archivo y convierte a JSON
            byte[] bsonData = Files.readAllBytes(Paths.get(bsonFilePath));
            BsonDocument bsonFromFile = BsonDocument.parse(new String(bsonData));
            JSONObject jsonFromBson = json2Bson.convertirBsonAJson(bsonFromFile);

            System.out.println("\nJSON generado desde BSON:");
            System.out.println(jsonFromBson.toString(4));

            // Convierte JSON a XML
            Json2Xml json2Xml = new Json2Xml();
            String xmlConverted = json2Xml.convertirJsonAXml(jsonFromBson);

            // Guarda XML convertido en un archivo
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
