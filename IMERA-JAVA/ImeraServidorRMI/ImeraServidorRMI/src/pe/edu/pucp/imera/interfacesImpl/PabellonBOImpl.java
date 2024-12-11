/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Pabellon;
import pe.edu.pucp.imera.interfaces.PabellonBO;

/**
 *
 * @author cesar
 */
public class PabellonBOImpl  extends UnicastRemoteObject  implements PabellonBO{
    
    private pe.edu.pucp.imera.infra.bo.PabellonBO pabellonBO;
    
    public PabellonBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.pabellonBO=new pe.edu.pucp.imera.infra.bo.PabellonBO();
    }

    @Override
    public ArrayList<Pabellon> listar_todos() throws RemoteException {
        return this.pabellonBO.listar_todos();
    }

    @Override
    public Integer insertar(String idPabellon, String nombre, Integer cantPisos, String[] aulas, Integer[] aforos, Integer[] enchufes) throws RemoteException {
        return this.pabellonBO.insertar(idPabellon, nombre, cantPisos, aulas, aforos, enchufes);
    }
    
    
}
