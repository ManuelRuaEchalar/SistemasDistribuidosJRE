/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

/**
 *
 * @author jruae
 */
public abstract class Producto {
    private int id;
    private String nombre;
    private String marca;
    private String modelo;
    private String tipo;
    private String descripcion;
    private int stock;
    private int precio;
    private double descuento;
    private double precioFinal;

    public Producto(int id, String nombre, String marca, String modelo, String tipo, String descripcion, int stock, int precio, double descuento) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.descuento = descuento;
    }
    
    //Getters

    public int getId() {
    
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
    

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public int getPrecio() {
        return precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getPrecioFinal() {
        this.setPrecioFinal();
        return precioFinal;
    }
    
    //Setters
    
    public void setId(int id){
    
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public void setPrecioFinal() {
        this.precioFinal= this.precio-this.precio*this.descuento;
    }
    
    public abstract void mostrarInformacion();
    
    
    
    
    
}
