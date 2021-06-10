/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Concepto;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.ConceptoDAOMySQL;
import javax.swing.JTable;


/**
 *
 * @author FiveCod Software
 */
public class GestionarConceptoServicio {

    private final GestorJDBC gestorJDBC;
    private final ConceptoDAOMySQL conceptoDAOMySQL;

    public GestionarConceptoServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        conceptoDAOMySQL = new ConceptoDAOMySQL(gestorJDBC);
    }

    public int guardarConcepto(Concepto concepto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = conceptoDAOMySQL.agregar(concepto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarConcepto(Concepto concepto) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = conceptoDAOMySQL.modificar(concepto);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

 

    public Concepto buscarConceptoPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Concepto concepto = conceptoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return concepto;

    }

    public int eliminarConcepto(Concepto conceptoSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = conceptoDAOMySQL.eliminar(conceptoSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarConcepto(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        conceptoDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();
    }

}
