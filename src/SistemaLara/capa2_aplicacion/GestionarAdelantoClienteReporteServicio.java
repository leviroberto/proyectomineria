/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa2_aplicacion;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa4_persistencia.AdelantoClienteReporteDAOMySQL;
import SistemaLara.capa4_persistencia.GestorJDBC;
import SistemaLara.capa4_persistencia.GestorJDBCMySQL;
import java.sql.Date;
import javax.swing.JLabel;
import rojerusan.RSTableMetro;

/**
 *
 * @author Jenss
 */
public class GestionarAdelantoClienteReporteServicio {

    private final GestorJDBC gestorJDBC;
    private AdelantoClienteReporteDAOMySQL adelantoClienteReporteDAOMySQL;

    public GestionarAdelantoClienteReporteServicio() {
        gestorJDBC = new GestorJDBCMySQL();
        adelantoClienteReporteDAOMySQL = new AdelantoClienteReporteDAOMySQL(gestorJDBC);
    }

    public void mostrarAdelantoClientePorEspecifico(Date dateDe, Date dateHasta, RSTableMetro tableFactura, int codigo, JLabel lbl_total_dolar, JLabel lbl_total_no_pagado_dolar, JLabel lbl_total_no_pagado_soles, JLabel lbl_total_pagado_dolar, JLabel lbl_total_pagado_soles, JLabel lbl_total_soles) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoClienteReporteDAOMySQL.mostrarAdelantoClientePorEspecifico(dateDe, dateHasta, tableFactura, codigo, lbl_total_dolar, lbl_total_no_pagado_dolar, lbl_total_no_pagado_soles, lbl_total_pagado_dolar, lbl_total_pagado_soles, lbl_total_soles);
        gestorJDBC.cerrarConexion();
    }

    public void mostrarAdelantoClienteTodos(Date dateDe, Date dateHasta, RSTableMetro tableFactura, JLabel lbl_total_dolar, JLabel lbl_total_no_pagado_dolar, JLabel lbl_total_no_pagado_soles, JLabel lbl_total_pagado_dolar, JLabel lbl_total_pagado_soles, JLabel lbl_total_soles) throws Exception {
        gestorJDBC.abrirConexion();
        adelantoClienteReporteDAOMySQL.mostrarAdelantoClienteTodos(dateDe, dateHasta, tableFactura, lbl_total_dolar, lbl_total_no_pagado_dolar, lbl_total_no_pagado_soles, lbl_total_pagado_dolar, lbl_total_pagado_soles, lbl_total_soles);
        gestorJDBC.cerrarConexion();

    }

}
