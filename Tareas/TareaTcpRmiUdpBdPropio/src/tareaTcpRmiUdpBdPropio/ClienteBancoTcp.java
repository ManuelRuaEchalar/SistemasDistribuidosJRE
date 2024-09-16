/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eantoniocf
 */
public class ClienteBancoTcp {

    public static void main(String[] args) {
        int port = 5002;
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String ci = null;

        try {
            Socket client = new Socket("localhost", port);
            System.out.println("Conectado al servidor");

            // Streams para enviar y recibir datos
            DataInputStream fromServer = new DataInputStream(client.getInputStream());
            DataOutputStream toServer = new DataOutputStream(client.getOutputStream());

            String mensajeCliente, mensajeServidor;
            Map<String, Map<String, String>> deudas = new HashMap<>();

            while (opcion != 3) {
                System.out.println("Seleccione una opcion para continuar");
                System.out.println("1. Buscar deudas con su C.I:");
                System.out.println("2. Pagar una deuda encontrada:");
                System.out.println("3.Salir");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        deudas.clear();
                        System.out.println("Ingrese su C.I:");
                        ci = sc.nextLine();
                        mensajeCliente = "buscar_ " + ci;
                        toServer.writeUTF(mensajeCliente);
                        mensajeServidor = fromServer.readUTF();
                        System.out.println("Mensaje recibido con exito");
//                        System.out.println(mensajeServidor);

                        // Verificar si la cadena contiene el formato esperado
                        if (!mensajeServidor.contains("-")) {
                            System.out.println("No tiene deudas");
                        } else {
                            // Separar las deudas usando "." como delimitador
                            String[] partes = mensajeServidor.split("\\.");

                            // Iterar sobre cada parte (deuda)
                            for (String deudaString : partes) {
                                // Extraer el identificador de la deuda
                                String[] subPartes = deudaString.split("-");
                                String deudaId = subPartes[0];
                                String[] llavesYDatos = subPartes[1].split(",");
                                Map<String, String> detallesDeuda = new HashMap<>();
                                for (String llaveDato : llavesYDatos) {

                                    String[] llaveDatoSeparado = llaveDato.split(":");
                                    String clave = llaveDatoSeparado[0].trim();
                                    String valor = llaveDatoSeparado[1].trim();
                                    detallesDeuda.put(clave, valor);

                                }

                                // Agregar el mapa de detalles de deuda al mapa principal
                                deudas.put(deudaId, detallesDeuda);
                            }

                            // Imprimir todas las deudas para verificar el resultado
                            for (Map.Entry<String, Map<String, String>> entry : deudas.entrySet()) {
                                String deudaId = entry.getKey();
                                Map<String, String> detalles = entry.getValue();
                                System.out.println(deudaId + ":");
                                for (Map.Entry<String, String> detalle : detalles.entrySet()) {
                                    System.out.println("  " + detalle.getKey() + ": " + detalle.getValue());
                                }
                            }
                        }

                        break;
                    case 2:
                        if (deudas.isEmpty()) {
                            System.out.println("No tiene deudas disponibles para pagar.");
                            break; // Salir de la opción 2 y volver al menú principal
                        }
                        System.out.println("Ingrese el numero (índice) de la deuda que desea pagar:");
                        int index = 0;
                        for (Map.Entry<String, Map<String, String>> entry : deudas.entrySet()) {
                            String deudaId = entry.getKey();
                            Map<String, String> detalles = entry.getValue();
                            System.out.print(index + ": ");
                            System.out.print(deudaId + " - ");
                            System.out.print("ci:" + detalles.get("ci") + ", ");
                            System.out.print("anio:" + detalles.get("anio") + ", ");
                            System.out.print("impuesto:" + detalles.get("impuesto") + ", ");
                            System.out.println("monto:" + detalles.get("monto"));
                            index++;
                        }

                        // Leer la selección del usuario
                        int seleccion = sc.nextInt();
                        sc.nextLine();

                        // Validar selección y mostrar deuda seleccionada
                        if (seleccion >= 0 && seleccion < deudas.size()) {
                            String selectedKey = "Deuda" + seleccion;
                            Map<String, String> selectedDeuda = deudas.get(selectedKey);
                            if (selectedDeuda != null) {
                                String result = String.format("ci:%s,anio:%s,impuesto:%s,monto:%s",
                                        selectedDeuda.get("ci"),
                                        selectedDeuda.get("anio"),
                                        selectedDeuda.get("impuesto"),
                                        selectedDeuda.get("monto"));
                                mensajeCliente = "pagar_" + result;
                                toServer.writeUTF(mensajeCliente);
                                mensajeServidor = fromServer.readUTF();
                                System.out.println(mensajeServidor);
                                if (mensajeServidor.contains("Deuda pagada exitosamente")) {
                                    deudas.remove(selectedKey);
                                }

                            } else {
                                System.out.println("Deuda no encontrada.");
                            }
                        } else {
                            System.out.println("Índice inválido.");
                        }
                        break;
                    default:
                        System.out.println("Saliendo...");
                        break;
                }
            }

            // Cerrar recursos
            fromServer.close();
            toServer.close();
            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
