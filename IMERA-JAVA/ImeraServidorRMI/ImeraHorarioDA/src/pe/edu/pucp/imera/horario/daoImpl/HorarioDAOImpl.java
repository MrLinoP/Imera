/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.daoImpl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.imera.config.DAOImpl;
import pe.edu.pucp.imera.horario.dao.HorarioDAO;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.Horario;

/**
 *
 * @author home
 */
public class HorarioDAOImpl extends DAOImpl implements HorarioDAO{
    private Horario horario;
    protected String idDiaValor;
    public HorarioDAOImpl() {
        super("Horario");
        this.horario=null;
        this.idDiaValor="curdate()";
    }
    @Override
    public Integer modificar(Horario horario) {
        this.horario = horario;
        //abrir conexion
        //mandar al modificar
        return super.modificar();
        //llamar al dao de cambioDeEstado para que inserte y que use la misma conexión
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "estado=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPabellon=? and idPiso=? and idAula=? and idHora=? and idDia="+this.idDiaValor;
    }


    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        incluirParametroString(1, this.horario.getEstado().toString());
        incluirParametroString(2, this.horario.getIdPabellon());
        incluirParametroInt(3, this.horario.getIdPiso());
        incluirParametroInt(4, this.horario.getIdAula());
        incluirParametroInt(5, this.horario.getIdHora());
        if("?".equals(this.idDiaValor)){
            incluirParametroDate(6, this.horario.getIdDia());
        }
    }
    
    @Override
    public Integer modificarUsandoTransaccion(Horario horario) {
        return this.modificar(horario);
    }

    @Override
    public Integer modificarHorariosClase(Horario[] horario, String[] fechas) {
        Integer resultado=0;
        this.usarTransaccion = false;
        this.idDiaValor="?";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate;
            this.iniciarTransaccion();
            for(int i=0; i<horario.length;i++){
                // Convierte el String a java.util.Date
                utilDate = formato.parse(fechas[i]);
                // Convierte java.util.Date a java.sql.Date
                Date sqlDate = new Date(utilDate.getTime());
                horario[i].setIdDia(sqlDate);
                horario[i].setEstado(EstadoAula.AULA_CLASE);
                resultado = this.modificarUsandoTransaccion(horario[i]);
            }
            this.comitarTransaccion();
            this.cerrarConexion();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(HorarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.usarTransaccion = true;
        this.idDiaValor="curdate()";
        return resultado;
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
