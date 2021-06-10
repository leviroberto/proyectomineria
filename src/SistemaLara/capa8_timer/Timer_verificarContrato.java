/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa8_timer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author FiveCod Software
 */
public class Timer_verificarContrato {

//    GestionarContratoServicio gestionarContratoServicio;
//    private List<Contrato> listaContrato = new ArrayList<Contrato>();
//
//    public Timer_verificarContrato() {
//        gestionarContratoServicio = new GestionarContratoServicio();
//    }
//
//    private void obtenerDatosBaseDatos() throws Exception {
//        Calendar c1 = Calendar.getInstance();
//        int anio = c1.get(Calendar.YEAR);
//        listaContrato = gestionarContratoServicio.mostrarContrato(Contrato.ESTADO_ACTIVO);
//    }
//
//    public void start() {
//        Timer timer;
//        timer = new Timer();
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                ArrayList<Contrato> lista = verificarProdcto();
//                if (lista.size() > 0) {
//                    for (Contrato contrato : lista) {
//                        contrato.setEstado(Contrato.ESTADO_INACTIVO);
//                        try {
//                            gestionarContratoServicio.modificarContrato(contrato);
//                            DesktopNotify.showDesktopMessage("FiveCod Software", "Ha terminado el contrato de " + contrato.getTrabajador().getNombre() + " " + contrato.getTrabajador().getApellidos(), 7);
//                        } catch (Exception ex) {
//                        }
//                    }
//                } else {
//                    FormMenuAlmacen.lblNotificacion.setText("");
//                }
//            }
//        };
//        // Empezamos dentro de 10ms y luego lanzamos la tarea cada 1000ms
//
//        timer.schedule(task, 10, 2000000);
//    }
//
//    private ArrayList<Contrato> verificarProdcto() {
//
//        ArrayList<Contrato> listaContratoAuxiliar = new ArrayList<>();
//
//        try {
//            FormMenuAlmacen.listaTrabajador.clear();
//            listaContrato.clear();
//            obtenerDatosBaseDatos();
//            for (Contrato contrato1 : listaContrato) {
//                if (!contrato1.estaContratoDisponible()) {
//                    listaContratoAuxiliar.add(contrato1);
//                    FormMenuAlmacen.listaTrabajador.add(contrato1.getTrabajador());
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return listaContratoAuxiliar;
//    }

}
