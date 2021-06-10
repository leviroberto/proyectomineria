/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.PagoTransporte;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PagoTransporteReporteDAOMySQL;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarPagoTransporteReporteServicio {
    
      private final GestorJDBC gestorJDBC;
    private final PagoTransporteReporteDAOMySQL PagoTransporteDAOMySQL; 

    public GestionarPagoTransporteReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        PagoTransporteDAOMySQL = new PagoTransporteReporteDAOMySQL(gestorJDBC);
    }
    
       public JasperPrint mostrarPagoTransporte(PagoTransporte pagoTransporte) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = PagoTransporteDAOMySQL.reportePagoTransporte(pagoTransporte);
        gestorJDBC.cerrarConexion();
        return print;
    }
}
