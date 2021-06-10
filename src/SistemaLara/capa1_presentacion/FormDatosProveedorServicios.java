package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarProveedorServicioServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.TipoProveedor;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosProveedorServicios extends javax.swing.JDialog {

    GestionarProveedorServicioServicio gestionarProveedorServicioServicio;
    ProveedorServicio proveedorServicio;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoProveedorServicio;
    private TipoProveedor tipoProveedor;

    public FormDatosProveedorServicios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_CREAR;
        gestionarProveedorServicioServicio = new GestionarProveedorServicioServicio();
        llenarItemTipoProveedor();
        txtPersonal.setEnabled(false);
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());
        txtEmail.setLineWrap(true);
        txtEmail.setWrapStyleWord(true);
    }

    private void llenarItemTipoProveedor() {
        try {
            cboTipoProveedor.removeAllItems();
            gestionarProveedorServicioServicio.llenarCBOTipoProveedor(1, cboTipoProveedor);

        } catch (Exception e) {
        }
    }

    public FormDatosProveedorServicios(java.awt.Frame parent, boolean modal, ProveedorServicio proveedorServicio) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
        this.proveedorServicio = proveedorServicio;
        gestionarProveedorServicioServicio = new GestionarProveedorServicioServicio();
        llenarItemTipoProveedor();
        llenarDatosCampos(this.proveedorServicio);
        txtPersonal.setEnabled(false);
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());
        verificarCamposVacios();
        verificarContadorTextos();
        txtEmail.setLineWrap(true);
        txtEmail.setWrapStyleWord(true);

    }
//    private void llenarFechas() {
//        java.util.Date fecha = new Date();
//    }

    private void llenarDatosCampos(ProveedorServicio proveedorServicio) {
        codigoProveedorServicio = proveedorServicio.getCodigo();
        txtRazonSocial.setText(proveedorServicio.getRazonSocial());
        txtRuc.setText(proveedorServicio.getRuc());
        txtCuenta.setText(proveedorServicio.getCuente());
        txtEntidad.setText(proveedorServicio.getEntidad());
        txtTelefono.setText(proveedorServicio.getTelefono());
        txtEmail.setText(proveedorServicio.getEmail());
        txtDireccion.setText(proveedorServicio.getDireccion());

        for (int i = 0; i < cboTipoProveedor.getItemCount(); i++) {
            if (proveedorServicio.getTipoProveedor().getDescripcion().equals(cboTipoProveedor.getItemAt(i).getDescripcion())) {
                cboTipoProveedor.setSelectedIndex(i);
            }
        }
    }
