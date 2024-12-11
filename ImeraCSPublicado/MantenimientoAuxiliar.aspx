<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="MantenimientoAuxiliar.aspx.cs" Inherits="ImeraWA.MantenimientoAuxiliar" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mantenimiento Auxiliares
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container-fluid">
        <div class="container row">
            <h2 style="font-size:34px; font-weight:bold;">Mantenimiento de Auxiliares</h2>
            <br />
            <br />
            <br />
            <asp:GridView ID="dgvAuxiliar" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" Width="100%">
                <Columns>
                    <asp:BoundField HeaderText="CodigoPucp" DataField="codigoPucp"/>
                    <asp:BoundField HeaderText="Nombre" DataField="nombre"/>
                    <asp:BoundField HeaderText="Apellido Paterno" DataField="apellidoP"/>
                    <asp:BoundField HeaderText="Apellido Materno" DataField="apellidoM"/>
                    <asp:BoundField HeaderText="Correo" DataField="CorreoPucp"/>
                    <asp:BoundField HeaderText="Pabellón" DataField="idPabellon"/>
                    <asp:BoundField HeaderText="Turno" DataField="Turno"/>
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-edit ps-2'></i>"  CommandArgument='<%# Eval("IdAuxiliar") %>'  OnClick="btnModificar_Click"/>
                            <asp:LinkButton runat="server" Text="<i class='fa-solid fa-trash ps-2'></i>" CommandArgument='<%# Eval("IdAuxiliar") %>' OnClick="btnEliminar_Click"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <div class="text-end">
                <asp:Button ID="btnInsertar" CssClass="float-end btn btn-primary" runat="server" Text="Insertar" OnClick="btnInsertar_Click" />
            </div>
        </div>
        
    </div>
</asp:Content>
