/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Ésta clase permite hacer pruebas de la clase comparador que compara los elementos de un array.
 * @author coder
 */
public class ComparadorTest {
    
    public ComparadorTest() {
    }

    /**
     * Prueba del método compare de la clase comparar.
     */
    @Test
    public void compare() {
        System.out.println("Prueba 1");
        Comparador compare= new Comparador();
        int comparacion= compare.compare(new Jugador("001", "Juan", 11.15, 8), new Jugador("002", "Ramiro", 30, 8));
        assertFalse(comparacion==1);
    }
    
}
