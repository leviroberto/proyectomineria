package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.JSystemFileChooser;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarPagoTransporteReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarPagoTransporteServicio;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.PagoTransporte;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa3_dominio.TipoCliente;
import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;

import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarPagoTransporte extends javax.swing.JDialog {

    private GestionarPagoTransporteServicio gestionarPagoTransporteServicio;
    public static PagoTransporte pagoTransporteSeleccionado;
    public static ProveedorServicio proveedorServicioSeleccionado;
    private TipoCliente tipoCliente;
    public static Procedencia procedenciaSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;
    private int tipo_accion;
    DefaultTableModel modelo;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private JasperPrint print = null;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private GestionarPagoTransporteReporteServicio gestionarPagoTransporteReporteServicio;

    public FormGestionarPagoTransporte(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();

            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            gestionarPagoTransporteServicio = new GestionarPagoTransporteServicio();
            gestionarPagoTransporteReporteServicio = new GestionarPagoTransporteReporteServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(true);
            txtRutaNroOperacion.setEnabled(false);
            // txtCuenta.setEnabled(false);
            txtProveedorServicio.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnCancelarl.setEnabled(false);
            inabilitarCampos(false);
            try {
                gestionarPagoTransporteServicio.llenarCBOTipoCliente(1, cboTipoCliente);

            } catch (Exception e) {
            }
            dataFecha.setDatoFecha(new Date());
            dataFechaPago.setDatoFecha(new Date());
            rbNoPagado.setSelected(true);
            txtProveedorServicio.setEnabled(false);
            //txtCuenta.setEnabled(false);
            popMenu.add(pnlMenu);
            descativarPreviar(false);
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
        tabla.agregarColumna(new Columna("PROVEEDOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("NRO FACTURA", "java.lang.String"));
        tabla.agregarColumna(new Columna("PESO", "java.lang.String"));
        tabla.agregarColumna(new Columna("PRECIO", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL", "java.lang.String"));
        tabla.agregarColumna(new Columna("DETRACCIÓN", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCONTAR", "java.lang.String"));
        tabla.agregarColumna(new Columna("ADELANTO", "java.lang.String"));
        tabla.agregarColumna(new Columna("IMPORTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("NRO OPERACION", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA PAGO", "java.lang.String"));
        tabla.agregarColumna(new Columna("TIPO CLIENTE", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableTransporte.setModel(modeloTabla);
        //    tableTransporte.getColumn(tableTransporte.getColumnName(0)).setWidth(0);
        ////    tableTransporte.getColumn(tableTransporte.getColumnName(0)).setMinWidth(0);
        //   tableTransporte.getColumn(tableTransporte.getColumnName(0)).setMaxWidth(0);
        TableColumnModel columnModel = tableTransporte.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);

        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(25);
        columnModel.getColumn(3).setPreferredWidth(20);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(6).setPreferredWidth(20);
        columnModel.getColumn(7).setPreferredWidth(20);
        columnModel.getColumn(8).setPreferredWidth(20);
        columnModel.getColumn(9).setPreferredWidth(20);
        columnModel.getColumn(10).setPreferredWidth(30);
        columnModel.getColumn(11).setPreferredWidth(30);
        columnModel.getColumn(12).setPreferredWidth(30);
        columnModel.getColumn(13).setPreferredWidth(100);

        tableTransporte.setColumnModel(columnModel);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableTransporte.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            inabilitarCampos(false);
            limpiarCampos();
            limpiarTabla();
            gestionarPagoTransporteServicio.mostrarPagoTransportes(1, tableTransporte);
            gestionarPagoTransporteServicio.calcularTotalPagado(lblTotalPagado);
            gestionarPagoTransporteServicio.calcularTotalNoPagado(lblTotalNoPagado);

        } catch (Exception e) {
        }

    }

    private void limpiarCampos() {
        txtAdelanto.setText("");
        proveedorServicioSeleccionado = null;
        txtRutaBaucher.setText("");
        txtCuenta.setText("");
        txtDetraccion.setText("");
        txtAdelanto.setText("0.0");
        txtImporte.setText("");
        txtNroFactura.setText("");
        txtRutaNroOperacion.setText("");
        txtPeso.setText("");
        txtPrecio.setText("");
        txtProveedorServicio.setText("");
        txtPrecioPortTonelada.setText("");
        swDetraccion.setSelected(false);

    }

    private void inabilitarCampos(boolean estado) {
        txtAdelanto.setEnabled(estado);
        txtRutaBaucher.setEnabled(estado);
        txtDetraccion.setEnabled(estado);
        txtImporte.setEnabled(estado);
        txtNroFactura.setEnabled(estado);
        txtRutaNroOperacion.setEnabled(estado);
        txtPeso.setEnabled(estado);
        txtPrecio.setEnabled(estado);
        txtPrecioPortTonelada.setEnabled(estado);
        btnProveedorServicio.setEnabled(estado);
        cboTipoCliente.setEnabled(estado);
//        swDetraccion.setSelected(true);
        btnRutaBaucher.setEnabled(estado);
        btnViewBaucher.setEnabled(estado);
        dataFecha.setEnabled(estado);
        dataFechaPago.setEnabled(estado);
        swDetraccion.setEnabled(estado);

    }

    private void llenarCamposParaModificar(PagoTransporte pagoTransporte) {
        limpiarCampos();
        lblCodigo.setText(String.valueOf(pagoTransporte.getCodigo()));
        txtProveedorServicio.setText(pagoTransporte.getProveedorServicio().getRazonSocial());
        dataFecha.setDatoFecha(pagoTransporte.getFecha());
        txtNroFactura.setText(pagoTransporte.getNroFactura());
        txtRutaBaucher.setText(pagoTransporte.getRutaBaucher());
        txtPeso.setText("" + pagoTransporte.getPeso());
        txtPrecio.setText("" + pagoTransporte.getPrecio());
        txtPrecioPortTonelada.setText("" + pagoTransporte.getTotal());
        txtDetraccion.setText("" + pagoTransporte.getDetraccion());
        txtAdelanto.setText("" + pagoTransporte.getAdelanto());
        txtImporte.setText("" + pagoTransporte.getImporte());
        txtRutaNroOperacion.setText(pagoTransporte.getNroOperacion());
        dataFechaPago.setDatoFecha(pagoTransporte.getFechaPago());
        proveedorServicioSeleccionado = pagoTransporte.getProveedorServicio();
        if (pagoTransporte.getEstadoPagoTransporte().equals("Pagado")) {
            rbPagado.setSelected(true);
        } else {
            rbNoPagado.setSelected(true);
        }
        if (pagoTransporte.getDescontar().equals("Si")) {
            swDetraccion.setSelected(true);
        } else if (pagoTransporte.getDescontar().equals("No")) {
            swDetraccion.setSelected(false);
        }
        for (int i = 0; i < cboTipoCliente.getItemCount(); i++) {
            if (pagoTransporte.getTipoCliente().getDescripcion().equals(cboTipoCliente.getItemAt(i).getDescripcion())) {
                cboTipoCliente.setSelectedIndex(i);
            }
        }
        txtCuenta.setText(proveedorServicioSeleccionado.getCuente());
        pagoTransporteSeleccionado = pagoTransporte;

    }

    private void guardarDatos() {
        PagoTransporte pagoTransporte = new PagoTransporte();
        String estadoDescontar = "";
//        copiarArchivosGuardarBaucher(txtRutaBaucher.getText());
        if (verificarCamposVacios()) {

            pagoTransporte.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
//            Date fecha = dataFecha.getDatoFecha();
//            Date fechaPago = dataFechaPago.getDatoFecha();
//            pagoTransporte.setFechaPago(new java.sql.Date(fechaPago.getTime()));
            if (dataFecha.getDatoFecha() != null) {
                Date fechaPago = dataFecha.getDatoFecha();
                pagoTransporte.setFecha(new java.sql.Date(fechaPago.getTime()));
            }
            if (dataFechaPago.getDatoFecha() != null) {
                Date fechaPagos = dataFechaPago.getDatoFecha();
                pagoTransporte.setFechaPago(new java.sql.Date(fechaPagos.getTime()));
            }

            pagoTransporte.setProveedorServicio(proveedorServicioSeleccionado);
            pagoTransporte.setNroFactura(txtNroFactura.getText());
            pagoTransporte.setPeso(Double.parseDouble(txtPeso.getText()));
            pagoTransporte.setTotal(Double.parseDouble(txtPrecioPortTonelada.getText()));
            pagoTransporte.setDetraccion(Double.parseDouble(txtDetraccion.getText()));

            if (swDetraccion.isSelected() == true) {
                estadoDescontar = "Si";
            } else {
                estadoDescontar = "No";
            }

            pagoTransporte.setDescontar(estadoDescontar);
            if (txtAdelanto.getText() == "") {
                pagoTransporte.setAdelanto(0.0);
            } else {
                pagoTransporte.setAdelanto(Double.parseDouble(txtAdelanto.getText()));

            }

            pagoTransporte.setImporte(Double.parseDouble(txtImporte.getText()));
            pagoTransporte.setNroOperacion(txtRutaNroOperacion.getText());
            pagoTransporte.setRutaBaucher(txtRutaBaucher.getText());
            TipoCliente tipoClientes = (TipoCliente) cboTipoCliente.getItemAt(cboTipoCliente.getSelectedIndex());
            pagoTransporte.setTipoCliente(tipoClientes);
            pagoTransporte.setPersonal(IniciarSesion.getUsuario());
            pagoTransporte.setPrecio(Double.parseDouble(txtPrecio.getText()));
//          
            pagoTransporte.setPersonal(IniciarSesion.getUsuario());

            if (rbNoPagado.isSelected()) {
                pagoTransporte.setEstadoPagoTransporte("No Pagado");
            } else {

                pagoTransporte.setEstadoPagoTransporte("Pagado");
            }

            int registros_afectados;

            if (tipo_accion == ACCION_CREAR) {
                try {

                    registros_afectados = gestionarPagoTransporteServicio.guardarPagoTransporte(pagoTransporte);
                    if (registros_afectados == 1) {
//                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Pago Transporte", DesktopNotify.SUCCESS);

                        inicializarTabla();

                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    pagoTransporte.setCodigo(Integer.parseInt(lblCodigo.getText()));
                    registros_afectados = gestionarPagoTransporteServicio.modificarPagoTransporte(pagoTransporte);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar el Pago Transporte", DesktopNotify.SUCCESS);
//                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                        //    DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de modificar un pagoTransporte", 7);

                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);

                }

            }
        }
    }

    private void copiarArchivosGuardarBaucher(String rutaOrigen) {
        File origen = new File(rutaOrigen);
        try {
            JOptionPane.showMessageDialog(null, rutaOrigen.toCharArray());
            String tipodeArchivo = Files.probeContentType(origen.toPath());
            JOptionPane.showMessageDialog(null, tipodeArchivo);
            File destino = new File("C:\\SistemaLara\\PagoTransporte\\Buucher\\" + 4);
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        lblCodigo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTransporte = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        btnCancelarl = new rojeru_san.RSButton();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        lblPersonal4 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        rSLabelImage13 = new rojerusan.RSLabelImage();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        dataFechaPago = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        txtPeso = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtProveedorServicio = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtNroFactura = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPrecio = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPrecioPortTonelada = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDetraccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtAdelanto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtImporte = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtRutaBaucher = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtRutaNroOperacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCuenta = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        btnNuevo = new rojeru_san.RSButton();
        btn_Guardar = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        jPanel6 = new javax.swing.JPanel();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        jPanel7 = new javax.swing.JPanel();
        dataFecha = new rojeru_san.componentes.RSDateChooser();
        btnProveedorServicio = new rojeru_san.RSButton();
        previewCheque = new com.icm.components.metro.CheckBoxMetroICM();
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
        rSPanelShadow1 = new rojeru_san.RSPanelShadow();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTotalPagado = new javax.swing.JLabel();
        lblTotalNoPagado = new javax.swing.JLabel();
        btnViewBaucher = new rojeru_san.RSButton();
        btnCheque = new rojeru_san.RSButton();
        swDetraccion = new com.icm.components.metro.switchButtonMetroICM();
        btnRutaBaucher = new rojeru_san.RSButton();
        cboTipoCliente = new FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoCliente>();
        rbPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rbNoPagado = new com.icm.components.metro.RadioButtonMetroICM();

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
        jLabel1.setText("GESTIONAR DATOS DE PAGO TRANSPORTE ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoPago.png"))); // NOI18N

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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableTransporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "hgh", "ghgh"
            }
        ));
        tableTransporte.setAltoHead(30);
        tableTransporte.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableTransporte.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableTransporte.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableTransporte.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableTransporte.setComponentPopupMenu(popMenu);
        tableTransporte.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        tableTransporte.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableTransporte.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableTransporte.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tableTransporte.setGrosorBordeFilas(0);
        tableTransporte.setGrosorBordeHead(0);
        tableTransporte.setRowHeight(20);
        tableTransporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableTransporteMousePressed(evt);
            }
        });
        tableTransporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableTransporteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableTransporteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableTransporteKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableTransporte);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelarl.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelarl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCancelarl.setText("CANCELAR");
        btnCancelarl.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelarl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelarl.setIconTextGap(0);
        btnCancelarl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarlActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelarl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, 140, 30));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 40, 30));
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 200, 10));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoProveedor.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 30));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NumeroFactura.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 40, 30));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cuenta.png"))); // NOI18N
        jPanel4.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 40, 30));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 40, 30));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 40, 30));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 40, 30));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Operacion.png"))); // NOI18N
        jPanel4.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 40, 30));

        dataFechaPago.setColorBackground(new java.awt.Color(65, 94, 255));
        dataFechaPago.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dataFechaPago.setColorForeground(new java.awt.Color(65, 94, 255));
        dataFechaPago.setFormatoFecha("yyyy-MM-dd");
        dataFechaPago.setPlaceholder("FECHA PAGO");
        jPanel4.add(dataFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 170, 30));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 40, 30));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Detraccio.png"))); // NOI18N
        jPanel4.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 40, 30));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 40, 30));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 40, 30));

        txtPeso.setForeground(new java.awt.Color(0, 0, 255));
        txtPeso.setAccent(new java.awt.Color(255, 0, 51));
        txtPeso.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPeso.setLabel("PESO TMH");
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });
        jPanel4.add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 200, 60));

        txtProveedorServicio.setForeground(new java.awt.Color(0, 0, 255));
        txtProveedorServicio.setAccent(new java.awt.Color(255, 0, 51));
        txtProveedorServicio.setCaretColor(new java.awt.Color(0, 0, 204));
        txtProveedorServicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtProveedorServicio.setLabel("PROVEEDOR SERVICIO");
        jPanel4.add(txtProveedorServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -10, 160, 60));

        txtNroFactura.setForeground(new java.awt.Color(0, 0, 255));
        txtNroFactura.setAccent(new java.awt.Color(255, 0, 51));
        txtNroFactura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNroFactura.setLabel("N° FACTURA");
        txtNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroFacturaKeyTyped(evt);
            }
        });
        jPanel4.add(txtNroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 74, 200, 60));

        txtPrecio.setForeground(new java.awt.Color(0, 0, 255));
        txtPrecio.setAccent(new java.awt.Color(255, 0, 51));
        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPrecio.setLabel("PRECIO X TMH");
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });
        jPanel4.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 200, 60));

        txtPrecioPortTonelada.setForeground(new java.awt.Color(0, 0, 255));
        txtPrecioPortTonelada.setAccent(new java.awt.Color(255, 0, 51));
        txtPrecioPortTonelada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPrecioPortTonelada.setLabel("TOTAL PREC. X TONEL.");
        txtPrecioPortTonelada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioPortToneladaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioPortToneladaKeyTyped(evt);
            }
        });
        jPanel4.add(txtPrecioPortTonelada, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, -10, 150, 60));

        txtDetraccion.setForeground(new java.awt.Color(0, 0, 255));
        txtDetraccion.setAccent(new java.awt.Color(255, 0, 51));
        txtDetraccion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDetraccion.setLabel("DETRACCION: ");
        txtDetraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDetraccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDetraccionKeyTyped(evt);
            }
        });
        jPanel4.add(txtDetraccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 120, 70));

        txtAdelanto.setForeground(new java.awt.Color(0, 0, 255));
        txtAdelanto.setAccent(new java.awt.Color(255, 0, 51));
        txtAdelanto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtAdelanto.setLabel("ADELANTO");
        txtAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdelantoActionPerformed(evt);
            }
        });
        txtAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyTyped(evt);
            }
        });
        jPanel4.add(txtAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 150, 60));

        txtImporte.setForeground(new java.awt.Color(0, 0, 255));
        txtImporte.setAccent(new java.awt.Color(255, 0, 51));
        txtImporte.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtImporte.setLabel("IMPORTE   A PAGAR ");
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtImporteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });
        jPanel4.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 150, 60));

        txtRutaBaucher.setForeground(new java.awt.Color(0, 0, 255));
        txtRutaBaucher.setAccent(new java.awt.Color(255, 0, 51));
        txtRutaBaucher.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRutaBaucher.setLabel("CARGAR VAUCHER");
        txtRutaBaucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutaBaucherKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRutaBaucherKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutaBaucherKeyTyped(evt);
            }
        });
        jPanel4.add(txtRutaBaucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 240, 60));

        txtRutaNroOperacion.setForeground(new java.awt.Color(0, 0, 255));
        txtRutaNroOperacion.setAccent(new java.awt.Color(255, 0, 51));
        txtRutaNroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRutaNroOperacion.setLabel("N° OPERACION:");
        txtRutaNroOperacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutaNroOperacionKeyPressed(evt);
            }
        });
        jPanel4.add(txtRutaNroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, -10, 210, 60));

        txtCuenta.setForeground(new java.awt.Color(0, 0, 255));
        txtCuenta.setAccent(new java.awt.Color(255, 0, 51));
        txtCuenta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCuenta.setLabel("Cuenta ");
        txtCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuentaKeyPressed(evt);
            }
        });
        jPanel4.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 180, 60));

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 140, 30));

        btn_Guardar.setBackground(new java.awt.Color(65, 94, 255));
        btn_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btn_Guardar.setText("GUARDAR");
        btn_Guardar.setColorHover(new java.awt.Color(253, 173, 1));
        btn_Guardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Guardar.setIconTextGap(0);
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btn_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 140, 30));

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
        jPanel4.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 60, 140, 30));

        jPanel6.setBackground(new java.awt.Color(65, 94, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 2, 210));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 40, 30));

        jPanel7.setBackground(new java.awt.Color(65, 94, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        dataFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dataFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dataFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dataFecha.setFormatoFecha("yyyy-MM-dd");
        dataFecha.setPlaceholder("FECHA ");
        jPanel4.add(dataFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 200, 30));

        btnProveedorServicio.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedorServicio.setText("Ver");
        btnProveedorServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorServicioActionPerformed(evt);
            }
        });
        jPanel4.add(btnProveedorServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 40, 30));

        previewCheque.setBorder(null);
        previewCheque.setText("PREVIEW");
        previewCheque.setDark(false);
        previewCheque.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        previewCheque.setHideActionText(true);
        previewCheque.setIconTextGap(1);
        jPanel4.add(previewCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 190, 90, -1));

        txtBuscarTipoTrabajador.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarTipoTrabajador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarTipoTrabajador.setPrompt("BUSCAR RAZON SOCIAL,N° FACTURA");
        txtBuscarTipoTrabajador.setPromptBackround(null);
        txtBuscarTipoTrabajador.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarTipoTrabajador.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarTipoTrabajador.setSelectionEnd(1);
        txtBuscarTipoTrabajador.setSelectionStart(2);
        txtBuscarTipoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarTipoTrabajadorActionPerformed(evt);
            }
        });
        txtBuscarTipoTrabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarTipoTrabajadorKeyReleased(evt);
            }
        });
        jPanel4.add(txtBuscarTipoTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 340, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("TOTAL PAGADO: ");

        jLabel4.setText("TOTAL NO PAGADO: ");

        lblTotalPagado.setText("Total");

        lblTotalNoPagado.setText("Total");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalPagado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalNoPagado, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalPagado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTotalNoPagado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelShadow1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 110, 300, 70));

        btnViewBaucher.setBackground(new java.awt.Color(65, 94, 255));
        btnViewBaucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnViewBaucher.setText("Ver");
        btnViewBaucher.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnViewBaucher.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnViewBaucher.setIconTextGap(2);
        btnViewBaucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBaucherActionPerformed(evt);
            }
        });
        jPanel4.add(btnViewBaucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, 90, 30));

        btnCheque.setBackground(new java.awt.Color(65, 94, 255));
        btnCheque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cheque.png"))); // NOI18N
        btnCheque.setText("CHEQUE");
        btnCheque.setColorHover(new java.awt.Color(253, 173, 1));
        btnCheque.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCheque.setIconTextGap(0);
        btnCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChequeActionPerformed(evt);
            }
        });
        jPanel4.add(btnCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 190, 120, 30));

        swDetraccion.setBorder(null);
        swDetraccion.setToolTipText("Descontar");
        swDetraccion.setDark(false);
        swDetraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swDetraccionActionPerformed(evt);
            }
        });
        jPanel4.add(swDetraccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        btnRutaBaucher.setBackground(new java.awt.Color(65, 94, 255));
        btnRutaBaucher.setText("...");
        btnRutaBaucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaBaucherActionPerformed(evt);
            }
        });
        jPanel4.add(btnRutaBaucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 40, 30));

        cboTipoCliente.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoCliente.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoCliente.setAccent(new java.awt.Color(65, 94, 255));
        cboTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoClienteActionPerformed(evt);
            }
        });
        jPanel4.add(cboTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 188, 170, 40));

        rbPagado.setBorder(null);
        buttonGroup3.add(rbPagado);
        rbPagado.setText("Pagado ");
        jPanel4.add(rbPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 90, -1));

        rbNoPagado.setBorder(null);
        buttonGroup3.add(rbNoPagado);
        rbNoPagado.setSelected(true);
        rbNoPagado.setText("No Pagado ");
        jPanel4.add(rbNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1404, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerDatosParaTabla() throws Exception {
//        listaPagoTransporte = gestionarPagoTransporteServicio.mostrarPagoTransportes(PagoTransporte.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }
    int fila;
    private void tableTransporteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransporteMousePressed
        PagoTransporte liqui = null;
        try {
            descativarPreviar(true);
            tipo_accion = ACCION_MODIFICAR;
            btnImprimir.setEnabled(true);

            fila = tableTransporte.getSelectedRow();
            String codigo = tableTransporte.getValueAt(fila, 0).toString();
            liqui = gestionarPagoTransporteServicio.buscarPagoTransportePorCodigo(Integer.parseInt(codigo));
            inabilitarCampos(true);
            llenarCamposParaModificar(liqui);
            btnCancelarl.setEnabled(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }

    }//GEN-LAST:event_tableTransporteMousePressed

    private void tableTransporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTransporteKeyReleased
        fila = tableTransporte.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableTransporteKeyReleased

    private void tableTransporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTransporteKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
//                    obtenerPagoTransporte();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableTransporteKeyTyped

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
    double totaltms;
    private void tableTransporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTransporteKeyPressed

//        int filamodificar = tableTransporte.getSelectedRow();
//        if (filamodificar >= 0) {
//            txtCodigo.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtLote.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtTmh.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtH2O.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtTms.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//
//            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("YYYY-MM-dd");
//            String strFecha = tableTransporte.getValueAt(filamodificar, 1).toString();
//            Date fecha = null;
//            try {
//                fecha = formatoDelTexto.parse(strFecha);
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//            dateFecha.setDatoFecha(fecha);
//
//            txtLeyAu.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtLeyAg.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtInter.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtRec.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtMaquilla.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtFactor.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//
//            txtConscn.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtEscalador.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txt$tms.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//
//            txtTotalUS.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//
//            //ESTADO LIQUIDACION
//            if (tableTransporte.getValueAt(filamodificar, 20).toString().equals("N")) {
//                rbtn.setSelected(true);
//                rbtv.setSelected(false);
//            } else if (tableTransporte.getValueAt(filamodificar, 20).toString().equals("V")) {
//                rbtn.setSelected(false);
//                rbtv.setSelected(true);
//            }
//
//            txtProveedorServicio.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//            txtProcedencia.setText(tableTransporte.getValueAt(filamodificar, 0).toString());
//        } else {
//            JOptionPane.showMessageDialog(this, "No ha seleccionado ");
//        }
        fila = tableTransporte.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }

    }//GEN-LAST:event_tableTransporteKeyPressed

    private void btnProveedorServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorServicioActionPerformed
        abrirFormulariosProveedor();
    }//GEN-LAST:event_btnProveedorServicioActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        try {
            if (rbPagado.isSelected()) {
                Mensaje.mostrarMensajeDefinido(this, "El registro no se puede Imprimir por que el estado esta en Pagado");
            } else {
                print = gestionarPagoTransporteReporteServicio.mostrarPagoTransporte(pagoTransporteSeleccionado);
            }
        } catch (Exception ex) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        opcionNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed
    private void abrirFormulariosProveedor() {
        FormGestionarProveedorServicios marca = new FormGestionarProveedorServicios(null, true, FormGestionarProveedorServicios.TIPO_PAGO_TRANSPORTE);
        marca.setVisible(true);
        if (proveedorServicioSeleccionado != null) {
            txtProveedorServicio.setText(proveedorServicioSeleccionado.getRazonSocial());
            txtCuenta.setText(proveedorServicioSeleccionado.getRuc());
            txtNroFactura.selectAll();
            txtNroFactura.requestFocus();
        }
    }
    
    private void opcionNuevo() {
        pagoTransporteSeleccionado = null;
        pagoTransporteSeleccionado = new PagoTransporte();
        pagoTransporteSeleccionado.setDescontar("No");
        pagoTransporteSeleccionado.setPeso(0.0);
        pagoTransporteSeleccionado.setTotal(0.0);
        pagoTransporteSeleccionado.setDetraccion(0.0);
        pagoTransporteSeleccionado.setAdelanto(0.0);
        pagoTransporteSeleccionado.setImporte(0.0);
        tipo_accion = ACCION_CREAR;
        limpiarCampos();
        btnImprimir.setEnabled(false);
        btnCancelarl.setEnabled(true);
        inabilitarCampos(true);
        rbNoPagado.setSelected(true);
        dataFecha.setDatoFecha(new Date());
        dataFechaPago.setDatoFecha(null);
        swDetraccion.setSelected(false);
        abrirFormulariosProveedor();
    }
    private void txtRutaBaucherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaBaucherKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtRutaBaucher.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRutaBaucherKeyTyped

    private void txtRutaBaucherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaBaucherKeyReleased
