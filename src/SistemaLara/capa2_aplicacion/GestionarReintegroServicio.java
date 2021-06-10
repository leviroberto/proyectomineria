/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import FiveCodMaterilalDesignComboBox.MaterialComboBox;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Reintegro;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa4_persistencia.ClienteEntranteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import SistemaLara.capa4_persistencia.LiquidacionDAOMySQL;
import SistemaLara.capa4_persistencia.ReintegroDAOMySQL;
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
public class GestionarReintegroServicio {

    private final GestorJDBC gestorJDBC;
    private final ReintegroDAOMySQL reintegroDAOMySQL;
    private final TipoClienteDAOMySQL tipoClienteDAOMySQL;
    private final ProcedenciaDAOMySQL procedenciaDAOMySQL;
    private final ClienteEntranteDAOMySQL clienteEntranteDAOMySQL;
    private LiquidacionDAOMySQL liquidacionDAOMySQL;

    public GestionarReintegroServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
        procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
        reintegroDAOMySQL = new ReintegroDAOMySQL(gestorJDBC);
        tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
        liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);

    }

    public int guardarReintegro(Reintegro reintegro) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = reintegroDAOMySQL.agregar(reintegro);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public int modificarReintegro(Reintegro reintegro) throws Exception {
        int numerosAfecatdos = 0;
        gestorJDBC.abrirConexion();
        numerosAfecatdos = reintegroDAOMySQL.modificar(reintegro);
        gestorJDBC.cerrarConexion();
        return numerosAfecatdos;
    }

    public void mostrarReintegros( JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarTodos( table);
        gestorJDBC.cerrarConexion();

    }
     public void mostrarReintegrosPorLiquidacion(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarTodosPorLiquidacion(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public void mostrarReintegrosGrafico(int estado, JTable table) throws Exception {
        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarTodosGrafico(estado, table);
        gestorJDBC.cerrarConexion();

    }

    public Reintegro buscarReintegroPorCodigo(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Reintegro reintegro = reintegroDAOMySQL.buscarPorCodigo(codigo);
        gestorJDBC.cerrarConexion();
        return reintegro;

    }

    public int eliminarReintegro(Reintegro reintegroSeleccionado) throws Exception {
        int numeroAfectados = 0;
        gestorJDBC.abrirConexion();
        numeroAfectados = reintegroDAOMySQL.eliminar(reintegroSeleccionado);
        if (numeroAfectados == 1) {
            numeroAfectados = liquidacionDAOMySQL.modificarEstadoReintegro(reintegroSeleccionado.getLiquidacion().getCodigo(), "NO");
        }
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
        numeroObtenido = reintegroDAOMySQL.verificarNumeroLote(numero);
        gestorJDBC.cerrarConexion();
        return numeroObtenido;
    }

    public void llenarCamposNuevo(RSDateChooser dateFecha, FCMaterialTextField txtH2O, FCMaterialTextField txtLeyAg, FCMaterialTextField txtInter, FCMaterialTextField maquilla, FCMaterialTextField txtConscn) throws Exception {
        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, maquilla, txtConscn);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarReintegroPoCodigoCliente(JTable tableValorizacion, String texto) throws Exception {

        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarReintegroPorDNICliente(tableValorizacion, texto);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarReintegroPorTipoCliente(int i, JTable tableValorizacion, int tipo) throws Exception {

        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarReintegroPorTipoCliente(i, tableValorizacion, tipo);
        gestorJDBC.cerrarConexion();
    }

    public Reintegro buscarReintegroPorCodigoCliente(int codigo) throws Exception {
        gestorJDBC.abrirConexion();
        Reintegro reintegro = reintegroDAOMySQL.buscarPorCodigoCliente(codigo);
        gestorJDBC.cerrarConexion();
        return reintegro;

    }

    public void mostrarReintegroPorLoteCliente(JTable tableValorizacion) throws Exception {

        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarReintegroLotesPorCliente(tableValorizacion);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarReintegros(int i, JTable tableReintegro, String texto) throws Exception {
        gestorJDBC.abrirConexion();
        reintegroDAOMySQL.mostrarTodos(i, tableReintegro, texto);
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
        clienteEntrante = clienteEntranteDAOMySQL.obtenerClientePorDefectoParaReintegro(estado);
        gestorJDBC.cerrarConexion();
        return clienteEntrante;
    }

    public Reintegro buscarReintegroPorLote(int numeroLoteSeleccionado) throws Exception {
        Reintegro a = null;
        gestorJDBC.abrirConexion();
        a = reintegroDAOMySQL.buscarReintegroPorLote(numeroLoteSeleccionado);
        gestorJDBC.cerrarConexion();
        return a;

    }

}
