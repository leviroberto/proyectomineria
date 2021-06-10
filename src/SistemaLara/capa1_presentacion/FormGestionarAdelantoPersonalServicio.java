/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoPersonalServicio;
import SistemaLara.capa3_dominio.Contrato;

import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarAdelantoPersonalServicio extends javax.swing.JDialog {

    private GestionarAdelantoPersonalServicio gestionarAdelantoPersonalServicio;
    public static Contrato contratoSeleccionad;

//    private FormDatosAdelantoPersonal formDatosAdelantoPersonal;
    private int x, y;
    int mes = 0;
    int anio = 0;

    public FormGestionarAdelantoPersonalServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        BarraDesplzamiento();
        inicializarTabla();

        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        obtenerDatosTabla();

        inabilitarBotones(true);
        popMenu.add(pnlMenu);

    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());

    }

    private void inicializarTabla() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código Contrato", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ", "java.lang.String"));
//        tabla.agregarColumna(new Columna("Motivo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Monto adelantado ", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Sueldo ", "java.lang.Double"));

        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaAdelantoPersonal.setModel(modeloTabla);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setWidth(0);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setMinWidth(0);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setMaxWidth(0);
    }

    private void limpiarTable() {
        ModeloTabla modelo = (ModeloTabla) tablaAdelantoPersonal.getModel();
        modelo.eliminarTotalFilas();
        tablaAdelantoPersonal.setModel(modelo);
    }

    private void obtenerDatosTabla() {

        try {
            limpiarTable();
            gestionarAdelantoPersonalServicio.mostrarAdelantos(tablaAdelantoPersonal);

        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnVerDetalle = new rojeru_san.RSButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMesAdelanto = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdelantoPersonal = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarAdelantoPersonalTrabajador = new org.jdesktop.swingx.JXSearchField();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        lblMes = new javax.swing.JLabel();
        btnBuscar = new rojerusan.RSButtonCircle();

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setAutoscrolls(true);
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        popMenu.setName("popMenu"); // NOI18N

        pnlMenu.setBackground(new java.awt.Color(255, 0, 0));
        pnlMenu.setForeground(new java.awt.Color(65, 94, 255));
        pnlMenu.setName("pnlMenu"); // NOI18N
        pnlMenu.setOpaque(false);

        btnVerDetalle.setBackground(new java.awt.Color(65, 94, 255));
        btnVerDetalle.setBorder(null);
        btnVerDetalle.setForeground(new java.awt.Color(65, 94, 255));
        btnVerDetalle.setText("VER DETALLE");
        btnVerDetalle.setColorHover(new java.awt.Color(255, 82, 54));
        btnVerDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVerDetalle.setIconTextGap(10);
        btnVerDetalle.setName("btnVerDetalle"); // NOI18N
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255), 2));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIONAR DATOS DEL ADELANTO");

        lblMesAdelanto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblMesAdelanto.setForeground(new java.awt.Color(255, 187, 51));

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton2.setFont(new java.awt.Font("Roboto Bold", 0, 20)); // NOI18N
        rSButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton2ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoAdelanto.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMesAdelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMesAdelanto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaAdelantoPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAdelantoPersonal.setAltoHead(30);
        tablaAdelantoPersonal.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaAdelantoPersonal.setComponentPopupMenu(popMenu);
        tablaAdelantoPersonal.setGrosorBordeFilas(0);
        tablaAdelantoPersonal.setGrosorBordeHead(0);
        tablaAdelantoPersonal.setRowHeight(20);
        tablaAdelantoPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdelantoPersonalMousePressed(evt);
            }
        });
        tablaAdelantoPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaAdelantoPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaAdelantoPersonalKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAdelantoPersonal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarAdelantoPersonalTrabajador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarAdelantoPersonalTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarAdelantoPersonalTrabajador.setPrompt("BUSCAR POR DNI, APELLIDOS, NOMBRE");
        txtBuscarAdelantoPersonalTrabajador.setPromptBackround(null);
        txtBuscarAdelantoPersonalTrabajador.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarAdelantoPersonalTrabajador.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarAdelantoPersonalTrabajador.setSelectionEnd(1);
        txtBuscarAdelantoPersonalTrabajador.setSelectionStart(2);
        txtBuscarAdelantoPersonalTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalTrabajadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalTrabajadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalTrabajadorKeyTyped(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnAgregar.setText("CREAR");
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

        btnBuscar.setBackground(new java.awt.Color(65, 94, 255));
        btnBuscar.setBorder(null);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AdelantoLetra.png"))); // NOI18N
        btnBuscar.setText("A");
        btnBuscar.setColorBorde(new java.awt.Color(253, 173, 1));
        btnBuscar.setColorHover(new java.awt.Color(253, 173, 1));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setIconTextGap(0);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarAdelantoPersonalTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarAdelantoPersonalTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tablaAdelantoPersonalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalMousePressed
        fila = tablaAdelantoPersonal.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaAdelantoPersonalMousePressed

    private void tablaAdelantoPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalKeyReleased
        fila = tablaAdelantoPersonal.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaAdelantoPersonalKeyReleased

    private void tablaAdelantoPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalKeyTyped
        fila = tablaAdelantoPersonal.getSelectedRow();
        inabilitarBotones(false);

    }//GEN-LAST:event_tablaAdelantoPersonalKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        FormDatosAdelantoPersonal f = new FormDatosAdelantoPersonal(null, true);
        f.setVisible(true);
        obtenerDatosTabla();
        inabilitarBotones(true);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            String nombrePersonal = tablaAdelantoPersonal.getValueAt(fila, 4).toString();
            obtenerContratoSeleccionado();
            FormListaDetalleAdelantoPersonal detalle = new FormListaDetalleAdelantoPersonal(null, true, contratoSeleccionad);
            detalle.setVisible(true);
            obtenerDatosTabla();

        } catch (Exception e) {
            Mensaje.mostrarFilaNoExiste(this);
        }

    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void txtBuscarAdelantoPersonalTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalTrabajadorKeyPressed

    }//GEN-LAST:event_txtBuscarAdelantoPersonalTrabajadorKeyPressed

    private void txtBuscarAdelantoPersonalTrabajadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalTrabajadorKeyTyped
