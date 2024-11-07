package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    
    private static final String URL = "jdbc:mysql://localhost:3306/empresa?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Line*age1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Introducir datos de empleado en la base de datos.");
            System.out.println("2. Leer e imprimir datos de la base de datos.");
            System.out.println("3. Salir del programa.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    introducirDatos(scanner);
                    break;
                case 2:
                    leerDatos();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 3);
        
        scanner.close();
    }

    private static void introducirDatos(Scanner scanner) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO empleados (id, nombre, edad, correo) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                while (true) {
                    System.out.print("Ingrese ID del empleado: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese nombre del empleado: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese edad del empleado: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese correo del empleado: ");
                    String correo = scanner.nextLine();

                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setInt(3, edad);
                    preparedStatement.setString(4, correo);
                    preparedStatement.executeUpdate();

                    System.out.print("¿Desea ingresar otro empleado? (s/n): ");
                    String respuesta = scanner.nextLine();
                    if (!respuesta.equalsIgnoreCase("s")) {
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al introducir datos: " + e.getMessage());
        }
    }

    private static void leerDatos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM empleados";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int edad = resultSet.getInt("edad");
                    String correo = resultSet.getString("correo");
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Correo: " + correo);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
    }
}
