using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class Imera : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnCerrarSesion(object sender, EventArgs e)
        {
            Session["IntendenteSesion"] = null;
            Response.Redirect("PaginaPrincipal.aspx");
        }
    }
}