//    private void llenarTipoProveedorServicio() {
//        try {
//            ArrayList<TipoProveedorServicio> listaTipoProveedorServicio = gestionarProveedorServicioServicio.listaTipoProveedorServicio(TipoProveedorServicio.ESTADO_ACTIVO);
//            for (TipoProveedorServicio tipoProveedorServicio : listaTipoProveedorServicio) {
//                cbxTipoProveedorServicio.addItem(tipoProveedorServicio.getDescripcion());
//            }
//        } catch (Exception e) {
//        }
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        rSButton2 = new rojeru_san.RSButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSPanelsSlider4 = new rojerusan.RSPanelsSlider();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        lblNombre1 = new javax.swing.JLabel();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        rSLabelImage13 = new rojerusan.RSLabelImage();
        lblTipoProveedor = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        lblRuc = new javax.swing.JLabel();
        lblCuenta = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        txtRazonSocial = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtRuc = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCuenta = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtEntidad = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboTipoProveedor = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        jPanel1 = new javax.swing.JPanel();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        lblTelefono = new javax.swing.JLabel();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        lblDireccion = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPersonal = new javax.swing.JLabel();
        txtTelefono = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEmail = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        btnCancelar = new rojeru_san.RSButton();
        btnAgregar1 = new rojeru_san.RSButton();

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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelsSlider1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(68, 138, 255)));
        rSPanelsSlider1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelsSlider2.setBackground(new java.awt.Color(65, 94, 255));

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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoProveedorServicios.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DE PROVEEDOR SERVICIOS");

        javax.swing.GroupLayout rSPanelsSlider2Layout = new javax.swing.GroupLayout(rSPanelsSlider2);
        rSPanelsSlider2.setLayout(rSPanelsSlider2Layout);
        rSPanelsSlider2Layout.setHorizontalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rSPanelsSlider2Layout.setVerticalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        rSPanelsSlider1.add(rSPanelsSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 614, 33));

        rSPanelsSlider4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowBottomShadow(false);
        rSPanelsSlider4.setBorder(dropShadowBorder1);
        rSPanelsSlider4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/RazonSocial.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoProveedor.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 50));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 40));
        rSPanelsSlider4.add(lblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 10));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cuenta.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 50, 40));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Banco.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 50, 40));
        rSPanelsSlider4.add(lblTipoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 204, 10));
        rSPanelsSlider4.add(lblRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 210, 10));
        rSPanelsSlider4.add(lblRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 210, 10));
        rSPanelsSlider4.add(lblCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 210, 10));
        rSPanelsSlider4.add(lblEntidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 210, 10));

        txtRazonSocial.setForeground(new java.awt.Color(65, 94, 255));
        txtRazonSocial.setAccent(new java.awt.Color(0, 132, 235));
        txtRazonSocial.setCaretColor(new java.awt.Color(0, 132, 235));
        txtRazonSocial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRazonSocial.setLabel("RAZON SOCIAL");
        txtRazonSocial.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 60));

        txtRuc.setForeground(new java.awt.Color(65, 94, 255));
        txtRuc.setAccent(new java.awt.Color(0, 132, 235));
        txtRuc.setCaretColor(new java.awt.Color(0, 132, 235));
        txtRuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRuc.setLabel("RUC");
        txtRuc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 210, 60));

        txtCuenta.setForeground(new java.awt.Color(65, 94, 255));
        txtCuenta.setAccent(new java.awt.Color(0, 132, 235));
        txtCuenta.setCaretColor(new java.awt.Color(0, 132, 235));
        txtCuenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCuenta.setLabel("CUENTA");
        txtCuenta.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 210, 60));

        txtEntidad.setForeground(new java.awt.Color(65, 94, 255));
        txtEntidad.setAccent(new java.awt.Color(0, 132, 235));
        txtEntidad.setCaretColor(new java.awt.Color(0, 132, 235));
        txtEntidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEntidad.setLabel("ENTIDAD");
        txtEntidad.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtEntidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntidadKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtEntidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 210, 60));

        cboTipoProveedor.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoProveedor.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoProveedor.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoProveedor.setAccent(new java.awt.Color(65, 94, 255));
        rSPanelsSlider4.add(cboTipoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 170, 40));

        rSButtonIconD1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        rSButtonIconD1.setColorHover(new java.awt.Color(255, 187, 51));
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });
        rSPanelsSlider4.add(rSButtonIconD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 30, 30));

        rSPanelsSlider1.add(rSPanelsSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 290, 360));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowBottomShadow(false);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowRightShadow(false);
        jPanel1.setBorder(dropShadowBorder2);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Email.png"))); // NOI18N
        jPanel1.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 42, 40));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Direccion.png"))); // NOI18N
        jPanel1.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 42, 40));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel1.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 42, 38));
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 218, 10));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Telefono.png"))); // NOI18N
        jPanel1.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 42, 40));
        jPanel1.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 218, 10));
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 220, 10));

        lblPersonal.setForeground(new java.awt.Color(51, 204, 0));
        lblPersonal.setText("Personal");
        jPanel1.add(lblPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 218, 10));

        txtTelefono.setForeground(new java.awt.Color(65, 94, 255));
        txtTelefono.setAccent(new java.awt.Color(0, 132, 235));
        txtTelefono.setCaretColor(new java.awt.Color(0, 132, 235));
        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTelefono.setLabel("TELEFONO");
        txtTelefono.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 220, 60));

        txtDireccion.setForeground(new java.awt.Color(65, 94, 255));
        txtDireccion.setAccent(new java.awt.Color(0, 132, 235));
        txtDireccion.setCaretColor(new java.awt.Color(0, 132, 235));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDireccion.setLabel("DIRECCION");
        txtDireccion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 220, 60));

        txtPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPersonal.setLabel("PERSONAL");
        txtPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPersonalKeyTyped(evt);
            }
        });
        jPanel1.add(txtPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 230, 60));

        txtEmail.setColumns(20);
        txtEmail.setForeground(new java.awt.Color(0, 112, 192));
        txtEmail.setTabSize(0);
        txtEmail.setToolTipText("INGRESE EL MOTIVO SI ES NECESARIO");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 132, 235)));
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtEmail);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 220, 140));

        rSPanelsSlider1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 300, 340));

        jPanel4.setBackground(new java.awt.Color(65, 94, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setColorText(new java.awt.Color(65, 94, 255));
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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 140, 46));

        btnAgregar1.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Guardar.png"))); // NOI18N
        btnAgregar1.setText("Guardar");
        btnAgregar1.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar1.setColorText(new java.awt.Color(65, 94, 255));
        btnAgregar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar1.setIconTextGap(0);
        btnAgregar1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgregar1FocusGained(evt);
            }
        });
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 140, 46));

        rSPanelsSlider1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 287, 614, 192));

        getContentPane().add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 482));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void btnAgregar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregar1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1FocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCancelarFocusGained

    }//GEN-LAST:event_btnCancelarFocusGained

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased
        txtRazonSocial.setText(txtRazonSocial.getText().toUpperCase());
    }//GEN-LAST:event_txtRazonSocialKeyReleased

    private void txtRazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtRuc.requestFocus();
        }
    }//GEN-LAST:event_txtRazonSocialKeyTyped

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyReleased

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtRuc.getText().length() == 11
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtCuenta.requestFocus();
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtEntidad.requestFocus();
        }
    }//GEN-LAST:event_txtCuentaKeyTyped

    private void txtEntidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtEntidadKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtTelefono.getText().length() == 9
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        txtDireccion.setText(txtDireccion.getText().toUpperCase());  // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalKeyReleased

    private void txtPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalKeyTyped

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed
        FormGestionarTipoProveedor a = new FormGestionarTipoProveedor(null, true);
        a.setVisible(true);
        llenarItemTipoProveedor();
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void txtEntidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadKeyReleased
        txtEntidad.setText(txtEntidad.getText().toUpperCase()); // TODO add your handling code here:
    }//GEN-LAST:event_txtEntidadKeyReleased

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtDireccionKeyPressed
//    private TipoProveedorServicio devolverTipoProveedorServicio(String nombre) throws Exception {
//
//        TipoProveedorServicio tipo = new TipoProveedorServicio();
//        try {
//            tipo = gestionarProveedorServicioServicio.buscarProveedorServicioPorNombreEspecifico(nombre);
//        } catch (Exception e) {
//        }
//
//        return tipo;
//    }
//

    private boolean verificarContadorTextos() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificarContadorNumeros(txtTelefono, lblTelefono, "Telefono", 9);
        aux = aux + contador;
        return aux == 1;
    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtRazonSocial, lblRazonSocial, "Razon Social");
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtRuc, lblRuc, "Ruc");
        aux = aux + contador;
