<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="ReporteMensual.aspx.cs" Inherits="ImeraWA.ReporteMensual" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Reporte Mensual
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <h2 class="ms-5 mt-4">Reporte Mensual de Aulas por Pabellón</h2>

    <asp:GridView ID="gvResultados" runat="server" CssClass="table" AutoGenerateColumns="false">
        <Columns>
            <asp:BoundField DataField="Pabellon" HeaderText="Pabellón" />
            <asp:BoundField DataField="HorasLibresAsignadas" HeaderText="Horas Libres Asignadas" />
            <asp:BoundField DataField="HorasLibresDesasignadas" HeaderText="Horas Libres Desasignadas" />
            <asp:BoundField DataField="Ratio" HeaderText="Ratio Asignadas/Revocadas" />
            <asp:BoundField DataField="HorasDisponible" HeaderText="Horas cambiadas a disponible" />
        </Columns>
    </asp:GridView>

    <!-- Contenedor para el gráfico de Highcharts -->
    <div id="chartContainer" style="width:100%; height:400px; margin-top: 20px;"></div>

    <!-- Importar la librería de Highcharts -->
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript">
        // JavaScript para inicializar el gráfico de Highcharts
        document.addEventListener("DOMContentLoaded", function () {
            Highcharts.chart('chartContainer', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Reporte de aulas libres del mes por Pabellón'
                },
                xAxis: {
                    categories: <%= GetPabellonesJson() %>,
                    title: {
                        text: 'Pabellones'
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Horas'
                    }
                },
                series: [{
                    name: 'Horas Libres Asignadas',
                    data: <%= GetHorasAsignadasJson() %>
                }, {
                    name: 'Horas Libres Desasignadas',
                    data: <%= GetHorasRevocadasJson() %>
                }, {
                    name: 'Horas cambiadas a dispoinble',
                    data: <%= GetHorasLibresJson() %>
                }]
            });
        });
    </script>
</asp:Content>
