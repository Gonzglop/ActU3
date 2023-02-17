import javax.mail.MessagingException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Busca la imagen en la API y la almaceno en una variable para enviarla por email posteriormente.
        API api = new API();
        String jsonResult = api.searchImage("onigiri");

        // Descarga la imagen usando el archivo JSON obtenido anteriormente.
        Json json = new Json();
        json.downloadImage(jsonResult);

        // Sube la imagen al servidor mediante FTP.
        FTP ftp = new FTP();
        ftp.uploadFile("192.168.0.107", 21, "Gonzalo", "gonzalo",
                "/gonzalo/imagen.jpg",
                "imagen.jpg");

        // Envía la imagen por email.
        String senderEmail = "glopcas222@g.educaand.es";
        String recipientEmail = "rvilbri995@g.educaand.es";
        String password = "olyxujwruausrypz";
        String subject = "Prueba Global Tema 4";

        try {
            String emailMessage = jsonResult;
            Email email = new Email();
            System.out.println("Enviando correo...");
            email.sendMessageWithAttachment(senderEmail, recipientEmail, subject, emailMessage,
                    senderEmail, password, "G:\\Mi unidad\\screenshot.png");

            System.out.println("Correo enviado con éxito.");

        } catch (MessagingException | IOException e) {
            System.out.println("No se ha podido enviar el correo.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}