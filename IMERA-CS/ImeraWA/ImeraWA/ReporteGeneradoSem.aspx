<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="ReporteGeneradoSem.aspx.cs" Inherits="ImeraWA.ReporteGeneradoSem" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Reporte Generado
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
     <style>
     .highcharts-figure,
     .highcharts-data-table table {
         min-width: 360px;
         max-width: 800px;
         margin: 1em auto;
     }

     .highcharts-data-table table {
         font-family: Verdana, sans-serif;
         border-collapse: collapse;
         border: 1px solid #ebebeb;
         margin: 10px auto;
         text-align: center;
         width: 100%;
         max-width: 500px;
     }

     .highcharts-data-table th {
         font-weight: 600;
         padding: 0.5em;
     }

     .highcharts-data-table td,
     .highcharts-data-table th,
     .highcharts-data-table caption {
         padding: 0.5em;
     }
 </style>
 <div class="container mt-4">
     <asp:Label ID="pabellonNombre" runat="server" style="font-size: 24px; font-weight: bold;"></asp:Label>
     <h4>Reporte de trabajo de los auxiliares</h4>
     <asp:GridView ID="dgvAuxiliar" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" Width="100%">
         <Columns>
            <asp:BoundField HeaderText="CodigoPucp" DataField="codigoAuxiliar"/>
            <asp:BoundField HeaderText="Nombre" DataField="nombre"/>
            <asp:BoundField HeaderText="Horas asignadas como libre" DataField="horasAsignadas"/>
            <asp:BoundField HeaderText="Horas de aula libres desasignadas" DataField="horasDesasignadas"/>
        </Columns>
    </asp:GridView>

     <!-- Aquí añadimos el contenedor del gráfico -->
     <h4>Distribución de horarios asignados en los últimos 7 días</h4>
     <figure class="highcharts-figure">
         <div id="container"></div>
         <p class="highcharts-description">
             Gráfica de distribución de horas asignadas por auxiliares en los últimos 7 días
         </p>
     </figure>

     <!-- Scripts de Highcharts -->
     <script src="https://code.highcharts.com/highcharts.js"></script>
     <script src="https://code.highcharts.com/modules/data.js"></script>
     <script src="https://code.highcharts.com/modules/series-label.js"></script>
     <script src="https://code.highcharts.com/modules/exporting.js"></script>
     <script src="https://code.highcharts.com/modules/export-data.js"></script>
     <script src="https://code.highcharts.com/modules/accessibility.js"></script>

     


 </div>
</asp:Content>
