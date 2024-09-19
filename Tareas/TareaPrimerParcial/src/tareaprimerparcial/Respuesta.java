package tareaprimerparcial;

import java.io.Serializable;

public class Respuesta implements Serializable {
    private boolean estado;
    private String mensaje;

    public Respuesta(boolean estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    // Getters
    public boolean getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
}