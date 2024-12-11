using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class gestionarAulasLibresIntendente : System.Web.UI.Page
    {
        private PabellonBO pabellonBO;
        private HorarioIntendenteBO horarioIntendenteBO;
        private BindingList<HorarioAulaSemestral> Horarios
        {
            get { return (BindingList<HorarioAulaSemestral>)ViewState["Horarios"]; }
            set { ViewState["Horarios"] = value; }
        }

        private BindingList<HorarioAulaSemestral> HorariosAInsertar
        {
            get { return (BindingList<HorarioAulaSemestral>)ViewState["HorariosAInsertar"]; }
            set { ViewState["HorariosAInsertar"] = value; }
        }

        public gestionarAulasLibresIntendente()
        {
            BindingList<HorarioAulaSemestral> aux = new BindingList<HorarioAulaSemestral>();
            HorariosAInsertar = aux;
            this.pabellonBO = new PabellonBO();
            this.horarioIntendenteBO = new HorarioIntendenteBO();
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            // Cargar los datos iniciales solo en la primera carga de la página
            ddlPabellon.DataSource = this.pabellonBO.listarTodos();
            ddlPabellon.DataTextField = "IdPabellon";
            ddlPabellon.DataValueField = "IdPabellon";
            ddlPabellon.DataBind();

            //BindingList<HorarioAulaSemestral> horarioList = ObtenerHorarioInicial();
            //Horarios = horarioList; // Guardar en ViewState
            //GenerarTabla(horarioList);
        }
        protected void Page_Load(object sender, EventArgs e)
        {

            if (!IsPostBack)
            {
                BindingList<HorarioAulaSemestral> horarioList = ObtenerHorarioInicial();
                Horarios = horarioList;
                GenerarTabla(horarioList);
            }
            else
            {
                // Recuperar datos del ViewState en postbacks
                BindingList<HorarioAulaSemestral> horarioList = (BindingList<HorarioAulaSemestral>)ViewState["Horarios"];
                GenerarTabla(horarioList);
            }
        }

        private BindingList<HorarioAulaSemestral> ObtenerHorarioInicial()
        {
            tableHorario.Rows.Clear();
            BindingList<HorarioAulaSemestral> horariosCarga = new BindingList<HorarioAulaSemestral>();
            BindingList<horarioIntendente> horariosBd = horarioIntendenteBO.listarTodosDisponibles(ddlPabellon.SelectedValue);

            // Agrupar horarios por aula y día
            var agrupados = horariosBd.GroupBy(h => new { h.nombreAula, h.diaSemana });

            foreach (var grupo in agrupados)
            {
                var horarios = grupo.ToList();
                horarios.Sort((h1, h2) => DateTime.Parse(h1.horario.Split('-')[0]).CompareTo(DateTime.Parse(h2.horario.Split('-')[0])));

                // Variables para la concatenación
                string aulaNombre = grupo.Key.nombreAula;
                string diaSemana = grupo.Key.diaSemana.ToString();
                TimeSpan? inicioConcatenacion = null;
                TimeSpan? finConcatenacion = null;
                int horasContadas = 0;

                foreach (var horario in horarios)
                {
                    TimeSpan inicio = TimeSpan.Parse(horario.horario.Split('-')[0]);
                    TimeSpan fin = TimeSpan.Parse(horario.horario.Split('-')[1]);

                    // Si estamos empezando una nueva concatenación
                    if (inicioConcatenacion == null)
                    {
                        inicioConcatenacion = inicio;
                        finConcatenacion = fin;
                        horasContadas = 1;
                    }
                    else
                    {
                        // Verificar si el horario es consecutivo
                        if (inicio == finConcatenacion)
                        {
                            // Concatenar horarios
                            finConcatenacion = fin;
                            horasContadas++;
                        }
                        else
                        {
                            // Si no es consecutivo, verificar si tenemos al menos 3 horas
                            if (horasContadas >= 3)
                            {
                                // Crear el nuevo objeto HorarioAulaSemestral
                                HorarioAulaSemestral nuevoHorario = new HorarioAulaSemestral
                                {
                                    Nombre = aulaNombre,
                                    Horario = $"{inicioConcatenacion.Value:hh\\:mm} - {finConcatenacion.Value:hh\\:mm}",
                                    Dia = diaSemana,
                                    Estado = "DISPONIBLE"
                                };
                                horariosCarga.Add(nuevoHorario);
                            }

                            // Reiniciar la concatenación
                            inicioConcatenacion = inicio;
                            finConcatenacion = fin;
                            horasContadas = 1;
                        }
                    }
                }

                // Verificar si al final tenemos un bloque válido
                if (horasContadas >= 3)
                {
                    HorarioAulaSemestral nuevoHorario = new HorarioAulaSemestral
                    {
                        Nombre = aulaNombre,
                        Horario = $"{inicioConcatenacion.Value:hh\\:mm} - {finConcatenacion.Value:hh\\:mm}",
                        Dia = diaSemana,
                        Estado = "DISPONIBLE"
                    };
                    horariosCarga.Add(nuevoHorario);
                }
            }

            return horariosCarga;
        }

        private void GenerarTabla(BindingList<HorarioAulaSemestral> horarioList)
        {
            // Limpiar la tabla antes de regenerarla
            tableHorario.Rows.Clear();

            // Crear encabezado
            TableHeaderRow headerRow = new TableHeaderRow();
            string[] dias = { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" };
            foreach (string dia in dias)
            {
                TableHeaderCell headerCell = new TableHeaderCell { Text = dia };
                headerRow.Cells.Add(headerCell);
            }
            tableHorario.Rows.Add(headerRow);

            // Crear 15 filas con 6 columnas, cada celda con un botón invisible
            for (int i = 0; i < 15; i++)
            {
                TableRow row = new TableRow();

                for (int j = 0; j < 6; j++)
                {
                    TableCell cell = new TableCell();
                    Button btnHorario = new Button
                    {
                        Text = "", // Texto vacío inicialmente
                        CssClass = "btn btn-light",
                        Visible = false, // Inicialmente invisible
                        CommandArgument = "0",
                        BorderStyle = BorderStyle.Solid,
                        BorderWidth = Unit.Pixel(1)
                    };
                    btnHorario.Click += new EventHandler(BtnHorario_Click); // Asignar el evento Click
                    cell.Controls.Add(btnHorario);
                    row.Cells.Add(cell);
                }

                tableHorario.Rows.Add(row);
            }

            // Configurar los botones según los datos de horarioList
            foreach (var horario in horarioList)
            {
                // Determinar la columna del día de la semana
                int colIndex = Array.IndexOf(dias, horario.Dia);
                if (colIndex == -1) continue; // Saltar si el día no coincide

                // Iterar por cada fila y verificar si ya existe un botón visible
                bool buttonInserted = false; // Bandera para verificar si se insertó un botón

                for (int rowIndex = 0; rowIndex < 15; rowIndex++)
                {
                    TableRow row = tableHorario.Rows[rowIndex + 1]; // +1 para omitir la fila del encabezado
                    TableCell cell = row.Cells[colIndex];
                    Button btnHorario = (Button)cell.Controls[0];

                    // Verificar si ya hay un botón visible en esta celda
                    if (btnHorario.Visible && btnHorario.CommandArgument.StartsWith($"{horario.Nombre}|{horario.Horario}"))
                    {
                        buttonInserted = true; // Ya existe un botón con el mismo nombre y horario
                        break; // Salir del bucle si ya se encontró un botón
                    }

                    if (!btnHorario.Visible) // Encontrar un botón invisible para configurar
                    {
                        btnHorario.Text = $"{horario.Nombre} {horario.Horario}";
                        btnHorario.Visible = true; // Hacerlo visible
                        btnHorario.CommandArgument = $"{horario.Nombre}|{horario.Horario}|{horario.Dia}|{horario.Estado}";
                        buttonInserted = true; // Se insertó un botón
                        break; // Salir de la iteración de filas una vez configurado
                    }
                }

                // Si no se insertó un botón, puedes decidir qué hacer (por ejemplo, mostrar un mensaje de advertencia, etc.)
                if (!buttonInserted)
                {
                    // Aquí puedes agregar lógica adicional si lo deseas
                }
            }
        }


        // Evento que cambia el color del botón cuando se hace clic
        protected void BtnHorario_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string[] args = btn.CommandArgument.Split('|');
            string aulaNombre = args[0];
            string horarioTiempo = args[1];
            string horarioDia = args[2];
            string horarioEstado = args[3];

            if (btn.CssClass == "btn btn-light")
                btn.CssClass = "btn btn-primary";
            else
                btn.CssClass = "btn btn-light";

            // Buscar el horario en la lista original Horarios
            foreach (HorarioAulaSemestral haux in Horarios)
            {
                if (haux.Nombre == aulaNombre && haux.Horario == horarioTiempo && haux.Dia == horarioDia)
                {
                    if (haux.Estado == "DISPONIBLE")
                    {
                        // Cambiar el estado a AULA_LIBRE_SEMESTRAL
                        haux.Estado = "AULA_LIBRE_SEMESTRAL";
                        //btn.CssClass = "btn btn-primary";
                        // Agregar a HorariosAInsertar
                        HorariosAInsertar.Add(new HorarioAulaSemestral
                        {
                            Nombre = aulaNombre,
                            Horario = horarioTiempo,
                            Dia = horarioDia,
                            Estado = "AULA_LIBRE_SEMESTRAL"
                        });
                    }
                    else if (haux.Estado == "AULA_LIBRE_SEMESTRAL")
                    {
                        // Cambiar el estado a DISPONIBLE
                        haux.Estado = "DISPONIBLE";
                        //btn.CssClass = "btn btn-light";
                        // Eliminar de HorariosAInsertar
                        var horarioToRemove = HorariosAInsertar.FirstOrDefault(h => h.Nombre == aulaNombre && h.Horario == horarioTiempo && h.Dia == horarioDia);
                        if (horarioToRemove != null)
                        {
                            HorariosAInsertar.Remove(horarioToRemove);
                        }
                    }
                    break; // Salir del bucle una vez encontrado
                }
            }
            Horarios = Horarios;
            // Actualizar el ViewState
            ViewState["HorariosAInsertar"] = HorariosAInsertar;
            //updPanelHorario.Update();
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Lógica para cancelar
            ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Acción cancelada.');", true);
            Response.Redirect("HomeIntendente.aspx");
        }

        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            // Recuperar la lista de horarios a insertar desde el ViewState
            BindingList<HorarioAulaSemestral> horariosAInsertar = (BindingList<HorarioAulaSemestral>)ViewState["HorariosAInsertar"];

            // Filtrar los horarios que tienen el estado AULA_LIBRE_SEMESTRAL
            List<HorarioAulaSemestral> horariosFiltrados = new List<HorarioAulaSemestral>();
            foreach (HorarioAulaSemestral horario in horariosAInsertar)
            {
                if (horario.Estado == "AULA_LIBRE_SEMESTRAL")
                {
                    horariosFiltrados.Add(horario);
                }
            }
            int cant = horariosFiltrados.Count;
            // Verificar si hay horarios para insertar
            if (cant > 0)
            {
                try
                {
                    // Lógica para insertar los horarios (puedes llamar a un método de tu capa de servicio aquí)
                    string[] nombresAulas = new string[cant];
                    string[] horarios = new string[cant];
                    string[] dias = new string[cant];
                    for (int i = 0; i < cant; i++)
                    {
                        nombresAulas[i] = horariosFiltrados[i].Nombre;
                        horarios[i] = horariosFiltrados[i].Horario;
                        dias[i] = horariosFiltrados[i].Dia;
                    }
                    horarioIntendenteBO.enviarAulasLibresSemestrales(nombresAulas, horarios, dias);

                    // Mostrar mensaje de éxito
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Horarios enviados exitosamente.');", true);
                }
                catch (Exception ex)
                {
                    // Manejo de errores
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", $"alert('Error al enviar los horarios: {ex.Message}');", true);
                }
            }
            else
            {
                // Mostrar mensaje si no hay horarios para enviar
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('No hay horarios para enviar.');", true);
            }
            Response.Redirect("HomeIntendente.aspx");
        }

        protected void DdlPabellon_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Obtener los horarios para el pabellón seleccionado
            BindingList<HorarioAulaSemestral> horarioList = ObtenerHorarioInicial();

            // Actualizar los ViewState
            Horarios = horarioList;
            HorariosAInsertar.Clear();

            // Regenerar la tabla
            GenerarTabla(horarioList);
            //updPanelHorario.Update();
        }

    }
}