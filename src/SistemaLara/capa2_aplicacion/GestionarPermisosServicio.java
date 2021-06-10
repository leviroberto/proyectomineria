/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Permisos;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PermisoDetallePersonalDAOMySQL;
import SistemaLara.capa4_persistencia.PermisosDAOMySQL;
import SistemaLara.capa4_persistencia.PersonalDAOMySQL;
import javax.swing.JTable;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarPermisosServicio {

    private final GestorJDBC gestorJDBC;
    private final PermisoDetallePersonalDAOMySQL permisoDetallePersonalDAOMySQL;
    private final PersonalDAOMySQL personalDAOMySQL;

    public GestionarPermisosServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        permisoDetallePersonalDAOMySQL = new PermisoDetallePersonalDAOMySQL(gestorJDBC);
        personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
    }

//    public int guardarPermisos(Permisos concepto) throws Exception {
//        int numerosAfecatdos = 0;
//        gestorJDBC.abrirConexion();
//        numerosAfecatdos = permisoDetallePersonalDAOMySQL.agregar(concepto);
//        gestorJDBC.cerrarConexion();
//        return numerosAfecatdos;
//    }
//
//    public int modificarPermisos(Permisos concepto) throws Exception {
//        int numerosAfecatdos = 0;
//        gestorJDBC.abrirConexion();
//        numerosAfecatdos = permisoDetallePersonalDAOMySQL.modificar(concepto);
//        gestorJDBC.cerrarConexion();
//        return numerosAfecatdos;
//    }
//
// 
//
//    public Permisos buscarPermisosPorCodigo(int codigo) throws Exception {
//        gestorJDBC.abrirConexion();
//        Permisos concepto = permisoDetallePersonalDAOMySQL.buscarPorCodigo(codigo);
//        gestorJDBC.cerrarConexion();
//        return concepto;
//
//    }
//
//    public int eliminarPermisos(Permisos conceptoSeleccionado) throws Exception {
//        int numeroAfectados = 0;
//        gestorJDBC.abrirConexion();
//        numeroAfectados = permisoDetallePersonalDAOMySQL.eliminar(conceptoSeleccionado);
//        gestorJDBC.cerrarConexion();
//        return numeroAfectados;
//    }
    public void mostrarPermisos(JTable table, int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        permisoDetallePersonalDAOMySQL.mostrarTodos(table, codigo);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarPersonal(int estado, JTable tablaPersonal) throws Exception {
        gestorJDBC.abrirConexion();
        personalDAOMySQL.mostrarTodos(estado, tablaPersonal);
        gestorJDBC.cerrarConexion();
    }

}
