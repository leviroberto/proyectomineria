/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.TipoProveedor;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.TipoProveedorDAOMySQL;
import javax.swing.JTable;
import rojerusan.RSTableMetro;


/**
 *
 * @author FiveCod Software
 */
public class GestionarTipoProveedorServicio {

    private final GestorJDBC gestorJDBC;
    private final TipoProveedorDAOMySQL tipoProveedorDAOMySQL;

    public GestionarTipoProveedorServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        tipoProveedorDAOMySQL = new TipoProveedorDAOMySQL(gestorJDBC);

    }

    public int guardarTipoProveedor(TipoProveedor tipoProveedor) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoProveedorDAOMySQL.agregar(tipoProveedor);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarTipoProveedor(TipoProveedor tipoProveedor) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoProveedorDAOMySQL.modificar(tipoProveedor);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public TipoProveedor buscarTipoProveedorPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        TipoProveedor tipoProveedor = tipoProveedorDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return tipoProveedor;

    }

    public int eliminarTipoProveedor(TipoProveedor tipoProveedorSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = tipoProveedorDAOMySQL.eliminar(tipoProveedorSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarTipoProveedores(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        tipoProveedorDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarTipoProveedores(int i, JTable tablaTipoProveedor, String texto) throws Exception {
   gestorJDBC.abrirConexion();
        tipoProveedorDAOMySQL.mostrarTodos(i, tablaTipoProveedor,texto);
        gestorJDBC.cerrarConexion();
    }

}
