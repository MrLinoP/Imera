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
    public class HorarioCompletoBO:BaseBO
    {
        public HorarioCompletoBO() : base()
        {

        }
        public BindingList<horarioCompleto> AlumnoistarTodos()
        {
            horarioCompleto[] arreglo = WsCliente.horarioCompletoAlumno_listarTodos();
            if (arreglo == null)
                return new BindingList<horarioCompleto>();
            return new BindingList<horarioCompleto>(arreglo);
        }
    }
}
