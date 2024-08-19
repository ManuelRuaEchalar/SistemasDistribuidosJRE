package ejercicioenclases2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente para interactuar con el servidor TCP.
 */
public class Cliente {

    public static void main(String[] args) {
        int port = 5002;
        Scanner sc = new Scanner(System.in);

        try {
            Socket client = new Socket("localhost", port);

            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));

            while (true) {
                // Mostrar el menú
                System.out.println("Seleccione una opción:");
                System.out.println("1) Calcular factorial");
                System.out.println("2) Calcular Fibonacci");
                System.out.println("3) Calcular sumatoria");
                System.out.println("4) Salir");
                System.out.print("Ingrese su opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();  // Limpiar el buffer

                if (opcion == 4) {
                    System.out.println("Saliendo...");
                    break;
                }

                String operacion;
                switch (opcion) {
                    case 1:
                        operacion = "fac";
                        break;
                    case 2:
                        operacion = "fib";
                        break;
                    case 3:
                        operacion = "sum";
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                        continue;
                }

                // Enviar la operación al servidor
                toServer.println(operacion);

                // Leer la respuesta del servidor
                String result = fromServer.readLine();
                if ("ok".equals(result)) {
                    System.out.print("Introduzca un número: ");
                    int n = sc.nextInt();
                    sc.nextLine();  // Limpiar el buffer

                    // Enviar el número al servidor
                    toServer.println(String.valueOf(n));

                    // Leer el resultado del servidor
                    String result2 = fromServer.readLine();
                    System.out.println("Resultado devuelto por el servidor: " + result2);
                } else {
                    System.out.println("Error en la operación.");
                }
            }

            client.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}