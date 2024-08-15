package com.mycompany.soquettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            System.out.println("Se inició el servidor con éxito");
            Socket client;
            PrintStream toClient;
            client = server.accept();
            System.out.println("Cliente conectado");

            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String recibido = fromClient.readLine();
            System.out.println("El cliente envió el mensaje: " + recibido);

            // Convertir el mensaje a un número entero
            int numero = Integer.parseInt(recibido);

            // Calcular el factorial
            int resultadoFactorial = calcularFactorial(numero);

            // Enviar el resultado de vuelta al cliente
            toClient = new PrintStream(client.getOutputStream());
            toClient.println("El factorial de " + numero + " es: " + resultadoFactorial);

            // Cerrar las conexiones
            fromClient.close();
            toClient.close();
            client.close();

        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    // Función para calcular el factorial de un número
    public static int calcularFactorial(int numero) {
        if (numero == 0 || numero == 1) {
            return 1;
        } else {
            return numero * calcularFactorial(numero - 1);
        }
    }
}