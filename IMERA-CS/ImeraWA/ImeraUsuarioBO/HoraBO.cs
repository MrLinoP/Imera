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
    public class HoraBO:BaseBO
    {
        public HoraBO() : base()
        {
        }
        public BindingList<int> distribucionHorariosReporteSemanal(string idPabellon)
        {
            int[] arreglo = WsCliente.reporte_distribucionHorasSemanal(idPabellon);
            if (arreglo == null)
                return new BindingList<int>();
            return new BindingList<int>(arreglo);
        }

    }
}
