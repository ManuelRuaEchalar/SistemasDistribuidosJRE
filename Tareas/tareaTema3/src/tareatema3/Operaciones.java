/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareatema3;
import java.math.BigInteger;
public class Operaciones {
    public static BigInteger calcularFactorial(int _n) {
        if (_n <= 0) throw new IllegalArgumentException("No existe factorial de un número negativo");
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= _n; i++) factorial = factorial.multiply(BigInteger.valueOf(i));
        return factorial;
    }

    public static long calcularFibonacci(int _n) {
        if (_n <= 0) throw new IllegalArgumentException("No existe fibonacci de un número negativo");
        long a = 0;
        long b = 1;
        for (int i = 2; i <= _n; i++) {
            long t = b;
            b = a + b;
            a = t;
        }
        return b;
    }
}