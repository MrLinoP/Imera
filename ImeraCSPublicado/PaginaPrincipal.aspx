﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PaginaPrincipal.aspx.cs" Inherits="ImeraWA.PaginaPrincipal" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <link href="Content/Fonts/css/all.css" rel="stylesheet" />
    <link href="Content/site.css" rel="stylesheet" />
    <link href="Content/custom.css" rel="stylesheet" />
    <link href="Content/EstilosEstudiante.css" rel="stylesheet" />
    <script src="Scripts/bootstrap.js"></script>
    <script src="Scripts/bootstrap.bundle.js"></script>
    <script src="Scripts/jquery-3.7.1.js"></script>
    <title>
        Aulas Libres
    </title>
    <style>
        .selected-button {
            background-color: #004085;
            border-color: #004085;
        }

        .btn-login {
            background-color: white;
            color: black;
            border: 1px solid black;
        }

        .btn-login:hover {
            background-color: #e6e6e6; /* Un gris claro para el efecto hover */
            color: black;
        }

        .order-text {
            font-weight: bold;
            font-size: 16px; /* Puedes ajustar este tamaño según tus preferencias */
            margin-right: 10px;
        }

        .sort-button {
            margin-right: 10px; /* Puedes ajustar este margen según tus preferencias */
        }
        .ms-2 {
            margin-left: 0.5rem; /* Espaciado a la izquierda */
            color: #007bff; /* Color del texto (puedes cambiarlo) */
            font-weight: bold; /* Negrita */
        }
        .ms-2:hover {
            text-decoration: underline; /* Subrayar al pasar el cursor */
        }

    </style>
</head>
<body>
    <form id="form1" runat="server">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #427cff;">
       <a href="PaginaPrincipal.aspx" class="navbar-brand d-flex align-items-center">
            <img src="Images/logo.png" width="100" class="p-3" />
            <h1 class="mb-0 ms-6" style="font-size: 40px;"><strong>IMERA</strong></h1>
        </a>
        <div class="collapse navbar-collapse justify-content-end" style="padding-right: 20px;">
            <button class="btn btn-login my-2 my-sm-0"" type="button" onclick="location.href='Login.aspx'">Iniciar Sesión</button>
        </div>
    </nav>
    <div class="main-content" style="width: 100%">
        <div class="container mt-4">
            <div class="row mb-4">
                <div class="col text-center">
                    <span class="order-text">Ordenar por:</span>
                        <asp:LinkButton ID="OrdenarHorario" runat="server" CssClass="btn btn-primary text-white sort-button" OnClick="SortBy_Click" CommandArgument="Horario">
                            <i class="fa fa-clock" style="margin-right: 5px;"></i>Horario
                        </asp:LinkButton>

                        <asp:LinkButton ID="OrdenarAforo" runat="server" CssClass="btn btn-primary text-white sort-button" OnClick="SortBy_Click" CommandArgument="Aforo">
                            <i class="fa fa-users" style="margin-right: 5px;"></i>Aforo
                        </asp:LinkButton>

                        <asp:LinkButton ID="OrdenarEnchufes" runat="server" CssClass="btn btn-primary text-white sort-button" OnClick="SortBy_Click" CommandArgument="Enchufes">
                            <i class="fa fa-plug" style="margin-right: 5px;"></i>Enchufes
                        </asp:LinkButton>

                        <asp:LinkButton ID="CambiaOrdenamiento" runat="server" CssClass="btn btn-secondary" OnClick="ReverseSort_Click">
                            <i class="fa fa-sort" style="margin-right: 5px;"></i>Reordenar
                        </asp:LinkButton>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <span class="order-text">Filtre por pabellón:</span>
                    <asp:DropDownList ID="PabellonDropDownList" runat="server" AutoPostBack="true" OnSelectedIndexChanged="PabellonDropDownList_SelectedIndexChanged">
                    </asp:DropDownList>
                    <span data-bs-toggle="tooltip" title="Si no se muestra tu pabellón, no hay aulas libres disponibles para el día de hoy en dicho pabellón." class="ms-2" style="cursor: pointer;">?</span>
                </div>
            </div>
            <div class="row justify-content-center" style="margin-top:20px">
                <div class="col-md-10 col-lg-8">
                    <asp:GridView ID="AulasGridView" runat="server" CssClass="table table-bordered" AutoGenerateColumns="False">
                        <Columns>
                            <asp:BoundField DataField="Nombre" HeaderText="Nombre" />
                            <asp:BoundField DataField="Horario" HeaderText="Horario" />
                            <asp:BoundField DataField="Aforo" HeaderText="Aforo" />
                            <asp:BoundField DataField="Enchufes" HeaderText="Enchufes" />
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
        <div class="row justify-content-center" style="width: 100%">
            <div class="col-auto text-center">
                <img src="Images/normas.jpg" alt="Normas del Aula" class="img-fluid" style="max-width: 100%; height: auto; width: 50%;" />
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            // Inicializar todos los tooltips
            var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
            tooltipTriggerList.forEach(function (tooltipTriggerEl) {
                new bootstrap.Tooltip(tooltipTriggerEl);
            });
        });
    </script>
</form>
</body>
</html>