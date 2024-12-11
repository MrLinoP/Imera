using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ImeraWA
{
    [Serializable]
    public class HorarioAulaSemestral
    {
        public HorarioAulaSemestral()
        {

        }
        public string Nombre { get; set; }
        public string Horario { get; set; }
        public string Dia { get; set; }
        public string Aforo { get; set; }
        public string Enchufes { get; set; }

        public string Estado { get; set; }
    }
}