using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.Script.Serialization;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class ReporteGeneradoSem : System.Web.UI.Page
    {
        public HoraBO horaBO;
        public ReporteBO reporteBO;

        public BindingList<int> listaDistribucion;
        public BindingList<reporteSemanal> reporteSemanal;

        protected void Page_Init(object sender, EventArgs e)
        {
            //if ((string)Session["idIntendente"] == null)
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            this.horaBO=new HoraBO();
            this.reporteBO = new ReporteBO();

            string idPabellon = ((string)Session["pabellon"]).LastOrDefault().ToString();
            this.listaDistribucion = horaBO.distribucionHorariosReporteSemanal(idPabellon);
            this.reporteSemanal = reporteBO.reporteSemanal(idPabellon);
            pabellonNombre.Text= (string)Session["pabellon"];
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int[] arregloHoras = listaDistribucion.ToArray();

                // Serializar los datos en formato JSON
                JavaScriptSerializer jsSerializer = new JavaScriptSerializer();
                string jsonData = jsSerializer.Serialize(arregloHoras);

                // Generar el script con los datos para ser insertado en la página
                string script = $@"
                <script type='text/javascript'>
                    document.addEventListener('DOMContentLoaded', function() {{
                        Highcharts.chart('container', {{
                            chart: {{
                                type: 'column'
                            }},
                            title: {{
                                text: 'Cantidad de horas de aulas libres asignadas según la hora'
                            }},
                            xAxis: {{
                                categories: ['07:00','08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
                            }},
                            yAxis: {{
                                min: 0,
                                title: {{
                                    text: ''
                                }}
                            }},
                            tooltip: {{
                                pointFormat: '{{series.name}}: <b>{{point.y}}</b>'
                            }},
                            series: [{{
                                name: 'Horas asignadas',
                                data: {jsonData}
                            }}]
                        }});
                    }});
                </script>";

                // Agrega el script al final de la página
                ClientScript.RegisterStartupScript(this.GetType(), "HighchartsScript", script);
                dgvAuxiliar.DataSource = reporteSemanal;
                dgvAuxiliar.DataBind();
            } 
        }
    }
}