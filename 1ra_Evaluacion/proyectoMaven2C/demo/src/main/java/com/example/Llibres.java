package com.example;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//  Clase que representa la lista de libros en el documento XML
@XmlRootElement(name = "llibres")
public class Llibres {
    private List<Llibre> llibre;
    
    @XmlElement(name = "llibre")
    public List<Llibre> getLlibre() {
        return llibre;
    }
    
    public void setLlibre(List<Llibre> llibre) {
        this.llibre = llibre;
    }
}