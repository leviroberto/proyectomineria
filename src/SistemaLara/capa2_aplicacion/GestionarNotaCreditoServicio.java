package SistemaLara.capa2_aplicacion;

import FiveCodMaterilalDesignComboBox.MaterialComboBox;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa1_presentacion.util.GenerarFacturas;
import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.NotaCredito;
import SistemaLara.capa3_dominio.NotaCreditoDetalle;
import SistemaLara.capa3_dominio.TipoNotaCD;
import SistemaLara.capa4_persistencia.EstadoFacturaElectronicaDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.FacturaDAOMySQL;
import SistemaLara.capa4_persistencia.FacturaDetalleDAOMySQL;
import SistemaLara.capa4_persistencia.NotaCreditoDAOMySQL;
import SistemaLara.capa4_persistencia.NotaCreditoDetalleDAOMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDAOMySQL;
import SistemaLara.capa4_persistencia.NotaDebitoDetalleDAOMySQL;
import SistemaLara.capa4_persistencia.TipoNotaCreditoDAOMySQL;
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
public class GestionarNotaCreditoServicio {

    private final GestorJDBC gestorJDBC;
    private final NotaCreditoDAOMySQL notaCreditoDAOMySQL;
    private final NotaCreditoDetalleDAOMySQL notaCreditoDetalleDAOMySQL;
    private final EstadoFacturaElectronicaDAOMySQL estadoFacturaElectronicaDAOMySQL;
    private final TipoNotaCreditoDAOMySQL tipoNotaCDDAOMySQL;

