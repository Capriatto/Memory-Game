/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import juego.Jugador;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * Esta clase fue creada para agrupar métodos comunes y ayudar a su reutilización.
 * @author coder
 */
public class Util {
    
    /**
     * Valida si un campo de texto esta vacío.
     * @param campo
     * @return true o false boolean
     */
    public boolean validarCampoVacio(JTextField campo) {
        return campo.getText().trim().isEmpty();
    }
    
    /**
     * Busca un jugador en un arraylist.
     * @param identifcacion
     * @param jugadores
     * @return posicion jugador o -1 int
     */
     public int buscarJugador(String identifcacion, ArrayList<Jugador> jugadores) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (identifcacion.equals(jugadores.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
     
     /**
      * Obtiene los jugadores de un arraylist en forma de cadena
      * @param jugadores
      * @return Lista de Strings String[]
      */
     public String[] obtenerJugadores( ArrayList<Jugador> jugadores) {
        String[] listaDeJugadores = new String[jugadores.size()];

        for (int i = 0; i < jugadores.size(); i++) {
            listaDeJugadores[i] = jugadores.get(i).toString();
        }
        return listaDeJugadores;
    }
     
     /**
      * Obtiene los jugadores de un arraylist en forma de cadena para la vista de resultados.
      * @param jugadores
      * @return Lista de Strings String[]
      */
      public String[] obtenerJugadoresResultado( ArrayList<Jugador> jugadores) {
        String[] listaDeJugadores = new String[jugadores.size()];

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getJugadas()!=0) {
                 listaDeJugadores[i] = jugadores.get(i).toStringResultado();
            }
         }
        return listaDeJugadores;
    }
}
