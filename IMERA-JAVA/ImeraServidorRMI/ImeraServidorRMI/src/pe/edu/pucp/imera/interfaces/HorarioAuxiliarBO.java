/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioAuxiliar;

/**
 *
 * @author cesar
 */
public interface HorarioAuxiliarBO  extends Remote{
    
    ArrayList<HorarioAuxiliar> listar_todos(String idPabellon, Integer idPiso) throws RemoteException;
    
}
