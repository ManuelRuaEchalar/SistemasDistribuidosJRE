/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microserviciobanco;

// Servidor Banco BCP (TCP)
import java.net.*;
import java.io.*;

public class BancoBCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor Banco BCP TCP iniciado");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                String request = new String(buffer, 0, bytesRead);
                
                String response;
                if (request.startsWith("Buscar:")) {
                    String[] datos = request.split(":")[1].split("-");
                    if (datos[0].equals("11021654")) {
                        response = "657654-20000.00";
                    } else {
                        response = "";
                    }
                } else if (request.startsWith("Congelar:")) {
                    String[] datos = request.split(":")[1].split("-");
                    response = "Si-" + datos[0];
                } else {
                    response = "Comando no reconocido";
                }
                
                out.write(response.getBytes());
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor Banco BCP: " + e.getMessage());
        }
    }
}