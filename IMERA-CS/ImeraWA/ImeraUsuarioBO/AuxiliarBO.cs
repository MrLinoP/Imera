using ImeraBaseBO;
using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace ImeraUsuarioBO
{
    public class AuxiliarBO : BaseBO
    {
        public AuxiliarBO():base()
        {
            
        }
        public int insertar(int codigoPucp, string correoPucp,
            string nombre,  string apellidoP,  string apellidoM,
             string idPabellon,  turno _turno,  char activo,string contrasenha,rol _rol)
        {
            return WsCliente.auxiliar_insertar(codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, _turno, activo,contrasenha,_rol);
        }

        public int modificar(int idAuxiliar,int codigoPucp, string correoPucp,
            string nombre, string apellidoP, string apellidoM,
             string idPabellon, turno _turno, char activo,string contrasenha)
        {
            return WsCliente.auxiliar_modificar(idAuxiliar,codigoPucp, correoPucp, nombre, apellidoP, apellidoM, idPabellon, _turno, (ushort)activo, contrasenha);
        }
        /*
        public int eliminar(int idEspecialidad)
        {
            return WsCliente.auxiliar_eliminar(idEspecialidad);
        }
        */
        public BindingList<auxiliar> listarTodos()
        {
            auxiliar[] arreglo = WsCliente.auxiliar_listarTodos();
            if (arreglo == null)
                return new BindingList<auxiliar>();
            
            return new BindingList<auxiliar>(arreglo);
        }

        public auxiliar obtenerPorId(int idEspecialidad)
        {
            return WsCliente.auxiliar_obtenerPorId(idEspecialidad);
        }
        /*
        public auxiliar obtenerPorDatosInicioSesion(string DatosUsuario,string contrasenha)
        {
            return WsCliente.auxiliar_obtenerPorIdPorCorreo_Codigo(DatosUsuario,contrasenha);
        }
        */
    }
}
