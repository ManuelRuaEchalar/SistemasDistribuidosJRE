/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author jruae
 */
public class Rectangulo extends Figura{
    double altura;
    double base;

    public Rectangulo(double altura, double base, String nombre) {
        super(nombre);
        this.altura = altura;
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public double getBase() {
        return base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setBase(double base) {
        this.base = base;
    }
    
    @Override
    public double calcularArea() {
        double area = this.altura * this.base;
        return area;
    }
    
    @Override
    public double calcularPerimetro() {
        double perimetro = 2*this.altura + 2*this.base;
        return perimetro;
    }
}
