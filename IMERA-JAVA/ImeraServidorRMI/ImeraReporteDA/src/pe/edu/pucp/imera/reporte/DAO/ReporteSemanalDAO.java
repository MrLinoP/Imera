/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.imera.reporte.DAO;

import java.util.ArrayList;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;

/**
 *
 * @author home
 */
public interface ReporteSemanalDAO {

    public ArrayList<ReporteSemanal> obtenerReporteSemanal(String idPabellon);

    
}
