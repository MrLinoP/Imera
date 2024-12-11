/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.AulaBO;

/**
 *
 * @author cesar
 */
public class AulaBOImpl extends UnicastRemoteObject  implements AulaBO{
    
    private pe.edu.pucp.imera.infra.bo.AulaBO aulaBO;
    
    public AulaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.aulaBO= new pe.edu.pucp.imera.infra.bo.AulaBO();
    }

    @Override
    public Integer insertar(Integer[] arrIdAula, Integer[] arrIdPiso, String[] arrIdPabellon, Integer[] arrAforo, Integer[] arrNumeroEnchufes) throws RemoteException {
        return aulaBO.insertar(arrIdAula,  arrIdPiso,  arrIdPabellon, arrAforo, arrNumeroEnchufes);
    }

    @Override
    public ArrayList<String> listarNombresAulasXPisoYPabellon(Integer idPiso, String idPabellon) throws RemoteException {
        return aulaBO.listarNombresAulasXPisoYPabellon( idPiso,  idPabellon);
    }

}
