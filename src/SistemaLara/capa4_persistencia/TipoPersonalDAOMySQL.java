/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.TipoPersonal;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class TipoPersonalDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public TipoPersonalDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(TipoPersonal tipoPersonal) throws SQLException {
        int resultado = 0;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_agregar_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, tipoPersonal.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, tipoPersonal.getEstado().getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el tipo de tipo personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificar(TipoPersonal tipoPersonal) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_Actualizar_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, tipoPersonal.getCodigo());
            prcProcedimientoAlmacenado.setString(2, tipoPersonal.getDescripcion());
            prcProcedimientoAlmacenado.setInt(3, tipoPersonal.getEstado().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el tipo de tipo personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int eliminar(TipoPersonal tipoPersonal) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, tipoPersonal.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el tipo Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public String[] obtenerColumas() {
        String[] listaColumnas = new String[15];
        listaColumnas[0] = "Codigo";
        listaColumnas[1] = "Descripción";
        return listaColumnas;
    }

    public TipoPersonal buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        TipoPersonal tipoPersonal = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_buscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                tipoPersonal = new TipoPersonal();
                tipoPersonal.setCodigo(resultado.getInt("tp.TipoPersonal_Id"));
                tipoPersonal.setDescripcion(resultado.getString("tp.TipoPersonal_Descripcion"));
            }
            resultado.close();
            return tipoPersonal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public DefaultComboBoxModel llenarCBOTipoPersonal(int estado) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        TipoPersonal itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new TipoPersonal();
                itemFacturaElectronica.setCodigo(resultado.getInt("TipoPersonal_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("TipoPersonal_Descripcion"));
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoPersonal_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoPersonal_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
           throw new SQLException("No se pudo Mostrar Tipo Personal.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int i, JTable tablaTipoPersonal, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tablaTipoPersonal.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoPersonal_BuscarTodosPorLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoPersonal_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoPersonal_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            tablaTipoPersonal.setModel(modeloTabla);

        } catch (Exception e) {
           throw new SQLException("No se pudo Mostrar Todos los Tipos de Personal.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }

}
