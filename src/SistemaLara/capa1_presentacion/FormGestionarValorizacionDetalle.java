package SistemaLara.capa1_presentacion;

import static SistemaLara.capa1_presentacion.FormListaPagosPorPersonal.contratoSeleccionado;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FCCustomPopMenu;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.NumeroToLetras;
import SistemaLara.capa1_presentacion.util.RedondearDecimales;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarValorizacionReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarValorizacionServicio;
import SistemaLara.capa3_dominio.Cambio;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Empresa;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Valorizacion;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarTipoTrabajadorServicio;
//import motorepuestos.capa3_dominio.TipoTrabajador;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarValorizacionDetalle extends javax.swing.JDialog {

    private List<Empresa> listaEmpresa = new ArrayList<Empresa>();
    private static GestionarValorizacionServicio gestionarValorizacionServicio;
    public static Empresa empresaSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int tipo_accion;
    private int TIPO_USUARIO;
    private static Liquidacion liquidacionSeleccionado;
    private ClienteEntrante clienteEntranteSeleccionado;
    private Valorizacion valorizacionSeleccionado;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private JasperPrint print = null;
    private int filaValoracion;
    private GestionarValorizacionReporteServicio gestionarValorizacionReporteServicio;
    private Date fechaSeleccionada;

    public FormGestionarValorizacionDetalle(java.awt.Frame parent, boolean modal, Liquidacion liquidacion, Date fecha) {
        super(parent, modal);
        try {
            initComponents();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            popMenuValirizacion.add(pnlMenuValorizacion);
            fechaSeleccionada = fecha;
            empresaSeleccionado = new Empresa();
            gestionarValorizacionServicio = new GestionarValorizacionServicio();
            this.liquidacionSeleccionado = liquidacion;
            this.clienteEntranteSeleccionado = liquidacion.getClienteEntrante();
            gestionarValorizacionReporteServicio = new GestionarValorizacionReporteServicio();
            inicializarTablaColumnasLiquidacion();
            inicializarTablaColumnasValorizacion();
            llenarCamposCliente(liquidacion);
            inicializarTablaLiquidacion(liquidacion.getClienteEntrante().getCodigo(), fechaSeleccionada);
            inicializarTablaValorizacion(liquidacion.getClienteEntrante().getCodigo(), fechaSeleccionada);
            inabilitarCamposGastos(false);
            btnImprimir.setEnabled(false);
            inabilitarBotones(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void inicializarTablaColumnasLiquidacion() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("PROCE", "java.lang.String"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("TMH", "java.lang.String"));
        tabla.agregarColumna(new Columna("TMS", "java.lang.String"));
        tabla.agregarColumna(new Columna("H2O", "java.lang.String"));
        tabla.agregarColumna(new Columna("LEYAU", "java.lang.String"));
        tabla.agregarColumna(new Columna("LEYAG", "java.lang.String"));
        tabla.agregarColumna(new Columna("INTER", "java.lang.String"));
        tabla.agregarColumna(new Columna("REC", "java.lang.String"));
        tabla.agregarColumna(new Columna("MAQUILL", "java.lang.String"));
        tabla.agregarColumna(new Columna("FACTOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("CONSCON", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESCALA", "java.lang.String"));
        tabla.agregarColumna(new Columna("STMS", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL US", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        tabla.agregarColumna(new Columna("VALORIZACION", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableLiquidacion.setModel(modeloTabla);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMinWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMaxWidth(0);

        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(18)).setWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(18)).setMinWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(18)).setMaxWidth(0);
    }

    private void inicializarTablaColumnasValorizacion() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("TMH", "java.lang.String"));
        tabla.agregarColumna(new Columna("COST1", "java.lang.String"));
        tabla.agregarColumna(new Columna("COST2", "java.lang.String"));
        tabla.agregarColumna(new Columna("LAB.", "java.lang.String"));
        tabla.agregarColumna(new Columna("TUS$", "java.lang.String"));
        tabla.agregarColumna(new Columna("%", "java.lang.String"));
        tabla.agregarColumna(new Columna("AD", "java.lang.String"));
        tabla.agregarColumna(new Columna("O GASTOS", "java.lang.String"));
        tabla.agregarColumna(new Columna("T GASTOS", "java.lang.String"));

        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("T/C", "java.lang.String"));
        tabla.agregarColumna(new Columna("TCOST1", "java.lang.String"));
        tabla.agregarColumna(new Columna("TCOST2", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLOTES", "java.lang.String"));
        tabla.agregarColumna(new Columna("T %", "java.lang.String"));
        tabla.agregarColumna(new Columna("T PAGAR $", "java.lang.String"));
        tabla.agregarColumna(new Columna("T PAGAR S/", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableValorizacion.setModel(modeloTabla);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setWidth(0);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setMinWidth(0);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(0)).setMaxWidth(0);

        tableValorizacion.getColumn(tableValorizacion.getColumnName(18)).setWidth(0);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(18)).setMinWidth(0);
        tableValorizacion.getColumn(tableValorizacion.getColumnName(18)).setMaxWidth(0);
    }

    public void limpiarTablaLiquidacion() {
        ModeloTabla modelo = (ModeloTabla) tableLiquidacion.getModel();
        modelo.eliminarTotalFilas();
    }

    public void limpiarTablaValorizacion() {
        ModeloTabla modelo = (ModeloTabla) tableValorizacion.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTablaLiquidacion(int codigo, Date fecha) {
        try {
            limpiarTablaLiquidacion();
            gestionarValorizacionServicio.mostrarParaDetalleLiquidacion(tableLiquidacion, codigo, fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void inicializarTablaValorizacion(int codigo, Date fecha) {
        try {
            limpiarTablaValorizacion();
            gestionarValorizacionServicio.mostrarParaDetalleValorizacion(tableValorizacion, codigo, fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void limpiarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tableLiquidacion.getModel();
            int filas = tableLiquidacion.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }

        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenuValorizacion = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        btnMostrarAdelantos = new rojeru_san.RSButton();
        popMenuValirizacion = new javax.swing.JPopupMenu();
        popMenu = new rojerusan.RSPopuMenu();
        btnValorizar = new javax.swing.JMenuItem();
        btnModificar = new javax.swing.JMenuItem();
        btnDesvalorizar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtFechaCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCodigoCliente1 = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtClienteNombre = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDni = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTipoCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCambio = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCodigoValorizacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel6 = new javax.swing.JPanel();
        txtTotalUs$ = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTmh = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel8 = new javax.swing.JPanel();
        txtCostoTransNascaDolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtToneladas = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtOtrosGastos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCostoTransTrujiDolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCostoTransNascaSoles = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtCostoTransTrujilloSoles = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtNumeroLote = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTarifaPorcentaje = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalPorcentaje = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTarifaAnalisis = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtPolicia = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalAnalisis = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtAdelanto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtSalgoPagar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalGastos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_TotalPagarSoles = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        lbl_son = new javax.swing.JLabel();
        rbPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rbNoPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        previewCheque = new com.icm.components.metro.CheckBoxMetroICM();
        jPanel9 = new javax.swing.JPanel();
        btnCancelar = new rojeru_san.RSButton();
        btnCancelar1 = new rojeru_san.RSButton();
        btnAgregar = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        btnCheque = new rojeru_san.RSButton();
        btnImprimir1 = new rojeru_san.RSButton();
        btnImprimir2 = new rojeru_san.RSButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableValorizacion = new rojerusan.RSTableMetro();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLiquidacion = new rojerusan.RSTableMetro();

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

        btnMostrarAdelantos.setBackground(new java.awt.Color(65, 94, 255));
        btnMostrarAdelantos.setBorder(null);
        btnMostrarAdelantos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnMostrarAdelantos.setText("VER ADELANTOS");
        btnMostrarAdelantos.setColorHover(new java.awt.Color(255, 82, 54));
        btnMostrarAdelantos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMostrarAdelantos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMostrarAdelantos.setIconTextGap(2);
        btnMostrarAdelantos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarAdelantosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuValorizacionLayout = new javax.swing.GroupLayout(pnlMenuValorizacion);
        pnlMenuValorizacion.setLayout(pnlMenuValorizacionLayout);
        pnlMenuValorizacionLayout.setHorizontalGroup(
            pnlMenuValorizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMostrarAdelantos, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuValorizacionLayout.setVerticalGroup(
            pnlMenuValorizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuValorizacionLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnMostrarAdelantos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        popMenuValirizacion.setBackground(new java.awt.Color(65, 94, 255));
        popMenuValirizacion.setForeground(new java.awt.Color(65, 94, 255));
        popMenuValirizacion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        popMenu.setForeground(new java.awt.Color(255, 255, 255));
        popMenu.setColorBackgroud(new java.awt.Color(65, 94, 255));
        popMenu.setColorBorde(new java.awt.Color(255, 255, 255));
        popMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        popMenu.setGrosorBorde(1);

        btnValorizar.setBackground(new java.awt.Color(0, 0, 0));
        btnValorizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnValorizar.setForeground(new java.awt.Color(255, 255, 255));
        btnValorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Valorizacion.png"))); // NOI18N
        btnValorizar.setText("VALORIZAR");
        btnValorizar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnValorizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValorizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnValorizar.setIconTextGap(6);
        btnValorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValorizarActionPerformed(evt);
            }
        });
        popMenu.add(btnValorizar);

        btnModificar.setBackground(new java.awt.Color(0, 0, 0));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(6);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        popMenu.add(btnModificar);

        btnDesvalorizar.setBackground(new java.awt.Color(0, 0, 0));
        btnDesvalorizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDesvalorizar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesvalorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Desvalorizar.png"))); // NOI18N
        btnDesvalorizar.setText("DESVALORIZAR");
        btnDesvalorizar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnDesvalorizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesvalorizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDesvalorizar.setIconTextGap(6);
        btnDesvalorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesvalorizarActionPerformed(evt);
            }
        });
        popMenu.add(btnDesvalorizar);

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
        jLabel1.setText("GESTIONAR DATOS DE VALORIZACION ");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFechaCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtFechaCliente.setAccent(new java.awt.Color(204, 0, 51));
        txtFechaCliente.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtFechaCliente.setLabel("FECHA");
        txtFechaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaClienteActionPerformed(evt);
            }
        });
        jPanel5.add(txtFechaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 150, 60));

        txtCodigoCliente1.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoCliente1.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigoCliente1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigoCliente1.setLabel("COD CLIENTE");
        txtCodigoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCliente1ActionPerformed(evt);
            }
        });
        jPanel5.add(txtCodigoCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 60));

        txtClienteNombre.setForeground(new java.awt.Color(0, 0, 204));
        txtClienteNombre.setAccent(new java.awt.Color(204, 0, 51));
        txtClienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtClienteNombre.setLabel("CLIENTE");
        txtClienteNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNombreActionPerformed(evt);
            }
        });
        jPanel5.add(txtClienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 190, 60));

        txtDni.setForeground(new java.awt.Color(0, 0, 204));
        txtDni.setAccent(new java.awt.Color(204, 0, 51));
        txtDni.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDni.setLabel("DNI");
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel5.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 60, 60));

        txtTipoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtTipoCliente.setAccent(new java.awt.Color(204, 0, 51));
        txtTipoCliente.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTipoCliente.setLabel("TIPO CLIENTE");
        txtTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoClienteActionPerformed(evt);
            }
        });
        jPanel5.add(txtTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 260, 60));

        txtCambio.setForeground(new java.awt.Color(0, 0, 204));
        txtCambio.setAccent(new java.awt.Color(204, 0, 51));
        txtCambio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCambio.setLabel("CAMBIO");
        txtCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCambioMouseClicked(evt);
            }
        });
        txtCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCambioActionPerformed(evt);
            }
        });
        jPanel5.add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 60));

        txtCodigoValorizacion.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoValorizacion.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigoValorizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigoValorizacion.setLabel("COD. VALOR");
        txtCodigoValorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoValorizacionActionPerformed(evt);
            }
        });
        jPanel5.add(txtCodigoValorizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 80, 60));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(0, 132, 235));
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel6.setBorder(dropShadowBorder2);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotalUs$.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalUs$.setText("0");
        txtTotalUs$.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalUs$.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTotalUs$.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalUs$.setLabel("TOTAL US$");
        txtTotalUs$.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalUs$KeyPressed(evt);
            }
        });
        jPanel6.add(txtTotalUs$, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 60));

        txtTmh.setForeground(new java.awt.Color(0, 0, 204));
        txtTmh.setText("0");
        txtTmh.setAccent(new java.awt.Color(204, 0, 51));
        txtTmh.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTmh.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTmh.setLabel("T.M.H");
        txtTmh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTmhKeyPressed(evt);
            }
        });
        jPanel6.add(txtTmh, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 140, 60));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        jPanel8.setBorder(dropShadowBorder3);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCostoTransNascaDolar.setForeground(new java.awt.Color(0, 0, 204));
        txtCostoTransNascaDolar.setText("0");
        txtCostoTransNascaDolar.setAccent(new java.awt.Color(204, 0, 51));
        txtCostoTransNascaDolar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCostoTransNascaDolar.setLabel("TRANS. A NASCA $.");
        txtCostoTransNascaDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoTransNascaDolarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoTransNascaDolarKeyTyped(evt);
            }
        });
        jPanel8.add(txtCostoTransNascaDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 150, 60));

        txtToneladas.setForeground(new java.awt.Color(255, 0, 51));
        txtToneladas.setText("31.02");
        txtToneladas.setAccent(new java.awt.Color(204, 0, 51));
        txtToneladas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtToneladas.setLabel("TONELADAS");
        txtToneladas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtToneladasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtToneladasKeyTyped(evt);
            }
        });
        jPanel8.add(txtToneladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 80, 60));

        txtOtrosGastos.setForeground(new java.awt.Color(0, 0, 204));
        txtOtrosGastos.setText("0");
        txtOtrosGastos.setAccent(new java.awt.Color(204, 0, 51));
        txtOtrosGastos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtOtrosGastos.setLabel("OTROS  GASTOS $");
        txtOtrosGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOtrosGastosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtrosGastosKeyTyped(evt);
            }
        });
        jPanel8.add(txtOtrosGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 170, 60));

        txtCostoTransTrujiDolar.setForeground(new java.awt.Color(0, 0, 204));
        txtCostoTransTrujiDolar.setText("0");
        txtCostoTransTrujiDolar.setAccent(new java.awt.Color(204, 0, 51));
        txtCostoTransTrujiDolar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCostoTransTrujiDolar.setLabel("TRANSP. A TRUJILLO $.");
        txtCostoTransTrujiDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoTransTrujiDolarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoTransTrujiDolarKeyTyped(evt);
            }
        });
        jPanel8.add(txtCostoTransTrujiDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 150, 60));

        txtCostoTransNascaSoles.setForeground(new java.awt.Color(255, 0, 51));
        txtCostoTransNascaSoles.setText("0");
        txtCostoTransNascaSoles.setAccent(new java.awt.Color(204, 0, 51));
        txtCostoTransNascaSoles.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCostoTransNascaSoles.setLabel("TRANS. A NASCA S/.");
        txtCostoTransNascaSoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoTransNascaSolesKeyPressed(evt);
            }
        });
        jPanel8.add(txtCostoTransNascaSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 170, 60));

        txtCostoTransTrujilloSoles.setForeground(new java.awt.Color(255, 0, 0));
        txtCostoTransTrujilloSoles.setText("0");
        txtCostoTransTrujilloSoles.setAccent(new java.awt.Color(204, 0, 51));
        txtCostoTransTrujilloSoles.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCostoTransTrujilloSoles.setLabel("TRANSP. A TRUJILLO S/.");
        txtCostoTransTrujilloSoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoTransTrujilloSolesKeyPressed(evt);
            }
        });
        jPanel8.add(txtCostoTransTrujilloSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 170, 60));

        txtNumeroLote.setForeground(new java.awt.Color(0, 0, 204));
        txtNumeroLote.setText("0");
        txtNumeroLote.setAccent(new java.awt.Color(204, 0, 51));
        txtNumeroLote.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtNumeroLote.setLabel("# LOTES");
        txtNumeroLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroLoteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroLoteKeyTyped(evt);
            }
        });
        jPanel8.add(txtNumeroLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 90, 60));

        txtTarifaPorcentaje.setForeground(new java.awt.Color(255, 0, 51));
        txtTarifaPorcentaje.setText("0");
        txtTarifaPorcentaje.setAccent(new java.awt.Color(204, 0, 51));
        txtTarifaPorcentaje.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTarifaPorcentaje.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTarifaPorcentaje.setLabel("TARIFA PORCENTAJE ");
        txtTarifaPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarifaPorcentajeKeyPressed(evt);
            }
        });
        jPanel8.add(txtTarifaPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 130, 60));

        txtTotalPorcentaje.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalPorcentaje.setText("0");
        txtTotalPorcentaje.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalPorcentaje.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTotalPorcentaje.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalPorcentaje.setLabel("PORCENTAJE $");
        txtTotalPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalPorcentajeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalPorcentajeKeyTyped(evt);
            }
        });
        jPanel8.add(txtTotalPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 190, 60));

        txtTarifaAnalisis.setForeground(new java.awt.Color(255, 0, 0));
        txtTarifaAnalisis.setText("0");
        txtTarifaAnalisis.setAccent(new java.awt.Color(204, 0, 51));
        txtTarifaAnalisis.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTarifaAnalisis.setLabel("TARIFA ANALISIS");
        txtTarifaAnalisis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarifaAnalisisKeyPressed(evt);
            }
        });
        jPanel8.add(txtTarifaAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 110, 60));

        txtPolicia.setForeground(new java.awt.Color(255, 0, 51));
        txtPolicia.setText("1300");
        txtPolicia.setAccent(new java.awt.Color(204, 0, 51));
        txtPolicia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPolicia.setLabel("POLICIA");
        txtPolicia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPoliciaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPoliciaKeyTyped(evt);
            }
        });
        jPanel8.add(txtPolicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 60, 60));

        txtTotalAnalisis.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalAnalisis.setText("0");
        txtTotalAnalisis.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalAnalisis.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalAnalisis.setLabel("TOTAL ANALISIS");
        txtTotalAnalisis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalAnalisisKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalAnalisisKeyTyped(evt);
            }
        });
        jPanel8.add(txtTotalAnalisis, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 110, 60));

        txtAdelanto.setForeground(new java.awt.Color(0, 0, 204));
        txtAdelanto.setText("0");
        txtAdelanto.setAccent(new java.awt.Color(204, 0, 51));
        txtAdelanto.setCaretColor(new java.awt.Color(0, 0, 204));
        txtAdelanto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtAdelanto.setLabel("ADELANTO $");
        txtAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyTyped(evt);
            }
        });
        jPanel8.add(txtAdelanto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 330, 60));

        txtSalgoPagar.setForeground(new java.awt.Color(0, 0, 204));
        txtSalgoPagar.setText("0.0");
        txtSalgoPagar.setAccent(new java.awt.Color(204, 0, 51));
        txtSalgoPagar.setCaretColor(new java.awt.Color(0, 0, 204));
        txtSalgoPagar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtSalgoPagar.setLabel("SALDO A PAGAR $.");
        txtSalgoPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSalgoPagarMousePressed(evt);
            }
        });
        txtSalgoPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalgoPagarActionPerformed(evt);
            }
        });
        txtSalgoPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSalgoPagarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalgoPagarKeyTyped(evt);
            }
        });
        jPanel8.add(txtSalgoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 150, 60));

        txtTotalGastos.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalGastos.setText("0");
        txtTotalGastos.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalGastos.setCaretColor(new java.awt.Color(0, 0, 204));
        txtTotalGastos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalGastos.setLabel("TOTAL GASTOS  $ ");
        txtTotalGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGastosActionPerformed(evt);
            }
        });
        txtTotalGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalGastosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalGastosKeyTyped(evt);
            }
        });
        jPanel8.add(txtTotalGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 330, 60));

        txt_TotalPagarSoles.setForeground(new java.awt.Color(0, 0, 204));
        txt_TotalPagarSoles.setText("0");
        txt_TotalPagarSoles.setAccent(new java.awt.Color(204, 0, 51));
        txt_TotalPagarSoles.setCaretColor(new java.awt.Color(0, 0, 204));
        txt_TotalPagarSoles.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_TotalPagarSoles.setLabel("SALDO A PAGAR S/");
        jPanel8.add(txt_TotalPagarSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 170, 60));

        lbl_son.setForeground(new java.awt.Color(0, 0, 204));
        lbl_son.setText("SON:");
        jPanel8.add(lbl_son, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 330, 20));

        rbPagado.setBorder(null);
        buttonGroup1.add(rbPagado);
        rbPagado.setText("Pagado ");
        jPanel8.add(rbPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 90, -1));

        rbNoPagado.setBorder(null);
        buttonGroup1.add(rbNoPagado);
        rbNoPagado.setSelected(true);
        rbNoPagado.setText("No Pagado ");
        jPanel8.add(rbNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, -1, -1));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel8.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 40, 30));

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
        jPanel8.add(previewCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 80, 30));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setShadowColor(new java.awt.Color(0, 132, 235));
        dropShadowBorder4.setShowLeftShadow(true);
        dropShadowBorder4.setShowTopShadow(true);
        jPanel9.setBorder(dropShadowBorder4);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel9.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 149, 32));

        btnCancelar1.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Limpiar.png"))); // NOI18N
        btnCancelar1.setText("LIMPIAR");
        btnCancelar1.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar1.setIconTextGap(0);
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel9.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 149, 32));

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnAgregar.setText("GUARDAR");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.setIconTextGap(0);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel9.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 149, 32));

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
        jPanel9.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 149, 32));

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
        jPanel9.add(btnCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 30));

        btnImprimir1.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnImprimir1.setText("ADELANTOS");
        btnImprimir1.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir1.setIconTextGap(0);
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 149, 32));

        btnImprimir2.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnImprimir2.setText("LIQUIDACIÓN");
        btnImprimir2.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir2.setIconTextGap(0);
        btnImprimir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir2ActionPerformed(evt);
            }
        });
        jPanel9.add(btnImprimir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 149, 32));

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
        tableValorizacion.setComponentPopupMenu(popMenuValirizacion);
        tableValorizacion.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableValorizacion.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableValorizacion.setGrosorBordeFilas(0);
        tableValorizacion.setGrosorBordeHead(0);
        tableValorizacion.setRowHeight(20);
        tableValorizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableValorizacionMousePressed(evt);
            }
        });
        tableValorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableValorizacionKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tableValorizacion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setShowLeftShadow(true);
        dropShadowBorder5.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder5);

        tableLiquidacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableLiquidacion.setAltoHead(30);
        tableLiquidacion.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableLiquidacion.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableLiquidacion.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableLiquidacion.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableLiquidacion.setComponentPopupMenu(popMenu);
        tableLiquidacion.setGrosorBordeFilas(0);
        tableLiquidacion.setGrosorBordeHead(0);
        tableLiquidacion.setRowHeight(20);
        tableLiquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLiquidacionMousePressed(evt);
            }
        });
        tableLiquidacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableLiquidacion);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    int filaLiquidacion;
    private void tableLiquidacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLiquidacionMousePressed
        liquidacionSeleccionado = null;
        //  limpiarCamposGastos();
        // mostrarCambio();
        filaLiquidacion = tableLiquidacion.getSelectedRow();
        obtenerLiquidacionSeleccionado();
        txtCodigoValorizacion.setText("" + (gestionarValorizacionServicio.obtenerUltimoCodigo() + 1));
        tipo_accion = ACCION_CREAR;
        //inabilitarBotones(true);


    }//GEN-LAST:event_tableLiquidacionMousePressed


    private void tableLiquidacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyReleased
        filaLiquidacion = tableLiquidacion.getSelectedRow();
    }//GEN-LAST:event_tableLiquidacionKeyReleased

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    public static void mostrarCambio() {
        try {

            Cambio cambio = gestionarValorizacionServicio.obtenerCambio();
            txtCambio.setText("" + cambio.getDolar());
            txtTarifaAnalisis.setText("" + cambio.getTarifaa());
            txtTarifaPorcentaje.setText("" + cambio.getTarifa());
            txtCostoTransTrujilloSoles.setText("" + cambio.getTrans1());
            txtCostoTransNascaSoles.setText("" + cambio.getTrans2());
            txtPolicia.setText("" + cambio.getPoli());
            txtToneladas.setText("" + cambio.getTonelada());
            txtTotalUs$.selectAll();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void obtenerTipoCambioPorCliente() {
        try {
            String tipoCambio = gestionarValorizacionServicio.obtenerTipoCambioPorCliente(liquidacionSeleccionado.getClienteEntrante().getCodigo());
            if (tipoCambio.equals("0.00")) {
                gestionarValorizacionServicio.agregarTipoCambioPorcentajePorClienteEntrante(liquidacionSeleccionado.getClienteEntrante().getCodigo(), txtTarifaPorcentaje.getText().toString());
            } else {
                txtTarifaPorcentaje.setText(tipoCambio);
            }
        } catch (Exception e) {
            Mensaje.mostrarAdvertencia(null, e.getMessage());
        }

    }

    private void valorizarLiquidacion() {
    }


    private void txtCostoTransNascaDolarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransNascaDolarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            txtNumeroLote.requestFocus();
            txtNumeroLote.selectAll();

        }
    }//GEN-LAST:event_txtCostoTransNascaDolarKeyPressed

    private void txtCostoTransNascaDolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransNascaDolarKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtCostoTransNascaDolar.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCostoTransNascaDolarKeyTyped

    private void txtToneladasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToneladasKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio fgc = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                fgc.txtToneladasPorCamion.requestFocus();
                fgc.setVisible(true);
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtToneladasKeyPressed

    private void txtToneladasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToneladasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToneladasKeyTyped

    private void txtOtrosGastosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosGastosKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            txtTotalGastos.requestFocus();
            txtTotalGastos.selectAll();
        }
    }//GEN-LAST:event_txtOtrosGastosKeyPressed

    private void txtOtrosGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosGastosKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtOtrosGastos.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtOtrosGastosKeyTyped

    private void txtCostoTransTrujiDolarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransTrujiDolarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtCostoTransNascaDolar.requestFocus();
            txtCostoTransNascaDolar.selectAll();
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
        }
    }//GEN-LAST:event_txtCostoTransTrujiDolarKeyPressed

    private void txtCostoTransTrujiDolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransTrujiDolarKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtCostoTransTrujiDolar.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCostoTransTrujiDolarKeyTyped

    private void txtCostoTransNascaSolesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransNascaSolesKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio fgc = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                fgc.txtTransporteNasca.requestFocus();
                fgc.setVisible(true);
                calculatransporte();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }//GEN-LAST:event_txtCostoTransNascaSolesKeyPressed

    private void txtCostoTransTrujilloSolesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTransTrujilloSolesKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio fgc = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                fgc.txtTransporteTrujillo.requestFocus();
                fgc.setVisible(true);
                calculatransporte();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }//GEN-LAST:event_txtCostoTransTrujilloSolesKeyPressed

    private void txtNumeroLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroLoteKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            txtTotalAnalisis.requestFocus();
            txtTotalAnalisis.selectAll();

        }
    }//GEN-LAST:event_txtNumeroLoteKeyPressed

    private void txtNumeroLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroLoteKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtNumeroLote.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumeroLoteKeyTyped

    private void txtTarifaAnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarifaAnalisisKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio fgc = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                fgc.txtTarifaAnalisis.requestFocus();
                fgc.setVisible(true);

                calculaotros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }//GEN-LAST:event_txtTarifaAnalisisKeyPressed

    private void txtPoliciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoliciaKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio clie = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                clie.txtPoli.requestFocus();
                clie.setVisible(true);

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtPoliciaKeyPressed

    private void txtPoliciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoliciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPoliciaKeyTyped

    private void txtTotalAnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAnalisisKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTotalPorcentaje.requestFocus();
            txtTotalPorcentaje.selectAll();
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            calculaporcentaje();
        }
    }//GEN-LAST:event_txtTotalAnalisisKeyPressed

    private void txtTotalAnalisisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAnalisisKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTotalAnalisis.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalAnalisisKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCamposGastos();
        inabilitarCamposGastos(false);
        btnImprimir.setEnabled(false);
        inabilitarBotones(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        mostrarCambio();
        limpiarCamposGastos();
        inabilitarCamposGastos(false);
        inabilitarBotones(false);
        lbl_son.setText("SON: ");

    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarValorizacionReporteServicio.mostrarValorizacion(liquidacionSeleccionado, valorizacionSeleccionado, txtTotalUs$, txtTmh, txtCambio, txt_TotalPagarSoles, txtCostoTransTrujilloSoles, txtCostoTransTrujiDolar, txtCostoTransNascaSoles, txtCostoTransNascaDolar, txtTotalAnalisis, txtTotalPorcentaje, txtAdelanto, txtOtrosGastos, txtTotalGastos, txtSalgoPagar);
        } catch (Exception e) {
            Mensaje.mostrarAdvertencia(this, e.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    private void tableValorizacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableValorizacionMousePressed
        filaValoracion = tableValorizacion.getSelectedRow();
        obtenerValorizacionSeleccionado();
        tipo_accion = ACCION_MODIFICAR;
        llenarValorizacionTextos(valorizacionSeleccionado);
        inabilitarCamposGastos(true);
        btnAgregar.setEnabled(true);
        btnImprimir.setEnabled(true);
        seleccionarLiquidacionValorizacion();
        txtTotalUs$.setEnabled(false);
        txtTmh.setEnabled(false);
        String lectura = NumeroToLetras.NumeroALetras(txtSalgoPagar.getText().toString());
        lbl_son.setText("SON : " + lectura + " DOLARES ");
    }//GEN-LAST:event_tableValorizacionMousePressed

    private void seleccionarLiquidacionValorizacion() {
        tableLiquidacion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ListSelectionModel a = new DefaultListSelectionModel();
        int filaValorizacion = tableValorizacion.getSelectedRow();
        String codigoValorizacion = tableValorizacion.getValueAt(filaValorizacion, 0).toString();
        for (int i = 0; i < tableLiquidacion.getRowCount(); i++) {
            String codigoLiquidacion = tableLiquidacion.getValueAt(i, 18).toString();
            if (codigoLiquidacion.equals(codigoValorizacion)) {
                a.addSelectionInterval(i, i);
            }

        }
        tableLiquidacion.setSelectionModel(a);
        //tableLiquidacion.changeSelection(1, 1, false, false);
    }
    private void txtTotalGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGastosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGastosActionPerformed

    private void txtFechaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaClienteActionPerformed

    private void txtCodigoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCliente1ActionPerformed

    private void txtClienteNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNombreActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoClienteActionPerformed

    private void txtCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCambioActionPerformed
        try {
            FormGestionarCambio clie = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
            clie.txtTarifaPorcentaje.requestFocus();
            clie.setVisible(true);

            calculaporcentaje();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCambioActionPerformed

    private void txtTotalUs$KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalUs$KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            calculatransporte();
            calculaporcentaje();
            txtTmh.requestFocus();
            txtTmh.selectAll();

        }
    }//GEN-LAST:event_txtTotalUs$KeyPressed

    private void txtTmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            txtCostoTransTrujiDolar.requestFocus();
            txtCostoTransTrujiDolar.selectAll();
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            calculatransporte();
            calculaporcentaje();

        }
    }//GEN-LAST:event_txtTmhKeyPressed

    private void txtTarifaPorcentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarifaPorcentajeKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                FormGestionarCambio clie = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
                clie.txtTarifaPorcentaje.requestFocus();
                clie.setVisible(true);
                actualizar_porcentaje_tipo_cambio_cliente();

                calculaporcentaje();

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_txtTarifaPorcentajeKeyPressed

    private void txtTotalPorcentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPorcentajeKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            txtAdelanto.requestFocus();
            txtAdelanto.selectAll();
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
        }
    }//GEN-LAST:event_txtTotalPorcentajeKeyPressed

    private void txtAdelantoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            FormGestionarAdelantoPorCliente cam = new FormGestionarAdelantoPorCliente(null, true, FormGestionarAdelantosPorveedorCliente.TIPO_VALORIZACION, clienteEntranteSeleccionado);
            cam.setVisible(true);
            if (txtAdelanto.getText().equals("")) {
                txtAdelanto.setText("0");
            }
            txtOtrosGastos.selectAll();
            txtOtrosGastos.requestFocus();
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
        }
    }//GEN-LAST:event_txtAdelantoKeyPressed

    private void txtSalgoPagarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalgoPagarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            double total$ = Double.parseDouble(txtTotalUs$.getText());
            double totalg = Double.parseDouble(txtTotalGastos.getText());
            double saldopagar = total$ - totalg;
            txtSalgoPagar.setText(RedondearDecimales.redondearDecimales(saldopagar, 2) + "");
            txtSalgoPagar.requestFocus();
            txtSalgoPagar.selectAll();
            guardarDatos();
        }

    }//GEN-LAST:event_txtSalgoPagarKeyPressed

    private void txtCodigoValorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoValorizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoValorizacionActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMostrarAdelantosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarAdelantosActionPerformed
        FormGestionarVerDetalleAdelantoPorCliente formGestionarVerDetalleAdelantoPorCliente = new FormGestionarVerDetalleAdelantoPorCliente(null, true, valorizacionSeleccionado);
        formGestionarVerDetalleAdelantoPorCliente.setVisible(true);
    }//GEN-LAST:event_btnMostrarAdelantosActionPerformed

    private void previewChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previewChequeActionPerformed

    private void txtTotalGastosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalGastosKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            calculaanalisis();
            calculaotros();
            calcularTotalGastos();
            txtSalgoPagar.requestFocus();
            txtSalgoPagar.selectAll();

            String lectura = NumeroToLetras.NumeroALetras(txtSalgoPagar.getText().toString());
            lbl_son.setText("SON : " + lectura + " DOLARES ");
        }
    }//GEN-LAST:event_txtTotalGastosKeyPressed

    private void txtTotalPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPorcentajeKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTotalPorcentaje.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalPorcentajeKeyTyped

    private void txtAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtAdelanto.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdelantoKeyTyped

    private void txtTotalGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalGastosKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTotalGastos.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalGastosKeyTyped

    private void txtSalgoPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalgoPagarKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtSalgoPagar.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSalgoPagarKeyTyped

    void valorizar() {

        mostrarCambio();
        obtenerTipoCambioPorCliente();
        String tmh, totalUs;
        if (tableLiquidacion.getValueAt(filaLiquidacion, 17).toString().equals("V")) {
            Mensaje.mostrarMensajeDefinido(this, "El lote ya esta valorizado");
        } else if (filaLiquidacion == -1) {
            Mensaje.mostrarMensajeDefinido(this, "No ha seleccionado ningun dato");
        } else {
            inabilitarCamposGastos(true);
            tmh = liquidacionSeleccionado.getTmh();
            totalUs = liquidacionSeleccionado.getTotalus();

            if ("0".equals(txtTmh.getText()) && "0".equals(txtTotalUs$.getText())) {
                txtTmh.setText(tmh);
                txtTotalUs$.setText(totalUs);
            } else {
                double tmh1 = Double.parseDouble(txtTmh.getText());
                double totalu1 = Double.parseDouble(txtTotalUs$.getText());
                double suma1 = Double.parseDouble(tmh) + (tmh1);
                double suma2 = Double.parseDouble(totalUs) + (totalu1);
                txtTmh.setText(RedondearDecimales.redondearDecimales(suma1, 3) + "");
                txtTotalUs$.setText(RedondearDecimales.redondearDecimales(suma2, 3) + "");

            }
            calculatransporte();
            calculaporcentaje();
            contarlotes();
            calculaanalisis();
            calculaotros();

        }
        calcularTotalGastos();

    }
    private void btnValorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValorizarActionPerformed
        try {
            actualizarToValorizado();
        } catch (Exception e) {
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnValorizarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {

            if (liquidacionSeleccionado != null) {
                FormGestionarLiquidacion fopDatosLiquidacion = new FormGestionarLiquidacion(null, true, FormGestionarLiquidacion.TIPO_VALORIZACION, liquidacionSeleccionado);
                fopDatosLiquidacion.setVisible(true);
                inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), liquidacionSeleccionado.getFecha());
                liquidacionSeleccionado = null;
            } else {
                Mensaje.mostrarErrorSistema(this);
            }
        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnDesvalorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesvalorizarActionPerformed
        int numerosAfectos = 0;
        try {
            liquidacionSeleccionado.setEstadoLiquidacion("N");
            liquidacionSeleccionado.setValorizacionId(0);
            numerosAfectos = gestionarValorizacionServicio.actualizarEstadoLiquidacion(liquidacionSeleccionado);
            if (numerosAfectos == 1) {
                //Mensaje.mostrarMensajeDefinido(this, "Se actualizo correctamente");
                DesktopNotify.showDesktopMessage("FiveCod Software", "Se actualizo correctamente", 7);
                inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                mostrarCambio();
            } else {
                Mensaje.mostrarMensajeDefinido(this, "No se actualizar la liquidacion");
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesvalorizarActionPerformed


    private void tableValorizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableValorizacionKeyPressed
        filaValoracion = tableValorizacion.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableValorizacionKeyPressed

    private void txtCambioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCambioMouseClicked
        try {
            FormGestionarCambio clie = new FormGestionarCambio(null, true, FormGestionarCambio.TIPO_VALORIZACION);
            clie.txtDolar.requestFocus();
            clie.setVisible(true);
            actualizar_porcentaje_tipo_cambio_cliente();

            calculaporcentaje();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCambioMouseClicked

    private void actualizar_porcentaje_tipo_cambio_cliente() throws Exception {
        String porcentajte = txtTarifaPorcentaje.getText().toString();
        gestionarValorizacionServicio.actualizarCambioPorcentajeTipoCambio(liquidacionSeleccionado.getClienteEntrante().getCodigo(), porcentajte);
    }

    private void txtSalgoPagarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSalgoPagarMousePressed

    }//GEN-LAST:event_txtSalgoPagarMousePressed

    private void txtSalgoPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalgoPagarActionPerformed

    }//GEN-LAST:event_txtSalgoPagarActionPerformed

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        ChequeCliente cheque = new ChequeCliente();
        cheque.setClienteEntrante(clienteEntranteSeleccionado);
        cheque.setEntregadoA(clienteEntranteSeleccionado.generarNombre());
        cheque.setConcepto("POR VALORIZACION ");
        cheque.setMoneda("$");
        cheque.setMonto(Double.parseDouble(valorizacionSeleccionado.getTotalPagar()));
        FormGestionarChequeClienteServicio a = new FormGestionarChequeClienteServicio(null, true);

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
    }//GEN-LAST:event_btnChequeActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        FormGestionarAdelantoPorCliente cam = new FormGestionarAdelantoPorCliente(null, true, FormGestionarAdelantosPorveedorCliente.TIPO_TRABAJADOR, clienteEntranteSeleccionado);
        cam.setVisible(true);
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnImprimir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir2ActionPerformed
        FormGestionarLiquidacion formGestionarLiquidacion = new FormGestionarLiquidacion(null, true, FormGestionarLiquidacion.TIPO_VALORIZACION);
        formGestionarLiquidacion.setVisible(true);
        inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), liquidacionSeleccionado.getFecha());
    }//GEN-LAST:event_btnImprimir2ActionPerformed

    public static Double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }

    void calcularTotalSoles() {

        double monto = Double.parseDouble(txtSalgoPagar.getText());
        double cambio = Double.parseDouble(txtCambio.getText());
        double totalsoles = monto * cambio;
        txt_TotalPagarSoles.setText("" + redondearDecimales(totalsoles, 2));
    }

    void enviaraadelantos() {

//        form_adelantos.rbtcliente.setSelected(true);
//        form_adelantos.rbtproveedor.setSelected(false);
//        form_adelantos.jButton2.setEnabled(true);
//        form_adelantos.txtcod.setText(txtCodigoCliente.getText());
//        form_adelantos.lbldni.setText(lbldni.getText());
//        form_adelantos.txtbus.setText(txtClienteNombre.getText() + " " + lbldni.getText());
//        form_adelantos.cargar_cliente(lbldni.getText());
    }

    private void obtenerLiquidacionSeleccionado() {
        try {
            String codigo = tableLiquidacion.getValueAt(filaLiquidacion, 0).toString();
            liquidacionSeleccionado = gestionarValorizacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
            clienteEntranteSeleccionado = liquidacionSeleccionado.getClienteEntrante();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void obtenerValorizacionSeleccionado() {
        try {
            String codigo = tableValorizacion.getValueAt(filaValoracion, 0).toString();
            valorizacionSeleccionado = gestionarValorizacionServicio.buscarValorizacionPorCodigo(Integer.parseInt(codigo));
            clienteEntranteSeleccionado = valorizacionSeleccionado.getClienteEntrante();

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

        btnModificar.setEnabled(!v);
        btnValorizar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnCancelar1;
    private rojeru_san.RSButton btnCheque;
    private javax.swing.JMenuItem btnDesvalorizar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnImprimir1;
    private rojeru_san.RSButton btnImprimir2;
    private javax.swing.JMenuItem btnModificar;
    private rojeru_san.RSButton btnMostrarAdelantos;
    private javax.swing.JMenuItem btnValorizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_son;
    private javax.swing.JPanel pnlMenuValorizacion;
    private rojerusan.RSPopuMenu popMenu;
    private javax.swing.JPopupMenu popMenuValirizacion;
    private com.icm.components.metro.CheckBoxMetroICM previewCheque;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage19;
    private com.icm.components.metro.RadioButtonMetroICM rbNoPagado;
    private com.icm.components.metro.RadioButtonMetroICM rbPagado;
    private rojerusan.RSTableMetro tableLiquidacion;
    private rojerusan.RSTableMetro tableValorizacion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtAdelanto;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCambio;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtClienteNombre;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoCliente1;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigoValorizacion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCostoTransNascaDolar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCostoTransNascaSoles;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCostoTransTrujiDolar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCostoTransTrujilloSoles;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDni;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtFechaCliente;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtNumeroLote;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtOtrosGastos;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPolicia;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtSalgoPagar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTarifaAnalisis;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTarifaPorcentaje;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTipoCliente;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTmh;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtToneladas;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalAnalisis;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalGastos;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalPorcentaje;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalUs$;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_TotalPagarSoles;
    // End of variables declaration//GEN-END:variables

    public void calculatransporte() {
        double costo1 = Double.parseDouble(txtCostoTransTrujilloSoles.getText());
        double costo2 = Double.parseDouble(txtCostoTransNascaSoles.getText());
        double toneladas = Double.parseDouble(txtTmh.getText());
        double cambio = Double.parseDouble(txtCambio.getText());

        double costot1 = ((costo1 * toneladas) / cambio);
        double costot2 = ((costo2 * toneladas) / cambio);

//        DecimalFormat df = new DecimalFormat("#.##");
        txtCostoTransTrujiDolar.setText(RedondearDecimales.redondearDecimales(costot1, 2) + "");
        txtCostoTransNascaDolar.setText(RedondearDecimales.redondearDecimales(costot2, 2) + "");
    }

    public void calculaporcentaje() {

        double total$ = Double.parseDouble(txtTotalUs$.getText());
        double tarifa = Double.parseDouble(txtTarifaPorcentaje.getText());
        double porcentaje = (total$ * tarifa) / 100;
        txtTotalPorcentaje.setText(RedondearDecimales.redondearDecimales(porcentaje, 2) + "");
        calcularTotalSoles();

    }

    private void actualizarEstadoLiquidacion() {
        try {
            liquidacionSeleccionado.setEstadoLiquidacion("V");
            liquidacionSeleccionado.setValorizacionId(Integer.parseInt(txtCodigoValorizacion.getText()));
            int numerosAfectados = gestionarValorizacionServicio.actualizarEstadoLiquidacion(liquidacionSeleccionado);
            if (numerosAfectados == 1) {
                Mensaje.mostrarMensajeDefinido(this, "Se actulizo la liquidacion correctamente");
                inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Error al actualizar");

            }
        } catch (Exception e) {
        }
    }

    void contarlotes() {
        int afeter = Integer.parseInt(txtNumeroLote.getText());
        int before = afeter + 1;
        txtNumeroLote.setText(before + "");
    }

    public void calculaanalisis() {
        double numerol = Double.parseDouble(txtNumeroLote.getText());
        double tarifaa = Double.parseDouble(txtTarifaAnalisis.getText());
        double total = tarifaa * numerol;
        txtTotalAnalisis.setText(RedondearDecimales.redondearDecimales(total, 2) + "");
    }

    public void calculaotros() {
        double policia = Double.parseDouble(txtPolicia.getText());
        double toneladasc = Double.parseDouble(txtToneladas.getText());
        double suma = policia / toneladasc;
        double tmh = Double.parseDouble(txtTmh.getText());
        double acumulativo = suma * tmh;
        double dolar = Double.parseDouble(txtCambio.getText());
        double total = acumulativo / dolar;
        txtOtrosGastos.setText(RedondearDecimales.redondearDecimales(total, 2) + "");

    }

    public void calcularTotalGastos() {
        double trans1 = Double.parseDouble(txtCostoTransTrujiDolar.getText());
        double trans2 = Double.parseDouble(txtCostoTransNascaDolar.getText());
        double analisis = Double.parseDouble(txtTotalAnalisis.getText());
        double porcentaje = Double.parseDouble(txtTotalPorcentaje.getText());
        double adelanto = Double.parseDouble(txtAdelanto.getText());
        double otrosg = Double.parseDouble(txtOtrosGastos.getText());
        double totalgastos = trans1 + trans2 + analisis + porcentaje + adelanto + otrosg;
        txtTotalGastos.setText(RedondearDecimales.redondearDecimales(totalgastos, 2) + "");
        double total$ = Double.parseDouble(txtTotalUs$.getText());
        double totalg = Double.parseDouble(txtTotalGastos.getText());
        double saldopagar = total$ - totalg;
        txtSalgoPagar.setText(RedondearDecimales.redondearDecimales(saldopagar, 2) + "");
        calcularTotalSoles();
    }

    private void cancelar() {
        try {
            liquidacionSeleccionado.setEstadoLiquidacion("V");
            liquidacionSeleccionado.setValorizacionId(Integer.parseInt(txtCodigoValorizacion.getText()));
            int numerosAfectados = gestionarValorizacionServicio.actualizarEstadoLiquidacion(liquidacionSeleccionado);
            if (numerosAfectados == 1) {
                Mensaje.mostrarMensajeDefinido(this, "Se actulizo la liquidacion correctamente");
                inicializarTablaLiquidacion(liquidacionSeleccionado.getClienteEntrante().getCodigo(), fechaSeleccionada);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Error al actualizar");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void nosequeponer() {
        int filasele = tableLiquidacion.getSelectedRow();
        txtCodigoCliente1.setText(tableLiquidacion.getValueAt(filasele, 0).toString());
        Double totalus = Double.parseDouble(txtTotalUs$.getText());
        Double tmh = Double.parseDouble(txtTmh.getText());
        Double trans1 = Double.parseDouble(txtCostoTransTrujiDolar.getText());
        Double trans2 = Double.parseDouble(txtCostoTransNascaDolar.getText());
        Double analisis = Double.parseDouble(txtTotalAnalisis.getText());
        Double porcentaje = Double.parseDouble(txtTotalPorcentaje.getText());
        Double adelanto = Double.parseDouble(txtAdelanto.getText());
        Double otrosg = Double.parseDouble(txtOtrosGastos.getText());
        Double totalg = Double.parseDouble(txtTotalGastos.getText());
        Double total = Double.parseDouble(txtSalgoPagar.getText());
        if (filasele == -1) {
            JOptionPane.showMessageDialog(null, "No Seleciono ninguna fila");
        } else {
            if (tableLiquidacion.getValueAt(filasele, 17).toString().equals("V")) {

                if (txtNumeroLote.getText().equals("1")) {
                    txtOtrosGastos.setText("0");
                    txtTmh.setText("0");
                    txtTotalAnalisis.setText("0");
                    txtTotalUs$.setText("0");
                    txtTotalPorcentaje.setText("0");
                    txtAdelanto.setText("0");
                    txtOtrosGastos.setText("0");
                    txtTotalGastos.setText("0");
                    txtSalgoPagar.setText("0");
                    txtCostoTransTrujiDolar.setText("0");
                    txtCostoTransNascaDolar.setText("0");
                    txtNumeroLote.setText("0");
                } else if (Integer.parseInt(txtNumeroLote.getText()) > 1) {

                    Double totalust = Double.parseDouble(tableLiquidacion.getValueAt(filasele, 16).toString());
                    Double restatotalus = totalus - totalust;
                    txtTotalUs$.setText(RedondearDecimales.redondearDecimales(restatotalus, 1) + "");

                    Double tmht = Double.parseDouble(tableLiquidacion.getValueAt(filasele, 4).toString());
                    Double restatmh = RedondearDecimales.redondearDecimales(tmh, 2) - RedondearDecimales.redondearDecimales(tmht, 1);
                    txtTmh.setText(RedondearDecimales.redondearDecimales(restatmh, 2) + "");

                    double costo1 = Double.parseDouble(txtCostoTransTrujilloSoles.getText());
                    double costo2 = Double.parseDouble(txtCostoTransNascaSoles.getText());
                    double toneladas = Double.parseDouble(tableLiquidacion.getValueAt(filasele, 4).toString());
                    double cambio = Double.parseDouble(txtCambio.getText());

                    double costot1 = ((costo1 * toneladas) / cambio);
                    double costot2 = ((costo2 * toneladas) / cambio);

                    double calculoresta1 = Double.parseDouble(txtCostoTransTrujiDolar.getText()) - costot1;
                    double calculoresta2 = Double.parseDouble(txtCostoTransNascaDolar.getText()) - costot2;

                    //        DecimalFormat df = new DecimalFormat("#.##");
                    txtCostoTransTrujiDolar.setText(RedondearDecimales.redondearDecimales(calculoresta1, 2) + "");
                    txtCostoTransNascaDolar.setText(RedondearDecimales.redondearDecimales(calculoresta2, 2) + "");

                    int afeter = Integer.parseInt(txtNumeroLote.getText());
                    int before = afeter - 1;
                    txtNumeroLote.setText(before + "");
                    double total$ = Double.parseDouble(tableLiquidacion.getValueAt(filasele, 17).toString());
                    double tarifa = Double.parseDouble(txtTarifaPorcentaje.getText());
                    double porcentajet = (total$ * tarifa) / 100;
                    double restaporcentaje = Double.parseDouble(txtTotalPorcentaje.getText()) - porcentajet;
                    txtTotalPorcentaje.setText(RedondearDecimales.redondearDecimales(restaporcentaje, 2) + "");

                    double analisisa = Double.parseDouble(txtNumeroLote.getText()) * Double.parseDouble(txtTarifaAnalisis.getText());
                    //       double restaanalisis = Double.parseDouble(txtanalisis.getText())-analisisa;
                    txtTotalAnalisis.setText(RedondearDecimales.redondearDecimales(analisisa, 2) + "");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Lote NO Valorizado");
            }
            cancelar();
//            calcular1();
        }
    }

    private void llenarCamposCliente(Liquidacion liquidacion) {

        txtDni.setText(liquidacion.getClienteEntrante().getDni());
        txtFechaCliente.setText("" + liquidacion.getFecha());
        txtTipoCliente.setText("" + liquidacion.getClienteEntrante().getTipoCliente().getDescripcion());
        txtCodigoCliente1.setText("" + liquidacion.getClienteEntrante().getCodigo());
        txtClienteNombre.setText("" + liquidacion.getClienteEntrante().generarNombre());
        inabilitarCamposCLiente(false);
    }

    void inabilitarCamposCLiente(boolean estado) {
        txtClienteNombre.setEnabled(estado);
        txtCodigoCliente1.setEnabled(estado);
        txtDni.setEnabled(estado);
        txtTipoCliente.setEnabled(estado);
        txtCambio.setEnabled(true);
        txtFechaCliente.setEnabled(estado);
    }

    private void limpiarCamposGastos() {
        txtOtrosGastos.setText("0.0");
        txtTmh.setText("0.0");
        txtTotalAnalisis.setText("0.0");
        txtTotalUs$.setText("0.0");
        txtTotalPorcentaje.setText("0.0");
        txtAdelanto.setText("0.0");
        txtOtrosGastos.setText("0.0");
        txtTotalGastos.setText("0.0");
        txtSalgoPagar.setText("0.0");
        txtCostoTransTrujiDolar.setText("0.0");
        txtCostoTransNascaDolar.setText("0.0");
        txtNumeroLote.setText("0");
        txtCodigoValorizacion.setText("");
        txt_TotalPagarSoles.setText("0.0");

    }

    private void inabilitarCamposGastos(boolean estado) {
        txtOtrosGastos.setEnabled(estado);
        txtTmh.setEnabled(estado);
        txtTotalAnalisis.setEnabled(estado);
        txtTotalUs$.setEnabled(estado);
        txtTotalPorcentaje.setEnabled(estado);
        txtAdelanto.setEnabled(estado);
        txtOtrosGastos.setEnabled(estado);
        txtTotalGastos.setEnabled(estado);
        txtSalgoPagar.setEnabled(estado);
        txtCostoTransTrujiDolar.setEnabled(estado);
        txtCostoTransNascaDolar.setEnabled(estado);
        txtNumeroLote.setEnabled(estado);
        txtCodigoValorizacion.setEnabled(estado);
        txtCostoTransNascaDolar.setEnabled(estado);
        txtCostoTransNascaSoles.setEnabled(estado);
        txtCostoTransTrujiDolar.setEnabled(estado);
        txtCostoTransTrujilloSoles.setEnabled(estado);
        txtPolicia.setEnabled(estado);
        txtToneladas.setEnabled(estado);
        txtTarifaAnalisis.setEnabled(estado);
        txtTarifaPorcentaje.setEnabled(estado);

    }

    private void guardarDatos() {
        Valorizacion valorizacion;
        if (verificarCamposVacios()) {
            try {
                valorizacion = new Valorizacion();
                valorizacion.setAdelantos(txtAdelanto.getText());
                valorizacion.setTotalAnalisis(txtTotalAnalisis.getText());
                valorizacion.setCambio(txtCambio.getText());

                valorizacion.setCostoTransporteNascaDolar(txtCostoTransNascaDolar.getText());
                valorizacion.setCostoTransporteNascaSoles(txtCostoTransNascaSoles.getText());
                valorizacion.setCostoTransporteTrujilloDolar(txtCostoTransTrujiDolar.getText());
                valorizacion.setCostoTransporteTrujilloSoles(txtCostoTransTrujilloSoles.getText());

                valorizacion.setOtrosGastos(txtOtrosGastos.getText());
                valorizacion.setTotalPorcentaje(txtTotalPorcentaje.getText());
                valorizacion.setTmh(txtTmh.getText());
                valorizacion.setTotalGastos(txtTotalGastos.getText());
                valorizacion.setTotalPagar(txtSalgoPagar.getText());
                valorizacion.setTotalPorcentaje(txtTotalPorcentaje.getText());
                valorizacion.setTotalUS(txtTotalUs$.getText());
                valorizacion.setcLotes(txtNumeroLote.getText());
                valorizacion.setTarifaAnalisis(txtTarifaAnalisis.getText());
                valorizacion.setTarifaPorcentaje(txtTarifaPorcentaje.getText());
                valorizacion.setPolicia(txtPolicia.getText());
                valorizacion.setToneladas(txtToneladas.getText());
                valorizacion.setDescuentoSoles(txt_TotalPagarSoles.getText());
                if (rbPagado.isSelected()) {
                    valorizacion.setValorizacionEstado("Pagado");
                } else {
                    valorizacion.setValorizacionEstado("No Pagado");
                }
                int registros_afectados = 0;

                if (tipo_accion == ACCION_CREAR) {
                    try {
                        valorizacion.setFechaId(liquidacionSeleccionado.getFecha());
                        valorizacion.setClienteEntrante(clienteEntranteSeleccionado);
                        registros_afectados = gestionarValorizacionServicio.guardarValorizacion(valorizacion);
                        if (registros_afectados == 1) {

//                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Personal", DesktopNotify.SUCCESS);
                            inicializarTablaValorizacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                            inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                            limpiarCamposGastos();
                            inabilitarCamposGastos(false);
                            btnAgregar.setEnabled(false);
                            btnImprimir.setEnabled(false);
                            mostrarCambio();

                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeCreacion(this);
                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeCreacion(this);
                    }
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {

                        valorizacion.setFechaId(valorizacionSeleccionado.getFechaId());
                        valorizacion.setClienteEntrante(valorizacionSeleccionado.getClienteEntrante());
                        valorizacion.setCodigo(valorizacionSeleccionado.getCodigo());
                        registros_afectados = gestionarValorizacionServicio.modificarValorizacion(valorizacion);
                        if (registros_afectados == 1) {
//                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de modificar la valorizaciòn", DesktopNotify.SUCCESS);

                            inicializarTablaValorizacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                            inabilitarCamposGastos(false);

                            btnAgregar.setEnabled(false);
                            btnImprimir.setEnabled(false);

                            limpiarCamposGastos();
                            mostrarCambio();
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un personal", DesktopNotify.INPUT_REQUEST);
                            liquidacionSeleccionado = null;
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

    private void actualizarToValorizado() throws Exception {
        liquidacionSeleccionado.setEstadoLiquidacion("V");
        liquidacionSeleccionado.setValorizacionId(Integer.parseInt(txtCodigoValorizacion.getText()));
        int resultado = gestionarValorizacionServicio.actualizarEstadoLiquidacion(liquidacionSeleccionado);
        if (resultado == 0) {
            DesktopNotify.showDesktopMessage("FiveCod Software", "Error al modificar la valorizacion", DesktopNotify.INPUT_REQUEST);
        } else if (resultado == 1) {

            btnAgregar.setEnabled(true);
            valorizar();
            txtTotalUs$.requestFocusInWindow();
            inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
        }
    }

    private void inabilitarBotones(boolean estado) {
        btnAgregar.setEnabled(estado);
    }

    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
//        contador = Verificador.verificadorCampos(txtClienteNombre, "dni");
        aux = aux + contador;
        return true;
    }

    private void llenarValorizacionTextos(Valorizacion valorizacion) {
        txtCodigoValorizacion.setText("" + valorizacion.getCodigo());
        txtAdelanto.setText("" + valorizacion.getAdelantos());
        txtCambio.setText("" + valorizacion.getCambio());
        txtCostoTransNascaDolar.setText("" + valorizacion.getCostoTransporteNascaDolar());
        txtCostoTransNascaSoles.setText("" + valorizacion.getCostoTransporteNascaSoles());
        txtCostoTransTrujiDolar.setText("" + valorizacion.getCostoTransporteTrujilloDolar());
        txtCostoTransTrujilloSoles.setText("" + valorizacion.getCostoTransporteTrujilloSoles());
        txt_TotalPagarSoles.setText("" + valorizacion.getDescuentoSoles());
        txtNumeroLote.setText("" + valorizacion.getcLotes());
        txtOtrosGastos.setText("" + valorizacion.getOtrosGastos());
        txtPolicia.setText("" + valorizacion.getPolicia());
        txtSalgoPagar.setText("" + valorizacion.getTotalPagar());
        txtTotalGastos.setText("" + valorizacion.getTotalGastos());
        txtTmh.setText("" + valorizacion.getTmh());
        txtToneladas.setText("" + valorizacion.getToneladas());
        txtTotalAnalisis.setText("" + valorizacion.getTotalAnalisis());
        txtTarifaPorcentaje.setText("" + valorizacion.getTarifaPorcentaje());
        txtTotalUs$.setText("" + valorizacion.getTotalUS());
        txtTarifaAnalisis.setText("" + valorizacion.getTarifaAnalisis());
        txtTarifaPorcentaje.setText("" + valorizacion.getTarifaPorcentaje());
        txtPolicia.setText("" + valorizacion.getPolicia());
        txtToneladas.setText("" + valorizacion.getToneladas());
        txtTotalPorcentaje.setText("" + valorizacion.getTotalPorcentaje());
        calcularTotalSoles();
        if (valorizacion.getValorizacionEstado().equals("Pagado")) {
            rbPagado.setSelected(true);
        } else {
            rbNoPagado.setSelected(true);

        }

    }

    private void metodoParaEliminar() {
        try {
            obtenerValorizacionSeleccionado();
            if (valorizacionSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    int registros_afectados = gestionarValorizacionServicio.eliminarValorizacion(valorizacionSeleccionado);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar una valorizaciòn", DesktopNotify.SUCCESS);

//                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        inicializarTablaValorizacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                        inabilitarCamposGastos(false);
                        limpiarCamposGastos();
                        mostrarCambio();
                        inicializarTablaLiquidacion(Integer.parseInt(txtCodigoCliente1.getText()), fechaSeleccionada);
                    } else {
                        Mensaje.mostrarAdvertenciaDeEliminacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarMensajeDefinido(this, e.getMessage());
                }
            } else {
                Mensaje.mostrarErrorSistema(this);
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

}
