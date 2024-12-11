<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="gestionarAulasLibresIntendente.aspx.cs" Inherits="ImeraWA.gestionarAulasLibresIntendente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 text-center">
                <h1>ASIGNAR AULAS LIBRES</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="d-flex justify-content-center align-items-center mb-4">
                    <label for="ddlPabellon" style="margin-right: 10px;">Seleccione un pabellón:</label>
                    <asp:DropDownList ID="ddlPabellon" runat="server" CssClass="dropdown-style" AutoPostBack="true" OnSelectedIndexChanged="DdlPabellon_SelectedIndexChanged">
                    </asp:DropDownList>
                </div>
                <div class="d-flex justify-content-start align-items-center mb-4">
                    <div style="background-color: #ffd800; padding: 10px; border-radius: 5px;">
                        <h5>Leyenda:</h5>
                        <span class="badge bg-primary">Aula libre semestral</span>
                        <span class="badge bg-light text-dark">Aula disponible</span>
                    </div>
                </div>
                <asp:UpdatePanel ID="updPanelHorario" runat="server">
                    <ContentTemplate>
                        <asp:Table ID="tableHorario" runat="server" CssClass="table table-bordered mt-3">
                            <asp:TableHeaderRow>
                            </asp:TableHeaderRow>
                        </asp:Table>
                        
                        <div class="d-flex justify-content-between mt-3">
                            <asp:Button ID="btnCancelar" runat="server" CssClass="btn btn-danger" Text="Cancelar" OnClick="btnCancelar_Click" />
                            <asp:Button ID="btnEnviar" runat="server" CssClass="btn btn-success" Text="Enviar" OnClick="btnEnviar_Click" />
                        </div>
                    </ContentTemplate>
                    <Triggers>
                        <asp:AsyncPostBackTrigger ControlID="btnCancelar" EventName="Click" />
                        <asp:AsyncPostBackTrigger ControlID="btnEnviar" EventName="Click" />
                    </Triggers>
                </asp:UpdatePanel>
            </div>
        </div>
    </div>
</asp:Content>