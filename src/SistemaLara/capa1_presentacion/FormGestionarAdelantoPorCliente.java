
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
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarAdelantoServicio;
import SistemaLara.capa2_aplicacion.GestionarCambioServicio;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa3_dominio.Valorizacion;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author FiveCod Software
 */
public class FormGestionarAdelantoPorCliente extends javax.swing.JDialog {

    public static GestionarAdelantoServicio gestionarAdelantoServicio;
    public static GestionarCambioServicio gestionarCambioServicio;
    public static ClienteEntrante clienteEntranteSeleccionado;
    private GestionarAdelantoReporteServicio gestionarAdelantoReporteServicio;

    public static Adelanto adelantoSeleccionado;
    private int TIPO_USUARIO;
    public static int TIPO_TRABAJADOR = 1;
    public static int TIPO_VALORIZACION = 3;
    private JasperPrint print = null;
    private int tipo_accion;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;

    public FormGestionarAdelantoPorCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            popMenuEliminar.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            BarraDesplzamiento();
            this.setLocationRelativeTo(null);
            adelantoSeleccionado = new Adelanto();
            gestionarCambioServicio = new GestionarCambioServicio();
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            gestionarAdelantoReporteServicio = new GestionarAdelantoReporteServicio();
            inicializarTablaColumnas();
            inicializarTablaColumnasDescontar();
            inicializarCambio();
            inabilitarBotones(false);
            inabilitarCampos(false);
            btnDescontar.setEnabled(false);
            btnDescontarValorizacion.setEnabled(false);
            txtBuscarTipoTrabajador.setVisible(false);
            btn_mostrarTodos.setVisible(false);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public FormGestionarAdelantoPorCliente(java.awt.Frame parent, boolean modal, int tipo, ClienteEntrante clienteEntrante) {
        super(parent, modal);
        try {
            initComponents();
            popMenuEliminar.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            BarraDesplzamiento();
            this.TIPO_USUARIO = tipo;
            adelantoSeleccionado = new Adelanto();
            gestionarCambioServicio = new GestionarCambioServicio();
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            inicializarTablaColumnas();
            inicializarTablaColumnasDescontar();
            inicializarCambio();
            clienteEntranteSeleccionado = clienteEntrante;
            InicializarTabla();
            inabilitarBotones(false);
            inabilitarCampos(false);
            btnDescontar.setEnabled(true);
            btnDescontarValorizacion.setEnabled(true);
            if (clienteEntranteSeleccionado != null) {
                tipo_accion = ACCION_CREAR;

                limpiarTabla();
                limpiarCampos();
                inabilitarBotones(true);
                inabilitarCampos(true);
                llenarCamposClienteSeleccionado();
            }
            txtBuscarTipoTrabajador.setVisible(false);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public static void limpiarTabla() {
        ModeloTabla modelos = (ModeloTabla) tableAdelanto.getModel();
        modelos.eliminarTotalFilas();
    }

    public void InicializarTabla() {
        try {
            limpiarTabla();
            if (clienteEntranteSeleccionado != null) {
                gestionarAdelantoServicio.mostrarAdelantoCliente(clienteEntranteSeleccionado, tableAdelanto);
                gestionarAdelantoServicio.calcularTotalNoPagadoSolesCliente(lblTotalNoPagadoSoles, clienteEntranteSeleccionado);
                gestionarAdelantoServicio.calcularTotalNoPagadoDolaresCliente(lblNoPagadoDolares, clienteEntranteSeleccionado);
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
        txtTipoCliente.setText("");
        dateFecha.setDatoFecha(new Date());

    }

    private void inabilitarCampos(boolean estado) {

        txtDescripcion.setEnabled(estado);
        txtImporte.setEnabled(estado);
        dateFecha.setEnabled(estado);
        rdbCliente.setEnabled(estado);
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

        tabla.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableAdelanto.setModel(modeloTabla);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setWidth(0);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMinWidth(0);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMaxWidth(0);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(1)).setWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(1)).setMinWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(1)).setMaxWidth(100);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(2)).setWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(2)).setMinWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(2)).setMaxWidth(100);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setWidth(120);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setMinWidth(120);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setMaxWidth(120);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(6)).setWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(6)).setMinWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(6)).setMaxWidth(100);

    }

    private void inicializarTablaColumnasDescontar() {
        Tabla tablas = new Tabla();
        tablas.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tablas.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tablas.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tablas.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tablas.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        tablas.agregarColumna(new Columna("FECHA", "java.lang.String"));

        ModeloTabla modeloTablasd = new ModeloTabla(tablas);
        tableDescontar.setModel(modeloTablasd);
        tableDescontar.getColumn(tableDescontar.getColumnName(0)).setWidth(0);
        tableDescontar.getColumn(tableDescontar.getColumnName(0)).setMinWidth(0);
        tableDescontar.getColumn(tableDescontar.getColumnName(0)).setMaxWidth(0);

        tableDescontar.getColumn(tableDescontar.getColumnName(1)).setWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(1)).setMinWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(1)).setMaxWidth(100);

        tableDescontar.getColumn(tableDescontar.getColumnName(2)).setWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(2)).setMinWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(2)).setMaxWidth(100);

        tableDescontar.getColumn(tableDescontar.getColumnName(5)).setWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(5)).setMinWidth(100);
        tableDescontar.getColumn(tableDescontar.getColumnName(5)).setMaxWidth(100);

    }

    private void llenarCamposParaModificar(Adelanto adelanto) {

        if (adelanto != null) {
            lblCodigo.setText(String.valueOf(adelanto.getCodigo()));
            txtImporte.setText(String.valueOf(adelanto.getCantidad()));
            txtDescripcion.setText(adelanto.getDescripcion());
            dateFecha.setDatoFecha(adelanto.getFechaPago());

            clienteEntranteSeleccionado = adelanto.getClienteEntrante();
            txtDestinatario.setText(clienteEntranteSeleccionado.getNombres() + " " + clienteEntranteSeleccionado.getApellidos() + " :" + clienteEntranteSeleccionado.getDni());
            txtTipoCliente.setText(clienteEntranteSeleccionado.getTipoCliente().getDescripcion());

            if (adelanto.getAdelantoEstado().equals("NO PAGADO")) {
                rdbNoPagado.setSelected(true);
                rdbPagado.setSelected(false);
            } else if (adelanto.getAdelantoEstado().equals("PAGADO")) {
                rdbNoPagado.setSelected(false);
                rdbPagado.setSelected(true);
            }
            if (("S/").equals(adelanto.getMoneda())) {
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

                adelanto.setClienteEntrante(clienteEntranteSeleccionado);
                adelanto.setProveedorServicio(null);

                adelanto.setCantidad(Double.parseDouble(txtImporte.getText()));
                adelanto.setDescripcion(txtDescripcion.getText());
                if (rdbSol.isSelected() == true) {
                    adelanto.setMoneda("S/");
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
                        registros_afectados = gestionarAdelantoServicio.guardarAdelantoCliente(adelanto);

                        if (registros_afectados == 1) {
//                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear un Nuevo Adelanto de Cliente", DesktopNotify.SUCCESS);
                            InicializarTabla();
                            eliminarCamposSinEliminarCliente();
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

                        registros_afectados = gestionarAdelantoServicio.modificarAdelantoCliente(adelanto);
                        InicializarTabla();
                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar el  Adelanto con Numero de Lote " + txtDestinatario.getText(), DesktopNotify.SUCCESS);
//                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            InicializarTabla();
                            eliminarCamposSinEliminarCliente();
                            txtImporte.selectAll();
                            txtImporte.requestFocus();
                            tipo_accion = ACCION_CREAR;
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
//                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar la Liquidacion", DesktopNotify.INPUT_REQUEST);
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

        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        popMenu = new rojerusan.RSPopuMenu();
        btnDescontar = new javax.swing.JMenuItem();
        btnEliminar = new javax.swing.JMenuItem();
        popMenuEliminar = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnEliminarDescontar = new rojeru_san.RSButton();
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
        rSLabelImage22 = new rojerusan.RSLabelImage();
        txtDestinatario = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtImporte = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTipoCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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
        rdbCliente = new com.icm.components.metro.RadioButtonMetroICM();
        rdbNoPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rdbPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rdbSol = new com.icm.components.metro.RadioButtonMetroICM();
        rdbDolar = new com.icm.components.metro.RadioButtonMetroICM();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel7 = new javax.swing.JPanel();
        btnNuevo = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        btnImprimir1 = new rojeru_san.RSButton();
        btnVerTodo = new rojeru_san.RSButton();
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
        btnCheque = new rojeru_san.RSButton();
        previewCheque = new com.icm.components.metro.CheckBoxMetroICM();
        btn_limpiar = new rojeru_san.RSButton();
        lbl_son = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDescontar = new rojerusan.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        lblDescontar = new javax.swing.JLabel();
        btnDescontarValorizacion = new rojeru_san.RSButton();
        btn_mostrarTodos = new rojeru_san.RSButton();

        popMenu.setForeground(new java.awt.Color(255, 255, 255));
        popMenu.setColorBackgroud(new java.awt.Color(65, 94, 255));
        popMenu.setColorBorde(new java.awt.Color(255, 255, 255));
        popMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        popMenu.setGrosorBorde(1);

        btnDescontar.setBackground(new java.awt.Color(0, 0, 0));
        btnDescontar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDescontar.setForeground(new java.awt.Color(255, 255, 255));
        btnDescontar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descontar.png"))); // NOI18N
        btnDescontar.setText("DESCONTAR");
        btnDescontar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnDescontar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescontar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDescontar.setIconTextGap(6);
        btnDescontar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescontarActionPerformed(evt);
            }
        });
        popMenu.add(btnDescontar);

        btnEliminar.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(6);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        popMenu.add(btnEliminar);

        popMenuEliminar.setBackground(new java.awt.Color(65, 94, 255));
        popMenuEliminar.setForeground(new java.awt.Color(65, 94, 255));
        popMenuEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        btnEliminarDescontar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminarDescontar.setBorder(null);
        btnEliminarDescontar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminarDescontar.setText("ELIMINAR");
        btnEliminarDescontar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminarDescontar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminarDescontar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminarDescontar.setIconTextGap(2);
        btnEliminarDescontar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDescontarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminarDescontar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminarDescontar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        jLabel1.setText("GESTIONAR DATOS DE ADALANTOS DE CLIENTES ENTRANTES");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoClienteEntrante.png"))); // NOI18N

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 200, 10));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Destinatario.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 40, 40));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, 40));

        rSLabelImage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 40, 30));

        txtDestinatario.setForeground(new java.awt.Color(0, 0, 204));
        txtDestinatario.setAccent(new java.awt.Color(204, 0, 51));
        txtDestinatario.setCaretColor(new java.awt.Color(0, 0, 204));
        txtDestinatario.setEnabled(false);
        txtDestinatario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDestinatario.setLabel("DESTINATARIO");
        jPanel4.add(txtDestinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 410, 60));

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
        jPanel4.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 410, 70));

        txtTipoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtTipoCliente.setAccent(new java.awt.Color(204, 0, 51));
        txtTipoCliente.setEnabled(false);
        txtTipoCliente.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTipoCliente.setLabel("TIPO DE CLIENTE");
        txtTipoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoClienteKeyReleased(evt);
            }
        });
        jPanel4.add(txtTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 220, 60));

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setLabel("DESCRIPCIÓN");
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });
        jPanel4.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 340, 60));

        rSLabelImage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Pago.png"))); // NOI18N
        jPanel4.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 40, 40));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 40, 40));

        dateFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFecha.setPlaceholder("FECHA ");
        jPanel4.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 180, 30));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 40, 40));

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
        jLabel11.setText("Total  NoPagado US$ :");

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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(lbldni)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalNoPagadoSoles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblcambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(lblcodhv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoPagadoDolares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel4.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 350, 120));

        rdbCliente.setBorder(null);
        rdbCliente.setSelected(true);
        rdbCliente.setText("CLIENTE");
        rdbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbClienteActionPerformed(evt);
            }
        });
        jPanel4.add(rdbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        rdbNoPagado.setBorder(null);
        buttonGroup3.add(rdbNoPagado);
        rdbNoPagado.setSelected(true);
        rdbNoPagado.setText("NO PAGADO");
        jPanel4.add(rdbNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, -1, -1));

        rdbPagado.setBorder(null);
        buttonGroup3.add(rdbPagado);
        rdbPagado.setText("PAGADO");
        jPanel4.add(rdbPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, -1));

        rdbSol.setBorder(null);
        buttonGroup4.add(rdbSol);
        rdbSol.setSelected(true);
        rdbSol.setText("Sol (S/.)");
        jPanel4.add(rdbSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, -1, -1));

        rdbDolar.setBorder(null);
        buttonGroup4.add(rdbDolar);
        rdbDolar.setText("Dolar ($)");
        jPanel4.add(rdbDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

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

        btnImprimir1.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnImprimir1.setText("IMPRIMIR");
        btnImprimir1.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir1.setIconTextGap(2);
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        btnVerTodo.setBackground(new java.awt.Color(65, 94, 255));
        btnVerTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btnVerTodo.setText("VER TODO");
        btnVerTodo.setColorHover(new java.awt.Color(253, 173, 1));
        btnVerTodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVerTodo.setIconTextGap(2);
        btnVerTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarTipoTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        rSPanelShadow2.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1200, 60));

        lbl_son.setForeground(new java.awt.Color(0, 0, 204));
        jPanel4.add(lbl_son, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 410, 20));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        jPanel6.setBorder(dropShadowBorder3);

        tableDescontar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableDescontar.setAltoHead(30);
        tableDescontar.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableDescontar.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableDescontar.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableDescontar.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableDescontar.setComponentPopupMenu(popMenuEliminar);
        tableDescontar.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        tableDescontar.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableDescontar.setGrosorBordeFilas(0);
        tableDescontar.setGrosorBordeHead(0);
        tableDescontar.setRowHeight(20);
        tableDescontar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableDescontarMousePressed(evt);
            }
        });
        tableDescontar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableDescontarKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tableDescontar);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("TOTAL A DESCONTAR  $. :");

        lblDescontar.setText("0.0");

        btnDescontarValorizacion.setBackground(new java.awt.Color(65, 94, 255));
        btnDescontarValorizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descontar.png"))); // NOI18N
        btnDescontarValorizacion.setText("DESCONTAR");
        btnDescontarValorizacion.setColorHover(new java.awt.Color(253, 173, 1));
        btnDescontarValorizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDescontarValorizacion.setIconTextGap(0);
        btnDescontarValorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescontarValorizacionActionPerformed(evt);
            }
        });

        btn_mostrarTodos.setBackground(new java.awt.Color(65, 94, 255));
        btn_mostrarTodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Visualizar.png"))); // NOI18N
        btn_mostrarTodos.setText("MOSTRAR TODOS");
        btn_mostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_mostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescontar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDescontarValorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDescontarValorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(lblDescontar))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_mostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
            //  try {
            fila = tableAdelanto.getSelectedRow();
            String codigo = tableAdelanto.getValueAt(fila, 0).toString();
            adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCliente(Integer.parseInt(codigo));
