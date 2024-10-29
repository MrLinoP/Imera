<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="ImeraWA.Home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Progra3 - Home
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h2 class="ms-5 mt-4">BIENVENIDOS A IMERA PUCP</h2>
    <div class="d-flex align-items-center">
        <!-- Imagen -->
        <div class="p-5">
            <img src="Images/cia.png" width="500" />
        </div>
        
        <!-- Botones -->
        <div class="d-flex flex-column gap-3">
            <button class="btn btn-info p-3 btn-custom-size">Asignar aulas libres semestrales</button>
            <button class="btn btn-info p-3 btn-custom-size">Gestionar pabellones</button>
            <button class="btn btn-info p-3 btn-custom-size">Gestionar auxiliares de aula</button>
            <button class="btn btn-info p-3 btn-custom-size">Visualizar reportes</button>
        </div>
    </div>
</asp:Content>
