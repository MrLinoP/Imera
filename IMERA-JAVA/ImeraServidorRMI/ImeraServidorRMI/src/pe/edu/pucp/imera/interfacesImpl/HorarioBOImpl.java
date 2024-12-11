/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.interfaces.HorarioBO;

/**
 *
 * @author cesar
 */
public class HorarioBOImpl  extends UnicastRemoteObject  implements HorarioBO{
    
    private pe.edu.pucp.imera.horario.bo.HorarioBO horarioBO;
    
    public HorarioBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.horarioBO=new pe.edu.pucp.imera.horario.bo.HorarioBO();
    }

    @Override
    public Integer modificar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estado) throws RemoteException {
        return this.horarioBO.modificar(idPabellon, idPiso, idAula, idHora, estado);
    }

    @Override
    public Integer modificarAulasClase(String[] idPabellones, Integer[] idPisos, Integer[] idAulas, Integer[] idHoras, String[] fechas) throws RemoteException {
        return this.horarioBO.modificarAulasClase(idPabellones, idPisos, idAulas, idHoras, fechas);
    }

    
}
