/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
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
public class GestionarFacturaElectronicaServicio {

    private final GestorJDBC gestorJDBC;
    private final FacturaElectronicaDAOMySQL facturaElectronicaDAOMySQL;
    private final TipoFacturaElectronicaDAOMySQL tipoFacturaElectronicaDAOMySQL;
    private final EstadoFacturaElectronicaDAOMySQL estadoFacturaElectronicaDAOMySQL;

    public GestionarFacturaElectronicaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        facturaElectronicaDAOMySQL = new FacturaElectronicaDAOMySQL(gestorJDBC);
        tipoFacturaElectronicaDAOMySQL = new TipoFacturaElectronicaDAOMySQL(gestorJDBC);
        estadoFacturaElectronicaDAOMySQL = new EstadoFacturaElectronicaDAOMySQL(gestorJDBC);

    }

    public void llenarItemTipoFacturaElectronica(int i, JComboBox cboItemFacturaElectronica) throws Exception {
        gestorJDBC.abrirConexion();
        tipoFacturaElectronicaDAOMySQL.llenarItemTipoFacturaElectronica(i, cboItemFacturaElectronica);
        gestorJDBC.cerrarConexion();
    }

    public List<EstadoFacturaElectronica> llenarEstadoFacturaElectronica(int i) throws Exception {
        List<EstadoFacturaElectronica> lista;
        gestorJDBC.abrirConexion();
        lista = estadoFacturaElectronicaDAOMySQL.llenarItemEstadoFacturaElectronica(i);
        gestorJDBC.cerrarConexion();
        return lista;

    }

    public void mostrarFacturaElectronicaDeBaja(int numero, String fechaInicio, String fechaFin) {

    }

    public void mostrarFacturaSinEnviar(int numero, String fechaInicio, String fechaFin) {

    }

    public void mostrarFacturaElectronica(int numero, String fechaInicio, String fechaFin, JTable tablaFacturaElectronica) throws Exception {

        gestorJDBC.abrirConexion();
        facturaElectronicaDAOMySQL.mostrarFacturaElectronica(numero, fechaInicio, fechaFin, tablaFacturaElectronica);
        gestorJDBC.cerrarConexion();
    }

    public CorreoFactura buscarParaCorreo(String codigo) throws Exception {
        gestorJDBC.abrirConexion();
        CorreoFactura correoFactura = facturaElectronicaDAOMySQL.buscarParaCorreo(codigo);
        gestorJDBC.cerrarConexion();
        return correoFactura;
    }

}
