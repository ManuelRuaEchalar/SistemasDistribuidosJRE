/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio8;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author jruae
 */
public class Ejercicio8 {
    private static ArrayList<Tarea> tareas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    

    public static void main(String[] args) {
        int opcion;

         // Crear e inicializar cuatro tareas
        Tarea tarea1 = new Tarea("Comprar comida", "Comprar verduras y frutas", "2024-08-15", "Pendiente", 2);
        Tarea tarea2 = new Tarea("Hacer ejercicio", "Ir al gimnasio", "2024-08-16", "En progreso", 1);
        Tarea tarea3 = new Tarea("Enviar correo", "Enviar el informe semanal al jefe", "2024-08-14", "Pendiente", 3);
        Tarea tarea4 = new Tarea("Leer libro", "Leer el libro de Java avanzado", "2024-08-20", "Completada", 4);

        // Agregar las tareas al ArrayList
        tareas.add(tarea1);
        tareas.add(tarea2);
        tareas.add(tarea3);
        tareas.add(tarea4);
        
        do {
            // Mostrar menú
            mostrarMenu();

            // Leer opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            // Ejecutar opción
            switch (opcion) {
                case 1:
                    mostrarTareas();
                    break;
                case 2:
                    agregarTarea();
                    break;
                case 3:
                    eliminarTarea();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1) Mostrar Tareas");
        System.out.println("2) Agregar Tarea");
        System.out.println("3) Eliminar Tarea");
        System.out.println("4) Salir");
        System.out.print("Ingrese su opción: ");
    }

    private static void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            System.out.println("Lista de tareas:");
            for (Tarea tarea : tareas) {
                System.out.println("Prioridad: " + tarea.getPrioridad());
                System.out.println("Estado: " + tarea.getEstado());
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Descripcion: " + tarea.getDescripcion());
                System.out.println("Fecha: " + tarea.getFechaLimite());
                System.out.println("********************************" );
                System.out.println("\n");
            }
        }
    }

    private static void agregarTarea() {
        System.out.print("Ingrese el nombre de la tarea: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese la descripción de la tarea: ");
        String tarea = scanner.nextLine();
        
        System.out.print("Ingrese el estado (pendiente, en progreso, completada) de la tarea: ");
        String estado = scanner.nextLine();
        
        System.out.print("Ingrese la fecha limite de la tarea: (dd-mm-aa)");
        String fecha = scanner.nextLine();
        
        System.out.print("Ingrese la prioridad de la tarea (1 a 10): ");
        int prioridad = scanner.nextInt();
        tareas.add(new Tarea(nombre, tarea, fecha, estado, prioridad));
        System.out.println("Tarea agregada exitosamente.");
    }

    private static void eliminarTarea() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        mostrarTareas();
        System.out.print("Ingrese el número de la tarea a eliminar: ");
        int numeroTarea = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        if (numeroTarea < 1 || numeroTarea > tareas.size()) {
            System.out.println("Número de tarea inválido.");
        } else {
            tareas.remove(numeroTarea - 1);
            System.out.println("Tarea eliminada exitosamente.");
        }
    }
}
