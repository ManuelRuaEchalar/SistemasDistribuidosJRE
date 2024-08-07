/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio4;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class GestionProductos {

    /**
     * @param listaProductos
     * @param args the command line arguments
     */ 
    
    public static void imprimirProductos(List<Producto> listaProductos) {
        for (Producto producto : listaProductos) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Marca: " + producto.getMarca());
            System.out.println("Modelo: " + producto.getModelo());
            System.out.println("Tipo: " + producto.getTipo());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Stock: " + producto.getStock());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Descuento: " + producto.getDescuento());
            System.out.println("Precio Final: " + producto.getPrecioFinal());
            System.out.println("----------");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1) Mostrar Productos");
            System.out.println("2) Agregar Productos");
            System.out.println("3) Eliminar Producto");
            System.out.println("4) Mostrar Precio Total del Stock");
            System.out.println("5) Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    
                    imprimirProductos(inventario.listarProductos());
                    break;
                case 2:
                    inventario.agregarProducto();
                    break;
                case 3:
                    
                    System.out.print("Ingrese el id: ");
                    int id = scanner.nextInt();
                    inventario.eliminarProducto(id);
                    break;
                case 4:
                    
                    int total = inventario.precioTotalStock();
                    System.out.println("El total de los productos en stock es " + total + " Bs.");
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 5);
    }
    
}
