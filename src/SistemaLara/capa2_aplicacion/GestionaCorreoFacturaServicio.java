/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
import SistemaLara.capa4_persistencia.CorreoFacturaDAOMySQL;
import SistemaLara.capa4_persistencia.EstadoFacturaElectronicaDAOMySQL;

import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.FacturaElectronicaDAOMySQL;
import SistemaLara.capa4_persistencia.TipoFacturaElectronicaDAOMySQL;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionaCorreoFacturaServicio {

    private final GestorJDBC gestorJDBC;
    private final CorreoFacturaDAOMySQL correoFacturaDAOMySQL;

    public GestionaCorreoFacturaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        correoFacturaDAOMySQL = new CorreoFacturaDAOMySQL(gestorJDBC);

    }

    public void mostrarTodos(JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        correoFacturaDAOMySQL.mostrarTodos(table);
        gestorJDBC.cerrarConexion();
    }

    public void agregar(CorreoFactura correoFactura) throws Exception {
        gestorJDBC.abrirConexion();
        correoFacturaDAOMySQL.agregar(correoFactura);
        gestorJDBC.cerrarConexion();
    }

}
