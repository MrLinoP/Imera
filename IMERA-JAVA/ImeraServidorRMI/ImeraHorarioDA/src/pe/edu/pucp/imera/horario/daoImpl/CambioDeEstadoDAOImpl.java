/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.daoImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.horario.dao.CambioDeEstadoDAO;
import pe.edu.pucp.imera.horario.model.CambioDeEstado;
import pe.edu.pucp.imera.horario.model.EstadoAula;

/**
 *
 * @author julia
 */
public class CambioDeEstadoDAOImpl extends DAOImpl implements CambioDeEstadoDAO {
    
    private CambioDeEstado cambioEstado;
    
    public CambioDeEstadoDAOImpl() {
        super("CambioDeEstado");
        this.cambioEstado=null;
    }
    
    @Override
    public Integer insertar(CambioDeEstado cambioEstado) {
        this.cambioEstado = cambioEstado;
        //uso la conexión que me pasaron
        return super.insertar();
        //cierro conexion
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idPersona, idAula, idPiso, idPabellon, idHora, idDia, fechaCambio, estadoInicial, nuevoEstado";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, CURDATE(), NOW(), ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        incluirParametroInt(1, this.cambioEstado.getIdAuxiliar());
        incluirParametroInt(2, this.cambioEstado.getIdAula());
        incluirParametroInt(3, this.cambioEstado.getIdPiso());
        incluirParametroString(4, this.cambioEstado.getIdPabellon());
        incluirParametroInt(5, this.cambioEstado.getIdHora());
        incluirParametroString(6, this.cambioEstado.getEstadoInicial().toString());
        incluirParametroString(7, this.cambioEstado.getEstadoFinal().toString());
    }
    
    private Integer obtenerHorasEstadoXPabellonUltimoMes(String idPabellon, String predicado,Connection conexion) {
        Integer cantidadHoras = 0;
        try {
            // usar la conexión ya abierta
            this.conexion=conexion;
            String sql = generarSQLReporte(idPabellon,predicado);
            
            this.colocarSQLenStatement(sql);
            this.ejecutarConsultaEnBD(sql);
            // Obtener el resultado
            if (this.resultSet.next()) {
                cantidadHoras = this.resultSet.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener horas asignadas por pabellón en el último mes - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException rollbackEx) {
                System.err.println("Error al hacer rollback - " + rollbackEx);
            }
        } 
        return cantidadHoras;
    }
    private String generarSQLReporte(String idPabellon,String predicado){
        return  "SELECT COUNT(*) AS cantidad " +
                "FROM " + this.nombre_tabla + " " +
                "WHERE idPabellon = '" + idPabellon + "' " +
                "AND " + predicado + " AND idDia BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE()";
    }
    @Override
    public Integer obtenerHorasAsignadasAulaLibreXPabellonUltimoMes(String IdPabellon,Connection conexion){
        return obtenerHorasEstadoXPabellonUltimoMes(IdPabellon, "nuevoEstado = 'AULA_LIBRE'",conexion);
    }
    @Override
    public Integer obtenerHorasDeLibreAReservadaXPabellonUltimoMes(String IdPabellon,Connection conexion){
        return obtenerHorasEstadoXPabellonUltimoMes(IdPabellon, "(estadoInicial = 'AULA_LIBRE' and nuevoEstado = 'AULA_RESERVADA')",conexion);
    }
    @Override
    public Integer obtenerHorasCambiadasADisponibleXPabellonUltimoMes(String IdPabellon,Connection conexion){
        return obtenerHorasEstadoXPabellonUltimoMes(IdPabellon, "nuevoEstado = 'DISPONIBLE'",conexion);
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