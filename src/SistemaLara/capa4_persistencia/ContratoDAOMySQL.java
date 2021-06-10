/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Contrato;
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
public class ContratoDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ContratoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public Contrato buscarPorCodigo(int codigoContrato) throws ExcepcionSQLConsulta {
        Contrato contrato = null;
        ResultSet resultado;
        String sentenciaSQL;

        PersonalDAOMySQL personalDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigoContrato);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                contrato = new Contrato();
                contrato.setCodigo(resultado.getInt("Contrato_Id"));
                contrato.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id")));
                contrato.setFechaIninicioContrato(resultado.getDate("Contrato_FechaContrato"));
                contrato.setFechaTerminoContrato(resultado.getDate("Contrato_FechaFin"));
                contrato.setTotalDiasPagar(resultado.getInt("Contrato_TotalDiasPagar"));
                contrato.setSueldo(resultado.getDouble("Contrato_Sueldo"));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                contrato.setEstado(estado);

            }
            resultado.close();
            return contrato;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrar(JTable table) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_MostrarTodos_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("Contrato_Id"));
                Personal personal = personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id"));
                fila.agregarValorCelda(personal.getGenerarNombre());
                fila.agregarValorCelda(resultado.getString("Contrato_FechaContrato"));
                fila.agregarValorCelda(resultado.getString("Contrato_FechaFin"));
                fila.agregarValorCelda(resultado.getInt("Contrato_TotalDiasPagar"));
                fila.agregarValorCelda(resultado.getDouble("Contrato_Sueldo"));
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

    public int agregar(Contrato contrato) throws Exception {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_Agregar_sp(?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, contrato.getSueldo());
            prcProcedimientoAlmacenado.setInt(2, contrato.getTotalDiasPagar());
            prcProcedimientoAlmacenado.setDate(3, contrato.getFechaIninicioContrato());
            prcProcedimientoAlmacenado.setDate(4, contrato.getFechaTerminoContrato());
            prcProcedimientoAlmacenado.setInt(5, contrato.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, contrato.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo Registrar el Contrato.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        //    JOptionPane.showMessageDialog(null, e.getMessage());
        }
      //  return 0;
    }
//

    public int modificar(Contrato contrato) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_Modificar_sp(?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, contrato.getSueldo());
            prcProcedimientoAlmacenado.setInt(2, contrato.getTotalDiasPagar());
            prcProcedimientoAlmacenado.setDate(3, contrato.getFechaIninicioContrato());
            prcProcedimientoAlmacenado.setDate(4, contrato.getFechaTerminoContrato());
            prcProcedimientoAlmacenado.setInt(5, contrato.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, contrato.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Contrato.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
      //      JOptionPane.showMessageDialog(null, e.getMessage());

        }
      //  return 0;
    }
