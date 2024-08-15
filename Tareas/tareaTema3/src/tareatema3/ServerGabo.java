/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareatema3;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.math.BigInteger;

public class ServerGabo {
    public static void main(String[] args) {
        int port = 5002;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Se inició el servidor");
            while (true) {
                try (Socket client = server.accept()) {
                    System.out.println("Cliente conectado");
                    Scanner fromClient = new Scanner(client.getInputStream());
                    PrintStream toClient = new PrintStream(client.getOutputStream());
                    String num1 = fromClient.nextLine();
                    System.out.println("Primer dato recibido del cliente: " + num1);
                    String num2 = fromClient.nextLine();
                    System.out.println("Segundo dato recibido del cliente: " + num2);
                    int value1 = Integer.parseInt(num1);
                    int value2 = Integer.parseInt(num2);
                    BigInteger result1 = Operaciones.calcularFactorial(value1);
                    long result2 = Operaciones.calcularFibonacci(value2);
                    String result = "El factorial es :" + result1.toString() + " El fibonacci es: " + Long.toString(result2);
                    System.out.println("Resultado del cálculo: " + result);
                    toClient.println("Resultado: " + result);
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
}