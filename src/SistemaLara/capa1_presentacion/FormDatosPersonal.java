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
import SistemaLara.capa2_aplicacion.GestionarPersonalServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.TipoPersonal;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
//import motorepuestos.capa1_presentacion.util.Animacion;
//import motorepuestos.capa1_presentacion.util.DesktopNotify;
//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarPersonalServicio;
//import motorepuestos.capa3_dominio.TipoPersonal;
//import motorepuestos.capa3_dominio.Personal;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosPersonal extends javax.swing.JDialog {

    GestionarPersonalServicio gestionarPersonalServicio;
    Personal personal;
    private TipoPersonal sad;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoPersonal;

    public FormDatosPersonal(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_CREAR;
        gestionarPersonalServicio = new GestionarPersonalServicio();
        DefaultComboBoxModel modelo = gestionarPersonalServicio.llenarCBOTipoPersonal(1);
        cboTipoPersonal.setModel(modelo);

    }

    public FormDatosPersonal(java.awt.Frame parent, boolean modal, Personal personal) throws Exception {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
        this.personal = personal;
        gestionarPersonalServicio = new GestionarPersonalServicio();
        DefaultComboBoxModel modelo = gestionarPersonalServicio.llenarCBOTipoPersonal(1);
        cboTipoPersonal.setModel(modelo);
        llenarDatosCampos(this.personal);
        verificarCamposVacios();
        verificarContadorTextos();

    }
