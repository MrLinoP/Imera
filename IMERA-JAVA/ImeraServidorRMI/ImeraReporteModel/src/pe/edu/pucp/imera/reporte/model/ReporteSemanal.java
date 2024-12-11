
package pe.edu.pucp.imera.reporte.model;

import java.io.Serializable;

public class ReporteSemanal implements Serializable{
    private Integer codigoAuxiliar;
    private String nombre;
    private Integer horasAsignadas;
    private Integer horasDesasignadas;

    public ReporteSemanal(Integer codigoAuxiliar, String nombre, Integer horasAsignadas,Integer horasDesasignadas) {
        this.codigoAuxiliar = codigoAuxiliar;
        this.nombre = nombre;
        this.horasAsignadas = horasAsignadas;
        this.horasDesasignadas = horasDesasignadas;
    }
    
    public ReporteSemanal() {
        this.codigoAuxiliar = null;
        this.nombre = null;
        this.horasAsignadas = null;
        this.horasDesasignadas = null;
    }

    public Integer getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(Integer codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(Integer horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }

    public Integer getHorasDesasignadas() {
        return horasDesasignadas;
    }

    public void setHorasDesasignadas(Integer horasDesasignadas) {
        this.horasDesasignadas = horasDesasignadas;
    }
    
    
    
}
