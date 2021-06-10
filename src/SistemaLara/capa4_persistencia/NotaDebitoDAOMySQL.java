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
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebitoDetalle;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebitoDetalle;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.*;
import javax.swing.*;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojeru_san.componentes.RSDateChooser;

/**
 *
 * @author FiveCod Software
 */
public class NotaDebitoDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public NotaDebitoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(NotaDebito notaDebito) throws SQLException {
        int registroAfectados = 0;
        ReintegroDAOMySQL reintegroDAOMySQL;
        NotaDebitoDetalleDAOMySQL notaDebitoDetalleDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, notaDebito.getNumeroNotaDebito());
            prcProcedimientoAlmacenado.setString(2, notaDebito.getNumeroFactura());
            prcProcedimientoAlmacenado.setInt(3, notaDebito.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setDate(4, notaDebito.getFechaEmision());
            prcProcedimientoAlmacenado.setDate(5, notaDebito.getFechaEmision_Comprobante());
            prcProcedimientoAlmacenado.setString(6, notaDebito.getDenominacion());
            prcProcedimientoAlmacenado.setString(7, notaDebito.getConsecion());
            prcProcedimientoAlmacenado.setString(8, notaDebito.getCodigoUnico());
            prcProcedimientoAlmacenado.setDouble(9, notaDebito.getValorVenta());
            prcProcedimientoAlmacenado.setDouble(10, notaDebito.getIGV());
            prcProcedimientoAlmacenado.setDouble(11, notaDebito.getTotal());
            prcProcedimientoAlmacenado.setString(12, notaDebito.getMoneda());
            prcProcedimientoAlmacenado.setString(13, notaDebito.getLectura());
            prcProcedimientoAlmacenado.setInt(14, notaDebito.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(15, notaDebito.getCodigo());
            prcProcedimientoAlmacenado.setString(16, notaDebito.getDescripcionMotivo());
            prcProcedimientoAlmacenado.setInt(17, notaDebito.getTipoNotaDebito().getCodigo());
            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (NotaDebitoDetalle notaDebitoDetalle : notaDebito.getListaNotaDebitoDetalle()) {
                    reintegroDAOMySQL = new ReintegroDAOMySQL(gestorJDBC);
                    notaDebitoDetalleDAOMySQL = new NotaDebitoDetalleDAOMySQL(gestorJDBC);
                    registroAfectados = notaDebitoDetalleDAOMySQL.agregar(notaDebitoDetalle);
                    // registroAfectados = reintegroDAOMySQL.modificarNotaDebitodo(notaDebito.getNumeroNotaDebito(), notaDebitoDetalle.getLiquidacion().getCodigo());
                }

            }
            return registroAfectados;
        } catch (Exception e) {
//            throw new SQLException("No se pudo registrar la Nota Debito.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 3;
    }

    public int modificarCorrelativoNotaDebito(int emp_id, int numero) throws SQLException, ExcepcionSQLConsulta {
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoNumero_Modificar_sp(?,?)");
        prcProcedimientoAlmacenado.setInt(1, emp_id);
        prcProcedimientoAlmacenado.setInt(2, numero);
        try {
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public int modificar(NotaDebito notaDebito) throws SQLException {
        int registroAfectados = 0;

        NotaDebitoDetalleDAOMySQL notaDebitoDetalleDAOMySQL;

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, notaDebito.getNumeroNotaDebito());
            prcProcedimientoAlmacenado.setString(2, notaDebito.getNumeroFactura());
            prcProcedimientoAlmacenado.setInt(3, notaDebito.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setString(4, notaDebito.getDenominacion());
            prcProcedimientoAlmacenado.setDate(5, notaDebito.getFechaEmision());
            prcProcedimientoAlmacenado.setDate(6, notaDebito.getFechaEmision_Comprobante());

            prcProcedimientoAlmacenado.setString(7, notaDebito.getConsecion());
            prcProcedimientoAlmacenado.setString(8, notaDebito.getCodigoUnico());
            prcProcedimientoAlmacenado.setDouble(9, notaDebito.getValorVenta());
            prcProcedimientoAlmacenado.setDouble(10, notaDebito.getIGV());
            prcProcedimientoAlmacenado.setDouble(11, notaDebito.getTotal());
            prcProcedimientoAlmacenado.setString(12, notaDebito.getMoneda());
            prcProcedimientoAlmacenado.setString(13, notaDebito.getLectura());
            prcProcedimientoAlmacenado.setInt(14, notaDebito.getCodigo());
            prcProcedimientoAlmacenado.setString(15, notaDebito.getDescripcionMotivo());
            prcProcedimientoAlmacenado.setInt(16, notaDebito.getTipoNotaDebito().getCodigo());

            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (NotaDebitoDetalle notaDebitoDetalle : notaDebito.getListaNotaDebitoDetalle()) {
                    notaDebitoDetalleDAOMySQL = new NotaDebitoDetalleDAOMySQL(gestorJDBC);
                    notaDebitoDetalle.setPersonal(IniciarSesion.getUsuario());
                    notaDebitoDetalle.setNotaDebito(notaDebito);
                    if (notaDebitoDetalle.getNotaDebitoEstado().equals("Nuevo Modificar")) {
                        registroAfectados = notaDebitoDetalleDAOMySQL.agregar(notaDebitoDetalle);
                    } else {
                        registroAfectados = notaDebitoDetalleDAOMySQL.modificar(notaDebitoDetalle);
                    }
                }

                for (NotaDebitoDetalle notaDebitos : notaDebito.getListaNotaDebitoEliminadas()) {
                    notaDebitoDetalleDAOMySQL = new NotaDebitoDetalleDAOMySQL(gestorJDBC);
                    if (notaDebitos.getNotaDebitoEstado().equals("Eliminar")) {
                        if (notaDebitos.getCodigo() != 0) {
                            registroAfectados = notaDebitoDetalleDAOMySQL.eliminar(notaDebitos);
                        }

                    }
                }

            }
            return registroAfectados;
        } catch (Exception e) {
//            throw new SQLException("No se pudo modificar la Nota Debito.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 3;
    }

    public void llenarCamposNuevo(RSDateChooser fecha, FCMaterialTextField h2o, FCMaterialTextField leyag, FCMaterialTextField inter, FCMaterialTextField maquilla, FCMaterialTextField conscn) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_LlenarNuevo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                fecha.setDatoFecha(resultado.getDate("fecha"));
                h2o.setText(resultado.getString("NotaDebito_H2O"));
                leyag.setText(resultado.getString("NotaDebito_Leyag"));
                inter.setText(resultado.getString("NotaDebito_Inter"));
                maquilla.setText(resultado.getString("NotaDebito_Maquilla"));
                conscn.setText(resultado.getString("NotaDebito_Conscon"));
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_MostrarTodos_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("NotaDebito_Id"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_NumeroNotaDebito"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_NumeroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_FechaEmision"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_FechaEmision_Comprobante"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_ValorVenta"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Total"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Moneda"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
//            throw new SQLException("No se ha podido  mostrar Todas las NotaDebitos.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void calcularTotalPagado(JLabel totalPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_CalcularTotalPagado_sp(?)");
            prcProcedimientoAlmacenado.setString(1, "Pagado");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalPagado.setText(resultado.getString("totalPagado"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido Calcular el Total Pagado NotaDebito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularTotalNoPagado(JLabel totalNoPagado) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_CalcularTotalNoPagado_sp(?)");
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

    public NotaDebito buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        NotaDebito notaDebito = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        NotaDebitoDetalleDAOMySQL notaDebitoDetalleDAOMySQL;
        TipoNotaDebitoDAOMySQL tipoNotaDebitoDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
                notaDebitoDetalleDAOMySQL = new NotaDebitoDetalleDAOMySQL(gestorJDBC);
                tipoNotaDebitoDAOMySQL = new TipoNotaDebitoDAOMySQL(gestorJDBC);
                notaDebito = new NotaDebito();
                notaDebito.setCodigo(resultado.getInt("NotaDebito_Id"));
                notaDebito.setNumeroNotaDebito(resultado.getString("NotaDebito_NumeroNotaDebito"));
                notaDebito.setNumeroFactura(resultado.getString("NotaDebito_NumeroFactura"));

                notaDebito.setProveedorServicio(proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));
                notaDebito.setFechaEmision(resultado.getDate("NotaDebito_FechaEmision"));
                notaDebito.setFechaEmision_Comprobante(resultado.getDate("NotaDebito_FechaEmision_Comprobante"));
                notaDebito.setMoneda(resultado.getString("NotaDebito_Moneda"));
                notaDebito.setConsecion(resultado.getString("NotaDebito_Consecion"));
                notaDebito.setDenominacion(resultado.getString("NotaDebito_Denominacion"));
                notaDebito.setCodigoUnico(resultado.getString("NotaDebito_CodigoUnico"));

                notaDebito.setValorVenta(resultado.getDouble("NotaDebito_ValorVenta"));
                notaDebito.setIGV(resultado.getDouble("NotaDebito_IGV"));
                notaDebito.setTotal(resultado.getDouble("NotaDebito_Total"));
                notaDebito.setLectura(resultado.getString("NotaDebito_Lectura"));
                notaDebito.setTipoNotaDebito(tipoNotaDebitoDAOMySQL.buscarPorCodigo(resultado.getInt("TipoNotaDebito_Id")));
                notaDebito.setDescripcionMotivo(resultado.getString("NotaDebito_DescripcionMotivo"));
                notaDebito.setListaNotaDebitoDetalle(notaDebitoDetalleDAOMySQL.buscarPorCodigonotaDebito(notaDebito));
            }

            resultado.close();
            return notaDebito;
        } catch (Exception e) {

            throw new SQLException("No se ha podido Buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //  return null;
    }

    public void mostrarNotaDebitoPorNumeroNotaDebito(int estado, String numero, JTable tableTransporte) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableTransporte.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, numero);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("NotaDebito_Id"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Nro"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Guian"));
                filaTabla.agregarValorCelda(resultado.getString("actura_ValorVenta"));

                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Total"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Moneda"));

                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_CodigoUnico"));
                filaTabla.agregarValorCelda(resultado.getString("Empresa_Nombre_Comercial"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            tableTransporte.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar NotaDebito Por Numero NotaDebito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int obtenerUltimoCodigo() throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        int ultimoCodigo = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_ObtenerUltimoCodigo_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                ultimoCodigo = resultado.getInt("ultimaNotaDebito");
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido Obtener el Ultimo Codigo Nota Debito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        return ultimoCodigo;

    }

    public String obtenerSerieNotaDebito(int IdEmpresa) throws Exception, SQLException {
        String serie = "";
        ResultSet resultado;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoNumero_ObtenerSerie_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, IdEmpresa);
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {

                serie = resultado.getString("serie");
            }

            resultado.close();

            return serie;
        } catch (SQLException e) {

            throw new SQLException("No se ha podido obtener el Numero de Serie Nota Debito.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // return null;
    }

    public int obtenerCorrelativo(int idEmpresa) throws ExcepcionSQLConsulta, SQLException {
        int serie = 0;
        ResultSet resultado;

        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoNumero_ObtenerCorrelativo_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, idEmpresa);
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebito_MostrarTodosPorlIke_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("NotaDebito_Id"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_NumeroNotaDebito"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_NumeroFactura"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_FechaEmision"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_FechaEmision_Comprobante"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_ValorVenta"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Total"));
                filaTabla.agregarValorCelda(resultado.getString("NotaDebito_Moneda"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
//            throw new SQLException("No se ha podido  mostrar Todas las NotaDebitos.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public CorreoFactura buscarParaCorreo(String codigo) throws SQLException {
        CorreoFactura correoFactura = null;
        Electronica facturaElectronica = null;
        ProveedorServicio proveedorServicio;
        ResultSet resultado;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoElectronico_BuscarParaCorreo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, Integer.parseInt(codigo));
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                correoFactura = new CorreoFactura();
                facturaElectronica = new Electronica();
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                facturaElectronica.setId(resultado.getInt("NotaDebitoElectronica_Id"));
                facturaElectronica.setRutapdf(resultado.getString("NotaDebitoElectronica_RutaPDF"));
                facturaElectronica.setRutaxml(resultado.getString("NotaDebitoElectronica_RutaXML"));
                facturaElectronica.setNumeroFactura(resultado.getString("NotaDebito_NumeroNotaDebito"));

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
