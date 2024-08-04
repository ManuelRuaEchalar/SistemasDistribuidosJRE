/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.segundoejercicio;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author jruae
 */
public class Biblioteca {
    String nombre;
    float tamaño;
    ArrayList<Armario> armarios = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/bd_biblio"; // Cambia "nombre_base_de_datos"
    String user = "root"; // Cambia "tu_usuario"
    String password = ""; // Cambia "tu_contraseña"

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public Biblioteca(String nombre, float tamaño) {
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTamaño() {
        return tamaño;
    }

    public ArrayList<Armario> getArmarios() {
        return armarios;
    }
    
    public void agregarArmario(){
    
        System.out.print("Introduce el material del armario (madera/metal): ");
        String material = scanner.nextLine().toLowerCase();

        if (!material.equals("madera") && !material.equals("metal")) {
            System.out.println("Material no válido. Debe ser 'madera' o 'metal'.");
        }

        System.out.print("Introduce el código del armario: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        for (Armario armario : armarios) {
            if (armario.getCodigo() == codigo) {
                System.out.println("El código ya existe. Debe ser único.");
            }
        }
        
        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO armario (id, material) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.setString(2, material);

            pstmt.executeUpdate();
            System.out.println("Armario agregado exitosamente a la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        armarios.add(new Armario(material, codigo));
    }
    
    public void mostrarArmarios(){
        System.out.println("Lista armarios: ");
    
        for (Armario armario : armarios){
        
            System.out.println("codigo armario: " + armario.getCodigo() + ", libros: " + armario.cantidad);
        }
    }
    
    public void cargarArmarios() {
    
        try {
                        // Establecer la conexión
                        conn = DriverManager.getConnection(url, user, password);
                        System.out.println("Conexión exitosa!");

                        // Crear un Statement
                        stmt = conn.createStatement();

                        // Ejecutar una consulta
                        String sql = "SELECT * from armario"; // Cambia "tu_tabla"
                        rs = stmt.executeQuery(sql);

                        // Procesar los resultados
                        while (rs.next()) {
                            Armario armario = new Armario( rs.getString("material") , rs.getInt("id"));
                            armario.cargarLibros();
                            armarios.add(armario);
                            
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        // Cerrar recursos
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                            if (stmt != null) {
                                stmt.close();
                            }
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
    }
}