//    private void llenarFechas() {
//        java.util.Date fecha = new Date();
//    }

    private void llenarDatosCampos(Personal personal) {
        codigoPersonal = personal.getCodigo();
        txtNombre.setText(personal.getNombres());
        txtApellidos.setText(personal.getApellidos());
        txtPassword.setText(personal.getPassword());

        txtDni.setText(personal.getDni());
        txtTelefono.setText(personal.getTelefono());
        txtDireccion.setText(personal.getDireccion());
        txtEmail.setText(personal.getEmail());
        cbxSexo.setSelectedItem(personal.getSexo().toString());
        dateFechaNacimiento.setDatoFecha(personal.getFechaNacimiento());
        for (int i = 0; i < cboTipoPersonal.getItemCount(); i++) {
            if (personal.getTipoPersonal().getDescripcion().equals(cboTipoPersonal.getItemAt(i).getDescripcion())) {
                cboTipoPersonal.setSelectedIndex(i);
            }
        }
    }

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
        lblTipoPersonal = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtNombre = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtApellidos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboTipoPersonal = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        txtDni = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        jPanel1 = new javax.swing.JPanel();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        lblTelefono = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        dateFechaNacimiento = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        lblSexo = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTelefono = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPassword = new SistemaLara.capa1_presentacion.util.FCMaterialPasswordField();
        cbxSexo = new FiveCodMaterilalDesignComboBox.MaterialComboBox();
        jLabel3 = new javax.swing.JLabel();
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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoTipoPersonal.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL PERSONAL");

        javax.swing.GroupLayout rSPanelsSlider2Layout = new javax.swing.GroupLayout(rSPanelsSlider2);
        rSPanelsSlider2.setLayout(rSPanelsSlider2Layout);
        rSPanelsSlider2Layout.setHorizontalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rSPanelsSlider2Layout.setVerticalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        rSPanelsSlider1.add(rSPanelsSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 614, 33));

        rSPanelsSlider4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowBottomShadow(false);
        rSPanelsSlider4.setBorder(dropShadowBorder1);
        rSPanelsSlider4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoPersonal.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 50, 50));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 50));
        rSPanelsSlider4.add(lblNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 10));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 50, 40));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Direccion.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 50, 50));
        rSPanelsSlider4.add(lblTipoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 192, 204, 10));
        rSPanelsSlider4.add(lblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 210, 10));
        rSPanelsSlider4.add(lblApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 210, 10));
        rSPanelsSlider4.add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 210, 10));
        rSPanelsSlider4.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 210, 10));

        txtNombre.setForeground(new java.awt.Color(65, 94, 255));
        txtNombre.setAccent(new java.awt.Color(0, 132, 235));
        txtNombre.setCaretColor(new java.awt.Color(0, 132, 235));
        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.setLabel("NOMBRE");
        txtNombre.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 60));

        txtApellidos.setForeground(new java.awt.Color(65, 94, 255));
        txtApellidos.setAccent(new java.awt.Color(0, 132, 235));
        txtApellidos.setCaretColor(new java.awt.Color(0, 132, 235));
        txtApellidos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtApellidos.setLabel("APELLIDOS");
        txtApellidos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 210, 60));

        cboTipoPersonal.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoPersonal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoPersonal.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoPersonal.setAccent(new java.awt.Color(65, 94, 255));
        rSPanelsSlider4.add(cboTipoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 180, 40));

        txtDni.setForeground(new java.awt.Color(65, 94, 255));
        txtDni.setAccent(new java.awt.Color(0, 132, 235));
        txtDni.setCaretColor(new java.awt.Color(0, 132, 235));
        txtDni.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDni.setLabel("DNI");
        txtDni.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 210, 60));

        txtDireccion.setForeground(new java.awt.Color(65, 94, 255));
        txtDireccion.setAccent(new java.awt.Color(0, 132, 235));
        txtDireccion.setCaretColor(new java.awt.Color(0, 132, 235));
        txtDireccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDireccion.setLabel("DIRECCION");
        txtDireccion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 210, 60));

        rSButtonIconD1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        rSButtonIconD1.setColorHover(new java.awt.Color(255, 187, 51));
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });
        rSPanelsSlider4.add(rSButtonIconD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 30, 30));

        rSPanelsSlider1.add(rSPanelsSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 290, 350));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowBottomShadow(false);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowRightShadow(false);
        jPanel1.setBorder(dropShadowBorder2);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Email.png"))); // NOI18N
        jPanel1.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 42, 40));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Password.png"))); // NOI18N
        jPanel1.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 42, 50));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Genero.png"))); // NOI18N
        jPanel1.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 42, 38));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel1.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 40, 40));
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 218, 10));
        jPanel1.add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 335, 220, 10));

        dateFechaNacimiento.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFechaNacimiento.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFechaNacimiento.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFechaNacimiento.setFormatoFecha("dd/yyyy/MM");
        dateFechaNacimiento.setPlaceholder("FECHA DE NACIMIENTO");
        jPanel1.add(dateFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 220, -1));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Telefono.png"))); // NOI18N
        jPanel1.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 42, 50));
        jPanel1.add(lblSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 218, 10));
        jPanel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 230, 10));
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 218, 10));

        txtEmail.setForeground(new java.awt.Color(65, 94, 255));
        txtEmail.setAccent(new java.awt.Color(0, 132, 235));
        txtEmail.setCaretColor(new java.awt.Color(0, 132, 235));
        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmail.setLabel("EMAIL");
        txtEmail.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 220, 60));

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
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 220, 60));

        txtPassword.setForeground(new java.awt.Color(65, 94, 255));
        txtPassword.setAccent(new java.awt.Color(0, 132, 235));
        txtPassword.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPassword.setLabel("PASSWORD");
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 220, 60));

        cbxSexo.setBackground(new java.awt.Color(255, 255, 255));
        cbxSexo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cbxSexo.setForeground(new java.awt.Color(65, 94, 255));
        cbxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "M", "F" }));
        cbxSexo.setAccent(new java.awt.Color(65, 94, 255));
        jPanel1.add(cbxSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 220, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 94, 255));
        jLabel3.setText("FECHA NACIMIENTO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 220, 20));

        rSPanelsSlider1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 300, 350));

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
        jPanel4.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 140, 46));

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
        personal = new Personal();
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (txtPassword.getText().length() < 8) {
            lblPassword.setText("La contraseña debe ser mayor de 8");
            txtPassword.requestFocus();
        }
        if (verificarCamposVacios()) {

            if (verificarContadorTextos()) {
                try {
                    TipoPersonal tipoPersonal = (TipoPersonal) cboTipoPersonal.getItemAt(cboTipoPersonal.getSelectedIndex());
                    personal.setTipoPersonal(tipoPersonal);
                    personal.setNombres(txtNombre.getText());
                    personal.setApellidos(txtApellidos.getText());
                    personal.setPassword(txtPassword.getText());
                    personal.setDni(txtDni.getText());
                    personal.setTelefono(txtTelefono.getText());
                    personal.setDireccion(txtDireccion.getText());
                    personal.setSexo(cbxSexo.getSelectedItem().toString());
                    personal.setEmail(txtEmail.getText());
                    Date fechaNacimiento = dateFechaNacimiento.getDatoFecha();
                    personal.setFechaNacimiento(new java.sql.Date(fechaNacimiento.getTime()));
                    personal.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
                    int registros_afectados;
                    if (tipo_accion == ACCION_CREAR) {
                        registros_afectados = gestionarPersonalServicio.guardarPersonal(personal);
                        if (registros_afectados == 1) {
                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crearr un Nuevo Personal", DesktopNotify.SUCCESS);
                            this.dispose();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeCreacion(this);
                        } else if (registros_afectados == 2) {
                            Verificador.pintarColor(lblDni, " DNI");
                            Mensaje.mostrarMensajeDefinido(this, "El DNI " + personal.getDni() + " Es de Otra Persona");
                        }
                    } else if (tipo_accion == ACCION_MODIFICAR) {

                        personal.setCodigo(codigoPersonal);
                        registros_afectados = gestionarPersonalServicio.modificarPersonal(personal);
                        if (registros_afectados == 1) {
                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un Perrsonal", DesktopNotify.INPUT_REQUEST);
                            this.dispose();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                        } else if (registros_afectados == 2) {
                            Verificador.pintarColor(lblDni, " DNI");
                            Mensaje.mostrarMensajeDefinido(this, "El DNI " + personal.getDni() + " Es de Otra Persona");

                        }
                    }
                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }

            }

        }
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

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        txtNombre.setText(txtNombre.getText().toUpperCase());
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtApellidos.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyReleased
        txtApellidos.setText(txtApellidos.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosKeyReleased

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDni.requestFocus();
        }
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased
        txtDni.setText(txtDni.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyReleased

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtDni.getText().length() == 8
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        txtDireccion.setText(txtDireccion.getText().toUpperCase());       // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
//       txtEmail.setText(txtEmail.getText().toUpperCase()); 
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyTyped

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

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed
        try {
            FormGestionarTipoPersonal a = new FormGestionarTipoPersonal(null, true);
            a.setVisible(true);
            DefaultComboBoxModel modelo = gestionarPersonalServicio.llenarCBOTipoPersonal(1);
            cboTipoPersonal.setModel(modelo);
        } catch (Exception e) {

        }

    }//GEN-LAST:event_rSButtonIconD1ActionPerformed
