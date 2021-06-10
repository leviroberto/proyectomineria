/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.AdelantoPersonal;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class AdelantoPersonalDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public AdelantoPersonalDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public AdelantoPersonal buscarPorCodigo(int adelantoCodigo) throws ExcepcionSQLConsulta {

        AdelantoPersonal adelantoPersonal = null;
        ResultSet resultado;
        ContratoDAOMySQL contratoDAOMySQL;

        try {
            contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, adelantoCodigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                adelantoPersonal = new AdelantoPersonal();
                adelantoPersonal.setCodigo(resultado.getInt("AdelantoPersonal_Id"));
                adelantoPersonal.setContrato(contratoDAOMySQL.buscarPorCodigo(resultado.getInt("Contrato_Id")));
                adelantoPersonal.setFecha(resultado.getDate("AdelantoPersonal_Fecha"));
                adelantoPersonal.setMotivo(resultado.getString("AdelantoPersonal_Motivo"));
                adelantoPersonal.setMontoAdelanto(resultado.getDouble("AdelantoPersonal_Monto"));
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                adelantoPersonal.setEstado(estado);

            }
            resultado.close();
            return adelantoPersonal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public int ingresar(AdelantoPersonal adelanto) throws SQLException {

        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_Agregar_sp(?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, adelanto.getContrato().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setDate(2, adelanto.getFecha());
            prcProcedimientoAlmacenadoDetalle.setDouble(3, adelanto.getMontoAdelanto());
            prcProcedimientoAlmacenadoDetalle.setString(4, adelanto.getMotivo());
            prcProcedimientoAlmacenadoDetalle.setInt(5, adelanto.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el adelanto del Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //    JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //  return 0;
    }

    public int modificarEstadoAdelanto(int codigoAdelanto, int codigoPagoPersonal) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_ModificarAdelantoPagoPersonal_sp(?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigoAdelanto);
            prcProcedimientoAlmacenadoDetalle.setInt(2, codigoPagoPersonal);
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
//            throw new SQLException("No se pudo modificar el adelanto del Personal.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 0;
    }

    public int modificar(AdelantoPersonal adelanto) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_Modificar_sp(?,?,?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, adelanto.getContrato().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setDate(2, adelanto.getFecha());
            prcProcedimientoAlmacenadoDetalle.setDouble(3, adelanto.getMontoAdelanto());
            prcProcedimientoAlmacenadoDetalle.setString(4, adelanto.getMotivo());
            prcProcedimientoAlmacenadoDetalle.setInt(5, adelanto.getCodigo());

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el adelanto del Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int eliminar(AdelantoPersonal adelanto) throws SQLException {
//        String sentenciaSQL = "delete from adelanto where codigoadelanto=?";
//        try {
//            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
//            sentencia.setString(1, adelanto.getCodigo());
//            return sentencia.executeUpdate();
//        } catch (Exception e) {
//            throw new SQLException("No se pudo eliminar el adelanto.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
//        }
        return 0;
    }

    public int obtenerUltimoCodigo() throws ExcepcionSQLConsulta {
        ResultSet resultado;
        String sentenciaSQL;
        int ultimoCodigo = 0;
        sentenciaSQL = "Select max(cast(codigoadelanto as int)) as codigo FROM adelanto ";
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

    public double buscarAdelantoPersonalTotalPorContrato(String codigoContrato, int mes, String estado) throws ExcepcionSQLConsulta {
        ResultSet resultado;
        String sentenciaSQL;
        Double montoTotal = null;
        sentenciaSQL = "SELECT sum(montoretiradoadelanto) as sumatotal FROM adelanto where codigocontrato='" + codigoContrato + "' and month(fechaadelanto)='" + mes + "' and estadoadelanto='" + estado + "'";
        try {
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            while (resultado.next()) {
                montoTotal = resultado.getDouble("sumatotal");

            }
            resultado.close();
            return montoTotal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public ArrayList<AdelantoPersonal> mostrarAdelantoPersonalsPorPersona(String codigo, int mes, int anio) throws ExcepcionSQLConsulta {
//        try {
//            ResultSet resultado;
//            String sentenciaSQL;
//            ArrayList<AdelantoPersonal> listaAdelantoPersonal = new ArrayList<>();
//            AdelantoPersonal adelanto;
//
//            ContratoDAOMySQL contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
//            sentenciaSQL = "SELECT codigoadelanto, codigocontrato, fechaadelanto, motivoadelanto, montoretiradoadelanto, estadoadelanto FROM adelanto   where codigocontrato='" + codigo + "' ";
//            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
//
//            while (resultado.next()) {
//                adelanto = new AdelantoPersonal();
//                adelanto.setCodigo(resultado.getString("codigoadelanto"));
//                adelanto.setContrato(contratoDAOMySQL.buscarPorCodigo(resultado.getString("codigocontrato")));
//                adelanto.setFecha(resultado.getDate("fechaadelanto"));
//                adelanto.setMotivo(resultado.getString("motivoadelanto"));
//                adelanto.setMontoAdelantoPersonal(resultado.getDouble("montoretiradoadelanto"));
//                adelanto.setEstado(resultado.getString("estadoadelanto"));
//                listaAdelantoPersonal.add(adelanto);
//            }
//            resultado.close();
//            return listaAdelantoPersonal;
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            throw new ExcepcionSQLConsulta(e);
//        }
        return null;
    }

    public int eliminarPorCodigo(int codigo) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_Eliminar_sp(?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigo);

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Eliminar el Adelanto del personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public AdelantoPersonal buscarAdelantoPersonalPagado(String codigo, int mes, int anio) throws ExcepcionSQLConsulta {
//        try {
//            AdelantoPersonal adelanto = null;
//            ResultSet resultado;
//            String sentenciaSQL;
//            ContratoDAOMySQL contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
//            sentenciaSQL = "SELECT a.codigoadelanto, a.codigocontrato,"
//                    + "                   a.fechaadelanto, a.motivoadelanto, a.montoretiraradelanto, a.estadoadelanto "
//                    + "                   FROM adelanto as a inner join contrato as t on a.codigocontrato=t.codigocontrato"
//                    + "               where a.estadoadelanto='ACTIVO' and a.codigocontrato='" + codigo + "' and month(a.fechaadelanto)='" + mes + "' and year(a.fechaadelanto)='" + anio + "'";
//
//            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
//
//            if (resultado.next()) {
//                adelanto = new AdelantoPersonal();
//                adelanto.setCodigo(resultado.getString("a.codigoadelanto"));
////                adelanto.setContrato(contratoDAOMySQL.buscarPorCodigo(resultado.getString("a.codigocontrato")));
//                adelanto.setFecha(resultado.getDate("a.fechaadelanto"));
//                adelanto.setMotivo(resultado.getString("a.motivoadelanto"));
//                adelanto.setMontoAdelantoPersonal(resultado.getDouble("a.montoretiraradelanto"));
//                adelanto.setEstado(resultado.getString("a.estadoadelanto"));
//
//            }
//            return adelanto;
//        } catch (SQLException e) {
//
//            throw new ExcepcionSQLConsulta(e);
//        }
        return null;
    }

    public void mostrarAdelantos(JTable table) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_MostrarTodos_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Contrato_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
//                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("total"));
                fila.agregarValorCelda(resultado.getString("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));

                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public Double obtenerTotalSolesAdelantoPorPersonal(int codigoContrato) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        String sentenciaSQL;
        Double totalSuma = 0.0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_ObtenerTotalSolesPorPersonal_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigoContrato);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {

                totalSuma = resultado.getDouble("total");

            }
            resultado.close();
            return totalSuma;

        } catch (Exception e) {
            throw new SQLException("No se pudo Obtener el Total Soles del Adelanto del Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //  return 0.0;
    }

    public void mostrarAdelantoPersonalPorContrato(int codigho, JTable table, int estado) throws ExcepcionSQLConsulta, SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_mostrarAdelantoPersonalPorContrato_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigho);
            prcProcedimientoAlmacenado.setInt(2, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("AdelantoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se pudo Mostrar el  Adelanto del Personal Por Contrato.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<Adelanto> obtenerAdelantoPorPersonal(int codigho,int estado) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;
        List<Adelanto> listaAdelanto = new ArrayList<>();
        Adelanto adelanto;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_mostrarAdelantoPersonalPorContrato_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigho);
            prcProcedimientoAlmacenado.setInt(2, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                adelanto = new Adelanto();
                adelanto.setCodigo(resultado.getInt("AdelantoPersonal_Id"));

                listaAdelanto.add(adelanto);

            }
            resultado.close();
            return listaAdelanto;

        } catch (SQLException e) {
            throw new SQLException("No se pudo Mostrar el  Adelanto del Personal Por Contrato.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int modificarAdelantoPersonalAPagado(int codigo) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_ModificarAPagado_sp(?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigo);

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Modificar el Adelanto del Personal a Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificarAdelantoPersonalPorPagar(int codigo) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_ModificarPorPagar_sp(?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigo);

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Modificar el Adelanto del Personal Por Pagar.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int verificarSiHayPagosDisponibles(int codigo) throws SQLException {

        ResultSet resultado;
        int total = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_verificarSiHayPagosDisponibles_sp(?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenadoDetalle.executeQuery();
            if (resultado.next()) {
                total = resultado.getInt("total");
            }
            return total;
        } catch (Exception e) {
            throw new SQLException("No se ha Podido Verificar si hay Pagos Disponibles del Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void mostrarAdelantoPersonalPorPago(int codigo, JTable table) throws SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_mostrarAdelantoPersonalPorPago_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getInt("AdelantoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se ha podido  mostrar Adelanto Personal Por Pago.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarAdelantosTodos(JTable table) throws SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_MostrarAdelantosTodos_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
//                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se ha podido Mostrar todos Los Adelantos del Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarAdelantosGrupo(JTable table, String texto) throws SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_MostrarTodosPorLikeGrupo_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Contrato_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
//                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("total"));
                fila.agregarValorCelda(resultado.getString("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));

                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se ha podido  mostrar Adelanto Personal Por Grupo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int actualizarAdelantoPorPagar(int codigo) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("AdelantoPersonal_ModificarPorPagar_sp(?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, codigo);

            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Modificar el Adelanto Por Pagar\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarAdelantos(JTable table, String texto) throws SQLException {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Fila fila;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoPersonal_MostrarAdelantosTodosPorLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Id"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Fecha"));
//                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Motivo"));
                fila.agregarValorCelda(resultado.getString("AdelantoPersonal_Monto"));
                fila.agregarValorCelda(resultado.getString("Contrato_Sueldo"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                modeloTabla.agregarFila(fila);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (SQLException e) {
            throw new SQLException("No se ha podido  mostrar Adelanto Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }
}
