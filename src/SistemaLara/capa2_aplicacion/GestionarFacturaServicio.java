/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa1_presentacion.util.GenerarFacturas;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.FacturaDAOMySQL;
import SistemaLara.capa4_persistencia.FacturaDetalleDAOMySQL;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import rojeru_san.componentes.RSDateChooser;


/**
 *
 * @author FiveCod Software
 */
public class GestionarFacturaServicio {

    private final GestorJDBC gestorJDBC;
    private final FacturaDAOMySQL facturaDAOMySQL;
    private final FacturaDetalleDAOMySQL facturaDetalleDAOMySQL;

    public GestionarFacturaServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        facturaDAOMySQL = new FacturaDAOMySQL(gestorJDBC);
        facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);

    }

    public int guardarFactura(Factura factura) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        int codigoFactura = facturaDAOMySQL.obtenerUltimoCodigo();

        factura.setCodigo(codigoFactura + 1);
        for (int i = 0; i < factura.getListaFacturaDetalle().size(); i++) {
            factura.getListaFacturaDetalle().get(i).setFactura(factura);
        }
        gestorJDBC.iniciarTransaccion();
        int correlativo = facturaDAOMySQL.obtenerCorrelativo(1);
        factura.setNroFactura(generarNROFactura(facturaDAOMySQL.obtenerSerie(1),correlativo));
        
        numerosAfecatdos = facturaDAOMySQL.agregar(factura);
        if (numerosAfecatdos == 1) {
            numerosAfecatdos = facturaDAOMySQL.modificarCorrelativo(1, correlativo + 1);
            if (numerosAfecatdos == 1) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de crear un nuevo factura numero " + factura.getNroFactura(), 7);
            }
        }
        gestorJDBC.terminarTransaccion();
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    private String generarNROFactura(String serie,int correlativo) {
       
        int tamañoSerie = String.valueOf(correlativo).length();//2
        int tamanñoNumeroSerie = 8;//8
        int ceros=tamanñoNumeroSerie-tamañoSerie;
        String contadorCeros="";
        for (int i = 0; i < ceros; i++) {
            contadorCeros=contadorCeros+"0";
        }
        return serie+"-"+contadorCeros+""+correlativo;
    }

    private String generarNumeroFactura(int correlativo, String serie) {
        String numeroFactura;
        GenerarFacturas generarFactura = new GenerarFacturas();
        generarFactura.generar(correlativo);
        numeroFactura = serie + "-" + generarFactura.correlativo();
        return numeroFactura;
    }

    public int modificarFactura(Factura factura) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = facturaDAOMySQL.modificar(factura);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarFacturas(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public Factura buscarFacturaPorCodigo(int codigo) throws Exception {
        Factura factura = null;
        gestorJDBC.abrirConexion();
        factura = facturaDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return factura;

    }

    public int eliminarFactura(Factura facturaSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = facturaDAOMySQL.eliminar(facturaSeleccionado);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

    public DefaultComboBoxModel llenarCBOFacturaDetalle(int i) throws SQLException, Exception {
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        modelo = facturaDetalleDAOMySQL.llenarCBOFacturaDetalle(i);
        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int verificarNumeroLote(int numero) throws SQLException, Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        numeroObtenido = facturaDAOMySQL.verificarNumeroLote(numero);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarFacturasPorNumeroFactura(int estado, String numero, JTable tableTransporte) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.mostrarFacturaPorNumeroFactura(estado, numero, tableTransporte);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.calcularTotalPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.calcularTotalNoPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public int eliminarFacturaElectronica(FacturaDetalle facturaDetalle) throws Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        numeroObtenido = facturaDetalleDAOMySQL.eliminar(facturaDetalle);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public int AgregarFacturaDetalle(FacturaDetalle facturaDetalle) throws Exception {
        int afectador = 0;
        gestorJDBC.abrirConexion();
        afectador = facturaDetalleDAOMySQL.agregar(facturaDetalle);
        gestorJDBC.cerrarConexion();
        return afectador;
    }

    public void mostrarFacturas(int i, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        facturaDAOMySQL.mostrarTodos(i, table, texto);
        gestorJDBC.cerrarConexion();

    }

}
