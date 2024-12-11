/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.imera.servicios;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.du.pucp.imera.infra.model.Pabellon;

import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.HorarioAuxiliar;
import pe.edu.pucp.imera.horario.model.HorarioCompleto;
import pe.edu.pucp.imera.horario.model.HorarioIntendente;
import pe.edu.pucp.imera.interfaces.AulaBO;
import pe.edu.pucp.imera.interfaces.AuxiliarBO;
import pe.edu.pucp.imera.interfaces.CambioDeEstadoBO;
import pe.edu.pucp.imera.interfaces.HoraBO;
import pe.edu.pucp.imera.interfaces.HorarioAuxiliarBO;
import pe.edu.pucp.imera.interfaces.HorarioBO;
import pe.edu.pucp.imera.interfaces.HorarioCompletoBO;
import pe.edu.pucp.imera.interfaces.HorarioIntendenteBO;
import pe.edu.pucp.imera.interfaces.PabellonBO;
import pe.edu.pucp.imera.interfaces.PersonaBO;
import pe.edu.pucp.imera.interfaces.PisoBO;
import pe.edu.pucp.imera.interfaces.ReporteMensualBO;
import pe.edu.pucp.imera.interfaces.ReporteSemanalBO;

import pe.edu.pucp.imera.reporte.model.ReporteMensual;
import pe.edu.pucp.imera.reporte.model.ReporteSemanal;
import pe.edu.pucp.imera.servidor.ImeraServidorRMI;


import pe.edu.pucp.imera.usuarios.model.Auxiliar;
import pe.edu.pucp.imera.usuarios.model.Persona;
import pe.edu.pucp.imera.usuarios.model.Rol;
import pe.edu.pucp.imera.usuarios.model.Turno;
import pe.edu.pucp.imera.util.CifradoCesar;

@WebService(serviceName = "ImeraServicio")
public class ImeraServicio {
    
    private AuxiliarBO auxiliarBO;
    private HorarioBO horarioBO;
    private PersonaBO personaBO;
    private HorarioCompletoBO horarioCompletoBO;
    private HorarioAuxiliarBO horarioAuxiliarBO;
    private PabellonBO pabellonBO;
    private AulaBO aulaBO;
    private PisoBO pisoBO;
    private CambioDeEstadoBO cambioEstadoBO;
    private HorarioIntendenteBO horarioIntendenteBO;
    private ReporteMensualBO reporteMensualBO;
    private ReporteSemanalBO reporteSemanalBO;
    private HoraBO horaBO;
    
