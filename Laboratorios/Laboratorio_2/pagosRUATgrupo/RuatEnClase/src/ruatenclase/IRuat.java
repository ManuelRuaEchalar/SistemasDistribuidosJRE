package ruatenclase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRuat extends Remote {
    Deuda[] buscar(String ci) throws RemoteException;
    boolean pagar(Deuda deuda) throws RemoteException;
    String manejarDeuda(String ci) throws RemoteException; // Método añadido anteriormente
    String manejarPago(String ci, int anio, String impuesto, double monto) throws RemoteException; // Nuevo método agregado
}