//    private TipoPersonal devolverTipoPersonal(String nombre) throws Exception {
//
//        TipoPersonal tipo = new TipoPersonal();
//        try {
//            tipo = gestionarPersonalServicio.buscarPersonalPorNombreEspecifico(nombre);
//        } catch (Exception e) {
//        }
//
//        return tipo;
//    }
//

    private boolean verificarContadorTextos() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificarContadorNumeros(txtDni, lblDni, " DNI", 8);
        aux = aux + contador;
        return aux == 1;
    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtNombre, lblNombres, "Nombre");
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtApellidos, lblApellidos, "Apellidos");
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtDni, lblDni, " DNI");
        aux = aux + contador;
//        contador = Verificador.verificarCombobox(cboTipoPersonal, lblTipoPersonal, "Tipo personal");
//        aux = aux + contador;
        contador = Verificador.verificarComboboxSexo(cbxSexo, lblSexo, "Sexo");
        aux = aux + contador;
//        contador = Verificador.verificadorCampos(txtEmail, lblEmail, "Email");
//        aux = aux + contador;
        contador = Verificador.verificadorCamposFechas(dateFechaNacimiento, lblFechaNacimiento, "Fecha Nacimiento");
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
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoPersonal> cboTipoPersonal;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox cbxSexo;
    private rojeru_san.componentes.RSDateChooser dateFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoPersonal;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage14;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private rojerusan.RSPanelsSlider rSPanelsSlider4;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtApellidos;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDireccion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDni;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtEmail;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNombre;
    private SistemaLara.capa1_presentacion.util.FCMaterialPasswordField txtPassword;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
