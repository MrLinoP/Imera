using ImeraBaseBO;
using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImeraUsuarioBO
{
    public class HorarioAuxiliarBO:BaseBO
    {
        public HorarioAuxiliarBO() : base()
        {

        }
        public BindingList<horarioAuxiliar> listarTodos(string idPabellon, int idPiso)
        {
            horarioAuxiliar[] arreglo = WsCliente.horarioAuxiliar_listarTodos(idPabellon,idPiso);
            if (arreglo == null)
                return new BindingList<horarioAuxiliar>();
            return new BindingList<horarioAuxiliar>(arreglo);
        }
    }
}
