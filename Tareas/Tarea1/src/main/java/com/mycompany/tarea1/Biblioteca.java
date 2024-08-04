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
public class Biblioteca {
    String nombre;
    float tamaño;
    ArrayList<Armario> armarios = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Biblioteca(String nombre, float tamaño) {
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTamaño() {
        return tamaño;
    }

    public ArrayList<Armario> getArmarios() {
        return armarios;
    }
    
    public void agregarArmario(){
    
        System.out.print("Introduce el material del armario (madera/metal): ");
        String material = scanner.nextLine().toLowerCase();

        if (!material.equals("madera") && !material.equals("metal")) {
            System.out.println("Material no válido. Debe ser 'madera' o 'metal'.");
        }

        System.out.print("Introduce el código del armario: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        for (Armario armario : armarios) {
            if (armario.getCodigo() == codigo) {
                System.out.println("El código ya existe. Debe ser único.");
            }
        }
        
        armarios.add(new Armario(material, codigo));
    }
    
    public void mostrarArmarios(){
        System.out.println("Lista armarios: ");
    
        for (Armario armario : armarios){
        
            System.out.println("codigo armario: " + armario.getCodigo() + ", libros: " + armario.cantidad);
        }
    }
}
