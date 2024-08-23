/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaenclase3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            int port = 5002;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Se inició el servidor en el puerto " + port);

            while (true) {
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream());
                
                System.out.println("Cliente se conectó");
                System.out.println("Bienvenido al Servidor de la materia SIS258");

                String mensaje = fromClient.readLine();
                
                String[] parts = mensaje.split("-");
                String opcion = parts[0];
                String n = parts.length > 1 ? parts[1] : "";  // Si existe un valor después de "-", lo toma
                
                static String ultimoN = ""; // Último valor de 'n' procesado
                String resultado = "";

                if (!n.isEmpty()) {
                    ultimoN = n;  // Guardar el nuevo valor de 'n' si se proporciona
                } else if (ultimoN.isEmpty()) {
                    toClient.println("Error: no se ha proporcionado un valor de 'n'.");
                    continue;
                }

                Integer numero = Integer.parseInt(ultimoN); // Convertir el valor de 'n' en entero
                Operaciones operaciones = new Operaciones(numero);

                // Ejecución de la operación solicitada
                switch (opcion) {
                    case "1":
                        resultado = String.valueOf(operaciones.factorial());
                        break;
                    case "2":
                        resultado = String.valueOf(operaciones.fibonacci());
                        break;
                    case "3":
                        resultado = String.valueOf(operaciones.sumatoria());
                        break;
                    default:
                        resultado = "Opción inválida.";
                        break;
                }

                toClient.println(resultado);

                // Cerrar streams y socket
                fromClient.close();
                toClient.close();
                client.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
