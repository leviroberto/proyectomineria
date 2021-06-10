/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.TipoProveedor;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class TipoProveedorDAOMySQL {
    
    private GestorJDBC gestorJDBC;
    
    public TipoProveedorDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    public int agregar(TipoProveedor tipoProveedor) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_Agregar_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, tipoProveedor.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, tipoProveedor.getPersonal().getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el tipo de tipo Proveedor.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            
        }
        
    }
    
    public int modificar(TipoProveedor tipoProveedor) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_Modificar_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, tipoProveedor.getDescripcion());
            prcProcedimientoAlmacenado.setInt(2, tipoProveedor.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, tipoProveedor.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el tipo de tipo Proveedor.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        
    }
    
    public int eliminar(TipoProveedor tipoProveedor) throws SQLException {
        
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, tipoProveedor.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el tipo Proveedor.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        
    }
    
    public List<TipoProveedor> mostrarTodos(int estado) throws ExcepcionSQLConsulta, SQLException {
        
        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            List<TipoProveedor> listaTipoProveedor = new ArrayList<TipoProveedor>();
            TipoProveedor tipoProveedor;
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                tipoProveedor = new TipoProveedor();
                tipoProveedor.setCodigo(resultado.getInt("tc.TipoProveedor_Id"));
                tipoProveedor.setDescripcion(resultado.getString("tc.TipoProveedor_Descripcion"));
                listaTipoProveedor.add(tipoProveedor);
            }
            resultado.close();
            return listaTipoProveedor;
        } catch (SQLException e) {
          throw new SQLException("No se pudo mostrar Todos Los Tipos de Proveedor.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
      //  return null;
    }
    
    public TipoProveedor buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        TipoProveedor tipoProveedor = null;
        ResultSet resultado;
        String sentenciaSQL;
        
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                tipoProveedor = new TipoProveedor();
                tipoProveedor.setCodigo(resultado.getInt("TipoProveedor_Id"));
                tipoProveedor.setDescripcion(resultado.getString("TipoProveedor_Descripcion"));
            }
            resultado.close();
            return tipoProveedor;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }
    
    public DefaultComboBoxModel llenarCBOTipoProveedor(int estado, JComboBox combo) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        TipoProveedor itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new TipoProveedor();
                itemFacturaElectronica.setCodigo(resultado.getInt("TipoProveedor_Id"));
                itemFacturaElectronica.setDescripcion(resultado.getString("TipoProveedor_Descripcion"));
                combo.addItem(itemFacturaElectronica);
            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }
    
    public void mostrarTodos(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {           
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);           
            resultado = prcProcedimientoAlmacenado.executeQuery();           
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoProveedor_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoProveedor_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));
                modeloTabla.agregarFila(filaTabla);
                
            }
            resultado.close();
            table.setModel(modeloTabla);
            
        } catch (Exception e) {
           throw new SQLException("No se pudo mostrar Todos.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }
    
    public void mostrarTodos(int i, JTable table, String texto) throws SQLException {
        
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoProveedor_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setString(2, texto);
            
            resultado = prcProcedimientoAlmacenado.executeQuery();
            
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("TipoProveedor_Id"));
                filaTabla.agregarValorCelda(resultado.getString("TipoProveedor_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("personal"));
                modeloTabla.agregarFila(filaTabla);
                
            }
            resultado.close();
            table.setModel(modeloTabla);
            
        } catch (Exception e) {
       throw new SQLException("No se pudo mostrar Todos los Proveedores.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
    }
    
}