//

    public int eliminar(int codigo) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el contrato.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int obtenerUltimoCodigo() throws ExcepcionSQLConsulta {
        ResultSet resultado;
        String sentenciaSQL;
        int ultimoCodigo = 0;
        sentenciaSQL = "Select max(cast(codigocontrato as int)) as codigo FROM contrato ";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            if (resultado.next()) {

                ultimoCodigo = resultado.getInt("codigo");
            }
            resultado.close();
            return ultimoCodigo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
//
//    public Contrato buscarPorFechaEspesifica(Date fecha) throws ExcepcionSQLConsulta {
//        Contrato contrato = null;
//        ResultSet resultado;
//        String sentenciaSQL;
//
//        sentenciaSQL = "Select codigocontrato,fechacontrato,estadocontrato FROM contrato where fechacontrato='" + fecha + "' ";
//        try {
//            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
//            if (resultado.next()) {
//                contrato = new Contrato();
//                contrato.setCodigocontrato(resultado.getString("codigocontrato"));
//                contrato.setFecha(resultado.getDate("fechacontrato"));
//                contrato.setEstado(resultado.getString("estadocontrato"));
//            }
//            resultado.close();
//            return contrato;
//        } catch (SQLException e) {
//            throw new ExcepcionSQLConsulta(e);
//        }
//
//    }

    public Contrato verificarSiTieneContratoPersonal(int codigo) throws ExcepcionSQLConsulta, SQLException {
        Contrato contrato = null;
        ResultSet resultado;
        String sentenciaSQL;

        PersonalDAOMySQL personalDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_VerificarSiTieneContratoPersonal_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                contrato = new Contrato();
                contrato.setCodigo(resultado.getInt("Contrato_Id"));
                contrato.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id")));
                contrato.setFechaIninicioContrato(resultado.getDate("Contrato_FechaContrato"));
                contrato.setFechaTerminoContrato(resultado.getDate("Contrato_FechaFin"));
                contrato.setTotalDiasPagar(resultado.getInt("Contrato_TotalDiasPagar"));
                contrato.setSueldo(resultado.getDouble("Contrato_Sueldo"));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                contrato.setEstado(estado);

            }
            resultado.close();
            return contrato;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public List<Contrato> buscarContratoPorDni(String dni, String estado) throws ExcepcionSQLConsulta {
//        Contrato contrato = null;
//        ArrayList<Contrato> listaContratos = new ArrayList<>();
//        ResultSet resultado;
//        String sentenciaSQL;
//        PersonalDAOMySQL personalDAOMySQL;
//
//        sentenciaSQL = "SELECT c.codigocontrato, c.codigopersonal, c.fechaingresocontrato,"
//                + "              c.fechafincontrato, c.totaldiaspagar,c.sueldocontrato ,c.estadocontrato"
//                + "           FROM contrato as c inner join personal as t on t.codigopersonal=c.codigopersonal where t.dnipersonal='" + dni + "' and c.estadocontrato='" + estado + "' ";
//        try {
//            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
//            while (resultado.next()) {
//                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
//                contrato = new Contrato();
//                contrato.setCodigo(resultado.getString("c.codigocontrato"));
//                contrato.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("c.codigopersonal")));
//                contrato.setFechaIninicioContrato(resultado.getDate("c.fechaingresocontrato"));
//                contrato.setFechaTerminoContrato(resultado.getDate("c.fechafincontrato"));
//                contrato.setTotalDiasPagar(resultado.getInt("c.totaldiaspagar"));
//                contrato.setSueldo(resultado.getDouble("c.sueldocontrato"));
//                Estado estado = new Estado();
//                estado.setCodigo(resultado.getInt("Estado_Id"));
//                contrato.setEstado(estado);
//                contrato.setEstado(resultado.getString("c.estadocontrato"));
//                listaContratos.add(contrato);
//            }
//            resultado.close();
//            return listaContratos;
//        } catch (SQLException e) {
//            throw new ExcepcionSQLConsulta(e);
//        }
        return null;

    }

    public Contrato buscarContratoPorDni(String dni) throws ExcepcionSQLConsulta {
//        Contrato contrato = null;
//
//        ResultSet resultado;
//        String sentenciaSQL;
//        PersonalDAOMySQL personalDAOMySQL;
//
//        sentenciaSQL = "SELECT c.codigocontrato, c.codigopersonal, c.fechaingresocontrato,"
//                + "              c.fechafincontrato, c.totaldiaspagar,c.sueldocontrato ,c.estadocontrato"
//                + "           FROM contrato as c inner join personal as t on t.codigopersonal=c.codigopersonal where t.dnipersonal='" + dni + "' ";
//        try {
//            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
//            if (resultado.next()) {
//                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
//                contrato = new Contrato();
//                contrato.setCodigo(resultado.getString("c.codigocontrato"));
//                contrato.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("c.codigopersonal")));
//                contrato.setFechaIninicioContrato(resultado.getDate("c.fechaingresocontrato"));
//                contrato.setFechaTerminoContrato(resultado.getDate("c.fechafincontrato"));
//                contrato.setTotalDiasPagar(resultado.getInt("c.totaldiaspagar"));
//                contrato.setSueldo(resultado.getDouble("c.sueldocontrato"));
//                contrato.setEstado(resultado.getString("c.estadocontrato"));
//            }
//            resultado.close();
//            return contrato;
//        } catch (SQLException e) {
//            throw new ExcepcionSQLConsulta(e);
//        }
        return null;
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

    public void mostrarContratosPorPersonal(JTable table, int codigo) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_MostrarTodosPorPersonal_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("Contrato_Id"));
                Personal personal = personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id"));
                fila.agregarValorCelda(personal.getGenerarNombre());
                fila.agregarValorCelda(resultado.getString("Contrato_FechaContrato"));
                fila.agregarValorCelda(resultado.getString("Contrato_FechaFin"));
                fila.agregarValorCelda(resultado.getInt("Contrato_TotalDiasPagar"));
                fila.agregarValorCelda(resultado.getDouble("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarContrato(int estado, JTable table, String texto) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        PersonalDAOMySQL personalDAOMySQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_MostrarTodosPorPersonalPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("Contrato_Id"));
                Personal personal = personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id"));
                fila.agregarValorCelda(personal.getGenerarNombre());
                fila.agregarValorCelda(resultado.getString("Contrato_FechaContrato"));
                fila.agregarValorCelda(resultado.getString("Contrato_FechaFin"));
                fila.agregarValorCelda(resultado.getInt("Contrato_TotalDiasPagar"));
                fila.agregarValorCelda(resultado.getDouble("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public List<Contrato> ObtenerContratoPersonalLista() throws ExcepcionSQLConsulta {

        Contrato contrato = null;
        ResultSet resultado;
        String sentenciaSQL;
        List<Contrato> listaContrato = new ArrayList<>();

        PersonalDAOMySQL personalDAOMySQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Contrato_obtenerListaContratoPersonal_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                personalDAOMySQL = new PersonalDAOMySQL(gestorJDBC);
                contrato = new Contrato();
                contrato.setCodigo(resultado.getInt("Contrato_Id"));
                contrato.setPersonal(personalDAOMySQL.buscarPorCodigo(resultado.getInt("Personal_Id")));
                contrato.setFechaIninicioContrato(resultado.getDate("Contrato_FechaContrato"));
                contrato.setFechaTerminoContrato(resultado.getDate("Contrato_FechaFin"));
                contrato.setTotalDiasPagar(resultado.getInt("Contrato_TotalDiasPagar"));
                contrato.setSueldo(resultado.getDouble("Contrato_Sueldo"));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                contrato.setEstado(estado);
                listaContrato.add(contrato);
            }
            resultado.close();
            return listaContrato;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
}
