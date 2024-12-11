
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.infra.bo;

import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Pabellon;
import pe.edu.pucp.imera.infra.dao.PabellonDAO;
import pe.edu.pucp.imera.infra.daoImpl.PabellonDAOImpl;

/**
 *
 * @author home
 */
public class PabellonBO {
    
    
    private final PabellonDAO pabellonDAO;

    public PabellonBO() {
        this.pabellonDAO = new PabellonDAOImpl();
    }
    public ArrayList<Pabellon> listar_todos(){
        return this.pabellonDAO.listarTodos();
    }
    public Integer insertar(String idPabellon, String nombre, Integer cantPisos, String[] aulas, Integer[] aforos, Integer[] enchufes){
        return this.pabellonDAO.insertar(idPabellon,  nombre,  cantPisos,  aulas, aforos, enchufes); 
    }
    
    
}
