/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import FiveCodMaterilalDesignComboBox.MaterialComboBox;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.Reintegro;
import SistemaLara.capa3_dominio.TipoFacturaElectronica;
import SistemaLara.capa3_dominio.TipoNotaCD;
import SistemaLara.capa3_dominio.TipoNotaDebito;
import SistemaLara.capa4_persistencia.EstadoFacturaElectronicaDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDAOMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDAOMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDAOMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDAOMySQL;
import SistemaLara.capa4_persistencia.ReintegroDAOMySQL;
import SistemaLara.capa4_persistencia.TipoNotaDebitoDAOMySQL;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class GestionarNotaDebitoServicio {

    private final GestorJDBC gestorJDBC;
    private final NotaDebitoDAOMySQL notaDebitoDAOMySQL;
    private final NotaDebitoDAOMySQL notaDebitoDetalleDAOMySQL;
    private final ReintegroDAOMySQL ReintegroDAOMySQL;
    private final TipoNotaDebitoDAOMySQL tipoNotaDebitoDAOMySQL;

    private final EstadoFacturaElectronicaDAOMySQL estadoFacturaElectronicaDAOMySQL;

    public GestionarNotaDebitoServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        notaDebitoDAOMySQL = new NotaDebitoDAOMySQL(gestorJDBC);
        notaDebitoDetalleDAOMySQL = new NotaDebitoDAOMySQL(gestorJDBC);
        ReintegroDAOMySQL = new ReintegroDAOMySQL(gestorJDBC);
        estadoFacturaElectronicaDAOMySQL = new EstadoFacturaElectronicaDAOMySQL(gestorJDBC);
        tipoNotaDebitoDAOMySQL = new TipoNotaDebitoDAOMySQL(gestorJDBC);

    }

    public int guardarNotaDebito(NotaDebito notaDebito) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        int codigoNotaDebito = notaDebitoDAOMySQL.obtenerUltimoCodigo();

        notaDebito.setCodigo(codigoNotaDebito + 1);
        for (int i = 0; i < notaDebito.getListaNotaDebitoDetalle().size(); i++) {
            notaDebito.getListaNotaDebitoDetalle().get(i).setNotaDebito(notaDebito);
        }
        gestorJDBC.iniciarTransaccion();
        int correlativo = notaDebitoDAOMySQL.obtenerCorrelativo(1);
        notaDebito.setNumeroNotaDebito(generarNRONotaDebito(notaDebitoDAOMySQL.obtenerSerieNotaDebito(1), correlativo));

        numerosAfecatdos = notaDebitoDAOMySQL.agregar(notaDebito);
        if (numerosAfecatdos == 1) {
            numerosAfecatdos = notaDebitoDAOMySQL.modificarCorrelativoNotaDebito(1, correlativo + 1);
            if (numerosAfecatdos == 1) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de crear un nuevo Nota Debito numero " + notaDebito.getNumeroNotaDebito(), 7);
            }
        }
        gestorJDBC.terminarTransaccion();
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    private String generarNRONotaDebito(String serie, int correlativo) {

        int tamañoSerie = String.valueOf(correlativo).length();//2
        int tamanñoNumeroSerie = 8;//8
        int ceros = tamanñoNumeroSerie - tamañoSerie;
        String contadorCeros = "";
        for (int i = 0; i < ceros; i++) {
            contadorCeros = contadorCeros + "0";
        }
        return serie + "-" + contadorCeros + "" + correlativo;
    }

    private String generarNumeroNotaDEbito(int correlativo, String serie) {
        String numeroNotaDebito = null;
//        GenerarNotaDebitos generarNotaDebito = new GenerarNotaDebitos();
//        generarNotaDebito.generar(correlativo);
//        numeroNotaDebito = serie + "-" + generarNotaDebito.correlativo();
        return numeroNotaDebito;
    }

    public int modificarNotaDebito(NotaDebito factura) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = notaDebitoDAOMySQL.modificar(factura);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarNotaDebitos(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        notaDebitoDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public NotaDebito buscarNotaDebitoPorCodigo(int codigo) throws Exception {
        NotaDebito factura = null;
        gestorJDBC.abrirConexion();
        factura = notaDebitoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return factura;

    }

    public DefaultComboBoxModel llenarCBONotaDebito(int i) throws SQLException, Exception {
        DefaultComboBoxModel modelo = null;
        gestorJDBC.abrirConexion();
        // modelo = notaDebitoDetalleDAOMySQL.llenarCBONotaDebito(i);
        gestorJDBC.cerrarConexion();
        return modelo;
    }

    public int verificarNumeroLote(int numero) throws SQLException, Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        //  numeroObtenido = notaDebitoDAOMySQL.verificarNumeroLote(numero);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        notaDebitoDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarNotaDebitosPorNumeroNotaDebito(int estado, String numero, JTable tableTransporte) throws Exception {
        gestorJDBC.abrirConexion();
        //   facturaDAOMySQL.mostrarNotaDebitoPorNumeroNotaDebito(estado, numero, tableTransporte);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        //  facturaDAOMySQL.calcularTotalPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        //  facturaDAOMySQL.calcularTotalNoPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public int eliminarNotaDebitoElectronica(NotaDebito facturaDetalle) throws Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        // numeroObtenido = facturaDetalleDAOMySQL.eliminar(facturaDetalle);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public int AgregarNotaDebito(NotaDebito facturaDetalle) throws Exception {
        int afectador = 0;
        gestorJDBC.abrirConexion();
        //  afectador = facturaDetalleDAOMySQL.agregar(facturaDetalle);
        gestorJDBC.cerrarConexion();
        return afectador;
    }

    public void mostrarNotaDebitos(int i, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        notaDebitoDAOMySQL.mostrarTodos(i, table, texto);
        gestorJDBC.cerrarConexion();

    }

    public Reintegro buscarReintegroPorLote(int numeroLoteSeleccionado) throws Exception {
        Reintegro a = null;
        gestorJDBC.abrirConexion();
        a = ReintegroDAOMySQL.buscarReintegroPorLote(numeroLoteSeleccionado);
        gestorJDBC.cerrarConexion();
        return a;

    }

    public void mostrarNotaDebitpElectronico(int numero, String fechaInicio, String fechaFin, RSTableMetro tablaFacturaElectronica) throws Exception {

        gestorJDBC.abrirConexion();
        ReintegroDAOMySQL.mostrarNotaDebitpElectronico(numero, fechaInicio, fechaFin, tablaFacturaElectronica);
        gestorJDBC.cerrarConexion();
    }

    public List<EstadoFacturaElectronica> llenarEstadoFacturaElectronica(int i) throws Exception {
        List<EstadoFacturaElectronica> lista;
        gestorJDBC.abrirConexion();
        lista = estadoFacturaElectronicaDAOMySQL.llenarItemEstadoFacturaElectronica(i);
        gestorJDBC.cerrarConexion();
        return lista;

    }

    public void llenarComboTipoNotaDebito(MaterialComboBox<TipoNotaDebito> cboTipoNotaDebito) throws Exception {
        gestorJDBC.abrirConexion();
        tipoNotaDebitoDAOMySQL.llenarCBOTipoProveedor(cboTipoNotaDebito);
        gestorJDBC.cerrarConexion();
    }

    public CorreoFactura buscarParaCorreo(String codigo) throws Exception {
        CorreoFactura correoFactura = null;
        gestorJDBC.abrirConexion();
        correoFactura = notaDebitoDAOMySQL.buscarParaCorreo(codigo);
        gestorJDBC.cerrarConexion();
        return correoFactura;
    }
}
