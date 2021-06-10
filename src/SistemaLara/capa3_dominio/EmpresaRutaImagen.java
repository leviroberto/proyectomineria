/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

/**
 *
 * @author mineria
 */
public class EmpresaRutaImagen {

    public static String rutaImagenYe = "/SistemaLara/capa5_imagenes/logoyersica.jpg";
    public static String rutaImagenSun = "/SistemaLara/capa5_imagenes/LogoSun.png";
    public static String rutaImagenJens = "/SistemaLara/capa5_imagenes/logojens.png";
    public static String rutaImagenMaster = "/SistemaLara/capa5_imagenes/general.png";

    public static String obtenerRuta() {
        if (IniciarSesion.getUsuario().getEmpresa().getNroDocumento().equals("10474255153")) {
            return rutaImagenSun;
        } else {
            return rutaImagenMaster;
            //return rutaImagenYe;
        }
    }

    public static String obtenerGeneral() {

        return rutaImagenMaster;

    }
}
