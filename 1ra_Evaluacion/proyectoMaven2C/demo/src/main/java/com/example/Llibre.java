package com.example;

import jakarta.xml.bind.annotation.XmlElement;

public class Llibre {

    // Atributos de la clase Llibre
    private String autor;
    private String titol;
    private int any;
    private String resum;

    // Métodos getter y setter

    @XmlElement
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @XmlElement
    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    @XmlElement
    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    @XmlElement
    public String getResum() {
        return resum;
    }

    public void setResum(String resum) {
        this.resum = resum;
    }

}