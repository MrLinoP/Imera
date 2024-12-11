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
    public class ReporteBO:BaseBO
    {
        public ReporteBO() : base()
        {

        }
        public BindingList<reporteMensual> reporteMensual()
        {
            reporteMensual[] arreglo = WsCliente.reporte_reporteMensual();
            if (arreglo == null)
                return new BindingList<reporteMensual>();
            return new BindingList<reporteMensual>(arreglo);
        }
        public BindingList<reporteSemanal> reporteSemanal(string idPabellon)
        {
            reporteSemanal[] arreglo = WsCliente.reporte_reporteSemanal(idPabellon);
            if (arreglo == null)
                return new BindingList<reporteSemanal>();
            return new BindingList<reporteSemanal>(arreglo);
        }

    }
}
