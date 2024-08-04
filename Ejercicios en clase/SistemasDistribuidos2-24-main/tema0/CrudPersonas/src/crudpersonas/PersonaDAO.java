/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudpersonas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class PersonaDAO {

    private Connection conexion;

    public PersonaDAO(String url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertar(Persona persona) {
        String sql = "INSERT INTO personas(nombre,apellidos,edad,nro_carnet) VALUES (?,?,?,?) ";

        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, persona.getNombre());
            instruccion.setString(2, persona.getApellidos());
            instruccion.setInt(3, persona.getEdad());
            instruccion.setString(4, persona.getNumerocarnet());

            instruccion.execute();

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void eliminarPersona(String nroCarnet) {
        String sql = "DELETE FROM personas WHERE nro_carnet = ?";

        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, nroCarnet);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona eliminada exitosamente.");
            } else {
                System.out.println("No se encontró una persona con ese número de carnet.");
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void buscarPersona(String nroCarnet) {
    
        String sql = "SELECT * FROM personas WHERE nro_carnet = ?";

    try {
        PreparedStatement instruccion = conexion.prepareStatement(sql);
        instruccion.setString(1, nroCarnet);

        ResultSet resultado = instruccion.executeQuery();
        if (resultado.next()) {
            // Crear objeto Persona con los datos obtenidos
            Persona persona = new Persona(
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("apellidos"),
                    resultado.getInt("edad"),
                    resultado.getString("nro_carnet")
            );
            System.out.println(persona);
        } else {
            System.out.println("No se encontró una persona con ese número de carnet.");
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void editarPersona(String nroCarnet, String nuevoNombre, String nuevosApellidos, int nuevaEdad, String nuevoNroCarnet) {
        String sql = "UPDATE personas SET nombre = ?, apellidos = ?, edad = ?, nro_carnet = ? WHERE nro_carnet = ?";

        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, nuevoNombre);
            instruccion.setString(2, nuevosApellidos);
            instruccion.setInt(3, nuevaEdad);
            instruccion.setString(4, nuevoNroCarnet);
            instruccion.setString(5, nroCarnet);

            int filasAfectadas = instruccion.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Persona actualizada exitosamente.");
            } else {
                System.out.println("No se encontró una persona con ese número de carnet.");
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Persona> listar() {
        List<Persona> auxiliar = new ArrayList<Persona>();

        String sql = "SELECT * FROM personas";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                Persona p = new Persona(resultado.getInt("id"), resultado.getString("nombre"),
                        resultado.getString("apellidos"), resultado.getInt("edad"), resultado.getString("nro_carnet"));
                auxiliar.add(p);
            }

            
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auxiliar;
    }
    
}
