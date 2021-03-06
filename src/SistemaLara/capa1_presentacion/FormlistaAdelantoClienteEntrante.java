/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarClienteEntranteServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author XGamer
 */
public class FormlistaAdelantoClienteEntrante extends javax.swing.JDialog {

    private GestionarClienteEntranteServicio gestionarClienteEntranteServicio;
    public static ClienteEntrante clienteEntranteSelecionado;

    public FormlistaAdelantoClienteEntrante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            this.setLocationRelativeTo(null);
            gestionarClienteEntranteServicio = new GestionarClienteEntranteServicio();
            clienteEntranteSelecionado = new ClienteEntrante();
            inicializarTablaColumnas();
            inicializarTabla();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("DNI", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("TELEFONO", "java.lang.String"));
        tabla.agregarColumna(new Columna("SEXO", "java.lang.String"));
        tabla.agregarColumna(new Columna("PERSONAL", "java.lang.String"));
        tabla.agregarColumna(new Columna("TIPO CLIENTE", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableClienteServicio.setModel(modeloTabla);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setMinWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setMaxWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(3)).setWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(3)).setMinWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(3)).setMaxWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(4)).setWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(4)).setMinWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(4)).setMaxWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(5)).setWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(5)).setMinWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(5)).setMaxWidth(0);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableClienteServicio.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarClienteEntranteServicio.mostrarClienteEntrantes(1, tableClienteServicio);
        } catch (Exception ex) {

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

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSButton3 = new rojeru_san.RSButton();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel8 = new javax.swing.JPanel();
        txtBuscar = new org.jdesktop.swingx.JXSearchField();
        jLabel5 = new javax.swing.JLabel();
        btnNuevo = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableClienteServicio = new rojerusan.RSTableMetro();

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
        jLabel3.setText("LISTA DE REGISTROS DE CLIENTES ENTRANTES ");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoClienteEntrante.png"))); // NOI18N

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
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(176, 176, 176)
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

        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.setToolTipText("BUSCAR");
        txtBuscar.setPrompt("BUSCAR POR DNI , APELLIDOS ");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("CLIENTE:");

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rSPanelShadow1.add(jPanel8, java.awt.BorderLayout.LINE_START);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableClienteServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableClienteServicio.setAltoHead(30);
        tableClienteServicio.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableClienteServicio.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableClienteServicio.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableClienteServicio.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableClienteServicio.setGrosorBordeFilas(0);
        tableClienteServicio.setGrosorBordeHead(0);
        tableClienteServicio.setRowHeight(20);
        tableClienteServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteServicioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableClienteServicioMousePressed(evt);
            }
        });
        tableClienteServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableClienteServicioKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableClienteServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton3ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            FormGestionarClienteEntrante formGestionarProveedorServicios = new FormGestionarClienteEntrante(null, true, FormGestionarProveedorServicios.TIPO_ADMINISTRADOR);
            formGestionarProveedorServicios.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void tableClienteServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteServicioMouseClicked


    }//GEN-LAST:event_tableClienteServicioMouseClicked

    private void tableClienteServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteServicioMousePressed
        try {
            fila = tableClienteServicio.getSelectedRow();
            obtenerClienteServicio();
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }

    }//GEN-LAST:event_tableClienteServicioMousePressed

    private void tableClienteServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClienteServicioKeyReleased
        fila = tableClienteServicio.getSelectedRow();
    }//GEN-LAST:event_tableClienteServicioKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            String texto = txtBuscar.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarClienteEntranteServicio.mostrarClienteEntrantes(1, tableClienteServicio, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    int fila;

    private void obtenerClienteServicio() {
        String codigo = tableClienteServicio.getValueAt(fila, 0).toString();
        try {
            clienteEntranteSelecionado = gestionarClienteEntranteServicio.buscarClienteEntrantePorCodigo(Integer.parseInt(codigo));

            FormGestionarAdelantoPorCliente.clienteEntranteSeleccionado = clienteEntranteSelecionado;
            this.dispose();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnNuevo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private rojeru_san.RSButton rSButton3;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojerusan.RSTableMetro tableClienteServicio;
    private org.jdesktop.swingx.JXSearchField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
