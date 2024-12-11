/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.imera.reporte.model.ReporteMensual;

/**
 *
 * @author cesar
 */
public interface ReporteMensualBO extends Remote {
    ArrayList<ReporteMensual> reporteMensualPabellones() throws RemoteException;
}
