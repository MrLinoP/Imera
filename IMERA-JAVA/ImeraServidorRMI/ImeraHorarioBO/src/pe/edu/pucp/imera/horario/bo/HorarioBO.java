/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.bo;


import java.util.ArrayList;
import java.sql.Date;
import pe.edu.pucp.imera.horario.dao.HorarioDAO;
import pe.edu.pucp.imera.horario.daoImpl.HorarioDAOImpl;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.Horario;

/**
 *
 * @author home
 */
public class HorarioBO {
    
    private final HorarioDAO horarioDAO;

    public HorarioBO() {
        horarioDAO = new HorarioDAOImpl();
    }
    
    public Integer modificar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estado){
        Horario horario = new Horario(idPabellon,  idPiso,  idAula,  idHora,null,  estado);
        return horarioDAO.modificar(horario);
    }
   public Integer modificarAulasClase(String[] idPabellones,Integer[] idPisos,Integer[] idAulas,Integer[] idHoras , String[] fechas){
        Horario[] horarios = new Horario[idPabellones.length];
        for(int i=0;i<idPabellones.length;i++){
            horarios[i] = new Horario();
            horarios[i].setIdPabellon(idPabellones[i]);
            horarios[i].setIdPiso(idPisos[i]);
            horarios[i].setIdAula(idAulas[i]);
            horarios[i].setIdHora(idHoras[i]);
        }
        return horarioDAO.modificarHorariosClase(horarios,fechas);
    }
}
