/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.Cambio;
import SistemaLara.capa3_dominio.RutaReporte;
import SistemaLara.capa4_persistencia.CambioDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.RutaReporteDAOMySQL;
import javax.swing.JLabel;

import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarRutaReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final RutaReporteDAOMySQL rutaReporteDAOMySQL;

    public GestionarRutaReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        rutaReporteDAOMySQL = new RutaReporteDAOMySQL(gestorJDBC);
    }

    public RutaReporte obtenerCambio(int codigo) throws Exception {
        RutaReporte rutaReporte = null;
        gestorJDBC.abrirConexion();
        rutaReporte = rutaReporteDAOMySQL.obtenerRutaReporte(codigo);
        gestorJDBC.cerrarConexion();
        return rutaReporte;
    }

}
