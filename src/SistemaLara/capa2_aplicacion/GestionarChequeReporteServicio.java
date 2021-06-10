/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa4_persistencia.ChequeReporteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class GestionarChequeReporteServicio {

    private final GestorJDBC gestorJDBC;
    private final ChequeReporteDAOMySQL chequeReporteDAOMySQL;

    public GestionarChequeReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        chequeReporteDAOMySQL = new ChequeReporteDAOMySQL(gestorJDBC);
    }

    public JasperPrint mostrarCheque(ChequeProveedor cheque, String moneda) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = chequeReporteDAOMySQL.reporteChequeProveedor(cheque, moneda);
        gestorJDBC.cerrarConexion();
        return print;
    }
    
     public JasperPrint mostrarCheque(ChequeCliente cheque, String moneda) throws Exception {
        gestorJDBC.abrirConexion();
        JasperPrint print = chequeReporteDAOMySQL.reporteChequeCliente(cheque, moneda);
        gestorJDBC.cerrarConexion();
        return print;
    }
}
