/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa8_timer;

import SistemaLara.capa1_presentacion.FormMenu;

import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa2_aplicacion.GestionarPersonalServicio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Personal;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class TimerCumpleaños {

    private static GestionarPersonalServicio gestionarPersonalServicio;
    public static List<Personal> listaPersonalCumpleaños = new ArrayList<Personal>();
    public static List<Personal> listaPersonalAuxiliarHoy = new ArrayList<Personal>();
    public static List<Personal> listaPersonalAuxiliarPasados = new ArrayList<Personal>();

    public TimerCumpleaños() {
        listaPersonalAuxiliarHoy.clear();
        listaPersonalCumpleaños.clear();
        listaPersonalAuxiliarPasados.clear();
        gestionarPersonalServicio = new GestionarPersonalServicio();
        obtenerPersonalBD();

    }

    

    public void start() {
        Timer timer;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                verificarPersonalCumpleaños();
                if (listaPersonalAuxiliarHoy.size() > 0) {
//                    IniciarDesktop a = new IniciarDesktop();
////                    a.llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);
//                    int suma = 0;
//                    for (Personal ta : listaPersonalAuxiliarHoy) {
//                        if (ta.getEstadoPersonalNotificacion().equals("Sin Click")) {
//                            suma = suma + 1;
//                        }
//                    }
                    FormMenu.lblCumpleaños.setText("" + listaPersonalAuxiliarHoy.size());
                } else {
                    FormMenu.lblCumpleaños.setText("0");
                }
            }
        };
        // Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms

        timer.schedule(task, 10, 2000000);
    }

    private static void verificarPersonalCumpleaños() {

        try {
            for (Personal personal : listaPersonalCumpleaños) {
                boolean estado = personal.estaDeCumpleaños();
                if (estado) {

                    listaPersonalAuxiliarHoy.add(personal);
                } else {
                    listaPersonalAuxiliarPasados.add(personal);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private static void obtenerPersonalBD() {
        try {

            listaPersonalCumpleaños = gestionarPersonalServicio.ObtenerPersonalLista();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void iniciarOtraVes() {
        listaPersonalAuxiliarHoy.clear();
        listaPersonalCumpleaños.clear();
        listaPersonalAuxiliarPasados.clear();
        obtenerPersonalBD();
        verificarPersonalCumpleaños();

    }

}
