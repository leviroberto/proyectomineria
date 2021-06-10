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
import SistemaLara.capa2_aplicacion.GestionarFacturaServicio;
import SistemaLara.capa2_aplicacion.GestionarLiquidacionReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarValorizacionReporteServicio;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Factura;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarFacturaServicio;
//import motorepuestos.capa3_dominio.Factura;
/**
 *
 * @author FiveCod Software
 */
public class FormReporteValorizacion extends javax.swing.JDialog {

    private GestionarValorizacionReporteServicio gestionarValorizacionReporteServicio;
    public static Factura facturaSeleccionado;
    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_LIQUIDACION = 2;
    private int TIPO_USUARIO;
    private int mes = 0;
    private int anio;
    public static ClienteEntrante clienteEntranteSeleccionado;

    public FormReporteValorizacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            BarraDesplzamiento();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
//            this.TIPO_USUARIO = tipo;
            facturaSeleccionado = new Factura();
            gestionarValorizacionReporteServicio = new GestionarValorizacionReporteServicio();
            inicializarTablaColumnas();
            inicializarTabla();
            inabilitarBotones(true);
            btnEliminar.setEnabled(false);
            clienteEntranteSeleccionado = null;
            cboTipoSelected.setSelectedIndex(0);
            inicializadores();
            inabilitarTextos();
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void inabilitarTextos() {
        txt_adelanto.setEnabled(false);
        txt_otros_gastos.setEnabled(false);
        txt_tarifa_analisis.setEnabled(false);
        txt_tarifa_porcentaje.setEnabled(false);
        txt_tmh.setEnabled(false);
        txt_totalUS.setEnabled(false);
        txt_total_gastos.setEnabled(false);
        txt_total_pagar.setEnabled(false);
        txt_transp_a_nasca_dolar.setEnabled(false);
        txt_transp_a_trujillo_dolar.setEnabled(false);
        

    }

