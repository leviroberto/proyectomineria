/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.NotaCredito;
import SistemaLara.capa3_dominio.NotaCreditoDetalle;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class NotaCreditoDetalleDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public NotaCreditoDetalleDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(NotaCreditoDetalle notaCreditoDetalle) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("NotaCreditoDetalle_Agregar_sp(?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setString(1, notaCreditoDetalle.getDescripcion());
            prcProcedimientoAlmacenadoDetalle.setDouble(2, notaCreditoDetalle.getValorVenta());
            prcProcedimientoAlmacenadoDetalle.setInt(3, notaCreditoDetalle.getNotaCredito().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(4, notaCreditoDetalle.getPersonal().getCodigo());
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la Nota Credito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //JOptionPane.showMessageDialog(null, e.getMessage());

        }
        // return 0;

    }

    public int modificar(NotaCreditoDetalle notaCreditoDetalle) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("NotaCreditoDetalle_Modificar_sp(?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setString(1, notaCreditoDetalle.getDescripcion());
            prcProcedimientoAlmacenadoDetalle.setDouble(2, notaCreditoDetalle.getValorVenta());
            prcProcedimientoAlmacenadoDetalle.setInt(3, notaCreditoDetalle.getNotaCredito().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(4, notaCreditoDetalle.getCodigo());
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se ha podido modificar la Nota Credito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        // return 5;

    }

    public int eliminar(NotaCreditoDetalle notaCreditoDetalle) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCreditoDetalle_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, notaCreditoDetalle.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Nota redito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<FacturaDetalle> mostrarTodos(int estado) throws ExcepcionSQLConsulta, SQLException {

        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaDetalle_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            List<FacturaDetalle> listaFacturaDetalle = new ArrayList<FacturaDetalle>();
            FacturaDetalle facturaDetalle;
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                facturaDetalle = new FacturaDetalle();
                facturaDetalle.setCodigo(resultado.getInt("tc.FacturaDetalle_Id"));
                facturaDetalle.setDescripcion(resultado.getString("tc.FacturaDetalle_Descripcion"));
                listaFacturaDetalle.add(facturaDetalle);
            }
            resultado.close();
            return listaFacturaDetalle;
        } catch (SQLException e) {
            throw new SQLException("No se ha podido mostrar Todos Factura Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //  return null;
    }

    public FacturaDetalle buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        FacturaDetalle facturaDetalle = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaDetalle_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                facturaDetalle = new FacturaDetalle();
                facturaDetalle.setCodigo(resultado.getInt("FacturaDetalle_Id"));
                facturaDetalle.setDescripcion(resultado.getString("FacturaDetalle_Descripcion"));
            }
            resultado.close();
            return facturaDetalle;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public ArrayList<NotaCreditoDetalle> buscarPorCodigoFactura(NotaCredito notaCredito) throws ExcepcionSQLConsulta {
        NotaCreditoDetalle facturaDetalle = null;
        ResultSet resultado;
        String sentenciaSQL;
        ArrayList<NotaCreditoDetalle> listaFacturaDetalle = new ArrayList<>();
        LiquidacionDAOMySQL liquidacionDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaCreditoDetalle_BuscarPorNotaCredito_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, notaCredito.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            liquidacionDAOMySQL = new LiquidacionDAOMySQL(gestorJDBC);
            while (resultado.next()) {

                facturaDetalle = new NotaCreditoDetalle();
                facturaDetalle.setCodigo(resultado.getInt("NotaCreditoDetalle_Id"));
                facturaDetalle.setDescripcion(resultado.getString("NotaCreditoDetalle_Descripcion"));
                facturaDetalle.setValorVenta(resultado.getDouble("NotaCreditoDetalle_ValorVenta"));

                facturaDetalle.setNotaCredito(notaCredito);
                facturaDetalle.setEstadoNotaCredito("Crear");
                listaFacturaDetalle.add(facturaDetalle);
            }
            resultado.close();
            return listaFacturaDetalle;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //   throw new ExcepcionSQLConsulta(e);
        }
        return null;
    }

    public DefaultComboBoxModel llenarCBOFacturaDetalle(int estado) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        FacturaDetalle itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaDetalle_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new FacturaDetalle();
                itemFacturaElectronica.setCodigo(resultado.getInt("FacturaDetalle_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("FacturaDetalle_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                modelo.addElement(itemFacturaElectronica);
            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("FacturaDetalle_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("FacturaDetalle_Id"));
                filaTabla.agregarValorCelda(resultado.getString("FacturaDetalle_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));
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
