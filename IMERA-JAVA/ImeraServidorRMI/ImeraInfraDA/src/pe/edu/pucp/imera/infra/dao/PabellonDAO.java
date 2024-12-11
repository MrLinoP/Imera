
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.infra.dao;

import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Pabellon;

/**
 *
 * @author home
 */
public interface PabellonDAO {
    
    Integer insertar(String idPabellon, String nombre, Integer cantPisos, String[] aulas, Integer[] aforos, Integer[] enchufes);

    ArrayList<Pabellon> listarTodos();
    
}
