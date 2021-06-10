/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Permisos;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PermisosDAOMySQL {

    private GestorJDBC gestorJDBC;

    public PermisosDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public Permisos buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Permisos permisos = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Permisos_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                permisos = new Permisos();
                permisos.setCodigo(resultado.getInt("Permisos_Id"));
                permisos.setDescripcion(resultado.getString("Permisos_Descripcion"));
            }
            resultado.close();
            return permisos;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void llenarCBOPermisos(int estado, JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        Permisos itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Permisos_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new Permisos();
                itemFacturaElectronica.setCodigo(resultado.getInt("Permisos_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("Permisos_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                combo.addItem(itemFacturaElectronica);
            }
            resultado.close();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int estado, JTable table, int codigoPersonal) throws SQLException {
        ResultSet resultado;

        DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Permisos_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, codigoPersonal);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {

                registros[0] = Boolean.FALSE;
                registros[1] = resultado.getInt("Permisos_Id");
                registros[2] = resultado.getString("Permisos_Descripcion");
                modeloTabla.addRow(registros);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
        throw new SQLException("No se pudo mostrar Todos.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }

}
