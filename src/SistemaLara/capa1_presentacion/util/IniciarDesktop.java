/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import SistemaLara.capa1_presentacion.FormDatosContrato;
import SistemaLara.capa1_presentacion.FormDatosPagoPersonal;
import SistemaLara.capa1_presentacion.FormGestionarPagoPersonal;
import SistemaLara.capa1_presentacion.FormMenu;
import SistemaLara.capa1_presentacion.FormTareasDiarias;
import SistemaLara.capa2_aplicacion.GestionarPersonalServicio;
import SistemaLara.capa2_aplicacion.GestionarTareaDiariaServicio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.TareaDiaria;
import SistemaLara.capa8_timer.TimerCumpleaños;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import SistemaLara.capa8_timer.TimerTareasDiarias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mineria
 */
public class IniciarDesktop {

    private static int contador = 0;
    private static GestionarTareaDiariaServicio gestionarTareaDiariaServicio;
    private static GestionarPersonalServicio gestionarPersonalServicio;

    public IniciarDesktop() {
        gestionarTareaDiariaServicio = new GestionarTareaDiariaServicio();
        gestionarPersonalServicio = new GestionarPersonalServicio();
    }

    public void llenarDescktopTareasDiarias(List<TareaDiaria> listaTareas) {
        contador = 0;
        for (TareaDiaria tareaDiaria : listaTareas) {
            if (tareaDiaria.getEstadoTarea().equals("Sin Click")) {
                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " fecha " + tareaDiaria.getFecha(), DesktopNotify.TIP, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        FormTareasDiarias a = new FormTareasDiarias(null, true);
                        a.llenarTextoCampos(tareaDiaria);
                        a.btnAgregar.setText("Guardar");
                        a.tipo_accion = a.ACCION_MODIFICAR;
                        a.inabilitarTextos(true);
                        a.setVisible(true);
                        String numero = FormMenu.lblNotificacionTareas.getText();

                        FormMenu.lblNotificacionTareas.setText("" + (Integer.valueOf(numero) - 1));

                        tareaDiaria.setEstadoTarea("Clickeado");
                        modificarBaseDatosTareaDiaria(tareaDiaria);

                    }
                });
            } else {
                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " fecha " + tareaDiaria.getFecha(), DesktopNotify.TIPO_CLICKEADO, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        FormTareasDiarias a = new FormTareasDiarias(null, true);
                        a.llenarTextoCampos(tareaDiaria);
                        a.btnAgregar.setText("Guardar");
                        a.tipo_accion = a.ACCION_MODIFICAR;
                        a.inabilitarTextos(true);

                        a.setVisible(true);
//                        DesktopNotify.eliminarWindowsAll();
//                        TimerTareasDiarias.iniciarOtraVes();
//                        llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);

                    }
                });

            }

            contador++;
        }

        //AHOIRA RECORREMOS LA LISTA DE LAS TAREAS QUE HAN PASADO
//        contador = 0;
//        for (TareaDiaria tareaDiaria : TimerTareasDiarias.listaTareasDiariasAuxiliarPasados) {
//            if (tareaDiaria.getEstadoTarea().equals("Sin Click")) {
//                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " En la fecha " + tareaDiaria.getFecha(), DesktopNotify.TIP, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        FormTareasDiarias a = new FormTareasDiarias(null, true);
//                        a.llenarTextoCampos(tareaDiaria);
//                        a.btnAgregar.setText("Guardar");
//                        a.tipo_accion = a.ACCION_MODIFICAR;
//                        a.inabilitarTextos(true);
//                        a.setVisible(true);
//                        String numero = FormMenu.lblNotificacionTareas.getText();
//                        FormMenu.lblNotificacionTareas.setText("" + (Integer.valueOf(numero) - 1));
//                        tareaDiaria.setEstadoTarea("Clickeado");
//                        modificarBaseDatosTareaDiaria(tareaDiaria);
//                        
//                        
//                    }
//                });
//            } else {
//                DesktopNotify.showDesktopMessage("Tareas Pasadas", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " En la fecha  " + tareaDiaria.getFecha(), DesktopNotify.TIPO_CLICKEADO, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                       
//                        FormTareasDiarias a = new FormTareasDiarias(null, true);
//                        a.llenarTextoCampos(tareaDiaria);
//                        a.btnAgregar.setText("Guardar");
//                        a.tipo_accion = a.ACCION_MODIFICAR;
//                        a.inabilitarTextos(true);
//                        a.setVisible(true);
//                         DesktopNotify.eliminarWindowsAll();
//                        TimerTareasDiarias.iniciarOtraVes();
//                        llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);
//                    }
//                });
//
//            }
//        }
    }
    Contrato contratoSeleccionado = null;

    public void llenarDescktopPagosContrato(List<Contrato> listaTareas) {
        contador = 0;

        for (Contrato contrato : listaTareas) {
            contratoSeleccionado = contrato;
            DesktopNotify.showDesktopMessage("Pago Personal", "Hoy es el pago de " + contrato.getPersonal().getGenerarNombre(), DesktopNotify.TIP, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    FormDatosPagoPersonal a = new FormDatosPagoPersonal(null, true, contratoSeleccionado);
                    a.setVisible(true);
//                    int numero = Integer.parseInt(FormMenu.lblPagoContrato.getText().toString());
//                    FormMenu.lblPagoContrato.setText("" + (numero - 1));

//                    FormGestionarPagoPersonal formGestionarPagoPersonal=new FormGestionarPagoPersonal(null, true);
//                    formGestionarPagoPersonal.setVisible(true);
                }
            });

        }

