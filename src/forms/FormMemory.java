package forms;

import dao.RankingDAO;
import dominio.Carta;
import dominio.Puntaje;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
public final class FormMemory extends JFrame {
    private final JPanel panelBotones;
    private final JLabel lblIntentos, lblTiempo;
    private final JTable tablaRanking;
    private final DefaultTableModel modeloTabla;
    private final JButton btnReiniciar;

    // Instancia del DAO para manejar los datos
    private final RankingDAO rankingDAO;

    private ArrayList<Carta> cartas;
    private final JButton[] botonesFichas;
    private Carta primeraCartaSelected = null;
    private JButton primerBotSelected = null;

    private int intentos = 0;
    private int aciertos = 0;
    private int segundos = 0;
    private final Timer cronometro;
    private Timer volteador;
    private boolean juegoIniciado = false;
    private boolean bloqueado = false;

    public FormMemory() {
        setTitle("Memory Game V2 - Java Desktop (DAO)");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Inicializamos nuestro objeto de acceso a datos
        this.rankingDAO = new RankingDAO();

        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        lblIntentos = new JLabel("Intentos: 0");
        lblTiempo = new JLabel("Tiempo: 0s");
        btnReiniciar = new JButton("Reiniciar Partida");

        panelControl.add(lblIntentos);
        panelControl.add(lblTiempo);
        panelControl.add(btnReiniciar);
        add(panelControl, BorderLayout.NORTH);

        panelBotones = new JPanel(new GridLayout(4, 4, 8, 8));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonesFichas = new JButton[16];
        add(panelBotones, BorderLayout.CENTER);

        JPanel panelRanking = new JPanel(new BorderLayout());
        panelRanking.setBorder(BorderFactory.createTitledBorder("Top 5 Mejores Jugadores"));

        String[] columnas = { "Posición", "Jugador", "Intentos", "Tiempo" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRanking = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaRanking);
        scroll.setPreferredSize(new Dimension(450, 120));
        panelRanking.add(scroll, BorderLayout.CENTER);
        add(panelRanking, BorderLayout.SOUTH);

        btnReiniciar.addActionListener(e -> inicializarJuego());

        cronometro = new Timer(1000, e -> {
            segundos++;
            lblTiempo.setText("Tiempo: " + segundos + "s");
        });

        inicializarJuego();
        actualizarTablaRanking();
    }

