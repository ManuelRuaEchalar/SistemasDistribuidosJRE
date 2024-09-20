/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microserviciobanco;

// Servidor Banco Mercantil (UDP)
import java.net.*;

public class BancoMercantilServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            
            System.out.println("Servidor Banco Mercantil UDP iniciado");
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                String response;
                if (request.startsWith("Buscar:")) {
                    String[] datos = request.split(":")[1].split("-");
                    if (datos[0].equals("11021654")) {
                        response = ""; // No hay cuentas en Mercantil para este CI
                    } else {
                        
                        response = "";
                    }
                } else if (request.startsWith("Congelar:")) {
                    String[] datos = request.split(":")[1].split("-");
                    response = "Si-" + datos[0];
                } else {
                    response = "Comando no reconocido";
                }
                
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor Banco Mercantil: " + e.getMessage());
        }
    }
}
