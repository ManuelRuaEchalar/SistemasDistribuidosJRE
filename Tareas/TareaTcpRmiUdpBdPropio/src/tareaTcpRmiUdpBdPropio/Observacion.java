/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

/**
 *
 * @author eantoniocf
 */
// Clase Observacion que contiene CI y si tiene o no observaciones
public class Observacion {
    private String ci;
    private boolean tieneObservacion; // true para "sí", false para "no"
    
    // Constructor
    public Observacion(String ci, boolean tieneObservacion) {
        this.ci = ci;
        this.tieneObservacion = tieneObservacion;
    }
    
    // Getters y Setters
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public boolean isTieneObservacion() {
        return tieneObservacion;
    }

    public void setTieneObservacion(boolean tieneObservacion) {
        this.tieneObservacion = tieneObservacion;
    }

    @Override
    public String toString() {
        return "CI: " + ci + ", Observación: " + (tieneObservacion ? "sí" : "no");
    }
}
