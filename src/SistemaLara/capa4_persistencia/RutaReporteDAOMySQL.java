/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.RutaReporte;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author FiveCod Software
 */
public class RutaReporteDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public RutaReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public RutaReporte obtenerRutaReporte(int codigo) throws SQLException {
        ResultSet resultado;
        RutaReporte rutaReporte = null;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("RutaReporte_ObtenerDatos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                rutaReporte = new RutaReporte();
                rutaReporte.setCodigo(resultado.getInt("ReporteRuta_Id"));
                rutaReporte.setAdelantoCliente(resultado.getString("RutaReporte_AdelantoCliente"));
                rutaReporte.setAdelantoProveedor(resultado.getString("RutaReporte_AdelantoProveedor"));
                rutaReporte.setCheque(resultado.getString("RutaReporte_Cheque"));
                rutaReporte.setFactura(resultado.getString("RutaReporte_Factura"));
                rutaReporte.setLiquidacion(resultado.getString("RutaReporte_Liquidacion"));
                rutaReporte.setPagoTransporte(resultado.getString("RutaReporte_PagoTransporte"));
                rutaReporte.setValorizacion(resultado.getString("RutaReporte_Valorizacion"));
                rutaReporte.setNotaDebito(resultado.getString("RutaReporte_NotaDebito"));
                rutaReporte.setNotaCredito(resultado.getString("RutaReporte_NotaCredito"));
            }
            resultado.close();
            return rutaReporte;

        } catch (Exception e) {
            throw new SQLException("No se pudo Ruta Reporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //  return null;

    }

}
