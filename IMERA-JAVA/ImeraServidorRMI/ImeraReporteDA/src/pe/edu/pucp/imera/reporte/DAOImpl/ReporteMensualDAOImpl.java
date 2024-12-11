/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.reporte.DAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.du.pucp.imera.infra.model.Pabellon;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.horario.dao.CambioDeEstadoDAO;
import pe.edu.pucp.imera.horario.daoImpl.CambioDeEstadoDAOImpl;
import pe.edu.pucp.imera.infra.dao.PabellonDAO;
import pe.edu.pucp.imera.infra.daoImpl.PabellonDAOImpl;
import pe.edu.pucp.imera.reporte.model.ReporteMensual;
import pe.edu.pucp.imera.reporte.DAO.ReporteMensualDAO;


public class ReporteMensualDAOImpl extends DAOImpl implements ReporteMensualDAO{

    private final PabellonDAO pabellonDAO;
    private final CambioDeEstadoDAO cambioDeEstadoDAO;
    public ReporteMensualDAOImpl(){
        super("");
        this.pabellonDAO = new PabellonDAOImpl();
        this.cambioDeEstadoDAO = new CambioDeEstadoDAOImpl();
    }
    @Override
    public ArrayList<ReporteMensual> obtenerReportePabellones() {
        ArrayList<ReporteMensual> reportePabellones = new ArrayList<>();
        ReporteMensual reporte;
        ArrayList<Pabellon> pabellones = pabellonDAO.listarTodos(); 
        
        double ratio;
        this.abrirConexion();
        for(Pabellon pabellon: pabellones){
            reporte = new ReporteMensual();
            reporte.setIdPabellon(pabellon.getIdPabellon());
            
            asignarValoresReporte(reporte,pabellon.getIdPabellon());
            
            if(reporte.getHorasAsignadasAulaLibre()!= 0 && reporte.getHorasDeLibreAReservada() == 0){
                reporte.setRatioAR(reporte.getHorasAsignadasAulaLibre()+".00:0");
            }else if(reporte.getHorasAsignadasAulaLibre()== 0 && reporte.getHorasDeLibreAReservada() == 0){
                reporte.setRatioAR("0:0");
            }else{
                ratio = (double)reporte.getHorasAsignadasAulaLibre()/reporte.getHorasDeLibreAReservada();
                reporte.setRatioAR(String.format("%.2f", ratio) + ":1");
            }
            
            reportePabellones.add(reporte);
        }
        try {
            this.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ReporteMensualDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportePabellones;
    }
    private void asignarValoresReporte(ReporteMensual reporte,String IdPabellon){
        reporte.setHorasAsignadasAulaLibre(cambioDeEstadoDAO.obtenerHorasAsignadasAulaLibreXPabellonUltimoMes(IdPabellon,this.conexion));
        reporte.setHorasDeLibreAReservada(cambioDeEstadoDAO.obtenerHorasDeLibreAReservadaXPabellonUltimoMes(IdPabellon,this.conexion));
        reporte.setHorasCambiadasADisponible(cambioDeEstadoDAO.obtenerHorasCambiadasADisponibleXPabellonUltimoMes(IdPabellon,this.conexion));
    }
        
    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    
    
    

