package ruatenclase;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

public class RuatServidor {
    public static void main(String[] args) {
        try {
            RuatLista registro = new RuatLista();
            LocateRegistry.createRegistry(1099);
            System.out.println("Servidor de registro RMI iniciado en el puerto 1099...");
            Naming.bind("RuatLista", registro);
            System.out.println("Servidor RuatLista enlazado y listo para aceptar solicitudes...");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            System.err.println("Error en el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
