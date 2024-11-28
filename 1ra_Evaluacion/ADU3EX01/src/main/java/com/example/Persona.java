package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // sirve para que la base de datos genere el id automáticamente
    private Long id;
    private String nombre;
    private int edad;
    private String email;

    public Persona(String nombre, int edad, String email) { 
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Persona %d, Nombre: '%s', Edad: %d, Email: %s", id, nombre, edad, email);
    }
}