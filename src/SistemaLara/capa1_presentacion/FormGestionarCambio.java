package SistemaLara.capa1_presentacion;

import FiveCodLookAndFeelThemeV2.FiveCodMaterialLookAndFeel;
import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarCambioServicio;
import SistemaLara.capa3_dominio.Cambio;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author XGamer
 */
public class FormGestionarCambio extends javax.swing.JDialog {

    private GestionarCambioServicio gestionarCambioServicio;
    Cambio cambio;
    private int ACCION_CREAR = 10;
    private int ACCION_MODIFICAR = 15;
    private int tipo_accion = 0;
    public static int TIPO_VALORIZACION = 2;
    public static int TIPO_ADMINISTRADOR = 3;
    private int tipo_usuario = 0;

    public FormGestionarCambio(java.awt.Frame parent, boolean modal, int tipoUsuario) {

        super(parent, modal);
        try {

            initComponents();
            BarraDesplzamiento();
            this.tipo_usuario = tipoUsuario;

            this.setLocationRelativeTo(null);
            gestionarCambioServicio = new GestionarCambioServicio();
            mostrarDatosenTextos();
            inicializarTablaColumnas();
            inicializarTabla();
            txtDolar.selectAll();
            txtDolar.requestFocus();

        } catch (Exception e) {
        }

    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void mostrarDatosenTextos() throws Exception {
        Cambio cambio = gestionarCambioServicio.obtenerCambio();
        if (cambio != null) {
            tipo_accion = ACCION_MODIFICAR;
            inabilitarBotones(false);
            txtCodigo.setText("" + cambio.getCodigo());
            txtDolar.setText("" + cambio.getDolar());
            txtPoli.setText("" + cambio.getPoli());
            txtTarifaAnalisis.setText("" + cambio.getTarifaa());
            txtTarifaPorcentaje.setText("" + cambio.getTarifa());
            txtToneladasPorCamion.setText("" + cambio.getTonelada());
            txtTransporteNasca.setText("" + cambio.getTrans2());
            txtTransporteTrujillo.setText("" + cambio.getTrans1());
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Debe registrar un cambio en el sistema");
            tipo_accion = ACCION_CREAR;
            txtDolar.requestFocus();
            inabilitarBotones(true);//se habilita campo agregar

        }

//        gestionarCambioServicio.llenarCamposModificar(txtCodigo, txtDolar, txtTarifaAnalisis, txtTarifaPorcentaje, txtTransporteTrujillo, txtTransporteNasca, txtPoli, txtToneladasPorCamion);
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtDolar.setText("");
        txtPoli.setText("");
        txtTarifaAnalisis.setText("");
        txtTarifaPorcentaje.setText("");
        txtToneladasPorCamion.setText("");
        txtTransporteNasca.setText("");
        txtTransporteTrujillo.setText("");

    }

    private void inabilitarBotones(boolean estado) {
        btnActulizar.setVisible(!estado);
        btnAgregar.setVisible(estado);
        btnCancelar.setVisible(!estado);
        btnActulizar.setVisible(!estado);

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("DOLAR", "java.lang.String"));
        tabla.agregarColumna(new Columna("TARIFA ANALISIS", "java.lang.String"));
        tabla.agregarColumna(new Columna("TARIFA PORCENTAJE", "java.lang.String"));
        tabla.agregarColumna(new Columna("TRANSP. A TRUJILLO S/.", "java.lang.String"));
        tabla.agregarColumna(new Columna("TRANSP. A NASCA S/.", "java.lang.String"));
        tabla.agregarColumna(new Columna("POLICIA", "java.lang.String"));
        tabla.agregarColumna(new Columna("TONELADAS CAMION", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableClienteServicio.setModel(modeloTabla);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setMinWidth(0);
        tableClienteServicio.getColumn(tableClienteServicio.getColumnName(0)).setMaxWidth(0);

    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableClienteServicio.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarCambioServicio.mostrarCambio(1, tableClienteServicio);
        } catch (Exception ex) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSButton3 = new rojeru_san.RSButton();
        jPanel8 = new javax.swing.JPanel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        txtCodigo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        txtDolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        txtTarifaAnalisis = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        txtTarifaPorcentaje = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        txtTransporteTrujillo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        txtTransporteNasca = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        txtPoli = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        txtToneladasPorCamion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel9 = new javax.swing.JPanel();
        btnActulizar = new rojeru_san.RSButton();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
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
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255), 2));

        jPanel6.setBackground(new java.awt.Color(65, 94, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("GESTIONAR DATOS DE CAMBIO ");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cambio.png"))); // NOI18N

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
                .addGap(131, 131, 131)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel8.setBorder(dropShadowBorder1);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel8.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        txtCodigo.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigo.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigo.setCaretColor(new java.awt.Color(0, 0, 204));
        txtCodigo.setEnabled(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigo.setLabel("CODIGO ");
        jPanel8.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 60));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dolar.png"))); // NOI18N
        jPanel8.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 40, 30));

        txtDolar.setForeground(new java.awt.Color(0, 0, 204));
        txtDolar.setAccent(new java.awt.Color(204, 0, 51));
        txtDolar.setCaretColor(new java.awt.Color(0, 0, 204));
        txtDolar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDolar.setLabel("DOLAR ");
        txtDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDolarKeyPressed(evt);
            }
        });
        jPanel8.add(txtDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 100, 60));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Tarifa.png"))); // NOI18N
        jPanel8.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 40));

        txtTarifaAnalisis.setForeground(new java.awt.Color(0, 0, 204));
        txtTarifaAnalisis.setAccent(new java.awt.Color(204, 0, 51));
        txtTarifaAnalisis.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTarifaAnalisis.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTarifaAnalisis.setLabel("TARIFA ANALISIS");
        txtTarifaAnalisis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarifaAnalisisKeyPressed(evt);
            }
        });
        jPanel8.add(txtTarifaAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 110, 60));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Porcentaje.png"))); // NOI18N
        jPanel8.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        txtTarifaPorcentaje.setForeground(new java.awt.Color(0, 0, 204));
        txtTarifaPorcentaje.setAccent(new java.awt.Color(204, 0, 51));
        txtTarifaPorcentaje.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTarifaPorcentaje.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTarifaPorcentaje.setLabel("TARIFA PORCENTAJE ");
        txtTarifaPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarifaPorcentajeKeyPressed(evt);
            }
        });
        jPanel8.add(txtTarifaPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 150, 60));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Transporte.png"))); // NOI18N
        jPanel8.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 40, 40));

        txtTransporteTrujillo.setForeground(new java.awt.Color(0, 0, 204));
        txtTransporteTrujillo.setAccent(new java.awt.Color(204, 0, 51));
        txtTransporteTrujillo.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTransporteTrujillo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTransporteTrujillo.setLabel("TRANSP. A TRUJILLO S/.");
        txtTransporteTrujillo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTransporteTrujilloKeyPressed(evt);
            }
        });
        jPanel8.add(txtTransporteTrujillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 150, 60));

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Transporte.png"))); // NOI18N
        jPanel8.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 40, 40));

        txtTransporteNasca.setForeground(new java.awt.Color(0, 0, 204));
        txtTransporteNasca.setAccent(new java.awt.Color(204, 0, 51));
        txtTransporteNasca.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTransporteNasca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTransporteNasca.setLabel("TRANSP. A NASCA S/. ");
        txtTransporteNasca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTransporteNascaKeyPressed(evt);
            }
        });
        jPanel8.add(txtTransporteNasca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 150, 60));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Policia.png"))); // NOI18N
        jPanel8.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 40, 30));

        txtPoli.setForeground(new java.awt.Color(0, 0, 204));
        txtPoli.setAccent(new java.awt.Color(204, 0, 51));
        txtPoli.setCaretColor(new java.awt.Color(0, 0, 204));
        txtPoli.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPoli.setLabel("POLICIA");
        txtPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPoliKeyPressed(evt);
            }
        });
        jPanel8.add(txtPoli, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 150, 60));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Transporte.png"))); // NOI18N
        jPanel8.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 40, 30));

        txtToneladasPorCamion.setForeground(new java.awt.Color(0, 0, 204));
        txtToneladasPorCamion.setAccent(new java.awt.Color(204, 0, 51));
        txtToneladasPorCamion.setCaretColor(new java.awt.Color(0, 0, 204));
        txtToneladasPorCamion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtToneladasPorCamion.setLabel("TONELADAS CAMION");
        txtToneladasPorCamion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtToneladasPorCamionKeyPressed(evt);
            }
        });
        jPanel8.add(txtToneladasPorCamion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 140, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(65, 94, 255));
        jPanel9.setBorder(dropShadowBorder2);

        btnActulizar.setBackground(new java.awt.Color(65, 94, 255));
        btnActulizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ActulizarNuevo.png"))); // NOI18N
        btnActulizar.setText("ACTULIZAR");
        btnActulizar.setColorHover(new java.awt.Color(253, 173, 1));
        btnActulizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnActulizar.setIconTextGap(2);
        btnActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar.setIconTextGap(0);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(btnActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActulizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 170, 140));

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
        tableClienteServicio.setFuenteFilas(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableClienteServicio.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableClienteServicio.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnActulizarActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void tableClienteServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteServicioMouseClicked

    }//GEN-LAST:event_tableClienteServicioMouseClicked

    private void tableClienteServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteServicioMousePressed
        // fila = tableClienteServicio.getSelectedRow();
    }//GEN-LAST:event_tableClienteServicioMousePressed

    private void tableClienteServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClienteServicioKeyReleased
        //   fila = tableClienteServicio.getSelectedRow();
    }//GEN-LAST:event_tableClienteServicioKeyReleased

    private void txtDolarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTarifaAnalisis.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtDolarKeyPressed

    private void txtTarifaAnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarifaAnalisisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTarifaPorcentaje.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtTarifaAnalisisKeyPressed

    private void txtTarifaPorcentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarifaPorcentajeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTransporteTrujillo.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtTarifaPorcentajeKeyPressed

    private void txtTransporteTrujilloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTransporteTrujilloKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTransporteNasca.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtTransporteTrujilloKeyPressed

    private void txtTransporteNascaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTransporteNascaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPoli.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtTransporteNascaKeyPressed

    private void txtPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtToneladasPorCamion.requestFocus();
            if (tipo_usuario == TIPO_VALORIZACION) {
                guardarDatos();
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtPoliKeyPressed

    private void txtToneladasPorCamionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToneladasPorCamionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtToneladasPorCamionKeyPressed

    private void guardarDatos() {
        if (verificarCamposVacios()) {
            cambio = new Cambio();

            cambio.setDolar(txtDolar.getText());
            cambio.setTarifa(txtTarifaPorcentaje.getText());

            cambio.setTarifaa(txtTarifaAnalisis.getText());
            cambio.setTrans1(txtTransporteTrujillo.getText());
            cambio.setTrans2(txtTransporteNasca.getText());
            cambio.setPoli(txtPoli.getText());
            cambio.setTonelada(txtToneladasPorCamion.getText());
            int registros_afectados = 0;
            try {
                if (tipo_accion == ACCION_CREAR) {
                    registros_afectados = gestionarCambioServicio.guardarCambio(cambio);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Cambio", DesktopNotify.SUCCESS);
//                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        inabilitarBotones(false);
                        tipo_accion = ACCION_MODIFICAR;
                        mostrarDatosenTextos();
                        inicializarTabla();
                    } else {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Los Datos No Fueron Modificados, Verifique", DesktopNotify.FAIL);
                    }
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    cambio.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    registros_afectados = gestionarCambioServicio.modificarCambio(cambio);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar El Cambio", DesktopNotify.INPUT_REQUEST);
                        //Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();
                        if (tipo_usuario == TIPO_VALORIZACION) {
                            FormGestionarValorizacionDetalle.mostrarCambio();
                        }

                    } else if (registros_afectados == 0) {
                        // Mensaje.mostrarAdvertenciaDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Los Datos No Fueron Actulizados, Verifique", DesktopNotify.FAIL);
                        if (tipo_usuario == TIPO_VALORIZACION) {
                            FormGestionarValorizacionDetalle.mostrarCambio();
                        }
                    }
                }

            } catch (Exception e) {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        }

    }

    private boolean verificarCamposVacios() {
        int contadr = 0, aux = 0;
        contadr = Verificador.verificadorCampos(txtDolar);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtPoli);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtTarifaAnalisis);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtTarifaPorcentaje);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtToneladasPorCamion);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtTransporteNasca);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtTransporteTrujillo);
        aux = contadr + aux;
        return aux == 7;
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormGestionarCambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormGestionarCambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormGestionarCambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormGestionarCambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FormGestionarCambio dialog = new FormGestionarCambio(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnActulizar;
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private rojeru_san.RSButton rSButton3;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSTableMetro tableClienteServicio;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigo;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDolar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPoli;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTarifaAnalisis;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTarifaPorcentaje;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtToneladasPorCamion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTransporteNasca;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTransporteTrujillo;
    // End of variables declaration//GEN-END:variables
}
