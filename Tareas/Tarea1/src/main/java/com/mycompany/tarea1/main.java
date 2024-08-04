package com.mycompany.tarea1;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author jruae
 */
public class main {
    Scanner scanner = new Scanner(System.in);
    Biblioteca usfx = new Biblioteca("Biblioteca USFX", 80);
    
    int opcion;
    
    public void main (){
    
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar armario");
            System.out.println("2. Mostrar armarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    usfx.agregarArmario();
                    break;
                case 2:
                    usfx.mostrarArmarios();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 3);
    }
    
}
