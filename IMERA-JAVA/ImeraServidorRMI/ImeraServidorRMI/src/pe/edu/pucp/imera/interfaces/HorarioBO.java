/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.imera.horario.model.EstadoAula;

/**
 *
 * @author cesar
 */
public interface HorarioBO  extends Remote{
    Integer modificar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estado) throws RemoteException;
    Integer modificarAulasClase(String[] idPabellones,Integer[] idPisos,Integer[] idAulas,Integer[] idHoras , String[] fechas) throws RemoteException;
}
