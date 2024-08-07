/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jruae
 */
public class LaptopDAO {
    
    private Connection conexion;

    public LaptopDAO(String url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(Laptop laptop) {
    String productoSQL = "INSERT INTO Producto (nombre, marca, modelo, tipo, descripcion, stock, precio, descuento, precioFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement instruccionProducto = conexion.prepareStatement(productoSQL, Statement.RETURN_GENERATED_KEYS);
        instruccionProducto.setString(1, laptop.getNombre());
        instruccionProducto.setString(2, laptop.getMarca());
        instruccionProducto.setString(3, laptop.getModelo());
        instruccionProducto.setString(4, laptop.getTipo());
        instruccionProducto.setString(5, laptop.getDescripcion());
        instruccionProducto.setInt(6, laptop.getStock());
        instruccionProducto.setInt(7, laptop.getPrecio());
        instruccionProducto.setDouble(8, laptop.getDescuento());
        instruccionProducto.setDouble(9, laptop.getPrecioFinal());

        int filasAfectadas = instruccionProducto.executeUpdate(); // Ejecutar la inserción en Producto

        if (filasAfectadas > 0) {

            ResultSet generatedKeys = instruccionProducto.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productoId = generatedKeys.getInt(1); // Obtener el ID generado

                
                String laptopSQL = "INSERT INTO Laptop (producto_id, memoriaRam, procesador) VALUES (?, ?, ?)";
                PreparedStatement instruccionLaptop = conexion.prepareStatement(laptopSQL);
                instruccionLaptop.setInt(1, productoId); 
                instruccionLaptop.setInt(2, laptop.getMemoriaRam());
                instruccionLaptop.setString(3, laptop.getProcesador());

                instruccionLaptop.executeUpdate(); 
                System.out.println("Laptop agregada exitosamente.");
            }
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void eliminarLaptop(int id) {
    
    String selectSQL = "SELECT stock FROM Producto WHERE id = ?";
    int stockActual = 0;

    try {
        PreparedStatement selectStmt = conexion.prepareStatement(selectSQL);
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
            stockActual = rs.getInt("stock");
        }

        
        stockActual -= 1;

        
        if (stockActual > 0) {
            
            String updateSQL = "UPDATE Producto SET stock = ? WHERE id = ?";
            PreparedStatement updateStmt = conexion.prepareStatement(updateSQL);
            updateStmt.setInt(1, stockActual);
            updateStmt.setInt(2, id);
            updateStmt.executeUpdate();
            System.out.println("Stock actualizado exitosamente.");
        } else {
            
            String deleteLaptopSQL = "DELETE FROM Laptop WHERE producto_id = ?";
            PreparedStatement deleteLaptopStmt = conexion.prepareStatement(deleteLaptopSQL);
            deleteLaptopStmt.setInt(1, id);
            deleteLaptopStmt.executeUpdate();
            
            String deleteProductoSQL = "DELETE FROM Producto WHERE id = ?";
            PreparedStatement deleteProductoStmt = conexion.prepareStatement(deleteProductoSQL);
            deleteProductoStmt.setInt(1, id);
            deleteProductoStmt.executeUpdate();
            
            System.out.println("Laptop eliminada exitosamente.");
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public List<Laptop> listar() {
        List<Laptop> listaLaptops = new ArrayList<>();

        String sql = "SELECT p.id, p.nombre, p.marca, p.modelo, p.tipo, p.descripcion, p.stock, p.precio, p.descuento, p.precioFinal, l.memoriaRam, l.procesador " +
                     "FROM Producto p JOIN Laptop l ON p.id = l.producto_id";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                // Crear el objeto Laptop con todos los datos necesarios
                Laptop laptop = new Laptop(
                    resultado.getInt("memoriaRam"),
                    resultado.getString("procesador"),
                    resultado.getInt("id"),
                    resultado.getString("nombre"),
                    resultado.getString("marca"),
                    resultado.getString("modelo"),
                    resultado.getString("tipo"),
                    resultado.getString("descripcion"),
                    resultado.getInt("stock"),
                    resultado.getInt("precio"),
                    resultado.getDouble("descuento")
                );

                listaLaptops.add(laptop);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaLaptops;
    }
    
}
