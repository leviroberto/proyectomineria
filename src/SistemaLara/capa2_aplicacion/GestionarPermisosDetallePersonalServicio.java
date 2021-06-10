/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.PermisosDetallePersonal;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PermisoDetallePersonalDAOMySQL;
import SistemaLara.capa4_persistencia.PersonalDAOMySQL;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiveCod Software
 */
public class GestionarPermisosDetallePersonalServicio {

    private final GestorJDBC gestorJDBC;
    private final PermisoDetallePersonalDAOMySQL permisoDetallePersonalDAOMySQL;
    private final PersonalDAOMySQL personalDAOMySQL;

    public GestionarPermisosDetallePersonalServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        permisoDetallePersonalDAOMySQL = new PermisoDetallePersonalDAOMySQL(gestorJDBC);
        personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
    }

    public int mostrarPermisos(JTable table, int codigo) throws Exception {
        int contador = 0;
        gestorJDBC.abrirConexion();

        contador = permisoDetallePersonalDAOMySQL.mostrarTodos(table, codigo);
        gestorJDBC.cerrarConexion();
        return contador;
    }

    public void mostrarPersonal(int estado, JTable tablaPersonal) throws Exception {
        gestorJDBC.abrirConexion();
        personalDAOMySQL.mostrarTodos(estado, tablaPersonal);
        gestorJDBC.cerrarConexion();
    }

    public DefaultTableModel listaTipoDetallePermisosPersonal(int estado) throws Exception {
        DefaultTableModel modelo = null;
//        gestorJDBC.abrirConexion();
//        modelo = tipoPermisoDetallePersonalDAOMySQL.mostrarTodos(estado,null);
//        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int guardarDetallePermisosPersonal(PermisosDetallePersonal detallePermisosPersonal) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();

        numerosAfecatdos = permisoDetallePersonalDAOMySQL.agregar(detallePermisosPersonal);

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarDetallePermisosPersonal(List<PermisosDetallePersonal> lissDetallePermisosPersonals) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        for (PermisosDetallePersonal detallePermisosPersonal : lissDetallePermisosPersonals) {
            numerosAfecatdos = permisoDetallePersonalDAOMySQL.modificar(detallePermisosPersonal);
        }
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarDetallePermisosPersonal(int estado, JTable table) throws Exception {
//        gestorJDBC.abrirConexion();
//        permisoDetallePersonalDAOMySQL.mostrarTodos(estado, table);
//        gestorJDBC.cerrarConexion();

    }

    public PermisosDetallePersonal buscarDetallePermisosPersonalPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        PermisosDetallePersonal detallePermisosPersonal = permisoDetallePersonalDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return detallePermisosPersonal;

    }

    public int eliminarDetallePermisosPersonal(PermisosDetallePersonal detallePermisosPersonalSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = permisoDetallePersonalDAOMySQL.eliminar(detallePermisosPersonalSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public List<PermisosDetallePersonal> obtenerListaPermisosPorPersonal(int codigo) throws Exception {
        List<PermisosDetallePersonal> list;
        gestorJDBC.abrirConexion();
        list = permisoDetallePersonalDAOMySQL.buscarPermisosPorPersonal(codigo);
        gestorJDBC.cerrarConexion();
        return list;
    }

    public PermisosDetallePersonal buscarDetallePermisosPersonalPorDni(String dni) throws Exception {
        gestorJDBC.abrirConexion();
        PermisosDetallePersonal detallePermisosPersonal = null;
        gestorJDBC.cerrarConexion();
        return detallePermisosPersonal;
    }

}
