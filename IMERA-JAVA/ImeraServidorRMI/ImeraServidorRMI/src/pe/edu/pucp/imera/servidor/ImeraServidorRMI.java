
package pe.edu.pucp.imera.servidor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.imera.interfaces.AulaBO;
import pe.edu.pucp.imera.interfaces.AuxiliarBO;
import pe.edu.pucp.imera.interfaces.CambioDeEstadoBO;
import pe.edu.pucp.imera.interfaces.HoraBO;
import pe.edu.pucp.imera.interfaces.HorarioAuxiliarBO;
import pe.edu.pucp.imera.interfaces.HorarioBO;
import pe.edu.pucp.imera.interfaces.HorarioCompletoBO;
import pe.edu.pucp.imera.interfaces.HorarioIntendenteBO;
import pe.edu.pucp.imera.interfaces.PabellonBO;
import pe.edu.pucp.imera.interfaces.PersonaBO;
import pe.edu.pucp.imera.interfaces.PisoBO;
import pe.edu.pucp.imera.interfaces.ReporteMensualBO;
import pe.edu.pucp.imera.interfaces.ReporteSemanalBO;
import pe.edu.pucp.imera.interfacesImpl.AulaBOImpl;
import pe.edu.pucp.imera.interfacesImpl.AuxiliarBOImpl;
import pe.edu.pucp.imera.interfacesImpl.CambioDeEstadoBOImpl;
import pe.edu.pucp.imera.interfacesImpl.HoraBOImpl;
import pe.edu.pucp.imera.interfacesImpl.HorarioAuxiliarBOImpl;
import pe.edu.pucp.imera.interfacesImpl.HorarioBOImpl;
import pe.edu.pucp.imera.interfacesImpl.HorarioCompletoBOImpl;
import pe.edu.pucp.imera.interfacesImpl.HorarioIntendenteBOImpl;
import pe.edu.pucp.imera.interfacesImpl.PabellonBOImpl;
import pe.edu.pucp.imera.interfacesImpl.PersonaBOImpl;
import pe.edu.pucp.imera.interfacesImpl.PisoBOImpl;
import pe.edu.pucp.imera.interfacesImpl.ReporteMensualBOImpl;
import pe.edu.pucp.imera.interfacesImpl.ReporteSemanalBOImpl;

import java.rmi.Remote;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.util.Properties;

public class ImeraServidorRMI {
    private static String IP;
    private static Integer puerto;
    private static final String ARCHIVO_CONFIGURACION = "servidorRMI.properties";
    
    private static void leer_archivo_de_propiedades() {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "C:" + "\\" + ARCHIVO_CONFIGURACION;

            properties.load(new FileInputStream(new File(nmArchivoConf)));
            ImeraServidorRMI.puerto = Integer.valueOf(properties.getProperty("puerto"));
            ImeraServidorRMI.IP = properties.getProperty("IP");
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
    }
    
    public static void main(String[] args) {
        try {
            ImeraServidorRMI.leer_archivo_de_propiedades();
            //registar el puerto
            LocateRegistry.createRegistry(puerto);
            
            //instanciar los objetos remotos
            AuxiliarBO auxiliarBO=new AuxiliarBOImpl(puerto);
            HorarioBO horarioBO=new HorarioBOImpl(puerto);
            PersonaBO personaBO=new PersonaBOImpl(puerto);
            HorarioCompletoBO horarioCompletoBO=new HorarioCompletoBOImpl(puerto);
            HorarioAuxiliarBO horarioAuxiliarBO=new HorarioAuxiliarBOImpl(puerto);
            PabellonBO pabellonBO=new PabellonBOImpl(puerto);
            AulaBO aulaBO=new AulaBOImpl(puerto);
            PisoBO pisoBO=new PisoBOImpl(puerto);
            CambioDeEstadoBO cambioEstadoBO=new CambioDeEstadoBOImpl(puerto);
            HorarioIntendenteBO horarioIntendenteBO=new HorarioIntendenteBOImpl(puerto);
            ReporteMensualBO reporteMensualBO=new ReporteMensualBOImpl(puerto);
            ReporteSemanalBO reporteSemanalBO=new ReporteSemanalBOImpl(puerto);
            HoraBO horaBO=new HoraBOImpl(puerto);
            
            
            //colocar en el RMI
            colocarRmi("auxiliarBO",auxiliarBO);
            colocarRmi("horarioBO",horarioBO);
            colocarRmi("personaBO",personaBO);
            colocarRmi("horarioCompletoBO",horarioCompletoBO);
            colocarRmi("horarioAuxiliarBO",horarioAuxiliarBO);
            colocarRmi("pabellonBO",pabellonBO);
            colocarRmi("aulaBO",aulaBO);
            colocarRmi("pisoBO",pisoBO);
            colocarRmi("cambioEstadoBO",cambioEstadoBO);
            colocarRmi("horarioIntendenteBO",horarioIntendenteBO);
            colocarRmi("reporteMensualBO",reporteMensualBO);
            colocarRmi("reporteSemanalBO",reporteSemanalBO);
            colocarRmi("horaBO",horaBO);
            
            System.out.println("Servidor RMI registrado correctamente...");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ImeraServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void colocarRmi(String nombreObjeto,Remote interfazRemota)throws MalformedURLException,RemoteException{
        String nombreServicio = retornarNombreDelServicio(nombreObjeto);
        Naming.rebind(nombreServicio, interfazRemota);
        
    }
    public static String retornarNombreDelServicio(String nombreDelObjetoRemoto) {
        ImeraServidorRMI.leer_archivo_de_propiedades();
        return "//" + IP + ":" + puerto + "/" + nombreDelObjetoRemoto;
    }
    
}
