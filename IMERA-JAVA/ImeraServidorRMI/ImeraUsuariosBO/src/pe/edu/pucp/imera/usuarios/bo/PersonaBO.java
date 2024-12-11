
package pe.edu.pucp.imera.usuarios.bo;

import pe.edu.pucp.imera.usuarios.dao.PersonaDAO;
import pe.edu.pucp.imera.usuarios.daoImpl.PersonaDAOImpl;
import pe.edu.pucp.imera.usuarios.model.Persona;
import pe.edu.pucp.imera.util.Cifrado;


public class PersonaBO {
    private final PersonaDAO personaDAO;
    public PersonaBO(){
        this.personaDAO=new PersonaDAOImpl();
    }
    public Persona obtenerPorIdPorCorreo_Codigo(String DatosInicioSesion,String contrasenha){
        return this.personaDAO.obtenerPorIdPorCorreo_Codigo(DatosInicioSesion, Cifrado.cifrarMD5(contrasenha));
    }

}
