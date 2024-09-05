/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.Naming;

class ManejadorCliente extends Thread {
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (
            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintStream salidaCliente = new PrintStream(cliente.getOutputStream())
        ) {
            String mensaje;
            while ((mensaje = entradaCliente.readLine()) != null) {
                if (mensaje.startsWith("Deuda:")) {
                    String ci = mensaje.split(":")[1];
                    procesarConsultaDeuda(ci, salidaCliente);
                } else if (mensaje.startsWith("Pagar:")) {
                    String[] parametros = mensaje.split(":")[1].split(",");
                    String ci = parametros[0];
                    int anio = Integer.parseInt(parametros[1]);
                    String tipoImpuesto = parametros[2];
                    double monto = Double.parseDouble(parametros[3]);

                    procesarPago(ci, anio, tipoImpuesto, monto, salidaCliente);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al manejar cliente: " + e.getMessage());
        }
    }

    private void procesarConsultaDeuda(String ci, PrintStream salidaCliente) {
        try {
            InterfazRuat ruat = (InterfazRuat) Naming.lookup("//localhost/ServicioRuat");
            Deuda[] deudas = ruat.buscar(ci);
            StringBuilder respuesta = new StringBuilder();
            for (Deuda deuda : deudas) {
                respuesta.append(deuda.getAnio()).append(",")
                        .append(deuda.getTipoImpuesto()).append(",")
                        .append(deuda.getMonto()).append("; ");
            }

            salidaCliente.println("deudas:" + respuesta.toString());

        } catch (Exception e) {
            salidaCliente.println("Error al consultar deudas: " + e.getMessage());
        }
    }

    private void procesarPago(String ci, int anio, String tipoImpuesto, double monto, PrintStream salidaCliente) {
        try {
            InterfazRuat ruat = (InterfazRuat) Naming.lookup("//localhost/ServicioRuat");
            Deuda deuda = new Deuda(ci, anio, tipoImpuesto, monto);
            boolean resultado = ruat.pagar(deuda);

            salidaCliente.println("transacción:" + resultado);

        } catch (IOException ex) {
          System.out.println(ex.getMessage());  
        } catch (Exception ex) {
            salidaCliente.println("Error al procesar pago: " + ex.getMessage());
        }
    }
}

