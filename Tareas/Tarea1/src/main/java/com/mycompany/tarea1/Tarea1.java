
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea1;
import java.util.Scanner;
/**
 *
 * @author jruae
 */
public class Tarea1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner lectura = new Scanner (System.in);
        int numero = 0;
        
        while(true){
            System.out.println("EJERCICIO 1 ");

            System.out.println("1) Ingresar número.");
            System.out.println("2) Calcular Fibonacci.");
            System.out.println("3) Calcular factorial.");
            System.out.println("4) Calcular sumatoria");
            System.out.println("Elija una opción: ");

            int opcion = lectura.nextInt();
            
            if (opcion == 1) {
                System.out.println("Ingrese un numero: ");
                numero = lectura.nextInt();
                System.out.println("Su numero es: " + numero);

            } else if (opcion == 2) {

                int a = 0;
                int b = 1;
                int c = 1;
                for (int i = 1; i <= numero - 3; i++) {

                    a = b;
                    b = c;
                    c = a + b;

                }
                System.out.println("El fibonacci de "+numero+" es: " + c);

            } else if (opcion == 3) {
            
                int factorial=1;
                for (int i=1;i<=numero;i++) {
                
                    factorial*=i;
                    
                }
                System.out.println("El factorial de "+numero+" es: " + factorial);
            } else if (opcion == 4) {
            
                int suma=0;
                for (int i=0;i<=numero;i++) {
                
                    suma+=i;
                    
                }
                System.out.println("La sumatoria de "+numero+" es: " + suma);

        }
    }
   }
}