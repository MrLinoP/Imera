using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.CodeDom;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Script.Serialization;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class ReporteMensual : System.Web.UI.Page
    {
        private ReporteBO reporteBO;
        private BindingList<reporteMensual> filasReporteMensual;
        public ReporteMensual()
        {
            this.reporteBO= new ReporteBO();
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            this.filasReporteMensual = this.reporteBO.reporteMensual();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                GenerarReporteMensual();
            }
        }

        private void GenerarReporteMensual()
        {
            DataTable dtResultados = ObtenerDatosReporteMensual();
            gvResultados.DataSource = dtResultados;
            gvResultados.DataBind();
        }

        private DataTable ObtenerDatosReporteMensual()
        {
            DataTable dt = new DataTable();
            dt.Columns.Add("Pabellon");
            dt.Columns.Add("HorasLibresAsignadas");
            dt.Columns.Add("HorasLibresDesasignadas");
            dt.Columns.Add("Ratio");
            dt.Columns.Add("HorasDisponible");

            if(filasReporteMensual!=null)
                foreach (var fila in filasReporteMensual)
                {
                    dt.Rows.Add(fila.idPabellon, fila.horasAsignadasAulaLibre, fila.horasDeLibreAReservada, fila.ratioAR, fila.horasCambiadasADisponible);
                }

            return dt;
        }
        // Métodos para pasar datos a Highcharts en formato JSON
        public string GetPabellonesJson()
        {
            var pabellones = ObtenerDatosReporteMensual().AsEnumerable()
                .Select(row => row["Pabellon"].ToString()).ToArray();
            return new JavaScriptSerializer().Serialize(pabellones);
        }

        public string GetHorasAsignadasJson()
        {
            var horasAsignadas = ObtenerDatosReporteMensual().AsEnumerable()
                .Select(row => Convert.ToInt32(row["HorasLibresAsignadas"])).ToArray();
            return new JavaScriptSerializer().Serialize(horasAsignadas);
        }

        public string GetHorasRevocadasJson()
        {
            var horasRevocadas = ObtenerDatosReporteMensual().AsEnumerable()
                .Select(row => Convert.ToInt32(row["HorasLibresDesasignadas"])).ToArray();
            return new JavaScriptSerializer().Serialize(horasRevocadas);
        }

        public string GetHorasLibresJson()
        {
            var horasLibres = ObtenerDatosReporteMensual().AsEnumerable()
                .Select(row => Convert.ToInt32(row["HorasDisponible"])).ToArray();
            return new JavaScriptSerializer().Serialize(horasLibres);
        }
    }
}