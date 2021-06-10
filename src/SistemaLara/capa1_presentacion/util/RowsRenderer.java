/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author FiveCod Software
 */
public class RowsRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        Double columna2 = Double.parseDouble(table.getValueAt(row, 5).toString());
        Double columna3 = Double.parseDouble(table.getValueAt(row, 6).toString());
        if (columna3 <= columna2) {
            table.setSelectionBackground(new Color(253, 173, 1));
            table.setSelectionForeground(new Color(255, 255, 255));
         
            //ROJo productos stock minimo
            //negro productos sotck normal 
            this.setForeground(Color.RED);
        } else {
            this.setForeground(Color.BLACK);
            table.setSelectionBackground(new Color(253, 173, 1));
            table.setSelectionForeground(new Color(255, 255, 255));
        }
        return this;
    }

}
