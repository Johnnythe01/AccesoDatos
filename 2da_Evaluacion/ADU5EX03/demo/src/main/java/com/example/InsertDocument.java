package com.example;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertDocument {
    public static void main(String[] args) {

        String uri = "mongodb+srv://usuario:contraseña@<tu_cluster>/<nom_base_dades>?retryWrites=true&w=majority"; 
        // Para MongoDB Atlas
        // String uri = "mongodb://localhost:27017/"; // Para MongoDB local sin autenticación

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("nom_base_dades");
            MongoCollection<Document> collection = database.getCollection("nom_coleccio");

            // Ejemplo de JSON a insertar
            String jsonString = "{ \"nom\": \"Joan\", \"edat\": 30, \"ciutat\": \"Barcelona\", \"interessos\": [\"programació\", \"viatjar\"]}";

            try {
                JSONObject jsonObject = new JSONObject(jsonString);

                // Conversión de JSONObject a Document
                Document document = Document.parse(jsonObject.toString());

                // Insertar el documento
                collection.insertOne(document);
                System.out.println("Document insertat correctament.");

                // (Opcional) Para insertar múltiples documentos a la vez:
                /*
                List<Document> documents = new ArrayList<>();
                documents.add(Document.parse("{\"nom\": \"Maria\", \"edat\": 25}"));
                documents.add(Document.parse("{\"nom\": \"Pere\", \"edat\": 40}"));
                collection.insertMany(documents);
                System.out.println("Documents inserits correctament.");
                */

            } catch (JSONException e) {
                System.err.println("Error en parsejar JSON: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error de connexió: " + e.getMessage());
        }
    }
}
