import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente_TCP {

    public static void main(String[] args) {
        int port = 5002;
        Scanner sc = new Scanner(System.in);
        try {           
            Socket client = new Socket("localhost", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while (true) {
                // Menú de operaciones
                System.out.println("Seleccione una operación:");
                System.out.println("1. Consultar Deuda");
                System.out.println("2. Pagar Deuda");
                System.out.println("3. Salir");

                int opcion = sc.nextInt();
                sc.nextLine(); 

                switch (opcion) {
                    case 1:
                        System.out.print("Introduzca su número de cédula de identidad (ci): ");
                        String ciConsulta = sc.nextLine();
                        String consultaDeuda = "Deuda:" + ciConsulta;
                        toServer.println(consultaDeuda);
                        
                        // Esperar la respuesta del servidor
                        String respuestaDeuda;
                        while ((respuestaDeuda = fromServer.readLine()) == null) {
                            // Espera hasta que se reciba la respuesta
                        }
                        System.out.println("Deudas encontradas: " + respuestaDeuda);
                        break;

                    case 2:
                        System.out.print("Introduzca su número de cédula de identidad (ci): ");
                        String ciPago = sc.nextLine();
                        System.out.print("Introduzca el año de la deuda: ");
                        String año = sc.nextLine();
                        System.out.print("Introduzca el tipo de impuesto: ");
                        String impuesto = sc.nextLine();
                        System.out.print("Introduzca el monto a pagar: ");
                        String monto = sc.nextLine();
                        
                        String pagarDeuda = "Pagar:" + ciPago + "," + año + "," + impuesto + "," + monto;
                        toServer.println(pagarDeuda);
                        
                        // Esperar la respuesta del servidor
                        String respuestaPago;
                        while ((respuestaPago = fromServer.readLine()) == null) {
                            // Espera hasta que se reciba la respuesta
                        }
                        System.out.println("Resultado de la transacción: " + respuestaPago);
                        break;

                    case 3:
                        // Salir del programa
                        System.out.println("Saliendo...");
                        client.close(); 
                        return; 

                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        continue; 
                }
            }
        } catch (IOException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
        }
    }
}
