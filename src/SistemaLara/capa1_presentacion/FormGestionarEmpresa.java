/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarEmpresaServicio;
import SistemaLara.capa3_dominio.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarTipoTrabajadorServicio;
//import motorepuestos.capa3_dominio.TipoTrabajador;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarEmpresa extends javax.swing.JDialog {

    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private GestionarEmpresaServicio gestionarEmpresaServicio;
    public static Empresa empresaSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;

    public FormGestionarEmpresa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            empresaSeleccionado = new Empresa();
            gestionarEmpresaServicio = new GestionarEmpresaServicio();
            inicializarTablaColumnas();
//            obtenerDatosTabla();
            inabilitarBotones(true);
            popMenu.add(pnlMenu);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("C??digo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Razon Social", "java.lang.String"));
        tabla.agregarColumna(new Columna("Direccion", "java.lang.String"));
        tabla.agregarColumna(new Columna("Telefono", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaEmpresa.setModel(modeloTabla);
        tablaEmpresa.getColumn(tablaEmpresa.getColumnName(0)).setWidth(0);
        tablaEmpresa.getColumn(tablaEmpresa.getColumnName(0)).setMinWidth(0);
        tablaEmpresa.getColumn(tablaEmpresa.getColumnName(0)).setMaxWidth(0);
    }
   public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    public void limpiarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tablaEmpresa.getModel();
            int filas = tablaEmpresa.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }

        } catch (Exception e) {

        }
    }

    private void inicializarTabla() {

        Fila filaTabla;
        try {

            listaEmpresa = gestionarEmpresaServicio.mostrarEmpresa(1);
            ModeloTabla modeloTabla = (ModeloTabla) tablaEmpresa.getModel();
            modeloTabla.eliminarTotalFilas();
            if (listaEmpresa.size() == 0) {
                JOptionPane.showMessageDialog(null, "No hay Tipo Cliente registrados ");
            } else {
                for (Empresa empresa : listaEmpresa) {
                    filaTabla = new Fila();
                    filaTabla.agregarValorCelda(empresa.getCodigo());
                    filaTabla.agregarValorCelda(empresa.getNombreComercial());
                    modeloTabla.agregarFila(filaTabla);
                }
                modeloTabla.refrescarDatos();
            }

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
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
        tablaEmpresa = new rojerusan.RSTableMetro();
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
        jLabel1.setText("GESTIONAR DATOS DE LA EMPRESA ");

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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        tablaEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaEmpresa.setAltoHead(30);
        tablaEmpresa.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaEmpresa.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaEmpresa.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaEmpresa.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaEmpresa.setComponentPopupMenu(popMenu);
        tablaEmpresa.setGrosorBordeFilas(0);
        tablaEmpresa.setGrosorBordeHead(0);
        tablaEmpresa.setRowHeight(20);
        tablaEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEmpresaMousePressed(evt);
            }
        });
        tablaEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaEmpresaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaEmpresaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaEmpresa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarTipoTrabajador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarTipoTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarTipoTrabajador.setToolTipText("BUSCAR");
        txtBuscarTipoTrabajador.setPrompt("BUSCAR");
        txtBuscarTipoTrabajador.setPromptBackround(null);
        txtBuscarTipoTrabajador.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarTipoTrabajador.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarTipoTrabajador.setSelectionEnd(1);
        txtBuscarTipoTrabajador.setSelectionStart(2);
        txtBuscarTipoTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyPressed(evt);
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
                .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerDatosParaTabla() throws Exception {
//        listaTipoTrabajador = gestionarTipoTrabajadorServicio.mostrarTipoTrabajadors(TipoTrabajador.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tablaEmpresaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpresaMousePressed
        fila = tablaEmpresa.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaEmpresaMousePressed

    private void tablaEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpresaKeyReleased
        fila = tablaEmpresa.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaEmpresaKeyReleased

    private void tablaEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpresaKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
//                    obtenerTipoTrabajador();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tablaEmpresaKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            FormDatosEmpresa formDatosEmpresa = new FormDatosEmpresa(null, true);
            formDatosEmpresa.setVisible(true);
            obtenerDatosParaTabla();
            empresaSeleccionado = null;
            inabilitarBotones(true);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarTipoTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyPressed
//        try {
//            if (!txtBuscarTipoTrabajador.getText().equals("")) {
//                listaTipoTrabajador = null;
//                listaTipoTrabajador = gestionarTipoTrabajadorServicio.buscarTipoTrabajadorPorNombre(txtBuscarTipoTrabajador.getText().trim());
//                obtenerDatosTabla();
//            } else {
//                listaTipoTrabajador = null;
//                obtenerDatosParaTabla();
//                txtBuscarTipoTrabajador.setText("");
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
//       try {
//            obtenerEmpresaSeleccionado();
//            FormDatosEmpresa tipoTrabajador = new FormDatosEmpresa(null, true, empresaSeleccionado);
//            tipoTrabajador.setVisible(true);
//            obtenerDatosParaTabla();
//            empresaSeleccionado = null;
//            inabilitarBotones(true);
//        } catch (Exception e) {
//            Mensaje.mostrarFilaNoExiste(this);
//        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         try {
            obtenerEmpresaSeleccionado();
            if (empresaSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = gestionarEmpresaServicio.eliminarEmpresa(empresaSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        obtenerDatosParaTabla();
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
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void obtenerEmpresaSeleccionado() {
        try {
            String codigo = tablaEmpresa.getValueAt(fila, 0).toString();
            empresaSeleccionado = gestionarEmpresaServicio.buscarEmpresaPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }
//    private void obtenerDatosTabla() {
//        Fila filaTabla;
//        try {
//            ModeloTabla modeloTabla = (ModeloTabla) tablaTipoTrabajadors.getModel();
//            modeloTabla.eliminarTotalFilas();
//            if (listaTipoTrabajador.size() == 0) {
//                Mensaje.mostrarMensajeDefinido(this, "No hay tipos de trabajadores registrados ");
//            } else {
//                for (TipoTrabajador tipoTrabajador : listaTipoTrabajador) {
//                    filaTabla = new Fila();
//                    filaTabla.agregarValorCelda(tipoTrabajador.getCodigo());
//                    filaTabla.agregarValorCelda(tipoTrabajador.getDescripcion());
//                    modeloTabla.agregarFila(filaTabla);
//                }
//                modeloTabla.refrescarDatos();
//            }
//
//        } catch (Exception e) {
//
//            Mensaje.mostrarErrorSistema(this);
//        }
//
//    }
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
    private rojerusan.RSTableMetro tablaEmpresa;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    // End of variables declaration//GEN-END:variables

//    private void obtenerTipoTrabajador() {
////        String codigo = tablaTipoTrabajadors.getValueAt(fila, 0).toString();
////
////        try {
////            tipoTrabajadorSeleccionado = gestionarTipoTrabajadorServicio.buscarTipoTrabajadorPorCodigo(codigo.trim());
////            if (TIPO_USUARIO == TIPO_TRABAJADOR) {
////                tipoTrabajadorSeleccionado = tipoTrabajadorSeleccionado;
////                this.dispose();
////            } else {
////                inabilitarBotones(false);
////            }
////
////        } catch (Exception e) {
////            Mensaje.mostrarErrorSistema(this);
////        }
////
////    }
//    }
}
