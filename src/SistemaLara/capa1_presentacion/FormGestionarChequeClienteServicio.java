/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Numero_a_Letra;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarChequeClienteServicio;
import SistemaLara.capa2_aplicacion.GestionarChequeReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarChequeProveedorServicio;
import SistemaLara.capa2_aplicacion.GestionarProcedenciaServicio;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ChequeProveedor;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.PagoTransporte;
import SistemaLara.capa3_dominio.Procedencia;
import SistemaLara.capa3_dominio.ProveedorServicio;
import SistemaLara.capa4_persistencia.ChequeReporteDAOMySQL;
import com.sun.glass.events.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JasperPrint;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarProcedenciaServicio;
//import motorepuestos.capa3_dominio.Procedencia;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarChequeClienteServicio extends javax.swing.JDialog {

    private GestionarChequeClienteServicio gestionarChequeServicio;
    public static ClienteEntrante clienteEntranteSeleccionado;

    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_LIQUIDACION = 2;
    public int ACCION_MOIDIFICAR = 2, ACCION_AGREGAR = 3;
    private int TIPO_USUARIO;
    public int tipo_accion;
    private static ChequeCliente chequeSeleccionado;
    public static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private int estado_preview = 0;
    public static int ESTADO_PREVIEW_SI = 10;
    public static int ESTADO_PREVIEW_NO = 15;

    private JasperPrint print = null;
    private GestionarChequeReporteServicio gestionarChequeReporteServicio;

    public FormGestionarChequeClienteServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
