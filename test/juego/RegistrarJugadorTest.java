/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Esta clase permite probar el registro de un jugador.
 * @author coder
 */
public class RegistrarJugadorTest {
    
    /**
     * Este método permite probar la agregación de un jugador al juego.
     */
    @Test
    public void agregarJugador() {
        System.out.println("Prueba 3");
        RegistrarJugador rj= new RegistrarJugador();
        boolean agregado = rj.agregarJugador(new Jugador("001", "Juan", 12.51, 10));
        assertTrue(agregado);
    }
    
    
    
}
