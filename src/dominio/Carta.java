package dominio;

public class Carta {
    private final String simbolo;
    private boolean dadaVuelta;
    private boolean emparejada;

    public Carta(String simbolo) {
        this.simbolo = simbolo;
        this.dadaVuelta = false;
        this.emparejada = false;
    }

    public String getSimbolo() { return simbolo; }
    public boolean isDadaVuelta() { return dadaVuelta; }
    public void setDadaVuelta(boolean dadaVuelta) { this.dadaVuelta = dadaVuelta; }
    public boolean isEmparejada() { return emparejada; }
    public void setEmparejada(boolean emparejada) { this.emparejada = emparejada; }
}
