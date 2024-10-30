import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    private static final String DB_URL_SQLITE = "jdbc:sqlite:empresa.db";
    
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL_SQLITE);
            System.out.println("Conexión establecida con SQLite.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
    
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS empleados (" +
                "id INTEGER PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "edad INTEGER," +
                "correo TEXT NOT NULL)";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'empleados' verificada o creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
