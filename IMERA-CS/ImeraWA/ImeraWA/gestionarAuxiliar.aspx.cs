using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using static System.Collections.Specialized.BitVector32;

namespace ImeraWA
{
    public partial class gestionarAuxiliar : System.Web.UI.Page
    {
        private AuxiliarBO auxiliarBO;
        private PabellonBO pabellonBO;
        private string idAuxiliar;
        private bool esta_modificando;
        public gestionarAuxiliar()
        {
            this.auxiliarBO = new AuxiliarBO();
            this.pabellonBO = new PabellonBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                ddlPabellon.DataSource = pabellonBO.listarTodos();
                ddlPabellon.DataTextField = "IdPabellon";
                ddlPabellon.DataValueField = "IdPabellon";
                ddlPabellon.DataBind();
            }

        }
        protected void Page_Init(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");

            txtIdAuxiliar.Enabled = false;
            this.idAuxiliar = (string)Session["idAuxiliar"];
            string accion = Request.QueryString["accion"];
            if (accion != null && accion == "modificar")
                esta_modificando = true;
            else
                esta_modificando = false;
            if (esta_modificando)
            {
                this.cargarDatosDeLaBD();
                tituloTexto.Text = "Modificación de Auxiliar";
            }
            else
            {
                tituloTexto.Text = "Creación de Auxiliar";
            }
        }
        private void cargarDatosDeLaBD()
        {
            auxiliar _auxiliar = this.auxiliarBO.obtenerPorId(Int32.Parse(this.idAuxiliar));
            txtIdAuxiliar.Text = _auxiliar.idAuxiliar.ToString();
            txtIdAuxiliar.Enabled = false;
            txtcodigoPucp.Text = _auxiliar.codigoPucp.ToString();
            ddlPabellon.SelectedValue = _auxiliar.idPabellon;
            txtNombres.Text = _auxiliar.nombre;
            txtCorreo.Text = _auxiliar.correoPucp;
            txtApellidoP.Text = _auxiliar.apellidoP;
            txtApellidoM.Text = _auxiliar.apellidoM;
            txtContrasenha.Text = _auxiliar.contrasenha;

            if (_auxiliar.turno == turno.MAÑANA)
            {
                rbMañana.Checked = true;
                rbTarde.Checked = false;
            }
            else if (_auxiliar.turno == turno.TARDE)
            {
                rbTarde.Checked = true;
                rbMañana.Checked = false;

            }
        }
        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("MantenimientoAuxiliar.aspx");
        }
        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            int codigo_pucp = Int32.Parse(txtcodigoPucp.Text);
            string apellidoM = txtApellidoM.Text;
            string apellidoP = txtApellidoP.Text;
            string correo = txtCorreo.Text;
            string nombres = txtNombres.Text;
            string pabellon = ddlPabellon.SelectedValue;
            turno _turno = turno.MAÑANA;
            string contrasenha = txtContrasenha.Text;

            if (rbTarde.Checked == true && rbMañana.Checked == false)
            {
                _turno = turno.TARDE;
            }
            else if (rbTarde.Checked == false && rbMañana.Checked == true)
            {
                _turno = turno.MAÑANA;
            }

            if (esta_modificando)
            {
                int idAux = Int32.Parse(txtIdAuxiliar.Text);
                auxiliar _auxiliar = this.auxiliarBO.obtenerPorId(idAux);
                this.auxiliarBO.modificar(idAux, codigo_pucp, correo, nombres, apellidoP, apellidoM, pabellon, _turno, (char)_auxiliar.activo, contrasenha);
            }
            else
                this.auxiliarBO.insertar(codigo_pucp, correo, nombres, apellidoP, apellidoM, pabellon, _turno, 'S', contrasenha, rol.AUXILIAR);

            Response.Redirect("MantenimientoAuxiliar.aspx");
        }
    }
}