/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Steak
 */
public class UsuarioJDBC {

    // Constantes que vamos a utilizar para realizar cada una de las operaciones CRUD.
    private static final String SQL_SELECT = "SELECT id_usuario, nombre_usuario, contraseña FROM usuario_jdbc";
    private static final String SQL_INSERT = "INSERT INTO usuario_jdbc (nombre_usuario, contraseña) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario_jdbc SET nombre_usuario = ?, contraseña = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario_jdbc WHERE id_usuario = ?";

    public List<Usuario> select() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            c = Conexion.getConnection(); // Obtenemos la conexión a la base de datos.
            ps = c.prepareStatement(SQL_SELECT); // Proporcionamos la cadena que se va a ejecutar.
            rs = ps.executeQuery(); // Ejecutamos el query y lo asignamos a nuestra variable rs.
            while (rs.next()) { // Con el método next vamos recorriendo cada uno de los registros que nos ha devuelto la
                // anterior sentencia.
                int id_usuario = rs.getInt("id_usuario");
                String nombre_usuario = rs.getString("nombre_usuario");
                String contraseña = rs.getString("contraseña");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setNombre_usuario(nombre_usuario);
                usuario.setContraseña(contraseña);

                usuarios.add(usuario); // El método add nos agrega cada usuario que estamos leyendo.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(c);
        }
        return usuarios; // Retorna el listado de usuarios.
    }
    
    public int insert(Usuario usuario) { // Recibe como parámetro un objeto de tipo usuario, el cual ya debe contener los
        // elementos que queremos insertar en la base de datos.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0; // Para saber cuantos registros han sido afectados una vez ejcutada la sentencia.
        try {
            c = Conexion.getConnection();
            ps = c.prepareStatement(SQL_INSERT); // Proporcionamos la cadena que se va a ejecutar.
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            
            System.out.println("Ejecutando query: " + SQL_INSERT);
            numeroRegistros = ps.executeUpdate();
            System.out.println("Registros Afectados: " + numeroRegistros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(ps);
            Conexion.close(c);
        }
        return numeroRegistros;
    }
    
    public int update(Usuario usuario) { // Recibe un objeto de tipo Usuario ya que debe contener el valor de la llave 
        // primaria que queremos modificar.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0;
        try {
            c = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            ps = c.prepareStatement(SQL_UPDATE); // Proporcionamos la cadena que se va a ejecutar.
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContraseña());
            ps.setInt(3, usuario.getId_usuario());
            
            numeroRegistros = ps.executeUpdate();
            System.out.println("Registros Actualizado: " + numeroRegistros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(ps);
            Conexion.close(c);
        }
        return numeroRegistros;
    }

    public int delete(Usuario usuario) { // Recibe un objeto de tipo Usuario ya que debe contener el valor de la llave 
        // primaria que queremos eliminar.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0;
        try {
            c = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            ps = c.prepareStatement(SQL_DELETE); // Proporcionamos la cadena que se va a ejecutar.
            ps.setInt(1, usuario.getId_usuario());
            numeroRegistros = ps.executeUpdate();
            System.out.println("Registros Eliminados: " + numeroRegistros);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(ps);
            Conexion.close(c);
        }
        return numeroRegistros;
    }
}
