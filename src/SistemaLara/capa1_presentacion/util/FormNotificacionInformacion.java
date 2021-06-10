/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class FormNotificacionInformacion extends javax.swing.JDialog {

    private int anchoPantalla;
    private int alturaPantalla;

    public FormNotificacionInformacion(java.awt.Frame parent, boolean modal, String texto) {
        super(parent, modal);
        initComponents();
        lblTitulo.setText("<html> " + texto + "<html>");
        sacarDimensionesPantalla();
        AWTUtilities.setOpaque(this, false);
        Ubicar(0);
    }

    void sacarDimensionesPantalla() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension miPantalla = tk.getScreenSize();
        anchoPantalla = miPantalla.width;
        alturaPantalla = miPantalla.height;
    }
    Timer timer = null;
    TimerTask task;
    int i = 32;

    private void Cerrar() {
        this.dispose();
        timer = null;
        task = null;
    }

    private void Trasparencia(float trasp) {
        AWTUtilities.setOpacity(this, trasp);
    }

    private void Ubicar(int y) {
        this.setLocation((anchoPantalla - 456) / 2, y - 120);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        rSPanelsSlider3 = new rojerusan.RSPanelsSlider();
        rSPanelsSlider4 = new rojerusan.RSPanelsSlider();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        lblTitulo = new javax.swing.JLabel();
        rSButtonHover2 = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(246, 134));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowSize(2);
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        rSPanelsSlider1.setBorder(dropShadowBorder1);

        rSPanelsSlider2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelsSlider2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(68, 138, 255)));

        rSPanelsSlider3.setBackground(new java.awt.Color(68, 138, 255));

        javax.swing.GroupLayout rSPanelsSlider3Layout = new javax.swing.GroupLayout(rSPanelsSlider3);
        rSPanelsSlider3.setLayout(rSPanelsSlider3Layout);
        rSPanelsSlider3Layout.setHorizontalGroup(
            rSPanelsSlider3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        rSPanelsSlider3Layout.setVerticalGroup(
            rSPanelsSlider3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        rSPanelsSlider4.setBackground(new java.awt.Color(255, 68, 68));

        javax.swing.GroupLayout rSPanelsSlider4Layout = new javax.swing.GroupLayout(rSPanelsSlider4);
        rSPanelsSlider4.setLayout(rSPanelsSlider4Layout);
        rSPanelsSlider4Layout.setHorizontalGroup(
            rSPanelsSlider4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rSPanelsSlider4Layout.setVerticalGroup(
            rSPanelsSlider4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/InformeNotificacion.png"))); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("ALERTA INFORMACIÓN");

        rSButtonHover2.setBackground(new java.awt.Color(253, 173, 1));
        rSButtonHover2.setText("ACEPTAR");
        rSButtonHover2.setColorHover(new java.awt.Color(253, 173, 1));
        rSButtonHover2.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        rSButtonHover2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rSButtonHover2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelsSlider2Layout = new javax.swing.GroupLayout(rSPanelsSlider2);
        rSPanelsSlider2.setLayout(rSPanelsSlider2Layout);
        rSPanelsSlider2Layout.setHorizontalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                        .addComponent(rSPanelsSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(rSButtonHover2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSPanelsSlider4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        rSPanelsSlider2Layout.setVerticalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(rSPanelsSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(rSButtonHover2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelsSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rSPanelShadow1.add(rSPanelsSlider2, java.awt.BorderLayout.CENTER);

        rSPanelsSlider1.add(rSPanelShadow1, "card2");

        getContentPane().add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 169));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        task = new TimerTask() {
            @Override
            public void run() {
                if (i == 352) {
                    timer.cancel();
                } else {
                    Ubicar(i);
                    i += 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new java.util.Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_formWindowOpened

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        task = new TimerTask() {
            @Override
            public void run() {
                if (i == 0) {
                    Cerrar();
                } else {
                    Ubicar(i);
                    i -= 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void rSButtonHover2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rSButtonHover2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            task = new TimerTask() {
                @Override
                public void run() {
                    if (i == 0) {
                        Cerrar();
                    } else {
                        Ubicar(i);
                        i -= 32;
                        Trasparencia((float) i / 352);
                    }
                }
            };
            timer = new Timer();
            timer.schedule(task, 0, 2);
        }
    }//GEN-LAST:event_rSButtonHover2KeyPressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FiveCodNotificacionInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FiveCodNotificacionInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FiveCodNotificacionInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FiveCodNotificacionInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FiveCodNotificacionInformacion dialog = new FiveCodNotificacionInformacion(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private rojerusan.RSButtonHover rSButtonHover2;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private rojerusan.RSPanelsSlider rSPanelsSlider3;
    private rojerusan.RSPanelsSlider rSPanelsSlider4;
    // End of variables declaration//GEN-END:variables
}
