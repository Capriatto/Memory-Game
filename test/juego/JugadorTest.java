/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author coder
 */
public class JugadorTest {
    
    public JugadorTest() {
    }

 
    /**
     * Prueba del método toString de la clase Jugador.
     */
    @Test
    public void testToString() {
        System.out.println("prueba 2");
        String a= "Identificación: "+"564"+" Nombre: "+"Juano"+" Tiempo: "+0.0+" Jugadas: "+0;
        Jugador ju= new Jugador("564", "Juano",0, 0);
        String b = ju.toString();
        assertEquals(a, b);
        
    }

 
    
}
