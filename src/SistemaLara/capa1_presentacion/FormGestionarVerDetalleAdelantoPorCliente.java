/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.RedondearDecimales;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarAdelantoServicio;
import SistemaLara.capa2_aplicacion.GestionarValorizacionServicio;
import SistemaLara.capa3_dominio.Adelanto;
import SistemaLara.capa3_dominio.Cambio;
import SistemaLara.capa3_dominio.ChequeCliente;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.Valorizacion;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jdesktop.core.animation.timing.Animator;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarAdelantoServicio;
//import motorepuestos.capa3_dominio.Adelanto;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarVerDetalleAdelantoPorCliente extends javax.swing.JDialog {

//    private List<Adelanto> listaAdelanto = new ArrayList<Adelanto>();
    private GestionarAdelantoServicio gestionarAdelantoServicio;
    public static Adelanto tipoTrabajadorSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;
    private JasperPrint print = null;
    private Valorizacion valorizacionSeleccionada;
    private GestionarAdelantoReporteServicio gestionarAdelantoReporteServicio;
    private static GestionarValorizacionServicio gestionarValorizacionServicio;
    private ClienteEntrante clienteEntranteSeleccionado;

    public FormGestionarVerDetalleAdelantoPorCliente(java.awt.Frame parent, boolean modal, Valorizacion valorizacion) {
        super(parent, modal);
        try {
            initComponents();
            Animacion.moverParaIzquierda(this);
            gestionarValorizacionServicio = new GestionarValorizacionServicio();
            BarraDesplzamiento();
            this.setLocationRelativeTo(null);
            valorizacionSeleccionada = valorizacion;
            gestionarAdelantoServicio = new GestionarAdelantoServicio();
            clienteEntranteSeleccionado = valorizacion.getClienteEntrante();
            inicializarTablaColumnas();
            gestionarAdelantoReporteServicio = new GestionarAdelantoReporteServicio();
            inicializarTabla();
//            inabilitarBotones(true);

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
        tabla.agregarColumna(new Columna("CANTIDAD", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONEDA", "java.lang.String"));
        tabla.agregarColumna(new Columna("DESCRIPCION", "java.lang.String"));
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

        tableAdelanto.getColumn(tableAdelanto.getColumnName(4)).setWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(4)).setMinWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(4)).setMaxWidth(100);

        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setMinWidth(100);
        tableAdelanto.getColumn(tableAdelanto.getColumnName(5)).setMaxWidth(100);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableAdelanto.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarAdelantoServicio.mostrarAdelantoPorClienteValorizacion(1, tableAdelanto, valorizacionSeleccionada);
            calcularTotales();
        } catch (Exception e) {
        }
    }

    private void calcularTotales() {
        for (int i = 0; i < tableAdelanto.getRowCount(); i++) {
            Double cantidad = Double.parseDouble(tableAdelanto.getValueAt(i, 1).toString());
            if (tableAdelanto.getValueAt(i, 2).toString().equals("$")) {
                Double totalDolares = Double.parseDouble(lbl_TotalDoales.getText().toString());
                Double calculo = cantidad + totalDolares;
                lbl_TotalDoales.setText(completarCeros("" + calculo));
            } else if (tableAdelanto.getValueAt(i, 2).toString().equals("S/")) {
                Double totalSoles = Double.parseDouble(lbl_TotalSoles.getText().toString());
                Double calculo = cantidad + totalSoles;
                lbl_TotalSoles.setText(completarCeros("" + calculo));
            }

        }
    }

    String completarCeros(String numero) {
        String splitNumero[] = numero.split(Pattern.quote("."));
        StringBuffer builder = new StringBuffer(numero);
        if (splitNumero.length == 2) {
            if (splitNumero[1].length() == 1) {
                builder.append("0");
            }
        } else {
            builder.append(".00");
        }
        return builder.toString();
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
        tableAdelanto = new rojerusan.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_TotalSoles = new javax.swing.JLabel();
        lbl_TotalDoales = new javax.swing.JLabel();
        btnImprimir = new rojeru_san.RSButton();
        btnCheque = new rojeru_san.RSButton();
        previewCheque = new com.icm.components.metro.CheckBoxMetroICM();

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
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
        jLabel1.setText("DATALLE DE ADELANTO POR CLIENTE");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoAdelanto.png"))); // NOI18N

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        tableAdelanto.setGrosorBordeFilas(0);
        tableAdelanto.setGrosorBordeHead(0);
        tableAdelanto.setRowHeight(20);
        tableAdelanto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableAdelantoMousePressed(evt);
            }
        });
        tableAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableAdelantoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableAdelantoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableAdelanto);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setForeground(new java.awt.Color(65, 94, 255));
        jLabel11.setText("Total Pagado $.");

        jLabel10.setForeground(new java.awt.Color(65, 94, 255));
        jLabel10.setText("Total Pagado S/.");

        lbl_TotalSoles.setForeground(new java.awt.Color(255, 82, 54));
        lbl_TotalSoles.setText("0.00");

        lbl_TotalDoales.setForeground(new java.awt.Color(255, 82, 54));
        lbl_TotalDoales.setText("0.00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_TotalSoles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_TotalDoales, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_TotalSoles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbl_TotalDoales)))
        );

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(previewCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(previewCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerDatosParaTabla() throws Exception {
//        listaAdelanto = gestionarAdelantoServicio.mostrarAdelantos(Adelanto.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }

    private void tableAdelantoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdelantoMousePressed

    }//GEN-LAST:event_tableAdelantoMousePressed

    private void tableAdelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyReleased

    }//GEN-LAST:event_tableAdelantoKeyReleased

    private void tableAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableAdelantoKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
//                    obtenerAdelanto();
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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            if (valorizacionSeleccionada != null) {
                print = gestionarAdelantoReporteServicio.reporteAdelantoValorizacionDetalle(valorizacionSeleccionada.getCodigo(), lbl_TotalSoles, lbl_TotalDoales);
            } else {
                Mensaje.mostrarMensajeDefinido(this, "Error con la valorizacion");
            }

        } catch (Exception ex) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequeActionPerformed
        Cambio cambioMonto = null;
        try {
            cambioMonto = gestionarValorizacionServicio.obtenerCambio();
        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("FiveCod Software", "Error al intentar mostrar el cheque", DesktopNotify.ERROR);

        }
        ChequeCliente cheque = new ChequeCliente();
        cheque.setClienteEntrante(clienteEntranteSeleccionado);
        cheque.setEntregadoA(clienteEntranteSeleccionado.generarNombre());
        cheque.setConcepto("POR VALORIZACION ");
        cheque.setMoneda("$");
        Double cambio = Double.parseDouble(lbl_TotalSoles.getText()) / Double.parseDouble(cambioMonto.getDolar());
        Double calculo = Double.parseDouble(lbl_TotalDoales.getText()) + cambio;
        cheque.setMonto(RedondearDecimales.redondearDecimales(calculo, 2));
        FormGestionarChequeClienteServicio a = new FormGestionarChequeClienteServicio(null, false);

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

//    private void obtenerDatosTabla() {
//        Fila filaTabla;
//        try {
//            ModeloTabla modeloTabla = (ModeloTabla) tablaAdelantos.getModel();
//            modeloTabla.eliminarTotalFilas();
//            if (listaAdelanto.size() == 0) {
//                Mensaje.mostrarMensajeDefinido(this, "No hay tipos de trabajadores registrados ");
//            } else {
//                for (Adelanto tipoTrabajador : listaAdelanto) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnCheque;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnImprimir;
    private rojeru_san.RSButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lbl_TotalDoales;
    public static javax.swing.JLabel lbl_TotalSoles;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private com.icm.components.metro.CheckBoxMetroICM previewCheque;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tableAdelanto;
    // End of variables declaration//GEN-END:variables

//    private void obtenerAdelanto() {
////        String codigo = tablaAdelantos.getValueAt(fila, 0).toString();
////
////        try {
////            tipoTrabajadorSeleccionado = gestionarAdelantoServicio.buscarAdelantoPorCodigo(codigo.trim());
////            if (TIPO_USUARIO == TIPO_TRABAJADOR) {
////                tipoTrabajadorSeleccionado = tipoTrabajadorSeleccionado;
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
}
