
package pe.edu.pucp.imera.reporte.bo;

import java.util.ArrayList;
import pe.edu.pucp.imera.reporte.DAO.ReporteSemanalDAO;
import pe.edu.pucp.imera.reporte.DAOImpl.ReporteSemanalDAOImpl;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;

/**
 *
 * @author ferjo
 */
public class ReporteSemanalBO {
    
    ReporteSemanalDAO reporteSemanalDAO;

    public ReporteSemanalBO() {
        this.reporteSemanalDAO = new ReporteSemanalDAOImpl();
    }
    
    public ArrayList<ReporteSemanal> reporteSemanalAulas(String idPabellon){
        return this.reporteSemanalDAO.obtenerReporteSemanal(idPabellon);
    }
    
    
}