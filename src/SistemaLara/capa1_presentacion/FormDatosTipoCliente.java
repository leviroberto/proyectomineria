/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

//import motorepuestos.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarTipoClienteServicio;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.TipoCliente;
import com.sun.glass.events.KeyEvent;

/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosTipoCliente extends javax.swing.JDialog {

    GestionarTipoClienteServicio gestionarTipoClienteServicio;
    TipoCliente tipoCliente;
    Personal personal;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private Estado estado;
    private int codigoTipoCliente;

    public FormDatosTipoCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        personal = new Personal();
        personal.setCodigo(1);
        tipo_accion = ACCION_CREAR;
        gestionarTipoClienteServicio = new GestionarTipoClienteServicio();
        txtPersonal.setEnabled(false);
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());
//        codigoTipoCliente = "" + gestionarTipoClienteServicio.obtenerUltimoCodigo() + 1;
    }

    public FormDatosTipoCliente(java.awt.Frame parent, boolean modal, TipoCliente tipoCliente) throws Exception {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
        this.tipoCliente = tipoCliente;
        llenarCampos(tipoCliente);
        gestionarTipoClienteServicio = new GestionarTipoClienteServicio();
        verificarCamposVacios();
        txtPersonal.setText(IniciarSesion.getUsuario().getGenerarNombre());
    }

    private void llenarCampos(TipoCliente tipoCliente1) {
        codigoTipoCliente = tipoCliente1.getCodigo();
        txtTipoCliente.setText(tipoCliente1.getDescripcion());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        lblTipocliente = new javax.swing.JLabel();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        lblPersonal = new javax.swing.JLabel();
        txtTipoCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPersonal = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel4 = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 94, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoTipoCliente.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL TIPO CLIENTE ");

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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 320, 33));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel3.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 50));
        jPanel3.add(lblTipocliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 210, 10));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel3.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 50, 50));
        jPanel3.add(lblPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 133, 210, 10));

        txtTipoCliente.setForeground(new java.awt.Color(65, 94, 255));
        txtTipoCliente.setAccent(new java.awt.Color(0, 132, 235));
        txtTipoCliente.setCaretColor(new java.awt.Color(0, 132, 235));
        txtTipoCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTipoCliente.setLabel("TIPO CLIENTE");
        txtTipoCliente.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyReleased(evt);
            }
        });
        jPanel3.add(txtTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 210, 60));

        txtPersonal.setForeground(new java.awt.Color(65, 94, 255));
        txtPersonal.setAccent(new java.awt.Color(0, 132, 235));
        txtPersonal.setCaretColor(new java.awt.Color(0, 132, 235));
        txtPersonal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPersonal.setLabel("PERSONAL");
        txtPersonal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel3.add(txtPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 210, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 41, 302, 150));

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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 104, 140, 46));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Guardar.png"))); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar.setColorText(new java.awt.Color(65, 94, 255));
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
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 104, 140, 46));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 97, 320, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCancelarFocusGained

    }//GEN-LAST:event_btnCancelarFocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        guardarDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void txtTipoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyReleased
        txtTipoCliente.setText(txtTipoCliente.getText().toUpperCase());
    }//GEN-LAST:event_txtTipoClienteKeyReleased

    private void txtTipoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtTipoClienteKeyPressed
    private boolean verificarCamposVacios() {
        int contadr = 0, aux = 0;
        contadr = Verificador.verificadorCampos(txtTipoCliente, lblTipocliente, "Tipo Cliente");
        aux = contadr + aux;
        return aux == 1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblPersonal;
    private javax.swing.JLabel lblTipocliente;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPersonal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTipoCliente;
    // End of variables declaration//GEN-END:variables

    private void guardarDatos() {
        if (verificarCamposVacios()) {
            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                    tipoCliente = new TipoCliente();
                    estado = new Estado();
                    estado.setCodigo(1);
                    tipoCliente.setDescripcion(txtTipoCliente.getText());
                    tipoCliente.setEstado(estado);
                    tipoCliente.setPersonal(IniciarSesion.getUsuario());
                    registros_afectados = gestionarTipoClienteServicio.guardarTipoCliente(tipoCliente);

                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba Crear una Nueva Tipo Cliente", DesktopNotify.SUCCESS);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    } else if (registros_afectados == 2) {

                        Mensaje.mostrarMensajeDefinido(this, "El Tipo Personal ya esta Registrada");
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    estado = new Estado();
                    estado.setCodigo(1);

                    this.tipoCliente.setDescripcion(txtTipoCliente.getText());
                    this.tipoCliente.setEstado(estado);
                    registros_afectados = gestionarTipoClienteServicio.modificarTipoCliente(this.tipoCliente);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba Modificar un Tipo Cliente", DesktopNotify.INPUT_REQUEST);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    } else if (registros_afectados == 2) {
                        Mensaje.mostrarMensajeDefinido(this, "EL Tipo Personal ya esta Registrada");
                    }

                } catch (Exception e) {

                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }
    }
}
