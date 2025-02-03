package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertDocument {

    public static void main(String[] args) {

        String uri = "mongodb://localhost:27017/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("practica_java");

            // Un pequeño menu de consola para introducir nombre de la coleccion
            System.out.print("Introduce el nombre de la coleccion a consultar: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String collectionName = scanner.nextLine();
            scanner.close();

            // Obtenemos la coleccion
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Obtenemos TODOS los documentos de la coleccion
            FindIterable<Document> documents = collection.find();

            Json2Xml json2Xml = new Json2Xml();
            int count = 1;

            // Iteramos sobre los documentos con el bucle
            for (Document doc : documents) {
                // Imprimimos el documento original en JSON
                System.out.println("Documento JSON: ");
                System.out.println(doc.toJson());  // Mostrar el documento original en JSON

                JSONObject jsonObject = new JSONObject(doc.toJson());

                // Convertimos JSON a XML
                String xmlConverted = json2Xml.convertirJsonAXml(jsonObject);

                // Crear un nombre de archivo único
                String fileName = "documento_" + count + ".xml";
                Files.write(Paths.get(fileName), xmlConverted.getBytes());

                System.out.println("XML guardado en: " + fileName);
                System.out.println(xmlConverted);
                count++;
            }

            System.out.println("Todos los documentos han sido procesados correctamente.");

        } catch (Exception e) {
            System.err.println("Error de conexion o procesamiento: " + e.getMessage());
        }
    }
}
