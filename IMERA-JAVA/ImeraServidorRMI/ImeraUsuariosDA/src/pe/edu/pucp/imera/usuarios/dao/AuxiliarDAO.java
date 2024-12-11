package pe.edu.pucp.imera.usuarios.dao;

import java.util.ArrayList;
import pe.edu.pucp.imera.usuarios.model.Auxiliar;


public interface AuxiliarDAO {
    Integer insertar(Auxiliar auxiliar);
    
    Integer modificar(Auxiliar auxiliar);
    
    Integer eliminar(Auxiliar auxiliar);
    
    ArrayList<Auxiliar> listarTodos();
    
    Auxiliar obtenerPorId(Integer idAuxiliar);
    
   // public Auxiliar obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha);
}
