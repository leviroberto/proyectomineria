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
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarAdelantosPorveedorCliente extends javax.swing.JDialog {
    
    public static GestionarAdelantoServicio gestionarAdelantoServicio;
    public static GestionarCambioServicio gestionarCambioServicio;
    public static ProveedorServicio proveedorServicioSeleccionado;
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
    
    public FormGestionarAdelantosPorveedorCliente(java.awt.Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            this.TIPO_USUARIO = tipo;
            adelantoSeleccionado = new Adelanto();
            gestionarCambioServicio = new GestionarCambioServicio();
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            gestionarAdelantoReporteServicio = new GestionarAdelantoReporteServicio();
            inicializarTablaColumnas();
            inicializarTablaColumnasDescontar();
            inicializarCambio();
            inabilitarBotones(true);
            inabilitarCampos(false);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }
    
    public FormGestionarAdelantosPorveedorCliente(java.awt.Frame parent, boolean modal, int tipo, ClienteEntrante clienteEntrante) {
        super(parent, modal);
        try {
            initComponents();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            this.TIPO_USUARIO = tipo;
            adelantoSeleccionado = new Adelanto();
            gestionarCambioServicio = new GestionarCambioServicio();
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            
            inicializarTablaColumnas();
            inicializarTablaColumnasDescontar();
            inicializarCambio();
            clienteEntranteSeleccionado = clienteEntrante;
            inicializarTablaCliente();
            inabilitarBotones(true);
            inabilitarCampos(false);
            btnDescontarValorizacion.setEnabled(true);
            btnDescontar.setEnabled(true);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }
    
    public static void limpiarTabla() {
        ModeloTabla modelos = (ModeloTabla) tableAdelanto.getModel();
        modelos.eliminarTotalFilas();
    }
    
    public static void inicializarTablaCliente() {
        try {
            limpiarTabla();
            //   gestionarAdelantoServicio.mostrarAdelantoCliente(1, clienteEntranteSeleccionado, tableAdelanto);
            gestionarAdelantoServicio.calcularTotalNoPagadoSolesCliente(lblTotalNoPagadoSoles, clienteEntranteSeleccionado);
            gestionarAdelantoServicio.calcularTotalNoPagadoDolaresCliente(lblNoPagadoDolares, clienteEntranteSeleccionado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void inicializarTablaProveedor() {
        try {
            limpiarTabla();
            gestionarAdelantoServicio.mostrarAdelantoProveedor(1, proveedorServicioSeleccionado, tableAdelanto);
            gestionarAdelantoServicio.calcularTotalNoPagadoSoles(lblTotalNoPagadoSoles, proveedorServicioSeleccionado);
            gestionarAdelantoServicio.calcularTotalNoPagadoDolares(lblNoPagadoDolares, proveedorServicioSeleccionado);
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
        rdbProveedor.setEnabled(estado);
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
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableAdelanto.setModel(modeloTabla);
        // tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setWidth(0);
        //tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMinWidth(0);
        // tableAdelanto.getColumn(tableAdelanto.getColumnName(0)).setMaxWidth(0);
    }
    
    private void inicializarTablaColumnasDescontar() {
        Tabla tablas = new Tabla();
        tablas.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tablas.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tablas.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tablas.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
        tablas.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tablas.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        ModeloTabla modeloTablasd = new ModeloTabla(tablas);
        tableDescontar.setModel(modeloTablasd);
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
            
            if (rdbCliente.isSelected()) {
                clienteEntranteSeleccionado = adelanto.getClienteEntrante();
                txtDestinatario.setText(clienteEntranteSeleccionado.getNombres() + " " + clienteEntranteSeleccionado.getApellidos() + " :" + clienteEntranteSeleccionado.getDni());
                txtTipoCliente.setText(clienteEntranteSeleccionado.getTipoCliente().getDescripcion());
                proveedorServicioSeleccionado = null;
            } else {
                proveedorServicioSeleccionado = adelanto.getProveedorServicio();
                txtDestinatario.setText(proveedorServicioSeleccionado.getRazonSocial() + " :" + proveedorServicioSeleccionado.getRuc());
                clienteEntranteSeleccionado = null;
            }
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
                if (rdbProveedor.isSelected()) {
                    adelanto.setProveedorServicio(proveedorServicioSeleccionado);
                    adelanto.setClienteEntrante(null);
                } else if (rdbCliente.isSelected()) {
                    adelanto.setClienteEntrante(clienteEntranteSeleccionado);
                    adelanto.setProveedorServicio(null);
                }
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
                        if (rdbCliente.isSelected()) {
                            registros_afectados = gestionarAdelantoServicio.guardarAdelantoCliente(adelanto);
                            inicializarTablaCliente();
                        } else if (rdbProveedor.isSelected()) {
                            registros_afectados = gestionarAdelantoServicio.guardarAdelantoProveedor(adelanto);
                            inicializarTablaProveedor();
                        }
                        if (registros_afectados == 1) {
                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de crear un nuevo adelanto", 7);
                            inabilitarCampos(false);
                            
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeCreacion(this);
                        }
                    } catch (Exception e) {
                        Mensaje.mostrarErrorDeCreacion(this);
                    }
                    
                } else if (tipo_accion == ACCION_MODIFICAR) {
                    try {
                        
                        adelanto.setCodigo(Integer.parseInt(lblCodigo.getText()));
                        if (rdbCliente.isSelected()) {
                            registros_afectados = gestionarAdelantoServicio.modificarAdelantoCliente(adelanto);
                            inicializarTablaCliente();
                            
                        } else if (rdbProveedor.isSelected()) {
                            registros_afectados = gestionarAdelantoServicio.modificarAdelantoProveedor(adelanto);
                            inicializarTablaProveedor();
                        }
                        if (registros_afectados == 1) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de actualizar la  adelanto con numero de lote " + txtDestinatario.getText(), 7);
                            Mensaje.mostrarAfirmacionDeActualizacion(this);
                            inabilitarCampos(false);
                        } else if (registros_afectados == 0) {
                            Mensaje.mostrarAdvertenciaDeActualizacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted acaba de modificar un liquidacion", 7);
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
        btnDescontar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        txtBuscarTipoTrabajador = new org.jdesktop.swingx.JXSearchField();
        lblPersonal4 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        btnGuardar = new rojeru_san.RSButton();
        rSLabelImage22 = new rojerusan.RSLabelImage();
        txtDestinatario = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtImporte = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTipoCliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtDescripcion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        btnNuevo = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
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
        btnImprimir1 = new rojeru_san.RSButton();
        rdbCliente = new RadioButton.FiveCodRadioButton();
        rdbProveedor = new RadioButton.FiveCodRadioButton();
        rdbPagado = new RadioButton.FiveCodRadioButton();
        rdbNoPagado = new RadioButton.FiveCodRadioButton();
        rdbDolar = new RadioButton.FiveCodRadioButton();
        rdbSol = new RadioButton.FiveCodRadioButton();
        btnGuardar1 = new rojeru_san.RSButton();
        btnVerTodo = new rojeru_san.RSButton();
        btnGuardar3 = new rojeru_san.RSButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDescontar = new rojerusan.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        lblDescontar = new javax.swing.JLabel();
        btnDescontarValorizacion = new rojeru_san.RSButton();

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
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

        btnDescontar.setBackground(new java.awt.Color(65, 94, 255));
        btnDescontar.setBorder(null);
        btnDescontar.setText("DESCONTAR");
        btnDescontar.setColorHover(new java.awt.Color(255, 82, 54));
        btnDescontar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDescontar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDescontar.setIconTextGap(2);
        btnDescontar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescontarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnDescontar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnDescontar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("GESTIONAR DATOS DE ADALANTOS DE PROVEDORES ");

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(68, 138, 255));
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        });
        jPanel4.add(txtBuscarTipoTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 500, 30));
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 200, 10));
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 40, 30));
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 40, 30));
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 40, 30));

        btnGuardar.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 110, 30));
        jPanel4.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 40, 30));

        txtDestinatario.setForeground(new java.awt.Color(0, 0, 204));
        txtDestinatario.setAccent(new java.awt.Color(204, 0, 51));
        txtDestinatario.setCaretColor(new java.awt.Color(0, 0, 204));
        txtDestinatario.setEnabled(false);
        txtDestinatario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDestinatario.setLabel("DESTINATARIO");
        jPanel4.add(txtDestinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 220, 60));

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
        jPanel4.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 220, 70));

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
        jPanel4.add(txtTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 220, 60));

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setAccent(new java.awt.Color(204, 0, 51));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setLabel("DESCRIPCIÓN");
        jPanel4.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 340, 70));
        jPanel4.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 40, 30));

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 130, 30));

        btnCancelar.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar.setText("ACTUALIZAR");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar.setIconTextGap(0);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 130, 30));

        btnImprimir.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir.setText("CHEQUE");
        btnImprimir.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setIconTextGap(0);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel4.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 100, 30));
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, 30));

        dateFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFecha.setPlaceholder("FECHA ");
        jPanel4.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 180, 30));
        jPanel4.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 40, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("T/C : ");

        lblcodhv.setText("0.0");

        lbldni.setText("0000000000");

        lblcambio.setText("cambio");
        lblcambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcambioMouseClicked(evt);
            }
        });

        jLabel11.setText("Total  Pagado US$ :");

        jLabel10.setText("Total No Pagado  S/.");

        lblTotalNoPagadoSoles.setText("0.0");

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
                    .addComponent(lblcambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
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

        rSPanelShadow1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel4.add(rSPanelShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 270, 120));

        btnImprimir1.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir1.setText("IMPRIMIR");
        btnImprimir1.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir1.setIconTextGap(0);
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnImprimir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 100, 30));

        buttonGroup2.add(rdbCliente);
        rdbCliente.setSelected(true);
        rdbCliente.setText("CLIENTE");
        rdbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbClienteActionPerformed(evt);
            }
        });
        jPanel4.add(rdbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        buttonGroup2.add(rdbProveedor);
        rdbProveedor.setText("PROVEEDOR");
        rdbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(rdbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        buttonGroup3.add(rdbPagado);
        rdbPagado.setText("PAGADO");
        jPanel4.add(rdbPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        buttonGroup3.add(rdbNoPagado);
        rdbNoPagado.setSelected(true);
        rdbNoPagado.setText("NO PAGADO");
        jPanel4.add(rdbNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        buttonGroup4.add(rdbDolar);
        rdbDolar.setSelected(true);
        rdbDolar.setText("Dolar ($)");
        jPanel4.add(rdbDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

        buttonGroup4.add(rdbSol);
        rdbSol.setText("Sol (S/.)");
        jPanel4.add(rdbSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        btnGuardar1.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar1.setText("Proveedor");
        btnGuardar1.setColorHover(new java.awt.Color(253, 173, 1));
        btnGuardar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setIconTextGap(0);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 150, 100, 30));

        btnVerTodo.setBackground(new java.awt.Color(65, 94, 255));
        btnVerTodo.setText("VER TODO");
        btnVerTodo.setColorHover(new java.awt.Color(253, 173, 1));
        btnVerTodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVerTodo.setIconTextGap(0);
        btnVerTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodoActionPerformed(evt);
            }
        });
        jPanel4.add(btnVerTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, 100, 30));

        btnGuardar3.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar3.setText("Cliente");
        btnGuardar3.setColorHover(new java.awt.Color(253, 173, 1));
        btnGuardar3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar3.setIconTextGap(0);
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 90, 100, 30));

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
        tableDescontar.setComponentPopupMenu(popMenu);
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDescontarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableDescontarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableDescontarKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tableDescontar);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1165, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("TOTAL A DESCONTAR  $. :");

        lblDescontar.setText("0.0");

        btnDescontarValorizacion.setBackground(new java.awt.Color(65, 94, 255));
        btnDescontarValorizacion.setText("DESCONTAR");
        btnDescontarValorizacion.setColorHover(new java.awt.Color(253, 173, 1));
        btnDescontarValorizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDescontarValorizacion.setIconTextGap(0);
        btnDescontarValorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescontarValorizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescontar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnDescontarValorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblDescontar))
                    .addComponent(btnDescontarValorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            btnDescontarValorizacion.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(true);
            fila = tableAdelanto.getSelectedRow();
            String codigo = tableAdelanto.getValueAt(fila, 0).toString();
            if (rdbCliente.isSelected()) {
                adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCliente(Integer.parseInt(codigo));
                
            } else {
                adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCodigoProveedor(Integer.parseInt(codigo));
            }
            llenarCamposParaModificar(adelantoSeleccionado);
            inabilitarCampos(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
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
        try {
            obtenerAdelantoSeleccionado();
            if (adelantoSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }
                try {
                    int registros_afectados = gestionarAdelantoServicio.eliminarAdelanto(adelantoSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        if (rdbCliente.isSelected()) {
                            inicializarTablaCliente();
                        } else {
                            inicializarTablaProveedor();
                        }
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
    }//GEN-LAST:event_btnEliminarActionPerformed

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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            tipo_accion = ACCION_CREAR;
            clienteEntranteSeleccionado = null;
            proveedorServicioSeleccionado = null;
            limpiarTabla();
            limpiarCampos();
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(true);
            
            inabilitarCampos(true);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        inabilitarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

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
        }
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped

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

    private void tableDescontarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDescontarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDescontarMousePressed

    private void tableDescontarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDescontarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDescontarKeyPressed

    private void tableDescontarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDescontarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDescontarKeyReleased

    private void tableDescontarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDescontarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDescontarKeyTyped

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        try {
            
            if (rdbCliente.isSelected()) {
                print = gestionarAdelantoReporteServicio.mostrarAdelantoCliente(clienteEntranteSeleccionado, lblTotalNoPagadoSoles, lblNoPagadoDolares);
            } else if (rdbProveedor.isSelected()) {
                print = gestionarAdelantoReporteServicio.mostrarAdelantoProveedor(proveedorServicioSeleccionado, lblTotalNoPagadoSoles, lblNoPagadoDolares);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        inicializarTablaProveedor();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void rdbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbProveedorActionPerformed
        FormlistaProvedorServicio formlistaProvedorServicio = new FormlistaProvedorServicio(null, true);
        formlistaProvedorServicio.setVisible(true);
        if (proveedorServicioSeleccionado != null) {
            limpiarCampos();
            txtDestinatario.setText(proveedorServicioSeleccionado.getRazonSocial() + " : " + proveedorServicioSeleccionado.getRuc());
            lbldni.setText(proveedorServicioSeleccionado.getRuc());
            lbldni.setForeground(Color.red);
            txtTipoCliente.setText("");
            inicializarTablaProveedor();
            
        }
    }//GEN-LAST:event_rdbProveedorActionPerformed

    private void rdbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbClienteActionPerformed
        FormlistaClienteEntrante formlistaClienteEntrante = new FormlistaClienteEntrante(null, true);
        formlistaClienteEntrante.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            limpiarCampos();
            txtDestinatario.setText(clienteEntranteSeleccionado.getNombres() + " " + clienteEntranteSeleccionado.getApellidos() + " : " + clienteEntranteSeleccionado.getDni());
            txtTipoCliente.setText(clienteEntranteSeleccionado.getTipoCliente().getDescripcion());
            lbldni.setText(clienteEntranteSeleccionado.getDni());
            lbldni.setForeground(Color.red);
            inicializarTablaCliente();
            
        }
    }//GEN-LAST:event_rdbClienteActionPerformed

    private void btnDescontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescontarActionPerformed
        
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
                JOptionPane.showMessageDialog(null, "Ya esta descontado");
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
                
                if (moneda == null ? ("S") == null : moneda.equals("S")) {
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
                inicializarTablaCliente();
            } else {
                JOptionPane.showMessageDialog(null, "no se puede actualizar");
            }
            
        }
        btnDescontarValorizacion.setEnabled(true);
        

    }//GEN-LAST:event_btnDescontarActionPerformed
    
    boolean actualizarDescuentoAdelanto(Adelanto adelanto) {
        Valorizacion valorizacion = new Valorizacion();
        valorizacion.setCodigo(Integer.parseInt(FormGestionarValorizacionDetalle.txtCodigoValorizacion.getText()));
        adelanto.setValorizacion(valorizacion);
        adelanto.setAdelantoEstado("S");
        return gestionarAdelantoServicio.actualizarValorizacionAdelanto(adelanto) == 1;
    }
    private void btnVerTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodoActionPerformed
        FormlistaProveedorCliente formlistaProveedorCliente = new FormlistaProveedorCliente(null, true);
        
        if (rdbCliente.isSelected() == true) {
            if (clienteEntranteSeleccionado != null) {
                FormlistaProveedorCliente.lblClienteProveedor.setText(txtDestinatario.getText());
                formlistaProveedorCliente.inicializarTablaColumnasCliente();
                formlistaProveedorCliente.inicializarTablaCliente();
                formlistaProveedorCliente.setVisible(true);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Seleccionar un cliente");
            }
            
        } else if (rdbProveedor.isSelected() == true) {
            if (proveedorServicioSeleccionado != null) {
                FormlistaProveedorCliente.lblClienteProveedor.setText(txtDestinatario.getText());
                formlistaProveedorCliente.inicializarTablaColumnasProveedor();
                formlistaProveedorCliente.inicializarTablaProveedor();
                formlistaProveedorCliente.setVisible(true);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Seleccionar un proveedor");
                
            }
            
        }
    }//GEN-LAST:event_btnVerTodoActionPerformed

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        inicializarTablaCliente();
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnDescontarValorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescontarValorizacionActionPerformed
        double calculo = Double.parseDouble(FormGestionarValorizacionDetalle.txtAdelanto.getText());
        double calculo2 = Double.parseDouble(lblDescontar.getText());
        double suma = calculo = calculo + calculo2;
        FormGestionarValorizacionDetalle.txtAdelanto.setText(String.valueOf(suma));
        this.dispose();
    }//GEN-LAST:event_btnDescontarValorizacionActionPerformed

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
        btnEliminar.setEnabled(v);
        
        btnDescontar.setEnabled(false);
        btnDescontarValorizacion.setEnabled(false);
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
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnDescontar;
    private rojeru_san.RSButton btnDescontarValorizacion;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnGuardar1;
    private rojeru_san.RSButton btnGuardar3;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnImprimir1;
    private rojeru_san.RSButton btnNuevo;
    private rojeru_san.RSButton btnVerTodo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescontar;
    public static javax.swing.JLabel lblNoPagadoDolares;
    private javax.swing.JLabel lblPersonal4;
    public static javax.swing.JLabel lblTotalNoPagadoSoles;
    public static javax.swing.JLabel lblcambio;
    private javax.swing.JLabel lblcodhv;
    public static javax.swing.JLabel lbldni;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage22;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojeru_san.RSPanelShadow rSPanelShadow1;
    public static RadioButton.FiveCodRadioButton rdbCliente;
    private RadioButton.FiveCodRadioButton rdbDolar;
    private RadioButton.FiveCodRadioButton rdbNoPagado;
    private RadioButton.FiveCodRadioButton rdbPagado;
    public static RadioButton.FiveCodRadioButton rdbProveedor;
    private RadioButton.FiveCodRadioButton rdbSol;
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
            adelantoSeleccionado = gestionarAdelantoServicio.buscarAdelantoServicioPorCodigoProveedor(Integer.parseInt(codigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}
