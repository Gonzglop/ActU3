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

    private void setSMTPProperties() {
        // Obtiene las propiedades del sistema
        properties = System.getProperties();
        // Establece las propiedades del servidor SMTP
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", true);
        session = Session.getInstance(properties, null);
    }

    private Transport connectSMTPServer(String email, String password) throws MessagingException {
        // Obtiene una instancia de la sesión SMTP y se conecta al servidor SMTP
        Transport transport = session.getTransport("smtp");
        transport.connect(properties.getProperty("mail.smtp.host"), email, password);
        return transport;
    }

    private Message createMessageCore(String sender, String recipient, String subject) throws MessagingException {
        // Crea un mensaje con remitente, destinatario y asunto
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        return message;
    }

    private Message createMessageWithAttachment(String sender, String recipient, String subject, String messageText, String filePath) throws MessagingException, IOException {
        // Crea el núcleo del mensaje con texto y archivo adjunto
        Message message = createMessageCore(sender, recipient, subject);
        BodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(messageText);
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File(filePath));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        message.setContent(multipart);
        return message;
    }

    public void sendMessageWithAttachment(String sender, String recipient, String subject, String messageText, String email, String password, String filePath) throws MessagingException, IOException {
        // Envía el mensaje con el archivo adjunto
        setSMTPProperties();
        Message message = createMessageWithAttachment(sender, recipient, subject, messageText, filePath);
        Transport transport = connectSMTPServer(email, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
