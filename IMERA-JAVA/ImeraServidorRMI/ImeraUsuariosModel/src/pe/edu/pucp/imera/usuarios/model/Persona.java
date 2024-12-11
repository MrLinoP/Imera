/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.usuarios.model;

import java.io.Serializable;

public class Persona implements Serializable{
    protected Integer idPersona;
    protected Rol rol;
    protected String idPabellon;
    protected Character activo;
    protected String correoPucp;
    protected Integer codigoPucp;
    protected String nombre;
    protected String apellidoP;
    protected String apellidoM;
    
    public Persona(Integer idPersona, Rol rol,String idPabellon,Character activo,String correoPucp,Integer codigoPucp,String nombre,String apellidoP,String apellidoM) {
        this.idPersona = idPersona;
        this.rol = rol;
        this.idPabellon=idPabellon;
        this.activo=activo;
        this.correoPucp=correoPucp;
        this.codigoPucp=codigoPucp;
        this.nombre=nombre;
        this.apellidoP=apellidoP;
        this.apellidoM=apellidoM;
    }
    public Persona() {
        this.idPersona = null;
        this.rol = null;
        this.idPabellon=null;
        this.activo=null;
        this.correoPucp=null;
        this.codigoPucp=null;
        this.nombre=null;
        this.apellidoP=null;
        this.apellidoM=null;
    }
    public String getCorreoPucp() {
        return correoPucp;
    }

    public void setCorreoPucp(String correoPucp) {
        this.correoPucp = correoPucp;
    }

    public Integer getCodigoPucp() {
        return codigoPucp;
    }

    public void setCodigoPucp(Integer codigoPucp) {
        this.codigoPucp = codigoPucp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public String getIdPabellon() {
        return idPabellon;
    }

    public void setIdPabellon(String idPabellon) {
        this.idPabellon = idPabellon;
    }

    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}
