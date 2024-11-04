import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String SQLITE_URL = "jdbc:sqlite:empresa.db";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "Line*age1";

    // Conexión a SQLite
    public static Connection connectSQLite() {
        try {
            return DriverManager.getConnection(SQLITE_URL);
        } catch (SQLException e) {
            System.out.println("Error de conexión SQLite: " + e.getMessage());
            return null;
        }
    }

    // Conexión a MySQL
    public static Connection connectMySQL() {
        try {
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión MySQL: " + e.getMessage());
            return null;
        }
    }
}
