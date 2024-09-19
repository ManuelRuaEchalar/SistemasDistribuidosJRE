package tareaprimerparcial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SERECIServer {
    public static void main(String[] args) {
        // Mapa que contiene los nombres de las personas y sus fechas de nacimiento
        Map<String, String> personas = new HashMap<>();
        personas.put("María Fernanda,Lopez Martinez", "22-05-1993");
        personas.put("Carlos Eduardo,Morales Quispe", "17-08-1985");
        personas.put("Ana Sofía,Pérez Gómez", "03-11-1990");
        personas.put("Luis Alberto,Rodríguez Aguilar", "10-12-1998");
        personas.put("Gabriela,Martínez Soto", "25-04-2001");
        personas.put("José Luis,Herrera Ruiz", "19-09-1995");
        personas.put("Patricia,Jiménez Castro", "07-03-1992");
        personas.put("Ricardo,Salazar Vega", "30-06-1988");
        personas.put("Laura,Castillo Muñoz", "12-10-1997");
        personas.put("Miguel Ángel,Ramírez Flores", "05-01-1980");
        personas.put("Juan Manuel,Rua Echalar", "13-07-2002");

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor SERECI TCP iniciado");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                String request = new String(buffer, 0, bytesRead);
                
                // Verificar el formato esperado "Ver-fecha:Nombre,Apellido,Fecha"
                if (request.startsWith("Ver-fecha:")) {
                    String[] partes = request.substring(10).split(","); // Dividir el string después de "Ver-fecha:"
                    if (partes.length == 3) {
                        String nombreCompleto = partes[0] + "," + partes[1];
                        String fechaNacimiento = partes[2];
                        
                        // Verificar si el nombre y la fecha coinciden en el mapa
                        if (personas.containsKey(nombreCompleto) && personas.get(nombreCompleto).equals(fechaNacimiento)) {
                            String response = "si:verificación correcta";
                            out.write(response.getBytes());
                        } else {
                            String response = "no:error fecha o nombre no coinciden";
                            out.write(response.getBytes());
                        }
                    } else {
                        String response = "no:formato incorrecto, debe ser 'Ver-fecha:Nombre,Apellido,Fecha'";
                        out.write(response.getBytes());
                    }
                } else {
                    String response = "no:comando no reconocido";
                    out.write(response.getBytes());
                }
                
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor SERECI: " + e.getMessage());
        }
    }
}
