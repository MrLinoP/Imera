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
public class HorarioAuxiliar implements Serializable{
    private String nombreAula;
    private String horario;
    private EstadoAula estado;

    public HorarioAuxiliar(String nombreAula, String horario, EstadoAula estado) {
        this.nombreAula = nombreAula;
        this.horario = horario;
        this.estado = estado;
    }
    
    public HorarioAuxiliar(){
        this.nombreAula = null;
        this.horario = null;
        this.estado = null;
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

    public EstadoAula getEstado() {
        return estado;
    }

    public void setEstado(EstadoAula estado) {
        this.estado = estado;
    }
    
    
}
