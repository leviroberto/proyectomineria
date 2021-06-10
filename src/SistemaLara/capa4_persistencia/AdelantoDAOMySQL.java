/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.Valorizacion;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class AdelantoDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public AdelantoDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregarAdelantoCliente(Adelanto adelanto) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_AgregarAdelantoCliente_sp(?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, adelanto.getCantidad());
            prcProcedimientoAlmacenado.setString(2, adelanto.getMoneda());
            prcProcedimientoAlmacenado.setString(3, adelanto.getDescripcion());
            prcProcedimientoAlmacenado.setDate(4, adelanto.getFechaPago());
            prcProcedimientoAlmacenado.setInt(5, adelanto.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, adelanto.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(7, adelanto.getEmpresa().getCodigo());
            prcProcedimientoAlmacenado.setString(8, adelanto.getAdelantoEstado());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar El Adelanto del Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //    JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // return 0;
    }

    public int agregarAdelantoProveedor(Adelanto adelanto) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_AgregarAdelantoProveedor_sp(?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, adelanto.getCantidad());
            prcProcedimientoAlmacenado.setString(2, adelanto.getMoneda());
            prcProcedimientoAlmacenado.setString(3, adelanto.getDescripcion());
            prcProcedimientoAlmacenado.setDate(4, adelanto.getFechaPago());
            prcProcedimientoAlmacenado.setInt(5, adelanto.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, adelanto.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(7, adelanto.getAdelantoEstado());
            prcProcedimientoAlmacenado.setInt(8, adelanto.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el Adelanto del Proveedor Servicios.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int modificarAdelantoCliente(Adelanto adelanto) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_ModificarCliente_sp(?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, adelanto.getCantidad());
            prcProcedimientoAlmacenado.setString(2, adelanto.getMoneda());
            prcProcedimientoAlmacenado.setString(3, adelanto.getDescripcion());
            prcProcedimientoAlmacenado.setDate(4, adelanto.getFechaPago());
            prcProcedimientoAlmacenado.setInt(5, adelanto.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, adelanto.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(7, adelanto.getAdelantoEstado());
            prcProcedimientoAlmacenado.setInt(8, adelanto.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Adelanto del cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificarAdelantoProveedor(Adelanto adelanto) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_ModificarProveedor_sp(?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDouble(1, adelanto.getCantidad());
            prcProcedimientoAlmacenado.setString(2, adelanto.getMoneda());
            prcProcedimientoAlmacenado.setString(3, adelanto.getDescripcion());
            prcProcedimientoAlmacenado.setDate(4, adelanto.getFechaPago());
            prcProcedimientoAlmacenado.setInt(5, adelanto.getProveedorServicio().getCodigo());
            prcProcedimientoAlmacenado.setInt(6, adelanto.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(7, adelanto.getAdelantoEstado());
            prcProcedimientoAlmacenado.setInt(8, adelanto.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Adelanto del Proveedor.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int eliminar(Adelanto adelanto) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, adelanto.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarTodosCliente(ClienteEntrante a, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosCliente_sp(?)");

            prcProcedimientoAlmacenado.setInt(1, a.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarTodosClientePagadoNoPagado(int estado, ClienteEntrante a, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosClientePagadoNoPagado_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, a.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodosProveedor(int estado, ProveedorServicio pro, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosProveedor_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, pro.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));

                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void mostrarTodosProveedorPagadoNoPagado(int estado, ProveedorServicio pro, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosProveedorPagadoNoPagado_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, pro.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void calcularTotalNoPagadoSolesProveedor(JLabel totalNoPagado, ProveedorServicio proveedorServicio) {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_CalcularTotalNoPagadoSolesProveedor_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setString(2, "S");
            prcProcedimientoAlmacenado.setInt(3, proveedorServicio.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalNoPagado"));
            }
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void calcularTotalNoPagadoDolaresProveedor(JLabel totalNoPagado, ProveedorServicio proveedorServicio) {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_CalcularTotalNoPagadoDolaresProveedor_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setString(2, "$");
            prcProcedimientoAlmacenado.setInt(3, proveedorServicio.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalNoPagado"));
            }
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void calcularTotalNoPagadoSolesCliente(JLabel totalNoPagado, ClienteEntrante clienteEntrante) {
        ResultSet resultado;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_CalcularTotalNoPagadoSolesCliente_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setString(2, "S/");
            prcProcedimientoAlmacenado.setInt(3, clienteEntrante.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalNoPagado"));
            }
            if (totalNoPagado.getText() == null) {
                totalNoPagado.setText("0.0");
            }
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void calcularTotalNoPagadoDolaresCliente(JLabel totalNoPagado, ClienteEntrante clienteEntrante) {
        ResultSet resultado;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_CalcularTotalNoPagadoDolaresCliente_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setString(2, "$");
            prcProcedimientoAlmacenado.setInt(3, clienteEntrante.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                totalNoPagado.setText(resultado.getString("totalNoPagado"));
            }
            if (totalNoPagado.getText() == null) {
                totalNoPagado.setText("0.0");
            }
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Adelanto buscarPorCodigoCliente(int codigo) throws ExcepcionSQLConsulta {
        Adelanto adelanto = null;
        ResultSet resultado;
        ClienteEntranteDAOMySQL clienteEntranteDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_BuscarPorCodigoCliente_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                adelanto = new Adelanto();
                clienteEntranteDAOMySQL = new ClienteEntranteDAOMySQL(gestorJDBC);
                adelanto.setCodigo(resultado.getInt("Adelanto_Id"));
                adelanto.setCantidad(resultado.getDouble("Adelanto_Cantidad"));
                adelanto.setMoneda(resultado.getString("Adelanto_Moneda"));
                adelanto.setDescripcion(resultado.getString("Adelanto_Descripcion"));
                adelanto.setFechaPago(resultado.getDate("Adelanto_Fecha"));
                adelanto.setClienteEntrante(clienteEntranteDAOMySQL.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                adelanto.setAdelantoEstado(resultado.getString("Adeelanto_Estado"));

            }
            resultado.close();
            return adelanto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public Adelanto buscarPorCodigoProveedor(int codigo) throws ExcepcionSQLConsulta {
        Adelanto adelanto = null;
        ResultSet resultado;
        ProveedorServicioDAOMySQL proveedorServicioDAOMySQL;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_BuscarPorCodigoProveedor_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            if (resultado.next()) {
                adelanto = new Adelanto();
                proveedorServicioDAOMySQL = new ProveedorServicioDAOMySQL(gestorJDBC);
                adelanto.setCodigo(resultado.getInt("Adelanto_Id"));
                adelanto.setCantidad(resultado.getDouble("Adelanto_Cantidad"));
                adelanto.setMoneda(resultado.getString("Adelanto_Moneda"));
                adelanto.setDescripcion(resultado.getString("Adelanto_Descripcion"));
                adelanto.setFechaPago(resultado.getDate("Adelanto_Fecha"));
                adelanto.setProveedorServicio(proveedorServicioDAOMySQL.buscarPorCodigo(resultado.getInt("ProveedorServicio_Id")));
                adelanto.setAdelantoEstado(resultado.getString("Adeelanto_Estado"));

            }
            resultado.close();
            return adelanto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public int modificarAdelantoNoPagado(int lblCodigo) throws ExcepcionSQLConsulta, SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_ModificarEstadoNoPagado_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setInt(2, lblCodigo);
            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo modificar adelanto no Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }
    
    public int modificarAdelantoProveedorNoPagado(int lblCodigo) throws ExcepcionSQLConsulta, SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoProveedor_ModificarEstadoNoPagado_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, "NO PAGADO");
            prcProcedimientoAlmacenado.setInt(2, lblCodigo);
            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo modificar adelanto no Pagado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int mosdificarValorizacionAdelanto(Adelanto adelanto) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MosdificarValorizacionAdelanto_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, adelanto.getAdelantoEstado());
            prcProcedimientoAlmacenado.setInt(2, adelanto.getValorizacion().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, adelanto.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar valorizacion del Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarAdelantoPorClienteValorizacion(int i, JTable tableAdelanto, Valorizacion valorizacionSeleccionada) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableAdelanto.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarAdelantoPorClienteValorizacion_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, valorizacionSeleccionada.getCodigo());
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableAdelanto.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto Por Cliente Valorizacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //  JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void mostrarTodosCliente(int estado, JTable table, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosClientePorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante"));

                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void mostrarTodosProveedorPagadoNoPagado(int estado, ProveedorServicio pro, JTable table, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosProveedorPagadoNoPagadoPorLike_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, pro.getCodigo());
            prcProcedimientoAlmacenado.setString(3, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarTodosClientePagadoNoPagado(int estado, ClienteEntrante a, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosClientePagadoNoPagadoPorLike_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setInt(2, a.getCodigo());
            prcProcedimientoAlmacenado.setString(3, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodosProveedor(int estado, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosProveedorPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarAdelantoReportePorCliente(String texto, JTable table) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_AdelantoPorCliente_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Adelanto_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                filaTabla.agregarValorCelda(resultado.getDouble("Adelanto_Cantidad"));
                filaTabla.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getDate("Adelanto_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                filaTabla.agregarValorCelda(resultado.getString("cliente"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int eliminarProveedorAdelanto(Adelanto adelantoSeleccionado) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("AdelantoProveedor_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, adelantoSeleccionado.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Adelanto.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
