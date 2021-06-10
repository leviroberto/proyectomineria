/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa7_reportes;

import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author XGamer
 */
public class FormReporteLiquidacionGrafico extends javax.swing.JDialog {

    private final GestionarLiquidacionServicio gestionarLiquidacionServicio;

    private BufferedImage _image;//para la imagen en memoria
    private JFreeChart grafico;// el grafico

    public FormReporteLiquidacionGrafico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
        inicializarTablaColumnas();
        try {
            inicializarTabla();
            graficar();
        } catch (Exception ex) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("MAQUILLA", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTALUS", "java.lang.String"));
        tabla.agregarColumna(new Columna("Tipo", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableLiquidacion.setModel(modeloTabla);

    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableLiquidacion.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {

            limpiarTabla();
            gestionarLiquidacionServicio.mostrarLiquidacionsGrafico(1, tableLiquidacion);
        } catch (Exception e) {
        }

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        pnlVisualizar = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLiquidacion = new rojerusan.RSTableMetro();

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

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255), 2));

        jPanel2.setBackground(new java.awt.Color(255, 68, 68));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REPORTE POR LIQUIDACIONES GRANDES");

        rSButton2.setBackground(new java.awt.Color(255, 68, 68));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(65, 94, 255));
        rSButton2.setFont(new java.awt.Font("Roboto Bold", 1, 20)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 543, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlVisualizar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlVisualizarLayout = new javax.swing.GroupLayout(pnlVisualizar);
        pnlVisualizar.setLayout(pnlVisualizarLayout);
        pnlVisualizarLayout.setHorizontalGroup(
            pnlVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlVisualizarLayout.setVerticalGroup(
            pnlVisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableLiquidacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableLiquidacion.setAltoHead(30);
        tableLiquidacion.setColorBackgoundHead(new java.awt.Color(255, 218, 31));
        tableLiquidacion.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableLiquidacion.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableLiquidacion.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableLiquidacion.setFuenteFilas(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tableLiquidacion.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tableLiquidacion.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableLiquidacion.setGrosorBordeFilas(0);
        tableLiquidacion.setGrosorBordeHead(0);
        tableLiquidacion.setRowHeight(25);
        tableLiquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLiquidacionMousePressed(evt);
            }
        });
        tableLiquidacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableLiquidacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addComponent(pnlVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        rSPanelsSlider1.add(jPanel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void tableLiquidacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLiquidacionMousePressed

    }//GEN-LAST:event_tableLiquidacionMousePressed

    private void tableLiquidacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyReleased

    }//GEN-LAST:event_tableLiquidacionKeyReleased

    private void tableLiquidacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyTyped

    }//GEN-LAST:event_tableLiquidacionKeyTyped

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlVisualizar;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSTableMetro tableLiquidacion;
    // End of variables declaration//GEN-END:variables

    public void graficar() {

        Dimension d = pnlVisualizar.getSize();
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < tableLiquidacion.getRowCount(); i++) {
            dataset.setValue("TOTALUS: " + tableLiquidacion.getValueAt(i, 3).toString(), Float.parseFloat(tableLiquidacion.getValueAt(i, 3).toString()));
              }
        // Creamos la tabla
        grafico = ChartFactory.createPieChart("TOTAL US POR CLIENTE", dataset, true, true, false);
        JLabel j = new JLabel();
        j.setBounds(0, 0, d.width, d.height);

        _image = grafico.createBufferedImage(d.width, d.height);
        ImageIcon imagenFondo = new ImageIcon(_image);
        j.setIcon(imagenFondo);
        j.repaint();

        pnlVisualizar.add(j);
        this.repaint();

    }

}
