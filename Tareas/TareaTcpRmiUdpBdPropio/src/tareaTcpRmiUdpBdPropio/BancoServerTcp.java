/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BancoServerTcp {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {
        int port = 5002;
        IRegistroDeudas registro = (IRegistroDeudas)Naming.lookup("rmi://192.168.185.5/RegistroDeDeudas");
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            Socket client = server.accept(); // Acepta la conexión del cliente
            System.out.println("Cliente conectado");

            // Streams para enviar y recibir datos
            DataInputStream fromClient = new DataInputStream(client.getInputStream());
            DataOutputStream toClient = new DataOutputStream(client.getOutputStream());

            String mensajeServidor, mensajeCliente;
            while (true) {
                // Leer mensaje del cliente
                System.out.println("Esperando mensaje de cliente...");
                mensajeCliente = fromClient.readUTF();
                System.out.println("Cliente: " + mensajeCliente);

                String[] pares = mensajeCliente.split("_");
                if (pares.length == 2) {
                    String clave = pares[0].trim();
                    String valor = pares[1].trim();

                    if (clave.equalsIgnoreCase("buscar")) {
                        ArrayList<Deuda> deudasEncontradas = registro.buscarDeudas(valor);
                        if (deudasEncontradas.isEmpty()) {
                            mensajeServidor = "No se encontraron deudas";
                            toClient.writeUTF(mensajeServidor);
                        } else {
                            StringBuilder deudas = new StringBuilder();
                            int i = 0;
                            for (Deuda deuda : deudasEncontradas) {
                                deudas.append(String.format("Deuda%d-ci:%s,anio:%d,impuesto:%s,monto:%d.",
                                        i,
                                        deuda.getCi(),
                                        deuda.getAnio(),
                                        deuda.getImpuesto().name(),
                                        deuda.getMonto()));
                                i++;
                            }
                            mensajeServidor = deudas.toString();
                            toClient.writeUTF(mensajeServidor);
                        }

                    } else if (clave.equalsIgnoreCase("pagar")) {
                        // Crear el objeto Deuda desde el mensaje recibido
                        Deuda deuda = parseDeuda(valor);
                        if (deuda != null) {
                            if (registro.pagarDeuda(deuda)) {
                                mensajeServidor = "Deuda pagada exitosamente";
                            } else {
                                mensajeServidor = "Error, no se pudo pagar la deuda.Su C.I. Tiene Observaciones";
                            }
                        } else {
                            mensajeServidor = "Error, formato de deuda inválido";
                        }
                        toClient.writeUTF(mensajeServidor);
                    } else {
                        mensajeServidor = "Clave no reconocida";
                        toClient.writeUTF(mensajeServidor);
                    }
                } else {
                    mensajeServidor = "Error con formato de datos";
                    toClient.writeUTF(mensajeServidor);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Deuda parseDeuda(String deudaStr) {
        // Ejemplo de formato: "ci:1234567,anio:2022,impuesto:Vehiculo,monto:2451"
        try {
            String[] partes = deudaStr.split(",");
            String ci = null;
            int anio = 0;
            Impuesto impuesto = null;
            int monto = 0;

            for (String parte : partes) {
                String[] keyValue = parte.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "ci":
                            ci = value;
                            break;
                        case "anio":
                            anio = Integer.parseInt(value);
                            break;
                        case "impuesto":
                            impuesto = Impuesto.valueOf(value); // Asegúrate de que 'Impuesto' tenga un método 'valueOf'
                            break;
                        case "monto":
                            monto = Integer.parseInt(value);
                            break;
                        default:
                            return null; // Clave desconocida
                    }
                } else {
                    return null; // Error en el formato
                }
            }

            if (ci != null && impuesto != null) {
                return new Deuda(ci, anio, impuesto, monto);
            } else {
                return null; // Datos incompletos
            }
        } catch (Exception e) {
            return null; // Error al analizar la deuda
        }
    }
}

