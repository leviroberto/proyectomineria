/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
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
public class ChequeProveedorDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ChequeProveedorDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(ChequeProveedor cheque) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("Cheque_Agregar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, cheque.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setString(2, cheque.getConcepto());
            prcProcedimientoAlmacenadoDetalle.setString(3, cheque.getEntregadoA());
            prcProcedimientoAlmacenadoDetalle.setDouble(4, cheque.getMonto());
            prcProcedimientoAlmacenadoDetalle.setString(5, cheque.getLectura());
            prcProcedimientoAlmacenadoDetalle.setString(6, cheque.getMoneda());
            prcProcedimientoAlmacenadoDetalle.setString(7, cheque.getFechaEmision());
            prcProcedimientoAlmacenadoDetalle.setString(8, cheque.getFechaPago());
            prcProcedimientoAlmacenadoDetalle.setInt(9, cheque.getEmpresa().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(10, cheque.getPersonal().getCodigo());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el Cheque.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //   JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //  return 0;

    }

    public int modificar(ChequeProveedor cheque) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("Cheque_Modificar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, cheque.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setString(2, cheque.getConcepto());
            prcProcedimientoAlmacenadoDetalle.setString(3, cheque.getEntregadoA());
            prcProcedimientoAlmacenadoDetalle.setDouble(4, cheque.getMonto());
            prcProcedimientoAlmacenadoDetalle.setString(5, cheque.getLectura());
            prcProcedimientoAlmacenadoDetalle.setString(6, cheque.getMoneda());
            prcProcedimientoAlmacenadoDetalle.setString(7, cheque.getFechaEmision());
            prcProcedimientoAlmacenadoDetalle.setString(8, cheque.getFechaPago());
            prcProcedimientoAlmacenadoDetalle.setInt(9, cheque.getPersonal().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(10, cheque.getCodigo());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Cheque.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //         JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //    return 0;

    }

    public int eliminar(ChequeProveedor cheque) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, cheque.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el cheque.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int obtenerUltimoCodigo(int estado) throws SQLException {

        try {
            ResultSet resultado = null;
            int ultimoCodigo = 0;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_ObtenerUltimoCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {

                ultimoCodigo = resultado.getInt("ultimo");

            }
            resultado.close();
            return ultimoCodigo;
        } catch (Exception e) {
            throw new SQLException("No se pudo obtener el ultimo codigo .\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public ChequeProveedor buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        ChequeProveedor cheque = null;
        ResultSet resultado;
        String sentenciaSQL;
        ProveedorServicioDAOMySQL prtAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                prtAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                cheque = new ChequeProveedor();
                cheque.setCodigo(resultado.getInt("Cheque_Id"));
                cheque.setEntregadoA(resultado.getString("Cheque_EntregadoA"));
                cheque.setConcepto(resultado.getString("Cheque_Concepto"));
                cheque.setMonto(resultado.getDouble("Cheque_Monto"));
                cheque.setMoneda(resultado.getString("Cheque_Moneda"));
                cheque.setFechaPago(resultado.getString("Cheque_FechaPago"));
                cheque.setFechaEmision(resultado.getString("Cheque_FechaEmision"));
                cheque.setProveedorServicio(prtAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));

            }
            resultado.close();
            return cheque;
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Cheque_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_EntregadoA"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_Concepto"));

                filaTabla.agregarValorCelda(resultado.getString("Cheque_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_FechaEmision"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido  mostrar todos los Cheques.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularMontoSoles(int i, FCMaterialTextField txtTotalSole) throws SQLException {
        ResultSet resultado;

        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_CalcularTotalSoles_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                txtTotalSole.setText(String.valueOf(resultado.getDouble("total")));
            }
            resultado.close();
        } catch (Exception e) {
            throw new SQLException("No se ha podido  calcular el Monto Soles.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void calcularMontoDolar(int i, FCMaterialTextField txtTotalDolar) throws SQLException {
        ResultSet resultado;

        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_CalcularTotalDolar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                txtTotalDolar.setText(String.valueOf(resultado.getDouble("totals")));
            }
            resultado.close();
        } catch (Exception e) {
             throw new SQLException("No se ha podido calcular el Monto Dolar.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cheque_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Cheque_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_EntregadoA"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_Concepto"));

                filaTabla.agregarValorCelda(resultado.getString("Cheque_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("Cheque_FechaEmision"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
             throw new SQLException("No se ha podido  mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }
}
