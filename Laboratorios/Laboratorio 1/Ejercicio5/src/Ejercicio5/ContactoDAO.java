/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jruae
 */
public class ContactoDAO {
    private Connection conexion;

    public ContactoDAO(String url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertar(Contacto contacto) {
        String sql = "INSERT INTO contacto(nombre,numero) VALUES (?,?) ";

        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, contacto.getNombre());
            instruccion.setInt(2, contacto.getNumero());

            instruccion.execute();

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void eliminarContacto(int numero) {
        String sql = "DELETE FROM contacto WHERE numero = ?";

        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setInt(1, numero);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Contacto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró una persona con ese número de telefono.");
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void buscarContacto(String nombre) {
    
        String sql = "SELECT * FROM contacto WHERE nombre = ?";

    try {
        PreparedStatement instruccion = conexion.prepareStatement(sql);
        instruccion.setString(1, nombre);

        ResultSet resultado = instruccion.executeQuery();
        if (resultado.next()) {
            // Crear objeto Persona con los datos obtenidos
            Contacto persona = new Contacto(
                    resultado.getString("nombre"),
                    resultado.getInt("numero")
            );
            System.out.println(persona);
        } else {
            System.out.println("No se encontró una persona con ese número de telefono.");
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public List<Contacto> listar() {
        List<Contacto> auxiliar = new ArrayList<Contacto>();

        String sql = "SELECT * FROM contacto";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                Contacto p = new Contacto(resultado.getString("nombre"), resultado.getInt("numero"));
                auxiliar.add(p);
            }

            
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auxiliar;
    }
}
