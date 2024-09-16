/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaTcpRmiUdpBdPropio;

import java.sql.Connection;

public class RegistroObservaciones {

    private ObservacionDAO observacionDAO;

    public RegistroObservaciones(Connection connection) {
        this.observacionDAO = new ObservacionDAO(connection);
    }

    // Método para buscar una observación por CI
    public Observacion buscarObservacionPorCi(String ci) {
        return observacionDAO.buscarObservacionPorCi(ci);
    }
}
