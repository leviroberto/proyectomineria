package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Procedencia;
import com.sun.glass.events.KeyEvent;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
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
public class FormGestionarLiquidacion extends javax.swing.JDialog {
    
    private GestionarLiquidacionServicio gestionarLiquidacionServicio;
    public static Liquidacion liquidacionSeleccionado;
    public static ClienteEntrante clienteEntranteSeleccionado;
    public static Procedencia procedenciaSeleccionado;
    public static int TIPO_FACTURA = 2;
    public static int TIPO_TRABAJADOR = 1;
    public static int TIPO_VALORIZACION = 3;
    private GestionarLiquidacionReporteServicio gestionarLiquidacionReporteServicio;
    JasperPrint print;
    private int TIPO_USUARIO;
    private int tipo_accion;
    DefaultTableModel modelo;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int codigoLiquidacion;
    private final String ruta = System.getProperties().getProperty("user.dir");
    
    public FormGestionarLiquidacion(java.awt.Frame parent, boolean modal, int tipo) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            this.TIPO_USUARIO = tipo;
            liquidacionSeleccionado = new Liquidacion();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarLiquidacionReporteServicio = new GestionarLiquidacionReporteServicio();
            llenarCBOProcedencia();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(true);
            txtClienteEntrante.setEnabled(false);
            txtCodigo.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(false);
            
            inabilitarCampos(false);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }
    
    public FormGestionarLiquidacion(java.awt.Frame parent, boolean modal, int tipo, Liquidacion liquidacion) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            this.TIPO_USUARIO = tipo;
            tipo_accion = ACCION_MODIFICAR;
            liquidacionSeleccionado = new Liquidacion();
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarLiquidacionReporteServicio = new GestionarLiquidacionReporteServicio();
            llenarCBOProcedencia();
            inicializarTablaColumnas();
            inicializarTabla();
