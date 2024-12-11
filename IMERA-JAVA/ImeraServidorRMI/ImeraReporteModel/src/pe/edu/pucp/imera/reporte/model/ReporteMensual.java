/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.reporte.model;

import java.io.Serializable;

/**
 *
 * @author home
 */
public class ReporteMensual implements Serializable {
    private String idPabellon;
    private Integer horasAsignadasAulaLibre;
    private Integer horasDeLibreAReservada;
    private Integer horasCambiadasADisponible;
    private String ratioAR;

    public ReporteMensual(String idPabellon, Integer horasAsignadas, Integer horasRevocadas, Integer horasLibres, String ratioAR) {
        this.idPabellon = idPabellon;
        this.horasAsignadasAulaLibre = horasAsignadas;
        this.horasDeLibreAReservada = horasRevocadas;
        this.horasCambiadasADisponible = horasLibres;
        this.ratioAR = ratioAR;
    }
    
    public ReporteMensual() {
        this.idPabellon = null;
        this.horasAsignadasAulaLibre = null;
        this.horasDeLibreAReservada = null;
        this.horasCambiadasADisponible = null;
        this.ratioAR = null;
    }

    public String getIdPabellon() {
        return idPabellon;
    }

    public void setIdPabellon(String idPabellon) {
        this.idPabellon = idPabellon;
    }

    public Integer getHorasAsignadasAulaLibre() {
        return horasAsignadasAulaLibre;
    }

    public void setHorasAsignadasAulaLibre(Integer horasAsignadasAulaLibre) {
        this.horasAsignadasAulaLibre = horasAsignadasAulaLibre;
    }

    public Integer getHorasDeLibreAReservada() {
        return horasDeLibreAReservada;
    }

    public void setHorasDeLibreAReservada(Integer horasDeLibreAReservada) {
        this.horasDeLibreAReservada = horasDeLibreAReservada;
    }

    public Integer getHorasCambiadasADisponible() {
        return horasCambiadasADisponible;
    }

    public void setHorasCambiadasADisponible(Integer horasCambiadasADisponible) {
        this.horasCambiadasADisponible = horasCambiadasADisponible;
    }

    public String getRatioAR() {
        return ratioAR;
    }

    public void setRatioAR(String ratioAR) {
        this.ratioAR = ratioAR;
    }
}
