/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisPaquetes;

import static Sistema.MainSistema.conn;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.SwingWorker;
import pnlsEncargado.pnlOfertas;

/**
 *Clase por la cual se envia correo electronico en segundo plano
 * @author Luis
 */
public class EnviaCorreo extends SwingWorker<Void, Void> {

    /**
     * Atributos de la clase
     */
    private Oferta ofer;
    private File archi;

    /***
     * Mutadores y accesadores
     * @return 
     */
    
    public Oferta getOfer() {
        return ofer;
    }

    public void setOfer(Oferta ofer) {
        this.ofer = ofer;
    }

    public File getArchi() {
        return archi;
    }

    public void setArchi(File archi) {
        this.archi = archi;
    }
    
    /**
     * Metodo abstracto de la clase swingWorker
     * @return
     * @throws Exception 
     */
    @Override
    protected Void doInBackground() throws Exception {
        enviarCorreo();
        return null;
    }
    
    /**
     * Funcion que envia un correo utilizando la api de java javamail
     * @param destinatario del correo
     * @param asunto del correo 
     * @param cuerpo del correo
     */
    private static void enviarConGMail(String destinatario, String asunto, Persona persona, Oferta oferta,File img) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "misofertasant@gmail.com";  //Para la dirección nomcuenta@gmail.com

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "misofertas123");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        
        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart1 = new MimeBodyPart();
            BodyPart messageBodyPart2 = new MimeBodyPart();

            //Zona de texto
            messageBodyPart1.setContent("<h1>Estimado "+ persona.getNombre()+ " "
                    + "hemos subido una nueva oferta con un " +  oferta.getPorc_descuento()+"% de descuento</h1>"
                    + "Puedes obtener mas informacion de nuestra oferta visualizando la imagen que te dejamos adjunta"
                    + "<img src=\"cid:image\">", 
                    "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart1);
            messageBodyPart1 = new MimeBodyPart();
            DataSource source1 = new FileDataSource(img);
            messageBodyPart1.setDataHandler(new DataHandler(source1));
            messageBodyPart1.setHeader("Content-ID","<image>");
            multipart.addBodyPart(messageBodyPart1);
            //Set File
            DataSource source = new FileDataSource(img);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(img.getName());

            //Add "file part" to multipart
            multipart.addBodyPart(messageBodyPart2);

            //Set multipart to message
            message.setContent(multipart);
           
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "misofertasant@gmail.com", "misofertas123");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            String mensaje = "Estimado "+ persona.getNombre()+ " "
                    + "hemos subido una nueva oferta con un " +  oferta.getPorc_descuento()+"% de descuento</h1>"
                    + "Puedes obtener mas informacion de nuestra oferta visualizando la imagen que te dejamos adjunta";
            agregarMensajeria(oferta,persona,asunto,mensaje);
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
    
    /**
     * Funcion que envia un correo a los consumidores del sistema
     */
    public void enviarCorreo() {
        Oferta oferta = this.ofer;
        File archivo = this.archi;
        Persona per = new Persona();
        ResultSet rs1 = per.listadoConsumidores(conn);
        try {
            while (rs1.next()) {
                Persona v_per = new Persona();
                //Definiendo el o los detinatarios del mensaje
                v_per.setEmail(rs1.getString("EMAIL"));
                v_per.setNombre(rs1.getString("NOMBRE"));
                v_per.setRun(rs1.getString("RUN"));
                String destinatario =  v_per.getEmail();
                
                //Definiendo el asunto y cuerpo del correo
                String asunto = "TENEMOS UNA NUEVA OFERTA PARA TI!!!!!";
                
                //llamado al metodo que enviara el correo
                enviarConGMail(destinatario, asunto, v_per,oferta,archivo); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlOfertas.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Funcion que llama al metodo agregar de la clase Mensajeria
     * @param oferta objeto oferta
     * @param persona objeto persona
     * @param asunto asunto del correo enviado
     * @param mensaje mensaje del correo enviado
     */
    private static void agregarMensajeria(Oferta oferta, Persona persona, String asunto, String mensaje) {
        Mensajeria msj = new Mensajeria();
        msj.setAsunto(asunto);
        msj.setMensaje(mensaje);
        msj.setCupon(oferta.getImagen());
        msj.setImgOfer(oferta.getImagen());
        msj.setIdSucur(oferta.getId_sucursal());
        msj.setIfOfer(oferta.getId());
        Consumidor con = new Consumidor();
        con.buscarConsumidorPorRun(persona.getRun(), conn);
        msj.setNomConsumidor(con.getUsername());
        msj.setRunConsumidor(con.getRun());
        int respuesta = msj.agregar(conn);
    }

    

    
    
}
