
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.ReporteSemanalBO;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;
import java.rmi.server.UnicastRemoteObject;

public class ReporteSemanalBOImpl extends UnicastRemoteObject implements ReporteSemanalBO{
    
    private pe.edu.pucp.imera.reporte.bo.ReporteSemanalBO reporteSemanalBO;
    
    public ReporteSemanalBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.reporteSemanalBO= new pe.edu.pucp.imera.reporte.bo.ReporteSemanalBO();
    }

    @Override
    public ArrayList<ReporteSemanal> reporteSemanalAulas(String idPabellon) throws RemoteException {
        return this.reporteSemanalBO.reporteSemanalAulas(idPabellon);
    }

    
}
