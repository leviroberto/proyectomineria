/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Concepto;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class ConceptoDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ConceptoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Concepto concepto) throws SQLException {
        int resultado = 0;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_Agregar_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, concepto.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, concepto.getPersonal().getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar El Concepto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificar(Concepto concepto) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_Modificar_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, concepto.getCodigo());
            prcProcedimientoAlmacenado.setString(2, concepto.getDescripcion());
            prcProcedimientoAlmacenado.setInt(3, concepto.getPersonal().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Concepto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int eliminar(Concepto concepto) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, concepto.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el concepto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public String[] obtenerColumas() {
        String[] listaColumnas = new String[15];
        listaColumnas[0] = "Codigo";
        listaColumnas[1] = "Descripción";
        return listaColumnas;
    }

    public Concepto buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Concepto concepto = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                concepto = new Concepto();
                concepto.setCodigo(resultado.getInt("Concepto_Id"));
                concepto.setDescripcion(resultado.getString("Concepto_Descripcion"));
            }
            resultado.close();
            return concepto;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void llenarCBOConcepto(int estado, JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        Concepto itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new Concepto();
                itemFacturaElectronica.setCodigo(resultado.getInt("Concepto_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("Concepto_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                combo.addItem(itemFacturaElectronica);
            }
            resultado.close();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Concepto_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Concepto_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Concepto_Descripcion"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
          throw new SQLException("No se ha podido  mostrar Todos los Conceptos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

}
