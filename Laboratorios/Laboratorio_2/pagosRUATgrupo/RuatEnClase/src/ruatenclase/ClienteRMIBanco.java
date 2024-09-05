package ruatenclase;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteRMIBanco extends UnicastRemoteObject {

    private static final long serialVersionUID = 1L;

    protected ClienteRMIBanco() throws RemoteException {
        super();
    }

    public String manejarDeuda(String ci) throws MalformedURLException {
        try {
            IRuat rmi = (IRuat) Naming.lookup("rmi://localhost/RuatLista");
            return rmi.manejarDeuda(ci);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            return "Error al manejar deuda: " + e.getMessage();
        }
    }

    public String manejarPago(String ci, int anio, String impuesto, double monto) {
        try {
            IRuat rmi = (IRuat) Naming.lookup("rmi://localhost/RuatLista");
            return rmi.manejarPago(ci, anio, impuesto, monto);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            return "Error al manejar pago: " + e.getMessage();
        }
    }
}
