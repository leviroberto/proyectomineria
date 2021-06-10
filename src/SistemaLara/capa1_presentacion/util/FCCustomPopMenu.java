/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

/**
 *
 * @author XGamer
 */
public class FCCustomPopMenu {

    public FCCustomPopMenu() {
    }
    
    public static void setCustomize(Color Background, Color Foreground, Color SelectionBackground, Color SelectionForeground)
  {
    UIManager.put("MenuItem.background", Background);
    UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder(10, 10, 10, 10));
    UIManager.put("MenuItem.disabledForeground", new Color(0, 0, 0, 100));
    UIManager.put("MenuItem.selectionBackground", SelectionBackground);
    UIManager.put("MenuItem.selectionForeground", SelectionForeground);
    UIManager.put("MenuItem.foreground", Foreground);

    UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    UIManager.put("PopupMenu.background", Background);
  }
    
    
}
