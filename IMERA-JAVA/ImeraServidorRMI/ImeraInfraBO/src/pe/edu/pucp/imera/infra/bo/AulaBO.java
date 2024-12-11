/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.infra.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.infra.dao.AulaDAO;
import pe.edu.pucp.imera.infra.daoImpl.AulaDAOImpl;

/**
 *
 * @author home
 */
public class AulaBO {
    
    private final AulaDAO aulaDAO;

    public AulaBO() {
        this.aulaDAO = new AulaDAOImpl();
    }
    public Integer insertar(Integer[] arrIdAula,Integer[] arrIdPiso,String[] arrIdPabellon, Integer[] arrAforo, Integer[] arrNumeroEnchufes){
        return aulaDAO.insertar_con_parametros(arrIdAula,arrIdPiso,arrIdPabellon,arrAforo,arrNumeroEnchufes);
    }
    public ArrayList<String> listarNombresAulasXPisoYPabellon(Integer idPiso,String idPabellon){
        return aulaDAO.listarNombresAulasXPisoYPabellon(idPiso, idPabellon);
    }
    
}