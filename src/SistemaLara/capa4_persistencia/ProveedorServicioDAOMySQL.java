/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.TipoProveedor;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class ProveedorServicioDAOMySQL {

    private GestorJDBC gestorJDBC;

    public ProveedorServicioDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(ProveedorServicio proveedorServicio) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_Agregar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, proveedorServicio.getRazonSocial());
            prcProcedimientoAlmacenado.setString(2, proveedorServicio.getRuc());
            prcProcedimientoAlmacenado.setString(3, proveedorServicio.getEntidad());
            prcProcedimientoAlmacenado.setString(4, proveedorServicio.getCuente());
            prcProcedimientoAlmacenado.setString(5, proveedorServicio.getTelefono());
            prcProcedimientoAlmacenado.setString(6, proveedorServicio.getDireccion());
            prcProcedimientoAlmacenado.setString(7, proveedorServicio.getEmail());
            prcProcedimientoAlmacenado.setInt(8, proveedorServicio.getTipoProveedor().getCodigo());
            prcProcedimientoAlmacenado.setInt(9, proveedorServicio.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(10, proveedorServicio.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
//            throw new SQLException("No se pudo registrar el Proveedor Servicio.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");

        }

        return 0;
    }

    public int modificar(ProveedorServicio proveedorServicio) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_Modificar_sp(?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, proveedorServicio.getRazonSocial());
            prcProcedimientoAlmacenado.setString(2, proveedorServicio.getRuc());
            prcProcedimientoAlmacenado.setString(3, proveedorServicio.getEntidad());
            prcProcedimientoAlmacenado.setString(4, proveedorServicio.getCuente());
            prcProcedimientoAlmacenado.setString(5, proveedorServicio.getTelefono());
            prcProcedimientoAlmacenado.setString(6, proveedorServicio.getDireccion());
            prcProcedimientoAlmacenado.setInt(7, proveedorServicio.getTipoProveedor().getCodigo());
            prcProcedimientoAlmacenado.setInt(8, proveedorServicio.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(9, proveedorServicio.getCodigo());
            prcProcedimientoAlmacenado.setString(10, proveedorServicio.getEmail());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Proveedor Servicio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int eliminar(ProveedorServicio proveedorServicio) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, proveedorServicio.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el proveedor Servicio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ProveedorServicio_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Ruc"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Entidad"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Cuenta"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Direccion"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Email"));
                filaTabla.agregarValorCelda(resultado.getString("personals"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarPorFacturacion(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_MostrarPorFacturacion_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ProveedorServicio_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Ruc"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Entidad"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Cuenta"));
                filaTabla.agregarValorCelda(resultado.getString("TipoProveedor_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personals"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar por Facturacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public ProveedorServicio buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        ProveedorServicio proveedorServicio = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoProveedorDAOMySQL tipoClienteDAOMySQL;
        try {
            tipoClienteDAOMySQL = new TipoProveedorDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoProveedor tipoProveedor;
            if (resultado.next()) {
                proveedorServicio = new ProveedorServicio();
                proveedorServicio.setCodigo(resultado.getInt("ProveedorServicio_Id"));
                proveedorServicio.setRazonSocial(resultado.getString("ProveedorServicio_Razon_Social"));
                proveedorServicio.setRuc(resultado.getString("ProveedorServicio_Ruc"));
                proveedorServicio.setEntidad(resultado.getString("ProveedorServicio_Entidad"));
                proveedorServicio.setCuente(resultado.getString("ProveedorServicio_Cuenta"));
                proveedorServicio.setTelefono(resultado.getString("ProveedorServicio_Telefono"));
                proveedorServicio.setDireccion(resultado.getString("ProveedorServicio_Direccion"));
                proveedorServicio.setEmail(resultado.getString("ProveedorServicio_Email"));

                tipoProveedor = tipoClienteDAOMySQL.buscarPorCodigo(resultado.getInt("TipoProveedor_Id"));
                proveedorServicio.setTipoProveedor(tipoProveedor);

            }
            resultado.close();
            return proveedorServicio;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarTodos(int i, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ProveedorServicio_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Ruc"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Entidad"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Cuenta"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Direccion"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Email"));
                filaTabla.agregarValorCelda(resultado.getString("personals"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar todos Proveedor Servicio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarTodosLikePorFacturacion(int i, JTable table, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ProveedorServicio_MostrarPorFacturacionTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setString(2, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ProveedorServicio_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Razon_Social"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Ruc"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Entidad"));
                filaTabla.agregarValorCelda(resultado.getString("ProveedorServicio_Cuenta"));
                filaTabla.agregarValorCelda(resultado.getString("TipoProveedor_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personals"));

                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Todos por Facturacion.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }

    }
}
