/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import datos.PersonaJDBC;
import dominio.Persona;
import java.util.List;

/**
 *
 * @author John Steak
 */
public class PruebaManejoDePersonas {
    
    public static void main(String[] args) {
        
        PersonaJDBC personaJdbc = new PersonaJDBC();
        List<Persona> personas = personaJdbc.select();
        
        // Recorremos cada uno de los objetos.
        for (Persona persona : personas) {
            System.out.println("Persona: " + persona);
        }
        
//        // Prueba insert.
//        Persona persona = new Persona();
//        persona.setNombre("Maria");
//        persona.setApellido_paterno("Lara");
//        persona.setApellido_materno("Mejia");
//        persona.setCorreo_electronico("mlaram@mail.com");
//        persona.setTelefono("5523461");
//        
//        personaJdbc.insert(persona);

//        // Prueba update.
//        Persona persona = new Persona();
//        persona.setId_persona(15);
//        persona.setNombre("Mayra");
//        persona.setApellido_paterno("Lara");
//        persona.setApellido_materno("Mejia");
//        persona.setCorreo_electronico("mlaram@mail.com");
//        persona.setTelefono("5523461");        
//        
//        personaJdbc.update(persona);

//        // Prueba delete.
//        Persona persona = new Persona();
//        persona.setId_persona(15);
//        
//        personaJdbc.delete(persona);
    } 
    
}
