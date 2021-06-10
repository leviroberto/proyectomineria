/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.FormGestionarValorizacion;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa3_dominio.TipoCliente;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojeru_san.componentes.RSDateChooser;

/**
 *
 * @author FiveCod Software
 */
public class LiquidacionDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public LiquidacionDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Liquidacion liquidacion) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDate(1, liquidacion.getFecha());
            prcProcedimientoAlmacenado.setInt(2, liquidacion.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, liquidacion.getProcedencia().getCodigo());
            prcProcedimientoAlmacenado.setString(4, liquidacion.getLote());
            prcProcedimientoAlmacenado.setString(5, liquidacion.getTmh());
            prcProcedimientoAlmacenado.setString(6, liquidacion.getH2o());
            prcProcedimientoAlmacenado.setString(7, liquidacion.getTms());
            prcProcedimientoAlmacenado.setString(8, liquidacion.getLeyau());
            prcProcedimientoAlmacenado.setString(9, liquidacion.getLeyag());
            prcProcedimientoAlmacenado.setString(10, liquidacion.getInter());
            prcProcedimientoAlmacenado.setString(11, liquidacion.getRec());
            prcProcedimientoAlmacenado.setString(12, liquidacion.getMaquilla());
            prcProcedimientoAlmacenado.setString(13, liquidacion.getFactor());
            prcProcedimientoAlmacenado.setString(14, liquidacion.getConscn());
            prcProcedimientoAlmacenado.setString(15, liquidacion.getEscalador());
            prcProcedimientoAlmacenado.setString(16, liquidacion.getStms());
            prcProcedimientoAlmacenado.setString(17, liquidacion.getEstadoLiquidacion());
            prcProcedimientoAlmacenado.setString(18, liquidacion.getReintegro());
            prcProcedimientoAlmacenado.setString(19, liquidacion.getFacturado());
            prcProcedimientoAlmacenado.setString(20, liquidacion.getTotalus());
            prcProcedimientoAlmacenado.setInt(21, liquidacion.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(22, liquidacion.getEmpresa().getCodigo());
            prcProcedimientoAlmacenado.setString(23, liquidacion.getProteccion());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar la Liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //     return 0;
    }

    public int modificar(Liquidacion liquidacion) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDate(1, liquidacion.getFecha());
            prcProcedimientoAlmacenado.setInt(2, liquidacion.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, liquidacion.getProcedencia().getCodigo());
            prcProcedimientoAlmacenado.setString(4, liquidacion.getLote());
            prcProcedimientoAlmacenado.setString(5, liquidacion.getTmh());
            prcProcedimientoAlmacenado.setString(6, liquidacion.getH2o());
            prcProcedimientoAlmacenado.setString(7, liquidacion.getTms());
            prcProcedimientoAlmacenado.setString(8, liquidacion.getLeyau());
            prcProcedimientoAlmacenado.setString(9, liquidacion.getLeyag());
            prcProcedimientoAlmacenado.setString(10, liquidacion.getInter());
            prcProcedimientoAlmacenado.setString(11, liquidacion.getRec());
            prcProcedimientoAlmacenado.setString(12, liquidacion.getMaquilla());
            prcProcedimientoAlmacenado.setString(13, liquidacion.getFactor());
            prcProcedimientoAlmacenado.setString(14, liquidacion.getConscn());
            prcProcedimientoAlmacenado.setString(15, liquidacion.getEscalador());
            prcProcedimientoAlmacenado.setString(16, liquidacion.getStms());
            prcProcedimientoAlmacenado.setString(17, liquidacion.getEstadoLiquidacion());
            prcProcedimientoAlmacenado.setString(18, liquidacion.getReintegro());
            prcProcedimientoAlmacenado.setString(19, liquidacion.getFacturado());
            prcProcedimientoAlmacenado.setString(20, liquidacion.getTotalus());
            prcProcedimientoAlmacenado.setInt(21, liquidacion.getPersonal().getCodigo());
            prcProcedimientoAlmacenado.setInt(22, liquidacion.getCodigo());
            prcProcedimientoAlmacenado.setString(23, liquidacion.getProteccion());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Modificar la Liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //     JOptionPane.showMessageDialog(null, e.getMessage());

        }
        //   return 0;
    }

    public int eliminar(Liquidacion liquidacion) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, liquidacion.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int verificarNumeroLote(int numero) throws SQLException {
        ResultSet resultado;
        int numeroObtenido = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_VerificarNumeroLote_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, numero);
            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                numeroObtenido = resultado.getInt("contador");

            }
            resultado.close();
            return numeroObtenido;
        } catch (Exception e) {
            throw new SQLException("No se ha podido Verificar el Numero de Lote.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        //  return 0;

    }

    public void llenarCamposNuevo(RSDateChooser fecha, FCMaterialTextField h2o, FCMaterialTextField leyag, FCMaterialTextField inter, FCMaterialTextField maquilla, FCMaterialTextField conscn) throws SQLException {
        ResultSet resultado;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_LlenarNuevo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                fecha.setDatoFecha(resultado.getDate("fecha"));
                h2o.setText(resultado.getString("Liquidacion_H2O"));
                leyag.setText(resultado.getString("Liquidacion_Leyag"));
                inter.setText(resultado.getString("Liquidacion_Inter"));
                maquilla.setText(resultado.getString("Liquidacion_Maquilla"));
                conscn.setText(resultado.getString("Liquidacion_Conscon"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido llenar Campos Nuevo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Liquidacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_H2O"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tms"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyau"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyag"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Totalus"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Reintegro"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Facturado"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Estado"));
                filaTabla.agregarValorCelda(resultado.getString("tipoCliente"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todas las Liquidaciones.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public Liquidacion buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        Liquidacion liquidacion = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {

            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setCodigo(resultado.getInt("Liquidacion_Id"));
                liquidacion.setFecha(resultado.getDate("Liquidacion_Fecha"));
                liquidacion.setClienteEntrante(clienteEntranteDao.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                Procedencia procedencia = procedenciaDAOMySQL.buscarPorCodigo(resultado.getInt("Procedencia_Id"));
                liquidacion.setProcedencia(procedencia);
                liquidacion.setLote(resultado.getString("Liquidacion_Lote"));
                liquidacion.setTmh(resultado.getString("Liquidacion_Tmh"));
                liquidacion.setH2o(resultado.getString("Liquidacion_H2O"));
                liquidacion.setTms(resultado.getString("Liquidacion_Tms"));
                liquidacion.setLeyau(resultado.getString("Liquidacion_Leyau"));
                liquidacion.setLeyag(resultado.getString("Liquidacion_Leyag"));
                liquidacion.setInter(resultado.getString("Liquidacion_Inter"));
                liquidacion.setRec(resultado.getString("Liquidacion_Rec"));
                liquidacion.setEscalador(resultado.getString("Liquidacion_Escalador"));
                liquidacion.setMaquilla(resultado.getString("Liquidacion_Maquilla"));
                liquidacion.setFactor(resultado.getString("Liquidacion_Factor"));
                liquidacion.setConscn(resultado.getString("Liquidacion_Conscon"));
                liquidacion.setStms(resultado.getString("Liquidacion_Stms"));
                liquidacion.setTotalus(resultado.getString("Liquidacion_Totalus"));
                liquidacion.setReintegro(resultado.getString("Liquidacion_Reintegro"));
                liquidacion.setFacturado(resultado.getString("Liquidacion_Facturado"));
                liquidacion.setEstadoLiquidacion(resultado.getString("Liquidacion_Estado"));
                liquidacion.setProteccion(resultado.getString("proteccion"));

            }

            resultado.close();
            return liquidacion;
        } catch (Exception e) {
            throw new SQLException("No se ha podido Buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        //     return null;
    }

    public int modificarFacturado(String numero, int codigoLiquidacion) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_ModificarFacturado_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, "SI-" + numero);
            prcProcedimientoAlmacenado.setInt(2, codigoLiquidacion);

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar Facturado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //   return 0;
    }

    public int modificarEstadoReintegro(int codigo, String texto) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_CambiarEstadoReintegro_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            prcProcedimientoAlmacenado.setString(2, texto);

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar Facturado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //   return 0;
    }

    public void mostrarLiquidacionPorDNICliente(JTable tableValorizacion, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableValorizacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_BuscarPorClienteLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Nombres"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));

                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                modeloTabla.agregarFila(filaTabla);
                FormGestionarValorizacion.txtNombre.setText(resultado.getString("ClienteEntrante_Nombres"));
                FormGestionarValorizacion.txtCodigoCliente.setText("" + resultado.getInt("ClienteEntrante_Id"));
                FormGestionarValorizacion.txtApellido.setText(resultado.getString("ClienteEntrante_Apellidos"));
                FormGestionarValorizacion.txtDni.setText(resultado.getString("ClienteEntrante_DNI"));

            }
            resultado.close();
            tableValorizacion.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Liquidacion Por DNI Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarLiquidacionPorTipoCliente(int i, JTable tableValorizacion, int tipo) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableValorizacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_BuscarPorTipoCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setInt(2, tipo);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Nombres"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableValorizacion.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Liquidacion Por Tipo Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarParaDetalleLiquidacion(JTable tableLiquidacion, int cliente, Date fecha) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableLiquidacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_BuscarParaDetalleValorizacion_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, cliente);
            prcProcedimientoAlmacenado.setDate(2, fecha);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Liquidacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_H2O"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tms"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyau"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyag"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Stms"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Totalus"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Estado"));
                filaTabla.agregarValorCelda(resultado.getString("Valorizacion_Id"));

                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableLiquidacion.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Para Detalle Liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int actualizarEstado(Liquidacion liquidacion) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_ModificarEstado_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, liquidacion.getCodigo());
            prcProcedimientoAlmacenado.setString(2, liquidacion.getEstadoLiquidacion());
            prcProcedimientoAlmacenado.setInt(3, liquidacion.getValorizacionId());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Actulizar Estado de Liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public Liquidacion buscarPorCodigoCliente(int codigo, String fecha) throws SQLException {
        Liquidacion liquidacion = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {

            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_BuscarPorCodigoCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            prcProcedimientoAlmacenado.setString(2, fecha);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setCodigo(resultado.getInt("Liquidacion_Id"));
                liquidacion.setFecha(resultado.getDate("Liquidacion_Fecha"));
                liquidacion.setClienteEntrante(clienteEntranteDao.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                Procedencia procedencia = procedenciaDAOMySQL.buscarPorCodigo(resultado.getInt("Procedencia_Id"));
                liquidacion.setProcedencia(procedencia);
                liquidacion.setLote(resultado.getString("Liquidacion_Lote"));
                liquidacion.setTmh(resultado.getString("Liquidacion_Tmh"));
                liquidacion.setH2o(resultado.getString("Liquidacion_H2O"));
                liquidacion.setTms(resultado.getString("Liquidacion_Tms"));
                liquidacion.setLeyau(resultado.getString("Liquidacion_Leyau"));
                liquidacion.setLeyag(resultado.getString("Liquidacion_Leyag"));
                liquidacion.setInter(resultado.getString("Liquidacion_Inter"));
                liquidacion.setRec(resultado.getString("Liquidacion_Rec"));
                liquidacion.setEscalador(resultado.getString("Liquidacion_Escalador"));
                liquidacion.setMaquilla(resultado.getString("Liquidacion_Maquilla"));
                liquidacion.setFactor(resultado.getString("Liquidacion_Factor"));
                liquidacion.setConscn(resultado.getString("Liquidacion_Conscon"));
                liquidacion.setStms(resultado.getString("Liquidacion_Stms"));
                liquidacion.setTotalus(resultado.getString("Liquidacion_Totalus"));
                liquidacion.setReintegro(resultado.getString("Liquidacion_Reintegro"));
                liquidacion.setFacturado(resultado.getString("Liquidacion_Facturado"));
                liquidacion.setEstadoLiquidacion(resultado.getString("Liquidacion_Estado"));

            }

            resultado.close();
            return liquidacion;
        } catch (Exception e) {
            throw new SQLException("No se ha podido buscar Por Codigo Cliente Liquidacion.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        //  return null;
    }

    public void mostrarLiquidacionLotesPorCliente(JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_MostrarLotesCliente_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();

                filaTabla.agregarValorCelda(resultado.getInt("Liquidacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("nombrecompleto"));
                filaTabla.agregarValorCelda(resultado.getString("totallotes"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();

            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Liquidacion Lotes Por Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Liquidacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_H2O"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Tms"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyau"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Leyag"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Totalus"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Reintegro"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Facturado"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Estado"));
                filaTabla.agregarValorCelda(resultado.getString("tipoCliente"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodosGrafico(int estado, JTable table) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_MostrarGrafico_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Liquidacion_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Liquidacion_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("totalus"));
                filaTabla.agregarValorCelda(resultado.getString("tipoCliente"));

                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todos Graficos.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public Liquidacion buscarLiquidacionPorLote(int numeroLoteSeleccionado) throws SQLException {
        Liquidacion liquidacion = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {

            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Liquidacion_buscarLiquidacionPorLote_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, numeroLoteSeleccionado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                liquidacion = new Liquidacion();
                liquidacion.setCodigo(resultado.getInt("Liquidacion_Id"));
                liquidacion.setFecha(resultado.getDate("Liquidacion_Fecha"));
                liquidacion.setClienteEntrante(clienteEntranteDao.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                Procedencia procedencia = procedenciaDAOMySQL.buscarPorCodigo(resultado.getInt("Procedencia_Id"));
                liquidacion.setProcedencia(procedencia);
                liquidacion.setLote(resultado.getString("Liquidacion_Lote"));
                liquidacion.setTmh(resultado.getString("Liquidacion_Tmh"));
                liquidacion.setH2o(resultado.getString("Liquidacion_H2O"));
                liquidacion.setTms(resultado.getString("Liquidacion_Tms"));
                liquidacion.setLeyau(resultado.getString("Liquidacion_Leyau"));
                liquidacion.setLeyag(resultado.getString("Liquidacion_Leyag"));
                liquidacion.setInter(resultado.getString("Liquidacion_Inter"));
                liquidacion.setRec(resultado.getString("Liquidacion_Rec"));
                liquidacion.setEscalador(resultado.getString("Liquidacion_Escalador"));
                liquidacion.setMaquilla(resultado.getString("Liquidacion_Maquilla"));
                liquidacion.setFactor(resultado.getString("Liquidacion_Factor"));
                liquidacion.setConscn(resultado.getString("Liquidacion_Conscon"));
                liquidacion.setStms(resultado.getString("Liquidacion_Stms"));
                liquidacion.setTotalus(resultado.getString("Liquidacion_Totalus"));
                liquidacion.setReintegro(resultado.getString("Liquidacion_Reintegro"));
                liquidacion.setFacturado(resultado.getString("Liquidacion_Facturado"));
                liquidacion.setEstadoLiquidacion(resultado.getString("Liquidacion_Estado"));

            }

            resultado.close();
            return liquidacion;
        } catch (Exception e) {
            throw new SQLException("No se ha podido Buscar Liquidacion Por Lote.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //    return null;

    }
}
