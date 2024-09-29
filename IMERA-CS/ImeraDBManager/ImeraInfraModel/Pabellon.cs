using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Metadata.W3cXsd2001;
using System.Text;
using System.Threading.Tasks;

namespace ImeraInfraModel
{
    public class Pabellon
    {
        private string _idPabellon;
        private int _numeroPisos;
        private string _nombre;

        public Pabellon() { }

        public string IdPabellon { get => _idPabellon; set => _idPabellon = value; }
        public int NumeroPisos { get => _numeroPisos; set => _numeroPisos = value; }
        public string Nombre { get => _nombre; set => _nombre = value; }
    }
}
