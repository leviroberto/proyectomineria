package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.FormPrincipal;
import SistemaLara.capa1_presentacion.util.Mensaje;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FiveCod Software
 */
public class GestorJDBCMySQL extends GestorJDBC {

    @Override
    public void abrirConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (FormPrincipal.numeroEmpresaSeleccionada == 1) {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdsistemayersica", "levi", "levi");
            } else if (FormPrincipal.numeroEmpresaSeleccionada == 2) {
//               conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.15/bdsistemasumner", "levi", "levi");
                conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.22/bdsistemayersica", "levi", "levi");
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorJDBC(e.getMessage());
        }
    }
}
