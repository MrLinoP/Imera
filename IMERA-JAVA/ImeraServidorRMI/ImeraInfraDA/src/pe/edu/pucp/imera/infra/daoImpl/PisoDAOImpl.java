/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.infra.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.du.pucp.imera.infra.model.Aula;
import pe.du.pucp.imera.infra.model.Piso;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.infra.dao.AulaDAO;
import pe.edu.pucp.imera.infra.dao.PisoDAO;

/**
 *
 * @author home
 */
public class PisoDAOImpl extends DAOImpl implements PisoDAO{

    private Piso piso;
    private String eliminar_idPabellon;
    private Integer tipoSelect;
    
    public PisoDAOImpl() {
        super("Piso");
        this.piso=null;
    }
    
    @Override
    public Integer insertar(Piso piso,Connection conexion, Boolean usarTransaccion){
        this.piso=piso;
        this.usarTransaccion=usarTransaccion;
        this.conexion=conexion;
        super.insertar();
        AulaDAO aulaDAO = new AulaDAOImpl();
        for (Aula aula : this.piso.getAulas()) {
            // Aquí puedes realizar operaciones con cada objeto 'piso'
            aulaDAO.insertar(aula,this.conexion,false);
        }
        return 1;
    } 

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idPiso, idPabellon, numeroAulas";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.piso.getIdPiso());
        this.incluirParametroString(2, this.piso.getIdPabellon());
        this.incluirParametroInt(3, this.piso.getNumeroAulas());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return " idPabellon=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroString(1, this.eliminar_idPabellon);
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        if(this.tipoSelect==0){
            return "idPiso";
        }else{
            return "idPiso, idPabellon, numeroAulas";
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
          if(this.tipoSelect==0){
              lista.add(this.resultSet.getInt("idPiso"));
          }else{
              instanciarObjetoDelResultSet();
              lista.add(this.piso);
          }
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.piso = new Piso();
        this.piso.setIdPiso(this.resultSet.getInt("idPiso"));
        this.piso.setIdPabellon(this.resultSet.getString("idPabellon"));
        this.piso.setNumeroAulas(this.resultSet.getInt("numeroAulas"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public ArrayList<Integer> listarNumerosDePisosXPabellon(String idPabellon) {
        this.tipoSelect = 0;
        this.predicadoParaListado = " where idPabellon = '" + idPabellon + "' order by idPiso";
        return (ArrayList<Integer>) super.listarTodos(null);
    }
    @Override
    public Integer insertarPiso(String idPabellon, Integer idPiso, String[] aulas, Integer[] aforos, Integer[] enchufes) {
        
        if (aulas == null || aforos == null || enchufes == null || aulas.length != aforos.length || aulas.length != enchufes.length) {
            throw new IllegalArgumentException("Los arreglos de aulas, aforos y enchufes deben tener el mismo tamaño y no pueden ser null.");
        }
        
        this.piso = new Piso(idPiso, idPabellon, 0);
        for (int i = 0; i < aulas.length; i++) {
            Aula aula = new Aula(Integer.valueOf(aulas[i]), idPiso, idPabellon, aforos[i], enchufes[i]);
            this.piso.getAulas().add(aula);
        }
        this.usarTransaccion = false;

        try {
            this.iniciarTransaccion();
            super.insertar();

            AulaDAO aulaDAO = new AulaDAOImpl();
            for (Aula aula : this.piso.getAulas()) {
                aulaDAO.insertar(aula,this.conexion,false);
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
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        return 1;
    }
}