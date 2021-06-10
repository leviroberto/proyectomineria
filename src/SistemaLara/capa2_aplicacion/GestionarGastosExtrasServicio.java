/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.GastosExtras;
import SistemaLara.capa4_persistencia.ConceptoDAOMySQL;
import SistemaLara.capa4_persistencia.GastosExtrasDAOMySQL;

import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarGastosExtrasServicio {

    private final GestorJDBC gestorJDBC;
    private final GastosExtrasDAOMySQL gastosExtrasDAOMySQL;
    private final ConceptoDAOMySQL conceptoExtrasDAOMySQL;

    public GestionarGastosExtrasServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        gastosExtrasDAOMySQL = new GastosExtrasDAOMySQL(gestorJDBC);
        conceptoExtrasDAOMySQL = new ConceptoDAOMySQL(gestorJDBC);
    }

    public DefaultTableModel listaConceptoExtras(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo =conceptoExtrasDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarGastosExtras(GastosExtras gastosExtras) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();

        numerosAfecatdos = gastosExtrasDAOMySQL.ingresar(gastosExtras);

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarGastosExtras(GastosExtras gastosExtras) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = gastosExtrasDAOMySQL.modificar(gastosExtras);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarGastosExtras(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        gastosExtrasDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public GastosExtras buscarGastosExtrasPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        GastosExtras gastosExtras = gastosExtrasDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return gastosExtras;

    }

    public int eliminarGastosExtras(GastosExtras gastosExtrasSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = gastosExtrasDAOMySQL.eliminar(gastosExtrasSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public DefaultTableModel buscarGastosExtrasPorNombre(String nombre) throws Exception {
        DefaultTableModel listaGastosExtras = null;
        gestorJDBC.abrirConexion();
        listaGastosExtras = gastosExtrasDAOMySQL.buscarPorNombre(nombre);
        gestorJDBC.cerrarConexion();
        return listaGastosExtras;
    }

    public GastosExtras buscarGastosExtrasPorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        GastosExtras gastosExtras = null;
        gestorJDBC.cerrarConexion();
        return gastosExtras;
    }

    public void llenarCBOConceptoExtras(int i, JComboBox combo) throws SQLException, ExcepcionSQLConsulta, Exception {

        gestorJDBC.abrirConexion();
        conceptoExtrasDAOMySQL.llenarCBOConcepto(i, combo);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarGastosExtras(int estado, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        gastosExtrasDAOMySQL.mostrarTodos(estado, table, texto);
        gestorJDBC.cerrarConexion();
    }
}
