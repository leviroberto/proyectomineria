/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarClienteEntranteServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarClienteEntranteServicio;
//import motorepuestos.capa3_dominio.ClienteEntrante;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarClienteEntrante extends javax.swing.JDialog {

    private GestionarClienteEntranteServicio gestionarClienteEntranteServicio;
    private List<ClienteEntrante> listaClienteEntrante = new ArrayList<ClienteEntrante>();
    public static ClienteEntrante clienteEntranteSeleccionado;
    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_LIQUIDACION = 2;
    public static int TIPO_GASTOS_EXTRAS = 3;
    public static int TIPO_VALORIZACION_PRINCIPAL = 4;
    public static int TIPO_CHEQUECLIENTE = 5;
    public static int TIPO_LIQUIDACION_REPORTE = 6;
    public static int TIPO_ADELANTO_CLIENTE_REPORTE = 7;

    private int TIPO_USUARIO;
    DefaultTableModel modelo;

    public FormGestionarClienteEntrante(java.awt.Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            TIPO_USUARIO = tipo;
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
//            this.TIPO_USUARIO = tipo;
            clienteEntranteSeleccionado = new ClienteEntrante();
            gestionarClienteEntranteServicio = new GestionarClienteEntranteServicio();
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
        tabla.agregarColumna(new Columna("DNI", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("TELEFONO", "java.lang.String"));
        tabla.agregarColumna(new Columna("SEXO", "java.lang.String"));
        tabla.agregarColumna(new Columna("PERSONAL", "java.lang.String"));
        tabla.agregarColumna(new Columna("TIPO CLIENTE", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableClienteEntrante.setModel(modeloTabla);
        tableClienteEntrante.getColumn(tableClienteEntrante.getColumnName(0)).setWidth(0);
        tableClienteEntrante.getColumn(tableClienteEntrante.getColumnName(0)).setMinWidth(0);
        tableClienteEntrante.getColumn(tableClienteEntrante.getColumnName(0)).setMaxWidth(0);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableClienteEntrante.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarClienteEntranteServicio.mostrarClienteEntrantes(1, tableClienteEntrante);
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
        tableClienteEntrante = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
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
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIONAR DATOS DEL CLIENTE ENTRANTE ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoClienteEntrante.png"))); // NOI18N

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
                .addGap(92, 92, 92)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 535, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableClienteEntrante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableClienteEntrante.setAltoHead(30);
        tableClienteEntrante.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableClienteEntrante.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableClienteEntrante.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableClienteEntrante.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableClienteEntrante.setComponentPopupMenu(popMenu);
        tableClienteEntrante.setGrosorBordeFilas(0);
        tableClienteEntrante.setGrosorBordeHead(0);
        tableClienteEntrante.setRowHeight(20);
        tableClienteEntrante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableClienteEntranteMousePressed(evt);
            }
        });
        tableClienteEntrante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableClienteEntranteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableClienteEntranteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableClienteEntranteKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableClienteEntrante);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarTipoTrabajador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarTipoTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarTipoTrabajador.setPrompt("BUSCAR POR DNI, APELLIDOS PARTERNO O MARTERNO");
        txtBuscarTipoTrabajador.setPromptBackround(null);
        txtBuscarTipoTrabajador.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarTipoTrabajador.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarTipoTrabajador.setSelectionEnd(1);
        txtBuscarTipoTrabajador.setSelectionStart(2);
        txtBuscarTipoTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyReleased(evt);
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
                .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
