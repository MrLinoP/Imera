/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;

/**
 *
 * @author cesar
 */
public interface HorarioIntendenteBO extends Remote {
    ArrayList<HorarioIntendente> listar_todosSemestrales() throws RemoteException;
    ArrayList<HorarioIntendente> listar_todosDisponibles(String idPabellon) throws RemoteException;
    Integer modificarDisponibleASemestral(String [] aulas, String [] horarios, String [] dias) throws RemoteException;
    Integer modificarSemestralADisponible(String aula, String horario, String dia) throws RemoteException;
}