//            obtenerDatosTabla();
            llenarCamposParaModificar(liquidacion);
            inabilitarCampos(true);
            
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }
    
    public void llenarCBOProcedencia() {
        try {
            cboProcedencia.removeAllItems();
            gestionarLiquidacionServicio.llenarCBOProcedencia(cboProcedencia);
        } catch (Exception e) {
        }
    }
    
    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    
    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("TMH", "java.lang.String"));
        tabla.agregarColumna(new Columna("H2O", "java.lang.String"));
        tabla.agregarColumna(new Columna("TMS", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("LEYAAU", "java.lang.String"));
        tabla.agregarColumna(new Columna("LEYAG", "java.lang.String"));
        tabla.agregarColumna(new Columna("INTER", "java.lang.String"));
        tabla.agregarColumna(new Columna("REC", "java.lang.String"));
        tabla.agregarColumna(new Columna("MAQUILLA", "java.lang.String"));
        tabla.agregarColumna(new Columna("FACTOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("CONSCON", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESCALADOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL US", "java.lang.String"));
        tabla.agregarColumna(new Columna("REINTEGRO", "java.lang.String"));
        tabla.agregarColumna(new Columna("FACTURADO", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableLiquidacion.setModel(modeloTabla);
        // tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setWidth(0);
        //tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMinWidth(0);
        //tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMaxWidth(0);

        TableColumnModel columnModel = tableLiquidacion.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(20);
        columnModel.getColumn(3).setPreferredWidth(20);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(6).setPreferredWidth(20);
        columnModel.getColumn(7).setPreferredWidth(20);
        columnModel.getColumn(8).setPreferredWidth(20);
        columnModel.getColumn(9).setPreferredWidth(20);
        columnModel.getColumn(10).setPreferredWidth(30);
        columnModel.getColumn(11).setPreferredWidth(30);
        columnModel.getColumn(12).setPreferredWidth(30);
        columnModel.getColumn(13).setPreferredWidth(40);
        columnModel.getColumn(18).setPreferredWidth(150);
        
        tableLiquidacion.setColumnModel(columnModel);
    }
    
    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableLiquidacion.getModel();
        modelo.eliminarTotalFilas();
    }
    
    private void inicializarTabla() {
        try {
            
            limpiarTabla();
            gestionarLiquidacionServicio.mostrarLiquidacions(1, tableLiquidacion);
            inabilitarCampos(false);
            limpiarCampos();
        } catch (Exception e) {
        }
        
    }
    
    private void limpiarCampos() {
        txt$tms.setText("");
        rbtn.setSelected(true);
        clienteEntranteSeleccionado = null;
        txtClienteEntrante.setText("");
        txtCodigo.setText("");
        txtConscn.setText("");
        txtEscalador.setText("");
        txtFactor.setText("");
        txtH2O.setText("");
        txtInter.setText("");
        txtLeyAg.setText("");
        txtLeyAu.setText("");
        txtLote.setText("");
        txtMaquilla.setText("");
        procedenciaSeleccionado = null;
        txtRec.setText("");
        txtTmh.setText("");
        txtTms.setText("");
        txtTotalUS.setText("");
        
    }
    
    private void llenarDatosDeCeros() {
        txt$tms.setText("0");
        txtCodigo.setText("0");
        txtConscn.setText("0");
        txtEscalador.setText("0");
        txtFactor.setText("0");
        txtH2O.setText("0");
        txtInter.setText("0");
        txtLeyAg.setText("0");
        txtLeyAu.setText("0");
        txtLote.setText("0");
        txtMaquilla.setText("0");
        txtRec.setText("0");
        txtTmh.setText("0");
        txtTms.setText("0");
        txtTotalUS.setText("0");
        for (int i = 0; i < cboProcedencia.getItemCount(); i++) {
            if ("RETAMAS".equals(cboProcedencia.getItemAt(i).getDescripcion())) {
                cboProcedencia.setSelectedIndex(i);
            }
        }
    }
    
    private void inabilitarCampos(boolean estado) {
        txt$tms.setEnabled(estado);
        txtCodigo.setEnabled(false);
        txtConscn.setEnabled(estado);
        txtEscalador.setEnabled(estado);
        txtFactor.setEnabled(estado);
        txtH2O.setEnabled(estado);
        txtInter.setEnabled(estado);
        txtLeyAg.setEnabled(estado);
        txtLeyAu.setEnabled(estado);
        txtLote.setEnabled(estado);
        txtMaquilla.setEnabled(estado);
        txtClienteEntrante.setEnabled(false);
        txtRec.setEnabled(estado);
        txtTmh.setEnabled(estado);
        txtTms.setEnabled(estado);
        txtTotalUS.setEnabled(estado);
        dateFecha.setEnabled(estado);
        btnClienteEntrante.setEnabled(estado);
        btnProcedencia.setEnabled(estado);
        cboProcedencia.setEnabled(estado);
    }
    
    private void llenarCamposParaModificar(Liquidacion liqui) {
        txtCodigo.setText(String.valueOf(liqui.getCodigo()));
        txtLote.setText(liqui.getLote());
        txtTmh.setText(liqui.getTmh());
        txtH2O.setText(liqui.getH2o());
        txtTms.setText(liqui.getTms());
        dateFecha.setDatoFecha(liqui.getFecha());
        txtLeyAu.setText(liqui.getLeyau());
        txtLeyAg.setText(liqui.getLeyag());
        txtInter.setText(liqui.getInter());
        txtRec.setText(liqui.getRec());
        txtMaquilla.setText(liqui.getMaquilla());
        txtFactor.setText(liqui.getFactor());
        clienteEntranteSeleccionado = liqui.getClienteEntrante();
        txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
        txtConscn.setText(liqui.getConscn());
        txtEscalador.setText(liqui.getEscalador());
        txt$tms.setText(liqui.getStms());
        procedenciaSeleccionado = liqui.getProcedencia();
        txt_proteccion.setText(liqui.getProteccion());
        txtTotalUS.setText(liqui.getTotalus());
        if (liqui.getEstadoLiquidacion().equals("N")) {
            rbtn.setSelected(true);
            rbtv.setSelected(false);
        } else if (liqui.getEstadoLiquidacion().equals("V")) {
            rbtn.setSelected(false);
            rbtv.setSelected(true);
        }
        for (int i = 0; i < cboProcedencia.getItemCount(); i++) {
            if (liqui.getProcedencia().getDescripcion().equals(cboProcedencia.getItemAt(i).getDescripcion())) {
                cboProcedencia.setSelectedIndex(i);
            }
        }
        
        liquidacionSeleccionado = liqui;
    }
    
    private void guardarDatos() {
        Liquidacion liquidacion = new Liquidacion();
        
        if (verificarCamposVacios()) {
            if (!existeLote()) {
                try {
                    Date fecha = dateFecha.getDatoFecha();
                    liquidacion.setFecha(new java.sql.Date(fecha.getTime()));
                    liquidacion.setClienteEntrante(clienteEntranteSeleccionado);
                    procedenciaSeleccionado = cboProcedencia.getItemAt(cboProcedencia.getSelectedIndex());
                    liquidacion.setProcedencia(procedenciaSeleccionado);
                    liquidacion.setLote(txtLote.getText());
                    liquidacion.setProteccion(txt_proteccion.getText());
                    liquidacion.setTmh(txtTmh.getText());
                    liquidacion.setH2o(txtH2O.getText());
                    liquidacion.setTms(txtTms.getText());
                    liquidacion.setLeyau(txtLeyAu.getText());
                    liquidacion.setLeyag(txtLeyAg.getText());
                    liquidacion.setInter(txtInter.getText());
                    liquidacion.setRec(txtRec.getText());
                    liquidacion.setMaquilla(txtMaquilla.getText());
                    liquidacion.setFactor(txtFactor.getText());
                    liquidacion.setConscn(txtConscn.getText());
                    liquidacion.setEscalador(txtEscalador.getText());
                    liquidacion.setStms(txt$tms.getText());
                    liquidacion.setTotalus(txtTotalUS.getText());
                    liquidacion.setReintegro("No");
                    liquidacion.setFacturado("No");
                    liquidacion.setPersonal(IniciarSesion.getUsuario());
                    liquidacion.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
                    if (rbtn.isSelected()) {
                        liquidacion.setEstadoLiquidacion("N");
                    } else {
                        liquidacion.setEstadoLiquidacion("V");
                    }
                    
                    int registros_afectados;
                    if (tipo_accion == ACCION_CREAR) {
                        try {
                            registros_afectados = gestionarLiquidacionServicio.guardarLiquidacion(liquidacion);
                            if (registros_afectados == 1) {
//                                Mensaje.mostrarAfirmacionDeCreacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear Una Nueva Liquidacion", DesktopNotify.SUCCESS);
                                inicializarTabla();
                                
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeCreacion(this);
                            }
                            
                        } catch (Exception e) {
                            Mensaje.mostrarErrorDeCreacion(this);
                        }
                    } else if (tipo_accion == ACCION_MODIFICAR) {
                        try {
                            liquidacion.setCodigo(Integer.parseInt(txtCodigo.getText()));
                            registros_afectados = gestionarLiquidacionServicio.modificarLiquidacion(liquidacion);
                            if (registros_afectados == 1) {
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar la  Liquidacion Con Numero de Lote " + txtLote.getText(), DesktopNotify.INPUT_REQUEST);
//                                Mensaje.mostrarAfirmacionDeActualizacion(this);
                                inicializarTabla();
                                
                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeActualizacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un Liquidacion", DesktopNotify.INPUT_REQUEST);
                                
                            }
                            
                        } catch (Exception e) {
                            Mensaje.mostrarErrorDeActualizacion(this);
                        }
                    }
                } catch (Exception e) {
                    Mensaje.mostrarErrorSistema(this);
                }
                
            }
            
        }
    }
    
    private boolean existeLote() {
        boolean respuesta = false;
        try {
            if (tipo_accion == ACCION_CREAR) {
                int lote = Integer.parseInt(txtLote.getText());
                int verificarNumero = gestionarLiquidacionServicio.verificarNumeroLote(lote);
                if (verificarNumero >= 1) {
                    //Mensaje.mostrarMensajeDefinido(this, "El lote " + lote + ": Ya Esta Registrado Ingrese Uno diferente o de lo Contrario Busque este Numero y Eliminelo: Haga Click en Aceptar");
                    DesktopNotify.showDesktopMessage("FiveCod Software", "El lote " + lote + ":Ya Esta Registrado Ingrese Uno diferente o de lo Contrario Busque este Numero y Eliminelo: Haga Click en Aceptar", DesktopNotify.WARNING);
                    
                    respuesta = true;
                    
                }
            }
            return respuesta;
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        btnReintegro = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLiquidacion = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarLiquidacion = new org.jdesktop.swingx.JXSearchField();
        btnClienteEntrante = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        lblPersonal3 = new javax.swing.JLabel();
        lblPersonal4 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        rSLabelImage13 = new rojerusan.RSLabelImage();
        rSLabelImage14 = new rojerusan.RSLabelImage();
        rSLabelImage15 = new rojerusan.RSLabelImage();
        dateFecha = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage16 = new rojerusan.RSLabelImage();
        rSLabelImage17 = new rojerusan.RSLabelImage();
        rSLabelImage18 = new rojerusan.RSLabelImage();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        rSLabelImage20 = new rojerusan.RSLabelImage();
        rSLabelImage22 = new rojerusan.RSLabelImage();
        txtCodigo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtH2O = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTmh = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtLeyAu = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtLeyAg = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtLote = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtInter = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtRec = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtMaquilla = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtFactor = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtEscalador = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt$tms = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalUS = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtClienteEntrante = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtConscn = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage21 = new rojerusan.RSLabelImage();
        btnNuevo = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        rbtn = new com.icm.components.metro.RadioButtonMetroICM();
        rbtv = new com.icm.components.metro.RadioButtonMetroICM();
        cboProcedencia = new FiveCodMaterilalDesignComboBox.MaterialComboBox<Procedencia>();
        btnProcedencia = new rojerusan.RSButtonIconD();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        txtTms = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        btnCheque = new rojeru_san.RSButton();
        txt_proteccion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        btnImprimir1 = new rojeru_san.RSButton();

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

        btnReintegro.setBackground(new java.awt.Color(65, 94, 255));
        btnReintegro.setBorder(null);
        btnReintegro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descontar.png"))); // NOI18N
        btnReintegro.setText("REINTEGRO");
        btnReintegro.setColorHover(new java.awt.Color(255, 82, 54));
        btnReintegro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReintegro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnReintegro.setIconTextGap(2);
        btnReintegro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReintegroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnReintegro, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnReintegro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("GESTIONAR DATOS DE LIQUIDACIÓN ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoLiquidacion.png"))); // NOI18N

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
                .addGap(235, 235, 235)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

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
        tableLiquidacion.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        tableLiquidacion.setFuenteFilas(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        tableLiquidacion.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        tableLiquidacion.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        tableLiquidacion.setGrosorBordeFilas(0);
        tableLiquidacion.setGrosorBordeHead(0);
        tableLiquidacion.setRowHeight(20);
        tableLiquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLiquidacionMousePressed(evt);
            }
        });
        tableLiquidacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableLiquidacionKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableLiquidacion);

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarLiquidacion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarLiquidacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarLiquidacion.setToolTipText("BUSCAR POR LOTE | CLIENTE");
        txtBuscarLiquidacion.setPrompt("BUSCAR POR LOTE | CLIENTE");
        txtBuscarLiquidacion.setPromptBackround(null);
        txtBuscarLiquidacion.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarLiquidacion.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarLiquidacion.setSelectionEnd(1);
        txtBuscarLiquidacion.setSelectionStart(2);
        txtBuscarLiquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarLiquidacionActionPerformed(evt);
            }
        });
        txtBuscarLiquidacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarLiquidacionKeyPressed(evt);
            }
        });
        jPanel4.add(txtBuscarLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 280, 20));

        btnClienteEntrante.setBackground(new java.awt.Color(65, 94, 255));
        btnClienteEntrante.setText("...");
        btnClienteEntrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEntranteActionPerformed(evt);
            }
        });
        jPanel4.add(btnClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 40, 30));

        btnGuardar.setBackground(new java.awt.Color(65, 94, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setColorHover(new java.awt.Color(253, 173, 1));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.setIconTextGap(0);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 50, 180, 30));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 40, 40));
        jPanel4.add(lblPersonal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 200, 10));
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 200, 10));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 40, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Lote.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 40, 40));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 40, 40));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Ley.png"))); // NOI18N
        jPanel4.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 40, 40));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Ley.png"))); // NOI18N
        jPanel4.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 40, 40));

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 40, 40));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factor.png"))); // NOI18N
        jPanel4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 40, 30));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Maquila.png"))); // NOI18N
        jPanel4.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 40, 30));

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 40, 30));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 40, 30));

        dateFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFecha.setFormatoFecha("yyyy-MM-dd");
        dateFecha.setPlaceholder("FECHA ");
        jPanel4.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 140, 40));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 40, 30));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 40, 30));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 40, 30));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 40, 30));

        rSLabelImage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 40, 40));

        rSLabelImage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 90, 40, 30));

        txtCodigo.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigo.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigo.setCaretColor(new java.awt.Color(0, 0, 204));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigo.setLabel("Codigo");
        jPanel4.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 120, 60));

        txtH2O.setForeground(new java.awt.Color(0, 0, 204));
        txtH2O.setAccent(new java.awt.Color(204, 0, 51));
        txtH2O.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtH2O.setLabel("% (H 2 O)");
        txtH2O.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtH2OKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtH2OKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtH2OKeyTyped(evt);
            }
        });
        jPanel4.add(txtH2O, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 120, 60));

        txtTmh.setForeground(new java.awt.Color(0, 0, 204));
        txtTmh.setAccent(new java.awt.Color(204, 0, 51));
        txtTmh.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTmh.setLabel("T.M.H");
        txtTmh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTmhKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTmhKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTmhKeyTyped(evt);
            }
        });
        jPanel4.add(txtTmh, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 120, 70));

        txtLeyAu.setForeground(new java.awt.Color(0, 0, 204));
        txtLeyAu.setAccent(new java.awt.Color(204, 0, 51));
        txtLeyAu.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtLeyAu.setLabel("Ley Au");
        txtLeyAu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLeyAuKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeyAuKeyTyped(evt);
            }
        });
        jPanel4.add(txtLeyAu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 140, 60));

        txtLeyAg.setForeground(new java.awt.Color(0, 0, 204));
        txtLeyAg.setAccent(new java.awt.Color(204, 0, 51));
        txtLeyAg.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtLeyAg.setLabel("Ley Ag");
        txtLeyAg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLeyAgKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeyAgKeyTyped(evt);
            }
        });
        jPanel4.add(txtLeyAg, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 140, 70));

        txtLote.setForeground(new java.awt.Color(0, 0, 204));
        txtLote.setAccent(new java.awt.Color(204, 0, 51));
        txtLote.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtLote.setLabel("Lote");
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteKeyReleased(evt);
            }
        });
        jPanel4.add(txtLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 120, 60));

        txtInter.setForeground(new java.awt.Color(0, 0, 204));
        txtInter.setAccent(new java.awt.Color(204, 0, 51));
        txtInter.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtInter.setLabel("Inter");
        txtInter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInterKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInterKeyTyped(evt);
            }
        });
        jPanel4.add(txtInter, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 150, 60));

        txtRec.setForeground(new java.awt.Color(0, 0, 204));
        txtRec.setAccent(new java.awt.Color(204, 0, 51));
        txtRec.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtRec.setLabel("Rec(%)");
        txtRec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRecKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecKeyTyped(evt);
            }
        });
        jPanel4.add(txtRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 150, 70));

        txtMaquilla.setForeground(new java.awt.Color(0, 0, 204));
        txtMaquilla.setAccent(new java.awt.Color(204, 0, 51));
        txtMaquilla.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtMaquilla.setLabel("Maquilla");
        txtMaquilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaquillaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaquillaKeyTyped(evt);
            }
        });
        jPanel4.add(txtMaquilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 150, 60));

        txtFactor.setForeground(new java.awt.Color(0, 0, 204));
        txtFactor.setAccent(new java.awt.Color(204, 0, 51));
        txtFactor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtFactor.setLabel("Factor");
        txtFactor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFactorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFactorKeyTyped(evt);
            }
        });
        jPanel4.add(txtFactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 150, 60));

        txtEscalador.setForeground(new java.awt.Color(0, 0, 204));
        txtEscalador.setAccent(new java.awt.Color(204, 0, 51));
        txtEscalador.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtEscalador.setLabel("Escalador");
        txtEscalador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEscaladorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEscaladorKeyTyped(evt);
            }
        });
        jPanel4.add(txtEscalador, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 230, 60));

        txt$tms.setForeground(new java.awt.Color(0, 0, 204));
        txt$tms.setAccent(new java.awt.Color(204, 0, 51));
        txt$tms.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt$tms.setLabel("$ TMS");
        txt$tms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt$tmsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt$tmsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt$tmsKeyTyped(evt);
            }
        });
        jPanel4.add(txt$tms, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 230, 60));

        txtTotalUS.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalUS.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalUS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalUS.setLabel("Total Us$");
        txtTotalUS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalUSKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalUSKeyTyped(evt);
            }
        });
        jPanel4.add(txtTotalUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, 220, 60));

        txtClienteEntrante.setForeground(new java.awt.Color(0, 0, 204));
        txtClienteEntrante.setAccent(new java.awt.Color(204, 0, 51));
        txtClienteEntrante.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtClienteEntrante.setLabel("Cliente Entrante");
        txtClienteEntrante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteEntranteKeyPressed(evt);
            }
        });
        jPanel4.add(txtClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 190, 60));

        txtConscn.setForeground(new java.awt.Color(0, 0, 204));
        txtConscn.setAccent(new java.awt.Color(204, 0, 51));
        txtConscn.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtConscn.setLabel("Cons cn");
        txtConscn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConscnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConscnKeyTyped(evt);
            }
        });
        jPanel4.add(txtConscn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 90, 60));

        rSLabelImage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Procedencia.png"))); // NOI18N
        jPanel4.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 40, 40));

        btnNuevo.setBackground(new java.awt.Color(65, 94, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 10, 180, 30));

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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 90, 180, 30));

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
        jPanel4.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 130, 180, 30));

        rbtn.setBorder(null);
        buttonGroup1.add(rbtn);
        rbtn.setSelected(true);
        rbtn.setText("No Valorizado");
        jPanel4.add(rbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 150, -1, -1));

        rbtv.setBorder(null);
        buttonGroup1.add(rbtv);
        rbtv.setText("Valorizado");
        jPanel4.add(rbtv, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 150, -1, -1));

        cboProcedencia.setBackground(new java.awt.Color(255, 255, 255));
        cboProcedencia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboProcedencia.setForeground(new java.awt.Color(65, 94, 255));
        cboProcedencia.setAccent(new java.awt.Color(65, 94, 255));
        jPanel4.add(cboProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 180, 40));

        btnProcedencia.setBackground(new java.awt.Color(255, 255, 255));
        btnProcedencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        btnProcedencia.setColorHover(new java.awt.Color(255, 187, 51));
        btnProcedencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcedenciaActionPerformed(evt);
            }
        });
        jPanel4.add(btnProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, 30, 30));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 40, 40));

        txtTms.setForeground(new java.awt.Color(0, 0, 204));
        txtTms.setAccent(new java.awt.Color(204, 0, 51));
        txtTms.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTms.setLabel("T.M.S");
        txtTms.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTmsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTmsKeyTyped(evt);
            }
        });
        jPanel4.add(txtTms, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 140, 60));

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
        jPanel4.add(btnCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 210, 180, 30));

        txt_proteccion.setForeground(new java.awt.Color(0, 0, 204));
        txt_proteccion.setText("60");
        txt_proteccion.setAccent(new java.awt.Color(204, 0, 51));
        txt_proteccion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_proteccion.setLabel("Protecciòn");
        txt_proteccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_proteccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_proteccionKeyTyped(evt);
            }
        });
        jPanel4.add(txt_proteccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 90, 60));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factor.png"))); // NOI18N
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 40, 30));

        btnImprimir1.setBackground(new java.awt.Color(65, 94, 255));
        btnImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Imprimir.png"))); // NOI18N
        btnImprimir1.setText("VALORIZACIÓN");
        btnImprimir1.setColorHover(new java.awt.Color(253, 173, 1));
        btnImprimir1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir1.setIconTextGap(0);
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnImprimir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 170, 180, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1554, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
//        listaLiquidacion = gestionarLiquidacionServicio.mostrarLiquidacions(Liquidacion.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnGuardarActionPerformed
    int fila;
    private void tableLiquidacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLiquidacionMousePressed
        btnEliminar.setEnabled(true);
        Liquidacion liquidacion = null;
        try {
            tipo_accion = ACCION_MODIFICAR;
            btnGuardar.setEnabled(true);
            btnImprimir.setEnabled(true);
//            btnActualizar.setEnabled(true);
            fila = tableLiquidacion.getSelectedRow();
            String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
            liquidacion = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
            llenarCamposParaModificar(liquidacion);
            
            inabilitarCampos(true);
            
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_tableLiquidacionMousePressed

    private void tableLiquidacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyReleased
        fila = tableLiquidacion.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableLiquidacionKeyReleased

    private void tableLiquidacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                if (TIPO_USUARIO == TIPO_FACTURA) {
                    obtenerLiquidacion();
                }
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
        

    }//GEN-LAST:event_tableLiquidacionKeyTyped

    private void btnClienteEntranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteEntranteActionPerformed
        
        FormGestionarClienteEntrante datosLiquidacion = new FormGestionarClienteEntrante(null, true, FormGestionarClienteEntrante.TIPO_LIQUIDACION);
        datosLiquidacion.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
        }
        

    }//GEN-LAST:event_btnClienteEntranteActionPerformed

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

    private void txtBuscarLiquidacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarLiquidacionKeyPressed
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
    }//GEN-LAST:event_txtBuscarLiquidacionKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            
            tipo_accion = ACCION_CREAR;
            limpiarCampos();
            llenarDatosDeCeros();
            dateFecha.setDatoFecha(new Date());
