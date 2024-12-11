/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;

/**
 *
 * @author cesar
 */
public interface ReporteSemanalBO  extends Remote{
    ArrayList<ReporteSemanal> reporteSemanalAulas(String idPabellon) throws RemoteException;
}
