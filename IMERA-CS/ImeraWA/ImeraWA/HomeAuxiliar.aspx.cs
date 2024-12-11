using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection.Emit;
using System.Web;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class HomeAuxiliar : System.Web.UI.Page
    {
        private HorarioAuxiliarBO horarioAuxiliarBO;
        private AulaBO aulaBO;
        private PisoBO pisoBO;
        private HorarioBO horarioBO;
        private CambioDeEstadoBO cambioDeEstadoBO;
        private string SelectedColor
        {
            get { return ViewState["SelectedColor"] as string ?? "White"; }
            set { ViewState["SelectedColor"] = value; }
        }

        private BindingList<Aula> Aulas
        {
            get { return (BindingList<Aula>)ViewState["Aulas"]; }
            set { ViewState["Aulas"] = value; }
        }

        protected void Page_Init(object sender, EventArgs e)
        {

            //if ((string)Session["idIntendente"] == null)
            if ((persona)Session["AuxiliarSesion"] == null)
                Response.Redirect("Login.aspx");
            horarioAuxiliarBO = new HorarioAuxiliarBO();
            aulaBO = new AulaBO();
            pisoBO = new PisoBO();
            horarioBO = new HorarioBO();
            cambioDeEstadoBO = new CambioDeEstadoBO();
            codigoAuxiliar.Text = ((persona)Session["AuxiliarSesion"]).codigoPucp.ToString();
            nombreAuxiliar.Text = ((persona)Session["AuxiliarSesion"]).nombre + " " +
                                    ((persona)Session["AuxiliarSesion"]).apellidoP + " " +
                                    ((persona)Session["AuxiliarSesion"]).apellidoM;
            correoAuxiliar.Text = ((persona)Session["AuxiliarSesion"]).correoPucp;
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                SelectedColor = "White";
                persona _auxiliar = (persona)Session["AuxiliarSesion"];
                pabellonAuxiliar.Text = "Pabellón " + _auxiliar.idPabellon;
                ddlPisos.DataSource = pisoBO.listarIdPisos(_auxiliar.idPabellon);
                //ddlPisos.DataTextField = "IdPiso";
                //ddlPisos.DataValueField = "IdPiso";
                ddlPisos.DataBind();

                Aulas = ObtenerAulas();
                GenerarTablaHorarios();
            }
            else
            {
                GenerarTablaHorarios();
            }
            hfCursorClass.Value = $"cursor-{SelectedColor}";
        }

        private void GenerarTablaHorarios()
        {
            tblSchedule.Rows.Clear();
            tblSchedule.CssClass = "table table-bordered schedule-table custom-table";
            CrearEncabezadoTabla();
            CrearFilasTabla();
        }

        private void CrearEncabezadoTabla()
        {
            TableHeaderRow headerRow = new TableHeaderRow();
            TableHeaderCell timeHeaderCell = new TableHeaderCell
            {
                Text = "Tiempo",
                Width = Unit.Pixel(120),
                Height = Unit.Pixel(56),
                CssClass = "custom-header"
            };
            headerRow.Cells.Add(timeHeaderCell);
            foreach (Aula aula in Aulas)
            {
                TableHeaderCell aulaHeaderCell = new TableHeaderCell
                {
                    Text = aula.Nombre,
                    Height = Unit.Pixel(51),
                    CssClass = "custom-header"
                };
                headerRow.Cells.Add(aulaHeaderCell);
            }
            tblSchedule.Rows.Add(headerRow);
        }

        private void CrearFilasTabla()
        {
            string[] horarios = { "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00",
                "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00",
                "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00",
                "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00" };

            foreach (string horario in horarios)
            {
                TableRow row = new TableRow();
                TableCell horarioCell = new TableCell
                {
                    Text = horario,
                    Height = Unit.Pixel(51),
                    CssClass = "custom-cell"
                };
                row.Cells.Add(horarioCell);
                foreach (Aula aula in Aulas)
                {
                    TableCell aulaCell = new TableCell
                    {
                        CssClass = "custom-cell",
                        Height = Unit.Pixel(51)
                    };
                    Button button = new Button
                    {
                        Width = Unit.Percentage(100),
                        Height = Unit.Percentage(100),
                        CommandArgument = aula.Nombre + "|" + horario,
                        BorderWidth = Unit.Pixel(0)
                    };
                    button.Click += new EventHandler(Cell_Click);
                    button.Style["cursor"] = $"url('Images/bucket-{SelectedColor}.png'), auto";
                    ConfigurarColorBoton(button, aula, horario);
                    aulaCell.Controls.Add(button);
                    row.Cells.Add(aulaCell);
                }
                tblSchedule.Rows.Add(row);
            }
        }

        private void ConfigurarColorBoton(Button button, Aula aula, string horario)
        {
            Horario horarioEncontrado = aula.Horarios.FirstOrDefault(h => h.Tiempo == horario);
            if (horarioEncontrado == null || horarioEncontrado.Estado == null)
            {
                string script = "alert('UN AUXILIAR NO PUEDE ACCEDER AL SISTEMA UN DOMINGO'); window.location='PaginaPrincipal.aspx';";
                ClientScript.RegisterStartupScript(this.GetType(), "alert", script, true);
                Session["AuxiliarSesion"] = null;
                Response.Redirect("PaginaPrincipal.aspx");
            }
            string horarioEstado = horarioEncontrado.Estado;
            switch (horarioEstado)
            {
                case "AULA_LIBRE":
                    button.BackColor = System.Drawing.Color.Yellow;
                    break;
                case "DISPONIBLE":
                    button.BackColor = System.Drawing.Color.White;
                    break;
                case "AULA_RESERVADA":
                    button.BackColor = System.Drawing.Color.Blue;
                    break;
                case "AULA_CLASE":
                    button.BackColor = System.Drawing.Color.SkyBlue;
                    break;
                case "AULA_LIBRE_SEMESTRAL":
                    button.BackColor = System.Drawing.Color.Gold;
                    break;
            }
        }


        private BindingList<Aula> ObtenerAulas()
        {
            Aula aula;
            BindingList<Aula> aulas = new BindingList<Aula>();
            BindingList<string> listaNombresAulas = aulaBO.listarNombresAula(int.Parse(ddlPisos.SelectedValue), ((persona)Session["AuxiliarSesion"]).idPabellon);
            BindingList<horarioAuxiliar> horariosAuxiliar = horarioAuxiliarBO.listarTodos(((persona)Session["AuxiliarSesion"]).idPabellon, int.Parse(ddlPisos.SelectedValue));
            foreach (string aux in listaNombresAulas)
            {
                aula = new Aula
                {
                    Nombre = aux
                };
                aulas.Add(aula);
            }

            string[] horarios = { "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00",
                "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00",
                "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00",
                "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00" };

            foreach (Aula aulaAux in aulas)
            {
                foreach (horarioAuxiliar horarioAux in horariosAuxiliar)
                {
                    if (aulaAux.Nombre == horarioAux.nombreAula)
                    {
                        Horario horario = new Horario
                        {
                            Tiempo = horarioAux.horario,
                            Estado = horarioAux.estado.ToString()
                        };
                        aulaAux.Horarios.Add(horario);
                    }
                }
            }
            return aulas;
        }


        protected void SetPaintBucket(object sender, EventArgs e)
        {
            if (sender is Button button)
            {
                SelectedColor = button.CommandArgument;
                SetCursorColor(SelectedColor);
            }
        }

        private void SetCursorColor(string color)
        {
            hfCursorClass.Value = $"cursor-{color}";
            ScriptManager.RegisterStartupScript(this, GetType(), "UpdateCursor",
                $@"
                document.body.className = '{hfCursorClass.Value}';
                var buttons = document.querySelectorAll('.schedule-cell button');
                buttons.forEach(button => {{
                    button.style.cursor = 'url(Images/bucket-{color}.png), auto';
                }});", true);
        }

        protected void Cell_Click(object sender, EventArgs e)
        {
            if (sender is Button button)
            {
                string[] args = button.CommandArgument.Split('|');
                string aulaNombre = args[0], tiempo = args[1];
                Aula aulaencontrada = Aulas.FirstOrDefault(a => a.Nombre == aulaNombre);
                Horario horario = aulaencontrada?.Horarios.FirstOrDefault(h => h.Tiempo == tiempo);

                if (horario != null && IsEstadoValido(horario.Estado))
                {
                    (string estadoNuevo, estadoAula estadoAux) = ObtenerEstadoNuevo(button);
                    (string idPabellon, int idPiso, int idAula, int idHora) = ObtenerAulaInfo(aulaNombre, tiempo);
                    
                    
                    estadoAula estadoAnteriorAux = ObtenerEstadoAnterior(horario.Estado);
                    if (estadoAux == estadoAnteriorAux) return;
                    horarioBO.modificar(idPabellon, idPiso, idAula, idHora, estadoAux);
                    cambioDeEstadoBO.insertar(idPabellon, idPiso, idAula, idHora, estadoAnteriorAux, estadoAux, ((persona)Session["AuxiliarSesion"]).idPersona);
                    horario.Estado = estadoNuevo;
                }
            }
        }

        private bool IsEstadoValido(string estado) => estado != "AULA_CLASE" && estado != "AULA_LIBRE_SEMESTRAL";

        private (string, estadoAula) ObtenerEstadoNuevo(Button button)
        {
            string estadoNuevo;
            estadoAula estadoAux;

            switch (SelectedColor)
            {
                case "yellow":
                    estadoNuevo = "AULA_LIBRE";
                    estadoAux = estadoAula.AULA_LIBRE;
                    button.BackColor = System.Drawing.Color.Yellow;
                    break;
                case "blue":
                    estadoNuevo = "AULA_RESERVADA";
                    estadoAux = estadoAula.AULA_RESERVADA;
                    button.BackColor = System.Drawing.Color.Blue;
                    break;
                default:
                    estadoNuevo = "DISPONIBLE";
                    estadoAux = estadoAula.DISPONIBLE;
                    button.BackColor = System.Drawing.Color.White;
                    break;
            }

            return (estadoNuevo, estadoAux);
        }

        private (string, int, int, int) ObtenerAulaInfo(string aulaNombre, string tiempo)
        {
            string idPabellon = aulaNombre[0].ToString();
            int idPiso = Int32.Parse(aulaNombre[1].ToString());
            int idAula = Int32.Parse(aulaNombre.Substring(2));
            int idHora = Int32.Parse(tiempo.Substring(0, 2)) - 6;
            return (idPabellon, idPiso, idAula, idHora);
        }

        private estadoAula ObtenerEstadoAnterior(string estado)
        {
            switch (estado)
            {
                case "AULA_LIBRE":
                    return estadoAula.AULA_LIBRE;
                case "DISPONIBLE":
                    return estadoAula.DISPONIBLE;
                case "AULA_RESERVADA":
                    return estadoAula.AULA_RESERVADA;
                default:
                    return estadoAula.DISPONIBLE; // Valor por defecto
            }
        }
        protected void DdlPiso_SelectedIndexChanged(object sender, EventArgs e)
        {
            Aulas = ObtenerAulas();
            GenerarTablaHorarios();
        }
        protected void btnCerrarSesion(object sender, EventArgs e)
        {
            Session["AuxiliarSesion"] = null;
            Response.Redirect("PaginaPrincipal.aspx");
        }
    }


}