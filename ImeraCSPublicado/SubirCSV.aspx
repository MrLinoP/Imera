<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="SubirCSV.aspx.cs" Inherits="ImeraWA.SubirCSV" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server"></asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container mt-5">
        <div class="row">
            <div class="col text-center">
                <h1>Adjunte el CSV</h1>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-md-6 offset-md-3">
                <div class="form-group mb-4">
                    <asp:FileUpload ID="fileUpload" runat="server" CssClass="form-control-file" />
                </div>
                <div class="form-group mb-4">
                    <asp:Button ID="btnUpload" runat="server" Text="Subir CSV" CssClass="btn btn-primary" OnClick="btnUpload_Click" />
                </div>
                <asp:Panel ID="pnlFileInfo" runat="server" Visible="false">
                    <div class="card">
                        <div class="card-body d-flex align-items-center">
                            <i class="fa fa-file-alt fa-2x me-3 text-primary"></i>
                            <div>
                                <asp:Label ID="lblFileName" runat="server" CssClass="form-text text-muted"></asp:Label>
                            </div>
                        </div>
                    </div>
                </asp:Panel>
                <asp:Panel ID="pnlMessage" runat="server" Visible="false" class="mt-4">
                    <div class="card">
                        <div class="card-body">
                            <asp:Label ID="lblMessage" runat="server"></asp:Label>
                        </div>
                    </div>
                </asp:Panel>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col text-center">
                <h2>Deslice para visualizar el tipo de formato a subir:</h2>
                <div id="formatosCarousel" class="carousel slide" data-bs-ride="false" data-bs-interval="false">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="Images/formato_aulas.png" alt="Formato Aulas" class="d-block w-100" style="max-height: 400px; object-fit: contain;" />
                        </div>
                        <div class="carousel-item">
                            <img src="Images/formato_pabellon.png" alt="Formato Panbellones" class="d-block w-100" style="max-height: 400px; object-fit: contain;" />
                        </div>
                        <div class="carousel-item">
                            <img src="Images/formato_piso.png" alt="Formato Pisos" class="d-block w-100" style="max-height: 400px; object-fit: contain;" />
                        </div>
                        <div class="carousel-item">
                            <img src="Images/formato_aulas_clase.png" alt="Formato Aulas de Clase" class="d-block w-100" style="max-height: 400px; object-fit: contain;" />
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#formatosCarousel" data-bs-slide="prev" style="background-color: rgba(0, 0, 0, 0.5);">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Anterior</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#formatosCarousel" data-bs-slide="next" style="background-color: rgba(0, 0, 0, 0.5);">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Siguiente</span>
                    </button>
                    <div id="carouselText" class="carousel-caption" style="position: absolute; top: 10px; left: 10px; color: black; text-align: left;">
                        <h5>Formato</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
