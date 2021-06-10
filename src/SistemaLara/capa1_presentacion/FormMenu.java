package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.DesktopNotify;
import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.IniciarDesktop;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarPermisosDetallePersonalServicio;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.PermisosDetallePersonal;
import SistemaLara.capa8_timer.TimerCumpleaños;
import SistemaLara.capa8_timer.TimerPagosTrabajador;
import SistemaLara.capa8_timer.TimerTareasDiarias;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rojeru_san.RSButton;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author XGamer
 */
public class FormMenu extends javax.swing.JFrame {

    public static int anchoPantalla;
    public static int alturaPantalla;
    private static List<RSButton> listaBotones;
    private boolean minimiza = false;
    private JPanel componenteSeleccionado = null;
    public static int ancho_para_formularios = 0;
    public static int posicion_x_form_menu_axuliar = 0;
    private int opcion_seleccionada = 0;
    private int OPCION_CLIENTE_ENTRANTE = 1;
    private int OPCION_PROVEEDOR_SERVICIO = 2;
    private int OPCION_LIQUIDACION = 3;
    private int OPCION_PAGO_TRANSPORTE = 4;
    private int OPCION_FACTURA = 5;
    private int OPCION_ADELANTOS = 6;
    private int OPCION_GASTOS_EXTRAS = 7;
    private int OPCION_VALORIZACION = 8;
    private int OPCION_TAREAS_DIARIAS = 9;
    private int OPCION_MENU_PRINCIPAL = 10;
    private int OPCION_PERSONAL = 11;
    public static int ANCHO_PANTALLA_PEQUEÑA = 1;

    public static boolean ACCION_INICIO_MENU_PRINCIPAL = false;
    public static int ANCHO_PANTALLA_GRANDE = 2;
    public static int opcion_ancho_pantalla = ANCHO_PANTALLA_PEQUEÑA;

    private static GestionarPermisosDetallePersonalServicio gestionarPermisosDetallePersonalServicio = new GestionarPermisosDetallePersonalServicio();

    /**
     * Creates new form FormMenu
     */
    public FormMenu() {

        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        cargarListaBotones();
        fullScreen();

        inicializadoresNotificaciones();
        componenteSeleccionado = formMenuPrincipalEmpresa1;
        opcion_seleccionada = OPCION_MENU_PRINCIPAL;
        ancho_para_formularios = anchoPantalla;

        inicializador();
        estadoBotonesMenu(false);
        verificarPermisosUsuario();

    }

    private static void verificarPermisosUsuario() {
        for (PermisosDetallePersonal permisosDetallePersonal : IniciarSesion.getUsuario().getListaDetallePermisos()) {
            if (permisosDetallePersonal.getEstado().getCodigo() == 1) {
                activarOpcion(permisosDetallePersonal.getPermisos().getDescripcion().toString());
            }

        }
    }

    private void inicializador() {
        lblTituloEmpresa.setText("BIENVENIDO A LA EMPRESA " + IniciarSesion.getUsuario().getEmpresa().getNombreComercial());

        //  lblNombreUsuario.setText(IniciarSesion.getUsuario().getGenerarNombre());
        lblNombreUsuario.setText(IniciarSesion.getUsuario().getGenerarNombre());

        lblTipo.setText(IniciarSesion.getUsuario().getTipoPersonal().getDescripcion());

    }

    private static void estadoBotonesMenu(boolean estado) {
        btnClienteEntrante.setEnabled(estado);
        btnProveedoresServicio.setEnabled(estado);
        btnPersonal.setEnabled(estado);
        btnAdalantos.setEnabled(estado);
        btnLiquidacion.setEnabled(estado);
        btnPagoTransporte.setEnabled(estado);
        btnFactura.setEnabled(estado);
        btnGastosExtras.setEnabled(estado);
        btnTareas.setEnabled(estado);
        btnValorizacion.setEnabled(estado);
        btnElectronicas.setEnabled(estado);
    }

    public static void obtenerPermisosActualizados() {
        try {
            IniciarSesion.getUsuario().setListaDetallePermisos(gestionarPermisosDetallePersonalServicio.obtenerListaPermisosPorPersonal(IniciarSesion.getUsuario().getCodigo()));
            estadoBotonesMenu(false);
            verificarPermisosUsuario();
        } catch (Exception e) {
        }

    }

    private static void activarOpcion(String opcion) {
        for (RSButton boButton : listaBotones) {
            if (boButton.getText().toString().equals(opcion)) {
                boButton.setEnabled(true);
            }
        }
    }

    private void inicializadoresNotificaciones() {
        IniciarDesktop a = new IniciarDesktop();
        a.llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);
    }

