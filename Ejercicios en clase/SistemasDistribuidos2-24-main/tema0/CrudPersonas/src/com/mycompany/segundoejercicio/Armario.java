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
public class Armario {
    String material;
    int codigo;
    int cantidad;
    ArrayList<Libro> libros = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/bd_biblio"; // Cambia "nombre_base_de_datos"
    String user = "root"; // Cambia "tu_usuario"
    String password = ""; // Cambia "tu_contraseña"

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public Armario(String material, int codigo) {
        this.material = material;
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }
    
    public void agregarLibro() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce el nombre del libro: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Introduce el autor del libro: ");
        String autor = scanner.nextLine();
        
        System.out.print("Introduce la editorial del libro: ");
        String editorial = scanner.nextLine();
        
        System.out.print("Introduce el año de publicación del libro: ");
        int anio = scanner.nextInt();
        
        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO libros (titulo, autor, editorial, anio, armario_id) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, autor);
            pstmt.setString(3, editorial);
            pstmt.setInt(4, anio);
            pstmt.setInt(5, this.codigo);

            pstmt.executeUpdate();
            System.out.println("Libro agregado exitosamente a la base de datos.");

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
        
        libros.add(new Libro(nombre, autor, editorial, anio));
        cantidad+=1;
        System.out.print("Libro añadido exitosamente!");
        System.out.print(" ");
    }
    
    public void mostrarLibros(){
    
        System.out.print("El armario cuenta con los siguientes libros: ");
        System.out.print("\n");
        for (Libro libro : libros) {
            System.out.print("Titulo: " + libro.getNombre() + ", autor: "+ libro.getAutor());
            System.out.print("\n");
        }
    }
    
    public void cargarLibros() {
    
        try {
                        // Establecer la conexión
                        conn = DriverManager.getConnection(url, user, password);
                        System.out.println("Conexión exitosa!");

                        // Crear un Statement
                        stmt = conn.createStatement();

                        // Ejecutar una consulta
                        String sql = "SELECT * FROM libros WHERE armario_id = " + this.codigo;
                        rs = stmt.executeQuery(sql);

                        // Procesar los resultados
                        while (rs.next()) {
                            libros.add(new Libro( rs.getString("titulo") , rs.getString("autor"), rs.getString("editorial") , rs.getInt("anio")));
                            cantidad += 1;
                            
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

