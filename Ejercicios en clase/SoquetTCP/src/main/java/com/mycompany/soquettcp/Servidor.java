/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soquettcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jruae
 */
public class Servidor {
    
    public static void main(String[] args){
    
        int port = 502;
        ServerSocket server;
        
        try {
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept();
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Cliente conectado");
            String recibido = fromClient.readLine();
            System.out.println("El cliente envio el mensaje: " + recibido);
            System.out.println(recibido);
            toClient = new PrintStream(client.getOutputStream());
            toClient.println("Hola Mundo desde el servidor");
            
        } catch (IOException ex){
            System.out.print(ex.getMessage());
            
            
            
        }
    }
    
}
