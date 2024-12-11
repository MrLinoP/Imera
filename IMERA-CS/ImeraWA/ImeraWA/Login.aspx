<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="ImeraWA.Login"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Imera - Login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="Content/MasEstilos.css" rel="stylesheet" />
</head>
<body>
    <form id="form1" runat="server">
        <div class="container-fluid">
            <div class="row no-gutters">
                <div class="col-md-6 left-half">
                    <img src="Images/cia.png" alt="PUCP" class="full-height-img" />
                </div>
                <div class="col-md-6 right-half">
                    <div class="login-form">
                        <div class="text-center mb-4">
                            <img src="Images/pucp.png" alt="PUCP" class="img-fluid" />
                            
                        </div>
                        <div class="form-group">
                            <label for="email">Ingrese su correo electrónico o código</label>
                            <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control" placeholder="Correo electrónico o código"></asp:TextBox>
                        </div>
                        <div class="form-group">
                            <label for="password">Ingrese su contraseña</label>
                            <asp:TextBox ID="txtContrasenha" runat="server" CssClass="form-control" TextMode="Password" placeholder="Contraseña"></asp:TextBox>
                        </div>
                        <div class="d-flex justify-content-center mb-2">
                            <asp:Label ID="wrongPwd" runat="server" Text="Usuario o contraseña incorrecta" CssClass="error-label" style="color:red" Visible="false"></asp:Label>
                        </div>
                        <asp:Button ID="loginButton" runat="server" CssClass="btn btn-primary btn-block" Text="Siguiente" OnClick="btnIngresar"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
    
</body>
</html>
