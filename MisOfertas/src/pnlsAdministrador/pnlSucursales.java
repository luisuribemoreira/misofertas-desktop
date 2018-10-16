/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pnlsAdministrador;

import MisPaquetes.Empresa;
import MisPaquetes.Sucursal;
import static Sistema.MainSistema.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class pnlSucursales extends javax.swing.JPanel {

    /**
     * Creates new form pnlHome
     */
    public pnlSucursales() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNewSucursal = new javax.swing.JButton();
        lblRespuestaBuscar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        pnlBusqueda = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnSucursal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtFono = new javax.swing.JTextField();
        txtComuna = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ddlEmpresa = new javax.swing.JComboBox<>();
        lblRespuestaEmpresa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSucursales = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        btnNewSucursal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNewSucursal.setText("Nueva Sucursal");
        btnNewSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSucursalActionPerformed(evt);
            }
        });

        lblRespuestaBuscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRespuestaBuscar.setForeground(java.awt.Color.red);
        lblRespuestaBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRespuestaBuscar.setText("Respuesta");

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 3), "Búsqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.setText("Ingrese ID de la Sucursal");
        txtBuscar.setToolTipText("");
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarMouseClicked(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar)
                .addContainerGap())
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnBuscar)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addContainerGap())
        );

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        pnSucursal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 3), "Sucursal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Dirección");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Teléfono:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Comuna:");

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtFono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtComuna.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("ID Sucursal");

        txtId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Empresa:");

        ddlEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ddlEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE EMPRESA ASOCIADA" }));

        lblRespuestaEmpresa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRespuestaEmpresa.setForeground(java.awt.Color.red);
        lblRespuestaEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRespuestaEmpresa.setText("Respuesta");

        javax.swing.GroupLayout pnSucursalLayout = new javax.swing.GroupLayout(pnSucursal);
        pnSucursal.setLayout(pnSucursalLayout);
        pnSucursalLayout.setHorizontalGroup(
            pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSucursalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnSucursalLayout.createSequentialGroup()
                                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnSucursalLayout.createSequentialGroup()
                                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtComuna, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pnSucursalLayout.createSequentialGroup()
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnSucursalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)))
                        .addGap(57, 57, 57))
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(ddlEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(305, Short.MAX_VALUE))))
            .addGroup(pnSucursalLayout.createSequentialGroup()
                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(txtDireccion)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSucursalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRespuestaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        pnSucursalLayout.setVerticalGroup(
            pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSucursalLayout.createSequentialGroup()
                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(pnSucursalLayout.createSequentialGroup()
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(ddlEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRespuestaEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnSucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tablaSucursales.setBackground(new java.awt.Color(240, 240, 240));
        tablaSucursales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaSucursales.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSucursalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSucursales);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnNewSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRespuestaBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRespuestaBuscar)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSucursalActionPerformed
        vistaDefault();
        txtDireccion.setEnabled(true);
        txtNombre.setEnabled(true);
        txtFono.setEnabled(true);
        txtComuna.setEnabled(true);
        ddlEmpresa.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNewSucursalActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        txtDireccion.setEnabled(true);
        txtNombre.setEnabled(true);
        txtFono.setEnabled(true);
        txtComuna.setEnabled(true);
        ddlEmpresa.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        txtBuscar.setText("");
    }//GEN-LAST:event_txtBuscarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarSucursal();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Sucursal suc = new Sucursal();
        suc.setId_sucur(Integer.parseInt(txtId.getText()));
        int respuesta = 0;
        int a = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar esta Sucursal?", "Message",  JOptionPane.YES_NO_OPTION);
        if(a == 0){
            respuesta = suc.eliminar(conn);
            if(respuesta == 1){
                JOptionPane.showMessageDialog(null,"La Sucursal fue Eliminada",null, JOptionPane.INFORMATION_MESSAGE, null);
                try {
                    cargarTabla();
                    vistaDefault();
                } catch (SQLException ex) {
                    Logger.getLogger(pnlSucursales.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Error al Eliminar",null, JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Sucursal suc = new Sucursal();
        suc.setNombre(txtNombre.getText());
        suc.setDireccion(txtDireccion.getText());
        suc.setFono(txtFono.getText());
        suc.setComuna(txtComuna.getText());
        Empresa emp = new Empresa();
        int respuesta = 0;

        if (ddlEmpresa.getSelectedIndex() != 0) {
            emp = (Empresa) ddlEmpresa.getModel().getSelectedItem();
            suc.setEmpresaRut(emp.getRut());
        }

        if (ddlEmpresa.getSelectedIndex() == 0) {
            lblRespuestaEmpresa.setText("Falta Seleccionar la Empresa de la Sucursal");
        }else if(txtId.getText().equals("")){
            int a = JOptionPane.showConfirmDialog(null, "¿Desea Registrar esta Sucursal?", "Message",  JOptionPane.YES_NO_OPTION);
            if(a == 0){
                respuesta = suc.agregar(conn);
                if(respuesta == 1){
                    JOptionPane.showMessageDialog(null,"La Sucursal fue Registrada",null, JOptionPane.INFORMATION_MESSAGE, null);
                    try {
                        cargarTabla();
                        vistaDefault();
                    } catch (SQLException ex) {
                        Logger.getLogger(pnlSucursales.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"La Sucursal ya fue Registrada",null, JOptionPane.ERROR_MESSAGE, null);
                }
            }
        }else{
            suc.setId_sucur(Integer.parseInt(txtId.getText()));
            int a = JOptionPane.showConfirmDialog(null, "¿Desea Modificar esta Sucursal?", "Message",  JOptionPane.YES_NO_OPTION);
            if(a == 0){
                respuesta = suc.modificar(conn);
                if(respuesta == 1){
                    JOptionPane.showMessageDialog(null,"La Sucursal fue Modificada",null, JOptionPane.INFORMATION_MESSAGE, null);
                    try {
                        cargarTabla();
                        vistaDefault();
                    } catch (SQLException ex) {
                        Logger.getLogger(pnlSucursales.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un error al Modificar",null, JOptionPane.ERROR_MESSAGE, null);
                }
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vistaDefault();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSucursalesMouseClicked
        int seleccion = tablaSucursales.getSelectedRow();
        int idSelect = (int) tablaSucursales.getModel().getValueAt(seleccion, 0);
        txtBuscar.setText(String.valueOf(idSelect));
        buscarSucursal();
    }//GEN-LAST:event_tablaSucursalesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNewSucursal;
    private javax.swing.JComboBox<Object> ddlEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRespuestaBuscar;
    private javax.swing.JLabel lblRespuestaEmpresa;
    private javax.swing.JPanel pnSucursal;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JTable tablaSucursales;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtComuna;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() throws SQLException {
        DefaultTableModel modelo =  new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Dirección");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Comuna");
            modelo.addColumn("Rut Empresa");
            
            Sucursal suc = new Sucursal();
            ResultSet rs = suc.listadoSucursales(conn);
            Object [] fila = new Object[6];
            while(rs.next()){
                fila[0] = rs.getInt("ID_SUCUR");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getString("DIRECCION");
                fila[3] = rs.getString("FONO");
                fila[4] = rs.getString("COMUNA");
                fila[5] = rs.getString("EMPRESA_RUT");
                modelo.addRow(fila);
            }
            
            
            tablaSucursales.setModel(modelo);
            tablaSucursales.setVisible(true);
    }

    private void vistaDefault() {
        
        txtDireccion.setEnabled(false);
        txtNombre.setEnabled(false);
        txtFono.setEnabled(false);
        txtComuna.setEnabled(false);
        ddlEmpresa.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtNombre.setText("");
        txtDireccion.setText("");
        txtFono.setText("");
        txtComuna.setText("");
        ddlEmpresa.setSelectedIndex(0);
        txtId.setText("");
        txtBuscar.setText("Ingrese ID de la Sucursal");
        lblRespuestaBuscar.setText("");
        lblRespuestaEmpresa.setText("");
    }

    private void buscarSucursal() {
        Sucursal suc = new Sucursal();
        suc = suc.buscar(Integer.parseInt(txtBuscar.getText()), conn);
        if(!suc.getNombre().equals("ERROR")){
            vistaDefault();
            txtBuscar.setText(String.valueOf(suc.getId_sucur()));
            txtId.setText(String.valueOf(suc.getId_sucur()));
            txtNombre.setText(suc.getNombre());
            txtDireccion.setText(suc.getDireccion());
            txtFono.setText(suc.getFono());
            txtComuna.setText(suc.getComuna());
            
            Empresa emp = new Empresa();
            emp = emp.buscar(suc.getEmpresaRut(), conn);
            ddlEmpresa.getModel().setSelectedItem(emp);
            
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }else if(txtNombre.getText().equals("Ingrese ID de la Sucursal")){
            vistaDefault();
            lblRespuestaBuscar.setText("Ingrese ID antes de Buscar");
        }else{
            lblRespuestaBuscar.setText("No hay registros de la Sucursal con ID: " + txtBuscar.getText());
            vistaDefault();
        }
    }

    private void cargarComboBox() throws SQLException {
        Empresa emp = new Empresa();
        ResultSet rs = emp.listadoEmpresas(conn);
        while (rs.next()) {
            Empresa v_emp = new Empresa();
            v_emp.setRut(rs.getString("RUT"));
            v_emp.setNombre(rs.getString("NOMBRE"));
            v_emp.setDireccion(rs.getString("DIRECCION"));
            v_emp.setRazon_social(rs.getString("RAZON_SOCIAL"));
            ddlEmpresa.addItem(v_emp);
        }
    }
}
