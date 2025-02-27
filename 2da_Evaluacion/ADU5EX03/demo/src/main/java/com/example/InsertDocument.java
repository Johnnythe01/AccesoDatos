package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertDocument {

    public static void main(String[] args) {

        // Esto es simplemente para desactivar logs de MongoDB, si se añade, saldrían muchos mensajes de iformación
        Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);

        String uri = "mongodb+srv://user:passguord@cluster0.to9ja.mongodb.net"; // Conexión a MongoDB en localhost

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("AD2025");
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
                System.out.println(doc.toJson());  // Muestra el documento original en JSON

                // Convertimos el documento a JSON
                JSONObject jsonObject = new JSONObject(doc.toJson());

                // Convertimos JSON a XML
                String xmlConverted = json2Xml.convertirJsonAXml(jsonObject);

                // Crear un nombre de archivo para guardar el XML
                String fileName = "documento_" + count + ".xml";
                Files.write(Paths.get(fileName), xmlConverted.getBytes());

                // Imprimimos el XML convertido por consola
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
