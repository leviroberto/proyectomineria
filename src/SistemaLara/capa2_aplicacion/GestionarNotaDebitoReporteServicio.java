/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoReporteDAOMySQL;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarNotaDebitoReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final NotaDebitoReporteDAOMySQL notaDebitoReporteDAOMySQL;

    public GestionarNotaDebitoReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        notaDebitoReporteDAOMySQL = new NotaDebitoReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarRegistroPedido(NotaDebito notaDebito) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = notaDebitoReporteDAOMySQL.reporteRegistroNotaDebito(notaDebito);
        gestorJDBC.cerrarConexion();
        return print;
    }

  
}
