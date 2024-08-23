/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaenclase3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int port = 5002;
            Socket client = new Socket("192.168.0.190", port); // dirección del servidor
            
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String lastN = "";  // Para almacenar el último valor de 'n' enviado
            
            while (true) {
                // Menú principal
                System.out.println("Seleccione una operación:");
                System.out.println("1. Factorial");
                System.out.println("2. Fibonacci");
                System.out.println("3. Sumatoria");
                System.out.println("4. Salir");
                System.out.print("Ingrese su opción (1-4): ");
                
                int opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea
                
                if (opcion == 4) {
                    System.out.println("Saliendo...");
                    break;
                }
                
                System.out.print("Ingrese el valor de 'n' (o presione Enter para usar el último valor): ");
                String n = sc.nextLine().trim();
                
                if (n.isEmpty()) {
                    if (lastN.isEmpty()) {
                        System.out.println("Debe proporcionar un valor de 'n' inicialmente.");
                        continue;
                    } else {
                        n = lastN; // Usar el último valor de 'n'
                    }
                } else {
                    lastN = n; // Guardar el valor de 'n' para futuros usos
                }
                
                String mensaje = opcion + "-" + n;  // Crear la cadena "NumeroOpcion-NumeroN"
                toServer.println(mensaje);  // Enviar al servidor
                
                String result = fromServer.readLine();  // Leer resultado del servidor
                System.out.println("El resultado es: " + result);
            }
            
            // Cerrar recursos
            sc.close();
            toServer.close();
            fromServer.close();
            client.close();
        } catch (IOException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
        }
    }
}
