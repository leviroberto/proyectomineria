package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.GastosExtras;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class GastosExtrasDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public GastosExtrasDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public DefaultTableModel buscarPorNombre(String nombre) throws ExcepcionSQLConsulta {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_buscarPorNombre_sp(?)");
            prcProcedimientoAlmacenado.setString(1, nombre);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                registros[0] = resultado.getInt("p.GastosExtras_Id");
                registros[1] = resultado.getString("p.GastosExtras_Nombres");
                registros[2] = resultado.getString("p.GastosExtras_Apellidos");
                registros[3] = resultado.getString("p.GastosExtras_DNI");
                registros[4] = resultado.getString("p.GastosExtras_Sexo");
                registros[5] = resultado.getString("p.GastosExtras_Telefono");
                registros[6] = resultado.getString("p.GastosExtras_Email");
                registros[7] = resultado.getString("p.GastosExtras_Direccion");
                registros[8] = resultado.getString("tp.TipoGastosExtras_Descripcion");
                modelo.addRow(registros);

            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public GastosExtras buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        GastosExtras gastosExtras = null;
        ResultSet resultado;
        String sentenciaSQL;
        GastosExtrasDAOMySQL gastosExtrasDAOMySQL = new GastosExtrasDAOMySQL(gestorJDBC);
        ConceptoDAOMySQL conceptoDAOMySQL = new ConceptoDAOMySQL(gestorJDBC);
        ClienteEntranteDAOMySQL clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {

                gastosExtras = new GastosExtras();
                gastosExtras.setCodigo(resultado.getInt("GastosExtras_Id"));
                gastosExtras.setDescripcion(resultado.getString("GastosExtras_Descripcion"));
                gastosExtras.setMonto(resultado.getDouble("GastosExtras_Monto"));
                gastosExtras.setMoneda(resultado.getString("GastosExtras_Moneda"));
                gastosExtras.setClienteEntrante(clienteEntranteDAOMySQL.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                gastosExtras.setConcepto(conceptoDAOMySQL.buscarPorCodigo(resultado.getInt("Concepto_Id")));
            }

            resultado.close();
            return gastosExtras;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public int ingresar(GastosExtras gastosExtras) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_Agregar_sp(?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, gastosExtras.getDescripcion());
            prcProcedimientoAlmacenado.setDouble(2, gastosExtras.getMonto());
            prcProcedimientoAlmacenado.setInt(3, gastosExtras.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(4, gastosExtras.getConcepto().getCodigo());
            prcProcedimientoAlmacenado.setString(5, gastosExtras.getMoneda());
            prcProcedimientoAlmacenado.setInt(6, gastosExtras.getPersonal().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
           throw new SQLException("No se pudo Registrar el Gastos Extras.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
  //          JOptionPane.showMessageDialog(null, e.getMessage());
        }
   //     return 2;
    }

    public int modificar(GastosExtras gastosExtras) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_Modificar_sp(?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, gastosExtras.getDescripcion());
            prcProcedimientoAlmacenado.setDouble(2, gastosExtras.getMonto());
            prcProcedimientoAlmacenado.setInt(3, gastosExtras.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(4, gastosExtras.getConcepto().getCodigo());
            prcProcedimientoAlmacenado.setString(5, gastosExtras.getMoneda());
            prcProcedimientoAlmacenado.setInt(6, gastosExtras.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(7, gastosExtras.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Gastos Extras.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int eliminar(GastosExtras gastosExtras) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, gastosExtras.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Gastos Extras.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("GastosExtras_Id"));
                filaTabla.agregarValorCelda(resultado.getString("GastosExtras_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("GastosExtras_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("clienteEntrante"));
                filaTabla.agregarValorCelda(resultado.getString("Concepto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
throw new SQLException("No se ha podido mostrar Todos .\n"
                    + "Intente de nuevo o consulte con el Administrador.");
       
        }

    }

    public DefaultTableModel buscarGastosExtrasesPorDni(String dni) throws ExcepcionSQLConsulta {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_buscarPorNombre_sp(?)");
            prcProcedimientoAlmacenado.setString(1, dni);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                registros[0] = resultado.getInt("p.GastosExtras_Id");
                registros[1] = resultado.getString("p.GastosExtras_Nombres");
                registros[2] = resultado.getString("p.GastosExtras_Apellidos");
                registros[3] = resultado.getString("p.GastosExtras_DNI");
                registros[4] = resultado.getString("p.GastosExtras_Sexo");
                registros[5] = resultado.getString("p.GastosExtras_Telefono");
                registros[6] = resultado.getString("p.GastosExtras_Email");
                registros[7] = resultado.getString("p.GastosExtras_Direccion");
                registros[8] = resultado.getString("tp.TipoGastosExtras_Descripcion");
                modelo.addRow(registros);

            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public ArrayList<Adelanto> buscarAdelantosPorContrato(Contrato contrato, Date date, String estado) throws ExcepcionSQLConsulta {
        try {
            String formatoAnio = "yyyy";
            String formatomes = "MM";
            SimpleDateFormat dateFormatAnio = new SimpleDateFormat(formatoAnio);
            int anio = Integer.parseInt(dateFormatAnio.format(date));
            SimpleDateFormat dateFormatMes = new SimpleDateFormat(formatomes);
            int mes = Integer.parseInt(dateFormatMes.format(date));
            ResultSet resultado;
            String sentenciaSQL;
            ArrayList<Adelanto> listaAdelanto = new ArrayList<>();
            Adelanto adelanto;
            sentenciaSQL = "SELECT a.codigoadelanto, a.codigocontrato,a.montoretiradoadelanto,a.fechaadelanto,a.motivoadelanto,"
                    + " a.estadoadelanto "
                    + "  FROM adelanto as a inner join contrato as t on "
                    + "a.codigocontrato=t.codigocontrato where"
                    + " t.codigocontrato='" + contrato.getCodigo() + "' "
                    + "and month(a.fechaadelanto)='" + mes + "' and year(a.fechaadelanto)='" + anio + "'"
                    + " and a.estadoadelanto='" + estado + "' ";

            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            while (resultado.next()) {
//                ContratoDAOMySQL contratoDAOMySQL = new ContratoDAOMySQL(gestorJDBC);
//                adelanto = new Adelanto();
//                adelanto.setCodigo(resultado.getString("a.codigoadelanto"));
//                adelanto.setContrato(contratoDAOMySQL.buscarPorCodigo(resultado.getString("a.codigocontrato")));
//                adelanto.setFecha(resultado.getDate("a.fechaadelanto"));
//                adelanto.setMotivo(resultado.getString("a.motivoadelanto"));
//                adelanto.setMontoAdelanto(resultado.getDouble("a.montoretiradoadelanto"));
//                adelanto.setEstado(resultado.getString("a.estadoadelanto"));
//                listaAdelanto.add(adelanto);

            }
            resultado.close();
            return listaAdelanto;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("GastosExtras_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("GastosExtras_Id"));
                filaTabla.agregarValorCelda(resultado.getString("GastosExtras_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("GastosExtras_Monto"));
                filaTabla.agregarValorCelda(resultado.getString("clienteEntrante"));
                filaTabla.agregarValorCelda(resultado.getString("Concepto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("GastosExtras_Fecha_Registrado"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
        throw new SQLException("No se ha podido mostrar Todos Gastos Extras.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
       
        }

    }

}
