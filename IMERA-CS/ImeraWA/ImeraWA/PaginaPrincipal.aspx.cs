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
    public partial class PaginaPrincipal : System.Web.UI.Page
    {
        private BindingList<HorarioAulaSemestral> listaHorarios;
        private HorarioCompletoBO horarioCompletoBO;


        private string SelectedButtonID
        {
            get { return ViewState["SelectedButtonID"] as string ?? string.Empty; }
            set { ViewState["SelectedButtonID"] = value; }
        }
        private DataTable AulasDataTable
        {
            get { return ViewState["AulasDataTable"] as DataTable; }
            set { ViewState["AulasDataTable"] = value; }
        }
        public PaginaPrincipal()
        {
            horarioCompletoBO = new HorarioCompletoBO();

        }
        private string CurrentSortExpression
        {
            get { return ViewState["CurrentSortExpression"] as string; }
            set { ViewState["CurrentSortExpression"] = value; }
        }

        private string CurrentSortDirection
        {
            get { return ViewState["CurrentSortDirection"] as string ?? "ASC"; }
            set { ViewState["CurrentSortDirection"] = value; }
        }

        private DataTable OriginalAulasDataTable
        {
            get { return ViewState["OriginalAulasDataTable"] as DataTable; }
            set { ViewState["OriginalAulasDataTable"] = value; }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarAulas();
            }
        }
        private BindingList<HorarioAulaSemestral> ObtenerHorarioInicial(BindingList<HorarioAulaSemestral> horariosInicial)
        {
            BindingList<HorarioAulaSemestral> horariosCarga = new BindingList<HorarioAulaSemestral>();
            BindingList<HorarioAulaSemestral> horariosBd = horariosInicial;

            // Agrupar horarios por aula y características
            IEnumerable<IGrouping<dynamic, HorarioAulaSemestral>> agrupados = AgruparHorariosPorAula(horariosBd);

            foreach (IGrouping<dynamic, HorarioAulaSemestral> grupo in agrupados)
            {
                ProcesarGrupoHorario(grupo, horariosCarga);
            }

            return horariosCarga;
        }

        // Función para agrupar horarios por aula y características
        private IEnumerable<IGrouping<dynamic, HorarioAulaSemestral>> AgruparHorariosPorAula(BindingList<HorarioAulaSemestral> horariosBd)
        {
            return horariosBd.GroupBy(h => new { h.Nombre, h.Aforo, h.Enchufes });
        }

        // Función para procesar cada grupo de horarios
        private void ProcesarGrupoHorario(IGrouping<dynamic, HorarioAulaSemestral> grupo, BindingList<HorarioAulaSemestral> horariosCarga)
        {
            List<HorarioAulaSemestral> horarios = grupo.ToList();
            horarios.Sort((h1, h2) => CompararHoras(h1.Horario, h2.Horario));

            // Inicialización de variables comunes para concatenación
            string aulaNombre = grupo.Key.Nombre;
            string aforo = grupo.Key.Aforo;
            string enchufes = grupo.Key.Enchufes;

            TimeSpan? inicioConcatenacion = null;
            TimeSpan? finConcatenacion = null;
            int horasContadas = 0;

            foreach (HorarioAulaSemestral horario in horarios)
            {
                TimeSpan inicio = TimeSpan.Parse(horario.Horario.Split('-')[0]);
                TimeSpan fin = TimeSpan.Parse(horario.Horario.Split('-')[1]);

                if (inicioConcatenacion == null)
                {
                    IniciarNuevaConcatenacion(ref inicioConcatenacion, ref finConcatenacion, ref horasContadas, inicio, fin);
                }
                else if (inicio == finConcatenacion)
                {
                    // Extender concatenación
                    finConcatenacion = fin;
                    horasContadas++;
                }
                else
                {
                    // Procesar bloque finalizado
                    AgregarHorarioConcatenado(horariosCarga, aulaNombre, aforo, enchufes, inicioConcatenacion, finConcatenacion);
                    // Reiniciar concatenación
                    IniciarNuevaConcatenacion(ref inicioConcatenacion, ref finConcatenacion, ref horasContadas, inicio, fin);
                }
            }

            // Procesar el último bloque
            AgregarHorarioConcatenado(horariosCarga, aulaNombre, aforo, enchufes, inicioConcatenacion, finConcatenacion);
        }

        private int CompararHoras(string horario1, string horario2)
        {
            return DateTime.Parse(horario1.Split('-')[0]).CompareTo(DateTime.Parse(horario2.Split('-')[0]));
        }

        // Función para inicializar una nueva concatenación
        private void IniciarNuevaConcatenacion(ref TimeSpan? inicioConcatenacion, ref TimeSpan? finConcatenacion, ref int horasContadas, TimeSpan inicio, TimeSpan fin)
        {
            inicioConcatenacion = inicio;
            finConcatenacion = fin;
            horasContadas = 1;
        }

        // Función para agregar un horario concatenado a la lista
        private void AgregarHorarioConcatenado(
            BindingList<HorarioAulaSemestral> horariosCarga,
            string aulaNombre,
            string aforo,
            string enchufes,
            TimeSpan? inicioConcatenacion,
            TimeSpan? finConcatenacion)
        {
            if (inicioConcatenacion != null && finConcatenacion != null)
            {
                horariosCarga.Add(new HorarioAulaSemestral
                {
                    Nombre = aulaNombre,
                    Horario = $"{inicioConcatenacion.Value:hh\\:mm} - {finConcatenacion.Value:hh\\:mm}",
                    Aforo = aforo,
                    Enchufes = enchufes
                });
            }
        }


        private void CargarAulas()
        {
            BindingList<horarioCompleto> listaHorariosSinAgrupar = horarioCompletoBO.AlumnoistarTodos();
            BindingList<HorarioAulaSemestral> listaHorariosAgrupados = agruparHorarios(listaHorariosSinAgrupar);
            listaHorarios = ObtenerHorarioInicial(listaHorariosAgrupados);
            DataTable dt = new DataTable();
            dt.Columns.Add("Nombre");
            dt.Columns.Add("Horario");
            dt.Columns.Add("Aforo");
            dt.Columns.Add("Enchufes");

            // Para llenar el DropDownList
            List<char> pabellones = listaHorarios.Select(h => h.Nombre[0]).Distinct().ToList();
            PabellonDropDownList.Items.Clear();
            PabellonDropDownList.Items.Add(new ListItem("Todos", "0")); // Opción para mostrar todos los pabellones
            foreach (char pabellon in pabellones)
            {
                PabellonDropDownList.Items.Add(new ListItem(pabellon.ToString(), pabellon.ToString()));
            }

            // Resto del código para cargar aulas
            foreach (HorarioAulaSemestral _horario in listaHorarios)
            {
                dt.Rows.Add(_horario.Nombre, _horario.Horario, _horario.Aforo, _horario.Enchufes);
            }

            // Almacena la tabla original
            OriginalAulasDataTable = dt.Copy();
            AulasDataTable = dt;
            BindGrid();
        }
        private BindingList<HorarioAulaSemestral> agruparHorarios(BindingList<horarioCompleto> listaHorariosSinAgrupar)
        {
            BindingList<HorarioAulaSemestral> nuevaLista = new BindingList<HorarioAulaSemestral>();
            HorarioAulaSemestral horarioAgrupado;
            foreach (horarioCompleto _horario in listaHorariosSinAgrupar)
            {
                horarioAgrupado = new HorarioAulaSemestral();
                string nombreAula = $"{_horario.idPabellon}{_horario.idPiso:D1}{_horario.idAula:D2}";
                string horario = $"{_horario.horaIni:D2}:00 - {_horario.horaFin:D2}:00";
                string aforo = $"{_horario.aforo:D2}";
                string enchufes = $"{_horario.cantEnchufes:D2}";

                horarioAgrupado.Horario = horario;
                horarioAgrupado.Nombre = nombreAula;
                horarioAgrupado.Aforo = aforo;
                horarioAgrupado.Enchufes = enchufes;
                nuevaLista.Add(horarioAgrupado);
            }
            return nuevaLista;
        }

        protected void PabellonDropDownList_SelectedIndexChanged(object sender, EventArgs e)
        {
            string selectedPabellon = PabellonDropDownList.SelectedValue;

            if (selectedPabellon != "0") // Si no se selecciona "Todos"
            {
                DataView dv = OriginalAulasDataTable.DefaultView; // Usar la tabla original
                dv.RowFilter = $"Nombre LIKE '{selectedPabellon}%'"; // Filtrar por pabellón
                AulasDataTable = dv.ToTable();
            }
            else
            {
                AulasDataTable = OriginalAulasDataTable.Copy(); // Cargar todas las aulas
            }

            BindGrid(); // Volver a enlazar el GridView
        }

        private void BindGrid()
        {
            AulasGridView.DataSource = AulasDataTable;
            AulasGridView.DataBind();
        }

        protected void SortBy_Click(object sender, EventArgs e)
        {
            LinkButton sortButton = (LinkButton)sender;
            string sortExpression = sortButton.CommandArgument;

            SelectedButtonID = sortButton.ID;

            CurrentSortExpression = sortExpression;
            CurrentSortDirection = "ASC";
            SortTable();

            UpdateButtonStyles();
        }

        protected void ReverseSort_Click(object sender, EventArgs e)
        {
            CurrentSortDirection = CurrentSortDirection == "ASC" ? "DESC" : "ASC";
            SortTable();
        }

        private void SortTable()
        {
            if (AulasDataTable != null)
            {
                DataView dv = AulasDataTable.DefaultView;
                dv.Sort = CurrentSortExpression + " " + CurrentSortDirection;
                AulasDataTable = dv.ToTable();
                BindGrid();
            }
        }

        private void UpdateButtonStyles()
        {
            List<LinkButton> sortButtons = new List<LinkButton> { OrdenarHorario, OrdenarAforo, OrdenarEnchufes };

            foreach (LinkButton button in sortButtons)
            {
                if (button.ID == SelectedButtonID)
                {
                    button.CssClass = "btn btn-primary text-white sort-button selected-button ";
                }
                else
                {
                    button.CssClass = "btn btn-primary text-white sort-button";
                }
            }
        }
    }
}