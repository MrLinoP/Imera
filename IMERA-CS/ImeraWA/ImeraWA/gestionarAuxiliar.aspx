<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="gestionarAuxiliar.aspx.cs" Inherits="ImeraWA.gestionarAuxiliar" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Gestión Auxiliar
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container my-5">
        <div class="card">
            <div class="card-header" >
                <div class="col-sm-6 d-flex">
                    <asp:Label ID="tituloTexto" runat="server" Text="ID Auxiliar: " CssClass="col-sm-0 col-form-label " style="font-size:34px; font-weight:bold;" ></asp:Label>
                </div>
            </div>
            <div class="card-body">
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblIdAuxiliar" runat="server" Text="ID Auxiliar: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtIdAuxiliar" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblcodigoPucp" runat="server" Text="Codigo PUCP: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtcodigoPucp" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblNombres" runat="server" Text="Nombre: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtNombres" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblApellidoP" runat="server" Text="Apellido Paterno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtApellidoP" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblApellidoM" runat="server" Text="Apellido Materno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtApellidoM" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblPabellon" runat="server" Text="Pabellón a asignar: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:DropDownList ID="ddlPabellon" runat="server" CssClass="form-control"></asp:DropDownList>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblCorreo" runat="server" Text="Correo de contacto: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblContrasenha" runat="server" Text="Contraseña: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex">
                        <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control" TextMode="Password" placeholder="Ingrese la contraseña"></asp:TextBox>
                    </div>
                </div>
                <div class="mb-3 row justify-content-center">
                    <asp:Label ID="lblTurno" runat="server" Text="Turno: " CssClass="col-sm-2 col-form-label"></asp:Label>
                    <div class="col-sm-6 d-flex align-items-center">
                        <div class="form-check form-check-inline me-3">
                            <input id="rbMañana" class="form-check-input" type="radio" runat="server" name="tipoTurno"/>
                            <label id="lblMañana" class="form-check-label" for="cphContenido_rbMañana">MAÑANA</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input id="rbTarde" class="form-check-input" type="radio" runat="server" name="tipoTurno"/>
                            <label id="lblTarde" class="form-check-label" for="cphContenido_rbTarde">TARDE</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer d-flex justify-content-between">
                <asp:Button ID="btnRegresar" CssClass="float-end btn btn-secondary" runat="server" Text="Regresar" OnClick="btnRegresar_Click"/>
                <asp:Button ID="btnGuardar"  CssClass="float-start btn btn-primary" runat="server" Text="Guardar" OnClick="btnGuardar_Click"/>
            </div>
        </div>
    </div>
</asp:Content>
