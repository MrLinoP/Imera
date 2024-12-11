﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="HomeAuxiliar.aspx.cs" Inherits="ImeraWA.HomeAuxiliar" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="Content/bootstrap.css" rel="stylesheet" />
    <link href="Content/Fonts/css/all.css" rel="stylesheet" />
    <link href="Content/site.css" rel="stylesheet" />
    <link href="Content/custom.css" rel="stylesheet" />
    <link href="Content/EstilosEstudiante.css" rel="stylesheet" />
    <link href="Content/EstilosAuxiliar.css" rel="stylesheet" />
    <script src="Scripts/bootstrap.js"></script>
    <script src="Scripts/bootstrap.bundle.js"></script>
    <script src="Scripts/jquery-3.7.1.js"></script>
    <style>
        .cursor-yellow, .cursor-yellow button, .cursor-yellow button:hover  {
            cursor: url('Images/bucket-yellow.png'), auto;
        }

        .cursor-white, .cursor-white button, .cursor-white button:hover  {
            cursor: url('Images/bucket-white.png'), auto;
        }

        .cursor-blue, .cursor-blue button, .cursor-blue button:hover  {
            cursor: url('Images/bucket-blue.png'), auto;
        }

        .custom-table {
            background-color: #d3d3d3; /* Fondo gris */
            color: #000000; /* Texto negro para mejor contraste */
            margin: 0;
            text-align: center;
        }
        .custom-table .custom-header, .custom-table th {
            background-color: #c0c0c0; /* Fondo gris para el encabezado */
            color: #000000; /* Texto negro para mejor contraste */
            margin: 0;
            font-size: 20px;
            font-weight: bold;
        }
        .custom-table .custom-cell {
            background-color: #dbdbdb; /* Fondo gris para filas del cuerpo */
            color: #000000; /* Texto negro para mejor contraste */
            margin: 0;
            padding: 0;
        }
        .custom-table .custom-cell:nth-child(even) {
            background-color: #dbdbdb; /* Gris claro para filas pares */
            margin: 0;
        }
    </style>
    <title>Aulas libres Auxiliar</title>
</head>
<body runat="server" class="<%= hfCursorClass.Value %>"  style="background-color:#e5e5e5;">
    <form id="form1" runat="server">
        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
        <asp:HiddenField ID="hfCursorClass" runat="server" />
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #427cff;height: 80px;">
            <a class="navbar-brand d-flex align-items-center mr-4" href="#">
                <img src="Images/logo.png" width="60" height="60" class="d-inline-block align-top" alt="" style="margin-left:10px"/>
                <h1 class="mb-0 ms-6" style="font-size: 45px; margin-left:10px; margin-right:50px;"><strong>IMERA</strong></h1>
            </a>
            <div class="d-flex align-items-center">
                <asp:Label ID="pabellonAuxiliar" runat="server" Text="" CssClass="col-sm-0 col-form-label" style="font-size:30px; color:white; font-weight: bold; margin-right:40px" ></asp:Label>
            </div>
            <div class="d-flex align-items-center">
                <asp:Label ID="piso" runat="server" Text="Seleccione un piso:" CssClass="col-sm-0 col-form-label" style="font-size:18px; color:white; white-space: nowrap;" ></asp:Label>
            </div>
            <div class="d-flex align-items-center" >
                <asp:DropDownList ID="ddlPisos" runat="server" CssClass="form-control ml-1" style="margin-left:10px" AutoPostBack="true" OnSelectedIndexChanged="DdlPiso_SelectedIndexChanged"> 
                </asp:DropDownList>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 bg-light" style="height:calc(100vh - 80px); overflow-y:auto;" >
                    <div class="p-3">
                        <h5>Bienvenido</h5>
                        <div class="col-sm-6 d-flex">
                            <asp:Label ID="codigoAuxiliar" runat="server" Text="" CssClass="col-sm-0 col-form-label " style="font-size:18px;" ></asp:Label>
                        </div>
                        <div class="col-sm-6 d-flex">
                            <asp:Label ID="nombreAuxiliar" runat="server" Text="" CssClass="col-sm-0 col-form-label " style="font-size:18px; white-space: nowrap;" ></asp:Label>
                        </div>
                        <div class="col-sm-6 d-flex">
                            <asp:Label ID="correoAuxiliar" runat="server" Text="" CssClass="col-sm-0 col-form-label " style="font-size:18px;" ></asp:Label>
                        </div>  
                            
                        
                        
                        <hr />
                        <h6>Leyenda:</h6>
                        <p><span class="color-box" style="background-color: yellow;"></span> Aulas libre (Reservada)</p>
                        <p><span class="color-box" style="background-color: lightblue;"></span> Aula de clase</p>
                        <p><span class="color-box" style="background-color: white; border: 1px solid #ccc;"></span> Aula disponible</p>
                        <p><span class="color-box" style="background-color: blue;"></span> Aula reservada (Profesor)</p>
                        <p><span class="color-box" style="background-color: gold;"></span> Aula libre semestral</p>
                        <hr />
                        <h6>Herramientas:</h6>
                        <asp:UpdatePanel ID="UpdatePanel1" runat="server">
                            <ContentTemplate>
                                <asp:Button ID="btnFree" runat="server" CssClass="btn btn-warning btn-paint" Text="Aula libre" style="background-color:yellow;" OnClick="SetPaintBucket" CommandArgument="yellow" />
                                <asp:Button ID="btnAvailable" runat="server" CssClass="btn btn-light btn-paint" Text="Aula disponible" OnClick="SetPaintBucket" CommandArgument="white" />
                                <asp:Button ID="btnTeacher" runat="server" CssClass="btn btn-paint" style="color:white; background-color:blue;" Text="Reservado profesor" OnClick="SetPaintBucket" CommandArgument="blue" />
                            </ContentTemplate>
                        </asp:UpdatePanel>
                        <hr />
                        <div class="logout-button  p-2 rounded text-center justify-content-center d-flex align-items-center" style="bottom: 10px; left: 10px; width: 260px;background-color:red;position: fixed; ">
                            <asp:LinkButton ID="LinkButtonLogOut" runat="server" style="color:white;background-color:red;" CssClass="btn w-100" OnClick="btnCerrarSesion"> 
                                <i class="fa-solid fa-sign-out-alt" style="margin-right: 10px;"></i>Cerrar Sesión 
                            </asp:LinkButton> 
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <asp:UpdatePanel ID="UpdatePanel2" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <asp:Table ID="tblSchedule" runat="server" CssClass="table table-bordered schedule-table"> 
                                <asp:TableHeaderRow ID="TableHeaderRow1" runat="server"></asp:TableHeaderRow> 
                            </asp:Table>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </form>
</body>
</html>