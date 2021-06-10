/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import static SistemaLara.capa1_presentacion.FormRegistroNotaCredito.notaCreditoSeleccionado;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.NumeroToLetras;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarFacturaServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaDebitoReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaDebitoServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.TipoNotaDebito;
import SistemaLara.capa3_dominio.NotaDebito;
import SistemaLara.capa3_dominio.NotaDebitoDetalle;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.Reintegro;
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
public class FormRegistroNotaDeDebito extends javax.swing.JDialog {

    private GestionarFacturaServicio gestionarFacturaServicio;
    //public static Factura facturaSeleccionado;
    public static NotaDebito notaDebitoSeleccionado;
    public static Reintegro reintegroSeleccionado;
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
    private GestionarNotaDebitoReporteServicio gestionarNotaDebitoReporteServicio;
    private GestionarNotaDebitoServicio gestionarNotaDebitoServicio;
    private int codigoNotaDebito;
    private JasperPrint print = null;

    private GestionarLiquidacionServicio gestionarLiquidacionServicio;

    public FormRegistroNotaDeDebito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            notaDebitoSeleccionado = new NotaDebito();
            gestionarFacturaServicio = new GestionarFacturaServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarNotaDebitoReporteServicio = new GestionarNotaDebitoReporteServicio();
            gestionarNotaDebitoServicio = new GestionarNotaDebitoServicio();
            inicializarTablaColumnas();
//            inicializarTabla();

