/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.PagoTransporte;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PagoTransporteDAOMySQL;
import SistemaLara.capa4_persistencia.TipoClienteDAOMySQL;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;

import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarPagoTransporteServicio {

    private final GestorJDBC gestorJDBC;
    private final PagoTransporteDAOMySQL pagoTransporteDAOMySQL;
    private final TipoClienteDAOMySQL tipoClienteDAOMySQL;

    public GestionarPagoTransporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        pagoTransporteDAOMySQL = new PagoTransporteDAOMySQL(gestorJDBC);
        tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);

    }

    public int guardarPagoTransporte(PagoTransporte pagoTransporte) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (pagoTransporte.esCorrectoFechaEmision()) {
            if (pagoTransporte.esCorrectoFechaPago()) {
                numerosAfecatdos = pagoTransporteDAOMySQL.agregar(pagoTransporte);
            }
        }

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarPagoTransporte(PagoTransporte pagoTransporte) throws Exception {
        int numerosAfecatdos = 5;
        gestorJDBC.abrirConexion();
        if (pagoTransporte.esCorrectoFechaEmision()) {
            if (pagoTransporte.esCorrectoFechaPago()) {
                numerosAfecatdos = pagoTransporteDAOMySQL.modificar(pagoTransporte);
            }
        }

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarPagoTransportes(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public PagoTransporte buscarPagoTransportePorCodigo(int codigo) throws Exception {
        PagoTransporte pagoTransporte = null;
        gestorJDBC.abrirConexion();
        pagoTransporte = pagoTransporteDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return pagoTransporte;

    }

    public int eliminarPagoTransporte(PagoTransporte pagoTransporteSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = pagoTransporteDAOMySQL.eliminar(pagoTransporteSeleccionado);
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
        numeroObtenido = pagoTransporteDAOMySQL.verificarNumeroLote(numero);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarPagoTransportesPorNumeroFactura(int estado, String numero, JTable tableTransporte) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.mostrarPagoTransportePorNumeroFactura(estado, numero, tableTransporte);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.calcularTotalPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.calcularTotalNoPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarPagoTransportes(int estado, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        pagoTransporteDAOMySQL.mostrarTodos(estado, table, texto);
        gestorJDBC.cerrarConexion();
    }
}
