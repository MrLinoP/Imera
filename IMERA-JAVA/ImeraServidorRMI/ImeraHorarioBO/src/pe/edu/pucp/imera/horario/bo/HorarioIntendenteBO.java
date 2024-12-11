/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.dao.HorarioIntendenteDAO;
import pe.edu.pucp.imera.horario.daoImpl.HorarioIntendenteDAOImpl;
import pe.edu.pucp.imera.horario.model.DiaSemana;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;

/**
 *
 * @author home
 */
public class HorarioIntendenteBO {
    private final HorarioIntendenteDAO horarioIntendenteDAO;
    
    public HorarioIntendenteBO() {
        horarioIntendenteDAO = new HorarioIntendenteDAOImpl();
    }
    
    public ArrayList<HorarioIntendente> listar_todosSemestrales(){
        return horarioIntendenteDAO.listarTodosSemestrales();
    }
    public ArrayList<HorarioIntendente> listar_todosDisponibles(String idPabellon){
        return horarioIntendenteDAO.listarTodosDisponibles(idPabellon);
    }
    public Integer modificarDisponibleASemestral(String [] aulas, String [] horarios, String [] dias){
        ArrayList<HorarioIntendente> horariosIntendente = new ArrayList<>();
        for(int i=0;i<aulas.length;i++){
            HorarioIntendente aux = new HorarioIntendente();
            aux.setNombreAula(aulas[i]);
            aux.setHorario(horarios[i]);
            aux.setDiaSemana(DiaSemana.valueOf(dias[i]));
            horariosIntendente.add(aux);
        }
        return horarioIntendenteDAO.modificarDisponibleASemestral(horariosIntendente);
    }
    
    public Integer modificarSemestralADisponible(String aula, String horario, String dia){
        HorarioIntendente aux = new HorarioIntendente(aula,horario,DiaSemana.valueOf(dia));
        return horarioIntendenteDAO.modificarSemestralADisponible(aux);
        
    }
}