/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarGastosExtrasServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Concepto;
import SistemaLara.capa3_dominio.GastosExtras;
import SistemaLara.capa3_dominio.IniciarSesion;
import com.sun.glass.events.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionargastosExtrasServicio;
//import motorepuestos.capa3_dominio.gastosExtras;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarGastosExtras extends javax.swing.JDialog {

    private GestionarGastosExtrasServicio gestionargastosExtrasServicio;
    public static GastosExtras gastosExtrasSeleccionado;
    public static ClienteEntrante clienteEntranteSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int ACCION_CREAR = 1;
    private int ACCION_MODIFICAR = 2;
    private int codigoGastosExtras = 0;

    private int TIPO_USUARIO;
    private int tipo_accion;
    private Concepto coneceptoSeleccionado;

    public FormGestionarGastosExtras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);

            gestionargastosExtrasServicio = new GestionarGastosExtrasServicio();
            llenarItemConcepto();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(true);
            inabilitarCampos(false);

            limpiarCampos();
            popMenu.add(pnlMenu);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

   public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void llenarItemConcepto() {
        try {
            cboConcepto.removeAllItems();
            gestionargastosExtrasServicio.llenarCBOConceptoExtras(1, cboConcepto);

        } catch (Exception e) {
        }
    }

    private void inabilitarBotones(Boolean v) {

        btnCrear.setEnabled(v);
        btnEliminar.setEnabled(!v);
        btnModificar.setEnabled(!v);
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCIÓN", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONTO", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLIENTE ENTRANTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("CONCEPTO", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA REGISTRO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableGastosExtras.setModel(modeloTabla);

        tableGastosExtras.getColumn(tableGastosExtras.getColumnName(0)).setWidth(0);
        tableGastosExtras.getColumn(tableGastosExtras.getColumnName(0)).setMinWidth(0);
        tableGastosExtras.getColumn(tableGastosExtras.getColumnName(0)).setMaxWidth(0);
        TableColumnModel columnModel = tableGastosExtras.getColumnModel();

        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(25);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(30);

        tableGastosExtras.setColumnModel(columnModel);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableGastosExtras.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionargastosExtrasServicio.mostrarGastosExtras(1, tableGastosExtras);
        } catch (Exception e) {
        }

    }

    private void llenarCamposParaModificar(GastosExtras gastosExtras) {
        if (gastosExtras != null) {
            codigoGastosExtras = gastosExtras.getCodigo();
            clienteEntranteSeleccionado = gastosExtras.getClienteEntrante();
            txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
            txtDescripcion.setText(gastosExtras.getDescripcion());
            txtMonto.setText("" + gastosExtras.getMonto());
            if (gastosExtras.getMoneda().equals("$")) {
                rbDolares.setSelected(true);
            } else {
                rbSoles.setSelected(true);
            }
            for (int i = 0; i < cboConcepto.getItemCount(); i++) {
                if (gastosExtras.getConcepto().getDescripcion().equals(cboConcepto.getItemAt(i).getDescripcion())) {
                    cboConcepto.setSelectedIndex(i);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnModificar = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGastosExtras = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtClienteEntrante = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        btnClienteEntrante = new rojeru_san.RSButton();
        txtMonto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        txtDescripcion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        cboConcepto = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        rbSoles = new com.icm.components.metro.RadioButtonMetroICM();
        rbDolares = new com.icm.components.metro.RadioButtonMetroICM();
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        btnNuevo = new rojeru_san.RSButton();
        btnCrear = new rojeru_san.RSButton();

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
        jLabel1.setText("GESTIONAR DATOS DE GASTOS EXTRAS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoGastos.png"))); // NOI18N

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tableGastosExtras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableGastosExtras.setAltoHead(30);
        tableGastosExtras.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableGastosExtras.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableGastosExtras.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableGastosExtras.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableGastosExtras.setComponentPopupMenu(popMenu);
        tableGastosExtras.setGrosorBordeFilas(0);
        tableGastosExtras.setGrosorBordeHead(0);
        tableGastosExtras.setRowHeight(20);
        tableGastosExtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableGastosExtrasMousePressed(evt);
            }
        });
        tableGastosExtras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableGastosExtrasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableGastosExtrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableGastosExtrasKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableGastosExtras);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtClienteEntrante.setForeground(new java.awt.Color(0, 0, 204));
        txtClienteEntrante.setAccent(new java.awt.Color(204, 0, 51));
        txtClienteEntrante.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtClienteEntrante.setLabel("Cliente Entrante");

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N

        btnClienteEntrante.setBackground(new java.awt.Color(65, 94, 255));
        btnClienteEntrante.setText("...");
        btnClienteEntrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEntranteActionPerformed(evt);
            }
        });

        txtMonto.setForeground(new java.awt.Color(0, 0, 204));
        txtMonto.setAccent(new java.awt.Color(204, 0, 51));
        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtMonto.setLabel("MONTO");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ConceptoPro.png"))); // NOI18N

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setLabel("DESCRIPCIÓN");
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dolar.png"))); // NOI18N

        cboConcepto.setBackground(new java.awt.Color(255, 255, 255));
        cboConcepto.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboConcepto.setForeground(new java.awt.Color(65, 94, 255));
        cboConcepto.setAccent(new java.awt.Color(65, 94, 255));

        rSButtonIconD1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        rSButtonIconD1.setColorHover(new java.awt.Color(255, 187, 51));
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });

        rbSoles.setBorder(null);
        buttonGroup1.add(rbSoles);
        rbSoles.setForeground(new java.awt.Color(65, 94, 255));
        rbSoles.setText("Soles");

        rbDolares.setBorder(null);
        buttonGroup1.add(rbDolares);
        rbDolares.setForeground(new java.awt.Color(65, 94, 255));
        rbDolares.setSelected(true);
        rbDolares.setText("Dolares");

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyReleased(evt);
            }
        });

        rSPanelShadow1.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setColorHover(new java.awt.Color(253, 173, 1));
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo.setIconTextGap(0);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCrear.setBackground(new java.awt.Color(65, 94, 255));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnCrear.setText("GUARDAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelShadow1Layout = new javax.swing.GroupLayout(rSPanelShadow1);
        rSPanelShadow1.setLayout(rSPanelShadow1Layout);
        rSPanelShadow1Layout.setHorizontalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        rSPanelShadow1Layout.setVerticalGroup(
            rSPanelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSLabelImage16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelImage15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtClienteEntrante, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnClienteEntrante, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cboConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(rSLabelImage17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rbDolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(190, 190, 190)
                        .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rSLabelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClienteEntrante, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnClienteEntrante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rSLabelImage17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(rbSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(rbDolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rSLabelImage16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(rSLabelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelImage15, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(rSPanelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rSLabelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void obtenerDatosParaTabla() throws Exception {
//        listagastosExtras = gestionargastosExtrasServicio.mostrargastosExtrass(gastosExtras.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        opcionNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed
    private void opcionNuevo() {

        tipo_accion = ACCION_CREAR;
        limpiarCampos();
        inabilitarCampos(true);
        obtenerClienteFormulario();
    }

    private void limpiarCampos() {
        rbDolares.setSelected(true);
        txtClienteEntrante.setText("");
        txtDescripcion.setText("");
        txtMonto.setText("");
        rbDolares.setSelected(true);
        cboConcepto.setSelectedIndex(0);
        rbDolares.setSelected(true);

    }

    private void inabilitarCampos(boolean estado) {
        txtClienteEntrante.setEnabled(estado);
        txtDescripcion.setEnabled(estado);
        txtMonto.setEnabled(estado);
        btnClienteEntrante.setEnabled(estado);
        rbDolares.setEnabled(estado);
        rbSoles.setEnabled(estado);
        cboConcepto.setEnabled(estado);
    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtClienteEntrante);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtDescripcion);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtMonto);
        aux = aux + contador;
        return aux == 3;
    }

    private void guardarDatos() {

        if (verificarCamposVacios()) {
            gastosExtrasSeleccionado = new GastosExtras();

            gastosExtrasSeleccionado.setClienteEntrante(clienteEntranteSeleccionado);
            gastosExtrasSeleccionado.setConcepto(cboConcepto.getItemAt(cboConcepto.getSelectedIndex()));
            gastosExtrasSeleccionado.setDescripcion(txtDescripcion.getText());
            gastosExtrasSeleccionado.setMonto(Double.parseDouble(txtMonto.getText()));
            if (rbDolares.isSelected()) {
                gastosExtrasSeleccionado.setMoneda("$");
            } else {
                gastosExtrasSeleccionado.setMoneda("S");
            }
            gastosExtrasSeleccionado.setPersonal(IniciarSesion.getUsuario());

            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                    registros_afectados = gestionargastosExtrasServicio.guardarGastosExtras(gastosExtrasSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        inicializarTabla();
                        inabilitarCampos(false);
                        limpiarCampos();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    gastosExtrasSeleccionado.setCodigo(codigoGastosExtras);
                    registros_afectados = gestionargastosExtrasServicio.modificarGastosExtras(gastosExtrasSeleccionado);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Gasto Extra", DesktopNotify.SUCCESS);
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();
                        inabilitarCampos(false);
                        limpiarCampos();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar Un nuevo Gasto Extra", DesktopNotify.INPUT_REQUEST);

                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);

                }

            }
        }

    }

    int fila;
    private void tableGastosExtrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGastosExtrasMousePressed
        btnEliminar.setEnabled(true);
        try {
            tipo_accion = ACCION_MODIFICAR;
            fila = tableGastosExtras.getSelectedRow();
            String codigo = tableGastosExtras.getValueAt(fila, 0).toString();
            GastosExtras gastosExtras = gestionargastosExtrasServicio.buscarGastosExtrasPorCodigo(Integer.parseInt(codigo));
            llenarCamposParaModificar(gastosExtras);
            inabilitarCampos(true);
        } catch (Exception e) {
               Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_tableGastosExtrasMousePressed

    private void tableGastosExtrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableGastosExtrasKeyReleased
//        fila = tablagastosExtrass.getSelectedRow();
//        inabilitarBotones(false);
    }//GEN-LAST:event_tableGastosExtrasKeyReleased

    private void tableGastosExtrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableGastosExtrasKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
//                    obtenergastosExtras();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableGastosExtrasKeyTyped

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        guardarDatos();


    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtBuscarTipoTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyPressed
//        try {
//            if (!txtBuscargastosExtras.getText().equals("")) {
//                listagastosExtras = null;
//                listagastosExtras = gestionargastosExtrasServicio.buscargastosExtrasPorNombre(txtBuscargastosExtras.getText().trim());
//                obtenerDatosTabla();
//            } else {
//                listagastosExtras = null;
//                obtenerDatosParaTabla();
//                txtBuscargastosExtras.setText("");
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
//            obtenergastosExtrasSeleccionado();
//            FormDatosgastosExtras gastosExtras = new FormDatosgastosExtras(null, true, gastosExtrasSeleccionado);
//            gastosExtras.setVisible(true);
//            obtenerDatosParaTabla();
//            gastosExtrasSeleccionado = null;
//            inabilitarBotones(true);
//        } catch (Exception e) {
//            Mensaje.mostrarFilaNoExiste(this);
//        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnClienteEntranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteEntranteActionPerformed
        obtenerClienteFormulario();
    }//GEN-LAST:event_btnClienteEntranteActionPerformed

    private void obtenerClienteFormulario() {
        FormGestionarClienteEntrante datosLiquidacion = new FormGestionarClienteEntrante(null, true, FormGestionarClienteEntrante.TIPO_GASTOS_EXTRAS);
        datosLiquidacion.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
            txtMonto.selectAll();
            txtMonto.requestFocus();
        }
    }
    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcion.selectAll();
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtMontoKeyPressed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
//        try {
//
//            int lote = Integer.parseInt(txtLote.getText());
//            int verificarNumero = gestionarLiquidacionServicio.verificarNumeroLote(lote);
//            if (verificarNumero >= 1) {
//                Mensaje.mostrarMensajeDefinido(this, "El lote " + lote + ": Ya Esta Registrado Ingrese Uno diferente o de lo contrario busque este numero y eliminelo");
//            }
//
//        } catch (Exception e) {
//
//        }

    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtMonto.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtBuscarTipoTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyReleased
        try {
            String texto = txtBuscarTipoTrabajador.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionargastosExtrasServicio.mostrarGastosExtras(1, tableGastosExtras, texto);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyReleased

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed
        FormGestionarConcepto c = new FormGestionarConcepto(null, true);
        c.setVisible(true);
        llenarItemConcepto();
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void tableGastosExtrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableGastosExtrasKeyPressed
        fila = tableGastosExtras.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableGastosExtrasKeyPressed

    private void obtenergastosExtrasSeleccionado() {
        try {
            String codigo = tableGastosExtras.getValueAt(fila, 0).toString();
            gastosExtrasSeleccionado = gestionargastosExtrasServicio.buscarGastosExtrasPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }
//    private void obtenerDatosTabla() {
//        Fila filaTabla;
//        try {
//            ModeloTabla modeloTabla = (ModeloTabla) tablagastosExtrass.getModel();
//            modeloTabla.eliminarTotalFilas();
//            if (listagastosExtras.size() == 0) {
//                Mensaje.mostrarMensajeDefinido(this, "No hay tipos de trabajadores registrados ");
//            } else {
//                for (gastosExtras gastosExtras : listagastosExtras) {
//                    filaTabla = new Fila();
//                    filaTabla.agregarValorCelda(gastosExtras.getCodigo());
//                    filaTabla.agregarValorCelda(gastosExtras.getDescripcion());
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
//    private void inabilitarBotones(Boolean v) {
//
//        btnAgregar.setEnabled(v);
//        btnEliminar.setEnabled(!v);
//        btnModificar.setEnabled(!v);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnClienteEntrante;
    private rojeru_san.RSButton btnCrear;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<Concepto> cboConcepto;
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
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private com.icm.components.metro.RadioButtonMetroICM rbDolares;
    private com.icm.components.metro.RadioButtonMetroICM rbSoles;
    private rojerusan.RSTableMetro tableGastosExtras;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtClienteEntrante;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMonto;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {
        try {
            obtenergastosExtrasSeleccionado();
            if (gastosExtrasSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = gestionargastosExtrasServicio.eliminarGastosExtras(gastosExtrasSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        inicializarTabla();
                        inabilitarBotones(true);
                        limpiarCampos();
                        inabilitarCampos(false);
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

//    private void obtenergastosExtras() {
////        String codigo = tablagastosExtrass.getValueAt(fila, 0).toString();
////
////        try {
////            gastosExtrasSeleccionado = gestionargastosExtrasServicio.buscargastosExtrasPorCodigo(codigo.trim());
////            if (TIPO_USUARIO == TIPO_TRABAJADOR) {
////                gastosExtrasSeleccionado = gastosExtrasSeleccionado;
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
