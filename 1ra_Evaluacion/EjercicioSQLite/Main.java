import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        
        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Introducir datos de empleado en la base de datos");
            System.out.println("2. Leer e imprimir datos de la base de datos");
            System.out.println("3. Salir del programa");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            
            switch (option) {
                case 1:
                    employeeDAO.insertEmployee(scanner);
                    break;
                case 2:
                    employeeDAO.readEmployees();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
            }
        }
    }
}
