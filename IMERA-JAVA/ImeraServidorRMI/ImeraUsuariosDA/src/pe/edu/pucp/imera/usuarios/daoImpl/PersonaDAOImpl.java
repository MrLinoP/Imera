/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.usuarios.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.usuarios.dao.PersonaDAO;
import pe.edu.pucp.imera.usuarios.model.Persona;
import pe.edu.pucp.imera.usuarios.model.Rol;

public class PersonaDAOImpl extends DAOImpl implements PersonaDAO{
    private Persona persona;

    public PersonaDAOImpl() {
        super("Persona");
        this.persona = null;
    }
    @Override
    public Persona obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha){
        this.persona=null;
        try {
            this.abrirConexion();
            String sql = "select idPersona,rol,idPabellon,activo,nombre,apellidoP,apellidoM,codigoPucp,correoPucp from Persona where ";
            sql = sql.concat("((correoPucp=? or codigoPucp=?) and contrasenha=?) ");
            this.colocarSQLenStatement(sql);
            incluirParametrosobtenerPorIdPorCorreo(DatosInicioSesion,contrasenha);
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe Persona - " + ex);
        } finally {
            try {
                
                this.cerrarConexion();
                
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }   
        return this.persona;
    }
    private void incluirParametrosobtenerPorIdPorCorreo(String DatosInicioSesion,String contrasenha) throws SQLException{
        this.incluirParametroString(1, DatosInicioSesion);
        this.incluirParametroString(2, DatosInicioSesion);
        this.incluirParametroString(3, contrasenha);
    }
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.persona = new Persona();
        persona.setIdPersona(this.resultSet.getInt("idPersona"));
        persona.setRol(Rol.valueOf(this.resultSet.getString("rol")));
        if(persona.getRol()==Rol.AUXILIAR){
            persona.setIdPabellon(this.resultSet.getString("idPabellon"));
            Character act = 'S';
            if(resultSet.getString("activo").charAt(0) == 'N') act = 'N';
            this.persona.setActivo(act);
        }
        persona.setCodigoPucp(this.resultSet.getInt("codigoPucp"));
        persona.setCorreoPucp(this.resultSet.getString("correoPucp"));
        persona.setNombre(this.resultSet.getString("nombre"));
        persona.setApellidoP(this.resultSet.getString("apellidoP"));
        persona.setApellidoM(this.resultSet.getString("apellidoM"));
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
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
