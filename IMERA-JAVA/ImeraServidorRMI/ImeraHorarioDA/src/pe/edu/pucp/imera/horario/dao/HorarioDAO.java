/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.horario.dao;

import java.sql.Connection;
import pe.edu.pucp.imera.horario.model.Horario;

/**
 *
 * @author home
 */
public interface HorarioDAO {
    
    //public Integer insertar(Horario horario,Connection conexion,Boolean usarTransaccion);
    Integer modificar(Horario horario);
    
    Integer modificarUsandoTransaccion(Horario horario);
    
    Integer modificarHorariosClase(Horario[] horario, String[] fechas);
}