    public GestionarNotaCreditoServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        notaCreditoDAOMySQL = new NotaCreditoDAOMySQL(gestorJDBC);
        notaCreditoDetalleDAOMySQL = new NotaCreditoDetalleDAOMySQL(gestorJDBC);
        estadoFacturaElectronicaDAOMySQL = new EstadoFacturaElectronicaDAOMySQL(gestorJDBC);
        tipoNotaCDDAOMySQL = new TipoNotaCreditoDAOMySQL(gestorJDBC);
    }

    public int guardarNotaCredito(NotaCredito notaCredito) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        int codigoFactura = notaCreditoDAOMySQL.obtenerUltimoCodigo();
        notaCredito.setCodigo(codigoFactura + 1);
        for (int i = 0; i < notaCredito.getListaNotaCreditoDetalle().size(); i++) {
            notaCredito.getListaNotaCreditoDetalle().get(i).setNotaCredito(notaCredito);
        }
        gestorJDBC.iniciarTransaccion();
        int correlativo = notaCreditoDAOMySQL.obtenerCorrelativo(1);

        notaCredito.setNumeroNotaCreadito(generarNRONotaCredito(notaCreditoDAOMySQL.obtenerSerie(1), correlativo));

        numerosAfecatdos = notaCreditoDAOMySQL.agregar(notaCredito);
        if (numerosAfecatdos == 1) {
            numerosAfecatdos = notaCreditoDAOMySQL.modificarCorrelativo(1, correlativo + 1);
            if (numerosAfecatdos == 1) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de crear un nuevo Nota Credito Numero " + notaCredito.getNumeroNotaCreadito(), 7);
            }
        }
        gestorJDBC.terminarTransaccion();
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    private String generarNRONotaCredito(String serie, int correlativo) {

        int tamañoSerie = String.valueOf(correlativo).length();//2
        int tamanñoNumeroSerie = 8;//8
        int ceros = tamanñoNumeroSerie - tamañoSerie;
        String contadorCeros = "";
        for (int i = 0; i < ceros; i++) {
            contadorCeros = contadorCeros + "0";
        }
        return serie + "-" + contadorCeros + "" + correlativo;
    }

    private String generarNumeroFactura(int correlativo, String serie) {
        String numeroFactura;
        GenerarFacturas generarFactura = new GenerarFacturas();
        generarFactura.generar(correlativo);
        numeroFactura = serie + "-" + generarFactura.correlativo();
        return numeroFactura;
    }

    public int modificarNotaCredito(NotaCredito notaCredito) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = notaCreditoDAOMySQL.modificar(notaCredito);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarNotaCreditos(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.mostrarTodos(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public NotaCredito buscarNotaCreditoPorCodigo(int codigo) throws Exception {
        NotaCredito notaCredito = null;
        gestorJDBC.abrirConexion();
        notaCredito = notaCreditoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return notaCredito;

    }

    public int eliminarNotaCredito(NotaCredito notaCredito) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = notaCreditoDAOMySQL.eliminar(notaCredito);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }

//    public DefaultComboBoxModel llenarCBONotaCreditoDetalle(int i) throws SQLException, Exception {
//        DefaultComboBoxModel modelo;
//        gestorJDBC.abrirConexion();
//        modelo = notaCreditoDAOMySQL.llenarCBOFacturaDetalle(i);
//        gestorJDBC.cerrarConexion();
//        return modelo;
//    }
//    public int verificarNumeroLote(int numero) throws SQLException, Exception {
//        int numeroObtenido = 0;
//        DefaultComboBoxModel modelo;
//        gestorJDBC.abrirConexion();
//        numeroObtenido = facturaDAOMySQL.verificarNumeroLote(numero);
//        gestorJDBC.cerrarConexion();
//        return numeroObtenido;
//    }
    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarFacturasPorNumeroFactura(int estado, String numero, JTable tableTransporte) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.mostrarFacturaPorNumeroFactura(estado, numero, tableTransporte);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.calcularTotalPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public void calcularTotalNoPagado(JLabel lblTotalPagado) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.calcularTotalNoPagado(lblTotalPagado);
        gestorJDBC.cerrarConexion();
    }

    public int eliminarNotaCreditoElectronica(NotaCreditoDetalle notaCreditoDetalle) throws Exception {
        int numeroObtenido = 0;
        DefaultComboBoxModel modelo;
        gestorJDBC.abrirConexion();
        numeroObtenido = notaCreditoDetalleDAOMySQL.eliminar(notaCreditoDetalle);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public int AgregarNotaCreditoDetalle(NotaCreditoDetalle notaCreditoDetalle) throws Exception {
        int afectador = 0;
        gestorJDBC.abrirConexion();
        afectador = notaCreditoDetalleDAOMySQL.agregar(notaCreditoDetalle);
        gestorJDBC.cerrarConexion();
        return afectador;
    }

    public void mostrarFacturas(int i, JTable table, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.mostrarTodos(i, table, texto);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarNotaCreditoElectronico(int numero, String fechaInicio, String fechaFin, RSTableMetro tablaFacturaElectronica) throws Exception {

        gestorJDBC.abrirConexion();
        notaCreditoDAOMySQL.mostrarNotaCreditoElectronico(numero, fechaInicio, fechaFin, tablaFacturaElectronica);
        gestorJDBC.cerrarConexion();
    }

    public List<EstadoFacturaElectronica> llenarEstadoFacturaElectronica(int i) throws Exception {
        List<EstadoFacturaElectronica> lista;
        gestorJDBC.abrirConexion();
        lista = estadoFacturaElectronicaDAOMySQL.llenarItemEstadoFacturaElectronica(i);
        gestorJDBC.cerrarConexion();
        return lista;

    }

    public void llenarComboTipoNotaCredito(MaterialComboBox<TipoNotaCD> cboTipoNotaCredito) throws Exception {
        gestorJDBC.abrirConexion();
        tipoNotaCDDAOMySQL.llenarCBOTipoProveedor(cboTipoNotaCredito);
        gestorJDBC.cerrarConexion();
    }

    public CorreoFactura buscarParaCorreo(String codigo) throws Exception {
        CorreoFactura correoFactura = null;
        gestorJDBC.abrirConexion();
        correoFactura = notaCreditoDAOMySQL.buscarParaCorreo(codigo);
        gestorJDBC.cerrarConexion();

        return correoFactura;

    }
}
