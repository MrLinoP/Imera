/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.PisoBO;

/**
 *
 * @author cesar
 */
public class PisoBOImpl extends UnicastRemoteObject   implements PisoBO{
    
    private pe.edu.pucp.imera.infra.bo.PisoBO pisoBO;
    
    public PisoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.pisoBO=new pe.edu.pucp.imera.infra.bo.PisoBO();
    }

    @Override
    public ArrayList<Integer> listarNumerosDePisosXPabellon(String idPabellon) throws RemoteException {
        return this.pisoBO.listarNumerosDePisosXPabellon(idPabellon);
    }

    @Override
    public Integer insertar(String idPabellon, Integer idPiso, String[] aulas, Integer[] aforos, Integer[] enchufes) throws RemoteException {
        return this.pisoBO.insertar(idPabellon, idPiso, aulas, aforos, enchufes);
    }

}
