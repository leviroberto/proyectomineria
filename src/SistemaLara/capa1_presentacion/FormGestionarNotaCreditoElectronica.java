package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarFacturaElectronicaServicio;
import SistemaLara.capa2_aplicacion.GestionarFacturaReporteServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaCreditoServicio;
import SistemaLara.capa2_aplicacion.GestionarNotaDebitoServicio;
import SistemaLara.capa3_dominio.CorreoFactura;
import SistemaLara.capa3_dominio.EstadoFacturaElectronica;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.TipoFacturaElectronica;
import com.icm.components.metro.CheckBoxMetroICM;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarNotaCreditoElectronica extends javax.swing.JDialog {

    private GestionarNotaCreditoServicio gestionarNotaCreditoServicio;
    private SimpleDateFormat format;
    private String fechaEnvio;
    DefaultTableModel modelo;
    private boolean estadoFactura = false;
    private GestionarFacturaReporteServicio gestionarFacturaReporteServicio;

    public FormGestionarNotaCreditoElectronica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);

        format = new SimpleDateFormat("yyyy-MM-dd");
        gestionarNotaCreditoServicio = new GestionarNotaCreditoServicio();
        gestionarFacturaReporteServicio = new GestionarFacturaReporteServicio();
//          inicializarTablaColumnas();
        llenarComboEstadoFacturaElectronica();
        Inicializador();
        popMenu.add(pnlMenu);

    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    private void inicializarTablaColumnas() {
//        Tabla tabla = new Tabla();
//        tabla.agregarColumna(new Columna("Seleccione", "java.lang.Boolean"));
//        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
//        tabla.agregarColumna(new Columna("Numero Factura", "java.lang.String"));
//        tabla.agregarColumna(new Columna("Total Factura", "java.lang.String"));
//        tabla.agregarColumna(new Columna("Fecha Factura", "java.lang.String"));
//        ModeloTabla modeloTabla = new ModeloTabla(tabla);
//        tablaFacturaElectronica.setModel(modeloTabla);

    }

    private void Inicializador() {
        try {
            fechaEnvio = format.format(new Date());
            dpFechaDe.setDatoFecha(new Date());
            dpFechaHasta.setDatoFecha(new Date());
            obtenerDatosParaLista();
            btnDarDeBaja.setEnabled(false);
            btnEnviar.setEnabled(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void limpiarTabla() {
        try {
            modelo = (DefaultTableModel) tablaFacturaElectronica.getModel();
            int filas = tablaFacturaElectronica.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    private void obtenerDatosParaLista() {
        try {
            Color colorNaranja = new Color(255, 102, 0);
            btnEnviar.setEnabled(false);
            Color colorAzul = new Color(0, 102, 255);
            btnEnviar.setBackground(colorAzul);
            btnDarDeBaja.setEnabled(false);
            btnDarDeBaja.setBackground(colorAzul);
            limpiarTabla();
            cboxSeleccionarTodos.setEnabled(true);
            String fechaInicio = format.format(dpFechaDe.getDatoFecha());
            String fechaFin = format.format(dpFechaHasta.getDatoFecha());
            int numero = cboEstadoFacturaElectronica.getItemAt(cboEstadoFacturaElectronica.getSelectedIndex()).getCodigo();
            gestionarNotaCreditoServicio.mostrarNotaCreditoElectronico(numero, fechaInicio, fechaFin, tablaFacturaElectronica);
            if (numero == 1) {
                btnEnviar.setEnabled(true);
                btnEnviar.setBackground(colorNaranja);

            } else if (numero == 3) {
                btnDarDeBaja.setEnabled(true);
                btnDarDeBaja.setBackground(colorNaranja);
            } else if (numero == 2) {
                cboxSeleccionarTodos.setEnabled(false);
                btnDarDeBaja.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void llenarComboEstadoFacturaElectronica() {
        try {
            List<EstadoFacturaElectronica> lista;
            lista = gestionarNotaCreditoServicio.llenarEstadoFacturaElectronica(1);
            for (EstadoFacturaElectronica estadoFacturaElectronica : lista) {
                cboEstadoFacturaElectronica.addItem(estadoFacturaElectronica);
            }
            estadoFactura = true;
        } catch (Exception e) {
        }

    }

    void estaSeleccionadoElPrimero() {
        int numero = cboEstadoFacturaElectronica.getItemAt(cboEstadoFacturaElectronica.getSelectedIndex()).getCodigo();
        if (numero == 1) {
            btnEnviar.setEnabled(true);
            btnDarDeBaja.setEnabled(false);
        } else if (numero == 3) {
            btnEnviar.setEnabled(false);
            btnDarDeBaja.setEnabled(true);
        } else if (numero == 2) {
            btnEnviar.setEnabled(false);
            btnDarDeBaja.setEnabled(false);
        }
    }

    void seleccionarCheckBoxTodos(boolean estado) {
        try {
            modelo = (DefaultTableModel) tablaFacturaElectronica.getModel();
            int contador = 0;
            for (int i = 0; i < tablaFacturaElectronica.getRowCount(); i++) {
                modelo.setValueAt(estado, contador, 0);
                contador++;
            }
            tablaFacturaElectronica.setModel(modelo);
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnEnviarCorreo = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaFacturaElectronica = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        btnEnviar = new rojeru_san.RSButton();
        btnDarDeBaja = new rojeru_san.RSButton();
        dpFechaDe = new rojeru_san.componentes.RSDateChooser();
        dpFechaHasta = new rojeru_san.componentes.RSDateChooser();
        btnBuscar = new rojeru_san.RSButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboEstadoFacturaElectronica = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        cboxSeleccionarTodos = new com.icm.components.metro.CheckBoxMetroICM();

        btnEnviarCorreo.setBackground(new java.awt.Color(65, 94, 255));
        btnEnviarCorreo.setBorder(null);
        btnEnviarCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnEnviarCorreo.setText("Enviar correo");
        btnEnviarCorreo.setColorHover(new java.awt.Color(255, 82, 54));
        btnEnviarCorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEnviarCorreo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEnviarCorreo.setIconTextGap(2);
        btnEnviarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEnviarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnEnviarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
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
        jLabel1.setText("GESTIONAR NOTA CREDITO ELECTRONICA ");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaFacturaElectronica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Seleccione", "Id", "Nro", "Total", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFacturaElectronica.setAltoHead(30);
        tablaFacturaElectronica.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaFacturaElectronica.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaFacturaElectronica.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaFacturaElectronica.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaFacturaElectronica.setComponentPopupMenu(popMenu);
        tablaFacturaElectronica.setGrosorBordeFilas(0);
        tablaFacturaElectronica.setGrosorBordeHead(0);
        tablaFacturaElectronica.setRowHeight(20);
        tablaFacturaElectronica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaFacturaElectronicaMousePressed(evt);
            }
        });
        tablaFacturaElectronica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaFacturaElectronicaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaFacturaElectronicaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaFacturaElectronica);

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        btnEnviar.setBackground(new java.awt.Color(0, 102, 255));
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Enviar.png"))); // NOI18N
        btnEnviar.setText("ENVIAR");
        btnEnviar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnDarDeBaja.setBackground(new java.awt.Color(0, 102, 255));
        btnDarDeBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/DarDeBajaNuevo.png"))); // NOI18N
        btnDarDeBaja.setText("DAR DE BAJA");
        btnDarDeBaja.setColorHover(new java.awt.Color(255, 82, 54));
        btnDarDeBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarDeBajaActionPerformed(evt);
            }
        });

        dpFechaDe.setColorBackground(new java.awt.Color(64, 95, 255));
        dpFechaDe.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dpFechaDe.setColorForeground(new java.awt.Color(64, 95, 255));
        dpFechaDe.setFormatoFecha("dd/MM/yyyy");
        dpFechaDe.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dpFechaDe.setPlaceholder("FECHA DE");
        dpFechaDe.setPreferredSize(new java.awt.Dimension(200, 40));

        dpFechaHasta.setColorBackground(new java.awt.Color(64, 95, 255));
        dpFechaHasta.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dpFechaHasta.setColorForeground(new java.awt.Color(64, 95, 255));
        dpFechaHasta.setFormatoFecha("dd/MM/yyyy");
        dpFechaHasta.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dpFechaHasta.setPlaceholder("FECHA HASTA ");
        dpFechaHasta.setPreferredSize(new java.awt.Dimension(200, 40));

        btnBuscar.setBackground(new java.awt.Color(0, 102, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Buscar.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setColorHover(new java.awt.Color(255, 82, 54));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("OPCIONES");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("HASTA");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DE");

        cboEstadoFacturaElectronica.setBackground(new java.awt.Color(255, 255, 255));
        cboEstadoFacturaElectronica.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboEstadoFacturaElectronica.setForeground(new java.awt.Color(65, 94, 255));
        cboEstadoFacturaElectronica.setAccent(new java.awt.Color(65, 94, 255));
        cboEstadoFacturaElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoFacturaElectronicaActionPerformed(evt);
            }
        });

        cboxSeleccionarTodos.setBorder(null);
        cboxSeleccionarTodos.setText("Todos");
        cboxSeleccionarTodos.setDark(false);
        cboxSeleccionarTodos.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        cboxSeleccionarTodos.setHideActionText(true);
        cboxSeleccionarTodos.setIconTextGap(1);
        cboxSeleccionarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxSeleccionarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboEstadoFacturaElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dpFechaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(dpFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboxSeleccionarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDarDeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboEstadoFacturaElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxSeleccionarTodos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDarDeBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpFechaHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpFechaDe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int fila;
    private void tablaFacturaElectronicaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturaElectronicaMousePressed
        fila = tablaFacturaElectronica.getSelectedRow();
        int columna = tablaFacturaElectronica.getSelectedColumn();
        if (columna == 0) {
            estaSeleccionadoElPrimero();
        } else if (columna >= 0) {
            estaSeleccionadoElPrimero();
        } else {
            btnDarDeBaja.setEnabled(false);
            btnEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_tablaFacturaElectronicaMousePressed

    private void tablaFacturaElectronicaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFacturaElectronicaKeyReleased
        fila = tablaFacturaElectronica.getSelectedRow();
        // inabilitarBotones(false);
    }//GEN-LAST:event_tablaFacturaElectronicaKeyReleased

    private void tablaFacturaElectronicaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaFacturaElectronicaKeyTyped


    }//GEN-LAST:event_tablaFacturaElectronicaKeyTyped

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged
    private List<Factura> obtenerFilasSeleccionadasCheckBox() {
        List<Factura> listaFactura = new ArrayList<>();
        modelo = (DefaultTableModel) tablaFacturaElectronica.getModel();
        Factura factura;
        for (int i = 0; i < modelo.getRowCount(); i++) {

            //Si la columna 4 está true añadimos el ID
            if ((Boolean) modelo.getValueAt(i, 0) == true) {
                factura = new Factura();
                factura.setCodigo((Integer) modelo.getValueAt(i, 1));
                factura.setNroFactura((String) modelo.getValueAt(i, 2));
                listaFactura.add(factura);
            }
        }
        tablaFacturaElectronica.setModel(modelo);
        return listaFactura;

    }


    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        List<Factura> listaSeleccionado = obtenerFilasSeleccionadasCheckBox();
        List<String> listaFacturasRespuestaCorrectas = new ArrayList<>();
        List<String> listaFacturasRespuestaIncorrectos = new ArrayList<>();
        modelo = (DefaultTableModel) tablaFacturaElectronica.getModel();
        int contadorExitosa = 0;
        if (listaSeleccionado.size() > 0) {
            for (int i = 0; i < listaSeleccionado.size(); i++) {

                boolean resultado = generarNotaCredito(listaSeleccionado.get(i).getCodigo(), IniciarSesion.getUsuario().getEmpresa().getEmpresabd());
                // generarPDF(listaSeleccionado.get(i));
                //    boolean resultado=generarFactura(codigoFactura);
                if (resultado) {
                    contadorExitosa++;
                    listaFacturasRespuestaCorrectas.add(listaSeleccionado.get(i).getNroFactura());
                    generarPDF(listaSeleccionado.get(i));
                } else {
                    contadorExitosa--;
                    listaFacturasRespuestaIncorrectos.add(listaSeleccionado.get(i).getNroFactura());
                }
            }
            FormGestionarListaRespuesFacturaElectronica a = new FormGestionarListaRespuesFacturaElectronica(null, true, listaFacturasRespuestaCorrectas, listaFacturasRespuestaIncorrectos);
            a.setVisible(true);
            seleccionarCheckBoxTodos(false);
            obtenerDatosParaLista();
            estadoSeleccionarTodo = false;
            cboxSeleccionarTodos.setSelected(false);
        } else {
            Mensaje.mostrarMensajeDefinido(this, "Falta Seleccionar Facturas Para Enviar");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void generarPDF(Factura factura) {
        try {
            //gestionarFacturaReporteServicio.generarFacturaElectronica(factura);
        } catch (Exception e) {
        }

    }

    public static String MensajeDeBaja = "";
    private void btnDarDeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDeBajaActionPerformed
        List<Factura> listaSeleccionado = obtenerFilasSeleccionadasCheckBox();
        List<String> listaFacturasElectronicoEnviarMensaje = new ArrayList<>();
        List<String> listaFacturasElectronicoEnviarIdFactura = new ArrayList<>();
        String listaMensaje = new String();
        String listaIdFactura = new String();

        if (listaSeleccionado.size() > 0) {
            for (Factura factura : listaSeleccionado) {
                FormMensajeDarDeBajaFEServicio faBajaFEServicio = new FormMensajeDarDeBajaFEServicio(null, true, factura);
                faBajaFEServicio.setVisible(true);

                if (MensajeDeBaja != null) {
                    listaMensaje = listaMensaje + "-" + MensajeDeBaja;
                    listaIdFactura = listaIdFactura + "-" + factura.getCodigo();
                }
            }
            if (listaIdFactura.length() > 0) {
                boolean estadoResultado = darDeBajaNotaCredito(listaIdFactura, listaMensaje, IniciarSesion.getUsuario().getEmpresa().getEmpresabd(), IniciarSesion.getUsuario().getEmpresa().getCodigo());
                if (estadoResultado) {
                    Mensaje.mostrarMensajeDefinido(this, "Se dieron de baja correctamente");

                    seleccionarCheckBoxTodos(false);
                    obtenerDatosParaLista();
                    estadoSeleccionarTodo = false;
                    cboxSeleccionarTodos.setSelected(false);
                } else {
                    Mensaje.mostrarMensajeDefinido(this, "Error al dar de baja");
                }
            }
//            FormGestionarListaRespuesFacturaElectronica a = new FormGestionarListaRespuesFacturaElectronica(null, true, listaFacturasRespuestaCorrectas, listaFacturasRespuestaIncorrectos);
//            a.setVisible(true);
//            seleccionarCheckBoxTodos(false);
//            obtenerDatosParaLista();
//            estadoSeleccionarTodo = false;
//            cboxSeleccionarTodos.setSelected(false);

        } else {
            Mensaje.mostrarMensajeDefinido(this, "Debe Seleccionar una Factura o Mas Para Poder Proceder");
        }


    }//GEN-LAST:event_btnDarDeBajaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        obtenerDatosParaLista();
    }//GEN-LAST:event_btnBuscarActionPerformed
    boolean estadoSeleccionarTodo = false;
    private void cboxSeleccionarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxSeleccionarTodosActionPerformed
        if (estadoSeleccionarTodo == false) {
            estadoSeleccionarTodo = true;
            seleccionarCheckBoxTodos(true);
            estaSeleccionadoElPrimero();
        } else if (estadoSeleccionarTodo) {
            estadoSeleccionarTodo = false;
            seleccionarCheckBoxTodos(false);
            btnDarDeBaja.setEnabled(false);
            btnEnviar.setEnabled(false);
        }
    }//GEN-LAST:event_cboxSeleccionarTodosActionPerformed

    private void cboEstadoFacturaElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoFacturaElectronicaActionPerformed
        if (estadoFactura == true) {
            obtenerDatosParaLista();
        }


    }//GEN-LAST:event_cboEstadoFacturaElectronicaActionPerformed

    private void btnEnviarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCorreoActionPerformed
        try {
            String codigo = tablaFacturaElectronica.getValueAt(fila, 1).toString();
            CorreoFactura correoFactura = gestionarNotaCreditoServicio.buscarParaCorreo(codigo);

            FormDatosCorreFactura formDatosCorreFactura = new FormDatosCorreFactura(null, true, correoFactura);
            formDatosCorreFactura.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btnEnviarCorreoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnBuscar;
    private rojeru_san.RSButton btnDarDeBaja;
    private rojeru_san.RSButton btnEnviar;
    private rojeru_san.RSButton btnEnviarCorreo;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<EstadoFacturaElectronica> cboEstadoFacturaElectronica;
    public static com.icm.components.metro.CheckBoxMetroICM cboxSeleccionarTodos;
    private rojeru_san.componentes.RSDateChooser dpFechaDe;
    private rojeru_san.componentes.RSDateChooser dpFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaFacturaElectronica;
    // End of variables declaration//GEN-END:variables

    private static Boolean generarNotaCredito(java.lang.Integer idNotaCredito, java.lang.String empresa) {
        boolean respuesta = false;
        try {

            org.tempuri.Service1 service = new org.tempuri.Service1();
            org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
            respuesta = port.generarNotaCredito(idNotaCredito, empresa);

        } catch (Exception e) {
            System.out.println("hubo error" + e.getMessage());
            respuesta = false;
        }
        return respuesta;
    }

    private static Boolean darDeBajaNotaCredito(java.lang.String listaIdFactura, java.lang.String listaMotivoBaj, java.lang.String empresa, java.lang.Integer idEmpresa) {

        boolean respuesta = false;
        try {

            org.tempuri.Service1 service = new org.tempuri.Service1();
            org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
            respuesta = port.darDeBajaNotaCredito(listaIdFactura, listaMotivoBaj, empresa, idEmpresa);
        } catch (Exception e) {
            System.out.println("hubo error" + e.getMessage());
            respuesta = false;
        }
        return respuesta;
    }

}
