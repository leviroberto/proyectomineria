/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.Cambio;
import SistemaLara.capa4_persistencia.CambioDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import javax.swing.JLabel;

import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarCambioServicio {

    private final GestorJDBC gestorJDBC;
    private final CambioDAOMySQL cambioDAOMySQL;

    public GestionarCambioServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        cambioDAOMySQL = new CambioDAOMySQL(gestorJDBC);
    }

    public int modificarCambio(Cambio cambio) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = cambioDAOMySQL.modificar(cambio);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarCambio(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        cambioDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();
    }

    public void llenarCamposModificar(FCMaterialTextField codigo, FCMaterialTextField dolar, FCMaterialTextField tarifa, FCMaterialTextField tarifaa, FCMaterialTextField trans1, FCMaterialTextField trans2, FCMaterialTextField poli, FCMaterialTextField tonelada) throws Exception {
        gestorJDBC.abrirConexion();
        cambioDAOMySQL.llenarCamposNuevo(codigo, dolar, tarifaa, tarifa, trans1, trans2, poli, tonelada);
        gestorJDBC.cerrarConexion();

    }

    public Cambio obtenerCambio() throws Exception {
        Cambio cambio = null;
        gestorJDBC.abrirConexion();
        cambio = cambioDAOMySQL.obtenerCambio();
        gestorJDBC.cerrarConexion();
        return cambio;
    }

//       public void mostrarAdelantoProveedor(Cambio cambio) throws Exception {
//        gestorJDBC.abrirConexion();
//        cambioDAOMySQL.modificarCambio(cambio);
//        gestorJDBC.cerrarConexion();
//    }
    public void modificarCambios(String cambio) throws Exception {
        gestorJDBC.abrirConexion();
        cambioDAOMySQL.modificarCambio(cambio);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarCambioDolar(int estado, JLabel lblcambio) throws Exception {
        gestorJDBC.abrirConexion();
        cambioDAOMySQL.mostrarCambioDolar(estado, lblcambio);
        gestorJDBC.cerrarConexion();
    }

    public int guardarCambio(Cambio cambio) throws Exception {
  int resultado=0;
        gestorJDBC.abrirConexion();
        resultado=cambioDAOMySQL.guardar(cambio);
        gestorJDBC.cerrarConexion();
return  resultado;
    }

}
