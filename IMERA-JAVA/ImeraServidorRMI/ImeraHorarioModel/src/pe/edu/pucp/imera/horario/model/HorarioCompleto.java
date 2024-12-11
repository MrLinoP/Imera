/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.model;

import java.io.Serializable;

/**
 *
 * @author julia
 */
public class HorarioCompleto implements Serializable{
    private String idPabellon;
    private Integer idPiso;
    private Integer idAula;
    private Integer horaIni;
    private Integer horaFin;
    private Integer aforo;
    private Integer cantEnchufes;
    
    public HorarioCompleto() {
        this.idPabellon = null;
        this.idPiso = null;
        this.idAula = null;
        this.horaIni = null;
        this.horaFin = null;
        this.aforo = null;
        this.cantEnchufes = null;
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

    public Integer getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getCantEnchufes() {
        return cantEnchufes;
    }

    public void setCantEnchufes(Integer cantEnchufes) {
        this.cantEnchufes = cantEnchufes;
    }
    
    
}
