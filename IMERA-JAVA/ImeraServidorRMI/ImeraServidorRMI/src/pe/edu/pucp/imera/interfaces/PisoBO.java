/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface PisoBO extends Remote {

    ArrayList<Integer> listarNumerosDePisosXPabellon(String idPabellon) throws RemoteException;
    
    Integer insertar(String idPabellon, Integer idPiso, String[] aulas, Integer[] aforos, Integer[] enchufes) throws RemoteException;

}
