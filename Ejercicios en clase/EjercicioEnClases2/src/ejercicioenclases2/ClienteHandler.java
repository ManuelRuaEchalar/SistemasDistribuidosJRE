/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioenclases2;

import java.io.*;
import java.net.*;

public class ClienteHandler implements Runnable {
    private final Socket clienteSocket;
    private final String palabraSecreta;
    private StringBuilder palabraAdivinada;
    private int errores;

    public ClienteHandler(Socket socket, String palabra) {
        this.clienteSocket = socket;
        this.palabraSecreta = palabra;
        this.palabraAdivinada = new StringBuilder("_".repeat(palabra.length()));
        this.errores = 0;
    }

    @Override
    public void run() {
        try (
            DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream())
        ) {
            while (errores < 7 && !palabraAdivinada.toString().equals(palabraSecreta)) {
                enviarEstadoJuego(salida);
                String letraIngresada = entrada.readUTF().toLowerCase();
                
                if (letraIngresada.length() == 1 && Character.isLetter(letraIngresada.charAt(0))) {
                    procesarLetra(letraIngresada.charAt(0));
                } else {
                    salida.writeUTF("Por favor, ingresa una sola letra válida.");
                }
            }

            if (palabraAdivinada.toString().equals(palabraSecreta)) {
                salida.writeUTF("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
            } else {
                salida.writeUTF("Juego terminado. La palabra era: " + palabraSecreta);
            }

        } catch (IOException e) {
            System.out.println("Error en la conexión con el cliente: " + e.getMessage());
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviarEstadoJuego(DataOutputStream salida) throws IOException {
        salida.writeUTF(
            "Palabra: " + palabraAdivinada + "\n" +
            "Errores: " + errores + "/" + 7 + "\n" +
            "Ingresa una letra: "
        );
    }

    private void procesarLetra(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraAdivinada.setCharAt(i, letra);
                acierto = true;
            }
        }
        if (!acierto) {
            errores++;
        }
    }
}
