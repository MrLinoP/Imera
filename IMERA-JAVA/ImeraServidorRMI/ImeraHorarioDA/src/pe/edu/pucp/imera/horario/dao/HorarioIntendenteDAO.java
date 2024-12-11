/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.horario.dao;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;

/**
 *
 * @author home
 */
public interface HorarioIntendenteDAO {
    //ArrayList<HorarioIntendente>  listarTodos(String idPabellon);
    
    Integer modificarDisponibleASemestral(ArrayList<HorarioIntendente> horariosIntendente);
    
    Integer modificarSemestralADisponible(HorarioIntendente horario);
    
    ArrayList<HorarioIntendente> listarTodosSemestrales();
    
    ArrayList<HorarioIntendente> listarTodosDisponibles(String idPabellon);
}