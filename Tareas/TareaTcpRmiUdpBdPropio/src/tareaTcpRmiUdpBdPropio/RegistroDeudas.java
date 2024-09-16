/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

/**
 *
 * @author eantoniocf
 */
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.SocketException;
//
//
//
//import java.util.Scanner;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroDeudas extends UnicastRemoteObject implements IRegistroDeudas {

    private DAODeuda daoDeuda;

    public RegistroDeudas() throws RemoteException {
        super();
        this.daoDeuda = new DAODeuda(); // Instanciar DAODeuda para operaciones con la base de datos
    }

    @Override
    public ArrayList<Deuda> buscarDeudas(String ci) throws RemoteException {
        return daoDeuda.buscarDeudas(ci); // Llamar al método del DAO para buscar deudas en la base de datos
    }

    @Override
    public Boolean pagarDeuda(Deuda deuda) throws RemoteException {
    int puerto = 6789;
    
    try {
        // Configuración de socket UDP para enviar la petición
        DatagramSocket socketUDP = new DatagramSocket();
        InetAddress hostServidor = InetAddress.getByName("localhost");

        String dato = deuda.getCi();

        // Convertimos el dato a bytes
        byte[] mensaje = dato.getBytes();

        // Construimos un datagrama para enviar el mensaje al servidor
        DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puerto);

        // Enviamos el datagrama
        socketUDP.send(peticion);

        // Construimos el DatagramPacket que contendrá la respuesta
        byte[] bufer = new byte[1000];
        DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);

        // Recibimos la respuesta del servidor
        socketUDP.receive(respuesta);

        String respuestaStr = new String(respuesta.getData(), 0, respuesta.getLength());

        // Si la respuesta del servidor es "0", significa que el pago fue exitoso
        if (respuestaStr.equalsIgnoreCase("0")) {
            System.out.println("Pago exitoso. Eliminando deuda de la base de datos.");

            // Llamamos a DAODeuda para eliminar la deuda de la base de datos
            boolean exito = daoDeuda.pagarDeuda(deuda);

            if (exito) {
                System.out.println("Deuda eliminada de la base de datos.");
            } else {
                System.out.println("Error al eliminar la deuda de la base de datos.");
            }

            return exito;
        } else {
            System.out.println("Pago no realizado. Respuesta UDP: " + respuestaStr);
        }

    } catch (SocketException e) {
        System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("IO: " + e.getMessage());
    }
    
    return false;
}
}
