/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.IniciarSesion;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.JComponent.getDefaultLocale;
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
public class ChequeReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public ChequeReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public JasperPrint reporteChequeProveedor(ChequeProveedor cheque, String moneda) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();
        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptCheque.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("logo1", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", cheque.getCodigo());
            parametros.put("moneda", moneda);
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreArchivo = "CHEQUE - " + cheque.getCodigo() + " - " + cheque.getEntregadoA() + " - " + cheque.getFechaEmision();
            String url = IniciarSesion.getUsuario().getRutaReporte().getCheque() + nombreArchivo + ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Cheque.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

    public JasperPrint reporteChequeCliente(ChequeCliente cheque, String moneda) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();
        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptChequeCliente.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("logo1", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", cheque.getCodigo());
            parametros.put("moneda", moneda);
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreArchivo = "CHEQUE - " + cheque.getCodigo() + " - " + cheque.getEntregadoA() + " - " + cheque.getFechaEmision();
            String url = IniciarSesion.getUsuario().getRutaReporte().getCheque() + nombreArchivo + ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Cheque.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }
}
