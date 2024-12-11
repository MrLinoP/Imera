/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author cesar
 */
public interface AulaBO extends Remote{
    
    Integer insertar(Integer[] arrIdAula,Integer[] arrIdPiso,String[] arrIdPabellon, Integer[] arrAforo, Integer[] arrNumeroEnchufes) throws RemoteException;
    ArrayList<String> listarNombresAulasXPisoYPabellon(Integer idPiso,String idPabellon) throws RemoteException;
}
