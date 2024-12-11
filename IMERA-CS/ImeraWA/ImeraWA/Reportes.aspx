<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="Reportes.aspx.cs" Inherits="ImeraWA.Reportes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Reportes
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h2 class="ms-5 mt-4">Generar Reportes</h2>

    <div class="mb-3">
        <label for="tipoReporte" class="form-label">Seleccionar Tipo de Reporte:</label>
        <asp:DropDownList ID="tipoReporte" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="tipoReporte_SelectedIndexChanged">
            <asp:ListItem Value="0">Seleccione un tipo de reporte</asp:ListItem>
            <asp:ListItem Value="1">Reporte Mensual</asp:ListItem>
            <asp:ListItem Value="2">Reporte Semanal por Pabellón</asp:ListItem>
        </asp:DropDownList>
    </div>

    <asp:Button ID="btnGenerarReporte" runat="server" CssClass="btn btn-primary" OnClick="btnGenerarReporte_Click" Visible="false" />
</asp:Content>
