/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Steak
 */
public class PersonaJDBC {

    // Constantes que vamos a utilizar para realizar cada una de las operaciones CRUD.
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido_paterno, apellido_materno, correo_electronico, telefono) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, correo_electronico = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

    public List<Persona> select() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();

        try {
            c = Conexion.getConnection(); // Obtenemos la conexión a la base de datos.
            ps = c.prepareStatement(SQL_SELECT); // Proporcionamos la cadena que se va a ejecutar.
            rs = ps.executeQuery(); // Ejecutamos el query y lo asignamos a nuestra variable rs.
            while (rs.next()) { // Con el método next vamos recorriendo cada uno de los registros que nos ha devuelto la
                // anterior sentencia.
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido_paterno = rs.getString("apellido_paterno");
                String apellido_materno = rs.getString("apellido_materno");
                String correo_electronico = rs.getString("correo_electronico");
                String telefono = rs.getString("telefono");

                persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido_paterno(apellido_paterno);
                persona.setApellido_materno(apellido_materno);
                persona.setCorreo_electronico(correo_electronico);
                persona.setTelefono(telefono);

                personas.add(persona); // El método add nos agrega cada persona que estamos leyendo.
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(c);
        }
        return personas; // Retorna el listado de personas.
    }
    
    public int insert(Persona persona) { // Recibe como parámetro un objeto de tipo persona, el cual ya debe contener los
        // elementos que queremos insertar en la base de datos.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0; // Para saber cuantos registros han sido afectados una vez ejcutada la sentencia.
        try {
            c = Conexion.getConnection();
            ps = c.prepareStatement(SQL_INSERT); // Proporcionamos la cadena que se va a ejecutar.
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido_paterno());
            ps.setString(3, persona.getApellido_materno());
            ps.setString(4, persona.getCorreo_electronico());
            ps.setString(5, persona.getTelefono());
            
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
    
    public int update(Persona persona) { // Recibe un objeto de tipo Persona ya que debe contener el valor de la llave 
        // primaria que queremos modificar.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0;
        try {
            c = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            ps = c.prepareStatement(SQL_UPDATE); // Proporcionamos la cadena que se va a ejecutar.
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido_paterno());
            ps.setString(3, persona.getApellido_materno());
            ps.setString(4, persona.getCorreo_electronico());
            ps.setString(5, persona.getTelefono());
            ps.setInt(6, persona.getId_persona());
            
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

    public int delete(Persona persona) { // Recibe un objeto de tipo Persona ya que debe contener el valor de la llave 
        // primaria que queremos eliminar.
        Connection c = null;
        PreparedStatement ps = null;
        int numeroRegistros = 0;
        try {
            c = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            ps = c.prepareStatement(SQL_DELETE); // Proporcionamos la cadena que se va a ejecutar.
            ps.setInt(1, persona.getId_persona());
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
