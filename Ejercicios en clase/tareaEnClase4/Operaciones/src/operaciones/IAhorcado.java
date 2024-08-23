/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

//OJO, mandarlo a un nuevo proyecto para el juego de ahorcado
package operaciones;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author eantoniocf
 */
public interface IAhorcado extends Remote{
    public boolean Iniciar() throws RemoteException;
    public Respuesta adivinarLetra(char letra) throws RemoteException;
    public Respuesta adivinarPalabra(String palabra) throws RemoteException;
    
}
