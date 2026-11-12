package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = configuracion("MEMORY_DB_URL", "jdbc:mysql://localhost:3306/juegomemory");
    private static final String USER = configuracion("MEMORY_DB_USER", "root");
    private static final String PASSWORD = configuracion("MEMORY_DB_PASSWORD", "");

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String configuracion(String variable, String valorPorDefecto) {
        String valor = System.getenv(variable);
        return valor == null || valor.trim().isEmpty() ? valorPorDefecto : valor.trim();
    }
}
