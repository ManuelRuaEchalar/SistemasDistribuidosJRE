package ruatenclase;

public class Alcaldia {
    private String ci;
    private int observacion;

    public Alcaldia(String ci, int observacion) {
        this.ci = ci;
        this.observacion = observacion;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public int getObservacion() {
        return observacion;
    }

    public void setObservacion(int observacion) {
        this.observacion = observacion;
    }
}
