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
    public class PabellonBO:BaseBO
    {
        public PabellonBO() : base()
        {

        }
        public BindingList<pabellon> listarTodos()
        {
            pabellon[] arreglo = WsCliente.pabellon_listar_todos();
            if (arreglo == null)
                return new BindingList<pabellon>();
            return new BindingList<pabellon>(arreglo);
        }
        public int insertar(string idPabellon, string nombre, int cantPisos, string[] aulas, int?[] aforos, int?[] enchufes)
        {
            return WsCliente.pabellon_insertarPabellon(idPabellon, nombre, cantPisos, aulas, aforos, enchufes);
        }
    }
}
