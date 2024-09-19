package tareaprimerparcial;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class SEDUCAServer {

    public static void main(String[] args) {
        try {
            // Crear un mapa de RUDEs y respuestas
            Map<String, String> rudeResponses = new HashMap<>();
            rudeResponses.put("RiSaVe30061988", "si:verificado con éxito");
            rudeResponses.put("LuRoAg10121998", "si:verificado con éxito");
            rudeResponses.put("PaJiCa07031992", "si:verificado con éxito");
            rudeResponses.put("JoHeRu19091995", "si:verificado con éxito");
            rudeResponses.put("MaLoMa22051993", "si:verificado con éxito");
            rudeResponses.put("CaMoQu17081985", "si:verificado con éxito");
            rudeResponses.put("MiRaFl05011980", "si:verificado con éxito");
            rudeResponses.put("AnPéGó03111990", "si:verificado con éxito");
            rudeResponses.put("GaMaSo25042001", "si:verificado con éxito");
            rudeResponses.put("LaCaMu12101997", "si:verificado con éxito");
            rudeResponses.put("JuRuEc13072002", "si:verificado con éxito");

            DatagramSocket socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];

            System.out.println("Servidor SEDUCA UDP iniciado");

            while (true) {
                // Recibir datos del cliente
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Verificar si el RUDE existe en el mapa
                String response;
                if (rudeResponses.containsKey(request)) {
                    response = rudeResponses.get(request);
                } else {
                    response = "no:no se encontró el titulo de bachiller" + request;
                }

                // Enviar la respuesta de vuelta al cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor SEDUCA: " + e.getMessage());
        }
    }
}
