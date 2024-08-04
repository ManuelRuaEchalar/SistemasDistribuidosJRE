/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tresenraya;

/**
 *
 * @author jruae
 */
public class TresEnRaya {
    public static void main(String[] args) {
        // Inicializar el tablero de tres en raya vacío
        char[][] tablero = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        // Imprimir la plantilla del tablero
        imprimirTablero(tablero);
    }

    public static void imprimirTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
                if (j < tablero[i].length - 1) {
                    System.out.print(" | ");
                }
            }
        }
    }
}

