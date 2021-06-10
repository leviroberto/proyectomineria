/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.PagoPersonal;
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
public class PagoPersonalDAOMySQL {

    private GestorJDBC gestorJDBC;

    public PagoPersonalDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public PagoPersonal buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        PagoPersonal pagoPersonal = null;
        ResultSet resultado;
        String sentenciaSQL;
        ContratoDAOMySQL contratoDAOMySQL;
        try {
            contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                pagoPersonal = new PagoPersonal();
                pagoPersonal.setCodigo(resultado.getInt("PagoPersonal_Id"));
                pagoPersonal.setContrato(contratoDAOMySQL.buscarPorCodigo(resultado.getInt("Contrato_Id")));
                pagoPersonal.setFechaPago(resultado.getDate("PagoPersonal_Fecha"));
                pagoPersonal.setMontoPagado(resultado.getDouble("PagoPersonal_Monto"));

            }
            resultado.close();
            return pagoPersonal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public int ingresar(PagoPersonal pagoPersonal) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_Agregar_sp(?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, pagoPersonal.getMontoPagado());
            prcProcedimientoAlmacenado.setDate(2, pagoPersonal.getFechaPago());
            prcProcedimientoAlmacenado.setInt(3, pagoPersonal.getContrato().getCodigo());
            prcProcedimientoAlmacenado.setInt(4, pagoPersonal.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo Registrar Pago Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //     return 0;
    }

    public int eliminar(PagoPersonal pagoPersonal) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, pagoPersonal.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Pago Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //     return 0;

    }

    public void mostrarPagosPersonal(JTable table) throws ExcepcionSQLConsulta, SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_MostrarTodos_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Fecha"));
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se pudo Mostrar Pagos Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int verificarSiEstaPagadoContrato(Date fecha, int contrato) throws ExcepcionSQLConsulta {

        ResultSet resultado;
        String sentenciaSQL;
        int resultadoVerificacion = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Pago_VerificarSiEstaPagadoContrato_sp(?,?)");
            prcProcedimientoAlmacenado.setDate(1, fecha);
            prcProcedimientoAlmacenado.setInt(2, contrato);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {

                resultadoVerificacion = resultado.getInt("suma");

            }
            resultado.close();

            return resultadoVerificacion;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarPagosPersonal(int i, JTable table, String texto) throws SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Fecha"));
                fila.agregarValorCelda(resultado.getString("PagoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se pudo mostrar Pagos Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int obtenerUltimoCodigoInsertado() throws ExcepcionSQLConsulta {

        ResultSet resultado;
        String sentenciaSQL;
        int resultadoVerificacion = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("PagoPersonal_ObtenerUltimoCodigoInsertado_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {

                resultadoVerificacion = resultado.getInt("codigo");

            }
            resultado.close();

            return resultadoVerificacion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //throw new ExcepcionSQLConsulta(e);
        }
        return 0;

    }
}
