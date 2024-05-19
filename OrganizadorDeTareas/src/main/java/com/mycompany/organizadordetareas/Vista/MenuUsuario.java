/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.organizadordetareas.Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.mycompany.organizadordetareas.Controlador.*;
/**
 *
 * @author Usuario
 */
public class MenuUsuario extends javax.swing.JFrame {
//Atributo Usuario para poder acceder a su nombre y su ruta
   private CrearEvento crearEv;
   private CrearTarea crearTar;
   Usuario user;
   GestorGeneral g;
    /**
     * Creates new form MenuUsuario
     * @param u es el usuario que inicio sesion
     */
    public MenuUsuario(Usuario u) {
        user = u;
        g = new GestorGeneral(u);
        initComponents();
        meterEventos();
        meterTareas();
        buscadores();
    }
    
    private void buscadores(){
        txtBuscarEvento.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtBuscarEvento.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscarEvento.getText().isEmpty()) {
                    txtBuscarEvento.setText("Buscar Evento");
                }
            }
        });

        txtBuscarTarea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtBuscarTarea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscarTarea.getText().isEmpty()) {
                    txtBuscarTarea.setText("Buscar Tarea");
                }
            }
        });

        // Agregar listener para la tecla "Enter"
        txtBuscarEvento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nombre = txtBuscarEvento.getText();
                if(nombre!=null){
                    Evento posibleEvento = g.buscarEventoObjeto(nombre);
                    if(posibleEvento!=null){
                        //Lanzar un frame para mostrar tarea y evento

                    }else{
                        JOptionPane.showMessageDialog(null, "No se logro encontrar su evento");
                    }
                }
                
            }
        });

        txtBuscarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nombre = txtBuscarTarea.getText();
                if(nombre!=null){
                    Tarea posibleTarea = g.buscarTareaObjeto(nombre);
                    if(posibleTarea!=null){
                        //Lanzar un frame para mostrar tarea y evento
                    }else{
                        JOptionPane.showMessageDialog(null, "No se logro encontrar su tarea");
                    }
                }
                
            }
        });
    
    }
 private void meterEventos(){
        JPanel panelEventos = new JPanel();
        panelEventos.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula
        
        // Supongamos que tienes un arreglo de nombres de eventos
        String[] nombresEventos = {};
        
        // Añadir etiquetas con los nombres de los eventos al panel
        for (String nombre : nombresEventos) {
            JLabel labelEvento = new JLabel(nombre);
            panelEventos.add(labelEvento);
        }
        scrollEvento.setViewportView(panelEventos);
        scrollEvento.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
    private void meterTareas(){
        JPanel panelTareas = new JPanel();
        panelTareas.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula
        
        // Supongamos que tienes un arreglo de nombres de eventos
        String[] nombresTareas = {};
        
        // Añadir etiquetas con los nombres de los eventos al panel
        for (String nombre : nombresTareas) {
            JLabel labelTarea = new JLabel(nombre);
            panelTareas.add(labelTarea);
        }
        scrollTarea.setViewportView(panelTareas);
        scrollTarea.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBienvenida = new javax.swing.JLabel();
        lblTarea = new javax.swing.JLabel();
        lblEvento = new javax.swing.JLabel();
        bttnAddEvento = new javax.swing.JButton();
        bttnAddTarea = new javax.swing.JButton();
        scrollEvento = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        scrollTarea = new javax.swing.JScrollPane();
        bttnEliminarTarea = new javax.swing.JButton();
        bttnEditarTarea = new javax.swing.JButton();
        bttnEliminarEvento = new javax.swing.JButton();
        bttnEditarEvento = new javax.swing.JButton();
        bttnCerrarSesion = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        txtBuscarEvento = new javax.swing.JTextField();
        txtBuscarTarea = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblBienvenida.setText("Bienvenido "+user.getNombre()+"!");
        lblBienvenida.setToolTipText("");

        lblTarea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTarea.setText("Tarea");

        lblEvento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEvento.setText("Evento");

        bttnAddEvento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bttnAddEvento.setText("+");
        bttnAddEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAddEventoActionPerformed(evt);
            }
        });

        bttnAddTarea.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bttnAddTarea.setText("+");
        bttnAddTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAddTareaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(scrollTarea);

        bttnEliminarTarea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnEliminarTarea.setText("Eliminar");
        bttnEliminarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnEliminarTareaActionPerformed(evt);
            }
        });

        bttnEditarTarea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnEditarTarea.setText("Editar");
        bttnEditarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnEditarTareaActionPerformed(evt);
            }
        });

        bttnEliminarEvento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnEliminarEvento.setText("Eliminar");
        bttnEliminarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnEliminarEventoActionPerformed(evt);
            }
        });

        bttnEditarEvento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnEditarEvento.setText("Editar");
        bttnEditarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnEditarEventoActionPerformed(evt);
            }
        });

        bttnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bttnCerrarSesion.setText("Cerrar Sesión");
        bttnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCerrarSesionActionPerformed(evt);
            }
        });

        txtBuscarEvento.setText("Buscar Evento");
        txtBuscarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEventoActionPerformed(evt);
            }
        });

        txtBuscarTarea.setText("Buscar Tarea");
        txtBuscarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEvento)
                                .addGap(33, 33, 33)
                                .addComponent(bttnAddEvento)
                                .addGap(86, 86, 86)
                                .addComponent(lblTarea)
                                .addGap(32, 32, 32)
                                .addComponent(bttnAddTarea))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(scrollEvento)
                                        .addGap(2, 2, 2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bttnEliminarEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bttnEditarEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(bttnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bttnEliminarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bttnEditarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2)
                                        .addGap(4, 4, 4)))))
                        .addGap(250, 250, 250))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(435, 435, 435)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtBuscarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(txtBuscarEvento))))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTarea)
                                .addComponent(bttnAddTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bttnAddEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEvento))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtBuscarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttnEliminarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnEditarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnEliminarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(bttnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAddEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAddEventoActionPerformed
        crearEv = new CrearEvento();
        crearEv.setMenuUs(this);
        crearEv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bttnAddEventoActionPerformed

    private void bttnAddTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAddTareaActionPerformed
        crearTar = new CrearTarea();
        crearTar.setMenuUs(this);
        crearTar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bttnAddTareaActionPerformed

    private void bttnEditarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnEditarTareaActionPerformed
        //Linkear con el frame de arrick
    }//GEN-LAST:event_bttnEditarTareaActionPerformed

    private void bttnEliminarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnEliminarTareaActionPerformed
        String input = JOptionPane.showInputDialog("Ingrese el nombre de la Tarea que quiere eliminar");
        if(g.eliminarTarea(input)){
            JOptionPane.showMessageDialog(null, "Se logro eliminar la tarea con titulo: "+input+" con exito!");
        }else{
            JOptionPane.showMessageDialog(null, "No logramos eliminar esa Tarea, por favor verifica si ingresaste su nombre de manera correcta");
        }
    }//GEN-LAST:event_bttnEliminarTareaActionPerformed

    private void bttnEliminarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnEliminarEventoActionPerformed
        String input = JOptionPane.showInputDialog("Ingrese el nombre del Evento que quiere eliminar");
        if(g.eliminarEvento(input)){
            JOptionPane.showMessageDialog(null, "Se logro eliminar el evento con titulo: "+input+" con exito!");
        }else{
            JOptionPane.showMessageDialog(null, "No logramos eliminar ese evento, por favor verifica si ingresaste su nombre de manera correcta");
        }
        
    }//GEN-LAST:event_bttnEliminarEventoActionPerformed

    private void bttnEditarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnEditarEventoActionPerformed
        //Linkear con el frame de arrick
    }//GEN-LAST:event_bttnEditarEventoActionPerformed

    private void bttnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCerrarSesionActionPerformed
        new VentanaInicio().setVisible(true);   
        this.dispose();
    }//GEN-LAST:event_bttnCerrarSesionActionPerformed

    private void txtBuscarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEventoActionPerformed
        /*String texto = txtBuscarEvento.getText();
        g.buscarEvento(texto);*/
    }//GEN-LAST:event_txtBuscarEventoActionPerformed

    private void txtBuscarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarTareaActionPerformed
        /*String texto = txtBuscarTarea.getText();
        g.buscarTarea(texto);*/
    }//GEN-LAST:event_txtBuscarTareaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUsuario(new Usuario("Arrick", "netBeans@123")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnAddEvento;
    private javax.swing.JButton bttnAddTarea;
    private javax.swing.JButton bttnCerrarSesion;
    private javax.swing.JButton bttnEditarEvento;
    private javax.swing.JButton bttnEditarTarea;
    private javax.swing.JButton bttnEliminarEvento;
    private javax.swing.JButton bttnEliminarTarea;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblEvento;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTarea;
    private javax.swing.JScrollPane scrollEvento;
    private javax.swing.JScrollPane scrollTarea;
    private javax.swing.JTextField txtBuscarEvento;
    private javax.swing.JTextField txtBuscarTarea;
    // End of variables declaration//GEN-END:variables
}