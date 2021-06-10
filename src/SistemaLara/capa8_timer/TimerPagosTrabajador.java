/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa8_timer;

import SistemaLara.capa1_presentacion.FormMenu;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa2_aplicacion.GestionarPagoPersonalServicio;
import SistemaLara.capa2_aplicacion.GestionarPersonalServicio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Personal;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




/**
 *
 * @author FiveCod Software
 */
public class TimerPagosTrabajador {


    private static GestionarContratoServicio gestionarContratoServicio;
    private static GestionarPagoPersonalServicio gestionarPagoPersonalServicio;
    public static List<Contrato> listaPersonalContratosActivos = new ArrayList<Contrato>();
    public static List<Contrato> listaContratoQueTerminanHoy = new ArrayList<Contrato>();

    public static List<Contrato> listaContratatoPagosHoy = new ArrayList<Contrato>();
    public static List<Contrato> listaPersonalAuxiliarPagosContrato = new ArrayList<Contrato>();

    public TimerPagosTrabajador() {
        gestionarPagoPersonalServicio = new GestionarPagoPersonalServicio();
        listaContratatoPagosHoy.clear();
        listaPersonalContratosActivos.clear();
        listaPersonalAuxiliarPagosContrato.clear();
        gestionarContratoServicio = new GestionarContratoServicio();
        obtenerPersonalBD();

    }



    public void start() {
        Timer timer;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                llenarCamposFormulario();

            }
        };
        // Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms

        timer.schedule(task, 10, 2000000);
    }

    private static void llenarCamposFormulario() {
        verificarPagoPersonal();
//        estaEnTiempoContrato();
        if (listaContratatoPagosHoy.size() > 0) {
//                    IniciarDesktop a = new IniciarDesktop();
//                    a.llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);
            int suma = 0;

            FormMenu.lblPagoContrato.setText("" + listaContratatoPagosHoy.size());
        } else {
            FormMenu.lblPagoContrato.setText("0");
        }
    }

    private static void verificarPagoPersonal() {
        for (Contrato contrato : listaPersonalContratosActivos) {
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            int suma = verificarSiEstaPagadoContrato(sqlDate, contrato.getCodigo());
            if (suma == 0) {
                contrato.obtenerFechasPagos();
                boolean estado = contrato.estaDePagoPersonal();
                if (estado) {
                   
                    listaContratatoPagosHoy.add(contrato);
                } else {
                    listaPersonalAuxiliarPagosContrato.add(contrato);
                }
            }

        }

    }

    private static int verificarSiEstaPagadoContrato(Date fecha, int contratoId) {
        int suma = 0;
        try {
            suma = gestionarPagoPersonalServicio.verificarSiEstaPagadoContrato(fecha, contratoId);
        } catch (Exception e) {
        }
        return suma;
    }

    private static void estaEnTiempoContrato() {
        try {
            for (Contrato contrato : listaPersonalContratosActivos) {
                boolean estado = contrato.estaEnTiempoContrato();
                if (estado == false) {
                    listaContratoQueTerminanHoy.add(contrato);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private static void obtenerPersonalBD() {
        try {

            listaPersonalContratosActivos = gestionarContratoServicio.ObtenerContratoPersonalLista();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void iniciarOtraVes() {
        listaContratoQueTerminanHoy.clear();
        listaContratatoPagosHoy.clear();
        listaPersonalContratosActivos.clear();
        listaPersonalAuxiliarPagosContrato.clear();
        obtenerPersonalBD();
        llenarCamposFormulario();

    }

}
