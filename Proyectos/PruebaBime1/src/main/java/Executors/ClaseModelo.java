package Executors;

public class ClaseModelo {
    private String url;
    private int estado;

    public ClaseModelo(String url, int estado) {
        this.url = url;
        this.estado = estado;
    }
    public String getUrl() {
        return url;
    }
    public int getEstado() {
        return estado;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
