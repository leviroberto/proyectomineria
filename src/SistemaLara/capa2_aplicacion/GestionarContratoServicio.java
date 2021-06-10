/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa4_persistencia.ContratoDAOMySQL;
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
public class GestionarContratoServicio {

    private final GestorJDBC gestorJDBC;
    private final ContratoDAOMySQL contratoDAOMySQL;
    private final PersonalDAOMySQL personalDAOMySQL;

    public GestionarContratoServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
        personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);

    }

    private Personal buscarPersonal() {
        return null;
    }

    public Contrato verificarSiTieneContratoPersonal(int codigo) throws ExcepcionSQLConsulta, Exception {
        Contrato contrato = null;
        gestorJDBC.abrirConexion();
        contrato = contratoDAOMySQL.verificarSiTieneContratoPersonal(codigo);
        gestorJDBC.cerrarConexion();
        return contrato;
    }

    public int guardarContrato(Contrato contrato) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        if (contrato.esCorrectaFechaFinalContrato()) {
            if (contrato.esCorrectoDiasPagar()) {
                contrato.getEstado().setCodigo(1);
                numerosAfecatdos = contratoDAOMySQL.agregar(contrato);
            } else {
                Mensaje.mostrarMensajeDefinido(null, "EL total de dias a pagar es incorrecto");
                numerosAfecatdos = 5;
            }

        } else {
            numerosAfecatdos = 2;
        }

        gestorJDBC.cerrarConexion();

        return numerosAfecatdos;
    }

    public int obtenerUltimoCodigo() throws Exception {
        gestorJDBC.abrirConexion();

        int ultimoCodigo = contratoDAOMySQL.obtenerUltimoCodigo();
        gestorJDBC.cerrarConexion();
        return ultimoCodigo;
    }

    public int modificarContrato(Contrato contrato) throws Exception {

        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        if (contrato.esCorrectaFechaFinalContrato()) {
            if (contrato.esCorrectoDiasPagar()) {
                contrato.getEstado().setCodigo(1);
                numerosAfecatdos = contratoDAOMySQL.modificar(contrato);
            } else {
                numerosAfecatdos = 5;
                Mensaje.mostrarMensajeDefinido(null, "EL total de dias a pagar es incorrecto");

            }

        } else {
            numerosAfecatdos = 2;
        }
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public Contrato buscarContratoPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Contrato contrato = contratoDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return contrato;

    }

    public int eliminarContrato(int codigo) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = contratoDAOMySQL.eliminar(codigo);
        gestorJDBC.cerrarConexion();
        return numeroAfectados;
    }
//
//    public double buscarContratoTotalPorPersonal(String codigo, int mes, String estado) throws Exception {
//        gestorJDBC.abrirConexion();
//        double montoTotal = contratoDAOMySQL.buscarContratoTotalPorPersonal(codigo, mes, estado);
//        gestorJDBC.cerrarConexion();
//        return montoTotal;
//    }
//
//    public ArrayList<Contrato> mostrarContratoPorPersonal(Personal personal, int mes, int anio) throws Exception {
//        ArrayList<Contrato> listaContratos = null;
//        gestorJDBC.abrirConexion();
//        listaContratos = contratoDAOMySQL.mostrarContratosPorPersona(personal, mes, anio);
//        gestorJDBC.cerrarConexion();
//        return listaContratos;
//    }
//
//    public Personal buscarPersonalPorCodigo(String codigo) throws Exception {
//        Personal personal = null;
//        gestorJDBC.abrirConexion();
//        personal = personalDAOMySQL.buscarPorCodigo(codigo);
//        gestorJDBC.cerrarConexion();
//        return personal;
//    }
//
//    public int eliminarContratoPorCodigo(String codigo) throws Exception {
//        int numeroAfectados = 0;
//        gestorJDBC.abrirConexion();
//        numeroAfectados = contratoDAOMySQL.eliminarPorCodigo(codigo);
//        gestorJDBC.cerrarConexion();
//        return numeroAfectados;
//    }
//
//    public List<Contrato> buscarContratoPorNombrePersonal(String nombre, int mes, int anio) throws Exception {
//        List<Contrato> listaContratos = null;
//        gestorJDBC.abrirConexion();
//        listaContratos = contratoDAOMySQL.mostroarTodosLosContratosCalculadosPorPersonal(nombre, mes, anio);
//        gestorJDBC.cerrarConexion();
//        return listaContratos;
//    }
//
//    public Pago buscarPersonalPagado(String codigo, int mes, int anio) throws Exception {
//        Pago pago = null;
//        gestorJDBC.abrirConexion();
//        pago = pagoDAOMySQL.buscarPagoPagado(codigo, mes, anio);
//        gestorJDBC.cerrarConexion();
//        return pago;
//
//    }

    public void mostrarContrato(JTable table) throws Exception {

        gestorJDBC.abrirConexion();
        contratoDAOMySQL.mostrar(table);
        gestorJDBC.cerrarConexion();

    }

    public List<Contrato> buscarContratoPorPersonalDni(String dni, String estado) throws ExcepcionJDBC, Exception {
        List<Contrato> listaContratos = null;
        gestorJDBC.abrirConexion();
        listaContratos = contratoDAOMySQL.buscarContratoPorDni(dni, estado);
        gestorJDBC.cerrarConexion();
        return listaContratos;
    }

    public Contrato buscarPersonalPorDni(String dni) throws Exception {
        Contrato contrato = null;
        gestorJDBC.abrirConexion();
        contrato = contratoDAOMySQL.buscarContratoPorDni(dni);
        gestorJDBC.cerrarConexion();
        return contrato;
    }

    public void llenarItemEstado(RSComboMetro cboFiltarPorMeses) throws Exception {
        gestorJDBC.abrirConexion();
        contratoDAOMySQL.llenarItemEstado(cboFiltarPorMeses);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarContratosPorPersonal(JTable tableContrato, int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        contratoDAOMySQL.mostrarContratosPorPersonal(tableContrato, codigo);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarContrato(int i, JTable tablaContrato, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        contratoDAOMySQL.mostrarContrato(i, tablaContrato, texto);
        gestorJDBC.cerrarConexion();

    }

    public List<Contrato> ObtenerContratoPersonalLista() throws Exception {
        List<Contrato> listaContrato = null;
        gestorJDBC.abrirConexion();
        listaContrato = contratoDAOMySQL.ObtenerContratoPersonalLista();
        gestorJDBC.cerrarConexion();
        return listaContrato;

    }

}
