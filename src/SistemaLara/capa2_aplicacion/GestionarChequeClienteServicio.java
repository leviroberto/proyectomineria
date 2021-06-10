/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa4_persistencia.ChequeClienteDAOMySQL;

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
public class GestionarChequeClienteServicio {

    private final GestorJDBC gestorJDBC;
    private final ChequeClienteDAOMySQL chequeClienteDAOMySQL;

    public GestionarChequeClienteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        chequeClienteDAOMySQL = new ChequeClienteDAOMySQL(gestorJDBC);
    }

    public DefaultTableModel listaTipoCheque(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo = tipoChequeDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarCheque(ChequeCliente cheque) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (cheque.esCorrectoFechaEmision()) {
            if (cheque.esCorrectoFechaPago()) {
                numerosAfecatdos = chequeClienteDAOMySQL.agregar(cheque);
            }
        }

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarCheque(ChequeCliente cheque) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (cheque.esCorrectoFechaEmision()) {
            if (cheque.esCorrectoFechaPago()) {
                numerosAfecatdos = chequeClienteDAOMySQL.modificar(cheque);
            }
        }

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int obtenerUltimoCodigo(int estado) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = chequeClienteDAOMySQL.obtenerUltimoCodigo(estado);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarCheque(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        chequeClienteDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public ChequeCliente buscarChequePorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        ChequeCliente cheque = chequeClienteDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return cheque;

    }

    public int eliminarCheque(ChequeCliente chequeSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = chequeClienteDAOMySQL.eliminar(chequeSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public ChequeCliente buscarChequePorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        ChequeCliente cheque = null;
        gestorJDBC.cerrarConexion();
        return cheque;
    }

    public void calcularMontoSoles(int i, FCMaterialTextField txtTotalSole) throws Exception {

        gestorJDBC.abrirConexion();
        chequeClienteDAOMySQL.calcularMontoSoles(i, txtTotalSole);
        gestorJDBC.cerrarConexion();
    }

    public void calcularMontoDolar(int i, FCMaterialTextField txtTotalDolar) throws Exception {
        gestorJDBC.abrirConexion();
        chequeClienteDAOMySQL.calcularMontoDolar(i, txtTotalDolar);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarCheque(int i, JTable tableCheque, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        chequeClienteDAOMySQL.mostrarTodos(i, tableCheque, texto);
        gestorJDBC.cerrarConexion();

    }

}
