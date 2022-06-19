/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import datos.UsuarioJDBC;
import dominio.Usuario;
import java.util.List;

/**
 *
 * @author John Steak
 */
public class PruebaManejoDeUsuarios {
    
    public static void main(String[] args) {
        
        UsuarioJDBC usuarioJdbc = new UsuarioJDBC();
        List<Usuario> usuarios = usuarioJdbc.select();
        
        // Recorremos cada uno de los objetos.
        for (Usuario usuario : usuarios) {
            System.out.println("Usuario: " + usuario);
        }
        
//        // Prueba insert.
//        Usuario usuario = new Usuario("Maria", "Lara");        
//        usuarioJdbc.insert(usuario);

//        // Prueba update.
//        Usuario usuario = new Usuario(3, "Mayra", "Lara");
//        usuarioJdbc.update(usuario);

//        // Prueba delete.       
//        usuarioJdbc.delete(new Usuario(3));
    } 
    
}
