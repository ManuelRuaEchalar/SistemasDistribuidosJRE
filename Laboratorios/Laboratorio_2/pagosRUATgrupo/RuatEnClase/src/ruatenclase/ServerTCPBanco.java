package ruatenclase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCPBanco {
    public static void main(String[] args) {
        int port = 5002;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor Banco TCP esperando conexiones...");

            try (Socket connectionSocket = serverSocket.accept();
                 BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                 PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true)) {

                System.out.println("Conexión establecida con cliente");
                String clientRequest = inFromClient.readLine();
                System.out.println("Solicitud del cliente: " + clientRequest);
                ClienteRMIBanco clienteRMIBanco = new ClienteRMIBanco();

                if (clientRequest.startsWith("Deuda:")) {
                    String ci = clientRequest.split(":")[1];
                    String response = clienteRMIBanco.manejarDeuda(ci);
                    outToClient.println(response);
                } else if (clientRequest.startsWith("Pago:")) {
                    String[] parts = clientRequest.split(":");
                    String ci = parts[1];
                    int anio = Integer.parseInt(parts[2]);
                    String impuesto = parts[3];
                    double monto = Double.parseDouble(parts[4]);
                    String response = clienteRMIBanco.manejarPago(ci, anio, impuesto, monto);
                    outToClient.println(response);
                } else {
                    outToClient.println("Comando no reconocido.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
