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
public class TelevisorDAO {
    private Connection conexion;

    public TelevisorDAO(String url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(Televisor tele) {
    String productoSQL = "INSERT INTO Producto (nombre, marca, modelo, tipo, descripcion, stock, precio, descuento, precioFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        
        PreparedStatement instruccionProducto = conexion.prepareStatement(productoSQL, Statement.RETURN_GENERATED_KEYS);
        instruccionProducto.setString(1, tele.getNombre());
        instruccionProducto.setString(2, tele.getMarca());
        instruccionProducto.setString(3, tele.getModelo());
        instruccionProducto.setString(4, tele.getTipo());
        instruccionProducto.setString(5, tele.getDescripcion());
        instruccionProducto.setInt(6, tele.getStock());
        instruccionProducto.setInt(7, tele.getPrecio());
        instruccionProducto.setDouble(8, tele.getDescuento());
        instruccionProducto.setDouble(9, tele.getPrecioFinal());

        int filasAfectadas = instruccionProducto.executeUpdate(); 

        if (filasAfectadas > 0) {
            // Obtener el ID generado
            ResultSet generatedKeys = instruccionProducto.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productoId = generatedKeys.getInt(1); 

                
                String laptopSQL = "INSERT INTO Televisor (producto_id, tamañoPantalla, calidad) VALUES (?, ?, ?)";
                PreparedStatement instruccionLaptop = conexion.prepareStatement(laptopSQL);
                instruccionLaptop.setInt(1, productoId); 
                instruccionLaptop.setInt(2, tele.getTamañoPantalla());
                instruccionLaptop.setString(3, tele.getCalidad());

                instruccionLaptop.executeUpdate(); 
                System.out.println("Televisor agregado exitosamente.");
            }
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void eliminarTelevisor(int id) {
    
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
            
            String deleteLaptopSQL = "DELETE FROM Televisor WHERE producto_id = ?";
            PreparedStatement deleteLaptopStmt = conexion.prepareStatement(deleteLaptopSQL);
            deleteLaptopStmt.setInt(1, id);
            deleteLaptopStmt.executeUpdate();
            
            String deleteProductoSQL = "DELETE FROM Producto WHERE id = ?";
            PreparedStatement deleteProductoStmt = conexion.prepareStatement(deleteProductoSQL);
            deleteProductoStmt.setInt(1, id);
            deleteProductoStmt.executeUpdate();
            
            System.out.println("Televisor eliminado exitosamente.");
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public List<Televisor> listar() {
        List<Televisor> listaTeles = new ArrayList<>();

        String sql = "SELECT p.id, p.nombre, p.marca, p.modelo, p.tipo, p.descripcion, p.stock, p.precio, p.descuento, p.precioFinal, t.tamañoPantalla, t.calidad " +
                     "FROM Producto p JOIN Televisor t ON p.id = t.producto_id";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                
                Televisor tele = new Televisor(
                    resultado.getInt("tamañoPantalla"),
                    resultado.getString("calidad"),
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

                listaTeles.add(tele);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaTeles;
    }
}
