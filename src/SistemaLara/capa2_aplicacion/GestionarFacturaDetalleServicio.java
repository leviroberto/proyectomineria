/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.FacturaDetalle;

import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.FacturaDetalleDAOMySQL;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author FiveCod Software
 */
public class GestionarFacturaDetalleServicio {

    private final GestorJDBC gestorJDBC;
    private final FacturaDetalleDAOMySQL facturaDetalleDAOMySQL;

    public GestionarFacturaDetalleServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);
    }

    public DefaultTableModel listaTipoFacturaDetalle(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo = tipoFacturaDetalleDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarFacturaDetalle(FacturaDetalle facturaDetalle) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();

        numerosAfecatdos = facturaDetalleDAOMySQL.agregar(facturaDetalle);

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarFacturaDetalle(FacturaDetalle facturaDetalle) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = facturaDetalleDAOMySQL.modificar(facturaDetalle);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarFacturaDetalle(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDetalleDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public FacturaDetalle buscarFacturaDetallePorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        FacturaDetalle facturaDetalle = facturaDetalleDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return facturaDetalle;

    }

    public int eliminarFacturaDetalle(FacturaDetalle facturaDetalleSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = facturaDetalleDAOMySQL.eliminar(facturaDetalleSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public FacturaDetalle buscarFacturaDetallePorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        FacturaDetalle facturaDetalle = null;
        gestorJDBC.cerrarConexion();
        return facturaDetalle;
    }

}
