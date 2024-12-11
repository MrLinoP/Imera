/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;
import pe.edu.pucp.imera.interfaces.HorarioIntendenteBO;

/**
 *
 * @author cesar
 */
public class HorarioIntendenteBOImpl  extends UnicastRemoteObject  implements HorarioIntendenteBO{
    
    private pe.edu.pucp.imera.horario.bo.HorarioIntendenteBO horarioIntendenteBO;
    
    public HorarioIntendenteBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.horarioIntendenteBO=new pe.edu.pucp.imera.horario.bo.HorarioIntendenteBO();
    }

    @Override
    public ArrayList<HorarioIntendente> listar_todosSemestrales() throws RemoteException {
        return this.horarioIntendenteBO.listar_todosSemestrales();
    }

    @Override
    public ArrayList<HorarioIntendente> listar_todosDisponibles(String idPabellon) throws RemoteException {
        return this.horarioIntendenteBO.listar_todosDisponibles(idPabellon);
    }

    @Override
    public Integer modificarDisponibleASemestral(String[] aulas, String[] horarios, String[] dias) throws RemoteException {
        return this.horarioIntendenteBO.modificarDisponibleASemestral(aulas, horarios, dias);
    }

    @Override
    public Integer modificarSemestralADisponible(String aula, String horario, String dia) throws RemoteException {
        return this.horarioIntendenteBO.modificarSemestralADisponible(aula, horario, dia);
    }
    
}
