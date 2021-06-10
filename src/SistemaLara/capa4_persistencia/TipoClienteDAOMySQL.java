/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.TipoCliente;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class TipoClienteDAOMySQL {

    private GestorJDBC gestorJDBC;

    public TipoClienteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(TipoCliente tipoCliente) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_Agregar_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, tipoCliente.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, tipoCliente.getPersonal().getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el tipo de tipo Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificar(TipoCliente tipoCliente) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_Modificar_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, tipoCliente.getCodigo());
            prcProcedimientoAlmacenado.setString(2, tipoCliente.getDescripcion());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el tipo de tipo Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int eliminar(TipoCliente tipoCliente) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, tipoCliente.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el tipo Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<TipoCliente> mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoCliente_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
           throw new ExcepcionSQLConsulta(e);
        }
        return null;
    }

    public TipoCliente buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        TipoCliente tipoCliente = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_buscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                tipoCliente = new TipoCliente();
                tipoCliente.setCodigo(resultado.getInt("TipoCliente_Id"));
                tipoCliente.setDescripcion(resultado.getString("TipoCliente_Descripcion"));
            }
            resultado.close();
            return tipoCliente;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void llenarCBOTipoCliente(int estado, JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        TipoCliente itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new TipoCliente();
                itemFacturaElectronica.setCodigo(resultado.getInt("TipoCliente_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("TipoCliente_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                combo.addItem(itemFacturaElectronica);
            }
            resultado.close();
       
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int i, JTable tablaTipoCliente, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tablaTipoCliente.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_BuscarTodosPorLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoCliente_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            tablaTipoCliente.setModel(modeloTabla);

        } catch (Exception e) {
        throw new SQLException("No se pudo mostrar Todos.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }

}
