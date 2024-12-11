using ImeraBaseBO;
using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImeraUsuarioBO
{
    public class PersonaBO:BaseBO
    {
        public PersonaBO() : base()
        {

        }
        public persona obtenerPorDatosInicioSesion(string DatosUsuario, string contrasenha)
        {
            return WsCliente.loginIntendenteAuxiliar(DatosUsuario, contrasenha);
        }
    }
}
