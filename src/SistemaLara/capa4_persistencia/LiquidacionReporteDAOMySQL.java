/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.FormGestionarFactura;
import SistemaLara.capa1_presentacion.FormLogin;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import static javax.swing.JComponent.getDefaultLocale;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
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
public class LiquidacionReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public LiquidacionReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;

    }

    public JasperPrint reporteLiquidacion(Liquidacion liquidacion) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptLiquidacionClienteEntrante.jasper";
        try {
//            if (IniciarSesion.getUsuario().getEmpresa().getRutaImagen().toString().equals("")) {
//                String imagen = empresaDAOMySQL.obtenerImagen();
//                JOptionPane.showMessageDialog(null, "recofidsfd " + imagen);
//                IniciarSesion.getUsuario().getEmpresa().setRutaImagen(imagen);
//            }

            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
//            ruta = IniciarSesion.getUsuario().getEmpresa().getRutaImagen().toString();

            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", liquidacion.getCodigo());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreRuta = "LIQUIDACION - " + liquidacion.getClienteEntrante().generarNombre() + " - " + liquidacion.getCodigo() + " - " + liquidacion.getFecha();
            String url = IniciarSesion.getUsuario().getRutaReporte().getLiquidacion() + nombreRuta + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());

        }
        return jprint;
    }

    public JasperPrint reporteLiquidacionLotePorCliente(JComboBox comboBox) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptLiquidacionDeLotePorClienteyFecha.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("tipocliente", comboBox.getSelectedItem().toString());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String url = IniciarSesion.getUsuario().getRutaReporte().getLiquidacion() + comboBox.getSelectedItem().toString() + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Liquidacion Por Lote Cliente.\n"
                   + "Intente de nuevo o consulte con el Administrador.");

        
        }
        return jprint;
    }

}
