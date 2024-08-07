/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

/**
 *
 * @author jruae
 */
public class Telefono extends Producto{
    private int memoriaRam;
    private String procesador;
    private String versionAndroid;

    public Telefono(int memoriaRam, String procesador,String versionAndroid, int id, String nombre, String marca, String modelo, String tipo, String descripcion, int stock, int precio, double descuento) {
        super(id, nombre, marca, modelo, tipo, descripcion, stock, precio, descuento);
        this.memoriaRam = memoriaRam;
        this.procesador = procesador;
        this.versionAndroid = versionAndroid;
    }
    
    //getters

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public String getProcesador() {
        return procesador;
    }

    public String getVersionAndroid() {
        return versionAndroid;
    }
    
    
    //setters

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setVersionAndroid(String versionAndroid) {
        this.versionAndroid = versionAndroid;
    }
    
    
    
    
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Código: " + getId());
        System.out.println("Memoria RAM: " + memoriaRam);
        System.out.println("Procesador: " + procesador);
    }
}
