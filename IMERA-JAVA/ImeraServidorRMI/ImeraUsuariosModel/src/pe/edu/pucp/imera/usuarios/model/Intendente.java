
package pe.edu.pucp.imera.usuarios.model;

public class Intendente extends Persona{
    

    public Intendente() {
        super();
    }
    
    //constructor con parámetros
    public Intendente(Integer idIntendente, Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM) {
        super(idIntendente,Rol.INTENDENTE,null,null,correoPucp,codigoPucp,nombre,apellidoP,apellidoM);
    }
    
    public Intendente(Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM) {
        super(null,Rol.INTENDENTE,null,null,correoPucp,codigoPucp,nombre,apellidoP,apellidoM);
    }

    public Integer getIdIntendente() {
        return this.idPersona;
    }

    public void setIdIntendente(Integer idIntendente) {
        this.idPersona = idIntendente;
    }
}
