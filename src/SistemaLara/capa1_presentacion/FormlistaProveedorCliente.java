/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;


import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoServicio;
import SistemaLara.capa2_aplicacion.GestionarProveedorServicioServicio;
import SistemaLara.capa3_dominio.ProveedorServicio;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author XGamer
 */
public class FormlistaProveedorCliente extends javax.swing.JDialog {

    private GestionarProveedorServicioServicio gestionarProveedorServicioServicio;
    public static ProveedorServicio proveedorServicioSeleccionado;
    private GestionarAdelantoServicio gestionarAdelantoServicio;

    public FormlistaProveedorCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            this.setLocationRelativeTo(null);
            popMenu.add(pnlMenu);
            gestionarAdelantoServicio = new GestionarAdelantoServicio();

//            gestionarProveedorServicioServicio = new GestionarProveedorServicioServicio();
//            proveedorServicioSeleccionado = new ProveedorServicio();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

     public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    public static void inicializarTablaColumnasCliente() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableProveedorServicio.setModel(modeloTabla);
        //  tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setWidth(0);
        //  tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMinWidth(0);
        //  tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMaxWidth(0);
    }

    public static void inicializarTablaColumnasProveedor() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableProveedorServicio.setModel(modeloTabla);
        // tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setWidth(0);
        //  tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMinWidth(0);
        // tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMaxWidth(0);
    }

    public static void inicializarTablaProveedor() {
        try {
            limpiarTabla();
            FormGestionarAdelantosPorveedorCliente.gestionarAdelantoServicio.mostrarAdelantoProveedorPagadoNoPagado(1, FormGestionarAdelantosPorveedorCliente.proveedorServicioSeleccionado, tableProveedorServicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inicializarTablaCliente() {
        try {
            limpiarTabla();
            FormGestionarAdelantosPorveedorCliente.gestionarAdelantoServicio.mostrarAdelantoClientePagadoNoPagado(1, FormGestionarAdelantosPorveedorCliente.clienteEntranteSeleccionado, tableProveedorServicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableProveedorServicio.getModel();
        modelo.eliminarTotalFilas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnPorPagar = new rojeru_san.RSButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSButton3 = new rojeru_san.RSButton();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel8 = new javax.swing.JPanel();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jLabel2 = new javax.swing.JLabel();
        lblClienteProveedor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProveedorServicio = new rojerusan.RSTableMetro();

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        btnPorPagar.setBackground(new java.awt.Color(65, 94, 255));
        btnPorPagar.setBorder(null);
        btnPorPagar.setText("CAMBIAR/POR PAGAR");
        btnPorPagar.setColorHover(new java.awt.Color(255, 82, 54));
        btnPorPagar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPorPagar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPorPagar.setIconTextGap(2);
        btnPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPorPagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 94, 255)));

        jPanel6.setBackground(new java.awt.Color(65, 94, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LISTA DE REGISTRO DE PROVEDOR SERVICIOS ");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoProveedorServicios.png"))); // NOI18N

        rSButton3.setBackground(new java.awt.Color(65, 94, 255));
        rSButton3.setBorder(null);
        rSButton3.setText("X");
        rSButton3.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton3.setFont(new java.awt.Font("Roboto Bold", 0, 20)); // NOI18N
        rSButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(199, 199, 199)
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jXSearchField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        jXSearchField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jXSearchField1.setToolTipText("BUSCAR");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CLIENTE:");

        lblClienteProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClienteProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblClienteProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXSearchField1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
        );

        rSPanelShadow1.add(jPanel8, java.awt.BorderLayout.CENTER);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableProveedorServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProveedorServicio.setAltoHead(30);
        tableProveedorServicio.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableProveedorServicio.setComponentPopupMenu(popMenu);
        tableProveedorServicio.setGrosorBordeFilas(0);
        tableProveedorServicio.setGrosorBordeHead(0);
        tableProveedorServicio.setRowHeight(20);
        tableProveedorServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedorServicioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableProveedorServicioMousePressed(evt);
            }
        });
        tableProveedorServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableProveedorServicioKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableProveedorServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSPanelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton3ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void tableProveedorServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedorServicioMouseClicked

    }//GEN-LAST:event_tableProveedorServicioMouseClicked

    private void tableProveedorServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedorServicioMousePressed

    }//GEN-LAST:event_tableProveedorServicioMousePressed

    private void tableProveedorServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProveedorServicioKeyReleased

    }//GEN-LAST:event_tableProveedorServicioKeyReleased
    int fila;
    private void btnPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorPagarActionPerformed
        int codigos;
        fila = tableProveedorServicio.getSelectedRow();
        if (fila == -1) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        } else {
            int registros_afectados = 0;
            try {
                codigos = (Integer.parseInt(tableProveedorServicio.getValueAt(fila, 0).toString()));
                registros_afectados = gestionarAdelantoServicio.modificarAdelantoNoPagado(codigos);
                if (registros_afectados == 1) {
                    DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de actualizar la  adelanto en el campo Estado", 7);
                    Mensaje.mostrarAfirmacionDeActualizacion(this);
                } else if (registros_afectados == 0) {
                    Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    DesktopNotify.showDesktopMessage("FiveCod Software", "Los datos no fueron actualizados, verifique", 7);
                }
                if (FormGestionarAdelantosPorveedorCliente.rdbProveedor.isSelected()) {
                    inicializarTablaProveedor();
                } else if (FormGestionarAdelantosPorveedorCliente.rdbCliente.isSelected()) {
                    inicializarTablaCliente();
                }
                if (FormGestionarAdelantosPorveedorCliente.rdbProveedor.isSelected()) {
                    FormGestionarAdelantosPorveedorCliente.inicializarTablaProveedor();
                } else if (FormGestionarAdelantosPorveedorCliente.rdbCliente.isSelected()) {
                    FormGestionarAdelantosPorveedorCliente.inicializarTablaCliente();
                }

            } catch (Exception ex) {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        }

    }//GEN-LAST:event_btnPorPagarActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnPorPagar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    public static javax.swing.JLabel lblClienteProveedor;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton3;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    public static rojerusan.RSTableMetro tableProveedorServicio;
    // End of variables declaration//GEN-END:variables
}
