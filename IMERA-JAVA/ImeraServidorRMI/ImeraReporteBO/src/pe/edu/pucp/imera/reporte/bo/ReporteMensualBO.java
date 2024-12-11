/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.reporte.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.reporte.DAOImpl.ReporteMensualDAOImpl;
import pe.edu.pucp.imera.reporte.model.ReporteMensual;
import pe.edu.pucp.imera.reporte.DAO.ReporteMensualDAO;

/**
 *
 * @author ferjo
 */
public class ReporteMensualBO {
    
    ReporteMensualDAO reporteMensualDeAulasPorPabellonDAO;

    public ReporteMensualBO() {
        this.reporteMensualDeAulasPorPabellonDAO = new ReporteMensualDAOImpl();
    }
    
    public ArrayList<ReporteMensual> reporteMensualPabellones(){
        return this.reporteMensualDeAulasPorPabellonDAO.obtenerReportePabellones();
    }
    
    
}
