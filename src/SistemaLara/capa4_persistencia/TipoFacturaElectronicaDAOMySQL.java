/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.TipoFacturaElectronica;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;


/**
 *
 * @author FiveCod Software
 */
public class TipoFacturaElectronicaDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public TipoFacturaElectronicaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public void llenarItemTipoFacturaElectronica(int estado, JComboBox combo) throws ExcepcionSQLConsulta {

        ResultSet resultado;
        TipoFacturaElectronica tipoFacturaElectronica;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoFacturaElectronica_MostrarTodos(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                tipoFacturaElectronica = new TipoFacturaElectronica();
                tipoFacturaElectronica.setCodigo(resultado.getInt("TipoFacturaElectronica_Id"));
                tipoFacturaElectronica.setDescripcion(resultado.getString("TipoFacturaElectronica_Descripcion"));
                combo.addItem(tipoFacturaElectronica);
            }
            resultado.close();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }
}
