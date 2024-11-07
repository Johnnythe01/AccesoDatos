import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Connection connection = null;

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Introducir datos de empleado en la base de datos.");
            System.out.println("2. Leer datos de la base de datos e imprimirlos.");
            System.out.println("3. Salir del programa.");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    connection = DataBaseConnection.connectMySQL();
                    if (connection != null) {
                        employeeDAO.insertEmployee(connection);
                        try { connection.close(); } catch (Exception e) { /* Ignorar */ }
                    }
                    break;
                case "2":
                    connection = DataBaseConnection.connectMySQL();
                    if (connection != null) {
                        employeeDAO.printEmployees(connection);
                        try { connection.close(); } catch (Exception e) { /* Ignorar */ }
                    }
                    break;
                case "3":
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
        scanner.close();
    }
}

/*
 ADU2EX01
Enunciado del Ejercicio:
Objetivo:
Crear una aplicación Java que se conecte a una base de datos SQLite, donde se podrá introducir información de empleados en la tabla empleados y, posteriormente, leer y mostrar los datos almacenados. El programa permitirá elegir entre dos funcionalidades: introducir datos de empleados o leer e imprimir los datos de la base de datos.
Requisitos del programa:
Conexión a SQLite:

La base de datos se llama empresa.
La BBDD y la tabla estarán creadas cuando se ejecute la aplicación. Deberá hacerlo durante el proceso de instalación y configuración de SGBD. Puede usar HeidiSQL o similar para crearlo.
La mesa empleados debe tener las siguientes columnas:
yd (INT): Identificador del empleado.
nombre (VARCHAR(100)): Nombre del empleado.
edad (INT): Edad del empleado.
correo (VARCHAR(100)): Correo electrónico del empleado.
Haz que la aplicación cree BBDD la primera vez que se ejecuta en caso de que ésta no exista.

Funcionalidades:
El programa debe permitir al usuario elegir entre dos opciones:
Introducir datos de empleados en la base de datos.
Leer los datos de la base de datos e imprimirlos por consola.

Parte 1: Introducción de datos de empleados
Cuando el usuario elija la opción de introducir datos, el programa debe permitir introducir la información de uno o más empleados.
Los datos de cada empleado deben ser: id (número entero), nombre (texto), edad (número entero) y correo (texto).
El programa debe permitir introducir datos de forma reiterativa hasta que el usuario indique que desea dejar de hacerlo.

Parte 2: Leer datos de la base de datos
Cuando el usuario elija la opción de leer los datos, el programa debe realizar una consulta en la tabla empleados e imprimir los datos (id, nombre, edad, correo) por consola.
Menu principal:
El programa debe incluir un menú principal que ofrezca las siguientes opciones:

1. Introducir datos de empleado en la base de datos.
2. Leer datos de la base de datos e imprimirlos.
3. Salir del programa.

Una vez el usuario elija la opción que desea ejecutar, el programa debe realizar la acción correspondiente.
Gestión de la conexión a la base de datos:
La conexión a la base de datos debe ser gestionada de forma correcta, cerrando las conexiones después de cada operación para evitar fugas de memoria.

Validación de la entrada de datos:
Si el usuario introduce valores incorrectos o en un formato inválido, el programa debe capturar estas excepciones y mostrar un mensaje de error.


Ampliación para DUAL, OBLIGATORIO PARA NO-DUAL:
(1 punto) Haz que la aplicación se conecte a otro tipo de SGBD (MariaDB, MySQL, …)


Anotaciones:
Revisa el uso de try/catch en la aplicación
Plantea si es necesario el uso de transacciones
Recuerda comentar el código
Recuerda las condiciones de entrega, formato, zip…
 */