/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarProveedorServicioServicio;
import SistemaLara.capa3_dominio.ProveedorServicio;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarProveedorServicios extends javax.swing.JDialog {

    private GestionarProveedorServicioServicio gestionarProveedorServicioServicio;
    private List<ProveedorServicio> listaProveedorServicio = new ArrayList<ProveedorServicio>();
    public static ProveedorServicio proveedorServicioSeleccionado;
    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_PAGO_TRANSPORTE = 2;
    public static int TIPO_FACTURA = 5;
    public static int TIPO_NOTADEBITO = 7;
    public static int TIPO_CHEQUE = 6;
    public static int TIPO_NOTA_CREDITO = 8;
        public static int TIPO_CORREO_FACTURA = 9;


    private int TIPO_USUARIO;

    public FormGestionarProveedorServicios(java.awt.Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            TIPO_USUARIO = tipo;
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
//            this.TIPO_USUARIO = tipo;
            proveedorServicioSeleccionado = new ProveedorServicio();
            gestionarProveedorServicioServicio = new GestionarProveedorServicioServicio();
            inicializarTablaColumnas();
            inicializarTabla();
//            obtenerDatosTabla();
            inabilitarBotones(true);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("RAZON SOCIAL", "java.lang.String"));
        tabla.agregarColumna(new Columna("RUC", "java.lang.String"));
        tabla.agregarColumna(new Columna("ENTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("CUENTA", "java.lang.String"));
        tabla.agregarColumna(new Columna("TELEFONO", "java.lang.String"));
        tabla.agregarColumna(new Columna("DIRECCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("EMAIL", "java.lang.String"));
        tabla.agregarColumna(new Columna("PERSONAL", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableProveedorServicio.setModel(modeloTabla);

        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setWidth(0);
        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMinWidth(0);
        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(0)).setMaxWidth(0);

        TableColumnModel columnModel = tableProveedorServicio.getColumnModel();

        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(80);
        tableProveedorServicio.setColumnModel(columnModel);
//        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(1)).setPreferredWidth(40);
//        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(2)).setPreferredWidth(10);
//        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(3)).setPreferredWidth(10);
//        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(4)).setPreferredWidth(10);
//        tableProveedorServicio.getColumn(tableProveedorServicio.getColumnName(5)).setPreferredWidth(10);
//        tableProveedorServicio.getColumn(3).setPreferredWidth(15);
//        tableProveedorServicio.getColumn(4).setPreferredWidth(10);
//        tableProveedorServicio.getColumn(5).setPreferredWidth(10);
    }

    public void limpiarTabla() {
        ModeloTabla modelos = (ModeloTabla) tableProveedorServicio.getModel();
        modelos.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarProveedorServicioServicio.mostrarProveedorServicios(1, tableProveedorServicio);

        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnModificar = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProveedorServicio = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarProveedorServicios = new org.jdesktop.swingx.JXSearchField();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(2);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("GESTIONAR DATOS PROVEEDOR SERVICIO ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoProveedorServicios.png"))); // NOI18N

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableProveedorServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProveedorServicio.setAltoHead(30);
        tableProveedorServicio.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableProveedorServicio.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableProveedorServicio.setComponentPopupMenu(popMenu);
        tableProveedorServicio.setGrosorBordeFilas(0);
        tableProveedorServicio.setGrosorBordeHead(0);
        tableProveedorServicio.setRowHeight(20);
        tableProveedorServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableProveedorServicioMousePressed(evt);
            }
        });
        tableProveedorServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProveedorServicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableProveedorServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableProveedorServicioKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableProveedorServicio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarProveedorServicios.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarProveedorServicios.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarProveedorServicios.setPrompt("BUSCAR POR RAZON SOCIAL O RUC");
        txtBuscarProveedorServicios.setPromptBackround(null);
        txtBuscarProveedorServicios.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarProveedorServicios.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarProveedorServicios.setSelectionEnd(1);
        txtBuscarProveedorServicios.setSelectionStart(2);
        txtBuscarProveedorServicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorServiciosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProveedorServiciosKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarProveedorServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarProveedorServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerDatosParaTabla() throws Exception {
//        listaProveedorServicio = gestionarProveedorServicioServicio.mostrarProveedorServicios(ProveedorServicio.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tableProveedorServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedorServicioMousePressed
        try {
            fila = tableProveedorServicio.getSelectedRow();
            inabilitarBotones(false);
            if (TIPO_USUARIO == TIPO_PAGO_TRANSPORTE) {
                obtenerProveedorServicio();
            } else if (TIPO_USUARIO == TIPO_FACTURA) {
                obtenerProveedorServicio();
            } else if (TIPO_USUARIO == TIPO_CHEQUE) {
                obtenerProveedorServicio();

            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_tableProveedorServicioMousePressed

    private void tableProveedorServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProveedorServicioKeyReleased
        fila = tableProveedorServicio.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableProveedorServicioKeyReleased

    private void tableProveedorServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProveedorServicioKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                if (TIPO_USUARIO == TIPO_PAGO_TRANSPORTE) {
                    obtenerProveedorServicio();
                } else if (TIPO_USUARIO == TIPO_FACTURA) {
                    obtenerProveedorServicio();

                }
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_tableProveedorServicioKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        FormDatosProveedorServicios datosProveedorServicio = new FormDatosProveedorServicios(null, true);
        datosProveedorServicio.setVisible(true);
        inicializarTabla();
        proveedorServicioSeleccionado = null;
        inabilitarBotones(true);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarProveedorServiciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorServiciosKeyPressed
//        try {
//            if (!txtBuscarProveedorServicio.getText().equals("")) {
//                listaProveedorServicio = null;
//                listaProveedorServicio = gestionarProveedorServicioServicio.buscarProveedorServicioPorNombre(txtBuscarProveedorServicio.getText().trim());
//                obtenerDatosTabla();
//            } else {
//                listaProveedorServicio = null;
//                obtenerDatosParaTabla();
//                txtBuscarProveedorServicio.setText("");
//            }
//
//        } catch (Exception ex) {
//            Mensaje.mostrarErrorDeConsulta(this);
//        }

    }//GEN-LAST:event_txtBuscarProveedorServiciosKeyPressed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            obtenerProveedorServicioSeleccionado();
            FormDatosProveedorServicios proveedorServicio = new FormDatosProveedorServicios(null, true, proveedorServicioSeleccionado);
            proveedorServicio.setVisible(true);
            inicializarTabla();
            proveedorServicioSeleccionado = null;
            inabilitarBotones(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoExiste(this);
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarProveedorServiciosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProveedorServiciosKeyReleased
        try {
            String texto = txtBuscarProveedorServicios.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarProveedorServicioServicio.mostrarProveedorServicios(1, tableProveedorServicio, texto);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarProveedorServiciosKeyReleased

    private void tableProveedorServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProveedorServicioKeyPressed
        fila = tableProveedorServicio.getSelectedRow();
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableProveedorServicioKeyPressed

    private void obtenerProveedorServicioSeleccionado() {
        try {
            String codigo = tableProveedorServicio.getValueAt(fila, 0).toString();
            proveedorServicioSeleccionado = gestionarProveedorServicioServicio.buscarProveedorServicioPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void inabilitarBotones(Boolean v) {
        btnAgregar.setEnabled(v);
        btnEliminar.setEnabled(!v);
        btnModificar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tableProveedorServicio;
    private org.jdesktop.swingx.JXSearchField txtBuscarProveedorServicios;
    // End of variables declaration//GEN-END:variables

    private void obtenerProveedorServicio() {
        String codigo = tableProveedorServicio.getValueAt(fila, 0).toString();
        try {
            proveedorServicioSeleccionado = gestionarProveedorServicioServicio.buscarProveedorServicioPorCodigo(Integer.parseInt(codigo));
            if (TIPO_USUARIO == TIPO_PAGO_TRANSPORTE) {
                FormGestionarPagoTransporte.proveedorServicioSeleccionado = proveedorServicioSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_FACTURA) {
                FormRegistroFactura.proveedorServicioSeleccionado = proveedorServicioSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_CHEQUE) {
                FormGestionarChequeProveedorServicio.proveedorServicioSeleccionado = proveedorServicioSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_NOTA_CREDITO) {
                FormRegistroNotaCredito.proveedorServicioSeleccionado = proveedorServicioSeleccionado;
                this.dispose();
            } else {
                inabilitarBotones(false);
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void metodoParaEliminar() {
        try {
            obtenerProveedorServicioSeleccionado();
            if (proveedorServicioSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    int registros_afectados = gestionarProveedorServicioServicio.eliminarProveedorServicio(proveedorServicioSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        inicializarTabla();
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
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

}
