/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.infra.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Piso;

/**
 *
 * @author home
 */
public interface PisoDAO {

   Integer insertar(Piso piso,Connection conexion, Boolean usarTransaccion);

    Integer insertarPiso(String idPabellon, Integer idPiso, String[] aulas, Integer[] aforos, Integer[] enchufes);
    
    ArrayList<Integer> listarNumerosDePisosXPabellon(String idPabellon);
    
}