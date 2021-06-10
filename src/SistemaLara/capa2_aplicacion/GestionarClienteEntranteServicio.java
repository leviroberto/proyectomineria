/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ClienteEntranteDAOMySQL;
import SistemaLara.capa4_persistencia.TipoClienteDAOMySQL;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarClienteEntranteServicio {

    private final GestorJDBC gestorJDBC;
    private final ClienteEntranteDAOMySQL clienteEntranteDAOMySQL;
    private final TipoClienteDAOMySQL tipoClienteDAOMySQL;

    public GestionarClienteEntranteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
        tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);

    }

    public int guardarClienteEntrante(ClienteEntrante clienteEntrante) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = clienteEntranteDAOMySQL.agregar(clienteEntrante);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarClienteEntrante(ClienteEntrante clienteEntrante) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = clienteEntranteDAOMySQL.modificar(clienteEntrante);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarClienteEntrantes(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        clienteEntranteDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public ClienteEntrante buscarClienteEntrantePorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        ClienteEntrante clienteEntrante = clienteEntranteDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return clienteEntrante;

    }

    public int eliminarClienteEntrante(ClienteEntrante clienteEntranteSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = clienteEntranteDAOMySQL.eliminar(clienteEntranteSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void llenarCBOTipoCliente(int i, JComboBox combo) throws SQLException, Exception {

        gestorJDBC.abrirConexion();
        tipoClienteDAOMySQL.llenarCBOTipoCliente(i, combo);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarClienteEntrantes(int i, JTable tableClienteEntrante, String texto) throws Exception {

        gestorJDBC.abrirConexion();
        clienteEntranteDAOMySQL.mostrarTodos(i, tableClienteEntrante, texto);
        gestorJDBC.cerrarConexion();

    }

}
