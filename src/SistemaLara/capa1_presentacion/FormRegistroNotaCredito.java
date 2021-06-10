/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.NumeroToLetras;
import SistemaLara.capa1_presentacion.util.Numero_a_Letra;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarFacturaReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaCreditoReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaCreditoServicio;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.FacturaDetalle;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.NotaCredito;
import SistemaLara.capa3_dominio.NotaCreditoDetalle;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.TipoNotaCD;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
public class FormRegistroNotaCredito extends javax.swing.JDialog {

    private GestionarNotaCreditoServicio gestionarNotaCreditoServicio;
    public static NotaCredito notaCreditoSeleccionado;
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
    private GestionarNotaCreditoReporteServicio gestionarNotaCreditoReporteServicio;
    private int codigoNotaCredito;
    private JasperPrint print = null;
//    public static Liquidacion liquidacionSeleccionada;
    private GestionarLiquidacionServicio gestionarLiquidacionServicio;
    int estadoSelected = 0;

    public FormRegistroNotaCredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            gestionarNotaCreditoServicio = new GestionarNotaCreditoServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            txtProveedor.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(false);
            inabilitarCampos(false);
            inabilitarCamposNotaCreditoDetalle(false);
            btnNuevoNotaCreditoDetalle.setEnabled(false);
            inabilitarBotonesDetalle(false);
            llenarComboTipoNota();
            estadoSelected = 1;
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public FormRegistroNotaCredito(java.awt.Frame parent, boolean modal, NotaCredito credito) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            tipo_accion = ACCION_MODIFICAR;
            TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
            gestionarNotaCreditoServicio = new GestionarNotaCreditoServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarCampos(true);
            gestionarNotaCreditoReporteServicio = new GestionarNotaCreditoReporteServicio();
            btnGuardar.setEnabled(true);
            btnImprimir.setEnabled(true);
            btnNuevo3.setEnabled(false);
            btnNuevoNotaCreditoDetalle.setEnabled(true);
            // btnGuardarDetalle.setEnabled(false);
            //  btnActualizarDetalle.setEnabled(false);
            this.print = null;
            inabilitarCamposNotaCreditoDetalle(false);
            llenarComboTipoNota();
            llenarDatosCampos(credito);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    private void llenarComboTipoNota() {
        try {
            cboTipoNotaCredito.removeAllItems();
            gestionarNotaCreditoServicio.llenarComboTipoNotaCredito(cboTipoNotaCredito);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void llenarDatosCampos(NotaCredito notaCredito) {
        notaCreditoSeleccionado = notaCredito;
        codigoNotaCredito = notaCredito.getCodigo();
        proveedorServicioSeleccionado = notaCredito.getProveedorServicio();
        txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
        txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());
        txtRuc.setText(proveedorServicioSeleccionado.getRuc());
//        txtDescripcion.setText(factura.getDescripcion());
        //      txtCodigoUnico.setText(factura.getCodigoUnico());
        txtNumeroNotaCredito.setText(notaCredito.getNumeroNotaCreadito());
        txtDenominacion.setText(notaCredito.getDenominacion());
        txtNumeroFactura.setText(notaCredito.getNumeroFactura());
        dataFechaEmision.setDatoFecha(notaCredito.getFechaEmision());
        dataFechaEmisionComprobante.setDatoFecha(notaCredito.getFechaEmisionComprobante());
        txtDescripcionMovito.setText(notaCredito.getDescripcionMotivo());
        txtLectura.setText(notaCredito.getLectura());
        txtTotal.setText("" + notaCredito.getTotal());
        txtIGV.setText("" + notaCredito.getIgv());
        txtSubTotal.setText("" + notaCredito.getSubtotal());

        if (notaCredito.getMoneda().equals("$")) {
            rbDolares.setSelected(true);
        } else {
            rbSoles.setSelected(true);
        }
        llenarTablaNotaCreditoDetalle();
        seleccionarRadioButton();
        estadoSelected = 1;
        for (int i = 0; i < cboTipoNotaCredito.getItemCount(); i++) {
            if (notaCredito.getTipoNotaCD().getDescripcion().equals(cboTipoNotaCredito.getItemAt(i).getDescripcion())) {
                cboTipoNotaCredito.setSelectedIndex(i);
            }
        }

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("VALOR VENTA ", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableitemFactura.setModel(modeloTabla);

        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setMinWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setMaxWidth(0);

        tableitemFactura.getColumn(tableitemFactura.getColumnName(2)).setWidth(100);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(2)).setMinWidth(100);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(2)).setMaxWidth(100);
    }

