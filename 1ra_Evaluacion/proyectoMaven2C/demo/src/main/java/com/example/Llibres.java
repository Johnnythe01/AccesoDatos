package com.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

// Clase que representa el elemento raíz <llibres>
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
