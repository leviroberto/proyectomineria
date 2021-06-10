/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarContratoServicio;
import SistemaLara.capa3_dominio.Contrato;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import com.sun.glass.events.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

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
public class FormGestionarContrato extends javax.swing.JDialog {

    private List<Contrato> listaContrato = new ArrayList<Contrato>();
    private GestionarContratoServicio gestionarContratoServicio;
    public static Contrato contratoSeleccionado;
    private FormDatosContrato formDatosContrato;
    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_ADELANTO = 2;
    public static int TIPO_PAGO = 3;

    private int tipo_Accion = 0;

    private int x, y;
    int mes = 0;
    int anio = 0;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public FormGestionarContrato(java.awt.Frame parent, boolean modal, int tipo) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        tipo_Accion = tipo;
        inicializarTabla();
        contratoSeleccionado = new Contrato();
        gestionarContratoServicio = new GestionarContratoServicio();
        inicializadores();
        consultarBaseDatos();
//            inabilitarBotones(true);
        popMenu.add(pnlMenu);

    }
 
    private void inicializadores() {
        Calendar c1 = Calendar.getInstance();
        mes = c1.get(Calendar.MONTH) + 1;
        anio = c1.get(Calendar.YEAR);

    }

    private void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tablaContrato.getModel();
        modelo.eliminarTotalFilas();
        tablaContrato.setModel(modelo);
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void consultarBaseDatos() {
        try {
            limpiarTabla();
            gestionarContratoServicio.mostrarContrato(tablaContrato);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void inicializarTabla() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ingreso", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha salida ", "java.lang.String"));

        tabla.agregarColumna(new Columna("Total Dias Pagar", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("sueldo", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaContrato.setModel(modeloTabla);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setWidth(0);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMinWidth(0);
        tablaContrato.getColumn(tablaContrato.getColumnName(0)).setMaxWidth(0);
//  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnModificar = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        btnTodosLosContratos = new rojeru_san.RSButton();
        btnVerDetalle = new rojeru_san.RSButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMesContrato = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaContrato = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtDniPersonal = new org.jdesktop.swingx.JXSearchField();
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

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setForeground(new java.awt.Color(65, 94, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(10);
        btnModificar.setName("btnVerDetalle"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setForeground(new java.awt.Color(65, 94, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR   ");
        btnEliminar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(10);
        btnEliminar.setName("btnVerDetalle"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnTodosLosContratos.setBackground(new java.awt.Color(65, 94, 255));
        btnTodosLosContratos.setBorder(null);
        btnTodosLosContratos.setForeground(new java.awt.Color(65, 94, 255));
        btnTodosLosContratos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnTodosLosContratos.setText("TODOS CONTRATOS");
        btnTodosLosContratos.setColorHover(new java.awt.Color(255, 82, 54));
        btnTodosLosContratos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTodosLosContratos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTodosLosContratos.setIconTextGap(2);
        btnTodosLosContratos.setName("btnVerDetalle"); // NOI18N
        btnTodosLosContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosLosContratosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
            .addComponent(btnTodosLosContratos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTodosLosContratos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
        jLabel1.setText("GESTIONAR DATOS DEL CONTRATO");

        lblMesContrato.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblMesContrato.setForeground(new java.awt.Color(255, 187, 51));

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
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Contrato.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMesContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMesContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaContrato.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaContrato.setAltoHead(30);
        tablaContrato.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaContrato.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaContrato.setComponentPopupMenu(popMenu);
        tablaContrato.setGrosorBordeFilas(0);
        tablaContrato.setGrosorBordeHead(0);
        tablaContrato.setRowHeight(20);
        tablaContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaContratoMousePressed(evt);
            }
        });
        tablaContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaContratoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaContratoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaContratoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaContrato);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtDniPersonal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtDniPersonal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDniPersonal.setToolTipText("BUSCAR POR DNI , NOMBRE , APELLIDOS");
        txtDniPersonal.setPrompt("BUSCAR POR DNI , NOMBRE , APELLIDOS");
        txtDniPersonal.setPromptBackround(null);
        txtDniPersonal.setPromptFontStyle(new java.lang.Integer(4));
        txtDniPersonal.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtDniPersonal.setSelectionEnd(1);
        txtDniPersonal.setSelectionStart(2);
        txtDniPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniPersonalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniPersonalKeyTyped(evt);
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
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Buscar.png"))); // NOI18N
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
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDniPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDniPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtDniPersonal.getAccessibleContext().setAccessibleDescription("BUSCAR POR DNI a");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tablaContratoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaContratoMousePressed
        fila = tablaContrato.getSelectedRow();
        inabilitarBotones(false);
        if (tipo_Accion == TIPO_ADELANTO) {
            obtenerContratoSeleccionado();
            if (contratoSeleccionado != null) {
                FormDatosAdelantoPersonal.contratoSeleccionado = contratoSeleccionado;
                this.dispose();
            }
        } else if (tipo_Accion == TIPO_PAGO) {
            obtenerContratoSeleccionado();
            if (contratoSeleccionado != null) {
                FormDatosPagoPersonal.contratoSeleccionado = contratoSeleccionado;
                this.dispose();
            }
        }
    }//GEN-LAST:event_tablaContratoMousePressed

    private void tablaContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratoKeyReleased
        fila = tablaContrato.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tablaContratoKeyReleased

    private void tablaContratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratoKeyTyped
        fila = tablaContrato.getSelectedRow();
        inabilitarBotones(false);

    }//GEN-LAST:event_tablaContratoKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        FormDatosContrato f = new FormDatosContrato(null, true);
        f.setVisible(true);

        consultarBaseDatos();
        contratoSeleccionado = null;
        inabilitarBotones(true);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed

    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void txtDniPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPersonalKeyPressed

    }//GEN-LAST:event_txtDniPersonalKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
//        try {
//            if (!txtDniPersonal.getText().equals("")) {
//                String estado = cboFiltarPorMeses.getSelectedItem().toString();
//                listaContrato = gestionarContratoServicio.buscarContratoPorPersonalDni(txtDniPersonal.getText().trim(), estado);
//                obtenerDatosTabla();
//            } else {
//                consultarBaseDatos();
//            }
//
//        } catch (Exception ex) {
//            Mensaje.mostrarErrorSistema(this);
//        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtDniPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPersonalKeyTyped
//        String patron_de_entrada = "0123456789";
//        if (txtDniPersonal.getText().length() == 8
//                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtDniPersonalKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            obtenerContratoSeleccionado();
            if (contratoSeleccionado != null) {
                FormDatosContrato contrato = new FormDatosContrato(null, true, contratoSeleccionado);
                contrato.setVisible(true);
                consultarBaseDatos();
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnTodosLosContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosLosContratosActionPerformed
        obtenerContratoSeleccionado();
        if (contratoSeleccionado != null) {
            FormlistaTodosLosContratosPersonal a = new FormlistaTodosLosContratosPersonal(null, true, contratoSeleccionado);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnTodosLosContratosActionPerformed

    private void txtDniPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniPersonalKeyReleased
        try {
            String texto = txtDniPersonal.getText().toString();
            if (texto.equals("")) {
                consultarBaseDatos();
            } else {
                limpiarTabla();
                gestionarContratoServicio.mostrarContrato(4, tablaContrato, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDniPersonalKeyReleased

    private void tablaContratoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaContratoKeyPressed
        fila = tablaContrato.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tablaContratoKeyPressed

    private void obtenerContratoSeleccionado() {
        try {
            String codigo = tablaContrato.getValueAt(fila, 0).toString();
            contratoSeleccionado = gestionarContratoServicio.buscarContratoPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

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
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btnTodosLosContratos;
    private rojeru_san.RSButton btnVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMesContrato;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaContrato;
    private org.jdesktop.swingx.JXSearchField txtDniPersonal;
    // End of variables declaration//GEN-END:variables
IniciarDesktop iniciarDesktop = new IniciarDesktop();

    private void metodoParaEliminar() {
        obtenerContratoSeleccionado();
        if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
            return;
        }

        try {

            if (contratoSeleccionado != null) {
                int registros_afectados = gestionarContratoServicio.eliminarContrato(contratoSeleccionado.getCodigo());
                if (registros_afectados == 1) {
                    Mensaje.mostrarAfirmacionDeEliminacion(this);
                    consultarBaseDatos();
                    inabilitarBotones(true);
                    DesktopNotify.eliminarWindowsAll();
                    TimerPagosTrabajador.iniciarOtraVes();
                    iniciarDesktop.llenarDescktopPagosContrato(TimerPagosTrabajador.listaContratatoPagosHoy);

                } else {
                    Mensaje.mostrarAdvertenciaDeEliminacion(this);
                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Falta seleccionar un contrato");
            }

        } catch (Exception e) {
            Mensaje.mostrarErrorDeEliminacion(this);
        }
    }
}
