/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import SistemaLara.capa1_presentacion.FormMenu;
import javax.swing.JFrame;

/**
 *
 * @author XGamer
 */
public class IniciarSesion {

    private static JFrame iniciarSesion;
    private static Personal personal;

    private IniciarSesion() {
    }

    public static JFrame crearInicioSesion() {
        if (iniciarSesion == null) {
            iniciarSesion = new FormMenu();
          
        }
        return iniciarSesion;
    }

    public static Personal getUsuario() {
        return personal;
    }

    public static void setUsuario(Personal personal) {
        if (IniciarSesion.personal == null) {
            IniciarSesion.personal = personal;
        }
    }

    public static void ponerUsuarioNull() {
        IniciarSesion.personal = null;
    }
      public static void poneIniciarSesionNull() {
        IniciarSesion.iniciarSesion = null;
    }
}
