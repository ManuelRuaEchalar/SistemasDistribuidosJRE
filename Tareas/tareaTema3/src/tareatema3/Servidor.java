package tareatema3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            System.out.println("Se inició el servidor con éxito");
            while (true){
            Socket client;
            PrintStream toClient;
            client = server.accept();
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Cliente conectado");
            String operacion = fromClient.readLine();
            String num = fromClient.readLine();
            int n = Integer.parseInt(num);
            Operaciones operador = new Operaciones();
            String mensaje = "";
            switch (operacion){
                case "fac":
                    
                    mensaje = "El Factorial es: " + Operaciones.calcularFactorial(n);
                    break;
                case "fibo":
                    mensaje = "El Fibonacci es: " + Operaciones.calcularFibonacci(n);
                    break;
                case "sum":
                    mensaje = "La Sumatoria es: " + Operaciones.calcularSumatoria(n);
                    break;
                default:
                    mensaje = "DEBES ESCOGER UNA OPERACION VALIDA";
                            
            }
            
            
            toClient = new PrintStream(client.getOutputStream());
            toClient.println(mensaje);
            }
            // Cerrar las conexiones
            //fromClient.close();
            //toClient.close();
            //client.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    // Función para calcular el factorial de un número
    public static int calcularFactorial(int numero) {
        if (numero == 0 || numero == 1) {
            return 1;
        } else {
            return numero * calcularFactorial(numero - 1);
        }
    }
}