/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Cambio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa4_persistencia.CambioDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.LiquidacionDAOMySQL;
import SistemaLara.capa4_persistencia.ValorizacionDAOMySQL;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author FiveCod Software
 */
public class GestionarValorizacionServicio {

    private final GestorJDBC gestorJDBC;
    private final ValorizacionDAOMySQL valorizacionDAOMySQL;
    private final LiquidacionDAOMySQL liquidacionDAOMySQL;
    private final CambioDAOMySQL cambioDAOMySQL;

    public GestionarValorizacionServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        valorizacionDAOMySQL = new ValorizacionDAOMySQL(gestorJDBC);
        liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);
        cambioDAOMySQL = new CambioDAOMySQL(gestorJDBC);
    }

    public int guardarValorizacion(Valorizacion valorizacion) throws Exception {
        int correlativo = 0;
        gestorJDBC.abrirConexion();
        correlativo = valorizacionDAOMySQL.agregar(valorizacion);
        gestorJDBC.cerrarConexion();
        return correlativo;
    }

    public int modificarValorizacion(Valorizacion valorizacion) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = valorizacionDAOMySQL.modificar(valorizacion);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarValorizacions(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public Valorizacion buscarValorizacionPorCodigo(int codigo) throws Exception {
        Valorizacion valorizacion = null;
        gestorJDBC.abrirConexion();
        valorizacion = valorizacionDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return valorizacion;

    }

    public int eliminarValorizacion(Valorizacion valorizacionSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = valorizacionDAOMySQL.eliminar(valorizacionSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void mostrarParaDetalleLiquidacion(JTable tableLiquidacion, int cliente, Date fecha) throws Exception {
        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarParaDetalleLiquidacion(tableLiquidacion, cliente, fecha);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarParaDetalleValorizacion(JTable tableLiquidacion, int cliente, Date fecha) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionDAOMySQL.mostrarParaDetalleValorizacion(tableLiquidacion, cliente, fecha);
        gestorJDBC.cerrarConexion();

    }

    public Cambio obtenerCambio() throws Exception {
        Cambio cambio = null;
        gestorJDBC.abrirConexion();
        cambio = cambioDAOMySQL.obtenerCambio();
        gestorJDBC.cerrarConexion();
        return cambio;
    }

    public Liquidacion buscarLiquidacionPorCodigo(int parseInt) throws Exception {
        Liquidacion liquidacion = null;
        gestorJDBC.abrirConexion();
        liquidacion = liquidacionDAOMySQL.buscarPorCodigo(parseInt);
        gestorJDBC.cerrarConexion();
        return liquidacion;
    }

    public int actualizarEstadoLiquidacion(Liquidacion liquidacion) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = liquidacionDAOMySQL.actualizarEstado(liquidacion);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public int obtenerUltimoCodigo() {

        int ultimoCodigo = 0;
        try {
            gestorJDBC.abrirConexion();
            ultimoCodigo = valorizacionDAOMySQL.obtenerUltimoCodigo();
            gestorJDBC.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return ultimoCodigo;
    }

    public String obtenerTipoCambioPorCliente(int codigo) throws Exception {
        String cambio = "";
        gestorJDBC.abrirConexion();
        cambio = valorizacionDAOMySQL.obtenerTipoCambioPorCliente(codigo);
        gestorJDBC.cerrarConexion();
        return cambio;
    }

    public void agregarTipoCambioPorcentajePorClienteEntrante(int codigo, String porcentaje) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionDAOMySQL.agregarTipoCambioPorcentajePorClienteEntrante(codigo, porcentaje);
        gestorJDBC.cerrarConexion();
    }

    public void actualizarCambioPorcentajeTipoCambio(int codigo, String porcentaje) throws Exception {
        gestorJDBC.abrirConexion();
        valorizacionDAOMySQL.actualizarTipoCambioPorcentajePorClienteEntrante(codigo, porcentaje);
        gestorJDBC.cerrarConexion();
    }

}
