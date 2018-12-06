/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pnlsAdministrador;

import MisPaquetes.Oferta;
import static Sistema.MainSistema.conn;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pnlsEncargado.*;

/**
 *
 * @author 
 */
public class pnlBI extends javax.swing.JPanel {

    /**
     * Creates new form pnlHome
     */
    public pnlBI() {
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

        jPanel1 = new javax.swing.JPanel();
        pnlOferta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cldFechaInicio = new com.toedter.calendar.JDateChooser();
        cldFechaTermino = new com.toedter.calendar.JDateChooser();
        btnAgregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(842, 677));

        pnlOferta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 3), "Solicitud De Documento Para BI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Ingrese las fechas sobre las que necesita que se genere el docuento con la informacion de las ofertas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Fecha Termino:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fecha Inicio:");

        cldFechaInicio.setMaxSelectableDate(new java.util.Date(4102459276000L));
        cldFechaInicio.setMinSelectableDate(new java.util.Date(-62135751524000L));
        cldFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cldFechaInicioPropertyChange(evt);
            }
        });

        cldFechaTermino.setMaxSelectableDate(new java.util.Date(4102459276000L));
        cldFechaTermino.setMinSelectableDate(new java.util.Date(-62135751524000L));

        javax.swing.GroupLayout pnlOfertaLayout = new javax.swing.GroupLayout(pnlOferta);
        pnlOferta.setLayout(pnlOfertaLayout);
        pnlOfertaLayout.setHorizontalGroup(
            pnlOfertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOfertaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(67, 67, 67)
                .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jLabel2)
                .addGap(90, 90, 90)
                .addComponent(cldFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
            .addGroup(pnlOfertaLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOfertaLayout.setVerticalGroup(
            pnlOfertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOfertaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(pnlOfertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOfertaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlOfertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(cldFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlOfertaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlOfertaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setText("Generar Documento");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlOferta, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(93, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(429, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlOferta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(492, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(218, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cldFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cldFechaInicioPropertyChange
        // TODO add your handling code here:
        if (!(cldFechaInicio.getDate() == null)) {
            cldFechaTermino.setMinSelectableDate(cldFechaInicio.getDate());
        }
    }//GEN-LAST:event_cldFechaInicioPropertyChange

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try{
            Oferta ofer = new Oferta();
            if (!validarfechas()) {
                throw new Exception("Ingrese la fecha de inicio y la fecha de termino de las ofertas a consultar");
            }
            java.util.Date utilDate = (java.util.Date) cldFechaInicio.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ofer.setFecha_inicio(sqlDate);
            utilDate = (java.util.Date) cldFechaTermino.getDate();
            sqlDate = new java.sql.Date(utilDate.getTime());
            ofer.setFecha_termino(sqlDate);
            String cadena = "";
            
            ResultSet rs = ofer.solicitudBI(conn);
            while(rs.next()){
                cadena += rs.getInt("ID") + " " + rs.getString("DESCRIPCION")+ " " + rs.getInt("DESCUENTO")+ " " + rs.getDate("FECHA")+ " " + rs.getInt("VALORACION")+ " " + rs.getString("RUN") + ";";
            }
            /*"D:\\Documentos\\documento.txt"*/
            BufferedWriter writer = new BufferedWriter( new FileWriter(".\\documento.txt"));
            writer.write(cadena);
            writer.close();
            JOptionPane.showMessageDialog(null,"El documento fue generado revisar en la carpeta especificada por la empresa desarrolladora",null, JOptionPane.INFORMATION_MESSAGE, null);
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"" + ex.getMessage(),null, JOptionPane.INFORMATION_MESSAGE, null);
            }
        
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private com.toedter.calendar.JDateChooser cldFechaInicio;
    private com.toedter.calendar.JDateChooser cldFechaTermino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlOferta;
    // End of variables declaration//GEN-END:variables

    private boolean validarfechas() {
        boolean validar = true;
        if (cldFechaInicio.getDate() == null) {
            validar = false;
        }
        if (cldFechaTermino.getDate() == null) {
            validar = false;
        }
        return validar;
    }
}
