/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.horario.model.HorarioCompleto;
import pe.edu.pucp.imera.interfaces.HorarioCompletoBO;

/**
 *
 * @author cesar
 */
public class HorarioCompletoBOImpl  extends UnicastRemoteObject  implements HorarioCompletoBO{
    
    private pe.edu.pucp.imera.horario.bo.HorarioCompletoBO horarioCompletoBO;
    
    public HorarioCompletoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.horarioCompletoBO=new pe.edu.pucp.imera.horario.bo.HorarioCompletoBO();
    }

    @Override
    public ArrayList<HorarioCompleto> listar_todos_alumno() throws RemoteException {
        return this.horarioCompletoBO.listar_todos_alumno();
    }

    @Override
    public ArrayList<HorarioCompleto> listar_todos_intendente() throws RemoteException {
        return this.horarioCompletoBO.listar_todos_intendente();
    }

    
}
