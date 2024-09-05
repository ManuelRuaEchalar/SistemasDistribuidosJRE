/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AlcaldiaServidorUDP {
    public static void main(String[] args) {
        try (DatagramSocket servidorSocket = new DatagramSocket(6000)) {
            System.out.println("Servidor UDP de la Alcaldía en funcionamiento.");
            while (true) {
                byte[] bufferRecibido = new byte[256];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                servidorSocket.receive(paqueteRecibido);

                String consultaRecibida = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Consulta recibida: " + consultaRecibida);

                String ci = consultaRecibida.split(":")[1];
                String respuesta;

                if (ci.equals("1234567")) {
                    respuesta = "respuesta:false";
                } else {
                    respuesta = "respuesta:true";
                }

                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(respuestaBytes, respuestaBytes.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                servidorSocket.send(paqueteRespuesta);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP de la Alcaldía: " + e.getMessage());
        }
    }
}

