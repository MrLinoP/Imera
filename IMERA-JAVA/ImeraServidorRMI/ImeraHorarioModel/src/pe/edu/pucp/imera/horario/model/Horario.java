/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.model;


import java.io.Serializable;
import java.sql.Date;



/**
 *
 * @author home
 */
public class Horario implements Serializable{
    private String idPabellon;
    private Integer idPiso;
    private Integer idAula;
    private Integer idHora;
    private Date idDia;
    private EstadoAula estado;

    public Horario(String idPabellon, Integer idPiso, Integer idAula, Integer idHora, Date idDia, EstadoAula estado) {
        this.idPabellon = idPabellon;
        this.idPiso = idPiso;
        this.idAula = idAula;
        this.idHora = idHora;
        this.idDia = idDia;
        this.estado = estado;
    }
    
    public Horario() {
        this.idPabellon = null;
        this.idPiso = null;
        this.idAula = null;
        this.idHora = null;
        this.idDia = null;
        this.estado = null;
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

    public Date getIdDia() {
        return idDia;
    }

    public void setIdDia(Date idDia) {
        this.idDia = idDia;
    }



    public EstadoAula getEstado() {
        return estado;
    }

    public void setEstado(EstadoAula estado) {
        this.estado = estado;
    }
    
    
    
}