            txtProveedor.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(false);
            inabilitarCampos(false);
            inabilitarCamposFacturaDetalle(false);
            inabilitarBotonesDetalle(false);
            estadoSelected = 1;
            llenarComboTipoNota();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public FormRegistroNotaDeDebito(java.awt.Frame parent, boolean modal, NotaDebito nota) {
        super(parent, modal);
        initComponents();
        try {
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            tipo_accion = ACCION_MODIFICAR;

            inicializarTablaColumnas();
//            inicializarTabla();
            gestionarFacturaServicio = new GestionarFacturaServicio();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarNotaDebitoReporteServicio = new GestionarNotaDebitoReporteServicio();
            gestionarNotaDebitoServicio = new GestionarNotaDebitoServicio();
            btnGuardar.setEnabled(true);
            btnImprimir.setEnabled(true);
            btnNuevo3.setEnabled(false);
            btnGuardarDetalle.setEnabled(false);
            //  btnActualizarDetalle.setEnabled(false);
            this.print = null;
            inabilitarCamposFacturaDetalle(false);
            llenarComboTipoNota();
            llenarDatosCampos(nota);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    private void llenarComboTipoNota() {
        try {
            cboTipoNotaDebito.removeAllItems();
            gestionarNotaDebitoServicio.llenarComboTipoNotaDebito(cboTipoNotaDebito);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void llenarDatosCampos(NotaDebito nota) {
        notaDebitoSeleccionado = nota;
        codigoNotaDebito = nota.getCodigo();
        proveedorServicioSeleccionado = nota.getProveedorServicio();
        txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
        txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());
        txtRuc.setText(proveedorServicioSeleccionado.getRuc());
        txtDenominacion.setText(nota.getDenominacion().toString());
        txtDescripcionMovito.setText(nota.getDescripcionMotivo());
        txtConsesion.setText(nota.getConsecion());
        txtCodigoUnico.setText(nota.getCodigoUnico());
        txtNroNotaDebito.setText(nota.getNumeroNotaDebito());
        txtNumeroFactura.setText(nota.getNumeroFactura().toString());
        dataFechaEmision.setDatoFecha(nota.getFechaEmision());
        dataFechaEmisionComprobante.setDatoFecha(nota.getFechaEmision_Comprobante());

        if (nota.getMoneda().equals("$")) {
            rbDolares.setSelected(true);
        } else {
            rbSoles.setSelected(true);
        }
        txtTotal.setText("" + Verificador.devolverRedondeoConCero(nota.getTotal().toString()));
        txtValor.setText("" + Verificador.devolverRedondeoConCero(nota.getValorVenta().toString()));
        txtIGV.setText("" + Verificador.devolverRedondeoConCero(nota.getIGV().toString()));
        lblLectura.setText(nota.getLectura());

        llenarTablaFacturaDetalle();
        metodoBotonesMoneda();
        estadoSelected = 1;

        for (int i = 0; i < cboTipoNotaDebito.getItemCount(); i++) {
            if (nota.getTipoNotaDebito().getDescripcion().equals(cboTipoNotaDebito.getItemAt(i).getDescripcion())) {
                cboTipoNotaDebito.setSelectedIndex(i);
            }
        }

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("UNIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tabla.agregarColumna(new Columna("PRECIO UNITARIO", "java.lang.String"));
        tabla.agregarColumna(new Columna("VALOR VENTA", "java.lang.String"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableitemFactura.setModel(modeloTabla);

        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setMinWidth(0);
        tableitemFactura.getColumn(tableitemFactura.getColumnName(0)).setMaxWidth(0);
    }

    void llenarTablaFacturaDetalle() {

        ModeloTabla modeloTabla = (ModeloTabla) tableitemFactura.getModel();
        Fila filaTabla;
        modeloTabla.eliminarTotalFilas();
        if (notaDebitoSeleccionado.getListaNotaDebitoDetalle().size() == 0) {
            Mensaje.mostrarAdvertencia(this, "No hay detalles facturas en la factura");
        } else {
            for (NotaDebitoDetalle notaDebitoDetalle : notaDebitoSeleccionado.getListaNotaDebitoDetalle()) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(notaDebitoDetalle.getReintegro().getCodigo());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getCantidad());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getUnidad());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getDescripcion());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getPrecioUnitario());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getValorVenta());
                filaTabla.agregarValorCelda(notaDebitoDetalle.getReintegro().getLote());
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
        txtNroNotaDebito.setText("F001-");
        rbDolares.setSelected(true);
        txtProveedor.setText("");
        txtRuc.setText("");
        txtDiireccion.setText("");

        txtValor.setText("0.0");
        txtIGV.setText("");
        txtTotal.setText("");
        lblLectura.setText("");
        dataFechaEmisionComprobante.setDatoFecha(new Date());
        dataFechaEmision.setDatoFecha(new Date());

        descartivarDetalle();

    }

    private void descartivarDetalle() {

    }

    private void inabilitarCampos(boolean estado) {
        txtDiireccion.setEnabled(false);

        txtConsesion.setEnabled(estado);
        txtCodigoUnico.setEnabled(estado);
        txtNroNotaDebito.setEnabled(false);
        txtProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        rbDolares.setEnabled(estado);
        rbSoles.setEnabled(estado);
        btnProveedorServicio.setEnabled(estado);
        dataFechaEmisionComprobante.setEnabled(estado);
    }

    private void inabilitarCamposFacturaDetalle(boolean estado) {
        txtCantidadDetalle.setEnabled(estado);
        txtDescripcionDetalle.setEnabled(estado);
        txtValorVenta.setEnabled(estado);
        txtPrecioUnitarioDetalle.setEnabled(estado);
        txtUnidadDetalle.setEnabled(estado);

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
        txtNroNotaDebito = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        txtCodigoUnico = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage20 = new rojerusan.RSLabelImage();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        txtDiireccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        dataFechaEmisionComprobante = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage22 = new rojerusan.RSLabelImage();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        txtConsesion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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
        txtValorVenta = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        panelSumas1 = new javax.swing.JPanel();
        btnGuardarDetalle = new rojeru_san.RSButton();
        btnMostrarLiquidacion = new rojeru_san.RSButton();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel6 = new javax.swing.JPanel();
        btnCancelar = new rojeru_san.RSButton();
        btnNuevo3 = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        rSLabelImage31 = new rojerusan.RSLabelImage();
        dataFechaEmision = new rojeru_san.componentes.RSDateChooser();
        txtDenominacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage32 = new rojerusan.RSLabelImage();
        rSLabelImage33 = new rojerusan.RSLabelImage();
        txtNumeroFactura = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rSLabelImage24 = new rojerusan.RSLabelImage();
        txtRuc = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        cboTipoNotaDebito = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        txtDescripcionMovito = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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
        jLabel1.setText("REGISTRO NOTA DE DEBITO");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        txtNroNotaDebito.setForeground(new java.awt.Color(0, 0, 204));
        txtNroNotaDebito.setAccent(new java.awt.Color(204, 0, 51));
        txtNroNotaDebito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNroNotaDebito.setLabel("NUMERO N° :");
        txtNroNotaDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroNotaDebitoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroNotaDebitoKeyTyped(evt);
            }
        });
        jPanel3.add(txtNroNotaDebito, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 190, 60));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dolar.png"))); // NOI18N
        jPanel3.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 40, 30));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel3.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 30));

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
        jPanel3.add(txtCodigoUnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 180, 60));

        rSLabelImage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 40, 30));

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

        dataFechaEmisionComprobante.setColorBackground(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setColorForeground(new java.awt.Color(64, 95, 255));
        dataFechaEmisionComprobante.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dataFechaEmisionComprobante.setPlaceholder("FECHA ");
        dataFechaEmisionComprobante.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel3.add(dataFechaEmisionComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 190, 30));

        rSLabelImage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 40, 30));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel3.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 40, 30));

        txtConsesion.setForeground(new java.awt.Color(0, 0, 204));
        txtConsesion.setText("CONCESION: EMPRESA MINERA MIRAFLORES");
        txtConsesion.setAccent(new java.awt.Color(204, 0, 51));
        txtConsesion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtConsesion.setLabel("CONSECION:");
        txtConsesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConsesionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsesionKeyTyped(evt);
            }
        });
        jPanel3.add(txtConsesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 240, 60));

        buttonGroup1.add(rbDolares);
        rbDolares.setSelected(true);
        rbDolares.setForeground(new java.awt.Color(65, 94, 255));
        rbDolares.setText("Dolares");
        rbDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDolaresActionPerformed(evt);
            }
        });
        jPanel3.add(rbDolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 80, -1));

        buttonGroup1.add(rbSoles);
        rbSoles.setForeground(new java.awt.Color(65, 94, 255));
        rbSoles.setText("Soles");
        rbSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSolesActionPerformed(evt);
            }
        });
        jPanel3.add(rbSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 70, -1));

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
        jPanel4.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 170, 60));

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
        jPanel4.add(txtCantidadDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 180, 60));

        rSLabelImage27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, 40));

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
        jPanel4.add(txtUnidadDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 180, 60));

        rSLabelImage28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, 40));

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
        jPanel4.add(txtDescripcionDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 330, 60));

        rSLabelImage29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 40, 40));

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
        jPanel4.add(txtPrecioUnitarioDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 130, 60));

        rSLabelImage30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage30, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 40, 40));

        txtValorVenta.setForeground(new java.awt.Color(0, 0, 204));
        txtValorVenta.setAccent(new java.awt.Color(204, 0, 51));
        txtValorVenta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtValorVenta.setLabel("VALOR VENTA");
        txtValorVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorVentaKeyTyped(evt);
            }
        });
        jPanel4.add(txtValorVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 150, 60));

        panelSumas1.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(68, 138, 255));
        dropShadowBorder2.setShadowSize(4);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        panelSumas1.setBorder(dropShadowBorder2);

        btnGuardarDetalle.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnGuardarDetalle.setText("GUARDAR");
        btnGuardarDetalle.setFont(new java.awt.Font("Roboto Bold", 1, 10)); // NOI18N
        btnGuardarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDetalleActionPerformed(evt);
            }
        });

        btnMostrarLiquidacion.setBackground(new java.awt.Color(65, 94, 255));
        btnMostrarLiquidacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cambio.png"))); // NOI18N
        btnMostrarLiquidacion.setText("REINTEGRO");
        btnMostrarLiquidacion.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnMostrarLiquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarLiquidacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSumas1Layout = new javax.swing.GroupLayout(panelSumas1);
        panelSumas1.setLayout(panelSumas1Layout);
        panelSumas1Layout.setHorizontalGroup(
            panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSumas1Layout.createSequentialGroup()
                .addGroup(panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMostrarLiquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSumas1Layout.setVerticalGroup(
            panelSumas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSumas1Layout.createSequentialGroup()
                .addComponent(btnMostrarLiquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(panelSumas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 150, 100));

        rSPanelShadow1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 410, 300));

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

        jPanel3.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 260, 90));

        rSLabelImage31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel3.add(rSLabelImage31, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 40, 30));

        dataFechaEmision.setColorBackground(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setColorForeground(new java.awt.Color(64, 95, 255));
        dataFechaEmision.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dataFechaEmision.setPlaceholder("FECHA ");
        dataFechaEmision.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel3.add(dataFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 190, 30));

        txtDenominacion.setForeground(new java.awt.Color(0, 0, 204));
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
        jPanel3.add(txtDenominacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 120, 60));

        rSLabelImage32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 40, 30));

        rSLabelImage33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel3.add(rSLabelImage33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 40, 30));

        txtNumeroFactura.setForeground(new java.awt.Color(0, 0, 204));
        txtNumeroFactura.setAccent(new java.awt.Color(204, 0, 51));
        txtNumeroFactura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNumeroFactura.setLabel("N° DE FACTURA");
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
        jPanel3.add(txtNumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 140, 60));

        jLabel4.setText("FECHA DE EMISIÓN COMPROBANTE");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 190, -1));

        jLabel5.setText("FECHA DE EMISIÓN");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 190, -1));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Dni.png"))); // NOI18N
        jPanel3.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 40, 30));

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
        jPanel3.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 130, 60));

        cboTipoNotaDebito.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoNotaDebito.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoNotaDebito.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoNotaDebito.setAccent(new java.awt.Color(65, 94, 255));
        jPanel3.add(cboTipoNotaDebito, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 180, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel3.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 40, 40));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ConceptoPro.png"))); // NOI18N
        jPanel3.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 40, 40));

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
        jPanel3.add(txtDescripcionMovito, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 270, 60));

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
            .addComponent(lblLectura)
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
        panelSumas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIgv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIgv.setText("I.G.V:");
        panelSumas.add(lblIgv, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 40, -1));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotal.setText("TOTAL:");
        panelSumas.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        lblValorVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblValorVenta.setText("VALOR VENTA:");
        panelSumas.add(lblValorVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        panelSumas.add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 110, -1));
        panelSumas.add(txtIGV, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 110, -1));
        panelSumas.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 110, -1));

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSumas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1269, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelSumas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            fila = tableitemFactura.getSelectedRow();

            String codigo = tableitemFactura.getValueAt(fila, 0).toString();
            NotaDebitoDetalle notaDebitoDetalle = notaDebitoSeleccionado.buscarNotaDebitoDetalle(Integer.parseInt(codigo));

            llenarCamposDetalle(notaDebitoDetalle);
            txtCantidadDetalle.selectAll();
            txtCantidadDetalle.requestFocus();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tableitemFacturaMousePressed

    private void tableitemFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyReleased

    }//GEN-LAST:event_tableitemFacturaKeyReleased
    private void llenarCamposDetalle(NotaDebitoDetalle notaDebitoDetalle) {
        txtCantidadDetalle.setText(notaDebitoDetalle.getCantidad());
        txtDescripcionDetalle.setText(notaDebitoDetalle.getDescripcion());
        txtValorVenta.setText(notaDebitoDetalle.getValorVenta().toString());
        txtPrecioUnitarioDetalle.setText("" + notaDebitoDetalle.getPrecioUnitario());
        txtUnidadDetalle.setText(notaDebitoDetalle.getUnidad());
        reintegroSeleccionado = notaDebitoDetalle.getReintegro();
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

    private void txtNroNotaDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroNotaDebitoKeyPressed

    }//GEN-LAST:event_txtNroNotaDebitoKeyPressed

    private void txtNroNotaDebitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroNotaDebitoKeyTyped

    }//GEN-LAST:event_txtNroNotaDebitoKeyTyped

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

        }
    }//GEN-LAST:event_txtDiireccionKeyPressed

    private void txtDiireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiireccionKeyTyped

    private void txtConsesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsesionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtCodigoUnico.requestFocus();
        }
    }//GEN-LAST:event_txtConsesionKeyPressed

    private void txtConsesionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsesionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsesionKeyTyped

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
                    reintegroSeleccionado = null;
                    reintegroSeleccionado = gestionarNotaDebitoServicio.buscarReintegroPorLote(numeroLoteSeleccionado);
                    if (reintegroSeleccionado != null) {
                        if (reintegroSeleccionado != null) {
                            inabilitarCamposFacturaDetalle(true);
                            btnGuardarDetalle.setEnabled(true);
                            txtCantidadDetalle.setText(reintegroSeleccionado.getTms().toString());
                            if (txtRuc.getText().equals("20460352674")) {
                                txtDescripcionDetalle.setText("MINERAL AURIFERO BRUTO LOTE:" + reintegroSeleccionado.getLote() + " LEY:" + reintegroSeleccionado.getLeyau() + " PIO:" + reintegroSeleccionado.getInter());
                            } else if (txtRuc.getText().equals("20536126440")) {
                                txtDescripcionDetalle.setText("MINERAL AURIFERO LOTE:" + reintegroSeleccionado.getLote() + " LEY AU:" + reintegroSeleccionado.getLeyau() + " OZ/TC");
                            }
                            Double calculo = reintegroSeleccionado.getReintrego() / reintegroSeleccionado.getTms();
                            Double calculoRedondeado = redondearDecimales(calculo, 2);
                            txtPrecioUnitarioDetalle.setText(calculoRedondeado.toString());

                            double cantidad = Double.parseDouble(txtCantidadDetalle.getText());
                            double punitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
                            double importe = cantidad * punitario;
                            txtValorVenta.setText(redondearDecimales(importe, 2) + "");

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
                txtValorVenta.selectAll();
                txtValorVenta.requestFocus();
            } else {
                double cantidad = Double.parseDouble(txtCantidadDetalle.getText());
                double punitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
                double importe = cantidad * punitario;
                txtValorVenta.setText(redondearDecimales(importe, 2) + "");
                txtValorVenta.selectAll();
                txtValorVenta.requestFocus();
            }

//            double cantidad = Double.parseDouble(txtCantidadDetalle.getText());
//            double punitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
//            double importe = cantidad * punitario;
//            txtValorVenta.setText(redondearDecimales(importe, 2) + "");
            txtValorVenta.selectAll();
            txtValorVenta.requestFocus();
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

    private void txtValorVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVentaKeyPressed
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
            guardarFacturaDetalle();
        }
    }//GEN-LAST:event_txtValorVentaKeyPressed

    private void txtValorVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVentaKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtValorVenta.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorVentaKeyTyped

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

    public static Double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    private void obtenerClienteFormulario() {
        FormlistaProvedorServicioFactura marca = new FormlistaProvedorServicioFactura(null, true, FormGestionarProveedorServicios.TIPO_NOTADEBITO);
        marca.setVisible(true);
        if (proveedorServicioSeleccionado != null) {
            txtProveedor.setText(proveedorServicioSeleccionado.getRazonSocial());
            txtRuc.setText(proveedorServicioSeleccionado.getRuc());
            txtDiireccion.setText(proveedorServicioSeleccionado.getDireccion());
            txtDenominacion.setText("FACTURA");
            txtNumeroFactura.setText("F001-00000");

            txtDenominacion.requestFocus();
            txtDenominacion.selectAll();
        }
    }

    private void agregarADetalle() throws HeadlessException, NumberFormatException {
        NotaDebitoDetalle notaDebitoDetalle = new NotaDebitoDetalle();
        if (reintegroSeleccionado != null) {
            notaDebitoDetalle.setCantidad(txtCantidadDetalle.getText());
            notaDebitoDetalle.setDescripcion(txtDescripcionDetalle.getText());
            notaDebitoDetalle.setUnidad(txtUnidadDetalle.getText());
            notaDebitoDetalle.setPrecioUnitario(Double.parseDouble(txtPrecioUnitarioDetalle.getText()));
            notaDebitoDetalle.setValorVenta(Double.parseDouble(txtValorVenta.getText()));
            notaDebitoDetalle.setReintegro(reintegroSeleccionado);
            notaDebitoDetalle.setPersonal(IniciarSesion.getUsuario());

            if (tipo_accion == ACCION_CREAR) {
                notaDebitoDetalle.setNotaDebitoEstado("Crear");
            } else if (tipo_accion == ACCION_MODIFICAR) {
                notaDebitoDetalle.setNotaDebitoEstado("Nuevo Modificar");
            }
            if (!notaDebitoSeleccionado.existeNotaDebitoDetalle(notaDebitoDetalle)) {
                if (notaDebitoSeleccionado.agregarNotaDebitoDetalle(notaDebitoDetalle)) {
                    inabilitarCamposFacturaDetalle(false);
                    llenarTablaFacturaDetalle();
                    btnGuardarDetalle.setEnabled(false);

                    limpiarCamposDetalle();
                    Mensaje.mostrarAfirmacionDeCreacion(this);
                    reintegroSeleccionado = null;
                    TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
                } else {

                    Mensaje.mostrarAdvertenciaDeCreacion(this);
                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "El detalle de la factura ya existe");

            }
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Debe seleccionar una liquidacion");

        }

    }
    int estadoSelected = 0;

    void llenarCalculos() {
        if (estadoSelected == 1) {
            Double total = notaDebitoSeleccionado.calcularTotal();
            txtValor.setText("" + Verificador.devolverRedondeoConCero(notaDebitoSeleccionado.calcularValor().toString()));
            txtIGV.setText("" + Verificador.devolverRedondeoConCero(notaDebitoSeleccionado.calcularIGV().toString()));
            txtTotal.setText("" + Verificador.devolverRedondeoConCero(total.toString()));

            String lectura = NumeroToLetras.NumeroALetras(total.toString());
            if (lectura != null) {
                if (rbDolares.isSelected()) {
                    lblLectura.setText("SON " + lectura + " DOLARES AMERICANOS");
                } else {
                    lblLectura.setText("SON " + lectura + " NUEVOS SOLES");

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
    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
        opcionNuevo();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatosFactura();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarNotaDebitoReporteServicio.mostrarRegistroPedido(notaDebitoSeleccionado);
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
            importe = Double.parseDouble(txtValorVenta.getText());
            cantidad = Double.parseDouble(txtCantidadDetalle.getText());
            calcultoPrecioUnitario = importe / cantidad;
        }
        return calcultoPrecioUnitario;

    }

    private void modificarADetalle() {
        if (reintegroSeleccionado != null) {
            Double precioUnitario = 0.0;
            if (txtPrecioUnitarioDetalle.getText().equals("")) {
                precioUnitario = calcularPrecioUnitario();
                precioUnitario = redondearDecimales(precioUnitario, 2);
            } else {
                precioUnitario = Double.parseDouble(txtPrecioUnitarioDetalle.getText());
            }
            NotaDebitoDetalle notaDebitoDetalle = new NotaDebitoDetalle();
            notaDebitoDetalle.setCantidad(txtCantidadDetalle.getText());
            notaDebitoDetalle.setDescripcion(txtDescripcionDetalle.getText());
            notaDebitoDetalle.setUnidad(txtUnidadDetalle.getText());
            notaDebitoDetalle.setPrecioUnitario(precioUnitario);
            notaDebitoDetalle.setValorVenta(Double.parseDouble(txtValorVenta.getText()));
            notaDebitoDetalle.setReintegro(reintegroSeleccionado);
            notaDebitoDetalle.setPersonal(IniciarSesion.getUsuario());

            if (notaDebitoSeleccionado.modificarNotaDebitoDetalle(notaDebitoDetalle)) {
                llenarTablaFacturaDetalle();
                btnGuardarDetalle.setEnabled(false);
                limpiarCamposDetalle();
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                // btnActualizarDetalle.setEnabled(false);
                btnGuardarDetalle.setEnabled(false);
                reintegroSeleccionado = null;
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
        metodoBotonesMoneda();
    }//GEN-LAST:event_rbDolaresActionPerformed

    private void rbSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSolesActionPerformed
        metodoBotonesMoneda();
    }//GEN-LAST:event_rbSolesActionPerformed

    private void metodoBotonesMoneda() {
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
    }
    private void tableitemFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableitemFacturaKeyPressed
        fila = tableitemFactura.getSelectedRow();

        if (evt.getKeyChar() == com.sun.glass.events.KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableitemFacturaKeyPressed

    private void txtDenominacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenominacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNumeroFactura.requestFocus();
            txtNumeroFactura.selectAll();
        }
    }//GEN-LAST:event_txtDenominacionKeyPressed

    private void txtDenominacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenominacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDenominacionKeyTyped

    private void txtNumeroFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBuscar.requestFocus();
            txtBuscar.selectAll();
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

    private void btnMostrarLiquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarLiquidacionActionPerformed
        obtenerDatosFormLiquidacion();

    }//GEN-LAST:event_btnMostrarLiquidacionActionPerformed
    private void limpiarCamposDetalle() {
        txtCantidadDetalle.setText("");
        txtDescripcionDetalle.setText("");
        txtValorVenta.setText("");
        txtPrecioUnitarioDetalle.setText("");
        txtUnidadDetalle.setText("TMS");
        reintegroSeleccionado = null;

    }

    private void opcionNuevo() {
        btnGuardarDetalle.setEnabled(false);
        // btnActualizarDetalle.setEnabled(false);
        notaDebitoSeleccionado = new NotaDebito();
        tipo_accion = ACCION_CREAR;
        TIPO_ACCION_DETALLE = ACCION_CREAR_DETALLE;
        limpiarCampos();
        btnImprimir.setEnabled(false);
        btnGuardar.setEnabled(true);

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
        reintegroSeleccionado = null;
        FormGestionarReintegro formGestionarReintegro = new FormGestionarReintegro(null, true, FormGestionarReintegro.TIPO_NOTA_DEBITO);
        formGestionarReintegro.setVisible(true);

        if (reintegroSeleccionado != null) {
            inabilitarCamposFacturaDetalle(true);
            btnGuardarDetalle.setEnabled(true);
            txtCantidadDetalle.setText("" + reintegroSeleccionado.getTms());
            if (txtRuc.getText().equals("20460352674")) {
                txtDescripcionDetalle.setText("MINERAL AURIFERO BRUTO LOTE:" + reintegroSeleccionado.getLote() + " LEY:" + reintegroSeleccionado.getLeyau() + " PIO:" + reintegroSeleccionado.getInter());
            } else if (txtRuc.getText().equals("20536126440")) {
                txtDescripcionDetalle.setText("MINERAL AURIFERO LOTE:" + reintegroSeleccionado.getLote() + " LEY AU:" + reintegroSeleccionado.getLeyau() + " OZ/TC");
            }
            txtPrecioUnitarioDetalle.setText("" + reintegroSeleccionado.getStms());
            txtValorVenta.setText("" + reintegroSeleccionado.getTotalUs());
            txtCantidadDetalle.selectAll();
            txtCantidadDetalle.requestFocus();
        } else {
            btnGuardarDetalle.setEnabled(false);
        }
    }

    private void inabilitarBotonesDetalle(boolean estado) {

        // btnActualizarDetalle.setEnabled(estado);
        btnGuardarDetalle.setEnabled(estado);

    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        contador = Verificador.verificadorCampos(txtProveedor);
        aux = aux + contador;

        contador = Verificador.verificadorCampos(txtConsesion);
        aux = aux + contador;

        contador = Verificador.verificadorCampos(txtDescripcionMovito);
        aux = aux + contador;

        contador = Verificador.verificadorCamposFechas(dataFechaEmision);
        aux = aux + contador;
        contador = Verificador.verificadorCamposFechas(dataFechaEmisionComprobante);
        aux = aux + contador;
        return aux == 5;
    }

    private void guardarDatosFactura() {
        if (verificarCamposVacios()) {
            if (lblLectura.getText().equals("null")) {
                DesktopNotify.showDesktopMessage("FiveCod Software", "Error al consumir el webservice. Activar el servidor o comicarse con soporte ", DesktopNotify.ERROR);
                return;
            }
            if (notaDebitoSeleccionado.getListaNotaDebitoDetalle().size() > 0) {
                TipoNotaDebito tipoNot = (TipoNotaDebito) cboTipoNotaDebito.getItemAt(cboTipoNotaDebito.getSelectedIndex());
                notaDebitoSeleccionado.setTipoNotaDebito(tipoNot);
                notaDebitoSeleccionado.setDescripcionMotivo(txtDescripcionMovito.getText().toString());
                notaDebitoSeleccionado.setNumeroFactura(txtNumeroFactura.getText().toString());
                notaDebitoSeleccionado.setProveedorServicio(proveedorServicioSeleccionado);
                notaDebitoSeleccionado.setDenominacion(txtDenominacion.getText().toString());
                Date fechaEmision = dataFechaEmision.getDatoFecha();
                notaDebitoSeleccionado.setFechaEmision(new java.sql.Date(fechaEmision.getTime()));
                Date fechaEmisionComprobante = dataFechaEmisionComprobante.getDatoFecha();
                notaDebitoSeleccionado.setFechaEmision_Comprobante(new java.sql.Date(fechaEmisionComprobante.getTime()));
                notaDebitoSeleccionado.setConsecion(txtConsesion.getText().toString());
                notaDebitoSeleccionado.setCodigoUnico(txtCodigoUnico.getText());
                notaDebitoSeleccionado.setValorVenta(Double.parseDouble(txtValor.getText()));
                notaDebitoSeleccionado.setIGV(Double.parseDouble(txtIGV.getText()));
                notaDebitoSeleccionado.setTotal(Double.parseDouble(txtTotal.getText()));
                if (rbDolares.isSelected()) {
                    notaDebitoSeleccionado.setMoneda("$");
                } else {
                    notaDebitoSeleccionado.setMoneda("S/.");
                }
                notaDebitoSeleccionado.setLectura(lblLectura.getText());
                notaDebitoSeleccionado.setPersonal(IniciarSesion.getUsuario());

                int registros_afectados;
                if (tipo_accion == ACCION_CREAR) {
                    try {
                        registros_afectados = gestionarNotaDebitoServicio.guardarNotaDebito(notaDebitoSeleccionado);
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
                        notaDebitoSeleccionado.setCodigo(codigoNotaDebito);
                        registros_afectados = gestionarNotaDebitoServicio.modificarNotaDebito(notaDebitoSeleccionado);
                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de actualizar la factura numero  " + notaDebitoSeleccionado.getNumeroNotaDebito(), DesktopNotify.SUCCESS);
                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            btnImprimir.setEnabled(true);
                            notaDebitoSeleccionado = gestionarNotaDebitoServicio.buscarNotaDebitoPorCodigo(codigoNotaDebito);
                            llenarTablaFacturaDetalle();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Error al Actualizar", DesktopNotify.ERROR);

                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeActualizacion(this);
                    }

                }
            } else {
                Mensaje.mostrarMensajeDefinido(this, "No ha ingresado Detalles para la factura");

            }

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
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoNotaDebito> cboTipoNotaDebito;
    private rojeru_san.componentes.RSDateChooser dataFechaEmision;
    private rojeru_san.componentes.RSDateChooser dataFechaEmisionComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage30;
    private rojerusan.RSLabelImage rSLabelImage31;
    private rojerusan.RSLabelImage rSLabelImage32;
    private rojerusan.RSLabelImage rSLabelImage33;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    private RadioButton.FiveCodRadioButton rbDolares;
    private RadioButton.FiveCodRadioButton rbSoles;
    private rojerusan.RSTableMetro tableitemFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtBuscar;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCantidadDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoUnico;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtConsesion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDenominacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcionDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcionMovito;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDiireccion;
    private javax.swing.JTextField txtIGV;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNroNotaDebito;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNumeroFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPrecioUnitarioDetalle;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRuc;
    private javax.swing.JTextField txtTotal;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtUnidadDetalle;
    private javax.swing.JTextField txtValor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtValorVenta;
    // End of variables declaration//GEN-END:variables

    private void metodoParaEliminar() {

        if (Mensaje.mostrarPreguntaDeEliminacion(this)) {
            fila = tableitemFactura.getSelectedRow();
            String codigo = tableitemFactura.getValueAt(fila, 0).toString();
            NotaDebitoDetalle notaDebitoDetalle = notaDebitoSeleccionado.buscarNotaDebitoDetalle(Integer.parseInt(codigo));
            if (tipo_accion == ACCION_CREAR) {
                notaDebitoDetalle.setNotaDebitoEstado("Eliminar");
                if (notaDebitoSeleccionado.eliminarNotaDebitoDetalle(notaDebitoDetalle)) {
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
                    notaDebitoDetalle.setNotaDebitoEstado("Eliminar");
                    if (notaDebitoSeleccionado.eliminarNotaDebitoDetalle(notaDebitoDetalle)) {
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

    }
}
