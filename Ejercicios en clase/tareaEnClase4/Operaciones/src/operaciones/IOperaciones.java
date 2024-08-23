/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package operaciones;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author eantoniocf
 */
public interface IOperaciones extends Remote {
   int suma(int a, int b) throws RemoteException;
    int resta(int a, int b) throws RemoteException;
    int multiplicacion(int a, int b) throws RemoteException;
    int division(int a, int b) throws RemoteException;
    
    
    
}