//            this.TIPO_USUARIO = tipo;

            gestionarChequeServicio = new GestionarChequeClienteServicio();
            gestionarChequeReporteServicio = new GestionarChequeReporteServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(false);
            inabilitarCampos(false);
            limpiarCampos();
            txtTotalSole.setEnabled(false);
            txtTotalDolar.setEnabled(false);
            popMenu.add(pnlMenu);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    public FormGestionarChequeClienteServicio(java.awt.Frame parent, boolean modal, ChequeCliente cheque) {
        super(parent, modal);
        try {

            initComponents();
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
            tipo_accion = ACCION_AGREGAR;
            gestionarChequeServicio = new GestionarChequeClienteServicio();
            gestionarChequeReporteServicio = new GestionarChequeReporteServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(true);
            popMenu.add(pnlMenu);
            inabilitarCampos(true);

            llenarCamposTexto(cheque);
            txtConcepto.selectAll();
            txtConcepto.requestFocus();
//            verificarPreview(estado_preview);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    public final void BarraDesplzamiento() {
        jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public void imprimir() {
        try {
            chequeSeleccionado = gestionarChequeServicio.buscarChequePorCodigo(gestionarChequeServicio.obtenerUltimoCodigo(1));
            print = gestionarChequeReporteServicio.mostrarCheque(chequeSeleccionado, chequeSeleccionado.getMoneda());
        } catch (Exception e) {
        }
    }

    private void verificarPreview(int preview) {
        try {
            if (preview == ESTADO_PREVIEW_NO) {
                guardarCheque();
                this.dispose();
                chequeSeleccionado = gestionarChequeServicio.buscarChequePorCodigo(gestionarChequeServicio.obtenerUltimoCodigo(1));
                print = gestionarChequeReporteServicio.mostrarCheque(chequeSeleccionado, chequeSeleccionado.getMoneda());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void llenarCamposTexto(ChequeCliente cheque) {
        try {
            if (String.valueOf(cheque.getCodigo()) != null) {
                txtCodigo.setText("" + cheque.getCodigo());
            }
            txtProveedorEntrante.setText(cheque.getClienteEntrante().generarNombre());
            txtEntregadoA.setText(cheque.getEntregadoA());
            txtMonto.setText("" + cheque.getMonto());
            txtConcepto.setText(cheque.getConcepto());
            txtLectura.setText(cheque.getLectura());
            if (cheque.getFechaEmision() != null) {
                Date fecaEmion = format.parse(cheque.getFechaEmision());
                dataEmision.setDatoFecha(fecaEmion);
            }
            if (cheque.getFechaPago() != null) {
                Date fecaPago = format.parse(cheque.getFechaPago());
                dataPago.setDatoFecha(fecaPago);
            }

            if (cheque.getMoneda().equals("$")) {
                rbDolar.setSelected(true);
            } else {
                rbSoles.setSelected(true);
            }
            clienteEntranteSeleccionado = cheque.getClienteEntrante();
            mostrarCalculos();
            chequeSeleccionado = cheque;
        } catch (Exception e) {
        }

    }

    public void inabilitarCampos(boolean estado) {
        txtCodigo.setEnabled(false);
        txtConcepto.setEnabled(estado);
        txtEntregadoA.setEnabled(estado);
        txtMonto.setEnabled(estado);
        txtProveedorEntrante.setEnabled(estado);
        txtLectura.setEnabled(estado);
        rbDolar.setEnabled(estado);
        rbSoles.setEnabled(estado);
        dataPago.setEnabled(estado);
        dataEmision.setEnabled(estado);

    }

    void limpiarCampos() {
        clienteEntranteSeleccionado = null;
        txtCodigo.setText("");
        txtConcepto.setText("");
        txtEntregadoA.setText("");
        txtMonto.setText("0");
        txtProveedorEntrante.setText("");
        txtLectura.setText("");

        dataPago.setDatoFecha(null);
        dataEmision.setDatoFecha(new Date());
        rbSoles.setSelected(true);
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código", "java.lang.String"));
        tabla.agregarColumna(new Columna("Cliente", "java.lang.String"));
        tabla.agregarColumna(new Columna("Entregado A", "java.lang.String"));
        tabla.agregarColumna(new Columna("Concepto", "java.lang.String"));
        tabla.agregarColumna(new Columna("Monto", "java.lang.String"));

        tabla.agregarColumna(new Columna("Moneda", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha Pago", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha Emsion", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableCheque.setModel(modeloTabla);

        //tableCheque.getColumn(tableCheque.getColumnName(0)).setWidth(0);
        //tableCheque.getColumn(tableCheque.getColumnName(0)).setMinWidth(0);
        // tableCheque.getColumn(tableCheque.getColumnName(0)).setMaxWidth(0);
        TableColumnModel columnModel = tableCheque.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(250);

        columnModel.getColumn(2).setPreferredWidth(250);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(10);
        tableCheque.setColumnModel(columnModel);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableCheque.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarChequeServicio.mostrarCheque(1, tableCheque);
            limpiarCampos();
            calcularMontosTotales();
            inabilitarCampos(false);
            inabilitarBotones(false);
        } catch (Exception e) {
        }

    }

    private void calcularMontosTotales() {
        try {
            gestionarChequeServicio.calcularMontoSoles(1, txtTotalSole);
            gestionarChequeServicio.calcularMontoDolar(1, txtTotalDolar);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel4 = new javax.swing.JPanel();
        txtEntregadoA = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtConcepto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtMonto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtProveedorEntrante = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtLectura = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        dataPago = new rojeru_san.componentes.RSDateChooser();
        dataEmision = new rojeru_san.componentes.RSDateChooser();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        rSPanelShadow2 = new rojeru_san.RSPanelShadow();
        jPanel7 = new javax.swing.JPanel();
        btnCancelar2 = new rojeru_san.RSButton();
        btnGuardar = new rojeru_san.RSButton();
        txtBuscar = new org.jdesktop.swingx.JXSearchField();
        btnImprimir = new rojeru_san.RSButton();
        rSLabelImage19 = new rojerusan.RSLabelImage();
        rbDolar = new com.icm.components.metro.RadioButtonMetroICM();
        rbSoles = new com.icm.components.metro.RadioButtonMetroICM();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        rSLabelImage20 = new rojerusan.RSLabelImage();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableCheque = new rojerusan.RSTableMetro();
        txtTotalDolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txtTotalSole = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();

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
        jLabel1.setText("GESTIONAR DATOS DE CHEQUE POR CLIENTE SERVICIO ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Cheque.png"))); // NOI18N

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
                .addGap(224, 224, 224)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder1);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEntregadoA.setForeground(new java.awt.Color(0, 0, 204));
        txtEntregadoA.setAccent(new java.awt.Color(204, 0, 51));
        txtEntregadoA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtEntregadoA.setLabel("ENTREGADO A");
        txtEntregadoA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntregadoAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntregadoAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntregadoAKeyTyped(evt);
            }
        });
        jPanel4.add(txtEntregadoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 240, 60));

        txtConcepto.setForeground(new java.awt.Color(0, 0, 204));
        txtConcepto.setAccent(new java.awt.Color(204, 0, 51));
        txtConcepto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtConcepto.setLabel("POR CONCEPTO DE ");
        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConceptoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConceptoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConceptoKeyTyped(evt);
            }
        });
        jPanel4.add(txtConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 450, 60));

        txtMonto.setForeground(new java.awt.Color(0, 0, 204));
        txtMonto.setText("0.0");
        txtMonto.setAccent(new java.awt.Color(204, 0, 51));
        txtMonto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtMonto.setLabel("MONTO");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });
        jPanel4.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 110, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("FECHA DE EMISIÓN");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 169, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("FECHA DE ENTREGA PAGO");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, 169, -1));

        txtCodigo.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigo.setAccent(new java.awt.Color(204, 0, 51));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtCodigo.setLabel("CÓDIGO");
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });
        jPanel4.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 130, 60));

        txtProveedorEntrante.setForeground(new java.awt.Color(0, 0, 204));
        txtProveedorEntrante.setAccent(new java.awt.Color(204, 0, 51));
        txtProveedorEntrante.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtProveedorEntrante.setLabel("PARA");
        txtProveedorEntrante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorEntranteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorEntranteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProveedorEntranteKeyTyped(evt);
            }
        });
        jPanel4.add(txtProveedorEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 260, 60));

        txtLectura.setForeground(new java.awt.Color(0, 0, 204));
        txtLectura.setAccent(new java.awt.Color(204, 0, 51));
        txtLectura.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtLectura.setLabel("LA SUMA DE ");
        txtLectura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLecturaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLecturaKeyTyped(evt);
            }
        });
        jPanel4.add(txtLectura, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 600, 60));

        dataPago.setColorBackground(new java.awt.Color(65, 94, 255));
        dataPago.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dataPago.setColorForeground(new java.awt.Color(65, 94, 255));
        dataPago.setFormatoFecha("yyyy-MM-dd");
        dataPago.setPlaceholder("FECHA ");
        jPanel4.add(dataPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 180, 180, 30));

        dataEmision.setColorBackground(new java.awt.Color(65, 94, 255));
        dataEmision.setColorButtonHover(new java.awt.Color(65, 94, 255));
        dataEmision.setColorForeground(new java.awt.Color(65, 94, 255));
        dataEmision.setFormatoFecha("yyyy-MM-dd");
        dataEmision.setPlaceholder("FECHA ");
        jPanel4.add(dataEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 180, 30));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Destinatario.png"))); // NOI18N
        jPanel4.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 40, 40));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Destinatario.png"))); // NOI18N
        jPanel4.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 40, 40));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Codigo.png"))); // NOI18N
        jPanel4.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 40));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ConceptoPro.png"))); // NOI18N
        jPanel4.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 40, 40));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 40, 40));

        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 40, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnCancelar2.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnCancelar2.setText("NUEVO");
        btnCancelar2.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar2.setIconTextGap(0);
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });

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

        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.setToolTipText("BUSCAR");
        txtBuscar.setPrompt("BUSCAR");
        txtBuscar.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscar.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        rSPanelShadow2.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(rSPanelShadow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1040, 60));

        rSLabelImage19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage19, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, 40, 30));

        rbDolar.setBorder(null);
        buttonGroup1.add(rbDolar);
        rbDolar.setForeground(new java.awt.Color(65, 94, 255));
        rbDolar.setText("Dolar");
        rbDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDolarActionPerformed(evt);
            }
        });
        jPanel4.add(rbDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, -1, -1));

        rbSoles.setBorder(null);
        buttonGroup1.add(rbSoles);
        rbSoles.setForeground(new java.awt.Color(65, 94, 255));
        rbSoles.setSelected(true);
        rbSoles.setText("Soles");
        rbSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSolesActionPerformed(evt);
            }
        });
        jPanel4.add(rbSoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Importe.png"))); // NOI18N
        jPanel4.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 40, 40));

        rSLabelImage20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Fecha.png"))); // NOI18N
        jPanel4.add(rSLabelImage20, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 40, 30));

        tableCheque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableCheque.setAltoHead(30);
        tableCheque.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableCheque.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableCheque.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableCheque.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableCheque.setComponentPopupMenu(popMenu);
        tableCheque.setGrosorBordeFilas(0);
        tableCheque.setGrosorBordeHead(0);
        tableCheque.setRowHeight(20);
        tableCheque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableChequeMousePressed(evt);
            }
        });
        tableCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableChequeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableChequeKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tableCheque);

        txtTotalDolar.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalDolar.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalDolar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalDolar.setLabel("TOTAL $");
        txtTotalDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalDolarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalDolarKeyTyped(evt);
            }
        });

        txtTotalSole.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalSole.setAccent(new java.awt.Color(204, 0, 51));
        txtTotalSole.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTotalSole.setLabel("TOTAL S/.");
        txtTotalSole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalSoleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalSoleKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTotalDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtTotalSole, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1353, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalSole, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int fila;
    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged
    private void obtenerChequeSeleccionado() {
        try {
            String codigo = tableCheque.getValueAt(fila, 0).toString();
            chequeSeleccionado = gestionarChequeServicio.buscarChequePorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }
    private void txtEntregadoAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregadoAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntregadoAKeyPressed

    private void txtEntregadoAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregadoAKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntregadoAKeyTyped

    private void txtConceptoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConceptoKeyPressed

    private void txtConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConceptoKeyTyped

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoKeyPressed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoKeyTyped

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtProveedorEntranteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorEntranteKeyPressed

    }//GEN-LAST:event_txtProveedorEntranteKeyPressed

    private void txtProveedorEntranteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorEntranteKeyTyped

    }//GEN-LAST:event_txtProveedorEntranteKeyTyped

    private void txtTotalDolarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDolarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDolarKeyPressed

    private void txtTotalDolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDolarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDolarKeyTyped

    private void txtTotalSoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalSoleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSoleKeyPressed

    private void txtTotalSoleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalSoleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSoleKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCheque();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        tipo_accion = ACCION_AGREGAR;
        limpiarCampos();
        inabilitarCampos(true);
        inabilitarBotones(true);
        txtProveedorEntrante.selectAll();
        txtProveedorEntrante.requestFocus();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            String moneda = "";
            if (rbDolar.isSelected()) {
                moneda = "$";
            } else if (rbSoles.isSelected()) {
                moneda = "S/.";
            }
            print = gestionarChequeReporteServicio.mostrarCheque(chequeSeleccionado, moneda);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtLecturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLecturaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardarCheque();
        }
    }//GEN-LAST:event_txtLecturaKeyPressed

    private void txtLecturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLecturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLecturaKeyTyped
    private void tableChequeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableChequeMousePressed
        try {
            tipo_accion = ACCION_MOIDIFICAR;
            fila = tableCheque.getSelectedRow();
            limpiarCampos();
            obtenerChequeSeleccionado();
            llenarCamposTexto(chequeSeleccionado);
            inabilitarCampos(true);
            inabilitarBotones(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_tableChequeMousePressed

    private void tableChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableChequeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableChequeKeyReleased

    private void tableChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableChequeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tableChequeKeyTyped

    private void txtProveedorEntranteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorEntranteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();

            txtProveedorEntrante.setLabel("PARA");
            FormGestionarClienteEntrante a = new FormGestionarClienteEntrante(null, true, FormGestionarClienteEntrante.TIPO_CHEQUECLIENTE);
            a.setVisible(true);
            if (clienteEntranteSeleccionado != null) {
                txtProveedorEntrante.setText(clienteEntranteSeleccionado.generarNombre());
                txtEntregadoA.setText(clienteEntranteSeleccionado.generarNombre());
                txtEntregadoA.requestFocus();
            }

        } else if (clienteEntranteSeleccionado != null) {
            txtProveedorEntrante.setText(clienteEntranteSeleccionado.generarNombre());
            txtProveedorEntrante.setLabel("PARA");

        } else {
            txtProveedorEntrante.setText("");

            txtProveedorEntrante.setLabel("PRECIONE ENTER PARA SELECCIONAR");

        }
    }//GEN-LAST:event_txtProveedorEntranteKeyReleased

    private void txtEntregadoAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregadoAKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtConcepto.requestFocus();
        }

    }//GEN-LAST:event_txtEntregadoAKeyReleased
    private static boolean band() {
        if (Math.random() > .5) {
            return true;
        } else {
            return false;
        }
    }
    private void txtConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtMonto.requestFocus();
        }
    }//GEN-LAST:event_txtConceptoKeyReleased

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtLectura.requestFocus();
            if (txtMonto.getText().equals("")) {
                txtMonto.setText("0.0");
            }
            mostrarCalculos();
        }
    }//GEN-LAST:event_txtMontoKeyReleased

    private void rbDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDolarActionPerformed
        mostrarCalculos();
    }//GEN-LAST:event_rbDolarActionPerformed

    private void rbSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSolesActionPerformed
        mostrarCalculos();        // TODO add your handling code here:
    }//GEN-LAST:event_rbSolesActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            String texto = txtBuscar.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarChequeServicio.mostrarCheque(1, tableCheque, texto);

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tableChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableChequeKeyPressed
        fila = tableCheque.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableChequeKeyPressed
    private static void mostrarCalculos() {
        Numero_a_Letra ntl = new Numero_a_Letra();
        String lectura = ntl.Convertir("" + txtMonto.getText(), band());
        if (rbDolar.isSelected()) {
            txtLectura.setText(lectura + " DOLARES AMERICANOS");
        } else {
            txtLectura.setText(lectura + " NUEVOS SOLES");

        }
    }

    public void inabilitarBotones(Boolean v) {

        btnGuardar.setEnabled(v);
        btnImprimir.setEnabled(v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCancelar2;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnGuardar;
    private rojeru_san.RSButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    public static rojeru_san.componentes.RSDateChooser dataEmision;
    public static rojeru_san.componentes.RSDateChooser dataPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage19;
    private rojerusan.RSLabelImage rSLabelImage20;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojeru_san.RSPanelShadow rSPanelShadow2;
    public static com.icm.components.metro.RadioButtonMetroICM rbDolar;
    public static com.icm.components.metro.RadioButtonMetroICM rbSoles;
    private rojerusan.RSTableMetro tableCheque;
    private org.jdesktop.swingx.JXSearchField txtBuscar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtCodigo;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtConcepto;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtEntregadoA;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtLectura;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtMonto;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtProveedorEntrante;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalDolar;
    public static SistemaLara.capa1_presentacion.util.FCMaterialTextField txtTotalSole;
    // End of variables declaration//GEN-END:variables

    private void obtenerProcedencia() {
//        String codigo = tableCheque.getValueAt(fila, 0).toString();
//
//        try {
//            procedenciaSeleccionado = gestionarProcedenciaServicio.buscarProcedenciaPorCodigo(Integer.parseInt(codigo));
//            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//                FormGestionarLiquidacion.procedenciaSeleccionado = procedenciaSeleccionado;
//                this.dispose();
//            } else {
//                inabilitarBotones(false);
//            }
//
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }

    }

    private boolean verificarCamposVacios() {
        int contadr = 0, aux = 0;
        contadr = Verificador.verificadorCampos(txtProveedorEntrante);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtEntregadoA);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtConcepto);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtMonto);
        aux = contadr + aux;
        contadr = Verificador.verificadorCampos(txtLectura);
        aux = contadr + aux;
        return aux == 5;
    }

    public void guardarCheque() {
        chequeSeleccionado = null;
        chequeSeleccionado = new ChequeCliente();
        if (verificarCamposVacios()) {

            chequeSeleccionado.setClienteEntrante(clienteEntranteSeleccionado);
            chequeSeleccionado.setEntregadoA(txtEntregadoA.getText());
            chequeSeleccionado.setConcepto(txtConcepto.getText());
            chequeSeleccionado.setMonto(Double.parseDouble(txtMonto.getText()));
            if (rbDolar.isSelected()) {
                chequeSeleccionado.setMoneda("$");
            } else {
                chequeSeleccionado.setMoneda("S/.");

            }

            if (dataPago.getDatoFecha() != null) {
                Date fechaPago = dataPago.getDatoFecha();
                chequeSeleccionado.setFechaPago(format.format(fechaPago).toString());
            }
            if (dataEmision.getDatoFecha() != null) {
                Date fechaPago = dataEmision.getDatoFecha();
                chequeSeleccionado.setFechaEmision(format.format(fechaPago).toString());
            }

            chequeSeleccionado.setLectura(txtLectura.getText());
            chequeSeleccionado.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
            chequeSeleccionado.setPersonal(IniciarSesion.getUsuario());

            int registros_afectados;

            if (tipo_accion == ACCION_AGREGAR) {
                try {
                    registros_afectados = gestionarChequeServicio.guardarCheque(chequeSeleccionado);
                    if (registros_afectados == 1) {
                        if (estado_preview == ESTADO_PREVIEW_SI) {
//                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba crear un nuevo cheque de pago", 7);
                            inicializarTabla();
                        } else if (estado_preview == ESTADO_PREVIEW_NO) {
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba crear un nuevo cheque de pago", 7);
                            inicializarTabla();
                        } else {
//                            Mensaje.mostrarAfirmacionDeCreacion(this);
                            DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba crear un nuevo cheque de pago", 7);
                            inicializarTabla();
                        }

                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MOIDIFICAR) {
                try {
                    chequeSeleccionado.setCodigo(Integer.parseInt(txtCodigo.getText().toString()));
                    registros_afectados = gestionarChequeServicio.modificarCheque(chequeSeleccionado);
                    if (registros_afectados == 1) {
//                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba modificar un  cheque de pago", 7);
                        inicializarTabla();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    }

                } catch (Exception e) {

                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }
    }

    private void metodoParaEliminar() {
        try {
            obtenerChequeSeleccionado();
            if (chequeSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = gestionarChequeServicio.eliminarCheque(chequeSeleccionado);
                    if (registros_afectados == 1) {
//                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba de eliminar un  cheque de pago", 7);
                        inicializarTabla();

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