//            } catch (Exception e) {
//                   Mensaje.mostrarFilaNoSeleccionada(this);
//            }
            llenarCamposParaModificar(adelantoSeleccionado);
            inabilitarCampos(true);
            String lectura = NumeroToLetras.NumeroALetras(txtImporte.getText().toString());
            lbl_son.setText("SON : S/. " + lectura + " ");
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

    /*
     private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                            
     metodoParaEliminar();
     }                                           
     */

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnGuardarActionPerformed

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

    private void txtTipoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtImporte.requestFocus();
        }
    }//GEN-LAST:event_txtTipoClienteKeyPressed

    private void txtTipoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoClienteKeyReleased

    }//GEN-LAST:event_txtTipoClienteKeyReleased

    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcion.requestFocus();
            String lectura = NumeroToLetras.NumeroALetras(txtImporte.getText().toString());
            lbl_son.setText("SON : S/. " + lectura + " ");
        }
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.' && evt.getKeyChar() != '-') {
            evt.consume();

        }
        if (evt.getKeyChar() == '.' && txtImporte.getText().contains(".") && txtImporte.getText().contains("-")) {
            evt.consume();

        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void tableAdelantoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyPressed
        fila = tableAdelanto.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
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

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        try {
            if (clienteEntranteSeleccionado != null) {
                print = gestionarAdelantoReporteServicio.mostrarAdelantoCliente(clienteEntranteSeleccionado, lblTotalNoPagadoSoles, lblNoPagadoDolares);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Seleccionar un cliente");
            }

        } catch (Exception ex) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnDescontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescontarActionPerformed
        try {
            double cambio = 0;
            String codigo = "", cantidad = "", descrip = "", fecha, moneda = "", estado;
            ModeloTabla tdbDescuento = (ModeloTabla) tableDescontar.getModel();
            Fila filas = new Fila();
            fila = tableAdelanto.getSelectedRow();
            if (fila == -1) {
                Mensaje.mostrarFilaNoSeleccionada(this);
            } else {
                estado = tableAdelanto.getValueAt(fila, 5).toString();
                if (estado.equals("PAGADO")) {
                    Mensaje.mostrarAdvertencia(this, "Ya esta Valorizado");
                } else if (actualizarDescuentoAdelanto(adelantoSeleccionado)) {
                    codigo = tableAdelanto.getValueAt(fila, 0).toString();
                    cantidad = tableAdelanto.getValueAt(fila, 1).toString();
                    moneda = tableAdelanto.getValueAt(fila, 2).toString();
                    descrip = tableAdelanto.getValueAt(fila, 3).toString();
                    fecha = tableAdelanto.getValueAt(fila, 4).toString();
                    filas.agregarValorCelda(codigo);
                    filas.agregarValorCelda(cantidad);
                    filas.agregarValorCelda(moneda);
                    filas.agregarValorCelda(descrip);
                    filas.agregarValorCelda(fecha);
                    filas.agregarValorCelda(estado);
                    tdbDescuento.agregarFila(filas);
                    tableDescontar.setModel(tdbDescuento);
                    if (moneda == null ? ("S/") == null : moneda.equals("S/")) {
                        double ccambio = Double.parseDouble(lblcambio.getText());
                        cambio = Double.parseDouble(cantidad) / ccambio;
                        if (lblDescontar.getText().equals("")) {
                            lblDescontar.setText(redondearDecimales(cambio, 2) + "");
                        } else {
                            double descuento1 = Double.parseDouble(lblDescontar.getText());
                            double toaldesc = descuento1 + cambio;
                            lblDescontar.setText(redondearDecimales(toaldesc, 2) + "");
                        }
                    } else {
                        cambio = Double.parseDouble(cantidad);
                        if (lblDescontar.getText().equals("")) {
                            lblDescontar.setText(redondearDecimales(cambio, 2) + "");
                        } else {
                            double descuento1 = Double.parseDouble(lblDescontar.getText());
                            double totaldescuento = descuento1 + cambio;
                            lblDescontar.setText(redondearDecimales(totaldescuento, 2) + "");
                        }
                    }
                    InicializarTabla();
                } else {
                    Mensaje.mostrarAdvertencia(this, "No se ha podido Actulizar");
                }
            }
            btnDescontarValorizacion.setEnabled(true);

        } catch (Exception e) {

        }


    }//GEN-LAST:event_btnDescontarActionPerformed

    private void descontar() {
        DefaultTableModel modelo = (DefaultTableModel) tableDescontar.getModel();
        Double suma = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {

            //Si la columna 4 está true añadimos el ID
            suma += (Double) modelo.getValueAt(i, 0);

        }
        if (suma == 0) {
            lblDescontar.setText("0.0");
        } else {
            double ccambio = Double.parseDouble(lblcambio.getText());
            double totalDescuento = suma / ccambio;
            lblDescontar.setText(String.valueOf(totalDescuento));
        }

    }

    boolean actualizarDescuentoAdelanto(Adelanto adelanto) {
        Valorizacion valorizacion = new Valorizacion();
        valorizacion.setCodigo(Integer.parseInt(FormGestionarValorizacionDetalle.txtCodigoValorizacion.getText()));
        adelanto.setValorizacion(valorizacion);
        adelanto.setAdelantoEstado("PAGADO");
        return gestionarAdelantoServicio.actualizarValorizacionAdelanto(adelanto) == 1;
    }
    private void btnVerTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodoActionPerformed
        if (clienteEntranteSeleccionado != null) {

            FormListaTodosAdelantoClienteEntrante formlistaProveedorCliente = new FormListaTodosAdelantoClienteEntrante(null, true, clienteEntranteSeleccionado);
            formlistaProveedorCliente.setVisible(true);
            InicializarTabla();
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Seleccionar un cliente");
        }


    }//GEN-LAST:event_btnVerTodoActionPerformed

    private void btnDescontarValorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescontarValorizacionActionPerformed
        double calculo = Double.parseDouble(FormGestionarValorizacionDetalle.txtAdelanto.getText());
        double calculo2 = Double.parseDouble(lblDescontar.getText());
        double suma = calculo = calculo + calculo2;
        FormGestionarValorizacionDetalle.txtAdelanto.setText(String.valueOf(suma));
        this.dispose();
    }//GEN-LAST:event_btnDescontarValorizacionActionPerformed

    private void txtBuscarTipoTrabajadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTipoTrabajadorKeyReleased
        try {
            String texto = txtBuscarTipoTrabajador.getText().toString();
            if (texto.equals("")) {
                InicializarTabla();
            } else {
                limpiarTabla();
                gestionarAdelantoServicio.mostrarAdelantoCliente(8, tableAdelanto, texto);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarTipoTrabajadorKeyReleased

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dateFecha.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tableDescontarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDescontarKeyTyped
        fila = tableAdelanto.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableDescontarKeyTyped

    private void btnEliminarDescontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDescontarActionPerformed
        metodoParaEliminarDescontar();
    }//GEN-LAST:event_btnEliminarDescontarActionPerformed
    int filaDescontar = 0;
    private void tableDescontarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDescontarMousePressed
        filaDescontar = tableDescontar.getSelectedRow();
    }//GEN-LAST:event_tableDescontarMousePressed

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        ChequeCliente cheque = new ChequeCliente();
        cheque.setClienteEntrante(clienteEntranteSeleccionado);
        cheque.setEntregadoA(clienteEntranteSeleccionado.generarNombre());
        cheque.setConcepto("POR ADELANTO ");
        if (rdbSol.isSelected()) {
            cheque.setMoneda("S/.");
        } else {
            cheque.setMoneda("$");
        }

        cheque.setMonto(Double.parseDouble(txtImporte.getText().toString()));
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

    private void previewChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previewChequeActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            tipo_accion = ACCION_CREAR;
            clienteEntranteSeleccionado = null;
            limpiarTabla();
            limpiarCampos();
            inabilitarBotones(true);
            inabilitarCampos(true);
