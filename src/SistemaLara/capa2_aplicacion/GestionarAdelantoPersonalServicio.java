/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.AdelantoPersonal;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.PagoPersonal;
import SistemaLara.capa4_persistencia.AdelantoPersonalDAOMySQL;
import SistemaLara.capa4_persistencia.ContratoDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author "FiveCod Software"
 */
public class GestionarAdelantoPersonalServicio {

    private final GestorJDBC gestorJDBC;
    private final AdelantoPersonalDAOMySQL adelantoPersonalDAOMySQL;
//    private final PagoDAOMySQL pagoDAOMySQL;
    ContratoDAOMySQL contratoDAOMySQL;

    public GestionarAdelantoPersonalServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        adelantoPersonalDAOMySQL = new AdelantoPersonalDAOMySQL(gestorJDBC);
        contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
    }

    public int guardarAdelantoPersonal(AdelantoPersonal adelantoPersonal) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = adelantoPersonalDAOMySQL.ingresar(adelantoPersonal);
        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int obtenerUltimoCodigo() throws Exception {
        gestorJDBC.abrirConexion();
        int ultimoCodigo = adelantoPersonalDAOMySQL.obtenerUltimoCodigo();
        gestorJDBC.cerrarConexion();
        return ultimoCodigo;
    }

    public int modificarAdelantoPersonal(AdelantoPersonal adelantoPersonal) throws Exception {
        gestorJDBC.abrirConexion();
        int ultimoCodigo = adelantoPersonalDAOMySQL.modificar(adelantoPersonal);
        gestorJDBC.cerrarConexion();
        return ultimoCodigo;
    }

    public AdelantoPersonal buscarAdelantoPersonalPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        AdelantoPersonal adelantoPersonal = adelantoPersonalDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return adelantoPersonal;

    }

    public int eliminarAdelantoPersonal(AdelantoPersonal adelantoPersonalSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoPersonalDAOMySQL.eliminar(adelantoPersonalSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public double buscarAdelantoPersonalTotalPorContrato(String codigo, int mes, String estado) throws Exception {
        gestorJDBC.abrirConexion();
        double montoTotal = adelantoPersonalDAOMySQL.buscarAdelantoPersonalTotalPorContrato(codigo, mes, estado);
        gestorJDBC.cerrarConexion();
        return montoTotal;
    }

    public ArrayList<AdelantoPersonal> mostrarAdelantoPersonalPorContrato(String codigo, int mes, int anio) throws Exception {
        ArrayList<AdelantoPersonal> listaAdelantoPersonals = null;
        gestorJDBC.abrirConexion();
        listaAdelantoPersonals = adelantoPersonalDAOMySQL.mostrarAdelantoPersonalsPorPersona(codigo, mes, anio);
        gestorJDBC.cerrarConexion();
        return listaAdelantoPersonals;
    }

    public Contrato buscarContratoPorCodigo(int codigo) throws Exception {
        Contrato contrato = null;
        gestorJDBC.abrirConexion();
        contrato = contratoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return contrato;
    }

    public int eliminarAdelantoPersonalPorCodigo(int codigo) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoPersonalDAOMySQL.eliminarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public PagoPersonal buscarContratoPagado(String codigo, int mes, int anio) throws Exception {
//        Pago pago = null;
//        gestorJDBC.abrirConexion();
//        pago = pagoDAOMySQL.buscarPagoPagado(codigo, mes, anio);
//        gestorJDBC.cerrarConexion();
//        return pago;
        return null;

    }

    public void mostrarAdelantos(JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantos(table);
        gestorJDBC.cerrarConexion();

    }

    public Double obtenerTotalSolesAdelantoPorPersonal(int codigo) throws Exception {
        Double totalSoles = 0.0;
        gestorJDBC.abrirConexion();
        totalSoles = adelantoPersonalDAOMySQL.obtenerTotalSolesAdelantoPorPersonal(codigo);
        gestorJDBC.cerrarConexion();
        return totalSoles;

    }

    public void mostrarAdelantoPersonalPorContrato(int codigho, JTable table, int estado) throws Exception {

        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantoPersonalPorContrato(codigho, table, estado);
        gestorJDBC.cerrarConexion();
    }

    public Contrato buscarContratoSeleccionado(int parseInt) throws ExcepcionSQLConsulta, Exception {
        Contrato contrato = null;
        gestorJDBC.abrirConexion();
        contrato = contratoDAOMySQL.buscarPorCodigo(parseInt);
        gestorJDBC.cerrarConexion();
        return contrato;
    }

    public int modificarAdelantoPersonalAPagado(int codigo) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoPersonalDAOMySQL.modificarAdelantoPersonalAPagado(codigo);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }
      public int modificarAdelantoPersonalPorPagar(int codigo) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = adelantoPersonalDAOMySQL.modificarAdelantoPersonalPorPagar(codigo);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarAdelantoPersonalPorPago(int codigo, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantoPersonalPorPago(codigo, table);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarAdelantosTodos(JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantosTodos(table);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarAdelantosGrupo(JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantosGrupo(table, texto);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantos(JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoPersonalDAOMySQL.mostrarAdelantos(table, texto);
        gestorJDBC.cerrarConexion();
    }

}
