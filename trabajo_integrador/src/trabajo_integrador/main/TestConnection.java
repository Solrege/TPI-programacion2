package trabajo_integrador.main;

import java.sql.Connection;
import java.sql.SQLException;
import trabajo_integrador.config.DatabaseConnection;


public class TestConnection {
    
    public static void main(String[] args) {
        System.out.println("Intentando conectar a la base de datos...");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            if (conn != null) {
                System.out.println("¡Conexión a la BBDD exitosa!");
                System.out.println("AutoCommit: " + conn.getAutoCommit());
            }
            
        } catch (SQLException e) {
            System.out.println(" Error en la conexión");
        }
    }
}