/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

/**
 *
 * @author eantoniocf
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRegistroDeudas extends Remote {
    
    public ArrayList<Deuda> buscarDeudas(String ci) throws RemoteException;;
    public Boolean pagarDeuda(Deuda deuda) throws RemoteException;
    
}
