using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ImeraWA
{
    [Serializable]
    public class Horario
    {
        private string tiempo;
        private string estado;
        public Horario()
        {
            this.tiempo = null;
            this.estado = null;
        }

        public string Tiempo { get => tiempo; set => tiempo = value; }
        public string Estado { get => estado; set => estado = value; }
    }
}