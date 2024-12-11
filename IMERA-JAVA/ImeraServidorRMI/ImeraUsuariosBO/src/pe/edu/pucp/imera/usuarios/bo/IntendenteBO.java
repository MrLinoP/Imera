package pe.edu.pucp.imera.usuarios.bo;

import pe.edu.pucp.imera.usuarios.dao.IntendenteDAO;
import pe.edu.pucp.imera.usuarios.daoImpl.IntendenteDAOImpl;
import pe.edu.pucp.imera.usuarios.model.Intendente;

public class IntendenteBO {
    private IntendenteDAO intendenteDAO;
    
    public IntendenteBO(){
        this.intendenteDAO = new IntendenteDAOImpl();
    }
    public Intendente obtenerPorId(Integer idIntendente){
        return this.intendenteDAO.obtenerPorId(idIntendente);
    }

}
