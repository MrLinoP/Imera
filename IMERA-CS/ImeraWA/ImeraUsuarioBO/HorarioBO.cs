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
    public class HorarioBO:BaseBO
    {
        public HorarioBO() : base()
        {

        }
        public int modificar(String idPabellon, int idPiso, int idAula, int idHora, estadoAula estado)
        {
            return WsCliente.horario_modificar(idPabellon, idPiso, idAula, idHora, estado);
        }
        public int modificarHorarios(string []pabellones, int?[]pisos, int?[] aulas, int?[] horas,string[] fechas)
        {
                return WsCliente.horario_modificarAulasClase(pabellones,pisos,aulas,horas,fechas);
        }
    }
}
