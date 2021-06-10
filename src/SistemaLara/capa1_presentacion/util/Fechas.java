/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class Fechas {

    public static Date sumarDiasAFecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static String obtenerFechaActual() {
        String fechas = null;
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(formatoFecha);
        try {

            java.util.Date fecha = new java.util.Date();
//        SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);

            fechas = format.format(fecha);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return fechas;
    }

    public static String formatearFechas(java.util.Date fecha) {
        String formatoFecha = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(formatoFecha);
        String fechaFormateada = "";
        try {
            fechaFormateada = format.format(fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return fechaFormateada;
    }

    public static String formatearFechaEspecificada(java.util.Date fecha, String formato) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        String fechaFormateada = "";
        try {
            fechaFormateada = format.format(fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return fechaFormateada;
    }

}
