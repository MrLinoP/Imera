/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioAuxiliar;
import pe.edu.pucp.imera.interfaces.HorarioAuxiliarBO;

/**
 *
 * @author cesar
 */
public class HorarioAuxiliarBOImpl  extends UnicastRemoteObject  implements HorarioAuxiliarBO{
    
    private pe.edu.pucp.imera.horario.bo.HorarioAuxiliarBO horarioAuxiliarBO;
    
    public HorarioAuxiliarBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.horarioAuxiliarBO=new pe.edu.pucp.imera.horario.bo.HorarioAuxiliarBO();
    }

    @Override
    public ArrayList<HorarioAuxiliar> listar_todos(String idPabellon, Integer idPiso) throws RemoteException {
        return this.horarioAuxiliarBO.listar_todos(idPabellon, idPiso);
    }

}
