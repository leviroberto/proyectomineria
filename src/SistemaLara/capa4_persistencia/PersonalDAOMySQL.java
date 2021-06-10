package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Empresa;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;

/**
 *
 * @author FiveCod Software
 */
public class PersonalDAOMySQL {

    private final GestorJDBC gestorJDBC;

    public PersonalDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public DefaultTableModel buscarPorNombre(String nombre) throws ExcepcionSQLConsulta {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_buscarPorNombre_sp(?)");
            prcProcedimientoAlmacenado.setString(1, nombre);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                registros[0] = resultado.getInt("p.Personal_Id");
                registros[1] = resultado.getString("p.Personal_Nombres");
                registros[2] = resultado.getString("p.Personal_Apellidos");
                registros[3] = resultado.getString("p.Personal_DNI");
                registros[4] = resultado.getString("p.Personal_Sexo");
                registros[5] = resultado.getString("p.Personal_Telefono");
                registros[6] = resultado.getString("p.Personal_Email");
                registros[7] = resultado.getString("p.Personal_Direccion");
                registros[8] = resultado.getString("tp.TipoPersonal_Descripcion");
                modelo.addRow(registros);

            }
            resultado.close();
            return modelo;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public Personal buscarPorCodigo(int codigo) throws ExcepcionSQLConsulta {
        Personal personal = null;
        ResultSet resultado;
        String sentenciaSQL;
        TipoPersonalDAOMySQL tipoPersonalDAOMySQL = new TipoPersonalDAOMySQL(gestorJDBC);
        EstadoDAOMySQL estadoDAOMySQL = new EstadoDAOMySQL(gestorJDBC);

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_buscarPorCodigo(?)");
            prcProcedimientoAlmacenado.setInt(1, codigo);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {

                personal = new Personal();
                personal.setCodigo(resultado.getInt("p.Personal_Id"));
                personal.setNombres(resultado.getString("p.Personal_Nombres"));
                personal.setApellidos(resultado.getString("p.Personal_Apellidos"));
                personal.setDni(resultado.getString("p.Personal_DNI"));
                personal.setSexo(resultado.getString("p.Personal_Sexo"));
                personal.setTelefono(resultado.getString("p.Personal_Telefono"));
                personal.setEmail(resultado.getString("p.Personal_Email"));
                personal.setDireccion(resultado.getString("p.Personal_Direccion"));
                personal.setSueldo(resultado.getDouble("p.Personal_Sueldo"));
                personal.setFechaPago(resultado.getDate("p.Personal_Fecha_Pago"));
                personal.setFechaIngreso(resultado.getDate("p.Personal_Fecha_Ingreso"));
                personal.setTotalDiasPagar(resultado.getInt("p.Personal_TotalDiasPago"));
                personal.setPassword(resultado.getString("p.Personal_Password"));
                personal.setFechaNacimiento(resultado.getDate("p.Personal_Fecha_Nacimiento"));
                personal.setTipoPersonal(tipoPersonalDAOMySQL.buscarPorCodigo(resultado.getInt("tp.TipoPersonal_Id")));
                personal.setEstado(estadoDAOMySQL.buscarPorCodigo(resultado.getInt("e.Estado_Id")));

            }

            resultado.close();
            return personal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public int ingresar(Personal personal) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_Agregar_sp(?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, personal.getNombres());
            prcProcedimientoAlmacenado.setString(2, personal.getApellidos());
            prcProcedimientoAlmacenado.setString(3, personal.getDni());
            prcProcedimientoAlmacenado.setString(4, personal.getSexo());
            prcProcedimientoAlmacenado.setString(5, personal.getTelefono());
            prcProcedimientoAlmacenado.setString(6, personal.getDireccion());
            prcProcedimientoAlmacenado.setDate(7, personal.getFechaNacimiento());
            prcProcedimientoAlmacenado.setInt(8, personal.getTipoPersonal().getCodigo());
            prcProcedimientoAlmacenado.setString(9, personal.getEmail());
            prcProcedimientoAlmacenado.setString(10, personal.getPassword());
            prcProcedimientoAlmacenado.setInt(11, personal.getEmpresa().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo Registrar Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //return 0;
    }

    public int modificar(Personal personal) throws SQLException {
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_Modificar_sp(?,?,?,?,?,?,?,?,?,?,?)");
            prcProcedimientoAlmacenado.setString(1, personal.getNombres());
            prcProcedimientoAlmacenado.setString(2, personal.getApellidos());
            prcProcedimientoAlmacenado.setString(3, personal.getDni());
            prcProcedimientoAlmacenado.setString(4, personal.getSexo());
            prcProcedimientoAlmacenado.setString(5, personal.getTelefono());
            prcProcedimientoAlmacenado.setString(6, personal.getDireccion());
            prcProcedimientoAlmacenado.setDate(7, personal.getFechaNacimiento());
            prcProcedimientoAlmacenado.setString(8, personal.getEmail());
            prcProcedimientoAlmacenado.setString(9, personal.getPassword());
            prcProcedimientoAlmacenado.setInt(10, personal.getCodigo());
            prcProcedimientoAlmacenado.setInt(11, personal.getTipoPersonal().getCodigo());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar el personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //  JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //return 1;
    }

    public int eliminar(Personal personal) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_Eliminar_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, personal.getCodigo());
            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo eliminar el personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }

    public void mostrarTodos(int estado, JTable table) throws ExcepcionSQLConsulta, SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_MostrarTodos_sp(?)");
            prcProcedimientoAlmacenado.setInt(1, estado);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Personal_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Nombres"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Sexo"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Email"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Direccion"));
                filaTabla.agregarValorCelda(resultado.getString("TipoPersonal_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            table.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public DefaultTableModel buscarPersonalesPorDni(String dni) throws ExcepcionSQLConsulta {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            ResultSet resultado;
            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_buscarPorNombre_sp(?)");
            prcProcedimientoAlmacenado.setString(1, dni);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                registros[0] = resultado.getInt("p.Personal_Id");
                registros[1] = resultado.getString("p.Personal_Nombres");
                registros[2] = resultado.getString("p.Personal_Apellidos");
                registros[3] = resultado.getString("p.Personal_DNI");
                registros[4] = resultado.getString("p.Personal_Sexo");
                registros[5] = resultado.getString("p.Personal_Telefono");
                registros[6] = resultado.getString("p.Personal_Email");
                registros[7] = resultado.getString("p.Personal_Direccion");
                registros[8] = resultado.getString("tp.TipoPersonal_Descripcion");
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

    public Personal verificarUsuario(String dni, String password, Empresa empresa) throws ExcepcionSQLConsulta, SQLException {
        Personal personal = null;
        ResultSet resultado;
        TipoPersonalDAOMySQL aOMySQL = new TipoPersonalDAOMySQL(gestorJDBC);
        EmpresaDAOMySQL empresaDAOMySQL = new EmpresaDAOMySQL(gestorJDBC);
        RutaReporteDAOMySQL rutaReporteDAOMySQL = new RutaReporteDAOMySQL(gestorJDBC);
        PermisoDetallePersonalDAOMySQL permisoDetallePersonalDAOMySQL = new PermisoDetallePersonalDAOMySQL(gestorJDBC);
        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_VerificarInicioSesion_sp(?,?,?)");
            prcProcedimientoAlmacenado.setString(1, dni);
            prcProcedimientoAlmacenado.setString(2, password);
            prcProcedimientoAlmacenado.setInt(3, empresa.getCodigo());

            resultado = prcProcedimientoAlmacenado.executeQuery();
            if (resultado.next()) {

                personal = new Personal();
                personal.setCodigo(resultado.getInt("Personal_Id"));
                personal.setPassword(password);
                personal.setDni(resultado.getString("Personal_DNI"));
                personal.setNombres(resultado.getString("Personal_Nombres"));
                personal.setApellidos(resultado.getString("Personal_Apellidos"));
                personal.setTelefono(resultado.getString("Personal_Telefono"));
                personal.setSueldo(resultado.getDouble("Personal_Sueldo"));
                personal.setEmail(resultado.getString("Personal_Email"));
                personal.setPassword(password);

                personal.setEmpresa(empresa);
                personal.setTipoPersonal(aOMySQL.buscarPorCodigo(resultado.getInt("TipoPersonal_Id")));
                personal.setRutaReporte(rutaReporteDAOMySQL.obtenerRutaReporte(resultado.getInt("Empresa_Id")));
                personal.setListaDetallePermisos(permisoDetallePersonalDAOMySQL.buscarPermisosPorPersonal(resultado.getInt("Personal_Id")));

            }
            resultado.close();
            return personal;
        } catch (Exception e) {
            throw new SQLException("No se pudo verificar  Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //return null;

    }

    public Personal verificarRecuperacion(String dni) throws ExcepcionSQLConsulta {
        ResultSet resultado;
        String sentenciaSQL;

        Personal personal = null;
        try {
            sentenciaSQL = "SELECT * FROM personal WHERE dnipersonal = '" + dni + "'";

            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);

            if (resultado.next()) {
                personal = new Personal();
                personal.setDni(resultado.getString("dnipersonal"));
                personal.setPassword(resultado.getString("passwordpersonal"));

//                if (JOptionPane.showConfirmDialog(null, "SU USUARIO Y CONTRASEÑA SON:\n\n"
//                        + "USUARIO: " + personal.getDni() + "\nCONTRASEÑA: " + personal.getPassword() + "\n\n¿GUARDAR DATOS?", "CÓDIGO DE RECUPERACIÓN", JOptionPane.YES_NO_OPTION,
//                        JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
//
//                }
            } else {

                JOptionPane.showMessageDialog(null, "EL CÓDIGO INGRESADO NO ES VÁLIDO,\nINGRESE UN CÓDIGO DE RECUPERACIÓN\n"
                        + "VÁLIDO O CONTACTE AL ADMINISTRADOR DEL SISTEMA.", "¡ERROR AL RECUPERAR!", JOptionPane.ERROR_MESSAGE);
            }
            resultado.close();
            return personal;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public void mostrarTodos(int i, JTable tablaPersonal, String texto) throws SQLException {
        ResultSet resultado;
        Fila filaTabla;
        ModeloTabla modeloTabla = (ModeloTabla) tablaPersonal.getModel();
        try {

            String sentenciaSQL;
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_BuscarTodosPorLike_sp(?)");
            prcProcedimientoAlmacenado.setString(1, texto);

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(resultado.getInt("Personal_Id"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Nombres"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Apellidos"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_DNI"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Sexo"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Telefono"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Email"));
                filaTabla.agregarValorCelda(resultado.getString("Personal_Direccion"));
                filaTabla.agregarValorCelda(resultado.getString("TipoPersonal_Descripcion"));
                modeloTabla.agregarFila(filaTabla);

            }
            resultado.close();
            tablaPersonal.setModel(modeloTabla);

        } catch (Exception e) {
            throw new SQLException("No se pudo mostrar todos Personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }

    }

    public List<Personal> ObtenerPersonalLista() throws SQLException {

        List<Personal> listaPersonal = new ArrayList<>();
        ResultSet resultado;
        Personal personal;
        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_ObtenerPersonalLista_sp()");

            resultado = prcProcedimientoAlmacenado.executeQuery();

            while (resultado.next()) {
                personal = new Personal();
                personal.setCodigo(resultado.getInt("Personal_Id"));
                personal.setNombres(resultado.getString("Personal_Nombres"));
                personal.setApellidos(resultado.getString("Personal_Apellidos"));
                personal.setDni(resultado.getString("Personal_DNI"));
                personal.setFechaNacimiento(resultado.getDate("Personal_Fecha_Nacimiento"));
                personal.setEstadoPersonalNotificacion(resultado.getString("Personalk_EstadoNotificacion"));

                listaPersonal.add(personal);

            }
            resultado.close();
            return listaPersonal;

        } catch (Exception e) {
            throw new SQLException("No se pudo obtener el ultimo codigo.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
        //return null;

    }

    public int modificarPersonalEstadoNotificacion(Personal personal) throws SQLException {

        try {

            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Personal_ModificarEstadoNotificacion_sp(?,?)");
            prcProcedimientoAlmacenado.setInt(1, personal.getCodigo());
            prcProcedimientoAlmacenado.setString(2, personal.getEstadoPersonalNotificacion());

            return prcProcedimientoAlmacenado.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("No se pudo modificar  el estado personal.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
        }
    }
}
