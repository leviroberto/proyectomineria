/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa1_presentacion.util.RedondearDecimales;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa6_exepciones.ExcepcionSQLConsulta;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Fila;
import rojerusan.RSTableMetro;

/**
 *
 * @author Jenss
 */
public class AdelantoClienteReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public AdelantoClienteReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }

    public void mostrarAdelantoClientePorEspecifico(Date dateDe, Date dateHasta, RSTableMetro table, int codigo, JLabel lbl_total_dolar, JLabel lbl_total_no_pagado_dolar, JLabel lbl_total_no_pagado_soles, JLabel lbl_total_pagado_dolar, JLabel lbl_total_pagado_soles, JLabel lbl_total_soles) throws ExcepcionSQLConsulta {
        ModeloTabla modeloTabla = (ModeloTabla) table.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        Fila fila;
        double total_no_pagado_soles = 0, total_no_pagado_dolar = 0, total_pagado_soles = 0, total_pagado_dolar = 0, total_soles = 0, total_dolar = 0;
        int total_item = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarEspesificoReporte_sp(?,?,?)");
            prcProcedimientoAlmacenado.setDate("dateDe", dateDe);
            prcProcedimientoAlmacenado.setDate("dateHasta", dateHasta);
            prcProcedimientoAlmacenado.setInt("codigo", codigo);

            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Adelanto_Fecha"));
                fila.agregarValorCelda(resultado.getString("cliente"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Cantidad"));
                fila.agregarValorCelda(resultado.getString("Adeelanto_Estado"));
                if (resultado.getString("Adelanto_Moneda").equals("S/")) {
                    if (resultado.getString("Adeelanto_Estado").equals("PAGADO")) {
                        total_pagado_soles += resultado.getDouble("Adelanto_Cantidad");
                    } else {
                        total_no_pagado_soles += resultado.getDouble("Adelanto_Cantidad");

                    }
                    total_soles += resultado.getDouble("Adelanto_Cantidad");
                } else {
                    if (resultado.getString("Adeelanto_Estado").equals("NO PAGADO")) {
                        total_no_pagado_dolar += resultado.getDouble("Adelanto_Cantidad");
                    } else {
                        total_pagado_dolar += resultado.getDouble("Adelanto_Cantidad");

                    }
                    total_dolar += resultado.getDouble("Adelanto_Cantidad");
                }

                modeloTabla.agregarFila(fila);
            }
            resultado.close();
            table.setModel(modeloTabla);

            lbl_total_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_dolar, 2) + "")));
            lbl_total_no_pagado_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_no_pagado_dolar, 2) + "")));
            lbl_total_no_pagado_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_no_pagado_soles, 2) + "")));
            lbl_total_pagado_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_pagado_dolar, 2) + "")));
            lbl_total_pagado_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_pagado_soles, 2) + "")));
            lbl_total_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_soles, 2) + "")));

        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }

    public void mostrarAdelantoClienteTodos(Date dateDe, Date dateHasta, RSTableMetro tableFactura, JLabel lbl_total_dolar, JLabel lbl_total_no_pagado_dolar, JLabel lbl_total_no_pagado_soles, JLabel lbl_total_pagado_dolar, JLabel lbl_total_pagado_soles, JLabel lbl_total_soles) throws ExcepcionSQLConsulta {

        ModeloTabla modeloTabla = (ModeloTabla) tableFactura.getModel();
        ResultSet resultado;
        String sentenciaSQL;
        Contrato contrato;
        Fila fila;
        double total_no_pagado_soles = 0, total_no_pagado_dolar = 0, total_pagado_soles = 0, total_pagado_dolar = 0, total_soles = 0, total_dolar = 0;

        try {
            CallableStatement prcProcedimientoAlmacenado = gestorJDBC.prepareCall("Adelanto_MostrarTodosReporte_sp(?,?)");
            prcProcedimientoAlmacenado.setDate("dateDe", dateDe);
            prcProcedimientoAlmacenado.setDate("dateHasta", dateHasta);
            resultado = prcProcedimientoAlmacenado.executeQuery();
            Object[] registros = new Object[20];
            while (resultado.next()) {
                fila = new Fila();
                fila.agregarValorCelda(resultado.getString("Adelanto_Fecha"));
                fila.agregarValorCelda(resultado.getString("cliente"));
                fila.agregarValorCelda(resultado.getString("personal"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Descripcion"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Moneda"));
                fila.agregarValorCelda(resultado.getString("Adelanto_Cantidad"));
                fila.agregarValorCelda(resultado.getString("Adeelanto_Estado"));

                if (resultado.getString("Adelanto_Moneda").equals("S/")) {
                    if (resultado.getString("Adeelanto_Estado").equals("PAGADO")) {
                        total_pagado_soles += resultado.getDouble("Adelanto_Cantidad");
                    } else {
                        total_no_pagado_soles += resultado.getDouble("Adelanto_Cantidad");

                    }
                    total_soles += resultado.getDouble("Adelanto_Cantidad");
                } else {
                    if (resultado.getString("Adeelanto_Estado").equals("NO PAGADO")) {

                        total_no_pagado_dolar += resultado.getDouble("Adelanto_Cantidad");
                    } else {
                        total_pagado_dolar += resultado.getDouble("Adelanto_Cantidad");

                    }
                    total_dolar += resultado.getDouble("Adelanto_Cantidad");
                }

                modeloTabla.agregarFila(fila);
            }
            resultado.close();
            tableFactura.setModel(modeloTabla);

            lbl_total_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_dolar, 2) + "")));
            lbl_total_no_pagado_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_no_pagado_dolar, 2) + "")));
            lbl_total_no_pagado_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_no_pagado_soles, 2) + "")));
            lbl_total_pagado_dolar.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_pagado_dolar, 2) + "")));
            lbl_total_pagado_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_pagado_soles, 2) + "")));
            lbl_total_soles.setText(String.valueOf(String.valueOf(RedondearDecimales.redondearDecimales(total_soles, 2) + "")));
        } catch (SQLException e) {
            throw new ExcepcionSQLConsulta(e);
        }

    }
}
