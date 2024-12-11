using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class HomeIntendente : System.Web.UI.Page
    {
        private BindingList<horarioIntendente> listaHorarios;
        private BindingList<HorarioAulaSemestral> listaHorariosTabla;
        private HorarioIntendenteBO horarioIntendenteBO;
        public HomeIntendente()
        {
            this.horarioIntendenteBO = new HorarioIntendenteBO();

        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            cargarAulas();
        }
        protected void btn_eliminarAulaLibreSemestral(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;
            string[] values = commandArgument.Split('|');

            if (values.Length == 3)
            {
                string nombre = values[0];
                string horario = values[1];
                string dia = values[2];
                horarioIntendenteBO.eliminarAulaLibreSemestral(nombre, horario, dia);
                cargarAulas();
            }
        }
        private void cargarAulas()
        {
            listaHorarios = horarioIntendenteBO.listarTodosSemestrales();
            listaHorariosTabla = ObtenerHorarioInicial(listaHorarios);
            dgvAulasLibresSemestrales.DataSource = listaHorariosTabla;
            dgvAulasLibresSemestrales.DataBind();
        }
        private BindingList<HorarioAulaSemestral> ObtenerHorarioInicial(BindingList<horarioIntendente> horariosInicial)
        {
            BindingList<HorarioAulaSemestral> horariosCarga = new BindingList<HorarioAulaSemestral>();
            var agrupados = horariosInicial.GroupBy(h => new { h.nombreAula, h.diaSemana });

            foreach (var grupo in agrupados)
            {
                var horarios = grupo.ToList();
                horarios.Sort((h1, h2) => DateTime.Parse(h1.horario.Split('-')[0]).CompareTo(DateTime.Parse(h2.horario.Split('-')[0])));

                ProcesarGrupoHorarios(horarios, grupo.Key.nombreAula, grupo.Key.diaSemana.ToString(), horariosCarga);
            }
            return horariosCarga;
        }

        private void ProcesarGrupoHorarios(List<horarioIntendente> horarios, string aulaNombre, string diaSemana, BindingList<HorarioAulaSemestral> horariosCarga)
        {
            TimeSpan? inicioConcatenacion = null;
            TimeSpan? finConcatenacion = null;
            int horasContadas = 0;

            foreach (horarioIntendente horario in horarios)
            {
                TimeSpan inicio = TimeSpan.Parse(horario.horario.Split('-')[0]);
                TimeSpan fin = TimeSpan.Parse(horario.horario.Split('-')[1]);

                if (inicioConcatenacion == null)
                {
                    InicializarVariables(ref inicioConcatenacion, ref finConcatenacion, ref horasContadas, inicio, fin);
                }
                else
                {
                    if (inicio == finConcatenacion)
                    {
                        finConcatenacion = fin;
                        horasContadas++;
                    }
                    else
                    {
                        AgregarHorarioSiValido(horasContadas, inicioConcatenacion, finConcatenacion, aulaNombre, diaSemana, horariosCarga);
                        InicializarVariables(ref inicioConcatenacion, ref finConcatenacion, ref horasContadas, inicio, fin);
                    }
                }
            }
            AgregarHorarioSiValido(horasContadas, inicioConcatenacion, finConcatenacion, aulaNombre, diaSemana, horariosCarga);
        }

        private void InicializarVariables(ref TimeSpan? inicioConcatenacion, ref TimeSpan? finConcatenacion, ref int horasContadas, TimeSpan inicio, TimeSpan fin)
        {
            inicioConcatenacion = inicio;
            finConcatenacion = fin;
            horasContadas = 1;
        }

        private void AgregarHorarioSiValido(int horasContadas, TimeSpan? inicioConcatenacion, TimeSpan? finConcatenacion, string aulaNombre, string diaSemana, BindingList<HorarioAulaSemestral> horariosCarga)
        {
            if (horasContadas >= 3)
            {
                HorarioAulaSemestral nuevoHorario = new HorarioAulaSemestral
                {
                    Nombre = aulaNombre,
                    Horario = $"{inicioConcatenacion.Value:hh\\:mm} - {finConcatenacion.Value:hh\\:mm}",
                    Dia = diaSemana,
                    Estado = "AULA_LIBRE_SEMESTRAL"
                };
                horariosCarga.Add(nuevoHorario);
            }
        }
    }
}