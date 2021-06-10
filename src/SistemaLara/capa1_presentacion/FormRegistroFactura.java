/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FormNotificacionPedirLote;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.NumeroToLetras;
import SistemaLara.capa1_presentacion.util.Numero_a_Letra;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarFacturaReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarFacturaServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.ProveedorServicio;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author XGamer
 */
public class FormRegistroFactura extends javax.swing.JDialog {

    private GestionarFacturaServicio gestionarFacturaServicio;
    public static Factura facturaSeleccionado;
    public static ProveedorServicio proveedorServicioSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;
    private int tipo_accion;
    DefaultTableModel modelo;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private final int ACCION_CREAR_DETALLE = 5;
    private final int ACCION_MODIFICAR_DETALLE = 6;
    private int TIPO_ACCION_DETALLE = 0;
    private GestionarFacturaReporteServicio gestionarFacturaReporteServicio;
    private int codigoFactura;
    private JasperPrint print = null;
    public static Liquidacion liquidacionSeleccionada;
    private GestionarLiquidacionServicio gestionarLiquidacionServicio;

    public FormRegistroFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            gestionarFacturaServicio = new GestionarFacturaServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            inicializarTablaColumnas();
//            inicializarTabla();

            txtProveedor.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(false);
            inabilitarCampos(false);
            inabilitarCamposFacturaDetalle(false);
            inabilitarBotonesDetalle(false);
                 inicializador = 1;
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public FormRegistroFactura(java.awt.Frame parent, boolean modal, Factura factura) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            tipo_accion = ACCION_MODIFICAR;
            gestionarFacturaServicio = new GestionarFacturaServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            inicializarTablaColumnas();
//            inicializarTabla();
            gestionarFacturaReporteServicio = new GestionarFacturaReporteServicio();
            btnGuardar.setEnabled(true);
            btnImprimir.setEnabled(true);
            btnNuevo3.setEnabled(false);
            btnGuardarDetalle.setEnabled(false);
            //  btnActualizarDetalle.setEnabled(false);
            this.print = null;
            inabilitarCamposFacturaDetalle(false);
            llenarDatosCampos(factura);
            inicializador = 1;

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    private void llenarDatosCampos(Factura factura) {
        facturaSeleccionado = factura;
        codigoFactura = factura.getCodigo();
        proveedorServicioSeleccionado = factura.getProveedorServicio();
        txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
        txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());

        txtRuc.setText(proveedorServicioSeleccionado.getRuc());
        txtGuian.setText(factura.getGuian());
        txtDescripcion.setText(factura.getDescripcion());
        txtCodigoUnico.setText(factura.getCodigoUnico());
        txtNroFactura.setText(factura.getNroFactura());
        dataFecha.setDatoFecha(factura.getFecha());
        if (factura.getMoneda().equals("$")) {
            rbDolares.setSelected(true);
        } else {
            rbSoles.setSelected(true);
        }

        lblLectura.setText("" + factura.getLectura());

        txtTotal.setText("" + Verificador.devolverRedondeoConCero(factura.getTotal().toString()));
        txtValor.setText("" + Verificador.devolverRedondeoConCero(factura.getValorVenta().toString()));
        txtIGV.setText("" + Verificador.devolverRedondeoConCero(factura.getIgv().toString()));

        llenarTablaFacturaDetalle();
        metodoParaMetodoMoneda();
        inicializador = 1;
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
//        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("UNIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("PRECIO UNITARIO", "java.lang.String"));
        tabla.agregarColumna(new Columna("IMPORTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("ADELANTO", "java.lang.String"));
        tabla.agregarColumna(new Columna("CODIGO LIQUIDACION", "java.lang.String"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.String"));

        //tabla.agregarColumna(new Columna("CODIGO ", "java.lang.Integer"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableitemFactura.setModel(modeloTabla);

        tableitemFactura.getColumn(tableitemFactura.getColumnName(6)).setWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(6)).setMinWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(6)).setMaxWidth(0);
    }

    void llenarTablaFacturaDetalle() {
        ModeloTabla modeloTabla = (ModeloTabla) tableitemFactura.getModel();
        Fila filaTabla;
        modeloTabla.eliminarTotalFilas();
        if (facturaSeleccionado.getListaFacturaDetalle().size() == 0) {
            Mensaje.mostrarAdvertencia(this, "No hay detalles facturas en la factura");
        } else {
            for (FacturaDetalle facturaDetalle : facturaSeleccionado.getListaFacturaDetalle()) {
                filaTabla = new Fila();

                filaTabla.agregarValorCelda(facturaDetalle.getCantidad());
                filaTabla.agregarValorCelda(facturaDetalle.getUnidad());
                filaTabla.agregarValorCelda(facturaDetalle.getDescripcion());
                filaTabla.agregarValorCelda(facturaDetalle.getPrecioUnitario());
                filaTabla.agregarValorCelda(facturaDetalle.getImporte());
                filaTabla.agregarValorCelda(facturaDetalle.getAdelanto());
                filaTabla.agregarValorCelda(facturaDetalle.getLiquidacion().getCodigo());
                filaTabla.agregarValorCelda(facturaDetalle.getLiquidacion().getLote());

                modeloTabla.agregarFila(filaTabla);
            }
            modeloTabla.refrescarDatos();

        }

    }

    public void limpiarTablaiItemFactura() {
        ModeloTabla modelo = (ModeloTabla) tableitemFactura.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            inabilitarCampos(false);
            limpiarCampos();
            limpiarTablaiItemFactura();
            gestionarFacturaServicio.mostrarFacturas(1, tableitemFactura);

        } catch (Exception e) {

        }

    }

