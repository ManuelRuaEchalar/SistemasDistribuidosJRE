/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

/**
 *
 * @author eantoniocf
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObservacionDAO {

    private Connection connection;

    public ObservacionDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para buscar una observación por CI
    public Observacion buscarObservacionPorCi(String ci) {
        Observacion observacion = null;
        String query = "SELECT * FROM observaciones WHERE ci = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ci);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                boolean tieneObservacion = rs.getBoolean("tieneObservacion");
                observacion = new Observacion(ci, tieneObservacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar observación: " + e.getMessage());
        }
        return observacion;
    }
}