    void llenarTablaNotaCreditoDetalle() {

        ModeloTabla modeloTabla = (ModeloTabla) tableitemFactura.getModel();
        Fila filaTabla;
        modeloTabla.eliminarTotalFilas();
        if (notaCreditoSeleccionado.getListaNotaCreditoDetalle().size() == 0) {
            Mensaje.mostrarAdvertencia(this, "No hay detalles de Nota de Credito en la nota de Credito");
        } else {
            for (NotaCreditoDetalle notaCreditoDetalle : notaCreditoSeleccionado.getListaNotaCreditoDetalle()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(notaCreditoDetalle.getCodigo());
                filaTabla.agregarValorCelda(notaCreditoDetalle.getDescripcion());
                filaTabla.agregarValorCelda(notaCreditoDetalle.getValorVenta());
                modeloTabla.agregarFila(filaTabla);
            }
            modeloTabla.refrescarDatos();

        }
        llenarCalculos();
    }

    public void limpiarTablaiItemNotaCredito() {
        ModeloTabla modelo = (ModeloTabla) tableitemFactura.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            inabilitarCampos(false);
            limpiarCampos();
            limpiarTablaiItemNotaCredito();
            // gestionarNotaCreditoServicio.mostrarNotaCreditos(1, tableitemFactura);

        } catch (Exception e) {

        }

    }

    private void limpiarCampos() {
        txtNumeroNotaCredito.setText("F001-");
        rbDolares.setSelected(true);
        txtProveedor.setText("");
        txtRuc.setText("");
        txtDiireccion.setText("");
        txtDenominacion.setText("");
        txtSubTotal.setText("0.0");
        txtIGV.setText("");
        txtTotal.setText("");
        txtLectura.setText("");
        dataFechaEmisionComprobante.setDatoFecha(new Date());
        dataFechaEmision.setDatoFecha(new Date());

        descartivarDetalle();

    }

    private void descartivarDetalle() {

    }

    private void inabilitarCampos(boolean estado) {
        txtDiireccion.setEnabled(false);
        txtDenominacion.setEnabled(estado);
        //   txtDescripcion.setEnabled(estado);
        //  txtCodigoUnico.setEnabled(estado);
        txtNumeroNotaCredito.setEnabled(false);
        txtProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        rbDolares.setEnabled(estado);
        rbSoles.setEnabled(estado);
        btnProveedorServicio.setEnabled(estado);
        dataFechaEmisionComprobante.setEnabled(estado);
    }

    private void inabilitarCamposNotaCreditoDetalle(boolean estado) {
        txtDescripcionDetalle.setEnabled(estado);
        txtPrecioValorVenta.setEnabled(estado);

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
        buttonGroup3 = new javax.swing.ButtonGroup();
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
        txtNumeroNotaCredito = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        txtDenominacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtRuc = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        txtDiireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        dataFechaEmisionComprobante = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        rSLabelImage24 = new rojerusan.RSLabelImage();
        rSLabelImage31 = new rojerusan.RSLabelImage();
        dataFechaEmision = new rojeru_san.componentes.RSDateChooser();
        rbSoles = new com.icm.components.metro.RadioButtonMetroICM();
        rbDolares = new com.icm.components.metro.RadioButtonMetroICM();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        txtNumeroFactura = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel4 = new javax.swing.JPanel();
        rSLabelImage28 = new rojerusan.RSLabelImage();
        txtDescripcionDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage29 = new rojerusan.RSLabelImage();
        txtPrecioValorVenta = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        btnNuevoNotaCreditoDetalle = new rojeru_san.RSButton();
        txtCodigoNotaCreditoDetalle = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage30 = new rojerusan.RSLabelImage();
        txtDescripcionMovito = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboTipoNotaCredito = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableitemFactura = new rojerusan.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtLectura = new javax.swing.JTextField();
        panelSumas = new javax.swing.JPanel();
        lblIgv = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblValorVenta = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel6 = new javax.swing.JPanel();
        btnNuevo3 = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
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
        jLabel1.setText("NOTA DE CREDITO");

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
        jPanel3.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 300, 60));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Direccion.png"))); // NOI18N
        jPanel3.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 30));

        btnProveedorServicio.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedorServicio.setText("...");
        btnProveedorServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorServicioActionPerformed(evt);
            }
        });
        jPanel3.add(btnProveedorServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 30, 30));

        txtNumeroNotaCredito.setForeground(new java.awt.Color(0, 0, 204));
        txtNumeroNotaCredito.setAccent(new java.awt.Color(204, 0, 51));
        txtNumeroNotaCredito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNumeroNotaCredito.setLabel("NUMERO N° :");
        txtNumeroNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroNotaCreditoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroNotaCreditoKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumeroNotaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 200, 60));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dolar.png"))); // NOI18N
        jPanel3.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 40, 40));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 40, 30));

        txtDenominacion.setForeground(new java.awt.Color(0, 0, 204));
        txtDenominacion.setText("FACTURA");
        txtDenominacion.setAccent(new java.awt.Color(204, 0, 51));
        txtDenominacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDenominacion.setLabel("DENOMINACIÓN");
        txtDenominacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDenominacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDenominacionKeyTyped(evt);
            }
        });
        jPanel3.add(txtDenominacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 200, 60));

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
        jPanel3.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 160, 60));

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
        jPanel3.add(txtDiireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 340, 70));

        dataFechaEmisionComprobante.setColorBackground(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setColorForeground(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setFuente(new java.awt.Font("Book Antiqua", 1, 12)); // NOI18N
        dataFechaEmisionComprobante.setPlaceholder("FECHA EMISION COMPROBANTE");
        dataFechaEmisionComprobante.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel3.add(dataFechaEmisionComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 270, 30));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel3.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 40, 30));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        jPanel3.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 30));

        rSLabelImage31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel3.add(rSLabelImage31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 40, 30));

        dataFechaEmision.setColorBackground(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setColorForeground(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dataFechaEmision.setPlaceholder("FECHA EMISIÓN");
        dataFechaEmision.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel3.add(dataFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 250, 30));

        rbSoles.setBorder(null);
        buttonGroup3.add(rbSoles);
        rbSoles.setText("Soles");
        rbSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSolesActionPerformed(evt);
            }
        });
        jPanel3.add(rbSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, -1, -1));

        rbDolares.setBorder(null);
        buttonGroup3.add(rbDolares);
        rbDolares.setSelected(true);
        rbDolares.setText("Dolares");
        rbDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDolaresActionPerformed(evt);
            }
        });
        jPanel3.add(rbDolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, -1, -1));

        jLabel4.setText("FECHA DE EMISIÓN COMPROBANTE");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 250, -1));

        jLabel5.setText("FECHA DE EMISIÓN");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 250, -1));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 40, 30));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 40, 30));

        txtNumeroFactura.setForeground(new java.awt.Color(0, 0, 204));
        txtNumeroFactura.setAccent(new java.awt.Color(204, 0, 51));
        txtNumeroFactura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNumeroFactura.setLabel("NUMERO FACTURA");
        txtNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroFacturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroFacturaKeyTyped(evt);
            }
        });
        jPanel3.add(txtNumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 120, 60));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 40));

        txtDescripcionDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcionDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcionDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcionDetalle.setLabel("DESCRIPCIÓN  :");
        txtDescripcionDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionDetalleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtDescripcionDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 390, 60));

        rSLabelImage29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 40));

        txtPrecioValorVenta.setForeground(new java.awt.Color(0, 0, 204));
        txtPrecioValorVenta.setAccent(new java.awt.Color(204, 0, 51));
        txtPrecioValorVenta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPrecioValorVenta.setLabel("VALOR DE VENTA O SERVICIO PRESTADO");
        txtPrecioValorVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioValorVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioValorVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioValorVentaKeyTyped(evt);
            }
        });
        jPanel4.add(txtPrecioValorVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 390, 60));

        btnNuevoNotaCreditoDetalle.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevoNotaCreditoDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnNuevoNotaCreditoDetalle.setText("NUEVO");
        btnNuevoNotaCreditoDetalle.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnNuevoNotaCreditoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoNotaCreditoDetalleActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevoNotaCreditoDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 130, 30));

        txtCodigoNotaCreditoDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoNotaCreditoDetalle.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigoNotaCreditoDetalle.setEnabled(false);
        txtCodigoNotaCreditoDetalle.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigoNotaCreditoDetalle.setLabel("CÓDIGO");
        txtCodigoNotaCreditoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoNotaCreditoDetalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoNotaCreditoDetalleKeyTyped(evt);
            }
        });
        jPanel4.add(txtCodigoNotaCreditoDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 390, 60));

        rSLabelImage30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 40, 40));

        rSPanelShadow1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 470, 290));

        txtDescripcionMovito.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcionMovito.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcionMovito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcionMovito.setLabel("Descripción");
        txtDescripcionMovito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionMovitoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionMovitoKeyTyped(evt);
            }
        });
        jPanel3.add(txtDescripcionMovito, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 280, 60));

        cboTipoNotaCredito.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoNotaCredito.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoNotaCredito.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoNotaCredito.setAccent(new java.awt.Color(65, 94, 255));
        jPanel3.add(cboTipoNotaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 250, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel3.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 40, 40));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ConceptoPro.png"))); // NOI18N
        jPanel3.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 40, 40));

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("SON:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtLectura, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelSumas.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder2.setShadowSize(4);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        panelSumas.setBorder(dropShadowBorder2);

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
            .addGroup(panelSumasLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblIgv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIGV, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
            .addGroup(panelSumasLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addComponent(txtTotal))
            .addGroup(panelSumasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblValorVenta)
                .addGap(18, 18, 18)
                .addComponent(txtSubTotal))
        );
        panelSumasLayout.setVerticalGroup(
            panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSumasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorVenta)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIgv)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(panelSumasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

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

        btnImprimir.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelShadow2.add(jPanel6, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rSPanelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelSumas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelSumas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSPanelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            //  btnGuardarDetalle.setEnabled(true);
            //btnActualizarDetalle.setEnabled(true);
            fila = tableitemFactura.getSelectedRow();

            String codigo = tableitemFactura.getValueAt(fila, 0).toString();

            NotaCreditoDetalle notaCreditoDetalle = notaCreditoSeleccionado.buscarFacturaDetalle(Integer.parseInt(codigo));

            llenarCamposDetalle(notaCreditoDetalle);
//            btnGuardarDetalle.setEnabled(false);
            //  txtCantidadDetalle.selectAll();
            //   txtCantidadDetalle.requestFocus();
            //  btnActualizarDetalle.setEnabled(true);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_tableitemFacturaMousePressed

    private void tableitemFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyReleased

    }//GEN-LAST:event_tableitemFacturaKeyReleased
    private void llenarCamposDetalle(NotaCreditoDetalle notaCreditoDetalle) {
        txtDescripcionDetalle.setText(notaCreditoDetalle.getDescripcion());
        txtPrecioValorVenta.setText("" + notaCreditoDetalle.getValorVenta());
        txtCodigoNotaCreditoDetalle.setText("" + notaCreditoDetalle.getCodigo());
        inabilitarCamposNotaCreditoDetalle(true);
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

    private void txtNumeroNotaCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroNotaCreditoKeyPressed

    }//GEN-LAST:event_txtNumeroNotaCreditoKeyPressed

    private void txtNumeroNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroNotaCreditoKeyTyped

    }//GEN-LAST:event_txtNumeroNotaCreditoKeyTyped

    private void txtDenominacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenominacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
            txtDescripcionDetalle.selectAll();
            txtDescripcionDetalle.requestFocus();
            limpiarCamposDetalle();
            inabilitarCamposNotaCreditoDetalle(true);
        }
    }//GEN-LAST:event_txtDenominacionKeyPressed

    private void txtDenominacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenominacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenominacionKeyTyped

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyPressed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtDiireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiireccionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDenominacion.requestFocus();
        }
    }//GEN-LAST:event_txtDiireccionKeyPressed

    private void txtDiireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiireccionKeyTyped

    private void txtPrecioValorVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioValorVentaKeyPressed
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {

            guardarFacturaDetalle();
        }


    }//GEN-LAST:event_txtPrecioValorVentaKeyPressed

    private void txtPrecioValorVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioValorVentaKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioValorVenta.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioValorVentaKeyTyped
    private void guardarFacturaDetalle() {

        if (TIPO_ACCION_DETALLE == ACCION_CREAR_DETALLE) {
            agregarADetalle();
        } else if (TIPO_ACCION_DETALLE == ACCION_MODIFICAR_DETALLE) {
            modificarADetalle();
        }
        txtDescripcionDetalle.requestFocus();
        txtDescripcionDetalle.selectAll();
    }

    private void obtenerClienteFormulario() {
        FormlistaProvedorServicioFactura marca = new FormlistaProvedorServicioFactura(null, true, FormlistaProvedorServicioFactura.TIPO_NOTA_CREDITO);
        marca.setVisible(true);
        if (proveedorServicioSeleccionado != null) {
            txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
            txtRuc.setText(proveedorServicioSeleccionado.getRuc());
            txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());
            txtNumeroFactura.selectAll();
            txtNumeroFactura.requestFocus();
            txtNumeroFactura.setText("F001-00000");
            txtDenominacion.setText("FACTURA");
        }
    }

    private void agregarADetalle() {
        try {
            if (verificarCamposVaciosDetalle()) {
                NotaCreditoDetalle notaCreditoDetalle = new NotaCreditoDetalle();
                notaCreditoDetalle.setDescripcion(txtDescripcionDetalle.getText());
                notaCreditoDetalle.setValorVenta(Double.parseDouble(txtPrecioValorVenta.getText()));
                notaCreditoDetalle.setNotaCredito(notaCreditoSeleccionado);
                notaCreditoDetalle.setPersonal(IniciarSesion.getUsuario());

                if (tipo_accion == ACCION_CREAR) {
                    notaCreditoDetalle.setEstadoNotaCredito("Crear");
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    notaCreditoDetalle.setEstadoNotaCredito("Nuevo Modificar");
                }
                if (notaCreditoSeleccionado.agregarNotaCreditoDetalle(notaCreditoDetalle)) {
                    inabilitarCamposNotaCreditoDetalle(false);
                    llenarTablaNotaCreditoDetalle();
                    limpiarCamposDetalle();
                    Mensaje.mostrarAfirmacionDeCreacion(this);
                    TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
                    agregarNuevoDetalle();
                } else {
                    Mensaje.mostrarAdvertenciaDeCreacion(this);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void llenarCalculos() {
        if (estadoSelected == 1) {
            Double total = notaCreditoSeleccionado.calcularTotal();
            txtSubTotal.setText("" + Verificador.devolverRedondeoConCero(notaCreditoSeleccionado.calcularValor().toString()));
            txtIGV.setText("" + Verificador.devolverRedondeoConCero(notaCreditoSeleccionado.calcularIGV().toString()));
            txtTotal.setText("" + Verificador.devolverRedondeoConCero(total.toString()));
//        txtValor.setText("" + notaCreditoSeleccionado.calcularValor());
//        txtIGV.setText("" + notaCreditoSeleccionado.calcularIGV());
//        txtTotal.setText("" + notaCreditoSeleccionado.calcularTotal());

            String lectura = NumeroToLetras.NumeroALetras(total.toString());

            if (lectura != null) {
                if (rbDolares.isSelected()) {
                    txtLectura.setText(lectura + " DOLARES AMERICANOS");
                } else {
                    txtLectura.setText(lectura + " NUEVOS SOLES");

                }

            } else {
                txtLectura.setText("null");
            }
        }

    }
    public static int numeroLoteSeleccionado = 0;
    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        opcionNuevo();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatosNotaCredito();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarNotaCreditoReporteServicio.mostrarRegistroNotaCredito(notaCreditoSeleccionado);
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
    private void modificarADetalle() {

        NotaCreditoDetalle notaCreditoDetalle = new NotaCreditoDetalle();

        notaCreditoDetalle.setDescripcion(txtDescripcionDetalle.getText());
        notaCreditoDetalle.setValorVenta(Double.parseDouble(txtPrecioValorVenta.getText()));
        notaCreditoDetalle.setCodigo(Integer.parseInt(txtCodigoNotaCreditoDetalle.getText()));
        notaCreditoDetalle.setNotaCredito(notaCreditoSeleccionado);
        notaCreditoDetalle.setPersonal(IniciarSesion.getUsuario());
        if (notaCreditoSeleccionado.modificarNotaCreditoDetalle(notaCreditoDetalle)) {
            llenarTablaNotaCreditoDetalle();
            limpiarCamposDetalle();
            Mensaje.mostrarAfirmacionDeActualizacion(this);
            agregarNuevoDetalle();
            TIPO_ACCION_DETALLE = ACCION_MODIFICAR_DETALLE;
        } else {
            Mensaje.mostrarAdvertenciaDeActualizacion(this);
        }

    }

    private static boolean band() {
        if (Math.random() > .5) {
            return true;
        } else {
            return false;
        }
    }
    private void tableitemFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyPressed
        fila = tableitemFactura.getSelectedRow();

        if (evt.getKeyChar() == com.sun.glass.events.KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableitemFacturaKeyPressed

    private void txtDescripcionDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionDetalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionDetalleKeyTyped

    private void txtDescripcionDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPrecioValorVenta.selectAll();
            txtPrecioValorVenta.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionDetalleKeyPressed

    private void txtNumeroFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDenominacion.selectAll();
            txtDenominacion.requestFocus();
        }


    }//GEN-LAST:event_txtNumeroFacturaKeyPressed

    private void txtNumeroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyTyped
        String patron_de_entrada = "0123456789-F";
        if (txtNumeroFactura.getText().length() == 13
                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtNumeroFactura.requestFocus();
        }
    }//GEN-LAST:event_txtNumeroFacturaKeyTyped

    private void btnNuevoNotaCreditoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoNotaCreditoDetalleActionPerformed
        agregarNuevoDetalle();
    }//GEN-LAST:event_btnNuevoNotaCreditoDetalleActionPerformed

    private void agregarNuevoDetalle() {
        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
        txtDescripcionDetalle.selectAll();
        txtDescripcionDetalle.requestFocus();
        limpiarCamposDetalle();
        inabilitarCamposNotaCreditoDetalle(true);
    }
    private void rbSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSolesActionPerformed
        if (rbDolares.isSelected()) {
            lblIgv.setText("IGV $:");
            lblValorVenta.setText("VALOR VENTA $:");
            lblTotal.setText("TOTAL $:");
        } else {
            lblIgv.setText("IGV S/.");
            lblValorVenta.setText("VALOR VENTA S/.");
            lblTotal.setText("TOTAL S/.");

        }
        llenarCalculos();
    }//GEN-LAST:event_rbSolesActionPerformed

    private void txtPrecioValorVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioValorVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioValorVentaKeyReleased

    private void txtCodigoNotaCreditoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoNotaCreditoDetalleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoNotaCreditoDetalleKeyPressed

    private void txtCodigoNotaCreditoDetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoNotaCreditoDetalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoNotaCreditoDetalleKeyTyped

    private void rbDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDolaresActionPerformed
        seleccionarRadioButton();
    }//GEN-LAST:event_rbDolaresActionPerformed

    private void txtDescripcionDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionDetalleKeyReleased
        txtDescripcionDetalle.setText(txtDescripcionDetalle.getText().toUpperCase());
    }//GEN-LAST:event_txtDescripcionDetalleKeyReleased

    private void txtDescripcionMovitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionMovitoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionMovitoKeyPressed

    private void txtDescripcionMovitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionMovitoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionMovitoKeyTyped

    private void txtNumeroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyReleased
        if (txtNumeroFactura.getText().equals("")) {
            txtNumeroFactura.setText("F001-");
        }
    }//GEN-LAST:event_txtNumeroFacturaKeyReleased

    void seleccionarRadioButton() {
        if (rbDolares.isSelected()) {
            lblIgv.setText("IGV $:");
            lblValorVenta.setText("VALOR VENTA $:");
            lblTotal.setText("TOTAL $:");
        } else {
            lblIgv.setText("IGV S/.:");
            lblValorVenta.setText("VALOR VENTA S/.:");
            lblTotal.setText("TOTAL S/.:");

        }
        llenarCalculos();
    }

    private void limpiarCamposDetalle() {
        txtDescripcionDetalle.setText("");
        txtPrecioValorVenta.setText("");
        txtCodigoNotaCreditoDetalle.setText("");
    }

    private void opcionNuevo() {
        btnNuevoNotaCreditoDetalle.setEnabled(false);
        notaCreditoSeleccionado = new NotaCredito();
        tipo_accion = ACCION_CREAR;
        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
        limpiarCampos();
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(true);
        rbDolares.setSelected(true);
        inabilitarCampos(true);
        inabilitarCamposNotaCreditoDetalle(false);
        inabilitarBotonesDetalle(true);
        obtenerClienteFormulario();
        // txtBuscar.setEnabled(true);
        limpiarTablaiItemNotaCredito();
        btnNuevoNotaCreditoDetalle.setEnabled(true);

    }

    private void inabilitarBotonesDetalle(boolean estado) {
        //btnMostrarLiquidacion.setEnabled(estado);
        // btnActualizarDetalle.setEnabled(estado);
        //  btnGuardarDetalle.setEnabled(estado);

    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtProveedor);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtDenominacion);
        aux = aux + contador;

        contador = Verificador.verificadorCampos(txtDescripcionMovito);
        aux = aux + contador;
        //contador = Verificador.verificadorCampos(txtDescripcion);
        aux = aux + contador;
        return aux == 4;
    }

    private void guardarDatosNotaCredito() {
        if (verificarCamposVacios()) {
            if (txtLectura.getText().equals("null")) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Error al consumir el webservice. Activar el servidor o comicarse con soporte ", DesktopNotify.ERROR);
                return;
            }
            if (notaCreditoSeleccionado.getListaNotaCreditoDetalle().size() > 0) {
                TipoNotaCD tipoNotaCD = (TipoNotaCD) cboTipoNotaCredito.getItemAt(cboTipoNotaCredito.getSelectedIndex());
                notaCreditoSeleccionado.setTipoNotaCD(tipoNotaCD);
                notaCreditoSeleccionado.setDescripcionMotivo(txtDescripcionMovito.getText().toString());
                Date fecha = dataFechaEmisionComprobante.getDatoFecha();
                notaCreditoSeleccionado.setFechaEmision(new java.sql.Date(fecha.getTime()));
                notaCreditoSeleccionado.setFechaEmisionComprobante(new java.sql.Date(fecha.getTime()));
                notaCreditoSeleccionado.setNumeroFactura(txtNumeroFactura.getText());
                notaCreditoSeleccionado.setNumeroNotaCreadito(txtNumeroNotaCredito.getText());
                notaCreditoSeleccionado.setProveedorServicio(proveedorServicioSeleccionado);
                notaCreditoSeleccionado.setDenominacion(txtDenominacion.getText());
                notaCreditoSeleccionado.setSubtotal(Double.parseDouble(txtSubTotal.getText()));
                notaCreditoSeleccionado.setIgv(Double.parseDouble(txtIGV.getText()));
                notaCreditoSeleccionado.setTotal(Double.parseDouble(txtTotal.getText()));
                notaCreditoSeleccionado.setPersonal(IniciarSesion.getUsuario());

                if (rbDolares.isSelected()) {
                    notaCreditoSeleccionado.setMoneda("$");
                } else {
                    notaCreditoSeleccionado.setMoneda("S/.");
                }
                notaCreditoSeleccionado.setLectura(txtLectura.getText());
                int registros_afectados;
                if (tipo_accion == ACCION_CREAR) {
                    try {
                        registros_afectados = gestionarNotaCreditoServicio.guardarNotaCredito(notaCreditoSeleccionado);
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
                        notaCreditoSeleccionado.setCodigo(codigoNotaCredito);
                        registros_afectados = gestionarNotaCreditoServicio.modificarNotaCredito(notaCreditoSeleccionado);
                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de actualizar la nota Credito numero  " + notaCreditoSeleccionado.getNumeroNotaCreadito(), DesktopNotify.SUCCESS);
                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            btnImprimir.setEnabled(true);
                            notaCreditoSeleccionado = gestionarNotaCreditoServicio.buscarNotaCreditoPorCodigo(codigoNotaCredito);

                            llenarTablaNotaCreditoDetalle();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "No se Actulizaron los datos", DesktopNotify.ERROR);
                        }
                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeActualizacion(this);
                    }

                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "No ha ingresado Detalles para la Nota de Credito");

            }

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnNuevo3;
    private rojeru_san.RSButton btnNuevoNotaCreditoDetalle;
    private rojeru_san.RSButton btnProveedorServicio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoNotaCD> cboTipoNotaCredito;
    private rojeru_san.componentes.RSDateChooser dataFechaEmision;
    private rojeru_san.componentes.RSDateChooser dataFechaEmisionComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblIgv;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValorVenta;
    private javax.swing.JPanel panelSumas;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage18;
    private rojerusan.RSLabelImage rSLabelImage19;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage28;
    private rojerusan.RSLabelImage rSLabelImage29;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage30;
    private rojerusan.RSLabelImage rSLabelImage31;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private com.icm.components.metro.RadioButtonMetroICM rbDolares;
    private com.icm.components.metro.RadioButtonMetroICM rbSoles;
    private rojerusan.RSTableMetro tableitemFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoNotaCreditoDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDenominacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcionDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcionMovito;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDiireccion;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtLectura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNumeroFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNumeroNotaCredito;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPrecioValorVenta;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRuc;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {
        try {
            if (Mensaje.mostrarPreguntaDeEliminacion(this)) {
                fila = tableitemFactura.getSelectedRow();
                String codigo = tableitemFactura.getValueAt(fila, 0).toString();
                NotaCreditoDetalle notaCreditoDetalle = notaCreditoSeleccionado.buscarFacturaDetalle(Integer.parseInt(codigo));
                if (tipo_accion == ACCION_CREAR) {
                    notaCreditoDetalle.setEstadoNotaCredito("Eliminar");
                    if (notaCreditoSeleccionado.eliminarNotaCreditoDetalle(notaCreditoDetalle)) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        llenarTablaNotaCreditoDetalle();
                        llenarCalculos();
                        limpiarCamposDetalle();
                        inabilitarCamposNotaCreditoDetalle(false);
                        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;

                    } else {
                        Mensaje.mostrarErrorDeEliminacion(this);
                    }
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {
                        notaCreditoDetalle.setEstadoNotaCredito("Eliminar");
                        if (notaCreditoSeleccionado.eliminarNotaCreditoDetalle(notaCreditoDetalle)) {
                            Mensaje.mostrarAfirmacionDeEliminacion(this);
                            llenarTablaNotaCreditoDetalle();
                            llenarCalculos();
                            limpiarCamposDetalle();
                            inabilitarCamposNotaCreditoDetalle(false);
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

    private boolean verificarCamposVaciosDetalle() {
        int contador = 0, suma = 0;
        contador = Verificador.verificadorCampos(txtDescripcionDetalle);
        suma = suma + contador;
        contador = Verificador.verificadorCampos(txtPrecioValorVenta);
        suma = suma + contador;
        return suma == 2;

    }
}
