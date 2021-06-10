/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;


import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ProcedenciaDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ProcedenciaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Procedencia procedencia) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_Agregar_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, procedencia.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, procedencia.getPersonal().getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la procedencia.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificar(Procedencia procedencia) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_Modificar_sp(?,?,?)");

            prcProcedimientoAlmacenado.setString(1, procedencia.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, procedencia.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, procedencia.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar la procedencia.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int eliminar(Procedencia procedencia) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, procedencia.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar la procedencia.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<Procedencia> mostrarTodos(int estado) throws ExcepcionSQLConsulta, SQLException {

        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            List<Procedencia> listaPersonal = new ArrayList<Procedencia>();
            Procedencia procedencia;
            Object[] registros = new Object[20];
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Personal personal;
            while (resultado.next()) {
                procedencia = new Procedencia();
                personal = new Personal();
                procedencia.setCodigo(resultado.getInt("Procedencia_Id"));
                procedencia.setDescripcion(resultado.getString("Procedencia_Descripcion"));
                personal.setNombres(resultado.getString("Personal"));
                procedencia.setPersonal(personal);
                listaPersonal.add(procedencia);

            }
            resultado.close();
            return listaPersonal;
        } catch (SQLException e) {
         throw new SQLException("No se pudo Mostrar todos.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
        //return null;
    }

    public Procedencia buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Procedencia procedencia = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                procedencia = new Procedencia();
                procedencia.setCodigo(resultado.getInt("Procedencia_Id"));
                procedencia.setDescripcion(resultado.getString("Procedencia_Descripcion"));

            }
            resultado.close();

            return procedencia;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrar(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Procedencia_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Personal"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
          throw new SQLException("No se pudo mostrar.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrar(int estado, JTable table, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Procedencia_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Personal"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
           throw new SQLException("No se pudo mostrar\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void llenarCBOProcedencia(JComboBox<Procedencia> cboProcedencia) throws SQLException {

        try {

            Fila filaTabla;
            ResultSet resultado;
            Procedencia procedencia;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Procedencia_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, 1);

            List<Procedencia> listaPersonal = new ArrayList<Procedencia>();
            Object[] registros = new Object[20];
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Personal personal;
            while (resultado.next()) {
                procedencia = new Procedencia();
                personal = new Personal();
                procedencia.setCodigo(resultado.getInt("Procedencia_Id"));
                procedencia.setDescripcion(resultado.getString("Procedencia_Descripcion"));
                personal.setNombres(resultado.getString("Personal"));
                procedencia.setPersonal(personal);
                cboProcedencia.addItem(procedencia);
            }
            resultado.close();

        } catch (Exception e) {
          throw new SQLException("No se pudo llenar ComboBox de Procendencia.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
