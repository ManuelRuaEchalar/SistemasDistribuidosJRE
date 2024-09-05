/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;


import java.io.Serializable;

public class Deuda implements Serializable {
    private String ci;
    private int anio;
    private String tipoImpuesto;  // Puede ser "Vehículo" o "Casa"
    private double monto;

    public Deuda(String ci, int anio, String tipoImpuesto, double monto) {
        this.ci = ci;
        this.anio = anio;
        this.tipoImpuesto = tipoImpuesto;
        this.monto = monto;
    }

    public String getCi() {
        return ci;
    }

    public int getAnio() {
        return anio;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public double getMonto() {
        return monto;
    }
}

