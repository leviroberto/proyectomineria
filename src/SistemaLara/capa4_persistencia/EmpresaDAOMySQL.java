/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa3_dominio.Empresa;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author XGamer
 */
public class EmpresaDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public EmpresaDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Empresa empresa) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, empresa.getDepartamento());
            prcProcedimientoAlmacenado.setString(2, empresa.getDireccion());
            prcProcedimientoAlmacenado.setString(3, empresa.getNombreComercial());
            prcProcedimientoAlmacenado.setString(4, empresa.getDistrito());
            prcProcedimientoAlmacenado.setString(5, empresa.getNombreLegal());
            prcProcedimientoAlmacenado.setString(6, empresa.getNroDocumento());
            prcProcedimientoAlmacenado.setString(7, empresa.getTipoDocumento());
            prcProcedimientoAlmacenado.setString(8, empresa.getUbigeo());
            prcProcedimientoAlmacenado.setString(9, empresa.getUrbanizacion());
            prcProcedimientoAlmacenado.setString(10, empresa.getProvincia());
            prcProcedimientoAlmacenado.setString(11, empresa.getRutaPFX());
            prcProcedimientoAlmacenado.setString(12, empresa.getClavePFX());
            prcProcedimientoAlmacenado.setString(13, empresa.getRutaXML());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la Empresa.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public int modificar(Empresa empresa) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_Modificar_sp(?,?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, empresa.getCodigo());
            prcProcedimientoAlmacenado.setString(2, empresa.getNombreComercial());
            prcProcedimientoAlmacenado.setString(3, empresa.getNroDocumento());
            prcProcedimientoAlmacenado.setString(4, empresa.getDireccion());
            prcProcedimientoAlmacenado.setString(5, empresa.getTelefono());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar la Empresa.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int eliminar(Empresa empresa) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, empresa.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar la Empresa.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<Empresa> mostrarTodos(int estado) throws ExcepcionSQLConsulta {

        try {
            Fila filaTabla;
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            List<Empresa> listaTipoCliente = new ArrayList<Empresa>();
            Empresa empresa;
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                empresa = new Empresa();
                empresa.setCodigo(resultado.getInt("tc.TipoCliente_Id"));
                empresa.setNombreComercial(resultado.getString("tc.TipoCliente_Descripcion"));
                listaTipoCliente.add(empresa);
            }
            resultado.close();
            return listaTipoCliente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public Empresa buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Empresa empresa = null;
        ResultSet resultado;
        String sentenciaSQL;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_buscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {
                empresa = new Empresa();
                empresa.setCodigo(resultado.getInt("Empresa_id"));
                empresa.setDepartamento(resultado.getString("Empresa_Departamento"));
                empresa.setNombreComercial(resultado.getString("Empresa_Nombre_Comercial"));
                empresa.setDistrito(resultado.getString("Empresa_Distrito"));
                empresa.setNombreLegal(resultado.getString("Empresa_Nombre_Legal"));
                empresa.setNroDocumento(resultado.getString("Empresa_Nro_Documento"));
                empresa.setTipoDocumento(resultado.getString("Empresa_Tipo_Documento"));
                empresa.setUbigeo(resultado.getString("Empresa_Ubigeo"));
                empresa.setUrbanizacion(resultado.getString("Empresa_Urbanizacion"));
                empresa.setProvincia(resultado.getString("Empresa_Provincia"));
                empresa.setDireccion(resultado.getString("Empresa_Direccion"));
                empresa.setTelefono(resultado.getString("Empresa_Telefono"));

                empresa.setMision(resultado.getString("Empresa_Mision"));
                empresa.setVision(resultado.getString("Empresa_Vision"));
                empresa.setFechaRegistro(resultado.getDate("Empresa_Fecha_Registrado"));
                empresa.setRutaImagen(resultado.getString("Empresa_RutaIImagen"));
                empresa.setEmpresabd(resultado.getString("Empresa_NombreBD"));

            }
            resultado.close();
            return empresa;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public DefaultComboBoxModel llenarCBOTipoCliente(int estado) throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;
        Empresa itemFacturaElectronica;
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("TipoCliente_MostrarTodos_sp(?)");
        prcProcedimientoAlmacenado.setInt(1, estado);
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                itemFacturaElectronica = new Empresa();
                itemFacturaElectronica.setCodigo(resultado.getInt("TipoCliente_Id"));
                itemFacturaElectronica.setNombreComercial(resultado.getString("TipoCliente_Descripcion"));
//                combo.addItem(itemFacturaElectronica);
                modelo.addElement(itemFacturaElectronica);
            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public String obtenerImagen() throws SQLException, ExcepcionSQLConsulta {
        ResultSet resultado;

        String imagen = "";
        CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Empresa_ObtenerImagen()");
        try {
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                imagen = resultado.getString("Empresa_RutaIImagen");

            }
            resultado.close();
            return imagen;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

}
