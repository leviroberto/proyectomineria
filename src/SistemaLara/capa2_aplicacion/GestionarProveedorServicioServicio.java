/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ProveedorServicioDAOMySQL;
import SistemaLara.capa4_persistencia.TipoProveedorDAOMySQL;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarProveedorServicioServicio {

    private final GestorJDBC gestorJDBC;
    private final ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
    private final TipoProveedorDAOMySQL tipoProveedorDAOMySQL;

    public GestionarProveedorServicioServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
        tipoProveedorDAOMySQL = new TipoProveedorDAOMySQL(gestorJDBC);

    }

    public int guardarProveedorServicio(ProveedorServicio proveedorServicio) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = proveedorServicioDAOMySQL.agregar(proveedorServicio);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarProveedorServicio(ProveedorServicio proveedorServicio) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = proveedorServicioDAOMySQL.modificar(proveedorServicio);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarProveedorServicios(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        proveedorServicioDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

      public void mostrarPorFacturacion(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        proveedorServicioDAOMySQL.mostrarPorFacturacion(estado, table);
        gestorJDBC.cerrarConexion();

    }
    
    
    
    public ProveedorServicio buscarProveedorServicioPorCodigo(int codigo) throws Exception {
        ProveedorServicio proveedorServicio = null;
        gestorJDBC.abrirConexion();
        proveedorServicio = proveedorServicioDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return proveedorServicio;
    }

    public int eliminarProveedorServicio(ProveedorServicio proveedorServicioSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = proveedorServicioDAOMySQL.eliminar(proveedorServicioSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public DefaultComboBoxModel llenarCBOTipoProveedor(int i, JComboBox combo) throws SQLException, Exception {
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        modelo = tipoProveedorDAOMySQL.llenarCBOTipoProveedor(i, combo);
        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public void mostrarProveedorServicios(int i, JTable tableProveedorServicio, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        proveedorServicioDAOMySQL.mostrarTodos(i, tableProveedorServicio, texto);
        gestorJDBC.cerrarConexion();

    }
    
       public void mostrarProveedorLikePorFacturacion(int i, JTable tableProveedorServicio, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        proveedorServicioDAOMySQL.mostrarTodosLikePorFacturacion(i, tableProveedorServicio, texto);
        gestorJDBC.cerrarConexion();

    }

}
