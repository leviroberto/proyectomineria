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
import SistemaLara.capa2_aplicacion.GestionarClienteEntranteServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.TipoCliente;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosClienteEntrante extends javax.swing.JDialog {

    GestionarClienteEntranteServicio gestionarClienteEntranteServicio;
    ClienteEntrante clienteEntrante;

    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoClienteEntrante;

    public FormDatosClienteEntrante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_CREAR;
        gestionarClienteEntranteServicio = new GestionarClienteEntranteServicio();

        llenarItemTipoCliente();
        txtPersonal.setEnabled(false);
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());

    }

    void llenarItemTipoCliente() {
        try {
            cboTipoCliente.removeAllItems();
            gestionarClienteEntranteServicio.llenarCBOTipoCliente(1, cboTipoCliente);

        } catch (Exception e) {
        }
    }

    public FormDatosClienteEntrante(java.awt.Frame parent, boolean modal, ClienteEntrante clienteEntrante) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
        this.clienteEntrante = clienteEntrante;
        gestionarClienteEntranteServicio = new GestionarClienteEntranteServicio();
        llenarItemTipoCliente();
        llenarDatosCampos(this.clienteEntrante);
        verificarCamposVacios();
        verificarContadorTextos();
        txtPersonal.setEnabled(false);
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());

    }

    private void llenarDatosCampos(ClienteEntrante clienteEntrante) {
        codigoClienteEntrante = clienteEntrante.getCodigo();
        txtNombre.setText(clienteEntrante.getNombres());
        txtApellidos.setText(clienteEntrante.getApellidos());
        txtDireccion.setText(clienteEntrante.getDireccion());
        txtTelefono.setText(clienteEntrante.getTelefono());
        txtDni.setText(clienteEntrante.getDni());
        cbxSexo.setSelectedItem(clienteEntrante.getSexo().toString());
        for (int i = 0; i < cboTipoCliente.getItemCount(); i++) {
            if (clienteEntrante.getTipoCliente().getDescripcion().equals(cboTipoCliente.getItemAt(i).getDescripcion())) {
                cboTipoCliente.setSelectedIndex(i);
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
        jLabel3 = new javax.swing.JLabel();
        rSPanelsSlider4 = new rojerusan.RSPanelsSlider();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        lblApellido = new javax.swing.JLabel();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        lblTipoCliente = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        txtNombre = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtApellidos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboTipoCliente = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        txtDni = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel1 = new javax.swing.JPanel();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        lblDireccion = new javax.swing.JLabel();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        lblSexo = new javax.swing.JLabel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        lblTelefono = new javax.swing.JLabel();
        lblPersonal = new javax.swing.JLabel();
        txtTelefono = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cbxSexo = new FiveCodMaterilalDesignComboBox.MaterialComboBox();
        txtDireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL CLIENTE ENTRANTE");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoClienteEntrante.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelsSlider2Layout = new javax.swing.GroupLayout(rSPanelsSlider2);
        rSPanelsSlider2.setLayout(rSPanelsSlider2Layout);
        rSPanelsSlider2Layout.setHorizontalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(0, 584, Short.MAX_VALUE)))
        );
        rSPanelsSlider2Layout.setVerticalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        rSPanelsSlider1.add(rSPanelsSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 614, 33));

        rSPanelsSlider4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowBottomShadow(false);
        rSPanelsSlider4.setBorder(dropShadowBorder1);
        rSPanelsSlider4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 50));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 50));
        rSPanelsSlider4.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 200, 10));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        rSPanelsSlider4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 50, 40));
        rSPanelsSlider4.add(lblTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 192, 200, 10));
        rSPanelsSlider4.add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 261, 210, 10));
        rSPanelsSlider4.add(lblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 210, 10));
        rSPanelsSlider4.add(lblApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 210, 10));

        txtNombre.setForeground(new java.awt.Color(65, 94, 255));
        txtNombre.setAccent(new java.awt.Color(0, 132, 235));
        txtNombre.setCaretColor(new java.awt.Color(0, 132, 235));
        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNombre.setLabel("NOMBRES");
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
        rSPanelsSlider4.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 210, 60));

        cboTipoCliente.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoCliente.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoCliente.setAccent(new java.awt.Color(65, 94, 255));
        rSPanelsSlider4.add(cboTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 180, 40));

        rSButtonIconD1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        rSButtonIconD1.setColorHover(new java.awt.Color(255, 187, 51));
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });
        rSPanelsSlider4.add(rSButtonIconD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 30, 30));

        txtDni.setForeground(new java.awt.Color(65, 94, 255));
        txtDni.setAccent(new java.awt.Color(0, 132, 235));
        txtDni.setCaretColor(new java.awt.Color(0, 132, 235));
        txtDni.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDni.setLabel("DNI");
        txtDni.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        rSPanelsSlider4.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 210, 60));

        rSPanelsSlider1.add(rSPanelsSlider4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 290, 290));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowBottomShadow(false);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowRightShadow(false);
        jPanel1.setBorder(dropShadowBorder2);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Telefono.png"))); // NOI18N
        jPanel1.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 42, 40));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Direccion.png"))); // NOI18N
        jPanel1.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 42, 40));
        jPanel1.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 131, 210, 10));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Genero.png"))); // NOI18N
        jPanel1.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 42, 38));
        jPanel1.add(lblSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 210, 10));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel1.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 40, 40));
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 61, 210, 10));
        jPanel1.add(lblPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 261, 210, 10));

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
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 60));

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
        jPanel1.add(txtPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 210, 60));

        cbxSexo.setBackground(new java.awt.Color(255, 255, 255));
        cbxSexo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cbxSexo.setForeground(new java.awt.Color(65, 94, 255));
        cbxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "M", "F", " " }));
        cbxSexo.setAccent(new java.awt.Color(65, 94, 255));
        jPanel1.add(cbxSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 210, 40));

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
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 210, 60));

        rSPanelsSlider1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 300, 290));

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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 140, 46));

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
        jPanel4.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 140, 46));

        rSPanelsSlider1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 287, 613, 116));

        getContentPane().add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 406));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCancelarFocusGained

    }//GEN-LAST:event_btnCancelarFocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregar1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregar1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1FocusGained

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        clienteEntrante = new ClienteEntrante();

        if (verificarCamposVacios()) {

            if (verificarContadorTextos()) {
                try {
                    TipoCliente tipoCliente = (TipoCliente) cboTipoCliente.getItemAt(cboTipoCliente.getSelectedIndex());
                    clienteEntrante.setNombres(txtNombre.getText());
                    clienteEntrante.setApellidos(txtApellidos.getText());
                    clienteEntrante.setTipoCliente(tipoCliente);
                    clienteEntrante.setDni(txtDni.getText());
                    clienteEntrante.setTelefono(txtTelefono.getText());
                    clienteEntrante.setDireccion(txtDireccion.getText());
                    clienteEntrante.setSexo(cbxSexo.getSelectedItem().toString());
                    clienteEntrante.setPersonal(IniciarSesion.getUsuario());
                    clienteEntrante.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
                    int registros_afectados;
                    if (tipo_accion == ACCION_CREAR) {
                        try {
                            registros_afectados = gestionarClienteEntranteServicio.guardarClienteEntrante(clienteEntrante);
                            if (registros_afectados == 1) {
                                Mensaje.mostrarAfirmacionDeCreacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de Crear un Nuevo Cliente Entrante", DesktopNotify.SUCCESS);
                                this.dispose();
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeCreacion(this);
                            } else if (registros_afectados == 2) {
                                Verificador.pintarColor(lblDni, "DNI");
                                Mensaje.mostrarMensajeDefinido(this, "El DNI " + clienteEntrante.getDni() + " Es de Otra Persona");

                            }

                        } catch (Exception e) {
                            Mensaje.mostrarErrorDeCreacion(this);
                        }
                    } else if (tipo_accion == ACCION_MODIFICAR) {
                        try {
                            clienteEntrante.setCodigo(codigoClienteEntrante);
                            registros_afectados = gestionarClienteEntranteServicio.modificarClienteEntrante(clienteEntrante);
                            if (registros_afectados == 1) {
                                Mensaje.mostrarAfirmacionDeActualizacion(this);
                                
                                this.dispose();
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeActualizacion(this);
                              DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar El Cliente Entrante",DesktopNotify.INPUT_REQUEST);

                            } else if (registros_afectados == 2) {
                                Verificador.pintarColor(lblDni, "DNI");
                                Mensaje.mostrarMensajeDefinido(this, "El DNI " + clienteEntrante.getDni() + " Es de Otra Persona");

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
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

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
        txtApellidos.setText(txtApellidos.getText().toUpperCase());   // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosKeyReleased

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDni.requestFocus();
        }
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed
        FormDatosTipoCliente t = new FormDatosTipoCliente(null, true);
        t.setVisible(true);
        llenarItemTipoCliente();
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtDni.getText().length() == 8
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        String patron_de_entrada = "0123456789";
        if (txtTelefono.getText().length() == 9
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalKeyReleased

    private void txtPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalKeyTyped

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        txtDireccion.setText(txtDireccion.getText().toUpperCase());      // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

//    private TipoClienteEntrante devolverTipoClienteEntrante(String nombre) throws Exception {
//
//        TipoClienteEntrante tipo = new TipoClienteEntrante();
//        try {
//            tipo = gestionarClienteEntranteServicio.buscarClienteEntrantePorNombreEspecifico(nombre);
//        } catch (Exception e) {
//        }
//
//        return tipo;
//    }
    private boolean verificarContadorTextos() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificarContadorNumeros(txtDni, lblDni, "Dni", 8);
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
        contador = Verificador.verificadorCampos(txtDni, lblDni, "Dni");
        aux = aux + contador;
//        contador = Verificador.verificarCombobox(cbxTipoCliente, lblTipoCliente, "Tipo clienteEntrante");
//        aux = aux + contador;
        contador = Verificador.verificarComboboxSexo(cbxSexo, lblSexo, "Sexo");
        aux = aux + contador;
        return aux == 4;
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
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoCliente> cboTipoCliente;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox cbxSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPersonal;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoCliente;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage14;
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
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNombre;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
