/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.IniciarSesion;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author XGamer
 */
public class FacturaReporteDAOMySQL {

    GestorJDBC gestorJDBC;
    private FacturaElectronicaDAOMySQL facturaElectronicaDAOMySQL;

    public FacturaReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
        this.facturaElectronicaDAOMySQL = new FacturaElectronicaDAOMySQL(gestorJDBC);

    }

    public JasperPrint reporteRegistroFactura(Factura factura) throws SQLException {
        JasperPrint jprint = null;
//        try {
        Map parametros = new HashMap();
//            p.put("codigo", factura.getCodigo());
//
//            JasperReport jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath()
//                    + "/src/restaurant/capa7_reportes/RptRegistroPedido.jrxml");
//            //(JasperReport) JRLoader.loadObject("/src/restaurant/capa7_reportes/RptReporteCliente.jrxml");
//            print = JasperFillManager.fillReport(jasperReport, p, gestorJDBC.getConnection());
////            JasperViewer view = new JasperViewer(print, false);
////            view.setTitle("Reporte de Cliente");
////            view.setVisible(true);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al mostarr Reporte" + e.getMessage());
//        }
//        return print;

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptRegistroFactura.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("codigo", factura.getCodigo());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String url = IniciarSesion.getUsuario().getRutaReporte().getFactura() + factura.getNroFactura() + ".pdf";
            facturaElectronicaDAOMySQL.modificarRutaPDF(url, factura.getCodigo());
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return jprint;
    }

    public String generarReporteFacturaParaElectronico(CorreoFactura correoFactura) throws SQLException {
        JasperPrint jprint = null;
        String url = "";
//        try {
        Map parametros = new HashMap();
//            p.put("codigo", factura.getCodigo());
//
//            JasperReport jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath()
//                    + "/src/restaurant/capa7_reportes/RptRegistroPedido.jrxml");
//            //(JasperReport) JRLoader.loadObject("/src/restaurant/capa7_reportes/RptReporteCliente.jrxml");
//            print = JasperFillManager.fillReport(jasperReport, p, gestorJDBC.getConnection());
////            JasperViewer view = new JasperViewer(print, false);
////            view.setTitle("Reporte de Cliente");
////            view.setVisible(true);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al mostarr Reporte" + e.getMessage());
//        }
//        return print;

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptRegistroFactura.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("codigo", correoFactura.getElectronico().getCodigoFactura());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            url = IniciarSesion.getUsuario().getRutaReporte().getFactura() + correoFactura.getElectronico().getNumeroFactura() + ".pdf";
            facturaElectronicaDAOMySQL.modificarRutaPDF(url, correoFactura.getElectronico().getCodigoFactura());
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return url;
    }

    public void resporteGenerarPDF(Factura factura) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();
        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptRegistroFactura.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("codigo", factura.getCodigo());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String url = IniciarSesion.getUsuario().getRutaReporte().getFactura() + factura.getNroFactura() + ".pdf";

            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();

        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Factura PDF.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }
}
