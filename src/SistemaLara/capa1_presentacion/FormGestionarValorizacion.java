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
import SistemaLara.capa2_aplicacion.GestionarLiquidacionReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa2_aplicacion.GestionarValorizacionReporteServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.TipoCliente;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarValorizacion extends javax.swing.JDialog {

    GestionarClienteEntranteServicio gestionarClienteEntranteServicio;
    GestionarLiquidacionServicio gestionarLiquidacionServicio;
    private Liquidacion liquidacionSeleccionado;
    TipoCliente clienteEntrante;
    public static ClienteEntrante clienteEntranteSeleccionado;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int codigoClienteEntrante;

    private JasperPrint print = null;

    private GestionarLiquidacionReporteServicio gestionarLiquidacionReporteServicio;

    public FormGestionarValorizacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarClienteEntranteServicio = new GestionarClienteEntranteServicio();
            gestionarLiquidacionReporteServicio = new GestionarLiquidacionReporteServicio();
            inicializarTablaColumnas();
            llenarItemTipoCliente();
//            obtenerDatosTabla();
            inabilitarBotones(true);

            inicializarTablaPorTipoCliente();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void llenarItemTipoCliente() {
        try {
            gestionarClienteEntranteServicio.llenarCBOTipoCliente(1, cboClienteEntrante);

        } catch (Exception e) {
        }
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("APELLIDOS", "java.lang.String"));
        tabla.agregarColumna(new Columna("NOMBRES", "java.lang.String"));
        tabla.agregarColumna(new Columna("DNI", "java.lang.String"));
        tabla.agregarColumna(new Columna("TIPO CLIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableValorizacion.setModel(modeloTabla);

        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setWidth(80);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setMinWidth(80);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setMaxWidth(80);

        tableValorizacion.getColumn(tableValorizacion.getColumnName(4)).setWidth(200);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(4)).setMinWidth(200);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(4)).setMaxWidth(200);

        tableValorizacion.getColumn(tableValorizacion.getColumnName(5)).setWidth(120);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(5)).setMinWidth(120);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(5)).setMaxWidth(120);

    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableValorizacion.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTablaPorLike() {
        try {
            limpiarCampos();
            if (txtBuscar.getText().equals("")) {
                inicializarTablaPorTipoCliente();
            } else {

                String texto = txtBuscar.getText().toString();
                limpiarTabla();
                gestionarLiquidacionServicio.mostrarLiquidacionPoCodigoCliente(tableValorizacion, texto);
            }

        } catch (Exception e) {
        }

    }

    void limpiarCampos() {
        txtApellido.setText("");
        txtCodigoCliente.setText("");
        txtDni.setText("");
        txtNombre.setText("");

    }

    private void inicializarTablaPorTipoCliente() {
        try {
            TipoCliente tipoCliente = cboClienteEntrante.getItemAt(cboClienteEntrante.getSelectedIndex());
            limpiarTabla();
            gestionarLiquidacionServicio.mostrarLiquidacionPorTipoCliente(1, tableValorizacion, tipoCliente.getCodigo());
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
        tableValorizacion = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtCodigoCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        txtBuscar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDni = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        txtApellido = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtNombre = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        btnProveedorServicio = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        btnProveedorServicio1 = new rojeru_san.RSButton();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        cboClienteEntrante = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();

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
        jLabel1.setText("GESTIONAR DATOS DE LA VALORIZACION ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Valorizacion.png"))); // NOI18N

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
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

        tableValorizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableValorizacion.setAltoHead(30);
        tableValorizacion.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableValorizacion.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableValorizacion.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableValorizacion.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableValorizacion.setGrosorBordeFilas(0);
        tableValorizacion.setGrosorBordeHead(0);
        tableValorizacion.setRowHeight(20);
        tableValorizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableValorizacionMousePressed(evt);
            }
        });
        tableValorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableValorizacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableValorizacionKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableValorizacion);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoCliente.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigoCliente.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigoCliente.setLabel("CODIGO CLIENTE");
        txtCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoClienteKeyTyped(evt);
            }
        });
        jPanel4.add(txtCodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 120, 60));

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel4.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 40));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 40, 40));

        txtBuscar.setForeground(new java.awt.Color(0, 0, 204));
        txtBuscar.setAccent(new java.awt.Color(204, 0, 51));
        txtBuscar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtBuscar.setLabel("INGRESO DATO A BUSCAR");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 260, 60));

        txtDni.setForeground(new java.awt.Color(0, 0, 204));
        txtDni.setAccent(new java.awt.Color(204, 0, 51));
        txtDni.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDni.setLabel("DNI");
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel4.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 120, 60));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        jPanel4.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 40, 40));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 40, 40));

        txtApellido.setForeground(new java.awt.Color(0, 0, 204));
        txtApellido.setAccent(new java.awt.Color(204, 0, 51));
        txtApellido.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtApellido.setLabel("APELLIDOS");
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel4.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 180, 60));

        txtNombre.setForeground(new java.awt.Color(0, 0, 204));
        txtNombre.setAccent(new java.awt.Color(204, 0, 51));
        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNombre.setLabel("NOMBRES");
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 210, 60));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        jPanel4.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 40));

        btnProveedorServicio.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedorServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnProveedorServicio.setText("IMPRIMIR");
        btnProveedorServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorServicioActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedorServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 130, 30));

        btnImprimir.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel4.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 510, 110, 30));

        btnProveedorServicio1.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedorServicio1.setText("...");
        btnProveedorServicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorServicio1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedorServicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 40, 30));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N
        jPanel4.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 40, 40));

        cboClienteEntrante.setBackground(new java.awt.Color(255, 255, 255));
        cboClienteEntrante.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboClienteEntrante.setForeground(new java.awt.Color(65, 94, 255));
        cboClienteEntrante.setAccent(new java.awt.Color(65, 94, 255));
        cboClienteEntrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClienteEntranteActionPerformed(evt);
            }
        });
        jPanel4.add(cboClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 200, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
//        listaTipoTrabajador = gestionarTipoTrabajadorServicio.mostrarTipoTrabajadors(TipoTrabajador.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }
    int fila;
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

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCodigoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteKeyPressed

    private void txtCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyPressed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnProveedorServicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorServicio1ActionPerformed
        FormGestionarClienteEntrante datosLiquidacion = new FormGestionarClienteEntrante(null, true, FormGestionarClienteEntrante.TIPO_VALORIZACION_PRINCIPAL);
        datosLiquidacion.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            txtApellido.setText(clienteEntranteSeleccionado.getApellidos());
            txtNombre.setText(clienteEntranteSeleccionado.getNombres());
            txtDni.setText(clienteEntranteSeleccionado.getDni());
            txtCodigoCliente.setText("" + clienteEntranteSeleccionado.getCodigo());
            txtBuscar.setText(clienteEntranteSeleccionado.getApellidos());
            inicializarTablaPorLike();
        }

    }//GEN-LAST:event_btnProveedorServicio1ActionPerformed

    private void tableValorizacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableValorizacionMousePressed
        try {
            fila = tableValorizacion.getSelectedRow();
            String codigo = tableValorizacion.getValueAt(fila, 0).toString();
            String fecha = tableValorizacion.getValueAt(fila, 5).toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            obtenerLiquidacionSeleccionado();
            java.util.Date tiempo = format.parse(fecha);
            java.sql.Date sqlDate = new java.sql.Date(tiempo.getTime());
            FormGestionarValorizacionDetalle datosTipoPersonal = new FormGestionarValorizacionDetalle(null, true, liquidacionSeleccionado, sqlDate);
            datosTipoPersonal.setVisible(true);
//            obtenerDatosParaTabla();
//            tipoTrabajadorSeleccionado = null;
//            inabilitarBotones(true);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_tableValorizacionMousePressed

    private void tableValorizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableValorizacionKeyReleased
        fila = tableValorizacion.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableValorizacionKeyReleased

    private void tableValorizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableValorizacionKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//                    obtenerClienteEntrante();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }

    }//GEN-LAST:event_tableValorizacionKeyTyped

    private void btnProveedorServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorServicioActionPerformed
        try {
            print = gestionarLiquidacionReporteServicio.mostrarLiquidacionLotePorCliente(cboClienteEntrante);
        } catch (Exception ex) {
            Logger.getLogger(FormGestionarValorizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnProveedorServicioActionPerformed

    private void cboClienteEntranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClienteEntranteActionPerformed
        inicializarTablaPorTipoCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_cboClienteEntranteActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        inicializarTablaPorLike();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void obtenerLiquidacionSeleccionado() {
        try {
            String codigo = tableValorizacion.getValueAt(fila, 0).toString();
            String fecha = tableValorizacion.getValueAt(fila, 5).toString();

            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigoCliente(Integer.parseInt(codigo),fecha);
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
//        btnAgregar.setEnabled(v);
//        btnEliminar.setEnabled(!v);
//        btnModificar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btnProveedorServicio;
    private rojeru_san.RSButton btnProveedorServicio1;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoCliente> cboClienteEntrante;
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
    private rojerusan.RSLabelImage rSLabelImage14;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage18;
    private rojerusan.RSLabelImage rSLabelImage19;
    private rojerusan.RSTableMetro tableValorizacion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtApellido;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtBuscar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoCliente;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDni;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNombre;
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