//        double thm = Double.parseDouble(this.txtTmh.getText());
//        double ph2o = Double.parseDouble(this.txtH2O.getText());
//        totaltms = +(thm - (thm * (ph2o / 100)));
//
//        double leyau = Double.parseDouble(this.txtPrecio.getText());
//        double inter = Double.parseDouble(this.txtpreciopt.getText());
//        double rec = Double.parseDouble(this.txtRec.getText());
//        double maquilla = Double.parseDouble(this.txtMaquilla.getText());
//        double factor = Double.parseDouble(this.txtFactor.getText());
//        double conscon = Double.parseDouble(this.txtConscn.getText());
//        double total = +(((((leyau * (rec / 100))) * (inter - 60)) - maquilla - conscon) * factor);
//        double t = Double.parseDouble(txt$tms.getText());
//        double totalu = +totaltms * t;
//        this.txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
    }//GEN-LAST:event_txtRutaBaucherKeyReleased

    private void txtRutaBaucherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaBaucherKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtCuenta.requestFocus();
        }
    }//GEN-LAST:event_txtRutaBaucherKeyPressed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtImporte.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtRutaNroOperacion.requestFocus();
        }
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyTyped

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtAdelanto.getText().equals("")) {
                txtAdelanto.setText("0.0");
            }
            pagoTransporteSeleccionado.setAdelanto(Double.parseDouble(txtAdelanto.getText()));
            obtenerCalculos();

