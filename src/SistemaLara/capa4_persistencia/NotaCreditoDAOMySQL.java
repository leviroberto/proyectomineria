/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.Electronica;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.NotaCredito;
import SistemaLara.capa3_dominio.NotaCreditoDetalle;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class NotaCreditoDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public NotaCreditoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(NotaCredito notaCredito) throws SQLException {
        int registroAfectados = 0;
        LiquidacionDAOMySQL liquidacionDAOMySQL;
        NotaCreditoDetalleDAOMySQL notaCreditoDetalleDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, notaCredito.getNumeroNotaCreadito());
            prcProcedimientoAlmacenado.setString(2, notaCredito.getNumeroFactura());
            prcProcedimientoAlmacenado.setInt(3, notaCredito.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setString(4, notaCredito.getDenominacion());

            prcProcedimientoAlmacenado.setDate(5, notaCredito.getFechaEmision());
            prcProcedimientoAlmacenado.setDate(6, notaCredito.getFechaEmisionComprobante());
            prcProcedimientoAlmacenado.setDouble(7, notaCredito.getSubtotal());
            prcProcedimientoAlmacenado.setDouble(8, notaCredito.getIgv());
            prcProcedimientoAlmacenado.setDouble(9, notaCredito.getTotal());
            prcProcedimientoAlmacenado.setString(10, notaCredito.getMoneda());
            prcProcedimientoAlmacenado.setString(11, notaCredito.getLectura());
            prcProcedimientoAlmacenado.setInt(12, notaCredito.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(13, notaCredito.getCodigo());
            prcProcedimientoAlmacenado.setInt(14, notaCredito.getTipoNotaCD().getCodigo());
            prcProcedimientoAlmacenado.setString(15, notaCredito.getDescripcionMotivo());

            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (NotaCreditoDetalle notaCreditoDetalle : notaCredito.getListaNotaCreditoDetalle()) {
                    liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);
                    notaCreditoDetalleDAOMySQL = new NotaCreditoDetalleDAOMySQL(gestorJDBC);
                    registroAfectados = notaCreditoDetalleDAOMySQL.agregar(notaCreditoDetalle);
                }

            }
            return registroAfectados;
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la Nota Credito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            // JOptionPane.showMessageDialog(null, e.getMessage());

        }
        // return 0;
    }

    public int modificarCorrelativo(int emp_id, int numero) throws SQLException, ExcepcionSQLConsulta {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_ModificarCorrelativo_sp(?)");

            prcProcedimientoAlmacenado.setInt(1, numero);

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            // throw new ExcepcionSQLConsulta(e);
        }
        return 0;
    }

    public int modificar(NotaCredito notaCredito) throws SQLException {
        int registroAfectados = 0;
        NotaCreditoDetalleDAOMySQL notaCreditoDetalleDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, notaCredito.getNumeroNotaCreadito());
            prcProcedimientoAlmacenado.setString(2, notaCredito.getNumeroFactura());
            prcProcedimientoAlmacenado.setInt(3, notaCredito.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setString(4, notaCredito.getDenominacion());

            prcProcedimientoAlmacenado.setDate(5, notaCredito.getFechaEmision());
            prcProcedimientoAlmacenado.setDate(6, notaCredito.getFechaEmisionComprobante());
            prcProcedimientoAlmacenado.setDouble(7, notaCredito.getSubtotal());
            prcProcedimientoAlmacenado.setDouble(8, notaCredito.getIgv());
            prcProcedimientoAlmacenado.setDouble(9, notaCredito.getTotal());
            prcProcedimientoAlmacenado.setString(10, notaCredito.getMoneda());
            prcProcedimientoAlmacenado.setString(11, notaCredito.getLectura());
            prcProcedimientoAlmacenado.setInt(12, notaCredito.getCodigo());
            prcProcedimientoAlmacenado.setInt(13, notaCredito.getTipoNotaCD().getCodigo());
            prcProcedimientoAlmacenado.setString(14, notaCredito.getDescripcionMotivo());
            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (NotaCreditoDetalle creditoDetalle : notaCredito.getListaNotaCreditoDetalle()) {
                    notaCreditoDetalleDAOMySQL = new NotaCreditoDetalleDAOMySQL(gestorJDBC);
                    creditoDetalle.setPersonal(IniciarSesion.getUsuario());
                    creditoDetalle.setNotaCredito(notaCredito);
                    if (creditoDetalle.getEstadoNotaCredito().equals("Nuevo Modificar")) {
                        registroAfectados = notaCreditoDetalleDAOMySQL.agregar(creditoDetalle);
                    } else {
                        registroAfectados = notaCreditoDetalleDAOMySQL.modificar(creditoDetalle);
                    }
                }

                for (NotaCreditoDetalle notaCreditoDetalle : notaCredito.getListaNotaCreditoEliminadas()) {
                    notaCreditoDetalleDAOMySQL = new NotaCreditoDetalleDAOMySQL(gestorJDBC);
                    if (notaCreditoDetalle.getEstadoNotaCredito().equals("Eliminar")) {
                        notaCreditoDetalleDAOMySQL.eliminar(notaCreditoDetalle);

                    }

                }

            }
            return registroAfectados;
        } catch (Exception e) {
//            throw new SQLException("No se pudo modificar la Factura.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    public int eliminar(NotaCredito credito) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, credito.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int verificarNumeroLote(int numero) throws SQLException {
        ResultSet resultado;
        int numeroObtenido = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_VerificarNumeroLote_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, numero);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                numeroObtenido = resultado.getInt("contador");

            }
            resultado.close();
            return numeroObtenido;
        } catch (Exception e) {

            throw new SQLException("No se ha podido verificar el Numero de Lote.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

//       JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //    return 1;

    }

    public void llenarCamposNuevo(RSDateChooser fecha, FCMaterialTextField h2o, FCMaterialTextField leyag, FCMaterialTextField inter, FCMaterialTextField maquilla, FCMaterialTextField conscn) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_LlenarNuevo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                fecha.setDatoFecha(resultado.getDate("fecha"));
                h2o.setText(resultado.getString("Factura_H2O"));
                leyag.setText(resultado.getString("Factura_Leyag"));
                inter.setText(resultado.getString("Factura_Inter"));
                maquilla.setText(resultado.getString("Factura_Maquilla"));
                conscn.setText(resultado.getString("Factura_Conscon"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido llenar campos Nuevo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("NotaCredito_id"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_NumeroNotaCreadito"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_NumeroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_FechaEmision"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_FechaEmision_Comprobante"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_SubtTotal"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_Total"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido  mostrar Todas las notas de Credito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularTotalPagado(JLabel totalPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_CalcularTotalPagado_sp(?)");
            prcProcedimientoAlmacenado.setString(1, "Pagado");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalPagado.setText(resultado.getString("totalPagado"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido Calcular el Total Pagado Factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularTotalNoPagado(JLabel totalNoPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_CalcularTotalNoPagado_sp(?)");
            prcProcedimientoAlmacenado.setString(1, "No Pagado");
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalPagado"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido Calcular el total No Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public NotaCredito buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        NotaCredito notaCredito = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        NotaCreditoDetalleDAOMySQL facturaDetalleDAOMySQL;
        TipoNotaCreditoDAOMySQL tipoNotaCDDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                tipoNotaCDDAOMySQL = new TipoNotaCreditoDAOMySQL(gestorJDBC);
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
                facturaDetalleDAOMySQL = new NotaCreditoDetalleDAOMySQL(gestorJDBC);

                notaCredito = new NotaCredito();
                notaCredito.setCodigo(resultado.getInt("NotaCredito_id"));
                notaCredito.setNumeroNotaCreadito(resultado.getString("NotaCredito_NumeroNotaCreadito"));
                notaCredito.setNumeroFactura(resultado.getString("NotaCredito_NumeroFactura"));
                notaCredito.setProveedorServicio(proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));
                notaCredito.setDenominacion(resultado.getString("NotaCredito_Denominacion"));
                notaCredito.setFechaEmision(resultado.getDate("NotaCredito_FechaEmision"));
                notaCredito.setFechaEmisionComprobante(resultado.getDate("NotaCredito_FechaEmision_Comprobante"));
                notaCredito.setMoneda(resultado.getString("NotaCredito_Moneda"));
                notaCredito.setDescripcionMotivo(resultado.getString("NotaCredito_DescripcionMotivo"));
                notaCredito.setLectura(resultado.getString("NotaCredito_Lectura"));

                notaCredito.setTotal(resultado.getDouble("NotaCredito_Total"));
                notaCredito.setIgv(resultado.getDouble("NotaCredito_IGV"));
                notaCredito.setSubtotal(resultado.getDouble("NotaCredito_SubtTotal"));

                notaCredito.setTipoNotaCD(tipoNotaCDDAOMySQL.buscarPorCodigo(resultado.getInt("TipoNotaCredito_Id")));
//                factura.setCodigoUnico(resultado.getString("Factura_CodigoUnico"));

                notaCredito.setListaNotaCreditoDetalle(facturaDetalleDAOMySQL.buscarPorCodigoFactura(notaCredito));
            }

            resultado.close();
            return notaCredito;
        } catch (Exception e) {

//            throw new SQLException("No se ha podido Buscar Por Codigo.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public void mostrarFacturaPorNumeroFactura(int estado, String numero, JTable tableTransporte) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableTransporte.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, numero);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Factura_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Nro"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Guian"));
                filaTabla.agregarValorCelda(resultado.getString("actura_ValorVenta"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Total"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Moneda"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_CodigoUnico"));
                filaTabla.agregarValorCelda(resultado.getString("Empresa_Nombre_Comercial"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            tableTransporte.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Factura Por Numero Factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int obtenerUltimoCodigo() throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        int ultimoCodigo = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_ObtenerUltimoCodigo()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                ultimoCodigo = resultado.getInt("ultimaNotaCredito");
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido Obtener el Ultimo Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        return ultimoCodigo;

    }

    public String obtenerSerie(int IdEmpresa) throws Exception, SQLException {
        String serie = "";
        ResultSet resultado;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_ObtenerSerie_sp()");
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {

                serie = resultado.getString("serie");
            }

            resultado.close();

            return serie;
        } catch (SQLException e) {

            throw new SQLException("No se ha podido obtener el Numero de Serie.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // return null;
    }

    public int obtenerCorrelativo(int idEmpresa) throws ExcepcionSQLConsulta, SQLException {
        int serie = 0;
        ResultSet resultado;

        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_ObtenerCorrelativo_sp()");
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                serie = resultado.getInt("correlativo");
            }
            resultado.close();

            return serie;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCredito_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("NotaCredito_id"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_NumeroNotaCreadito"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_NumeroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_FechaEmision"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_FechaEmision_Comprobante"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_SubtTotal"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("NotaCredito_Total"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
//            throw new SQLException("No se ha podido  mostrar Todas las notas de Credito.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void mostrarNotaCreditoElectronico(int numero, String fechaInicio, String fechaFin, RSTableMetro tablaFacturaElectronica) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaFacturaElectronica.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCreditoElectronica_MostrarFacturas_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, numero);
            prcProcedimientoAlmacenado.setString(2, fechaInicio);
            prcProcedimientoAlmacenado.setString(3, fechaFin);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                filaTabla = new Fila();
                registros[0] = Boolean.FALSE;
                registros[1] = resultado.getInt("NotaCredito_id");
                registros[2] = resultado.getString("NotaCredito_NumeroNotaCreadito");
                registros[3] = resultado.getString("NotaCredito_Total");
                registros[4] = resultado.getString("NotaCredito_FechaEmision");
                registros[5] = resultado.getString("NotaCredito_FechaEmision");

                modeloTabla.addRow(registros);
            }
            resultado.close();
            tablaFacturaElectronica.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Factura Electronica.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public CorreoFactura buscarParaCorreo(String codigo) throws SQLException {
        Factura factura = null;
        CorreoFactura correoFactura = null;
        Electronica facturaElectronica = null;
        ProveedorServicio proveedorServicio;
        ResultSet resultado;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCreditoElectronico_BuscarParaCorreo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, Integer.parseInt(codigo));
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                correoFactura = new CorreoFactura();
                facturaElectronica = new Electronica();
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                facturaElectronica.setId(resultado.getInt("NotaCreditoElectronica_Id"));
                facturaElectronica.setRutapdf(resultado.getString("NotaCreditoElectronica_RutaPDF"));
                facturaElectronica.setRutaxml(resultado.getString("NotaCreditoElectronica_RutaXML"));
                facturaElectronica.setNumeroFactura(resultado.getString("NotaCredito_NumeroNotaCreadito"));

                proveedorServicio = proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id"));

                correoFactura.setElectronico(facturaElectronica);
                correoFactura.setProveedorServicio(proveedorServicio);

            }
            resultado.close();
            return correoFactura;
        } catch (Exception e) {
            throw new SQLException("No se ha podido buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