    private void inicializarJuego() {
        cronometro.stop();
        if (volteador != null) {
            volteador.stop();
            volteador = null;
        }
        segundos = 0;
        intentos = 0;
        aciertos = 0;
        juegoIniciado = false;
        bloqueado = false;
        primeraCartaSelected = null;
        primerBotSelected = null;

        lblIntentos.setText("Intentos: 0");
        lblTiempo.setText("Tiempo: 0s");

        String[] simbolosBase = { "A", "B", "C", "D", "E", "F", "G", "H" };
        ArrayList<String> listaSimbolos = new ArrayList<>();
        for (String s : simbolosBase) {
            listaSimbolos.add(s);
            listaSimbolos.add(s);
        }
        Collections.shuffle(listaSimbolos);

        cartas = new ArrayList<>();
        panelBotones.removeAll();

        for (int i = 0; i < 16; i++) {
            cartas.add(new Carta(listaSimbolos.get(i)));
            botonesFichas[i] = new JButton("?");
            botonesFichas[i].setFont(new Font("Arial", Font.BOLD, 22));
            botonesFichas[i].setBackground(Color.DARK_GRAY);
            botonesFichas[i].setForeground(Color.WHITE);
            botonesFichas[i].setFocusPainted(false);

            final int index = i;
            botonesFichas[i].addActionListener(e -> clickFicha(index));
            panelBotones.add(botonesFichas[i]);
        }

        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void clickFicha(int index) {
        if (bloqueado)
            return;

        Carta cartaActual = cartas.get(index);
        JButton botonActual = botonesFichas[index];

        if (cartaActual.isDadaVuelta() || cartaActual.isEmparejada())
            return;

        if (!juegoIniciado) {
            juegoIniciado = true;
            cronometro.start();
        }

        cartaActual.setDadaVuelta(true);
        botonActual.setText(cartaActual.getSimbolo());
        botonActual.setBackground(Color.WHITE);
        botonActual.setForeground(Color.BLACK);

        if (primeraCartaSelected == null) {
            primeraCartaSelected = cartaActual;
            primerBotSelected = botonActual;
        } else {
            intentos++;
            lblIntentos.setText("Intentos: " + intentos);

            if (cartaActual.getSimbolo().equals(primeraCartaSelected.getSimbolo())) {
                cartaActual.setEmparejada(true);
                primeraCartaSelected.setEmparejada(true);
                aciertos++;

                primeraCartaSelected = null;
                primerBotSelected = null;

                if (aciertos == 8) {
                    cronometro.stop();
                    String nombre = JOptionPane.showInputDialog(
                            this, "¡Ganaste en " + segundos + " segundos!\nIngresa tu nombre:");
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        nombre = nombre.trim();
                        if (nombre.length() > 50) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "El nombre no puede superar los 50 caracteres.",
                                    "Nombre demasiado largo",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            guardarRecord(nombre, intentos, segundos);
                        }
                    }
                }
            } else {
                bloqueado = true;
                Carta cartaPrimera = primeraCartaSelected;
                JButton botonPrimero = primerBotSelected;
                volteador = new Timer(1000, e -> {
                    cartaActual.setDadaVuelta(false);
                    cartaPrimera.setDadaVuelta(false);

                    botonActual.setText("?");
                    botonActual.setBackground(Color.DARK_GRAY);
                    botonActual.setForeground(Color.WHITE);

                    botonPrimero.setText("?");
                    botonPrimero.setBackground(Color.DARK_GRAY);
                    botonPrimero.setForeground(Color.WHITE);

                    if (primeraCartaSelected == cartaPrimera) {
                        primeraCartaSelected = null;
                        primerBotSelected = null;
                    }
                    bloqueado = false;
                    volteador = null;
                });
                volteador.setRepeats(false);
                volteador.start();
            }
        }
    }

    private void guardarRecord(String nombre, int intentosFinales, int segundosFinales) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                rankingDAO.guardarRecord(nombre, intentosFinales, segundosFinales);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    actualizarTablaRanking();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    mostrarErrorBaseDatos("No se pudo completar el guardado.", e);
                } catch (ExecutionException e) {
                    mostrarErrorBaseDatos("No se pudo guardar el puntaje.", e);
                }
            }
        }.execute();
    }

    private void actualizarTablaRanking() {
        new SwingWorker<List<Puntaje>, Void>() {
            @Override
            protected List<Puntaje> doInBackground() throws Exception {
                return rankingDAO.obtenerTopRanking();
            }

            @Override
            protected void done() {
                try {
                    List<Puntaje> puntajes = get();
                    modeloTabla.setRowCount(0);
                    int posicion = 1;
                    for (Puntaje puntaje : puntajes) {
                        modeloTabla.addRow(new Object[] {
                                "#" + posicion++,
                                puntaje.getJugador(),
                                puntaje.getIntentos() + " intentos",
                                puntaje.getTiempoSegundos() + "s"
                        });
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    mostrarErrorBaseDatos("No se pudo completar la consulta del ranking.", e);
                } catch (ExecutionException e) {
                    mostrarErrorBaseDatos("No se pudo cargar el ranking.", e);
                }
            }
        }.execute();
    }

    private void mostrarErrorBaseDatos(String mensaje, Exception error) {
        Throwable causa = error;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        String detalle = causa.getMessage();
        if (detalle == null || detalle.trim().isEmpty()) {
            detalle = causa.getClass().getSimpleName();
        }
        JOptionPane.showMessageDialog(
                this,
                mensaje + "\n" + detalle,
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMemory().setVisible(true));
    }
}
