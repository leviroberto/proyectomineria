/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.LiquidacionReporteDAOMySQL;
import java.sql.Date;
import javax.swing.JComboBox;

import net.sf.jasperreports.engine.JasperPrint;
import rojerusan.RSTableMetro;

/**
 *
 * @author XGamer
 */
public class GestionarLiquidacionReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final LiquidacionReporteDAOMySQL liquidacionReporteDAOMySQL;

    public GestionarLiquidacionReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        liquidacionReporteDAOMySQL = new LiquidacionReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarLiquidacion(Liquidacion liquidacion) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = liquidacionReporteDAOMySQL.reporteLiquidacion(liquidacion);
        gestorJDBC.cerrarConexion();
        return print;
    }

    public JasperPrint mostrarLiquidacionLotePorCliente(JComboBox box) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = liquidacionReporteDAOMySQL.reporteLiquidacionLotePorCliente(box);
        gestorJDBC.cerrarConexion();
        return print;
    }



}
