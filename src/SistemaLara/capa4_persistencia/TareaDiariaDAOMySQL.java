/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.TareaDiaria;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.Personal;
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
public class TareaDiariaDAOMySQL {

    private GestorJDBC gestorJDBC;

    public TareaDiariaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public TareaDiaria buscarPorCodigo(int codigoTareaDiaria) throws ExcepcionSQLConsulta {
        TareaDiaria tareaDiaria = null;
        ResultSet resultado;
        String sentenciaSQL;

        PersonalDAOMySQL personalDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigoTareaDiaria);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                tareaDiaria = new TareaDiaria();
                tareaDiaria.setCodigo(resultado.getInt("TareaDiaria_Id"));
                tareaDiaria.setDescripcion(resultado.getString("TareaDiaria_Descripcion"));
                tareaDiaria.setFecha(resultado.getDate("TareaDiaria_Fecha"));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                tareaDiaria.setEstado(estado);
            }
            resultado.close();
            return tareaDiaria;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrar(JTable table) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        TareaDiaria tareaDiaria;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_MostrarTodos_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("TareaDiaria_Id"));
                Personal personal = personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id"));
                fila.agregarValorCelda(personal.getGenerarNombre());
                fila.agregarValorCelda(resultado.getString("TareaDiaria_FechaTareaDiaria"));
                fila.agregarValorCelda(resultado.getString("TareaDiaria_FechaFin"));
                fila.agregarValorCelda(resultado.getInt("TareaDiaria_TotalDiasPagar"));
                fila.agregarValorCelda(resultado.getDouble("TareaDiaria_Sueldo"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
//

    public int agregar(TareaDiaria tareaDiaria) throws Exception {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_Agregar_sp(?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, tareaDiaria.getEmpresa().getCodigo());
            prcProcedimientoAlmacenado.setInt(2, tareaDiaria.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setDate(3, tareaDiaria.getFecha());
            prcProcedimientoAlmacenado.setString(4, tareaDiaria.getDescripcion());
            prcProcedimientoAlmacenado.setInt(5, tareaDiaria.getEstado().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo Registrar Tarea Diaria.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
         //   JOptionPane.showMessageDialog(null, e.getMessage());
        }
       // return 0;
    }
//

    public int modificar(TareaDiaria tareaDiaria) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_Modificar_sp(?,?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, tareaDiaria.getCodigo());
            prcProcedimientoAlmacenado.setString(2, tareaDiaria.getDescripcion());
            prcProcedimientoAlmacenado.setDate(3, tareaDiaria.getFecha());
            prcProcedimientoAlmacenado.setInt(4, tareaDiaria.getEstado().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el tarea Diaria.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }
//

    public int eliminar(int codigo) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el tarea Diaria.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void llenarItemEstado(JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        Estado itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Estado_MostrarTodos_sp()");
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new Estado();
                itemFacturaElectronica.setCodigo(resultado.getInt("Estado_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("Estado_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                combo.addItem(itemFacturaElectronica);
            }
            resultado.close();

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarTareaDiariasPorPersonal(JTable table, int codigo) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        TareaDiaria tareaDiaria;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_MostrarTodosPorPersonal_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("TareaDiaria_Id"));
                Personal personal = personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id"));
                fila.agregarValorCelda(personal.getGenerarNombre());
                fila.agregarValorCelda(resultado.getString("TareaDiaria_FechaTareaDiaria"));
                fila.agregarValorCelda(resultado.getString("TareaDiaria_FechaFin"));
                fila.agregarValorCelda(resultado.getInt("TareaDiaria_TotalDiasPagar"));
                fila.agregarValorCelda(resultado.getDouble("TareaDiaria_Sueldo"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarPorHacer(JTable table, int estado) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        TareaDiaria tareaDiaria;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("TareaDiaria_Id"));
                fila.agregarValorCelda(resultado.getString("TareaDiaria_Descripcion"));
                fila.agregarValorCelda(resultado.getString("TareaDiaria_Fecha"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));

                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public int modificarTareaDiariaPorEstado(TareaDiaria tareaDiaria) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_ModificarPorEstado_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, tareaDiaria.getCodigo());
            prcProcedimientoAlmacenado.setInt(2, tareaDiaria.getEstado().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo modificar tarea diiaria por Estado .\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int modificarTareaEstadoNotificacion(TareaDiaria tareaDiaria) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_CambiarEstadoNotificacion_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, tareaDiaria.getCodigo());
            prcProcedimientoAlmacenado.setString(2, tareaDiaria.getEstadoTarea());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar Tarea Por estado Notificacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<TareaDiaria> ObtenerListaTareasPorHacer() throws ExcepcionSQLConsulta {
        TareaDiaria tareaDiaria = null;
        ResultSet resultado;
        String sentenciaSQL;

        PersonalDAOMySQL personalDAOMySQL;
        List<TareaDiaria> listaTareas = new ArrayList<>();

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TareaDiaria_ObtenerListaTareasPorHacer_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                tareaDiaria = new TareaDiaria();
                tareaDiaria.setCodigo(resultado.getInt("TareaDiaria_Id"));
                tareaDiaria.setDescripcion(resultado.getString("TareaDiaria_Descripcion"));
                tareaDiaria.setFecha(resultado.getDate("TareaDiaria_Fecha"));
                tareaDiaria.setEstadoTarea(resultado.getString("TareaDiaria_EstadoNotificacion"));
                tareaDiaria.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id")));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                tareaDiaria.setEstado(estado);
                listaTareas.add(tareaDiaria);

            }
            resultado.close();
            return listaTareas;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
}
