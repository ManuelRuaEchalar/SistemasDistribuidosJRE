/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudpersonas;

import java.util.List;
import java.util.Scanner;


public class CrudPersonas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonaDAO personaDAO = new PersonaDAO("jdbc:mysql://localhost:3306/bd_personas", "root", "");

        boolean salir = false;
        while (!salir) {
            // Mostrar menú
            System.out.println("Seleccione una opcion:");
            System.out.println("1) Agregar persona");
            System.out.println("2) Mostrar personas");
            System.out.println("3) Eliminar persona");
            System.out.println("4) Editar persona");
            System.out.println("5) Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del entero

            switch (opcion) {
                case 1:
                    // Añadir persona
                    System.out.println("Introduce el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce los apellidos:");
                    String apellidos = scanner.nextLine();
                    System.out.println("Introduce la edad:");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después del entero
                    System.out.println("Introduce el número de carnet:");
                    String numerocarnet = scanner.nextLine();

                    Persona persona = new Persona(0, nombre, apellidos, edad, numerocarnet);
                    personaDAO.insertar(persona);
                    System.out.println("Persona añadida exitosamente.");
                    break;

                case 2:
                    // Mostrar personas
                    List<Persona> personas = personaDAO.listar();
                    if (personas.isEmpty()) {
                        System.out.println("No hay personas registradas.");
                    } else {
                        System.out.println("Listado de personas:");
                        for (Persona p : personas) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nro de carnet: ");
                    numerocarnet = scanner.nextLine();
                    personaDAO.eliminarPersona(numerocarnet);
                    break;
                case 4:
                    System.out.println("Ingrese el nro de carnet: ");
                    numerocarnet = scanner.nextLine();
                    personaDAO.buscarPersona(numerocarnet);
                    System.out.println("\n");
                    System.out.println("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.println("Introduce los apellidos:");
                    String nuevoapellidos = scanner.nextLine();
                    System.out.println("Introduce la edad:");
                    int nuevaEdad = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después del entero
                    System.out.println("Introduce el número de carnet:");
                    String nuevoNumeroCarnet = scanner.nextLine();
                    personaDAO.editarPersona(numerocarnet, nuevoNombre, nuevoapellidos, nuevaEdad, nuevoNumeroCarnet);
                    break;
                    
                case 5:
                    // Salir
                    System.out.println("Saliendo...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}

