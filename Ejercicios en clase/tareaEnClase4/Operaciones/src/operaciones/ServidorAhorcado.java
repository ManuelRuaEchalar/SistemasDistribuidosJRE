package operaciones;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.AlreadyBoundException;
import java.net.MalformedURLException;

public class ServidorAhorcado {
    public static void main(String[] args) {
        try {
            // Crear una instancia de Operacion
            Ahorcado ahorcado = new Ahorcado();
            
            // Levantar el servidor de registro en el puerto 1099
            LocateRegistry.createRegistry(1099);
            
            // Asociar el objeto remoto con un nombre en el registro RMI
            Naming.bind("JuegoAhorcado", ahorcado);
            
            // Mensaje de éxito si todo funciona correctamente
            System.out.println("Servidor RMI listo y registrado con éxito.");
            
        } catch (RemoteException ex) {
            // Manejo de excepciones relacionadas con RMI
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Error de comunicación remota", ex);
        } catch (AlreadyBoundException ex) {
            // Manejo de excepciones si el nombre ya está registrado en el registro RMI
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "El nombre ya está vinculado en el registro RMI", ex);
        } catch (MalformedURLException ex) {
            // Manejo de excepciones si la URL es incorrecta
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "URL mal formada para el registro RMI", ex);
        }
    }
}
