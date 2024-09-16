/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
/**
 *
 * @author eantoniocf
 */
public class RuatServidorRMI {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        RegistroDeudas registro = new RegistroDeudas();
        LocateRegistry.createRegistry(6001); // Levantar el servidor de registro
        Naming.bind("RegistroDeDeudas", registro); // Registrar el objeto remoto
    }
}
