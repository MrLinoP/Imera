
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.infra.bo;

import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Piso;
import pe.edu.pucp.imera.infra.dao.PisoDAO;
import pe.edu.pucp.imera.infra.daoImpl.PisoDAOImpl;

/**
 *
 * @author home
 */
public class PisoBO {
    private final PisoDAO pisoDAO;

    public PisoBO() {
        this.pisoDAO = new PisoDAOImpl();
    }
    
    public ArrayList<Integer> listarNumerosDePisosXPabellon(String idPabellon){
        return pisoDAO.listarNumerosDePisosXPabellon(idPabellon);
    }
    
    public Integer insertar(String idPabellon, Integer idPiso, String[] aulas, Integer[] aforos, Integer[] enchufes) {
        return pisoDAO.insertarPiso(idPabellon, idPiso, aulas, aforos, enchufes);
    }
    
}
