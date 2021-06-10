/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarPagoPersonalServicio;
import SistemaLara.capa3_dominio.PagoPersonal;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

import rojerusan.RSAnimation;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarPagoPersonal extends javax.swing.JDialog {

    private GestionarPagoPersonalServicio gestionarPagoPersonalServicio;
    public PagoPersonal pagoSeleccionado;
    int mes, anio;
//    private FormDatosPago formDatosPago;
//    private int x, y;

    public FormGestionarPagoPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        Animacion.moverParaIzquierda(this);
         BarraDesplzamiento() ;
        this.setLocationRelativeTo(null);
        inicializarTabla();
        pagoSeleccionado = new PagoPersonal();
        gestionarPagoPersonalServicio = new GestionarPagoPersonalServicio();
        obtenerDatosParaTabla();
        inabilitarBotones(true);
        inicializadores();
        popMenu.add(pnlMenu);

    }
 public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
     
    }
    private void inicializadores() {
        Calendar c1 = Calendar.getInstance();
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);
    }

    private void limpiarTable() {
        ModeloTabla modelo = (ModeloTabla) tablaPagos.getModel();
        modelo.eliminarTotalFilas();
        tablaPagos.setModel(modelo);
    }

    private void obtenerDatosParaTabla() {
        try {
            limpiarTable();
            gestionarPagoPersonalServicio.mostrarPagosPersonal(tablaPagos);
        } catch (Exception e) {
        }

    }

    private void inicializarTabla() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha de pago", "java.lang.String"));
        tabla.agregarColumna(new Columna("Saldo pagado", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaPagos.setModel(modeloTabla);
        tablaPagos.getColumn(tablaPagos.getColumnName(0)).setWidth(0);
        tablaPagos.getColumn(tablaPagos.getColumnName(0)).setMinWidth(0);
        tablaPagos.getColumn(tablaPagos.getColumnName(0)).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnVerAdelantosPago = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new rojeru_san.RSButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPagos = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarPago = new org.jdesktop.swingx.JXSearchField();
        btnAgregar = new rojeru_san.RSButton();
        btnBuscar = new rojerusan.RSButtonCircle();
        btnCancelar = new rojeru_san.RSButton();

        btnVerAdelantosPago.setBackground(new java.awt.Color(65, 94, 255));
        btnVerAdelantosPago.setBorder(null);
        btnVerAdelantosPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnVerAdelantosPago.setText("VER ADELANTOS DEL PAGO");
        btnVerAdelantosPago.setColorHover(new java.awt.Color(255, 82, 54));
        btnVerAdelantosPago.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVerAdelantosPago.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVerAdelantosPago.setIconTextGap(2);
        btnVerAdelantosPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerAdelantosPagoActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(2);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnVerAdelantosPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnVerAdelantosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

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
        jLabel1.setText("GESTIONAR DATOS DE LA PAGO");

        btnCerrar.setBackground(new java.awt.Color(65, 94, 255));
        btnCerrar.setBorder(null);
        btnCerrar.setText("X");
        btnCerrar.setColorHover(new java.awt.Color(255, 82, 54));
        btnCerrar.setFont(new java.awt.Font("Roboto Bold", 1, 20)); // NOI18N
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPago.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaPagos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPagos.setAltoHead(30);
        tablaPagos.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaPagos.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaPagos.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaPagos.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaPagos.setComponentPopupMenu(popMenu);
        tablaPagos.setGrosorBordeFilas(0);
        tablaPagos.setGrosorBordeHead(0);
        tablaPagos.setRowHeight(20);
        tablaPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaPagosMousePressed(evt);
            }
        });
        tablaPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPagosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaPagosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaPagosKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPagos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarPago.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarPago.setPrompt("BUSCAR POR  DNI , NOMBRE , APELLIDOS");
        txtBuscarPago.setPromptBackround(null);
        txtBuscarPago.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarPago.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarPago.setSelectionEnd(1);
        txtBuscarPago.setSelectionStart(2);
        txtBuscarPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarPagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarPagoKeyTyped(evt);
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

        btnBuscar.setBackground(new java.awt.Color(65, 94, 255));
        btnBuscar.setBorder(null);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/PagoLtra.png"))); // NOI18N
        btnBuscar.setColorBorde(new java.awt.Color(253, 173, 1));
        btnBuscar.setColorHover(new java.awt.Color(253, 173, 1));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setIconTextGap(0);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tablaPagosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMousePressed
        fila = tablaPagos.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaPagosMousePressed

    private void tablaPagosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPagosKeyReleased
        fila = tablaPagos.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaPagosKeyReleased

    private void tablaPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPagosKeyTyped


    }//GEN-LAST:event_tablaPagosKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        FormDatosPagoPersonal f = new FormDatosPagoPersonal(null, true);
        f.setVisible(true);
        obtenerDatosParaTabla();


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPagoKeyPressed
    }//GEN-LAST:event_txtBuscarPagoKeyPressed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPagoKeyTyped
//        String patron_de_entrada = "0123456789";
//        if (txtBuscarPago.getText().length() == 8
//                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtBuscarPagoKeyTyped

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

    private void btnVerAdelantosPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerAdelantosPagoActionPerformed
        try {
         obtenerPagoSeleccionado();
        FormListaDetalleAdelantoPersonal detalle = new FormListaDetalleAdelantoPersonal(null, true, pagoSeleccionado);
        detalle.setVisible(true);
        buscarPorLike();   
        } catch (Exception e) {
             Mensaje.mostrarFilaNoExiste(this);
        }
    
        
    }//GEN-LAST:event_btnVerAdelantosPagoActionPerformed

    private void txtBuscarPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPagoKeyReleased
        buscarPorLike();
    }//GEN-LAST:event_txtBuscarPagoKeyReleased

    public void buscarPorLike() {
        try {
            String texto = txtBuscarPago.getText().toString();
            if (texto.equals("")) {
                obtenerDatosParaTabla();
            } else {
                limpiarTable();
                gestionarPagoPersonalServicio.mostrarGastosExtras(10, tablaPagos, texto);

            }

        } catch (Exception e) {
        }
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaPagosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPagosKeyPressed
        fila = tablaPagos.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tablaPagosKeyPressed

    private void obtenerPagoSeleccionado() {
        try {
            String codigo = tablaPagos.getValueAt(fila, 0).toString();
            pagoSeleccionado = gestionarPagoPersonalServicio.buscarPagoPersonalPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    private void inabilitarBotones(Boolean v) {

        btnAgregar.setEnabled(v);
//        btnEliminar.setEnabled(!v);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojerusan.RSButtonCircle btnBuscar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnCerrar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnVerAdelantosPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojerusan.RSTableMetro tablaPagos;
    private org.jdesktop.swingx.JXSearchField txtBuscarPago;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {
        obtenerPagoSeleccionado();
        if (pagoSeleccionado != null) {
            if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                return;
            }
            try {
                int registros_afectados = gestionarPagoPersonalServicio.eliminarPagoPersonal(pagoSeleccionado);
                if (registros_afectados == 1) {
                    Mensaje.mostrarAfirmacionDeEliminacion(this);
                    buscarPorLike();
                    inabilitarBotones(true);
                } else {
                    Mensaje.mostrarAdvertenciaDeEliminacion(this);
                }

            } catch (Exception e) {
                Mensaje.mostrarErrorDeEliminacion(this);
            }
        } else {
            Mensaje.mostrarErrorSistema(this);
        }
    }
}
