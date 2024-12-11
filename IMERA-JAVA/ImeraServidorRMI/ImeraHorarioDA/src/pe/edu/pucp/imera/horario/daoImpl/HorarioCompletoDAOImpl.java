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
import pe.edu.pucp.imera.horario.model.HorarioCompleto;
import pe.edu.pucp.imera.horario.dao.HorarioCompletoDAO;

/**
 *
 * @author julia
 */
public class HorarioCompletoDAOImpl extends DAOImpl implements HorarioCompletoDAO {

    private HorarioCompleto horario;
    
    public HorarioCompletoDAOImpl() {
        super("Horario");
        this.horario=null;
    }
    
    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "Horario.idPabellon, Horario.idPiso, ";
        sql = sql.concat("Horario.idAula, Hora.horaIni, Hora.horaFin, ");
        sql = sql.concat("Aula.aforo, Aula.numeroEnchufes");
        return sql;
    }
    
    @Override
    protected String incluirJoins(){
        String sql = " inner join Hora on Horario.idHora = Hora.idHora ";
        sql = sql.concat("inner join Aula on (Horario.idAula = Aula.idAula and Horario.idPabellon=Aula.idPabellon and Horario.idPiso=Aula.idPiso)");
        return sql;
    }
    
    @Override
    public ArrayList<HorarioCompleto> listarTodosAlumno() {
        this.predicadoParaListado = " where (Horario.estado='AULA_LIBRE_SEMESTRAL' ";
        this.predicadoParaListado = this.predicadoParaListado.concat("or Horario.estado='AULA_LIBRE') and Horario.idDia=CURDATE() ");
        
        return (ArrayList<HorarioCompleto>) super.listarTodos(null);
    }
    
    @Override
    public ArrayList<HorarioCompleto> listarTodosIntendente() {
        this.predicadoParaListado = " where Horario.estado='AULA_LIBRE_SEMESTRAL' ";
        this.predicadoParaListado = this.predicadoParaListado.concat("group by Horario.idPabellon, Horario.idPiso, Horario.idAula, Horario.idHora ");
        
        return (ArrayList<HorarioCompleto>) super.listarTodos(null);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.horario = new HorarioCompleto();
        
        this.horario.setIdPabellon(this.resultSet.getString("Horario.idPabellon"));
        this.horario.setIdPiso(this.resultSet.getInt("Horario.idPiso"));
        this.horario.setIdAula(this.resultSet.getInt("Horario.idAula"));
        this.horario.setHoraIni(this.resultSet.getInt("Hora.horaIni"));
        this.horario.setHoraFin(this.resultSet.getInt("Hora.horaFin"));
        this.horario.setAforo(this.resultSet.getInt("Aula.aforo"));
        this.horario.setCantEnchufes(this.resultSet.getInt("Aula.numeroEnchufes"));
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.horario);
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
