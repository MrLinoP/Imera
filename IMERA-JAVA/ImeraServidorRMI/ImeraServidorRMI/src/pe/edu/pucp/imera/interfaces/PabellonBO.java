/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.du.pucp.imera.infra.model.Pabellon;

/**
 *
 * @author cesar
 */
public interface PabellonBO extends Remote {
    ArrayList<Pabellon> listar_todos() throws RemoteException;
    Integer insertar(String idPabellon, String nombre, Integer cantPisos, String[] aulas, Integer[] aforos, Integer[] enchufes) throws RemoteException;
}
