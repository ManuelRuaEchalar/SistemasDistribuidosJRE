/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

/**
 *
 * @author jruae
 */
public class Televisor extends Producto{
    private int tamañoPantalla;
    private String calidad;

    public Televisor(int tamañoPantalla, String calidad,int id, String nombre, String marca, String modelo, String tipo, String descripcion, int stock, int precio, double descuento) {
        super(id, nombre, marca, modelo, tipo, descripcion, stock, precio, descuento);
        this.tamañoPantalla = tamañoPantalla;
        this.calidad = calidad;
    }
    
    //getters

    public int getTamañoPantalla() {
        return tamañoPantalla;
    }

    public String getCalidad() {
        return calidad;
    }
    
    //setters

    public void setTamañoPantalla(int tamañoPantalla) {
        this.tamañoPantalla = tamañoPantalla;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }
    
    
    
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Código: " + getId());
        System.out.println("Tamano Pantalla en pulgadas: " + getTamañoPantalla());
        System.out.println("Calidad de video " + getCalidad());
    }
}