//        for (Contrato contrato : TimerPagosTrabajador.listaContratoQueTerminanHoy) {
//            contratoSeleccionado = contrato;
//            DesktopNotify.showDesktopMessage("Pago Personal", "El Contrato de   " + contrato.getPersonal().getGenerarNombre()+ " esta por finalizar", DesktopNotify.TIP, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    FormDatosContrato a = new FormDatosContrato(null, true, contratoSeleccionado);
//                    a.setVisible(true);
//                    DesktopNotify.eliminarWindowsAll();
//                    TimerPagosTrabajador.iniciarOtraVes();
//                    llenarDescktopPagosContrato(TimerPagosTrabajador.listaContratatoPagosHoy);
//                }
//            });
//        }
    }

    public void sumarNumeroNotificacionDescktopTareasDiarias(List<TareaDiaria> listaTareas) {
        contador = 0;
        FormMenu.lblNotificacionTareas.setText("0");
        for (TareaDiaria tareaDiaria : listaTareas) {
            if (tareaDiaria.getEstadoTarea().equals("Sin Click")) {
                String numero = FormMenu.lblNotificacionTareas.getText();
                FormMenu.lblNotificacionTareas.setText("" + (Integer.valueOf(numero) + 1));
                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " fecha " + tareaDiaria.getFecha(), DesktopNotify.TIP, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        FormTareasDiarias a = new FormTareasDiarias(null, true);
                        a.llenarTextoCampos(tareaDiaria);
                        a.btnAgregar.setText("Guardar");
                        a.tipo_accion = a.ACCION_MODIFICAR;
                        a.inabilitarTextos(true);
                        a.setVisible(true);
                        String numero = FormMenu.lblNotificacionTareas.getText();
                        FormMenu.lblNotificacionTareas.setText("" + (Integer.valueOf(numero) - 1));
                        tareaDiaria.setEstadoTarea("Clickeado");
                        modificarBaseDatosTareaDiaria(tareaDiaria);

                    }
                });
            } else {
                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " fecha " + tareaDiaria.getFecha(), DesktopNotify.TIPO_CLICKEADO, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        FormTareasDiarias a = new FormTareasDiarias(null, true);
                        a.llenarTextoCampos(tareaDiaria);
                        a.btnAgregar.setText("Guardar");
                        a.tipo_accion = a.ACCION_MODIFICAR;
                        a.inabilitarTextos(true);

                        a.setVisible(true);
//                        DesktopNotify.eliminarWindowsAll();
//                        TimerTareasDiarias.iniciarOtraVes();
//                        llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);

                    }
                });

            }

            contador++;
        }

        //AHOIRA RECORREMOS LA LISTA DE LAS TAREAS QUE HAN PASADO
