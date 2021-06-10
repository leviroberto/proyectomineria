/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FiveCod Software
 */
public class EstadoDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public EstadoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Estado estado) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Estado_Agregar_sp(?)");
            prcProcedimientoAlmacenado.setString(1, estado.getDescripcion());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el tipo de estado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int modificar(Estado estado) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Estado_Actualizar_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado.getCodigo());
            prcProcedimientoAlmacenado.setString(2, estado.getDescripcion());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el tipo de estado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

 

    public DefaultTableModel mostrarTodos(int estado) throws ExcepcionSQLConsulta {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Estado_MostrarTodos_sp()");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                registros[0] = resultado.getInt("e.Estado_Id");
                registros[1] = resultado.getString("e.Estado_Descripcion");
                modelo.addRow(registros);

            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public Estado buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Estado estado = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Estado_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                estado = new Estado();
                estado.setCodigo(resultado.getInt("e.Estado_Id"));
                estado.setDescripcion(resultado.getString("e.Estado_Descripcion"));

            }
            resultado.close();
            return estado;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

}
