package pagosruatv3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class UsuarioClienteTCP {
    public static void main(String[] args) {
        try (
            Socket clienteSocket = new Socket("localhost", 5000);
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintStream salidaServidor = new PrintStream(clienteSocket.getOutputStream());
            BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                System.out.println("Menu:");
                System.out.println("1) Ver deudas");
                System.out.println("2) Pagar deudas");
                System.out.println("3) Salir");
                System.out.print("Seleccione una opcion: ");
                String opcion = entradaUsuario.readLine();

                if (opcion.equals("1")) {
                    System.out.print("Ingrese su CI: ");
                    String ci = entradaUsuario.readLine();
                    salidaServidor.println("Deuda:" + ci);

                    String respuestaDeudas = entradaServidor.readLine();
                    System.out.println("\n Deudas encontradas: " + respuestaDeudas);

                } else if (opcion.equals("2")) {
                    System.out.print("Ingrese su CI: ");
                    String ci = entradaUsuario.readLine();

                    System.out.print("Ingrese el año de la deuda a pagar: ");
                    int anio = Integer.parseInt(entradaUsuario.readLine());

                    System.out.print("Ingrese el tipo de impuesto (Vehículo/Casa): ");
                    String tipoImpuesto = entradaUsuario.readLine();

                    System.out.print("Ingrese el monto a pagar: ");
                    double monto = Double.parseDouble(entradaUsuario.readLine());

                    salidaServidor.println("Pagar:" + ci + "," + anio + "," + tipoImpuesto + "," + monto);
                    String resultado = entradaServidor.readLine();
                    System.out.println("Resultado de la transacción: \n" + resultado);

                } else if (opcion.equals("3")) {
                    System.out.println("Saliendo del programa...");
                    break;
                } else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
