/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import SistemaLara.capa1_presentacion.FormMenu;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import rojerusan.RSAnimation;

/**
 *
 * @author FiveCod Software
 */
public class Animacion {

    public static int anchoPantalla;

    public static void moverParaIzquierda(JDialog dialog) {

        int nummero = 0;
        if (FormMenu.opcion_ancho_pantalla == FormMenu.ANCHO_PANTALLA_PEQUEÑA) {
            nummero = 186;
        }
      
      
        RSAnimation.setMoverIzquierda(anchoPantalla + dialog.getWidth(), (nummero + (anchoPantalla / 2)) - dialog.getWidth() / 2, 1, 90, dialog);
    }

    public static void moverParaDerecha(JDialog dialog) {
        RSAnimation.setMoverDerecha((186 + (anchoPantalla / 2)) - dialog.getWidth() / 2, anchoPantalla + dialog.getWidth(), 1, 7, dialog);
    }

    public static void bajar(int inicio, final int fin, final long retardo, final int salto, JComponent componente) {
        new Thread() {
            public void run() {
                for (int i = inicio; i <= fin; i += salto) {
                    try {
                        Thread.sleep(retardo);
                        componente.setLocation(componente.getX(), i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void subir(int inicio, final int fin, final long retardo, final int salto, JComponent componente) {
        new Thread() {
            public void run() {
                for (int i = inicio; i >= fin; i -= salto) {
                    try {
                        Thread.sleep(retardo);
                        componente.setLocation(componente.getX(), i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void mover_derecha(int inicio, final int fin, final long retardo, final int salto, JComponent componente) {
        new Thread() {
            public void run() {
                for (int i = inicio; i <= fin; i += salto) {
                    try {
                        Thread.sleep(retardo);
                        componente.setLocation(i, componente.getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void mover_izquierda(int inicio, final int fin, final long retardo, final int salto, JComponent componente) {
        new Thread() {
            public void run() {
                for (int i = inicio; i >= fin; i -= salto) {
                    try {
                        Thread.sleep(retardo);
                        componente.setLocation(i, componente.getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void bajar(int inicio, final int fin, final long retardo, final int salto, JFrame frame) {
        new Thread() {
            public void run() {
                for (int i = inicio; i <= fin; i += salto) {
                    try {
                        Thread.sleep(retardo);
                        frame.setLocation(frame.getX(), i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void subir(int inicio, final int fin, final long retardo, final int salto, JFrame frame) {
        new Thread() {
            public void run() {
                for (int i = inicio; i >= fin; i -= salto) {
                    try {
                        Thread.sleep(retardo);
                        frame.setLocation(frame.getX(), i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void mover_derecha(int inicio, final int fin, final long retardo, final int salto, JFrame frame) {
        new Thread() {
            public void run() {
                for (int i = inicio; i <= fin; i += salto) {
                    try {
                        Thread.sleep(retardo);
                        frame.setLocation(i, frame.getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void mover_izquierda(int inicio, final int fin, final long retardo, final int salto, JFrame frame) {
        new Thread() {
            public void run() {
                for (int i = inicio; i >= fin; i -= salto) {
                    try {
                        Thread.sleep(retardo);
                        frame.setLocation(i, frame.getY());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
