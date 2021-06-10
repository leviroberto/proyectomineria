/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarAdelantoPersonalServicio;
import SistemaLara.capa3_dominio.AdelantoPersonal;
import SistemaLara.capa3_dominio.Contrato;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarTodosAdelantoPersonalServicio extends javax.swing.JDialog {

    private GestionarAdelantoPersonalServicio gestionarAdelantoPersonalServicio;
    public static Contrato contratoSeleccionad;
    public AdelantoPersonal adelantosSeleccionado;

//    private FormDatosAdelantoPersonal formDatosAdelantoPersonal;
    private int x, y;
    int mes = 0;
    int anio = 0;

    public FormGestionarTodosAdelantoPersonalServicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
        inicializarTabla();

        gestionarAdelantoPersonalServicio = new GestionarAdelantoPersonalServicio();
        obtenerDatosTabla();
        popMenu.add(pnlMenu);

    }
  public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    private void inicializarTabla() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Código ", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha ", "java.lang.String"));
//        tabla.agregarColumna(new Columna("Motivo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Monto adelantado ", "java.lang.Double"));
        tabla.agregarColumna(new Columna("Sueldo ", "java.lang.Double"));

        tabla.agregarColumna(new Columna("Personal", "java.lang.String"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaAdelantoPersonal.setModel(modeloTabla);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setWidth(0);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setMinWidth(0);
        tablaAdelantoPersonal.getColumn(tablaAdelantoPersonal.getColumnName(0)).setMaxWidth(0);
    }

    private void limpiarTable() {
        ModeloTabla modelo = (ModeloTabla) tablaAdelantoPersonal.getModel();
        modelo.eliminarTotalFilas();
        tablaAdelantoPersonal.setModel(modelo);
    }

    private void obtenerDatosTabla() {

        try {
            limpiarTable();
            gestionarAdelantoPersonalServicio.mostrarAdelantosTodos(tablaAdelantoPersonal);
        } catch (Exception e) {
            Mensaje.mostrarMensajeDefinido(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        pnlMenu = new javax.swing.JPanel();
        btnEliminar = new rojeru_san.RSButton();
        btnModificar = new rojeru_san.RSButton();
        btnModificarAPagado = new rojeru_san.RSButton();
        btnModificarPorPagar = new rojeru_san.RSButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMesAdelanto = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAdelantoPersonal = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarAdelantoPersonal = new org.jdesktop.swingx.JXSearchField();
        lblMes = new javax.swing.JLabel();

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setAutoscrolls(true);
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        popMenu.setName("popMenu"); // NOI18N

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setForeground(new java.awt.Color(65, 94, 255));

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR REGISTRO");
        btnEliminar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setIconTextGap(0);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR REGISTRO");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setIconTextGap(0);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnModificarAPagado.setBackground(new java.awt.Color(65, 94, 255));
        btnModificarAPagado.setBorder(null);
        btnModificarAPagado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/EstadoPorPagar.png"))); // NOI18N
        btnModificarAPagado.setText("CAMBIAR /A PAGADO");
        btnModificarAPagado.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificarAPagado.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificarAPagado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarAPagado.setIconTextGap(0);
        btnModificarAPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAPagadoActionPerformed(evt);
            }
        });

        btnModificarPorPagar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificarPorPagar.setBorder(null);
        btnModificarPorPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/EstadoPorPagar.png"))); // NOI18N
        btnModificarPorPagar.setText("CAMBIAR /POR PAGAR");
        btnModificarPorPagar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificarPorPagar.setFont(new java.awt.Font("Roboto Bold", 1, 13)); // NOI18N
        btnModificarPorPagar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarPorPagar.setIconTextGap(0);
        btnModificarPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPorPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnModificarPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnModificarAPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnModificarPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(btnModificarAPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("GESTIONAR DATOS DEL ADELANTO");

        lblMesAdelanto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblMesAdelanto.setForeground(new java.awt.Color(255, 187, 51));

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

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/IconoAdelanto.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMesAdelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMesAdelanto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tablaAdelantoPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAdelantoPersonal.setAltoHead(30);
        tablaAdelantoPersonal.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tablaAdelantoPersonal.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tablaAdelantoPersonal.setComponentPopupMenu(popMenu);
        tablaAdelantoPersonal.setGrosorBordeFilas(0);
        tablaAdelantoPersonal.setGrosorBordeHead(0);
        tablaAdelantoPersonal.setRowHeight(20);
        tablaAdelantoPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaAdelantoPersonalMousePressed(evt);
            }
        });
        tablaAdelantoPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaAdelantoPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaAdelantoPersonalKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tablaAdelantoPersonal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscarAdelantoPersonal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscarAdelantoPersonal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarAdelantoPersonal.setPrompt("BUSCAR POR DNI, APELLIDOS, NOMBRES");
        txtBuscarAdelantoPersonal.setPromptBackround(null);
        txtBuscarAdelantoPersonal.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscarAdelantoPersonal.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscarAdelantoPersonal.setSelectionEnd(1);
        txtBuscarAdelantoPersonal.setSelectionStart(2);
        txtBuscarAdelantoPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarAdelantoPersonalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(793, 793, 793))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscarAdelantoPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtBuscarAdelantoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int fila;
    private void tablaAdelantoPersonalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalMousePressed
        fila = tablaAdelantoPersonal.getSelectedRow();
    }//GEN-LAST:event_tablaAdelantoPersonalMousePressed

    private void tablaAdelantoPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalKeyReleased
        fila = tablaAdelantoPersonal.getSelectedRow();
    }//GEN-LAST:event_tablaAdelantoPersonalKeyReleased

    private void tablaAdelantoPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAdelantoPersonalKeyTyped
        fila = tablaAdelantoPersonal.getSelectedRow();

    }//GEN-LAST:event_tablaAdelantoPersonalKeyTyped

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void txtBuscarAdelantoPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalKeyPressed

    }//GEN-LAST:event_txtBuscarAdelantoPersonalKeyPressed

    private void txtBuscarAdelantoPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalKeyTyped
