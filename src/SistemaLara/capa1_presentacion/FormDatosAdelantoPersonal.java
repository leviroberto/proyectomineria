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
import SistemaLara.capa2_aplicacion.GestionarAdelantoPersonalServicio;
import SistemaLara.capa3_dominio.AdelantoPersonal;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.PagoPersonal;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosAdelantoPersonal extends javax.swing.JDialog {

    private Contrato contrato;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private AdelantoPersonal adelantoPersonal;
    private int codigoAdelantoPersonal;
    public static Contrato contratoSeleccionado;
    int mes, anio;
    private GestionarAdelantoPersonalServicio gestionarAdelantoPersonalServicio;

    public FormDatosAdelantoPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        adelantoPersonal = new AdelantoPersonal();
        contratoSeleccionado = new Contrato();
        tipo_accion = ACCION_CREAR;
        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        llenarFechas();
        inicializadores();
        inabilitarTextos();
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
    }

    void inabilitarTextos() {
        txtMontoTotalAdelantado.setEnabled(false);
        txtTrabajador.setEnabled(false);
        txtSaldoActual.setEnabled(false);
        txtSueldoPersonal.setEnabled(false);
    }

    private void llenarFechas() {
        java.util.Date fecha = new Date();
        datefechaAdelantoPersonal.setDatoFecha(fecha);
    }

    private void inicializadores() {
        Calendar c1 = Calendar.getInstance();
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);
    }

    public FormDatosAdelantoPersonal(java.awt.Frame parent, boolean modal, AdelantoPersonal adelanto) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        adelantoPersonal = adelanto;
        contratoSeleccionado = adelantoPersonal.getContrato();
        tipo_accion = ACCION_MODIFICAR;
        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        llenarCampos(adelantoPersonal);
        btnTrabajador.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnCalcular.setEnabled(true);
        inicializadores();
        inabilitarTextos();
        if (adelantoPersonal.getEstado().getCodigo() == 7) {
            txtMontoAdelantoPersonal.setEnabled(false);
            btnCalcular.setEnabled(false);
            btnAgregar.setEnabled(false);
            txtMotivo.setEnabled(false);
            datefechaAdelantoPersonal.setEnabled(false);
        }
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
    }

    private void llenarCampos(AdelantoPersonal adelantoPersonal) {
        try {
            Double totalSolesAdelanto = gestionarAdelantoPersonalServicio.obtenerTotalSolesAdelantoPorPersonal(contratoSeleccionado.getCodigo());

            codigoAdelantoPersonal = adelantoPersonal.getCodigo();
            txtTrabajador.setText(adelantoPersonal.getContrato().getPersonal().getGenerarNombre());
            txtMotivo.setText(adelantoPersonal.getMotivo());
            datefechaAdelantoPersonal.setDatoFecha(adelantoPersonal.getFecha());
            txtMontoAdelantoPersonal.setText("" + adelantoPersonal.getMontoAdelanto());
            txtSueldoPersonal.setText("" + adelantoPersonal.getContrato().getSueldo());
            txtMontoTotalAdelantado.setText("" + totalSolesAdelanto);
            Double saldoActual = (Double.parseDouble(txtSueldoPersonal.getText()) - Double.parseDouble(txtMontoTotalAdelantado.getText()));
            txtSaldoActual.setText("" + saldoActual);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new rojeru_san.RSButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        jPanel1 = new javax.swing.JPanel();
        btnCalcular = new rojeru_san.RSButton();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        lblMontoAdalantado = new javax.swing.JLabel();
        lblSueldoActual = new javax.swing.JLabel();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        txtMontoTotalAdelantado = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtSueldoPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtMontoAdelantoPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtSaldoActual = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblBuscarTrabajador = new javax.swing.JLabel();
        datefechaAdelantoPersonal = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        lblFechaAdalantado = new javax.swing.JLabel();
        btnTrabajador = new rojerusan.RSButtonIconD();
        txtTrabajador = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnCancelar = new rojeru_san.RSButton();
        btnAgregar = new rojeru_san.RSButton();

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

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(68, 138, 255)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(65, 94, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATOS DE PAGOS ADALANTADOS ");

        btnCerrar.setBackground(new java.awt.Color(65, 94, 255));
        btnCerrar.setBorder(null);
        btnCerrar.setForeground(new java.awt.Color(0, 0, 0));
        btnCerrar.setText("X");
        btnCerrar.setColorHover(new java.awt.Color(255, 68, 68));
        btnCerrar.setFont(new java.awt.Font("Roboto Bold", 0, 20)); // NOI18N
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPrestamo.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 667, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(15);
        dropShadowBorder1.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel11.setBorder(dropShadowBorder1);
        jPanel11.setPreferredSize(new java.awt.Dimension(392, 440));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel11.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 39));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel11.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 43, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowBottomShadow(false);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowRightShadow(false);
        jPanel1.setBorder(dropShadowBorder2);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCalcular.setBackground(new java.awt.Color(204, 204, 204));
        btnCalcular.setBorder(null);
        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Calcular.png"))); // NOI18N
        btnCalcular.setText("Calcular");
        btnCalcular.setColorHover(new java.awt.Color(253, 173, 1));
        btnCalcular.setColorText(new java.awt.Color(68, 138, 255));
        btnCalcular.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCalcular.setIconTextGap(0);
        btnCalcular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnCalcularFocusGained(evt);
            }
        });
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, -1));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel1.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 40, 40));
        jPanel1.add(lblMontoAdalantado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 230, 10));
        jPanel1.add(lblSueldoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 200, 10));

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel1.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 40, 40));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel1.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 40));

        txtMontoTotalAdelantado.setForeground(new java.awt.Color(65, 94, 255));
        txtMontoTotalAdelantado.setAccent(new java.awt.Color(0, 132, 235));
        txtMontoTotalAdelantado.setCaretColor(new java.awt.Color(0, 132, 235));
        txtMontoTotalAdelantado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMontoTotalAdelantado.setLabel("TOTAL DINERO ADELANTDADO");
        txtMontoTotalAdelantado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(txtMontoTotalAdelantado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 60));

        txtSueldoPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtSueldoPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtSueldoPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtSueldoPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSueldoPersonal.setLabel("SUELDO");
        txtSueldoPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(txtSueldoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 210, 60));

        txtMontoAdelantoPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtMontoAdelantoPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtMontoAdelantoPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtMontoAdelantoPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMontoAdelantoPersonal.setLabel("MONTO DE ADELANTO");
        txtMontoAdelantoPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtMontoAdelantoPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoAdelantoPersonalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoAdelantoPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoAdelantoPersonalKeyTyped(evt);
            }
        });
        jPanel1.add(txtMontoAdelantoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 230, 60));

        txtSaldoActual.setForeground(new java.awt.Color(65, 94, 255));
        txtSaldoActual.setAccent(new java.awt.Color(0, 132, 235));
        txtSaldoActual.setCaretColor(new java.awt.Color(0, 132, 235));
        txtSaldoActual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSaldoActual.setLabel("SUELDO ACTUAL");
        txtSaldoActual.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(txtSaldoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 190, 60));

        jPanel11.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 300, 350));

        txtMotivo.setColumns(20);
        txtMotivo.setForeground(new java.awt.Color(0, 112, 192));
        txtMotivo.setTabSize(0);
        txtMotivo.setToolTipText("INGRESE EL MOTIVO SI ES NECESARIO");
        txtMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 132, 235)));
        txtMotivo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtMotivo);

        jPanel11.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 230, 180));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(65, 94, 255));
        jLabel2.setText("MOTIVO");
        jPanel11.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 50, -1));
        jPanel11.add(lblBuscarTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 220, 10));

        datefechaAdelantoPersonal.setColorBackground(new java.awt.Color(64, 95, 255));
        datefechaAdelantoPersonal.setColorButtonHover(new java.awt.Color(64, 95, 255));
        datefechaAdelantoPersonal.setColorForeground(new java.awt.Color(64, 95, 255));
        datefechaAdelantoPersonal.setFormatoFecha("dd/MM/yyyy");
        datefechaAdelantoPersonal.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        datefechaAdelantoPersonal.setPlaceholder("FECHA DE ADELANTO");
        datefechaAdelantoPersonal.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel11.add(datefechaAdelantoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 230, 30));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel11.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 44, 40));
        jPanel11.add(lblFechaAdalantado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 354, 230, 10));

        btnTrabajador.setBackground(new java.awt.Color(255, 255, 255));
        btnTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        btnTrabajador.setColorHover(new java.awt.Color(255, 187, 51));
        btnTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadorActionPerformed(evt);
            }
        });
        jPanel11.add(btnTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 30, 30));

        txtTrabajador.setForeground(new java.awt.Color(65, 94, 255));
        txtTrabajador.setAccent(new java.awt.Color(0, 132, 235));
        txtTrabajador.setCaretColor(new java.awt.Color(0, 132, 235));
        txtTrabajador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTrabajador.setLabel("SELECCIONAR TRABAJADOR");
        txtTrabajador.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTrabajadorKeyReleased(evt);
            }
        });
        jPanel11.add(txtTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 230, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 94, 255));
        jLabel3.setText("FECHA DEL ADELANTO");
        jPanel11.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 230, -1));

        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, 650, 370));

        jPanel12.setBackground(new java.awt.Color(65, 94, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setColorText(new java.awt.Color(68, 138, 255));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel12.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 150, 46));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Guardar.png"))); // NOI18N
        btnAgregar.setText("Adelantar");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar.setColorText(new java.awt.Color(68, 138, 255));
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
        jPanel12.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 150, 46));

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 372, 669, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCalcularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCalcularFocusGained
    }//GEN-LAST:event_btnCalcularFocusGained

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        calcularMontoActual();
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void calcularMontoActual() {
        try {
            verificadorCamposVacios();
            if (tipo_accion == ACCION_CREAR) {
                calcularAgregarAdelantoPersonal();
            } else {
                calcularModificarAdelantoPersonal();
            }

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    private boolean calcularModificarAdelantoPersonal() throws NumberFormatException {
        boolean resultado = false;

        try {

            numeros = 0.0;
            if (txtMontoAdelantoPersonal.getText().length() != 0) {
                double adelantoPersonalTotal = (Double.parseDouble(txtMontoTotalAdelantado.getText()) - adelantoPersonal.getMontoAdelanto());
                numeros = (Double.parseDouble(txtMontoAdelantoPersonal.getText()) + adelantoPersonalTotal);
                if (adelantoPersonal.verificarMonto(numeros)) {
                    resultado = true;
                    txtSaldoActual.setText("" + adelantoPersonal.calcularSueldoActual(numeros));
                } else {
                    resultado = false;
                    Verificador.pintarColor(lblMontoAdalantado, "adelantoPersonal no disponible");
                }
//                double sumaAdelantoPersonal = (Double.parseDouble(txtMontoTotalAdelantado.getText()) + adelantoPersonalTotal);
//                txtMontoTotalAdelantado.setText("" + sumaAdelantoPersonal);
                numeros = 0.0;
            } else {
                Verificador.pintarColor(lblSueldoActual, " calcular");
            }

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
        return resultado;

    }

    private boolean calcularAgregarAdelantoPersonal() throws NumberFormatException {
        numeros = 0.0;
        boolean resultado = false;
        if (txtMontoAdelantoPersonal.getText().length() != 0) {
            numeros = (Double.parseDouble(txtMontoAdelantoPersonal.getText()) + Double.parseDouble(txtMontoTotalAdelantado.getText()));
            if (adelantoPersonal.verificarMonto(numeros)) {
                resultado = true;
                txtSaldoActual.setText("" + adelantoPersonal.calcularSueldoActual(numeros));
            } else {
                resultado = false;
                Verificador.pintarColor(lblMontoAdalantado, "adelantoPersonal no disponible");
            }
            numeros = 0.0;
        } else {
            Verificador.pintarColor(lblSueldoActual, " calcular");
        }
        return resultado;
    }
    Double numeros;
    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        verificarDatosParaGuardar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void btnTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadorActionPerformed
        try {
            txtSaldoActual.setText("");
            FormGestionarContrato f = new FormGestionarContrato(null, true, FormGestionarContrato.TIPO_ADELANTO);
            f.setVisible(true);
            if (contratoSeleccionado != null) {
                Double totalSolesAdelanto = gestionarAdelantoPersonalServicio.obtenerTotalSolesAdelantoPorPersonal(contratoSeleccionado.getCodigo());
                //para verificar si se ha pagado ya al pata             
                adelantoPersonal.setContrato(contratoSeleccionado);
                txtSueldoPersonal.setText("" + contratoSeleccionado.getSueldo());
                txtTrabajador.setText(contratoSeleccionado.getPersonal().getGenerarNombre());
                txtMontoTotalAdelantado.setText("" + totalSolesAdelanto);
                Double saldoActual = (Double.parseDouble(txtSueldoPersonal.getText()) - Double.parseDouble(txtMontoTotalAdelantado.getText()));
                txtSaldoActual.setText("" + saldoActual);

            }

        } catch (Exception e) {
//            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
        }
    }//GEN-LAST:event_btnTrabajadorActionPerformed

    private void txtTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrabajadorKeyReleased

    }//GEN-LAST:event_txtTrabajadorKeyReleased

    private void txtMontoAdelantoPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAdelantoPersonalKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtMontoAdelantoPersonal.getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_txtMontoAdelantoPersonalKeyTyped

    private void txtMontoAdelantoPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAdelantoPersonalKeyReleased
//      calcularMontoActual();
    }//GEN-LAST:event_txtMontoAdelantoPersonalKeyReleased

    private void txtMontoAdelantoPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAdelantoPersonalKeyPressed
       if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
           guardarDatos();
        }
    }//GEN-LAST:event_txtMontoAdelantoPersonalKeyPressed

    double buscarAdelantoPersonalAuxiliar(Contrato contrato) {
//        try {
//            return gestionarAdelantoPersonalServicio.buscarAdelantoPersonalTotalPorContrato(contrato.getCodigo(), mes, AdelantoPersonal.ESTADO_POR_PAGAR);
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }
        return 0;
    }

    private void verificarDatosParaGuardar() {
        if (tipo_accion == ACCION_CREAR) {
            if (calcularAgregarAdelantoPersonal()) {
                guardarDatos();
            }
        } else if (calcularModificarAdelantoPersonal()) {
            guardarDatos();
        }
    }

    private void guardarDatos() {
        adelantoPersonal = new AdelantoPersonal();
        if (verificadorCamposVacios()) {
            adelantoPersonal.setContrato(contratoSeleccionado);
            adelantoPersonal.setMotivo(txtMotivo.getText());
            adelantoPersonal.setMontoAdelanto(Double.parseDouble(txtMontoAdelantoPersonal.getText()));
            Date fechaAdelantoPersonal = datefechaAdelantoPersonal.getDatoFecha();
            adelantoPersonal.setFecha(new java.sql.Date(fechaAdelantoPersonal.getTime()));
            adelantoPersonal.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                    registros_afectados = gestionarAdelantoPersonalServicio.guardarAdelantoPersonal(adelantoPersonal);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba Crear una Nuevo Adelanto Personal",DesktopNotify.SUCCESS);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    adelantoPersonal.setCodigo(codigoAdelantoPersonal);
                    registros_afectados = gestionarAdelantoPersonalServicio.modificarAdelantoPersonal(adelantoPersonal);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
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

    private boolean verificadorCamposVacios() {
        int contador = 0, aux = 0;
        try {
            contador = Verificador.verificadorCampos(txtTrabajador, lblBuscarTrabajador, " Seleccionar Trabajador");
            aux = contador + aux;
            contador = Verificador.verificadorCampos(txtMontoAdelantoPersonal, lblMontoAdalantado, " Monto de adelantoPersonal");
            aux = contador + aux;
            contador = Verificador.verificadorCamposFechas(datefechaAdelantoPersonal, lblFechaAdalantado, "Fecha de adelantoPersonal");
            aux = contador + aux;

//            contador = Verificador.verificarCombobox(cboEstado, lblEstado, "Estado");
//            aux = contador + aux;
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

        return aux == 3;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCalcular;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnCerrar;
    private rojerusan.RSButtonIconD btnTrabajador;
    private rojeru_san.componentes.RSDateChooser datefechaAdelantoPersonal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarTrabajador;
    private javax.swing.JLabel lblFechaAdalantado;
    private javax.swing.JLabel lblMontoAdalantado;
    private javax.swing.JLabel lblSueldoActual;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMontoAdelantoPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMontoTotalAdelantado;
    private javax.swing.JTextArea txtMotivo;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSaldoActual;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSueldoPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTrabajador;
    // End of variables declaration//GEN-END:variables
}
