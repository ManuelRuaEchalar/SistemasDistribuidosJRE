
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 6789;
        try (DatagramSocket socketUDP = new DatagramSocket(puerto)) {
            byte[] bufer = new byte[1000];

            while (true) {
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(peticion);

                String ci = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Solicitud recibida para el CI: " + ci);

                // Lógica de validación de observaciones
                int observacion = "1234567".equals(ci) ? 1 : 0; // Cambiado a 1 si hay observaciones

                String respuestaStr = Integer.toString(observacion);
                byte[] respuesta = respuestaStr.getBytes();

                DatagramPacket respuestaPacket = new DatagramPacket(
                        respuesta, respuesta.length, peticion.getAddress(), peticion.getPort()
                );
                socketUDP.send(respuestaPacket);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
