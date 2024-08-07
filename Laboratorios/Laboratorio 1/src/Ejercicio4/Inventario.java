/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jruae
 */
public class Inventario {
    
    List<Producto> productos = new ArrayList<>();
    LaptopDAO laptopDAO = new LaptopDAO("jdbc:mysql://localhost:3306/bd_productos", "root", "");
    TelefonoDAO telefonoDAO = new TelefonoDAO("jdbc:mysql://localhost:3306/bd_productos", "root", "");
    TelevisorDAO televisorDAO = new TelevisorDAO("jdbc:mysql://localhost:3306/bd_productos", "root", "");
    private Scanner scanner = new Scanner(System.in);

    public Inventario() {
    }
    
    
    public void agregarProducto() {
        // Solicitar los datos generales del producto

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la marca del producto: ");
        String marca = scanner.nextLine();

        System.out.print("Ingrese el modelo del producto: ");
        String modelo = scanner.nextLine();

        System.out.print("Ingrese el tipo del producto (laptop, telefono, televisor): ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el stock del producto: ");
        int stock = scanner.nextInt();

        System.out.print("Ingrese el precio del producto: ");
        int precio = scanner.nextInt();

        System.out.print("Ingrese el porcentaje descuento del producto: ");
        int descuento = scanner.nextInt();
        double des = descuento/100;

        // Crear el producto basado en el tipo
        if (tipo.equals("laptop")) {
            System.out.print("Ingrese la memoria RAM de la laptop (en GB): ");
            int memoriaRam = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese el procesador de la laptop: ");
            String procesador = scanner.nextLine();

            // Crear y devolver un objeto Laptop
            Producto producto = new Laptop(memoriaRam, procesador, 0, nombre, marca, modelo, tipo, descripcion, stock, precio, des);
            laptopDAO.insertar((Laptop) producto);
        } else if (tipo.equals("telefono")) {
            System.out.print("Ingrese la memoria RAM del telefono (en GB): ");
            int memoriaRam = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese el procesador del telefono: ");
            String procesador = scanner.nextLine();
            
            System.out.print("Ingrese la version de android del telefono: ");
            String versionAndroid = scanner.nextLine();

            // Crear y devolver un objeto Laptop
            Producto producto = new Telefono(memoriaRam, procesador, versionAndroid, 0, nombre, marca, modelo, tipo, descripcion, stock, precio, des);
            telefonoDAO.insertar((Telefono) producto);
        } else if (tipo.equals("televisor")) {
            System.out.print("Ingrese el tamaño de la pantalla (en pulgadas): ");
            int tamañoPantalla = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese la calidad de video: ");
            String calidad = scanner.nextLine();

            // Crear y devolver un objeto Laptop
            Producto producto = new Televisor(tamañoPantalla, calidad, 0, nombre, marca, modelo, tipo, descripcion, stock, precio, des);
            televisorDAO.insertar((Televisor) producto);
        }
        else {
            System.out.println("Tipo de producto no válido. No se puede crear el producto.");
            
        }
    }
    
    public void eliminarProducto(int id) {
    
        System.out.println("Ingrese el número del tipo de producto a eliminar: ");
        System.out.println("1. Laptop");
        System.out.println("2. Telefono movil");
        System.out.println("3. Televisor");
        System.out.println("\n");
        int opcion = scanner.nextInt();
        
        switch (opcion){
        
            case 1:
                laptopDAO.eliminarLaptop(id);
                break;
            case 2:
                telefonoDAO.eliminarTelefono(id);
                break;
            case 3:
                televisorDAO.eliminarTelevisor(id);
                break;
            default:
                System.out.println("Debes ingresar un tipo valido! \n");
                break;
        }
        
        
    }
    
    public List<Producto> listarProductos() {
        List<Producto> listaProductos = new ArrayList<>();

        // Obtener la lista de laptops desde el LaptopDAO
        List<Laptop> listaLaptops = laptopDAO.listar();
        List<Telefono> listaTel = telefonoDAO.listar();
        List<Televisor> listaTeles = televisorDAO.listar();

        // Añadir cada Laptop a la lista de productos
        listaProductos.addAll(listaLaptops);
        listaProductos.addAll(listaTel);
        listaProductos.addAll(listaTeles);

        // Si en el futuro tienes otros tipos de productos, puedes añadirlos aquí
        // Por ejemplo:
        // List<OtroProducto> listaOtrosProductos = otroProductoDAO.listar();
        // listaProductos.addAll(listaOtrosProductos);

        return listaProductos;
    }
    
    public int precioTotalStock(){
    
        productos = this.listarProductos();
        int total=0;
        for (Producto producto : productos){
        
            int stock = producto.getStock();
            int precio = producto.getPrecio();
            total += (stock*precio);
        }
        return total;
    } 
}
