<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="MantenimientoPabellon.aspx.cs" Inherits="ImeraWA.MantenimientoPabellon" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <h2>Mantenimiento de Pabellones</h2>
        <br />
        <div class="container row">
            <asp:GridView ID="dgvPabellon" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                <Columns>
                    <asp:BoundField HeaderText="Letra" DataField="idPabellon"/>
                    <asp:BoundField HeaderText="Nombre" DataField="nombre"/>
                    <asp:BoundField HeaderText="n° Pisos" DataField="numeroPisos"/>
                </Columns>
            </asp:GridView>
            <div class="text-end">
                <asp:Button ID="btnInsertar" CssClass="float-start btn btn-primary" runat="server" Text="Insertar" OnClick="btnAgregarPabellon_Click" />
            </div>
        </div>
    </div>
</asp:Content>
