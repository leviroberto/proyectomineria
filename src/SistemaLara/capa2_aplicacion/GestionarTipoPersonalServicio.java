/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.TipoPersonal;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.TipoPersonalDAOMySQL;
import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarTipoPersonalServicio {

    private final GestorJDBC gestorJDBC;
    private final TipoPersonalDAOMySQL tipoPersonalDAOMySQL;

    public GestionarTipoPersonalServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        tipoPersonalDAOMySQL = new TipoPersonalDAOMySQL(gestorJDBC);
    }

    public int guardarTipoPersonal(TipoPersonal tipoPersonal) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoPersonalDAOMySQL.agregar(tipoPersonal);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarTipoPersonal(TipoPersonal tipoPersonal) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = tipoPersonalDAOMySQL.modificar(tipoPersonal);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

 

    public TipoPersonal buscarTipoPersonalPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        TipoPersonal tipoPersonal = tipoPersonalDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return tipoPersonal;

    }

    public int eliminarTipoPersonal(TipoPersonal tipoPersonalSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = tipoPersonalDAOMySQL.eliminar(tipoPersonalSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarTipoPersonal(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        tipoPersonalDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarTipoPersonal(int i, JTable tablaTipoPersonal, String texto) throws Exception {
 gestorJDBC.abrirConexion();
        tipoPersonalDAOMySQL.mostrarTodos(i, tablaTipoPersonal,texto);
        gestorJDBC.cerrarConexion();


    }

}
