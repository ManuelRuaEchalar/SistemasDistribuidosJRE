/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio5;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class Ejercicio5 {

    /**
     * @param args the command line arguments
     */
    
    Scanner scanner = new Scanner(System.in);
    static ContactoDAO ContactoDAO = new ContactoDAO("jdbc:mysql://localhost:3306/bd_contactos", "root", "");
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            // Mostrar el menú
            System.out.println("Seleccione una opción:");
            System.out.println("1) Mostrar contacto");
            System.out.println("2) Buscar contacto por nombre");
            System.out.println("3) Agregar contacto");
            System.out.println("4) Salir");
            System.out.print("Ingrese su opción: ");
            
            // Leer la opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada
            
            switch (opcion) {
                case 1:
                    // Mostrar contacto
                    List<Contacto> contactos = ContactoDAO.listar();
                    
                    for (Contacto contacto:contactos) {
                        System.out.print("Nombre: " + contacto.getNombre());
                        System.out.print("\n");
                        System.out.print("Numero de telefono: " + contacto.getNumero());
                        System.out.print("\n");
                        System.out.print("********************************************");
                        System.out.print("\n");
                    }
                    
                    break;
                case 2:
                    // Buscar contacto por nombre
                    System.out.print("Ingrese el nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    ContactoDAO.buscarContacto(nombre);
                    break;
                case 3:
                    // Agregar contacto
                    System.out.print("Ingrese el nombre del contacto: ");
                    String nom = scanner.nextLine();
                    System.out.print("Ingrese el numero del contacto: ");
                    int numero = scanner.nextInt();
                    
                    ContactoDAO.insertar(new Contacto(nom,numero));
                    break;
                case 4:
                    // Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            
        } while (opcion != 4);
        
        scanner.close();
    }
    
}
