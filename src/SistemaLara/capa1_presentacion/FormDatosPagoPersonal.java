/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa2_aplicacion.GestionarPagoPersonalServicio;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.PagoPersonal;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosPagoPersonal extends javax.swing.JDialog {

    GestionarPagoPersonalServicio gestionarPagoPersonalServicio;
    private GestionarContratoServicio gestionarContratoServicio;
    PagoPersonal pagoPersonalPersonal;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    public static Contrato contratoSeleccionado;
    int mes = 0;
    int anio;
    private int codigoPago;
    private GestionarPagoPersonalServicio gestionarAdelantoServicio;

    public FormDatosPagoPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        pagoPersonalPersonal = new PagoPersonal();
        tipo_accion = ACCION_CREAR;

        gestionarAdelantoServicio = new GestionarPagoPersonalServicio();
        gestionarPagoPersonalServicio = new GestionarPagoPersonalServicio();
        inicializadores();
        inisializadorAgregar();
        inabilitarTextos();

    }

    public FormDatosPagoPersonal(java.awt.Frame parent, boolean modal, PagoPersonal pagoPersonal) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        pagoPersonalPersonal = pagoPersonal;
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
        gestionarAdelantoServicio = new GestionarPagoPersonalServicio();
        btnAgregar.setEnabled(true);
        btnTrabajador.setEnabled(false);
        btnDetalles.setVisible(true);

    }

    public FormDatosPagoPersonal(java.awt.Frame parent, boolean modal, Contrato contrato) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);

        this.setLocationRelativeTo(null);
        try {

            tipo_accion = ACCION_CREAR;
            gestionarContratoServicio = new GestionarContratoServicio();
            gestionarAdelantoServicio = new GestionarPagoPersonalServicio();
            gestionarPagoPersonalServicio = new GestionarPagoPersonalServicio();
            contratoSeleccionado = contrato;
            btnAgregar.setEnabled(true);
            btnTrabajador.setEnabled(false);
            btnDetalles.setVisible(true);
            inicializadores();
            obtenerDatosFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void inabilitarTextos() {
        txtSueldo.setEnabled(false);
        txtTotalAdelanto.setEnabled(false);
        txtPersonal.setEnabled(false);
    }

    private void llenarDatosTextos() {
        txtSueldo.setText(String.valueOf(pagoPersonalPersonal.getContrato().getSueldo()));
        txtSueldoActual.setText(String.valueOf(pagoPersonalPersonal.getMontoPagado()));
        dateFechaPago.setDatoFecha(pagoPersonalPersonal.getFechaPago());
        contratoSeleccionado = pagoPersonalPersonal.getContrato();
    }

    void inicializadores() {
        Calendar c1 = Calendar.getInstance();
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);
        dateFechaPago.setDatoFecha(new Date());

    }

    private void inisializadorAgregar() {
        java.util.Date fecha = new Date();
        dateFechaPago.setDatoFecha(fecha);
        btnDetalles.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        dateFechaPago = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        lblTrabajador = new javax.swing.JLabel();
        lblSueldo = new javax.swing.JLabel();
        lblSueldoActual = new javax.swing.JLabel();
        lblFechaPago = new javax.swing.JLabel();
        btnDetalles = new rojerusan.RSButtonRound();
        txtPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtSueldo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        btnTrabajador = new rojerusan.RSButtonIconD();
        txtSueldoActual = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        lblSueldo1 = new javax.swing.JLabel();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        txtTotalAdelanto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 94, 255)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel5.setBorder(dropShadowBorder1);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel5.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 50, 50));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel5.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 50, 50));

        dateFechaPago.setColorBackground(new java.awt.Color(64, 95, 255));
        dateFechaPago.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dateFechaPago.setColorForeground(new java.awt.Color(64, 95, 255));
        dateFechaPago.setPlaceholder("FECHA DE PAGO");
        dateFechaPago.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel5.add(dateFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 250, 35));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 50, 50));
        jPanel5.add(lblTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 190, 10));
        jPanel5.add(lblSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 250, 10));
        jPanel5.add(lblSueldoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 250, 10));
        jPanel5.add(lblFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 250, 10));

        btnDetalles.setBackground(new java.awt.Color(65, 94, 255));
        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnDetalles.setText("Ver Detalle");
        btnDetalles.setColorHover(new java.awt.Color(253, 173, 1));
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        jPanel5.add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 150, 26));

        txtPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPersonal.setLabel("PERSONAL");
        txtPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel5.add(txtPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 230, 60));

        txtSueldo.setForeground(new java.awt.Color(65, 94, 255));
        txtSueldo.setAccent(new java.awt.Color(0, 132, 235));
        txtSueldo.setCaretColor(new java.awt.Color(0, 132, 235));
        txtSueldo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSueldo.setLabel("S/. SUELDO");
        txtSueldo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtSueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoKeyTyped(evt);
            }
        });
        jPanel5.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 250, 60));

        btnTrabajador.setBackground(new java.awt.Color(255, 255, 255));
        btnTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        btnTrabajador.setColorHover(new java.awt.Color(255, 187, 51));
        btnTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadorActionPerformed(evt);
            }
        });
        jPanel5.add(btnTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 40, 40));

        txtSueldoActual.setForeground(new java.awt.Color(65, 94, 255));
        txtSueldoActual.setAccent(new java.awt.Color(0, 132, 235));
        txtSueldoActual.setCaretColor(new java.awt.Color(0, 132, 235));
        txtSueldoActual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSueldoActual.setLabel("SUELDO ACTUAL");
        txtSueldoActual.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtSueldoActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoActualKeyTyped(evt);
            }
        });
        jPanel5.add(txtSueldoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 250, 60));
        jPanel5.add(lblSueldo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 250, 10));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 50, 50));

        txtTotalAdelanto.setForeground(new java.awt.Color(65, 94, 255));
        txtTotalAdelanto.setAccent(new java.awt.Color(0, 132, 235));
        txtTotalAdelanto.setCaretColor(new java.awt.Color(0, 132, 235));
        txtTotalAdelanto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalAdelanto.setLabel("S/. TOTAL ADELANTO");
        txtTotalAdelanto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtTotalAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalAdelantoKeyTyped(evt);
            }
        });
        jPanel5.add(txtTotalAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 250, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 94, 255));
        jLabel3.setText("FECHA DE PAGO");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 250, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 41, 350, 380));

        jPanel3.setBackground(new java.awt.Color(65, 94, 255));
        jPanel3.setForeground(new java.awt.Color(65, 94, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Guardar.png"))); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar.setColorText(new java.awt.Color(68, 138, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.setIconTextGap(0);
        btnAgregar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgregarFocusGained(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 140, 46));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setColorText(new java.awt.Color(68, 138, 255));
        btnCancelar.setIconTextGap(0);
        btnCancelar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnCancelarFocusGained(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 140, 46));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 370, 364, 130));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));
        jPanel2.setForeground(new java.awt.Color(255, 68, 68));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL PAGO");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPago.png"))); // NOI18N

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(255, 68, 68));
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 364, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        guardarDatos();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCancelarFocusGained

    }//GEN-LAST:event_btnCancelarFocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        try {
            FormListaDetalleAdelantoPersonal detalle = new FormListaDetalleAdelantoPersonal(null, true, contratoSeleccionado);
            detalle.setVisible(true);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void txtSueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtSueldoActual.requestFocus();
        }
    }//GEN-LAST:event_txtSueldoKeyTyped

    private void btnTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadorActionPerformed
        FormGestionarContrato a = new FormGestionarContrato(null, true, FormGestionarContrato.TIPO_PAGO);
        a.setVisible(true);
        obtenerDatosFormulario();
    }//GEN-LAST:event_btnTrabajadorActionPerformed

    private void txtTotalAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAdelantoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAdelantoKeyTyped

    private void txtSueldoActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoActualKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtSueldoActual.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSueldoActualKeyTyped

    public void obtenerDatosFormulario() {

        if (contratoSeleccionado != null) {
            txtSueldo.setText(String.valueOf(contratoSeleccionado.getSueldo()));
            try {
                Double totalSolesAdelanto = gestionarPagoPersonalServicio.obtenerTotalSolesAdelantoPorPersonal(contratoSeleccionado.getCodigo());
                txtTotalAdelanto.setText(String.valueOf(totalSolesAdelanto));
                Double resta = contratoSeleccionado.getSueldo() - totalSolesAdelanto;
                txtSueldoActual.setText(String.valueOf(resta));
                txtPersonal.setText(contratoSeleccionado.getPersonal().getGenerarNombre());
                btnDetalles.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } else {
            btnDetalles.setVisible(false);
        }
    }

    private void guardarDatos() {
        pagoPersonalPersonal = new PagoPersonal();
        if (verificadorCamposVacios()) {
            pagoPersonalPersonal.setContrato(contratoSeleccionado);
            pagoPersonalPersonal.setMontoPagado(Double.parseDouble(txtSueldoActual.getText()));
            Date fechaPago = dateFechaPago.getDatoFecha();
            pagoPersonalPersonal.setFechaPago(new java.sql.Date(fechaPago.getTime()));
            pagoPersonalPersonal.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                   List<Adelanto> listaAdelanto=gestionarPagoPersonalServicio.obtenerAdelantoPorPersonal(contratoSeleccionado.getCodigo(), 8);
                   pagoPersonalPersonal.setListaAdelantos(listaAdelanto);
                    registros_afectados = gestionarPagoPersonalServicio.guardarPagoPersonal(pagoPersonalPersonal);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod software", "Usted Acaba crear un Nuevo Pago para el Personal", DesktopNotify.SUCCESS);
                        this.dispose();
                        DesktopNotify.eliminarWindowsAll();
                        TimerPagosTrabajador.iniciarOtraVes();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    pagoPersonalPersonal.setCodigo(codigoPago);

                    registros_afectados = gestionarPagoPersonalServicio.modificarPagoPersonal(pagoPersonalPersonal);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod software", "Usted Acaba de Modificar el Pago del Personal", DesktopNotify.INPUT_REQUEST);
                        this.dispose();
                    } else {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }

    }

    private boolean verificarSiHayPagosDisponibles() {
        boolean resultado = false;
        try {
            int total = gestionarAdelantoServicio.verificarSiHayPagosDisponibles(contratoSeleccionado.getCodigo());
            if (total > 0) {
                resultado = true;
            }
        } catch (Exception e) {
        }
        return resultado;

    }

    double buscarAdelantoAuxiliar(Contrato contrato) throws Exception {
//        return gestionarAdelantoServicio.buscarAdelantoTotalPorContrato(contrato.getCodigo(), mes, Adelanto.ESTADO_POR_PAGAR);
        return 0.0;
    }

    private boolean verificadorCamposVacios() {
        int contador = 0, aux = 0;

        contador = Verificador.verificadorCampos(txtPersonal, lblTrabajador, "trabajador");
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtSueldo, lblSueldo, "Sueldo");
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtSueldoActual, lblSueldoActual, "Sueldo actual");
        aux = contador + aux;
        contador = Verificador.verificadorCamposFechas(dateFechaPago, lblFechaPago, "Fecha de pagoPersonalPersonal");
        aux = contador + aux;

        return aux == 4;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private rojerusan.RSButtonRound btnDetalles;
    private rojerusan.RSButtonIconD btnTrabajador;
    private rojeru_san.componentes.RSDateChooser dateFechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblSueldo;
    private javax.swing.JLabel lblSueldo1;
    private javax.swing.JLabel lblSueldoActual;
    private javax.swing.JLabel lblTrabajador;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSueldo;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSueldoActual;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalAdelanto;
    // End of variables declaration//GEN-END:variables
}
