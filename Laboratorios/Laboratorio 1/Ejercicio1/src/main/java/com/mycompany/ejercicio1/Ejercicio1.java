/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1;
import java.util.Scanner;

/**
 *
 * @author jruae
 */
public class Ejercicio1 {

    public static Figura ingresarFigura(Scanner scanner) {
        System.out.println("Seleccione el tipo de figura:");
        System.out.println("1) Rectángulo");
        System.out.println("2) Círculo");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la base del rectángulo: ");
                double base = scanner.nextDouble();
                System.out.print("Ingrese el alto del rectángulo: ");
                double alto = scanner.nextDouble();
                return new Rectangulo(alto, base, "Rectangulo");
            case 2:
                System.out.print("Ingrese el radio del círculo: ");
                double radio = scanner.nextDouble();
                return new Circulo(radio, "Circulo");
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
                return null;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Figura figura = null;
        

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1) Ingresar Figura");
            System.out.println("2) Calcular área de la figura");
            System.out.println("3) Calcular perímetro de la figura");
            System.out.println("4) Salir");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    figura = ingresarFigura(scanner);
                    break;
                case 2:
                    if (figura != null) {
                        System.out.println("Área: " + figura.calcularArea());
                    } else {
                        System.out.println("Primero debe ingresar una figura.");
                    }
                    break;
                case 3:
                    if (figura != null) {
                        System.out.println("Perímetro: " + figura.calcularPerimetro());
                    } else {
                        System.out.println("Primero debe ingresar una figura.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