//            btnActualizar.setEnabled(false);
            btnImprimir.setEnabled(false);
            btnGuardar.setEnabled(true);
            llenarDatosAgregar();
            inabilitarCampos(true);
            clienteEntranteSeleccionado = gestionarLiquidacionServicio.obtenerClientePorDefecto(1);
            
            if (clienteEntranteSeleccionado != null) {
                txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
            }
            gestionarLiquidacionServicio.llenarCamposNuevo(dateFecha, txtH2O, txtLeyAg, txtInter, txtMaquilla, txtConscn);
            txtLote.selectAll();
            txtLote.requestFocus();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        inabilitarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarLiquidacionReporteServicio.mostrarLiquidacion(liquidacionSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(FormGestionarLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTmh.requestFocus();
            txtTmh.selectAll();
        }
    }//GEN-LAST:event_txtLoteKeyPressed

    private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
        existeLote();
        

    }//GEN-LAST:event_txtLoteKeyReleased

    private void txtTmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txtH2O.requestFocus();
                txtH2O.selectAll();
            }
        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, "Este campo No Puede Estar Vacio");
        }
        

    }//GEN-LAST:event_txtTmhKeyPressed

    private void txtTmhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTmh.getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_txtTmhKeyTyped
    double totaltms;
    private void txtH2OKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtH2OKeyPressed
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                double thm = Double.parseDouble(this.txtTmh.getText());
                double ph2o = Double.parseDouble(this.txtH2O.getText());
                totaltms = +(thm - (thm * (ph2o / 100)));
                this.txtTms.setText(redondearDecimales(totaltms, 3) + "");
                txtTms.requestFocus();
                txtTms.selectAll();
            }
        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, "Calculo no Realizado por que el Campo: " + txtH2O.getLabel() + " Este Vacio, O es Posible que el Campo: " + txtTmh.getLabel() + " Este Vacio");
        }

    }//GEN-LAST:event_txtH2OKeyPressed

    private void txtH2OKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtH2OKeyReleased
        try {
            double thm = Double.parseDouble(this.txtTmh.getText());
            double ph2o = Double.parseDouble(this.txtH2O.getText());
            totaltms = +(thm - (thm * (ph2o / 100)));
            this.txtTms.setText(redondearDecimales(totaltms, 3) + "");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtH2OKeyReleased

    private void txtH2OKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtH2OKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtH2O.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtH2OKeyTyped

    private void txtTmsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLeyAu.requestFocus();
            txtLeyAu.selectAll();
        }
    }//GEN-LAST:event_txtTmsKeyPressed

    private void txtTmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmsKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTms.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTmsKeyTyped

    private void txtLeyAuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeyAuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLeyAg.requestFocus();
            txtLeyAg.selectAll();
            
        }
    }//GEN-LAST:event_txtLeyAuKeyPressed

    private void txtLeyAuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeyAuKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtLeyAu.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtLeyAuKeyTyped

    private void txtLeyAgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeyAgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtInter.requestFocus();
            txtInter.selectAll();
        }
    }//GEN-LAST:event_txtLeyAgKeyPressed

    private void txtLeyAgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeyAgKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtLeyAg.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtLeyAgKeyTyped

    private void txtInterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtRec.requestFocus();
            txtRec.selectAll();
        }
    }//GEN-LAST:event_txtInterKeyPressed

    private void txtInterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInterKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtInter.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtInterKeyTyped

    private void txtRecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMaquilla.requestFocus();
            txtMaquilla.selectAll();
        }
    }//GEN-LAST:event_txtRecKeyPressed

    private void txtRecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtRec.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRecKeyTyped

    private void txtMaquillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaquillaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFactor.requestFocus();
            txtFactor.selectAll();
        }
    }//GEN-LAST:event_txtMaquillaKeyPressed

    private void txtMaquillaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaquillaKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtMaquilla.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaquillaKeyTyped

    private void txtFactorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFactorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtConscn.requestFocus();
            txtConscn.selectAll();
        }
    }//GEN-LAST:event_txtFactorKeyPressed

    private void txtFactorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFactorKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtFactor.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFactorKeyTyped

    private void txtConscnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConscnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEscalador.requestFocus();
            txtEscalador.selectAll();
        }
    }//GEN-LAST:event_txtConscnKeyPressed

    private void txtConscnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConscnKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtConscn.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtConscnKeyTyped

    private void txtEscaladorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEscaladorKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String protec = txt_proteccion.getText().toString();
            Double protexxion = 0.0;
            if (protec == "") {
                txt_proteccion.setText("0");
            } else {
                protexxion = Double.parseDouble(protec);
            }
            double thm = Double.parseDouble(this.txtTmh.getText());
            double ph2o = Double.parseDouble(this.txtH2O.getText());
            totaltms = +(thm - (thm * (ph2o / 100)));
            
            double leyau = Double.parseDouble(this.txtLeyAu.getText());
            double inter = Double.parseDouble(this.txtInter.getText());
            double rec = Double.parseDouble(this.txtRec.getText());
            double maquilla = Double.parseDouble(this.txtMaquilla.getText());
            double factor = Double.parseDouble(this.txtFactor.getText());
            double conscon = Double.parseDouble(this.txtConscn.getText());
            double total = +(((((leyau * (rec / 100))) * (inter - protexxion)) - maquilla - conscon) * factor);
            this.txt$tms.setText(redondearDecimales(total, 2) + "");
            
            double totalu = +totaltms * total;
            this.txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
            txt$tms.requestFocus();
            txt$tms.selectAll();
        }
    }//GEN-LAST:event_txtEscaladorKeyPressed

    private void txtEscaladorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEscaladorKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtEscalador.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEscaladorKeyTyped

    private void txt$tmsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt$tmsKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTotalUS.requestFocus();
            txtTotalUS.selectAll();
        }
    }//GEN-LAST:event_txt$tmsKeyPressed

    private void txt$tmsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt$tmsKeyReleased
        try {
            String protec = txt_proteccion.getText().toString();
            Double proteccion = 0.0;
            if (protec == "") {
                txt_proteccion.setText("0");
            } else {
                proteccion = Double.parseDouble(protec);
            }
            double thm = Double.parseDouble(this.txtTmh.getText());
            double ph2o = Double.parseDouble(this.txtH2O.getText());
            totaltms = +(thm - (thm * (ph2o / 100)));
            double leyau = Double.parseDouble(this.txtLeyAu.getText());
            double inter = Double.parseDouble(this.txtInter.getText());
            double rec = Double.parseDouble(this.txtRec.getText());
            double maquilla = Double.parseDouble(this.txtMaquilla.getText());
            double factor = Double.parseDouble(this.txtFactor.getText());
            double conscon = Double.parseDouble(this.txtConscn.getText());
            double total = +(((((leyau * (rec / 100))) * (inter - proteccion)) - maquilla - conscon) * factor);
            double t = Double.parseDouble(txt$tms.getText());
            double totalu = +totaltms * t;
            this.txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, "Este campo No Puede Estar Vacio");
        }
        

    }//GEN-LAST:event_txt$tmsKeyReleased

    private void txt$tmsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt$tmsKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txt$tms.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txt$tmsKeyTyped

    private void txtTotalUSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalUSKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtTotalUSKeyPressed

    private void txtTotalUSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalUSKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTotalUS.getText().contains(".")) {
            evt.consume();
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            guardarDatos();
        }
    }//GEN-LAST:event_txtTotalUSKeyTyped

    private void tableLiquidacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyPressed
        fila = tableLiquidacion.getSelectedRow();
        
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            if (TIPO_USUARIO == TIPO_VALORIZACION) {
                this.dispose();
            }
        }
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

    }//GEN-LAST:event_tableLiquidacionKeyPressed

    private void btnProcedenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcedenciaActionPerformed
        FormDatosProcedencia t = new FormDatosProcedencia(null, true);
        t.setVisible(true);
        llenarCBOProcedencia();
    }//GEN-LAST:event_btnProcedenciaActionPerformed

    private void txtBuscarLiquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarLiquidacionActionPerformed
        try {
            String texto = txtBuscarLiquidacion.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarLiquidacionServicio.mostrarLiquidacions(1, tableLiquidacion, texto);
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarLiquidacionActionPerformed

    private void txtClienteEntranteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteEntranteKeyPressed
        txtClienteEntrante.setText(txtClienteEntrante.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteEntranteKeyPressed

    private void txtTmhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyReleased

    }//GEN-LAST:event_txtTmhKeyReleased

    private void btnReintegroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReintegroActionPerformed

//        FormGestionarReintegro formGestionarReintegro=new FormGestionarReintegro(this, true, liquidacionSeleccionado);
//      formGestionarReintegro.setVisible(true);
        int filamodificar = tableLiquidacion.getSelectedRow();
        String estado = tableLiquidacion.getModel().getValueAt(filamodificar, 17).toString();
        if (estado.equals("N")) {
            Mensaje.mostrarAdvertencia(this, "Lote No Valorizado.!");
        } else {
            FormGestionarReintegro a = new FormGestionarReintegro(null, true, FormGestionarReintegro.TIPO_LIQUIDACION, liquidacionSeleccionado);
            a.setVisible(true);
            inicializarTabla();
        }
        

    }//GEN-LAST:event_btnReintegroActionPerformed

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        ChequeCliente cheque = new ChequeCliente();
        cheque.setClienteEntrante(clienteEntranteSeleccionado);
        cheque.setEntregadoA(clienteEntranteSeleccionado.generarNombre());
        cheque.setConcepto("POR LIQUIDACION ");
        cheque.setMoneda("$");
        cheque.setMonto(Double.parseDouble(txtTotalUS.getText().toString()));
        FormGestionarChequeClienteServicio a = new FormGestionarChequeClienteServicio(null, true);
        
        int estadoPreview = 0;
        
        a.llenarCamposTexto(cheque);
        a.inabilitarCampos(true);
        a.inabilitarBotones(true);
        a.tipo_accion = a.ACCION_AGREGAR;
        a.txtConcepto.selectAll();
        a.txtConcepto.requestFocus();
        a.setVisible(true);

    }//GEN-LAST:event_btnChequeActionPerformed

    private void txt_proteccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_proteccionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtEscalador.requestFocus();
            txtEscalador.selectAll();
        }
    }//GEN-LAST:event_txt_proteccionKeyPressed

    private void txt_proteccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_proteccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_proteccionKeyTyped

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        FormGestionarValorizacion formGestionarValorizacion = new FormGestionarValorizacion(null, true);
        formGestionarValorizacion.setVisible(true);
    }//GEN-LAST:event_btnImprimir1ActionPerformed
    
    private void obtenerLiquidacionSeleccionado() {
        try {
            String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
    
    private boolean verificarCamposVacios() {
        int contador = 0;
        int aux = 0;
        
        contador = Verificador.verificadorCampos(txtClienteEntrante);
        aux = aux + contador;
        
        contador = Verificador.verificadorCamposFechas(dateFecha);
        aux = aux + contador;
        
        return aux == 2;
    }
    
    private void inabilitarBotones(Boolean v) {
        
        btnClienteEntrante.setEnabled(v);
        btnEliminar.setEnabled(!v);
        
    }
    
    void llenarDatosAgregar() {
        txtInter.setText("1210.90");
        txtRec.setText("90");
        txtMaquilla.setText("240");
        txtFactor.setText("1.1023");
        txtConscn.setText("20.00");
        txtEscalador.setText("0");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnCheque;
    private rojeru_san.RSButton btnClienteEntrante;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnImprimir1;
    private rojeru_san.RSButton btnNuevo;
    private rojerusan.RSButtonIconD btnProcedencia;
    private rojeru_san.RSButton btnReintegro;
    private javax.swing.ButtonGroup buttonGroup1;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<Procedencia> cboProcedencia;
    private rojeru_san.componentes.RSDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblPersonal3;
    private javax.swing.JLabel lblPersonal4;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage14;
    private rojerusan.RSLabelImage rSLabelImage15;
    private rojerusan.RSLabelImage rSLabelImage16;
    private rojerusan.RSLabelImage rSLabelImage17;
    private rojerusan.RSLabelImage rSLabelImage18;
    private rojerusan.RSLabelImage rSLabelImage19;
    private rojerusan.RSLabelImage rSLabelImage20;
    private rojerusan.RSLabelImage rSLabelImage21;
    private rojerusan.RSLabelImage rSLabelImage22;
    private rojerusan.RSLabelImage rSLabelImage23;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private com.icm.components.metro.RadioButtonMetroICM rbtn;
    private com.icm.components.metro.RadioButtonMetroICM rbtv;
    private rojerusan.RSTableMetro tableLiquidacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt$tms;
    private org.jdesktop.swingx.JXSearchField txtBuscarLiquidacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtClienteEntrante;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigo;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtConscn;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtEscalador;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtFactor;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtH2O;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtInter;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtLeyAg;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtLeyAu;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtLote;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMaquilla;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRec;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTmh;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTms;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalUS;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_proteccion;
    // End of variables declaration//GEN-END:variables

    private void obtenerLiquidacion() {
        String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
        
        try {
            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
            if (TIPO_USUARIO == TIPO_FACTURA) {
                FormRegistroFactura.liquidacionSeleccionada = liquidacionSeleccionado;
                this.dispose();
            } else {
                inabilitarBotones(false);
            }
            
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
        
    }
    
    private void metodoParaEliminar() {
        
        obtenerLiquidacionSeleccionado();
        if (liquidacionSeleccionado != null) {
            if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                return;
            }
            try {
                int registros_afectados = gestionarLiquidacionServicio.eliminarLiquidacion(liquidacionSeleccionado);
                if (registros_afectados == 1) {
//                    Mensaje.mostrarAfirmacionDeEliminacion(this);
                    DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar una liquidaciòn", DesktopNotify.SUCCESS);
                    
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
