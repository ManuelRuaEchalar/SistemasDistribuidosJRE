package ejercicioenclases2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor que realiza operaciones matemáticas según la solicitud del cliente.
 */
public class Servidor {

    public static void main(String[] args) {
        int port = 5002;
        Operaciones operaciones = new Operaciones();
        
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Se inició el servidor con éxito");

            while (true) {
                try (Socket client = server.accept();
                     PrintStream toClient = new PrintStream(client.getOutputStream());
                     BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                     
                    System.out.println("Cliente se conectó");
                    
                    String operacion = fromClient.readLine();
                    System.out.println("Operación Enviada: " + operacion);

                    String respuesta1;
                    if ("fac".equalsIgnoreCase(operacion) || "fib".equalsIgnoreCase(operacion) || "sum".equalsIgnoreCase(operacion)) {
                        respuesta1 = "ok";
                    } else {
                        respuesta1 = "error operacion no valida";
                    }
                    toClient.println(respuesta1);
                    
                    if ("ok".equals(respuesta1)) {
                        String cadenaN = fromClient.readLine();
                        Integer n;
                        try {
                            n = Integer.parseInt(cadenaN);
                        } catch (NumberFormatException e) {
                            toClient.println("error número no válido");
                            continue;
                        }

                        String resultado;
                        switch (operacion) {
                            case "fac":
                                resultado = operaciones.calcularFactorial(n);
                                break;
                            case "fib":
                                resultado = operaciones.calcularFibonacci(n);
                                break;
                            case "sum":
                                resultado = operaciones.calcularSumatoria(n);
                                break;
                            default:
                                resultado = "error operacion no válida";
                        }
                        toClient.println(resultado);
                    }
                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}