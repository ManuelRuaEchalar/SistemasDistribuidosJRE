/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RuatServidorRMIUDP extends UnicastRemoteObject implements InterfazRuat {
    private List<Deuda> listaDeudas;

    protected RuatServidorRMIUDP() throws RemoteException {
        super();
        listaDeudas = new ArrayList<>();
        listaDeudas.add(new Deuda("1234567", 2022, "Vehículo", 2451));
        listaDeudas.add(new Deuda("1234567", 2022, "Casa", 2500));
        listaDeudas.add(new Deuda("555587", 2021, "Vehículo", 5000));
        listaDeudas.add(new Deuda("333357", 2023, "Casa", 24547));
    }

    @Override
    public Deuda[] buscar(String ci) throws RemoteException {
        return listaDeudas.stream().filter(d -> d.getCi().equals(ci)).toArray(Deuda[]::new);
    }

    @Override
    public boolean pagar(Deuda deuda) throws RemoteException {
        // Verificar con la Alcaldía si el CI tiene observaciones
        boolean sinObservaciones = consultarAlcaldia(deuda.getCi());

        if (sinObservaciones) {
            // Eliminar la deuda de la lista
            listaDeudas.removeIf(d -> d.getCi().equals(deuda.getCi()) && d.getAnio() == deuda.getAnio() && d.getTipoImpuesto().equals(deuda.getTipoImpuesto()));
            return true;
        } else {
            return false;
        }
    }

    private boolean consultarAlcaldia(String ci) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccion = InetAddress.getByName("localhost");
            String consulta = "consulta:" + ci;
            byte[] buffer = consulta.getBytes();
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, 6000);
            socket.send(paquete);

            byte[] bufferRespuesta = new byte[256];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
            socket.receive(paqueteRespuesta);

            String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
            socket.close();

            return respuesta.equals("respuesta:true");
        } catch (Exception e) {
            System.out.println("Error al consultar a la Alcaldía: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            RuatServidorRMIUDP servicioRuat = new RuatServidorRMIUDP();
            java.rmi.Naming.bind("//localhost/ServicioRuat", servicioRuat);
            System.out.println("Servidor RMI del RUAT listo.");
        } catch (Exception e) {
            System.out.println("Error en el servidor RMI del RUAT: " + e.getMessage());
        }
    }
}

