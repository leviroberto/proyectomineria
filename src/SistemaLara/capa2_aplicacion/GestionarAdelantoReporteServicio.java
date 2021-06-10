/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa4_persistencia.AdelantoReporteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import javax.swing.JLabel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarAdelantoReporteServicio {
    private final GestorJDBC gestorJDBC;
    private final AdelantoReporteDAOMySQL adelantoReporteDAOMySQL;

    public GestionarAdelantoReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        adelantoReporteDAOMySQL = new AdelantoReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarAdelantoCliente(ClienteEntrante adelanto, JLabel lblsoles, JLabel lblDolares) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = adelantoReporteDAOMySQL.reporteAdelantoCliente(adelanto, lblsoles, lblDolares);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public JasperPrint reporteAdelantoValorizacionDetalle(Integer codigo, JLabel lblsoles, JLabel lblDolares) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = adelantoReporteDAOMySQL.reporteAdelantoValorizacionDetalle(codigo, lblsoles, lblDolares);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public JasperPrint mostrarAdelantoProveedor(ProveedorServicio proveedorServicio, JLabel lblsoles, JLabel lblDolares) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = adelantoReporteDAOMySQL.reporteAdelantoProveedor(proveedorServicio, lblsoles, lblDolares);
        gestorJDBC.cerrarConexion();
        return print;
    }
}
