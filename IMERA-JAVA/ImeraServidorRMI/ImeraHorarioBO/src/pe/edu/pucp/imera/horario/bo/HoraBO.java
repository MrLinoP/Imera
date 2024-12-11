/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.dao.HoraDAO;
import pe.edu.pucp.imera.horario.daoImpl.HoraDAOImpl;

/**
 *
 * @author cesar
 */
public class HoraBO {
    private final HoraDAO horaDAO;

    public HoraBO() {
        horaDAO = new HoraDAOImpl();
    }
    public ArrayList<Integer> obtenerDistribucionHorasXPabellon(String idPabellon) {
        return horaDAO.obtenerDistribucionHorasXPabellon(idPabellon);
    }
}
