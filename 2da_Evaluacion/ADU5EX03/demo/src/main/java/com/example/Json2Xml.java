package com.example;

import org.json.JSONObject;
import org.json.XML;

class Json2Xml {
    // Método para convertir un objeto JSON a XML
    public String convertirJsonAXml(JSONObject jsonObject) throws Exception {
        try {
            // Añado la etiqueta <documento> al principio y al final para que se entienda y quede bien formado
            return "<documento>" + XML.toString(jsonObject) + "</documento>";
        } catch (Exception e) {
            throw new Exception("Error al convertir JSON a XML: " + e.getMessage());
        }
    }
}