//        listaClienteEntrante = gestionarClienteEntranteServicio.mostrarClienteEntrantes(ClienteEntrante.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tableClienteEntranteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteEntranteMousePressed
        try {
            fila = tableClienteEntrante.getSelectedRow();
            inabilitarBotones(false);
            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
                obtenerClienteEntrante();
            } else if (TIPO_USUARIO == TIPO_GASTOS_EXTRAS) {
                obtenerClienteEntrante();
            } else if (TIPO_USUARIO == TIPO_VALORIZACION_PRINCIPAL) {
                obtenerClienteEntrante();
            } else if (TIPO_USUARIO == TIPO_CHEQUECLIENTE) {
                obtenerClienteEntrante();
            } else if (TIPO_USUARIO == TIPO_LIQUIDACION_REPORTE) {
                obtenerClienteEntrante();
            } else if (TIPO_USUARIO == TIPO_ADELANTO_CLIENTE_REPORTE) {
                obtenerClienteEntrante();

            }
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }

    }//GEN-LAST:event_tableClienteEntranteMousePressed

    private void tableClienteEntranteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClienteEntranteKeyReleased
        fila = tableClienteEntrante.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableClienteEntranteKeyReleased

    private void tableClienteEntranteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClienteEntranteKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
                    obtenerClienteEntrante();
                }
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }


    }//GEN-LAST:event_tableClienteEntranteKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        FormDatosClienteEntrante datosClienteEntrante = new FormDatosClienteEntrante(null, true);
        datosClienteEntrante.setVisible(true);
        inicializarTabla();
        clienteEntranteSeleccionado = null;
        inabilitarBotones(true);


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarTipoTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyPressed
//        try {
//            if (!txtBuscarClienteEntrante.getText().equals("")) {
//                listaClienteEntrante = null;
//                listaClienteEntrante = gestionarClienteEntranteServicio.buscarClienteEntrantePorNombre(txtBuscarClienteEntrante.getText().trim());
//                obtenerDatosTabla();
//            } else {
//                listaClienteEntrante = null;
//                obtenerDatosParaTabla();
//                txtBuscarClienteEntrante.setText("");
//            }
//
//        } catch (Exception ex) {
//            Mensaje.mostrarErrorDeConsulta(this);
//        }

    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyPressed

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
            obtenerClienteEntranteSeleccionado();
            FormDatosClienteEntrante clienteEntrante = new FormDatosClienteEntrante(null, true, clienteEntranteSeleccionado);
            clienteEntrante.setVisible(true);
            inicializarTabla();
            clienteEntranteSeleccionado = null;
            inabilitarBotones(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoExiste(this);
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarTipoTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyReleased
        try {
            String texto = txtBuscarTipoTrabajador.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarClienteEntranteServicio.mostrarClienteEntrantes(1, tableClienteEntrante, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyReleased

    private void tableClienteEntranteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClienteEntranteKeyPressed
        fila = tableClienteEntrante.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableClienteEntranteKeyPressed

    private void obtenerClienteEntranteSeleccionado() {
        try {
            String codigo = tableClienteEntrante.getValueAt(fila, 0).toString();
            clienteEntranteSeleccionado = gestionarClienteEntranteServicio.buscarClienteEntrantePorCodigo(Integer.parseInt(codigo));
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
    private rojerusan.RSTableMetro tableClienteEntrante;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    // End of variables declaration//GEN-END:variables

    private void obtenerClienteEntrante() {
        String codigo = tableClienteEntrante.getValueAt(fila, 0).toString();

        try {
            clienteEntranteSeleccionado = gestionarClienteEntranteServicio.buscarClienteEntrantePorCodigo(Integer.parseInt(codigo));
            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
                FormGestionarLiquidacion.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_GASTOS_EXTRAS) {
                FormGestionarGastosExtras.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_VALORIZACION_PRINCIPAL) {
                FormGestionarValorizacion.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_CHEQUECLIENTE) {
                FormGestionarChequeClienteServicio.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_LIQUIDACION_REPORTE) {
                FormReporteValorizacion.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
                this.dispose();
            } else if (TIPO_USUARIO == TIPO_ADELANTO_CLIENTE_REPORTE) {
                FormReporteAdelantoCliente.clienteEntranteSeleccionado = clienteEntranteSeleccionado;
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
            obtenerClienteEntranteSeleccionado();
            if (clienteEntranteSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    int registros_afectados = gestionarClienteEntranteServicio.eliminarClienteEntrante(clienteEntranteSeleccionado);
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
