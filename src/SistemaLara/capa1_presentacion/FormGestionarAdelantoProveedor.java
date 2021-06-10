/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarAdelantoServicio;
import SistemaLara.capa2_aplicacion.GestionarCambioServicio;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.Valorizacion;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarAdelantoProveedor extends javax.swing.JDialog {

    public static GestionarAdelantoServicio gestionarAdelantoServicio;
    public static GestionarCambioServicio gestionarCambioServicio;
    public static ProveedorServicio proveedorServicioSeleccionado;
    private GestionarAdelantoReporteServicio gestionarAdelantoReporteServicio;

    public static Adelanto adelantoSeleccionado;
    private int TIPO_USUARIO;
    public static int TIPO_TRABAJADOR = 1;
    public static int TIPO_VALORIZACION = 3;
    private JasperPrint print = null;
    private int tipo_accion;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;

    public FormGestionarAdelantoProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            adelantoSeleccionado = new Adelanto();
            gestionarCambioServicio = new GestionarCambioServicio();
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            gestionarAdelantoReporteServicio = new GestionarAdelantoReporteServicio();
            inicializarTablaColumnas();
            inicializarCambio();
            inabilitarBotones(false);
            inabilitarCampos(false);
            txtBuscarTipoTrabajador.setVisible(false);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public static void limpiarTabla() {
        ModeloTabla modelos = (ModeloTabla) tableAdelanto.getModel();
        modelos.eliminarTotalFilas();
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public static void inicializarTabla() {
        try {
            if (proveedorServicioSeleccionado != null) {
                limpiarTabla();
                gestionarAdelantoServicio.mostrarAdelantoProveedor(8, proveedorServicioSeleccionado, tableAdelanto);
                gestionarAdelantoServicio.calcularTotalNoPagadoSoles(lblTotalNoPagadoSoles, proveedorServicioSeleccionado);
                gestionarAdelantoServicio.calcularTotalNoPagadoDolares(lblNoPagadoDolares, proveedorServicioSeleccionado);
            } else {
                limpiarTabla();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void inicializarCambio() {
        try {
            gestionarCambioServicio.mostrarCambioDolar(1, lblcambio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void limpiarCampos() {
        txtDestinatario.setText("");

        txtDescripcion.setText("");
        txtBuscarTipoTrabajador.setText("");
        txtImporte.setText("");
        rdbSol.setSelected(true);

        dateFecha.setDatoFecha(new Date());

    }

    private void inabilitarCampos(boolean estado) {

        txtDescripcion.setEnabled(estado);
        txtImporte.setEnabled(estado);
        dateFecha.setEnabled(estado);
        rdbProveedor.setEnabled(estado);
        rdbPagado.setEnabled(estado);
        rdbNoPagado.setEnabled(estado);
        rdbDolar.setEnabled(estado);
        rdbSol.setEnabled(estado);
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("PROVEEDOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableAdelanto.setModel(modeloTabla);
        // tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setWidth(0);
        //tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMinWidth(0);
        // tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMaxWidth(0);
    }

    private void llenarCamposParaModificar(Adelanto adelanto) {

        if (adelanto != null) {
            lblCodigo.setText(String.valueOf(adelanto.getCodigo()));
            txtImporte.setText(String.valueOf(adelanto.getCantidad()));
            txtDescripcion.setText(adelanto.getDescripcion());
            dateFecha.setDatoFecha(adelanto.getFechaPago());

            proveedorServicioSeleccionado = adelanto.getProveedorServicio();
            txtDestinatario.setText(proveedorServicioSeleccionado.getRazonSocial() + " :" + proveedorServicioSeleccionado.getRuc());

            if (adelanto.getAdelantoEstado().equals("NO PAGADO")) {
                rdbNoPagado.setSelected(true);
                rdbPagado.setSelected(false);
            } else if (adelanto.getAdelantoEstado().equals("PAGADO")) {
                rdbNoPagado.setSelected(false);
                rdbPagado.setSelected(true);
            }
            if (("S").equals(adelanto.getMoneda())) {
                rdbSol.setSelected(true);
                rdbDolar.setSelected(false);
            } else if (("$").equals(adelanto.getMoneda())) {
                rdbSol.setSelected(true);
                rdbDolar.setSelected(true);
            }
        }

    }

    private void guardarDatos() {
        Adelanto adelanto = new Adelanto();
        if (verificarCamposVacios()) {
            try {
                adelanto.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
                Date fecha = dateFecha.getDatoFecha();
                adelanto.setFechaPago(new java.sql.Date(fecha.getTime()));

                adelanto.setProveedorServicio(proveedorServicioSeleccionado);
                adelanto.setClienteEntrante(null);

                adelanto.setCantidad(Double.parseDouble(txtImporte.getText()));
                adelanto.setDescripcion(txtDescripcion.getText());
                if (rdbSol.isSelected() == true) {
                    adelanto.setMoneda("S");
                } else if (rdbDolar.isSelected() == true) {
                    adelanto.setMoneda("$");
                }
                if (rdbPagado.isSelected() == true) {
                    adelanto.setAdelantoEstado("PAGADO");
                } else if (rdbNoPagado.isSelected() == true) {
                    adelanto.setAdelantoEstado("NO PAGADO");
                }

                adelanto.setPersonal(IniciarSesion.getUsuario());
                int registros_afectados = 0;
                if (tipo_accion == ACCION_CREAR) {
                    try {

                        registros_afectados = gestionarAdelantoServicio.guardarAdelantoProveedor(adelanto);

                        if (registros_afectados == 1) {
//                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Adelanto", DesktopNotify.SUCCESS);
                            eliminarCamposSinProveedor();
                            inicializarTabla();

                            tipo_accion = ACCION_CREAR;
                            txtImporte.selectAll();
                            txtImporte.requestFocus();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeCreacion(this);
                        }
                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeCreacion(this);
                    }

                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {

                        adelanto.setCodigo(Integer.parseInt(lblCodigo.getText()));

                        registros_afectados = gestionarAdelantoServicio.modificarAdelantoProveedor(adelanto);

                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar la  Adelanto con Numero de Lote " + txtDestinatario.getText(), DesktopNotify.SUCCESS);
//                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            eliminarCamposSinProveedor();
                            inicializarTabla();
                            tipo_accion = ACCION_CREAR;
                            txtImporte.selectAll();
                            txtImporte.requestFocus();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un Liquidacion", DesktopNotify.INPUT_REQUEST);
                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeActualizacion(this);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        lblCodigo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAdelanto = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        lblPersonal4 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        txtDestinatario = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtImporte = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDescripcion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        dateFecha = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage24 = new rojerusan.RSLabelImage();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblcodhv = new javax.swing.JLabel();
        lbldni = new javax.swing.JLabel();
        lblcambio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalNoPagadoSoles = new javax.swing.JLabel();
        lblNoPagadoDolares = new javax.swing.JLabel();
        rdbSol = new com.icm.components.metro.RadioButtonMetroICM();
        rdbDolar = new com.icm.components.metro.RadioButtonMetroICM();
        rdbPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rdbNoPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rdbProveedor = new com.icm.components.metro.RadioButtonMetroICM();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel6 = new javax.swing.JPanel();
        btnNuevo = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        btnCheque = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        btnVerTodo = new rojeru_san.RSButton();
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
        previewCheque = new com.icm.components.metro.CheckBoxMetroICM();
        btn_limpiar = new rojeru_san.RSButton();

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
            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel1.setText("GESTIONAR DATOS DE ADELANTOS DE PROVEDORES ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoAdelanto.png"))); // NOI18N

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
                .addGap(44, 44, 44)
                .addComponent(lblCodigo)
                .addGap(87, 87, 87)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCodigo))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableAdelanto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableAdelanto.setAltoHead(30);
        tableAdelanto.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableAdelanto.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableAdelanto.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableAdelanto.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableAdelanto.setComponentPopupMenu(popMenu);
        tableAdelanto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableAdelanto.setFuenteFilas(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tableAdelanto.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableAdelanto.setGrosorBordeFilas(0);
        tableAdelanto.setGrosorBordeHead(0);
        tableAdelanto.setRowHeight(20);
        tableAdelanto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableAdelantoMousePressed(evt);
            }
        });
        tableAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableAdelantoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableAdelantoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableAdelantoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableAdelanto);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 200, 10));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Destinatario.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 40, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 40));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 40, 50));

        txtDestinatario.setForeground(new java.awt.Color(0, 0, 204));
        txtDestinatario.setAccent(new java.awt.Color(204, 0, 51));
        txtDestinatario.setCaretColor(new java.awt.Color(0, 0, 204));
        txtDestinatario.setEnabled(false);
        txtDestinatario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDestinatario.setLabel("DESTINATARIO");
        jPanel4.add(txtDestinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 220, 60));

        txtImporte.setForeground(new java.awt.Color(0, 0, 204));
        txtImporte.setAccent(new java.awt.Color(204, 0, 51));
        txtImporte.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtImporte.setLabel("IMPORTE");
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtImporteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });
        jPanel4.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 220, 70));

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setLabel("DESCRIPCIÓN");
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });
        jPanel4.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 340, 70));

        rSLabelImage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Pago.png"))); // NOI18N
        jPanel4.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 40, 40));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 40, 40));

        dateFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFecha.setPlaceholder("FECHA ");
        jPanel4.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 180, 30));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 40, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("T/C : ");

        lblcodhv.setText("0.0");

        lbldni.setForeground(new java.awt.Color(65, 94, 255));
        lbldni.setText("0000000000");

        lblcambio.setText("cambio");
        lblcambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcambioMouseClicked(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(65, 94, 255));
        jLabel11.setText("Total No  Pagado US$ :");

        jLabel10.setForeground(new java.awt.Color(65, 94, 255));
        jLabel10.setText("Total No Pagado  S/.:");

        lblTotalNoPagadoSoles.setForeground(new java.awt.Color(255, 82, 54));
        lblTotalNoPagadoSoles.setText("0.0");

        lblNoPagadoDolares.setForeground(new java.awt.Color(255, 82, 54));
        lblNoPagadoDolares.setText("0.0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbldni, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalNoPagadoSoles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoPagadoDolares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblcambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(lblcodhv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblcodhv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldni)
                    .addComponent(lblcambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTotalNoPagadoSoles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblNoPagadoDolares))
                .addGap(9, 9, 9))
        );

        rSPanelShadow1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 390, 120));

        rdbSol.setBorder(null);
        buttonGroup4.add(rdbSol);
        rdbSol.setText("Sol (S/.)");
        jPanel4.add(rdbSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 80, -1));

        rdbDolar.setBorder(null);
        buttonGroup4.add(rdbDolar);
        rdbDolar.setSelected(true);
        rdbDolar.setText("Dolar ($)");
        rdbDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDolarActionPerformed(evt);
            }
        });
        jPanel4.add(rdbDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, -1, -1));

        rdbPagado.setBorder(null);
        buttonGroup3.add(rdbPagado);
        rdbPagado.setText("PAGADO");
        jPanel4.add(rdbPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        rdbNoPagado.setBorder(null);
        buttonGroup3.add(rdbNoPagado);
        rdbNoPagado.setSelected(true);
        rdbNoPagado.setText("NO PAGADO");
        jPanel4.add(rdbNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        rdbProveedor.setBorder(null);
        rdbProveedor.setSelected(true);
        rdbProveedor.setText("PROVEEDOR");
        rdbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(rdbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCheque.setBackground(new java.awt.Color(65, 94, 255));
        btnCheque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCheque.setText("CHEQUE");
        btnCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChequeActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setIconTextGap(0);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnVerTodo.setBackground(new java.awt.Color(65, 94, 255));
        btnVerTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnVerTodo.setText("VER TODO");
        btnVerTodo.setColorHover(new java.awt.Color(253, 173, 1));
        btnVerTodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVerTodo.setIconTextGap(0);
        btnVerTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodoActionPerformed(evt);
            }
        });

        txtBuscarTipoTrabajador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarTipoTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarTipoTrabajador.setPrompt("BUSCAR POR RUC, RAZON SOCIAL");
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

        previewCheque.setBorder(null);
        previewCheque.setText("PREVIEW");
        previewCheque.setDark(false);
        previewCheque.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        previewCheque.setHideActionText(true);
        previewCheque.setIconTextGap(1);
        previewCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewChequeActionPerformed(evt);
            }
        });

        btn_limpiar.setBackground(new java.awt.Color(65, 94, 255));
        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Limpiar.png"))); // NOI18N
        btn_limpiar.setText("LIMPIAR");
        btn_limpiar.setColorHover(new java.awt.Color(253, 173, 1));
        btn_limpiar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_limpiar.setIconTextGap(2);
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(previewCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVerTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(previewCheque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rSPanelShadow2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel4.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1290, 60));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1307, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
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
//        listaLiquidacion = gestionarLiquidacionServicio.mostrarLiquidacions(Liquidacion.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }
    int fila;
    private void tableAdelantoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdelantoMousePressed
        try {
            limpiarCampos();
            tipo_accion = ACCION_MODIFICAR;
            btnCheque.setEnabled(true);
            fila = tableAdelanto.getSelectedRow();
            String codigo = tableAdelanto.getValueAt(fila, 0).toString();

            adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCodigoProveedor(Integer.parseInt(codigo));

            llenarCamposParaModificar(adelantoSeleccionado);
            inabilitarCampos(true);
            inabilitarBotones(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_tableAdelantoMousePressed

    private void tableAdelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyReleased
        fila = tableAdelanto.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableAdelantoKeyReleased

    private void tableAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_FACTURA) {
//                    obtenerLiquidacion();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableAdelantoKeyTyped

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        ChequeProveedor cheque = new ChequeProveedor();
        cheque.setProveedorServicio(proveedorServicioSeleccionado);
        cheque.setEntregadoA(proveedorServicioSeleccionado.getRazonSocial());
        cheque.setConcepto("POR PROVEEDOR");

        cheque.setMonto(adelantoSeleccionado.getCantidad());
        if (rdbDolar.isSelected()) {
            cheque.setMoneda("$");
        } else {
            cheque.setMoneda("S/.");

        }
        FormGestionarChequeProveedorServicio a = new FormGestionarChequeProveedorServicio(null, true, cheque);

        if (previewCheque.isSelected()) {
            a.llenarCamposTexto(cheque);
            a.inabilitarCampos(true);
            a.inabilitarBotones(true);
            a.tipo_accion = a.ACCION_AGREGAR;
            a.txtConcepto.selectAll();
            a.txtConcepto.requestFocus();
            a.setVisible(true);
        } else {
            a.tipo_accion = a.ACCION_AGREGAR;
            a.llenarCamposTexto(cheque);
            a.guardarCheque();
            a.imprimir();
            a.setVisible(false);

        }
        previewCheque.setSelected(false);
    }//GEN-LAST:event_btnChequeActionPerformed

    private void txtBuscarTipoTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyPressed
        //        try {
        //            if (!txtBuscarLiquidacion.getText().equals("")) {
        //                listaLiquidacion = null;
        //                listaLiquidacion = gestionarLiquidacionServicio.buscarLiquidacionPorNombre(txtBuscarLiquidacion.getText().trim());
        //                obtenerDatosTabla();
        //            } else {
        //                listaLiquidacion = null;
        //                obtenerDatosParaTabla();
        //                txtBuscarLiquidacion.setText("");
        //            }
        //
        //        } catch (Exception ex) {
        //            Mensaje.mostrarErrorDeConsulta(this);
        //        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            tipo_accion = ACCION_CREAR;
            proveedorServicioSeleccionado = null;
            limpiarTabla();
            limpiarCampos();
            inabilitarBotones(true);
            inabilitarCampos(true);
//            abrirFormularioParaCliente();

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtImporte.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void tableAdelantoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyPressed

//        int filamodificar = tableLiquidacion.getSelectedRow();
//        if (filamodificar >= 0) {
//            txtCodigo.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtLote.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtTmh.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtH2O.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtTms.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//
//            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("YYYY-MM-dd");
//            String strFecha = tableLiquidacion.getValueAt(filamodificar, 1).toString();
//            Date fecha = null;
//            try {
//                fecha = formatoDelTexto.parse(strFecha);
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//            dateFecha.setDatoFecha(fecha);
//
//            txtLeyAu.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtLeyAg.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtInter.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtRec.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtMaquilla.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtFactor.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//
//            txtConscn.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtEscalador.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txt$tms.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//
//            txtTotalUS.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//
//            //ESTADO LIQUIDACION
//            if (tableLiquidacion.getValueAt(filamodificar, 20).toString().equals("N")) {
//                rbtn.setSelected(true);
//                rbtv.setSelected(false);
//            } else if (tableLiquidacion.getValueAt(filamodificar, 20).toString().equals("V")) {
//                rbtn.setSelected(false);
//                rbtv.setSelected(true);
//            }
//
//            txtClienteEntrante.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//            txtProcedencia.setText(tableLiquidacion.getValueAt(filamodificar, 0).toString());
//        } else {
//            JOptionPane.showMessageDialog(this, "No ha seleccionado ");
//        }
        fila = tableAdelanto.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableAdelantoKeyPressed

    private void lblcambioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcambioMouseClicked
        String cambio = JOptionPane.showInputDialog(rdbNoPagado, "Ingrese Cambio");
        try {
            if (cambio != null) {
                if (!cambio.equals("")) {
                    gestionarCambioServicio.modificarCambios(cambio);
                    inicializarCambio();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_lblcambioMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            if (proveedorServicioSeleccionado != null) {
                print = gestionarAdelantoReporteServicio.mostrarAdelantoProveedor(proveedorServicioSeleccionado, lblTotalNoPagadoSoles, lblNoPagadoDolares);

            } else {
                Mensaje.mostrarMensajeDefinido(this, "Seleccione un proveedor");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    boolean actualizarDescuentoAdelanto(Adelanto adelanto) {
        Valorizacion valorizacion = new Valorizacion();
        valorizacion.setCodigo(Integer.parseInt(FormGestionarValorizacionDetalle.txtCodigoValorizacion.getText()));
        adelanto.setValorizacion(valorizacion);
        adelanto.setAdelantoEstado("S");
        return gestionarAdelantoServicio.actualizarValorizacionAdelanto(adelanto) == 1;
    }
    private void btnVerTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodoActionPerformed

        if (proveedorServicioSeleccionado != null) {
            FormListaTodosAdelantoProveedor formlistaProveedorCliente = new FormListaTodosAdelantoProveedor(null, true, proveedorServicioSeleccionado);
            formlistaProveedorCliente.setVisible(true);
            inicializarTabla();
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Seleccionar un proveedor");

        }
    }//GEN-LAST:event_btnVerTodoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscarTipoTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyReleased
        try {
            String texto = txtBuscarTipoTrabajador.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarAdelantoServicio.mostrarTodosProveedor(8, tableAdelanto, texto);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyReleased

    private void rdbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbProveedorActionPerformed
        try {
            tipo_accion = ACCION_CREAR;
            proveedorServicioSeleccionado = null;
            limpiarTabla();
            limpiarCampos();

            inabilitarCampos(true);
            inabilitarBotones(true);
            abrirParaSeleccionarProveedor();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_rdbProveedorActionPerformed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dateFecha.requestFocus();

        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void previewChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previewChequeActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        tipo_accion = ACCION_CREAR;
        txtDescripcion.setText("");
        txtImporte.setText("");
        rdbSol.setSelected(true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void rdbDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbDolarActionPerformed

//    private void obtenerLiquidacionSeleccionado() {
    //        try {
    //            String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
    //            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
    //        } catch (Exception e) {
    //            JOptionPane.showMessageDialog(null, e.getMessage());
    //        }
    //
    //    }
    void actualizarEstadoAdelanto() {

    }

    private void eliminarCamposSinProveedor() {
        txtDescripcion.setText("");
        txtImporte.setText("");
        dateFecha.setDatoFecha(new Date());
        rdbNoPagado.setSelected(true);
        rdbSol.setSelected(true);
        txtDestinatario.setText(proveedorServicioSeleccionado.getRazonSocial());
    }

    private void abrirParaSeleccionarProveedor() {
        FormlistaProvedorServicio formlistaProvedorServicio = new FormlistaProvedorServicio(null, true);
        formlistaProvedorServicio.setVisible(true);
        if (proveedorServicioSeleccionado != null) {

            limpiarCampos();
            txtDestinatario.setText(proveedorServicioSeleccionado.getRazonSocial() + " : " + proveedorServicioSeleccionado.getRuc());
            lbldni.setText(proveedorServicioSeleccionado.getRuc());
            lbldni.setForeground(Color.red);
            inicializarTabla();
            txtImporte.selectAll();
            txtImporte.requestFocus();

        }
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtDestinatario);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtDescripcion);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtImporte);
        aux = aux + contador;
        return aux == 3;
    }

    private void inabilitarBotones(Boolean v) {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(v);
        btnImprimir.setEnabled(v);
        btnVerTodo.setEnabled(v);
        btnCheque.setEnabled(v);
        btn_limpiar.setEnabled(v);
        previewCheque.setEnabled(v);
    }

    void llenarDatosAgregar() {
//        txtInter.setText("1210.90");
//        txtRec.setText("90");
//        txtMaquilla.setText("240");
//        txtFactor.setText("1.1023");
//        txtConscn.setText("20.00");
//        txtEscalador.setText("0");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCheque;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnNuevo;
    private rojeru_san.RSButton btnVerTodo;
    private rojeru_san.RSButton btn_limpiar;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private rojeru_san.componentes.RSDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodigo;
    public static javax.swing.JLabel lblNoPagadoDolares;
    private javax.swing.JLabel lblPersonal4;
    public static javax.swing.JLabel lblTotalNoPagadoSoles;
    public static javax.swing.JLabel lblcambio;
    private javax.swing.JLabel lblcodhv;
    public static javax.swing.JLabel lbldni;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private com.icm.components.metro.CheckBoxMetroICM previewCheque;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private com.icm.components.metro.RadioButtonMetroICM rdbDolar;
    private com.icm.components.metro.RadioButtonMetroICM rdbNoPagado;
    private com.icm.components.metro.RadioButtonMetroICM rdbPagado;
    public static com.icm.components.metro.RadioButtonMetroICM rdbProveedor;
    private com.icm.components.metro.RadioButtonMetroICM rdbSol;
    public static rojerusan.RSTableMetro tableAdelanto;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDestinatario;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtImporte;
    // End of variables declaration//GEN-END:variables

    private void obtenerLiquidacion() {
//        String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
//
//        try {
//            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
//            if (TIPO_USUARIO == TIPO_FACTURA) {
//                FormRegistroFactura.liquidacionSeleccionada = liquidacionSeleccionado;
//                this.dispose();
//            } else {
//                inabilitarBotones(false);
//            }
//
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }
//
    }

    private void obtenerAdelantoSeleccionado() {
        try {
            String codigo = tableAdelanto.getValueAt(fila, 0).toString();
            adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCodigoProveedor(Integer.parseInt(codigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void metodoParaEliminar() {
        try {
            obtenerAdelantoSeleccionado();
            if (adelantoSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    int registros_afectados = gestionarAdelantoServicio.eliminarProveedorAdelanto(adelantoSeleccionado);
                    if (registros_afectados == 1) {
//                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar un Adelanto", DesktopNotify.SUCCESS);
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
