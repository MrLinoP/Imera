using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;

namespace ImeraWA
{
    [Serializable]
    public class Aula
    {
        private string nombre;
        private BindingList<Horario> horarios;

        public Aula()
        {
            Horarios = new BindingList<Horario>();
        }

        public string Nombre { get => nombre; set => nombre = value; }
        public BindingList<Horario> Horarios { get => horarios; set => horarios = value; }
    }
}