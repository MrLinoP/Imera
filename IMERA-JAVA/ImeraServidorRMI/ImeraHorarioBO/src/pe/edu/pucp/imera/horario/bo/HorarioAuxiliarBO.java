/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.dao.HorarioAuxiliarDAO;
import pe.edu.pucp.imera.horario.model.HorarioAuxiliar;
import pe.edu.pucp.imera.horario.daoImpl.HorarioAuxiliarDAOImpl;

/**
 *
 * @author julia
 */
public class HorarioAuxiliarBO {
    private final HorarioAuxiliarDAO horarioAuxiliarDAO;
    
    public HorarioAuxiliarBO() {
        horarioAuxiliarDAO = new HorarioAuxiliarDAOImpl();
    }
    
    public ArrayList<HorarioAuxiliar> listar_todos(String idPabellon, Integer idPiso){
        return horarioAuxiliarDAO.listarTodos(idPabellon, idPiso);
    }
}
