package org.example;

import jakarta.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dataNaixement;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Llibre> llibreList;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public List<Llibre> getLlibreList() {
        return llibreList;
    }

    public void setLlibreList(List<Llibre> llibreList) {
        this.llibreList = llibreList;
    }
}