//            abrirFormularioParaCliente();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void rdbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbClienteActionPerformed
        try {
            tipo_accion = ACCION_CREAR;
            clienteEntranteSeleccionado = null;
            limpiarTabla();
            limpiarCampos();
            inabilitarBotones(true);
            inabilitarCampos(true);
            abrirFormularioParaCliente();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_rdbClienteActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        tipo_accion = ACCION_CREAR;
        txtDescripcion.setText("");
        txtImporte.setText("");
        lbl_son.setText("SON: ");
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_mostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrarTodosActionPerformed
        try {
            FormlistaAdelantoClienteEntrante formlistaAdelantoClienteEntrante = new FormlistaAdelantoClienteEntrante(null, true);
            formlistaAdelantoClienteEntrante.setVisible(true);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_mostrarTodosActionPerformed

    private void eliminarCamposSinEliminarCliente() {
        txtDestinatario.setText(clienteEntranteSeleccionado.generarNombre());
        txtTipoCliente.setText(clienteEntranteSeleccionado.getTipoCliente().getDescripcion());
        txtDescripcion.setText("");
        txtImporte.setText("");

        rdbNoPagado.setSelected(true);
        lbl_son.setText("SON: ");
    }

    private void abrirFormularioParaCliente() {
        FormlistaClienteEntrante formlistaClienteEntrante = new FormlistaClienteEntrante(null, true);
        formlistaClienteEntrante.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            llenarCamposClienteSeleccionado();

        }
    }

    private void llenarCamposClienteSeleccionado() {
        limpiarCampos();
        txtDestinatario.setText(clienteEntranteSeleccionado.getNombres() + " " + clienteEntranteSeleccionado.getApellidos() + " : " + clienteEntranteSeleccionado.getDni());
        txtTipoCliente.setText(clienteEntranteSeleccionado.getTipoCliente().getDescripcion());
        lbldni.setText(clienteEntranteSeleccionado.getDni());
        lbldni.setForeground(Color.red);
        InicializarTabla();
        txtImporte.requestFocus();
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

        btnVerTodo.setEnabled(v);
        btnImprimir1.setEnabled(v);
        btnGuardar.setEnabled(v);
        btnNuevo.setEnabled(true);
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
    private javax.swing.JMenuItem btnDescontar;
    private rojeru_san.RSButton btnDescontarValorizacion;
    private javax.swing.JMenuItem btnEliminar;
    private rojeru_san.RSButton btnEliminarDescontar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir1;
    private rojeru_san.RSButton btnNuevo;
    private rojeru_san.RSButton btnVerTodo;
    private rojeru_san.RSButton btn_limpiar;
    private rojeru_san.RSButton btn_mostrarTodos;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private rojeru_san.componentes.RSDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescontar;
    public static javax.swing.JLabel lblNoPagadoDolares;
    private javax.swing.JLabel lblPersonal4;
    public static javax.swing.JLabel lblTotalNoPagadoSoles;
    private javax.swing.JLabel lbl_son;
    public static javax.swing.JLabel lblcambio;
    private javax.swing.JLabel lblcodhv;
    public static javax.swing.JLabel lbldni;
    private javax.swing.JPanel pnlMenu;
    private rojerusan.RSPopuMenu popMenu;
    private javax.swing.JPopupMenu popMenuEliminar;
    private com.icm.components.metro.CheckBoxMetroICM previewCheque;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage22;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    public static com.icm.components.metro.RadioButtonMetroICM rdbCliente;
    private com.icm.components.metro.RadioButtonMetroICM rdbDolar;
    private com.icm.components.metro.RadioButtonMetroICM rdbNoPagado;
    private com.icm.components.metro.RadioButtonMetroICM rdbPagado;
    private com.icm.components.metro.RadioButtonMetroICM rdbSol;
    public static rojerusan.RSTableMetro tableAdelanto;
    private rojerusan.RSTableMetro tableDescontar;
    private org.jdesktop.swingx.JXSearchField txtBuscarTipoTrabajador;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDescripcion;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtDestinatario;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtImporte;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTipoCliente;
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
            adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCliente(Integer.parseInt(codigo));
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
                    int registros_afectados = gestionarAdelantoServicio.eliminarAdelanto(adelantoSeleccionado);
                    if (registros_afectados == 1) {
//                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar un  Adelanto de Cliente", DesktopNotify.SUCCESS);
                        InicializarTabla();

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
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }

    private void metodoParaEliminarDescontar() {
        try {
            String codigo = null;
            codigo = tableDescontar.getValueAt(filaDescontar, 0).toString();
            Double cantidad = Double.parseDouble(tableDescontar.getValueAt(filaDescontar, 1).toString());
            String moneda = tableDescontar.getValueAt(filaDescontar, 2).toString();
            if (codigo != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    ModeloTabla modelo = (ModeloTabla) tableDescontar.getModel();
                    modelo.eliminarFila(filaDescontar);
                    tableDescontar.setModel(modelo);
                    int registros_afectados = gestionarAdelantoServicio.modificarAdelantoNoPagado(Integer.parseInt(codigo));
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de eliminar el descuento", 7);
//                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        InicializarTabla();
                        Double montoTotalDescuento = Double.parseDouble(lblDescontar.getText().toString());

                        double ccambio = Double.parseDouble(lblcambio.getText());
                        double total = 0.0;
                        if (moneda.equals("S/")) {
                            total = cantidad / ccambio;
                            total = montoTotalDescuento - total;
                        } else {
                            total = montoTotalDescuento - cantidad;
                        }
                        Double redondear = redondearDecimales(total, 2);
                        if (redondear < 0) {
                            lblDescontar.setText(String.valueOf(0));
                        } else {
                            lblDescontar.setText(String.valueOf(redondear));
                        }

                    } else if (registros_afectados == 0) {
//                        Mensaje.mostrarAdvertenciaDeEliminacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Los datos no pudo eliminar, verifique", 7);
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
