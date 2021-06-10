/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ValorizacionReporteDAOMySQL;
import java.sql.Date;
import net.sf.jasperreports.engine.JasperPrint;
import rojerusan.RSTableMetro;

/**
 *
 * @author XGamer
 */
public class GestionarValorizacionReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final ValorizacionReporteDAOMySQL valorizacionReporteDAOMySQL;

    public GestionarValorizacionReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        valorizacionReporteDAOMySQL = new ValorizacionReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarValorizacion(Liquidacion liquidacion, Valorizacion valorizacion, FCMaterialTextField totalUS,
            FCMaterialTextField totalTmh, FCMaterialTextField tipocambio, FCMaterialTextField totalDescuento,
            FCMaterialTextField costrotransportetrujillosoles, FCMaterialTextField costrotransportetrujillodolares,
            FCMaterialTextField costotransporteanascasoles,
            FCMaterialTextField costotransporteanascadolares, FCMaterialTextField tarifaanalisis,
            FCMaterialTextField tarifaporcentaje, FCMaterialTextField adelanto, FCMaterialTextField otros,
            FCMaterialTextField totalgastos, FCMaterialTextField saldoporpagar) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = valorizacionReporteDAOMySQL.reporteValorizacion(liquidacion, valorizacion, totalUS, totalTmh, tipocambio, totalDescuento, costrotransportetrujillosoles, costrotransportetrujillodolares, costotransporteanascasoles, costotransporteanascadolares, tarifaanalisis, tarifaporcentaje, adelanto, otros, totalgastos, saldoporpagar);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public void mostrarValoracionTodos(Date dateDe, Date dateHasta, RSTableMetro tableFactura, FCMaterialTextField txt_adelanto, FCMaterialTextField txt_otros_gastos, FCMaterialTextField txt_tarifa_analisis, FCMaterialTextField txt_tarifa_porcentaje, FCMaterialTextField txt_tmh, FCMaterialTextField txt_totalUS, FCMaterialTextField txt_total_gastos, FCMaterialTextField txt_total_pagar, FCMaterialTextField txt_transp_a_nasca_dolar, FCMaterialTextField txt_transp_a_trujillo_dolar) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionReporteDAOMySQL.mostrarValoracionTodos(dateDe, dateHasta, tableFactura, txt_adelanto,
                txt_otros_gastos,
                txt_tarifa_analisis,
                txt_tarifa_porcentaje,
                txt_tmh,
                txt_totalUS,
                txt_total_gastos,
                txt_total_pagar,
                txt_transp_a_nasca_dolar,
                txt_transp_a_trujillo_dolar);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarValoracionPorEspecifico(Date dateDe, Date dateHasta, RSTableMetro tableFactura, int codigo,
            FCMaterialTextField txt_adelanto,
            FCMaterialTextField txt_otros_gastos, 
            FCMaterialTextField txt_tarifa_analisis, 
            FCMaterialTextField txt_tarifa_porcentaje, 
            FCMaterialTextField txt_tmh, 
            FCMaterialTextField txt_totalUS,
            FCMaterialTextField txt_total_gastos,
            FCMaterialTextField txt_total_pagar, 
            FCMaterialTextField txt_transp_a_nasca_dolar,
            FCMaterialTextField txt_transp_a_trujillo_dolar) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionReporteDAOMySQL.mostrarValoracionPorEspecifico(dateDe, dateHasta, tableFactura, codigo, txt_adelanto,
                txt_otros_gastos,
                txt_tarifa_analisis,
                txt_tarifa_porcentaje,
                txt_tmh,
                txt_totalUS,
                txt_total_gastos,
                txt_total_pagar,
                txt_transp_a_nasca_dolar,
                txt_transp_a_trujillo_dolar);
        gestorJDBC.cerrarConexion();

    }
}
