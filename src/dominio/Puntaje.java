package dominio;

public final class Puntaje {
    private final String jugador;
    private final int intentos;
    private final int tiempoSegundos;

    public Puntaje(String jugador, int intentos, int tiempoSegundos) {
        this.jugador = jugador;
        this.intentos = intentos;
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getJugador() {
        return jugador;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }
}
