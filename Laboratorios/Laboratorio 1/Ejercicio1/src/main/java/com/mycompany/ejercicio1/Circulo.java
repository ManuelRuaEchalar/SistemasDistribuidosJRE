/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author jruae
 */
public class Circulo extends Figura {
    double radio;

    public Circulo(double radio, String nombre) {
        super(nombre);
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
    
    @Override
    public double calcularArea() {
        double area = 3.1416f * this.radio*this.radio;
        return area;
    }
    
    @Override
    public double calcularPerimetro() {
        double perimetro = 2*3.1416f * this.radio;
        return perimetro;
    }
}
