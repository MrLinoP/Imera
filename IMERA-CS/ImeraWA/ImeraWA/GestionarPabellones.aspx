<%@ Page Title="" Language="C#" MasterPageFile="~/Imera.Master" AutoEventWireup="true" CodeBehind="GestionarPabellones.aspx.cs" Inherits="ImeraWA.GestionarPabellones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Imera - Gestionar Pabellones
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <!DOCTYPE html>
    <html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Administrar Pabellones</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Estilos personalizados */
            h2 {
                font-size: 2em !important;
                color: #1a237e !important;
                font-weight: bold !important;
            }

            th, td {
                color: #1a237e !important;
                font-weight: bold !important;
            }

            .btn-add {
                background-color: #ffd600 !important;
                color: #000 !important;
                font-weight: bold !important;
                border-radius: 20px !important;
                padding: 5% 10% !important;
            }

                .btn-add:hover {
                    background-color: #ffca28 !important;
                }
        </style>
    </head>
    <body>

        <div class="container mt-5 content-container" style="width: 90%;">
            <h2>Pabellones:</h2>
            <div class="table-responsive table-container">
                <table class="table table-striped" id="tablaPabellones">
                    <thead>
                        <tr>
                            <th>Letra</th>
                            <th>Nombre</th>
                            <th>N° pisos</th>
                            <th>N° salones</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>N</td>
                            <td>Mc. Greggor</td>
                            <td>11</td>
                            <td>60</td>
                            <td>
                                <button class="btn btn-outline-primary btn-sm" onclick="editarPabellon('N')">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button class="btn btn-outline-danger btn-sm" onclick="eliminarPabellon(this)">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <button class="btn btn-add mt-3" data-toggle="modal" data-target="#agregarModal">Agregar</button>
            </div>
        </div>

        <!-- Modal para agregar pabellón -->
        <div class="modal fade" id="agregarModal" tabindex="-1" aria-labelledby="agregarModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="width: 100%;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="agregarModalLabel">Agregar Pabellón</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="formAgregarPabellon">
                            <div class="form-group">
                                <label for="letraPabellon">Letra</label>
                                <input type="text" class="form-control" id="letraPabellon" required>
                            </div>
                            <div class="form-group">
                                <label for="nombrePabellon">Nombre</label>
                                <input type="text" class="form-control" id="nombrePabellon" required>
                            </div>
                            <div class="form-group">
                                <label for="numPisos">N° de pisos</label>
                                <input type="number" class="form-control" id="numPisos" required>
                            </div>
                            <div class="form-group">
                                <label for="numSalones">N° de salones</label>
                                <input type="number" class="form-control" id="numSalones" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" onclick="agregarFila()">Agregar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script>
            function editarPabellon(letra) {
                window.location.href = `gestion_pisos.html?pabellon=${letra}`;
            }

            function eliminarPabellon(button) {
                if (confirm("¿Está seguro de que desea eliminar este pabellón?")) {
                    // Eliminar la fila correspondiente
                    const fila = button.closest('tr');
                    fila.remove();
                }
            }

            function agregarFila() {
                // Obtener los valores del formulario en el modal
                const letra = document.getElementById("letraPabellon").value;
                const nombre = document.getElementById("nombrePabellon").value;
                const numPisos = document.getElementById("numPisos").value;
                const numSalones = document.getElementById("numSalones").value;

                // Validar que los campos no estén vacíos
                if (letra && nombre && numPisos && numSalones) {
                    // Obtener la tabla y agregar una nueva fila con los datos
                    const tabla = document.getElementById("tablaPabellones").getElementsByTagName('tbody')[0];
                    const nuevaFila = tabla.insertRow();

                    nuevaFila.innerHTML = `
                <td>${letra}</td>
                <td>${nombre}</td>
                <td>${numPisos}</td>
                <td>${numSalones}</td>
                <td>
                    <button class="btn btn-outline-primary btn-sm" onclick="editarPabellon('${letra}')">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-outline-danger btn-sm" onclick="eliminarPabellon(this)">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            `;

                    // Limpiar el formulario y cerrar el modal
                    document.getElementById("formAgregarPabellon").reset();
                    $('#agregarModal').modal('hide');
                } else {
                    alert("Por favor, complete todos los campos.");
                }
            }
        </script>

    </body>
    </html>

</asp:Content>
