using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ImeraUsuarioBOTest
{
    internal class Program
    {
        static void Main(string[] args)
        {
            AuxiliarBO auxiliarBO = new AuxiliarBO();
            int resultado;
            
            resultado = auxiliarBO.insertar(781326, "asdf@gmail.com",
            "elPepe", "Gonzales", "Linares",
             "H", turno.Tarde, 'S');
            if (resultado != 0)
            {
                Console.WriteLine(resultado + "la prueba fue exitosa");
            }
        }
    }
}
