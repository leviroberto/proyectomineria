/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa8_timer;

import SistemaLara.capa1_presentacion.FormMenu;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import static SistemaLara.capa1_presentacion.util.DesktopNotify.mostraMernsajeSinTiempo;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa2_aplicacion.GestionarTareaDiariaServicio;

import SistemaLara.capa3_dominio.Contrato;

import SistemaLara.capa3_dominio.TareaDiaria;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import static SistemaLara.capa1_presentacion.util.DesktopNotify.mostraMernsajeSinTiempo;
import static SistemaLara.capa1_presentacion.util.DesktopNotify.mostraMernsajeSinTiempo;
import static SistemaLara.capa1_presentacion.util.DesktopNotify.mostraMernsajeSinTiempo;

/**
 *
 * @author FiveCod Software
 */
public class TimerTareasDiarias {

    private static GestionarTareaDiariaServicio gestionarTareasDiariasServicio;
    public static List<TareaDiaria> listaTareasDiarias = new ArrayList<TareaDiaria>();
    public static List<TareaDiaria> listaTareasDiariasAuxiliarHoy = new ArrayList<TareaDiaria>();
    public static List<TareaDiaria> listaTareasDiariasAuxiliarPasados = new ArrayList<TareaDiaria>();

    public TimerTareasDiarias() {
        listaTareasDiariasAuxiliarHoy.clear();
        listaTareasDiariasAuxiliarHoy.clear();
        listaTareasDiariasAuxiliarPasados.clear();
        gestionarTareasDiariasServicio = new GestionarTareaDiariaServicio();
        obtenerTareasDiariasBD();
    }

    public void start() {
        Timer timer;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                verificarTareasDiariasCumpleaños();
                if (listaTareasDiariasAuxiliarHoy.size() > 0) {
//                    IniciarDesktop a = new IniciarDesktop();
//                    a.llenarDescktopTareasDiarias(listaTareasDiariasAuxiliarHoy);
                    int suma = 0;
                    for (TareaDiaria ta : listaTareasDiariasAuxiliarHoy) {
                        if (ta.getEstadoTarea().equals("Sin Click")) {
                            suma = suma + 1;
                        }
                    }
                    FormMenu.lblNotificacionTareas.setText("" + suma);

                } else {
                    FormMenu.lblNotificacionTareas.setText("0");
                }
            }
        };
        // Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms

        timer.schedule(task, 10, 2000000);
    }

    private static void verificarTareasDiariasCumpleaños() {

        try {
            for (TareaDiaria tareaDiaria : listaTareasDiarias) {
                if (tareaDiaria.estaTareaActivaHoy()) {
                    listaTareasDiariasAuxiliarHoy.add(tareaDiaria);
                } else {
                    listaTareasDiariasAuxiliarPasados.add(tareaDiaria);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private static void obtenerTareasDiariasBD() {
        try {
            listaTareasDiarias = gestionarTareasDiariasServicio.ObtenerListaTareasPorHacer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void iniciarOtraVes() {
        listaTareasDiarias.clear();
        listaTareasDiariasAuxiliarHoy.clear();
        listaTareasDiariasAuxiliarPasados.clear();
        obtenerTareasDiariasBD();
        verificarTareasDiariasCumpleaños();

    }

    public static void eliminarListas() {
        listaTareasDiarias.clear();
        listaTareasDiariasAuxiliarHoy.clear();
        listaTareasDiariasAuxiliarPasados.clear();
    }

}
