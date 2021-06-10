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
import SistemaLara.capa2_aplicacion.GestionarProcedenciaServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Procedencia;
import com.sun.glass.events.KeyEvent;
import javax.swing.JOptionPane;

//import motorepuestos.capa1_presentacion.util.DesktopNotify;
//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarProcedenciaServicio;
//import motorepuestos.capa3_dominio.Procedencia;
/**
 *
 * @author "FiveCod Software"
 */
public class FormDatosProcedencia extends javax.swing.JDialog {

    GestionarProcedenciaServicio gestionarProcedenciaServicio;
    Procedencia procedencia;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoProcedencia;

    public FormDatosProcedencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //  Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);

        tipo_accion = ACCION_CREAR;
        gestionarProcedenciaServicio = new GestionarProcedenciaServicio();
//        codigoProcedencia = "" + gestionarProcedenciaServicio.obtenerUltimoCodigo() + 1;

    }

    public FormDatosProcedencia(java.awt.Frame parent, boolean modal, Procedencia procedencia) throws Exception {
        super(parent, modal);
        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_accion = ACCION_MODIFICAR;
//        this.procedencia = procedencia;
        llenarCampos(procedencia);
        gestionarProcedenciaServicio = new GestionarProcedenciaServicio();
        verificarCamposVacios();
    }

    private void llenarCampos(Procedencia procedencia1) {
        codigoProcedencia = procedencia1.getCodigo();
        txtProcedencia.setText(procedencia1.getDescripcion());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton3 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        lblProcedencia = new javax.swing.JLabel();
        txtProcedencia = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoProcedencia.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DE LA PROCEDENCIA ");

        rSButton3.setBackground(new java.awt.Color(65, 94, 255));
        rSButton3.setBorder(null);
        rSButton3.setText("X");
        rSButton3.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton3.setFont(new java.awt.Font("Roboto Bold", 1, 20)); // NOI18N
        rSButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButton3.addActionListener(new java.awt.event.ActionListener() {
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
            .addComponent(rSButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 320, 33));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Procedencia.png"))); // NOI18N
        jPanel3.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 50));
        jPanel3.add(lblProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 210, 10));

        txtProcedencia.setForeground(new java.awt.Color(65, 94, 255));
        txtProcedencia.setAccent(new java.awt.Color(0, 132, 235));
        txtProcedencia.setCaretColor(new java.awt.Color(0, 132, 235));
        txtProcedencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtProcedencia.setLabel("PROCEDENCIA");
        txtProcedencia.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtProcedencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProcedenciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProcedenciaKeyTyped(evt);
            }
        });
        jPanel3.add(txtProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 210, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 41, 302, 90));

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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 140, 46));

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
        jPanel4.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 46));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 106, 320, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
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


    private void txtProcedenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedenciaKeyTyped

    }//GEN-LAST:event_txtProcedenciaKeyTyped

    private void txtProcedenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedenciaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtProcedenciaKeyPressed
    private boolean verificarCamposVacios() {
        int contadr = 0, aux = 0;
        contadr = Verificador.verificadorCampos(txtProcedencia, lblProcedencia, "Procedencia");
        aux = contadr + aux;
        return aux == 1;
    }

    private void guardarDatos() {
        procedencia = new Procedencia();
        
        
        if (verificarCamposVacios()) {

            procedencia.setPersonal(IniciarSesion.getUsuario());
            procedencia.setDescripcion(txtProcedencia.getText());
            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                    registros_afectados = gestionarProcedenciaServicio.guardarProcedencia(procedencia);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba Crear una Nueva Procedencia", DesktopNotify.SUCCESS);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    } else if (registros_afectados == 2) {
                        Verificador.pintarColor(lblProcedencia, " Procedencia");
                        Mensaje.mostrarMensajeDefinido(this, "La Procedencia ya esta Registrada");
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {

                    procedencia.setCodigo(codigoProcedencia);

                    registros_afectados = gestionarProcedenciaServicio.modificarProcedencia(procedencia);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar Procedencia", DesktopNotify.INPUT_REQUEST);
                        this.dispose();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    } else if (registros_afectados == 2) {
                        Verificador.pintarColor(lblProcedencia, " Procedencia");
                        Mensaje.mostrarMensajeDefinido(this, "La Procedencia ya esta Registrada");
                    }

                } catch (Exception e) {

                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }

    }


    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void txtProcedenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedenciaKeyReleased
        txtProcedencia.setText(txtProcedencia.getText().toUpperCase());
    }//GEN-LAST:event_txtProcedenciaKeyReleased

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblProcedencia;
    private rojeru_san.RSButton rSButton3;
    private rojerusan.RSLabelImage rSLabelImage3;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProcedencia;
    // End of variables declaration//GEN-END:variables

}
