/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa4_persistencia.FacturaReporteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarFacturaReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final FacturaReporteDAOMySQL facturaReporteDAOMySQL;

    public GestionarFacturaReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        facturaReporteDAOMySQL = new FacturaReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarRegistroPedido(Factura factura) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = facturaReporteDAOMySQL.reporteRegistroFactura(factura);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public String generarReporteFacturaParaElectronico(CorreoFactura correoFactura) throws Exception {
        gestorJDBC.abrirConexion();
        String print = facturaReporteDAOMySQL.generarReporteFacturaParaElectronico(correoFactura);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public void generarFacturaElectronica(Factura factura) throws Exception {
        gestorJDBC.abrirConexion();
        facturaReporteDAOMySQL.resporteGenerarPDF(factura);
        gestorJDBC.cerrarConexion();

    }
}
