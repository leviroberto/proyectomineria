/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa4_persistencia.AdelantoDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarAdelantoServicio {

    private final GestorJDBC gestorJDBC;
    private final AdelantoDAOMySQL adelantoDAOMySQL;

    public GestionarAdelantoServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        adelantoDAOMySQL = new AdelantoDAOMySQL(gestorJDBC);
    }

    public int guardarAdelantoCliente(Adelanto adelanto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = adelantoDAOMySQL.agregarAdelantoCliente(adelanto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int guardarAdelantoProveedor(Adelanto adelanto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = adelantoDAOMySQL.agregarAdelantoProveedor(adelanto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarAdelantoCliente(Adelanto adelanto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = adelantoDAOMySQL.modificarAdelantoCliente(adelanto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarAdelantoProveedor(Adelanto adelanto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = adelantoDAOMySQL.modificarAdelantoProveedor(adelanto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarAdelantoCliente(ClienteEntrante a, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosCliente(a, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoReportePorCliente(String texto, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarAdelantoReportePorCliente(texto, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoProveedor(int estado, ProveedorServicio pro, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosProveedor(estado, pro, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoClientePagadoNoPagado(int estado, ClienteEntrante a, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosClientePagadoNoPagado(estado, a, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoProveedorPagadoNoPagado(int estado, ProveedorServicio pro, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosProveedorPagadoNoPagado(estado, pro, table);
        gestorJDBC.cerrarConexion();
    }

    public Adelanto buscarAdelantoServicioPorCodigoProveedor(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Adelanto adelanto = adelantoDAOMySQL.buscarPorCodigoProveedor(codigo);
        gestorJDBC.cerrarConexion();
        return adelanto;
    }

    public int eliminarAdelanto(Adelanto adelantoSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoDAOMySQL.eliminar(adelantoSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void calcularTotalNoPagadoSoles(JLabel lblTotalNoPagado, ProveedorServicio proveedorServicio) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.calcularTotalNoPagadoSolesProveedor(lblTotalNoPagado, proveedorServicio);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagadoDolares(JLabel lblTotalNoPagado, ProveedorServicio proveedorServicio) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.calcularTotalNoPagadoDolaresProveedor(lblTotalNoPagado, proveedorServicio);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagadoSolesCliente(JLabel lblTotalNoPagado, ClienteEntrante clienteEntrante) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.calcularTotalNoPagadoSolesCliente(lblTotalNoPagado, clienteEntrante);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagadoDolaresCliente(JLabel lblTotalNoPagado, ClienteEntrante clienteEntrante) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.calcularTotalNoPagadoDolaresCliente(lblTotalNoPagado, clienteEntrante);
        gestorJDBC.cerrarConexion();
    }

    public Adelanto buscarAdelantoServicioPorCliente(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Adelanto adelanto = adelantoDAOMySQL.buscarPorCodigoCliente(codigo);
        gestorJDBC.cerrarConexion();
        return adelanto;
    }

    public int modificarAdelantoNoPagado(int jLabel) throws Exception {
        int afectados = 0;
        gestorJDBC.abrirConexion();
        afectados = adelantoDAOMySQL.modificarAdelantoNoPagado(jLabel);
        gestorJDBC.cerrarConexion();
        return afectados;
    }
    
     public int modificarAdelantoProveedorNoPagado(int jLabel) throws Exception {
        int afectados = 0;
        gestorJDBC.abrirConexion();
        afectados = adelantoDAOMySQL.modificarAdelantoProveedorNoPagado(jLabel);
        gestorJDBC.cerrarConexion();
        return afectados;
    }

    public int actualizarValorizacionAdelanto(Adelanto adelanto) {
        int numeroAfectados = 0;
        try {
            gestorJDBC.abrirConexion();
            numeroAfectados = adelantoDAOMySQL.mosdificarValorizacionAdelanto(adelanto);
            gestorJDBC.cerrarConexion();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return numeroAfectados;
    }

    public void mostrarAdelantoPorClienteValorizacion(int i, JTable tableAdelanto, Valorizacion valorizacionSeleccionada) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarAdelantoPorClienteValorizacion(i, tableAdelanto, valorizacionSeleccionada);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarAdelantoCliente(int i, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosCliente(i, table, texto);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarAdelantoProveedorPagadoNoPagado(int estado, ProveedorServicio pro, JTable table, String texto) throws Exception {

        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosProveedorPagadoNoPagado(estado, pro, table, texto);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoClientePagadoNoPagado(int estado, ClienteEntrante clienteEntranteSeleccionado, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosClientePagadoNoPagado(estado, clienteEntranteSeleccionado, table, texto);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarTodosProveedor(int estado, JTable tableAdelanto, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoDAOMySQL.mostrarTodosProveedor(estado, tableAdelanto, texto);
        gestorJDBC.cerrarConexion();
    }

    public int eliminarProveedorAdelanto(Adelanto adelantoSeleccionado) throws Exception {

        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoDAOMySQL.eliminarProveedorAdelanto(adelantoSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

}
