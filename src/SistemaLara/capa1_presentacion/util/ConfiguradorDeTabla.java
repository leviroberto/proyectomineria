/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SistemaLara.capa1_presentacion.util;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author FiveCod Software
 */


public class ConfiguradorDeTabla {
         
    /**
     * Método que selecciona una fila en una tabla.
     * @param tabla
     * @param fila 
     */    
    public static void seleccionarFilaTabla(JTable tabla, int fila){
        if(tabla == null)
            return;        

        if(tabla.getRowCount() > 0){
            if(fila < 0)
                fila = 0;
            tabla.requestFocusInWindow();
            tabla.setRowSelectionInterval(fila,fila);
        }                                
    }           
 
    /**
     * Método que configura el ancho de las columnas de una tabla   
     * @param tabla
     * @param num_columna
     * @param ancho
     * @param maximo
     * @param mininmo 
     */    
    public static void configurarAnchoColumna(JTable tabla, int num_columna, int ancho, int maximo, int mininmo){
        if(tabla == null)
            return;        

        if(num_columna >= 0 && ancho > 0 && maximo > 0 && mininmo > 0){
            TableColumn columna = tabla.getColumnModel().getColumn(num_columna);
            columna.setPreferredWidth(ancho);
            columna.setMaxWidth(maximo);
            columna.setMinWidth(mininmo);   
        }
    }
    
    /**
     * permite ocultar una columna de la tabla
     * @param tabla
     * @param columna 
     */
    public static void ocultarColumna(JTable tabla, int columna){
        TableColumn columnaTabla;

        if(tabla == null)
            return;        
        
        if(columna >= 0 && columna < tabla.getColumnCount()){
            columnaTabla = tabla.getColumnModel().getColumn(columna);
            tabla.removeColumn(columnaTabla);
        }
    }
    
    /**
     * Método que oculta o quita columnas de una tabla   
     * @param tabla
     * @param columnas 
     */    
    public static void ocultarColumnas(JTable tabla, int columnas[]){
        TableColumn columna;

        if(tabla == null)
            return;        
        
        if(columnas != null && columnas.length > 0){
            for(int i = columnas.length-1; i >= 0; i--){
                columna = tabla.getColumnModel().getColumn(columnas[i]);
                tabla.removeColumn(columna);
            }
        }
    }
                
}
