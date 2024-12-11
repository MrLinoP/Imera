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
import pe.edu.pucp.imera.db.DBManager;
import pe.edu.pucp.imera.horario.dao.HorarioIntendenteDAO;
import pe.edu.pucp.imera.horario.model.DiaSemana;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;

/**
 *
 * @author home
 */
public class HorarioIntendenteDAOImpl extends DAOImpl implements HorarioIntendenteDAO {

    private HorarioIntendente horario;
    private String pabellonFiltro;
    private Integer diaFiltro;
    private Boolean abrirConex;
    private Boolean cerrarConex;
    private Boolean disponibleASemestral;
    private String estadoListado;
    private String condicionPabellonListado;
    private Boolean muestraSemestrales;

    public HorarioIntendenteDAOImpl() {
        super("Horario");
        this.horario = null;
        this.pabellonFiltro = null;
        this.diaFiltro = null;
        this.abrirConex = true;
        this.cerrarConex = true;
        this.disponibleASemestral = true;
        this.estadoListado=null;
        this.muestraSemestrales=false;
        this.condicionPabellonListado=null;
    }
    
    @Override
    public Integer modificarDisponibleASemestral(ArrayList<HorarioIntendente> horariosIntendente) {
        Integer resultado=0;
        this.abrirConex = true;
        this.cerrarConex = false;
        this.disponibleASemestral = true;
        for(int i=0;i<horariosIntendente.size();i++){
            if(i==horariosIntendente.size()-1){
                this.cerrarConex = true;
            }
            this.horario = horariosIntendente.get(i);
            resultado = super.modificar();
        }
        return resultado;
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "estado=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPabellon=? and idPiso=? and idAula=? and (idHora between ? and ?) and DAYOFWEEK(idDia)=?";
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        EstadoAula estadoAux = EstadoAula.AULA_LIBRE_SEMESTRAL;
        if(!this.disponibleASemestral)
            estadoAux = EstadoAula.DISPONIBLE;
        String[] partes = this.horario.getHorario().split("-");
        String horaInicio = partes[0].split(":")[0]; // Extraer la hora de inicio
        String horaFin = partes[1].split(":")[0];   // Extraer la hora de fin
         // Convertir a enteros
        Integer inicio = Integer.valueOf(horaInicio.trim());
        Integer fin = Integer.valueOf(horaFin.trim());
        String nombreAula = this.horario.getNombreAula();
        String idPab = nombreAula.substring(0, 1);
        Integer idPiso = Integer.valueOf(nombreAula.substring(1, 2));
        Integer idAula = Integer.valueOf(nombreAula.substring(2));
        Integer numDia = this.obtenerNombreDelDia(this.horario.getDiaSemana());
        this.incluirParametroString(1, estadoAux.toString());
        this.incluirParametroString(2, idPab);
        this.incluirParametroInt(3, idPiso);
        this.incluirParametroInt(4, idAula);
        this.incluirParametroInt(5, inicio-6);
        this.incluirParametroInt(6, fin-7);
        this.incluirParametroInt(7, numDia);
        
    }
    
    @Override
    public Integer modificarSemestralADisponible(HorarioIntendente horario) {
       this.abrirConex = true;
       this.cerrarConex = true;
       this.disponibleASemestral = false;
       this.horario = horario;
       return super.modificar();
    }
    
    private Integer obtenerNombreDelDia(DiaSemana diaSemana) {
        Integer resultado = -1;
        switch(diaSemana){
            case LUNES -> resultado = 2;
            case MARTES -> resultado = 3;
            case MIERCOLES -> resultado = 4;
            case JUEVES -> resultado = 5;
            case VIERNES -> resultado = 6;
            case SABADO -> resultado = 7;
            default -> throw new AssertionError(diaSemana.name());

        }
        return resultado;
    }
    @Override
    public ArrayList<HorarioIntendente> listarTodosDisponibles(String idPabellon){
        this.estadoListado=EstadoAula.DISPONIBLE.toString();
        this.muestraSemestrales=false;
        return this.listarTodos(idPabellon);
    }
    @Override
    public ArrayList<HorarioIntendente> listarTodosSemestrales(){
        this.estadoListado=EstadoAula.AULA_LIBRE_SEMESTRAL.toString();
        this.muestraSemestrales=true;
        return this.listarTodos("");
    }
    
