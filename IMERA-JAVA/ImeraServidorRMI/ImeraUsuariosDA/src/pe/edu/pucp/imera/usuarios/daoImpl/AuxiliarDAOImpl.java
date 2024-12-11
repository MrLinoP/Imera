package pe.edu.pucp.imera.usuarios.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.usuarios.dao.AuxiliarDAO;
import pe.edu.pucp.imera.usuarios.model.Auxiliar;
import pe.edu.pucp.imera.usuarios.model.Rol;
import pe.edu.pucp.imera.usuarios.model.Turno;

public class AuxiliarDAOImpl extends DAOImpl implements AuxiliarDAO{

    private Auxiliar auxiliar;

    public AuxiliarDAOImpl() {
        super("Persona");
        this.auxiliar = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return " codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo,contrasenha,rol";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        
        incluirParametroInt(1, this.auxiliar.getCodigoPucp());
        incluirParametroString(2, this.auxiliar.getCorreoPucp());
        incluirParametroString(3, this.auxiliar.getNombre());
        incluirParametroString(4, this.auxiliar.getApellidoP());
        incluirParametroString(5, this.auxiliar.getApellidoM());
        incluirParametroString(6, this.auxiliar.getIdPabellon());
        incluirParametroString(7, this.auxiliar.getTurno().toString());
        incluirParametroString(8, this.auxiliar.getActivo().toString());
        incluirParametroString(9, this.auxiliar.getContrasenha());
        incluirParametroString(10, this.auxiliar.getRol().toString());


    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "codigoPucp=?, correoPucp=?, nombre=?, apellidoP=?, apellidoM=?, idPabellon=?, turno=?, activo=?,contrasenha=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPersona=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        
        incluirParametroInt(1, this.auxiliar.getCodigoPucp());
        incluirParametroString(2, this.auxiliar.getCorreoPucp());
        incluirParametroString(3, this.auxiliar.getNombre());
        incluirParametroString(4, this.auxiliar.getApellidoP());
        incluirParametroString(5, this.auxiliar.getApellidoM());
        incluirParametroString(6, this.auxiliar.getIdPabellon());
        incluirParametroString(7, this.auxiliar.getTurno().toString());
        incluirParametroString(8, this.auxiliar.getActivo().toString());    
        incluirParametroString(9, this.auxiliar.getContrasenha());
        incluirParametroInt(10, this.auxiliar.getIdAuxiliar());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.auxiliar.getIdAuxiliar());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idPersona, codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo, contrasenha";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.auxiliar);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.auxiliar.getIdAuxiliar());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.auxiliar = new Auxiliar();
        this.auxiliar.setIdAuxiliar(resultSet.getInt("idPersona"));
        this.auxiliar.setCodigoPucp(resultSet.getInt("codigoPucp"));
        this.auxiliar.setCorreoPucp(resultSet.getString("correoPucp"));
        this.auxiliar.setNombre(resultSet.getString("nombre"));
        this.auxiliar.setApellidoP(resultSet.getString("apellidoP"));
        this.auxiliar.setApellidoM(resultSet.getString("apellidoM"));
        this.auxiliar.setIdPabellon(resultSet.getString("idPabellon"));
        this.auxiliar.setTurno(Turno.valueOf(resultSet.getString("turno")));
        Character act = 'S';
        if(resultSet.getString("activo").charAt(0) == 'N') act = 'N';
        this.auxiliar.setActivo(act);
        this.auxiliar.setContrasenha(resultSet.getString("contrasenha"));
        //this.auxiliar.setRol(Rol.valueOf(resultSet.getString("rol")));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.auxiliar = null;
    }

    @Override
    public Integer insertar(Auxiliar auxiliar) {
        this.auxiliar = auxiliar;
        return super.insertar();
    }

    @Override
    public Integer modificar(Auxiliar auxiliar) {
        this.auxiliar = auxiliar;
        return super.modificar();
    }
    
    
    @Override
    public Integer eliminar(Auxiliar auxiliar) {
        this.auxiliar = auxiliar;
        return super.eliminar();
    }

    @Override
    public ArrayList<Auxiliar> listarTodos() {
        this.predicadoParaListado=" where activo='S' and rol='AUXILIAR' ";
        return (ArrayList<Auxiliar>) super.listarTodos(null);
    }

    @Override
    public Auxiliar obtenerPorId(Integer idAuxiliar) {
        this.auxiliar = new Auxiliar();
        this.auxiliar.setIdAuxiliar(idAuxiliar);
        super.obtenerPorId();
        return this.auxiliar;
    }

}
