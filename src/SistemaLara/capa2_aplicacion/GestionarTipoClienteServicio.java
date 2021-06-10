/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.TipoCliente;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.TipoClienteDAOMySQL;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarTipoClienteServicio {

    private final GestorJDBC gestorJDBC;
    private final TipoClienteDAOMySQL tipoClienteDAOMySQL;

    public GestionarTipoClienteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
    }

    public int guardarTipoCliente(TipoCliente tipoCliente) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoClienteDAOMySQL.agregar(tipoCliente);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarTipoCliente(TipoCliente tipoCliente) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoClienteDAOMySQL.modificar(tipoCliente);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public List<TipoCliente> mostrarTipoClientes(int estado,JTable table) throws Exception {
        List<TipoCliente> lista = null;
        gestorJDBC.abrirConexion();
        lista = tipoClienteDAOMySQL.mostrarTodos(estado,table);
        gestorJDBC.cerrarConexion();
        return lista;

    }

    public TipoCliente buscarTipoClientePorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        TipoCliente tipoCliente = tipoClienteDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return tipoCliente;

    }

    public int eliminarTipoCliente(TipoCliente tipoClienteSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = tipoClienteDAOMySQL.eliminar(tipoClienteSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarTipoClientes(int i, JTable tablaTipoCliente, String texto) throws Exception {

       
        gestorJDBC.abrirConexion();
         tipoClienteDAOMySQL.mostrarTodos(i,tablaTipoCliente,texto);
        gestorJDBC.cerrarConexion();
    }

}
