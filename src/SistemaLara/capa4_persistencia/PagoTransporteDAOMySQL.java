/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.PagoTransporte;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojeru_san.componentes.RSDateChooser;

/**
 *
 * @author FiveCod Software
 */
public class PagoTransporteDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public PagoTransporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(PagoTransporte pagoTransporte) throws SQLException {
        int resultado = 0;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            prcProcedimientoAlmacenado.setInt(1, pagoTransporte.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setDate(2, pagoTransporte.getFecha());
            prcProcedimientoAlmacenado.setString(3, pagoTransporte.getNroFactura());

            prcProcedimientoAlmacenado.setDouble(4, pagoTransporte.getPeso());
            prcProcedimientoAlmacenado.setDouble(5, pagoTransporte.getTotal());
            prcProcedimientoAlmacenado.setDouble(6, pagoTransporte.getDetraccion());
            prcProcedimientoAlmacenado.setString(7, pagoTransporte.getDescontar());
            prcProcedimientoAlmacenado.setDouble(8, pagoTransporte.getAdelanto());
            prcProcedimientoAlmacenado.setDouble(9, pagoTransporte.getImporte());
            prcProcedimientoAlmacenado.setString(10, pagoTransporte.getNroOperacion());

            prcProcedimientoAlmacenado.setDate(11, pagoTransporte.getFechaPago());
            prcProcedimientoAlmacenado.setInt(12, pagoTransporte.getTipoCliente().getCodigo());
            prcProcedimientoAlmacenado.setInt(13, pagoTransporte.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setDouble(14, pagoTransporte.getPrecio());
            prcProcedimientoAlmacenado.setString(15, pagoTransporte.getEstadoPagoTransporte());
            prcProcedimientoAlmacenado.setString(16, pagoTransporte.getRutaBaucher());
            prcProcedimientoAlmacenado.setInt(17, pagoTransporte.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar Pago Transporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //    JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //     return 0;
    }

    public int modificar(PagoTransporte pagoTransporte) throws SQLException {
        int resultado = 0;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, pagoTransporte.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setDate(2, pagoTransporte.getFecha());
            prcProcedimientoAlmacenado.setString(3, pagoTransporte.getNroFactura());
            prcProcedimientoAlmacenado.setDouble(4, pagoTransporte.getPeso());
            prcProcedimientoAlmacenado.setDouble(5, pagoTransporte.getTotal());
            prcProcedimientoAlmacenado.setDouble(6, pagoTransporte.getDetraccion());
            prcProcedimientoAlmacenado.setString(7, pagoTransporte.getDescontar());
            prcProcedimientoAlmacenado.setDouble(8, pagoTransporte.getAdelanto());
            prcProcedimientoAlmacenado.setDouble(9, pagoTransporte.getImporte());
            prcProcedimientoAlmacenado.setString(10, pagoTransporte.getNroOperacion());
            prcProcedimientoAlmacenado.setDate(11, pagoTransporte.getFechaPago());
            prcProcedimientoAlmacenado.setInt(12, pagoTransporte.getTipoCliente().getCodigo());
            prcProcedimientoAlmacenado.setInt(13, pagoTransporte.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setDouble(14, pagoTransporte.getPrecio());
            prcProcedimientoAlmacenado.setDouble(15, pagoTransporte.getCodigo());
            prcProcedimientoAlmacenado.setString(16, pagoTransporte.getEstadoPagoTransporte());
            prcProcedimientoAlmacenado.setString(17, pagoTransporte.getRutaBaucher());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar El Pago del Transporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //   return 5;

    }

    public int eliminar(PagoTransporte pagoTransporte) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, pagoTransporte.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el pago Transporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int verificarNumeroLote(int numero) throws SQLException {
        ResultSet resultado;
        int numeroObtenido = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_VerificarNumeroLote_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, numero);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                numeroObtenido = resultado.getInt("contador");

            }
            resultado.close();
            return numeroObtenido;
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar verificar Numero Lote Transporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //   return 1;

    }

    public void llenarCamposNuevo(RSDateChooser fecha, FCMaterialTextField h2o, FCMaterialTextField leyag, FCMaterialTextField inter, FCMaterialTextField maquilla, FCMaterialTextField conscn) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_LlenarNuevo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                fecha.setDatoFecha(resultado.getDate("fecha"));
                h2o.setText(resultado.getString("PagoTransporte_H2O"));
                leyag.setText(resultado.getString("PagoTransporte_Leyag"));
                inter.setText(resultado.getString("PagoTransporte_Inter"));
                maquilla.setText(resultado.getString("PagoTransporte_Maquilla"));
                conscn.setText(resultado.getString("PagoTransporte_Conscon"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se pudo llenar Campos Nuevo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("PagoTransporte_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Peso"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Precio"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Total"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Detraccion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Descontar"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Adelanto"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Importe"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroOperacion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularTotalPagado(JLabel totalPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_CalcularTotalPagado_sp(?)");
            prcProcedimientoAlmacenado.setString(1, "Pagado");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalPagado.setText(resultado.getString("totalPagado"));
            }
            if (totalPagado.getText() == null) {
                totalPagado.setText("0.0");
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se pudo calcular Total Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularTotalNoPagado(JLabel totalNoPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_CalcularTotalNoPagado_sp(?)");
            prcProcedimientoAlmacenado.setString(1, "No Pagado");
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalPagado"));
            }
            if (totalNoPagado.getText() == null) {
                totalNoPagado.setText("0.0");
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se pudo calcular Total No Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public PagoTransporte buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        PagoTransporte pagoTransporte = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);

                pagoTransporte = new PagoTransporte();
                pagoTransporte.setCodigo(resultado.getInt("PagoTransporte_Id"));
                pagoTransporte.setFecha(resultado.getDate("PagoTransporte_Fecha"));

                pagoTransporte.setTipoCliente(tipoClienteDAOMySQL.buscarPorCodigo(resultado.getInt("TipoCliente_Id")));
                pagoTransporte.setProveedorServicio(proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));
                pagoTransporte.setNroFactura(resultado.getString("PagoTransporte_NroFactura"));
                pagoTransporte.setPeso(resultado.getDouble("PagoTransporte_Peso"));
                pagoTransporte.setPrecio(resultado.getDouble("PagoTransporte_Precio"));
                pagoTransporte.setTotal(resultado.getDouble("PagoTransporte_Total"));
                pagoTransporte.setDetraccion(resultado.getDouble("PagoTransporte_Detraccion"));

                pagoTransporte.setDescontar(resultado.getString("PagoTransporte_Descontar"));

                pagoTransporte.setAdelanto(resultado.getDouble("PagoTransporte_Adelanto"));
                pagoTransporte.setImporte(resultado.getDouble("PagoTransporte_Importe"));
                pagoTransporte.setNroOperacion(resultado.getString("PagoTransporte_NroOperacion"));
                pagoTransporte.setFechaPago(resultado.getDate("PagoTransporte_FechaPago"));
                pagoTransporte.setEstadoPagoTransporte(resultado.getString("PagoTransporte_Estado"));
                pagoTransporte.setRutaBaucher(resultado.getString("PagoTransporte_RutaBaucher"));

            }
            resultado.close();
            return pagoTransporte;
        } catch (Exception e) {
            throw new SQLException("No se pudo Buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //      return null;
    }

    public void mostrarPagoTransportePorNumeroFactura(int estado, String numero, JTable tableTransporte) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableTransporte.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_MostrarPorNumeroFactura_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, numero);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("PagoTransporte_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Peso"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Precio"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Total"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Detraccion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Descontar"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Adelanto"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Importe"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroOperacion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableTransporte.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Pago Transporte Por Numero Factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoTransporte_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("PagoTransporte_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Peso"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Precio"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Total"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Detraccion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Descontar"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Adelanto"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_Importe"));

                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_NroOperacion"));
                filaTabla.agregarValorCelda(resultado.getString("PagoTransporte_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
        throw new SQLException("No se pudo mostrar Todos Pago Transporte.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
