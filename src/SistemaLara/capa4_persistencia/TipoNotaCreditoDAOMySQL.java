/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.TipoNotaCD;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class TipoNotaCreditoDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public TipoNotaCreditoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public void llenarCBOTipoProveedor(JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        TipoNotaCD tipoNotaCD;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoNotaCredito_MostrarTodos_sp()");

        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                tipoNotaCD = new TipoNotaCD();
                tipoNotaCD.setCodigo(resultado.getInt("TipoNotaCredito_id"));
                tipoNotaCD.setDescripcion(resultado.getString("TipoNotaCredito_descripcion"));
                combo.addItem(tipoNotaCD);
            }
            resultado.close();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public TipoNotaCD buscarPorCodigo(int aInt) throws SQLException, ExcepcionSQLConsulta {

        ResultSet resultado;
        TipoNotaCD tipoNotaCD = null;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoNotaCredito_BuscarPorCodigo_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, aInt);

        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                tipoNotaCD = new TipoNotaCD();
                tipoNotaCD.setCodigo(resultado.getInt("TipoNotaCredito_id"));
                tipoNotaCD.setDescripcion(resultado.getString("TipoNotaCredito_descripcion"));
            }
            resultado.close();
            return tipoNotaCD;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }
}
