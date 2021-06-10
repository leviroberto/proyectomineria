package SistemaLara.capa1_presentacion;

import FiveCodScrollbar.MaterialScrollBarUI;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.ImagenTable;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.RenderFecha;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarTareaDiariaServicio;
import SistemaLara.capa3_dominio.Estado;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.Personal;
import SistemaLara.capa3_dominio.TareaDiaria;
import SistemaLara.capa8_timer.TimerCumpleaños;
import SistemaLara.capa8_timer.TimerTareasDiarias;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

import rojerusan.RSAnimation;
import rojerusan.RSButtonCircleIcon;

/**
 * @author FiveCod Software
 */
public class FormTareasDiarias extends javax.swing.JDialog {

    private List<TareaDiaria> listaTarea = new ArrayList<>();
    GestionarTareaDiariaServicio gestionarTareaDiariaServicio;
    private TareaDiaria tareaDiaria;
    public static int ACCION_CREAR = 1;
    public static int ACCION_MODIFICAR = 2;
    public static int tipo_accion;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private int codigoTareaDiaria;

    public FormTareasDiarias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        BarraDesplzamiento();
        inicializarTablaColumnas();
        tipo_accion = ACCION_CREAR;
        llenarEstado();
        inabilitarTextos(false);
        gestionarTareaDiariaServicio = new GestionarTareaDiariaServicio();
        inicializarTabla();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        popMenu.add(pnlMenu);

//        String texto = "Se tiene que hacer una buena tarea poara todo los dias de la semana por favor incluir archivos de investigacion";
//        lblTextoTarea.setText("<html> " + texto + "<html>");
//        String fecha = "2019-08-15";
//        lblFecha.setText("<html> " + fecha + "<html>");
//        crearComponentesDeLasMesas();
    }

    public final void BarraDesplzamiento() {
        jScrollPane1.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new RSScrollBar());
         jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    void llenarEstado() {
        Estado porhacer = new Estado();
        porhacer.setCodigo(12);
        porhacer.setDescripcion("Por Hacer");
        Estado hechos = new Estado();
        hechos.setCodigo(13);
        hechos.setDescripcion("Hechos");
        cboEstadoTareaDiaria.addItem(porhacer);
        cboEstadoTareaDiaria.addItem(hechos);
        cboEstadTarea.addItem(porhacer);
        cboEstadTarea.addItem(hechos);

    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Codigo", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("TAREA PENDIENTE", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableTareasDespues.setModel(modeloTabla);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(0)).setWidth(0);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(0)).setMinWidth(0);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(0)).setMaxWidth(0);

        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(2)).setWidth(90);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(2)).setMinWidth(90);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(2)).setMaxWidth(90);

        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(3)).setWidth(90);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(3)).setMinWidth(90);
        tableTareasDespues.getColumn(tableTareasDespues.getColumnName(3)).setMaxWidth(90);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableTareasDespues.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            int codigo = cboEstadoTareaDiaria.getItemAt(cboEstadoTareaDiaria.getSelectedIndex()).getCodigo();
            gestionarTareaDiariaServicio.mostrarTareaDiariaPorHacer(tableTareasDespues, codigo);
            cambiarDeColor();
            limpiarCampos();
            inabilitarTextos(false);
        } catch (Exception e) {
        }
    }

    private void cambiarDeColor() {
        Date fecha = new Date();
        String fechaFomateada = format.format(fecha);
        RenderFecha form = new RenderFecha(2, fechaFomateada);
        tableTareasDespues.setDefaultRenderer(Object.class, form);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnCambiarHecho = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        btnCambiarPorHacer = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel6 = new javax.swing.JPanel();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        dataTarea = new rojeru_san.componentes.RSDateChooser();
        btnCancelar = new rojeru_san.RSButton();
        btnAgregar = new rojeru_san.RSButton();
        cboEstadTarea = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTareasDespues = new rojerusan.RSTableMetro();
        cboEstadoTareaDiaria = new FiveCodMaterilalDesignComboBox.MaterialComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        btnCambiarHecho.setBackground(new java.awt.Color(65, 94, 255));
        btnCambiarHecho.setBorder(null);
        btnCambiarHecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ActulizarNuevo.png"))); // NOI18N
        btnCambiarHecho.setText("MODIFICAR/Por Hecho");
        btnCambiarHecho.setColorHover(new java.awt.Color(255, 82, 54));
        btnCambiarHecho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCambiarHecho.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCambiarHecho.setIconTextGap(2);
        btnCambiarHecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarHechoActionPerformed(evt);
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

        btnCambiarPorHacer.setBackground(new java.awt.Color(65, 94, 255));
        btnCambiarPorHacer.setBorder(null);
        btnCambiarPorHacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ActulizarNuevo.png"))); // NOI18N
        btnCambiarPorHacer.setText("MODIFICAR/Por Hacer");
        btnCambiarPorHacer.setColorHover(new java.awt.Color(255, 82, 54));
        btnCambiarPorHacer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCambiarPorHacer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCambiarPorHacer.setIconTextGap(2);
        btnCambiarPorHacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPorHacerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCambiarHecho, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCambiarPorHacer, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnCambiarHecho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCambiarPorHacer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Tare.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TAREAS QUE SE TIENE QUE HACER");

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
                .addGap(254, 254, 254)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(65, 94, 255));
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel6.setBorder(dropShadowBorder1);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/ConceptoPro.png"))); // NOI18N
        jPanel6.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 40));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Trabajador.png"))); // NOI18N
        jPanel6.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 50, 50));

        txtDescripcion.setColumns(20);
        txtDescripcion.setForeground(new java.awt.Color(65, 94, 255));
        txtDescripcion.setTabSize(0);
        txtDescripcion.setToolTipText("INGRESE EL MOTIVO SI ES NECESARIO");
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255)));
        txtDescripcion.setCaretColor(new java.awt.Color(65, 94, 255));
        txtDescripcion.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtDescripcion.setSelectionColor(new java.awt.Color(65, 94, 255));
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 560, 80));

        dataTarea.setColorBackground(new java.awt.Color(64, 95, 255));
        dataTarea.setColorButtonHover(new java.awt.Color(64, 95, 255));
        dataTarea.setColorForeground(new java.awt.Color(64, 95, 255));
        dataTarea.setFormatoFecha("dd/MM/yyyy");
        dataTarea.setFuente(new java.awt.Font("Book Antiqua", 1, 14)); // NOI18N
        dataTarea.setPlaceholder("FECHA ");
        dataTarea.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel6.add(dataTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 240, 30));

        btnCancelar.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnCancelar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnCancelarFocusGained(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 140, 40));

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/GuardarNuevo.png"))); // NOI18N
        btnAgregar.setText("Nuevo");
        btnAgregar.setColorHover(new java.awt.Color(253, 173, 1));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAgregar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAgregarFocusGained(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel6.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 140, 40));

        cboEstadTarea.setBackground(new java.awt.Color(255, 255, 255));
        cboEstadTarea.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboEstadTarea.setForeground(new java.awt.Color(65, 94, 255));
        cboEstadTarea.setAccent(new java.awt.Color(65, 94, 255));
        cboEstadTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadTareaActionPerformed(evt);
            }
        });
        jPanel6.add(cboEstadTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 160, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("ESTADO:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 60, 20));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder2);

        jScrollPane4.setBorder(null);

        tableTareasDespues.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableTareasDespues.setAltoHead(30);
        tableTareasDespues.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableTareasDespues.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableTareasDespues.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableTareasDespues.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableTareasDespues.setComponentPopupMenu(popMenu);
        tableTareasDespues.setGrosorBordeFilas(0);
        tableTareasDespues.setGrosorBordeHead(0);
        tableTareasDespues.setRowHeight(20);
        tableTareasDespues.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableTareasDespuesMousePressed(evt);
            }
        });
        tableTareasDespues.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableTareasDespuesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableTareasDespuesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableTareasDespuesKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tableTareasDespues);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );

        cboEstadoTareaDiaria.setBackground(new java.awt.Color(255, 255, 255));
        cboEstadoTareaDiaria.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 94, 255)));
        cboEstadoTareaDiaria.setForeground(new java.awt.Color(65, 94, 255));
        cboEstadoTareaDiaria.setAccent(new java.awt.Color(65, 94, 255));
        cboEstadoTareaDiaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoTareaDiariaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 204));
        jLabel5.setText("BUSCAR POR ESTADOO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(cboEstadoTareaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstadoTareaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnCambiarHechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarHechoActionPerformed
        try {
            obtenerTrabajadorSeleccionado();
            if (tareaDiaria != null) {

                try {
                    Estado estado = new Estado();
                    estado.setCodigo(13);
                    tareaDiaria.setEstado(estado);
                    int registros_afectados = gestionarTareaDiariaServicio.modificarTareaDiariaPorEstado(tareaDiaria);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();

                    } else {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            } else {
                Mensaje.mostrarErrorSistema(this);
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }//GEN-LAST:event_btnCambiarHechoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        metodoParaEliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCambiarPorHacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPorHacerActionPerformed
        try {
            obtenerTrabajadorSeleccionado();
            if (tareaDiaria != null) {

                try {
                    Estado estado = new Estado();
                    estado.setCodigo(12);
                    tareaDiaria.setEstado(estado);
                    int registros_afectados = gestionarTareaDiariaServicio.modificarTareaDiariaPorEstado(tareaDiaria);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        inicializarTabla();

                    } else {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            } else {
                Mensaje.mostrarErrorSistema(this);
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnCambiarPorHacerActionPerformed

    private void cboEstadoTareaDiariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoTareaDiariaActionPerformed
        inicializarTabla();
    }//GEN-LAST:event_cboEstadoTareaDiariaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (btnAgregar.getText().equals("Nuevo")) {
            limpiarCampos();
            btnAgregar.setText("Guardar");
            inabilitarTextos(true);
        } else if (btnAgregar.getText().equals("Guardar")) {
            guardarDatos();
            btnAgregar.setText("Nuevo");
            limpiarCampos();
            inabilitarTextos(false);
            inicializarNotificacionesTareas();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void inicializarNotificacionesTareas() {

        DesktopNotify.eliminarWindowsAll();
        TimerTareasDiarias.iniciarOtraVes();
        IniciarDesktop a = new IniciarDesktop();
        a.sumarNumeroNotificacionDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);

    }
    private void btnAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAgregarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarFocusGained

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        inabilitarTextos(false);
        btnAgregar.setText("Nuevo");
        tipo_accion = ACCION_CREAR;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnCancelarFocusGained

    }//GEN-LAST:event_btnCancelarFocusGained

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        txtDescripcion.setText(txtDescripcion.getText().toUpperCase());
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyPressed

    private void tableTareasDespuesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTareasDespuesKeyTyped
        //        try {
        //            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        //                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
        //                    obtenerConcepto();
        //                }
        //            }
        //        } catch (Exception e) {
        //            Mensaje.mostrarErrorSistema(this);
        //        }
    }//GEN-LAST:event_tableTareasDespuesKeyTyped

    private void tableTareasDespuesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTareasDespuesKeyReleased
        fila = tableTareasDespues.getSelectedRow();
    }//GEN-LAST:event_tableTareasDespuesKeyReleased

    private void tableTareasDespuesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTareasDespuesMousePressed
        fila = tableTareasDespues.getSelectedRow();
        obtenerTrabajadorSeleccionado();
        llenarTextoCampos(tareaDiaria);
        tipo_accion = ACCION_MODIFICAR;
        inabilitarTextos(true);
        btnAgregar.setText("Guardar");
    }//GEN-LAST:event_tableTareasDespuesMousePressed

    private void cboEstadTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadTareaActionPerformed

    private void tableTareasDespuesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTareasDespuesKeyPressed
        fila = tableTareasDespues.getSelectedRow();

        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            metodoParaEliminar();
        }
    }//GEN-LAST:event_tableTareasDespuesKeyPressed

    private void obtenerTrabajadorSeleccionado() {
        try {
            String codigo = tableTareasDespues.getValueAt(fila, 0).toString();
            tareaDiaria = gestionarTareaDiariaServicio.buscarTareaDiariaPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    public void inabilitarTextos(boolean estado) {
        txtDescripcion.setEnabled(estado);
        dataTarea.setEnabled(estado);
        cboEstadTarea.setEnabled(estado);
    }

    private boolean verificarCamposVacios() {
        int contadr = 0, aux = 0;
        contadr = Verificador.verificadorCampos(txtDescripcion);
        aux = contadr + aux;
        return aux == 1;
    }
    
    

    private void guardarDatos() {
        tareaDiaria = null;
        tareaDiaria = new TareaDiaria();
        if (verificarCamposVacios()) {
            tareaDiaria.setDescripcion(txtDescripcion.getText());
            tareaDiaria.setPersonal(IniciarSesion.getUsuario());
            tareaDiaria.setEmpresa(IniciarSesion.getUsuario().getEmpresa());
            Date fecha = dataTarea.getDatoFecha();
            tareaDiaria.setFecha(new java.sql.Date(fecha.getTime()));
            Estado estado = cboEstadoTareaDiaria.getItemAt(cboEstadTarea.getSelectedIndex());
            tareaDiaria.setEstado(estado);
            int registros_afectados;
            if (tipo_accion == ACCION_CREAR) {
                try {
                    registros_afectados = gestionarTareaDiariaServicio.guardarTareaDiaria(tareaDiaria);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeCreacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba crear una nueva tareaDiaria", 7);
                        inicializarTabla();
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeCreacion(this);
                    }
                } catch (Exception e) {
                    Mensaje.mostrarErrorDeCreacion(this);
                }
            } else if (tipo_accion == ACCION_MODIFICAR) {
                try {
                    tareaDiaria.setCodigo(codigoTareaDiaria);
                    registros_afectados = gestionarTareaDiariaServicio.modificarTareaDiaria(tareaDiaria);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeActualizacion(this);
                        DesktopNotify.showDesktopMessage("FiveCod Software", "Usted Acaba crear una nueva tareaDiaria", 7);
                        inicializarTabla();
                        tipo_accion = ACCION_CREAR;
                    } else if (registros_afectados == 0) {
                        Mensaje.mostrarAdvertenciaDeActualizacion(this);
                    }
                } catch (Exception e) {

                    Mensaje.mostrarErrorDeActualizacion(this);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCambiarHecho;
    private rojeru_san.RSButton btnCambiarPorHacer;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnEliminar;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<Estado> cboEstadTarea;
    private FiveCodMaterilalDesignComboBox.MaterialComboBox<Estado> cboEstadoTareaDiaria;
    private rojeru_san.componentes.RSDateChooser dataTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSTableMetro tableTareasDespues;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        txtDescripcion.setText("");
        dataTarea.setDatoFecha(new Date());
        cboEstadTarea.setSelectedIndex(0);

    }

    public void llenarTextoCampos(TareaDiaria tareaDiaria) {
        if (tareaDiaria != null) {
            txtDescripcion.setText(tareaDiaria.getDescripcion());
            dataTarea.setDatoFecha(tareaDiaria.getFecha());
            codigoTareaDiaria = tareaDiaria.getCodigo();
            if (tareaDiaria.getEstado().getCodigo() == 12) {
                cboEstadTarea.setSelectedIndex(0);
            } else {
                cboEstadTarea.setSelectedIndex(1);

            }
        }

    }

    private void metodoParaEliminar() {
        try {
            obtenerTrabajadorSeleccionado();
            if (tareaDiaria != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = gestionarTareaDiariaServicio.eliminarTareaDiaria(tareaDiaria.getCodigo());
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        inicializarTabla();
                        inicializarNotificacionesTareas();

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
