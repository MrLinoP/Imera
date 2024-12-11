
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.du.pucp.imera.infra.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Piso implements Serializable{
    private Integer idPiso;
    private String  idPabellon;
    private Integer numeroAulas;
    private ArrayList<Aula> aulas;
    
    public Piso(Integer idPiso, String idPabellon, Integer numeroAulas) {
        this.idPiso = idPiso;
        this.idPabellon = idPabellon;
        this.numeroAulas = numeroAulas;
        this.aulas = new ArrayList<>();
    }
    
    public Piso() {
        this.idPiso = null;
        this.idPabellon = null;
        this.numeroAulas = null;
        this.aulas = null;
    }
    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(ArrayList<Aula> aulas) {
        this.aulas = aulas;
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

    public Integer getNumeroAulas() {
        return numeroAulas;
    }

    public void setNumeroAulas(Integer numeroAulas) {
        this.numeroAulas = numeroAulas;
    }
    
    
    
    
}
