using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class MantenimientoAuxiliar : System.Web.UI.Page
    {
        private BindingList<auxiliar> listaAuxiliares;
        private AuxiliarBO auxiliarBO;
        public MantenimientoAuxiliar()
        {
            this.auxiliarBO = new AuxiliarBO();
            this.listaAuxiliares = this.auxiliarBO.listarTodos();
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            //if ((string)Session["idIntendente"] == null)
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            this.listaAuxiliares = this.auxiliarBO.listarTodos();
            dgvAuxiliar.DataSource = listaAuxiliares;
            dgvAuxiliar.DataBind();

        }
        protected void btnInsertar_Click(object sender, EventArgs e)
        {
            Response.Redirect("gestionarAuxiliar.aspx");
        }
        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            string idAuxiliar = ((LinkButton)sender).CommandArgument.ToString();
            auxiliar _auxiliar = this.auxiliarBO.obtenerPorId(Int32.Parse(idAuxiliar));


            int codigo_pucp = _auxiliar.codigoPucp;
            string apellidoM = _auxiliar.apellidoM;
            string apellidoP = _auxiliar.apellidoP;
            string correo = _auxiliar.correoPucp;
            string nombres = _auxiliar.nombre;
            string pabellon = _auxiliar.idPabellon;
            turno _turno = _auxiliar.turno;
            string contrasenha=_auxiliar.contrasenha;
            char ene = 'N';
            this.auxiliarBO.modificar(Int32.Parse(idAuxiliar), codigo_pucp, correo, nombres, apellidoP, apellidoM, pabellon, _turno, ene,contrasenha);
            Response.Redirect("MantenimientoAuxiliar.aspx");
        }
        protected void btnModificar_Click(object sender, EventArgs e)
        {
            string idAuxiliar = ((LinkButton)sender).CommandArgument;
            Session["idAuxiliar"] = idAuxiliar;
            Response.Redirect("gestionarAuxiliar.aspx?accion=modificar");

        }

        public char ConvertToChar(object value)
        {
            return (char)Convert.ToUInt16(value);
        }
    }
}