/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioenclases2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteAhorcado {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5056;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PUERTO);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado al servidor de Ahorcado.");

            while (true) {
                String mensajeServidor = entrada.readUTF();
                System.out.println(mensajeServidor);

                if (mensajeServidor.startsWith("Juego terminado") || mensajeServidor.startsWith("¡Felicidades!")) {
                    break;
                }

                if (mensajeServidor.contains("Ingresa una letra:")) {
                    String letra = scanner.nextLine().toLowerCase();
                    salida.writeUTF(letra);
                }
            }

        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}