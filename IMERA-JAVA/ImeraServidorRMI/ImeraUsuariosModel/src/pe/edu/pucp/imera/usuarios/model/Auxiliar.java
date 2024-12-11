
package pe.edu.pucp.imera.usuarios.model;

public class Auxiliar extends Persona{
    private Turno turno;
    private Character activo;
    private String contrasenha;

    public Auxiliar(Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha, Rol rol) {
        super(null,rol,idPabellon,activo,correoPucp,codigoPucp,nombre,apellidoP,apellidoM);
        this.turno = turno;
        this.activo = activo;
        this.contrasenha=contrasenha;
        
    }
    
    public Auxiliar(Integer idAuxiliar, Integer codigoPucp, String correoPucp, String nombre, String apellidoP, String apellidoM, String idPabellon, Turno turno, Character activo,String contrasenha,Rol rol) {
        super(idAuxiliar,rol,idPabellon,activo,correoPucp,codigoPucp,nombre,apellidoP,apellidoM);
        this.turno = turno;
        this.activo = activo;
        this.contrasenha=contrasenha;
    }
    
    public Auxiliar() {
        super();
        this.turno = null;
        this.activo = null;
        this.contrasenha=null;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    
    public Integer getIdAuxiliar() {
        return this.idPersona;
    }

    public void setIdAuxiliar(Integer idAuxiliar) {
        this.idPersona = idAuxiliar;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
