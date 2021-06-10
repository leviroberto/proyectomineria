/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;
import SistemaLara.capa2_aplicacion.GestionarFacturaServicio;
import SistemaLara.capa3_dominio.Concepto;
import java.util.List;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

/**
 *
 * @author FiveCod Software
 */
public class FormGestionarListaRespuesFacturaElectronica extends javax.swing.JDialog {

//    private List<Concepto> listaConcepto = new ArrayList<Concepto>();
    private GestionarFacturaServicio gestionarFacturaServicio;
    public static Concepto tipoTrabajadorSeleccionado;
    public static int TIPO_ADMINISTRADOR = 2;
    public static int TIPO_TRABAJADOR = 1;
    private int TIPO_USUARIO;

    public FormGestionarListaRespuesFacturaElectronica(java.awt.Frame parent, boolean modal, List<String> listaEnviadas, List<String> listaErrores) {
        super(parent, modal);
        try {
            initComponents();
            Animacion.moverParaIzquierda(this);
            this.setLocationRelativeTo(null);
//           this.TIPO_USUARIO = tipo;

            gestionarFacturaServicio = new GestionarFacturaServicio();
            inicializarTablaEnviadas();
            inicializarTablaErrores();
            llenarTableEnviados(listaEnviadas);
            llenarTableErrores(listaErrores);
//            inabilitarBotones(true);
            popMenu.add(pnlMenu);
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

     public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane4.getHorizontalScrollBar().setUI(new RSScrollBar());
    }
    
    
    private void inicializarTablaEnviadas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("NUMERO FACTURA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableFacturasEnviadas.setModel(modeloTabla);
    }

    private void inicializarTablaErrores() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("NUMERO FACTURA", "java.lang.String"));
        tabla.agregarColumna(new Columna("ESTADO", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableFasturasErrores.setModel(modeloTabla);
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableFacturasEnviadas.getModel();
        modelo.eliminarTotalFilas();
    }

    private void llenarTableEnviados(List<String> listaEnviados) {
        try {
            ModeloTabla modelo = (ModeloTabla) tableFacturasEnviadas.getModel();
            Fila filaTabla;
            for (String factura : listaEnviados) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(factura);
                filaTabla.agregarValorCelda("CORRECTO");

                modelo.agregarFila(filaTabla);
            }
            tableFacturasEnviadas.setModel(modelo);
        } catch (Exception e) {
        }

    }

    private void llenarTableErrores(List<String> listaEnviados) {
        try {
            ModeloTabla modelo = (ModeloTabla) tableFasturasErrores.getModel();
            Fila filaTabla;
            for (String factura : listaEnviados) {
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(factura);
                filaTabla.agregarValorCelda("INCORRECTO");

                modelo.agregarFila(filaTabla);
            }
            tableFasturasErrores.setModel(modelo);
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
        rSButton2 = new rojeru_san.RSButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFacturasEnviadas = new rojerusan.RSTableMetro();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableFasturasErrores = new rojerusan.RSTableMetro();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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
        jLabel1.setText("RESPUESTA DE FACTURA ELECTRONICA");

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
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factura.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tableFacturasEnviadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableFacturasEnviadas.setAltoHead(30);
        tableFacturasEnviadas.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableFacturasEnviadas.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableFacturasEnviadas.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableFacturasEnviadas.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableFacturasEnviadas.setComponentPopupMenu(popMenu);
        tableFacturasEnviadas.setGrosorBordeFilas(0);
        tableFacturasEnviadas.setGrosorBordeHead(0);
        tableFacturasEnviadas.setRowHeight(20);
        tableFacturasEnviadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableFacturasEnviadasMousePressed(evt);
            }
        });
        tableFacturasEnviadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableFacturasEnviadasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableFacturasEnviadasKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableFacturasEnviadas);

        tableFasturasErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableFasturasErrores.setAltoHead(30);
        tableFasturasErrores.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableFasturasErrores.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableFasturasErrores.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableFasturasErrores.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableFasturasErrores.setComponentPopupMenu(popMenu);
        tableFasturasErrores.setGrosorBordeFilas(0);
        tableFasturasErrores.setGrosorBordeHead(0);
        tableFasturasErrores.setRowHeight(20);
        tableFasturasErrores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableFasturasErroresMousePressed(evt);
            }
        });
        tableFasturasErrores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableFasturasErroresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableFasturasErroresKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tableFasturasErrores);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 94, 255));
        jLabel3.setText("FACTURAS CON ERROR");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(65, 94, 255));
        jLabel4.setText("FACTURAS ENVIADAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void obtenerDatosParaTabla() throws Exception {
//        listaConcepto = gestionarConceptoServicio.mostrarConceptos(Concepto.ESTADO_ACTIVO);
//        obtenerDatosTabla();
    }
    int fila;
    private void tableFacturasEnviadasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFacturasEnviadasMousePressed
        fila = tableFacturasEnviadas.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableFacturasEnviadasMousePressed

    private void tableFacturasEnviadasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFacturasEnviadasKeyReleased
        fila = tableFacturasEnviadas.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableFacturasEnviadasKeyReleased

    private void tableFacturasEnviadasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFacturasEnviadasKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_TRABAJADOR) {
//                    obtenerConcepto();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableFacturasEnviadasKeyTyped

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

    private void tableFasturasErroresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFasturasErroresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableFasturasErroresMousePressed

    private void tableFasturasErroresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFasturasErroresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableFasturasErroresKeyReleased

    private void tableFasturasErroresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableFasturasErroresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tableFasturasErroresKeyTyped

    private void obtenerConceptoSeleccionado() {

    }
//    private void obtenerDatosTabla() {
//        Fila filaTabla;
//        try {
//            ModeloTabla modeloTabla = (ModeloTabla) tablaConceptos.getModel();
//            modeloTabla.eliminarTotalFilas();
//            if (listaConcepto.size() == 0) {
//                Mensaje.mostrarMensajeDefinido(this, "No hay tipos de trabajadores registrados ");
//            } else {
//                for (Concepto tipoTrabajador : listaConcepto) {
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

    private void inabilitarBotones(Boolean v) {

        btnModificar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tableFacturasEnviadas;
    private rojerusan.RSTableMetro tableFasturasErrores;
    // End of variables declaration//GEN-END:variables

//    private void obtenerConcepto() {
////        String codigo = tablaConceptos.getValueAt(fila, 0).toString();
////
////        try {
////            tipoTrabajadorSeleccionado = gestionarConceptoServicio.buscarConceptoPorCodigo(codigo.trim());
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
