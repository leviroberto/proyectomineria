/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Empresa;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.TipoPersonal;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PersonalDAOMySQL;
import SistemaLara.capa4_persistencia.TipoPersonalDAOMySQL;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiveCod Software
 */
public class GestionarPersonalServicio {

    private final GestorJDBC gestorJDBC;
    private final PersonalDAOMySQL personalDAOMySQL;
    private final TipoPersonalDAOMySQL tipoPersonalDAOMySQL;

    public GestionarPersonalServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
        tipoPersonalDAOMySQL = new TipoPersonalDAOMySQL(gestorJDBC);
    }

    public DefaultTableModel listaTipoPersonal(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo = tipoPersonalDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarPersonal(Personal trabajador) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = personalDAOMySQL.ingresar(trabajador);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarPersonal(Personal trabajador) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = personalDAOMySQL.modificar(trabajador);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarPersonal(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        personalDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public Personal buscarPersonalPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Personal trabajador = personalDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return trabajador;

    }

    public int eliminarPersonal(Personal trabajadorSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = personalDAOMySQL.eliminar(trabajadorSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public DefaultTableModel buscarPersonalPorNombre(String nombre) throws Exception {
        DefaultTableModel listaPersonal = null;
        gestorJDBC.abrirConexion();
        listaPersonal = personalDAOMySQL.buscarPorNombre(nombre);
        gestorJDBC.cerrarConexion();
        return listaPersonal;
    }

    public TipoPersonal buscarPersonalPorNombreEspecifico(String nombre) throws Exception {
        gestorJDBC.abrirConexion();
        TipoPersonal listaPersonal = null;
        gestorJDBC.cerrarConexion();
        return listaPersonal;
    }

    public Personal verificarUsuario(String dni, String password, Empresa empresa) throws Exception {
        Personal trabajador;
        gestorJDBC.abrirConexion();
        trabajador = personalDAOMySQL.verificarUsuario(dni, password, empresa);
        gestorJDBC.cerrarConexion();
        return trabajador;
    }

    public Personal verificarRecuperacion(String dni) throws Exception {
        Personal trabajador;
        gestorJDBC.abrirConexion();
        trabajador = personalDAOMySQL.verificarRecuperacion(dni);
        gestorJDBC.cerrarConexion();
        return trabajador;
    }

    public Personal buscarPersonalPorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        Personal trabajador = null;
        gestorJDBC.cerrarConexion();
        return trabajador;
    }

    public DefaultComboBoxModel llenarCBOTipoPersonal(int i) throws SQLException, ExcepcionSQLConsulta, Exception {
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        modelo = tipoPersonalDAOMySQL.llenarCBOTipoPersonal(i);
        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public void mostrarPersonal(int i, JTable tablaPersonal, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        personalDAOMySQL.mostrarTodos(i, tablaPersonal, texto);
        gestorJDBC.cerrarConexion();
    }

    public List<Personal> ObtenerPersonalLista() throws Exception {
        List<Personal> listaPersonal = null;
        gestorJDBC.abrirConexion();
        listaPersonal = personalDAOMySQL.ObtenerPersonalLista();
        gestorJDBC.cerrarConexion();
        return listaPersonal;
    }

    public int modificarPersonalEstadoNotificacion(Personal personal) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = personalDAOMySQL.modificarPersonalEstadoNotificacion(personal);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;

    }
}
