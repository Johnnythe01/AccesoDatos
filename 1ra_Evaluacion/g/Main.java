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
    private static final String URL_WITHOUT_DB = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Line*age1";

    public static void main(String[] args) {
        crearBaseDeDatosYTablas(); // Crea la base de datos y tabla si no existen

        Scanner scanner = new Scanner(System.in);

        int opcion; // Opción seleccionada por el usuario en el menú
        do { 
            System.out.println("Menú Principal:");
            System.out.println("1. Introducir datos de empleado en la base de datos.");
            System.out.println("2. Leer e imprimir datos de la base de datos.");
            System.out.println("3. Salir del programa.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) { // Realizar la acción correspondiente a la opción seleccionada
                case 1:
                    introducirDatos(scanner); // Pasar el scanner como argumento
                    break;
                case 2:
                    leerDatos(); // Leer e imprimir datos de la base de datos
                    break;
                case 3:
                    System.out.println("Saliendo del programa..."); // Salir del programa
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente."); // Opción inválida si pulsamos otro número
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void crearBaseDeDatosYTablas() {
        try {
            // Intentar conectar sin especificar la base de datos para crearla si no existe
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador
            try (Connection connection = DriverManager.getConnection(URL_WITHOUT_DB, USER, PASSWORD); // Conectar sin base de datos
                 Statement statement = connection.createStatement()) {

                // Verificar si la base de datos "empresa" existe
                ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE 'empresa'");
                if (!resultSet.next()) { 
                    // Crear base de datos "empresa" si no existe
                    statement.executeUpdate("CREATE DATABASE empresa");
                    System.out.println("Base de datos 'empresa' creada exitosamente.");
                }

                // Conectar a la base de datos "empresa"
                try (Connection dbConnection = DriverManager.getConnection(URL, USER, PASSWORD); // Conectar a la base de datos
                     Statement dbStatement = dbConnection.createStatement()) {

                    // Verificar si la tabla "empleados" existe
                    ResultSet rs = dbStatement.executeQuery("SHOW TABLES LIKE 'empleados'"); // Verificar si la tabla existe
                    if (!rs.next()) {
                        // Crear tabla "empleados" si no existe
                        String createTableSQL = "CREATE TABLE empleados ("
                                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                                + "nombre VARCHAR(50), "
                                + "edad INT, "
                                + "correo VARCHAR(100))";
                        dbStatement.executeUpdate(createTableSQL);
                        System.out.println("Tabla 'empleados' creada exitosamente.");
                    }
                }
            }
        } catch (ClassNotFoundException e) { // Capturar excepciones
            System.out.println("Controlador no encontrado: " + e.getMessage()); // Mostrar mensaje de error
        } catch (SQLException e) {
            System.out.println("Error al crear base de datos o tablas: " + e.getMessage());
        }
    }

    private static void introducirDatos(Scanner scanner) { // Recibir un objeto Scanner como argumento
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) { // Conectar a la base de datos
                String sql = "INSERT INTO empleados (nombre, edad, correo) VALUES (?, ?, ?)"; // Consulta SQL
                PreparedStatement preparedStatement = connection.prepareStatement(sql); // Preparar la consulta
                while (true) {
                    System.out.print("Ingrese nombre del empleado: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese edad del empleado: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese correo del empleado: ");
                    String correo = scanner.nextLine();

                    // Establecer los valores de los parámetros de la consulta
                    preparedStatement.setString(1, nombre);
                    preparedStatement.setInt(2, edad);
                    preparedStatement.setString(3, correo);
                    preparedStatement.executeUpdate();

                    // Preguntar al usuario si desea ingresar otro empleado
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

    private static void leerDatos() { // Leer e imprimir datos de la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registrar el controlador
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM empleados";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                // Verificar si el ResultSet tiene datos
                if (!resultSet.isBeforeFirst()) { // isBeforeFirst() devuelve false si no hay filas
                    System.out.println("No hay información disponible en la base de datos.");
                    return; // Salir del método si no hay datos
                }

                // Mostrar los datos si existen en la tabla
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int edad = resultSet.getInt("edad");
                    String correo = resultSet.getString("correo");
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Correo: " + correo);
                }
            }
            // Capturar excepciones
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
    }
}
