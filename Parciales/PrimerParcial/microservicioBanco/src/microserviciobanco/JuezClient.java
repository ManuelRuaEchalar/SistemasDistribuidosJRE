/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microserviciobanco;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class JuezClient {
    public static void main(String[] args) {
        try {
            
            
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IASFI asfi = (IASFI) registry.lookup("ASFI");
            while(true){
                
             System.out.println("BIENVENIDO AL SISTEMA DE RETENCIONES FISCALES \n");
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Ingrese su CI: ");
            String ci = scanner.nextLine();
            System.out.println("Ingrese sus nombres: ");
            String nombres = scanner.nextLine();
            System.out.println("Ingrese sus apellidos: ");
            String apellidos = scanner.nextLine();
            
            ArrayList<Cuenta> cuentas = asfi.consultarCuentas(ci, nombres, apellidos);
            
            if (cuentas.isEmpty()) {
                System.out.println("Sin cuentas para los datos proporcionados.");
            } else {
                System.out.println("Cuentas:");
                for (int i = 0; i < cuentas.size(); i++) {
                    System.out.println((i + 1) + ". " + cuentas.get(i));
                }
                
                System.out.print("Selecciona la opcion de la cuenta que deseas retener: ");
                int seleccion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                
                if (seleccion > 0 && seleccion <= cuentas.size()) {
                    Cuenta cuentaSeleccionada = cuentas.get(seleccion - 1);
                    
                    System.out.print("Ingrese el monto en BS a retener: ");
                    double monto = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea
                    
                    System.out.print("Ingrese la glosa: ");
                    String glosa = scanner.nextLine();
                    
                    boolean resultado = asfi.retenerMonto(cuentaSeleccionada, monto, glosa);
                    
                    if (resultado) {
                        System.out.println("Transaccion exitosa.");
                    } else {
                        System.out.println("Transacion fallida.");
                    }
                } else {
                    System.out.println("Selección no valida.");
                }
            }
            
            
            }
        } catch (Exception e) {
            System.err.println("Error en el cliente Juez: " + e.getMessage());
            e.printStackTrace();
        }
    }
}