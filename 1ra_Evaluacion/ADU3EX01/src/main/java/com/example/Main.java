package com.example;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_example.odb"); // Nombre de la base de datos

    public static void main(String[] args) { // Método principal
        Scanner scanner = new Scanner(System.in); // Creamos el scanner para leer la entrada del usuario

        while (true) { // Bucle para mostrar el menú principal
            System.out.println("\nMENU PRINCIPAL:");
            System.out.println("1. Mantenimiento de Persona");
            System.out.println("2. Mantenimiento de Empresa");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt(); // Leemos la opción seleccionada por el usuario

            switch (opcion) { // Dependiendo de la opción seleccionada llamamos a un método diferente
                case 1 -> menuPersona(scanner);
                case 2 -> menuEmpresa(scanner);
                case 3 -> {
                    emf.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción inválida."); // Si la opción no es válida mostramos un mensaje
            }
        }
    }

    // Menú Persona
    private static void menuPersona(Scanner scanner) { // Método para mostrar el menú de Persona

        while (true) {
            System.out.println("\nMENU PERSONA:");
            System.out.println("1. Añadir Persona");
            System.out.println("2. Mostrar Personas");
            System.out.println("3. Modificar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt(); // Leemos la opción seleccionada por el usuario

            switch (opcion) { // Dependiendo de la opción seleccionada llamamos a un método diferente
                case 1 -> añadirPersona(scanner);
                case 2 -> mostrarTodasLasPersonas();
                case 3 -> modificarPersona(scanner);
                case 4 -> eliminarPersonaPorNombre(scanner);
                case 5 -> { return; }
                default -> System.out.println("Opción inválida."); // Si la opción elegida no es válida mostramos un mensaje
            }
        }
    }

    private static void añadirPersona(Scanner scanner) { // Método para añadir una persona a la base de datos
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Email: ");
        String email = scanner.next();

        EntityManager em = emf.createEntityManager(); // Creamos un EntityManager para gestionar la base de datos
        try {
            em.getTransaction().begin();
            em.persist(new Persona(nombre, edad, email)); // Añadimos la persona a la base de datos
            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println("Persona añadida con éxito."); // Mostramos un mensaje de éxito
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback(); //Aqui se deshace la transacción si hay un error
            System.out.println("Error al añadir la persona.");
        } finally {
            em.close();
        }
    }

    private static void mostrarTodasLasPersonas() { // Método para mostrar todas las personas de la base de datos
        EntityManager em = emf.createEntityManager(); // Creamos un EntityManager para gestionar la base de datos
        try {
            List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList(); // Obtenemos todas las personas de la base de datos
            if (personas.isEmpty()) { // Si no hay personas, mostramos un mensaje
                System.out.println("No hay personas registradas.");
            } else {
                personas.forEach(System.out::println); // Con esto mostramos todas las personas
            }
        } finally {
            em.close(); // Cerramos el EntityManager
        }
    }

    private static Persona buscarPersonaPorNombre(String nombre) { // Método para buscar una persona por nombre
        EntityManager em = emf.createEntityManager(); // Creamos otro EntityManager
        try {
            List<Persona> personas = em.createQuery(
                "SELECT p FROM Persona p WHERE p.nombre = :nombre", Persona.class) // Query para buscar una persona por nombre
                .setParameter("nombre", nombre) // Pasamos el nombre como parámetro
                .getResultList();
            return personas.isEmpty() ? null : personas.get(0); // Si no hay personas devolvemos null, si hay personas devolvemos la primera de la lista
        } finally {
            em.close();
        }
    }

    private static void modificarPersona(Scanner scanner) { // Método para modificar una persona por nombre
        scanner.nextLine();  // Este nextLine limpia los saltos de línea pendientes, sin esto, el siguiente nextLine no funciona
        System.out.print("Nombre de la persona a modificar: ");
        String nombre = scanner.nextLine();  // Lee el nombre de la persona que vamos a modificar
    
        EntityManager em = emf.createEntityManager(); 
        try {
            List<Persona> personas = em.createQuery("SELECT p FROM Persona p WHERE p.nombre = :nombre", Persona.class) // Query para buscar una persona por nombre
                    .setParameter("nombre", nombre)
                    .getResultList();
    
            if (personas.isEmpty()) { // Si no hay personas mostramos este mensaje
                System.out.println("Persona no encontrada.");
                return;
            }
    
            Persona persona = personas.get(0);  // Esto nos da la primera persona de la lista
    
            // Pedimos los nuevos datos de la persona
            System.out.print("Nuevo nombre (dejar vacío para no modificar): ");
            String nuevoNombre = scanner.nextLine(); //
            System.out.print("Nueva edad (introducir 0 para no modificar): "); 
            int nuevaEdad = scanner.nextInt();
            scanner.nextLine();  // Limpia el buffer después de nextInt(), si no, el siguiente nextLine() no funcionaba, se va para abajo eternamente, pasa lo mismo en nuevoNombre
            System.out.print("Nuevo email (dejar vacío para no modificar): ");
            String nuevoEmail = scanner.nextLine();
    
            em.getTransaction().begin(); // Iniciamos la transacción para modificar la persona
            if (!nuevoNombre.isBlank()) persona.setNombre(nuevoNombre);
            if (nuevaEdad > 0) persona.setEdad(nuevaEdad);
            if (!nuevoEmail.isBlank()) persona.setEmail(nuevoEmail);
            em.getTransaction().commit(); // Confirmamos la transacción
    
            System.out.println("Persona modificada con éxito.");
        } catch (Exception e) { // Si hay un error deshacemos la transacción y mostramos un mensaje
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al modificar la persona.");
        } finally {
            em.close();
        }
    }
    
    private static void eliminarPersonaPorNombre(Scanner scanner) { // Método para eliminar una persona por nombre
        System.out.print("Nombre de la persona a eliminar: ");
        String nombre = scanner.next(); // Leemos el nombre de la persona a eliminar

        EntityManager em = emf.createEntityManager(); // Creamos otro EntityManager
        try {
            Persona persona = buscarPersonaPorNombre(nombre);
            if (persona == null) { // Si no se encuentra la persona mostramos un mensaje
                System.out.println("Persona no encontrada."); 
                return;
            }

            em.getTransaction().begin(); // Iniciamos la transacción para eliminar la persona
            em.remove(em.merge(persona)); // Esto es para que la persona se pueda eliminar
            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println("Persona eliminada con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback(); // Si hay un error al eliminar la persona deshacemos la transacción
            System.out.println("Error al eliminar la persona.");
        } finally {
            em.close();
        }
    }

    private static void menuEmpresa(Scanner scanner) { // Método para mostrar el menú de Empresa
        while (true) {
            System.out.println("\nMENU EMPRESA:");
            System.out.println("1. Añadir Empresa");
            System.out.println("2. Mostrar Empresas");
            System.out.println("3. Modificar Empresa");
            System.out.println("4. Eliminar Empresa");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt(); // Leemos la opción seleccionada por el usuario

            switch (opcion) { // Según la opción seleccionada, llamamos a un método diferente
                case 1 -> añadirEmpresa(scanner);
                case 2 -> mostrarEmpresas();
                case 3 -> modificarEmpresa(scanner);
                case 4 -> eliminarEmpresa(scanner);
                case 5 -> { return; } // Si es la opción 5 salimos del método
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void añadirEmpresa(Scanner scanner) { // Método para añadir una empresa
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("CIF: ");
        String cif = scanner.next(); 
        System.out.print("Dirección: ");
        String direccion = scanner.next();

        EntityManager em = emf.createEntityManager(); // Creamos otro EntityManager
        try {
            em.getTransaction().begin(); // Iniciamos la transacción
            em.persist(new Empresa(nombre, cif, direccion)); // Añadimos la empresa a la base de datos
            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println("Empresa añadida con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback(); // Si hay un error, deshacemos la transacción
            System.out.println("Error al añadir la empresa.");
        } finally {
            em.close();
        }
    }

    private static void mostrarEmpresas() { // Método para mostrar todas las empresas
        EntityManager em = emf.createEntityManager(); // Otro EntityManager
        try {
            List<Empresa> empresas = em.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList(); // Nos da todas las empresas
            if (empresas.isEmpty()) { // Si no hay empresas, mostramos un mensaje
                System.out.println("No hay empresas registradas.");
            } else {
                empresas.forEach(System.out::println);
            }
        } finally {
            em.close();
        }
    }

    private static void modificarEmpresa(Scanner scanner) { // Método para modificar una empresa
        System.out.print("ID de la empresa a modificar: ");
        Long id = scanner.nextLong(); // Leemos el ID de la empresa a modificar
        scanner.nextLine(); // Limpiamos el buffer, si no, el siguiente nextLine no funciona, como en modificarPersona, igualito

        EntityManager em = emf.createEntityManager();
        try {
            Empresa empresa = em.find(Empresa.class, id); // Buscamos la empresa por ID
            if (empresa == null) {
                System.out.println("Empresa no encontrada.");
                return;
            }

            System.out.print("Nuevo nombre (dejar vacío para no modificar): ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Nuevo CIF (dejar vacío para no modificar): ");
            String nuevoCif = scanner.nextLine();
            System.out.print("Nueva dirección (dejar vacío para no modificar): ");
            String nuevaDireccion = scanner.nextLine();

            em.getTransaction().begin(); // Iniciamos la transacción para modificar la empresa
            if (!nuevoNombre.isBlank()) empresa.setNombre(nuevoNombre);          // Si el nuevo nombre no está vacío, lo cambiamos
            if (!nuevoCif.isBlank()) empresa.setCif(nuevoCif);                   // Si el nuevo CIF no está vacío, lo cambiamos
            if (!nuevaDireccion.isBlank()) empresa.setDireccion(nuevaDireccion); // Si la nueva dirección no está vacía, la cambiamos
            em.getTransaction().commit(); // Confirmamos la transacción

            System.out.println("La empresa ha sido modificada.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al modificar la empresa.");
        } finally {
            em.close(); // Cerramos el EntityManager
        }
    }

    private static void eliminarEmpresa(Scanner scanner) { // Método para eliminar una empresa
        System.out.print("Nombre de la empresa a eliminar: ");
        String nombre = scanner.next();

        EntityManager em = emf.createEntityManager(); // Otro EntityManager
        try {
            em.getTransaction().begin();
            List<Empresa> empresas = em.createQuery( // Query para buscar una empresa por nombre
                "SELECT e FROM Empresa e WHERE e.nombre = :nombre", Empresa.class)
                .setParameter("nombre", nombre).getResultList();
            if (empresas.isEmpty()) { // Si no se encuentra la empresa se muestra un mensaje
                System.out.println("Empresa no encontrada.");
                return;
            }

            em.remove(empresas.get(0)); // Eliminamos la empresa
            em.getTransaction().commit(); // Confirmamos la transacción
            System.out.println("Empresa eliminada con éxito.");
        } catch (Exception e) { // Si hay un error, deshacemos la transacción y se muestra un mensaje
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.out.println("Error al eliminar la empresa.");
        } finally {
            em.close();
        }
    }
}