//            Double preciot = Double.parseDouble(txtPrecioPortTonelada.getText());
//            Double adelanto = Double.parseDouble(txtAdelanto.getText());
//            Double total = preciot - adelanto;
//            txtImporte.setText(total + "");
        }
    }//GEN-LAST:event_txtAdelantoKeyTyped

    private void txtAdelantoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtImporte.requestFocus();
        }
    }//GEN-LAST:event_txtAdelantoKeyPressed

    private void txtDetraccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetraccionKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtDetraccion.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDetraccionKeyTyped

    private void txtDetraccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetraccionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAdelanto.requestFocus();
            txtAdelanto.selectAll();
        }
    }//GEN-LAST:event_txtDetraccionKeyPressed

    private void txtPrecioPortToneladaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPortToneladaKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecioPortTonelada.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioPortToneladaKeyTyped

    private void txtPrecioPortToneladaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPortToneladaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDetraccion.requestFocus();
            txtDetraccion.selectAll();
        }
    }//GEN-LAST:event_txtPrecioPortToneladaKeyPressed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPrecio.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        double detraccion;
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtPrecio.getText().equals("")) {
                txtPrecio.setText("0.0");
            }
            obtenerCalculos();
            txtPrecioPortTonelada.requestFocus();
        }


    }//GEN-LAST:event_txtPrecioKeyPressed

    private void obtenerCalculos() {
        if (txtPrecio.getText().equals("")) {
            txtPrecio.setText("0");
        }
        pagoTransporteSeleccionado.setPrecio(Double.parseDouble(txtPrecio.getText()));
        txtDetraccion.setText("" + pagoTransporteSeleccionado.calcularDetraccion());
        txtPrecioPortTonelada.setText("" + pagoTransporteSeleccionado.calcularPrecioPorTonelada());
        txtImporte.setText("" + pagoTransporteSeleccionado.calcularImporte());

        txtImporte.setText("" + pagoTransporteSeleccionado.calcularDescuentoDestraccion());

    }
    private void txtNroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyTyped

    }//GEN-LAST:event_txtNroFacturaKeyTyped

    private void txtNroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarDatosTablePorFactura(txtNroFactura.getText());
        }
    }//GEN-LAST:event_txtNroFacturaKeyReleased

    private void txtNroFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFacturaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtNroFacturaKeyPressed

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtPeso.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtPesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txtPeso.getText().equals("")) {
                txtPeso.setText("0.0");
            }
            txtPrecio.requestFocus();
            pagoTransporteSeleccionado.setPeso(Double.parseDouble(txtPeso.getText()));
        }
    }//GEN-LAST:event_txtPesoKeyPressed

    private void btnCancelarlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarlActionPerformed
        inabilitarCampos(false);
        limpiarCampos();
        btnImprimir.setEnabled(false);
        btnCheque.setEnabled(false);
        previewCheque.setEnabled(false);

    }//GEN-LAST:event_btnCancelarlActionPerformed

    private void txtBuscarTipoTrabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyPressed
        //        try {
        //            if (!txtBuscarPagoTransporte.getText().equals("")) {
        //                listaPagoTransporte = null;
        //                listaPagoTransporte = gestionarPagoTransporteServicio.buscarPagoTransportePorNombre(txtBuscarPagoTransporte.getText().trim());
        //                obtenerDatosTabla();
        //            } else {
        //                listaPagoTransporte = null;
        //                obtenerDatosParaTabla();
        //                txtBuscarPagoTransporte.setText("");
        //            }
        //
        //        } catch (Exception ex) {
        //            Mensaje.mostrarErrorDeConsulta(this);
        //        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyPressed

    private void txtBuscarTipoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarTipoTrabajadorActionPerformed

    private void btnViewBaucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBaucherActionPerformed
        try {
            if (txtRutaBaucher.getText().equals("")) {
                Mensaje.mostrarMensajeDefinido(this, "No tiene ruta para el baucher");
            } else {
                String url = txtRutaBaucher.getText();
                ProcessBuilder p = new ProcessBuilder();
                p.command("cmd.exe", "/c", url);
                p.start();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnViewBaucherActionPerformed

    private void txtAdelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyReleased

    }//GEN-LAST:event_txtAdelantoKeyReleased

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        ChequeProveedor cheque = new ChequeProveedor();
        cheque.setProveedorServicio(proveedorServicioSeleccionado);
        cheque.setEntregadoA(proveedorServicioSeleccionado.getRazonSocial());
        cheque.setConcepto("POR FACTURA DE TRANSPORTE N° " + pagoTransporteSeleccionado.getNroFactura() + "FECHA FAC. " + pagoTransporteSeleccionado.getFecha());
        cheque.setMoneda("S/.");
        cheque.setMonto(pagoTransporteSeleccionado.getImporte());
        FormGestionarChequeProveedorServicio a = new FormGestionarChequeProveedorServicio(null, true);

        int estadoPreview = 0;

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
        rbPagado.setSelected(true);
        guardarDatos();
        descativarPreviar(false);
    }//GEN-LAST:event_btnChequeActionPerformed

    private void swDetraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swDetraccionActionPerformed
        if (swDetraccion.isSelected()) {
            pagoTransporteSeleccionado.setDescontar("Si");
            obtenerCalculos();
        } else {
            pagoTransporteSeleccionado.setDescontar("No");
            obtenerCalculos();

        }
    }//GEN-LAST:event_swDetraccionActionPerformed

    private void btnRutaBaucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaBaucherActionPerformed
