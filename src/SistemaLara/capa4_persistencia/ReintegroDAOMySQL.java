/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.FormGestionarValorizacion;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.Reintegro;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa3_dominio.TipoCliente;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojeru_san.componentes.RSDateChooser;
import rojerusan.RSTableMetro;

/**
 *
 * @author FiveCod Software
 */
public class ReintegroDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public ReintegroDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public int agregar(Reintegro reintegro) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDate(1, reintegro.getFecha());
            prcProcedimientoAlmacenado.setInt(2, reintegro.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, reintegro.getProcedencia().getCodigo());
            prcProcedimientoAlmacenado.setString(4, reintegro.getLote());
            prcProcedimientoAlmacenado.setDouble(5, reintegro.getTmh());
            prcProcedimientoAlmacenado.setDouble(6, reintegro.getH2o());
            prcProcedimientoAlmacenado.setDouble(7, reintegro.getLeyau());
            prcProcedimientoAlmacenado.setDouble(8, reintegro.getLeyag());
            prcProcedimientoAlmacenado.setDouble(9, reintegro.getInter());
            prcProcedimientoAlmacenado.setDouble(10, reintegro.getRec());
            prcProcedimientoAlmacenado.setDouble(11, reintegro.getMaquilla());
            prcProcedimientoAlmacenado.setDouble(12, reintegro.getFactor());
            prcProcedimientoAlmacenado.setDouble(13, reintegro.getConscon());
            prcProcedimientoAlmacenado.setDouble(14, reintegro.getEscalador());
            prcProcedimientoAlmacenado.setDouble(15, reintegro.getStms());
            prcProcedimientoAlmacenado.setDouble(16, reintegro.getTotalUs());
            prcProcedimientoAlmacenado.setInt(17, reintegro.getEstado().getCodigo());
            prcProcedimientoAlmacenado.setInt(18, reintegro.getLiquidacion().getCodigo());
            prcProcedimientoAlmacenado.setDouble(19, reintegro.getReintrego());

            prcProcedimientoAlmacenado.setString(20, reintegro.getPago());
            prcProcedimientoAlmacenado.setDouble(21, reintegro.getTms());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
