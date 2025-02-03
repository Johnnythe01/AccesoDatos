package com.example;

import org.json.JSONObject;
import org.json.XML;

class Json2Xml {

    public String convertirJsonAXml(JSONObject jsonObject) throws Exception {
        try {
            // Agregamos una etiqueta raíz <documento> para evitar errores en la estructura XML
            return "<documento>" + XML.toString(jsonObject) + "</documento>";
        } catch (Exception e) {
            throw new Exception("Error al convertir JSON a XML: " + e.getMessage());
        }
    }
}
