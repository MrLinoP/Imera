package pe.edu.pucp.imera.usuarios.bo;
import java.util.ArrayList;
import pe.edu.pucp.imera.usuarios.dao.AuxiliarDAO;
import pe.edu.pucp.imera.usuarios.daoImpl.AuxiliarDAOImpl;
import pe.edu.pucp.imera.usuarios.model.Auxiliar;
import pe.edu.pucp.imera.usuarios.model.Rol;
import pe.edu.pucp.imera.usuarios.model.Turno;
import pe.edu.pucp.imera.util.Cifrado;

public class AuxiliarBO{
    
    private final AuxiliarDAO auxiliarDAO;
    
    public AuxiliarBO(){
        this.auxiliarDAO = new AuxiliarDAOImpl();
    }
    
    public Integer insertar(Integer codigoPucp, String correoPucp, String nombre, String apellidoP, 
            String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha,Rol rol){
        Auxiliar auxiliar = new Auxiliar(codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo,Cifrado.cifrarMD5(contrasenha),rol);
        return auxiliarDAO.insertar(auxiliar);
    }
    
    public Integer modificar(Integer idAuxiliar, Integer codigoPucp, String correoPucp, String nombre, 
            String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha){
        Auxiliar auxiliar = new Auxiliar(idAuxiliar, codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, turno, activo,Cifrado.cifrarMD5(contrasenha),null);
        return auxiliarDAO.modificar(auxiliar);
    }
    
    public Integer eliminar(Integer idAuxiliar){
        Auxiliar auxiliar = new Auxiliar();
        auxiliar.setIdAuxiliar(idAuxiliar);
        return auxiliarDAO.eliminar(auxiliar);
    }
    
    public ArrayList<Auxiliar> listarTodos(){
        return auxiliarDAO.listarTodos();
    }
    
    public Auxiliar obtenerPorId(Integer idAuxiliar){
        return auxiliarDAO.obtenerPorId(idAuxiliar);
    }
    //public Auxiliar obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha){
    //    return auxiliarDAO.obtenerPorIdPorCorreo_Codigo(DatosInicioSesion, Cifrado.cifrarMD5(contrasenha));
    //}
}
