/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.horario.model;

import java.io.Serializable;

/**
 *
 * @author home
 */
public class HorarioIntendente implements Serializable{
    private String nombreAula;
    private String horario;
    private DiaSemana diaSemana;

    public HorarioIntendente(String nombreAula, String horario, DiaSemana diaSemana) {
        this.nombreAula = nombreAula;
        this.horario = horario;
        this.diaSemana = diaSemana;
    }

    
    public HorarioIntendente() {
        this.nombreAula = null;
        this.horario = null;
        this.diaSemana = null;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    

    
}