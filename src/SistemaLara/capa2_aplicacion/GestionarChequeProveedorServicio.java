/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ChequeProveedor;

import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ChequeProveedorDAOMySQL;

import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSComboMetro;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarChequeProveedorServicio {

    private final GestorJDBC gestorJDBC;
    private final ChequeProveedorDAOMySQL chequeDAOMySQL;

    public GestionarChequeProveedorServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        chequeDAOMySQL = new ChequeProveedorDAOMySQL(gestorJDBC);
    }

    public DefaultTableModel listaTipoCheque(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo = tipoChequeDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarCheque(ChequeProveedor cheque) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (cheque.esCorrectoFechaEmision()) {
            if (cheque.esCorrectoFechaPago()) {
                numerosAfecatdos = chequeDAOMySQL.agregar(cheque);
            }
        }

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarCheque(ChequeProveedor cheque) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (cheque.esCorrectoFechaEmision()) {
            if (cheque.esCorrectoFechaPago()) {
                numerosAfecatdos = chequeDAOMySQL.modificar(cheque);
            }
        }

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int obtenerUltimoCodigo(int estado) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = chequeDAOMySQL.obtenerUltimoCodigo(estado);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarCheque(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        chequeDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public ChequeProveedor buscarChequePorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        ChequeProveedor cheque = chequeDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return cheque;

    }

    public int eliminarCheque(ChequeProveedor chequeSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = chequeDAOMySQL.eliminar(chequeSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public ChequeProveedor buscarChequePorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        ChequeProveedor cheque = null;
        gestorJDBC.cerrarConexion();
        return cheque;
    }

    public void calcularMontoSoles(int i, FCMaterialTextField txtTotalSole) throws Exception {

        gestorJDBC.abrirConexion();
        chequeDAOMySQL.calcularMontoSoles(i, txtTotalSole);
        gestorJDBC.cerrarConexion();
    }

    public void calcularMontoDolar(int i, FCMaterialTextField txtTotalDolar) throws Exception {
        gestorJDBC.abrirConexion();
        chequeDAOMySQL.calcularMontoDolar(i, txtTotalDolar);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarCheque(int i, JTable tableCheque, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        chequeDAOMySQL.mostrarTodos(i, tableCheque, texto);
        gestorJDBC.cerrarConexion();

    }

}
