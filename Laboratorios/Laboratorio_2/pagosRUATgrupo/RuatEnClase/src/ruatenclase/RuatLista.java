package ruatenclase;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RuatLista extends UnicastRemoteObject implements IRuat {

    private ArrayList<Deuda> listaDeudas;

    public RuatLista() throws RemoteException {
        listaDeudas = new ArrayList<>();
        listaDeudas.add(new Deuda("1234567", 2022, Impuesto.Vehiculo, 2451.0));
        listaDeudas.add(new Deuda("1234567", 2022, Impuesto.Inmueble, 2500.0));
        listaDeudas.add(new Deuda("555587", 2021, Impuesto.Vehiculo, 5000.0));
        listaDeudas.add(new Deuda("333357", 2023, Impuesto.Inmueble, 24547.0));
    }

@Override
public Deuda[] buscar(String ci) throws RemoteException {
    if (!consultarAlcaldia(ci)) { // Aquí se invierte la lógica: si hay observaciones, no buscamos
        return new Deuda[0];
    }
    
    ArrayList<Deuda> deudasCI = new ArrayList<>();
    for (Deuda deuda : listaDeudas) {
        if (deuda.getCi().equals(ci)) {
            deudasCI.add(deuda);
        }
    }
    return deudasCI.toArray(new Deuda[0]);
}



    @Override
    public boolean pagar(Deuda deuda) throws RemoteException {
        return listaDeudas.remove(deuda);
    }

    @Override
public String manejarDeuda(String ci) throws RemoteException {
    Deuda[] deudas = buscar(ci);
    if (deudas.length == 0) {
        return "El cliente con CI " + ci + " no tiene deudas registradas.";
    } else {
        StringBuilder resultado = new StringBuilder("Deudas del cliente con CI " + ci + ":\n");
        for (Deuda deuda : deudas) {
            resultado.append("Año: ").append(deuda.getAnio())
                     .append(", Impuesto: ").append(deuda.getImpuesto())
                     .append(", Monto: ").append(deuda.getMonto())
                     .append("\n");
        }
        return resultado.toString();
    }
}

    @Override
    public String manejarPago(String ci, int anio, String impuesto, double monto) throws RemoteException {
        for (Deuda deuda : listaDeudas) {
            if (deuda.getCi().equals(ci) && deuda.getAnio() == anio && deuda.getImpuesto().toString().equals(impuesto) && deuda.getMonto() == monto) {
                if (pagar(deuda)) {
                    return "Pago realizado con éxito para " + ci + " por el impuesto " + impuesto + " del año " + anio;
                } else {
                    return "Error al realizar el pago.";
                }
            }
        }
        return "No se encontró la deuda correspondiente para el CI " + ci + ".";
    }

    private boolean consultarAlcaldia(String ci) {
    String ip = "localhost"; // IP de la Alcaldía (cambiar según sea necesario)
    int puerto = 6789; // Puerto del servidor UDP de la Alcaldía

    try (DatagramSocket socketUDP = new DatagramSocket()) {
        // Enviar consulta a la Alcaldía
        byte[] mensaje = ci.getBytes();
        InetAddress hostServidor = InetAddress.getByName(ip);
        DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puerto);
        socketUDP.send(peticion);

        // Recibir respuesta de la Alcaldía
        byte[] bufer = new byte[1000];
        DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
        socketUDP.receive(respuesta);

        // Interpretar la respuesta
        String respuestaStr = new String(respuesta.getData(), 0, respuesta.getLength());
        return respuestaStr.equals("0"); // "0" significa que no hay observaciones
    } catch (SocketException e) {
        System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("IO: " + e.getMessage());
    }
    return false; // Si algo falla, se asume que hay observaciones
}


    
}
