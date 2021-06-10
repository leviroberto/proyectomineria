/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.IniciarSesion;
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
public class FacturaDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public FacturaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Factura factura) throws SQLException {
        int registroAfectados = 0;
        LiquidacionDAOMySQL liquidacionDAOMySQL;
        FacturaDetalleDAOMySQL facturaDetalleDAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, factura.getNroFactura());
            prcProcedimientoAlmacenado.setInt(2, factura.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setDate(3, factura.getFecha());
            prcProcedimientoAlmacenado.setString(4, factura.getGuian());
            prcProcedimientoAlmacenado.setString(5, factura.getDireccion());
            prcProcedimientoAlmacenado.setDouble(6, factura.getValorVenta());
            prcProcedimientoAlmacenado.setDouble(7, factura.getIgv());
            prcProcedimientoAlmacenado.setDouble(8, factura.getTotal());
            prcProcedimientoAlmacenado.setString(9, factura.getDescripcion());
            prcProcedimientoAlmacenado.setString(10, factura.getMoneda());
            prcProcedimientoAlmacenado.setString(11, factura.getCodigoUnico());
            prcProcedimientoAlmacenado.setString(12, factura.getRnc());
            prcProcedimientoAlmacenado.setString(13, factura.getLectura());
            prcProcedimientoAlmacenado.setInt(14, 1);
            prcProcedimientoAlmacenado.setInt(15, factura.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(16, factura.getCodigo());
            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (FacturaDetalle facturaDetalle : factura.getListaFacturaDetalle()) {
                    liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);
                    facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);
                    registroAfectados = facturaDetalleDAOMySQL.agregar(facturaDetalle);
                    registroAfectados = liquidacionDAOMySQL.modificarFacturado(factura.getNroFactura(), facturaDetalle.getLiquidacion().getCodigo());
                }

            }
            return registroAfectados;
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la Factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //    return 3;
    }

    public int modificarCorrelativo(int emp_id, int numero) throws SQLException, ExcepcionSQLConsulta {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaNumero_Modificar_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, emp_id);
            prcProcedimientoAlmacenado.setInt(2, numero);
            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public int modificar(Factura factura) throws SQLException {
        int registroAfectados = 0;

        FacturaDetalleDAOMySQL facturaDetalleDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, factura.getNroFactura());
            prcProcedimientoAlmacenado.setInt(2, factura.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setDate(3, factura.getFecha());
            prcProcedimientoAlmacenado.setString(4, factura.getGuian());
            prcProcedimientoAlmacenado.setString(5, factura.getDireccion());
            prcProcedimientoAlmacenado.setDouble(6, factura.getValorVenta());
            prcProcedimientoAlmacenado.setDouble(7, factura.getIgv());
            prcProcedimientoAlmacenado.setDouble(8, factura.getTotal());
            prcProcedimientoAlmacenado.setString(9, factura.getDescripcion());
            prcProcedimientoAlmacenado.setString(10, factura.getMoneda());
            prcProcedimientoAlmacenado.setString(11, factura.getCodigoUnico());
            prcProcedimientoAlmacenado.setString(12, factura.getRnc());
            prcProcedimientoAlmacenado.setString(13, factura.getLectura());
            prcProcedimientoAlmacenado.setInt(14, 1);//para la empresa
            prcProcedimientoAlmacenado.setInt(15, factura.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(16, factura.getCodigo());
            registroAfectados = prcProcedimientoAlmacenado.executeUpdate();
            if (registroAfectados == 1) {
                for (FacturaDetalle facturaDetalle : factura.getListaFacturaDetalle()) {
                    facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);
                    facturaDetalle.setPersonal(IniciarSesion.getUsuario());
                    facturaDetalle.setFactura(factura);
                    if (facturaDetalle.getFacturaEstado().equals("Nuevo Modificar")) {
                        registroAfectados = facturaDetalleDAOMySQL.agregar(facturaDetalle);
                    } else {
                        registroAfectados = facturaDetalleDAOMySQL.modificar(facturaDetalle);
                    }
                }
                for (FacturaDetalle facturaDetalle : factura.getListaFacturaEliminadas()) {
                    facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);
                    if (facturaDetalle.getFacturaEstado().equals("Eliminar")) {
                        if (facturaDetalle.getCodigo() != 0) {
                            registroAfectados = facturaDetalleDAOMySQL.eliminar(facturaDetalle);
                        }

                    }
                }

            }
            return registroAfectados;
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar la Factura.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //  JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //     return 3;
    }

    public int eliminar(Factura factura) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, factura.getCodigo());
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Factura_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Nro"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Guian"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_ValorVenta"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Total"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Moneda"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_CodigoUnico"));
                filaTabla.agregarValorCelda(resultado.getString("Empresa_Nombre_Comercial"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido  mostrar Todas las Facturas.\n"
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

    public Factura buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        Factura factura = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        FacturaDetalleDAOMySQL facturaDetalleDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
                facturaDetalleDAOMySQL = new FacturaDetalleDAOMySQL(gestorJDBC);

                factura = new Factura();
                factura.setCodigo(resultado.getInt("Factura_Id"));
                factura.setNroFactura(resultado.getString("Factura_Nro"));
                factura.setProveedorServicio(proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));
                factura.setFecha(resultado.getDate("Factura_Fecha"));
                factura.setGuian(resultado.getString("Factura_Guian"));
                factura.setDireccion(resultado.getString("Factura_Direccion"));
                factura.setDescripcion(resultado.getString("Factura_Descripcion"));
                factura.setMoneda(resultado.getString("Factura_Moneda"));
                factura.setCodigoUnico(resultado.getString("Factura_CodigoUnico"));
                factura.setLectura(resultado.getString("Factura_Lectura"));
                factura.setIgv(resultado.getDouble("Factura_IGV"));
                factura.setTotal(resultado.getDouble("Factura_Total"));
                factura.setValorVenta(resultado.getDouble("Factura_ValorVenta"));

                factura.setListaFacturaDetalle(facturaDetalleDAOMySQL.buscarPorCodigoFactura(factura));
            }

            resultado.close();
            return factura;
        } catch (Exception e) {

            throw new SQLException("No se ha podido Buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //  return null;
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_ObtenerUltimoCodigo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                ultimoCodigo = resultado.getInt("ultimaFactura");
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
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaNumero_ObtenerSerie_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, IdEmpresa);
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

        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaNumero_ObtenerCorrelativo_sp(?)");
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Factura_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Factura_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Nro"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Guian"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_ValorVenta"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_IGV"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Total"));
                filaTabla.agregarValorCelda(resultado.getString("Factura_Moneda"));

                filaTabla.agregarValorCelda(resultado.getString("Factura_CodigoUnico"));
                filaTabla.agregarValorCelda(resultado.getString("Empresa_Nombre_Comercial"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
