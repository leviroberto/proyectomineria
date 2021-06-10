/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import SistemaLara.capa8_timer.TimerTareasDiarias;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosContrato extends javax.swing.JDialog {

    GestionarContratoServicio gestionarContratoServicio;
    Contrato contrato;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;

    public static Personal personalSeleccionado;
    int mes = 0;
    int anio;

    private int codigoContrato;

    public FormDatosContrato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        contrato = new Contrato();
        tipo_accion = ACCION_CREAR;
        gestionarContratoServicio = new GestionarContratoServicio();
        inicializadores();
        inisializadorAgregar();

    }

    public FormDatosContrato(java.awt.Frame parent, boolean modal, Contrato contratos) {
        super(parent, modal);
        try {
            initComponents();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            gestionarContratoServicio = new GestionarContratoServicio();
            personalSeleccionado = new Personal();
            tipo_accion = ACCION_MODIFICAR;
            inicializadores();
            llenarCampos(contratos);
            verificadorCamposVacios();

//            btnPersonal.setEnabled(false);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    void llenarCampos(Contrato contrato) {

        codigoContrato = contrato.getCodigo();
        txtPersonal.setText(contrato.getPersonal().getGenerarNombre());
        txtSueldo.setText("" + contrato.getSueldo());
        txtTotalDiasPagar.setText("" + contrato.getTotalDiasPagar());
        dateFechaIngreso.setDatoFecha(contrato.getFechaIninicioContrato());
        dateFechaFinal.setDatoFecha(contrato.getFechaTerminoContrato());
        personalSeleccionado = contrato.getPersonal();
    }

    void inicializadores() {
        Calendar c1 = Calendar.getInstance();
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);
    }

    private void inisializadorAgregar() {
        java.util.Date fecha = new Date();
        dateFechaFinal.setDatoFecha(fecha);
        dateFechaIngreso.setDatoFecha(fecha);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        dateFechaFinal = new rojeru_san.componentes.RSDateChooser();
        lblPersonal = new javax.swing.JLabel();
        lblTotalDiasPagar = new javax.swing.JLabel();
        lblFechaIncio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        dateFechaIngreso = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        lblSueldo = new javax.swing.JLabel();
        txtPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtSueldo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalDiasPagar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        btnPersonal = new rojerusan.RSButtonIconD();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jLabel3 = new javax.swing.JLabel();

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
        jPanel5.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 50, 50));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel5.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 50, 50));

        dateFechaFinal.setColorBackground(new java.awt.Color(64, 95, 255));
        dateFechaFinal.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dateFechaFinal.setColorForeground(new java.awt.Color(64, 95, 255));
        dateFechaFinal.setPlaceholder("FECHA FIN");
        dateFechaFinal.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel5.add(dateFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 250, 35));
        jPanel5.add(lblPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 200, 10));
        jPanel5.add(lblTotalDiasPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 250, 10));
        jPanel5.add(lblFechaIncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 250, 10));
        jPanel5.add(lblFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 250, 10));

        dateFechaIngreso.setColorBackground(new java.awt.Color(64, 95, 255));
        dateFechaIngreso.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dateFechaIngreso.setColorForeground(new java.awt.Color(64, 95, 255));
        dateFechaIngreso.setPlaceholder("FECHA DE INICIO");
        dateFechaIngreso.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel5.add(dateFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 250, 35));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel5.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 50, 50));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel5.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 50, 50));
        jPanel5.add(lblSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 250, 10));

        txtPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPersonal.setEnabled(false);
        txtPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPersonal.setLabel("NOMBRE TRABAJADOR");
        txtPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel5.add(txtPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 200, 60));

        txtSueldo.setForeground(new java.awt.Color(65, 94, 255));
        txtSueldo.setAccent(new java.awt.Color(0, 132, 235));
        txtSueldo.setCaretColor(new java.awt.Color(0, 132, 235));
        txtSueldo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSueldo.setLabel("SUELDO");
        txtSueldo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtSueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoKeyTyped(evt);
            }
        });
        jPanel5.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 250, 60));

        txtTotalDiasPagar.setForeground(new java.awt.Color(65, 94, 255));
        txtTotalDiasPagar.setAccent(new java.awt.Color(0, 132, 235));
        txtTotalDiasPagar.setCaretColor(new java.awt.Color(0, 132, 235));
        txtTotalDiasPagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalDiasPagar.setLabel("TOTAL DIAS A PAGAR");
        txtTotalDiasPagar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel5.add(txtTotalDiasPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 250, 60));

        btnPersonal.setBackground(new java.awt.Color(255, 255, 255));
        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        btnPersonal.setColorHover(new java.awt.Color(255, 187, 51));
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });
        jPanel5.add(btnPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 40, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(65, 94, 255));
        jLabel4.setText("FECHA DE INICIO");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 250, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(65, 94, 255));
        jLabel5.setText("FECHA FIN");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 250, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 41, 350, 370));

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
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 140, 46));

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
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 140, 46));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 310, 369, 177));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));
        jPanel2.setForeground(new java.awt.Color(255, 68, 68));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL CONTRATO");

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Contrato.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(rSButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 369, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 375, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained
    IniciarDesktop iniciarDesktop = new IniciarDesktop();
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        contrato = new Contrato();
        if (verificadorCamposVacios()) {
            contrato.setPersonal(personalSeleccionado);
            contrato.setSueldo(Double.parseDouble(txtSueldo.getText()));
            contrato.setTotalDiasPagar(Integer.parseInt(txtTotalDiasPagar.getText()));

            Date fechaIngresoContrato = dateFechaIngreso.getDatoFecha();
            contrato.setFechaIninicioContrato(new java.sql.Date(fechaIngresoContrato.getTime()));
            Date fechaFinContrato = dateFechaFinal.getDatoFecha();
            contrato.setFechaTerminoContrato(new java.sql.Date(fechaFinContrato.getTime()));
            contrato.setEstado(new Estado());
            contrato.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
            int registros_afectados;

            if (tipo_accion == ACCION_CREAR) {
                try {
                    registros_afectados = gestionarContratoServicio.guardarContrato(contrato);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod software", "Usted Abaca de Crear Un Nuevo Contrato", DesktopNotify.SUCCESS);
                        this.dispose();
                        DesktopNotify.eliminarWindowsAll();
                        TimerPagosTrabajador.iniciarOtraVes();
                        iniciarDesktop.llenarDescktopPagosContrato(TimerPagosTrabajador.listaContratatoPagosHoy);
                   
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    } else if (registros_afectados == 2) {
                        Mensaje.mostrarMensajeDefinido(this, "La Fecha De Inicio de Contrato es Mayor a la de Final " + personalSeleccionado.getNombres());
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    contrato.setCodigo(codigoContrato);
                    registros_afectados = gestionarContratoServicio.modificarContrato(contrato);
                    if (registros_afectados == 1) {
                        DesktopNotify.eliminarWindowsAll();
                        TimerPagosTrabajador.iniciarOtraVes();
                        
                        iniciarDesktop.llenarDescktopPagosContrato(TimerPagosTrabajador.listaContratatoPagosHoy);
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod software", "Usted Abaca de Modificar el Contrato", DesktopNotify.INPUT_REQUEST);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    } else if (registros_afectados == 2) {
                        Mensaje.mostrarMensajeDefinido(this, "La Fecha De Inicio de Contrato es Mayor a la de Final " + personalSeleccionado.getNombres());
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }


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

    private void txtSueldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTotalDiasPagar.requestFocus();
        }
    }//GEN-LAST:event_txtSueldoKeyTyped

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        Contrato contrato = null;
        txtPersonal.setText("");
        FormGestionarPersonal f = new FormGestionarPersonal(null, true, FormGestionarPersonal.TIPO_CONTRATO);
        f.setVisible(true);
        if (personalSeleccionado != null) {
            try {
                contrato = gestionarContratoServicio.verificarSiTieneContratoPersonal(personalSeleccionado.getCodigo());

                if (!verificarSiTieneContrato(contrato)) {
                    txtPersonal.setText(personalSeleccionado.getNombres());

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }//GEN-LAST:event_btnPersonalActionPerformed

    private boolean verificarSiTieneContrato(Contrato contrato) {
        boolean tieneContrato = false;
        if (contrato != null) {
            if (contrato.getEstado().getCodigo() == 4) {
                Mensaje.mostrarMensajeDefinido(this, "Ya tiene contrato activo este personal de la fecha " + contrato.getFechaIninicioContrato() + " hasta " + contrato.getFechaTerminoContrato());
                tieneContrato = true;
            }
        }

        return tieneContrato;
    }

    private boolean verificadorCamposVacios() {
        int contador = 0, aux = 0;
        try {
            contador = Verificador.verificadorCampos(txtPersonal, lblPersonal, "Personal");
            aux = contador + aux;
            contador = Verificador.verificadorCampos(txtSueldo, lblSueldo, "Sueldo");
            aux = contador + aux;
            contador = Verificador.verificadorCampos(txtTotalDiasPagar, lblTotalDiasPagar, "total dias pago");
            aux = contador + aux;
            contador = Verificador.verificadorCamposFechas(dateFechaFinal, lblFechaFin, "Fecha fin contrato");
            aux = contador + aux;
            contador = Verificador.verificadorCamposFechas(dateFechaIngreso, lblFechaIncio, "Fecha inicio contrato");
            aux = contador + aux;

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

        return aux == 5;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private rojerusan.RSButtonIconD btnPersonal;
    private rojeru_san.componentes.RSDateChooser dateFechaFinal;
    private rojeru_san.componentes.RSDateChooser dateFechaIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaIncio;
    private javax.swing.JLabel lblPersonal;
    private javax.swing.JLabel lblSueldo;
    private javax.swing.JLabel lblTotalDiasPagar;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage8;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSueldo;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalDiasPagar;
    // End of variables declaration//GEN-END:variables
}