    private void limpiarCampos() {
        txtNroFactura.setText("F001-");
        rbDolares.setSelected(true);
        txtProveedor.setText("");
        txtRuc.setText("");
        txtDiireccion.setText("");
        txtGuian.setText("");
        txtValor.setText("0.0");
        txtIGV.setText("");
        txtTotal.setText("");
        lblLectura.setText("");
        dataFecha.setDatoFecha(new Date());
        
        descartivarDetalle();

    }

    private void descartivarDetalle() {

    }

    private void inabilitarCampos(boolean estado) {
        txtDiireccion.setEnabled(false);
        txtGuian.setEnabled(estado);
        txtDescripcion.setEnabled(estado);
        txtCodigoUnico.setEnabled(estado);
        txtNroFactura.setEnabled(false);
        txtProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        rbDolares.setEnabled(estado);
        rbSoles.setEnabled(estado);
        btnProveedorServicio.setEnabled(estado);
        dataFecha.setEnabled(estado);
    }

    private void inabilitarCamposFacturaDetalle(boolean estado) {
        txtCantidadDetalle.setEnabled(estado);
        txtDescripcionDetalle.setEnabled(estado);
        txtImporteDetalle.setEnabled(estado);
        txtPrecioUnitarioDetalle.setEnabled(estado);
        txtUnidadDetalle.setEnabled(estado);

        rbNoAdelanto.setEnabled(estado);
        rbSiAdelanto.setEnabled(estado);

    }

