package pe.edu.pucp.imera.usuarios.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.imera.usuarios.dao.IntendenteDAO;
import pe.edu.pucp.imera.usuarios.model.Intendente;
import pe.edu.pucp.imera.config.DAOImpl;

public class IntendenteDAOImpl extends DAOImpl implements IntendenteDAO{
    
    private Intendente intendente;

    public IntendenteDAOImpl() {
        super("Persona");
        this.intendente = null;
    }
    @Override
    public Intendente obtenerPorId(Integer idIntendente) {
        this.intendente = new Intendente();
        this.intendente.setIdIntendente(idIntendente);
        super.obtenerPorId();
        return this.intendente;
    }
    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idPersona, codigoPucp, correoPucp, nombre, apellidoP, apellidoM";
    }
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPersona=?";
    }
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.intendente);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.intendente.getIdIntendente());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.intendente = new Intendente();
        this.intendente.setIdIntendente(resultSet.getInt("idPersona"));
        this.intendente.setCodigoPucp(resultSet.getInt("codigoPucp"));
        this.intendente.setCorreoPucp(resultSet.getString("correoPucp"));
        this.intendente.setNombre(resultSet.getString("nombre"));
        this.intendente.setApellidoP(resultSet.getString("apellidoP"));
        this.intendente.setApellidoM(resultSet.getString("apellidoM"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.intendente = null;
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
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   

    
    
}
