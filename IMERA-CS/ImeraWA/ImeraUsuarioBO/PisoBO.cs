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
    public class PisoBO:BaseBO
    {
        public PisoBO() : base() { }
        public BindingList<int> listarIdPisos(string idPabellon)
        {
            int[] arreglo = WsCliente.piso_listarNumeroPisos(idPabellon);
            if (arreglo == null)
                return new BindingList<int>();
            return new BindingList<int>(arreglo);
        }
        public int insertar(string idPabellon, int idPiso, string[] aulas, int?[] aforos, int?[] enchufes)
        {
            return WsCliente.piso_insertarPiso(idPabellon, idPiso, aulas, aforos, enchufes);
        }
    }
}
