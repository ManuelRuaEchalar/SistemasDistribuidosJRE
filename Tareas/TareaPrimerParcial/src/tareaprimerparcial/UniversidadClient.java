package tareaprimerparcial;

import java.rmi.Naming;
import java.util.Scanner;

public class UniversidadClient {
    public static void main(String[] args) {
        try {
            IUniversidad universidad = (IUniversidad) Naming.lookup("rmi://localhost/Universidad");
            
            Scanner scanner = new Scanner(System.in);
            int opcion;
            
            do {
                // Mostrar el menú
                System.out.println("CLIENTE UNIVERSIDAD USFX");
                System.out.println("1. Emitir Diploma");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        // Solicitar datos al usuario
                        System.out.print("Ingrese el número de matrícula: ");
                        String matricula = scanner.nextLine();
                        
                        System.out.print("Ingrese su nombre completo (primero y segundo si lo hay): ");
                        String primerNombre = scanner.nextLine();
                        
                        System.out.print("Ingrese el primer apellido: ");
                        String primerApellido = scanner.nextLine();
                        
                        System.out.print("Ingrese el segundo apellido: ");
                        String segundoApellido = scanner.nextLine();
                        
                        System.out.print("Ingrese la fecha de nacimiento (dd-mm-aaaa): ");
                        String fechaNacimiento = scanner.nextLine();
                        
                        System.out.print("Ingrese el título otorgado: ");
                        String titulo = scanner.nextLine();
                        
                        // Enviar datos al servidor
                        Diploma diploma = universidad.emitirDiploma(matricula, primerNombre, primerApellido, segundoApellido, fechaNacimiento, titulo);
                        System.out.println("Diploma emitido:");
                        System.out.println(diploma);
                        break;
                    
                    case 2:
                        System.out.println("Saliendo...");
                        break;
                    
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 2);
            
            scanner.close(); // Cerrar el scanner al finalizar
        } catch (Exception e) {
            System.err.println("Error en el cliente Universidad: " + e.getMessage());
        }
    }
}