    private void inicializadores() {
        java.util.Date fecha = new Date();
        dateDe.setDatoFecha(fecha);
        dateHasta.setDatoFecha(Verificador.sumarDiasFecha(fecha, 30));
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL US $", "java.lang.String"));
        tabla.agregarColumna(new Columna("TMH", "java.lang.String"));
        tabla.agregarColumna(new Columna("TRANSP. A TRUJILLO $", "java.lang.String"));
        tabla.agregarColumna(new Columna("TRANSP. A NASCA $", "java.lang.String"));
        tabla.agregarColumna(new Columna("TARIFA ANALISIS", "java.lang.String"));
        tabla.agregarColumna(new Columna("TARIFA PORCENTAJE", "java.lang.String"));
        tabla.agregarColumna(new Columna("ADELANTO", "java.lang.String"));
        tabla.agregarColumna(new Columna("OTROS GASTOS", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL GASTOS", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL PAGAR", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableFactura.setModel(modeloTabla);
        TableColumnModel columnModel = tableFactura.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(30);
        columnModel.getColumn(2).setPreferredWidth(30);
        tableFactura.setColumnModel(columnModel);
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableFactura.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            //   gestionarFacturaServicio.mostrarFacturas(1, tableFactura);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnModificar = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFactura = new rojerusan.RSTableMetro();
        txt_total_gastos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_totalUS = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_tmh = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_transp_a_trujillo_dolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_transp_a_nasca_dolar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_tarifa_analisis = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_tarifa_porcentaje = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_adelanto = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_otros_gastos = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        txt_total_pagar = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_buscar = new rojeru_san.RSButton();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        cboTipoSelected = new FiveCodMaterilalDesignComboBox.MaterialComboBox<String>();
        txt_cliente = new SistemaLara.capa1_presentacion.util.FCMaterialTextField();
        bnt_buscarCliente = new rojerusan.RSButtonIconD();
        lbl_de = new javax.swing.JLabel();
        dateDe = new rojeru_san.componentes.RSDateChooser();
        lbl_guion = new javax.swing.JLabel();
        lbl_hasta = new javax.swing.JLabel();
        dateHasta = new rojeru_san.componentes.RSDateChooser();

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(2);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

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
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("GESTIONAR REPORTE VALORIZACIONES");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factura.png"))); // NOI18N

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 505, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableFactura.setAltoHead(30);
        tableFactura.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableFactura.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableFactura.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableFactura.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableFactura.setComponentPopupMenu(popMenu);
        tableFactura.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableFactura.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableFactura.setGrosorBordeFilas(0);
        tableFactura.setGrosorBordeHead(0);
        tableFactura.setRowHeight(20);
        tableFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableFacturaMousePressed(evt);
            }
        });
        tableFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableFacturaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableFactura);

        txt_total_gastos.setForeground(new java.awt.Color(65, 94, 255));
        txt_total_gastos.setAccent(new java.awt.Color(0, 132, 235));
        txt_total_gastos.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_total_gastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_gastos.setLabel("TOTAL GASTOS");
        txt_total_gastos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_total_gastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_total_gastosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_total_gastosKeyTyped(evt);
            }
        });

        txt_totalUS.setForeground(new java.awt.Color(65, 94, 255));
        txt_totalUS.setAccent(new java.awt.Color(0, 132, 235));
        txt_totalUS.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_totalUS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_totalUS.setLabel("TOTAL US $");
        txt_totalUS.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_totalUS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_totalUSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_totalUSKeyTyped(evt);
            }
        });

        txt_tmh.setForeground(new java.awt.Color(65, 94, 255));
        txt_tmh.setAccent(new java.awt.Color(0, 132, 235));
        txt_tmh.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_tmh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_tmh.setLabel("TMH");
        txt_tmh.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_tmh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tmhKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tmhKeyTyped(evt);
            }
        });

        txt_transp_a_trujillo_dolar.setForeground(new java.awt.Color(65, 94, 255));
        txt_transp_a_trujillo_dolar.setAccent(new java.awt.Color(0, 132, 235));
        txt_transp_a_trujillo_dolar.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_transp_a_trujillo_dolar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_transp_a_trujillo_dolar.setLabel("TRANSP. A TRUJILLO $");
        txt_transp_a_trujillo_dolar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_transp_a_trujillo_dolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_transp_a_trujillo_dolarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_transp_a_trujillo_dolarKeyTyped(evt);
            }
        });

        txt_transp_a_nasca_dolar.setForeground(new java.awt.Color(65, 94, 255));
        txt_transp_a_nasca_dolar.setAccent(new java.awt.Color(0, 132, 235));
        txt_transp_a_nasca_dolar.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_transp_a_nasca_dolar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_transp_a_nasca_dolar.setLabel("TRANSP. A NASCA $");
        txt_transp_a_nasca_dolar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_transp_a_nasca_dolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_transp_a_nasca_dolarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_transp_a_nasca_dolarKeyTyped(evt);
            }
        });

        txt_tarifa_analisis.setForeground(new java.awt.Color(65, 94, 255));
        txt_tarifa_analisis.setAccent(new java.awt.Color(0, 132, 235));
        txt_tarifa_analisis.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_tarifa_analisis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_tarifa_analisis.setLabel("TARIFA ANALISIS");
        txt_tarifa_analisis.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_tarifa_analisis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tarifa_analisisKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tarifa_analisisKeyTyped(evt);
            }
        });

        txt_tarifa_porcentaje.setForeground(new java.awt.Color(65, 94, 255));
        txt_tarifa_porcentaje.setAccent(new java.awt.Color(0, 132, 235));
        txt_tarifa_porcentaje.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_tarifa_porcentaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_tarifa_porcentaje.setLabel("TARIFA PORCENTAJE");
        txt_tarifa_porcentaje.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_tarifa_porcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tarifa_porcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tarifa_porcentajeKeyTyped(evt);
            }
        });

        txt_adelanto.setForeground(new java.awt.Color(65, 94, 255));
        txt_adelanto.setAccent(new java.awt.Color(0, 132, 235));
        txt_adelanto.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_adelanto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_adelanto.setLabel("ADELANTO");
        txt_adelanto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_adelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_adelantoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_adelantoKeyTyped(evt);
            }
        });

        txt_otros_gastos.setForeground(new java.awt.Color(65, 94, 255));
        txt_otros_gastos.setAccent(new java.awt.Color(0, 132, 235));
        txt_otros_gastos.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_otros_gastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_otros_gastos.setLabel("OTROS GASTOS");
        txt_otros_gastos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_otros_gastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_otros_gastosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_otros_gastosKeyTyped(evt);
            }
        });

        txt_total_pagar.setForeground(new java.awt.Color(65, 94, 255));
        txt_total_pagar.setAccent(new java.awt.Color(0, 132, 235));
        txt_total_pagar.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_total_pagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_pagar.setLabel("TOTAL PAGAR");
        txt_total_pagar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_total_pagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_total_pagarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_total_pagarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(txt_tmh, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_transp_a_trujillo_dolar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txt_transp_a_nasca_dolar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_tarifa_analisis, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_tarifa_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_adelanto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_otros_gastos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_gastos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txt_totalUS, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1387, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total_gastos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_otros_gastos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tmh, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transp_a_nasca_dolar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transp_a_trujillo_dolar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarifa_analisis, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarifa_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_adelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(460, Short.MAX_VALUE)
                    .addComponent(txt_totalUS, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        btn_buscar.setBackground(new java.awt.Color(65, 94, 255));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btn_buscar.setText("BUSCAR");
        btn_buscar.setColorHover(new java.awt.Color(253, 173, 1));
        btn_buscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_buscar.setIconTextGap(0);
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NombreNuevo.png"))); // NOI18N

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/TipoPersonal.png"))); // NOI18N

        cboTipoSelected.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoSelected.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboTipoSelected.setForeground(new java.awt.Color(65, 94, 255));
        cboTipoSelected.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR", "TODOS", "CLIENTE ESPECIFÌFICO" }));
        cboTipoSelected.setAccent(new java.awt.Color(65, 94, 255));
        cboTipoSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoSelectedActionPerformed(evt);
            }
        });

        txt_cliente.setForeground(new java.awt.Color(65, 94, 255));
        txt_cliente.setAccent(new java.awt.Color(0, 132, 235));
        txt_cliente.setCaretColor(new java.awt.Color(0, 132, 235));
        txt_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cliente.setLabel("CLIENTE");
        txt_cliente.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_clienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_clienteKeyTyped(evt);
            }
        });

        bnt_buscarCliente.setBackground(new java.awt.Color(255, 255, 255));
        bnt_buscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/AgregarTipoProveedor.png"))); // NOI18N
        bnt_buscarCliente.setColorHover(new java.awt.Color(255, 187, 51));
        bnt_buscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_buscarClienteActionPerformed(evt);
            }
        });

        lbl_de.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_de.setForeground(new java.awt.Color(65, 94, 255));
        lbl_de.setText("DE");

        dateDe.setColorBackground(new java.awt.Color(64, 95, 255));
        dateDe.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dateDe.setColorForeground(new java.awt.Color(64, 95, 255));
        dateDe.setFormatoFecha("dd/MM/yyyy");
        dateDe.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dateDe.setPlaceholder("DE");
        dateDe.setPreferredSize(new java.awt.Dimension(200, 40));

        lbl_guion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_guion.setForeground(new java.awt.Color(65, 94, 255));
        lbl_guion.setText("-");

        lbl_hasta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_hasta.setForeground(new java.awt.Color(65, 94, 255));
        lbl_hasta.setText("HASTA");

        dateHasta.setColorBackground(new java.awt.Color(64, 95, 255));
        dateHasta.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dateHasta.setColorForeground(new java.awt.Color(64, 95, 255));
        dateHasta.setFormatoFecha("dd/MM/yyyy");
        dateHasta.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dateHasta.setPlaceholder("DE");
        dateHasta.setPreferredSize(new java.awt.Dimension(200, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTipoSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSLabelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnt_buscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_de, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateDe, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_guion, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_hasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboTipoSelected, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addComponent(rSLabelImage4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(rSLabelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lbl_de)
                                    .addGap(5, 5, 5)
                                    .addComponent(dateDe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbl_hasta)
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_guion)))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(bnt_buscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
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


    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        if (buscarValorizacio()) {
            return;
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private boolean buscarValorizacio() {
        limpiarTabla();
        try {
            int seleccionado = cboTipoSelected.getSelectedIndex();
            if (seleccionado != 0) {
                Date fechaDe = dateDe.getDatoFecha();
                Date fechaHasta = dateHasta.getDatoFecha();
                switch (seleccionado) {
                    case 1:
                        gestionarValorizacionReporteServicio.mostrarValoracionTodos(
                                (new java.sql.Date(fechaDe.getTime())),
                                (new java.sql.Date(fechaHasta.getTime())),
                                tableFactura,
                                txt_adelanto,
                                txt_otros_gastos,
                                txt_tarifa_analisis,
                                txt_tarifa_porcentaje,
                                txt_tmh,
                                txt_totalUS,
                                txt_total_gastos,
                                txt_total_pagar,
                                txt_transp_a_nasca_dolar,
                                txt_transp_a_trujillo_dolar);
                        break;
                    case 2:
                        if (clienteEntranteSeleccionado == null) {
                            DesktopNotify.showDesktopMessage("FiveCod software", "Falta seleccionar un cliente", DesktopNotify.WARNING);
                            return true;
                        }
                        gestionarValorizacionReporteServicio.mostrarValoracionPorEspecifico(
                                (new java.sql.Date(fechaDe.getTime())),
                                (new java.sql.Date(fechaHasta.getTime())),
                                tableFactura, clienteEntranteSeleccionado.getCodigo(), txt_adelanto,
                                txt_otros_gastos,
                                txt_tarifa_analisis,
                                txt_tarifa_porcentaje,
                                txt_tmh,
                                txt_totalUS,
                                txt_total_gastos,
                                txt_total_pagar,
                                txt_transp_a_nasca_dolar,
                                txt_transp_a_trujillo_dolar
                        );
                        break;
                }
            } else {
                DesktopNotify.showDesktopMessage("FiveCod software", "Debe seleccionar un tipo de accion", DesktopNotify.WARNING);
            }
        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
        }
        return false;
    }
    int fila;
    private void tableFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFacturaMousePressed
        fila = tableFactura.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableFacturaMousePressed

    private void tableFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFacturaKeyReleased
        fila = tableFactura.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableFacturaKeyReleased

    private void tableFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFacturaKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//
//                    obtenerFactura();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableFacturaKeyTyped

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            obtenerFacturaSeleccionado();
            FormRegistroFactura factura = new FormRegistroFactura(null, true, facturaSeleccionado);
            factura.setVisible(true);
            inicializarTabla();
            facturaSeleccionado = null;
            inabilitarBotones(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            obtenerFacturaSeleccionado();
            if (facturaSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = 0; //gestionarFacturaServicio.eliminarFactura(facturaSeleccionado);
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
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txt_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyReleased
        txt_cliente.setText(txt_cliente.getText().toUpperCase());
    }//GEN-LAST:event_txt_clienteKeyReleased

    private void txt_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyTyped

    }//GEN-LAST:event_txt_clienteKeyTyped

    private void cboTipoSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoSelectedActionPerformed
        limpiarTabla();
        buscarValorizacion();
    }//GEN-LAST:event_cboTipoSelectedActionPerformed

    private void bnt_buscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_buscarClienteActionPerformed
        FormGestionarClienteEntrante formGestionarClienteEntrante = new FormGestionarClienteEntrante(null, true, FormGestionarClienteEntrante.TIPO_LIQUIDACION_REPORTE);
        formGestionarClienteEntrante.setVisible(true);
        if (clienteEntranteSeleccionado != null) {
            txt_cliente.setText(clienteEntranteSeleccionado.generarNombre());
            buscarValorizacion();
        }

    }//GEN-LAST:event_bnt_buscarClienteActionPerformed

    private void txt_total_gastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_total_gastosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_gastosKeyReleased

    private void txt_total_gastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_total_gastosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_gastosKeyTyped

    private void txt_totalUSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalUSKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalUSKeyReleased

    private void txt_totalUSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalUSKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalUSKeyTyped

    private void txt_tmhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tmhKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tmhKeyReleased

    private void txt_tmhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tmhKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tmhKeyTyped

    private void txt_transp_a_trujillo_dolarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_transp_a_trujillo_dolarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_transp_a_trujillo_dolarKeyReleased

    private void txt_transp_a_trujillo_dolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_transp_a_trujillo_dolarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_transp_a_trujillo_dolarKeyTyped

    private void txt_transp_a_nasca_dolarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_transp_a_nasca_dolarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_transp_a_nasca_dolarKeyReleased

    private void txt_transp_a_nasca_dolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_transp_a_nasca_dolarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_transp_a_nasca_dolarKeyTyped

    private void txt_tarifa_analisisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarifa_analisisKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tarifa_analisisKeyReleased

    private void txt_tarifa_analisisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarifa_analisisKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tarifa_analisisKeyTyped

    private void txt_tarifa_porcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarifa_porcentajeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tarifa_porcentajeKeyReleased

    private void txt_tarifa_porcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarifa_porcentajeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tarifa_porcentajeKeyTyped

    private void txt_adelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_adelantoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adelantoKeyReleased

    private void txt_adelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_adelantoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adelantoKeyTyped

    private void txt_otros_gastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otros_gastosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otros_gastosKeyReleased

    private void txt_otros_gastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otros_gastosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otros_gastosKeyTyped

    private void txt_total_pagarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_total_pagarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_pagarKeyReleased

    private void txt_total_pagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_total_pagarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_pagarKeyTyped

    private void obtenerFacturaSeleccionado() {
        try {
            String codigo = tableFactura.getValueAt(fila, 0).toString();
            // facturaSeleccionado = gestionarFacturaServicio.buscarFacturaPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void inabilitarBotones(Boolean v) {

//        btnEliminar.setEnabled(!v);
        btnModificar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconD bnt_buscarCliente;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btn_buscar;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<String> cboTipoSelected;
    private rojeru_san.componentes.RSDateChooser dateDe;
    private rojeru_san.componentes.RSDateChooser dateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_de;
    private javax.swing.JLabel lbl_guion;
    private javax.swing.JLabel lbl_hasta;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSTableMetro tableFactura;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_adelanto;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_cliente;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_otros_gastos;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_tarifa_analisis;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_tarifa_porcentaje;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_tmh;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_totalUS;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_total_gastos;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_total_pagar;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_transp_a_nasca_dolar;
    private SistemaLara.capa1_presentacion.util.FCMaterialTextField txt_transp_a_trujillo_dolar;
    // End of variables declaration//GEN-END:variables

//    private void obtenerFactura() {
//        String codigo = tableFactura.getValueAt(fila, 0).toString();
//
//        try {
//            facturaSeleccionado = gestionarFacturaServicio.buscarFacturaPorCodigo(Integer.parseInt(codigo));
//            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//                FormGestionarLiquidacion.facturaSeleccionado = facturaSeleccionado;
//                this.dispose();
//            } else {
//                inabilitarBotones(false);
//            }
//
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }
//
//    }
    private void buscarValorizacion() {

        int seleccionado = cboTipoSelected.getSelectedIndex();
        switch (seleccionado) {
            case 0:
                txt_cliente.setVisible(false);
                bnt_buscarCliente.setVisible(false);
                dateDe.setVisible(false);
                dateHasta.setVisible(false);
                lbl_de.setVisible(false);
                lbl_guion.setVisible(false);
                lbl_hasta.setVisible(false);
                btn_buscar.setVisible(false);
                break;
            case 1:
                txt_cliente.setVisible(false);
                bnt_buscarCliente.setVisible(false);
                dateDe.setVisible(true);
                dateHasta.setVisible(true);
                lbl_de.setVisible(true);
                lbl_guion.setVisible(true);
                lbl_hasta.setVisible(true);

                btn_buscar.setVisible(true);
                break;
            case 2:
                bnt_buscarCliente.setVisible(true);
                dateDe.setVisible(true);
                dateHasta.setVisible(true);
                btn_buscar.setVisible(true);
                lbl_de.setVisible(true);
                lbl_guion.setVisible(true);
                lbl_hasta.setVisible(true);
                txt_cliente.setVisible(true);
                txt_cliente.setEnabled(false);
                break;
        }
    }
}
