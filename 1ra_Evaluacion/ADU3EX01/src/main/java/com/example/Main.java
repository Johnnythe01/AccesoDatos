package com.example;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
// Crea la unidad de persistencia. Se define en un archivo persistence.xml
 EntityManagerFactory enf
        Persistence.createEntityManagerFactory("objectdb_example.odb");
        EntityManager en emf.createEntityManager();
        try {
// Inicia una transacción
            em.getTransaction().begin();
// Crear y persistir nuevos objetos
            Persona personal = new Persona(nombre
            : "Juan", edad:
            30
            ); Persona persona2 = new Persona(nombre
            : "Maria", edad:
            25
            ); Persona persona3 = new Persona(nombre
            : "paquito", edad:
            45
            ); em.persist(personal);
            em.persist(persona2);
            em.persist(persona3);
// Finaliza la transacción em.getTransaction().commit();
            System.out.println(x
            :"Personas guardadas:");
// Consultar los objetos guardados
List<Persona> personas en.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
// Mostrar los objetos recuperados
            for (Persona persona personas) {
            }
            System.out.println(persona);
        } catch (Exception e) {
// Si ocurre un error, deshace la transacción if (em.getTransaction().isActive()) {
        }
        em.getTransaction().rollback();
        e.printStackTrace();
        finally {
// Cierra el EntityManager
                    em.close();
                    emf.close();
        }
    }
}
