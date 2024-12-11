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
public interface CambioDeEstadoBO extends Remote {
    
    Integer insertar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estadoInicial, EstadoAula estadoFinal, Integer idAuxiliar) throws RemoteException;
    
}