//        String patron_de_entrada = "0123456789";
//        if (txtBuscarAdelantoPersonal.getText().length() == 8
//                || !patron_de_entrada.contains(String.valueOf(evt.getKeyChar()))) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtBuscarAdelantoPersonalKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            if (!Mensaje.mostrarPreguntaDeEliminacion(null)) {
                return;
            }

            int registrosAfectados = gestionarAdelantoPersonalServicio.eliminarAdelantoPersonalPorCodigo(Integer.parseInt(codigo));
            if (registrosAfectados == 1) {
                Mensaje.mostrarAfirmacionDeEliminacion(null);
                obtenerDatosTabla();
            } else {
                Mensaje.mostrarAdvertenciaDeEliminacion(null);
            }
        } catch (Exception ee) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
             obtenerAdelantoPersonal();
        FormDatosAdelantoPersonal a = new FormDatosAdelantoPersonal(null, true, adelantosSeleccionado);
        a.setVisible(true);
        buscarPorlike(); 
        } catch (Exception e) {
             Mensaje.mostrarFilaNoExiste(this);
        }
      
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnModificarAPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAPagadoActionPerformed
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            int numeros_afectados = gestionarAdelantoPersonalServicio.modificarAdelantoPersonalAPagado(Integer.parseInt(codigo));
            if (numeros_afectados == 1) {
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                buscarPorlike();
            } else {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        } catch (Exception e) {
             Mensaje.mostrarFilaNoExiste(this);
        }

    }//GEN-LAST:event_btnModificarAPagadoActionPerformed

    private void txtBuscarAdelantoPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAdelantoPersonalKeyReleased
        buscarPorlike();
    }//GEN-LAST:event_txtBuscarAdelantoPersonalKeyReleased

    private void btnModificarPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPorPagarActionPerformed
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            int numeros_afectados = gestionarAdelantoPersonalServicio.modificarAdelantoPersonalPorPagar(Integer.parseInt(codigo));
            if (numeros_afectados == 1) {
                Mensaje.mostrarAfirmacionDeActualizacion(this);
                buscarPorlike();
            } else {
                Mensaje.mostrarErrorDeActualizacion(this);
            }
        } catch (Exception e) {
             Mensaje.mostrarFilaNoExiste(this);
        }
    }//GEN-LAST:event_btnModificarPorPagarActionPerformed
    private void buscarPorlike() {
        try {
            String texto = txtBuscarAdelantoPersonal.getText().toString();
            if (texto.equals("")) {
                obtenerDatosTabla();
            } else {
                limpiarTable();
                gestionarAdelantoPersonalServicio.mostrarAdelantos(tablaAdelantoPersonal, texto);

            }

        } catch (Exception e) {
        }
    }

    public void obtenerAdelantoPersonal() {
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            adelantosSeleccionado = gestionarAdelantoPersonalServicio.buscarAdelantoPersonalPorCodigo(Integer.parseInt(codigo));

        } catch (Exception e) {
        }
    }

    public boolean verificarCombobox() {
        int contador = 0, aux = 0;
//        contador = Verificador.verificarCombobox(cboFiltarPorMeses, lblMes, " mes");
//        aux = contador + aux;
        return aux == 0;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private rojeru_san.RSButton btnModificarAPagado;
    private rojeru_san.RSButton btnModificarPorPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMesAdelanto;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tablaAdelantoPersonal;
    private org.jdesktop.swingx.JXSearchField txtBuscarAdelantoPersonal;
    // End of variables declaration//GEN-END:variables

    private void obtenerContratoSeleccionado() {
        try {
            String codigo = tablaAdelantoPersonal.getValueAt(fila, 0).toString();
            contratoSeleccionad = gestionarAdelantoPersonalServicio.buscarContratoSeleccionado(Integer.parseInt(codigo));
        } catch (Exception e) {
        }

    }
}
