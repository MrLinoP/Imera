package pe.edu.pucp.imera.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.imera.config.DBManager;

public abstract class DAOImpl<T> {

    protected String nombre_tabla;
    protected Connection conexion;
    protected PreparedStatement statement;
    protected ResultSet resultset;
    protected Boolean retornarLlavePrimaria;

    public DAOImpl(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
        this.retornarLlavePrimaria = false;
    }
    
    protected void abrirConexion() throws SQLException {
        this.conexion = DBManager.getInstance().getConnection();
    }
    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
        }
    }

    protected void iniciarTransaccion() throws SQLException {
        this.conexion = DBManager.getInstance().getConnection();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected Integer ejecutarModificacionesEnBD(String sql) throws SQLException {
        this.statement = this.conexion.prepareStatement(sql);
        return this.statement.executeUpdate(sql);
    }
    
    protected void ejecutarConsultaEnBD(String sql) throws SQLException {
        this.statement = this.conexion.prepareStatement(sql);
        this.resultset = this.statement.executeQuery(sql);
    }

    public Integer insertar() {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaInsercion();
            resultado = this.ejecutarModificacionesEnBD(sql);
            if (this.retornarLlavePrimaria){
                Integer id = this.retornarUltimoAutoGenerado();
                resultado = id;
            }
            this.comitarTransaccion();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    public Integer modificar(){
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLParaModificacion();
            resultado = this.ejecutarModificacionesEnBD(sql);
            this.comitarTransaccion();
        } catch (SQLException ex) {
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    private String generarSQLParaInsercion() {
        String sql = "insert into " + this.nombre_tabla;
        sql = sql.concat(" (");
        sql = sql.concat(this.obtenerListaDeAtributosParaInsert());
        sql = sql.concat(") values(");
        sql = sql.concat(this.obtenerListaDeValoresParaInsert());
        sql = sql.concat(")");
        return sql;
    }

    protected abstract String obtenerListaDeAtributosParaInsert();

    protected abstract String obtenerListaDeValoresParaInsert();

    protected Integer retornarUltimoAutoGenerado() throws SQLException {
        Integer resultado = null;
        String sql = "select @@last_insert_id as id";
        this.ejecutarConsultaEnBD(sql);
        if (this.resultset.next())
            resultado = this.resultset.getInt("id");
        return resultado;
    }

    private String generarSQLParaModificacion() {
        String sql = "update " + this.nombre_tabla;
        sql = sql.concat(" set ");
        sql = sql.concat(this.obtenerListaDeAtributosYValoresParaModificar());
        return sql;
    }

    protected abstract String obtenerListaDeAtributosYValoresParaModificar();
    
    protected ArrayList<T> listarTodos() {
        ArrayList<T> lista = new ArrayList<>();
        T object;
        try {
            iniciarTransaccion();
            String sql = generarSQLParaListarTodos();
            ejecutarConsultaEnBD(sql);
            while (resultset.next()) {
                object = agregarObjetoALaLista();
                lista.add(object);
            }
        } catch (SQLException ex) {
            try {
                rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    protected String generarSQLParaListarTodos() {
        String sql = "select " + obtenerProyeccionParaSelect() + " from " + this.nombre_tabla;
        return sql;
    }
    
    protected abstract String obtenerProyeccionParaSelect();
    
    protected abstract T agregarObjetoALaLista();
    
    protected T obtenerPorId(int idComprobante) {
        T objeto = null;
        try {
            iniciarTransaccion();
            String sql = generarSQLParaObtenerPorId();  // Método para construir el SQL con WHERE
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idComprobante);  // Aquí se usa el ID para setear el valor en el PreparedStatement
            resultset = statement.executeQuery();

            if (resultset.next()) {
                objeto = agregarObjetoALaLista();  // Extrae el objeto de ResultSet
            }
            comitarTransaccion();
        } catch (SQLException ex) {
            try {
                rollbackTransaccion();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objeto;
    }
    
    protected String generarSQLParaObtenerPorId() {
        return "SELECT " + obtenerProyeccionParaSelect() + " FROM " + nombre_tabla + " WHERE idComprobante = ?";
    }
}
