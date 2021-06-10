/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

//import motorepuestos.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarConceptoServicio;
import SistemaLara.capa3_dominio.Concepto;

//import motorepuestos.capa1_presentacion.util.DesktopNotify;
//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarConceptoServicio;
//import motorepuestos.capa3_dominio.Concepto;
/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosEmpresa extends javax.swing.JDialog {

    GestionarConceptoServicio gestionarConceptoServicio;
    Concepto concepto;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoConcepto;

    public FormDatosEmpresa(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);

        tipo_accion = ACCION_CREAR;
        gestionarConceptoServicio = new GestionarConceptoServicio();
        //  txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());

    }

//    public FormDatosEmpresa(java.awt.Frame parent, boolean modal, Concepto concepto) throws Exception {
//        super(parent, modal);
//        initComponents();
//        Animacion.moverParaIzquierda(this);
//        this.setLocationRelativeTo(null);
//        tipo_accion = ACCION_MODIFICAR;
//        gestionarConceptoServicio = new GestionarConceptoServicio();
//        this.concepto = concepto;
//        llenarCampos(concepto);
//
//        verificarCamposVacios();
//        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());
//    }
//    private void llenarCampos(Concepto concepto1) {
//        codigoConcepto = concepto1.getCodigo();
//        txtDescripcion.setText(concepto1.getDescripcion());
//
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar1 = new rojeru_san.RSButton();
        jPanel5 = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        txtRazonSocial = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        txtDireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        cboTipoDocumento = new rojerusan.RSComboMetro();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        cboProvincia = new rojerusan.RSComboMetro();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        cboDistrito = new rojerusan.RSComboMetro();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        txtNombreLegal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        txtUrbanizacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        txtNumeroDocumento = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboDepartament = new rojerusan.RSComboMetro();
        rSLabelImage13 = new rojerusan.RSLabelImage();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        txtUbigeo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        txtRutaPfx = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        txtClavePdx = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        txtRutaXml = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        sftFoto = new rojerusan.RSFotoSquare();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(68, 138, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(15);
        dropShadowBorder1.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder1);
        jPanel4.setPreferredSize(new java.awt.Dimension(392, 440));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setText("Guardar");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
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
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 140, 46));

        btnCancelar1.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar1.setText("Cancelar");
        btnCancelar1.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 140, 46));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel5.setBorder(dropShadowBorder2);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 410, 10));

        txtRazonSocial.setForeground(new java.awt.Color(0, 0, 204));
        txtRazonSocial.setAccent(new java.awt.Color(204, 0, 51));
        txtRazonSocial.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRazonSocial.setLabel("RAZON SOCIAL");
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });
        jPanel5.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 190, 60));
        jPanel5.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));
        jPanel5.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 30));

        txtDireccion.setForeground(new java.awt.Color(0, 0, 204));
        txtDireccion.setAccent(new java.awt.Color(204, 0, 51));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDireccion.setLabel("DIRECCION");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jPanel5.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 190, 60));
        jPanel5.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 40, 30));

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO DOCUMENTO" }));
        cboTipoDocumento.setColorArrow(new java.awt.Color(65, 94, 255));
        cboTipoDocumento.setColorBorde(new java.awt.Color(65, 94, 255));
        cboTipoDocumento.setColorFondo(new java.awt.Color(65, 94, 255));
        jPanel5.add(cboTipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 190, 20));
        jPanel5.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 40, 30));

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PROVINCIA" }));
        cboProvincia.setColorArrow(new java.awt.Color(65, 94, 255));
        cboProvincia.setColorBorde(new java.awt.Color(65, 94, 255));
        cboProvincia.setColorFondo(new java.awt.Color(65, 94, 255));
        cboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProvinciaActionPerformed(evt);
            }
        });
        jPanel5.add(cboProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 190, 20));
        jPanel5.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 40, 30));

        cboDistrito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DISTRITO" }));
        cboDistrito.setColorArrow(new java.awt.Color(65, 94, 255));
        cboDistrito.setColorBorde(new java.awt.Color(65, 94, 255));
        cboDistrito.setColorFondo(new java.awt.Color(65, 94, 255));
        jPanel5.add(cboDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, 20));
        jPanel5.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 40, 30));

        txtNombreLegal.setForeground(new java.awt.Color(0, 0, 204));
        txtNombreLegal.setAccent(new java.awt.Color(204, 0, 51));
        txtNombreLegal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNombreLegal.setLabel("NOMBRE LEGAL");
        txtNombreLegal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreLegalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreLegalKeyTyped(evt);
            }
        });
        jPanel5.add(txtNombreLegal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 190, 60));
        jPanel5.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 40, 30));

        txtUrbanizacion.setForeground(new java.awt.Color(0, 0, 204));
        txtUrbanizacion.setAccent(new java.awt.Color(204, 0, 51));
        txtUrbanizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtUrbanizacion.setLabel("URBANIZACION");
        txtUrbanizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUrbanizacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUrbanizacionKeyTyped(evt);
            }
        });
        jPanel5.add(txtUrbanizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 190, 60));
        jPanel5.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 40, 30));

        txtNumeroDocumento.setForeground(new java.awt.Color(0, 0, 204));
        txtNumeroDocumento.setAccent(new java.awt.Color(204, 0, 51));
        txtNumeroDocumento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNumeroDocumento.setLabel("N° DOCUMENTO ");
        txtNumeroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroDocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroDocumentoKeyTyped(evt);
            }
        });
        jPanel5.add(txtNumeroDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, -10, 190, 60));

        cboDepartament.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPARTAMENTO", "AMAZONAS", "ANCASH", "APURIMAC", "AREQUIPA", "AYACUCHO", "CAJAMARCA", "CALLAO", "CUSCO", "HUANCAVELICA", "HUÁNUCO", "ICA", "JUNIN", "LA LIBERTAD", "LAMBAYEQUE", "LIMA", "LORETO", "MADRE DE DIOS", "MOQUEGUA", "PASCO", "PIURA", "PUNO", "SAN MARTIN", "TACNA", "TUMBES", "UCAYALI" }));
        cboDepartament.setColorArrow(new java.awt.Color(65, 94, 255));
        cboDepartament.setColorBorde(new java.awt.Color(65, 94, 255));
        cboDepartament.setColorFondo(new java.awt.Color(65, 94, 255));
        cboDepartament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepartamentActionPerformed(evt);
            }
        });
        jPanel5.add(cboDepartament, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 190, 20));
        jPanel5.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 30));
        jPanel5.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 40, 30));

        txtUbigeo.setForeground(new java.awt.Color(0, 0, 204));
        txtUbigeo.setAccent(new java.awt.Color(204, 0, 51));
        txtUbigeo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtUbigeo.setLabel("UBIGEO");
        txtUbigeo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUbigeoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUbigeoKeyTyped(evt);
            }
        });
        jPanel5.add(txtUbigeo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 190, 60));
        jPanel5.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 40, 30));

        txtRutaPfx.setForeground(new java.awt.Color(0, 0, 204));
        txtRutaPfx.setAccent(new java.awt.Color(204, 0, 51));
        txtRutaPfx.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRutaPfx.setLabel("RUTAPFX");
        txtRutaPfx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutaPfxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutaPfxKeyTyped(evt);
            }
        });
        jPanel5.add(txtRutaPfx, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 190, 60));
        jPanel5.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 40, 30));

        txtClavePdx.setForeground(new java.awt.Color(0, 0, 204));
        txtClavePdx.setAccent(new java.awt.Color(204, 0, 51));
        txtClavePdx.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtClavePdx.setLabel("CLAVEPFX");
        txtClavePdx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClavePdxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClavePdxKeyTyped(evt);
            }
        });
        jPanel5.add(txtClavePdx, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 190, 60));
        jPanel5.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 40, 30));

        txtRutaXml.setForeground(new java.awt.Color(0, 0, 204));
        txtRutaXml.setAccent(new java.awt.Color(204, 0, 51));
        txtRutaXml.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRutaXml.setLabel("RUTAXML");
        txtRutaXml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutaXmlKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutaXmlKeyTyped(evt);
            }
        });
        jPanel5.add(txtRutaXml, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 190, 60));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 580, 310));

        sftFoto.setBorder(null);
        sftFoto.setColorBorde(new java.awt.Color(65, 94, 255));
        sftFoto.setColorBotonCargar(new java.awt.Color(65, 94, 255));
        sftFoto.setColorBotonRemover(new java.awt.Color(65, 94, 255));
        sftFoto.setImagenDefault(null);
        sftFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sftFotoMouseClicked(evt);
            }
        });
        jPanel4.add(sftFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 150, 190));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOGO");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 130, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 48, 770, 400));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATOS DE LA EMPRESA");

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
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
                .addContainerGap(275, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 797, 100));

        jPanel3.setBackground(new java.awt.Color(65, 94, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 417, 799, 48));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void txtRazonSocialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialKeyPressed

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialKeyTyped

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void cboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProvinciaActionPerformed

    }//GEN-LAST:event_cboProvinciaActionPerformed

    private void txtNombreLegalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreLegalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreLegalKeyPressed

    private void txtNombreLegalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreLegalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreLegalKeyTyped

    private void txtUrbanizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrbanizacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrbanizacionKeyPressed

    private void txtUrbanizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrbanizacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrbanizacionKeyTyped

    private void txtNumeroDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroDocumentoKeyPressed

    private void txtNumeroDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroDocumentoKeyTyped

    private void cboDepartamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDepartamentActionPerformed

        if (cboDepartament.getSelectedItem().equals("DEPARTAMENTO")) {
            cboProvincia.removeAllItems();
            cboProvincia.addItem("SELECCIONE");
        } else {
            String selcccio = cboDepartament.getSelectedItem().toString();
            String[] devolver = Verificador.llenarComboRegion(cboProvincia, selcccio);
            cboProvincia.removeAllItems();
            for (String lista : devolver) {
                cboProvincia.addItem(lista);
            }
        }

    }//GEN-LAST:event_cboDepartamentActionPerformed

    private void txtUbigeoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbigeoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbigeoKeyPressed

    private void txtUbigeoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbigeoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbigeoKeyTyped

    private void txtRutaPfxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaPfxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaPfxKeyPressed

    private void txtRutaPfxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaPfxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaPfxKeyTyped

    private void txtClavePdxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClavePdxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClavePdxKeyPressed

    private void txtClavePdxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClavePdxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClavePdxKeyTyped

    private void txtRutaXmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaXmlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaXmlKeyPressed

    private void txtRutaXmlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaXmlKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaXmlKeyTyped

    private void sftFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sftFotoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sftFotoMouseClicked

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed
//    private boolean verificarCamposVacios() {
//        int contadr = 0, aux = 0;
//        contadr = Verificador.verificadorCampos(txtDescripcion, lblDescripcion, "Descripción");
//        aux = contadr + aux;
//
//        return aux == 1;
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar1;
    private rojerusan.RSComboMetro cboDepartament;
    private rojerusan.RSComboMetro cboDistrito;
    private rojerusan.RSComboMetro cboProvincia;
    private rojerusan.RSComboMetro cboTipoDocumento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblTelefono;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage14;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSFotoSquare sftFoto;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtClavePdx;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDireccion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNombreLegal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNumeroDocumento;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRazonSocial;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRutaPfx;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRutaXml;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtUbigeo;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtUrbanizacion;
    // End of variables declaration//GEN-END:variables
}
