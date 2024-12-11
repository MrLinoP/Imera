/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.dao;

import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioCompleto;

/**
 *
 * @author julia
 */
public interface HorarioCompletoDAO {
    ArrayList<HorarioCompleto>  listarTodosAlumno();
    ArrayList<HorarioCompleto>  listarTodosIntendente(); //ESTE SE VA
}
