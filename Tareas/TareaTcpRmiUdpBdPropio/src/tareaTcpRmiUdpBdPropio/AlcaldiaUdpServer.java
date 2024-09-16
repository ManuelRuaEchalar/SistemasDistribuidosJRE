    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AlcaldiaUdpServer {

    public static void main(String[] args) {
        int port = 6789;
        
        // Conectar a la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ruat", "root", "")) {
            
            RegistroObservaciones observaciones = new RegistroObservaciones(connection);
            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] bufer = new byte[1000];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.print("Datagrama recibido del host: " + peticion.getAddress());
                System.out.println(" desde el puerto remoto: " + peticion.getPort());

                String cadena = new String(peticion.getData(), 0, peticion.getLength()).trim();

                // Buscamos la observación en la base de datos
                Observacion observacion = observaciones.buscarObservacionPorCi(cadena);

                String response;
                if (observacion != null && observacion.isTieneObservacion()) {
                    response = "1";
                } else {
                    response = "0";
                }

                // Construimos el DatagramPacket para enviar la respuesta
                byte[] mensaje = response.getBytes();
                DatagramPacket respuesta = new DatagramPacket(mensaje, mensaje.length, peticion.getAddress(), peticion.getPort());

                // Enviamos la respuesta
                socketUDP.send(respuesta);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL: " + e.getMessage());
        }
    }
}