//    void inicializadores() {
//        IniciarDesktop a = new IniciarDesktop();
//        a.llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliar);
//        a.llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliar);
//    }
    void cargarListaBotones() {
        listaBotones = new ArrayList<RSButton>();
        listaBotones.add(btnClienteEntrante);
        listaBotones.add(btnProveedoresServicio);
        listaBotones.add(btnPersonal);
        listaBotones.add(btnAdalantos);
        listaBotones.add(btnLiquidacion);
        listaBotones.add(btnPagoTransporte);
        listaBotones.add(btnFactura);
        listaBotones.add(btnGastosExtras);
        listaBotones.add(btnTareas);
        listaBotones.add(btnValorizacion);
        listaBotones.add(btnMenuPrinciapal);
        listaBotones.add(btnElectronicas);
    }

    public void fullScreen() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension miPantalla = tk.getScreenSize();
        anchoPantalla = miPantalla.width;
        Animacion.anchoPantalla = anchoPantalla - pnlMenu.getWidth();
        alturaPantalla = miPantalla.height;
    }

//    private void CambiarImagen() {
//        ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Minera.png"));
//        Image Image = ImageIcon.getImage();
//        this.setIconImage(Image);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        pnlSlider = new rojerusan.RSPanelsSlider();
        formMenuPrincipalEmpresa1 = new SistemaLara.capa1_presentacion.FormMenuPrincipalEmpresa();
        formMenuPersonal1 = new SistemaLara.capa1_presentacion.FormMenuPersonal();
        formMenuAdalantos1 = new SistemaLara.capa1_presentacion.FormMenuAdalantos();
        formMenuClienteEntrante1 = new SistemaLara.capa1_presentacion.FormMenuClienteEntrante();
        formMenuGastosExtras1 = new SistemaLara.capa1_presentacion.FormMenuGastosExtras();
        formMenuLiquidacion1 = new SistemaLara.capa1_presentacion.FormMenuLiquidacion();
        formMenuPagoTransporte1 = new SistemaLara.capa1_presentacion.FormMenuPagoTransporte();
        formMenuProveedorServicios1 = new SistemaLara.capa1_presentacion.FormMenuProveedorServicios();
        formMenuValorizacion1 = new SistemaLara.capa1_presentacion.FormMenuValorizacion();
        formMenuFacturacion1 = new SistemaLara.capa1_presentacion.FormMenuFacturacion();
        formMenuElectronico1 = new SistemaLara.capa1_presentacion.FormMenuElectronico();
        pnlMenu = new javax.swing.JPanel();
        btnPersonal = new rojeru_san.RSButton();
        btnClienteEntrante = new rojeru_san.RSButton();
        btnProveedoresServicio = new rojeru_san.RSButton();
        btnLiquidacion = new rojeru_san.RSButton();
        btnPagoTransporte = new rojeru_san.RSButton();
        btnFactura = new rojeru_san.RSButton();
        btnAdalantos = new rojeru_san.RSButton();
        btnGastosExtras = new rojeru_san.RSButton();
        btnValorizacion = new rojeru_san.RSButton();
        btnTareas = new rojeru_san.RSButton();
        btnTareas1 = new rojeru_san.RSButton();
        btnMenuPrinciapal = new rojeru_san.RSButton();
        btnElectronicas = new rojeru_san.RSButton();
        fiveCodPanelGradiente1 = new fivecodpanelgradiente.FiveCodPanelGradiente();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        rSButton1 = new rojeru_san.RSButton();
        btnRe = new rojeru_san.RSButton();
        btnMi = new rojeru_san.RSButton();
        lblTituloEmpresa = new javax.swing.JLabel();
        rSPanelImage3 = new rojerusan.RSPanelImage();
        lblCumpleaños = new javax.swing.JLabel();
        btnNotificacionTareas = new rojerusan.RSPanelImage();
        lblNotificacionTareas = new javax.swing.JLabel();
        btnNotificacionPagosPersonal = new rojerusan.RSPanelImage();
        lblPagoContrato = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        btnVolverMenu = new SistemaLara.capa1_presentacion.util.FCMaterialIconButton();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        lblBienveniudo = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        rSPanelsSlider2.setBackground(new java.awt.Color(255, 255, 255));

        formMenuPrincipalEmpresa1.setName("formMenuPrincipalEmpresa1"); // NOI18N
        pnlSlider.add(formMenuPrincipalEmpresa1, "card11");

        formMenuPersonal1.setName("formMenuPersonal1"); // NOI18N
        pnlSlider.add(formMenuPersonal1, "card9");

        formMenuAdalantos1.setName("formMenuAdalantos1"); // NOI18N
        pnlSlider.add(formMenuAdalantos1, "card3");

        formMenuClienteEntrante1.setName("formMenuClienteEntrante1"); // NOI18N
        pnlSlider.add(formMenuClienteEntrante1, "card4");

        formMenuGastosExtras1.setName("formMenuGastosExtras1"); // NOI18N
        pnlSlider.add(formMenuGastosExtras1, "card6");

        formMenuLiquidacion1.setName("formMenuLiquidacion1"); // NOI18N
        pnlSlider.add(formMenuLiquidacion1, "card7");

        formMenuPagoTransporte1.setName("formMenuPagoTransporte1"); // NOI18N
        pnlSlider.add(formMenuPagoTransporte1, "card8");

        formMenuProveedorServicios1.setName("formMenuProveedorServicios1"); // NOI18N
        pnlSlider.add(formMenuProveedorServicios1, "card10");

        formMenuValorizacion1.setName("formMenuValorizacion1"); // NOI18N
        pnlSlider.add(formMenuValorizacion1, "card11");

        formMenuFacturacion1.setName("formMenuFacturacion1"); // NOI18N
        pnlSlider.add(formMenuFacturacion1, "card11");

        formMenuElectronico1.setName("formMenuElectronico1"); // NOI18N
        pnlSlider.add(formMenuElectronico1, "card12");

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPersonal.setBackground(new java.awt.Color(65, 94, 255));
        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaPersonal.png"))); // NOI18N
        btnPersonal.setText("PERSONAL");
        btnPersonal.setColorHover(new java.awt.Color(255, 82, 54));
        btnPersonal.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnPersonal.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPersonal.setIconTextGap(2);
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });
        pnlMenu.add(btnPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        btnClienteEntrante.setBackground(new java.awt.Color(65, 94, 255));
        btnClienteEntrante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaClienteEntrante.png"))); // NOI18N
        btnClienteEntrante.setText("CLIENTE ENTRANTE");
        btnClienteEntrante.setColorHover(new java.awt.Color(255, 82, 54));
        btnClienteEntrante.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnClienteEntrante.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnClienteEntrante.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClienteEntrante.setIconTextGap(2);
        btnClienteEntrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteEntranteActionPerformed(evt);
            }
        });
        pnlMenu.add(btnClienteEntrante, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        btnProveedoresServicio.setBackground(new java.awt.Color(65, 94, 255));
        btnProveedoresServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaProveedorServicio.png"))); // NOI18N
        btnProveedoresServicio.setText("PROVEEDOR SERVICIO");
        btnProveedoresServicio.setColorHover(new java.awt.Color(255, 82, 54));
        btnProveedoresServicio.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnProveedoresServicio.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnProveedoresServicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnProveedoresServicio.setIconTextGap(2);
        btnProveedoresServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresServicioActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProveedoresServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        btnLiquidacion.setBackground(new java.awt.Color(65, 94, 255));
        btnLiquidacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaLiquidacion.png"))); // NOI18N
        btnLiquidacion.setText("LIQUIDACIÓN");
        btnLiquidacion.setColorHover(new java.awt.Color(255, 82, 54));
        btnLiquidacion.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnLiquidacion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnLiquidacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLiquidacion.setIconTextGap(2);
        btnLiquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiquidacionActionPerformed(evt);
            }
        });
        pnlMenu.add(btnLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        btnPagoTransporte.setBackground(new java.awt.Color(65, 94, 255));
        btnPagoTransporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaPagoTransporte.png"))); // NOI18N
        btnPagoTransporte.setText("PAGO TRANSPORTE");
        btnPagoTransporte.setColorHover(new java.awt.Color(255, 82, 54));
        btnPagoTransporte.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnPagoTransporte.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPagoTransporte.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPagoTransporte.setIconTextGap(2);
        btnPagoTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoTransporteActionPerformed(evt);
            }
        });
        pnlMenu.add(btnPagoTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        btnFactura.setBackground(new java.awt.Color(65, 94, 255));
        btnFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaFactura.png"))); // NOI18N
        btnFactura.setText("FACTURACION");
        btnFactura.setColorHover(new java.awt.Color(255, 82, 54));
        btnFactura.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnFactura.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnFactura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFactura.setIconTextGap(2);
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });
        pnlMenu.add(btnFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        btnAdalantos.setBackground(new java.awt.Color(65, 94, 255));
        btnAdalantos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaAdelanto.png"))); // NOI18N
        btnAdalantos.setText("ADELANTOS");
        btnAdalantos.setColorHover(new java.awt.Color(255, 82, 54));
        btnAdalantos.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnAdalantos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAdalantos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAdalantos.setIconTextGap(2);
        btnAdalantos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdalantosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnAdalantos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        btnGastosExtras.setBackground(new java.awt.Color(65, 94, 255));
        btnGastosExtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaGastosExtras.png"))); // NOI18N
        btnGastosExtras.setText("GASTOS EXTRAS");
        btnGastosExtras.setColorHover(new java.awt.Color(255, 82, 54));
        btnGastosExtras.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnGastosExtras.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGastosExtras.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGastosExtras.setIconTextGap(2);
        btnGastosExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGastosExtrasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnGastosExtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, -1, -1));

        btnValorizacion.setBackground(new java.awt.Color(65, 94, 255));
        btnValorizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaValorizacion.png"))); // NOI18N
        btnValorizacion.setText("VALORIZACION");
        btnValorizacion.setColorHover(new java.awt.Color(255, 82, 54));
        btnValorizacion.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnValorizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnValorizacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnValorizacion.setIconTextGap(2);
        btnValorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValorizacionActionPerformed(evt);
            }
        });
        pnlMenu.add(btnValorizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        btnTareas.setBackground(new java.awt.Color(65, 94, 255));
        btnTareas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaReporte.png"))); // NOI18N
        btnTareas.setText("TAREAS DIARIAS");
        btnTareas.setColorHover(new java.awt.Color(255, 82, 54));
        btnTareas.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnTareas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTareas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTareas.setIconTextGap(2);
        btnTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTareasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnTareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, -1, -1));

        btnTareas1.setBackground(new java.awt.Color(255, 51, 51));
        btnTareas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuAtras.png"))); // NOI18N
        btnTareas1.setText("Cerrar sesion");
        btnTareas1.setColorHover(new java.awt.Color(255, 82, 54));
        btnTareas1.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnTareas1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTareas1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTareas1.setIconTextGap(2);
        btnTareas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTareas1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnTareas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, -1, 20));

        btnMenuPrinciapal.setBackground(new java.awt.Color(65, 94, 255));
        btnMenuPrinciapal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaPersonal.png"))); // NOI18N
        btnMenuPrinciapal.setText("MENU");
        btnMenuPrinciapal.setColorHover(new java.awt.Color(255, 82, 54));
        btnMenuPrinciapal.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnMenuPrinciapal.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnMenuPrinciapal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMenuPrinciapal.setIconTextGap(2);
        btnMenuPrinciapal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrinciapalActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuPrinciapal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnElectronicas.setBackground(new java.awt.Color(65, 94, 255));
        btnElectronicas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuEmpresaFactura.png"))); // NOI18N
        btnElectronicas.setText("ELECTRONICAS");
        btnElectronicas.setColorHover(new java.awt.Color(255, 82, 54));
        btnElectronicas.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnElectronicas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnElectronicas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnElectronicas.setIconTextGap(2);
        btnElectronicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElectronicasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnElectronicas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, -1, -1));

        fiveCodPanelGradiente1.setColorPrimario(new java.awt.Color(96, 193, 213));
        fiveCodPanelGradiente1.setColorSecundario(new java.awt.Color(156, 162, 225));
        fiveCodPanelGradiente1.setGradiente(fivecodpanelgradiente.FiveCodPanelGradiente.Gradiente.VERTICAL);
        fiveCodPanelGradiente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fiveCodPanelGradiente1MouseClicked(evt);
            }
        });

        rSButton1.setBackground(new java.awt.Color(152, 163, 224));
        rSButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoSalir.png"))); // NOI18N
        rSButton1.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton1ActionPerformed(evt);
            }
        });

        btnRe.setBackground(new java.awt.Color(152, 163, 224));
        btnRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Icono Restaurar.png"))); // NOI18N
        btnRe.setColorHover(new java.awt.Color(255, 68, 68));
        btnRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReActionPerformed(evt);
            }
        });

        btnMi.setBackground(new java.awt.Color(152, 163, 224));
        btnMi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Icono Minimizar.png"))); // NOI18N
        btnMi.setColorHover(new java.awt.Color(255, 68, 68));
        btnMi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rSPanelsSlider1Layout = new javax.swing.GroupLayout(rSPanelsSlider1);
        rSPanelsSlider1.setLayout(rSPanelsSlider1Layout);
        rSPanelsSlider1Layout.setHorizontalGroup(
            rSPanelsSlider1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider1Layout.createSequentialGroup()
                .addComponent(btnMi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(rSButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rSPanelsSlider1Layout.setVerticalGroup(
            rSPanelsSlider1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblTituloEmpresa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTituloEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuCaritaFeliz.png"))); // NOI18N
        lblTituloEmpresa.setText("Bienvenido !!");
        lblTituloEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTituloEmpresaMouseClicked(evt);
            }
        });

        rSPanelImage3.setImagen(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CumpleañosNuevo.png"))); // NOI18N
        rSPanelImage3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSPanelImage3MouseClicked(evt);
            }
        });
        rSPanelImage3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCumpleaños.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCumpleaños.setForeground(new java.awt.Color(255, 0, 102));
        lblCumpleaños.setText("0");
        rSPanelImage3.add(lblCumpleaños, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 10, -1));

        btnNotificacionTareas.setImagen(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NotificacionTarea.png"))); // NOI18N
        btnNotificacionTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNotificacionTareasMouseClicked(evt);
            }
        });
        btnNotificacionTareas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNotificacionTareas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNotificacionTareas.setForeground(new java.awt.Color(255, 0, 102));
        lblNotificacionTareas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNotificacionTareas.setText("0");
        lblNotificacionTareas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNotificacionTareas.add(lblNotificacionTareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 0, -1, -1));

        btnNotificacionPagosPersonal.setImagen(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/NotificacionPagoPersonal.png"))); // NOI18N
        btnNotificacionPagosPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNotificacionPagosPersonalMouseClicked(evt);
            }
        });
        btnNotificacionPagosPersonal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPagoContrato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPagoContrato.setForeground(new java.awt.Color(255, 0, 102));
        lblPagoContrato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagoContrato.setText("0");
        lblPagoContrato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNotificacionPagosPersonal.add(lblPagoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 0, -1, -1));

        lblNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setText("Bienvenido !!");
        lblNombreUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombreUsuarioMouseClicked(evt);
            }
        });

        btnVolverMenu.setBorder(null);
        btnVolverMenu.setForeground(new java.awt.Color(255, 68, 68));
        btnVolverMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/MenuMenu.png"))); // NOI18N
        btnVolverMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVolverMenu.setIconTextGap(0);
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/WelcomePersonal.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        lblBienveniudo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBienveniudo.setForeground(new java.awt.Color(255, 255, 255));
        lblBienveniudo.setText("Welcome...");
        lblBienveniudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBienveniudoMouseClicked(evt);
            }
        });

        lblTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Bienvenido !!");
        lblTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTipoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fiveCodPanelGradiente1Layout = new javax.swing.GroupLayout(fiveCodPanelGradiente1);
        fiveCodPanelGradiente1.setLayout(fiveCodPanelGradiente1Layout);
        fiveCodPanelGradiente1Layout.setHorizontalGroup(
            fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fiveCodPanelGradiente1Layout.createSequentialGroup()
                .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addComponent(btnNotificacionPagosPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNotificacionTareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(fiveCodPanelGradiente1Layout.createSequentialGroup()
                .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBienveniudo, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fiveCodPanelGradiente1Layout.setVerticalGroup(
            fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fiveCodPanelGradiente1Layout.createSequentialGroup()
                .addGroup(fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fiveCodPanelGradiente1Layout.createSequentialGroup()
                        .addGroup(fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTituloEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolverMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fiveCodPanelGradiente1Layout.createSequentialGroup()
                                .addComponent(lblBienveniudo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(lblTipo))))
                    .addGroup(fiveCodPanelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rSPanelImage3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnNotificacionTareas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNotificacionPagosPersonal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rSPanelsSlider2Layout = new javax.swing.GroupLayout(rSPanelsSlider2);
        rSPanelsSlider2.setLayout(rSPanelsSlider2Layout);
        rSPanelsSlider2Layout.setHorizontalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(fiveCodPanelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rSPanelsSlider2Layout.setVerticalGroup(
            rSPanelsSlider2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(pnlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rSPanelsSlider2Layout.createSequentialGroup()
                .addComponent(fiveCodPanelGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDraggedFrame(evt, this);        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        if (!btnPersonal.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnPersonal);
            pnlSlider.slidPanel(1, formMenuPersonal1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuPersonal1;
            opcion_seleccionada = OPCION_PERSONAL;
        }
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void btnClienteEntranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteEntranteActionPerformed
        if (!btnClienteEntrante.isSelected()) {
            //  btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnClienteEntrante);
            pnlSlider.slidPanel(1, formMenuClienteEntrante1, RSPanelsSlider.direct.Right);
            componenteSeleccionado = formMenuClienteEntrante1;
            opcion_seleccionada = OPCION_CLIENTE_ENTRANTE;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnClienteEntranteActionPerformed

    private void btnProveedoresServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresServicioActionPerformed
        if (!btnProveedoresServicio.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnProveedoresServicio);
            pnlSlider.slidPanel(1, formMenuProveedorServicios1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuProveedorServicios1;
            opcion_seleccionada = OPCION_PROVEEDOR_SERVICIO;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnProveedoresServicioActionPerformed

    private void btnLiquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiquidacionActionPerformed
        if (!btnLiquidacion.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnLiquidacion);
            pnlSlider.slidPanel(1, formMenuLiquidacion1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuLiquidacion1;
            opcion_seleccionada = OPCION_LIQUIDACION;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnLiquidacionActionPerformed

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        if (!btnFactura.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnFactura);
            pnlSlider.slidPanel(1, formMenuFacturacion1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuFacturacion1;
            opcion_seleccionada = OPCION_FACTURA;

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnPagoTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoTransporteActionPerformed
        if (!btnPagoTransporte.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnPagoTransporte);
            pnlSlider.slidPanel(1, formMenuPagoTransporte1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuPagoTransporte1;
            opcion_seleccionada = OPCION_PAGO_TRANSPORTE;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnPagoTransporteActionPerformed

    private void btnAdalantosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdalantosActionPerformed
        if (!btnAdalantos.isSelected()) {
            //btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnAdalantos);
            pnlSlider.slidPanel(1, formMenuAdalantos1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuAdalantos1;
            opcion_seleccionada = OPCION_ADELANTOS;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdalantosActionPerformed

    private void btnGastosExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGastosExtrasActionPerformed
        if (!btnGastosExtras.isSelected()) {
            //  btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnGastosExtras);
            pnlSlider.slidPanel(1, formMenuGastosExtras1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuGastosExtras1;
            opcion_seleccionada = OPCION_GASTOS_EXTRAS;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnGastosExtrasActionPerformed

    private void btnValorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValorizacionActionPerformed
        if (!btnValorizacion.isSelected()) {
            //  btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnValorizacion);
            pnlSlider.slidPanel(1, formMenuValorizacion1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuValorizacion1;
            opcion_seleccionada = OPCION_VALORIZACION;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnValorizacionActionPerformed

    private void btnReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReActionPerformed
        maximizeHandle();
    }//GEN-LAST:event_btnReActionPerformed

    private void rSButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton1ActionPerformed
        exitHandle();
    }//GEN-LAST:event_rSButton1ActionPerformed

    private void btnMiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiActionPerformed
        this.setExtendedState(ICONIFIED);
        if (!minimiza) {
            minimiza = false;
            this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        } else {
            minimiza = true;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnMiActionPerformed
    public static boolean activarParaNotificacionTareas = true;
    private void btnNotificacionTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificacionTareasMouseClicked
        if (activarParaNotificacionTareas) {
            DesktopNotify.eliminarWindowsAll();
            IniciarDesktop a = new IniciarDesktop();
            a.llenarDescktopTareasDiarias(TimerTareasDiarias.listaTareasDiariasAuxiliarHoy);
            activarParaNotificacionTareas = false;
        } else {
            DesktopNotify.eliminarWindowsAll();
            activarParaNotificacionTareas = true;
        }

        //        try {
        //            FormNotificacionCumpleaño notificaciones = new FormNotificacionCumpleaño(null, true);
        //            notificaciones.setVisible(true);
        //            lblNotificacion.setText("");
        //        } catch (Exception e) {
        //        }
    }//GEN-LAST:event_btnNotificacionTareasMouseClicked
    private boolean activarParaNotificacionPersonalCumpleaños = true;
    private void rSPanelImage3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSPanelImage3MouseClicked
//        FormNotificacionCumpleaños notificaciones = new FormNotificacionCumpleaños(null, true);
//        notificaciones.setVisible(true);
//        lblCumpleaños.setText("0");

        if (activarParaNotificacionPersonalCumpleaños) {
            DesktopNotify.eliminarWindowsAll();
            IniciarDesktop a = new IniciarDesktop();
            a.llenarDescktopCumpleaños(TimerCumpleaños.listaPersonalAuxiliarHoy);
            activarParaNotificacionPersonalCumpleaños = false;
        } else {
            DesktopNotify.eliminarWindowsAll();
            activarParaNotificacionPersonalCumpleaños = true;
        }
    }//GEN-LAST:event_rSPanelImage3MouseClicked

    private void btnTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTareasActionPerformed

        btnTareas.setSelected(true);
        // btnMenu.setSelected(false);
        inabilitarBotonesMenu(btnTareas);
        FormTareasDiarias a = new FormTareasDiarias(null, true);
        a.setVisible(true);
        btnTareas.setSelected(false);

    }//GEN-LAST:event_btnTareasActionPerformed

    private void btnTareas1ActionPerformed(java.awt.event.ActionEvent evt) {
        lblTituloEmpresa.setText("");
        IniciarSesion.ponerUsuarioNull();
        DesktopNotify.eliminarWindowsAll();
        TimerTareasDiarias.eliminarListas();
        this.dispose();
        IniciarSesion.poneIniciarSesionNull();
        FormPrincipal a = new FormPrincipal();
        a.setVisible(true);
    }

    private void lblTituloEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloEmpresaMouseClicked

    }//GEN-LAST:event_lblTituloEmpresaMouseClicked
    int anchoAuxiliar = 0;
    public static boolean activarParaNotificacionPagoContrato = true;
    private void btnNotificacionPagosPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificacionPagosPersonalMouseClicked
        if (activarParaNotificacionPagoContrato) {
            DesktopNotify.eliminarWindowsAll();
            IniciarDesktop a = new IniciarDesktop();
            a.llenarDescktopPagosContrato(TimerPagosTrabajador.listaContratatoPagosHoy);
            activarParaNotificacionPagoContrato = false;
        } else {
            DesktopNotify.eliminarWindowsAll();
            activarParaNotificacionPagoContrato = true;
        }
    }//GEN-LAST:event_btnNotificacionPagosPersonalMouseClicked
    private boolean estadoMenuDeslizable = false;
    private void btnMenuPrinciapalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrinciapalActionPerformed
        if (!btnMenuPrinciapal.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnMenuPrinciapal);
            pnlSlider.slidPanel(1, formMenuPrincipalEmpresa1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuPrincipalEmpresa1;
            opcion_seleccionada = OPCION_MENU_PRINCIPAL;
        }
    }//GEN-LAST:event_btnMenuPrinciapalActionPerformed

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        try {
            if (estadoMenuDeslizable == false) {
                estadoMenuDeslizable = true;
                //  jButton1.setVisible(true);
//                Animacion.mover_izquierda(150, 0, 2, 2, btnMenu);
                Animacion.mover_izquierda(150, -200, 2, 2, pnlMenu);

                Animacion.mover_izquierda(150, 0, 2, 2, pnlSlider);
                anchoAuxiliar = pnlSlider.getWidth();
                pnlSlider.setBounds(pnlSlider.getX(), pnlSlider.getY(), anchoPantalla, pnlSlider.getHeight());
                componenteSeleccionado.setBounds(componenteSeleccionado.getX(), componenteSeleccionado.getY(), anchoPantalla, componenteSeleccionado.getHeight());

                modificarTamañoCompoentesHijos(anchoPantalla);
                Animacion.anchoPantalla = anchoPantalla;
                opcion_ancho_pantalla = ANCHO_PANTALLA_GRANDE;
                ancho_para_formularios = anchoPantalla;
                //  jButton1.setVisible(true);
            } else {
                estadoMenuDeslizable = false;

//                Animacion.mover_derecha(0, 150, 2, 2, btnMenu);
                Animacion.mover_derecha(-200, 0, 2, 2, pnlMenu);
                Animacion.anchoPantalla = anchoPantalla - pnlMenu.getWidth();

                Animacion.mover_derecha(0, 200, 2, 2, pnlSlider);
                pnlSlider.setBounds(pnlSlider.getX(), pnlSlider.getY(), anchoAuxiliar, pnlSlider.getHeight());
                componenteSeleccionado.setBounds(componenteSeleccionado.getX(), componenteSeleccionado.getY(), anchoAuxiliar, componenteSeleccionado.getHeight());

                modificarTamañoCompoentesHijos(anchoAuxiliar);
                opcion_ancho_pantalla = ANCHO_PANTALLA_PEQUEÑA;
                ancho_para_formularios = anchoAuxiliar;

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    int contadorClick = 0;
    private void fiveCodPanelGradiente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fiveCodPanelGradiente1MouseClicked
        contadorClick++;
        if (contadorClick == 2) {
            contadorClick = 0;
            maximizeHandle();
        }
    }//GEN-LAST:event_fiveCodPanelGradiente1MouseClicked

    private void lblTipoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTipoUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTipoUsuarioMouseClicked

    private void lblBienveniudoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBienveniudoMouseClicked
        System.out.println("SistemaLara.capa1_presentacion.FormMenu.lblBienveniudoMouseClicked()");
    }//GEN-LAST:event_lblBienveniudoMouseClicked


    private void lblNombreUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombreUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNombreUsuarioMouseClicked


    private void lblTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTipoMouseClicked

    private void btnElectronicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElectronicasActionPerformed
        if (!btnElectronicas.isSelected()) {
            // btnMenu.setSelected(false);
            inabilitarBotonesMenu(btnElectronicas);
            pnlSlider.slidPanel(1, formMenuElectronico1, RSPanelsSlider.direct.Left);
            componenteSeleccionado = formMenuElectronico1;
            opcion_seleccionada = OPCION_FACTURA;

        }
    }//GEN-LAST:event_btnElectronicasActionPerformed

    public void modificarTamañoCompoentesHijos(int ancho) {

        if (opcion_seleccionada == OPCION_CLIENTE_ENTRANTE) {
            formMenuClienteEntrante1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_PROVEEDOR_SERVICIO) {
            formMenuProveedorServicios1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_LIQUIDACION) {
            formMenuLiquidacion1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_PAGO_TRANSPORTE) {
            formMenuPagoTransporte1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_FACTURA) {
            formMenuFacturacion1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_ADELANTOS) {
            formMenuAdalantos1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_GASTOS_EXTRAS) {
            formMenuGastosExtras1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_VALORIZACION) {
            formMenuValorizacion1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_MENU_PRINCIPAL) {
            formMenuPrincipalEmpresa1.modificarTamañoComponentes((ancho));
        } else if (opcion_seleccionada == OPCION_PERSONAL) {
            formMenuPersonal1.modificarTamañoComponentes((ancho));
        }
    }

    private void inabilitarBotonesMenu(RSButton butonSele) {

        for (RSButton boton : listaBotones) {
            if (boton.getText().equals(butonSele.getText())) {
                boton.setSelected(true);
            } else {
                boton.setSelected(false);
            }
        }

    }

    private void maximizeHandle() {
        if ((this.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            this.setExtendedState(this.getExtendedState() & ~JFrame.MAXIMIZED_BOTH);
        } else {
            this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
    }

    private void exitHandle() {
        // ask    
        if (!Mensaje.mostrarPreguntaDeSalir(this)) {
            return;
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static rojeru_san.RSButton btnAdalantos;
    private static rojeru_san.RSButton btnClienteEntrante;
    private static rojeru_san.RSButton btnElectronicas;
    private static rojeru_san.RSButton btnFactura;
    private static rojeru_san.RSButton btnGastosExtras;
    private static rojeru_san.RSButton btnLiquidacion;
    private static rojeru_san.RSButton btnMenuPrinciapal;
    private rojeru_san.RSButton btnMi;
    private rojerusan.RSPanelImage btnNotificacionPagosPersonal;
    private rojerusan.RSPanelImage btnNotificacionTareas;
    private static rojeru_san.RSButton btnPagoTransporte;
    private static rojeru_san.RSButton btnPersonal;
    private static rojeru_san.RSButton btnProveedoresServicio;
    private rojeru_san.RSButton btnRe;
    private static rojeru_san.RSButton btnTareas;
    private rojeru_san.RSButton btnTareas1;
    private static rojeru_san.RSButton btnValorizacion;
    private SistemaLara.capa1_presentacion.util.FCMaterialIconButton btnVolverMenu;
    private fivecodpanelgradiente.FiveCodPanelGradiente fiveCodPanelGradiente1;
    private SistemaLara.capa1_presentacion.FormMenuAdalantos formMenuAdalantos1;
    private SistemaLara.capa1_presentacion.FormMenuClienteEntrante formMenuClienteEntrante1;
    private SistemaLara.capa1_presentacion.FormMenuElectronico formMenuElectronico1;
    private SistemaLara.capa1_presentacion.FormMenuFacturacion formMenuFacturacion1;
    private SistemaLara.capa1_presentacion.FormMenuGastosExtras formMenuGastosExtras1;
    private SistemaLara.capa1_presentacion.FormMenuLiquidacion formMenuLiquidacion1;
    private SistemaLara.capa1_presentacion.FormMenuPagoTransporte formMenuPagoTransporte1;
    private SistemaLara.capa1_presentacion.FormMenuPersonal formMenuPersonal1;
    private SistemaLara.capa1_presentacion.FormMenuPrincipalEmpresa formMenuPrincipalEmpresa1;
    private SistemaLara.capa1_presentacion.FormMenuProveedorServicios formMenuProveedorServicios1;
    private SistemaLara.capa1_presentacion.FormMenuValorizacion formMenuValorizacion1;
    private javax.swing.JLabel lblBienveniudo;
    public static javax.swing.JLabel lblCumpleaños;
    private javax.swing.JLabel lblNombreUsuario;
    public static javax.swing.JLabel lblNotificacionTareas;
    public static javax.swing.JLabel lblPagoContrato;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTituloEmpresa;
    private javax.swing.JPanel pnlMenu;
    private rojerusan.RSPanelsSlider pnlSlider;
    private rojeru_san.RSButton rSButton1;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojerusan.RSPanelImage rSPanelImage3;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    // End of variables declaration//GEN-END:variables
}
