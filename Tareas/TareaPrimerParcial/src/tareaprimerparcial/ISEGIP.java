/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tareaprimerparcial;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISEGIP extends Remote {
    Respuesta verificarDatos(String CI, String nombres, String apellidos) throws RemoteException;
}