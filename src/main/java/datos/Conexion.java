/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Steak
 */
public class Conexion {
    
    // Definimos la variables para realizar la conexión a la base de datos, van hacer constantes.
    private static final String JDBC_URL = "jdbc:mysql://localhost/prueba?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); // Pasamos los valores para conectarnos a la
        // base de datos, básicamente con esto estamos creando la conexión a la base de datos.
    }
    
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection c) {
        try {
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
