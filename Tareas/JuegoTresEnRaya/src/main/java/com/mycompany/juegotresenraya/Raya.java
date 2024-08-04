/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juegotresenraya;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author jruae
 */
public class Raya {
    String[][] matriz = new String[3][3];
    ArrayList<Integer> disponibles = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    int n=8;
    boolean endGame = false;
    int turno = 0;
    int jugador;
    String ficha;
    Scanner scanner = new Scanner(System.in);
    
    void juego() {
    
        while (!endGame){
            
            if (turno == 0){
                ficha = "x";
                jugador = 1;
            } else {
                
                ficha = "o";
                jugador = 2;
            }
        
            System.out.print("Jugador " + jugador + ", es tu turno, selecciona una posicion:");
            for (int posicion : disponibles){
            
                System.out.print(posicion + ".  ");
                
            }
            System.out.print("\n");
            System.out.print("Ingresa una posicion valida por teclado: ");
            System.out.print("\n");
            int posicion = scanner.nextInt();
            for (int i = 0;i<=n;i++){
            
                if (disponibles.get(i)== posicion){
                
                    n-=1;
                    disponibles.remove(i);
                }
            }
            int counter = 0;
            int fil=100;
            int col=100;
            for (int i=0; i<=2;i++){
                
                for (int j=0; j<=2;j++){
                
                    counter += 1;
                    if (counter==posicion){
                        matriz[i][j] = ficha;
                        fil=i;
                        col=j;
                    }
                }
                    
            }
            
            this.imprimirTablero();
            System.out.print("\n");
            
            if(matriz[0][col]== matriz[1][col] && matriz[2][col]==matriz[1][col] && matriz[1][col]==ficha){
            
                System.out.print("Jugador " + jugador + " gana!");
                System.out.print("\n");
                endGame = true; 
            } else if(matriz[fil][0]== matriz[fil][1] && matriz[fil][2]==matriz[fil][1] && matriz[1][fil]==ficha){
            
                System.out.print("Jugador " + jugador + " gana!");
                System.out.print("\n");
                endGame = true; 
                
            } else if(fil==col){
            
                if(matriz[1][1] == matriz[2][2] && matriz[2][2] == matriz[0][0] && matriz[fil][col]==ficha){
                    System.out.print("Jugador " + jugador + " gana!");
                    System.out.print("\n");
                    endGame = true;
                }
            }
            
            if (turno==0){
            
                turno=1;
            } else {
            
                turno=0;
            }
            
            
        }
    }
    
    void imprimirTablero(){
    
        for (int i=0;i<=2;i++){
        
            for (int j=0;j<=2;j++){
            
                System.out.print("[");
                System.out.print(" ");
                if (matriz[i][j]!= null){
                
                    System.out.print(matriz[i][j]);
                }
                System.out.print(" ");
                System.out.print("]");
            }
            
            System.out.print("\n");
        }
    }
}
