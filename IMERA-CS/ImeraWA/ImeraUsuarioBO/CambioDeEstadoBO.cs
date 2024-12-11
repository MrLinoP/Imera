using ImeraBaseBO;
using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImeraUsuarioBO
{
    public class CambioDeEstadoBO:BaseBO
    {
        public CambioDeEstadoBO() : base()
        {

        }
        public int insertar(string idPabellon,int idPiso, int idAula,int idHora,estadoAula estadoInicial,
            estadoAula estadoFinal,int idAuxiliar)
        {
            return WsCliente.cambioEstado_insertar(idPabellon,idPiso,idAula,idHora,estadoInicial,estadoFinal, idAuxiliar);
        }
    }
}
