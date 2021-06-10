/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.TareaDiaria;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa4_persistencia.TareaDiariaDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PersonalDAOMySQL;
import SistemaLara.capa6_exepciones.ExcepcionJDBC;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import rojerusan.RSComboMetro;
import rojerusan.RSTableMetro;

/**
 *
 * @author "FiveCod Software"
 */
public class GestionarTareaDiariaServicio {

    private final GestorJDBC gestorJDBC;
    private final TareaDiariaDAOMySQL tareaDiariaDAOMySQL;
    private final PersonalDAOMySQL personalDAOMySQL;

    public GestionarTareaDiariaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        tareaDiariaDAOMySQL = new TareaDiariaDAOMySQL(gestorJDBC);
        personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);

    }

    private Personal buscarPersonal() {
        return null;
    }

    public int guardarTareaDiaria(TareaDiaria tareaDiaria) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();

        numerosAfecatdos = tareaDiariaDAOMySQL.agregar(tareaDiaria);

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int modificarTareaDiaria(TareaDiaria tareaDiaria) throws Exception {

        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();

        numerosAfecatdos = tareaDiariaDAOMySQL.modificar(tareaDiaria);

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public TareaDiaria buscarTareaDiariaPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        TareaDiaria tareaDiaria = tareaDiariaDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return tareaDiaria;

    }

    public int eliminarTareaDiaria(int codigo) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = tareaDiariaDAOMySQL.eliminar(codigo);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarTareaDiaria(JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        tareaDiariaDAOMySQL.mostrar(table);
        gestorJDBC.cerrarConexion();

    }

    public void llenarItemEstado(RSComboMetro cboFiltarPorMeses) throws Exception {
        gestorJDBC.abrirConexion();
        tareaDiariaDAOMySQL.llenarItemEstado(cboFiltarPorMeses);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarTareaDiariasPorPersonal(JTable tableTareaDiaria, int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        tareaDiariaDAOMySQL.mostrarTareaDiariasPorPersonal(tableTareaDiaria, codigo);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarTareaDiariaPorHacer(JTable table, int estado) throws Exception {
        gestorJDBC.abrirConexion();
        tareaDiariaDAOMySQL.mostrarPorHacer(table, estado);
        gestorJDBC.cerrarConexion();

    }

    public int modificarTareaDiariaPorEstado(TareaDiaria tareaDiaria) throws Exception {

        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = tareaDiariaDAOMySQL.modificarTareaDiariaPorEstado(tareaDiaria);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public List<TareaDiaria> ObtenerListaTareasPorHacer() throws Exception {

        List<TareaDiaria> listaTareas = null;

        gestorJDBC.abrirConexion();
        listaTareas = tareaDiariaDAOMySQL.ObtenerListaTareasPorHacer();
        gestorJDBC.cerrarConexion();
        return listaTareas;
    }

    public int modificarTareaEstadoNotificacion(TareaDiaria tareaDiariaSeleccionada) {
        int numero = 0;
        try {
            gestorJDBC.abrirConexion();
            numero = tareaDiariaDAOMySQL.modificarTareaEstadoNotificacion(tareaDiariaSeleccionada);
            gestorJDBC.cerrarConexion();
        } catch (Exception e) {
        }
        return numero;
    }

    

}
