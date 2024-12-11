
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.infra.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Aula;

/**
 *
 * @author home
 */
public interface AulaDAO {
    
    ArrayList<String> listarNombresAulasXPisoYPabellon(Integer idPiso,String idPabellon);
    
    Integer insertar(Aula aula,Connection conexion, Boolean usarTransaccion);
    
    Integer insertar_con_parametros(Integer[] arrIdAula, Integer[] arrIdPiso, String[] arrIdPabellon, Integer[] arrAforo, Integer[] arrNumeroEnchufes);

}
