/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODeuda {
    
    private Connection connection;

    public DAODeuda() {
        try {
            // Establecer conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ruat", "root", "");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener deudas por CI
    public ArrayList<Deuda> buscarDeudas(String ci) {
        ArrayList<Deuda> deudas = new ArrayList<>();
        String query = "SELECT * FROM deudas WHERE ci = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ci);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Crear objeto Deuda con los datos obtenidos de la base de datos
                Deuda deuda = new Deuda(
                        rs.getString("ci"),
                        rs.getInt("anio"),
                        Impuesto.valueOf(rs.getString("impuesto")),
                        rs.getInt("monto")
                );
                deudas.add(deuda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deudas;
    }

    // Método para pagar y eliminar deuda
    public boolean pagarDeuda(Deuda deuda) {
        String query = "DELETE FROM deudas WHERE ci = ? AND anio = ? AND impuesto = ? AND monto = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, deuda.getCi());
            stmt.setInt(2, deuda.getAnio());
            stmt.setString(3, deuda.getImpuesto().name());
            stmt.setInt(4, deuda.getMonto());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // True si la deuda fue pagada y eliminada
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para agregar deudas (opcional si es necesario)
    public void agregarDeuda(Deuda deuda) {
        String query = "INSERT INTO deudas (ci, anio, impuesto, monto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, deuda.getCi());
            stmt.setInt(2, deuda.getAnio());
            stmt.setString(3, deuda.getImpuesto().name());
            stmt.setInt(4, deuda.getMonto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
