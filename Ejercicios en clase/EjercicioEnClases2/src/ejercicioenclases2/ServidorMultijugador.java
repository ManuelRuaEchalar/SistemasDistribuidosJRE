/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioenclases2;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorMultijugador {
    private static final int PUERTO = 5056;
    private static final String[] PALABRAS = {"ahorcado", "juego", "palabra", "arbol", "mentira"};
    private static final int MAX_ERRORES = 7;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de Ahorcado iniciado en el puerto " + PUERTO);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clienteSocket);

                String palabraSecreta = seleccionarPalabraAleatoria();
                ClienteHandler manejadorCliente = new ClienteHandler(clienteSocket, palabraSecreta);
                new Thread(manejadorCliente).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static String seleccionarPalabraAleatoria() {
        Random random = new Random();
        return PALABRAS[random.nextInt(PALABRAS.length)];
    }
}