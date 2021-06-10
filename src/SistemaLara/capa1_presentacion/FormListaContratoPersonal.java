/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa3_dominio.Contrato;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

import rojerusan.RSAnimation;

/**
 * @author FiveCod Software
 */
public class FormListaContratoPersonal extends javax.swing.JDialog {

    private GestionarContratoServicio gestionarContratoServicio;
    public static Contrato contratoSeleccionado;

    public FormListaContratoPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        BarraDesplzamiento() ;
        this.setLocationRelativeTo(null);
        contratoSeleccionado = new Contrato();
        gestionarContratoServicio = new GestionarContratoServicio();
        inicializarTabla();

        obtenerDatosTable();

    }
 public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    private void inicializarTabla() {

        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ingreso", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha salida ", "java.lang.String"));

        tabla.agregarColumna(new Columna("Total Dias Pagar", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("sueldo", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaContratos.setModel(modeloTabla);
//        tablaContratos.getColumn(tablaContratos.getColumnName(0)).setWidth(0);
//        tablaContratos.getColumn(tablaContratos.getColumnName(0)).setMinWidth(0);
//        tablaContratos.getColumn(tablaContratos.getColumnName(0)).setMaxWidth(0);

    }

    private void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tablaContratos.getModel();
        modelo.eliminarTotalFilas();
        tablaContratos.setModel(modelo);
    }

    private void obtenerDatosTable() {

        try {
            limpiarTabla();
            gestionarContratoServicio.mostrarContrato(tablaContratos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaContratos = new rojerusan.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255), 2));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaContratos.setAlignmentX(0.0F);
        tablaContratos.setAlignmentY(0.0F);
        tablaContratos.setAltoHead(40);
        tablaContratos.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaContratos.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaContratos.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaContratos.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaContratos.setFuenteFilas(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tablaContratos.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaContratos.setGrosorBordeFilas(0);
        tablaContratos.setGrosorBordeHead(0);
        tablaContratos.setRowHeight(60);
        tablaContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaContratosMousePressed(evt);
            }
        });
        tablaContratos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaContratosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaContratosKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaContratos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPago.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTIFICACIONES PAGOS");

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton2.setFont(new java.awt.Font("Roboto Bold", 0, 20)); // NOI18N
        rSButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int fila;
    private void tablaContratosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaContratosMousePressed
        fila = tablaContratos.getSelectedRow();
        obtenerContratoSeleccionado();
    }//GEN-LAST:event_tablaContratosMousePressed

    private void tablaContratosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratosKeyReleased
        fila = tablaContratos.getSelectedRow();

    }//GEN-LAST:event_tablaContratosKeyReleased

    private void tablaContratosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratosKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                obtenerContratoSeleccionado();
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }


    }//GEN-LAST:event_tablaContratosKeyTyped

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        contratoSeleccionado = null;
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void obtenerContratoSeleccionado() {
        try {
            String codigo = tablaContratos.getValueAt(fila, 0).toString();
            contratoSeleccionado = gestionarContratoServicio.buscarContratoPorCodigo(Integer.parseInt(codigo));
            FormDatosPagoPersonal.contratoSeleccionado = contratoSeleccionado;
            this.dispose();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaContratos;
    // End of variables declaration//GEN-END:variables
}
