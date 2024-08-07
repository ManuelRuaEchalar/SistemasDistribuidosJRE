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
public class TelefonoDAO {
    private Connection conexion;

    public TelefonoDAO(String url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(Telefono telefono) {
    String productoSQL = "INSERT INTO Producto (nombre, marca, modelo, tipo, descripcion, stock, precio, descuento, precioFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        // Preparar el Statement para devolver las claves generadas
        PreparedStatement instruccionProducto = conexion.prepareStatement(productoSQL, Statement.RETURN_GENERATED_KEYS);
        instruccionProducto.setString(1, telefono.getNombre());
        instruccionProducto.setString(2, telefono.getMarca());
        instruccionProducto.setString(3, telefono.getModelo());
        instruccionProducto.setString(4, telefono.getTipo());
        instruccionProducto.setString(5, telefono.getDescripcion());
        instruccionProducto.setInt(6, telefono.getStock());
        instruccionProducto.setInt(7, telefono.getPrecio());
        instruccionProducto.setDouble(8, telefono.getDescuento());
        instruccionProducto.setDouble(9, telefono.getPrecioFinal());

        int filasAfectadas = instruccionProducto.executeUpdate(); // Ejecutar la inserción en Producto

        if (filasAfectadas > 0) {
            // Obtener el ID generado
            ResultSet generatedKeys = instruccionProducto.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productoId = generatedKeys.getInt(1); // Obtener el ID generado

                // Insertar en la tabla Laptop
                String laptopSQL = "INSERT INTO Telefono (producto_id, memoriaRam, procesador, versionAndroid) VALUES (?, ?, ?, ?)";
                PreparedStatement instruccionLaptop = conexion.prepareStatement(laptopSQL);
                instruccionLaptop.setInt(1, productoId); // Usar el ID del producto
                instruccionLaptop.setInt(2, telefono.getMemoriaRam());
                instruccionLaptop.setString(3, telefono.getProcesador());
                instruccionLaptop.setString(4, telefono.getVersionAndroid());

                instruccionLaptop.executeUpdate(); // Ejecutar la inserción en Laptop
                System.out.println("Telefono agregado exitosamente.");
            }
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void eliminarTelefono(int id) {
    // Paso 1: Obtener el stock actual
    String selectSQL = "SELECT stock FROM Producto WHERE id = ?";
    int stockActual = 0;

    try {
        PreparedStatement selectStmt = conexion.prepareStatement(selectSQL);
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
            stockActual = rs.getInt("stock");
        }

        // Paso 2: Restar uno al stock
        stockActual -= 1;

        // Paso 3: Actualizar el stock o eliminar el producto
        if (stockActual > 0) {
            // Actualizar el stock
            String updateSQL = "UPDATE Producto SET stock = ? WHERE id = ?";
            PreparedStatement updateStmt = conexion.prepareStatement(updateSQL);
            updateStmt.setInt(1, stockActual);
            updateStmt.setInt(2, id);
            updateStmt.executeUpdate();
            System.out.println("Stock actualizado exitosamente.");
        } else {
            // Eliminar de Laptop y Producto si el stock es 0
            String deleteLaptopSQL = "DELETE FROM Telefono WHERE producto_id = ?";
            PreparedStatement deleteLaptopStmt = conexion.prepareStatement(deleteLaptopSQL);
            deleteLaptopStmt.setInt(1, id);
            deleteLaptopStmt.executeUpdate();
            
            String deleteProductoSQL = "DELETE FROM Producto WHERE id = ?";
            PreparedStatement deleteProductoStmt = conexion.prepareStatement(deleteProductoSQL);
            deleteProductoStmt.setInt(1, id);
            deleteProductoStmt.executeUpdate();
            
            System.out.println("Telefono eliminado exitosamente.");
        }

    } catch (SQLException ex) {
        System.out.print(ex.getMessage());
        // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public List<Telefono> listar() {
        List<Telefono> listaLaptops = new ArrayList<>();

        String sql = "SELECT p.id, p.nombre, p.marca, p.modelo, p.tipo, p.descripcion, p.stock, p.precio, p.descuento, p.precioFinal, t.memoriaRam, t.procesador, t.versionAndroid " +
                     "FROM Producto p JOIN Telefono t ON p.id = t.producto_id";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()) {
                // Crear el objeto Laptop con todos los datos necesarios
                Telefono telefono = new Telefono(
                    resultado.getInt("memoriaRam"),
                    resultado.getString("procesador"),
                    resultado.getString("versionAndroid"),
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

                listaLaptops.add(telefono);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            // Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaLaptops;
    }
}
