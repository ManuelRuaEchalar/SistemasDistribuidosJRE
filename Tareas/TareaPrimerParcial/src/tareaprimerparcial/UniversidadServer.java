/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaprimerparcial;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.net.*;

public class UniversidadServer extends UnicastRemoteObject implements IUniversidad {
    
    protected UniversidadServer() throws RemoteException {
        super();
    }

    @Override
    public Diploma emitirDiploma(String CI, String nombres, String primerApellido, String segundoApellido, String fechaNacimiento, String carrera) throws RemoteException {
        StringBuilder mensajeError = new StringBuilder();

        // Verificar con SEGIP
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ISEGIP segip = (ISEGIP) registry.lookup("SEGIP");
            Respuesta respuestaSEGIP = segip.verificarDatos(CI, nombres, primerApellido + " " + segundoApellido);
            if (!respuestaSEGIP.getEstado()) {
                mensajeError.append(respuestaSEGIP.getMensaje()).append(". ");
            }
        } catch (Exception e) {
            mensajeError.append("Error al comunicarse con SEGIP. ");
        }

        // Verificar con SEDUCA
        String rude = calcularRUDE(nombres, primerApellido, segundoApellido, fechaNacimiento);
        System.out.println(rude);
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            byte[] sendData = (rude).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 9876);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String respuestaSEDUCA = new String(receivePacket.getData(), 0, receivePacket.getLength());
            socket.close();

            if (!respuestaSEDUCA.startsWith("si:")) {
                mensajeError.append(respuestaSEDUCA.split(":")[1]).append(". ");
            }
        } catch (IOException e) {
            mensajeError.append("Error al comunicarse con SEDUCA. ");
        }

        // Verificar con SERECI
        try {
            Socket sereceSocket = new Socket("localhost", 5000);
            String mensaje = "Ver-fecha:" + nombres + "," + primerApellido + " " + segundoApellido + "," + fechaNacimiento;
            sereceSocket.getOutputStream().write(mensaje.getBytes());

            byte[] respuestaBytes = new byte[1024];
            int bytesRead = sereceSocket.getInputStream().read(respuestaBytes);
            String respuestaSERECI = new String(respuestaBytes, 0, bytesRead);
            sereceSocket.close();

            if (!respuestaSERECI.startsWith("si:")) {
                mensajeError.append(respuestaSERECI.split(":")[1]).append(". ");
            }
        } catch (IOException e) {
            mensajeError.append("Error al comunicarse con SERECI. ");
        }

        // Emitir diploma
        if (mensajeError.length() > 0) {
            return new Diploma("", "", "", mensajeError.toString().trim());
        } else {
            String nombreCompleto = nombres + " " + primerApellido + " " + segundoApellido;
            return new Diploma(nombreCompleto, carrera, fechaNacimiento, "");
        }
    }

    private String calcularRUDE(String nombres, String primerApellido, String segundoApellido, String fechaNacimiento) {
        return nombres.substring(0, 2) + primerApellido.substring(0, 2) + segundoApellido.substring(0, 2) + fechaNacimiento.replace("-", "");
    }

    public static void main(String[] args) {
        try {
            UniversidadServer server = new UniversidadServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Universidad", server);
            System.out.println("Servidor Universidad RMI iniciado");
        } catch (RemoteException e) {
            System.err.println("Error al iniciar el servidor Universidad: " + e.getMessage());
        }
    }
}