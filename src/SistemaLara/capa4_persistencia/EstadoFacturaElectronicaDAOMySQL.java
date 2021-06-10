/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author FiveCod Software
 */
public class EstadoFacturaElectronicaDAOMySQL {

    private GestorJDBC gestorJDBC;

    public EstadoFacturaElectronicaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }



    public   List<EstadoFacturaElectronica> llenarItemEstadoFacturaElectronica(int estado) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        EstadoFacturaElectronica estadoFacturaElectronica;
        List<EstadoFacturaElectronica> listaEstado=new ArrayList<EstadoFacturaElectronica>();

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("EstadoFacturaElectronica_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                estadoFacturaElectronica = new EstadoFacturaElectronica();
                estadoFacturaElectronica.setCodigo(resultado.getInt("EstadoFacturaElectronica_Id"));
                estadoFacturaElectronica.setDescripcion(resultado.getString("EstadoFacturaElectronica_Descripcion"));
                listaEstado.add(estadoFacturaElectronica);
            }
            resultado.close();

            return listaEstado;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

}