    private void llenarCamposParaModificar(Factura factura) {
//        limpiarCampos();
//        lblCodigo.setText(String.valueOf(factura.getCodigo()));
//        txtProveedorServicio.setText(factura.getProveedorServicio().getRazonSocial());
//        dataFecha.setDatoFecha(factura.getFecha());
//        txtNroFactura.setText(factura.getNroFactura());
//        txtPeso.setText("" + factura.getPeso());
//        txtPrecio.setText("" + factura.getPrecio());
//        txtPrecioPortTonelada.setText("" + factura.getTotal());
//        txtDetraccion.setText("" + factura.getDetraccion());
//        txtAdelanto.setText("" + factura.getAdelanto());
//        txtImporte.setText("" + factura.getImporte());
//        txtNroOperacion.setText(factura.getNroOperacion());
//        dataFechaPago.setDatoFecha(factura.getFechaPago());
//        proveedorServicioSeleccionado = factura.getProveedorServicio();
//        if (factura.getEstadoFactura().equals("Pagado")) {
//            rbPagado.setSelected(true);
//        } else {
//            rbNoPagado.setSelected(true);
//        }
//        if (factura.getDescontar().equals("Si")) {
//            swDetraccion.setActivado(true);
//        } else {
//            swDetraccion.setActivado(false);
//        }
//        for (int i = 0; i < cboTipoCliente.getItemCount(); i++) {
//            if (factura.getTipoCliente().getDescripcion().equals(cboTipoCliente.getItemAt(i).getDescripcion())) {
//                cboTipoCliente.setSelectedIndex(i);
//            }
//        }
//        txtCuenta.setText(proveedorServicioSeleccionado.getCuente());
//        facturaSeleccionado = factura;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        lblCodigo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtProveedor = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        btnProveedorServicio = new rojeru_san.RSButton();
        txtNroFactura = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        txtGuian = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        txtRuc = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCodigoUnico = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage20 = new rojerusan.RSLabelImage();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        txtDiireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        dataFecha = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage22 = new rojerusan.RSLabelImage();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        txtDescripcion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage24 = new rojerusan.RSLabelImage();
        rbDolares = new RadioButton.FiveCodRadioButton();
        rbSoles = new RadioButton.FiveCodRadioButton();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage25 = new rojerusan.RSLabelImage();
        rSLabelImage26 = new rojerusan.RSLabelImage();
        txtCantidadDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage27 = new rojerusan.RSLabelImage();
        txtUnidadDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage28 = new rojerusan.RSLabelImage();
        txtDescripcionDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage29 = new rojerusan.RSLabelImage();
        txtPrecioUnitarioDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage30 = new rojerusan.RSLabelImage();
        txtImporteDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jLabel6 = new javax.swing.JLabel();
        rbNoAdelanto = new RadioButton.FiveCodRadioButton();
        rbSiAdelanto = new RadioButton.FiveCodRadioButton();
        panelSumas1 = new javax.swing.JPanel();
        btnMostrarLiquidacion = new rojeru_san.RSButton();
        btnGuardarDetalle = new rojeru_san.RSButton();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel6 = new javax.swing.JPanel();
        btnCancelar = new rojeru_san.RSButton();
        btnNuevo3 = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableitemFactura = new rojerusan.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        lblLectura = new javax.swing.JTextField();
        panelSumas = new javax.swing.JPanel();
        lblIgv = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblValorVenta = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnImprimir = new rojeru_san.RSButton();

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
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factura.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE FACTURA");

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton2.setFont(new java.awt.Font("Roboto Bold", 1, 20)); // NOI18N
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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
            .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProveedor.setForeground(new java.awt.Color(0, 0, 204));
        txtProveedor.setAccent(new java.awt.Color(204, 0, 51));
        txtProveedor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtProveedor.setLabel("Proveedor Servicios");
        jPanel3.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 310, 60));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Direccion.png"))); // NOI18N
        jPanel3.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 40, 30));

        btnProveedorServicio.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedorServicio.setText("...");
        btnProveedorServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorServicioActionPerformed(evt);
            }
        });
        jPanel3.add(btnProveedorServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 40, 30));

        txtNroFactura.setForeground(new java.awt.Color(0, 0, 204));
        txtNroFactura.setAccent(new java.awt.Color(204, 0, 51));
        txtNroFactura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNroFactura.setLabel("NUMERO N° :");
        txtNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyTyped(evt);
            }
        });
        jPanel3.add(txtNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, -10, 190, 60));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dolar.png"))); // NOI18N
        jPanel3.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 40, 30));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 40, 30));

        txtGuian.setForeground(new java.awt.Color(0, 0, 204));
        txtGuian.setAccent(new java.awt.Color(204, 0, 51));
        txtGuian.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtGuian.setLabel("GUIA DE REM. N° :");
        txtGuian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGuianKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGuianKeyTyped(evt);
            }
        });
        jPanel3.add(txtGuian, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 310, 60));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 40, 30));

        txtRuc.setForeground(new java.awt.Color(0, 0, 204));
        txtRuc.setAccent(new java.awt.Color(204, 0, 51));
        txtRuc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRuc.setLabel("RUC:");
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });
        jPanel3.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 190, 60));

        txtCodigoUnico.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoUnico.setText("CODIGO UNICO:030026614");
        txtCodigoUnico.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigoUnico.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigoUnico.setLabel("CODIGO UNICO");
        txtCodigoUnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoUnicoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoUnicoKeyTyped(evt);
            }
        });
        jPanel3.add(txtCodigoUnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 310, 60));

        rSLabelImage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 40, 30));

        rSLabelImage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoProveedor.png"))); // NOI18N
        jPanel3.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        txtDiireccion.setForeground(new java.awt.Color(0, 0, 204));
        txtDiireccion.setAccent(new java.awt.Color(204, 0, 51));
        txtDiireccion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDiireccion.setLabel("Direccion");
        txtDiireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiireccionKeyTyped(evt);
            }
        });
        jPanel3.add(txtDiireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 310, 60));

        dataFecha.setColorBackground(new java.awt.Color(64, 95, 255));
        dataFecha.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataFecha.setColorForeground(new java.awt.Color(64, 95, 255));
        dataFecha.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dataFecha.setPlaceholder("FECHA ");
        dataFecha.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel3.add(dataFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 190, 30));

        rSLabelImage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 40, 30));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel3.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 40, 30));

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setText("CONCESION: EMPRESA MINERA MIRAFLORES");
        txtDescripcion.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setLabel("CONSECION:");
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        jPanel3.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 310, 60));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        jPanel3.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 40, 30));

        buttonGroup1.add(rbDolares);
        rbDolares.setForeground(new java.awt.Color(65, 94, 255));
        rbDolares.setText("Dolares");
        rbDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDolaresActionPerformed(evt);
            }
        });
        jPanel3.add(rbDolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 80, -1));

        buttonGroup1.add(rbSoles);
        rbSoles.setForeground(new java.awt.Color(65, 94, 255));
        rbSoles.setText("Soles");
        rbSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSolesActionPerformed(evt);
            }
        });
        jPanel3.add(rbSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 70, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setForeground(new java.awt.Color(0, 0, 204));
        txtBuscar.setAccent(new java.awt.Color(204, 0, 51));
        txtBuscar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtBuscar.setLabel("INGRESE N° LOTE");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 210, 60));

        rSLabelImage25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        jPanel4.add(rSLabelImage25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        rSLabelImage26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        txtCantidadDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtCantidadDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtCantidadDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCantidadDetalle.setLabel("CANTIDAD :");
        txtCantidadDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtCantidadDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 210, 60));

        rSLabelImage27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 40));

        txtUnidadDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtUnidadDetalle.setText("TMS");
        txtUnidadDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtUnidadDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtUnidadDetalle.setLabel("UNIDAD :");
        txtUnidadDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnidadDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtUnidadDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 210, 60));

        rSLabelImage28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        txtDescripcionDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcionDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcionDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcionDetalle.setLabel("DESCRIPCIÓN  :");
        txtDescripcionDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtDescripcionDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 390, 60));

        rSLabelImage29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 40, 40));

        txtPrecioUnitarioDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtPrecioUnitarioDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtPrecioUnitarioDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPrecioUnitarioDetalle.setLabel("P. UNITARIO :");
        txtPrecioUnitarioDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioUnitarioDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioUnitarioDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtPrecioUnitarioDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 160, 60));

        rSLabelImage30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 40, 40));

        txtImporteDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtImporteDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtImporteDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtImporteDetalle.setLabel("IMPORTE :");
        txtImporteDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtImporteDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtImporteDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 170, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(65, 94, 255));
        jLabel6.setText("ADALANTO");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, 20));

        buttonGroup2.add(rbNoAdelanto);
        rbNoAdelanto.setSelected(true);
        rbNoAdelanto.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbNoAdelanto.setForeground(new java.awt.Color(65, 94, 255));
        rbNoAdelanto.setText("NO");
        rbNoAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoAdelantoActionPerformed(evt);
            }
        });
        jPanel4.add(rbNoAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 258, 60, 20));

        buttonGroup2.add(rbSiAdelanto);
        rbSiAdelanto.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbSiAdelanto.setForeground(new java.awt.Color(65, 94, 255));
        rbSiAdelanto.setText("SI");
        rbSiAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSiAdelantoActionPerformed(evt);
            }
        });
        jPanel4.add(rbSiAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 258, 50, 20));

        panelSumas1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder2.setShadowSize(4);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        panelSumas1.setBorder(dropShadowBorder2);

        btnMostrarLiquidacion.setBackground(new java.awt.Color(65, 94, 255));
        btnMostrarLiquidacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cambio.png"))); // NOI18N
        btnMostrarLiquidacion.setText("LIQUIDACION");
        btnMostrarLiquidacion.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnMostrarLiquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarLiquidacionActionPerformed(evt);
            }
        });

        btnGuardarDetalle.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnGuardarDetalle.setText("GUARDAR");
        btnGuardarDetalle.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnGuardarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSumas1Layout = new javax.swing.GroupLayout(panelSumas1);
        panelSumas1.setLayout(panelSumas1Layout);
        panelSumas1Layout.setHorizontalGroup(
            panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSumas1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMostrarLiquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelSumas1Layout.setVerticalGroup(
            panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSumas1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMostrarLiquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnGuardarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(panelSumas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 170, 100));

        rSPanelShadow1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 470, 290));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnCancelar.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo3.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo3.setText("NUEVO");
        btnNuevo3.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo3ActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rSPanelShadow2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 260, 90));

        tableitemFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableitemFactura.setAltoHead(30);
        tableitemFactura.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableitemFactura.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableitemFactura.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableitemFactura.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableitemFactura.setComponentPopupMenu(popMenu);
        tableitemFactura.setFuenteFilas(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableitemFactura.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableitemFactura.setFuenteHead(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableitemFactura.setGrosorBordeFilas(0);
        tableitemFactura.setGrosorBordeHead(0);
        tableitemFactura.setRowHeight(20);
        tableitemFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableitemFacturaMousePressed(evt);
            }
        });
        tableitemFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableitemFacturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableitemFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableitemFacturaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableitemFactura);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lblLectura, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelSumas.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder3.setShadowSize(4);
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        panelSumas.setBorder(dropShadowBorder3);

        lblIgv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIgv.setText("I.G.V:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotal.setText("TOTAL:");

        lblValorVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblValorVenta.setText("VALOR VENTA:");

        javax.swing.GroupLayout panelSumasLayout = new javax.swing.GroupLayout(panelSumas);
        panelSumas.setLayout(panelSumasLayout);
        panelSumasLayout.setHorizontalGroup(
            panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSumasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSumasLayout.createSequentialGroup()
                        .addComponent(lblValorVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSumasLayout.createSequentialGroup()
                        .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSumasLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lblTotal))
                            .addGroup(panelSumasLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(lblIgv)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIGV)
                            .addComponent(txtTotal))))
                .addContainerGap())
        );
        panelSumasLayout.setVerticalGroup(
            panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSumasLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorVenta)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIgv)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnImprimir.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(74, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelSumas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelSumas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed
    int fila;

    private void tableitemFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableitemFacturaMousePressed
        try {
            TIPO_ACCION_DETALLE = ACCION_MODIFICAR_DETALLE;
            btnGuardarDetalle.setEnabled(true);
            //btnActualizarDetalle.setEnabled(true);
            fila = tableitemFactura.getSelectedRow();

            String codigo = tableitemFactura.getValueAt(fila, 6).toString();

            FacturaDetalle facturaDetalle = facturaSeleccionado.buscarFacturaDetalle(Integer.parseInt(codigo));

            llenarCamposDetalle(facturaDetalle);
//            btnGuardarDetalle.setEnabled(false);
            txtCantidadDetalle.selectAll();
            txtCantidadDetalle.requestFocus();
            //  btnActualizarDetalle.setEnabled(true);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_tableitemFacturaMousePressed

    private void tableitemFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyReleased

    }//GEN-LAST:event_tableitemFacturaKeyReleased
    private void llenarCamposDetalle(FacturaDetalle facturaDetalle) {
        txtCantidadDetalle.setText(facturaDetalle.getCantidad());
        txtDescripcionDetalle.setText(facturaDetalle.getDescripcion());
        txtImporteDetalle.setText(facturaDetalle.getImporte());
        txtPrecioUnitarioDetalle.setText("" + facturaDetalle.getPrecioUnitario());
        txtUnidadDetalle.setText(facturaDetalle.getUnidad());
        liquidacionSeleccionada = facturaDetalle.getLiquidacion();
        if (facturaDetalle.getAdelanto().equals("Si")) {
            rbSiAdelanto.setSelected(true);
        } else {
            rbNoAdelanto.setSelected(true);

        }
        inabilitarCamposFacturaDetalle(true);
    }


    private void tableitemFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyTyped
        //        try {
        //            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        //                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
        //                    obtenerProcedencia();
        //                }
        //            }
        //        } catch (Exception e) {
        //            Mensaje.mostrarErrorSistema(this);
        //        }

    }//GEN-LAST:event_tableitemFacturaKeyTyped

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnProveedorServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorServicioActionPerformed
        obtenerClienteFormulario();

    }//GEN-LAST:event_btnProveedorServicioActionPerformed

    private void txtNroFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyPressed

    }//GEN-LAST:event_txtNroFacturaKeyPressed

    private void txtNroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyTyped

    }//GEN-LAST:event_txtNroFacturaKeyTyped

    private void txtGuianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGuianKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtGuianKeyPressed

    private void txtGuianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGuianKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuianKeyTyped

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyPressed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtCodigoUnicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoUnicoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtBuscar.requestFocus();
            txtBuscar.selectAll();
        }
    }//GEN-LAST:event_txtCodigoUnicoKeyPressed

    private void txtCodigoUnicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoUnicoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoUnicoKeyTyped

    private void txtDiireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiireccionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtGuian.requestFocus();
        }
    }//GEN-LAST:event_txtDiireccionKeyPressed

    private void txtDiireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiireccionKeyTyped

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtCodigoUnico.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                if (txtBuscar.getText().equals("")) {
                    return;
                }
                limpiarCamposDetalle();
                numeroLoteSeleccionado = Integer.parseInt(txtBuscar.getText().toString());
                TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
                if (numeroLoteSeleccionado != 0) {
                    liquidacionSeleccionada = null;
                    liquidacionSeleccionada = gestionarLiquidacionServicio.buscarLiquidacionPorLote(numeroLoteSeleccionado);
                    if (liquidacionSeleccionada != null) {
                        if (liquidacionSeleccionada != null) {
                            inabilitarCamposFacturaDetalle(true);
                            btnGuardarDetalle.setEnabled(true);
                            txtCantidadDetalle.setText(liquidacionSeleccionada.getTms());
                            if (txtRuc.getText().equals("20460352674")) {
                                txtDescripcionDetalle.setText("MINERAL AURIFERO BRUTO LOTE:" + liquidacionSeleccionada.getLote() + " LEY:" + liquidacionSeleccionada.getLeyau() + " PIO:" + liquidacionSeleccionada.getInter());
                            } else if (txtRuc.getText().equals("20536126440")) {
                                txtDescripcionDetalle.setText("MINERAL AURIFERO LOTE:" + liquidacionSeleccionada.getLote() + " LEY AU:" + liquidacionSeleccionada.getLeyau() + " OZ/TC");
                            }
                            txtPrecioUnitarioDetalle.setText(liquidacionSeleccionada.getStms());
                            txtImporteDetalle.setText(liquidacionSeleccionada.getTotalus());
                            txtCantidadDetalle.selectAll();
                            txtCantidadDetalle.requestFocus();
                            numeroLoteSeleccionado = 0;
                            guardarFacturaDetalle();
                            limpiarCamposDetalle();

                        } else {
                            btnGuardarDetalle.setEnabled(false);
                        }
                    } else {
                        Mensaje.mostrarMensajeDefinido(null, "El lote todabia no se ha registrado");
                        limpiarCamposDetalle();
                    }
                }
                numeroLoteSeleccionado = 0;
                txtBuscar.setText("");
            } catch (Exception e) {
                Mensaje.mostrarMensajeDefinido(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtCantidadDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadDetalleKeyPressed
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
            txtUnidadDetalle.selectAll();
            txtUnidadDetalle.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadDetalleKeyPressed

    private void txtCantidadDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadDetalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDetalleKeyTyped

    private void txtUnidadDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcionDetalle.selectAll();
            txtDescripcionDetalle.requestFocus();
        }
    }//GEN-LAST:event_txtUnidadDetalleKeyPressed

    private void txtUnidadDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadDetalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadDetalleKeyTyped

    private void txtDescripcionDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPrecioUnitarioDetalle.selectAll();
            txtPrecioUnitarioDetalle.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionDetalleKeyPressed

    private void txtDescripcionDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionDetalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionDetalleKeyTyped

    private void txtPrecioUnitarioDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCantidadDetalle.getText().equals("") || txtPrecioUnitarioDetalle.getText().equals("")) {
                txtImporteDetalle.selectAll();
                txtImporteDetalle.requestFocus();
            } else {
                double cantidad = Double.parseDouble(txtCantidadDetalle.getText());
                double punitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
                double importe = cantidad * punitario;
                txtImporteDetalle.setText(redondearDecimales(importe, 2) + "");
                txtImporteDetalle.selectAll();
                txtImporteDetalle.requestFocus();
            }

        }


    }//GEN-LAST:event_txtPrecioUnitarioDetalleKeyPressed

    private void txtPrecioUnitarioDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioDetalleKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioUnitarioDetalle.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioUnitarioDetalleKeyTyped

    private void txtImporteDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteDetalleKeyPressed
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
            guardarFacturaDetalle();
        }
    }//GEN-LAST:event_txtImporteDetalleKeyPressed

    private void txtImporteDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteDetalleKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtImporteDetalle.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteDetalleKeyTyped

    private void btnGuardarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDetalleActionPerformed

        guardarFacturaDetalle();
    }//GEN-LAST:event_btnGuardarDetalleActionPerformed
    private void guardarFacturaDetalle() {
        if (TIPO_ACCION_DETALLE == ACCION_CREAR_DETALLE) {
            agregarADetalle();
        } else if (TIPO_ACCION_DETALLE == ACCION_MODIFICAR_DETALLE) {
            modificarADetalle();
        }
        llenarCalculos();
        txtBuscar.requestFocus();
        txtBuscar.selectAll();
    }

    private void obtenerClienteFormulario() {
        FormlistaProvedorServicioFactura marca = new FormlistaProvedorServicioFactura(null, true, FormGestionarProveedorServicios.TIPO_FACTURA);
        marca.setVisible(true);
        if (proveedorServicioSeleccionado != null) {
            txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
            txtRuc.setText(proveedorServicioSeleccionado.getRuc());
            txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());
            txtGuian.selectAll();
            txtGuian.requestFocus();
        }
    }
    
    public static Double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    private void agregarADetalle() throws HeadlessException, NumberFormatException {
        FacturaDetalle facturaDetalle = new FacturaDetalle();
        if (liquidacionSeleccionada != null) {
            facturaDetalle.setCantidad(txtCantidadDetalle.getText());
            facturaDetalle.setDescripcion(txtDescripcionDetalle.getText());
            facturaDetalle.setUnidad(txtUnidadDetalle.getText());
            facturaDetalle.setPrecioUnitario(Double.parseDouble(txtPrecioUnitarioDetalle.getText()));
            facturaDetalle.setImporte(txtImporteDetalle.getText());
            facturaDetalle.setLiquidacion(liquidacionSeleccionada);
            facturaDetalle.setPersonal(IniciarSesion.getUsuario());
            if (rbNoAdelanto.isSelected()) {
                facturaDetalle.setAdelanto("No");
            } else {
                facturaDetalle.setAdelanto("Si");
            }
            if (tipo_accion == ACCION_CREAR) {
                facturaDetalle.setFacturaEstado("Crear");
            } else if (tipo_accion == ACCION_MODIFICAR) {
                facturaDetalle.setFacturaEstado("Nuevo Modificar");
            }
            if (!facturaSeleccionado.existeDetalleFactura(facturaDetalle)) {
                if (facturaSeleccionado.agregarFacturaDetalle(facturaDetalle)) {
                    inabilitarCamposFacturaDetalle(false);
                    llenarTablaFacturaDetalle();
                    btnGuardarDetalle.setEnabled(false);
                    //   btnActualizarDetalle.setEnabled(false);
                    limpiarCamposDetalle();
                    Mensaje.mostrarAfirmacionDeCreacion(this);
                    liquidacionSeleccionada = null;
                    TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
                } else {

                    Mensaje.mostrarAdvertenciaDeCreacion(this);
                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "El detalle de la factura ya existe");

            }
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Debe seleccionar una liquidacion");
            obtenerDatosFormLiquidacion();
        }

    }
    int inicializador = 0;

    void llenarCalculos() {
        if (inicializador == 1) {
            Double total = facturaSeleccionado.calcularTotal();
            txtValor.setText("" + Verificador.devolverRedondeoConCero(facturaSeleccionado.calcularValor().toString()));
            txtIGV.setText("" + Verificador.devolverRedondeoConCero(facturaSeleccionado.calcularIGV().toString()));
            txtTotal.setText("" + Verificador.devolverRedondeoConCero(total.toString()));

            String lectura = NumeroToLetras.NumeroALetras(total.toString());
            if (lectura != null) {
                if (rbDolares.isSelected()) {
                    lblLectura.setText("SON "+lectura + " DOLARES AMERICANOS");
                } else {
                    lblLectura.setText("SON "+lectura + " NUEVOS SOLES");

                }

            } else {
                lblLectura.setText("null");
            }
        }

    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed
    public static int numeroLoteSeleccionado = 0;
    private void btnMostrarLiquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarLiquidacionActionPerformed
        obtenerDatosFormLiquidacion();


    }//GEN-LAST:event_btnMostrarLiquidacionActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        opcionNuevo();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatosFactura();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarFacturaReporteServicio.mostrarRegistroPedido(facturaSeleccionado);
        } catch (Exception ex) {
            Mensaje.mostrarMensajeDefinido(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed

        //        obtenerLiquidacionSeleccionado();
        //        FormDatosLiquidacion liquidacion = new FormDatosLiquidacion(null, true, liquidacionSeleccionado);
        //        liquidacion.setVisible(true);
        //        inicializarTabla();
        //        liquidacionSeleccionado = null;
        //        inabilitarBotones(true);
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    public Double calcularPrecioUnitario() {
        Double importe = 0.00, cantidad = 0.00, calcultoPrecioUnitario = 0.00;
        if (txtCantidadDetalle.getText().equals("")) {
            txtCantidadDetalle.requestFocus();
            txtCantidadDetalle.selectAll();
        } else {
            importe = Double.parseDouble(txtImporteDetalle.getText());
            cantidad = Double.parseDouble(txtCantidadDetalle.getText());
            calcultoPrecioUnitario = importe / cantidad;
        }
        return calcultoPrecioUnitario;

    }

    private void modificarADetalle() {
        if (liquidacionSeleccionada != null) {
            Double precioUnitario = 0.0;
            if (txtPrecioUnitarioDetalle.getText().equals("")) {
                precioUnitario = calcularPrecioUnitario();
                precioUnitario = redondearDecimales(precioUnitario, 2);
            } else {
                precioUnitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
            }

            FacturaDetalle facturaDetalle = new FacturaDetalle();
            facturaDetalle.setCantidad(txtCantidadDetalle.getText());
            facturaDetalle.setDescripcion(txtDescripcionDetalle.getText());
            facturaDetalle.setUnidad(txtUnidadDetalle.getText());
            facturaDetalle.setPrecioUnitario(precioUnitario);
            facturaDetalle.setImporte(txtImporteDetalle.getText());
            facturaDetalle.setLiquidacion(liquidacionSeleccionada);
            facturaDetalle.setPersonal(IniciarSesion.getUsuario());
            if (rbNoAdelanto.isSelected()) {
                facturaDetalle.setAdelanto("No");
            } else {
                facturaDetalle.setAdelanto("Si");
            }
            if (facturaSeleccionado.modificarFacturaDetalle(facturaDetalle)) {
                llenarTablaFacturaDetalle();
                btnGuardarDetalle.setEnabled(false);
                limpiarCamposDetalle();
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                // btnActualizarDetalle.setEnabled(false);
                btnGuardarDetalle.setEnabled(false);
                liquidacionSeleccionada = null;
                TIPO_ACCION_DETALLE = ACCION_MODIFICAR_DETALLE;
            } else {
                Mensaje.mostrarAdvertenciaDeActualizacion(this);
            }

        }
    }

    private static boolean band() {
        if (Math.random() > .5) {
            return true;
        } else {
            return false;
        }
    }
    private void rbDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDolaresActionPerformed
        metodoParaMetodoMoneda();

    }//GEN-LAST:event_rbDolaresActionPerformed

    private void rbSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSolesActionPerformed
        metodoParaMetodoMoneda();
    }//GEN-LAST:event_rbSolesActionPerformed

    private void metodoParaMetodoMoneda() {
        if (rbDolares.isSelected()) {
            lblIgv.setText("IGV $:");
            lblValorVenta.setText("VALOR VENTA $:");
            lblTotal.setText("TOTAL $:");
            llenarCalculos();
        } else {
            lblIgv.setText("IGV S/.");
            lblValorVenta.setText("VALOR VENTA S/.");
            lblTotal.setText("TOTAL S/.");
            llenarCalculos();

        }

    }
    private void rbSiAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSiAdelantoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSiAdelantoActionPerformed

    private void rbNoAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoAdelantoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbNoAdelantoActionPerformed

    private void tableitemFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyPressed
        fila = tableitemFactura.getSelectedRow();

        if (evt.getKeyChar() == com.sun.glass.events.KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableitemFacturaKeyPressed
    private void limpiarCamposDetalle() {
        txtCantidadDetalle.setText("");
        txtDescripcionDetalle.setText("");
        txtImporteDetalle.setText("");
        txtPrecioUnitarioDetalle.setText("");
        txtUnidadDetalle.setText("TMS");
        liquidacionSeleccionada = null;

    }

    private void opcionNuevo() {
        btnGuardarDetalle.setEnabled(false);
        facturaSeleccionado = new Factura();
        tipo_accion = ACCION_CREAR;
        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
        limpiarCampos();
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(true);
        rbNoAdelanto.setSelected(true);
        rbDolares.setSelected(true);
        inabilitarCampos(true);
        inabilitarCamposFacturaDetalle(false);
        inabilitarBotonesDetalle(true);
        obtenerClienteFormulario();
        txtBuscar.setEnabled(true);
        limpiarTablaiItemFactura();

    }

    private void obtenerDatosFormLiquidacion() {
        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
        liquidacionSeleccionada = null;
        FormGestionarLiquidacion formGestionarLiquidacion = new FormGestionarLiquidacion(null, false, FormGestionarLiquidacion.TIPO_FACTURA);
        formGestionarLiquidacion.setVisible(true);
        if (liquidacionSeleccionada != null) {
            inabilitarCamposFacturaDetalle(true);
            btnGuardarDetalle.setEnabled(true);
            txtCantidadDetalle.setText(liquidacionSeleccionada.getTms());
            if (txtRuc.getText().equals("20460352674")) {
                txtDescripcionDetalle.setText("MINERAL AURIFERO BRUTO LOTE:" + liquidacionSeleccionada.getLote() + " LEY:" + liquidacionSeleccionada.getLeyau() + " PIO:" + liquidacionSeleccionada.getInter());
            } else if (txtRuc.getText().equals("20536126440")) {
                txtDescripcionDetalle.setText("MINERAL AURIFERO LOTE:" + liquidacionSeleccionada.getLote() + " LEY AU:" + liquidacionSeleccionada.getLeyau() + " OZ/TC");
            }
            txtPrecioUnitarioDetalle.setText(liquidacionSeleccionada.getStms());
            txtImporteDetalle.setText(liquidacionSeleccionada.getTotalus());
            txtCantidadDetalle.selectAll();
            txtCantidadDetalle.requestFocus();
        } else {
            btnGuardarDetalle.setEnabled(false);
        }
    }

    private void inabilitarBotonesDetalle(boolean estado) {
        btnMostrarLiquidacion.setEnabled(estado);
        btnGuardarDetalle.setEnabled(estado);

    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtProveedor);
        aux = aux + contador;

        contador = Verificador.verificadorCampos(txtDescripcion);
        aux = aux + contador;
        return aux == 2;
    }

    private void guardarDatosFactura() {
        if (verificarCamposVacios()) {
            if (lblLectura.getText().equals("null")) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Error al consumir el webservice. Activar el servidor o comicarse con soporte ", DesktopNotify.ERROR);
                return;
            }
            if (facturaSeleccionado.getListaFacturaDetalle().size() > 0) {
                Date fecha = dataFecha.getDatoFecha();
                facturaSeleccionado.setFecha(new java.sql.Date(fecha.getTime()));
                facturaSeleccionado.setGuian(txtGuian.getText());
                facturaSeleccionado.setProveedorServicio(proveedorServicioSeleccionado);
                facturaSeleccionado.setDireccion(txtDiireccion.getText());
                facturaSeleccionado.setDescripcion(txtDescripcion.getText());
                facturaSeleccionado.setCodigoUnico(txtCodigoUnico.getText());
                facturaSeleccionado.setValorVenta(Double.parseDouble(txtValor.getText()));
                facturaSeleccionado.setIgv(Double.parseDouble(txtIGV.getText()));
                facturaSeleccionado.setTotal(Double.parseDouble(txtTotal.getText()));
                facturaSeleccionado.setPersonal(IniciarSesion.getUsuario());
                if (rbDolares.isSelected()) {
                    facturaSeleccionado.setMoneda("$");
                } else {
                    facturaSeleccionado.setMoneda("S/.");
                }
                facturaSeleccionado.setRnc("");
                facturaSeleccionado.setLectura(lblLectura.getText());

                int registros_afectados;
                if (tipo_accion == ACCION_CREAR) {
                    try {
                        registros_afectados = gestionarFacturaServicio.guardarFactura(facturaSeleccionado);
                        if (registros_afectados == 1) {
                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            this.dispose();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeCreacion(this);
                        }
                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeCreacion(this);
                    }
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {
                        registros_afectados = gestionarFacturaServicio.modificarFactura(facturaSeleccionado);
                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de actualizar la factura numero  " + facturaSeleccionado.getNroFactura(), DesktopNotify.SUCCESS);
                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            btnImprimir.setEnabled(true);
                            facturaSeleccionado = gestionarFacturaServicio.buscarFacturaPorCodigo(codigoFactura);
                            llenarTablaFacturaDetalle();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "No se puede actualizar la factura", DesktopNotify.ERROR);

                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeActualizacion(this);
                    }

                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "No ha ingresado Detalles para la factura");

            }

        } else {
            Mensaje.mostrarMensajeDefinido(this, "Falta llenar datos");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnGuardarDetalle;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnMostrarLiquidacion;
    private rojeru_san.RSButton btnNuevo3;
    private rojeru_san.RSButton btnProveedorServicio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private rojeru_san.componentes.RSDateChooser dataFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblIgv;
    private javax.swing.JTextField lblLectura;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValorVenta;
    private javax.swing.JPanel panelSumas;
    private javax.swing.JPanel panelSumas1;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage18;
    private rojerusan.RSLabelImage rSLabelImage20;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage22;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage25;
    private rojerusan.RSLabelImage rSLabelImage26;
    private rojerusan.RSLabelImage rSLabelImage27;
    private rojerusan.RSLabelImage rSLabelImage28;
    private rojerusan.RSLabelImage rSLabelImage29;
    private rojerusan.RSLabelImage rSLabelImage30;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private RadioButton.FiveCodRadioButton rbDolares;
    private RadioButton.FiveCodRadioButton rbNoAdelanto;
    private RadioButton.FiveCodRadioButton rbSiAdelanto;
    private RadioButton.FiveCodRadioButton rbSoles;
    private rojerusan.RSTableMetro tableitemFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtBuscar;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCantidadDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoUnico;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcionDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDiireccion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtGuian;
    private javax.swing.JTextField txtIGV;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtImporteDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNroFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPrecioUnitarioDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRuc;
    private javax.swing.JTextField txtTotal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtUnidadDetalle;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {
        try {
            if (Mensaje.mostrarPreguntaDeEliminacion(this)) {
                fila = tableitemFactura.getSelectedRow();
                String codigo = tableitemFactura.getValueAt(fila, 6).toString();
                FacturaDetalle facturaDetalle = facturaSeleccionado.buscarFacturaDetalle(Integer.parseInt(codigo));
                if (tipo_accion == ACCION_CREAR) {
                    if (facturaSeleccionado.eliminarFacturaDetalle(facturaDetalle)) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        llenarTablaFacturaDetalle();
                        llenarCalculos();
                        limpiarCamposDetalle();
                        inabilitarCamposFacturaDetalle(false);
                        btnGuardarDetalle.setEnabled(false);
                        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;

                    } else {
                        Mensaje.mostrarErrorDeEliminacion(this);
                    }
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {
                        facturaDetalle.setFacturaEstado("Eliminar");
                        if (facturaSeleccionado.eliminarFacturaDetalle(facturaDetalle)) {
                            Mensaje.mostrarAfirmacionDeEliminacion(this);
                            llenarTablaFacturaDetalle();
                            llenarCalculos();
                            limpiarCamposDetalle();
                            inabilitarCamposFacturaDetalle(false);
                            btnGuardarDetalle.setEnabled(false);
                            TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
                        } else {
                            Mensaje.mostrarErrorDeEliminacion(this);
                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeEliminacion(this);
                    }

                }

            }
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }

    }
}
