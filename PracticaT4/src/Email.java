import java.io.File;
import java.io.IOException;
import java.util.Properties;
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
public class Email {
    private Properties properties;
    private Session session;
    private void setPropiedadesServidorSMTP(){
        properties = System.getProperties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable",true);
        session = Session.getInstance(properties,null);
    }
    private Transport conectarServidorSMTP(String direccionEmail, String password) throws MessagingException {
        Transport t = session.getTransport("smtp");
        t.connect(properties.getProperty("mail.smtp.host"),direccionEmail,password);
        return t;
    }
    private Message crearNucleoMensaje(String emisor, String destinario, String asunto) throws MessagingException {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(emisor));
        mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(destinario));
        mensaje.setSubject(asunto);
        return mensaje;
    }
    private Message crearMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String pathFichero) throws MessagingException, IOException {
        Message mensaje = crearNucleoMensaje(emisor,destinatario,asunto);
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(textoMensaje);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.attachFile(new File(pathFichero));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(mimeBodyPart);
        mensaje.setContent(multipart);
        return mensaje;
    }
    public void enviarMensajeConAdjunto(String emisor, String destinatario, String asunto, String textoMensaje, String direccionEmail, String password,String pathFichero) throws MessagingException, IOException {
        setPropiedadesServidorSMTP();
        Message mensaje = crearMensajeConAdjunto(emisor,destinatario,asunto,textoMensaje,pathFichero);
        Transport t = conectarServidorSMTP(direccionEmail,password);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }
}
