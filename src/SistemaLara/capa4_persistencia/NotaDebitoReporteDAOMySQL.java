/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.NotaDebito;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class NotaDebitoReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public NotaDebitoReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;

    }

    public JasperPrint reporteRegistroNotaDebito(NotaDebito notaDebito) throws SQLException {
        JasperPrint jprint = null;
//       try {
        Map parametros = new HashMap();

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptRegistroNotaDebito.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("codigo", notaDebito.getCodigo());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String url = IniciarSesion.getUsuario().getRutaReporte().getNotaDebito()+ notaDebito.getNumeroNotaDebito()+ ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Nota Debito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

}
