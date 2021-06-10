/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.TipoCliente;
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
public class ClienteEntranteDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public ClienteEntranteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(ClienteEntrante clienteEntrante) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_Agregar_sp(?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, clienteEntrante.getNombres());
            prcProcedimientoAlmacenado.setString(2, clienteEntrante.getApellidos());
            prcProcedimientoAlmacenado.setInt(3, clienteEntrante.getTipoCliente().getCodigo());
            prcProcedimientoAlmacenado.setString(4, clienteEntrante.getTelefono());
            prcProcedimientoAlmacenado.setString(5, clienteEntrante.getSexo());
            prcProcedimientoAlmacenado.setInt(6, clienteEntrante.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(7, clienteEntrante.getDni());
            prcProcedimientoAlmacenado.setString(8, clienteEntrante.getDireccion());
            prcProcedimientoAlmacenado.setInt(9, clienteEntrante.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar los Clientes entrantes.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int modificar(ClienteEntrante clienteEntrante) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_Modificar_sp(?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, clienteEntrante.getNombres());
            prcProcedimientoAlmacenado.setString(2, clienteEntrante.getApellidos());
            prcProcedimientoAlmacenado.setInt(3, clienteEntrante.getTipoCliente().getCodigo());
            prcProcedimientoAlmacenado.setString(4, clienteEntrante.getTelefono());
            prcProcedimientoAlmacenado.setString(5, clienteEntrante.getSexo());
            prcProcedimientoAlmacenado.setInt(6, clienteEntrante.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(7, clienteEntrante.getDni());
            prcProcedimientoAlmacenado.setString(8, clienteEntrante.getDireccion());
            prcProcedimientoAlmacenado.setInt(9, clienteEntrante.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Cliente Entrante\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int eliminar(ClienteEntrante clienteEntrante) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, clienteEntrante.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el Cliente Entrante.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("nombre"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Sexo"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);
        } catch (Exception e) {
            throw new SQLException("No se ha podido  mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public ClienteEntrante buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        ClienteEntrante clienteEntrante = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        try {
            tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                clienteEntrante = new ClienteEntrante();
                clienteEntrante.setCodigo(resultado.getInt("ClienteEntrante_Id"));
                clienteEntrante.setApellidos(resultado.getString("ClienteEntrante_Apellidos"));
                clienteEntrante.setNombres(resultado.getString("ClienteEntrante_Nombres"));
                clienteEntrante.setDni(resultado.getString("ClienteEntrante_Dni"));
                clienteEntrante.setTelefono(resultado.getString("ClienteEntrante_Telefono"));
                clienteEntrante.setDireccion(resultado.getString("ClienteEntrante_Direccion"));
                clienteEntrante.setSexo(resultado.getString("ClienteEntrante_Sexo"));
                tipoCliente = tipoClienteDAOMySQL.buscarPorCodigo(resultado.getInt("TipoCliente_Id"));
                clienteEntrante.setTipoCliente(tipoCliente);

            }
            resultado.close();
            return clienteEntrante;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int i, JTable tableClienteEntrante, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableClienteEntrante.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_BuscarTodosPorLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("nombre"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Sexo"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableClienteEntrante.setModel(modeloTabla);
        } catch (Exception e) {
            throw new SQLException("No se ha podido  mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public ClienteEntrante obtenerClientePorDefectoParaLiquidacion(int estado) throws ExcepcionSQLConsulta {

        ClienteEntrante clienteEntrante = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        try {
            tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_obtenerClientePorDefectoParaLiquidacion_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                clienteEntrante = new ClienteEntrante();
                clienteEntrante.setCodigo(resultado.getInt("ClienteEntrante_Id"));
                clienteEntrante.setApellidos(resultado.getString("ClienteEntrante_Apellidos"));
                clienteEntrante.setNombres(resultado.getString("ClienteEntrante_Nombres"));
                clienteEntrante.setDni(resultado.getString("ClienteEntrante_Dni"));
                clienteEntrante.setTelefono(resultado.getString("ClienteEntrante_Telefono"));
                clienteEntrante.setDireccion(resultado.getString("ClienteEntrante_Direccion"));
                clienteEntrante.setSexo(resultado.getString("ClienteEntrante_Sexo"));
                tipoCliente = tipoClienteDAOMySQL.buscarPorCodigo(resultado.getInt("TipoCliente_Id"));
                clienteEntrante.setTipoCliente(tipoCliente);

            }
            resultado.close();
            return clienteEntrante;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public ClienteEntrante obtenerClientePorDefectoParaReintegro(int estado) throws ExcepcionSQLConsulta {
        ClienteEntrante clienteEntrante = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoClienteDAOMySQL tipoClienteDAOMySQL;
        try {
            tipoClienteDAOMySQL = new TipoClienteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("ClienteEntrante_obtenerClientePorDefectoParaLiquidacion_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                clienteEntrante = new ClienteEntrante();
                clienteEntrante.setCodigo(resultado.getInt("ClienteEntrante_Id"));
                clienteEntrante.setApellidos(resultado.getString("ClienteEntrante_Apellidos"));
                clienteEntrante.setNombres(resultado.getString("ClienteEntrante_Nombres"));
                clienteEntrante.setDni(resultado.getString("ClienteEntrante_Dni"));
                clienteEntrante.setTelefono(resultado.getString("ClienteEntrante_Telefono"));
                clienteEntrante.setDireccion(resultado.getString("ClienteEntrante_Direccion"));
                clienteEntrante.setSexo(resultado.getString("ClienteEntrante_Sexo"));
                tipoCliente = tipoClienteDAOMySQL.buscarPorCodigo(resultado.getInt("TipoCliente_Id"));
                clienteEntrante.setTipoCliente(tipoCliente);

            }
            resultado.close();
            return clienteEntrante;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

}
