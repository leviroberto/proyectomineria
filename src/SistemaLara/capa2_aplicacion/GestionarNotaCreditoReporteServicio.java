/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;



import SistemaLara.capa3_dominio.NotaCredito;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.NotaCreditoReporteDAOMySQL;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarNotaCreditoReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final NotaCreditoReporteDAOMySQL notaCreditoReporteDAOMySQL;

    public GestionarNotaCreditoReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        notaCreditoReporteDAOMySQL = new NotaCreditoReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarRegistroNotaCredito(NotaCredito notaCredito) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = notaCreditoReporteDAOMySQL.reporteRegistroNotaCredito(notaCredito);
        gestorJDBC.cerrarConexion();
        return print;
    }

  
}
