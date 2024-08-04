/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jruae
 */
public class Armario {
    String material;
    int codigo;
    int cantidad;
    ArrayList<Libro> libros = new ArrayList<>();

    public Armario(String material, int codigo) {
        this.material = material;
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }
    
    public void agregarLibro() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce el nombre del libro: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Introduce el autor del libro: ");
        String autor = scanner.nextLine();
        
        System.out.print("Introduce la editorial del libro: ");
        String editorial = scanner.nextLine();
        
        System.out.print("Introduce el año de publicación del libro: ");
        int anio = scanner.nextInt();
        
        libros.add(new Libro(nombre, autor, editorial, anio));
        cantidad+=1;
        System.out.print("Libro añadido exitosamente!");
    }
    
    public void mostrarLibros(){
    
        System.out.print("El armario cuenta con los siguientes libros: ");
        for (Libro libro : libros) {
            System.out.print("Titulo: " + libro.getNombre() + ", autor: "+ libro.getAutor());
        }
    }
    
}
