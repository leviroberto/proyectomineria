/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mineria
 */
public class TableCellRender_ControlDocumentos extends DefaultTableCellRenderer {

    private int columna;

    public TableCellRender_ControlDocumentos(int Colpatron) {
        this.columna = Colpatron;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        JLabel etiqueta = new JLabel();

        if (columna == 1) {
            if (selected) {
                etiqueta.setForeground(Color.CYAN);
            } else {
                etiqueta.setBackground(Color.YELLOW);
            }
        } else {
            etiqueta.setBackground(table.getBackground());
        }

        if (value instanceof String) {
            etiqueta.setOpaque(true);
            etiqueta.setText((String) value);
        }

        return etiqueta;
    }

}











