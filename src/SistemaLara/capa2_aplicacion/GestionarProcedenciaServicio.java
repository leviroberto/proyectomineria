/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ProcedenciaDAOMySQL;

import javax.swing.JTable;
import rojerusan.RSTableMetro;


/**
 *
 * @author FiveCod Software
 */
public class GestionarProcedenciaServicio {

    private final GestorJDBC gestorJDBC;
    private final ProcedenciaDAOMySQL procedenciaDAOMySQL;

    public GestionarProcedenciaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
    }

    public int guardarProcedencia(Procedencia procedencia) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = procedenciaDAOMySQL.agregar(procedencia);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarProcedencia(Procedencia procedencia) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = procedenciaDAOMySQL.modificar(procedencia);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

  

    public Procedencia buscarProcedenciaPorCodigo(int codigo) throws Exception {
        Procedencia procedencia;
        gestorJDBC.abrirConexion();
         procedencia = procedenciaDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return procedencia;

    }

    public int eliminarProcedencia(Procedencia procedenciaSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = procedenciaDAOMySQL.eliminar(procedenciaSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarProcedencia(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        procedenciaDAOMySQL.mostrar(estado,table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarProcedencia(int i, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        procedenciaDAOMySQL.mostrar(i,table,texto);
        gestorJDBC.cerrarConexion();
    }

}
