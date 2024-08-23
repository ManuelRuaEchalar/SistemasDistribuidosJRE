package ahorcado;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Buscar el objeto remoto en el registro RMI
            IAhorcado ahorcado = (IAhorcado) Naming.lookup("rmi://localhost/JuegoAhorcado");

            // Crear un objeto Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Iniciar el juego
            boolean inicioExitoso = ahorcado.Iniciar();
            if (inicioExitoso) {
                System.out.println("Juego iniciado exitosamente.");
            } else {
                System.out.println("No se pudo iniciar el juego.");
                return;
            }

            // Interactuar con el juego: adivinar letras y palabras
            while (true) {
                System.out.println("Ingrese una letra o una palabra (o 'salir' para terminar):");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("salir")) {
                    break;
                }

                // Si el input tiene una longitud de 1, se considera una letra
                if (input.length() == 1) {
                    char letra = input.charAt(0);
                    Respuesta respuesta = ahorcado.adivinarLetra(letra);
                    System.out.println("Estado actual: " + respuesta.getEstado());
                    System.out.println("Número de vidas restantes: " + respuesta.getNumerovidas());
                } else {
                    // De lo contrario, se considera una palabra
                    Respuesta respuesta = ahorcado.adivinarPalabra(input);
                    System.out.println("Estado actual: " + respuesta.getEstado());
                    System.out.println("Número de vidas restantes: " + respuesta.getNumerovidas());
                }
            }

            // Cerrar el escáner
            scanner.close();

        } catch (RemoteException e) {
            System.err.println("Error de comunicación remota: " + e.getMessage());
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("El objeto remoto no está vinculado: " + e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.err.println("URL mal formada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
