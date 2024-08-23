/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//OJO, mandarlo a un nuevo proyecto para el juego de ahorcado
package operaciones;

import java.io.Serializable;

/**
 *
 * @author eantoniocf
 */
public class Respuesta implements Serializable {
    String estado; //cadena de guiones y letras adivinadas hasta ahora Ejemplo: Gato -->  _a__
    int numeroVidas;

    public Respuesta(String estado, int numeroVidas) {
        this.estado = estado;
        this.numeroVidas = numeroVidas;
    }
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroVidas() {
        return numeroVidas;
    }

    public void setNumeroVidas(int numeroVidas) {
        this.numeroVidas = numeroVidas;
    }
    
    
    
}
