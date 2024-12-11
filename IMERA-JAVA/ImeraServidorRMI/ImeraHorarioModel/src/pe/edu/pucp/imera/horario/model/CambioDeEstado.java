
package pe.edu.pucp.imera.horario.model;

import java.io.Serializable;


public class CambioDeEstado implements Serializable{
    private Integer idCambioEstado;
    private String idPabellon;
    private Integer idPiso;
    private Integer idAula;
    private Integer idHora;
    private EstadoAula estadoInicial;
    private EstadoAula estadoFinal;
    private Integer idAuxiliar;
    
    public CambioDeEstado() {
        this.idCambioEstado = null;
        this.idPabellon = null;
        this.idPiso = null;
        this.idAula = null;
        this.idHora = null;
        this.idAuxiliar = null;
    }

    public CambioDeEstado(Integer idCambioEstado, String idPabellon, Integer idPiso, Integer idAula, Integer idHora, EstadoAula estadoInicial, EstadoAula estadoFinal, Integer idAuxiliar) {
        this.idCambioEstado = idCambioEstado;
        this.idPabellon = idPabellon;
        this.idPiso = idPiso;
        this.idAula = idAula;
        this.idHora = idHora;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.idAuxiliar = idAuxiliar;
    }

    public Integer getIdCambioEstado() {
        return idCambioEstado;
    }

    public void setIdCambioEstado(Integer idCambioEstado) {
        this.idCambioEstado = idCambioEstado;
    }

    public String getIdPabellon() {
        return idPabellon;
    }

    public void setIdPabellon(String idPabellon) {
        this.idPabellon = idPabellon;
    }

    public Integer getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(Integer idPiso) {
        this.idPiso = idPiso;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdHora() {
        return idHora;
    }

    public void setIdHora(Integer idHora) {
        this.idHora = idHora;
    }

    public EstadoAula getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(EstadoAula estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public EstadoAula getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(EstadoAula estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Integer getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(Integer idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }
    
    
}

