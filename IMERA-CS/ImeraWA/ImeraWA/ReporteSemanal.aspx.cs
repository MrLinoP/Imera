using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class ReporteSemanal : System.Web.UI.Page
    {
        public BindingList<pabellon> pabellonesLista;
        private PabellonBO pabellonBO;
        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            this.pabellonBO=new PabellonBO();
            this.pabellonesLista = pabellonBO.listarTodos();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Llenar el DropDownList con los pabellones desde el back-end
                CargarPabellones();
            }
        }

        private void CargarPabellones()
        {

            foreach (pabellon _pabellon in pabellonesLista)
            {
                pabellones.Items.Add(new ListItem("Pabellón " + _pabellon.idPabellon.ToString()));
            }
            
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            // Guardar los valores en la sesión antes de redirigir
            Session["pabellon"] = pabellones.SelectedValue;

            // Redirigir a la página de reporte generada
            Response.Redirect("ReporteGeneradoSem.aspx");
        }
    }
}