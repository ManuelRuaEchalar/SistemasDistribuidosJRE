/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaprimerparcial;


import java.io.Serializable;

public class Diploma implements Serializable {
    private String nombreCompleto;
    private String carrera;
    private String fecha;
    private String mensaje;

    public Diploma(String nombreCompleto, String carrera, String fecha, String mensaje) {
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

   
    @Override
    public String toString() {
        if (mensaje.isEmpty()) {
            return "Diploma{" +
                    "nombreCompleto='" + nombreCompleto + '\'' +
                    ", carrera='" + carrera + '\'' +
                    ", fecha='" + fecha + '\'' +
                    '}';
        } else {
            return "Mensaje: " + mensaje;
        }
    }
}