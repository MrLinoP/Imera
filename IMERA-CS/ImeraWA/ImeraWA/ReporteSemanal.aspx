<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="ReporteSemanal.aspx.cs" Inherits="ImeraWA.ReporteSemanal" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Reporte Semanal
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h2 class="ms-5 mt-4">Reporte de Aulas por Pabellón</h2>

    <!-- Controles para seleccionar la fecha inicial y final -->

    <div class="mb-3">
        <label for="pabellones" class="form-label">Seleccionar Pabellón:</label>
        <asp:DropDownList ID="pabellones" runat="server" CssClass="form-select">
            <asp:ListItem Value="0">Seleccione un pabellón</asp:ListItem>
        </asp:DropDownList>
    </div>
    <asp:Button ID="btnGenerarReporte" runat="server" Text="Generar Reporte" CssClass="btn btn-primary" OnClick="btnGenerarReporte_Click" />
</asp:Content>
