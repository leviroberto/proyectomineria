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
 * @author mineria
 */
public class RenderFecha extends DefaultTableCellRenderer {

    private int columna_patron;
    private String fechaHoy = "";

    public RenderFecha(int Colpatron, String fechaHoy) {
        this.columna_patron = Colpatron;
        this.fechaHoy = fechaHoy;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setBackground(new Color(255,255,255));//color de fondo
        table.setForeground(new Color(65,94,255));//color de texto

//        setBackground((row % 2 == 0) ? Color.white : (new Color(178, 243, 221)));

        // Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if (table.getValueAt(row, columna_patron).equals(this.fechaHoy)) {
            setBackground(new Color(253,173,1));
            table.setForeground(Color.WHITE);
            
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
