/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.horario.dao.HorarioAuxiliarDAO;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.HorarioAuxiliar;

/**
 *
 * @author julia
 */
public class HorarioAuxiliarDAOImpl extends DAOImpl implements HorarioAuxiliarDAO{
    private HorarioAuxiliar horario;
    private String pabellonFiltro;
    private Integer pisoFiltro;
    
    public HorarioAuxiliarDAOImpl() {
        super("Horario");
        this.horario=null;
        this.pabellonFiltro=null;
        this.pisoFiltro=null;
    }
    
    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "Horario.idPabellon, Horario.idPiso, Horario.idAula, ";
        sql = sql.concat("Horario.estado, Hora.horaIni, Hora.horaFin ");
        return sql;
    }
    
    @Override
    protected String incluirJoins(){
        String sql = " inner join Hora on Horario.idHora = Hora.idHora ";
        return sql;
    }
    
    @Override
    public ArrayList<HorarioAuxiliar> listarTodos(String idPabellon, Integer idPiso) {
        this.predicadoParaListado = " where idPabellon = ? ";
        this.predicadoParaListado = this.predicadoParaListado.concat("and Horario.idPiso = ? and Horario.idDia=CURDATE() ");
        this.pabellonFiltro = idPabellon;
        this.pisoFiltro = idPiso;
        return (ArrayList<HorarioAuxiliar>) super.listarTodos(null);
    }
    
    private String convertirHora(Integer hora){
        String nombre="";
        if(hora<10){
            nombre="0";
        }
        nombre = nombre.concat(hora.toString());
        nombre = nombre.concat(":00");
        return nombre;
    }
    
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.horario = new HorarioAuxiliar();
        String idPabellon, hIni, hFin, nAula="";
        Integer horaIni, horaFin, idPiso, idAula;
        
        idPabellon = this.resultSet.getString("Horario.idPabellon");
        idPiso = this.resultSet.getInt("Horario.idPiso");
        idAula = this.resultSet.getInt("Horario.idAula");
        horaIni = this.resultSet.getInt("Hora.horaIni");
        horaFin = this.resultSet.getInt("Hora.horaFin");
        hIni = convertirHora(horaIni);
        hFin = convertirHora(horaFin);
        if(idAula<10){
            nAula="0";
        }
        nAula = nAula.concat(idAula.toString());
        this.horario.setEstado(EstadoAula.valueOf(this.resultSet.getString("Horario.estado")));
        this.horario.setNombreAula(idPabellon + idPiso.toString() + nAula);
        this.horario.setHorario(hIni + " - " + hFin);
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.horario);
    }
    
    @Override
    protected void incluirValorDeParametrosParaListado() throws SQLException{
        this.incluirParametroString(1, this.pabellonFiltro);
        this.incluirParametroInt(2, this.pisoFiltro);
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
}