    private ArrayList<HorarioIntendente> listarTodos(String idPabellon) {

        this.pabellonFiltro = idPabellon;                   //AND DAYOFWEEK(Horario.idDia) = ?
        this.condicionPabellonListado="";
        if(!this.muestraSemestrales)
            this.condicionPabellonListado="where Horario.idPabellon=? ";
        
        this.predicadoParaListado = this.condicionPabellonListado+" GROUP BY Horario.idPabellon,"
                + " Horario.idPiso, Horario.idAula, Hora.horaIni, Hora.horaFin, DAYOFWEEK(idDia)"
                + "HAVING COUNT(CASE WHEN Horario.estado <> '"+this.estadoListado+"' THEN 1 END) = 0 ";
        
        ArrayList<HorarioIntendente> listaHorarios = (ArrayList<HorarioIntendente>) super.listarTodos(null);
        return listaHorarios;
    }

    //Sobrecarga de abrir conexión necsearia para este daoImpl para hacer varios listar en una conexión
    @Override
    protected void abrirConexion() {
        if (this.abrirConex) {
            this.conexion = DBManager.getInstance().getConnection();
        }
    }

    //Sobrecarga de cerrar conexión necsearia para este daoImpl para hacer varios listar en una conexión
    @Override
    protected void cerrarConexion() throws SQLException {
        if (this.cerrarConex) {
            if (this.conexion != null) {
                this.conexion.close();
                this.conexion = null;
            }
        }
    }

    @Override
    protected void incluirValorDeParametrosParaListado() throws SQLException {
        if(!this.muestraSemestrales)
            this.incluirParametroString(1, this.pabellonFiltro);
        //this.incluirParametroInt(2, this.diaFiltro);
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
        instanciarObjetoDelResultSet();
        lista.add(this.horario);
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return " Horario.idPabellon, Horario.idPiso, Horario.idAula, Hora.horaIni, Hora.horaFin, DAYOFWEEK(idDia) as DiaSemana";
    }

    @Override
    protected String incluirJoins() {
        String sql = " inner join Hora on Horario.idHora = Hora.idHora ";
        return sql;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String convertirHora(Integer hora) {
        String nombre = "";
        if (hora < 10) {
            nombre = "0";
        }
        nombre = nombre.concat(hora.toString());
        nombre = nombre.concat(":00");
        return nombre;
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.horario = new HorarioIntendente();
        String idPabellon, hIni, hFin, nAula = "";
        Integer horaIni, horaFin, idPiso, idAula;

        idPabellon = this.resultSet.getString("Horario.idPabellon");
        idPiso = this.resultSet.getInt("Horario.idPiso");
        idAula = this.resultSet.getInt("Horario.idAula");
        horaIni = this.resultSet.getInt("Hora.horaIni");
        horaFin = this.resultSet.getInt("Hora.horaFin");
        hIni = convertirHora(horaIni);
        hFin = convertirHora(horaFin);
        if (idAula < 10) {
            nAula = "0";
        }
        nAula = nAula.concat(idAula.toString());
        this.horario.setNombreAula(idPabellon + idPiso.toString() + nAula);
        this.horario.setHorario(hIni + " - " + hFin);
        //this.diaFiltroDiaSemana
        switch (this.resultSet.getInt("DiaSemana")) {
            case 2 -> this.horario.setDiaSemana(DiaSemana.LUNES);
            case 3 -> this.horario.setDiaSemana(DiaSemana.MARTES);
            case 4 -> this.horario.setDiaSemana(DiaSemana.MIERCOLES);
            case 5 -> this.horario.setDiaSemana(DiaSemana.JUEVES);
            case 6 -> this.horario.setDiaSemana(DiaSemana.VIERNES);
            case 7 -> this.horario.setDiaSemana(DiaSemana.SABADO);
            default -> System.out.println("Número de día inválido");
        }
        
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}