import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeDAO {
    public EmployeeDAO() {
        DatabaseConnection.createTable();  // Crear tabla si no existe
    }

    public void insertEmployee(Scanner scanner) {
        System.out.print("Ingrese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese correo: ");
        String correo = scanner.nextLine();
        
        String sql = "INSERT INTO empleados(id, nombre, edad, correo) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, edad);
            pstmt.setString(4, correo);
            
            pstmt.executeUpdate();
            System.out.println("Empleado agregado con éxito.");
            
        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    public void readEmployees() {
        String sql = "SELECT id, nombre, edad, correo FROM empleados";
        
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String correo = rs.getString("correo");
                
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Correo: " + correo);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al leer empleados: " + e.getMessage());
        }
    }
}
