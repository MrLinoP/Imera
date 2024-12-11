using ImeraBaseBO.ImeraServicio;
using ImeraUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ImeraWA
{
    public partial class SubirCSV : System.Web.UI.Page
    {
        private HorarioBO horarioBO;
        private PabellonBO pabellonBO;
        private PisoBO pisoBO;
        private AulaBO aulaBO;
        public SubirCSV()
        {
            this.pabellonBO = new PabellonBO();
            this.pisoBO = new PisoBO();
            this.aulaBO = new AulaBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if ((persona)Session["IntendenteSesion"] == null)
                Response.Redirect("Login.aspx");
            horarioBO = new HorarioBO();
        }

        protected void btnUpload_Click(object sender, EventArgs e)
        {
            if (fileUpload.HasFile)
            {
                try
                {
                    string filename = Path.GetFileName(fileUpload.FileName);
                    string savePath = Server.MapPath("~/Uploads/") + filename;
                    fileUpload.SaveAs(savePath);
                    lblFileName.Text = filename;

                    ProcesarArchivo(savePath);
                }
                catch (Exception ex)
                {
                    MostrarMensaje("Error al subir el archivo al servidor: " + ex.Message, "danger");
                }
            }
            else
            {
                MostrarMensaje("Por favor, seleccione un archivo para subir.", "warning");
            }
        }

        private void ProcesarArchivo(string path)
        {
            using (StreamReader reader = new StreamReader(path))
            {
                string line = reader.ReadLine();
                int resultado = -1;

                switch (line)
                {
                    case "AULAS_DE_CLASE":
                        resultado = modificaHorariosClase(reader);
                        break;
                    case "INSERTAR_PABELLON":
                        resultado = insertaPabellon(reader);
                        break;
                    case "INSERTAR_PISO":
                        resultado = insertaPiso(reader);
                        break;
                    case "INSERTAR_AULA":
                        resultado = insertaAula(reader);
                        break;
                    default:
                        MostrarMensaje("Tipo de archivo no reconocido.", "warning");
                        return;
                }

                if (resultado == -1) return;

                MostrarMensaje("Archivo subido exitosamente al servidor!", "success");
            }
        }

        private void MostrarMensaje(string mensaje, string tipo)
        {
            pnlMessage.CssClass = "card text-white bg-" + tipo;
            lblMessage.Text = mensaje;
            pnlMessage.Visible = true;
            pnlFileInfo.Visible = true;
        }
        protected int modificaHorariosClase(StreamReader reader)
        {
            (BindingList<string> idPabellones, BindingList<int?> idPisos, BindingList<int?> idAulas,
                BindingList<int?> idHoras,
                BindingList<string> fechas) result = CargarHorariosDesdeCSV(reader);
            BindingList<string> pabellones = result.idPabellones;
            BindingList<int?> pisos = result.idPisos;
            BindingList<int?> aulas = result.idAulas;
            BindingList<int?> horas = result.idHoras;
            BindingList<string> myFechas = result.fechas;
            try
            {
                horarioBO.modificarHorarios(pabellones.ToArray(), pisos.ToArray(), aulas.ToArray(), horas.ToArray(), myFechas.ToArray());
            }
            catch (Exception ex)
            {
                pnlMessage.CssClass = "card text-white bg-danger";
                lblMessage.Text = "Error al modificar los horarios en la base de datos: " + ex.Message;
                pnlMessage.Visible = true;
                return -1; // Evitar que se muestre el mensaje de éxito si ocurre un error.
            }
            return 0;
        }

        protected (BindingList<string> idPabellones, BindingList<int?> idPisos
            , BindingList<int?> idAulas, BindingList<int?> idHoras, BindingList<string>) CargarHorariosDesdeCSV(StreamReader reader)
        {
            BindingList<string> fechas = new BindingList<string>();
            BindingList<string> idPabellones = new BindingList<string>();
            BindingList<int?> idPisos = new BindingList<int?>();
            BindingList<int?> idAulas = new BindingList<int?>();
            BindingList<int?> idHoras = new BindingList<int?>();
            while (!reader.EndOfStream)
            {
                string linea = reader.ReadLine();
                string[] valores = linea.Split(',');

                // Asume que el archivo Horarios.csv tiene el formato adecuado
                string aula = valores[0];
                string idPabellon = aula.Substring(0, 1);
                int idPiso = Int32.Parse(aula.Substring(1, 1));
                int idAula = Int32.Parse(aula.Substring(2, 2));
                int horaInicio = Int32.Parse(valores[1]);
                int horaFin = Int32.Parse(valores[2]);
                int idHora = horaFin - 7;
                string _idDia = valores[3];

                idPabellones.Add(idPabellon);
                idPisos.Add(idPiso);
                idAulas.Add(idAula);
                idHoras.Add(idHora);
                fechas.Add(_idDia);
            }
            return (idPabellones, idPisos, idAulas, idHoras, fechas);
        }

        protected int insertaPabellon(StreamReader reader)
        {
            string idPabellon = null;
            string nombPabellon = null;
            int numPisosPabellon = 0;
            BindingList<string> aulas = new BindingList<string>();
            BindingList<int?> aforos = new BindingList<int?>();
            BindingList<int?> enchufes = new BindingList<int?>();
            try
            {
                CargarPabellonesDesdeCSV(reader, ref idPabellon, ref nombPabellon, ref numPisosPabellon, aulas, aforos, enchufes);

                pabellonBO.insertar(idPabellon, nombPabellon, numPisosPabellon, aulas.ToArray(), aforos.ToArray(), enchufes.ToArray());

                pnlMessage.CssClass = "card text-white bg-success";
                lblMessage.Text = "Pabellones insertados exitosamente.";
                pnlMessage.Visible = true;
                return 0;
            }
            catch (Exception ex)
            {
                pnlMessage.CssClass = "card text-white bg-danger";
                lblMessage.Text = "Error al insertar los pabellones en la base de datos: " + ex.Message;
                pnlMessage.Visible = true;
                return -1;
            }
        }
        protected void CargarPabellonesDesdeCSV(StreamReader lector, ref string idPabellon, ref string nombPabellon, ref int numPisosPabellon,
           BindingList<string> aulas, BindingList<int?> aforos, BindingList<int?> enchufes)
        {
            string linea = null;
            linea = lector.ReadLine();

            // Leer datos del pabellón
            string[] datosPabellon = linea.Split(',');
            if (datosPabellon.Length == 3)
            {
                idPabellon = datosPabellon[0];
                nombPabellon = datosPabellon[1];
                numPisosPabellon = int.Parse(datosPabellon[2]);
            }

            // Leer aulas asociadas al pabellón

            while ((linea = lector.ReadLine()) != null && !string.IsNullOrWhiteSpace(linea))
            {
                string[] datosAula = linea.Split(',');
                if (datosAula.Length == 3)
                {
                    aulas.Add(datosAula[0]);
                    aforos.Add(int.Parse(datosAula[1]));
                    enchufes.Add(int.Parse(datosAula[2]));
                }
            }
        }
        protected int insertaPiso(StreamReader reader)
        {
            string idPabellon = null;
            int idPiso = 0;
            BindingList<string> aulas = new BindingList<string>();
            BindingList<int?> aforos = new BindingList<int?>();
            BindingList<int?> enchufes = new BindingList<int?>();
            //int res = 0;
            try
            {
                CargarPisosDesdeCSV(reader, ref idPabellon, ref idPiso, aulas, aforos, enchufes);

                pisoBO.insertar(idPabellon, idPiso, aulas.ToArray(), aforos.ToArray(), enchufes.ToArray());

                pnlMessage.CssClass = "card text-white bg-success";
                lblMessage.Text = "Pisos insertados exitosamente.";
                pnlMessage.Visible = true;
                return 0;
            }
            catch (Exception ex)
            {
                pnlMessage.CssClass = "card text-white bg-danger";
                lblMessage.Text = "Error al insertar los pisos en la base de datos: " + ex.Message;
                pnlMessage.Visible = true;
                return -1;
            }
        }
        protected void CargarPisosDesdeCSV(StreamReader lector, ref string idPabellon, ref int idPiso, BindingList<string> aulas, BindingList<int?> aforos,
            BindingList<int?> enchufes)
        {
            string linea = null;
            linea = lector.ReadLine();
            string[] datosPabellon = linea.Split(',');
            if (datosPabellon.Length == 2)
            {
                idPabellon = datosPabellon[0];
                idPiso = int.Parse(datosPabellon[1]);
            }
            // Leer aulas
            while ((linea = lector.ReadLine()) != null && !string.IsNullOrWhiteSpace(linea))
            {
                string[] datosAula = linea.Split(',');
                if (datosAula.Length == 3)
                {
                    aulas.Add(datosAula[0]);
                    aforos.Add(int.Parse(datosAula[1]));
                    enchufes.Add(int.Parse(datosAula[2]));
                }
            }
        }
        protected int insertaAula(StreamReader reader)
        {
            try
            {
                BindingList<string> pabellones = new BindingList<string>();
                BindingList<int?> pisos = new BindingList<int?>();
                BindingList<int?> idsAula = new BindingList<int?>();
                BindingList<int?> aforos = new BindingList<int?>();
                BindingList<int?> enchufes = new BindingList<int?>();

                CargarAulasDesdeCSV(reader, pabellones, pisos, idsAula, aforos, enchufes);

                aulaBO.insertar(idsAula.ToArray(), pisos.ToArray(), pabellones.ToArray(), aforos.ToArray(), enchufes.ToArray());

                pnlMessage.CssClass = "card text-white bg-success";
                lblMessage.Text = "Aulas insertados exitosamente.";
                pnlMessage.Visible = true;
                return 0;
            }
            catch (Exception ex)
            {
                pnlMessage.CssClass = "card text-white bg-danger";
                lblMessage.Text = "Error al insertar los aulas en la base de datos: " + ex.Message;
                pnlMessage.Visible = true;
                return -1;
            }
        }

        public static void CargarAulasDesdeCSV(StreamReader reader,
            BindingList<string> pabellones,
            BindingList<int?> pisos,
            BindingList<int?> idsAula,
            BindingList<int?> aforos,
            BindingList<int?> enchufes)
        {
            string linea = string.Empty;
            while ((linea = reader.ReadLine()) != null)
            {
                string[] datos = linea.Split(',');
                if (datos.Length != 3) continue; // Asegura que la línea contiene todos los datos

                // Extraer datos básicos
                string codigoAula = datos[0].Trim();
                int aforo = int.Parse(datos[1].Trim());
                int cantidadEnchufes = int.Parse(datos[2].Trim());

                // Descomponer el código del aula
                string pabellon = codigoAula.Substring(0, 1); // Primer carácter como pabellón
                int piso = int.Parse(codigoAula.Substring(1, 1)); // Segundo carácter como piso
                int idAula = int.Parse(codigoAula.Substring(2)); // Los 2 dígitos restantes como ID de aula

                // Agregar a las listas
                pabellones.Add(pabellon);
                pisos.Add(piso);
                idsAula.Add(idAula);
                aforos.Add(aforo);
                enchufes.Add(cantidadEnchufes);
            }
        }
    }
}