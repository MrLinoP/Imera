/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.imera.interfaces.ReporteMensualBO;
import pe.edu.pucp.imera.reporte.model.ReporteMensual;

/**
 *
 * @author cesar
 */
public class ReporteMensualBOImpl extends UnicastRemoteObject  implements ReporteMensualBO{
    
    private pe.edu.pucp.imera.reporte.bo.ReporteMensualBO reporteMensualBO;
    
    public ReporteMensualBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.reporteMensualBO= new pe.edu.pucp.imera.reporte.bo.ReporteMensualBO();
    }

    @Override
    public ArrayList<ReporteMensual> reporteMensualPabellones() throws RemoteException {
        return this.reporteMensualBO.reporteMensualPabellones();
    }

    
}
