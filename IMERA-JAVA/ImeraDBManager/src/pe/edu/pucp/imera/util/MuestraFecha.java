/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MuestraFecha {
    public static String toString(Date date) {
        String fecha = "NULL";
        if (date != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            fecha = formateador.format(date);
        }
        return fecha;
    }
}
