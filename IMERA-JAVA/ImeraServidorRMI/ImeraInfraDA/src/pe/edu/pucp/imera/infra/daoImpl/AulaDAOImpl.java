package pe.edu.pucp.imera.infra.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import pe.du.pucp.imera.infra.model.Aula;
import pe.edu.pucp.imera.config.DAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import pe.edu.pucp.imera.infra.dao.AulaDAO;

/**
 *
 * @author home
 */
public class AulaDAOImpl extends DAOImpl implements AulaDAO {

    private Aula aula;
    private String eliminar_idPabellon;
    private Integer tipoSeleccion;

    public AulaDAOImpl() {
        super("Aula");
        this.aula = null;
    }
     @Override
    public Integer insertar_con_parametros(Integer[] arrIdAula, Integer[] arrIdPiso, String[] arrIdPabellon, Integer[] arrAforo, Integer[] arrNumeroEnchufes) {
        Aula[] aulas = new Aula[arrIdAula.length];
        for (int i = 0; i < arrIdAula.length; i++) {
            // Crear un objeto Aula para cada índice y almacenarlo en el arreglo
            aulas[i] = new Aula(arrIdAula[i], arrIdPiso[i], arrIdPabellon[i], arrAforo[i], arrNumeroEnchufes[i]);
        }
        
        this.usarTransaccion = false;
        try {
            super.iniciarTransaccion();
            
            for (Aula aulaVari : aulas) {
                this.insertar(aulaVari, this.conexion, this.usarTransaccion);
            }
            
            this.statement = this.conexion.prepareCall(" call GenerarHorariosAulas ");
            this.statement.executeUpdate();
            
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar insertar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al intentar hacer rollback - " + ex1);
            }
        }finally{
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        this.usarTransaccion = true;
        return 1;
    }
     @Override
    public Integer insertar(Aula aula, Connection conexion, Boolean usarTransaccion) {
        this.aula = aula;
        this.conexion = conexion;
        this.usarTransaccion = usarTransaccion;
        return super.insertar();
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idAula, idPiso, idPabellon, aforo, numeroEnchufes, tieneHorarios";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.aula.getIdAula());
        this.incluirParametroInt(2, this.aula.getIdPiso());
        this.incluirParametroString(3, this.aula.getIdPabellon());
        this.incluirParametroInt(4, this.aula.getAforo());
        this.incluirParametroInt(5, this.aula.getNumeroEnchufes());
        this.incluirParametroInt(6, 0);
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return " idPabellon=?";
    }


    @Override
    protected String obtenerProyeccionParaSelect() {
        if(this.tipoSeleccion==0){
            return "idAula, idPiso, idPabellon";
        }else{
            return "idAula, idPiso, idPabellon, aforo, numeroEnchufes";
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        if(tipoSeleccion == 0){
            String cad = "";
            Integer idAula;
            cad += this.resultSet.getString("idPabellon");
            cad += this.resultSet.getInt("idPiso");
            idAula = this.resultSet.getInt("idAula");
            if(idAula < 10) cad += 0;
            cad += idAula;
            System.out.println(cad);
            lista.add(cad);
        }else{
            instanciarObjetoDelResultSet();//tengo a mi Piso ahora debería ponerle su arraylist de Aulas de mismo idPiso
            lista.add(this.aula);
        }
    }


    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.aula = new Aula();
        this.aula.setIdAula(this.resultSet.getInt("idAula"));
        this.aula.setIdPiso(this.resultSet.getInt("idPiso"));
        this.aula.setIdPabellon(this.resultSet.getString("idPabellon"));
        this.aula.setAforo(this.resultSet.getInt("aforo"));
        this.aula.setNumeroEnchufes(this.resultSet.getInt("numeroEnchufes"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.aula=null;
    }

    @Override
    public ArrayList<String> listarNombresAulasXPisoYPabellon(Integer idPiso, String idPabellon) {
        this.tipoSeleccion = 0;
        this.predicadoParaListado = " where idPiso = " + idPiso + " and idPabellon = '" + idPabellon + "'";
        return (ArrayList<String>) super.listarTodos(null);
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

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}