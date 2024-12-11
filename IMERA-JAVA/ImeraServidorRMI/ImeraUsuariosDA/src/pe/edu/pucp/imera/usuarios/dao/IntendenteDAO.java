package pe.edu.pucp.imera.usuarios.dao;

import java.util.ArrayList;
import pe.edu.pucp.imera.usuarios.model.Intendente;

public interface IntendenteDAO {
    //public Integer insertar(Intendente intendente);
    
    //public Integer modificar(Intendente intendente);
    
    //public Integer eliminar(Intendente intendente);
    
    //public ArrayList<Intendente> listarTodos();
    
    Intendente obtenerPorId(Integer idIntendente);
    
   // public Intendente obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha);
}
