using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class Login : System.Web.UI.Page
    {
        PersonaBO personaBO;
        protected void Page_Load(object sender, EventArgs e)
        {

            Session["IntendenteSesion"] = null;
            Session["AuxiliarSesion"] = null;
            personaBO = new PersonaBO();
        }
        protected void btnIngresar(object sender, EventArgs e)
        {
            persona _persona = personaBO.obtenerPorDatosInicioSesion(txtEmail.Text, CifradoCesar.Cifrar(txtContrasenha.Text));
            if (_persona == null) {
                wrongPwd.Visible = true;
                txtEmail.Text = null;
                return;
            }

            if (_persona.rol == rol.AUXILIAR && (char)_persona.activo == 'S')
            {
                Session["AuxiliarSesion"] = _persona;
                Response.Redirect("HomeAuxiliar.aspx");
            }else if (_persona.rol == rol.INTENDENTE)
            {
                Session["IntendenteSesion"] = _persona;
                Response.Redirect("HomeIntendente.aspx");
            }
        }
    }
}