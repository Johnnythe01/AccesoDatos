package com.example;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_example.odb");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Añadir Persona");
            System.out.println("2. Mostrar Personas");
            System.out.println("3. Modificar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> añadirPersona(scanner);
                case 2 -> mostrarPersonas();
                case 3 -> modificarPersona(scanner);
                case 4 -> eliminarPersona(scanner);
                case 5 -> {
                    emf.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void añadirPersona(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Email: ");
        String email = scanner.next();


        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Persona(nombre, edad, email));
            em.getTransaction().commit();
            System.out.println("Persona añadida con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al añadir la persona.");
        } finally {
            em.close();
        }
    }

    private static void mostrarPersonas() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
            if (personas.isEmpty()) {
                System.out.println("No hay personas registradas.");
            } else {
                System.out.println("Personas registradas:");
                personas.forEach(System.out::println);
            }
        } finally {
            em.close();
        }
    }

    private static void modificarPersona(Scanner scanner) {
        System.out.print("ID de la persona a modificar: ");
        Long id = scanner.nextLong();

        EntityManager em = emf.createEntityManager();
        try {
            Persona persona = em.find(Persona.class, id);
            if (persona == null) {
                System.out.println("Persona no encontrada.");
                return;
            }

            System.out.print("Nuevo nombre (dejar vacío para no modificar): ");
            String nuevoNombre = scanner.next();
            System.out.print("Nueva edad (introducir 0 para no modificar): ");
            int nuevaEdad = scanner.nextInt();

            em.getTransaction().begin();
            if (!nuevoNombre.isBlank()) persona.setNombre(nuevoNombre);
            if (nuevaEdad > 0) persona.setEdad(nuevaEdad);
            em.getTransaction().commit();

            System.out.println("Persona modificada con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al modificar la persona.");
        } finally {
            em.close();
        }
    }

    private static void eliminarPersona(Scanner scanner) {
        System.out.print("ID de la persona a eliminar: ");
        Long id = scanner.nextLong();

        EntityManager em = emf.createEntityManager();
        try {
            Persona persona = em.find(Persona.class, id);
            if (persona == null) {
                System.out.println("Persona no encontrada.");
                return;
            }

            em.getTransaction().begin();
            em.remove(persona);
            em.getTransaction().commit();

            System.out.println("Persona eliminada con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al eliminar la persona.");
        } finally {
            em.close();
        }
    }
}
