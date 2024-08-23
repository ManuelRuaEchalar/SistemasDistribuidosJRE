/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcado;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author DELL
 */
public class Ahorcado extends UnicastRemoteObject implements IAhorcado {

    private String palabraSecreta;
    private StringBuilder estado;
    private int vidasRestantes;

    public Ahorcado() throws RemoteException {
        super();
    }

    @Override
    public boolean Iniciar() {
        palabraSecreta = "ejemplo"; // Aquí puedes añadir lógica para seleccionar una palabra al azar
        estado = new StringBuilder("_".repeat(palabraSecreta.length()));
        vidasRestantes = 6; // Puedes ajustar la cantidad de vidas
        return true;
    }

    @Override
    public Respuesta adivinarLetra(char letra) throws RemoteException {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                estado.setCharAt(i, letra);
                acierto = true;
            }
        }
        if (!acierto) {
            vidasRestantes--;
        }
        return new Respuesta(estado.toString(), vidasRestantes);
    }

    @Override
    public Respuesta adivinarPalabra(String palabra) throws RemoteException {
        if (palabra.equals(palabraSecreta)) {
            return new Respuesta(palabraSecreta, vidasRestantes);
        } else {
            vidasRestantes--;
            return new Respuesta(estado.toString(), vidasRestantes);
        }
    }

}
