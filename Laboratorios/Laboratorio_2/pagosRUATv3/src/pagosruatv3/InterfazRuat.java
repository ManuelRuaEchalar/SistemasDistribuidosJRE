/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pagosruatv3;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazRuat extends Remote {
    Deuda[] buscar(String ci) throws RemoteException;
    boolean pagar(Deuda deuda) throws RemoteException;
}

