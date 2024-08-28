/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruat;

import java.io.*;
import java.net.*;

public class ClienteUsuario {
    public static void main(String[] args) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el CI para consultar deudas:");
            String ci = userInput.readLine();

            // Conectar al servidor Banco
            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Consultar deudas
            outToServer.writeBytes("Deuda:" + ci + '\n');
            String response = inFromServer.readLine();
            System.out.println("Deudas recibidas: " + response);

            // Opcional: Proceso de pago basado en la respuesta
            // Ejemplo: Pagar deuda
            System.out.println("Ingrese el año, impuesto y monto a pagar (separados por coma):");
            String pago = userInput.readLine();
            outToServer.writeBytes("Pagar:" + ci + "," + pago + '\n');
            String pagoResponse = inFromServer.readLine();
            System.out.println("Respuesta de pago: " + pagoResponse);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
