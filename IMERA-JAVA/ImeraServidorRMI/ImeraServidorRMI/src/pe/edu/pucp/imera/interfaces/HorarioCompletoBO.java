/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioCompleto;

/**
 *
 * @author cesar
 */
public interface HorarioCompletoBO  extends Remote{
    
    ArrayList<HorarioCompleto> listar_todos_alumno() throws RemoteException;
    
    ArrayList<HorarioCompleto> listar_todos_intendente() throws RemoteException;
    
}
