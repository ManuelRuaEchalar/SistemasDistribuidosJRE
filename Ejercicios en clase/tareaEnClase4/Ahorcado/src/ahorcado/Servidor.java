/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;



/**
 *
 * @author Carlos
 */
public class Servidor {
    
    public static void main(String[] args) {
    try {
        Ahorcado ahorcado = new Ahorcado();
        
        try {
            LocateRegistry.createRegistry(1099); // Intentar crear el registro
            System.out.println("Registro RMI creado en el puerto 1099");
        } catch (RemoteException e) {
            System.out.println("El registro RMI ya está corriendo en el puerto 1099");
        }
        
        Naming.bind("JuegoAhorcado", ahorcado);
        System.out.println("Servidor de Juego Ahorcado listo y registrado.");
        
    } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
        e.printStackTrace();
    }
}

    
    
}
