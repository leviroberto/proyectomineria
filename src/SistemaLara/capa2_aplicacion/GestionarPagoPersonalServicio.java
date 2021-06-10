package SistemaLara.capa2_aplicacion;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.PagoPersonal;
import SistemaLara.capa4_persistencia.AdelantoPersonalDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.PagoPersonalDAOMySQL;
import SistemaLara.capa4_persistencia.PersonalDAOMySQL;
import SistemaLara.capa6_exepciones.ExcepcionJDBC;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import rojerusan.RSTableMetro;

/**
 *
 * @author "FiveCod Software"
 */
public class GestionarPagoPersonalServicio {

    private final GestorJDBC gestorJDBC;
    private final PagoPersonalDAOMySQL pagoDAOMySQL;
    private final AdelantoPersonalDAOMySQL adelantoPersonalDAOMySQL;
    private final PersonalDAOMySQL trabajadorDAOMySQL = null;

    public GestionarPagoPersonalServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        pagoDAOMySQL = new PagoPersonalDAOMySQL(gestorJDBC);
//        trabajadorDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
        adelantoPersonalDAOMySQL = new AdelantoPersonalDAOMySQL(gestorJDBC);
    }

    public int guardarPagoPersonal(PagoPersonal pago) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = pagoDAOMySQL.ingresar(pago);
        gestorJDBC.cerrarConexion();
        if (numerosAfecatdos == 1) {
            gestorJDBC.abrirConexion();
            int codigoInsertado = obtenerUltimoCodigoInsertado();
            gestorJDBC.cerrarConexion();
            for (Adelanto adelanto : pago.getListaAdelantos()) {
                gestorJDBC.abrirConexion();
                adelantoPersonalDAOMySQL.modificarEstadoAdelanto(adelanto.getCodigo(), codigoInsertado);
                gestorJDBC.cerrarConexion();
            }
        }

        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public List<Adelanto> obtenerAdelantoPorPersonal(int codigoContrato, int estado) throws ExcepcionSQLConsulta, Exception {

        List<Adelanto> listaAdelanto = null;
        gestorJDBC.abrirConexion();
        listaAdelanto = adelantoPersonalDAOMySQL.obtenerAdelantoPorPersonal(codigoContrato, estado);
        gestorJDBC.cerrarConexion();
        return listaAdelanto;
    }

    private int obtenerUltimoCodigoInsertado() throws ExcepcionJDBC, Exception {
        gestorJDBC.abrirConexion();
        int ultimoCodigo = pagoDAOMySQL.obtenerUltimoCodigoInsertado();
        gestorJDBC.cerrarConexion();
        return ultimoCodigo;
    }

    public int modificarPagoPersonal(PagoPersonal pago) throws Exception {
//        gestorJDBC.abrirConexion();
//        pago.setEstado(PagoPersonal.ESTADO_ACTIVO);
//        int ultimoCodigo = pagoDAOMySQL.modificar(pago);
//        gestorJDBC.cerrarConexion();
//        return ultimoCodigo;
        return 0;

    }

    public List<PagoPersonal> mostrarPagoPersonals() throws Exception {
//        List<PagoPersonal> listaPagoPersonal = null;
//        gestorJDBC.abrirConexion();
//        listaPagoPersonal = pagoDAOMySQL.mostrarTodos();
//        gestorJDBC.cerrarConexion();
//        return listaPagoPersonal;
        return null;

    }

    public List<PagoPersonal> mostrarPagoPersonals(Date date) throws Exception {
//        List<PagoPersonal> listaPagoPersonal = null;
//        gestorJDBC.abrirConexion();
//        listaPagoPersonal = pagoDAOMySQL.mostrarTodos(date);
//        gestorJDBC.cerrarConexion();
//        return listaPagoPersonal;
        return null;

    }

    public PagoPersonal buscarPagoPersonalPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        PagoPersonal pago = pagoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return pago;

    }

    public int eliminarPagoPersonal(PagoPersonal pago) throws Exception {
        int resultado = 0;
        gestorJDBC.abrirConexion();
        resultado = pagoDAOMySQL.eliminar(pago);
        gestorJDBC.cerrarConexion();
        return resultado;

    }

    public void mostrarPagosPersonal(JTable tablaPagos) throws Exception {
        gestorJDBC.abrirConexion();
        pagoDAOMySQL.mostrarPagosPersonal(tablaPagos);
        gestorJDBC.cerrarConexion();

    }

    public Double obtenerTotalSolesAdelantoPorPersonal(int codigo) throws Exception {

        Double totalSoles = 0.0;
        gestorJDBC.abrirConexion();
        totalSoles = adelantoPersonalDAOMySQL.obtenerTotalSolesAdelantoPorPersonal(codigo);
        gestorJDBC.cerrarConexion();
        return totalSoles;
    }

    public int verificarSiHayPagosDisponibles(int codigo) throws Exception {
        int contador = 0;
        gestorJDBC.abrirConexion();
        contador = adelantoPersonalDAOMySQL.verificarSiHayPagosDisponibles(codigo);
        gestorJDBC.cerrarConexion();
        return contador;

    }

    public void mostrarGastosExtras(int i, JTable table, String texto) throws Exception {

        gestorJDBC.abrirConexion();
        pagoDAOMySQL.mostrarPagosPersonal(i, table, texto);
        gestorJDBC.cerrarConexion();
    }

    public int actualizarAdelantoPorPagar(int codigo) throws Exception {
        int resultado = 0;
        gestorJDBC.abrirConexion();
        resultado = adelantoPersonalDAOMySQL.actualizarAdelantoPorPagar(codigo);
        gestorJDBC.cerrarConexion();
        return resultado;

    }

    public int verificarSiEstaPagadoContrato(java.sql.Date fecha, int contratoId) throws Exception {
        int suma = 0;
        gestorJDBC.abrirConexion();
        suma = pagoDAOMySQL.verificarSiEstaPagadoContrato(fecha, contratoId);
        gestorJDBC.cerrarConexion();
        return suma;
    }

}
