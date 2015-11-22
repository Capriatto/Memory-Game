package juego;

import utilidades.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase crea un formulario juego.
 *
 * @author coder
 */
public class Juego extends JFrame implements ActionListener {

    Jugador jugador;
    ArrayList<Jugador> jugadores;
    ClassLoader loader;
    Timer timer;
    Time claseTime;
    Util utilidades;
    private final String[] nombreCartas = {"carta0.gif", "carta1.gif", "carta2.gif", "carta3.gif", "carta4.gif", "carta5.gif", "carta6.gif", "carta7.gif"};
    ArrayList<ImageIcon> iconos;
    JButton[] arrayDeBotones;
    JLabel intentos;
    JLabel etiquetaTiempoPartida;
    JLabel tiempoPartida;
    boolean[] estaGirada;
    ImageIcon cartaPorDefecto;
    JFrame ventana;
    JPanel panel;
    JMenuBar barraDeMenu;
    boolean estaPrimeraCartaGirada = false;
    boolean estaSegundaCartaGirada = false;
    int indicePrimeraCarta = -1;
    int indiceSegundaCarta = -1;
    Timer tiempo;
    long contador;
    int contadorJugadas;
    String tiempoFinal;

    /**
     * Constructor del juego que inicializa los valores.
     *
     * @param juga
     * @param jugadorActual
     */
    public Juego(ArrayList<Jugador> juga, Jugador jugadorActual) {
        jugadores = new ArrayList<>();
        this.jugadores = juga;
        this.jugador = jugadorActual;
        loader = Juego.class.getClassLoader();
        utilidades = new Util();
        claseTime = new Time();
        ventana = new JFrame("Concéntrese - Juan S.");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(520, 475);
        ventana.setLayout(null);
        ventana.setResizable(false);
        contadorJugadas = 0;
        cartaPorDefecto = new ImageIcon(loader.getResource("recursos/carta8.gif"));
        panel = new JPanel();
        panel.setSize(300, 450);
        panel.setLayout(new GridLayout(4, 4));
        intentos = new JLabel("Intento : 0");
        etiquetaTiempoPartida = new JLabel("Tiempo: ");
        tiempoPartida = new JLabel("00.00.000");
        etiquetaTiempoPartida.setFont(etiquetaTiempoPartida.getFont().deriveFont(20.0f));
        tiempoPartida.setFont(tiempoPartida.getFont().deriveFont(20.0f));
        intentos.setFont(intentos.getFont().deriveFont(20.0f));
        intentos.setBounds(320, 50, 200, 30);
        tiempoPartida.setBounds(420, 5, 200, 30);
        etiquetaTiempoPartida.setBounds(320, 5, 200, 30);
        ventana.add(tiempoPartida);
        ventana.add(etiquetaTiempoPartida);
        ventana.add(intentos);
        ventana.add(panel);

        crearMenuSuperior();
        crearMatrizIconos();
        crearArrayBotones();
        crearTemporizador();

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Este método crea un menú superior que esconde algunas opciones
     * importantes.
     */
    private void crearMenuSuperior() {
        barraDeMenu = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Opciones");
        JMenuItem destaparCartas = new JMenuItem("Destapar cartas");
        JMenuItem resetear = new JMenuItem("Volver a empezar");

        JMenuItem salirJuego = new JMenuItem("Salir");

        destaparCartas.addActionListener(this);
        resetear.addActionListener(this);
        salirJuego.addActionListener(this);

        menuPrincipal.add(destaparCartas);
        menuPrincipal.add(resetear);
        menuPrincipal.addSeparator();

        menuPrincipal.addSeparator();
        menuPrincipal.add(salirJuego);
        barraDeMenu.add(menuPrincipal);

        ventana.setJMenuBar(barraDeMenu);
    }

    /**
     * Éste método crea una matriz con íconos y los asigna a cada botón.
     */
    private void crearMatrizIconos() {
        ImageIcon[][] miMatrizIconos = new ImageIcon[4][4];
        iconos = new ArrayList<>();
        int indice = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; j++) {
                if (indice >= nombreCartas.length) {
                    indice = 0;
                }
                miMatrizIconos[i][j] = new ImageIcon(loader.getResource("recursos/" + nombreCartas[indice]));
                miMatrizIconos[i][j].setDescription(nombreCartas[indice]); // set description sirve para dar al usuario una breve explicacion textual de la imagen.
                iconos.add(miMatrizIconos[i][j]);
                ++indice;

            }

        }
        Collections.shuffle(iconos); // el metodo shuffle de la clase collections sirve para bajarar las cartas, es un randomizador de arraylist.

    }

    /**
     * Éste método crea el array de botones del juego y pone todas cartas
     * tapadas.
     */
    private void crearArrayBotones() {
        arrayDeBotones = new JButton[16];
        for (int i = 0; i < 16; ++i) {
            crearBoton(i);
        }
        estaGirada = new boolean[16];
        for (int j = 0; j < 16; ++j) {
            estaGirada[j] = false;
        }
    }

    /**
     * Éste método permite que al seleccionar un par de cartas, sino son pareja
     * se vuelvan a ocultar automaticamente.
     */
    private void crearTemporizador() {
        ActionListener accionTemporizador = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent xEvent) {
                if (contador == 0) {
                    tiempo.stop();
                    validarCartasSeleccionadas();
                } else {
                    contador--;
                }
            }
        };
        tiempo = new Timer(800, accionTemporizador);
    }

    /**
     * Este método crea cada botón para el juego y atiende sus eventos.
     *
     * @param indice
     */
    private void crearBoton(final int indice) {
        arrayDeBotones[indice] = new JButton(cartaPorDefecto);
        arrayDeBotones[indice].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent xEvent) {
                if (contadorJugadas == 0) {
                    hiloActualizaTiempo();
                }
                estaGirada[indice] = true;
                intentos.setText("Intento : ".concat(String.valueOf(++contadorJugadas)));
                if (!estaPrimeraCartaGirada) {
                    arrayDeBotones[indice].setIcon(iconos.get(indice));
                    indicePrimeraCarta = indice;
                    estaPrimeraCartaGirada = true;
                } else if (!estaSegundaCartaGirada) {
                    arrayDeBotones[indice].setIcon(iconos.get(indice));
                    indiceSegundaCarta = indice;
                    estaSegundaCartaGirada = true;
                    contador = 1;
                    tiempo.setInitialDelay(0);
                    tiempo.start();
                }

                if (juegoHaTerminado(estaGirada)) {
                    claseTime.tiempoFinal();
                    timer.stop();
                    tiempoFinal = tiempoPartida.getText();
                    JOptionPane.showMessageDialog(ventana,
                            "Terminaste, Felicitaciones!!" + "\n" + "Cantidad de intentos:" + contadorJugadas + "\n" + "Tiempo Final: " + tiempoFinal);
                    partidaTerminada();
                }
            }
        });
        panel.add(arrayDeBotones[indice]);
    }

    /**
     * Este método sirve para verificar la partida del jugador y mostrar un
     * mensaje de confirmación.
     */
    private void partidaTerminada() {
        verificarPartida(); // este metodo comprueba si la partida actual del jugador es mejor que las anteriores.
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog("¡Partida Finalizada!\nIngrese 1 para volver a iniciar una partida.\nIngrese 2 para ir al menú principal.");
        } while (opcion == null || !opcion.equals("1") && !opcion.equals("2"));

        switch (opcion) {
            case "1": // en caso de que sea uno se resetea todo el juego.
                resetearJuego();
                break;
            case "2": // en caso de que sea dos se regresa al menú principal.
                ventana.dispose();
                MainInterface main = new MainInterface(jugadores);
                main.setVisible(true);
                break;
        }
    }

    /**
     * Éste método valida si las cartas seleccionadas no son pares.
     */
    private void validarCartasSeleccionadas() {
        String descripcionPrimeraCartaSeleccionada = ((ImageIcon) arrayDeBotones[indicePrimeraCarta].getIcon()).getDescription();
        String descripcionSegundaCartaSeleccionada = ((ImageIcon) arrayDeBotones[indiceSegundaCarta].getIcon()).getDescription();
        if (!descripcionPrimeraCartaSeleccionada.equals(descripcionSegundaCartaSeleccionada)) {
            arrayDeBotones[indicePrimeraCarta].setIcon(cartaPorDefecto);
            arrayDeBotones[indiceSegundaCarta].setIcon(cartaPorDefecto);
            estaGirada[indicePrimeraCarta] = false;
            estaGirada[indiceSegundaCarta] = false;
        } else if (descripcionPrimeraCartaSeleccionada.equals(descripcionSegundaCartaSeleccionada) && indicePrimeraCarta != indiceSegundaCarta) {
            arrayDeBotones[indicePrimeraCarta].setEnabled(false); // desactiva la carta cuando se encuentra la carta par.
            arrayDeBotones[indiceSegundaCarta].setEnabled(false); // desactiva la carta cuando se encuentra la carta par.
        } else {
            arrayDeBotones[indicePrimeraCarta].setIcon(cartaPorDefecto);
            arrayDeBotones[indiceSegundaCarta].setIcon(cartaPorDefecto);

        }

        indicePrimeraCarta = -1; //reset
        indiceSegundaCarta = -1; // reset
        estaPrimeraCartaGirada = false; //reset
        estaSegundaCartaGirada = false; //reset
    }

    /**
     * Este método comprueba si todas las cartas estan descubiertas.
     *
     * @param giradas
     * @return true or false boolean
     */
    public boolean juegoHaTerminado(boolean[] giradas) {
        for (int i = 0; i < 16; ++i) {
            if (giradas[i] == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Este método sirve como un atajo para descubrir todas las cartas.
     */
    private void destaparCartas() {
        for (int i = 0; i < 16; ++i) {
            arrayDeBotones[i].setIcon(iconos.get(i));
            estaGirada[i] = true;
        }
    }

    /**
     * Este método sirve para volver a iniciar el juego y sus valores base.
     */
    private void resetearJuego() {
        claseTime = null;
        claseTime = new Time();
        tiempoPartida.setText("00.000");
        contadorJugadas = 0;
        intentos.setText("Intento : ".concat(String.valueOf(contadorJugadas)));
        for (int i = 0; i < 16; ++i) {
            arrayDeBotones[i].setIcon(cartaPorDefecto);
            arrayDeBotones[i].setEnabled(true);
            estaGirada[i] = false;
        }
        Collections.shuffle(iconos);
    }

    /**
     * Este método sirve para verificar los datos de la partida y actualizar los
     * datos si el usuario ha superado algún record.
     */
    private void verificarPartida() {
        int jugadas = jugador.getJugadas();
        double tiempoJugador = jugador.getTiempo();

        if (contadorJugadas <= jugadas || jugadas == 0) {
            if (contadorJugadas < jugadas) {
                if (Double.parseDouble(tiempoFinal) < tiempoJugador) {
                    jugador.setJugadas(contadorJugadas);
                    jugador.setTiempo(Double.parseDouble(tiempoFinal));
                } else {
                    jugador.setJugadas(contadorJugadas);
                }
            }
            if (contadorJugadas == jugadas) {
                if (Double.parseDouble(tiempoFinal) < tiempoJugador) {
                    jugador.setTiempo(Double.parseDouble(tiempoFinal));
                }
            }
            if (jugadas == 0) {
                jugador.setJugadas(contadorJugadas);
                jugador.setTiempo(Double.parseDouble(tiempoFinal));
            }
        }
    }

    /**
     * eventos del menu bar.
     *
     * @param xEvent
     */
    @Override
    public void actionPerformed(ActionEvent xEvent) {
        switch (xEvent.getActionCommand()) {
            case "Destapar cartas":
                destaparCartas();
                break;
            case "Volver a empezar":
                resetearJuego();
                break;
            case "Salir":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Este método es un hilo que permite actualizar dinamicamente un jlabel que
     * controla el tiempo de la partida.
     */
    private void hiloActualizaTiempo() {
        Thread hilo = new Thread(new Runnable() {
            public void run() {
                Clock clock = new Clock();
                timer = new Timer(50, clock);
                timer.start();
            }
        });
        hilo.start();
    }

    /**
     * Esta clase sirve para heredar de ActionListener y poder añadir un gestor
     * de eventos que actualice el valor del tiempo cada segundo.
     */
    private class Clock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tiempoPartida.setText(claseTime.updateClock());
        }
    }
}
