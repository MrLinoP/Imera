using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImeraBaseBO
{
    public class BaseBO
    {
        private ImeraServicio.ImeraServicioClient wsCliente;

        public BaseBO()
        {
            this.WsCliente = new ImeraServicio.ImeraServicioClient();
        }

        public ImeraServicioClient WsCliente { get => wsCliente; set => wsCliente = value; }
    }
}
