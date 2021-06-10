package SistemaLara.capa1_presentacion.util;

import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//import motorepuestos.capa4_persistencia.Almacen.GestorJDBC;
//import motorepuestos.capa6_excepciones.ExcepcionSQLConsulta;

/**
 *
 * @author FiveCod Software
 */



public final class DatosDeTabla {

    GestorJDBC gestorJDBC;
    ResultSet st;

    public DatosDeTabla(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;

    }

    public static PreparedStatement ps;

    /**
     * Return the columns name for the table
     */
    public static List<String> getTableHeaders() {
        List<String> tableHeader = new ArrayList<String>();
        //TITULOS DE LAS COLUMNAS
        tableHeader.add("Codigo");
        tableHeader.add("Producto");
        tableHeader.add("Categoría");
        tableHeader.add("Modelo");
        tableHeader.add("Proveedor");
        tableHeader.add("Stock mínimo");
        tableHeader.add("Stock actual");
        return tableHeader;
    }

    /**
     * Return values for the table
     *
     * @param numberOfRows Number of rows we want to receive
     *
     * @return Values
     */
    public List<List<String>> getTableContent(int numberOfRows) throws ExcepcionSQLConsulta {
        try {
            if (numberOfRows <= 0) {
                throw new IllegalArgumentException("The number of rows must be a positive integer");
            }

            List<List<String>> tableContent = new ArrayList<List<String>>();

            String SQL = "select p.codigoproducto as codigo, p.nombreproducto as nombre,"
                    + "c.descripcioncategoria as nombrecat,p.modeloproducto as modelorp,"
                    + " pro.empresaproveedor as nombreprove,p.stockminimoproducto as stockmin,"
                    + "p.stockactualproducto as stockac from producto as p inner join categoria as c"
                    + " on c.codigocategoria=p.codigocategoria inner join proveedor as "
                    + "pro on pro.codigoproveedor=p.codigoproveedor"
                    + " where p.estadoproducto='ACTIVO' and p.stockactualproducto<=p.stockminimoproducto";
            
               
            st = gestorJDBC.ejecutarConsulta(SQL);   
        
            int i = 0;
            List<String> row = null;
            while (st.next()) {
                tableContent.add(row = new ArrayList<String>());      
                row.add(st.getString("codigo"));
                row.add(st.getString("nombre"));
                row.add(st.getString("nombrecat"));
                row.add(st.getString("modelorp"));
                row.add(st.getString("nombreprove"));
                row.add(st.getString("stockmin"));
                row.add(st.getString("stockac"));

                i++;
            }
            return tableContent;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

}
