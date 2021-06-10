/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import static SistemaLara.capa1_presentacion.FormRegistroFactura.facturaSeleccionado;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa2_aplicacion.GestionarFacturaReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarPagoPersonalServicio;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Mail;
import SistemaLara.capa3_dominio.PagoPersonal;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosCorreFactura extends javax.swing.JDialog {

    GestionarPagoPersonalServicio gestionarPagoPersonalServicio;
    private GestionarContratoServicio gestionarContratoServicio;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private CorreoFactura correoFactura;
    private JasperPrint print = null;
    private GestionarFacturaReporteServicio gestionarFacturaReporteServicio;
    int mes = 0;
    int anio;
    private int codigoPago;
    private GestionarPagoPersonalServicio gestionarAdelantoServicio;

    public FormDatosCorreFactura(java.awt.Frame parent, boolean modal, CorreoFactura correoFactura) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_CREAR;
        this.correoFactura = correoFactura;
        gestionarAdelantoServicio = new GestionarPagoPersonalServicio();
        gestionarPagoPersonalServicio = new GestionarPagoPersonalServicio();
        gestionarFacturaReporteServicio = new GestionarFacturaReporteServicio();
        inicializarTablaColumnas();
        llenarDatos();
    }

    void llenarDatos() {
        try {
            if (!correoFactura.getElectronico().getRutapdf().equals("")) {
                btnView.setVisible(true);
            } else {
                btnView.setVisible(false);
            }
            txtPdf.setText(correoFactura.getElectronico().getRutapdf());
            txtxml.setText(correoFactura.getElectronico().getRutaxml());
            txtProveedor.setText(correoFactura.getProveedorServicio().getRazonSocial());
            agregarFila(correoFactura.getProveedorServicio());
            if (correoFactura.getProveedorServicio().getEmail().isEmpty()) {
                Mensaje.mostrarMensajeDefinido(this, "Debe asociar un correo electronico al proveedor por favor llenelo e intente en otro momento");
                btnEnviar.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
//        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CORREO ELECTRÓNICOS", "java.lang.String"));

        //tabla.agregarColumna(new Columna("CODIGO ", "java.lang.Integer"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaProveedorServicio.setModel(modeloTabla);

    }

    void agregarFila(ProveedorServicio proveedorServicio) {
        ModeloTabla modeloTabla = (ModeloTabla) tablaProveedorServicio.getModel();
        Fila filaTabla;
        filaTabla = new Fila();
        String[] listaCorreos = proveedorServicio.getEmail().split(";");
        for (String correoElectronico : listaCorreos) {
            filaTabla = new Fila();
            filaTabla.agregarValorCelda(correoElectronico);
            modeloTabla.agregarFila(filaTabla);
        }
        modeloTabla.refrescarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        lblTrabajador = new javax.swing.JLabel();
        lblSueldoActual = new javax.swing.JLabel();
        lblFechaPago = new javax.swing.JLabel();
        txtProveedor = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        lblSueldo1 = new javax.swing.JLabel();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        txtMensaje = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProveedorServicio = new rojerusan.RSTableMetro();
        txtxml = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        txtPdf = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtAsunto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        btnView = new rojeru_san.RSButton();
        btnDarDeBaja1 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        btnEnviar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        rSButton3 = new rojeru_san.RSButton();

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

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel5.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));
        jPanel5.add(lblTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 190, 10));
        jPanel5.add(lblSueldoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 250, 10));
        jPanel5.add(lblFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 250, 10));

        txtProveedor.setForeground(new java.awt.Color(65, 94, 255));
        txtProveedor.setAccent(new java.awt.Color(0, 132, 235));
        txtProveedor.setCaretColor(new java.awt.Color(0, 132, 235));
        txtProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtProveedor.setLabel("PROVEEDOR SERVICIO");
        txtProveedor.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel5.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 620, 60));
        jPanel5.add(lblSueldo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 250, 10));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 50, 50));

        txtMensaje.setForeground(new java.awt.Color(65, 94, 255));
        txtMensaje.setAccent(new java.awt.Color(0, 132, 235));
        txtMensaje.setCaretColor(new java.awt.Color(0, 132, 235));
        txtMensaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMensaje.setLabel("MENSAJE");
        txtMensaje.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMensajeKeyTyped(evt);
            }
        });
        jPanel5.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 630, 60));

        tablaProveedorServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProveedorServicio.setAltoHead(30);
        tablaProveedorServicio.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaProveedorServicio.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaProveedorServicio.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaProveedorServicio.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaProveedorServicio.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaProveedorServicio.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaProveedorServicio.setGrosorBordeFilas(0);
        tablaProveedorServicio.setGrosorBordeHead(0);
        tablaProveedorServicio.setRowHeight(20);
        tablaProveedorServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaProveedorServicioMousePressed(evt);
            }
        });
        tablaProveedorServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProveedorServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaProveedorServicioKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaProveedorServicio);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 800, 140));

        txtxml.setForeground(new java.awt.Color(65, 94, 255));
        txtxml.setAccent(new java.awt.Color(0, 132, 235));
        txtxml.setCaretColor(new java.awt.Color(0, 132, 235));
        txtxml.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtxml.setLabel("ARCHIVO XML");
        txtxml.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtxml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtxmlKeyTyped(evt);
            }
        });
        jPanel5.add(txtxml, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 740, 60));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 50, 50));

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 50, 50));

        txtPdf.setForeground(new java.awt.Color(65, 94, 255));
        txtPdf.setAccent(new java.awt.Color(0, 132, 235));
        txtPdf.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPdf.setLabel("ARCHIVO PDF");
        txtPdf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtPdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPdfKeyTyped(evt);
            }
        });
        jPanel5.add(txtPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 540, 60));

        txtAsunto.setForeground(new java.awt.Color(65, 94, 255));
        txtAsunto.setAccent(new java.awt.Color(0, 132, 235));
        txtAsunto.setCaretColor(new java.awt.Color(0, 132, 235));
        txtAsunto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAsunto.setLabel("ASUNTO");
        txtAsunto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtAsunto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAsuntoKeyTyped(evt);
            }
        });
        jPanel5.add(txtAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 630, 60));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 50, 50));

        btnView.setBackground(new java.awt.Color(0, 102, 255));
        btnView.setText("VIEW");
        btnView.setColorHover(new java.awt.Color(255, 82, 54));
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        jPanel5.add(btnView, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 70, -1));

        btnDarDeBaja1.setBackground(new java.awt.Color(0, 102, 255));
        btnDarDeBaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/DarDeBajaNuevo.png"))); // NOI18N
        btnDarDeBaja1.setText("GENERAR");
        btnDarDeBaja1.setColorHover(new java.awt.Color(255, 82, 54));
        btnDarDeBaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarDeBaja1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnDarDeBaja1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, 130, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 41, 820, 500));

        jPanel3.setBackground(new java.awt.Color(65, 94, 255));
        jPanel3.setForeground(new java.awt.Color(65, 94, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEnviar.setBackground(new java.awt.Color(255, 255, 255));
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Guardar.png"))); // NOI18N
        btnEnviar.setText("Enviar");
        btnEnviar.setColorHover(new java.awt.Color(253, 173, 1));
        btnEnviar.setColorText(new java.awt.Color(68, 138, 255));
        btnEnviar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEnviar.setIconTextGap(0);
        btnEnviar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnEnviarFocusGained(evt);
            }
        });
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 140, 46));

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
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 140, 46));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 820, 90));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));
        jPanel2.setForeground(new java.awt.Color(255, 68, 68));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS CORREO FACTURA");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(253, 253, 253)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 820, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnEnviarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarFocusGained

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarCorreoElectronico();

    }//GEN-LAST:event_btnEnviarActionPerformed

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

    private void txtMensajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensajeKeyTyped

    private void txtxmlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtxmlKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtxmlKeyTyped

    private void txtPdfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPdfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPdfKeyTyped

    private void rSButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton3ActionPerformed

    private void tablaProveedorServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProveedorServicioKeyTyped
        //        try {
        //            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        //                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
        //
        //                    obtenerFactura();
        //                }
        //            }
        //        } catch (Exception e) {
        //            Mensaje.mostrarErrorSistema(this);
        //        }
    }//GEN-LAST:event_tablaProveedorServicioKeyTyped

    private void tablaProveedorServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProveedorServicioKeyReleased

    }//GEN-LAST:event_tablaProveedorServicioKeyReleased

    private void tablaProveedorServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedorServicioMousePressed

    }//GEN-LAST:event_tablaProveedorServicioMousePressed

    private void txtAsuntoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAsuntoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsuntoKeyTyped

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed

        try {
            String url = txtPdf.getText().toString();
            if (!url.equals("")) {
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                p.start();
            } else {
                Mensaje.mostrarAdvertencia(this, "No fueposible abrir el archivo");
            }
        } catch (Exception e) {
            Mensaje.mostrarAdvertencia(this, "No fueposible abrir el archivo");
        }


    }//GEN-LAST:event_btnViewActionPerformed

    private void btnDarDeBaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDeBaja1ActionPerformed
        try {
            String url = gestionarFacturaReporteServicio.generarReporteFacturaParaElectronico(correoFactura);
            if (!url.equals("")) {
                correoFactura.getElectronico().setRutapdf(url);
                btnView.setVisible(true);
                txtPdf.setText(url);
            } else {
                btnView.setVisible(false);
            }

        } catch (Exception ex) {
            Mensaje.mostrarMensajeDefinido(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnDarDeBaja1ActionPerformed

    public void obtenerDatosFormulario() {

//        if (contratoSeleccionado != null) {
//            txtSueldo.setText(String.valueOf(contratoSeleccionado.getSueldo()));
//            try {
//                Double totalSolesAdelanto = gestionarPagoPersonalServicio.obtenerTotalSolesAdelantoPorPersonal(contratoSeleccionado.getCodigo());
//                txtDescripcion.setText(String.valueOf(totalSolesAdelanto));
//                Double resta = contratoSeleccionado.getSueldo() - totalSolesAdelanto;
//                txtSueldoActual.setText(String.valueOf(resta));
//                txtProveedor.setText(contratoSeleccionado.getPersonal().getGenerarNombre());
//                btnDetalles.setVisible(true);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            }
//
//        } else {
//            btnDetalles.setVisible(false);
//        }
    }

    private void enviarCorreoElectronico() {
        if (verificadorCamposVacios()) {
            try {
                correoFactura.setAsunoto(txtAsunto.getText());
                correoFactura.setMensaje(txtMensaje.getText());
                boolean estado = false;
                String[] listaCorreos = correoFactura.getProveedorServicio().getEmail().split(";");
                int contador = 0;
                String correoEnviados = "";
                for (String correoElectronico : listaCorreos) {
                    estado = Mail.enviarCorreo(correoFactura, correoElectronico);
                    if (estado) {
                        correoEnviados = correoEnviados + " " + correoElectronico;
                    }
                }
                if (estado) {
                    JOptionPane.showMessageDialog(null, "se envio correctamente");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Solamente se envio alos siguiente correos " + correoEnviados);
                }

            } catch (Exception e) {
                Mensaje.mostrarErrorDeCreacion(this);
            }

        }

    }

    private boolean verificarSiHayPagosDisponibles() {
//        boolean resultado = false;
//        try {
//            int total = gestionarAdelantoServicio.verificarSiHayPagosDisponibles(contratoSeleccionado.getCodigo());
//            if (total > 0) {
//                resultado = true;
//            }
//        } catch (Exception e) {
//        }
//        return resultado;
        return true;
    }

    double buscarAdelantoAuxiliar(Contrato contrato) throws Exception {
//        return gestionarAdelantoServicio.buscarAdelantoTotalPorContrato(contrato.getCodigo(), mes, Adelanto.ESTADO_POR_PAGAR);
        return 0.0;
    }

    private boolean verificadorCamposVacios() {
        int contador = 0, aux = 0;
        contador = Verificador.verificadorCampos(txtProveedor, lblTrabajador, "proveedor");
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtAsunto);
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtMensaje);
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtxml);
        aux = contador + aux;
        contador = Verificador.verificadorCampos(txtPdf);
        aux = contador + aux;
        return aux == 5;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnDarDeBaja1;
    private rojeru_san.RSButton btnEnviar;
    private rojeru_san.RSButton btnView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblSueldo1;
    private javax.swing.JLabel lblSueldoActual;
    private javax.swing.JLabel lblTrabajador;
    private rojeru_san.RSButton rSButton2;
    private rojeru_san.RSButton rSButton3;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSTableMetro tablaProveedorServicio;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtAsunto;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMensaje;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPdf;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtxml;
    // End of variables declaration//GEN-END:variables
}
