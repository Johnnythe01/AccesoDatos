package com.example;

import org.json.JSONObject;
import org.json.XML;

class Xml2Json {
    public JSONObject convertirXmlAJson(String xmlContent) throws Exception {
        try {
            return XML.toJSONObject(xmlContent);
        } catch (Exception e) {
            throw new Exception("Error al convertir XML a JSON: " + e.getMessage());
        }
    }
}