//        Creamos nuestra variable archivo en la cual podremos usar todos los metodos de la clase jFileChooser
        JSystemFileChooser a = new JSystemFileChooser();

        JFileChooser archivo = a;

        //Si deseamos crear filtros para la selecion de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPG(*.jpg)", "jpg", " otros");
        //Si deseas que se muestre primero los filtros usa la linea q esta abajo de esta.
        //archivo.setFileFilter(filtro);
        // Agregamos el Filtro pero cuidado se mostrara despues de todos los archivos
        archivo.addChoosableFileFilter(filtro);
        // Colocamos titulo a nuestra ventana de Seleccion
        archivo.setDialogTitle("Abrir Archivo");
        //Si deseamos que muestre una carpeta predetermina usa la siguiente linea
        File ruta = new File("C:/Documentos");
        //Le implementamos a nuestro ventana de seleccion
        archivo.setCurrentDirectory(ruta);
        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            txtRutaBaucher.setText(file + "");
            Image logo;
        }
    }//GEN-LAST:event_btnRutaBaucherActionPerformed

    private void txtBuscarTipoTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyReleased
        try {
            String texto = txtBuscarTipoTrabajador.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarPagoTransporteServicio.mostrarPagoTransportes(1, tableTransporte, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyReleased

    private void cboTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoClienteActionPerformed

    private void txtAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdelantoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdelantoActionPerformed

    private void txtRutaNroOperacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutaNroOperacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtRutaBaucher.requestFocus();
        }
    }//GEN-LAST:event_txtRutaNroOperacionKeyPressed

    private void txtCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtCuentaKeyPressed

    private void obtenerPagoTransporteSeleccionado() {
        try {
            String codigo = tableTransporte.getValueAt(fila, 0).toString();
            pagoTransporteSeleccionado = gestionarPagoTransporteServicio.buscarPagoTransportePorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        contador = Verificador.verificadorCampos(txtProveedorServicio);
        aux = aux + contador;
        contador = Verificador.verificadorCampos(txtImporte);
        aux = aux + contador;
        contador = Verificador.verificarCantidadLetras(txtNroFactura, 2);
        aux = aux + contador;
        return aux == 3;
    }

    private void inabilitarBotones(Boolean v) {

        btnProveedorServicio.setEnabled(v);
        btnEliminar.setEnabled(true);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCancelarl;
    private rojeru_san.RSButton btnCheque;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnNuevo;
    private rojeru_san.RSButton btnProveedorServicio;
    private rojeru_san.RSButton btnRutaBaucher;
    private rojeru_san.RSButton btnViewBaucher;
    private rojeru_san.RSButton btn_Guardar;
    private javax.swing.ButtonGroup buttonGroup3;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<TipoCliente> cboTipoCliente;
    private rojeru_san.componentes.RSDateChooser dataFecha;
    private rojeru_san.componentes.RSDateChooser dataFechaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblPersonal4;
    private javax.swing.JLabel lblTotalNoPagado;
    private javax.swing.JLabel lblTotalPagado;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private com.icm.components.metro.CheckBoxMetroICM previewCheque;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage18;
    private rojerusan.RSLabelImage rSLabelImage19;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private com.icm.components.metro.RadioButtonMetroICM rbNoPagado;
    private com.icm.components.metro.RadioButtonMetroICM rbPagado;
    private com.icm.components.metro.switchButtonMetroICM swDetraccion;
    private rojerusan.RSTableMetro tableTransporte;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtAdelanto;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCuenta;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDetraccion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtImporte;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNroFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPeso;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPrecio;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPrecioPortTonelada;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedorServicio;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRutaBaucher;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRutaNroOperacion;
    // End of variables declaration//GEN-END:variables

    private void cargarDatosTablePorFactura(String numero) {
        try {

            limpiarTabla();
            gestionarPagoTransporteServicio.mostrarPagoTransportesPorNumeroFactura(1, numero, tableTransporte);
        } catch (Exception e) {
        }
    }

    private void descativarPreviar(boolean estado) {
        btnCheque.setEnabled(estado);
        previewCheque.setEnabled(estado);
    }
//    private void obtenerPagoTransporte() {
////        String codigo = tablaPagoTransportes.getValueAt(fila, 0).toString();
////
////        try {
////            pagoTransporteSeleccionado = gestionarPagoTransporteServicio.buscarPagoTransportePorCodigo(codigo.trim());
////            if (TIPO_USUARIO == TIPO_TRABAJADOR) {
////                pagoTransporteSeleccionado = pagoTransporteSeleccionado;
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

    private void metodoParaEliminar() {
        obtenerPagoTransporteSeleccionado();
        if (pagoTransporteSeleccionado != null) {
            if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                return;
            }
            try {
                int registros_afectados = gestionarPagoTransporteServicio.eliminarPagoTransporte(pagoTransporteSeleccionado);
                if (registros_afectados == 1) {
                    DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar  Pago Transporte", DesktopNotify.SUCCESS);

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
    }
}
