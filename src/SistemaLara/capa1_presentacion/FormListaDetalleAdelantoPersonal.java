/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoPersonalServicio;
import SistemaLara.capa3_dominio.AdelantoPersonal;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.PagoPersonal;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormListaDetalleAdelantoPersonal extends javax.swing.JDialog {

    private GestionarAdelantoPersonalServicio gestionarAdelantoPersonalServicio;
    public AdelantoPersonal adelantosSeleccionado;
    private Contrato contratoSeleccionado;
    public static int mes, anio;

    public FormListaDetalleAdelantoPersonal(java.awt.Frame parent, boolean modal, Contrato contrato) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
         BarraDesplzamiento();
        this.setLocationRelativeTo(null);
        inicializarTabla();
        this.mes = mes;
        this.anio = anio;
        contratoSeleccionado = contrato;
        lblNombreTrabajador.setText(contrato.getPersonal().getGenerarNombre());
        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        obtenerDatosBaseDatos();
        popMenu.add(pnlMenu);

    }
 public final void BarraDesplzamiento() {
        jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    public FormListaDetalleAdelantoPersonal(java.awt.Frame parent, boolean modal, PagoPersonal pago) {
        super(parent, modal);
        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        inicializarTabla();
        this.mes = mes;
        this.anio = anio;
        contratoSeleccionado = pago.getContrato();
        lblNombreTrabajador.setText(pago.getContrato().getPersonal().getGenerarNombre());
        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        cboEstado.setVisible(false);
        lblEstado.setVisible(false);
        obtenerDatosBaseDatosPorPago(pago);
        popMenu.add(pnlMenu);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificarAPagado.setEnabled(false);

    }

    private void limpiarTable() {
        ModeloTabla modelo = (ModeloTabla) tablaDetalleAdelantoPersonals.getModel();
        modelo.eliminarTotalFilas();
        tablaDetalleAdelantoPersonals.setModel(modelo);
    }

    private void obtenerDatosBaseDatosPorPago(PagoPersonal pago) {
        try {
            limpiarTable();
            gestionarAdelantoPersonalServicio.mostrarAdelantoPersonalPorPago(pago.getCodigo(), tablaDetalleAdelantoPersonals);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void obtenerDatosBaseDatos() {
        try {
            limpiarTable();
            String textocbo = cboEstado.getSelectedItem().toString();
            int numero = 8;
            if (textocbo.equals("ADELANTO POR PAGAR")) {
                numero = 8;
            } else {
                numero = 7;
            }
            gestionarAdelantoPersonalServicio.mostrarAdelantoPersonalPorContrato(contratoSeleccionado.getCodigo(), tablaDetalleAdelantoPersonals, numero);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void inicializarTabla() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ", "java.lang.String"));
        tabla.agregarColumna(new Columna("Motivo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Monto adelantado", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaDetalleAdelantoPersonals.setModel(modeloTabla);
        //        PARA OCULATR LA COLUMNA CODIGO
        tablaDetalleAdelantoPersonals.getColumn(tablaDetalleAdelantoPersonals.getColumnName(0)).setWidth(0);
        tablaDetalleAdelantoPersonals.getColumn(tablaDetalleAdelantoPersonals.getColumnName(0)).setMinWidth(0);
        tablaDetalleAdelantoPersonals.getColumn(tablaDetalleAdelantoPersonals.getColumnName(0)).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        btnModificar = new rojeru_san.RSButton();
        btnModificarAPagado = new rojeru_san.RSButton();
        btnModificarPorPagar = new rojeru_san.RSButton();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new rojeru_san.RSButton();
        lblNombreTrabajador = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaDetalleAdelantoPersonals = new rojerusan.RSTableMetro();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new FiveCodMaterilalDesignComboBox.MaterialComboBox();

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        pnlMenu.setBackground(new java.awt.Color(255, 0, 0));
        pnlMenu.setForeground(new java.awt.Color(65, 94, 255));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR REGISTRO");
        btnEliminar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setIconTextGap(0);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlMenu.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 194, 33));

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR REGISTRO");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setIconTextGap(0);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlMenu.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 194, 40));

        btnModificarAPagado.setBackground(new java.awt.Color(65, 94, 255));
        btnModificarAPagado.setBorder(null);
        btnModificarAPagado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/EstadoPorPagar.png"))); // NOI18N
        btnModificarAPagado.setText("CAMBIAR /A PAGADO");
        btnModificarAPagado.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificarAPagado.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificarAPagado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarAPagado.setIconTextGap(0);
        btnModificarAPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAPagadoActionPerformed(evt);
            }
        });
        pnlMenu.add(btnModificarAPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 194, 40));

        btnModificarPorPagar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificarPorPagar.setBorder(null);
        btnModificarPorPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/EstadoPorPagar.png"))); // NOI18N
        btnModificarPorPagar.setText("CAMBIAR /POR PAGAR");
        btnModificarPorPagar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificarPorPagar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificarPorPagar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarPorPagar.setIconTextGap(0);
        btnModificarPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPorPagarActionPerformed(evt);
            }
        });
        pnlMenu.add(btnModificarPorPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 194, 40));

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

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        btnCerrar.setBackground(new java.awt.Color(65, 94, 255));
        btnCerrar.setBorder(null);
        btnCerrar.setText("X");
        btnCerrar.setColorHover(new java.awt.Color(255, 68, 68));
        btnCerrar.setFont(new java.awt.Font("Roboto Bold", 1, 20)); // NOI18N
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblNombreTrabajador.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblNombreTrabajador.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoAdelanto.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" ADELANTOS DE: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(lblNombreTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255)));

        tablaDetalleAdelantoPersonals.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDetalleAdelantoPersonals.setAltoHead(30);
        tablaDetalleAdelantoPersonals.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaDetalleAdelantoPersonals.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaDetalleAdelantoPersonals.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaDetalleAdelantoPersonals.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaDetalleAdelantoPersonals.setComponentPopupMenu(popMenu);
        tablaDetalleAdelantoPersonals.setGrosorBordeFilas(0);
        tablaDetalleAdelantoPersonals.setGrosorBordeHead(0);
        tablaDetalleAdelantoPersonals.setRowHeight(20);
        tablaDetalleAdelantoPersonals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaDetalleAdelantoPersonalsMousePressed(evt);
            }
        });
        tablaDetalleAdelantoPersonals.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaDetalleAdelantoPersonalsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaDetalleAdelantoPersonalsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaDetalleAdelantoPersonalsKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tablaDetalleAdelantoPersonals);

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(65, 94, 255));
        lblEstado.setText("NOMBRE:");

        cboEstado.setBackground(new java.awt.Color(255, 255, 255));
        cboEstado.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboEstado.setForeground(new java.awt.Color(65, 94, 255));
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADELANTO POR PAGAR", "ADELANTO PAGADO" }));
        cboEstado.setAccent(new java.awt.Color(65, 94, 255));
        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(lblEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblEstado))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout rSPanelShadow1Layout = new javax.swing.GroupLayout(rSPanelShadow1);
        rSPanelShadow1.setLayout(rSPanelShadow1Layout);
        rSPanelShadow1Layout.setHorizontalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSPanelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rSPanelShadow1Layout.setVerticalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 int fila;
    private void tablaDetalleAdelantoPersonalsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetalleAdelantoPersonalsMousePressed
        fila = tablaDetalleAdelantoPersonals.getSelectedRow();
    }//GEN-LAST:event_tablaDetalleAdelantoPersonalsMousePressed

    private void tablaDetalleAdelantoPersonalsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleAdelantoPersonalsKeyReleased
        fila = tablaDetalleAdelantoPersonals.getSelectedRow();
    }//GEN-LAST:event_tablaDetalleAdelantoPersonalsKeyReleased

    private void tablaDetalleAdelantoPersonalsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleAdelantoPersonalsKeyTyped

    }//GEN-LAST:event_tablaDetalleAdelantoPersonalsKeyTyped

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        obtenerAdelantoPersonal();
        FormDatosAdelantoPersonal a = new FormDatosAdelantoPersonal(null, true, adelantosSeleccionado);
        a.setVisible(true);
        obtenerDatosBaseDatos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnModificarAPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAPagadoActionPerformed
        try {
            String codigo = tablaDetalleAdelantoPersonals.getValueAt(fila, 0).toString();
            int numeros_afectados = gestionarAdelantoPersonalServicio.modificarAdelantoPersonalAPagado(Integer.parseInt(codigo));
            if (numeros_afectados == 1) {
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                obtenerDatosBaseDatos();
            } else {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnModificarAPagadoActionPerformed

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        obtenerDatosBaseDatos();        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void btnModificarPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPorPagarActionPerformed
        try {
            String codigo = tablaDetalleAdelantoPersonals.getValueAt(fila, 0).toString();
            int numeros_afectados = gestionarAdelantoPersonalServicio.modificarAdelantoPersonalPorPagar(Integer.parseInt(codigo));
            if (numeros_afectados == 1) {
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                obtenerDatosBaseDatos();
            } else {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarPorPagarActionPerformed

    private void tablaDetalleAdelantoPersonalsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDetalleAdelantoPersonalsKeyPressed
        fila = tablaDetalleAdelantoPersonals.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tablaDetalleAdelantoPersonalsKeyPressed

    public void obtenerAdelantoPersonal() {
        try {
            String codigo = tablaDetalleAdelantoPersonals.getValueAt(fila, 0).toString();
            adelantosSeleccionado = gestionarAdelantoPersonalServicio.buscarAdelantoPersonalPorCodigo(Integer.parseInt(codigo));
//            adelantosSeleccionado.setContrato(contratoSeleccionado);
            obtenerDatosBaseDatos();
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCerrar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btnModificarAPagado;
    private rojeru_san.RSButton btnModificarPorPagar;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox cboEstado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombreTrabajador;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojerusan.RSTableMetro tablaDetalleAdelantoPersonals;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {
        try {
            String codigo = tablaDetalleAdelantoPersonals.getValueAt(fila, 0).toString();
            if (!Mensaje.mostrarPreguntaDeEliminacion(null)) {
                return;
            }

            int registrosAfectados = gestionarAdelantoPersonalServicio.eliminarAdelantoPersonalPorCodigo(Integer.parseInt(codigo));
            if (registrosAfectados == 1) {
                Mensaje.mostrarAfirmacionDeEliminacion(null);
                obtenerDatosBaseDatos();
            } else {
                Mensaje.mostrarAdvertenciaDeEliminacion(null);
            }
        } catch (Exception ee) {
            Mensaje.mostrarErrorSistema(this);
        }
    }
}
