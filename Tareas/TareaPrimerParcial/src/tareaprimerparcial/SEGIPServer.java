package tareaprimerparcial;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class SEGIPServer extends UnicastRemoteObject implements ISEGIP {

    // Mapa que contiene los CI, y su respectivo nombre y apellidos
    private Map<String, String> personas;

    protected SEGIPServer() throws RemoteException {
        super();
        // Inicializar el mapa con los datos
        personas = new HashMap<>();
        personas.put("1140506", "Walter Jhamil,Segovia Arellano");
        personas.put("1189506", "María Fernanda,Lopez Martinez");
        personas.put("1189507", "Carlos Eduardo,Morales Quispe");
        personas.put("1189508", "Ana Sofía,Pérez Gómez");
        personas.put("1189509", "Luis Alberto,Rodríguez Aguilar");
        personas.put("1189510", "Gabriela,Martínez Soto");
        personas.put("1189511", "José Luis,Herrera Ruiz");
        personas.put("1189512", "Patricia,Jiménez Castro");
        personas.put("1189513", "Ricardo,Salazar Vega");
        personas.put("1189514", "Laura,Castillo Muñoz");
        personas.put("1189515", "Miguel Ángel,Ramírez Flores");
        personas.put("10321389", "Juan Manuel,Rua Echalar");
    }

    @Override
    public Respuesta verificarDatos(String CI, String nombres, String apellidos) throws RemoteException {
        // Buscar si el CI existe en el mapa
        if (personas.containsKey(CI)) {
            // Obtener el valor asociado al CI
            String datosAlmacenados = personas.get(CI);
            String datosIngresados = nombres + "," + apellidos;

            // Verificar si los nombres y apellidos coinciden con los almacenados
            if (datosAlmacenados.equals(datosIngresados)) {
                return new Respuesta(true, "Los Datos son correctos");
            } else {
                return new Respuesta(false, "Los nombres o apellidos no coinciden");
            }
        } else {
            return new Respuesta(false, "El CI no existe");
        }
    }

    public static void main(String[] args) {
        try {
            SEGIPServer server = new SEGIPServer();

            // Intenta obtener el registro existente, si falla, crea uno nuevo
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(1099);
                registry.list(); // Esta llamada lanzará una excepción si el registro no existe
            } catch (RemoteException e) {
                System.out.println("Registro no encontrado, creando uno nuevo");
                registry = LocateRegistry.createRegistry(1099);
            }

            registry.rebind("SEGIP", server);
            System.out.println("Servidor SEGIP RMI iniciado");
        } catch (RemoteException e) {
            System.err.println("Error de comunicación remota: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
