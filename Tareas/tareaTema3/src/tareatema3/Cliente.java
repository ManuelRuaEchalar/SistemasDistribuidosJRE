/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soquettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author jruae
 */
public class Cliente {
    public static void main(String[] args){
   
        int port = 5002;
        try {
            Socket client = new Socket("localhost", port);
            Scanner sc = new Scanner(System.in);
            while (true){
                PrintStream toServer = new PrintStream(client.getOutputStream());
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("Ingrese la operacion fac/fibo/sum");
                String operacion = sc.nextLine();
                toServer.println(operacion);
                System.out.println("Ingrese un número: ");
                String num = sc.nextLine();
                toServer.println(num);
                String result = fromServer.readLine();
                System.out.println(result);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
