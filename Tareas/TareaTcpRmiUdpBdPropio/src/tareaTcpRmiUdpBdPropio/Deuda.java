/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.io.Serializable;

/**
 *
 * @author eantoniocf
 */
public class Deuda implements Serializable {

    private String ci;
    private int anio;
    private Impuesto impuesto;
    private int monto;

    public Deuda(String ci, int anio, Impuesto impuesto, int monto) {
        this.ci = ci;
        this.anio = anio;
        this.impuesto = impuesto;
        this.monto = monto;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Deuda{" + "ci=" + ci + ", anio=" + anio + ", impuesto=" + impuesto + ", monto=" + monto + '}';
    }
    
    
}
