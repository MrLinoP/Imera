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
    public class HorarioIntendenteBO : BaseBO
    {
        public HorarioIntendenteBO() : base()
        {

        }

        public BindingList<horarioIntendente> listarTodosDisponibles(string idPabellon)
        {
            horarioIntendente[] arreglo = WsCliente.horarioIntendente_listar_todosIntendente(idPabellon);
            if (arreglo == null)
                return new BindingList<horarioIntendente>();
            return new BindingList<horarioIntendente>(arreglo);
        }
        public BindingList<horarioIntendente> listarTodosSemestrales()
        {
            horarioIntendente[] arreglo = WsCliente.horarioIntendente_listar_todosSemestrales();
            if (arreglo == null)
                return new BindingList<horarioIntendente>();
            return new BindingList<horarioIntendente>(arreglo);
        }
        public int enviarAulasLibresSemestrales(string[] aulas, string[] horarios, string[] dias)
        {
            int resultado = WsCliente.horarioIntendente_asignarAulaLibreSemestral(aulas, horarios, dias);
            return resultado;
        }
        public int eliminarAulaLibreSemestral(string aula, string horario, string dia)
        {
            int resultado = WsCliente.horarioIntendente_eliminarAulaLibreSemestral(aula, horario, dia);
            return resultado;
        }
    }
}