//            throw new SQLException("No se pudo registrar la Reintegro.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 0;
    }

    public int modificar(Reintegro reintegro) throws SQLException {
        int resultado = 0;
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setDate(1, reintegro.getFecha());
            prcProcedimientoAlmacenado.setInt(2, reintegro.getClienteEntrante().getCodigo());
            prcProcedimientoAlmacenado.setInt(3, reintegro.getProcedencia().getCodigo());
            prcProcedimientoAlmacenado.setString(4, reintegro.getLote());
            prcProcedimientoAlmacenado.setDouble(5, reintegro.getTmh());
            prcProcedimientoAlmacenado.setDouble(6, reintegro.getH2o());
            prcProcedimientoAlmacenado.setDouble(7, reintegro.getLeyau());
            prcProcedimientoAlmacenado.setDouble(8, reintegro.getLeyag());
            prcProcedimientoAlmacenado.setDouble(9, reintegro.getInter());
            prcProcedimientoAlmacenado.setDouble(10, reintegro.getRec());
            prcProcedimientoAlmacenado.setDouble(11, reintegro.getMaquilla());
            prcProcedimientoAlmacenado.setDouble(12, reintegro.getFactor());
            prcProcedimientoAlmacenado.setDouble(13, reintegro.getConscon());
            prcProcedimientoAlmacenado.setDouble(14, reintegro.getEscalador());
            prcProcedimientoAlmacenado.setDouble(15, reintegro.getStms());
            prcProcedimientoAlmacenado.setDouble(16, reintegro.getTotalUs());
            prcProcedimientoAlmacenado.setInt(17, reintegro.getEstado().getCodigo());
            prcProcedimientoAlmacenado.setDouble(18, reintegro.getReintrego());

            prcProcedimientoAlmacenado.setString(19, reintegro.getPago());
            prcProcedimientoAlmacenado.setDouble(20, reintegro.getTms());
            prcProcedimientoAlmacenado.setInt(21, reintegro.getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
//            throw new SQLException("No se pudo Modificar la Reintegro.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return 0;
    }

    public int eliminar(Reintegro reintegro) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_Eliminar_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, reintegro.getCodigo());
            prcProcedimientoAlmacenado.setInt(2, reintegro.getLiquidacion().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el reintegro.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public int verificarNumeroLote(int numero) throws SQLException {
        ResultSet resultado;
        int numeroObtenido = 0;

        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_VerificarNumeroLote_sp(?)");
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_LlenarNuevo_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {

                fecha.setDatoFecha(resultado.getDate("fecha"));
                h2o.setText(resultado.getString("Reintegro_H2O"));
                leyag.setText(resultado.getString("Reintegro_Leyag"));
                inter.setText(resultado.getString("Reintegro_Inter"));
                maquilla.setText(resultado.getString("Reintegro_Maquilla"));
                conscn.setText(resultado.getString("Reintegro_Conscon"));
            }
            resultado.close();

        } catch (Exception e) {
            throw new SQLException("No se ha podido llenar Campos Nuevo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void mostrarTodosPorLiquidacion(int codigoLiquidacion, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_MostrarTodos_Loquidacion_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigoLiquidacion);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_H2o"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_LeyAu"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_LeyAg"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Stms"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_TotalUs"));
                filaTabla.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Pago"));

                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todas las Reintegroes.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public void mostrarTodos(JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_MostrarTodos_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_H2o"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_LeyAu"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_LeyAg"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Stms"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_TotalUs"));
                filaTabla.agregarValorCelda(resultado.getString("Estado_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Pago"));

                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Todas las Reintegroes.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public Reintegro buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta, SQLException {
        Reintegro reintegro = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {

            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_BuscarPorCodigo_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                reintegro = new Reintegro();
                reintegro.setCodigo(resultado.getInt("Reintegro_Id"));
                reintegro.setFecha(resultado.getDate("Reintegro_Fecha"));
                reintegro.setClienteEntrante(clienteEntranteDao.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                Procedencia procedencia = procedenciaDAOMySQL.buscarPorCodigo(resultado.getInt("Procedencia_Id"));
                reintegro.setProcedencia(procedencia);
                reintegro.setLote(resultado.getString("Reintegro_Lote"));
                reintegro.setTmh(resultado.getDouble("Reintegro_Tmh"));
                reintegro.setH2o(resultado.getDouble("Reintegro_H2o"));
                reintegro.setTms(resultado.getDouble("Reintegro_Tms"));
                reintegro.setLeyau(resultado.getDouble("Reintegro_LeyAu"));
                reintegro.setLeyag(resultado.getDouble("Reintegro_LeyAg"));
                reintegro.setInter(resultado.getDouble("Reintegro_Inter"));
                reintegro.setRec(resultado.getDouble("Reintegro_Rec"));
                reintegro.setMaquilla(resultado.getDouble("Reintegro_Maquilla"));
                reintegro.setFactor(resultado.getDouble("Reintegro_Factor"));
                reintegro.setConscon(resultado.getDouble("Reintegro_Conscon"));
                reintegro.setStms(resultado.getDouble("Reintegro_Stms"));
                reintegro.setTotalUs(resultado.getDouble("Reintegro_Totalus"));
                reintegro.setTotalUs(resultado.getDouble("Reintegro_TotalUs"));

                Estado estado = new Estado();
                estado.setCodigo(resultado.getInt("Estado_Id"));
                reintegro.setEscalador(resultado.getDouble("Reintegro_Escalador"));

                reintegro.setReintrego(resultado.getDouble("Reintegro_Reintegro"));
                reintegro.setPago(resultado.getString("Reintegro_Pago"));

                reintegro.setEstado(estado);

            }

            resultado.close();
            return reintegro;
        } catch (Exception e) {
            throw new SQLException("No se ha podido Buscar Por Codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        //     return null;
    }

    public int modificarFacturado(String numero, int codigoReintegro) throws SQLException {
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_ModificarFacturado_sp(?,?)");
            prcProcedimientoAlmacenado.setString(1, "SI-" + numero);
            prcProcedimientoAlmacenado.setInt(2, codigoReintegro);

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar Facturado.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //      JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //   return 0;
    }

    public void mostrarReintegroPorDNICliente(JTable tableValorizacion, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableValorizacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_BuscarPorClienteLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Nombres"));
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
            throw new SQLException("No se ha podido mostrar Reintegro Por DNI Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarReintegroPorTipoCliente(int i, JTable tableValorizacion, int tipo) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableValorizacion.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_BuscarPorTipoCliente_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, i);
            prcProcedimientoAlmacenado.setInt(2, tipo);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("ClienteEntrante_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_Nombres"));
                filaTabla.agregarValorCelda(resultado.getString("TipoCliente_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableValorizacion.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Reintegro Por Tipo Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }

    }

    public void mostrarParaDetalleReintegro(JTable tableReintegro, int cliente, Date fecha) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tableReintegro.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_BuscarParaDetalleValorizacion_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, cliente);
            prcProcedimientoAlmacenado.setDate(2, fecha);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Procedencia_Descripcion"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_H2O"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tms"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Leyau"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Leyag"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Stms"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Totalus"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Estado"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();
            tableReintegro.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Para Detalle Reintegro.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }

    public int actualizarEstado(Reintegro reintegro) throws SQLException {

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_ModificarEstado_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, reintegro.getCodigo());
            //prcProcedimientoAlmacenado.setString(2, reintegro.getEstadoReintegro());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Actulizar Estado de Reintegro.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public Reintegro buscarPorCodigoCliente(int codigo) throws SQLException {
        Reintegro reintegro = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {

            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_BuscarPorCodigoCliente_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                reintegro = new Reintegro();
                reintegro.setCodigo(resultado.getInt("Reintegro_Id"));
                reintegro.setFecha(resultado.getDate("Reintegro_Fecha"));
                reintegro.setClienteEntrante(clienteEntranteDao.buscarPorCodigo(resultado.getInt("ClienteEntrante_Id")));
                Procedencia procedencia = procedenciaDAOMySQL.buscarPorCodigo(resultado.getInt("Procedencia_Id"));
                reintegro.setProcedencia(procedencia);
//                reintegro.setLote(resultado.getString("Reintegro_Lote"));
//                reintegro.setTmh(resultado.getString("Reintegro_Tmh"));
//                reintegro.setH2o(resultado.getString("Reintegro_H2O"));
//                reintegro.setTms(resultado.getString("Reintegro_Tms"));
//                reintegro.setLeyau(resultado.getString("Reintegro_Leyau"));
//                reintegro.setLeyag(resultado.getString("Reintegro_Leyag"));
//                reintegro.setInter(resultado.getString("Reintegro_Inter"));
//                reintegro.setRec(resultado.getString("Reintegro_Rec"));
//                reintegro.setEscalador(resultado.getString("Reintegro_Escalador"));
//                reintegro.setMaquilla(resultado.getString("Reintegro_Maquilla"));
//                reintegro.setFactor(resultado.getString("Reintegro_Factor"));
//                reintegro.setConscn(resultado.getString("Reintegro_Conscon"));
//                reintegro.setStms(resultado.getString("Reintegro_Stms"));
//                reintegro.setTotalus(resultado.getString("Reintegro_Totalus"));
//                reintegro.setReintegro(resultado.getString("Reintegro_Reintegro"));
//                reintegro.setFacturado(resultado.getString("Reintegro_Facturado"));
//                reintegro.setEstadoReintegro(resultado.getString("Reintegro_Estado"));

            }

            resultado.close();
            return reintegro;
        } catch (Exception e) {
            throw new SQLException("No se ha podido buscar Por Codigo Cliente Reintegro.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        //  return null;
    }

    public void mostrarReintegroLotesPorCliente(JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_MostrarLotesCliente_sp()");
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();

                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("ClienteEntrante_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("nombrecompleto"));
                filaTabla.agregarValorCelda(resultado.getString("totallotes"));
                modeloTabla.agregarFila(filaTabla);
            }
            resultado.close();

            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Reintegro Lotes Por Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table, String texto) throws SQLException {

        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_MostrarTodosPorLike_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            prcProcedimientoAlmacenado.setString(2, texto);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tmh"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_H2O"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Tms"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Fecha"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Leyau"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Leyag"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Inter"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Rec"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Maquilla"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Factor"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Conscon"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Escalador"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Totalus"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Reintegro"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Facturado"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Estado"));
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
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_MostrarGrafico_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Reintegro_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Lote"));
                filaTabla.agregarValorCelda(resultado.getString("Reintegro_Maquilla"));
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

    public Reintegro buscarReintegroPorLote(int numeroLoteSeleccionado) throws SQLException {
        Reintegro reintegro = null;
        ResultSet resultado;
        String sentenciaSQL;
        ClienteEntranteDAOMySQL clienteEntranteDao;
        ClienteEntrante clienteEntrante;
        ProcedenciaDAOMySQL procedenciaDAOMySQL;
        try {
            procedenciaDAOMySQL = new ProcedenciaDAOMySQL(gestorJDBC);
            clienteEntranteDao = new ClienteEntranteDAOMySQL(gestorJDBC);
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Reintegro_buscarReintegroPorLote_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, numeroLoteSeleccionado);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            TipoCliente tipoCliente;
            if (resultado.next()) {
                reintegro = new Reintegro();
                reintegro.setCodigo(resultado.getInt("Reintegro_Id"));
                reintegro.setLote(resultado.getString("Reintegro_Lote"));
                reintegro.setTms(resultado.getDouble("Reintegro_Tms"));
                reintegro.setLeyau(resultado.getDouble("Reintegro_LeyAu"));
                reintegro.setInter(resultado.getDouble("Reintegro_Inter"));
                reintegro.setStms(resultado.getDouble("Reintegro_Stms"));
                reintegro.setReintrego(resultado.getDouble("Reintegro_Reintegro"));

                reintegro.setTotalUs(resultado.getDouble("Reintegro_TotalUs"));

            }

            resultado.close();
            return reintegro;
        } catch (Exception e) {
//            throw new SQLException("No se ha podido Buscar Reintegro Por Lote.\n"
//                    + "Intente de nuevo o consulte con el Administrador.");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }

    public void mostrarNotaDebitpElectronico(int numero, String fechaInicio, String fechaFin, RSTableMetro tablaFacturaElectronica) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaFacturaElectronica.getModel();
        try {
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("NotaDebitoElectronica_MostrarFacturas_sp(?,?,?)");
            prcProcedimientoAlmacenado.setInt(1, numero);
            prcProcedimientoAlmacenado.setString(2, fechaInicio);
            prcProcedimientoAlmacenado.setString(3, fechaFin);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                filaTabla = new Fila();
                registros[0] = Boolean.FALSE;
                registros[1] = resultado.getInt("NotaDebito_Id");
                registros[2] = resultado.getString("NotaDebito_NumeroNotaDebito");
                registros[3] = resultado.getString("NotaDebito_Total");
                registros[4] = resultado.getString("NotaDebito_FechaEmision");

                modeloTabla.addRow(registros);
            }
            resultado.close();
            tablaFacturaElectronica.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se ha podido mostrar Factura Electronica.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
    }
}
