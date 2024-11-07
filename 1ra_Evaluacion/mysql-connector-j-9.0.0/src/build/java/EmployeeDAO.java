import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDAO {

    // Método para insertar datos en la base de datos
    public void insertEmployee(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        String sql = "INSERT INTO empleados (id, nombre, edad, correo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            boolean continuar = true;
            while (continuar) {
                System.out.print("Ingrese el ID del empleado: ");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese el nombre del empleado: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese la edad del empleado: ");
                int edad = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese el correo del empleado: ");
                String correo = scanner.nextLine();

                pstmt.setInt(1, id);
                pstmt.setString(2, nombre);
                pstmt.setInt(3, edad);
                pstmt.setString(4, correo);
                pstmt.executeUpdate();

                System.out.print("¿Desea introducir otro empleado? (si/no): ");
                continuar = scanner.nextLine().equalsIgnoreCase("si");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Formato de entrada inválido. Por favor, intente nuevamente.");
        }
    }

    // Método para leer e imprimir los datos de la base de datos
    public void printEmployees(Connection conn) {
        String sql = "SELECT * FROM empleados";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Edad: " + rs.getInt("edad"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los empleados: " + e.getMessage());
        }
    }
}
