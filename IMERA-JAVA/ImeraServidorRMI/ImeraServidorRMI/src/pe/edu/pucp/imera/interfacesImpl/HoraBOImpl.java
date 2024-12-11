/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.HoraBO;

/**
 *
 * @author cesar
 */
public class HoraBOImpl  extends UnicastRemoteObject  implements HoraBO{
    
    private pe.edu.pucp.imera.horario.bo.HoraBO horaBO;
    
    public HoraBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.horaBO=new pe.edu.pucp.imera.horario.bo.HoraBO();
    }

    @Override
    public ArrayList<Integer> obtenerDistribucionHorasXPabellon(String idPabellon) throws RemoteException {
        return this.horaBO.obtenerDistribucionHorasXPabellon(idPabellon);
    }

    
}
