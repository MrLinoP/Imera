/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pe.edu.pucp.imera.usuarios.model.Persona;

/**
 *
 * @author cesar
 */
public interface PersonaBO  extends Remote{
        Persona obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha) throws RemoteException;

}
