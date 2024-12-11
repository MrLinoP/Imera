/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.horario.dao;

import java.util.ArrayList;

/**
 *
 * @author home
 */
public interface HoraDAO {
    
    ArrayList<Integer> obtenerDistribucionHorasXPabellon(String idPabellon);
    
}
