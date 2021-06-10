package SistemaLara.capa4_persistencia;

import SistemaLara.capa6_exepciones.ExcepcionJDBC;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GestorJDBC {

    protected Connection conexion;

    public abstract void abrirConexion() throws Exception;


    public void cerrarConexion() throws ExcepcionJDBC {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new ExcepcionJDBC(e);
        }
    }

    public void iniciarTransaccion() throws ExcepcionJDBC {
        try {
            conexion.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ExcepcionJDBC(e);
        }
    }

    public void terminarTransaccion() throws ExcepcionJDBC {
        try {
            conexion.commit();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (SQLException e) {
            throw new ExcepcionJDBC(e);
        }
    }

    public void cancelarTransaccion() throws ExcepcionJDBC {
        try {
            conexion.rollback();
            conexion.setAutoCommit(true);
            conexion.close();
        } catch (SQLException e) {
            throw new ExcepcionJDBC(e);
        }
    }

    public Connection getConnection() {
        return this.conexion;
    }

    public PreparedStatement prepararSentencia(String sql) throws ExcepcionJDBC {
        try {
            return conexion.prepareStatement(sql);
        } catch (SQLException e) {
            throw new ExcepcionJDBC(e);
        }
    }

    public ResultSet ejecutarConsulta(String sql) throws ExcepcionSQLConsulta {
        try {
            Statement sentencia;
            ResultSet resultado;
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        String ruta = "{call " + sql + "}";
        CallableStatement prcProcedimientoAlmacenado = conexion.prepareCall(ruta);
        return prcProcedimientoAlmacenado;
    }
}
