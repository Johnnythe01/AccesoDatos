import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.json.XML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MongoDBToXML {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "practica_java";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom de la col·lecció: ");
        String collectionName = scanner.nextLine();
        scanner.close();

        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            for (Document doc : collection.find()) {
                String jsonString = doc.toJson();
                JSONObject jsonObject = new JSONObject(jsonString);
                String xmlString = XML.toString(jsonObject, "element");
                System.out.println(xmlString);
                saveToFile(xmlString, doc.getObjectId("_id").toString());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void saveToFile(String xmlContent, String fileName) {
        try {
            File file = new File("output/" + fileName + ".xml");
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xmlContent);
            }
            System.out.println("Arxiu creat: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error en desar l'arxiu: " + e.getMessage());
        }
    }
}
