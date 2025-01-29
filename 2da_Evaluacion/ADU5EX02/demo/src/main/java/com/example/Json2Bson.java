package com.example;

import org.bson.BsonDocument;
import org.bson.BsonInvalidOperationException;
import org.bson.json.JsonParseException;
import org.json.JSONObject;

class Json2Bson {
    public BsonDocument convertirJsonABson(JSONObject jsonObject) throws Exception {
        try {
            return BsonDocument.parse(jsonObject.toString());
        } catch (JsonParseException e) {
            throw new Exception("Error al convertir JSON a BSON: " + e.getMessage());
        }
    }

    public JSONObject convertirBsonAJson(BsonDocument bsonDocument) throws Exception {
        try {
            return new JSONObject(bsonDocument.toJson());
        } catch (BsonInvalidOperationException e) {
            throw new Exception("Error al convertir BSON a JSON: " + e.getMessage());
        }
    }
}