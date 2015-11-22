package utilidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Clase que controla el tiempo del jugador.
 * @author coder
 */
public class Time extends JFrame {
    
    private final ClockListener clock = new ClockListener(); // se crea escuchador de reloj
    private final Timer timer = new Timer(53, clock); // se crea un timer para repetir una acción cada 57 milisegundos
    public SimpleDateFormat date = new SimpleDateFormat("mm.ss"); // formato de segundos.
    private final long startTime; // se toma de referencia la feha de inicio

    /**
     * Inicializa los valores de la clase.
     */
    public Time() {
        timer.setInitialDelay(0);
        startTime = System.currentTimeMillis();
        timer.start();

    }
   
    /**
     * Este método actualiza constantemente el segundero.
     * @return cambio del segundero String
     */
    public String  updateClock() {
        Date elapsed = new Date(System.currentTimeMillis() - startTime);
        return date.format(elapsed);
    }
    
   /**
    * Este método permite detener la ejecución del tiempo y obtener el valor final.
    * @return el tiempo total de la partida String
    */
    public String tiempoFinal(){
        timer.stop();
        Date elapsed = new Date(System.currentTimeMillis() - startTime);
        return date.format(elapsed);
    }
    
    /**
     * Sirve para escuchar el reloj cada que cambia.
     */
    private class ClockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateClock();
        }
    }
}