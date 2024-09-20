/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package microserviciobanco;


// Interfaz RMI para ASFI
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

interface IASFI extends Remote {
    ArrayList<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;
    boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException;
}