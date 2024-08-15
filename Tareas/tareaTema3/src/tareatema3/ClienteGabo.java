/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareatema3;

/**
 *
 * @author jruae
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteGabo {
    public static void main(String[] args) {
        int port = 5002;
        try {
            Socket client = new Socket("26.2.248.112", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            Scanner fromServer = new Scanner(client.getInputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el primer numero: ");
            String dat1 = sc.nextLine();
            System.out.println("Ingrese el primer numero: ");
            String dat2 = sc.nextLine();
            
            toServer.println(dat1);
            toServer.println(dat2);
            if (fromServer.hasNextLine()) {
                String result = fromServer.nextLine();
                System.out.println("Respuesta del servidor: " + result);
            }
            fromServer.close();
            toServer.close();
            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
