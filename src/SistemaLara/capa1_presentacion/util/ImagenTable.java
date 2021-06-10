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
 * @author FiveCod Software
 */
public class ImagenTable extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JLabel) {
            JLabel lbl = (JLabel) value;
            table.setSelectionForeground(new Color(255, 255, 255));
            table.setSelectionBackground(new Color(253, 173, 1));
            return lbl;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        }
    }

}
