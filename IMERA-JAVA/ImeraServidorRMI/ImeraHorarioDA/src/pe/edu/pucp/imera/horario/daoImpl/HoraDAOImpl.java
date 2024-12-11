
package pe.edu.pucp.imera.horario.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.horario.dao.HoraDAO;

public class HoraDAOImpl extends DAOImpl implements HoraDAO {
    private String idPabellon;
    public HoraDAOImpl() {
        super("Hora h");
        this.idPabellon=null;

    }
    @Override
    public ArrayList<Integer> obtenerDistribucionHorasXPabellon(String idPabellon) {
        this.predicadoParaListado=" GROUP BY h.idHora ORDER BY h.idHora";
        this.idPabellon=idPabellon;
        return (ArrayList<Integer>)super.listarTodos(null);
    }
    @Override
    protected void incluirValorDeParametrosParaListado()throws SQLException{
        this.incluirParametroString(1, this.idPabellon);
    }
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        lista.add(this.resultSet.getInt("horasAsignadas"));
    }
    @Override
    protected String incluirJoins(){
        return " LEFT JOIN CambioDeEstado c ON h.idHora = c.idHora AND c.nuevoEstado"
                + "= 'AULA_LIBRE' AND c.idPabellon =? AND c.idDia >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) ";
    }

    
    @Override
    protected String obtenerProyeccionParaSelect() {
        return "COALESCE(COUNT(c.idHora), 0) as horasAsignadas";
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



    @Override
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
