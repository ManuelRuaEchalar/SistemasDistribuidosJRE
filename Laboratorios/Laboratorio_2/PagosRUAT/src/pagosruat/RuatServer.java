/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RuatServer extends UnicastRemoteObject implements RuatInterface {
    public RuatServer() throws RemoteException {
        super();
    }

    @Override
    public Deuda[] buscar(String ci) throws RemoteException {
        // Simulación de datos estáticos
        if (ci.equals("1234567")) {
            return new Deuda[]{
                new Deuda(ci, 2022, "Vehículo", 2451),
                new Deuda(ci, 2022, "Casa", 2500)
            };
        } else if (ci.equals("555587")) {
            return new Deuda[]{
                new Deuda(ci, 2021, "Vehículo", 5000)
            };
        } else if (ci.equals("333357")) {
            return new Deuda[]{
                new Deuda(ci, 2023, "Casa", 24547)
            };
        } else {
            return new Deuda[0];
        }
    }

    @Override
    public boolean pagar(Deuda deuda) throws RemoteException {
        // Simulación de consulta a la Alcaldía usando UDP
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            String consulta = "consulta:" + deuda.getCi();
            sendData = consulta.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());

            clientSocket.close();
            return respuesta.equals("respuesta:false");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            Naming.rebind("RuatService", new RuatServer());
            System.out.println("RUAT RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}