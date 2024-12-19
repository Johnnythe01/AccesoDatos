package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class BibliotecaApp {
    public static void main(String[] args) {
        // Crear EntityManagerFactory desde persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");

        // Crear EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Crear un autor
            Autor autor = new Autor();
            autor.setNom("Gabriel García Márquez");
            autor.setDataNaixement(new java.util.Date());

            // Crear un libro
            Llibre llibre = new Llibre();
            llibre.setTitol("Cien Años de Soledad");
            llibre.setAnyPublicacio(1967);
            llibre.setAutor(autor);

            // Relacionar el autor con el libro
            autor.setLlibreList(List.of(llibre));

            // Iniciar transacción
            em.getTransaction().begin();

            // Persistir el autor (y gracias a CascadeType.ALL, también el libro)
            em.persist(autor);

            // Commit
            em.getTransaction().commit();
            System.out.println("Datos insertados correctamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
