package com.example.demo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productes") // Nombre de la tabla en la base de datos
public class Producte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de la clave primaria
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo") // Validación de los campos
    @Column(nullable = false)
    private String nom;

    private String descripcio;

    @NotNull(message = "El precio no puede ser nulo")
    @Column(nullable = false)
    private BigDecimal preu;

    @NotNull(message = "La cantidad no puede ser nula")
    @Column(nullable = false)
    private Integer quantitat;

    public Producte() { // Constructor vacío
    }

    public Producte(Integer id, String nom, String descripcio, BigDecimal preu, Integer quantitat) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.preu = preu;
        this.quantitat = quantitat;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public BigDecimal getPreu() {
        return preu;
    }

    public void setPreu(BigDecimal preu) {
        this.preu = preu;
    }

    public Integer getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(Integer quantitat) {
        this.quantitat = quantitat;
    }

    // toString, hashCode, equals
    @Override
    public String toString() {
        return "Producte{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", preu=" + preu +
                ", quantitat=" + quantitat +
                '}';
    }
}
