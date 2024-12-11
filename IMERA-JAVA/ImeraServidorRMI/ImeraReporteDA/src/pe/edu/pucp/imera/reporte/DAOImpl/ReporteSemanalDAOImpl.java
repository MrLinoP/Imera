package pe.edu.pucp.imera.reporte.DAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.reporte.DAO.ReporteSemanalDAO;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;


public class ReporteSemanalDAOImpl extends DAOImpl implements ReporteSemanalDAO{

    private ReporteSemanal reporteSemanal;
    private String idPabellon;
    public ReporteSemanalDAOImpl(){
        super("(select idPersona,codigoPucp,nombre,apellidoP,apellidoM from Persona where idPabellon=? and rol='AUXILIAR' and activo='S') a ");
        this.reporteSemanal=null;
        this.idPabellon=null;
    }
    
    @Override
    public ArrayList<ReporteSemanal> obtenerReporteSemanal(String idPabellon) {
        this.predicadoParaListado="";
        this.idPabellon=idPabellon;
        return (ArrayList<ReporteSemanal>)super.listarTodos(null);
    }
    @Override
    protected void incluirValorDeParametrosParaListado()throws SQLException{
        this.incluirParametroString(1, this.idPabellon);
        this.incluirParametroString(2, this.idPabellon);
    }
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.reporteSemanal);
    }
    @Override
    protected String incluirJoins(){
        return "LEFT JOIN CambioDeEstado c "+
                "ON a.idPersona = c.idPersona "+
                "AND c.idPabellon = ? "+
                "AND c.idDia >= DATE_SUB(curdate(), INTERVAL 7 DAY)"+
                " GROUP BY a.idPersona"+
                " ORDER BY a.idPersona";
    }

    
    @Override
    protected String obtenerProyeccionParaSelect() {
        return "a.codigoPucp, CONCAT(a.nombre, ' ', a.apellidoP, ' ', a.apellidoM) as nombre,"+ 
       "COALESCE(SUM(CASE WHEN c.nuevoEstado = 'AULA_LIBRE'    AND c.idDia >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) "+
                       "THEN 1 ELSE 0 END), 0) AS horasLibresUltimaSemana," +
       "COALESCE(SUM(CASE WHEN c.nuevoEstado = 'AULA_RESERVADA' AND c.estadoInicial = 'AULA_LIBRE' "+
                            "AND c.idDia >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) "+
                       "THEN 1 ELSE 0 END), 0) AS horasReservadasUltimaSemana";
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.reporteSemanal = new ReporteSemanal();
        this.reporteSemanal.setCodigoAuxiliar(resultSet.getInt("codigoPucp"));
        this.reporteSemanal.setNombre(resultSet.getString("nombre"));
        this.reporteSemanal.setHorasAsignadas(resultSet.getInt("horasLibresUltimaSemana"));
        this.reporteSemanal.setHorasDesasignadas(resultSet.getInt("horasReservadasUltimaSemana"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    


    
}
    