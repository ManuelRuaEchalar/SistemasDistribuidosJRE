/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.segundoejercicio;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author jruae
 */
public class Segundoejercicio {

    
    
    
    public static void main(String[] args) {
        
         String url = "jdbc:mysql://localhost:3306/bd_biblio"; // Cambia "nombre_base_de_datos"
        String user = "root"; // Cambia "tu_usuario"
        String password = ""; // Cambia "tu_contraseña"

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        
        
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        Biblioteca usfx = new Biblioteca("Biblioteca USFX", 80);
        int opcion;
        

        do {
            System.out.println("Menu:");
            System.out.println("1. Agregar armario");
            System.out.println("2. Mostrar armarios");
            System.out.println("3. Seleccionar armario");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            usfx.cargarArmarios();

            switch (opcion) {
                case 1:
                    usfx.agregarArmario();
                    
                    
                    break;
                case 2:
                    usfx.mostrarArmarios();
                    break;
                case 3:
                    usfx.mostrarArmarios();
                    System.out.println("inserte el codigo del armario que quiere seleccionar: ");
                    int codigo = scanner.nextInt();
                    System.out.println("1. Mostrar libros");
                    System.out.println("2. Agregar Libros");
                    opcion = scanner.nextInt();
                    if (opcion == 1) {
                    
                        for (Armario armario : usfx.armarios) {
                        
                            if (armario.codigo == codigo){
                            
                                armario.mostrarLibros();
                                break;
                            }
                        }
                    } else if (opcion == 2) {
                    
                        for (Armario armario : usfx.armarios) {
                        
                            if (armario.codigo == codigo){
                            
                                armario.agregarLibro();
                                break;
                            }
                        }
                    }
                    
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 4);
    }
    
}
