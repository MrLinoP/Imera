/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.dao.HorarioCompletoDAO;
import pe.edu.pucp.imera.horario.model.HorarioCompleto;
import pe.edu.pucp.imera.horario.daoImpl.HorarioCompletoDAOImpl;

/**
 *
 * @author julia
 */
public class HorarioCompletoBO {
    private final HorarioCompletoDAO horarioCompletoDAO;
    
    public HorarioCompletoBO() {
        horarioCompletoDAO = new HorarioCompletoDAOImpl();
    }
    
    public ArrayList<HorarioCompleto> listar_todos_alumno(){
        return horarioCompletoDAO.listarTodosAlumno();
    }
    
    public ArrayList<HorarioCompleto> listar_todos_intendente(){
        return horarioCompletoDAO.listarTodosIntendente();
    }
}
