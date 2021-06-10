/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.Cambio;
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
public class CambioDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public CambioDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int modificar(Cambio cambio) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_Actualizar_sp(?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, cambio.getDolar());
            prcProcedimientoAlmacenado.setString(2, cambio.getTarifa());
            prcProcedimientoAlmacenado.setString(3, cambio.getTarifaa());
            prcProcedimientoAlmacenado.setString(4, cambio.getTrans1());
            prcProcedimientoAlmacenado.setString(5, cambio.getTrans2());
            prcProcedimientoAlmacenado.setString(6, cambio.getPoli());
            prcProcedimientoAlmacenado.setString(7, cambio.getTonelada());
            prcProcedimientoAlmacenado.setInt(8, cambio.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo agregar el cambio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void llenarCamposNuevo(FCMaterialTextField codigo, FCMaterialTextField dolar, FCMaterialTextField tarifaa, FCMaterialTextField tarifa, FCMaterialTextField trans1, FCMaterialTextField trans2, FCMaterialTextField poli, FCMaterialTextField tonelada) throws SQLException {
        ResultSet resultado;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_LlenarCambio_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                codigo.setText(resultado.getString("Cambios_Id"));
                dolar.setText(resultado.getString("Cambios_Dolar"));
                tarifa.setText(resultado.getString("Cambios_Tarifa"));
                tarifaa.setText(resultado.getString("Cambios_Tarifaa"));
                trans1.setText(resultado.getString("Cambios_Trans1"));
                trans2.setText(resultado.getString("Cambios_Trans2"));
                poli.setText(resultado.getString("Cambios_Poli"));
                tonelada.setText(resultado.getString("Cambios_Tonelada"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido  LLenar los Campos Nuevo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Cambios_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Dolar"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Tarifaa"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Tarifa"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Trans1"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Trans2"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Poli"));
                filaTabla.agregarValorCelda(resultado.getString("Cambios_Tonelada"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
throw new SQLException("No se ha podido Todos Los Cambios.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public Cambio obtenerCambio() throws SQLException {
        ResultSet resultado;
        Cambio cambio = null;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_LlenarCambio_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                cambio = new Cambio();
                cambio.setCodigo(resultado.getInt("Cambios_Id"));
                cambio.setDolar(resultado.getString("Cambios_Dolar"));
                cambio.setTarifa(resultado.getString("Cambios_Tarifa"));
                cambio.setTarifaa(resultado.getString("Cambios_Tarifaa"));
                cambio.setTrans1(resultado.getString("Cambios_Trans1"));
                cambio.setTrans2(resultado.getString("Cambios_Trans2"));
                cambio.setPoli(resultado.getString("Cambios_Poli"));
                cambio.setTonelada(resultado.getString("Cambios_Tonelada"));

            }
            resultado.close();
            return cambio;

        } catch (Exception e) {
             throw new SQLException("No se ha podido Obtener el Cambio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
      //  return null;

    }

//    public void modificarCambio(Cambio  cambio) throws ExcepcionSQLConsulta, SQLException {
//        ResultSet resultado;
//        try {
//            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_ActulizarDolar_sp(?)");
//            prcProcedimientoAlmacenado.setString(1, cambio.getDolar());
//            resultado = prcProcedimientoAlmacenado.executeQuery();
//            resultado.close();
//        } catch (Exception e) {
//            throw new SQLException("No se pudo Mostrar el Adelanto.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
//        }
//    
//}
    public void modificarCambio(String cambio) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_ActulizarDolar_sp(?)");
            prcProcedimientoAlmacenado.setString(1, cambio);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            resultado.close();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el Cambio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarCambioDolar(int estado, JLabel label) throws SQLException {
        ResultSet resultado;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_MostrarDolar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                label.setText(resultado.getString("Cambios_Dolar"));
            }
            resultado.close();
        } catch (Exception e) {
           throw new SQLException("No se ha podido  mostrar el Cambio de Dolar.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public int guardar(Cambio cambio) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Cambio_Agregar_sp(?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, cambio.getDolar());
            prcProcedimientoAlmacenado.setString(2, cambio.getPoli());
            prcProcedimientoAlmacenado.setString(3, cambio.getTarifa());
            prcProcedimientoAlmacenado.setString(4, cambio.getTarifaa());
            prcProcedimientoAlmacenado.setString(5, cambio.getTonelada());
            prcProcedimientoAlmacenado.setString(6, cambio.getTrans1());
            prcProcedimientoAlmacenado.setString(7, cambio.getTrans2());

            return prcProcedimientoAlmacenado.executeUpdate();

        } catch (Exception e) {
            throw new SQLException("No se pudo guardar el Cambio.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

}
