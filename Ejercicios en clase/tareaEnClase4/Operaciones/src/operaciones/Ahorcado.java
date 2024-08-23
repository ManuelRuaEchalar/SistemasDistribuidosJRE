/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operaciones;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
//OJO, mandarlo a un nuevo proyecto para el juego de ahorcado
/**
 *
 * @author eantoniocf
 */
public class Ahorcado extends UnicastRemoteObject implements IAhorcado {

    public Ahorcado () throws RemoteException{
        super();
    }    
    @Override
    public boolean Iniciar() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Respuesta adivinarLetra(char letra) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Respuesta adivinarPalabra(String palabra) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
