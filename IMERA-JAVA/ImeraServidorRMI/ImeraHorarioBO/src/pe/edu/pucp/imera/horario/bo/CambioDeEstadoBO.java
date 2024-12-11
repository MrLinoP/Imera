
package pe.edu.pucp.imera.horario.bo;

import pe.edu.pucp.imera.horario.dao.CambioDeEstadoDAO;
import pe.edu.pucp.imera.horario.daoImpl.CambioDeEstadoDAOImpl;
import pe.edu.pucp.imera.horario.model.EstadoAula;
import pe.edu.pucp.imera.horario.model.CambioDeEstado;


public class CambioDeEstadoBO {
    private final CambioDeEstadoDAO cambioEstadoDAO;
    
    public CambioDeEstadoBO() {
        cambioEstadoDAO = new CambioDeEstadoDAOImpl();
    }
    
    public Integer insertar(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estadoInicial, EstadoAula estadoFinal, Integer idAuxiliar){
        CambioDeEstado cambioEstado = new CambioDeEstado(null, idPabellon, idPiso, idAula, idHora, estadoInicial, estadoFinal, idAuxiliar);
        return cambioEstadoDAO.insertar(cambioEstado);
    }
}