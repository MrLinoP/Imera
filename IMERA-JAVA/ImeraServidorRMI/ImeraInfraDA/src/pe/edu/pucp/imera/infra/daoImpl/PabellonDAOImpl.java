/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.infra.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.du.pucp.imera.infra.model.Aula;
import pe.du.pucp.imera.infra.model.Pabellon;
import pe.du.pucp.imera.infra.model.Piso;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.infra.dao.PabellonDAO;
import pe.edu.pucp.imera.infra.dao.PisoDAO;

/**
 *
 * @author home
 */
public class PabellonDAOImpl extends DAOImpl implements PabellonDAO {

    private Pabellon pabellon;
    public PabellonDAOImpl() {
        super("Pabellon");
        this.pabellon = null;
    }

     @Override
    public Integer insertar(String idPabellon, String nombre, Integer cantPisos, String[] aulas, Integer[] aforos, Integer[] enchufes) {
        this.pabellon = new Pabellon(idPabellon, 0, nombre);
        // Crear una lista para almacenar los pisos
        ArrayList<Piso> pisosCampo = new ArrayList<Piso>();

        // Recorremos las aulas
        for (int i = 0; i < aulas.length; i++) {
            String aulaNombre = aulas[i];
            Integer idPiso = Integer.valueOf(aulaNombre.substring(0, 1)); // Primer número: idPiso
            Integer idAula = Integer.valueOf(aulaNombre.substring(1));   // Restantes: idAula

            // Crear el objeto Aula
            Aula aulaFor = new Aula(idAula, idPiso, idPabellon, aforos[i], enchufes[i]);
            // Verificar si el piso ya existe en el pabellón
            Piso pisoExistente = null;
            for (Piso piso : pisosCampo) {
                if (piso.getIdPiso().equals(idPiso)) {
                    pisoExistente = piso;
                    break;  // Si ya encontramos el piso, salimos del bucle
                }
            }
            // Si el piso no existe, creamos un nuevo piso y lo agregamos
            if (pisoExistente == null) {
                pisoExistente = new Piso(idPiso, idPabellon, 0);
                pisosCampo.add(pisoExistente);
            }
            // Agregar el aula al piso correspondiente
            pisoExistente.getAulas().add(aulaFor);  // Suponiendo que Piso tiene un método agregarAula()
        }

        // Ahora puedes agregar los pisos al pabellón
        for (Piso piso : pisosCampo) {
            pabellon.getPisos().add(piso);  // Suponiendo que Pabellon tiene un método agregarPiso()
        }
        //this.pabellon.setNumeroPisos(pisosCampo.size());
        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            super.insertar();
            PisoDAO pisoDAO = new PisoDAOImpl();
            for (Piso piso : this.pabellon.getPisos()) {
                // Aquí puedes realizar operaciones con cada objeto 'piso'
                pisoDAO.insertar(piso, this.conexion, false);
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
        this.usarTransaccion = true;
        return 1;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idPabellon, numeroPisos, nombre";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.pabellon.getIdPabellon());
        this.incluirParametroInt(2, this.pabellon.getNumeroPisos());
        this.incluirParametroString(3, this.pabellon.getNombre());

    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPabellon=?";
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.pabellon);
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idPabellon, numeroPisos, nombre";
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.pabellon = new Pabellon();
        this.pabellon.setIdPabellon(this.resultSet.getString("idPabellon"));
        this.pabellon.setNumeroPisos(this.resultSet.getInt("numeroPisos"));
        this.pabellon.setNombre(this.resultSet.getString("nombre"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.pabellon=null;
    }

    @Override
    public ArrayList<Pabellon> listarTodos() {
        this.predicadoParaListado="";
        return (ArrayList<Pabellon>) super.listarTodos(null);
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