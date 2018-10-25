/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import MisPaquetes.Conexion;
import MisPaquetes.Usuario;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nicolás
 */
public class MainSistema {

    public static MisPaquetes.Conexion conn = new Conexion();
    static MisPaquetes.Usuario user_conectado = new Usuario();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        conn.Conectar();
        
        //Funcion que realiza el llamado al login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SingIn dialog = new SingIn(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
    }
    
    
    
    
    
    
}
