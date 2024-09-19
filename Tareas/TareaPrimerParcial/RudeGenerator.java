import java.util.HashMap;
import java.util.Map;

public class RudeGenerator {

    // Función para calcular el RUDE
    private String calcularRUDE(String nombres, String primerApellido, String segundoApellido, String fechaNacimiento) {
        return nombres.substring(0, 2) + primerApellido.substring(0, 2) + segundoApellido.substring(0, 2) + fechaNacimiento.replace("-", "");
    }

    // Función principal para generar la lista de RUDEs
    public Map<String, String> generarListaDeRudes(Map<String, String> personas) {
        Map<String, String> rudes = new HashMap<>();

        for (Map.Entry<String, String> entry : personas.entrySet()) {
            String[] nombresApellidos = entry.getKey().split(",");
            String nombres = nombresApellidos[0];
            String primerApellido = nombresApellidos[1].split(" ")[0]; // Primer apellido
            String segundoApellido = nombresApellidos[1].split(" ")[1]; // Segundo apellido
            String fechaNacimiento = entry.getValue();

            String rude = calcularRUDE(nombres, primerApellido, segundoApellido, fechaNacimiento);
            rudes.put(entry.getKey(), rude);
        }

        return rudes;
    }

    public static void main(String[] args) {
        // Mapa con las personas
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

        // Instancia de RudeGenerator y generación de la lista de RUDEs
        RudeGenerator generator = new RudeGenerator();
        Map<String, String> rudes = generator.generarListaDeRudes(personas);

        // Imprimir los RUDEs generados
        for (Map.Entry<String, String> entry : rudes.entrySet()) {
            System.out.println("Persona: " + entry.getKey() + " - RUDE: " + entry.getValue());
        }
    }
}
