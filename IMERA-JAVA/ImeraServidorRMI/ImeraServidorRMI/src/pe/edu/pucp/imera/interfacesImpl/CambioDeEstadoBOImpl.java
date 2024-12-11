/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.interfaces.CambioDeEstadoBO;

/**
 *
 * @author cesar
 */
public class CambioDeEstadoBOImpl  extends UnicastRemoteObject  implements CambioDeEstadoBO{
    private pe.edu.pucp.imera.horario.bo.CambioDeEstadoBO cambioDeEstadoBO;
    public CambioDeEstadoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.cambioDeEstadoBO=new pe.edu.pucp.imera.horario.bo.CambioDeEstadoBO();
    }

    @Override
    public Integer insertar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estadoInicial, EstadoAula estadoFinal, Integer idAuxiliar) throws RemoteException {
        return this.cambioDeEstadoBO.insertar(idPabellon, idPiso, idAula, idHora, estadoInicial, estadoFinal, idAuxiliar);
    }

    
}