//        contador = 0;
//        for (TareaDiaria tareaDiaria : TimerTareasDiarias.listaTareasDiariasAuxiliarPasados) {
//            if (tareaDiaria.getEstadoTarea().equals("Sin Click")) {
//                DesktopNotify.showDesktopMessage("Tareas Diarias", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " En la fecha " + tareaDiaria.getFecha(), DesktopNotify.TIP, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        FormTareasDiarias a = new FormTareasDiarias(null, true);
//                        a.llenarTextoCampos(tareaDiaria);
//                        a.btnAgregar.setText("Guardar");
//                        a.tipo_accion = a.ACCION_MODIFICAR;
//                        a.inabilitarTextos(true);
//                        a.setVisible(true);
//                        String numero = FormMenu.lblNotificacionTareas.getText();
//                        FormMenu.lblNotificacionTareas.setText("" + (Integer.valueOf(numero) - 1));
//                        tareaDiaria.setEstadoTarea("Clickeado");
//                        modificarBaseDatosTareaDiaria(tareaDiaria);
//                        
//                        
//                    }
//                });
//            } else {
//                DesktopNotify.showDesktopMessage("Tareas Pasadas", tareaDiaria.getPersonal().getGenerarNombre() + " Dice " + tareaDiaria.getDescripcion() + " En la fecha  " + tareaDiaria.getFecha(), DesktopNotify.TIPO_CLICKEADO, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                       
//                        FormTareasDiarias a = new FormTareasDiarias(null, true);
//                        a.llenarTextoCampos(tareaDiaria);
//                        a.btnAgregar.setText("Guardar");
//                        a.tipo_accion = a.ACCION_MODIFICAR;
//                        a.inabilitarTextos(true);
//                        a.setVisible(true);
//                         DesktopNotify.eliminarWindowsAll();
//                        TimerTareasDiarias.iniciarOtraVes();
//                        llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);
//                    }
//                });
//
//            }
//        }
    }

    void modificarBaseDatosTareaDiaria(TareaDiaria tareaDiaria) {
        gestionarTareaDiariaServicio.modificarTareaEstadoNotificacion(tareaDiaria);
        DesktopNotify.eliminarWindowsAll();
        TimerTareasDiarias.iniciarOtraVes();
        llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);

    }

    public void llenarDescktopCumpleaños(List<Personal> listaTareas) {
        contador = 0;
        for (Personal personal : listaTareas) {
//            if (personal.getEstadoPersonalNotificacion().equals("Sin Click")) {
            DesktopNotify.showDesktopMessage("Cumpleaños", "Hoy es el cumpleaño de " + personal.getGenerarNombre() + " Cumple " + personal.calcularEdad() + " años !Deséale un feliz cumpleaños!", DesktopNotify.TIPO_CUMPLEAÑOS, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String numero = FormMenu.lblCumpleaños.getText();
                    FormMenu.lblCumpleaños.setText("" + (Integer.valueOf(numero) - 1));
                    personal.setEstadoPersonalNotificacion("Clickeado");
//                        modificarBaseDatosPersonalEstadoNotificacion(personal);
                }
            });
//            } else {
//                DesktopNotify.showDesktopMessage("Cumpleaños", "Hoy es el cumpleaño de " + personal.getGenerarNombre() + " Cumple " + personal.calcularEdad() + " !Deséale un feliz cumpleaños!", DesktopNotify.TIPO_CUMPLEAÑOS, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
////                        DesktopNotify.eliminarWindowsAll();
////                        TimerCumpleaños.iniciarOtraVes();
////                        llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);
//                    }
//                });
//
//            }

            contador++;
        }

//        contador = 0;
//        for (Personal personal : TimerCumpleaños.listaPersonalAuxiliarPasados) {
//            if (personal.getEstadoPersonalNotificacion().equals("Sin Click")) {
//                DesktopNotify.showDesktopMessage("Cumpleaños", "Fue el cumpleaños de  " + personal.getGenerarNombre() + " Cumplio " + personal.calcularEdad() + "Cumplio " + personal.getFechaNacimiento(), DesktopNotify.TIPO_CUMPLEAÑOS, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        String numero = FormMenu.lblCumpleaños.getText();
//                        FormMenu.lblCumpleaños.setText("" + (Integer.valueOf(numero) - 1));
//                        personal.setEstadoPersonalNotificacion("Clickeado");
//                        modificarBaseDatosPersonalEstadoNotificacion(personal);
//                    }
//                });
//            } else {
//                DesktopNotify.showDesktopMessage("Cumpleaños", "Fue el cumpleaño de  " + personal.getGenerarNombre() + " Cumplio " + personal.calcularEdad() + " Cumplio " + personal.getFechaNacimiento(), DesktopNotify.TIPO_CUMPLEAÑOS, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        DesktopNotify.eliminarWindowsAll();
//                        TimerCumpleaños.iniciarOtraVes();
//                        llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);
//                    }
//                });
//
//            }
//
//            contador++;
//        }
    }

    void modificarBaseDatosPersonalEstadoNotificacion(Personal personal) {
        try {
            gestionarPersonalServicio.modificarPersonalEstadoNotificacion(personal);
            DesktopNotify.eliminarWindowsAll();
            TimerCumpleaños.iniciarOtraVes();
            llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);

        } catch (Exception e) {
        }

    }
}
