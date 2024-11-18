package com.example;

import javax.persistence.*;

@Entity

public class Persona {
    @Id
    @GeneratedValuel(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private int edad;

    public Persona() {
        // Constructor sin parámetros (requerido por JPA)
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Long getId() {
    return id;
    }

    public String getNombre() {
    return nombre;
    }

    public int getEdad() {
    return edad;
}

    @Override
    public String toString() {
        return "Persona{id=" + id + ", nombre='" + nombre + "', edad=" + edad + "}";
    }
}