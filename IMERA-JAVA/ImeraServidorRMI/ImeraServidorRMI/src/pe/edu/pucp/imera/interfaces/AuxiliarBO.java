/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.usuarios.model.Auxiliar;
import pe.edu.pucp.imera.usuarios.model.Rol;
import pe.edu.pucp.imera.usuarios.model.Turno;

/**
 *
 * @author cesar
 */
public interface AuxiliarBO  extends Remote{
    Integer insertar(Integer codigoPucp, String correoPucp, String nombre, String apellidoP, 
            String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha,Rol rol) throws RemoteException;
    
    Integer modificar(Integer idAuxiliar, Integer codigoPucp, String correoPucp, String nombre, 
            String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha) throws RemoteException;
    
    Integer eliminar(Integer idAuxiliar) throws RemoteException;
    
    ArrayList<Auxiliar> listarTodos() throws RemoteException;
    
    Auxiliar obtenerPorId(Integer idAuxiliar) throws RemoteException;
}