//        contador = Verificador.verificadorCampos(txtCuenta, lblCuenta, "Cuenta");
//        aux = aux + contador;
//        contador = Verificador.verificadorCampos(txtEntidad, lblEntidad, "Entidada");
//        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtPersonal, lblPersonal, "Personal");
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtDireccion, lblDireccion, "Direccion");
        aux = aux + contador;
        contador = Verificador.verificarCombobox(cboTipoProveedor, lblTipoProveedor, "Tipo Proveedor");
        aux = aux + contador;
        return aux == 5;
    }
//    private void calcularFechaPago() {
//        if (cboDiasAPagar.getSelectedIndex() != 0) {
//            Date date = Fechas.sumarDiasAFecha(dateFechaIngreso.getDatoFecha(), Integer.parseInt(cboDiasAPagar.getSelectedItem().toString()));
//            dateFechaPago.setDatoFecha(date);
//        } else {
//            Verificador.pintarColor(lblDiasAPagar, "Día seleccionado ");
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar1;
    private rojeru_san.RSButton btnCancelar;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoProveedor> cboTipoProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblPersonal;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoProveedor;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage14;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private rojerusan.RSPanelsSlider rSPanelsSlider4;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCuenta;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDireccion;
    private javax.swing.JTextArea txtEmail;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtEntidad;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRazonSocial;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRuc;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void guardarDatos() {
        proveedorServicio = new ProveedorServicio();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (verificarCamposVacios()) {
            if (verificarContadorTextos()) {
                try {
                    tipoProveedor = (TipoProveedor) cboTipoProveedor.getItemAt(cboTipoProveedor.getSelectedIndex());
                    proveedorServicio.setRazonSocial(txtRazonSocial.getText());
                    proveedorServicio.setRuc(txtRuc.getText());
                    proveedorServicio.setEntidad(txtEntidad.getText());
                    proveedorServicio.setCuente(txtCuenta.getText());
                    proveedorServicio.setTelefono(txtTelefono.getText());
                    proveedorServicio.setDireccion(txtDireccion.getText());
                    proveedorServicio.setEmail(txtEmail.getText());
                    proveedorServicio.setTipoProveedor(tipoProveedor);
                    proveedorServicio.setPersonal(IniciarSesion.getUsuario());
                    proveedorServicio.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
                    int registros_afectados;
                    if (tipo_accion == ACCION_CREAR) {
                        try {
                            registros_afectados = gestionarProveedorServicioServicio.guardarProveedorServicio(proveedorServicio);
                            if (registros_afectados == 1) {
                                Mensaje.mostrarAfirmacionDeCreacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Proveedor Servicio", DesktopNotify.SUCCESS);
                                this.dispose();
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeCreacion(this);
                            }

                        } catch (Exception e) {
                            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
                        }
                    } else if (tipo_accion == ACCION_MODIFICAR) {
                        try {
                            proveedorServicio.setCodigo(codigoProveedorServicio);
                            registros_afectados = gestionarProveedorServicioServicio.modificarProveedorServicio(proveedorServicio);
                            if (registros_afectados == 1) {
                                Mensaje.mostrarAfirmacionDeActualizacion(this);
                                this.dispose();
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeActualizacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un Proveedor Servicio", DesktopNotify.INPUT_REQUEST);

                            }
                        } catch (Exception e) {
                            Mensaje.mostrarErrorDeActualizacion(this);
                        }
                    }
                } catch (Exception e) {
                    Mensaje.mostrarErrorSistema(this);

                }

            }

        }
    }
}
