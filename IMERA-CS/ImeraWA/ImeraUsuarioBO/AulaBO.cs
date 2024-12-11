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
    public class AulaBO:BaseBO
    {
        public AulaBO() : base()
        {

        }
        public BindingList<string> listarNombresAula(int idPiso,string idPabellon)
        {
            string[] arreglo = WsCliente.aula_listarNombresAulasXPisoYPabellon(idPiso,idPabellon);
            if (arreglo == null)
                return new BindingList<string>();
            return new BindingList<string>(arreglo);
        }
        public int insertar(int?[] idAulas, int?[] idPisos , string[] idPabellones, int?[] aforos, int?[] enchufes)
        {
            return WsCliente.aula_insertarAulas(idAulas, idPisos, idPabellones, aforos, enchufes);
        }
    }
}
