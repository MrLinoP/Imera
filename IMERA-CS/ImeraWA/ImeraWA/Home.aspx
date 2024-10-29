<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="ImeraWA.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Imera - Home
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container row">
        <asp:GridView ID="dgvAulasLibresSemestrales" runat="server" AllowPaging="false" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
            <Columns>
                <asp:BoundField HeaderText="Pabellón" DataField="NombrePabellon" />
                <asp:BoundField HeaderText="Aula" DataField="NumeroAula" />
                <asp:BoundField HeaderText="Hora Inicio" DataField="HoraInicio" />
                <asp:BoundField HeaderText="Hora Fin" DataField="HoraFin" />

                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" CommandArgument='<%# Eval("IdSeccion") %>' />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
        
</asp:Content>
