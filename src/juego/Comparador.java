/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.Comparator;

/**
 * Esta clase sirve para ordenar el arraylist de jugadores.
 * @author coder
 */
public class Comparador implements Comparator<Jugador> {
    /**
    *Metodo heredado para comparar los elementos del array.
    *@param o1 pivote de Jugador 1
    *@param o2 pivote de jugador 2
    *@return valor entero que puede ser 1 0 -1
    */
    @Override
    public int compare(Jugador o1, Jugador o2) {
        if (o1.getJugadas() > o2.getJugadas()) {
            return 1;
        } else if (o1.getJugadas() == o2.getJugadas()) {
            if (o1.getTiempo() > o2.getTiempo()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

}
