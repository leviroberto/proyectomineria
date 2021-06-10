/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ClienteEntrante;
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
public class ChequeClienteDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ChequeClienteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(ChequeCliente cheque) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("ChequeCliente_Agregar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, cheque.getClienteEntrante().getCodigo());
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

    public int modificar(ChequeCliente cheque) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("ChequeCliente_Modificar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, cheque.getClienteEntrante().getCodigo());
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

    public int eliminar(ChequeCliente cheque) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_Eliminar_sp(?)");
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_ObtenerUltimoCodigo_sp(?)");
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

    public ChequeCliente buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        ChequeCliente cheque = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL prtAOMySQL;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                prtAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
                cheque = new ChequeCliente();
                cheque.setCodigo(resultado.getInt("ChequeCliente_Id"));
                cheque.setEntregadoA(resultado.getString("ChequeCliente_EntregadoA"));
                cheque.setConcepto(resultado.getString("ChequeCliente_Concepto"));
                cheque.setMonto(resultado.getDouble("ChequeCliente_Monto"));
                cheque.setMoneda(resultado.getString("ChequeCliente_Moneda"));
                cheque.setFechaPago(resultado.getString("ChequeCliente_FechaPago"));

                cheque.setFechaEmision(resultado.getString("ChequeCliente_FechaEmision"));
                cheque.setClienteEntrante(prtAOMySQL.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));

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
        ClienteEntranteDAOMySQL prtAOMySQL;
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            prtAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ChequeCliente_Id"));
                ClienteEntrante clienteEntrante = prtAOMySQL.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(clienteEntrante.generarNombre());
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_EntregadoA"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Concepto"));

                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_FechaEmision"));

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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_CalcularTotalSoles_sp(?)");
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_CalcularTotalDolar_sp(?)");
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ChequeCliente_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ChequeCliente_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_EntregadoA"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Concepto"));

                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_FechaPago"));
                filaTabla.agregarValorCelda(resultado.getString("ChequeCliente_FechaEmision"));

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
