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
import SistemaLara.capa2_aplicacion.GestionarPermisosDetallePersonalServicio;
import SistemaLara.capa2_aplicacion.GestionarPermisosServicio;
import SistemaLara.capa2_aplicacion.GestionarTipoPersonalServicio;
import SistemaLara.capa3_dominio.PermisosDetallePersonal;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.TipoPersonal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarPermisosPersonal extends javax.swing.JDialog {

    private GestionarPermisosDetallePersonalServicio gestionarPermisosDetallePersonalServicio;
    public static TipoPersonal tipoTrabajadorSeleccionado;
    private List<TipoPersonal> listaPersonal = new ArrayList<TipoPersonal>();
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;
    DefaultTableModel modelo;

    public FormGestionarPermisosPersonal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
   
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
//           this.TIPO_USUARIO = tipo;
        tipoTrabajadorSeleccionado = new TipoPersonal();
        gestionarPermisosDetallePersonalServicio = new GestionarPermisosDetallePersonalServicio();
        // inicializarTablaColumnas();
        iniciarTablaColumnasPermisos();
        inicializarTablaColumnasPersonal();
        inicializarTablaPersonal();
        cboxSeleccionarTodos.setVisible(false);
        panelOpciones.setVisible(false);

//            inabilitarBotones(true);
    }

    private void inicializarTablaColumnasPersonal() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("NOMBRE", "java.lang.String"));
        tabla.agregarColumna(new Columna("APELLIDOS", "java.lang.String"));
        tabla.agregarColumna(new Columna("DNI", "java.lang.String"));
        tabla.agregarColumna(new Columna("SEXO", "java.lang.String"));
        tabla.agregarColumna(new Columna("TELEFONO", "java.lang.String"));
        tabla.agregarColumna(new Columna("EMAIL", "java.lang.String"));
        tabla.agregarColumna(new Columna("DIKRECCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("TIPO PERSONAL", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaPersonal.setModel(modeloTabla);
//        tablaPersonal.getColumn(tablaPersonal.getColumnName(0)).setWidth(0);
//        tablaPersonal.getColumn(tablaPersonal.getColumnName(0)).setMinWidth(0);
//        tablaPersonal.getColumn(tablaPersonal.getColumnName(0)).setMaxWidth(0);

        tablaPersonal.getColumn(tablaPersonal.getColumnName(4)).setWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(4)).setMinWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(4)).setMaxWidth(0);

        tablaPersonal.getColumn(tablaPersonal.getColumnName(5)).setWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(5)).setMinWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(5)).setMaxWidth(0);

        tablaPersonal.getColumn(tablaPersonal.getColumnName(6)).setWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(6)).setMinWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(6)).setMaxWidth(0);

        tablaPersonal.getColumn(tablaPersonal.getColumnName(7)).setWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(7)).setMinWidth(0);
        tablaPersonal.getColumn(tablaPersonal.getColumnName(7)).setMaxWidth(0);
    }

    private void iniciarTablaColumnasPermisos() {
        tablePermisos.getColumn(tablePermisos.getColumnName(1)).setWidth(0);
        tablePermisos.getColumn(tablePermisos.getColumnName(1)).setMinWidth(0);
        tablePermisos.getColumn(tablePermisos.getColumnName(1)).setMaxWidth(0);
    }

    public void limpiarTablaPersonal() {
        ModeloTabla modelo = (ModeloTabla) tablaPersonal.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTablaPersonal() {
        try {
            limpiarTablaPersonal();
            gestionarPermisosDetallePersonalServicio.mostrarPersonal(1, tablaPersonal);
        } catch (Exception e) {
        }

    }

    public final void BarraDesplzamiento() {
        jScrollPane1.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new  RSScrollBar());
    }

    public void limpiarTablePermisos() {
        try {
            modelo = (DefaultTableModel) tablePermisos.getModel();
            int filas = tablePermisos.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    private void inicializarTablaPermisos() {
        try {
            limpiarTablePermisos();
            int contadorSeleccionadosTodos = gestionarPermisosDetallePersonalServicio.mostrarPermisos(tablePermisos, codigoPersonal);

            if (contadorSeleccionadosTodos == tablePermisos.getRowCount()) {
                cboxSeleccionarTodos.setSelected(true);
                estadoSeleccionarTodo = true;

            } else {
                cboxSeleccionarTodos.setSelected(false);
                estadoSeleccionarTodo = false;

            }
        } catch (Exception e) {
        }

    }

    private List<PermisosDetallePersonal> obtenerFilasSeleccionadasCheckBox() {
        List<PermisosDetallePersonal> listaPermisos = new ArrayList<>();
        modelo = (DefaultTableModel) tablePermisos.getModel();
        PermisosDetallePersonal detallePermisosPersonal;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            detallePermisosPersonal = new PermisosDetallePersonal();
            //Si la columna 4 está true añadimos el ID
            if ((Boolean) modelo.getValueAt(i, 0) == true) {
                int codigo = (Integer) modelo.getValueAt(i, 1);
                detallePermisosPersonal.setCodigo(codigo);
                Estado estado = new Estado();
                estado.setCodigo(1);
                detallePermisosPersonal.setEstado(estado);
                listaPermisos.add(detallePermisosPersonal);
            } else {
                int codigo = (Integer) modelo.getValueAt(i, 1);
                detallePermisosPersonal.setCodigo(codigo);
                Estado estado = new Estado();
                estado.setCodigo(2);
                detallePermisosPersonal.setEstado(estado);
                listaPermisos.add(detallePermisosPersonal);
            }

        }
        tablePermisos.setModel(modelo);
        return listaPermisos;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonal = new rojerusan.RSTableMetro();
        panelOpciones = new javax.swing.JPanel();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePermisos = new rojerusan.RSTableMetro();
        cboxSeleccionarTodos = new com.icm.components.metro.CheckBoxMetroICM();

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
        jLabel1.setText("GESTIONAR DATOS DE TIPO PERSONAL\n");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoTipoPersonal.png"))); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 638, Short.MAX_VALUE)
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

        jScrollPane1.setForeground(new java.awt.Color(0, 102, 255));

        tablaPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Selección", "Codigo", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPersonal.setAltoHead(30);
        tablaPersonal.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaPersonal.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaPersonal.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaPersonal.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaPersonal.setGrosorBordeFilas(0);
        tablaPersonal.setGrosorBordeHead(0);
        tablaPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaPersonalMousePressed(evt);
            }
        });
        tablaPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPersonalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaPersonalKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPersonal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        panelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        panelOpciones.setBorder(dropShadowBorder2);

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnAgregar.setText("GUARDAR");
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

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        jPanel5.setBorder(dropShadowBorder3);

        jScrollPane2.setForeground(new java.awt.Color(0, 102, 255));

        tablePermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selección", "Codigo", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePermisos.setAltoHead(30);
        tablePermisos.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablePermisos.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablePermisos.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablePermisos.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablePermisos.setGrosorBordeFilas(0);
        tablePermisos.setGrosorBordeHead(0);
        tablePermisos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablePermisosMousePressed(evt);
            }
        });
        tablePermisos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablePermisosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablePermisosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablePermisosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tablePermisos);

        cboxSeleccionarTodos.setBorder(null);
        cboxSeleccionarTodos.setText("Todos");
        cboxSeleccionarTodos.setDark(false);
        cboxSeleccionarTodos.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboxSeleccionarTodos.setHideActionText(true);
        cboxSeleccionarTodos.setIconTextGap(1);
        cboxSeleccionarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxSeleccionarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(cboxSeleccionarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxSeleccionarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    private void obtenerDatosParaTabla() {
//        obtenerDatosTabla();
//    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        panelOpciones.setVisible(false);
        cboxSeleccionarTodos.setVisible(false);
        limpiarTablePermisos();

    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        int numeroAfectados = 0;
        List<PermisosDetallePersonal> listaPermisos = obtenerFilasSeleccionadasCheckBox();
        if (listaPermisos.size() > 0) {
            try {
                numeroAfectados = gestionarPermisosDetallePersonalServicio.modificarDetallePermisosPersonal(listaPermisos);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            if (numeroAfectados == 1) {
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                cboxSeleccionarTodos.setVisible(false);
                limpiarTablePermisos();
                panelOpciones.setVisible(false);
                FormMenu.obtenerPermisosActualizados();

            } else {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        } else {
            Mensaje.mostrarMensajeDefinido(this, "No ha seleccionado ningun permiso");
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged
    int codigoPersonal = 0;
    private void tablaPersonalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonalMousePressed
     
        try {
               fila = tablaPersonal.getSelectedRow();
//        inabilitarBotones(false);
        String codigo = tablaPersonal.getValueAt(fila, 0).toString();
        codigoPersonal = Integer.parseInt(codigo);
        inicializarTablaPermisos();
        cboxSeleccionarTodos.setVisible(true);
        panelOpciones.setVisible(true);
        } catch (Exception e) {
             Mensaje.mostrarFilaNoSeleccionada(this);
        }
 
     


    }//GEN-LAST:event_tablaPersonalMousePressed

    private void tablaPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPersonalKeyTyped
        fila = tablaPersonal.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaPersonalKeyTyped

    private void tablaPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPersonalKeyReleased
        fila = tablaPersonal.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaPersonalKeyReleased

    private void tablaPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPersonalKeyPressed
        fila = tablaPersonal.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaPersonalKeyPressed

    private void tablePermisosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePermisosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePermisosMousePressed

    private void tablePermisosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePermisosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePermisosKeyPressed

    private void tablePermisosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePermisosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePermisosKeyReleased

    private void tablePermisosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePermisosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tablePermisosKeyTyped
    public static boolean estadoSeleccionarTodo = false;
    private void cboxSeleccionarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxSeleccionarTodosActionPerformed
        if (estadoSeleccionarTodo == false) {
            estadoSeleccionarTodo = true;
            seleccionarCheckBoxTodos(true);
        } else if (estadoSeleccionarTodo) {
            estadoSeleccionarTodo = false;
            seleccionarCheckBoxTodos(false);

        }
    }//GEN-LAST:event_cboxSeleccionarTodosActionPerformed

    void seleccionarCheckBoxTodos(boolean estado) {
        try {
            modelo = (DefaultTableModel) tablePermisos.getModel();
            int contador = 0;
            for (int i = 0; i < tablePermisos.getRowCount(); i++) {
                modelo.setValueAt(estado, contador, 0);
                contador++;
            }
            tablePermisos.setModel(modelo);
        } catch (Exception e) {

        }
    }

    private void obtenerTipoPersonalSeleccionado() {
//        try {
//            String codigo = tablePermisos.getValueAt(fila, 0).toString();
//            tipoTrabajadorSeleccionado = gestionarPermisosDetallePersonalServicio.buscarTipoPersonalPorCodigo(Integer.parseInt(codigo));
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }

    }
//    private void obtenerDatosTabla() {
//        Fila filaTabla;
//        try {
//            ModeloTabla modeloTabla = (ModeloTabla) tablaTipoPersonals.getModel();
//            modeloTabla.eliminarTotalFilas();
//            if (listaTipoPersonal.size() == 0) {
//                Mensaje.mostrarMensajeDefinido(this, "No hay tipos de trabajadores registrados ");
//            } else {
//                for (TipoPersonal tipoTrabajador : listaTipoPersonal) {
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    public static com.icm.components.metro.CheckBoxMetroICM cboxSeleccionarTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelOpciones;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaPersonal;
    private rojerusan.RSTableMetro tablePermisos;
    // End of variables declaration//GEN-END:variables

//    private void obtenerTipoPersonal() {
////        String codigo = tablaTipoPersonals.getValueAt(fila, 0).toString();
////
////        try {
////            tipoTrabajadorSeleccionado = gestionarPermisosDetallePersonalServicio.buscarTipoPersonalPorCodigo(codigo.trim());
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
