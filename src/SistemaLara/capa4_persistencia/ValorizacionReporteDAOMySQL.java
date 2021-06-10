/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa1_presentacion.util.RedondearDecimales;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.JComponent.getDefaultLocale;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import rojerusan.RSTableMetro;

/**
 *
 * @author XGamer
 */
public class ValorizacionReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public ValorizacionReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;

    }

    public JasperPrint reporteValorizacion(Liquidacion liquidacion, Valorizacion valo, FCMaterialTextField totalUS,
            FCMaterialTextField totalTmh, FCMaterialTextField tipocambio, FCMaterialTextField totalDescuento,
            FCMaterialTextField costrotransportetrujillosoles, FCMaterialTextField costrotransportetrujillodolares,
            FCMaterialTextField costotransporteanascasoles,
            FCMaterialTextField costotransporteanascadolares, FCMaterialTextField tarifaanalisis,
            FCMaterialTextField tarifaporcentaje, FCMaterialTextField adelanto, FCMaterialTextField otros,
            FCMaterialTextField totalgastos, FCMaterialTextField saldoporpagar) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptValorizacionMineral.jasper";
        try {
            //  JOptionPane.showMessageDialog(null, liquidacion.getCodigo());
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", liquidacion.getClienteEntrante().getCodigo());
            parametros.put("fecha", liquidacion.getFecha());
            parametros.put("totalUS", totalUS.getText());
            parametros.put("totalTmh", totalTmh.getText());
            parametros.put("tipoCambio", tipocambio.getText());
            parametros.put("totalDescuento", totalDescuento.getText());//TOTAL SOLES
            parametros.put("costoatrujillosoles", costrotransportetrujillosoles.getText());
            parametros.put("costoatrujillodolares", costrotransportetrujillodolares.getText());
            parametros.put("costoanascasoles", costotransporteanascasoles.getText());
            parametros.put("costoanascadolares", costotransporteanascadolares.getText());
            parametros.put("tarifaanalisis", tarifaanalisis.getText());

            parametros.put("tarifaporcentaje", tarifaporcentaje.getText());
            parametros.put("adelanto", adelanto.getText());
            parametros.put("otros", otros.getText());
            parametros.put("totalgastos", totalgastos.getText());
            parametros.put("saldoporpagar", saldoporpagar.getText());
            parametros.put("valorizacionId", valo.getCodigo());

            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreArchivo = "VALORIZACION N° " + valo.getCodigo() + " - LIQUIDACION N° " + liquidacion.getCodigo() + " - FECHA " + liquidacion.getFecha() + "- CLIENTE " + liquidacion.getClienteEntrante().getApellidos() + " " + liquidacion.getClienteEntrante().getNombres();
            String url = IniciarSesion.getUsuario().getRutaReporte().getValorizacion() + nombreArchivo + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

    public void mostrarValoracionTodos(Date dateDe, Date dateHasta, RSTableMetro table,
            FCMaterialTextField txt_adelanto,
            FCMaterialTextField txt_otros_gastos, 
            FCMaterialTextField txt_tarifa_analisis,
            FCMaterialTextField txt_tarifa_porcentaje,
            FCMaterialTextField txt_tmh, 
            FCMaterialTextField txt_totalUS,
            FCMaterialTextField txt_total_gastos, 
            FCMaterialTextField txt_total_pagar,
            FCMaterialTextField txt_transp_a_nasca_dolar,
            FCMaterialTextField txt_transp_a_trujillo_dolar) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        Fila fila;
        double totalUs = 0, thm = 0, costoTraTruDolar = 0, costTraNascDolar = 0, tarifaAnalisis = 0, totalPorcentaje = 0, adelante = 0, otrosGastos = 0, totalGastos = 0, totalPagar = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_MostrarTodosReporte_sp(?,?)");
            prcProcedimientoAlmacenado.setDate("dateDe", dateDe);
            prcProcedimientoAlmacenado.setDate("datHasta", dateHasta);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Valorizacion_Fecha"));

                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalUS"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_Tmh"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_CostoTraTruDolar"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_CostTraNascDolar"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valoracion_TarifaAnalisis"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalPorcentaje"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_Adelanto"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_OtrosGastos"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalGastos"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalPagar"),2));

                totalUs += resultado.getDouble("Valorizacion_TotalUS");
                thm += resultado.getDouble("Valorizacion_Tmh");
                costoTraTruDolar += resultado.getDouble("Valorizacion_CostoTraTruDolar");
                costTraNascDolar += resultado.getDouble("Valorizacion_CostTraNascDolar");
                tarifaAnalisis += resultado.getDouble("Valoracion_TarifaAnalisis");
                totalPorcentaje += resultado.getDouble("Valorizacion_TotalPorcentaje");
                adelante += resultado.getDouble("Valorizacion_Adelanto");
                otrosGastos += resultado.getDouble("Valorizacion_OtrosGastos");
                totalGastos += resultado.getDouble("Valorizacion_TotalGastos");
                totalPagar += resultado.getDouble("Valorizacion_TotalPagar");

                modeloTabla.agregarFila(fila);
            }
            resultado.close();
            table.setModel(modeloTabla);

            txt_adelanto.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(adelante, 2) + "")));
            txt_otros_gastos.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(otrosGastos, 2) + "")));
            txt_tarifa_analisis.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(tarifaAnalisis, 2) + "")));
            txt_tarifa_porcentaje.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalPorcentaje, 2) + "")));
            //tarifaAnalisis.setText(String.valueOf(totalUs));
            txt_tmh.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(thm, 2) + "")));
            txt_totalUS.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalUs, 2) + "")));
            txt_total_gastos.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalGastos, 2) + "")));
            txt_total_pagar.setText(String.valueOf(RedondearDecimales.redondearDecimales(totalPagar, 2) + ""));
            txt_transp_a_nasca_dolar.setText(String.valueOf(RedondearDecimales.redondearDecimales(costTraNascDolar, 2) + ""));
            txt_transp_a_trujillo_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(costoTraTruDolar, 2) + "")));

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarValoracionPorEspecifico(Date dateDe, Date dateHasta, RSTableMetro tableFactura, int codigo, FCMaterialTextField txt_adelanto, FCMaterialTextField txt_otros_gastos, FCMaterialTextField txt_tarifa_analisis, FCMaterialTextField txt_tarifa_porcentaje, FCMaterialTextField txt_tmh, FCMaterialTextField txt_totalUS, FCMaterialTextField txt_total_gastos, FCMaterialTextField txt_total_pagar, FCMaterialTextField txt_transp_a_nasca_dolar, FCMaterialTextField txt_transp_a_trujillo_dolar) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) tableFactura.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        Fila fila;
        double totalUs = 0, thm = 0, costoTraTruDolar = 0, costTraNascDolar = 0, tarifaAnalisis = 0, totalPorcentaje = 0, adelante = 0, otrosGastos = 0, totalGastos = 0, totalPagar = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_MostrarValoracionPorEspecifico_sp(?,?,?)");
            prcProcedimientoAlmacenado.setDate("dateDe", dateDe);
            prcProcedimientoAlmacenado.setDate("dateHasta", dateHasta);
            prcProcedimientoAlmacenado.setInt("cliente_id", codigo);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Valorizacion_Fecha"));
                 fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalUS"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_Tmh"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_CostoTraTruDolar"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_CostTraNascDolar"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valoracion_TarifaAnalisis"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalPorcentaje"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_Adelanto"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_OtrosGastos"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalGastos"),2));
                fila.agregarValorCelda(RedondearDecimales.redondearDecimales(resultado.getDouble("Valorizacion_TotalPagar"),2));

                totalUs += resultado.getDouble("Valorizacion_TotalUS");
                thm += resultado.getDouble("Valorizacion_Tmh");
                costoTraTruDolar += resultado.getDouble("Valorizacion_CostoTraTruDolar");
                costTraNascDolar += resultado.getDouble("Valorizacion_CostTraNascDolar");
                tarifaAnalisis += resultado.getDouble("Valoracion_TarifaAnalisis");
                totalPorcentaje += resultado.getDouble("Valorizacion_TotalPorcentaje");
                adelante += resultado.getDouble("Valorizacion_Adelanto");
                otrosGastos += resultado.getDouble("Valorizacion_OtrosGastos");
                totalGastos += resultado.getDouble("Valorizacion_TotalGastos");
                totalPagar += resultado.getDouble("Valorizacion_TotalPagar");

                modeloTabla.agregarFila(fila);

            }

            txt_adelanto.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(adelante, 2) + "")));
            txt_otros_gastos.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(otrosGastos, 2) + "")));
            txt_tarifa_analisis.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(tarifaAnalisis, 2) + "")));
            txt_tarifa_porcentaje.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalPorcentaje, 2) + "")));
            //tarifaAnalisis.setText(String.valueOf(totalUs));
            txt_tmh.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(thm, 2) + "")));
            txt_totalUS.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalUs, 2) + "")));
            txt_total_gastos.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(totalGastos, 2) + "")));
            txt_total_pagar.setText(String.valueOf(RedondearDecimales.redondearDecimales(totalPagar, 2) + ""));
            txt_transp_a_nasca_dolar.setText(String.valueOf(RedondearDecimales.redondearDecimales(costTraNascDolar, 2) + ""));
            txt_transp_a_trujillo_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(costoTraTruDolar, 2) + "")));
            resultado.close();
            tableFactura.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
}
