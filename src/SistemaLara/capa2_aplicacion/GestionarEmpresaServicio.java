/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Empresa;
import SistemaLara.capa4_persistencia.EmpresaDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import java.util.List;

/**
 *
 * @author FiveCod Software
 */
public class GestionarEmpresaServicio {

    private final GestorJDBC gestorJDBC;
    private final EmpresaDAOMySQL empresaDAOMySQL;

    public GestionarEmpresaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        empresaDAOMySQL = new EmpresaDAOMySQL(gestorJDBC);
    }

    public int guardarEmpresa(Empresa empresa) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = empresaDAOMySQL.agregar(empresa);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarEmpresa(Empresa empresa) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = empresaDAOMySQL.modificar(empresa);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public List<Empresa> mostrarEmpresa(int estado) throws Exception {
        List<Empresa> lista = null;
        gestorJDBC.abrirConexion();
        lista = empresaDAOMySQL.mostrarTodos(estado);
        gestorJDBC.cerrarConexion();
        return lista;

    }

    public Empresa buscarEmpresaPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Empresa empresa = empresaDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return empresa;

    }

    public int eliminarEmpresa(Empresa empresaSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = empresaDAOMySQL.eliminar(empresaSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

}
