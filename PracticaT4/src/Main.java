import javax.mail.MessagingException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws MessagingException {

        // obtengo el archivo json de la imagen y lo almaceno en una variable para posteriormente enviarlo por email.
        API api = new API();

        String cadenaJson = api.buscarImagen("onigiri");

        Json json = new Json();
        try {
            json.gestionaJson(cadenaJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // subo la imagen al servidor


        FTP ftp = new FTP();
        ftp.subirArchivo("192.168.0.107", 21, "Gonzalo", "gonzalo",
                "/gonzalo/imagenPrueba.jpg",
                "imagenPrueba.jpg");


        //envio la imagen por email
        String emailEmisor = "glopcas222@g.educaand.es";
        String emailDestinatario = "gonz.g.lop@gmail.com"; //rvilbri995@g.educaand.es
        String password = "olyxujwruausrypz";
        String asunto = "Prueba Global Tema 4";

        try {
            String mensajeEmail = cadenaJson;
            Email email = new Email();
            email.enviarMensajeConAdjunto(emailEmisor, emailDestinatario, asunto, mensajeEmail, emailEmisor, password, "G:\\Mi unidad\\screenshot.png");
            System.out.println("Correo enviado con éxito.");

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}