/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microserviciobanco;

// Clase Cuenta
class Cuenta implements java.io.Serializable {
    private Banco banco;
    private String nroCuenta;
    private String ci;
    private String nombres;
    private String apellidos;
    private double saldo;

    // Constructor
    public Cuenta(Banco banco, String nroCuenta, String ci, String nombres, String apellidos, double saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    // Getters y setters
    public Banco getBanco() { return banco; }
    public void setBanco(Banco banco) { this.banco = banco; }
    public String getNroCuenta() { return nroCuenta; }
    public void setNroCuenta(String nroCuenta) { this.nroCuenta = nroCuenta; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return "Banco" + banco +": " + nroCuenta + "-" + saldo;
    }
}
