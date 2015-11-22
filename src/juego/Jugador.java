/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

/**
 *Ésta clase crea un nuevo jugador de concéntrese.
 * @author coder
 */
public class Jugador {
    private String id;
    private String nombre;
    private double tiempo;
    private int jugadas;
    
    /**
     * Constructor de la clase Jugador que inicializa sus valores.
     * @param id
     * @param nombre
     * @param tiempo
     * @param jugadas 
     */
    public Jugador(String id, String nombre, double tiempo, int jugadas) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = 0.0;
        this.jugadas = 0;
    }
    
    /**
     * Método para obtener el id del jugador.
     * @return id String  
     */
    public String getId() {
        return id;
    }
    
    /**
     * Método para asignar un id a un jugador.
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método para obtener el nombre del jugador.
     * @return nombre String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para asignar nombre a un jugador.
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener el tiempo del jugador.
     * @return tiempo double 
     */
    public double getTiempo() {
        return tiempo;
    }
    
    /**
     * Método para asignar el tiempo del jugador.
     * @param tiempo 
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    
    /**
     * Metodo para obtener la cantidad de  jugadas del jugador.
     * @return jugadas int
     */
    public int getJugadas() {
        return jugadas;
    }
    
    /**
     * Metodo para asignar la cantidad de  jugadas al jugador.
     * @param jugadas 
     */
    public void setJugadas(int jugadas) {
        this.jugadas = jugadas;
    }
    
    /**
     * Método que sobrescribe toString() para presentar la informacion del arraylist.
     * @return String
     */
    @Override
    public String toString() {
        return  "Identificación: "+id+" Nombre: "+nombre+" Tiempo: "+tiempo+" Jugadas: "+jugadas;
    }

    /**
     * Método para presentar la información del array jugadores en la interfaz resultados.
     * @return array en String
     */
    public String toStringResultado() {
        return  " Nombre: "+nombre+" ............     Jugadas: "+jugadas+" ...........  Tiempo: "+tiempo;
    }

}
