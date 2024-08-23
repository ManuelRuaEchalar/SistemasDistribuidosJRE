package operaciones;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {

        try {
            // Buscar la referencia del objeto remoto
            IOperaciones operacion = (IOperaciones) Naming.lookup("rmi://localhost/Operaciones");
            
            // Puedes ahora usar los métodos remotos, por ejemplo:
            int resultado = operacion.suma(5, 3);
            System.out.println("Resultado de la suma: " + resultado);
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
