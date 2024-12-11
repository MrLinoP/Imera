<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="HomeIntendente.aspx.cs" Inherits="ImeraWA.HomeIntendente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Imera - HomeIntendente
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container row">
        <h2 style="font-size:34px; font-weight:bold;">Aulas libres Semestrales</h2>
        <br />
        <br />
        <br />
        <asp:GridView ID="dgvAulasLibresSemestrales" runat="server" AllowPaging="false"  AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" style="text-align: center; vertical-align:middle;">
            <Columns>
                <asp:BoundField HeaderText="Aula" DataField="Nombre" />
                <asp:BoundField HeaderText="Horario" DataField="Horario" />
                <asp:BoundField HeaderText="Día" DataField="Dia" />
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" 
                            CommandArgument='<%# Eval("Nombre") + "|" + Eval("Horario")+ "|" + Eval("Dia") %>' OnClick="btn_eliminarAulaLibreSemestral"/>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
        
</asp:Content>
