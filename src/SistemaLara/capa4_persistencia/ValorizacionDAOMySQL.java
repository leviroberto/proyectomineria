/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class ValorizacionDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public ValorizacionDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Valorizacion valorizacion) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("Valorizacion_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, valorizacion.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setString(2, valorizacion.getCostoTransporteTrujilloSoles());
            prcProcedimientoAlmacenadoDetalle.setString(3, valorizacion.getCostoTransporteNascaSoles());
            prcProcedimientoAlmacenadoDetalle.setString(4, valorizacion.getTotalAnalisis());
            prcProcedimientoAlmacenadoDetalle.setString(5, valorizacion.getTotalUS());

            prcProcedimientoAlmacenadoDetalle.setString(6, valorizacion.getTotalPorcentaje());
            prcProcedimientoAlmacenadoDetalle.setString(7, valorizacion.getAdelantos());
            prcProcedimientoAlmacenadoDetalle.setString(8, valorizacion.getOtrosGastos());
            prcProcedimientoAlmacenadoDetalle.setString(9, valorizacion.getTotalGastos());
            prcProcedimientoAlmacenadoDetalle.setString(10, valorizacion.getTotalPagar());

            prcProcedimientoAlmacenadoDetalle.setString(11, valorizacion.getValorizacionEstado());
            prcProcedimientoAlmacenadoDetalle.setString(12, valorizacion.getCambio());
            prcProcedimientoAlmacenadoDetalle.setString(13, valorizacion.getCostoTransporteTrujilloDolar());
            prcProcedimientoAlmacenadoDetalle.setString(14, valorizacion.getCostoTransporteNascaDolar());
            prcProcedimientoAlmacenadoDetalle.setString(15, valorizacion.getcLotes());

            prcProcedimientoAlmacenadoDetalle.setDate(16, valorizacion.getFechaId());
            prcProcedimientoAlmacenadoDetalle.setString(17, valorizacion.getDescuentoSoles());
            prcProcedimientoAlmacenadoDetalle.setString(18, valorizacion.getTmh());
            prcProcedimientoAlmacenadoDetalle.setString(19, valorizacion.getTarifaPorcentaje());

            prcProcedimientoAlmacenadoDetalle.setString(20, valorizacion.getPolicia());
            prcProcedimientoAlmacenadoDetalle.setString(21, valorizacion.getTarifaAnalisis());
            prcProcedimientoAlmacenadoDetalle.setString(22, valorizacion.getToneladas());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //    JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //    return 0;

    }

    public int modificar(Valorizacion valorizacion) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("Valorizacion_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, valorizacion.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setString(2, valorizacion.getCostoTransporteTrujilloSoles());
            prcProcedimientoAlmacenadoDetalle.setString(3, valorizacion.getCostoTransporteNascaSoles());
            prcProcedimientoAlmacenadoDetalle.setString(4, valorizacion.getTotalAnalisis());
            prcProcedimientoAlmacenadoDetalle.setString(5, valorizacion.getTotalUS());

            prcProcedimientoAlmacenadoDetalle.setString(6, valorizacion.getTotalPorcentaje());
            prcProcedimientoAlmacenadoDetalle.setString(7, valorizacion.getAdelantos());
            prcProcedimientoAlmacenadoDetalle.setString(8, valorizacion.getOtrosGastos());
            prcProcedimientoAlmacenadoDetalle.setString(9, valorizacion.getTotalGastos());
            prcProcedimientoAlmacenadoDetalle.setString(10, valorizacion.getTotalPagar());

            prcProcedimientoAlmacenadoDetalle.setString(11, valorizacion.getCambio());
            prcProcedimientoAlmacenadoDetalle.setString(12, valorizacion.getCostoTransporteTrujilloDolar());
            prcProcedimientoAlmacenadoDetalle.setString(13, valorizacion.getCostoTransporteNascaDolar());
            prcProcedimientoAlmacenadoDetalle.setString(14, valorizacion.getcLotes());

            prcProcedimientoAlmacenadoDetalle.setDate(15, valorizacion.getFechaId());
            prcProcedimientoAlmacenadoDetalle.setString(16, valorizacion.getDescuentoSoles());
            prcProcedimientoAlmacenadoDetalle.setString(17, valorizacion.getTmh());
            prcProcedimientoAlmacenadoDetalle.setString(18, valorizacion.getTarifaPorcentaje());

            prcProcedimientoAlmacenadoDetalle.setString(19, valorizacion.getPolicia());
            prcProcedimientoAlmacenadoDetalle.setString(20, valorizacion.getTarifaAnalisis());
            prcProcedimientoAlmacenadoDetalle.setString(21, valorizacion.getToneladas());
            prcProcedimientoAlmacenadoDetalle.setInt(22, valorizacion.getCodigo());
            prcProcedimientoAlmacenadoDetalle.setString(23, valorizacion.getValorizacionEstado());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo modificar la valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //   JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //    return 0;

    }

    public int eliminar(Valorizacion valorizacion) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, valorizacion.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public Valorizacion buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Valorizacion valorizacion = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_Buscar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
                valorizacion = new Valorizacion();
                valorizacion.setCodigo(resultado.getInt("Valorizacion_codigo"));
                valorizacion.setClienteEntrante(clienteEntranteDAOMySQL.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                valorizacion.setTmh(resultado.getString("Valorizacion_Tmh"));
                valorizacion.setCostoTransporteTrujilloSoles(resultado.getString("Valorizacion_CostoTraTrujiSoles"));
                valorizacion.setCostoTransporteNascaSoles(resultado.getString("Valorizacion_CostTraNasSoles"));
                valorizacion.setTotalAnalisis(resultado.getString("Valorizacion_TotalAnalisis"));
                valorizacion.setTotalUS(resultado.getString("Valorizacion_TotalUS"));
                valorizacion.setTotalPorcentaje(resultado.getString("Valorizacion_TotalPorcentaje"));
                valorizacion.setAdelantos(resultado.getString("Valorizacion_Adelanto"));
                valorizacion.setOtrosGastos(resultado.getString("Valorizacion_OtrosGastos"));
                valorizacion.setTotalGastos(resultado.getString("Valorizacion_TotalGastos"));
                valorizacion.setTotalPagar(resultado.getString("Valorizacion_TotalPagar"));
                valorizacion.setValorizacionEstado(resultado.getString("Valorizacion_Estado"));
                valorizacion.setCambio(resultado.getString("Valorizacion_Cambio"));
                valorizacion.setCostoTransporteTrujilloDolar(resultado.getString("Valorizacion_CostoTraTruDolar"));
                valorizacion.setCostoTransporteNascaDolar(resultado.getString("Valorizacion_CostTraNascDolar"));
                valorizacion.setcLotes(resultado.getString("Valorizacion_CLotes"));
                valorizacion.setTotalPorcentaje(resultado.getString("Valorizacion_TotalPorcentaje"));
                valorizacion.setFechaId(resultado.getDate("Valorizacion_FechaId"));
                valorizacion.setDescuentoSoles(resultado.getString("Valorizacion_DescuentoSoles"));
                valorizacion.setTarifaAnalisis(resultado.getString("Valoracion_TarifaAnalisis"));
                valorizacion.setTarifaPorcentaje(resultado.getString("Valorizacion_TarifaPorcentaje"));
                valorizacion.setPolicia(resultado.getString("Valoracion_Policia"));
                valorizacion.setToneladas(resultado.getString("Valorizacion_Toneladas"));

            }
            resultado.close();
            return valorizacion;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarParaDetalleValorizacion(JTable tableLiquidacion, int cliente, Date fecha) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableLiquidacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_BuscarPorCodigoCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, cliente);
            prcProcedimientoAlmacenado.setDate(2, fecha);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Valorizacion_codigo"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_CostoTraTrujiSoles"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_CostTraNasSoles"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalAnalisis"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalUS"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalPorcentaje"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Adelanto"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_OtrosGastos"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalGastos"));

                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_FechaId"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Cambio"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_CostoTraTruDolar"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_CostTraNascDolar"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_CLotes"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalPorcentaje"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_TotalPagar"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_DescuentoSoles"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Estado"));

                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableLiquidacion.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Detalle Valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Valorizacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int obtenerUltimoCodigo() throws SQLException {
        ResultSet resultado;
        int ultimoCodigo = 0;
        try {

            String sentenciaSQL;

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_ObtenerUltimoCodigo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                ultimoCodigo = resultado.getInt("codigo");

            }
            resultado.close();
            return ultimoCodigo - 1;

        } catch (Exception e) {
            throw new SQLException("No se pudo obtener el ultimo codigo de la valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //  return 0;

    }

    public String obtenerTipoCambioPorCliente(int codigo) throws SQLException {
        ResultSet resultado;
        String cambio = "0.00";
        try {

            String sentenciaSQL;

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_obtenerTipoCambioPorPersona_sp(?)");
            prcProcedimientoAlmacenado.setInt("codigo", codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                cambio = resultado.getString("cambio");

            }
            resultado.close();
            return cambio;

        } catch (Exception e) {
            throw new SQLException("No se pudo obtener el cambio del cliente  .\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //  return 0;

    }

    public void agregarTipoCambioPorcentajePorClienteEntrante(int codigo, String porcentaje) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_AgregarPorcentajeCambioCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            prcProcedimientoAlmacenado.setString(2, porcentaje);
            prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void actualizarTipoCambioPorcentajePorClienteEntrante(int codigo, String porcentaje) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Valorizacion_ActualizarPorcentajeCambioCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            prcProcedimientoAlmacenado.setString(2, porcentaje);
            prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

}