//        String patron_de_entrada = "0123456789";
//        if (txtBuscarAdelantoPersonalTrabajador.getText().length() == 8
//                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtBuscarAdelantoPersonalTrabajadorKeyTyped

    private void txtBuscarAdelantoPersonalTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalTrabajadorKeyReleased
        try {
            String texto = txtBuscarAdelantoPersonalTrabajador.getText().toString();
            if (texto.equals("")) {
                obtenerDatosTabla();
            } else {
                limpiarTable();
                gestionarAdelantoPersonalServicio.mostrarAdelantosGrupo(tablaAdelantoPersonal, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarAdelantoPersonalTrabajadorKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //        try {
        //            if (!txtBuscarPago.getText().equals("")) {
        //                listaPago = gestionarPagoPersonalServicio.buscarPagosPorContrato(txtBuscarPago.getText().trim(), mes, anio);
        //                obtenerDatosTabla();
        //            } else {
        //                listaPago = gestionarPagoPersonalServicio.mostrarPagos();
        //
        //                obtenerDatosTabla();
        //            }
        //
        //        } catch (Exception ex) {
        //            Mensaje.mostrarErrorSistema(this);
        //        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void inabilitarBotones(Boolean v) {
        btnAgregar.setEnabled(v);
    }

    public boolean verificarCombobox() {
        int contador = 0, aux = 0;
//        contador = Verificador.verificarCombobox(cboFiltarPorMeses, lblMes, " mes");
//        aux = contador + aux;
        return aux == 0;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojerusan.RSButtonCircle btnBuscar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMesAdelanto;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaAdelantoPersonal;
    private org.jdesktop.swingx.JXSearchField txtBuscarAdelantoPersonalTrabajador;
    // End of variables declaration//GEN-END:variables

    private void obtenerContratoSeleccionado() {
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            contratoSeleccionad = gestionarAdelantoPersonalServicio.buscarContratoSeleccionado(Integer.parseInt(codigo));
        } catch (Exception e) {
        }

    }
}
