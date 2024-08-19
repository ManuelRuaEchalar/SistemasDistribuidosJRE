/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioenclases2;
import java.math.BigInteger;
public class Operaciones {
    public static String calcularFactorial(int _n) {
        if (_n <= 0) throw new IllegalArgumentException("No existe factorial de un número negativo");
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= _n; i++) factorial = factorial.multiply(BigInteger.valueOf(i));
        String resultado = factorial.toString();
        return resultado;
    }

    public static String calcularFibonacci(int _n) {
        if (_n <= 0) throw new IllegalArgumentException("No existe fibonacci de un número negativo");
        long a = 0;
        long b = 1;
        for (int i = 2; i <= _n; i++) {
            long t = b;
            b = a + b;
            a = t;
        }
        String resultado = Long.toString(b);
        
        return resultado;
    }
    
    public static String calcularSumatoria (int n){
        long total =0;
        for (int i=1; i<=n;i++){
            total+=i;
        }
        String resultado = Long.toString(total);
        return resultado;
    }
}