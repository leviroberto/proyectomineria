package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.FCMaterialTextField;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionServicio;
import SistemaLara.capa2_aplicacion.GestionarReintegroServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Liquidacion;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa3_dominio.Reintegro;
import com.sun.glass.events.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarLiquidacionServicio;
//import motorepuestos.capa3_dominio.Liquidacion;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarReintegro extends javax.swing.JDialog {

    private GestionarLiquidacionServicio gestionarLiquidacionServicio;
    public static Liquidacion liquidacionSeleccionado;
    public static Reintegro reintegroSeleccionado;

    public static ClienteEntrante clienteEntranteSeleccionado;
    public static Procedencia procedenciaSeleccionado;
    public static int TIPO_LIQUIDACION = 2;
    public static int TIPO_NOTA_DEBITO = 4;

    public static int TIPO_TRABAJADOR = 1;
    public static int TIPO_VALORIZACION = 3;
    private GestionarLiquidacionReporteServicio gestionarLiquidacionReporteServicio;
    private GestionarReintegroServicio gestionarReintegroServicio;
    JasperPrint print;
    private int TIPO_USUARIO;
    private int tipo_accion;
    DefaultTableModel modelo;
    private final int ACCION_CREAR = 1;
    private final int ACCION_MODIFICAR = 2;
    private int codigoLiquidacion;

    public FormGestionarReintegro(java.awt.Frame parent, boolean modal, int tipo) {
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
            gestionarReintegroServicio = new GestionarReintegroServicio();
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
            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
        }
    }

    public FormGestionarReintegro(java.awt.Frame parent, boolean modal, int tipo, Liquidacion liquidacion) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            popMenu.add(pnlMenu);
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            this.TIPO_USUARIO = tipo;
            tipo_accion = ACCION_CREAR;
            liquidacionSeleccionado = new Liquidacion();
            liquidacionSeleccionado = liquidacion;
            gestionarLiquidacionServicio = new GestionarLiquidacionServicio();
            gestionarLiquidacionReporteServicio = new GestionarLiquidacionReporteServicio();
            gestionarReintegroServicio = new GestionarReintegroServicio();
            llenarCBOProcedencia();
            inicializarTablaColumnas();
            inicializarTabla();
            //obtenerDatosTabla();
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
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("CLIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("PROCE", "java.lang.String"));
        tabla.agregarColumna(new Columna("LOTE", "java.lang.Double"));
        tabla.agregarColumna(new Columna("TMH", "java.lang.Double"));
        tabla.agregarColumna(new Columna("H2O", "java.lang.Double"));
        tabla.agregarColumna(new Columna("LEYAU", "java.lang.Double"));
        tabla.agregarColumna(new Columna("LEYAG", "java.lang.Double"));
        tabla.agregarColumna(new Columna("INTER", "java.lang.Double"));
        tabla.agregarColumna(new Columna("REC", "java.lang.Double"));
        tabla.agregarColumna(new Columna("MAQUILLA", "java.lang.Double"));
        tabla.agregarColumna(new Columna("FACTOR", "java.lang.Double"));
        tabla.agregarColumna(new Columna("CONSCON", "java.lang.Double"));
        tabla.agregarColumna(new Columna("ESCALADOR", "java.lang.Double"));
        tabla.agregarColumna(new Columna("STMS", "java.lang.Double"));
        tabla.agregarColumna(new Columna("TOTALUS", "java.lang.Double"));
        tabla.agregarColumna(new Columna("REINTEGRO", "java.lang.String"));
        tabla.agregarColumna(new Columna("ES. PAGO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableLiquidacion.setModel(modeloTabla);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMinWidth(0);
        tableLiquidacion.getColumn(tableLiquidacion.getColumnName(0)).setMaxWidth(0);

        TableColumnModel columnModel = tableLiquidacion.getColumnModel();

//        columnModel.getColumn(1).setPreferredWidth(20);
//        columnModel.getColumn(2).setPreferredWidth(20);
//        columnModel.getColumn(3).setPreferredWidth(20);
//        columnModel.getColumn(4).setPreferredWidth(20);
//        columnModel.getColumn(6).setPreferredWidth(20);
//        columnModel.getColumn(7).setPreferredWidth(20);
//        columnModel.getColumn(8).setPreferredWidth(20);
//        columnModel.getColumn(9).setPreferredWidth(20);
//        columnModel.getColumn(10).setPreferredWidth(30);
//        columnModel.getColumn(11).setPreferredWidth(30);
//        columnModel.getColumn(12).setPreferredWidth(30);
//        columnModel.getColumn(13).setPreferredWidth(40);
//        columnModel.getColumn(18).setPreferredWidth(150);
        tableLiquidacion.setColumnModel(columnModel);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableLiquidacion.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {

            limpiarTabla();
            if (tipo_accion == TIPO_TRABAJADOR) {
                gestionarReintegroServicio.mostrarReintegrosPorLiquidacion(liquidacionSeleccionado.getCodigo(), tableLiquidacion);
            } else {
                gestionarReintegroServicio.mostrarReintegros(tableLiquidacion);
            }

            inabilitarCampos(false);
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void limpiarCampos() {
        txt$tms.setText("");
        rbtReintegroSi.setSelected(true);
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
        txtTotalUS.setText(liqui.getTotalus());
        txtPreLiquidacion.setText(liqui.getTotalus().toString());
        txtLeyAu.requestFocus();
        txtLeyAu.selectAll();
//        if (liqui.getEstadoLiquidacion().equals("N")) {
//            rbtn.setSelected(true);
//            rbtv.setSelected(false);
//        } else if (liqui.getEstadoLiquidacion().equals("V")) {
//            rbtn.setSelected(false);
//            rbtv.setSelected(true);
//        }
        for (int i = 0; i < cboProcedencia.getItemCount(); i++) {
            if (liqui.getProcedencia().getDescripcion().equals(cboProcedencia.getItemAt(i).getDescripcion())) {
                cboProcedencia.setSelectedIndex(i);
            }
        }

        liquidacionSeleccionado = liqui;
    }

    private void guardarDatos() {
        Reintegro reintegro = new Reintegro();
        int registros_afectados;
        Estado estado = new Estado();
        if (verificarCamposVacios()) {
            Date fecha = dateFecha.getDatoFecha();
            reintegro.setFecha(new java.sql.Date(fecha.getTime()));
            reintegro.setClienteEntrante(clienteEntranteSeleccionado);
            procedenciaSeleccionado = cboProcedencia.getItemAt(cboProcedencia.getSelectedIndex());
            reintegro.setProcedencia(procedenciaSeleccionado);
            reintegro.setLote(txtLote.getText());
            reintegro.setTmh(Double.parseDouble(txtTmh.getText()));
            reintegro.setH2o(Double.parseDouble(txtH2O.getText()));
            reintegro.setTms(Double.parseDouble(txtTms.getText()));
            reintegro.setLeyau(Double.parseDouble(txtLeyAu.getText()));
            reintegro.setLeyag(Double.parseDouble(txtLeyAg.getText()));
            reintegro.setInter(Double.parseDouble(txtInter.getText()));
            reintegro.setRec(Double.parseDouble(txtRec.getText()));
            reintegro.setMaquilla(Double.parseDouble(txtMaquilla.getText()));
            reintegro.setFactor(Double.parseDouble(txtFactor.getText()));
            reintegro.setConscon(Double.parseDouble(txtConscn.getText()));
            reintegro.setEscalador(Double.parseDouble(txtEscalador.getText()));
            reintegro.setStms(Double.parseDouble(txt$tms.getText()));
            reintegro.setTotalUs(Double.parseDouble(txtTotalUS.getText()));

            if (rbtReintegroSi.isSelected()) {
                estado.setCodigo(14);
            } else {
                estado.setCodigo(15);
            }
            reintegro.setEstado(estado);
            reintegro.setReintrego(Double.parseDouble((txtReintegro.getText())));

            if (rbtNoPagado.isSelected()) {
                reintegro.setPago(rbtNoPagado.getText().toString());
            } else {
                reintegro.setPago(rbtPagado.getText().toString());

            }

            if (tipo_accion == ACCION_CREAR) {
                if (!estaAgregadoLote()) {
                    try {

                        try {
                            reintegro.setLiquidacion(liquidacionSeleccionado);
                            registros_afectados = gestionarReintegroServicio.guardarReintegro(reintegro);
                            if (registros_afectados == 1) {
                                Mensaje.mostrarAfirmacionDeCreacion(this);
                                DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Crear Una Nueva Liquidacion", DesktopNotify.SUCCESS);
                                inicializarTabla();

                            } else if (registros_afectados == 0) {
                                Mensaje.mostrarAdvertenciaDeCreacion(this);
                            }

                        } catch (Exception e) {
                            Mensaje.mostrarErrorDeCreacion(this);
                        }

                    } catch (Exception e) {
                        Mensaje.mostrarErrorSistema(this);
                    }
                } else {
                    Mensaje.mostrarMensajeDefinido(this, "Ya esta agregado el lote " + txtLote.getText().toString());
                }

            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    reintegro.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    registros_afectados = gestionarReintegroServicio.modificarReintegro(reintegro);
                    if (registros_afectados == 1) {
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar la  Liquidacion Con Numero de Lote " + txtLote.getText(), DesktopNotify.INPUT_REQUEST);
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();

                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de Modificar un Liquidacion", DesktopNotify.INPUT_REQUEST);

                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);
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
                    txtLote.requestFocus();
                    txtLote.selectAll();
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
        popMenu = new javax.swing.JPopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableLiquidacion = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        btnClienteEntrante = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        lblPersonal4 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        rSLabelImage11 = new rojerusan.RSLabelImage();
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
        btnActualizar = new rojeru_san.RSButton();
        rSLabelImage22 = new rojerusan.RSLabelImage();
        txtTms = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
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
        btnCancelar = new rojeru_san.RSButton();
        btnImprimir = new rojeru_san.RSButton();
        rbtReintegroSi = new com.icm.components.metro.RadioButtonMetroICM();
        rbtReintegroNo = new com.icm.components.metro.RadioButtonMetroICM();
        cboProcedencia = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        btnProcedencia = new rojerusan.RSButtonIconD();
        rbtPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rbtNoPagado = new com.icm.components.metro.RadioButtonMetroICM();
        rSLabelImage23 = new rojerusan.RSLabelImage();
        txtPreLiquidacion = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage24 = new rojerusan.RSLabelImage();
        txtReintegro = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        rSLabelImage25 = new rojerusan.RSLabelImage();
        jLabel3 = new javax.swing.JLabel();

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
        jLabel1.setText("GESTIONAR DATOS DE REINTEGRO ");

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
                .addGap(236, 236, 236)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        tableLiquidacion.setFuenteFilas(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        tableLiquidacion.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        tableLiquidacion.setFuenteHead(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
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
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClienteEntrante.setBackground(new java.awt.Color(65, 94, 255));
        btnClienteEntrante.setText("...");
        btnClienteEntrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEntranteActionPerformed(evt);
            }
        });
        jPanel4.add(btnClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 30, 30));

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
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 50, 140, 30));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, 40));
        jPanel4.add(lblPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 200, 10));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Lote.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, 40));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Ley.png"))); // NOI18N
        jPanel4.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 40, 40));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Ley.png"))); // NOI18N
        jPanel4.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 40, 40));

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 40, 40));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 40, 40));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factor.png"))); // NOI18N
        jPanel4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 40, 30));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Maquila.png"))); // NOI18N
        jPanel4.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 40, 30));

        rSLabelImage14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 40, 30));

        rSLabelImage15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoCliente.png"))); // NOI18N
        jPanel4.add(rSLabelImage15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 40, 30));

        dateFecha.setColorBackground(new java.awt.Color(65, 94, 255));
        dateFecha.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dateFecha.setColorForeground(new java.awt.Color(65, 94, 255));
        dateFecha.setFormatoFecha("yyyy-MM-dd");
        dateFecha.setPlaceholder("FECHA ");
        jPanel4.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 140, 30));

        rSLabelImage16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 40, 30));

        rSLabelImage17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 40, 30));

        rSLabelImage18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Descripcion.png"))); // NOI18N
        jPanel4.add(rSLabelImage18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 40, 30));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Mineral.png"))); // NOI18N
        jPanel4.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 40, 30));

        rSLabelImage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 160, 40, 40));

        btnActualizar.setBackground(new java.awt.Color(65, 94, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ActulizarNuevo.png"))); // NOI18N
        btnActualizar.setText("ACTULIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 90, 140, 30));

        rSLabelImage22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage22, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 40, 30));

        txtTms.setForeground(new java.awt.Color(204, 0, 0));
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
        jPanel4.add(txtTms, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 140, 60));

        txtCodigo.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigo.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigo.setCaretColor(new java.awt.Color(0, 0, 204));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigo.setLabel("Codigo");
        jPanel4.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 60));

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
        jPanel4.add(txtH2O, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 120, 60));

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
        jPanel4.add(txtTmh, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 120, 70));

        txtLeyAu.setForeground(new java.awt.Color(0, 0, 204));
        txtLeyAu.setAccent(new java.awt.Color(204, 0, 51));
        txtLeyAu.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtLeyAu.setLabel("Ley Au");
        txtLeyAu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLeyAuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLeyAuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeyAuKeyTyped(evt);
            }
        });
        jPanel4.add(txtLeyAu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 140, 60));

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
        jPanel4.add(txtLeyAg, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 140, 60));

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
        jPanel4.add(txtLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 100, 60));

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
        jPanel4.add(txtInter, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 150, 60));

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
        jPanel4.add(txtRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 150, 70));

        txtMaquilla.setForeground(new java.awt.Color(0, 0, 204));
        txtMaquilla.setAccent(new java.awt.Color(204, 0, 51));
        txtMaquilla.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtMaquilla.setLabel("Maquilla");
        txtMaquilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaquillaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaquillaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaquillaKeyTyped(evt);
            }
        });
        jPanel4.add(txtMaquilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 150, 60));

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
        jPanel4.add(txtFactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 150, 60));

        txtEscalador.setForeground(new java.awt.Color(0, 0, 204));
        txtEscalador.setAccent(new java.awt.Color(204, 0, 51));
        txtEscalador.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtEscalador.setLabel("Escalador");
        txtEscalador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEscaladorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEscaladorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEscaladorKeyTyped(evt);
            }
        });
        jPanel4.add(txtEscalador, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 180, 60));

        txt$tms.setForeground(new java.awt.Color(204, 0, 0));
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
        jPanel4.add(txt$tms, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 180, 60));

        txtTotalUS.setForeground(new java.awt.Color(204, 0, 0));
        txtTotalUS.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalUS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalUS.setLabel("Total Us$");
        txtTotalUS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalUSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalUSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalUSKeyTyped(evt);
            }
        });
        jPanel4.add(txtTotalUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 160, 60));

        txtClienteEntrante.setForeground(new java.awt.Color(0, 0, 204));
        txtClienteEntrante.setAccent(new java.awt.Color(204, 0, 51));
        txtClienteEntrante.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtClienteEntrante.setLabel("Cliente Entrante");
        txtClienteEntrante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteEntranteKeyPressed(evt);
            }
        });
        jPanel4.add(txtClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 190, 60));

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
        jPanel4.add(txtConscn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 180, 60));

        rSLabelImage21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Procedencia.png"))); // NOI18N
        jPanel4.add(rSLabelImage21, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 30, 30));

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
        jPanel4.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 130, 140, 30));

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
        jPanel4.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 170, 140, 30));

        rbtReintegroSi.setBorder(null);
        buttonGroup2.add(rbtReintegroSi);
        rbtReintegroSi.setText("Si");
        jPanel4.add(rbtReintegroSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 170, -1, -1));

        rbtReintegroNo.setBorder(null);
        buttonGroup2.add(rbtReintegroNo);
        rbtReintegroNo.setSelected(true);
        rbtReintegroNo.setText("No");
        jPanel4.add(rbtReintegroNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 170, -1, -1));

        cboProcedencia.setBackground(new java.awt.Color(255, 255, 255));
        cboProcedencia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboProcedencia.setForeground(new java.awt.Color(65, 94, 255));
        cboProcedencia.setAccent(new java.awt.Color(65, 94, 255));
        jPanel4.add(cboProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 170, 40));

        btnProcedencia.setBackground(new java.awt.Color(255, 255, 255));
        btnProcedencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        btnProcedencia.setColorHover(new java.awt.Color(255, 187, 51));
        btnProcedencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcedenciaActionPerformed(evt);
            }
        });
        jPanel4.add(btnProcedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 30, 30));

        rbtPagado.setBorder(null);
        buttonGroup1.add(rbtPagado);
        rbtPagado.setText("Pagado");
        jPanel4.add(rbtPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, -1, -1));

        rbtNoPagado.setBorder(null);
        buttonGroup1.add(rbtNoPagado);
        rbtNoPagado.setSelected(true);
        rbtNoPagado.setText("No Pagado");
        jPanel4.add(rbtNoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 110, -1, -1));

        rSLabelImage23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage23, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 40, 30));

        txtPreLiquidacion.setForeground(new java.awt.Color(0, 0, 204));
        txtPreLiquidacion.setAccent(new java.awt.Color(204, 0, 51));
        txtPreLiquidacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPreLiquidacion.setLabel("Pre Liquidacion US$");
        txtPreLiquidacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPreLiquidacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPreLiquidacionKeyTyped(evt);
            }
        });
        jPanel4.add(txtPreLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 160, 60));

        rSLabelImage24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage24, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 40, 30));

        txtReintegro.setForeground(new java.awt.Color(0, 0, 204));
        txtReintegro.setAccent(new java.awt.Color(204, 0, 51));
        txtReintegro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtReintegro.setLabel("Reintegro");
        txtReintegro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReintegroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReintegroKeyTyped(evt);
            }
        });
        jPanel4.add(txtReintegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 160, 60));

        rSLabelImage25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 70, 40, 40));

        jLabel3.setText("Reintegracion?");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 150, -1, -1));

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, Short.MAX_VALUE)
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
        Reintegro reintegro = null;
        try {
            tipo_accion = ACCION_MODIFICAR;
            btnGuardar.setEnabled(true);
            btnImprimir.setEnabled(true);
//            btnActualizar.setEnabled(true);
            fila = tableLiquidacion.getSelectedRow();
            String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
            reintegro = gestionarReintegroServicio.buscarReintegroPorCodigo(Integer.parseInt(codigo));
            llenarCamposParaModificarReintegro(reintegro);
            inabilitarCampos(true);
            calculo();

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
                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        inabilitarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            print = gestionarLiquidacionReporteServicio.mostrarLiquidacion(liquidacionSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(FormGestionarReintegro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTmh.requestFocus();
        }
    }//GEN-LAST:event_txtLoteKeyPressed

    private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
        //


    }//GEN-LAST:event_txtLoteKeyReleased

    private void txtTmhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txtH2O.requestFocus();
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
            calculo();
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
            calculo();
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
            calculo();
            txt$tms.requestFocus();
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
            double thm = Double.parseDouble(this.txtTmh.getText());
            double ph2o = Double.parseDouble(this.txtH2O.getText());
            totaltms = +(thm - (thm * (ph2o / 100)));

            double t = Double.parseDouble(txt$tms.getText());
            double totalu = +totaltms * t;
            this.txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
            reintegro();
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
            txtPreLiquidacion.requestFocus();
            txtPreLiquidacion.selectAll();
        }
    }//GEN-LAST:event_txtTotalUSKeyPressed

    private void txtTotalUSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalUSKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtTotalUS.getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_txtTotalUSKeyTyped

    private void tableLiquidacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLiquidacionKeyPressed
        fila = tableLiquidacion.getSelectedRow();

        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            if (TIPO_USUARIO == TIPO_VALORIZACION) {

                this.dispose();
            } else if (TIPO_USUARIO == TIPO_NOTA_DEBITO) {
                FormRegistroNotaDeDebito.reintegroSeleccionado = reintegroSeleccionado;
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

    private void txtClienteEntranteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteEntranteKeyPressed
        txtClienteEntrante.setText(txtClienteEntrante.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteEntranteKeyPressed

    private void txtTmhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTmhKeyReleased

    }//GEN-LAST:event_txtTmhKeyReleased

    private void txtPreLiquidacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreLiquidacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtReintegro.requestFocus();
            txtReintegro.selectAll();
        }
    }//GEN-LAST:event_txtPreLiquidacionKeyPressed

    private void txtPreLiquidacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreLiquidacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreLiquidacionKeyTyped

    private void txtReintegroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReintegroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarDatos();

        }
    }//GEN-LAST:event_txtReintegroKeyPressed

    private void txtReintegroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReintegroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReintegroKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        guardarDatos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtLeyAuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeyAuKeyReleased

        calculo();
    }//GEN-LAST:event_txtLeyAuKeyReleased

    private void txtMaquillaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaquillaKeyReleased
        calculo();
    }//GEN-LAST:event_txtMaquillaKeyReleased

    private void txtEscaladorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEscaladorKeyReleased
        calculo();
    }//GEN-LAST:event_txtEscaladorKeyReleased

    private void txtTotalUSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalUSKeyReleased
        double thm = Double.parseDouble(this.txtTmh.getText());
        double ph2o = Double.parseDouble(this.txtH2O.getText());
        totaltms = +(thm - (thm * (ph2o / 100)));

        double t = Double.parseDouble(txt$tms.getText());
        double totalu = +totaltms * t;
        this.txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
        reintegro();
    }//GEN-LAST:event_txtTotalUSKeyReleased

    private void obtenerLiquidacionSeleccionado() {
        try {
            String codigo = tableLiquidacion.getValueAt(fila, 0).toString();
            reintegroSeleccionado = gestionarReintegroServicio.buscarReintegroPorCodigo(Integer.parseInt(codigo));
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
        contador = Verificador.verificadorCampos(txtReintegro);
        aux = aux + contador;

        return aux == 3;
    }

    private void validarTextoVacio(FCMaterialTextField texto) {
        if (texto.getText().toString().equals("")) {
            texto.setText("0.00");
        }
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
    private rojeru_san.RSButton btnActualizar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnClienteEntrante;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir;
    private rojerusan.RSButtonIconD btnProcedencia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<Procedencia> cboProcedencia;
    private rojeru_san.componentes.RSDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
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
    private rojerusan.RSLabelImage rSLabelImage24;
    private rojerusan.RSLabelImage rSLabelImage25;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private com.icm.components.metro.RadioButtonMetroICM rbtNoPagado;
    private com.icm.components.metro.RadioButtonMetroICM rbtPagado;
    private com.icm.components.metro.RadioButtonMetroICM rbtReintegroNo;
    private com.icm.components.metro.RadioButtonMetroICM rbtReintegroSi;
    private rojerusan.RSTableMetro tableLiquidacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt$tms;
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
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtPreLiquidacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtRec;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtReintegro;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTmh;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTms;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalUS;
    // End of variables declaration//GEN-END:variables

    private void obtenerLiquidacion() {
        String codigo = tableLiquidacion.getValueAt(fila, 0).toString();

        try {
            liquidacionSeleccionado = gestionarLiquidacionServicio.buscarLiquidacionPorCodigo(Integer.parseInt(codigo));
            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
                FormRegistroFactura.liquidacionSeleccionada = liquidacionSeleccionado;
                this.dispose();
            } else {
                inabilitarBotones(false);
            }

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    void calculo() {

        try {
            double thm = Double.parseDouble(txtTmh.getText());
            double ph2o = Double.parseDouble(txtH2O.getText());
            totaltms = +(thm - (thm * (ph2o / 100)));

            double leyau = Double.parseDouble(txtLeyAu.getText());
            double inter = Double.parseDouble(txtInter.getText());
            double rec = Double.parseDouble(txtRec.getText());
            double maquilla = Double.parseDouble(txtMaquilla.getText());
            double factor = Double.parseDouble(txtFactor.getText());
            double conscon = Double.parseDouble(txtConscn.getText());
            double total = +(((((leyau * (rec / 100))) * (inter - 60)) - maquilla - conscon) * factor);

            txt$tms.setText(redondearDecimales(total, 2) + "");

            double totalu = +totaltms * total;
            txtTotalUS.setText(redondearDecimales(totalu, 2) + "");
//           txt$tms.requestFocus();
//           txt$tms.selectAll();
            reintegro();
        } catch (Exception e) {
        }

    }

    public void reintegro() {
        double postl = Double.parseDouble(txtTotalUS.getText());
        double prel = Double.parseDouble(txtPreLiquidacion.getText());
        double t = postl - prel;
        if (t < 0) {
            txtReintegro.setText("0.00");
        } else {
            txtReintegro.setText(redondearDecimales(t, 2) + "");
        }

    }

    private void metodoParaEliminar() {
        obtenerLiquidacionSeleccionado();
        if (reintegroSeleccionado != null) {
            if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                return;
            }
            try {
                reintegroSeleccionado.setLiquidacion(liquidacionSeleccionado);
                int registros_afectados = gestionarReintegroServicio.eliminarReintegro(reintegroSeleccionado);
                if (registros_afectados == 1) {
                    Mensaje.mostrarAfirmacionDeEliminacion(this);
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

    private boolean estaAgregadoLote() {
        boolean estado = false;
        ModeloTabla modelo = (ModeloTabla) tableLiquidacion.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String loteTable = modelo.getValueAt(i, 4).toString();
            String lote = txtLote.getText().toString();
            if (loteTable.equals(lote)) {
                estado = true;

            }
        }

        return estado;
    }

    private void llenarCamposParaModificarReintegro(Reintegro reint) {
        txtCodigo.setText(String.valueOf(reint.getCodigo()));
        txtLote.setText(reint.getLote());
        txtTmh.setText(reint.getTmh().toString());
        txtH2O.setText(reint.getH2o().toString());
        txtTms.setText(reint.getTms().toString());
        dateFecha.setDatoFecha(reint.getFecha());
        txtLeyAu.setText(reint.getLeyau().toString());
        txtLeyAg.setText(reint.getLeyag().toString());
        txtInter.setText(reint.getInter().toString());
        txtRec.setText(reint.getRec().toString());
        txtMaquilla.setText(reint.getMaquilla().toString());
        txtFactor.setText(reint.getFactor().toString());
        clienteEntranteSeleccionado = reint.getClienteEntrante();
        txtClienteEntrante.setText(clienteEntranteSeleccionado.generarNombre());
        txtConscn.setText(reint.getConscon().toString());
        txtEscalador.setText(reint.getEscalador().toString());
        txt$tms.setText(reint.getStms().toString());
        procedenciaSeleccionado = reint.getProcedencia();
        txtTotalUS.setText(reint.getTotalUs().toString());

//        txtLeyAu.requestFocus();
//        txtLeyAu.selectAll();
        if (reint.getPago().toString().equals("Pagado")) {
            rbtPagado.setSelected(true);
        } else {
            rbtNoPagado.setSelected(false);
        }

        if (reint.getEstado().getCodigo() == 14) {
            rbtReintegroSi.setSelected(true);
        } else {
            rbtReintegroNo.setSelected(true);
        }
        for (int i = 0; i < cboProcedencia.getItemCount(); i++) {
            if (reint.getProcedencia().getDescripcion().equals(cboProcedencia.getItemAt(i).getDescripcion())) {
                cboProcedencia.setSelectedIndex(i);
            }
        }
        reintegroSeleccionado = reint;

    }

    void cargarParaNotaDebito() {

    }

}