    public ImeraServicio(){
        String nombreServicio;
        try {
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("auxiliarBO");
            this.auxiliarBO=(AuxiliarBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("horarioBO");
            this.horarioBO=(HorarioBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("personaBO");
            this.personaBO=(PersonaBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("horarioCompletoBO");
            this.horarioCompletoBO=(HorarioCompletoBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("horarioAuxiliarBO");
            this.horarioAuxiliarBO=(HorarioAuxiliarBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("pabellonBO");
            this.pabellonBO=(PabellonBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("aulaBO");
            this.aulaBO=(AulaBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("pisoBO");
            this.pisoBO=(PisoBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("cambioEstadoBO");
            this.cambioEstadoBO=(CambioDeEstadoBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("horarioIntendenteBO");
            this.horarioIntendenteBO=(HorarioIntendenteBO)Naming.lookup(nombreServicio);
            
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("reporteMensualBO");
            this.reporteMensualBO=(ReporteMensualBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("reporteSemanalBO");
            this.reporteSemanalBO=(ReporteSemanalBO)Naming.lookup(nombreServicio);
            
            nombreServicio=ImeraServidorRMI.retornarNombreDelServicio("horaBO");
            this.horaBO=(HoraBO)Naming.lookup(nombreServicio);
            
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @WebMethod(operationName = "auxiliar_insertar")
    public Integer auxiliar_insertar(@WebParam(name = "codigoPucp") Integer codigoPucp, @WebParam(name = "correoPucp") String correoPucp, 
            @WebParam(name = "nombre")String nombre,@WebParam(name = "apellidoP") String apellidoP,@WebParam(name = "apellidoM") String apellidoM,
            @WebParam(name = "idPabellon") String idPabellon, @WebParam(name = "turno") Turno turno, @WebParam(name = "activo") Character activo,
            @WebParam(name = "contrasenha") String contrasenha,@WebParam(name = "rol")Rol rol) {
        Integer resultado=null;
        try {
            resultado= auxiliarBO.insertar(codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo,contrasenha,rol);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "auxiliar_modificar")
    public Integer auxiliar_modificar(@WebParam(name = "idAuxiliar")Integer idAuxiliar,@WebParam(name = "codigoPucp") Integer codigoPucp, @WebParam(name = "correoPucp") String correoPucp, 
            @WebParam(name = "nombre")String nombre,@WebParam(name = "apellidoP") String apellidoP,@WebParam(name = "apellidoM") String apellidoM,
            @WebParam(name = "idPabellon") String idPabellon, @WebParam(name = "turno") Turno turno, @WebParam(name = "activo") Character activo,
            @WebParam(name = "contrasenha") String contrasenha) {
        Integer resultado=null;
        try {
             resultado=auxiliarBO.modificar(idAuxiliar,codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo,contrasenha);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "auxiliar_listarTodos")
    public ArrayList<Auxiliar> auxiliar_listarTodos() {
        ArrayList<Auxiliar> resultado=null;
        try {
            resultado= auxiliarBO.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "auxiliar_obtenerPorId")
    public Auxiliar auxiliar_obtenerPorId(@WebParam(name = "idAuxiliar")Integer idAuxiliar) {
        Auxiliar resultado=null;
        try {
            resultado= auxiliarBO.obtenerPorId(idAuxiliar);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioAuxiliar_listarTodos")
    public ArrayList<HorarioAuxiliar> horarioAuxiliar_listarTodos(@WebParam(name = "idPabellon")String idPabellon, @WebParam(name = "idPiso") Integer idPiso){
        ArrayList<HorarioAuxiliar> resultado=null;
        try {
            resultado= horarioAuxiliarBO.listar_todos(idPabellon, idPiso);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioCompletoAlumno_listarTodos")
    public ArrayList<HorarioCompleto> horarioCompletoAlumno_listarTodos(){
        ArrayList<HorarioCompleto> resultado=null;
        try {
            resultado= horarioCompletoBO.listar_todos_alumno();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioCompletoIntendente_listarTodos")
    public ArrayList<HorarioCompleto> horarioCompletoIntendente_listarTodos(){
        ArrayList<HorarioCompleto> resultado=null;
        try {
            resultado= horarioCompletoBO.listar_todos_intendente();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "loginIntendenteAuxiliar")
    public Persona loginIntendenteAuxiliar(@WebParam(name = "DatosInicioSesion")String DatosInicioSesion,
            @WebParam(name = "contrasenha")String contrasenha){
        Persona resultado=null;
        try {
            resultado= personaBO.obtenerPorIdPorCorreo_Codigo(DatosInicioSesion, CifradoCesar.descifrar(contrasenha));
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "pabellon_listar_todos")
    public ArrayList<Pabellon> pabellon_listar_todos(){
        ArrayList<Pabellon> resultado=null;
        try {
            resultado= pabellonBO.listar_todos();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "piso_listarNumeroPisos")
    public ArrayList<Integer> piso_listarNumeroPisos(@WebParam(name = "idPabellon")String idPabellon){
        ArrayList<Integer> resultado=null;
        try {
            resultado= pisoBO.listarNumerosDePisosXPabellon(idPabellon);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "aula_listarNombresAulasXPisoYPabellon")
    public ArrayList<String> aula_listarNombresAulasXPisoYPabellon(@WebParam(name = "idPiso")Integer idPiso,@WebParam(name = "idPabellon")String idPabellon){
        ArrayList<String> resultado=null;
        try {
            resultado= aulaBO.listarNombresAulasXPisoYPabellon(idPiso, idPabellon);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horario_modificar")
    public Integer horario_modificar(@WebParam(name = "idPabellon")String idPabellon,@WebParam(name = "idPiso") Integer idPiso, 
            @WebParam(name = "idAula")Integer idAula, @WebParam(name = "idHora")Integer idHora, 
            @WebParam(name = "estado")EstadoAula estado){
        Integer resultado=null;
        try {
            resultado= horarioBO.modificar(idPabellon,  idPiso,  idAula,  idHora,  estado);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "cambioEstado_insertar")
    public Integer cambioEstado_insertar(@WebParam(name = "idPabellon")String idPabellon, @WebParam(name = "idPiso")Integer idPiso, 
            @WebParam(name = "idAula")Integer idAula, @WebParam(name = "idHora")Integer idHora, @WebParam(name = "estadoInicial")EstadoAula estadoInicial, 
            @WebParam(name = "estadoFinal")EstadoAula estadoFinal, @WebParam(name = "idAuxiliar")Integer idAuxiliar){
        Integer resultado=null;
        try {
            resultado= cambioEstadoBO.insertar(idPabellon,  idPiso,  idAula, idHora, estadoInicial, estadoFinal,  idAuxiliar);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horario_modificarAulasClase")
    public Integer horario_modificarAulasClase(@WebParam(name = "horarios")String[] idPabellones,@WebParam(name = "idPisos")Integer[] idPisos,
            @WebParam(name = "idAulas")Integer[] idAulas,@WebParam(name = "idHoras")Integer[] idHoras, @WebParam(name = "fechas")String[] fechas){
        Integer resultado=null;
        try {
            resultado= horarioBO.modificarAulasClase(idPabellones,idPisos,idAulas,idHoras,fechas);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioIntendente_listar_todosSemestrales")
    public ArrayList<HorarioIntendente> horarioIntendente_listar_todosSemestrales(){
        ArrayList<HorarioIntendente> resultado=null;
        try {
            resultado= horarioIntendenteBO.listar_todosSemestrales();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioIntendente_listar_todosIntendente")
    public ArrayList<HorarioIntendente> horarioIntendente_listar_todosDisponibles(@WebParam(name = "idPabellon")String idPabellon){
        ArrayList<HorarioIntendente> resultado=null;
        try {
            resultado= horarioIntendenteBO.listar_todosDisponibles(idPabellon);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioIntendente_asignarAulaLibreSemestral")
    public Integer modificarDisponibleASemestral(@WebParam(name = "aulas")String [] aulas, @WebParam(name = "horarios")String [] horarios,
                                                @WebParam(name = "dias")String [] dias){
        Integer resultado=null;
        try {
            resultado= horarioIntendenteBO.modificarDisponibleASemestral(aulas,horarios,dias);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "horarioIntendente_eliminarAulaLibreSemestral")
    public Integer modificarSemestralADisponible(@WebParam(name = "aula")String aula,@WebParam(name = "horario") String horario, 
            @WebParam(name = "dia")String dia){
        Integer resultado=null;
        try { 
            resultado= horarioIntendenteBO.modificarSemestralADisponible(aula,horario,dia);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "pabellon_insertarPabellon")
    public Integer pabellon_insertarPabellon(@WebParam(name = "idPabellon")String  idPabellon,@WebParam(name = "nombre")String nombre, 
                 @WebParam(name = "cantPisos")Integer cantPisos, @WebParam(name = "aulas")String [] aulas,@WebParam(name = "aforos")
                         Integer [] aforos,@WebParam(name = "enchufes")Integer [] enchufes){
        Integer resultado=null;
        try {
            resultado= pabellonBO.insertar(idPabellon, nombre, cantPisos, aulas, aforos, enchufes);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "piso_insertarPiso")
    public Integer piso_insertarPiso(@WebParam(name = "idPabellon")String  idPabellon,@WebParam(name = "idPiso")Integer idPiso, 
                  @WebParam(name = "aulas")String [] aulas,@WebParam(name = "aforos")
                         Integer [] aforos,@WebParam(name = "enchufes")Integer [] enchufes){
        Integer resultado=null;
        try {
            resultado= pisoBO.insertar(idPabellon, idPiso, aulas, aforos, enchufes);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    @WebMethod(operationName = "aula_insertarAulas")
    public Integer aula_insertarAulas(@WebParam(name = "idAulas")Integer []  idAulas,@WebParam(name = "idPisos")Integer []  idPisos, 
                  @WebParam(name = "idPabellones")String [] idPabellones,@WebParam(name = "aforos")
                         Integer [] aforos,@WebParam(name = "enchufes")Integer [] enchufes){
        Integer resultado=null;
        try {
            resultado= aulaBO.insertar(idAulas, idPisos, idPabellones, aforos, enchufes);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    @WebMethod(operationName = "reporte_reporteMensual")
    public ArrayList<ReporteMensual> reporte_reporteMensual(){
        ArrayList<ReporteMensual> resultado=null;
        try {
            resultado= reporteMensualBO.reporteMensualPabellones();
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    @WebMethod(operationName = "reporte_reporteSemanal")
    public ArrayList<ReporteSemanal> reporte_reporteSemanal(@WebParam(name = "idPabellon")String idPabellon){
        ArrayList<ReporteSemanal> resultado=null;
        try {
            resultado= reporteSemanalBO.reporteSemanalAulas(idPabellon);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    @WebMethod(operationName = "reporte_distribucionHorasSemanal")
    public ArrayList<Integer> reporte_distribucionHorasSemanal(@WebParam(name = "idPabellon")String idPabellon){
        ArrayList<Integer> resultado=null;
        try {
            resultado= horaBO.obtenerDistribucionHorasXPabellon(idPabellon);
        } catch (RemoteException ex) {
            Logger.getLogger(ImeraServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
