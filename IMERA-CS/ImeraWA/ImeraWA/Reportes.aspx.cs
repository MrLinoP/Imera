using ImeraBaseBO.ImeraServicio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class Reportes : System.Web.UI.Page
    {
        protected void Page_Init(object sender, EventArgs e)
        {
            //if ((string)Session["idIntendente"] == null)
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
        }
            protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                btnGenerarReporte.Visible = false; // Ocultar el botón al cargar la página
            }
        }

        protected void tipoReporte_SelectedIndexChanged(object sender, EventArgs e)
        {
            int selectedValue = int.Parse(tipoReporte.SelectedValue);

            // Configurar el botón según la selección
            switch (selectedValue)
            {
                case 1:
                    btnGenerarReporte.Text = "Generar Reporte Mensual";
                    btnGenerarReporte.CommandArgument = "Mensual";
                    btnGenerarReporte.Visible = true;
                    btnGenerarReporte.CssClass = "btn btn-primary";
                    break;
                case 2:
                    btnGenerarReporte.Text = "Generar Reporte Semanal";
                    btnGenerarReporte.CommandArgument = "Semanal";
                    btnGenerarReporte.Visible = true;
                    btnGenerarReporte.CssClass = "btn btn-secondary";
                    break;
                default:
                    btnGenerarReporte.Visible = false;
                    break;
            }
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            string tipoReporte = ((Button)sender).CommandArgument;

            // Redireccionar según el tipo de reporte
            if (tipoReporte == "Mensual")
            {
                Response.Redirect("ReporteMensual.aspx");
            }
            else if (tipoReporte == "Semanal")
            {
                Response.Redirect("ReporteSemanal.aspx");
            }
        }
    }
}