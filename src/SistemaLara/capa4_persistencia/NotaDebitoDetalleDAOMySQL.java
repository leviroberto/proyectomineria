/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebitoDetalle;
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
public class NotaDebitoDetalleDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public NotaDebitoDetalleDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(NotaDebitoDetalle notaDebitoDetalle) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("NotaDebitoDetalle_Agregar_sp(?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setString(1, notaDebitoDetalle.getCantidad());
            prcProcedimientoAlmacenadoDetalle.setString(2, notaDebitoDetalle.getUnidad());
            prcProcedimientoAlmacenadoDetalle.setString(3, notaDebitoDetalle.getDescripcion());
            prcProcedimientoAlmacenadoDetalle.setDouble(4, notaDebitoDetalle.getPrecioUnitario());
            prcProcedimientoAlmacenadoDetalle.setDouble(5, notaDebitoDetalle.getValorVenta());
            prcProcedimientoAlmacenadoDetalle.setInt(6, notaDebitoDetalle.getNotaDebito().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(7, notaDebitoDetalle.getReintegro().getCodigo());
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
//            throw new SQLException("No se pudo registrar la Nota Debito Detalle.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 0;

    }

    public int modificar(NotaDebitoDetalle notaDebitoDetalle) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("NotaDebitoDetalle_Modificar_sp(?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setString(1, notaDebitoDetalle.getCantidad());
            prcProcedimientoAlmacenadoDetalle.setString(2, notaDebitoDetalle.getUnidad());
            prcProcedimientoAlmacenadoDetalle.setString(3, notaDebitoDetalle.getDescripcion());
            prcProcedimientoAlmacenadoDetalle.setDouble(4, notaDebitoDetalle.getPrecioUnitario());
            prcProcedimientoAlmacenadoDetalle.setDouble(5, notaDebitoDetalle.getValorVenta());
            prcProcedimientoAlmacenadoDetalle.setInt(6, notaDebitoDetalle.getNotaDebito().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(7, notaDebitoDetalle.getCodigo());
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se ha podido modificar la nota Debito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        // return 5;

    }

    public int eliminar(NotaDebitoDetalle notaDebitoDetalle) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoDetalle_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, notaDebitoDetalle.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el nota Debito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<NotaDebitoDetalle> mostrarTodos(int estado) throws ExcepcionSQLConsulta, SQLException {

        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("notaDebitoDetalle_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            List<NotaDebitoDetalle> listanotaDebitoDetalle = new ArrayList<NotaDebitoDetalle>();
            NotaDebitoDetalle notaDebitoDetalle;
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                notaDebitoDetalle = new NotaDebitoDetalle();
                notaDebitoDetalle.setCodigo(resultado.getInt("tc.notaDebitoDetalle_Id"));
                notaDebitoDetalle.setDescripcion(resultado.getString("tc.notaDebitoDetalle_Descripcion"));
                listanotaDebitoDetalle.add(notaDebitoDetalle);
            }
            resultado.close();
            return listanotaDebitoDetalle;
        } catch (SQLException e) {
            throw new SQLException("No se ha podido mostrar Todos notaDebito Detalle.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //  return null;
    }

    public NotaDebitoDetalle buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        NotaDebitoDetalle facturaDetalle = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("notaDebitoDetalle_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                facturaDetalle = new NotaDebitoDetalle();
                facturaDetalle.setCodigo(resultado.getInt("notaDebitoDetalle_Id"));
                facturaDetalle.setDescripcion(resultado.getString("notaDebitoDetalle_Descripcion"));
            }
            resultado.close();
            return facturaDetalle;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public ArrayList<NotaDebitoDetalle> buscarPorCodigonotaDebito(NotaDebito notaDebito) throws ExcepcionSQLConsulta {
        NotaDebitoDetalle notaDebitoDetalle = null;
        ResultSet resultado;
        String sentenciaSQL;
        ArrayList<NotaDebitoDetalle> listanotaDebitoDetalle = new ArrayList<>();
        ReintegroDAOMySQL reintegroDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoDetalle_BuscarPornotaDebito_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, notaDebito.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                reintegroDAOMySQL = new ReintegroDAOMySQL(gestorJDBC);
                notaDebitoDetalle = new NotaDebitoDetalle();
                notaDebitoDetalle.setCodigo(resultado.getInt("NotaDebitoDetalle_Id"));
                notaDebitoDetalle.setCantidad(resultado.getString("NotaDebitoDetalle_Cantidad"));
                notaDebitoDetalle.setUnidad(resultado.getString("NotaDebitoDetalle_Unidad"));
                notaDebitoDetalle.setDescripcion(resultado.getString("NotaDebitoDetalle_Descripcion"));
                notaDebitoDetalle.setPrecioUnitario(resultado.getDouble("NotaDebitoDetalle_PrecioUnitario"));
                notaDebitoDetalle.setValorVenta(resultado.getDouble("NotaDebitoDetalle_ValorVenta"));
                notaDebitoDetalle.setNotaDebito(notaDebito);
                notaDebitoDetalle.setNotaDebitoEstado("Crear");
                notaDebitoDetalle.setReintegro(reintegroDAOMySQL.buscarReintegroPorLote(resultado.getInt("Reintegro_Lote")));
                listanotaDebitoDetalle.add(notaDebitoDetalle);

            }
            resultado.close();
            return listanotaDebitoDetalle;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public DefaultComboBoxModel llenarCBOnotaDebitoDetalle(int estado) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        NotaDebitoDetalle itemnotaDebitoElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("notaDebitoDetalle_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemnotaDebitoElectronica = new NotaDebitoDetalle();
                itemnotaDebitoElectronica.setCodigo(resultado.getInt("notaDebitoDetalle_Id"));
                itemnotaDebitoElectronica.setDescripcion(resultado.getString("notaDebitoDetalle_Descripcion"));
//                combo.addItem(itemnotaDebitoElectronica);
                modelo.addElement(itemnotaDebitoElectronica);
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("notaDebitoDetalle_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("notaDebitoDetalle_Id"));
                filaTabla.agregarValorCelda(resultado.getString("notaDebitoDetalle_Descripcion"));
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
