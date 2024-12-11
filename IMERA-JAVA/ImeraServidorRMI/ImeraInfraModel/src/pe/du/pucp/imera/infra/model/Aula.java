
package pe.du.pucp.imera.infra.model;

import java.io.Serializable;

public class Aula implements Serializable{
    
    private Integer idAula;
    private Integer idPiso;
    private String idPabellon;
    private Integer aforo;
    private Integer numeroEnchufes;
    

    public Aula(Integer idAula, Integer idPiso, String idPabellon, Integer aforo, Integer numeroEnchufes) {
        this.idAula = idAula;
        this.idPiso = idPiso;
        this.idPabellon = idPabellon;
        this.aforo = aforo;
        this.numeroEnchufes = numeroEnchufes;
    }
    
    public Aula() {
        this.idAula = null;
        this.idPiso = null;
        this.idPabellon = null;
        this.aforo = null;
        this.numeroEnchufes = null;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(Integer idPiso) {
        this.idPiso = idPiso;
    }

    public String getIdPabellon() {
        return idPabellon;
    }

    public void setIdPabellon(String idPabellon) {
        this.idPabellon = idPabellon;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getNumeroEnchufes() {
        return numeroEnchufes;
    }

    public void setNumeroEnchufes(Integer numeroEnchufes) {
        this.numeroEnchufes = numeroEnchufes;
    }
    
    
}