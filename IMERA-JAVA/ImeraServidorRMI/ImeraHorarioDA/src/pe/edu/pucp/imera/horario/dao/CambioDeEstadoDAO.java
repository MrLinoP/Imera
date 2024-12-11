
package pe.edu.pucp.imera.horario.dao;

import pe.edu.pucp.imera.horario.model.CambioDeEstado;
import java.sql.Connection;
public interface CambioDeEstadoDAO {
    Integer insertar(CambioDeEstado cambioEstado);
    
    Integer obtenerHorasAsignadasAulaLibreXPabellonUltimoMes(String IdPabellon,Connection conexion);
    
    Integer obtenerHorasDeLibreAReservadaXPabellonUltimoMes(String IdPabellon,Connection conexion);
    
    Integer obtenerHorasCambiadasADisponibleXPabellonUltimoMes(String IdPabellon,Connection conexion);
}