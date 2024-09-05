/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonpattern;

/**
 *
 * @author jruae
 */
public class Operaciones {
    
    public int a;
    public int b;
    static Operaciones operador = null;

    private Operaciones(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public int suma(int a, int b){
        return a+b;
    }
    
    public int resta(int a, int b){
        return a-b;
    }
    
    public int multiplicacion(int a, int b){
        return (int) a*b;
    }
    
    public int division(int a, int b){
        return (int) a/b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    
    
    public static Operaciones operadorSingleton(int a, int b){
        if (operador==null){
            operador = new Operaciones(a,b);
            return operador;
        } else {
            return operador;
        }
    }
    
    
    
}
