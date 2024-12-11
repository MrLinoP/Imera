
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface HoraBO  extends Remote{
    
    ArrayList<Integer> obtenerDistribucionHorasXPabellon(String idPabellon) throws RemoteException;
    
}
