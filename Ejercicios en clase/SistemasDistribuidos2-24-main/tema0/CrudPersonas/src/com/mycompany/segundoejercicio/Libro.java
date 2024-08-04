/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.segundoejercicio;

/**
 *
 * @author jruae
 */
public class Libro {
    String nombre;
    String autor;
    String Editorial;
    int anio;

    public Libro(String nombre, String autor, String Editorial, int anio) {
        this.nombre = nombre;
        this.autor = autor;
        this.Editorial = Editorial;
        this.anio = anio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return Editorial;
    }

    public int getAnio() {
        return anio;
    }
    
    public void mostrarDatos(){
    
        System.out.println("Titulo: "+nombre);
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + Editorial);
        System.out.println("Anio: " + anio);
    }
    
}
