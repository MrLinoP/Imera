/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.du.pucp.imera.infra.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class Pabellon implements Serializable{
    private String idPabellon;
    private Integer numeroPisos;
    private String nombre;
    private ArrayList<Piso> pisos;
    
    public Pabellon(String idPabellon, Integer numeroPisos, String nombre) {
        this.idPabellon = idPabellon;
        this.numeroPisos = numeroPisos;
        this.nombre = nombre;
        this.pisos = new ArrayList<>();
    }
    
    public Pabellon() {
        this.idPabellon = null;
        this.numeroPisos = null;
        this.nombre = null;
        this.pisos = new ArrayList<>();
    }
    
    public ArrayList<Piso> getPisos() {
        return pisos;
    }

    public void setPisos(ArrayList<Piso> pisos) {
        this.pisos = pisos;
    }

    public String getIdPabellon() {
        return idPabellon;
    }

    public void setIdPabellon(String idPabellon) {
        this.idPabellon = idPabellon;
    }

    public Integer getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(Integer numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}