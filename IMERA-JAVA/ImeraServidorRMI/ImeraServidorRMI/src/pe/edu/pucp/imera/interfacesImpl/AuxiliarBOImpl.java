/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.AuxiliarBO;
import pe.edu.pucp.imera.usuarios.model.Auxiliar;
import pe.edu.pucp.imera.usuarios.model.Rol;
import pe.edu.pucp.imera.usuarios.model.Turno;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AuxiliarBOImpl extends UnicastRemoteObject  implements AuxiliarBO{
    
    private pe.edu.pucp.imera.usuarios.bo.AuxiliarBO auxiliarBO;
    
    public AuxiliarBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.auxiliarBO= new pe.edu.pucp.imera.usuarios.bo.AuxiliarBO();
    }

    @Override
    public Integer insertar(Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo, String contrasenha, Rol rol) throws RemoteException {
        return auxiliarBO.insertar(codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo, contrasenha, rol);
    }

    @Override
    public Integer modificar(Integer idAuxiliar, Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo, String contrasenha) throws RemoteException {
        return auxiliarBO.modificar(idAuxiliar, codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo, contrasenha);
    }

    @Override
    public Integer eliminar(Integer idAuxiliar) throws RemoteException {
        return auxiliarBO.eliminar(idAuxiliar);
    }

    @Override
    public ArrayList<Auxiliar> listarTodos() throws RemoteException {
        return auxiliarBO.listarTodos();
    }

    @Override
    public Auxiliar obtenerPorId(Integer idAuxiliar) throws RemoteException {
        return auxiliarBO.obtenerPorId(idAuxiliar);
    }
    
}
