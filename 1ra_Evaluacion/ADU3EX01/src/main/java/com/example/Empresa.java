package com.example;

import javax.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // sirve para que la base de datos genere el id automáticamente
    private Long id;
    private String nombre;
    private String cif;
    private String direccion;

    public Empresa(String nombre, String cif, String direccion) {
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "ID de empresa: " + id + ", Nombre: " + nombre + ", CIF: " + cif + ", Dirección: " + direccion + "]";
    }
}
