/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import FiveCodMaterilalDesignComboBox.MaterialComboBox;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa4_persistencia.ClienteEntranteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.LiquidacionDAOMySQL;
import SistemaLara.capa4_persistencia.ProcedenciaDAOMySQL;
import SistemaLara.capa4_persistencia.TipoClienteDAOMySQL;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarLiquidacionServicio {

    private final GestorJDBC gestorJDBC;
    private final LiquidacionDAOMySQL liquidacionDAOMySQL;
    private final TipoClienteDAOMySQL tipoClienteDAOMySQL;
    private final ProcedenciaDAOMySQL procedenciaDAOMySQL;
    private final ClienteEntranteDAOMySQL clienteEntranteDAOMySQL;

    public GestionarLiquidacionServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
        procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
        liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);
        tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);

    }

    public int guardarLiquidacion(Liquidacion liquidacion) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = liquidacionDAOMySQL.agregar(liquidacion);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarLiquidacion(Liquidacion liquidacion) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = liquidacionDAOMySQL.modificar(liquidacion);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarLiquidacions(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarLiquidacionsGrafico(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarTodosGrafico(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public Liquidacion buscarLiquidacionPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Liquidacion liquidacion = liquidacionDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return liquidacion;

    }

    public int eliminarLiquidacion(Liquidacion liquidacionSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = liquidacionDAOMySQL.eliminar(liquidacionSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public void llenarCBOTipoCliente(int i, JComboBox combo) throws SQLException, Exception {

        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        tipoClienteDAOMySQL.llenarCBOTipoCliente(i, combo);
        gestorJDBC.cerrarConexion();

    }

    public int verificarNumeroLote(int numero) throws SQLException, Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        numeroObtenido = liquidacionDAOMySQL.verificarNumeroLote(numero);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarLiquidacionPoCodigoCliente(JTable tableValorizacion, String texto) throws Exception {

        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarLiquidacionPorDNICliente(tableValorizacion, texto);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarLiquidacionPorTipoCliente(int i, JTable tableValorizacion, int tipo) throws Exception {

        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarLiquidacionPorTipoCliente(i, tableValorizacion, tipo);
        gestorJDBC.cerrarConexion();
    }

    public Liquidacion buscarLiquidacionPorCodigoCliente(int codigo, String fecha) throws Exception {
        gestorJDBC.abrirConexion();
        Liquidacion liquidacion = liquidacionDAOMySQL.buscarPorCodigoCliente(codigo,fecha);
        gestorJDBC.cerrarConexion();
        return liquidacion;

    }

    public void mostrarLiquidacionPorLoteCliente(JTable tableValorizacion) throws Exception {

        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarLiquidacionLotesPorCliente(tableValorizacion);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarLiquidacions(int i, JTable tableLiquidacion, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        liquidacionDAOMySQL.mostrarTodos(i, tableLiquidacion, texto);
        gestorJDBC.cerrarConexion();
    }

    public void llenarCBOProcedencia(JComboBox<Procedencia> cboProcedencia) throws Exception {
        gestorJDBC.abrirConexion();
        procedenciaDAOMySQL.llenarCBOProcedencia(cboProcedencia);
        gestorJDBC.cerrarConexion();
    }

    public ClienteEntrante obtenerClientePorDefecto(int estado) throws Exception {

        ClienteEntrante clienteEntrante = null;
        gestorJDBC.abrirConexion();
        clienteEntrante = clienteEntranteDAOMySQL.obtenerClientePorDefectoParaLiquidacion(estado);
        gestorJDBC.cerrarConexion();
        return clienteEntrante;
    }

    public Liquidacion buscarLiquidacionPorLote(int numeroLoteSeleccionado) throws Exception {
        Liquidacion a = null;
        gestorJDBC.abrirConexion();
        a = liquidacionDAOMySQL.buscarLiquidacionPorLote(numeroLoteSeleccionado);
        gestorJDBC.cerrarConexion();
        return a;

    }

}
