/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruat;

import java.io.*;
import java.net.*;
import java.rmi.Naming;

public class BancoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Banco TCP Server is running...");
            RuatInterface ruat = (RuatInterface) Naming.lookup("//localhost/RuatService");

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                String clientSentence = inFromClient.readLine();
                System.out.println("Received: " + clientSentence);
                String[] parts = clientSentence.split(":");

                if (parts[0].equals("Deuda")) {
                    String ci = parts[1].trim();
                    Deuda[] deudas = ruat.buscar(ci);
                    StringBuilder response = new StringBuilder("deudas:");
                    for (Deuda deuda : deudas) {
                        response.append(deuda.getAnio()).append(",").append(deuda.getImpuesto()).append(",").append(deuda.getMonto()).append(";");
                    }
                    outToClient.writeBytes(response.toString() + '\n');
                } else if (parts[0].equals("Pagar")) {
                    String[] params = parts[1].split(",");
                    String ci = params[0].trim();
                    int anio = Integer.parseInt(params[1].trim());
                    String impuesto = params[2].trim();
                    double monto = Double.parseDouble(params[3].trim());
                    Deuda deuda = new Deuda(ci, anio, impuesto, monto);
                    boolean resultado = ruat.pagar(deuda);
                    outToClient.writeBytes("transacción:" + resultado + '\n');
                }

                connectionSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
