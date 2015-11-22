/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
import javax.swing.JTextField;
import juego.Juego;
import juego.Jugador;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


/**
 *
 * @author coder
 */
public class UtilTest {

    static Util util;

    public UtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        util = new Util();
    }

    /**
     * Prueba que valida si un campo está vacío, de la clase Util.
     */
    @Test
    public void testValidarCampoVacio() {
        System.out.println("Prueba 4");
        JTextField tf = new JTextField("JTextField");
        tf.setText("No estoy vacío");
        boolean vacio = util.validarCampoVacio(tf);
        assertFalse(vacio);
    }

    /**
     * Prueba del metodo que devuelve la posicion del jugador buscado, de la clase util.
     */
    @Test
    public void testBuscarJugador() {
        ArrayList<Jugador> jugadores;
        jugadores = new ArrayList<>();
        System.out.println("Prueba 5");
        Jugador ju = new Jugador("005", "Sebas", 0, 0);
        jugadores.add(ju);
        int encontrado= util.buscarJugador("005", jugadores);
        assertEquals(0, encontrado);
    }

    /**
     * Prueba del método que devuelve en String los jugadores de Concéntrese, de la clase util.
     */
    @Test
    public void testObtenerJugadores() {
        System.out.println("Prueba 6");
        ArrayList<Jugador> jugadores;
        jugadores = new ArrayList<>();
        Jugador j1 = new Jugador("007", "Sebas", 0, 0);
        Jugador j2 = new Jugador("008", "Damian", 0, 0);
        Jugador j3 = new Jugador("009", "Sebas", 0, 0);
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        String [] lista = util.obtenerJugadores(jugadores);
        assertNotNull(lista);
    }
    
    /**
     * Test que comprueba si el juego terminó. Pertenece a la clase util.
     */
    @Test
    public void juegoHaTerminado() {
        System.out.println("Prueba 7");
        boolean[] giradas= new boolean[16];

        for (int j = 0; j < 16; ++j) {
            giradas[j] = true;
        }
        ArrayList<Jugador> jugadores;
        jugadores = new ArrayList<>();
         Jugador jugadorActual = new Jugador("005", "Sebas", 0, 0);
         
        Juego juego= new Juego(jugadores, jugadorActual );
        boolean bo=juego.juegoHaTerminado(giradas);
        
        assertTrue(bo);
    }
}
