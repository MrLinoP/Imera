/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.imera.interfaces.PersonaBO;
import pe.edu.pucp.imera.usuarios.model.Persona;

/**
 *
 * @author cesar
 */
public class PersonaBOImpl  extends UnicastRemoteObject  implements PersonaBO{
    
    private pe.edu.pucp.imera.usuarios.bo.PersonaBO personaBO;
    
    public PersonaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.personaBO= new pe.edu.pucp.imera.usuarios.bo.PersonaBO();
    }

    @Override
    public Persona obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion, String contrasenha) throws RemoteException {
        return this.personaBO.obtenerPorIdPorCorreo_Codigo(DatosInicioSesion, contrasenha);
    }

    
}
