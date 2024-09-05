/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class BancoServidorTCPRMI {
    public static void main(String[] args) {
        int puerto = 5000;
        List<Thread> clientes = new ArrayList<>();
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Banco TCP esperando conexiones...");
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");
                Thread hilo = new ManejadorCliente(cliente);
                clientes.add(hilo);
                hilo.start();
            }
        } catch (IOException ex) {
            System.out.println("Error en el servidor: " + ex.getMessage());
        }
    }
}
