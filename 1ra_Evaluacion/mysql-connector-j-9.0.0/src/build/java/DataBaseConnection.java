import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "Line*age1";

    // Conexión a MySQL
    public static Connection connectMySQL() {
        try {
            // Cargar explícitamente el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentar conectar con la base de datos
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver de MySQL no encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Error de conexión MySQL: " + e.getMessage());
            return null;
        }
    }
}
