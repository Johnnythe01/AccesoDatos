package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // Crea la unidad de persistencia. Se define en un archivo persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_example.odb");
        EntityManager em = emf.createEntityManager();

        try {
            // Inicia una transacción
            em.getTransaction().begin();

            // Crear y persistir nuevos objetos
            Persona personal = new Persona("Juan", 30);
            Persona persona2 = new Persona("Maria", 25);
            Persona persona3 = new Persona("paquito", 45);
            em.persist(personal);
            em.persist(persona2);
            em.persist(persona3);

            // Finaliza la transacción
            em.getTransaction().commit();

            System.out.println("Personas guardadas:");

            // Consultar los objetos guardados
            List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();

            // Mostrar los objetos recuperados
            for (Persona persona : personas) {
                System.out.println(persona);
            }

        } catch (Exception e) {
        // Si ocurre un error, deshace la transacción
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        } finally {
        // Cierra el EntityManager
            em.close();
            emf.close();
        }
    }
}

// java -jar C:\Users\alumne-DAM\Desktop\objectdb-2.9.1\bin\explorer.jar