/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;


import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.Permisos;
import SistemaLara.capa3_dominio.PermisosDetallePersonal;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class PermisoDetallePersonalDAOMySQL {
    
    private final GestorJDBC gestorJDBC;
    
    public PermisoDetallePersonalDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    public int agregar(PermisosDetallePersonal detallePermisosPersonal) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("DetallePermisosPersonal_Agregar_sp(?,?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, detallePermisosPersonal.getEmpresa().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(2, detallePermisosPersonal.getPermisos().getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(3, detallePermisosPersonal.getPersonal().getCodigo());
            
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Registrar Permisos de Detalle Personal.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
   //         JOptionPane.showMessageDialog(null, e.getMessage());
            
        }
  //      return 0;
        
    }
    
    public int modificar(PermisosDetallePersonal detallePermisosPersonal) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenadoDetalle = gestorJDBC.prepareCall("DetallePermisosPersonal_Modificar_sp(?,?)");
            prcProcedimientoAlmacenadoDetalle.setInt(1, detallePermisosPersonal.getCodigo());
            prcProcedimientoAlmacenadoDetalle.setInt(2, detallePermisosPersonal.getEstado().getCodigo());
            
            return prcProcedimientoAlmacenadoDetalle.executeUpdate();
        } catch (Exception e) {
      throw new SQLException("No se pudo modificar Permisos de Detalle Personal.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
        //return 5;
        
    }
    
    public int eliminar(PermisosDetallePersonal detallePermisosPersonal) throws SQLException {
        
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, detallePermisosPersonal.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el detalle PermisosPersonal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        
    }
    
    public List<PermisosDetallePersonal> mostrarTodos(int estado) throws ExcepcionSQLConsulta, SQLException {
        
        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            List<PermisosDetallePersonal> listaDetallePermisosPersonal = new ArrayList<PermisosDetallePersonal>();
            PermisosDetallePersonal detallePermisosPersonal;
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                detallePermisosPersonal = new PermisosDetallePersonal();
                detallePermisosPersonal.setCodigo(resultado.getInt("tc.DetallePermisosPersonal_Id"));
//                detallePermisosPersonal.setPermisos(resultado.getInt("tc.DetallePermisosPersonal_Descripcion"));
                listaDetallePermisosPersonal.add(detallePermisosPersonal);
            }
            resultado.close();
            return listaDetallePermisosPersonal;
        } catch (SQLException e) {
             throw new SQLException("No se pudo mostrar detalle Personal.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
  //      return null;
    }
    
    public PermisosDetallePersonal buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        PermisosDetallePersonal detallePermisosPersonal = null;
        ResultSet resultado;
        String sentenciaSQL;
        
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                detallePermisosPersonal = new PermisosDetallePersonal();
                detallePermisosPersonal.setCodigo(resultado.getInt("DetallePermisosPersonal_Id"));
//                detallePermisosPersonal.setDescripcion(resultado.getString("DetallePermisosPersonal_Descripcion"));
            }
            resultado.close();
            return detallePermisosPersonal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

//    public DefaultComboBoxModel llenarCBODetallePermisosPersonal(int estado) throws SQLException, ExcepcionSQLConsulta {
//        ResultSet resultado;
//        DetallePermisosPersonal itemFacturaElectronica;
//        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_MostrarTodos_sp(?)");
//        prcProcedimientoAlmacenado.setInt(1, estado);
//        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
//        try {
//            resultado = prcProcedimientoAlmacenado.executeQuery();
//            while (resultado.next()) {
//                itemFacturaElectronica = new DetallePermisosPersonal();
//                itemFacturaElectronica.setCodigo(resultado.getInt("DetallePermisosPersonal_Id"));
//                itemFacturaElectronica.setDescripcion(resultado.getString("DetallePermisosPersonal_Descripcion"));
////                combo.addItem(itemFacturaElectronica);
//                modelo.addElement(itemFacturaElectronica);
//            }
//            resultado.close();
//            return modelo;
//        } catch (SQLException e) {
//            throw new ExcepcionSQLConsulta(e);
//        }
//    }
    public int mostrarTodos(JTable table, int codigoPersonal) throws SQLException {
        ResultSet resultado;
        int contadorSeleccionadosTodos = 0;
        DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_MostrarPorPersonal_sp(?)");
            
            prcProcedimientoAlmacenado.setInt(1, codigoPersonal);
            
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                if (resultado.getInt("Estado_Id") == 1) {
                    registros[0] = Boolean.TRUE;
                    contadorSeleccionadosTodos++;
                } else {
                    registros[0] = Boolean.FALSE;
                    contadorSeleccionadosTodos--;
                    
                }
                registros[1] = resultado.getInt("DetallePermisoPersonal_Id");
                registros[2] = resultado.getString("Permisos_Descripcion");
                modeloTabla.addRow(registros);
            }
            resultado.close();
            
            table.setModel(modeloTabla);
            
        } catch (Exception e) {
           throw new SQLException("No se pudo Mostrar todos.\n"
                   + "Intente de nuevo o consulte con el Administrador.");
        }
        return contadorSeleccionadosTodos;
    }
    
    public List<PermisosDetallePersonal> buscarPermisosPorPersonal(int codigo) throws Exception {
        ResultSet resultado;
        PermisosDetallePersonal permisosDetallePersonal;
        List<PermisosDetallePersonal> listaPermisosPersonal = new ArrayList<>();
        PermisosDAOMySQL permisosDAOMySQL = new PermisosDAOMySQL(gestorJDBC);
        int contadorSeleccionadosTodos = 0;
        Permisos permisos;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("DetallePermisosPersonal_MostrarPorPersonal_sp(?)");
            
            prcProcedimientoAlmacenado.setInt(1, codigo);
            
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                permisos = new Permisos();
                
                permisosDetallePersonal = new PermisosDetallePersonal();
                permisosDetallePersonal.setCodigo(resultado.getInt("DetallePermisoPersonal_Id"));
                permisos.setDescripcion(resultado.getString("Permisos_Descripcion"));
                permisos.setCodigo(resultado.getInt("Permisos_Id"));
                permisosDetallePersonal.setPermisos(permisos);
                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                permisosDetallePersonal.setEstado(estado);
                listaPermisosPersonal.add(permisosDetallePersonal);
                
            }
            resultado.close();
            return listaPermisosPersonal;
            
        } catch (Exception e) {
            throw new SQLException("No se puedo buscar Permisos por Personal .\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        
    }
    
}
