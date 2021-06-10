/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa3_dominio.Contrato;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
public class FormListaPagosPorPersonal extends javax.swing.JDialog {

    private GestionarContratoServicio gestionarContratoServicio;
    public static Contrato contratoSeleccionado;
    private SimpleDateFormat formato;

    public FormListaPagosPorPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        formato = new SimpleDateFormat("yyyy-MM-dd");
        this.setLocationRelativeTo(null);
        contratoSeleccionado = new Contrato();
        gestionarContratoServicio = new GestionarContratoServicio();
        inicializarTablaContrato();
        inicializarTablaFechas();
        consultarBaseDatos();

    }

    private void inicializarTablaContrato() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ingreso", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha salida ", "java.lang.String"));

        tabla.agregarColumna(new Columna("Total Dias Pagar", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("sueldo", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaContrato.setModel(modeloTabla);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setWidth(0);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMinWidth(0);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMaxWidth(0);
//  
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
        jScrollPane5.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane5.getHorizontalScrollBar().setUI(new RSScrollBar());

    }

    private void inicializarTablaFechas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Fechas de pago", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaFechas.setModel(modeloTabla);
//        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setWidth(0);
//        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMinWidth(0);
//        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMaxWidth(0);
//  
    }

    private void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tablaContrato.getModel();
        modelo.eliminarTotalFilas();
        tablaContrato.setModel(modelo);
    }

    private void limpiarTablaFechas() {
        ModeloTabla modelo = (ModeloTabla) tablaFechas.getModel();
        modelo.eliminarTotalFilas();
        tablaFechas.setModel(modelo);
    }

    private void consultarBaseDatos() {

        try {
            limpiarTabla();
            gestionarContratoServicio.mostrarContrato(tablaContrato);
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
        tablaContrato = new rojerusan.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaFechas = new rojerusan.RSTableMetro();

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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaContrato.setAltoHead(30);
        tablaContrato.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaContrato.setGrosorBordeFilas(0);
        tablaContrato.setGrosorBordeHead(0);
        tablaContrato.setRowHeight(20);
        tablaContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaContratoMousePressed(evt);
            }
        });
        tablaContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaContratoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaContratoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaContrato);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 34, 1040, 240));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPago.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 1040, -1));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        tablaFechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaFechas.setAltoHead(30);
        tablaFechas.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaFechas.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaFechas.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaFechas.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaFechas.setEnabled(false);
        tablaFechas.setGrosorBordeFilas(0);
        tablaFechas.setGrosorBordeHead(0);
        tablaFechas.setRowHeight(20);
        tablaFechas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaFechasMousePressed(evt);
            }
        });
        tablaFechas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaFechasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaFechasKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(tablaFechas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 720, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int fila;
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

    private void tablaContratoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaContratoMousePressed
        fila = tablaContrato.getSelectedRow();
        obtenerContratoSeleccionado();
        if (contratoSeleccionado != null) {
            llenarFechasPagoTable();
        }
    }//GEN-LAST:event_tablaContratoMousePressed

    private void tablaContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratoKeyReleased
        fila = tablaContrato.getSelectedRow();
//        inabilitarBotones(false);
    }//GEN-LAST:event_tablaContratoKeyReleased

    private void tablaContratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratoKeyTyped
        fila = tablaContrato.getSelectedRow();
//        inabilitarBotones(false);
    }//GEN-LAST:event_tablaContratoKeyTyped

    private void tablaFechasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFechasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaFechasMousePressed

    private void tablaFechasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFechasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaFechasKeyReleased

    private void tablaFechasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFechasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaFechasKeyTyped

    private void obtenerContratoSeleccionado() {
        try {
            String codigo = tablaContrato.getValueAt(fila, 0).toString();
            contratoSeleccionado = gestionarContratoServicio.buscarContratoPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    void llenarFechasPagoTable() {
        contratoSeleccionado.obtenerFechasPagos();
        limpiarTablaFechas();
        ModeloTabla modelo = (ModeloTabla) tablaFechas.getModel();
        int numeroSeleccionado = contratoSeleccionado.obtenerFechaPagoProximoIndice();
        Fila fila;
        for (Date fecha : contratoSeleccionado.getListaFechasPagos()) {
            fila = new Fila();
            String fechasFormateada = formato.format(fecha);
            fila.agregarValorCelda(fechasFormateada);
            modelo.agregarFila(fila);
        }
        tablaFechas.setModel(modelo);
        tablaFechas.changeSelection((numeroSeleccionado + 1), 1, false, false);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaContrato;
    private rojerusan.RSTableMetro tablaFechas;
    // End of variables declaration//GEN-END:variables
}
