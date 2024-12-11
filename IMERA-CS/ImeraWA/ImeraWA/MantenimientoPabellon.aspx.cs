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
    public partial class MantenimientoPabellon : System.Web.UI.Page
    {
        private BindingList<pabellon> listaPabellones;
        private PabellonBO pabellonBO;

        public MantenimientoPabellon()
        {
            this.pabellonBO = new PabellonBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

            this.listaPabellones = this.pabellonBO.listarTodos();
            dgvPabellon.DataSource = listaPabellones;
            dgvPabellon.DataBind();
        }

        protected void btnAgregarPabellon_Click(object sender, EventArgs e)
        {
            Response.Redirect("subirCSV.aspx");
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
        }
    }
}