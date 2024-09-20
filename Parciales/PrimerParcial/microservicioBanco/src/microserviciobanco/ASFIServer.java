/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microserviciobanco;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.net.*;

public class ASFIServer extends UnicastRemoteObject implements IASFI {
    protected ASFIServer() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public ArrayList<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws java.rmi.RemoteException {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        
        // Consultar Banco Mercantil (UDP)
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            String mensaje = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
            byte[] sendData = mensaje.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 9876);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            String[] cuentasMercantil = respuesta.split(":");
            for (String cuenta : cuentasMercantil) {
                String[] datosCuenta = cuenta.split("-");
                if (datosCuenta.length == 2) {
                    cuentas.add(new Cuenta(Banco.MERCANTIL, datosCuenta[0], ci, nombres, apellidos, Double.parseDouble(datosCuenta[1])));
                }
            }
            
            socket.close();
        } catch (Exception e) {
            System.err.println("Error al consultar Banco Mercantil: " + e.getMessage());
        }

        // Consultar Banco BCP (TCP)
        try (Socket socket = new Socket("localhost", 5000)) {
            String mensaje = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
            socket.getOutputStream().write(mensaje.getBytes());

            byte[] receiveData = new byte[1024];
            int bytesRead = socket.getInputStream().read(receiveData);
            String respuesta = new String(receiveData, 0, bytesRead);

            String[] cuentasBCP = respuesta.split(":");
            for (String cuenta : cuentasBCP) {
                String[] datosCuenta = cuenta.split("-");
                if (datosCuenta.length == 2) {
                    cuentas.add(new Cuenta(Banco.BCP, datosCuenta[0], ci, nombres, apellidos, Double.parseDouble(datosCuenta[1])));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al consultar Banco BCP: " + e.getMessage());
        }

        return cuentas;
    }

    @Override
    public boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws java.rmi.RemoteException {
        boolean resultado = false;

        if (cuenta.getBanco() == Banco.MERCANTIL) {
            // Retener en Banco Mercantil (UDP)
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName("localhost");
                String mensaje = "Congelar:" + cuenta.getNroCuenta() + "-" + monto;
                byte[] sendData = mensaje.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 9876);
                socket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                resultado = respuesta.startsWith("Si-");
                socket.close();
            } catch (Exception e) {
                System.err.println("Error al retener monto en Banco Mercantil: " + e.getMessage());
            }
        } else if (cuenta.getBanco() == Banco.BCP) {
            // Retener en Banco BCP (TCP)
            try (Socket socket = new Socket("localhost", 5000)) {
                String mensaje = "Congelar:" + cuenta.getNroCuenta() + "-" + monto;
                socket.getOutputStream().write(mensaje.getBytes());

                byte[] receiveData = new byte[1024];
                int bytesRead = socket.getInputStream().read(receiveData);
                String respuesta = new String(receiveData, 0, bytesRead);

                resultado = respuesta.startsWith("Si-");
            } catch (Exception e) {
                System.err.println("Error al retener monto en Banco BCP: " + e.getMessage());
            }
        }

        return resultado;
    }

    public static void main(String args[]) {
        try {
            ASFIServer obj = new ASFIServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ASFI", obj);
            System.out.println("Servidor ASFI listo");
        } catch (Exception e) {
            System.err.println("Error en el servidor ASFI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}